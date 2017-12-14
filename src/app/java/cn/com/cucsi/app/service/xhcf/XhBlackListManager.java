package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhBlackListDao;
import cn.com.cucsi.app.entity.xhcf.XhBlackList;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhBlackListManager {

	private XhBlackListDao xhBlackListDao;

	@Autowired
	public void setXhBlackListDao(XhBlackListDao xhBlackListDao) {
		this.xhBlackListDao = xhBlackListDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhBlackList> searchXhBlackList(final Page<XhBlackList> page,
			final Map<String, Object> filters) {
		return xhBlackListDao.queryXhBlackList(page, filters);
	}

	@Transactional(readOnly = true)
	public XhBlackList getXhBlackList(Long id) {
		return xhBlackListDao.get(id);
	}

	public void saveXhBlackList(XhBlackList entity) {
		xhBlackListDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhBlackList(Long id) {
		xhBlackListDao.delete(id);
	}

	public boolean batchDelXhBlackList(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhBlackList(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhBlackList(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 客户姓名
		if (filter.containsKey("name")) {
			value = String.valueOf(filter.get("name"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.NAME = '" + value + "'";
			}
		}
		// 客户身份证号
		if (filter.containsKey("identifId")) {
			value = String.valueOf(filter.get("identifId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.IDENTIF_ID = '" + value + "'";
			}
		}

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	public List<?> findBlackListBySFId(String identifId) {
		return xhBlackListDao.findBlackListBySFId(identifId);
	}
}
