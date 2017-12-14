package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.dao.xhcf.XhCapitalLoanReportDao;
import cn.com.cucsi.app.dao.xhcf.XhMadewordDao;
import cn.com.cucsi.app.dao.xhcf.XhSendemailDao;
import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReport;
import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReportInfo;
import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.app.entity.xhcf.XhSendemail;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.Java2Word;
import cn.com.cucsi.app.web.util.MoneyUtil;
import cn.com.cucsi.app.web.util.converter.docConverter.DocConverter;
import cn.com.cucsi.app.web.util.converter.pdfConverter.OpenOfficePDFConverter;
import cn.com.cucsi.app.web.util.converter.pdfConverter.PDFConverter;
import cn.com.cucsi.app.web.util.converter.swfConverter.SWFConverter;
import cn.com.cucsi.app.web.util.converter.swfConverter.SWFToolsSWFConverter;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCapitalLoanReportManager {
	@Autowired
	private CityDao cityDao;

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	private XhMadewordDao xhMadewordDao;
	@Autowired
	public void setXhMadewordDao(XhMadewordDao xhMadewordDao) {
		this.xhMadewordDao = xhMadewordDao;
	}
	private XhCapitalLoanReportDao xhCapitalLoanReportDao;

	@Autowired
	public void setXhCapitalLoanReportDao(
			XhCapitalLoanReportDao xhCapitalLoanReportDao) {
		this.xhCapitalLoanReportDao = xhCapitalLoanReportDao;
	}
	
	private XhSendemailDao xhSendemailDao;

	@Autowired
	public void setXhSendemailDao(XhSendemailDao xhSendemailDao) {
		this.xhSendemailDao = xhSendemailDao;
	}
	

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhCapitalLoanReport> searchXhCapitalLoanReport(
			final Page<XhCapitalLoanReport> page,
			final Map<String, Object> filters) {
		return xhCapitalLoanReportDao.queryXhCapitalLoanReport(page, filters);
	}
	@Transactional(readOnly = true)
	public List<XhCapitalLoanReport> findByTzsq(Map<String, Object> filter){
		return xhCapitalLoanReportDao.findByTzsq(filter);
	}
	
	@Transactional(readOnly = true)
	public XhCapitalLoanReport getXhCapitalLoanReport(Long id) {
		return xhCapitalLoanReportDao.get(id);
	}

	public void saveXhCapitalLoanReport(XhCapitalLoanReport entity) {
		xhCapitalLoanReportDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCapitalLoanReport(Long id) {
		xhCapitalLoanReportDao.delete(id);
	}

	public boolean batchDelXhCapitalLoanReport(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhCapitalLoanReport(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void makeCapitalLoanReportFile(Long id) {
		XhCapitalLoanReport xhCapitalLoanReport = xhCapitalLoanReportDao
				.get(id);
//		if(!"1".equals(xhCapitalLoanReport.getState())){
			xhCapitalLoanReport.setState("1");
			xhCapitalLoanReportDao.save(xhCapitalLoanReport);
//		}
		//保存到制作表
		XhMadeword xhMadeword = new XhMadeword();
		xhMadeword.setState("0");
		xhMadeword.setStype("0");
		xhMadeword.setTableId(id);
		xhMadeword.setTableName("xh_zzcapital_loan_report");
		xhMadewordDao.save(xhMadeword);
		/*
		// List<XhCapitalLoanReportInfo> xhCapitalLoanReportInfos =
		// xhCapitalLoanReport
		// .getXhCapitalLoanReportInfos();
		Map filters = new HashMap();
		filters.put("id", id);
		List<Map<String, Object>> reportInfos = this.searchXhCapitalLoanReport(
				"queryXhCapitalLoanReportList", filters);
		Java2Word j = new Java2Word();
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String mouFilePath2 = InitSetupListener.filePath + "report"
				+ File.separator;
		XhcfCjrxx lender = xhCapitalLoanReport.getLender();

		HashMap data = new HashMap();
		data.put("zipCode", lender.getYb());// 邮编
*/
		/*
		 * cityDao.get(Long.parseLong(lender.getProvince())) .getName() +
		 * cityDao.get(Long.parseLong(lender.getCity())).getName() +
		 * cityDao.get(Long.parseLong(lender.getArea())).getName() +
		 */
		/*
		data.put("address", lender.getTxdz());// 地址
		String sex = lender.getCjrxb().trim();
		String chenghu = "先生";
		if (sex.equals("女")) {
			chenghu = "女士";
		}
		data.put("chenghu", chenghu);// 称呼

		data.put("name", lender.getCjrxm());// 尊敬的***
		data.put("reportCicle", xhCapitalLoanReport.getReportCycle());
//		data.put(
//				"allCapitalValue",
//				MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhCapitalLoanReport
//						.getAllMoneyOfCurrent() + "")));
		
//
		String acountLevel = "";
		if (xhCapitalLoanReport.getAccountLevel() == 1)
			acountLevel = "信和账户";
		else if (xhCapitalLoanReport.getAccountLevel() == 2)
			acountLevel = "贵宾账户";
		else if (xhCapitalLoanReport.getAccountLevel() == 3)
			acountLevel = "金卡账户";
		else if (xhCapitalLoanReport.getAccountLevel() == 4)
			acountLevel = "白金账户";
		else if (xhCapitalLoanReport.getAccountLevel() == 5)
			acountLevel = "钻石账户";

		data.put("acountLevel", acountLevel);
		data.put("lenderNo", xhCapitalLoanReport.getLenderNumber());

		String reportDate = CreditHarmonyComputeUtilties
				.dateToString(xhCapitalLoanReport.getReportDate());
		data.put("reportDate", reportDate);

		/*
		 * select b.all_money as ALL_MONEY, b.first_lend_date as
		 * FIRST_LEND_DATE,b.first_lend_money as FIRST_LEND_MONEY,b.lend_no as
		 * LEND_NO ,b.lend_type as LEND_TYPE ,sum(b.shoud_back) as
		 * ALL_SHOULD_BACK,b.lend_no,sum(b.real_back) as
		 * ALL_REAL_BACK,sum(b.re_lend) as ALL_RE_LEND,sum(b.drawing) as
		 * ALL_DRAWING from XH_CAPITAL_LOAN_REPORT a,XH_CAPITAL_LOAN_REPORT_INFO
		 * b where 1=1 and a.id=15318 and b.report_id=a.id group by
		 * b.first_lend_date
		 * ,b.first_lend_money,b.lend_no,b.lend_type,b.all_money
		 */
		/*
		String lendNumber = "";
		if (reportInfos.size() > 0) {
			List tableOne = new ArrayList();
			String[] insertColumnNo = { "1", "2", "3", "4" };

			tableOne.add(insertColumnNo);
			for (Map<String, Object> m : reportInfos) {

				String[] insertColumnContent = new String[4];
				lendNumber = (String) m.get("LEND_NO");
				insertColumnContent[0] = (String) m.get("LEND_NO");
				insertColumnContent[1] = (String) m.get("LEND_TYPE");
//				insertColumnContent[2] = CreditHarmonyComputeUtilties
//						.dateToString((Date) m.get("FIRST_LEND_DATE"));
				insertColumnContent[2] = xhCapitalLoanReport.getTzsq().getJhtzrq();
				insertColumnContent[3] = MoneyUtil.toEnglish(MoneyUtil
						.reBackTwo(m.get("FIRST_LEND_MONEY") + ""));
				tableOne.add(insertColumnContent);

			}
			data.put("table$5@2", tableOne);

			List tableTwo22 = new ArrayList();
			String[] insertColumnNoOftableTwo22 = { "1", "2", "3", "4", "5",
					"6", "7", "8", "9" };
			tableTwo22.add(insertColumnNoOftableTwo22);
			Map<String, Object> mTemp=null;
			for (Map<String, Object> m : reportInfos) {
				mTemp=m;
				String[] insertColumnContent22 = new String[9];
				// insertColumnContent22[0] = (String) m.get("LEND_NO");
				data.put("lendNo", (String) m.get("LEND_NO"));
				insertColumnContent22[0] = reportDate;
				insertColumnContent22[1] = MoneyUtil.toEnglish(MoneyUtil
						.reBackTwo(m.get("ALL_SHOULD_BACK") + ""));
				insertColumnContent22[2] = MoneyUtil.toEnglish(MoneyUtil
						.reBackTwo(m.get("ALL_REAL_BACK") + ""));
				insertColumnContent22[3] = "0.00";
				// insertColumnContent22[5] = info.getMngFeeRate() + "";
				// insertColumnContent22[6] =
				// MoneyUtil.toEnglish(info.getMngFee()
				// + "");
				insertColumnContent22[4] = "0.00";
				insertColumnContent22[5] = "0.00";
				insertColumnContent22[6] = MoneyUtil.toEnglish(MoneyUtil
						.reBackTwo(m.get("ALL_RE_LEND") + ""));
				insertColumnContent22[7] = MoneyUtil.toEnglish(MoneyUtil
						.reBackTwo(m.get("ALL_DRAWING") + ""));
				if(!"月息通".equals((String) m.get("LEND_TYPE"))){
					insertColumnContent22[8] = MoneyUtil.toEnglish(MoneyUtil
							.reBackTwo(((BigDecimal)m.get("FIRST_LEND_MONEY")).add((BigDecimal)m.get("ALL_MONEY")) + ""));
				}else{
					insertColumnContent22[8] = MoneyUtil.toEnglish(MoneyUtil
							.reBackTwo(m.get("FIRST_LEND_MONEY") + ""));
				}
				

				tableTwo22.add(insertColumnContent22);

			}
			if(mTemp!=null){
				if(!"月息通".equals((String)mTemp.get("LEND_TYPE"))){
					data.put("allCapitalValue",MoneyUtil.toEnglish(MoneyUtil.reBackTwo(((BigDecimal)mTemp.get("FIRST_LEND_MONEY")).add((BigDecimal)mTemp.get("ALL_MONEY")) + "")));}
				else{
					data.put("allCapitalValue",MoneyUtil.toEnglish(MoneyUtil.reBackTwo(mTemp.get("FIRST_LEND_MONEY") + "")));
				}
			}
			
			data.put("table$3@3", tableTwo22);

		}
		String nextDate = CreditHarmonyComputeUtilties.getLastDateOfBackMoney(
				reportDate, 2);
		if (null != nextDate && nextDate.contains("-")) {
			String[] YMD = nextDate.split("-");
			if (YMD.length == 3) {
				data.put("{NIAN}", YMD[0]);
				data.put("{YUE}", YMD[1]);
				data.put("{RI}", YMD[2]);
			}
		}
		if (null != reportDate && reportDate.contains("-")) {
			String[] YMD1 = reportDate.split("-");
			if (YMD1.length == 3) {
				int iRI1 = Integer.parseInt(YMD1[2]) - 2;
				data.put("{NIAN1}", YMD1[0]);
				data.put("{YUE1}", YMD1[1]);
				data.put("{RI1}", iRI1);
			}

		}

		lendNumber = lendNumber.substring(lendNumber.lastIndexOf('-') + 1);
		// 资金出借报告-" + lender.getCjrxm() + "-"+reportDate + "
		String fileName = "资金出借报告-" + lender.getCjrxm() + "-" + lendNumber+"("+id+")"
				+ ".doc";
		// 资金出借报告-"+lender.getCjrxm()+"-"+xhCapitalLoanReport.getReportDate()+"
		j.toWord(mouFilePath + "lendReport.doc", mouFilePath2 + reportDate
				+ File.separator + fileName, data);
		j.quit();
		// PDFConverter pdfConverter = new OpenOfficePDFConverter();
		//
		// DocConverter converter = new DocConverter(pdfConverter, null);
		// converter.convertPdf(mouFilePath + reportDate + File.separator
		// + fileName);
		*/
		

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhCapitalLoanReport(
			String queryName, Map<String, Object> filter) {
		String sql = "";
		String value = "";

		// ID
		if (filter.containsKey("id")) {
			value = String.valueOf(filter.get("id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = '" + value + "'";
			}
		}
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}

	/**
	 * 
	 * @return
	 */
	public List<Date> getAllReportDate() {

		return xhCapitalLoanReportDao.getAllReportDate();
	}
	
	/**
	 * 批量发送
	 * @param id
	 */
	
	public void saveXhSendemail(String id) {
		XhSendemail xsd = new XhSendemail();
		xsd.setState("0");
		xsd.setTableId(Long.parseLong(id));
		xsd.setTableName("xh_zzcapital_loan_report");
		xhSendemailDao.save(xsd);
	}
	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> queryLoanReportListForDown(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		// 客户编号
		if (filter.containsKey("lenderNumber")) {
			value = String.valueOf(filter.get("lenderNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.khbm like '%"+value+"%'";
			}
		}
		
		// 报告日
		if (filter.containsKey("reportDate")) {
			value = String.valueOf(filter.get("reportDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_char(a.report_date,'yyyy-MM-dd') = '"+value+"'";
			}
		}
		
		// 客户姓名
		if (filter.containsKey("lenderName")) {
			value = String.valueOf(filter.get("lenderName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.cjrxm like '%" +  value + "%'";
			}
		}
		// 投资产品
		if (filter.containsKey("tzcp")) {
			value = String.valueOf(filter.get("tzcp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.tzcp_id = " + value;
			}
			//criteria.add(Expression.like("lenderName", "%"+filter.get("lenderName")+"%"));
			//filter.put("lenderName", "%"+filter.get("lenderName")+"%");
		}
		
		// 状态（0：未生成报告文件、1：已生成报告文件）
		if (filter.containsKey("state")) {
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '"+value+"'";
			}
		}
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
}
