package cn.com.cucsi.app.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Customer;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class CustomerDao extends HibernateDao<Customer, Long> {

	public Page<Customer> queryCustomer(Page<Customer> page, Map<String, Object> filters) {
		String hql = "from Customer customer where 1=1";
		
		if(filters.containsKey("name")){
			hql = hql + " and name like '%'||:name||'%'";
		}
		if(filters.containsKey("sts")){
			hql = hql + " and sts = :sts";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}			

	public List<Customer> getCustomerByCall(String caller) {
		String hql = "from Customer customer where customer.mobile1 = '"+caller+"' or customer.mobile2 = '"+caller+"' or customer.tel1 = '"+caller+"' or customer.tel2 = '"+caller+"'";
		
		return this.find(hql);
		
	}

}
