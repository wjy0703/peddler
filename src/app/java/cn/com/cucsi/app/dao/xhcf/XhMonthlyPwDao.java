package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMonthlyPw;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMonthlyPwDao extends HibernateDao<XhMonthlyPw, Long>{

	public Page<XhMonthlyPw> queryXhMonthlyPw(Page<XhMonthlyPw> page, Map<String, Object> params){
		String hql = "from XhMonthlyPw xhMonthlyPw where 1=1";
		//主键
		if(params.containsKey("id")){
			hql = hql + " and id = :id";
		}
		//帐外
		if(params.containsKey("additional")){
			hql = hql + " and additional = :additional";
		}
		//银行名称
		if(params.containsKey("bankName")){
			hql = hql + " and bankName = :bankName";
		}
		//银行账号
		if(params.containsKey("bankNumber")){
			hql = hql + " and bankNumber = :bankNumber";
		}
		//银行开户行
		if(params.containsKey("bankOpen")){
			hql = hql + " and bankOpen = :bankOpen";
		}
		//利息
		if(params.containsKey("interest")){
			hql = hql + " and interest = :interest";
		}
		//出借人ID
		if(params.containsKey("lenderId")){
			hql = hql + " and lenderId = :lenderId";
		}
		//出借人身份证号
		if(params.containsKey("lenderIdCard")){
			hql = hql + " and lenderIdCard = :lenderIdCard";
		}
		//出借人名称
		if(params.containsKey("lenderName")){
			hql = hql + " and lenderName = :lenderName";
		}
		//出借编号
		if(params.containsKey("lenderNumber")){
			hql = hql + " and lenderNumber = :lenderNumber";
		}
		//出借状态
		if(params.containsKey("lenderState")){
			hql = hql + " and lenderState = :lenderState";
		}
		//金额
		if(params.containsKey("money")){
			hql = hql + " and money = :money";
		}
		//付款日期
		if(params.containsKey("payDate")){
			hql = hql + " and payDate = :payDate";
		}
		//付款类型
		if(params.containsKey("payType")){
			hql = hql + " and payType = :payType";
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
