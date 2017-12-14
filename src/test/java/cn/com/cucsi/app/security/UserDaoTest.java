package cn.com.cucsi.app.security;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import cn.com.cucsi.app.dao.security.RoleDao;
import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.core.test.spring.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@SuppressWarnings("static-access")
public class UserDaoTest extends SpringTxTestCase {

	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;


	@Test
	public void getUser(){
		User u = userDao.get(1L);
		
		List<Role> list = u.getRoleList();
		for(Role r: list){
			System.out.println(r.getName());
		}
		
		this.assertTrue("ok", list.size()>0);
	}
	
	@Test
	public void getRole(){
		Role r = roleDao.get(1L);
		List<User> list = r.getUserList();
		this.assertTrue(list.size()>0);
	}
	
	@Test
	@Rollback(false)
	public void deleteUer(){
		
		userDao.delete(8L);
		userDao.flush();
	}
	
	@Test
	@Rollback(false)
	public void saveUser(){
		User u = userDao.get(7L);
		List<Role> roleList = new LinkedList<Role>();
		Role r = roleDao.get(1L);
		roleList.add(r);
		r = roleDao.get(2L);
		roleList.add(r);
		u.setRoleList(roleList);
		
		userDao.save(u);
		
	}
}
