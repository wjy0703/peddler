package cn.com.cucsi.app.dao.loan;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqStateDao extends HibernateDao<XhJksqState, Long>{

	public Page<XhJksqState> queryXhJksqState(Page<XhJksqState> page, Map<String, Object> params){
		String hql = "from XhJksqState xhjksqstate where 1=1";
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	/**
	 * 通过借款申请Id查找借款申请流程状态历史记录
	 * @param page
	 * @param Id
	 * @return
	 */
	public Page<XhJksqState> queryXhJksqState(Page<XhJksqState> page, Long Id){
		String hql = "from XhJksqState xhjksqstate where 1=1";
		
		hql = hql + " and xhjksqstate.xhjksq.id='" + Id + "' ";
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}else{
//			hql = hql + " order by createTime asc";
			hql = hql + " order by createTime desc";
		}
		return this.findPage(page, hql);
	}
	
	public Page<XhJksqState> queryXhJksqState2(Page<XhJksqState> page, Long Id){
		String hql = "from XhJksqState xhjksqstate where 1=1";
		
		hql = hql + " and xhjksqstate.xhjksq.id='" + Id + "' ";
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}else{
//			hql = hql + " order by createTime asc";
			hql = hql + " order by createTime desc";
		}
		List<XhJksqState> list = this.find(hql);
		if(null != list && list.size()>0){
			page.setResult(list);
		}
		return page;
	}
	
	
	
}
