package cn.com.cucsi.app.service.xhcf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.xhcf.XhCjrgtjlDao;
import cn.com.cucsi.app.dao.xhcf.XhfkglDao;
import cn.com.cucsi.app.entity.xhcf.XhFkgl;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhfkglManager {
	
	private XhfkglDao xhfkglDao;
	@Autowired
	public void setXhfkglDao(XhfkglDao xhfkglDao) {
		this.xhfkglDao = xhfkglDao;
	}
	@Transactional(readOnly = true)
	public Page<XhFkgl> searchXhFkgl(final Page<XhFkgl> page, final Map<String,Object> filters) {
		return xhfkglDao.queryFkgl(page, filters);
	}
	@Transactional(readOnly = true)
	public XhFkgl getXhFkgl(Long id) {
		return xhfkglDao.get(id);
	}

	public void saveXhFkgl(XhFkgl entity) {
		xhfkglDao.save(entity);
	}
}
