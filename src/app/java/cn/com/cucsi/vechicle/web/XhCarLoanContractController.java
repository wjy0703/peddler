package cn.com.cucsi.vechicle.web;

import java.io.File;
import java.text.DecimalFormat;
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
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanContract;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyManager;
import cn.com.cucsi.vechicle.service.XhCarLoanContractManager;
import cn.com.cucsi.vechicle.util.CarState;

@Controller
@RequestMapping(value="/xhCarLoanContract")
public class XhCarLoanContractController {
	@Autowired
	private XhCarLoanContractManager xhCarLoanContractManager;
	
	@Autowired
	private XhCarLoanApplyManager xhCarLoanApplyManager;
	@Autowired
	private BaseInfoManager baseInfoManager;
	//待确认车借合同利率列表
	@RequestMapping(value="/listXhCarLoanContract")
	public String listXhCarLoanContract(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("state", CarState.DAI_QUE_REN_HE_TONG_DKLL.getText());
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanApply = xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList", map,page);
		model.addAttribute("xhCarLoanApply", xhCarLoanApply);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/contract/xhCarLoanContractIndex";
		
	}
	
	//已制作合同列表
	@RequestMapping(value="/listXhCarLoanContractOver")
	public String listXhCarLoanContractOver(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("hetongstate", CarState.DAI_HE_TONG_ZHI_ZUO_SHEN_HE.getText());
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanContract = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map,page);
		model.addAttribute("xhCarLoanContract", xhCarLoanContract);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/contract/xhCarLoanContractOverIndex";
		
	}
	
	@RequestMapping(value="/madeXhCarLoanContract/{Id}", method=RequestMethod.GET)
	public synchronized ModelAndView madeXhCarLoanContract(@PathVariable Long Id, Model model){
		XhCarLoanContract xhCarLoanContract = new XhCarLoanContract();
		//判断合同是否存在
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("car_apply_id", Id);
		XhCarAudit xhCarAudit = xhCarLoanContractManager.getXhCarAudit(map);
		model.addAttribute("xhCarAudit", xhCarAudit);
		List<XhCarLoanContract> queryXhCarLoanContractList = xhCarLoanContractManager.queryXhCarLoanContractList(map);
		if(null != queryXhCarLoanContractList && queryXhCarLoanContractList.size()==1){
			xhCarLoanContract = queryXhCarLoanContractList.get(0);
		}
		XhCarLoanApply xhCarLoanApply = xhCarLoanContractManager.getXhCarLoanApply(Id);
		xhCarLoanContract.setXhCarLoanApply(xhCarLoanApply);
		xhCarLoanContract.setContractNum(xhCarLoanApply.getLoanCode());
		//XhCarAudit xhCarAudit = xhCarLoanContractManager.getXhCarAudit(map);
		//xhCarLoanContract.setContractMoney(xhCarAudit.getCreditAmount());
		xhCarLoanContract.setDkllComlpex(xhCarAudit.getCreditAllRate());
		resetMoneyDkll(xhCarLoanContract, xhCarAudit);
		return new ModelAndView("xhCarLoan/contract/xhCarLoanContractInput", "xhCarLoanContract", xhCarLoanContract);
	}
	
	private void resetMoneyDkll(XhCarLoanContract xhCarLoanContract,XhCarAudit xhCarAudit){
		Double creditAmount = xhCarAudit.getCreditAmount();//审核金额
		String jkType = xhCarLoanContract.getXhCarLoanApply().getJkType();//借款类型 0 GPS;1 移交
		//违约金利率(5%)
		Double breakMoneyDkll = 5.0;
		xhCarLoanContract.setBreakMoneyDkll(breakMoneyDkll);
		//违约罚息率(GPS:0.2%，移交：0.05%)
		Double breakInterestDkll = 0.0;
		//利息率（移交：0.5%；GPS：0.475%）
		Double dkll = 0.0;
		//总费率（移交：3.5%；GPS：5%  ，客户经理可录入）
		Double dkllComlpex = xhCarLoanContract.getDkllComlpex();
		
		if("0".equals(jkType)){
			//违约罚息率(GPS:0.2%，移交：0.05%)
			breakInterestDkll= 0.2;
			//利息率（移交：0.5%；GPS：0.475%）
			dkll = 0.475;
		}else{
			//违约罚息率(GPS:0.2%，移交：0.05%)
			breakInterestDkll= 0.05;
			//利息率（移交：0.5%；GPS：0.475%）
			dkll = 0.5;
		}
		xhCarLoanContract.setBreakInterestDkll(breakInterestDkll);
		xhCarLoanContract.setDkll(dkll);
		
		
		//违约日利息（借款金额*违约罚息率）
		Double breakInterest = resetValue(creditAmount*breakInterestDkll/100);
		xhCarLoanContract.setBreakInterest(breakInterest);
		//违约金
		Double breakMoney = resetValue(creditAmount*breakMoneyDkll/100);
		xhCarLoanContract.setBreakMoney(breakMoney);
		//利息
		Double interest = resetValue(creditAmount*dkll/100);
		xhCarLoanContract.setInterest(interest);
		
		Double comlpexMoney = 0.0;
		//综合费用（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）
		if(creditAmount*dkllComlpex/100<1000){
			comlpexMoney = 1000-interest;
		}else{
			comlpexMoney = resetValue(creditAmount*dkllComlpex/100-interest);
		}
		xhCarLoanContract.setComlpexMoney(comlpexMoney);
		//咨询费（综合息费*59%）
		Double adviceFee = resetValue(comlpexMoney*59/100);
		xhCarLoanContract.setAdviceFee(adviceFee);
		//审核费（综合息费*5%）
		Double auditFee = resetValue(comlpexMoney*5/100);
		xhCarLoanContract.setAuditFee(auditFee);
		//服务费（综合息费-咨询费-审核费）
		Double serviceFee = resetValue(comlpexMoney-adviceFee-auditFee);//comlpexMoney*36/100;
		xhCarLoanContract.setServiceFee(serviceFee);
		//合同金额
		Double contractMoney = resetValue(creditAmount-interest);
		xhCarLoanContract.setContractMoney(contractMoney);
		
	}
	
	private static Double resetValue(Double value){
		DecimalFormat df = new DecimalFormat("#0.000");
		Double result = Double.parseDouble(df.format(value));
		return result;
	}
	@RequestMapping(value="/saveXhCarLoanContract",method=RequestMethod.POST)
	public String saveXhCarLoanContract(@ModelAttribute("xhCarLoanContract") XhCarLoanContract xhCarLoanContract, HttpServletRequest request, HttpServletResponse response){
		
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(xhCarLoanContract.getXhCarLoanApply().getId());
		xhCarLoanContract.setXhCarLoanApply(xhCarLoanApply);
		xhCarLoanContract.setOverDate(CreditHarmonyComputeUtilties.getDateAfter(xhCarLoanApply.getJkLoanDate(),Integer.parseInt(xhCarLoanApply.getJkCycle()+"")));
		String fkzh = request.getParameter("middleMan.id");
		MiddleMan middleMan = baseInfoManager.getMiddleMan(Long.valueOf(fkzh));
		xhCarLoanContract.setMiddleMan(middleMan);
		xhCarLoanContract.setHkr(CreditHarmonyComputeUtilties.getZdr(xhCarLoanApply.getJkLoanDate()));
		xhCarLoanContractManager.saveXhCarLoanContract(xhCarLoanContract);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanContract","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//待制作车借合同列表
	@RequestMapping(value="/listXhCarLoanContractNeedMade")
	public String listXhCarLoanContractNeedMade(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("state", CarState.DAI_ZHI_ZUO_HE_TONG.getText());
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanApply = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map,page);
		model.addAttribute("xhCarLoanApply", xhCarLoanApply);
		
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/contract/xhCarLoanContractNeedMadeIndex";
		
	}
	
	/**
	 * 待制作车借合同
	 */
	@RequestMapping(value="/xhCarLoanContractNeedMade/{Id}", method=RequestMethod.GET)
    public String  xhCarLoanContractNeedMade(@PathVariable Long Id,Model model){

	    XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
	    model.addAttribute("xhCarLoanContract",xhCarLoanContract);
		return "xhCarLoan/contract/xhCarLoanContractNeedMadeInput";
    }
	/**
	 * 制作车借合同
	 */
	@RequestMapping(value="/saveXhCarLoanContractNeedMade",method=RequestMethod.POST)
	public String saveXhCarLoanContractNeedMade(@ModelAttribute("xhCarLoanContract") XhCarLoanContract xhCarLoanContract, HttpServletRequest request, HttpServletResponse response){
		xhCarLoanContractManager.saveXhCarLoanContractMade(xhCarLoanContract);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanContractNeedMade","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	
	@RequestMapping(value="/addXhCarLoanContract", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("xhCarLoan/contract/xhCarLoanContractInput", "xhCarLoanContract", new XhCarLoanContract());
	}
	
	@RequestMapping(value="/editXhCarLoanContract/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
		return new ModelAndView("xhCarLoan/contract/xhCarLoanContractInput", "xhCarLoanContract", xhCarLoanContract);
	}

	@RequestMapping(value="/delXhCarLoanContract/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCarLoanContractManager.deleteXhCarLoanContract(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCarLoanContract","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCarLoanContract")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCarLoanContractManager.batchDelXhCarLoanContract(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCarLoanContract","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCarLoanContract","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 协议查看框架页
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryAgaee/{Id}", method = RequestMethod.GET)
	public ModelAndView queryAgaee(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
//		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
//		Long applyId = xhCarLoanApply.getId();
//		JdbcPage page = JdbcPageUtils.generatePage(request);
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
//		map.put("carApplyId", applyId);
//		List<Map<String, Object>> list = xhCarLoanContractManager.searchXhCarLoanContract("queryXhCarLoanOverList", map, page);
//		Map<String, Object> xhCarLoanContract = list.get(0);
		XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
		model.addAttribute("xhCarLoanContract", xhCarLoanContract);
		//XhJkht xhJkht = xhJksq.getXhjkht();
		//XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		return new ModelAndView("xhCarLoan/contract/queryAgaee", "xhCarLoanApply", xhCarLoanContract.getXhCarLoanApply());
	}
	
	/**
	 * 申请中协议查看
	 * @author fjh
     * @date 2013-10-24 上午9:05
	 * 
	 */
	
	@RequestMapping(value = "/isAgreementLook/{Id}", method = RequestMethod.GET)
	public ModelAndView isAgreementLook(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		XhCarLoanContract xhCarLoanContract=xhCarLoanContractManager.getAgreementLook(Id);
		model.addAttribute("xhCarLoanContract", xhCarLoanContract);
		return new ModelAndView("xhCarLoan/contract/queryAgaee", "xhCarLoanApply", xhCarLoanContract.getXhCarLoanApply());
	}
	
	/**
	 * 协议文档查看
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryAgaeeFile", method = RequestMethod.GET)
	public ModelAndView queryAgaeeFile(HttpServletRequest request, Model model) {
		String Id = request.getParameter("Id");
		String sType = request.getParameter("sType");
		model.addAttribute("id", Id);
		return new ModelAndView("xhCarLoan/contract/queryAgaeeFile", "fileName", Id + sType
				+ ".swf");
	}
    /**
     * 取得对应的下载文件的地址
     * @param id   申请id
     * @param sType   协议查看的类型
     * @return
     */
//	private String getFilePath(String id, String sType) {
//		
//		return id + sType + ".swf";
//	}
	@RequestMapping(value = "/downAgaeeFile")
	public String downAgaeeFile(HttpServletRequest request,
			HttpServletResponse response) {
		String Id = request.getParameter("Id");
		String sType = request.getParameter("sType");
		String base = InitSetupListener.filePath + "agaeeFile" + File.separator
				+ Id;// request.getRealPath(xhUploadFiles.getFilepath());
		// System.out.println("base===>" + base);
		String fileName = Id + sType + ".doc";
		// String ext =
		// fileName.substring(fileName.lastIndexOf("."),fileName.length());
		// System.out.println("ext========"+ext);
		// String path = base + File.separator + fileName;
		String filePath = base + File.separator;
		String downLoadPath = filePath + fileName;

		FileUtil.downLoad(downLoadPath, fileName, request, response);
		/*
		 * try { java.io.BufferedInputStream bis = null;
		 * java.io.BufferedOutputStream bos = null;
		 * response.setContentType("text/html;charset=utf-8");
		 * request.setCharacterEncoding("UTF-8"); //
		 * System.out.println(downLoadPath); try { long fileLength = new
		 * File(downLoadPath).length();
		 * response.setContentType("application/octet-stream; charset=utf-8");
		 * response.setHeader( "Content-disposition", "attachment; filename=" //
		 * + new String(fileName.getBytes("UTF-8"), "GB2312")); +
		 * URLEncoder.encode( fileName, "utf-8"));
		 * response.setHeader("Content-Length", String.valueOf(fileLength)); bis
		 * = new BufferedInputStream(new FileInputStream(downLoadPath)); bos =
		 * new BufferedOutputStream(response.getOutputStream()); byte[] buff =
		 * new byte[1024]; int bytesRead; while (-1 != (bytesRead =
		 * bis.read(buff, 0, buff.length))) { bos.write(buff, 0, bytesRead); } }
		 * catch (Exception e) { // e.printStackTrace(); } finally { if (bos !=
		 * null) { bos.close(); } if (bis != null) { bis.close(); }
		 * 
		 * } } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */
		return null;
	}
	
	/**
	 * 查看合同
	 *
	 * @param Id
	 * @param model
	 * @return
	 * @author fjh
	 * @date 2013-10-15 下午4:31
	 */
	@RequestMapping(value="/editXhCarLoanContractLook/{Id}", method=RequestMethod.GET)
    public String  editLook(@PathVariable Long Id,Model model){

	    XhCarLoanContract xhCarLoanContract = xhCarLoanContractManager.getXhCarLoanContract(Id);
	    model.addAttribute("xhCarLoanContract",xhCarLoanContract);
		return "xhCarLoan/contract/xhCarLoanContractOverInput";
    }
	
	
}
