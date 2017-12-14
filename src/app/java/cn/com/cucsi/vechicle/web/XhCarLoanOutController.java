package cn.com.cucsi.vechicle.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanContract;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyManager;
import cn.com.cucsi.vechicle.service.XhCarLoanContractManager;
import cn.com.cucsi.vechicle.service.XhCarLoanDeductManager;
import cn.com.cucsi.vechicle.service.XhCarLoanOutManager;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;

@Controller
@RequestMapping(value="/xhCarLoanOut")
public class XhCarLoanOutController {
	@Autowired
	private XhCarLoanOutManager xhCarLoanOutManager;
	
	@Autowired
	private XhCarLoanApplyManager xhCarLoanApplyManager;
	@Autowired
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	private XhCarLoanContractManager xhCarLoanContractManager;
	//待制作车借合同列表
	@RequestMapping(value="/listXhCarLoanOutOver")
	public String listXhCarLoanDeduct(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("state", CarState.DAI_TI_JIAO_FANG_KUAN_SHENHE.getText());
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanContract = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map,page);
		model.addAttribute("xhCarLoanApply", xhCarLoanContract);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/out/xhCarLoanOutIndex";
		
	}
	//已制作合同列表
	@RequestMapping(value="/listXhCarLoanOut")
	public String listXhCarLoanContractOver(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		//map.put("state", "50");
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanContract = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map,page);
		model.addAttribute("xhCarLoanApply", xhCarLoanContract);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/out/xhCarLoanOutIndex";
		
	}
	
	@RequestMapping(value="/madeXhCarLoanOut/{Id}", method=RequestMethod.GET)
	public ModelAndView madeXhCarLoanContract(@PathVariable Long Id){
		XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
		return new ModelAndView("xhCarLoan/out/xhCarLoanOutInput", "xhCarLoanContract", xhCarLoanContract);
	}
	@RequestMapping(value="/saveXhCarLoanOut",method=RequestMethod.POST)
	public String saveXhCarLoanContract(@ModelAttribute("xhCarLoanContract") XhCarLoanContract xhCarLoanContract, HttpServletRequest request, HttpServletResponse response){
		String opt = request.getParameter("opt");
		if(opt.equals("60")){
			xhCarLoanOutManager.saveXhCarLoanContract(xhCarLoanContract);
		}else{
			XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(xhCarLoanContract.getXhCarLoanApply().getId());
			String oldState = xhCarLoanApply.getState();
			xhCarLoanApply.setState(CarState.DAI_QUE_DING_QIAN_SHU.getText());
			xhCarLoanApplyManager.saveXhCarLoanApply(xhCarLoanApply);
			//待放款审核不通过记录历史状态
			xhCarLoanApplyManager.saveHistory(oldState, xhCarLoanApply, CarOperation.DAI_QUE_DING_QIAN_SHU, CarOperation.DAI_QUE_DING_QIAN_SHU.getMessage());
		}

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanOutOver","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/addXhCarLoanOut", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("xhCarLoan/out/xhCarLoanOutInput", "xhCarLoanContract", new XhCarLoanContract());
	}
	
	@RequestMapping(value="/editXhCarLoanDeduct/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhCarLoanContract xhCarLoanContract = xhCarLoanOutManager.getXhCarLoanContract(Id);
		return new ModelAndView("xhCarLoan/out/xhCarLoanOutInput", "xhCarLoanContract", xhCarLoanContract);
	}

	@RequestMapping(value="/delXhCarLoanOut/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCarLoanOutManager.deleteXhCarLoanContract(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCarLoanOutOver","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCarLoanOut")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCarLoanOutManager.batchDelXhCarLoanContract(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCarLoanOutOver","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCarLoanOutOver","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}
