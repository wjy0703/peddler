package cn.com.cucsi.app.dao.loan;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJkrxx;
import cn.com.cucsi.app.entity.xhcf.XhJkrxxUPHistory;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author 
 */
@Component
public class XhJkrxxUPHistoryDao extends HibernateDao<XhJkrxxUPHistory, Long> {
	
	
	public Page<XhJkrxxUPHistory> queryXhcfDkrxxxgjl(Page<XhJkrxxUPHistory> page, Map<String, Object> filters) {
		String hql = "from XhJkrxxUPHistory xhjkrxxuphistory where 1=1";
		
		if(filters.containsKey("name")){
			hql = hql + " and supplierName like '%'||:name||'%'";
		}
		if(filters.containsKey("sts")){
			hql = hql + " and sts = :sts";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}			
	
	/**
	 * 某借款人历史信息查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<XhJkrxxUPHistory> queryXhJkrxxxgjl(Page<XhJkrxxUPHistory> page, Map<String, Object> filters, 
			XhJkrxx xhJkrxx) {
		String hql = "from XhJkrxxUPHistory xhjkrxxuphistory where 1=1";
		hql = hql + " and xhjkrxxuphistory.xhJkrxx.id='"+xhJkrxx.getId()+"' ";
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}			

	
	

	
	
}
