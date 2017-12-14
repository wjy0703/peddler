package cn.com.cucsi.app.service.xhcf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.com.cucsi.app.dao.xhcf.xhHouseLoansDao;
import cn.com.cucsi.app.entity.xhcf.XhHouseloans;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhHouseLoansManager {
private xhHouseLoansDao houseDao;
@Autowired
public void setXhHouseLoansDao(xhHouseLoansDao houseDao) {
	this.houseDao = houseDao;
}
@Transactional(readOnly = true)
public Page<XhHouseloans> searchXhHkgl(final Page<XhHouseloans> page, final Map<String,Object> filters) {
	return houseDao.queryHouseLoans(page, filters);
}
@Transactional(readOnly = true)
public XhHouseloans getXhHouseloans(Long id) {
	return houseDao.get(id);
}

public void saveXhHouseloans(XhHouseloans entity) {
	houseDao.save(entity);
}
}
