package cn.com.cucsi.app.service.security;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.MenuDao;
import cn.com.cucsi.app.dao.security.AuthorityDao;
import cn.com.cucsi.app.dao.security.RoleDao;
import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.ReflectionUtils;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AccountManager {

	private static Logger logger = LoggerFactory.getLogger(AccountManager.class);

	private UserDao userDao;
	private RoleDao roleDao;
    private AuthorityDao authorityDao;
    private MenuDao menuDao;
    private JdbcDao jdbcDao;

	//-- User Manager --//
	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.get(id);
	}

	public void saveUser(User entity) {
		if(entity.getPassword().length()==0){
			User obj = userDao.get(entity.getId());
			obj.setLoginName(entity.getLoginName());
			obj.setSts(entity.getSts());
			obj.setEmployee(entity.getEmployee());
			obj.setRoleList(entity.getRoleList());
			userDao.save(obj);
//			entity.setPassword(userDao.get(entity.getId()).getPassword());
//			userDao.merge(entity);
		}
		else if (entity.getPassword().length()!=32){
			entity.setPassword(EncodeUtils.getMd5PasswordEncoder(entity.getPassword(),entity.getLoginName()));
			userDao.save(entity);
		}
		else{
			userDao.save(entity);
		}
		
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", SpringSecurityUtils.getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		
		userDao.delete(id);
	}

	public boolean batchDelUser(String[] ids){
		
		try {
			for(String id: ids){
				deleteUser(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 使用属性过滤条件查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<User> searchUser(final Page<User> page, final Map<String,Object> filters) {
		return userDao.queryUser(page, filters);
	}
	
	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		User user = userDao.findUniqueBy("loginName", loginName);
		if(user!=null && user.getEmployee() != null && !"0".equals(user.getEmployee().getSts())){
			user = null;
		}
		return user;
	}
	
	public void resetPass(String[] Ids) {
//		String hql = "from User where 1=1";
//		Object[] obj = null;
//		List<User> l =  userDao.find(hql, obj);
//		for(User u : l){
//			if(!u.getLoginName().equals("admin")){
//				u.setPassword(EncodeUtils.getMd5PasswordEncoder("abc123",u.getLoginName()));
//				//saveUser(u);
//				userDao.save(u);
//			}
//		}
		User u =  new User();
		for(String id: Ids){
			u  = getUser(Long.valueOf(id));
			u.setPassword(EncodeUtils.getMd5PasswordEncoder("abc123",u.getLoginName()));
			userDao.save(u);
		}
		//return userDao.findUniqueBy("loginName", loginName);
	}
	/**
	 * 检查用户名是否唯一.
	 *
	 * @return loginName在数据库中唯一或等于oldLoginName时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isLoginNameUnique(String newLoginName, String oldLoginName) {
		return userDao.isPropertyUnique("loginName", newLoginName, oldLoginName);
	}

	//-- Role Manager --//
	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		return roleDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Role> getAllRole() {
		return roleDao.getAll("id", true);
	}

	/**
	 * 返回所有的角色，已经与用户关联的角色需要做出标志
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Role> getMergedUserRoleAndAllRole(List<Role> userRoles) {
		List<Role> allRoles = roleDao.getRolesBySts();
		Long lastFlagUserRoleId = 0L;
		for(Role r1: allRoles){
			if(r1.getId() < lastFlagUserRoleId){continue;}
			for(Role r2: userRoles){
				if(r1.getId() == r2.getId()){
					r1.setFlag("Y");
					lastFlagUserRoleId = r2.getId();
					break;
				}
			}
		}
		return allRoles;
	}

	
	public void saveRole(Role entity) {
		roleDao.save(entity);
	}

	public void deleteRole(Long id) {
		roleDao.delete(id);
	}

	public List<Role> getRoleListByIds(	List<Long> ids) {		
		return roleDao.findByIds(ids);
	}

	public void searchRole(Page<Role> page, Map<String, Object> params) {
		roleDao.queryRole(page, params);		
	}

	public boolean batchDelRole(String[] ids) {
		try {
			for(String id: ids){
				deleteRole(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean isRoleNameUnique(String newValue, String oldValue) {
		return roleDao.isPropertyUnique("name", newValue, oldValue);
		
	}


	public List<Authority> getAuthorityListByIds(List<Long> ids) {
		return authorityDao.findByIds(ids);
	}
	
	public List<Menu> getMenuListByIds(List<Long> ids) {
		return menuDao.findByIds(ids);
	}

	public List<Authority> getMergedRoleAuthAndAllAuth(List<Authority> roleAuths) {
		List<Authority> allAuths = authorityDao.queryAuthorities();
		for(Authority a1: roleAuths){
			allAuths.remove(a1);
		}
		return allAuths;
	}
	
	public List<Menu> getMergedRoleMenu(List<Menu> roleMenu) {
		List<Menu> allMenu = menuDao.queryMenu();
		for(Menu menu : roleMenu){
			allMenu.remove(menu);
		}
		return allMenu;
	}
	
	public List<Map<String,Object>> getMergedRoleMenu(List<Menu> menu, List<Menu> roleMenu) {
		List<Map<String,Object>> tree = new LinkedList<Map<String, Object>>();
		for(Menu m : menu){
			boolean check = false;
			Map<String, Object> modelMap = new HashMap<String, Object>();
			for(Menu m1 : roleMenu){
				if(m.getId() == m1.getId()){
					check = true;
					break;
				}
			}
			modelMap.put("menu", m);
			modelMap.put("checked", check);
			tree.add(modelMap);
		}
		return tree;
	}
	
	public List<Menu> getMenusByLevels(Integer levelId) {// MDY
		return menuDao.getMenusByLevel(levelId);
	}
	
	public String[] buildMenuByTopId(List<Menu> menuRoleList){
		String[] res1 = new String[2];
		StringBuffer chenkedNode = new StringBuffer();
		List<Menu> menuList = getMenusByLevels(new Integer(1));
		StringBuffer tree = new StringBuffer();	
		//tree.append("<ul class=\"tree treeFolder\"><li><a href=\"#\">组织机构</a><ul>");
		for(Menu m : menuList){
			String[] res = buildMenuNode(m.getId(), menuRoleList);
			chenkedNode.append(res[0]);
			tree.append(res[1]);
		}
		//tree.append("</ul></li></ul>");
		res1[0] = chenkedNode.toString();
		res1[1] = tree.toString();
		return res1;
	}
	
	private String[] buildMenuNode(Long orgId, List<Menu> menuRoleList){
		String[] res = new String[2];
		StringBuffer chenkedNode = new StringBuffer();
		Menu menu = menuDao.get(orgId);
		String chenked = "";
		boolean isChenked = getCheckedMenu(menu, menuRoleList);
		if(isChenked){
			chenked = "checked=true";
			chenkedNode.append("{id:'"+menu.getId()+"', name:'"+menu.getMenuName()+"'}|");
		}
		StringBuffer node = new StringBuffer();
		node.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+chenked+">"+menu.getMenuName()+"</a>");
		List<Menu> menuList = getMenuByParent(orgId);
		if(menuList != null && menuList.size()>0 ){
			node.append("<ul>");
			for (Menu m : menuList) {
				String[] ss = buildMenuNode(m.getId(), menuRoleList);
				chenkedNode.append(ss[0]);
				node.append(ss[1]);
			}
			node.append("</ul>");
		}
		node.append("</li>");
		res[0] = chenkedNode.toString();
		res[1] = node.toString();
		return res;
	}
	
	private boolean getCheckedMenu(Menu menu, List<Menu> menuRoleList){
		boolean isChenked = false;
		for (Menu m : menuRoleList) {
			if(m.getId() == menu.getId()){
				isChenked = true;
				break;
			}
		}
		return isChenked;
	}
	
	public List<Menu> getMenuByParent(Long parentId) {
		return menuDao.getMenusByParent(parentId);
	}

	//-- Authority Manager --//
	@Transactional(readOnly = true)
	public List<Authority> getAllAuthority() {
		return authorityDao.getAll();
	}

	public void searchAuthority(Page<Authority> page, Map<String, Object> params) {
		authorityDao.queryAuth(page, params);	
		
	}
	
	public void saveAuthority(Authority auth) {
		authorityDao.save(auth);
	}
	
	public Authority getAuthority(Long id) {
		return authorityDao.get(id);
	}

	public void deleteAuthority(Long id) {
		authorityDao.delete(id);
		
	}

	public boolean batchDelAuth(String[] ids) {
		try {
			for(String id: ids){
				deleteAuthority(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isAuthNameUnique(String newValue, String oldValue) {
		return authorityDao.isPropertyUnique("name", newValue, oldValue);
	}



	public String getTypeByCode(String TYPE_CODE){
		StringBuffer sql = new StringBuffer("select xma.name,xma.value ");
		sql.append("    from xhcf_mate_data xma,xhcf_matedata_type xmatype");
		sql.append("    where xma.matedatatype_id = xmatype.id ");
		sql.append("          and  xma.state='0'");
		sql.append("          and xmatype.type_code='").append(TYPE_CODE).append("'");
		sql.append("          ");

    	List<Map<String, Object>> list = jdbcDao.searchByMergeSql(sql.toString());
    	String seq = "0";
    	if(null != list && list.size()>0){
    		Map<String,Object> map = list.get(0);
    		java.math.BigDecimal count = (java.math.BigDecimal)map.get("MONEY");
    		seq = count.toString();
    	}
		return seq+"";
	}
	
	public String getLoginCityData(Long id){
		String res = "";
		String sql = "select id from BASE_ZZJG t start with t.id = "+id+" connect by nocycle t.parent_id = prior id";
		List<Map<String, Object>> list = jdbcDao.searchByMergeSql(sql);
		if(list.size() > 0){
			res = ReflectionUtils.convertElementPropertyToString(list, "ID", ",");
		}
		return res;
	}
	
	public String getLoginTeamInData(String id){
		String res = "";
		String sql = "select e.id from base_employee e where e.organi_id in ("+id+") and e.position_id = 6";
		List<Map<String, Object>> list = jdbcDao.searchByMergeSql(sql);
		if(list.size() > 0){
			res = ReflectionUtils.convertElementPropertyToString(list, "ID", ",");
		}
		return res;
	}
	

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
    @Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

}
