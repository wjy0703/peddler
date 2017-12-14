package cn.com.cucsi.vechicle.web;

import java.sql.Date;
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
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;
/**
 * 车借划扣
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/xhCarLoanDeduct")
public class XhCarLoanDeductController {
	@Autowired
	private XhCarLoanDeductManager xhCarLoanDeductManager;
	@Autowired
	private XhCarLoanContractManager xhCarLoanContractManager;
	@Autowired
	private XhCarLoanApplyManager xhCarLoanApplyManager;
	@Autowired
	private BaseInfoManager baseInfoManager;
	//待车借划扣审核列表
	@RequestMapping(value="/listXhCarLoanHuaKou")
	public String listXhCarLoanDeduct(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("state", CarState.DAI_TI_JIAO_HUA_KOU_SHENHE.getText());
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanApply = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map,page);
		model.addAttribute("xhCarLoanApply", xhCarLoanApply);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/deduct/xhCarLoanDeductIndex";
		
	}
	//待车借划扣列表
	@RequestMapping(value="/listXhCarLoanDeductOver")
	public String listXhCarLoanContractOver(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("state", CarState.DAI_HUA_KOU.getText());
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanApply = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map,page);
		model.addAttribute("xhCarLoanApply", xhCarLoanApply);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/deduct/xhCarLoanDeducttDaiHuagouIndex";
		
	}
	
	//已划扣列表
	@RequestMapping(value="/listXhCarLoanDeductHuaKouOver")
	public String listXhCarLoanDeductHuaKouOver(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		List<Map<String,Object>> xhCarLoanMakeLoans = xhCarLoanDeductManager.searchXhCarLoanHuaKouContract("queryXhCarLoanHuaKouOverList", map,page);
		model.addAttribute("xhCarLoanMakeLoans", xhCarLoanMakeLoans);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/deduct/xhCarLoanDeductHuaKouIndex";
		
	}
	
	
	
	//带划扣审核页
	@RequestMapping(value="/madeXhCarLoanDeduct/{Id}", method=RequestMethod.GET)
	public ModelAndView madeXhCarLoanContract(@PathVariable Long Id){
		XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
		
		return new ModelAndView("xhCarLoan/deduct/xhCarLoanDeductInput", "xhCarLoanContract", xhCarLoanContract);
	}
	
	//待划扣审核
	@RequestMapping(value="/saveXhCarLoanDeduct",method=RequestMethod.POST)
	public String saveXhCarLoanContract(@ModelAttribute("xhCarLoanContract") XhCarLoanContract xhCarLoanContract, HttpServletRequest request, HttpServletResponse response){
		
		String opt = request.getParameter("opt");
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(xhCarLoanContract.getXhCarLoanApply().getId());

		if(opt.equals("60")){
			String oldState = xhCarLoanApply.getState();
			xhCarLoanApply.setState(CarState.DAI_HUA_KOU.getText());
			xhCarLoanApplyManager.saveXhCarLoanApply(xhCarLoanApply);
			xhCarLoanApplyManager.saveHistory(oldState, xhCarLoanApply, CarOperation.DAI_HUA_KOU, CarOperation.DAI_HUA_KOU.getMessage());
		}else{
			String oldState = xhCarLoanApply.getState();
			xhCarLoanApply.setState(CarState.DAI_QUE_DING_QIAN_SHU.getText());
			xhCarLoanApplyManager.saveXhCarLoanApply(xhCarLoanApply);
			xhCarLoanApplyManager.saveHistory(oldState, xhCarLoanApply, CarOperation.DAI_QUE_DING_QIAN_SHU,CarOperation.DAI_QUE_DING_QIAN_SHU.getMessage());
		}

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanHuaKou","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//划扣
	@RequestMapping(value="/madeXhCarLoanDeductHuaKou/{Id}", method=RequestMethod.GET)
	public ModelAndView madeXhCarLoanDeductHuaKou(@PathVariable Long Id,Model model){
		XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
		Double hkje = xhCarLoanContract.getComlpexMoney()+xhCarLoanContract.getInterest();//划扣金额=综合息费+利息
		model.addAttribute("hkje", hkje);
		
		return new ModelAndView("xhCarLoan/deduct/xhCarLoanDeductHuaKouInput", "xhCarLoanContract", xhCarLoanContract);
	}
	
	
	@RequestMapping(value="/saveXhCarLoanDeductHuaKou",method=RequestMethod.POST)
	public String saveXhCarLoanDeductHuaKou(@ModelAttribute("xhCarLoanContract") XhCarLoanContract xhCarLoanContract, HttpServletRequest request, HttpServletResponse response){
		
		 long middleManId = Long.parseLong(request.getParameter("middleMan.id"));
	     MiddleMan middleMan = baseInfoManager.getMiddleMan(middleManId);
	     Date date = Date.valueOf(request.getParameter("makeLoanDate"));
		xhCarLoanDeductManager.saveXhCarLoanDeductHuaKou(date,middleMan,xhCarLoanContract);
		
		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanDeductOver","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	
	
	
	@RequestMapping(value="/addXhCarLoanDeduct", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("xhCarLoan/contract/xhCarLoanContractInput", "xhCarLoanContract", new XhCarLoanContract());
	}
	
	@RequestMapping(value="/editXhCarLoanDeduct/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhCarLoanContract xhCarLoanContract = xhCarLoanDeductManager.getXhCarLoanContract(Id);
		return new ModelAndView("xhCarLoan/deduct/xhCarLoanDeductInput", "xhCarLoanContract", xhCarLoanContract);
	}

	@RequestMapping(value="/delXhCarLoanDeduct/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCarLoanDeductManager.deleteXhCarLoanContract(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCarLoanDeductOver","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCarLoanDeduct")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCarLoanDeductManager.batchDelXhCarLoanContract(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCarLoanDeductOver","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCarLoanDeductOver","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}
