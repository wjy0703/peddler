package cn.com.cucsi.app.dao.loan;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqTogetherDao extends HibernateDao<XhJksqTogether, Long>{

	public Page<XhJksqTogether> queryXhJksq(Page<XhJksqTogether> page, Map<String, Object> params){
		String hql = "from XhJksqTogether xhjksqtogether where 1=1";
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	/**
	 * 通过借款申请信息查找共同借款人信息
	 * @param xhJksq
	 * @return
	 */
	public List<XhJksqTogether> getXhJksqTogether(XhJksq xhJksq){
		StringBuffer hql = new StringBuffer();
		hql.append(" from XhJksqTogether xhjksqtogether where 1=1 ");
		hql.append(" and xhjksqtogether.xhjksq.id='").append(xhJksq.getId()).append("'");
		hql.append(" order by xhjksqtogether.createTime desc");
		return this.find(hql.toString());
	}
	
}
