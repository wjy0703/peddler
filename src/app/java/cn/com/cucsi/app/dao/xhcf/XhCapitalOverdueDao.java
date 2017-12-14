package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhCapitalOverdue;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCapitalOverdueDao extends HibernateDao<XhCapitalOverdue, Long>{

	public Page<XhCapitalOverdue> queryXhCapitalOverdue(Page<XhCapitalOverdue> page, Map<String, Object> params){
		String hql = "from XhCapitalOverdue x where 1=1";
		//主键
		if(params.containsKey("id")){
			hql = hql + " and id = :id";
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
		//违约金
		if(params.containsKey("damagesMoney")){
			hql = hql + " and damagesMoney = :damagesMoney";
		}
		//借款人ID
		if(params.containsKey("lenderId")){
			hql = hql + " and lenderId = :lenderId";
		}
		//借款人身份证号
		if(params.containsKey("lenderIdCard")){
			hql = hql + " and lenderIdCard like '%'||:lenderIdCard||'%'";
		}
		//借款人名称
		if(params.containsKey("lenderName")){
			hql = hql + " and lenderName like '%'||:lenderName||'%'";
		}
		//借款编号
		if(params.containsKey("lenderNumber")){
			hql = hql + " and lenderNumber like '%'||:lenderNumber||'%'";
		}
		//逾期时间
		if(params.containsKey("overdueDate")){
			hql = hql + " and to_char(x.overdueDate,'YYYY-mm') = :overdueDate";
		}
		//逾期金额
		if(params.containsKey("overdueMoney")){
			hql = hql + " and overdueMoney = :overdueMoney";
		}
		//逾期状态
		if(params.containsKey("overdueStatr")){
			hql = hql + " and overdueStatr = :overdueStatr";
		}
		//罚息
		if(params.containsKey("punishInterest")){
			hql = hql + " and punishInterest = :punishInterest";
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
