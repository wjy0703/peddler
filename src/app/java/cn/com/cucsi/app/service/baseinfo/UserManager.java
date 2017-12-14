package cn.com.cucsi.app.service.baseinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.entity.security.User;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserManager {

	@Autowired
	private UserDao userDao;
	
	public User queryById(Long id){
	    return userDao.get(id);
	}	
}
