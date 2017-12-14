package cn.com.cucsi.app.web.xhcf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhMakeLoans;
import cn.com.cucsi.app.excel.bean.ExcelConfiguration;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.ReleaseMoneyExcelService;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.app.service.xhcf.XhMakeLoansManager;
import cn.com.cucsi.app.web.util.DateConvert;
import cn.com.cucsi.app.web.util.ExtractDataFromRequest;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.app.web.util.ajaxdata.BackInfo;
import cn.com.cucsi.app.web.util.ajaxdata.ErrorInfosBean;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

/**
 * 
 * 
 * @author xjs
 * @date 2013-8-8 下午4:01:31
 */
@Controller
@RequestMapping(value = "/makeLoans")
public class XhMakeLoansController {

    private static final String EXCEL_CONFIG = "releaseMoneyExcelService";

    @Autowired
    ExcelConfiguration excelConfiguration;

    @Autowired
    private XhMakeLoansManager xhMakeLoansManager;

    @Autowired
    private ReleaseMoneyExcelService releaseMoneyExcelService;

    @Autowired
    private BaseInfoManager baseInfoManager;

    @Autowired
    private XhJkhtManager jkhtManager;
    
    @Autowired
    private XhJksqManager xhjksqManager;
    
    @Autowired
    private XhJkhtManager xhJkhtManager;
    /**
     * 通过上传文件批量 放款
     *
     * @param request
     * @author xjs
     * @date 2013-8-10 下午2:36:44
     */
    
    @RequestMapping(value = "/multiMakeLoan")
    public void multiMakeLoan(HttpServletRequest request,HttpServletResponse response) {

        Map<Integer, Object> format = ExcelConfiguration.getConfigureColumnMap(excelConfiguration, EXCEL_CONFIG);
        List<Map<String, Object>> records = ExtractDataFromRequest.extractData(request, format);
        List<ErrorInfosBean> errors = new ArrayList<ErrorInfosBean>();
        String currentUser = baseInfoManager.getUserEmployee().getName();
        int count = 0;
        for (Map<String, Object> record : records) {
            ErrorEnum flag = ErrorEnum.SUCCESS;//默认操作成功
            String account = record.get("ACCOUNT")!=null ? record.get("ACCOUNT").toString() : "";
            String bank = record.get("BANK")!=null ? record.get("BANK").toString() : "";
            String bankNum = record.get("BANKNUM")!=null ? record.get("BANKNUM").toString() : "";
            try {
                XhMakeLoans xhMakeLoans = new XhMakeLoans();
                List<MiddleMan> middleMans = releaseMoneyExcelService.getMiddleManByNameAndBank(account, bank, bankNum);
                MiddleMan middleMan = null;
                if(middleMans != null && middleMans.size() == 1){
                    middleMan = middleMans.get(0);
                }else{
                    //放款人不存在
                    flag = ErrorEnum.ACCOUNT_ERROR;
                }
                
                Double hetong = (Double) record.get("HETONGID");
                long hetongId = hetong.longValue();            
                
                Date makeLoanDate = null;
                try {
                  //取得放款时间
                    if(record.get("MAKELOANDATE") != null && record.get("MAKELOANDATE") instanceof java.lang.Double){
                        Double doubleDate = (Double)record.get("MAKELOANDATE");
                        makeLoanDate = new Date(doubleDate.longValue());
                    }
                    if(record.get("MAKELOANDATE") != null && record.get("MAKELOANDATE") instanceof java.lang.String){
                        String makeLoanDateStr = record.get("MAKELOANDATE") != null? record.get("MAKELOANDATE").toString() : "";
                        makeLoanDate = DateConvert.convert(makeLoanDateStr, "yyyy-MM-dd");
                    }
                    if(record.get("MAKELOANDATE") != null && record.get("MAKELOANDATE") instanceof java.util.Date){
                        makeLoanDate = (Date) record.get("MAKELOANDATE");
                    }
                    
                } catch (Exception e) {
                }
                
                if(makeLoanDate == null){
                    flag = ErrorEnum.DATEEXCEPTION; //无放款时间
                }else{
                    xhMakeLoans.setMakeLoanDate(makeLoanDate);
                    xhMakeLoans.setCreateBy(currentUser);
                }
                if(flag == ErrorEnum.SUCCESS){
                    saveMakeLoans(middleMan,xhMakeLoans,hetongId);
                }
                System.out.println(hetongId+ " : "+ flag);
            } catch (Exception e) {
                flag = ErrorEnum.ROWEXCEPTION;
                e.printStackTrace();                
            }
            
            if(flag == ErrorEnum.SUCCESS){
                count++;
            }else{
                if(StringUtils.isNotEmpty(account)){
                    ErrorInfosBean error = new ErrorInfosBean();
                    error.setId(record.get("LOAN_CODE") != null ? record.get("LOAN_CODE").toString() :"");
                    error.setErrorMsg(flag.toString());
                    errors.add(error);
                }
            }
        }
        BackInfo backInfo = new BackInfo();
        backInfo.setCount(count);
        backInfo.setErrors(errors);
        ServletUtils.renderJson(response,backInfo);
    }
    

    @RequestMapping(value = "/saveXhMakeLoans/{Id}", method = RequestMethod.POST)
    public void saveXhMakeLoans(@PathVariable Long Id, @ModelAttribute("xhMakeLoans") XhMakeLoans xhMakeLoans, HttpServletRequest request, HttpServletResponse response) {
        xhMakeLoans.getCreateBy();
        xhMakeLoans.getMakeLoanDate();
        long middleManId = Long.parseLong(request.getParameter("middleMan.id"));
        MiddleMan middleMan = baseInfoManager.getMiddleMan(middleManId);
        saveMakeLoans(middleMan, xhMakeLoans, Id);
        DwzResult success = new DwzResult("200", "保存放款信息成功", "rel_listXhMakeLoans", "", "closeCurrent", "");
        ServletUtils.renderJson(response, success);
    }

    /**
     * 放款调用的方法
     * 
     * @param middleManId
     * @param xhMakeLoans
     * @param jkhtId
     * @author xjs
     * @date 2013-8-10 下午2:31:37
     */
    private void saveMakeLoans(MiddleMan middleMan, XhMakeLoans xhMakeLoans, long jkhtId) {
        XhJkht loanContract = jkhtManager.getXhJkht(jkhtId);
        xhMakeLoans.setMiddleMan(middleMan);
        xhMakeLoans.setLoanContract(loanContract);
        xhMakeLoansManager.saveXhMakeLoans(xhMakeLoans);
    }


    /**
     * 待放款列表，通过状态查询借款申请列表,流程处理各步骤可以通用
     * 
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = "/listLoanApplyByState/{state}")
    public String listLoanApplyeByState(@PathVariable Integer state, HttpServletRequest request, Model model) {
        // 处理分页的参数
        JdbcPage page = JdbcPageUtils.generatePage(request);
        model.addAttribute("page", page);
        // 封装查询条件
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
        map.put("state", state);
        switch (state) {
		case 58:
			map.put("backup01", "CREDIT");
			break;
        }
        // 查找借款申请
        List<Map<String, Object>> listJksq = releaseMoneyExcelService.searchXhJksq(map, page);
        model.addAttribute("listJksq", listJksq);
        // 封装页面过滤条件
        RequestPageUtils.fillModelWithRequest(model, request);
        String returnUrl = null;
		switch (state) {
			case 60:
				returnUrl = "makeLoans/listMakeLoansTask";
				break;
			case 58:
				returnUrl = "jkht/jksqListFkqrsh";
				break;
		}
        return returnUrl;
    }

    /**
     * 已放款列表
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/listMakeLoans")
    public String listXhMakeLoans(HttpServletRequest request, Model model) {
        // 处理分页的参数
        Page<XhMakeLoans> page = new RequestPageUtils<XhMakeLoans>().generatePage(request);

        Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");
        xhMakeLoansManager.searchXhMakeLoans(page, map);
        model.addAttribute("page", page);
        model.addAttribute("map", map);
        return "makeLoans/xhMakeLoansIndex";

    }

    /**
     * 添加放款
     * 
     * @param Id
     * @return
     */
    @RequestMapping(value = "/addMakeLoans/{Id}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Long Id) {

        XhJkht loanContract = jkhtManager.findLoanContarctByApplyID(Id);
        // 根据申请ID查唯一的合同
        XhMakeLoans makeLoans = new XhMakeLoans();
        makeLoans.setLoanContract(loanContract);

        return new ModelAndView("makeLoans/xhMakeLoansInput", "makeLoans", makeLoans);
    }
    
    
    /**
     * 放款退回
     * 
     * @param Id
     * @return
     */
    @RequestMapping(value = "/MakeLoansBack/{Id}", method = RequestMethod.GET)
    public ModelAndView MakeLoansBack(@PathVariable Long Id) {

    	XhJksq xhJksq = xhjksqManager.getXhJksq(Id);

        return new ModelAndView("makeLoans/xhMakeLoansBackInput", "xhJksq", xhJksq);
    }
    
    @RequestMapping(value = "/saveLoanslBack", method = RequestMethod.POST)
	public void saveQrfkPlBack(@ModelAttribute("xhJksq") XhJksq xhJksq,HttpServletRequest request,
			HttpServletResponse response) {
			
		//String id = request.getParameter("id");
		String remark = request.getParameter("remark");
		xhJksq = xhjksqManager.getXhJksq(xhJksq.getId());
		xhJkhtManager.saveXhLoansBack(xhJksq,remark);
		
		DwzResult success = new DwzResult("200", "操作成功", "", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);
	}
    
        
    private enum ErrorEnum{
        SUCCESS,
        ACCOUNT_ERROR{

            @Override
            public String toString() {
                return "请核对放款人账户信息";
            }
        },
        ROWEXCEPTION{

            @Override
            public String toString() {
                return "读入行出现错误，请手工放款该条数据";
            }
        },
        DATEEXCEPTION{

            @Override
            public String toString() {
                return "请核对放款日期";
            }
        }
    }
    
}
