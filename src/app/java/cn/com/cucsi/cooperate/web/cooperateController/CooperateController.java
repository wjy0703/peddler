package cn.com.cucsi.cooperate.web.cooperateController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.JksqStateUtils;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.cooperate.entity.p2p.P2pLendInfo;
import cn.com.cucsi.cooperate.service.cooperateManage.CooperateManager;
import cn.com.cucsi.cooperate.service.webServiceClientManage.ServiceClientManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/cooperate")
public class CooperateController {

	private Logger logger = LoggerFactory.getLogger(CooperateController.class);
	
	private CooperateManager cooperateManager;
	
	private BaseInfoManager baseInfoManager;
	
	private XhJksqManager xhjksqManager;
	
	private ServiceClientManager serviceClientManager;

	@Autowired
	public void setServiceClientManager(ServiceClientManager serviceClientManager) {
		this.serviceClientManager = serviceClientManager;
	}

	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setCooperateManager(CooperateManager cooperateManager) {
		this.cooperateManager = cooperateManager;
	}
	
	/**
	 * 标的列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listTarget")
	public String listTarget(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("P2P借款标的列表");
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		JdbcPage page = JdbcPageUtils.generatePage(request);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);

		// 贷款标志
		map.put("backup01", "P2P");
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq("P2pApplyJksqList", map, page);

		for (int index = 0; index < listJksq.size(); index++) {
			Map<String, Object> jksq = listJksq.get(index);
			jksq.put("canEdit", JksqStateUtils.isCanChange(jksq));
			jksq.put("canApplyChange", JksqStateUtils.isApplyChange(jksq));
		}

		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
		model.addAttribute("organiName", request.getParameter("filter_organi.name"));

		RequestPageUtils.fillModelWithRequest(model, request);
		model.addAttribute("map", map);

		return "cooperate/target/targetList";
	}
	
	/**
	 * 借款标的发布
	 * @param Id
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/issuanceClient/{Id}")
    public String issuanceClient(@PathVariable Long Id, HttpServletRequest request, HttpServletResponse response) {
    	logger.info("借款标的发布");
    	String code = "300";
    	String msg = "发布失败，请联系线上P2P技术人员！";
    	Integer ret = serviceClientManager.serviceClient(Id);
    	if(ret == 1){
    		code = "200";
    		msg = "发布成功，请等待线上P2p招标！";
    	}else if(ret == 99){
    		msg = "发布失败，标的数据异常！";
    	}
        DwzResult success = new DwzResult(code, msg, "rel_listTarget", "", "", "");
        ServletUtils.renderJson(response, success);

        return null;
    }
    
    /**
     * 初始化上传声明书
     * @param Id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/InitUpLoadStatement/{Id}")
	public String InitUpLoadStatement(@PathVariable Long Id, HttpServletRequest request, Model model) {
    	logger.info("初始化上传声明书");
		request.setAttribute("Id", Id);
		request.setAttribute("upUrl", "cooperate/upLoadP2PFile");
		return "cooperate/target/upLoadStatement";
	}
    
    /**
	 * 上传声明书 只上传文件不做数据库操作
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadP2PFile")
	public void upLoadP2PFile(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("上传声明书");
		List<Map<String, String>> fileName;
		try {
			fileName = PropertiesUtils.upFileFullFunctions(request,"upload");
			ServletUtils.renderJson(response, fileName);
		} catch (Exception e) {
			ServletUtils.renderJson(response, "0");
		}
	}
    
    /**
	 * 上传声明书写数据库
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadP2PFileBack")
	public void upLoadP2PFileBack(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("上传声明书写数据库");
		String id = request.getParameter("id");
		String files = request.getParameter("fileName");
		List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
		XhJksq jksq = xhjksqManager.getXhJksq(Long.parseLong(id));
		if(null != jksq && "1000".equals(jksq.getState())){
			xhjksqManager.uploadAndChangeState(id, fileName, "1001", "P2P", "XH_JKSQ");
		}
		ServletUtils.renderJson(response, "1");
	}
	
	/**
	 * 查看已上传文件
	 * 
	 * @param Id 借款申请ID
	 * @param upFlag 上传文件类型
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/lookUpLoadFile/{Id},{upFlag}")
	public String seeUpLoadFile(@PathVariable Long Id, @PathVariable String upFlag, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("已传声明书列表");
		if (null != upFlag && !"".equals(upFlag)) {
			String[] upflag = upFlag.split(":");
			for (int i = 0; i < upflag.length; i++) {
				if ("P2P".equals(upflag[i].trim())) {// p2p声明书
					upflag[i] = "P2P";
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(Id, upflag[i]);
					model.addAttribute("waifang", files);
				}
			}
		}
		return "cooperate/target/lookUploadFile";
	}
}
