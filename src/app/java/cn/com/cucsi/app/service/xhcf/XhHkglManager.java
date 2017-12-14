package cn.com.cucsi.app.service.xhcf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.xhcf.XhCjrgtjlDao;
import cn.com.cucsi.app.dao.xhcf.XhHkglDao;
import cn.com.cucsi.app.dao.xhcf.XhfkglDao;
import cn.com.cucsi.app.entity.xhcf.XhFkgl;
import cn.com.cucsi.app.entity.xhcf.XhHkgl;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhHkglManager {
	
	private XhHkglDao xhHkglDao;
	@Autowired
	public void setXhHkglDao(XhHkglDao xhHkglDao) {
		this.xhHkglDao = xhHkglDao;
	}
	@Transactional(readOnly = true)
	public Page<XhHkgl> searchXhHkgl(final Page<XhHkgl> page, final Map<String,Object> filters) {
		return xhHkglDao.queryHkgl(page, filters);
	}
	@Transactional(readOnly = true)
	public XhHkgl getXhHkgl(Long id) {
		return xhHkglDao.get(id);
	}

	public void saveXhHkgl(XhHkgl entity) {
		xhHkglDao.save(entity);
	}
}
