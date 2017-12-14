package cn.com.cucsi.app.service.security;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;

import com.google.common.collect.Sets;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author calvin
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountManager accountManager;
	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		User user = accountManager.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		//-- mini-web示例中无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		OperatorDetails userDetails = new OperatorDetails(user.getLoginName(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		//加入登录时间信息和用户角色
		userDetails.setLoginTime(new Date());
		userDetails.setRoleList(user.getRoleList());
		userDetails.setUserId(user.getId());
		if ((user.getEmployee()!=null)&&(user.getEmployee().getName()!=null)){
			userDetails.setCtiCode(user.getEmployee().getName());
		}
		if ((user.getEmployee()!=null)&&(user.getEmployee().getOrgani()!=null)){
			Employee employee = user.getEmployee();
			userDetails.setDeptId(employee.getOrgani().getId());
			String cityInData = accountManager.getLoginCityData(employee.getOrgani().getId());
			userDetails.setCityInData(cityInData);
			userDetails.setLoginEmployeeId(employee.getId());
			userDetails.setPositionCode(employee.getPosition().getPositionCode());
		}
		return userDetails;

	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			for (Authority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}

	
}
