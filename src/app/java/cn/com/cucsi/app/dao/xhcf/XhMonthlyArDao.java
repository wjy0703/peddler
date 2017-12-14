package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMonthlyAr;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMonthlyArDao extends HibernateDao<XhMonthlyAr, Long>{

	public Page<XhMonthlyAr> queryXhMonthlyAr(Page<XhMonthlyAr> page, Map<String, Object> params){
		String hql = "from XhMonthlyAr xhMonthlyAr where 1=1";
		//帐外
		if(params.containsKey("additional")){
			hql = hql + " and additional = :additional";
		}
		//省份
		if(params.containsKey("area")){
			hql = hql + " and area = :area";
		}
		//银行名称
		if(params.containsKey("bankName")){
			hql = hql + " and bankName = :bankName";
		}
		//银行账户
		if(params.containsKey("bankNumber")){
			hql = hql + " and bankNumber = :bankNumber";
		}
		//银行开户行
		if(params.containsKey("bankOpen")){
			hql = hql + " and bankOpen = :bankOpen";
		}
		//账单日
		if(params.containsKey("billDay")){
			hql = hql + " and billDay = :billDay";
		}
		//地市
		if(params.containsKey("city")){
			hql = hql + " and city = :city";
		}
		//利息
		if(params.containsKey("interest")){
			hql = hql + " and interest = :interest";
		}
		//借款人ID
		if(params.containsKey("loanId")){
			hql = hql + " and loanId = :loanId";
		}
		//借款人身份证号
		if(params.containsKey("loanIdCard")){
			hql = hql + " and loanIdCard = :loanIdCard";
		}
		//借款人名称
		if(params.containsKey("loanName")){
			hql = hql + " and loanName = :loanName";
		}
		//借款人编号
		if(params.containsKey("loanNumber")){
			hql = hql + " and loanNumber = :loanNumber";
		}
		//借款产品
		if(params.containsKey("loanPro")){
			hql = hql + " and loanPro = :loanPro";
		}
		//借款状态
		if(params.containsKey("loanState")){
			hql = hql + " and loanState = :loanState";
		}
		//金额
		if(params.containsKey("money")){
			hql = hql + " and money = :money";
		}
		//结算日期
		if(params.containsKey("settlementDate")){
			hql = hql + " and settlementDate = :settlementDate";
		}
		//备用字段1
		if(params.containsKey("spareField01")){
			hql = hql + " and spareField01 = :spareField01";
		}
		//备用字段2
		if(params.containsKey("spareField02")){
			hql = hql + " and spareField02 = :spareField02";
		}
		//备用字段3
		if(params.containsKey("spareField03")){
			hql = hql + " and spareField03 = :spareField03";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
