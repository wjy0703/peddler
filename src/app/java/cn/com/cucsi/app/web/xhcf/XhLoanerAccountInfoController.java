package cn.com.cucsi.app.web.xhcf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.xhcf.XhLoanReturnRecords;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccountInfo;
import cn.com.cucsi.app.service.xhcf.XhLoanReturnRecordsManager;
import cn.com.cucsi.app.service.xhcf.XhLoanerAccountInfoManager;
import cn.com.cucsi.app.service.xhcf.XhLoanerAccountManager;
import cn.com.cucsi.app.web.util.DateConvert;
import cn.com.cucsi.app.web.util.ExtractDataFromRequest;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.app.web.util.ajaxdata.BackInfo;
import cn.com.cucsi.app.web.util.ajaxdata.ErrorInfosBean;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.util.CarStateExchange;

@Controller
@RequestMapping(value = "/xhLoanerAccountInfo")
public class XhLoanerAccountInfoController {
    
    
    private Logger logger = LoggerFactory.getLogger(XhLoanerAccountInfoController.class);
    
    private static final Map<Integer, Object> format = new HashMap<Integer, Object>(){
        {
            put(4,"bank");               //银行
            put(5,"bankNum");            //银行帐号
            put(6,"name");               //还款人名
            put(8,"returnMoney");        //还款额
            put(10,"returnDATE");        //还款时间
            put(11,"result");            //还款是否成功
            put(13,"jkhtbm");        //合同编号
        }
    };

    
	private XhLoanerAccountInfoManager xhLoanerAccountInfoManager;
	private XhLoanerAccountManager xhLoanerAccountManager;
	
	@Autowired
	private XhLoanReturnRecordsManager XhLoanReturnRecordsManager;

	@Autowired
	public void setXhLoanerAccountManager(
			XhLoanerAccountManager xhLoanerAccountManager) {
		this.xhLoanerAccountManager = xhLoanerAccountManager;
	}

	@Autowired
	public void setXhLoanerAccountInfoManager(
			XhLoanerAccountInfoManager xhLoanerAccountInfoManager) {
		this.xhLoanerAccountInfoManager = xhLoanerAccountInfoManager;
	}

	@RequestMapping(value = "/listXhLoanerAccountInfo/{Id}")
	public String listXhLoanerAccountInfo(HttpServletRequest request,
			Model model,@PathVariable Long Id) {
		// 处理分页的参数
		Page<XhLoanerAccountInfo> page = new RequestPageUtils<XhLoanerAccountInfo>().generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");
		map.put("loanerMainAccountId", Id);

		xhLoanerAccountInfoManager.searchXhLoanerAccountInfo(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		model.addAttribute("loanerMainAccountId", Id);
		return "loanerAccount/xhLoanerAccountInfoIndex";

	}

	@RequestMapping(value = "/saveXhLoanerAccountInfo", method = RequestMethod.POST)
	public String saveXhLoanerAccountInfo(
			@ModelAttribute("xhLoanerAccountInfo") XhLoanerAccountInfo xhLoanerAccountInfo,
			HttpServletRequest request, HttpServletResponse response) {
		String code = "200";
		String msg = "保存成功";
		String id = request.getParameter("mainAccountId");
		XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager.getXhLoanerAccount(Long.parseLong(id));
		Integer state = xhLoanerAccount.getAccountState();
		
		if(state != null && state == 3){
			code = "300";
			msg = "已经提前结清！";
		}else if(state != null && state == 2){
			code = "300";
			msg = "该记录为逾期，请执行冲抵操作！";
		}else{
			xhLoanerAccountInfo.setLoanerMainAccount(xhLoanerAccount);
			xhLoanerAccountInfoManager.saveXhLoanerAccountInfo(xhLoanerAccountInfo);
		}


		DwzResult success = new DwzResult(code, msg,
				"rel_listhkgl", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}
	
	/**
	 * 删除还款记录 只允许删除类型为0 and 1的数据
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteInfo/{Id}")
	public String deleteInfo(@PathVariable Long Id, HttpServletResponse response) {
		String code = "300";
		String msg = "退款失败，该账户非正常状态，不允许退款！";
		XhLoanerAccountInfo accId = xhLoanerAccountInfoManager.getXhLoanerAccountInfoById(Id);
		Integer type = accId.getDealingType();
		XhLoanerAccount xhLoanerAccount = accId.getLoanerMainAccount();
		if(xhLoanerAccount.getAccountState() == 0){
			if(xhLoanerAccount != null){
				double bal = xhLoanerAccount.getAccountBalance() - accId.getDeailingMoney();
				if(type == 0){
					xhLoanerAccount.setAccountBalance(bal);
				}else if(type == 1){
					xhLoanerAccount.setAccountBalance(xhLoanerAccount.getAccountBalance() - accId.getDeailingMoney());
				}
				XhLoanerAccountInfo accInfo = new XhLoanerAccountInfo();
				accInfo.setBalance(0.0);
				accInfo.setDealingTime(accId.getDealingTime());
				accInfo.setDealingType(4);
				accInfo.setDeailingMoney(-accId.getDeailingMoney());
				accInfo.setBalance(bal);
				accInfo.setLoanerMainAccount(xhLoanerAccount);
				xhLoanerAccountInfoManager.saveAccInfo(accInfo);
				code = "200";
				msg = "退款成功!";
			}
		}
		DwzResult success = new DwzResult(code, msg, "rel_listhkgl,rel_listAccountInfo",
				"", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 批量导入还款
	 *
	 * @param request
	 * @param response
	 * @author xjs
	 * @date 2013-8-14 下午3:37:29
	 */
	@RequestMapping(value = "/multiSaveAccountInfo", method = RequestMethod.POST)
    public void multiSaveAccountInfo(HttpServletRequest request, HttpServletResponse response) {
        
	    List<Map<String, Object>> records = ExtractDataFromRequest.extractData(request,0, format);
	    int count = 0;
	    List<ErrorInfosBean> errors = new ArrayList<ErrorInfosBean>();
	    
	    for(Map<String, Object> record:records){
	        if(record.get("result")!= null && StringUtils.equalsIgnoreCase("成功", record.get("result").toString())){
        	        ErrorEnum flag = ErrorEnum.SUCCESS;
            	    XhLoanerAccountInfo xhLoanerAccountInfo = new XhLoanerAccountInfo();
                    String bankNum = record.get("bankNum") != null ? record.get("bankNum").toString() :"";
                    String bank = record.get("bank") != null ? record.get("bank").toString() :"";
                    String name = record.get("name") != null ? record.get("name").toString() :"";
                    String jkhtbm = record.get("jkhtbm") != null ? record.get("jkhtbm").toString() :"";//合同编号
                    if(!org.springframework.util.StringUtils.hasText(jkhtbm)){
                        flag = ErrorEnum.NOHTBM;
                    }
                    List<XhLoanerAccount> xhLoanerAccounts = xhLoanerAccountManager.getXhLoanerAccountByCondition(name, bankNum, bank,jkhtbm);
                   
                    Long mainAccountId = null;//记录主账户Id,主要是为了发生错误是写入历史记录
                    Date returnDate = null;
                    //取还款日期
                    if(record.get("returnDATE") != null && record.get("returnDATE") instanceof java.lang.Double){
                        Double doubleDate = (Double)record.get("returnDATE");
                        returnDate = new Date(doubleDate.longValue());
                    }else if(record.get("returnDATE") != null && record.get("returnDATE") instanceof java.lang.String){
                        String makeLoanDateStr = record.get("returnDATE") != null? record.get("returnDATE").toString() : "";
                        returnDate = DateConvert.convert(makeLoanDateStr, "yyyy-MM-dd HH:MM");
                    }else if(record.get("returnDATE") != null && record.get("returnDATE") instanceof java.util.Date){
                        returnDate = (Date)record.get("returnDATE");                        
                    }
                    
                    //还款金额
                    Double returnMoney = null;
                    if(record.get("returnMoney") != null && record.get("returnMoney") instanceof java.lang.Double)
                        returnMoney = (Double)record.get("returnMoney");   
                    
                    
                    
                    if(xhLoanerAccounts != null && xhLoanerAccounts.size() == 1){
                        //取得主帐号
                        XhLoanerAccount xhLoanerAccount = xhLoanerAccounts.get(0);
                        mainAccountId = xhLoanerAccount.getId();
                        //还款时间
                        
                        try {
                            
                            if(returnDate == null){
                               //无合同编号应该直接退出，后加内容暂且这样处理，因为要写错误历史记录所以这样写
                               //主要是当flag == ErrorEnum.NOHTBM 不改变flag的值，以下同
                                flag =  (flag == ErrorEnum.NOHTBM) ? ErrorEnum.NOHTBM : ErrorEnum.DATEEXCEPTION;
                            }
                            
                        } catch (Exception e) {
                            flag = (flag == ErrorEnum.NOHTBM) ? ErrorEnum.NOHTBM : ErrorEnum.ROWEXCEPTION;
                        }
                        
                                     
                        if(returnMoney == null){
                            flag = (flag == ErrorEnum.NOHTBM) ? ErrorEnum.NOHTBM : ErrorEnum.MONEYEXCEPTION;
                        }
                        if(flag == ErrorEnum.SUCCESS){
                            //没有还款记录，保证重复提交的时候只执行一次，实际操作更应该放入saveXhLoanerAccountInfo中
                            if(xhLoanerAccountInfoManager.notHandle(xhLoanerAccount.getId(),returnMoney,returnDate)){
                                xhLoanerAccountInfo.setDealingTime(returnDate);
                                xhLoanerAccountInfo.setDeailingMoney(returnMoney);
                                xhLoanerAccountInfo.setLoanerMainAccount(xhLoanerAccount);
                                xhLoanerAccountInfo.setDealingType(0);
                                Integer res = xhLoanerAccountInfoManager.saveXhLoanerAccountInfo(xhLoanerAccountInfo);
                                if(res == 1){
                                	flag = (flag == ErrorEnum.NOHTBM) ? ErrorEnum.NOHTBM :ErrorEnum.OVER;
                                }
                            }else{
                                flag = (flag == ErrorEnum.NOHTBM) ? ErrorEnum.NOHTBM :ErrorEnum.ALREADYPUT;
                            }
                        }
                    }else{
                        //未查询到记录，或多条记录
                        flag = (flag == ErrorEnum.NOHTBM) ? ErrorEnum.NOHTBM :ErrorEnum.ACCOUNT_ERROR;
                    }  
                    
                    if(flag == ErrorEnum.SUCCESS){
                        count++;
                    }else{
                        ErrorInfosBean error = new ErrorInfosBean();
                        XhLoanReturnRecords xhLoanReturnRecords = new XhLoanReturnRecords();
                        xhLoanReturnRecords.setJkrxm(name);
                        //xhLoanerAccountInfo
                        xhLoanReturnRecords.setDealingType(new Long(100));//100代表批量操作
                        xhLoanReturnRecords.setDeailingMoney(returnMoney);
                        xhLoanReturnRecords.setDealingTime(returnDate);
                        xhLoanReturnRecords.setErrorState(flag.toString());
                        xhLoanReturnRecords.setHtbm(jkhtbm);
                        if(mainAccountId != null)
                            xhLoanReturnRecords.setLoanerMainAccountId(mainAccountId);
                        
                        try{
                            //加入try catch主要是不影响下次操作
                            XhLoanReturnRecordsManager.saveXhLoanReturnRecords(xhLoanReturnRecords);
                            
                        }catch(Exception e){
                            if(logger.isErrorEnabled()){
                                logger.error(String.format("批量导入:%s 的记录是出现错误", name));
                            }
                            e.printStackTrace();
                        }
                        error.setId(record.get("name") != null ? record.get("name").toString() :"");
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
	

	@RequestMapping(value = "/addXhLoanerAccountInfo", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("folder/xhLoanerAccountInfoInput",
				"xhLoanerAccountInfo", new XhLoanerAccountInfo());
	}

	@RequestMapping(value = "/editXhLoanerAccountInfo/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		XhLoanerAccountInfo xhLoanerAccountInfo = xhLoanerAccountInfoManager
				.getXhLoanerAccountInfo(Id);
		return new ModelAndView("folder/xhLoanerAccountInfoInput",
				"xhLoanerAccountInfo", xhLoanerAccountInfo);
	}
	
     private enum ErrorEnum {
        SUCCESS, ACCOUNT_ERROR {

            @Override
            public String toString() {
                return "请核对还款账户信息";
            }
        },
        ROWEXCEPTION {

            @Override
            public String toString() {
                return "读入行出现错误，请手工放款该条数据";
            }
        },
        DATEEXIST {
            @Override
            public String toString() {
                return "已存在放款记录,请核对是否充分还款";
            }
        }, DATEEXCEPTION{
            @Override
            public String toString() {
                return "请检查还款时间";
            }
        }, MONEYEXCEPTION{
            @Override
            public String toString() {
                return "请检查还款金额";
            }
        }, ALREADYPUT{
            @Override
            public String toString() {
                return "请检查是否已经导入还款记录";
            }
        }, NOHTBM{
            @Override
            public String toString() {
                return "无合同编码信息";
            }
        }, OVER{
            @Override
            public String toString() {
                return "该记录为逾期，请执行冲抵操作！";
            }
        }
    }
}
