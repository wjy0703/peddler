package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReportInfo;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCapitalLoanReportInfoDao extends HibernateDao<XhCapitalLoanReportInfo, Long>{

	public Page<XhCapitalLoanReportInfo> queryXhCapitalLoanReportInfo(Page<XhCapitalLoanReportInfo> page, Map<String, Object> params){
		String hql = "from XhCapitalLoanReportInfo xhCapitalLoanReportInfo where 1=1";
		//报告主表
		if(params.containsKey("reportId")){
			hql = hql + " and reportId = :reportId";
		}
		//出借编号
		if(params.containsKey("lendNo")){
			hql = hql + " and lendNo = :lendNo";
		}
		//初始出借日期
		if(params.containsKey("firstLendDate")){
			hql = hql + " and firstLendDate = :firstLendDate";
		}
		//出借及回收方式
		if(params.containsKey("lendType")){
			hql = hql + " and lendType = :lendType";
		}
		//初始出借金额
		if(params.containsKey("firstLendMoney")){
			hql = hql + " and firstLendMoney = :firstLendMoney";
		}
		//本期应还金额
		if(params.containsKey("shoudBack")){
			hql = hql + " and shoudBack = :shoudBack";
		}
		//本期实际还款金额
		if(params.containsKey("realBack")){
			hql = hql + " and realBack = :realBack";
		}
		//延迟支付金额
		if(params.containsKey("latePayMoney")){
			hql = hql + " and latePayMoney = :latePayMoney";
		}
		//账户管理费率
		if(params.containsKey("mngFeeRate")){
			hql = hql + " and mngFeeRate = :mngFeeRate";
		}
		//账户管理费
		if(params.containsKey("mngFee")){
			hql = hql + " and mngFee = :mngFee";
		}
		//当期受让金额
		if(params.containsKey("reLend")){
			hql = hql + " and reLend = :reLend";
		}
		//当期回收金额
		if(params.containsKey("drawing")){
			hql = hql + " and drawing = :drawing";
		}
		//当前全部账户资产
		if(params.containsKey("allMoney")){
			hql = hql + " and allMoney = :allMoney";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
