package cn.com.cucsi.app.dao.xhcf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReport;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCapitalLoanReportDao extends
		HibernateDao<XhCapitalLoanReport, Long> {

	public Page<XhCapitalLoanReport> queryXhCapitalLoanReport(
			Page<XhCapitalLoanReport> page, Map<String, Object> params) {
		
		
		String hql = "from XhCapitalLoanReport xhCapitalLoanReport where 1=1";
		// 客户编号
		if (params.containsKey("lenderNumber")) {
			
			hql = hql + " and lenderNumber like '"+ "%"+params.get("lenderNumber")+"%'";
		}
		
		// 报告日
		if (params.containsKey("reportDate")) {
			String strReportDate = (String) params.get("reportDate");
			Date reportDate = CreditHarmonyComputeUtilties.StringToDate(
					strReportDate, "yyyy-MM-dd");
		//	params.remove("reportDate");
			params.put("reportDate", reportDate);
		
			hql = hql + " and reportDate = :reportDate";
		}
		
		// 客户姓名
		if (params.containsKey("lenderName")) {
			//criteria.add(Expression.like("lenderName", "%"+params.get("lenderName")+"%"));
			//params.put("lenderName", "%"+params.get("lenderName")+"%");
			hql = hql + " and lenderName like '"+ "%"+params.get("lenderName")+"%'";
		}
		
		// 投资产品
		if (params.containsKey("tzcp")) {
			params.put("tzcp", Long.parseLong(params.get("tzcp")+""));
			//criteria.add(Expression.like("lenderName", "%"+params.get("lenderName")+"%"));
			//params.put("lenderName", "%"+params.get("lenderName")+"%");
			hql = hql + " and xhCapitalLoanReport.tzsq.tzcp.id = :tzcp";
		}
		
		if(params.containsKey("province")){
			
			hql = hql + " and xhCapitalLoanReport.tzsq.cjrxx.province = :province";
		}
		
		if(params.containsKey("city")){
			
			hql = hql + " and xhCapitalLoanReport.tzsq.cjrxx.city = :city";
		}
		
		if(params.containsKey("zqjsfs")){
			
			hql = hql + " and xhCapitalLoanReport.tzsq.cjrxx.zqjsfs =:zqjsfs";
		}
		
		// 状态（0：未生成报告文件、1：已生成报告文件）
		if (params.containsKey("state")) {
//			params.put("state", Long.parseLong(params.get("state")+""));
			//criteria.add(Expression.like("lenderName", "%"+params.get("lenderName")+"%"));
			//params.put("lenderName", "%"+params.get("lenderName")+"%");
			hql = hql + " and state = "+params.get("state");
		}
		
		//邮件发送状态
		if(params.containsKey("billSendState")){
			hql = hql +" and billSendState = " +params.get("billSendState");
		}
		//报表制作状态
		if(params.containsKey("reportMakeState")){
		    hql = hql +" and reportMakeState = " +params.get("reportMakeState");
		}
		
		if (page.getOrderBy() != null) {
		//	if("asc".equals(page.getOrder()))
		//	criteria.addOrder(Order.asc( page.getOrderBy() ));
		//	else if("desc".equals(page.getOrder()))
		//	criteria.addOrder(Order.desc( page.getOrderBy() ));
			
		hql = hql + " order by " + page.getOrderBy() + " "
				+ page.getOrder();
		}
		return this.findPage(page, hql,params);
	
		//return this.findPage(page, criteria);
	}
	public List<XhCapitalLoanReport> findByTzsq(Map<String, Object> params){
		String hql = "from XhCapitalLoanReport xhCapitalLoanReport where 1=1";
		// 客户编号
		if (params.containsKey("tzsqid")) {
			hql = hql + " and tzsq.id = :tzsqid";
		}
 		
		//	if("asc".equals(page.getOrder()))
		//	criteria.addOrder(Order.asc( page.getOrderBy() ));
		//	else if("desc".equals(page.getOrder()))
		//	criteria.addOrder(Order.desc( page.getOrderBy() ));
			
		hql = hql + " order by reportDate desc";
		return this.find(hql,params);
	}
	
	
	public List<Date> getAllReportDate() {
		String hql = "select distinct reportDate from XhCapitalLoanReport xhCapitalLoanReport order by reportDate desc";
		return this.find(hql);
	}

}
