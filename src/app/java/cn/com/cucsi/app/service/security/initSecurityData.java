package cn.com.cucsi.app.service.security;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.security.AuthorityDao;
import cn.com.cucsi.app.dao.security.RoleDao;
import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.core.utils.EncodeUtils;

@Service("initAuthorityService")
@Transactional
public class initSecurityData {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AuthorityDao authorityDao;

	public void initAll() throws Exception {
		deleteAllAuthority();
		deleteAllRole();
		deleteAllUser();
		// 初始化
		initAuthority();
		initRole();
		initUser();
	}

	/*
	 * 删除所有用户
	 */
	public void deleteAllUser() throws SQLException {
		userDao.batchExecute("delete from User");
	}

	/*
	 * 删除所有角色
	 */
	public void deleteAllRole() throws SQLException {
		userDao.executeUpdateBySql("delete from base_user_role");
		userDao.batchExecute("delete from Role");
	}

	/*
	 * 删除所有资源
	 */
	public void deleteAllAuthority() throws SQLException {
		userDao.executeUpdateBySql("delete from base_role_authority");
		userDao.batchExecute("delete from Authority");
	}

	public void initAuthority() throws Exception {
		Authority a = new Authority();
		a.setName("查看用户");
		authorityDao.save(a);
		// =================================
		a = new Authority();
		a.setName("编辑用户");
		authorityDao.save(a);
		// =================================
		a = new Authority();
		a.setName("查看角色");
		authorityDao.save(a);
		// =================================
		a = new Authority();
		a.setName("编辑角色");
		authorityDao.save(a);
		// =================================
		a = new Authority();
		a.setName("查看权限");
		authorityDao.save(a);
		// =================================
		a = new Authority();
		a.setName("编辑权限");
		authorityDao.save(a);
	}

	public void initRole() throws Exception {
		Role r = new Role();
		r.setName("超级管理员");
		List<Authority> a = authorityDao.getAll();
		r.setAuthorityList(a);
		roleDao.save(r);
	}

	public void initUser() throws Exception {
		User u = new User();
		u.setLoginName("admin");
		u.setCname("管理员");
		u.setPassword("admin");
		
		List<Role> r = roleDao.getAll();
		u.setRoleList(r);
		saveUser(u);
		// =================================
		u = new User();
		u.setLoginName("user");
		u.setCname("普通用户");
		u.setPassword("user");
		r = roleDao.getAll();
		u.setRoleList(r);
		saveUser(u);
	}

	public void saveUser(User entity) {
		if (entity.getPassword().length() < 32) {
			String psw = EncodeUtils.getMd5PasswordEncoder(
					entity.getPassword(), entity.getLoginName());
			entity.setPassword(psw);
		}
		userDao.save(entity);
	}
}
