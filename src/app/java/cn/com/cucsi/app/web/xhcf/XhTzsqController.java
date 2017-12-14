package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhTzsqHistory;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.CjrxxManager;
import cn.com.cucsi.app.service.xhcf.JyglManager;
import cn.com.cucsi.app.service.xhcf.XhTzsqManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
/**
 * 投资申请管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/xhTzsq")
public class XhTzsqController { 
    @Autowired
	private CjrxxManager cjrxxManager;
    @Autowired
    private BaseInfoManager baseInfoManager;
    @Autowired
    private XhTzsqManager xhTzsqManager;
    @Autowired
    private JyglManager jyglManager;
	
    //--------------------------------------投资申请        开始-----------------------------------
	@RequestMapping(value="/listXhTzsq")
	public String listXhTzsq(HttpServletRequest request, Model model){
		//得到当前登录用户
		
	    // 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("startdate", request.getParameter("filter_startdate"));
		map.put("enddate", request.getParameter("filter_enddate"));
		map.put("filter_date", request.getParameter("filter_date"));
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		Map<String, Object> mapYyb = new HashMap<String, Object>();
		mapYyb.put("cjrState", "1");
		mapYyb.put("state", "2");
		List<Map<String,Object>> listYyb = cjrxxManager.searchYyb("queryYybList", mapYyb);
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("page", page);
		return "tzsq/xhTzsqIndex";
	}
	
	@RequestMapping(value="/saveXhTzsq",method=RequestMethod.POST)
	public String saveXhTzsq(@ModelAttribute("xhTzsq") XhTzsq xhTzsq, HttpServletRequest request, HttpServletResponse response){
		if (xhTzsq.getCjrxx() != null && xhTzsq.getCjrxx().getId()!=null){
			xhTzsq.setCjrxx(cjrxxManager.getCjrxx(xhTzsq.getCjrxx().getId()));
			xhTzsq.setEmployeeCca(xhTzsq.getCjrxx().getEmployeeCca());
			xhTzsq.setEmployeeCrm(xhTzsq.getCjrxx().getEmployeeCrm());
		}
		else{
			xhTzsq.setCjrxx(null);
		}
		if (xhTzsq.getTzcp() != null && xhTzsq.getTzcp().getId()!=null){
			Tzcp cp = baseInfoManager.getCp(xhTzsq.getTzcp().getId());
			xhTzsq.setTzcp(cp);
			xhTzsq.setTzfs(cp.getTzcpMc());
			xhTzsq.setHsfs(cp.getTzcpMc());
			xhTzsq.setLastCjzq(cp.getTzcpZq());
			xhTzsq.setCjzq(cp.getTzcpZq());
		}
		else{
			xhTzsq.setTzcp(null);
		}
		String tzfkyhmc= request.getParameter("cjrxx.tzfkyhmc");
		String tzfkkhmc= request.getParameter("cjrxx.tzfkkhmc");
		String tzfkyhzh= request.getParameter("cjrxx.tzfkyhzh");
		
		String hszjyhmc= request.getParameter("cjrxx.hszjyhmc");
		String hszjkhmc= request.getParameter("cjrxx.hszjkhmc");
		String hszjyhzh= request.getParameter("cjrxx.hszjyhzh");
		//String sqtype = request.getParameter("sqfs");
		//xhTzsq.setSqtype(sqtype);
		xhTzsq.setTzfkkhmc(tzfkkhmc);
		xhTzsq.setTzfkyhmc(tzfkyhmc);
		xhTzsq.setTzfkyhzh(tzfkyhzh);
		
		xhTzsq.setHszjkhmc(hszjkhmc);
		xhTzsq.setHszjyhmc(hszjyhmc);
		xhTzsq.setHszjyhzh(hszjyhzh);
		
		Employee emp = baseInfoManager.getUserEmployee();
		xhTzsq.setOrgani(emp.getOrgani());
//		xhTzsq.setState("0");
		xhTzsq.setUpstate("-1");
		xhTzsq.setOverstate("0");
		
		if(null != xhTzsq.getId() && !"".equals(xhTzsq.getId())&& "1".equals(xhTzsq.getState())){
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("fileOwner", "XH_TZSQ");
			filters.put("mainId", xhTzsq.getId());
			List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
			if(files.size() >0){
				xhTzsq.setState("8");
			}
		}
		xhTzsq.setXszklyxqx(CreditHarmonyComputeUtilties.getXszklyxqx(xhTzsq.getJhtzrq(),
														xhTzsq.getTzcp().getId()));
		xhTzsq.setFirstdate(CreditHarmonyComputeUtilties.getFirstdate(xhTzsq.getJhtzrq()));
		xhTzsqManager.saveXhTzsq(xhTzsq);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhTzsq","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhTzsq", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		initialize1(request);
		return new ModelAndView("tzsq/xhTzsqInput", "xhTzsq", new XhTzsq());
	}
	
	@RequestMapping(value="/editXhTzsq/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id,HttpServletRequest request){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		initialize1(request);
		return new ModelAndView("tzsq/xhTzsqInput", "xhTzsq", xhTzsq);
	}
	/**
	 * 投资申请-提交待审批
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/subXhTzsq/{Id}")
	public String subXhTzsq(@PathVariable Long Id, HttpServletResponse response){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_TZSQ");
		filters.put("mainId", xhTzsq.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		if(files.size() >0){
			xhTzsq.setState("8");
		}else{
			xhTzsq.setState("1");
		}
		xhTzsqManager.saveXhTzsq(xhTzsq);
		DwzResult success = new DwzResult("200","提交成功","rel_listXhTzsq","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	/**
	 * 投资申请-查看
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lookOutTzsq/{Id}", method=RequestMethod.GET)
	public ModelAndView lookOutTzsq(@PathVariable Long Id,HttpServletRequest request){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		initialize1(request);
		return new ModelAndView("tzsq/lookOutTzsq", "xhTzsq", xhTzsq);
	}
	
	@RequestMapping(value="/delXhTzsq/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhTzsqManager.deleteXhTzsq(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhTzsq","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//债款回款明细复查
	@RequestMapping(value="/checkXhTzsqMoney/{Id}")
	public String checkXhTzsqMoney(@PathVariable Long Id, HttpServletResponse response){
		xhTzsqManager.checkXhTzsqMoney(Id);
		DwzResult success = new DwzResult("200","复查成功","rel_listXhTzsq","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	@RequestMapping(value="/batchdelXhTzsq")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhTzsqManager.batchDelXhTzsq(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhTzsq","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhTzsq","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	
	@RequestMapping(value="/overTzsq")
	public ModelAndView overTzsq(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		
		String date = DateUtils.format(new Date(), "yyyy-MM-dd");
		request.setAttribute("shrq", date);
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Long.valueOf(id));
		initialize1(request);
		return new ModelAndView("tzsq/overTzsqInput", "xhTzsq", xhTzsq);
	}
	@RequestMapping(value="/overTzsqSave")
	public String overTzsqSave(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		String shrq = request.getParameter("shrq");
		xhTzsqManager.overXhTzsq(Long.valueOf(id),shrq);
		DwzResult success = new DwzResult("200","赎回成功","rel_listXhTzsq","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 投资申请-发送首期债权
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sendSqZqtj")
	public String sendSqZqtj(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
	
		String error = xhTzsqManager.saveXhSendemail(id);
		
		String code = "200";
		String message = "发送首期债权成功";
		if(!error.equals("0")){
			code = "300";
			message = error;
		}
		DwzResult success = new DwzResult(code,message,"rel_listXhTzsq","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	//--------------------------------------投资申请       结束-----------------------------------
	//--------------------------------------综合查询        开始-----------------------------------
		@RequestMapping(value="/IntegrativeQuery")
		public String IntegrativeQuery(HttpServletRequest request, Model model){
			//得到当前登录用户
			
		    // 处理分页的参数
			JdbcPage page = JdbcPageUtils.generatePage(request);
			Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//			Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
			//过滤查询内容，所需条件   ----开始
			
			map.put("startdate", request.getParameter("filter_startdate"));
			map.put("enddate", request.getParameter("filter_enddate"));
			map.put("filter_date", request.getParameter("filter_date"));
			Employee emp = baseInfoManager.getUserEmployee();
			OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
			String loginName = operator.getCtiCode();
			map.put("loginName", loginName);
			map.put("emp", emp);
			//过滤查询内容，所需条件   ----结束
			List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
			
			model.addAttribute("listTzsq", listTzsq);
			model.addAttribute("map", map);
			Map<String, Object> mapYyb = new HashMap<String, Object>();
			mapYyb.put("cjrState", "1");
			mapYyb.put("state", "2");
			List<Map<String,Object>> listYyb = cjrxxManager.searchYyb("queryYybList", mapYyb);
			model.addAttribute("listYyb", listYyb);
			model.addAttribute("page", page);
			return "tzsq/integrativeQuery";
		}
	
	//--------------------------------------投资审批        开始-----------------------------------
	/**
	 * 投资审批
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAuditTzsq")
	public String listAuditTzsq(HttpServletRequest request, Model model){
		
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除， 
		if(map.containsKey("state")){
			String value = String.valueOf(map.get("state"));
			if(StringUtils.isEmpty(value)) {
				map.put("state", "8");
			}
		}else{
			map.put("state", "8");
		}
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
	
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/auditTzsqIndex";
		
	}
	
	/**
	 * 投资审批-进入投资审批页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/auditTzsq/{Id}", method=RequestMethod.GET)
	public synchronized ModelAndView auditTzsq(@PathVariable Long Id, HttpServletRequest request){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		XhcfCjrxx cjr = cjrxxManager.getCjrxx(xhTzsq.getCjrxx().getId());
		String cjrkhbm = cjr.getKhbm();
		Organi org = baseInfoManager.gerOrgani(cjr.getEmployeeCrm().getOrgani().getParentId());
		String orgcode = org.getOrganiCode();
		String[] orgcodes = orgcode.split("-");
		if(null == cjrkhbm || "".equals(cjrkhbm)){
			//客户编号
			String khbm = "K2";
			//地区：0000（区号，前补0）
			//khbm += baseInfoManager.getAreaCode(Long.parseLong(cjrxxFind.getCity()));
			khbm += orgcodes[0];
			//营业部：00
			//khbm += orgcodes[1];
			//0000
//			khbm += baseInfoManager.getSequenceFro("CJRXX_KHBM_SEQUENCE");
			khbm += baseInfoManager.getKhXh("xh_cjrxx", "khbm", khbm);
			cjrkhbm = khbm;
			cjr.setKhbm(khbm);
			cjrxxManager.saveCjrxx(cjr);
			xhTzsq.setCjrxx(cjr);
		}
		if(null == xhTzsq.getTzsqbh() || "".equals(xhTzsq.getTzsqbh())){
			String sqbh = "";
			String bh = "";
			//产品编号：8x
			sqbh += xhTzsq.getTzcp().getTzcpBh();
			//地区：0000（区号，前补0）
			bh += cjrkhbm.substring(2, 6);
//			bh += baseInfoManager.getAreaCode(Long.parseLong(xhTzsqFind.getCjrxx().getCity()));
			//营业部：00
//			sqbh += xhTzsqFind.getCjrxx().getOrgani().getOrganiCode();
			bh += orgcodes[1];
			//客户编号=地区：0000+营业部：00+0000
			//bh += cjrkhbm.substring(6, cjrkhbm.length());
			bh += baseInfoManager.getCjXh( bh,cjr.getId()+"");
			//中间加入次数000
			sqbh += bh + "-" + baseInfoManager.getXh("XH_TZSQ", "tzsqbh", bh);
			xhTzsq.setTzsqbh(sqbh.trim());
			xhTzsqManager.saveXhTzsq(xhTzsq);
		}
		initialize1(request);
		return new ModelAndView("tzsq/auditTzsqInput", "xhTzsq", xhTzsq);
	}
	
	/**
	 * 投资审批 -保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveTzsqAudit",method=RequestMethod.POST)
	public String saveTzsqAudit(@ModelAttribute("xhTzsq") XhTzsq xhTzsq, HttpServletRequest request, HttpServletResponse response){
		XhTzsq xhTzsqFind = xhTzsqManager.getXhTzsq(xhTzsq.getId());
		Employee emp = baseInfoManager.getUserEmployee();
		xhTzsqFind.setAuditPerson(emp.getName());
		xhTzsqFind.setAuditIdea(xhTzsq.getAuditIdea());
		xhTzsqFind.setState(xhTzsq.getState());
		xhTzsqFind.setAuditTime(new Timestamp(new Date().getTime()));
		//xhTzsqManager.saveXhTzsq(xhTzsqFind);
		String sqbh = "";
		String bh = "";
		sqbh = request.getParameter("bianma");
		
		XhcfCjrxx cjr = cjrxxManager.getCjrxx(xhTzsqFind.getCjrxx().getId());
		String khbm = request.getParameter("khbmbianma");
		if(xhTzsq.getState().equals("2")){
			cjr.setKhbm(khbm.trim());
			//产品编号：8x
//			sqbh += xhTzsqFind.getTzcp().getTzcpBh();
			//地区：0000（区号，前补0）
//			bh += baseInfoManager.getAreaCode(Long.parseLong(xhTzsqFind.getCjrxx().getCity()));
			//营业部：00
//			sqbh += xhTzsqFind.getCjrxx().getOrgani().getOrganiCode();
//			bh += xhTzsqFind.getCjrxx().getOrgani().getOrganiCode();
			//客户编号=地区：0000+营业部：00+0000
//			sqbh += xhTzsqFind.getCjrxx().getKhbm();
			//中间加入次数000
//			sqbh += "-" + baseInfoManager.getXh("XH_TZSQ", "tzsqbh", bh);
			System.out.println("sqbh==========>"+sqbh);
			xhTzsqFind.setHkstate("0");
			xhTzsqFind.setTzsqbh(sqbh.trim());
			xhTzsqFind.setSghkrq(CreditHarmonyComputeUtilties.getFirstDateOfBackMoney(xhTzsqFind.getJhtzrq()));
			xhTzsqFind.setZdr(CreditHarmonyComputeUtilties.getZdr(xhTzsqFind.getSghkrq()));
			//审批通过，备份一次历史信息
			XhTzsqHistory uch = new XhTzsqHistory(xhTzsqFind);
			xhTzsqManager.saveXhTzsqHistory(uch);
			cjrxxManager.saveCjrxx(cjr);
		}else{
			xhTzsqFind.setTzsqbh("");
		}
		DwzResult success ;
		try {
			xhTzsqManager.auditXhTzsq(xhTzsqFind);
			success = new DwzResult("200","保存成功","rel_listAuditTzsq","","closeCurrent","");
		} catch (Exception e) {
			e.printStackTrace();
			success = new DwzResult("200","保存失败","rel_listAuditTzsq","","closeCurrent","");
		}
		//如果投资申请审核通过则向资金流水表添加一条记录
//		if(xhTzsq.getState().equals("2")){
//			XhLentmoneywater xhLentmoneywater = new XhLentmoneywater();
//			xhLentmoneywater.setJhtzrq(xhTzsqFind.getJhtzrq());
//			xhLentmoneywater.setMoney(Double.parseDouble(xhTzsqFind.getJhtzje()));
//			xhLentmoneywater.setState("0");
//			xhLentmoneywater.setXhTzsq(xhTzsqFind);
//			xhTzsqManager.saveXhLentmoneywater(xhLentmoneywater);
//		}
		//审批通过，备份一次历史信息
//		if(xhTzsq.getState().equals("2")){
//			UpdateCjrxxHistory uch = new UpdateCjrxxHistory(cjrxxFind);
//			cjrxxManager.saveUpdateCjrxx(uch);
//		}
//		DwzResult success = new DwzResult("200","保存成功","rel_listAuditTzsq","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	//--------------------------------------投资划扣        开始-----------------------------------
	/**
	 * 投资划扣
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listHuaKouTzsq")
	public String listHuaKouTzsq(HttpServletRequest request, Model model){
		
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//			Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
//		Employee emp = baseInfoManager.getUserEmployee();
//		String loginName = operator.getCtiCode();
//		map.put("loginName", loginName);
//		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除， 
		if(map.containsKey("state")){
			String value = String.valueOf(map.get("state"));
			if(StringUtils.isEmpty(value)) {
				map.put("state", "9");
			}
		}else{
			map.put("state", "9");
		}
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
	
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/huakouTzsqIndex";
		
	}
	/**
	 * 投资申请-提交划扣
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/canZqtj/{Id}", method=RequestMethod.GET)
	public ModelAndView canZqtj(@PathVariable Long Id, HttpServletResponse response,HttpServletRequest request){
	
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
//		xhTzsq.setState("2");
//		xhTzsqManager.auditXhTzsq(xhTzsq);
//		DwzResult success = new DwzResult("200","提交成功","rel_listAuditTzsq","","","");
//		ServletUtils.renderJson(response, success);
//		return null;
		initialize1(request);
		return new ModelAndView("tzsq/huakouTzsqInput", "xhTzsq", xhTzsq);
	}
	
	/**
	 * 投资划扣-保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveTzsqHuakou",method=RequestMethod.POST)
	public String saveTzsqHuakou(@ModelAttribute("xhTzsq") XhTzsq xhTzsq, HttpServletRequest request, HttpServletResponse response){
		XhTzsq xhTzsqFind = xhTzsqManager.getXhTzsq(xhTzsq.getId());
		xhTzsqFind.setState(xhTzsq.getState());
		//xhTzsqManager.saveXhTzsq(xhTzsqFind);
		
		if(xhTzsq.getState().equals("2")){
			xhTzsqFind.setJhhkrq(xhTzsq.getJhhkrq());
			xhTzsqFind.setJhtzrq(xhTzsq.getJhtzrq());
			xhTzsqFind.setSghkrq(CreditHarmonyComputeUtilties.getFirstDateOfBackMoney(xhTzsqFind.getJhtzrq()));
			xhTzsqFind.setZdr(CreditHarmonyComputeUtilties.getZdr(xhTzsqFind.getSghkrq()));
		}
		DwzResult success ;
		try {
			xhTzsqManager.auditXhTzsq(xhTzsqFind);
			success = new DwzResult("200","划扣成功","rel_listHuaKouTzsq","","closeCurrent","");
		} catch (Exception e) {
			e.printStackTrace();
			success = new DwzResult("200","划扣失败","rel_listHuaKouTzsq","","closeCurrent","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	//--------------------------------------投资审批       结束-----------------------------------
	
	//--------------------------------------投资变更申请        开始-----------------------------------
	/**
	 * 投资变更申请
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listChangeTzsq")
	public String listChangeTzsq(HttpServletRequest request, Model model){
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("state", "2");
		//map.put("lentcount", "1");//查询资金流水记录为1的，大于1的则不允许提交变更申请
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/changeTzsqIndex";
	}
	
	/**
	 * 投资变更申请-进入变更申请页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/changeTzsq/{Id}", method=RequestMethod.GET)
	public ModelAndView changeTzsq(@PathVariable Long Id,HttpServletRequest request){
		String lentcount = request.getParameter("lentcount");
		System.out.println("lentcount====>" + lentcount);
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		XhTzsqHistory xth;
		//如果主表的变更状态为-1，则新建并初始化变更实体
		if(!xhTzsq.getUpstate().equals("-1")){
			xth = xhTzsq.getHistory();
			Map<String, Object> filters = new HashMap<String,Object>();
			filters.put("fileOwner", "XH_TZSQ_UPHISTORY");
			filters.put("mainId", xth.getId());
			List<XhUploadFiles> findXhUploadFiles = baseInfoManager.findXhUploadFiles(filters);
			if(findXhUploadFiles.size() > 0){
				request.setAttribute("xhUploadFiles", findXhUploadFiles.get(0));
			}
		}else{
			xth = new XhTzsqHistory(xhTzsq);
		}
		request.setAttribute("lentcount", lentcount);
		initialize1(request);
		return new ModelAndView("tzsq/changeTzsqInput", "xhTzsq", xth);
	}
	
	/**
	 * 出借人信息变更-保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveTzsqChange",method=RequestMethod.POST)
	public String saveTzsqChange(@ModelAttribute("xhTzsq") XhTzsqHistory xhTzsq, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		XhTzsq xhTzsqFind = xhTzsqManager.getXhTzsq(xhTzsq.getXhTzsq().getId());
		//保存变更信息
		XhTzsqHistory xthc;
		if(xhTzsq.getId() != null && !xhTzsq.getId().equals("")){
			xthc = xhTzsqManager.getXhTzsqHistory(xhTzsq.getId());
		}else{
			xthc = new XhTzsqHistory(xhTzsqFind);
		}
		if (xhTzsq.getTzcp() != null && xhTzsq.getTzcp().getId()!=null){
			Tzcp cp = baseInfoManager.getCp(xhTzsq.getTzcp().getId());
			xthc.setTzcp(cp);
			xthc.setTzfs(cp.getTzcpMc());
			xthc.setHsfs(cp.getTzcpMc());
			xthc.setLastCjzq(cp.getTzcpZq());
			xthc.setCjzq(cp.getTzcpZq());
		}
		else{
			xthc.setTzcp(null);
		}
		
		//将变更实体信息替换表单信息
		xthc.toXhTzsqHistory(xhTzsq);
		xthc.setUpstate(xhTzsq.getState());
		xhTzsqManager.saveXhTzsqHistory(xthc);
		//保存主表信息
		xhTzsqFind.setHistory(xthc);
		xhTzsqFind.setUpstate(xhTzsq.getState());
		xhTzsqManager.saveXhTzsq(xhTzsqFind);
		//List<Map<String,String>> fileName = PropertiesUtils.upFile(request, "upload");
		String fileName =request.getParameter("fileName");
		String newFileName =request.getParameter("newFileName");
		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if(StringUtils.isNotEmpty(fileName)){
			//for(Map<String,String> m:fileName){
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(fileName);
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(newFileName);
				xhUploadFiles.setFileOwner("XH_TZSQ_UPHISTORY");
				xhUploadFiles.setMainId(xthc.getId());
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
		//	}
		}
		
		DwzResult success = new DwzResult("200","保存成功","rel_listXhTzsq","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	/**
	 * 出借人信息变更-提交待审批
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/subTzsqChange/{Id}")
	public String subTzsqChange(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		//设置变更状态为待审批
		xhTzsq.setUpstate("1");
		xhTzsqManager.saveXhTzsq(xhTzsq);
		DwzResult success = new DwzResult("200","提交成功","rel_listChangeTzsq","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	//--------------------------------------投资变更申请       结束-----------------------------------
	
	//--------------------------------------投资变更审批        开始-----------------------------------
	/**
	 * 投资变更审批
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listChangeAuditTzsq")
	public String listChangeAuditTzsq(HttpServletRequest request, Model model){
		
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("state", "2");
		map.put("upstate", "1");
		//map.put("lentcount", "1");//查询资金流水记录为1的，大于1的则不允许提交变更申请
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/changeAuditTzsqIndex";
		
	}
	
	/**
	 * 投资变更审批-进入变更审批页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/changeAuditTzsq/{Id}", method=RequestMethod.GET)
	public ModelAndView changeAuditTzsq(@PathVariable Long Id,HttpServletRequest request){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		XhTzsqHistory xth = xhTzsq.getHistory();
		Map<String, Object> filters = new HashMap<String,Object>();
		filters.put("fileOwner", "XH_TZSQ_UPHISTORY");
		filters.put("mainId", xth.getId());
		List<XhUploadFiles> findXhUploadFiles = baseInfoManager.findXhUploadFiles(filters);
		if(findXhUploadFiles.size() > 0){
			request.setAttribute("xhUploadFiles", findXhUploadFiles.get(0));
		}
		initialize1(request);
		return new ModelAndView("tzsq/changeAuditTzsqInput", "xhTzsq", xth);
	}
	
	/**
	 * 出借人信息变更审批-审批保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveTzsqChangeAudit",method=RequestMethod.POST)
	public String saveTzsqChangeAudit(@ModelAttribute("xhTzsq") XhTzsqHistory xhTzsq, HttpServletRequest request, HttpServletResponse response){
		Employee emp = baseInfoManager.getUserEmployee();
		XhTzsqHistory uch = xhTzsqManager.getXhTzsqHistory(xhTzsq.getId());
		uch.setUpstate(xhTzsq.getUpstate());
		uch.setAuditIdea(xhTzsq.getAuditIdea());
		uch.setAuditPerson(emp.getName());
		uch.setAuditTime(new Timestamp(new Date().getTime()));
		xhTzsqManager.saveXhTzsqHistory(uch);
		XhTzsq cjrxxFind = xhTzsqManager.getXhTzsq(xhTzsq.getXhTzsq().getId());
		if(xhTzsq.getUpstate().equals("3")){
			//审批不通过，则只修改主表的变更状态
			cjrxxFind.setUpstate("3");
			
		}else{
			//审批通过，则将变更表信息复制给主表，同时主表变更状态设置-1
			cjrxxFind.setXhTzsq(uch);
			cjrxxFind.setUpstate("-1");
		}
		xhTzsqManager.auditXhTzsqChange(cjrxxFind);
		DwzResult success = new DwzResult("200","保存成功","rel_listChangeAuditTzsq","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	//--------------------------------------投资变更审批      结束-----------------------------------
	
	
	//--------------------------------------债权转让申请        开始-----------------------------------
	/**
	 * 债权转让申请
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listZhuanRaTzsq")
	public String listZhuanRaTzsq(HttpServletRequest request, Model model){
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("state", "2");
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
	
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/zhuanRaTzsqIndex";
		
	}
	
	/**
	 * 债权转让申请-进入债权转让申请页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/zhuanRaTzsq/{Id}", method=RequestMethod.GET)
	public ModelAndView zhuanRaTzsq(@PathVariable Long Id,HttpServletRequest request){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		initialize1(request);
		return new ModelAndView("tzsq/auditTzsqInput", "xhTzsq", xhTzsq);
	}
	//--------------------------------------债权转让申请      结束-----------------------------------
	
	
	//--------------------------------------债权转让审批        开始-----------------------------------
	/**
	 * 债权转让审批
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listZhuanRaAuditTzsq")
	public String listZhuanRaAuditTzsq(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
				
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("state", "2");
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/zhuanRaAuditTzsqIndex";
		
	}
	
	/**
	 * 债权转让审批-进入债权转让审批页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/zhuanRaAuditTzsq/{Id}", method=RequestMethod.GET)
	public ModelAndView zhuanRaAuditTzsq(@PathVariable Long Id,HttpServletRequest request){
		XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
		initialize1(request);
		return new ModelAndView("tzsq/auditTzsqInput", "xhTzsq", xhTzsq);
	}
	//--------------------------------------债权转让审批      结束-----------------------------------
	
	
	
	//--------------------------------------投资申请到期查询        开始-----------------------------------
	/**
	 * 投资申请到期查询
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listTzsqDateOver/{days}")
	public String listTzsqDateOver(@PathVariable Integer days,HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//			String loginName = operator.getUsername();
//			String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//			Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//map.put("state", "2");
		map.put("days", days);
		//map.put("cjzq", "-1");
		//map.put("hkstate", "2");
		//map.put("overstatenot", "2");
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryOverStateXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		String returnUrl = null;
		switch (days) {
		case 7:
			returnUrl = "tzsq/tzsqDateOver7Index";
			break;
		case 30:
			returnUrl = "tzsq/tzsqDateOver30Index";
			break;
		}
		return returnUrl;
		
	}
	
	@RequestMapping(value="/listTzsqDateRealyOver")
	public String listTzsqDateRealyOver(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//			String loginName = operator.getUsername();
//			String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//			Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//map.put("state", "2");
		//map.put("cjzq", "-1");
		//map.put("hkstate", "2");
		//map.put("overstatenot", "2");
		Date createTime = new Date();
		if(map.containsKey("yesterday")){
			String value = String.valueOf(map.get("yesterday"));
			if(StringUtils.isEmpty(value)) {
				map.put("yesterday",
						CreditHarmonyComputeUtilties.getDateBefore(DateUtils.format(createTime, "yyyy-MM-dd"),1));
			}
		}else{
			map.put("yesterday",
					CreditHarmonyComputeUtilties.getDateBefore(DateUtils.format(createTime, "yyyy-MM-dd"),1));
		}
		//map.put("yesterday", yesterday);
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryOverStateXhTzsqList", map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "tzsq/tzsqDateOver0Index";
		
	}
	
	/**
	 * 债权转让审批-进入债权转让审批页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/downTzsqDateOver/{days}")
	public ModelAndView downTzsqDateOver(@PathVariable Integer days,HttpServletRequest request,
			HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//			String loginName = operator.getUsername();
//			String loginName = operator.getCtiCode();
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//map.put("state", "2");
		if(days > 0){
			map.put("days", days);
		}
		//map.put("cjzq", "-1");
		//map.put("hkstate", "2");
		//map.put("overstatenot", "2");
		//List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryOverStateXhTzsqList", map);
		String fileName = "";
		String filePath = "";
		Date createTime = new Date();
		fileName = DateUtils.format(createTime, "yyyyMMdd")
				+ "客户回款明细.xls";
		String mouFilePath2 = InitSetupListener.filePath + "excel"
				+ File.separator;
		filePath = mouFilePath2 + fileName;
		xhTzsqManager.exportHkmx(filePath, map);
		response.setContentType("APPLICATION/OCTET-STREAM");

		FileUtil.downLoad(filePath, fileName, request, response);
		System.out.println("删除单个文件===>" + filePath);
		FileUtil.deleteFile(filePath);
		System.out.println("删除单个文件   成功===>" + filePath);
		return null;
	}
	//--------------------------------------债权转让审批      结束-----------------------------------
	/**
	 * 页面下拉菜单信息初始化
	 * @param request
	 * @param cjrxx
	 */
	public void initialize1(HttpServletRequest request){
		
//		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
//		request.setAttribute("tzcp", tzcp);
		//投资产品
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("tzcpFl", "投资产品");
		List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
		request.setAttribute("tzcp", tzcp);
		/*//委托协议版本号
		List<MateData> wtxybbh = baseInfoManager.getTypeByCode("0017");
		request.setAttribute("wtxybbh", wtxybbh);
		//开户行
		List<MateData> khh = baseInfoManager.getTypeByCode("0001");
		request.setAttribute("khh", khh);
		
		//回收方式
		List<MateData> hsfs = baseInfoManager.getTypeByCode("0021");
		request.setAttribute("hsfs", hsfs);
		//付款方式
		List<MateData> fkfs = baseInfoManager.getTypeByCode("0022");
		request.setAttribute("fkfs", fkfs);*/
	}
	//--------------------------------------页面下拉菜单信息初始化       结束-----------------------------------
	
	
	/**
	 * 上传
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/upLoadeFile")
	public String upLoadeFile(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		request.setAttribute("Id", id+";"+flag);
		return "tzsq/uploadFiles";

	}
	
	/**
	 * 上传
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/upChangeLoadeFile")
	public String upChangeLoadeFile(HttpServletRequest request, Model model){
		return "tzsq/upChangeLoadeFile";
	}
	
	
	/**
	 * 上传测试
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveChangeLoadFile")
	public void saveChangeLoadFile(HttpServletRequest request, HttpServletResponse response, Model model){
		//得到当前登录用户
//				String loginName = operator.getUsername();
//				String loginName = operator.getCtiCode();
		
		List<Map<String,String>> fileName = PropertiesUtils.upFile(request, "upload");
		if(fileName.size()==1){
			Map<String,String> m=fileName.get(0);
//			xhUploadFiles = new XhUploadFiles();
//			xhUploadFiles.setFilename(m.get("fileName"));
//			xhUploadFiles.setFilepath("upload");
//			xhUploadFiles.setNewfilename(m.get("newFileName"));
//			xhUploadFiles.setFileOwner("XH_TZSQ");
			System.out.println(m.get("fileName") + ";;"+m.get("fileName"));
			ServletUtils.renderJson(response,m);

		}
	}
	
	/**
	 * 上传测试
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/upLoadFile")
	public void upLoadFile(HttpServletRequest request, HttpServletResponse response, Model model){

	    List<Map<String, String>> fileName;
        try {
            fileName = PropertiesUtils.upFileFullFunctions(request, "upload");
            ServletUtils.renderJson(response, fileName);
        } catch (Exception e) {
            ServletUtils.renderJson(response, "0");
        }
		
	}
	
	@RequestMapping(value="/upLoadFileBack")
    public void upLoadFileBack(HttpServletRequest request, HttpServletResponse response, Model model){
           
        String[] ids = request.getParameter("id").split(";");
        String id = ids[0];
        String flag = ids[1];
        String files = request.getParameter("fileName");
        List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
        xhTzsqManager.saveUploadedFiles(id,flag,fileName);
        ServletUtils.renderJson(response, "1");
    }
	
	
	
	@RequestMapping(value = "/downLoadFile")
	public String downLoadFile(
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		String Id = request.getParameter("id");
		String flag = request.getParameter("flag");
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_TZSQ");
		filters.put("mainId", Long.parseLong(Id));
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		request.setAttribute("files", files);
		request.setAttribute("flag", flag);
		return "tzsq/downloadFile";
	}
	
	
	//---------------------------申请单导出、打印--------------------
		/**
		 * 导出工程立项信息
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 */
		@RequestMapping(value="/exportXhTzsq")
		public String exportXhTzsq(HttpServletRequest request, HttpServletResponse response) throws IOException{
			//得到当前登录用户
			OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
			String filePath = request.getSession().getServletContext().getRealPath("agaeeFile");
			Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//			Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
			//过滤查询内容，所需条件   ----开始
			Employee emp = baseInfoManager.getUserEmployee();
			String loginName = operator.getCtiCode();
			map.put("loginName", loginName);
			map.put("emp", emp);
			//过滤查询内容，所需条件   ----结束
			List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map);
			String path = xhTzsqManager.exportProref(filePath, listTzsq);
			response.setContentType("APPLICATION/OCTET-STREAM"); 
			Date createTime = new Date();
			
			response.setHeader("Content-Disposition", "attachment; filename=\"TZSQ_" +createTime.toString()+"_SystemLog.xls"+ "\"");
			File excel = new File(path);
			if(!excel.exists()){
				excel.mkdirs();
			}else{
				FileInputStream fis = new FileInputStream(excel);
				ServletOutputStream clientOut = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int read = 0;
				while((read = fis.read(buffer,0,buffer.length)) != -1){
					clientOut.write(buffer,0,read);
				}
				fis.close();
				excel.delete();
			}
			return null;
		}
		
		//---------------------------申请单导出、打印   结束--------------------
		@RequestMapping(value = "/downloadSqFile")
		public String downloadSqFile(
				HttpServletRequest request,
				HttpServletResponse response, Model model){
//			Map<String, Object> filters = new HashMap<String, Object>();
//			filters.put("tzsqid", Long.parseLong(Id));
//			List<XhCapitalLoanReport> files = xhCapitalLoanReportManager.findByTzsq(filters);
//			request.setAttribute("files", files);
			String id = request.getParameter("id");
			System.out.println(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			List<Map<String, Object>> listTzsq = xhTzsqManager.selectXhTzsqOne("downloadTzsqFile", map);
			System.out.println(listTzsq);
//			Map<String, Object> value = listTzsq.get(0);
			model.addAttribute("listTzsq", listTzsq);
			model.addAttribute("map", map);
//			model.addAttribute("value", value);
			return "tzsq/downloadSqFile";
		}
		
		/**
		 * 下载首期出借
		 * 
		 * @return
		 */
		@RequestMapping(value = "/downloadOneFile")
		public ModelAndView getInputStream(HttpServletRequest request,
				HttpServletResponse response) {
			String LENT_STATE = request.getParameter("LENT_STATE");
			String id = request.getParameter("id");
			Long Id = Long.parseLong(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Id);
			List<Map<String, Object>> listTzsq = xhTzsqManager.findXhTzsq(
					"downloadTzsqFile", map);
			Map<String, Object> value = listTzsq.get(0);
			Long tzsqid = Long.parseLong(value.get("TZSQ_ID").toString());
			XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(tzsqid);
			String mouFilePath2 = InitSetupListener.filePath + "customerUse"
	                + File.separator;
			String filePath = mouFilePath2 + "zq" + xhTzsq.getJhtzrq()
					+ File.separator;
			String fileName = "";
			String tzsqbh = xhTzsq.getTzsqbh();
			tzsqbh = tzsqbh.substring(tzsqbh.length() - 3, tzsqbh.length());
			if (LENT_STATE.equals("0")) {
				// fileName =
				// "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
				fileName = "首期债权转让及受让协议-" + value.get("CJRXM")  + "-" + tzsqbh + "("
						+ id + ")" + xhTzsq.getJhtzrq()
						 + ".pdf";
			} else {
				// fileName =
				// "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
				fileName = "非首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh + "("
						+ id + ")"  + xhTzsq.getJhtzrq() 
						 + ".pdf";
			}
			System.out.println("filePath===>" + filePath);
			String downLoadPath = filePath + fileName;

			FileUtil.downLoad(downLoadPath, fileName, request, response);
			/*
			 * try { response.setContentType("text/html;charset=utf-8");
			 * request.setCharacterEncoding("UTF-8"); java.io.BufferedInputStream
			 * bis = null; java.io.BufferedOutputStream bos = null;
			 * System.out.println(downLoadPath); try { long fileLength = new
			 * File(downLoadPath).length();
			 * response.setContentType("application/octet-stream; charset=utf-8");
			 * response.setHeader("Content-disposition", "attachment; filename=" //
			 * + new String(fileName.getBytes("UTF-8"), "GB2312")); +
			 * URLEncoder.encode(fileName, "utf-8"));
			 * response.setHeader("Content-Length", String.valueOf(fileLength)); bis
			 * = new BufferedInputStream(new FileInputStream(downLoadPath)); bos =
			 * new BufferedOutputStream(response.getOutputStream()); byte[] buff =
			 * new byte[1024]; int bytesRead; while (-1 != (bytesRead =
			 * bis.read(buff, 0, buff.length))) { bos.write(buff, 0, bytesRead); } }
			 * catch (Exception e) { // e.printStackTrace(); } finally { if (bis !=
			 * null) { bis.close(); } if (bos != null) { bos.close(); } } } catch
			 * (UnsupportedEncodingException e) { e.printStackTrace(); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
			return null;

		}
		
		/*
		 * 债权转让申请
		 */
		@RequestMapping(value = "/CreditApply/{Id}")
		public void CreditApply(@PathVariable Long Id,HttpServletRequest request,HttpServletResponse response){
			XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
			String creTime = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			xhTzsqManager.CreditApply(xhTzsq,creTime);
			
			DwzResult success = new DwzResult("200", "操作成功", "rel_listXhTzsq", "", "", "");
			ServletUtils.renderJson(response, success);
		}
		
		
		
		/**
		 * 债权转让申请页
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/listXhCreditApply")
		public String listXhCreditApply(HttpServletRequest request, Model model){
			
			// 处理分页的参数
			JdbcPage page = JdbcPageUtils.generatePage(request);
			
			Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//			Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
			//过滤查询内容，所需条件   ----开始
			Employee emp = baseInfoManager.getUserEmployee();
			OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
			String loginName = operator.getCtiCode();
			map.put("loginName", loginName);
			map.put("emp", emp);
			//过滤查询内容，所需条件   ----结束
			//债权转让申请状态1，债权转让申请 2.待债权转让审批
			if(map.containsKey("creditstate")){
				String value = String.valueOf(map.get("creditstate"));
				if(StringUtils.isEmpty(value)) {
					map.put("creditstate", "0");
				}
			}else{
				map.put("creditstate", "0");
			}
			List<Map<String,Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map,page);
			initialize1(request);
			model.addAttribute("listTzsq", listTzsq);
			model.addAttribute("map", map);
		
			model.addAttribute("page", page);
			/*List<City> province = baseInfoManager.getSuggestCity("0");
			request.setAttribute("province", province);*/
			return "tzsq/xhCreditApplyIndex";
			
		}
		
		
		/**
		 * 债权转让审批-进入债权转让上传材料页
		 * @param cjrxx
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/uploadCreditApply/{Id}", method=RequestMethod.GET)
		public ModelAndView uploadCreditApply(@PathVariable Long Id,HttpServletRequest request){
			XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
			//initialize1(request);
			return new ModelAndView("tzsq/xhCreditApplyInput", "xhTzsq", xhTzsq);
		}
		
		
		/**
		 * 债权转让申请上传资料
		 * @param cjrxx
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/saveCreditApply",method=RequestMethod.POST)
		public String saveCreditApply(@ModelAttribute("xhTzsq") XhTzsq xhTzsq, HttpServletRequest request, HttpServletResponse response){
			String fileName =request.getParameter("fileName");
			String newFileName =request.getParameter("newFileName");
			xhTzsqManager.saveCreditApply(xhTzsq,fileName,newFileName);
			
			DwzResult success = new DwzResult("200","保存成功","rel_listXhCreditApply","","closeCurrent","");
			ServletUtils.renderJson(response, success);
			
			return null;
		}
		
		
		
		/**
		 * 债权转让审批-进入债权转让审批页
		 * @param cjrxx
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/auditCreditApply/{Id}", method=RequestMethod.GET)
		public synchronized ModelAndView auditCreditApply(@PathVariable Long Id, HttpServletRequest request){
			XhTzsq xhTzsq = xhTzsqManager.getXhTzsq(Id);
			Map<String, Object> filters = new HashMap<String,Object>();
			filters.put("fileOwner", "XH_TZSQ");
			filters.put("mainId", Id);
			List<XhUploadFiles> findXhUploadFiles = baseInfoManager.findXhUploadFiles(filters);
			if(findXhUploadFiles.size() > 0){
				request.setAttribute("xhUploadFiles", findXhUploadFiles.get(0));
			}
			initialize1(request);
			return new ModelAndView("tzsq/auditCreditApplyInput", "xhTzsq", xhTzsq);
		}
		
		
		/**
		 * 债权转让申请审批 -保存
		 * @param cjrxx
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/saveCreditAudit",method=RequestMethod.POST)
		public String saveCreditAudit(@ModelAttribute("xhTzsq") XhTzsq xhTzsq, HttpServletRequest request, HttpServletResponse response){
			XhTzsq xhTzsqFind = xhTzsqManager.getXhTzsq(xhTzsq.getId());
			xhTzsqManager.saveCreditAudit(xhTzsqFind,xhTzsq.getCreditstate(),xhTzsq.getCreditidea());
			
			DwzResult success = new DwzResult("200","保存成功","rel_listXhCreditApply","","closeCurrent","");
			ServletUtils.renderJson(response, success);
			
			return null;
		}
		
		/**
		 * 下载债权转让申请
		 *
		 * @param request
		 * @param response
		 * @return
		 * @author nichuanying
		 * @date 2013-11-8 上午8:51:27
		 */
		 @RequestMapping(value = "/downApplyFile")
		    public ModelAndView downApplyFile(HttpServletRequest request,
		            HttpServletResponse response) {
		        String id = request.getParameter("id");
		        Long Id = Long.parseLong(id);
		        Map<String, Object> map = new HashMap<String, Object>();
		        map.put("id", Id);
		        List<Map<String, Object>> listTzsq = xhTzsqManager.searchXhTzsq("queryXhTzsqList", map);
		        Map<String, Object> value = listTzsq.get(0);

		        String mouFilePath2 = InitSetupListener.filePath + "zqzr"
	                    + File.separator;
		        String fileName = "";

		        
		        String xingming = value.get("CJRXM") + "";
	            String chujiebianhao = value.get("TZSQBH") + "";
	            String chushiriqi = value.get("JHTZRQ")+"";
		        fileName = "zq"+chushiriqi.substring(0,chushiriqi.lastIndexOf("-"))
                        + File.separator + "出借人债权转让申请-"+xingming+"-"+chujiebianhao+"("+id+")"+chushiriqi+".doc";
		       /* String filePath = mouFilePath2 + "zq"+chushiriqi.substring(0,chushiriqi.lastIndexOf("-"))
	                    + File.separator + "出借人债权转让申请-"+xingming+"-"+chujiebianhao+"("+id+")"+chushiriqi+".doc";*/
		       
		        String downLoadPath = mouFilePath2 + fileName;
		        System.out.println("filePath===>" + downLoadPath);
		        FileUtil.downLoad(downLoadPath, fileName, request, response);

		        return null;

		    }
}
