package cn.com.cucsi.app.dao.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhBlackList;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhBlackListDao extends HibernateDao<XhBlackList, Long> {

	public Page<XhBlackList> queryXhBlackList(Page<XhBlackList> page,
			Map<String, Object> params) {
		String hql = "from XhBlackList xhBlackList where 1=1";
		// 客户姓名
		if (params.containsKey("name")) {
			hql = hql + " and name = :name";
		}
		// 客户身份证号
		if (params.containsKey("identifId")) {
			hql = hql + " and identifId = :identifId";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

	public List<?> findBlackListBySFId(String identifId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifId", identifId);
		String hql = "from XhBlackList xhBlackList where 1=1 and identifId = :identifId";
		return this.find(hql, params);
	}
}
