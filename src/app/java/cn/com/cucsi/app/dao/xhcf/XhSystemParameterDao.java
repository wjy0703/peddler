package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhSystemParameter;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhSystemParameterDao extends HibernateDao<XhSystemParameter, Long> {

	public Page<XhSystemParameter> queryXhSystemParameter(
			Page<XhSystemParameter> page, Map<String, Object> params) {
		String hql = "from XhSystemParameter xhSystemParameter where 1=1";
		// 备注
		if (params.containsKey("reamrk")) {
			hql = hql + " and reamrk = :reamrk";
		}
		// 变量值
		if (params.containsKey("sysValue")) {
			hql = hql + " and sysValue like '%'||:sysValue||'%' ";
		}
		// 变量说明
		if (params.containsKey("sysCname")) {
			hql = hql + " and sysCname like '%'||:sysCname||'%' ";
		}
		// 变量名
		if (params.containsKey("sysName")) {
			hql = hql + " and sysName like '%'||:sysName||'%' ";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

	public XhSystemParameter findXhSystemParameterByName(String parmName) {

		return this.findUniqueBy("sysName", parmName);
	}
}
