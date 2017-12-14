package cn.com.cucsi.app.dao.loan;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJkrxx;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author 
 */
@Component
public class XhJkrxxDao extends HibernateDao<XhJkrxx, Long> {
	
	/**
	 * 借款人信息查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<XhJkrxx> queryXhcfDkrxx(Page<XhJkrxx> page, Map<String, Object> filters) {
		String hql = "from XhJkrxx xhjkrxx where 1=1";
		
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
	 * 借款人信息变更列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<XhJkrxx> queryXhJkrxxxgjl(Page<XhJkrxx> page, Map<String, Object> filters) {
		String hql = "from XhJkrxx xhjkrxx where xhjkrxx.changeState='1' ";
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}			

	
	/**
	 * 添加借款人信息变更申请中的借款人带回
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<XhJkrxx> getXhJkrxxList(Page<XhJkrxx> page, Map<String, Object> filters){
//		String hql = "from XhJkrxx xhjkrxx where xhjkrxx.state='2' ";
		String hql = "from XhJkrxx xhjkrxx where 2=2 ";
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}

	
	
}
