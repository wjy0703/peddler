package cn.com.cucsi.app.service.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.Constants;
import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.AttrDao;
import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.dao.baseinfo.CustomerDao;
import cn.com.cucsi.app.dao.baseinfo.DeptDao;
import cn.com.cucsi.app.dao.baseinfo.EmployeeDao;
import cn.com.cucsi.app.dao.baseinfo.MateDataDao;
import cn.com.cucsi.app.dao.baseinfo.MenuDao;
import cn.com.cucsi.app.dao.baseinfo.MiddleManDao;
import cn.com.cucsi.app.dao.baseinfo.OrganiDao;
import cn.com.cucsi.app.dao.baseinfo.PositionDao;
import cn.com.cucsi.app.dao.baseinfo.TzcpDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.security.CldkzxDao;
import cn.com.cucsi.app.dao.security.FcdkzxDao;
import cn.com.cucsi.app.dao.security.RoleDao;
import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.dao.security.XydkzxDao;
import cn.com.cucsi.app.dao.xhcf.XhUploadFilesDao;
import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.BasePosition;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Customer;
import cn.com.cucsi.app.entity.baseinfo.Dept;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.MateDataType;
import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.PropertyFilter;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;

import com.et.mvc.JsonView;

/**
 * 基础信息相关实体的管理类, 包括员工,部门,系统字典类.
 * 
 * @author jiangxd
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class BaseInfoManager extends PublicService{

	private static Logger logger = LoggerFactory
			.getLogger(BaseInfoManager.class);

	private EmployeeDao employeeDao;
	private DeptDao deptDao;
	
	private UserDao userDao;
	private MenuDao menuDao;
	private CustomerDao customerDao;
	private TzcpDao tzcpDao;
	private OrganiDao organiDao;
	private MateDataDao mateDataDao;
	private RoleDao roleDao;
	private PositionDao positionDao;
	private XydkzxDao xydkzxDao;
	private FcdkzxDao fcdkzxDao;
	private CldkzxDao cldkzxDao;
	private JdbcDao jdbcDao;
	private CityDao cityDao;
	private MiddleManDao middleManDao;
	private XhUploadFilesDao xhUploadFilesDao;
	private XhJksqStateDao xhJksqStateDao;
	private AttrDao attrDao;

	@Autowired
	public void setXhUploadFilesDao(XhUploadFilesDao xhUploadFilesDao) {
		this.xhUploadFilesDao = xhUploadFilesDao;
	}

	public void saveXhUploadFiles(XhUploadFiles xhUploadFiles) {
		xhUploadFilesDao.save(xhUploadFiles);
	}

	public void deleteXhUploadFiles(Long id) {
		xhUploadFilesDao.delete(id);
	}

	public List<XhUploadFiles> findXhUploadFiles(Map<String, Object> filters) {
		return xhUploadFilesDao.findXhUploadFiles(filters);
	}

	@Transactional(readOnly = true)
	@Deprecated
	public List<City> getSuggestCity(String parentId) {
		return cityDao.getSuggestCity(parentId);
	}

	@Transactional(readOnly = true)
	public List<BasePosition> getSuggestPosition() {
		return positionDao.getAll();
	}

	// -- Employee Manager --//
	@Transactional(readOnly = true)
	public Employee getEmployee(Long id) {
		return employeeDao.get(id);
	}

	@Autowired
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public boolean saveEmployee(Employee entity) {
		boolean res = false;
		if(entity.getId() == null){
			List<Employee> list = employeeDao.findBy("name", entity.getName());
			if(list.size() == 0){
				res = true;
				employeeDao.save(entity);
			}
		}else{
			res = true;
			employeeDao.save(entity);
		}
		return res;
	}

	public void deleteEmployee(Long id) {
		if (userDao.isReferenceToEmployee(id)) {
			logger.warn("操作员{}尝试删除已经被登录账户关联的员工",
					SpringSecurityUtils.getCurrentUserName());
			throw new ServiceException("该员工已经被登录账户关联，不能删除！");
		}
		employeeDao.delete(id);
	}

	/**
	 * 使用属性过滤条件查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<Employee> searchEmployee(final Page<Employee> page,
			final Map<String, Object> filters) {
		return employeeDao.queryEmployee(page, filters);
	}

	public void getEmployeeOa(StringBuffer data, Long oId) {
		List<Organi> list = organiDao.getOrganiByParent(oId);
		if (list != null) {
			for (Organi o : list) {
				data.append(o.getId());
				data.append(",");
				getEmployeeOa(data, o.getId());
			}
		}
	}

	public List<Employee> getSuggestEmployee(String pinyin) {
		PropertyFilter pf = new PropertyFilter("LIKES_name_OR_spell", pinyin);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(pf);
		return employeeDao.find(filters);
	}
	
	/**
	 * 根据职务查找员工
	 * @param id
	 * @return
	 */
	public List<Employee> findEmpByPosId(Long id) {
		String hql = "from Employee e where e.position.id=" + id +" and e.sts=0";
		return employeeDao.find(hql);
	}

	// -- Dept Manager --//
	@Transactional(readOnly = true)
	public Dept getDept(Long id) {
		return deptDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Dept> getAllDept() {
		return deptDao.getAll("id", true);
	}

	public void saveDept(Dept entity) {
		deptDao.save(entity);
	}

	public void deleteDept(Long id) {
		deptDao.delete(id);
	}
	
	public void saveOrgani(Organi entity) {
		organiDao.save(entity);
	}

	public List<Organi> getSuggestDept(String pinyin) {
		PropertyFilter pf = new PropertyFilter("LIKES_rganiName_OR_rganiName",
				pinyin);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(pf);
		return organiDao.find(filters);
	}
	
	public String buildOrganiByTopId(){
		List<Organi> organiList = getOrganiByParent(new Long(0));
		StringBuffer tree = new StringBuffer();	
		//tree.append("<ul class=\"tree treeFolder\"><li><a href=\"#\">组织机构</a><ul>");
		for(Organi o : organiList){
			tree.append(buildOrgnizationNode(o.getId()));
		}
		//tree.append("</ul></li></ul>");
		return tree.toString();
	}
	
	
	
	private String buildOrgnizationNode(Long orgId){
		Organi organization = organiDao.get(orgId);
		StringBuffer node = new StringBuffer();
		node.append("<li><a href=\"javascript:\" onclick=\"$.bringBack({id:'"+organization.getId()+"', name:'"+organization.getRganiName()+"'})\">"+organization.getRganiName()+"</a>");
		List<Organi> organiList = getOrganiByParent(orgId);
		if(organiList != null && organiList.size()>0 ){
			node.append("<ul>");
			for (Organi o : organiList) {
				node.append(buildOrgnizationNode(o.getId()));
			}
			node.append("</ul>");
		}
		node.append("</li>");
		return node.toString();
	}
	
	/**
	 * 
	 * 生成树的同时设置节点的onclick为clickfunction，这种写法并不好，争取到写法是在前台形成js。
	 * 
	 * @param clickFunction
	 * @return
	 * @author xjs
	 * @date 2013-8-5 下午2:20:00
	 */
	public String buildOrganiByTopId(String clickFunction){
        List<Organi> organiList = getOrganiByParent(new Long(0));
        StringBuffer tree = new StringBuffer(); 
        //tree.append("<ul class=\"tree treeFolder\"><li><a href=\"#\">组织机构</a><ul>");
        for(Organi o : organiList){
            tree.append(buildOrgnizationNode(o.getId(),clickFunction));
        }
        //tree.append("</ul></li></ul>");
        return tree.toString();
    }
	
	private String buildOrgnizationNode(Long orgId,String clickFunction){
        Organi organization = organiDao.get(orgId);
        StringBuffer node = new StringBuffer();
        node.append("<li><a href=\"javascript:\" onclick=\""+ clickFunction +"({id:'"+organization.getId()+"', name:'"+organization.getRganiName()+"',organiFlag:'"+organization.getOrganiFlag()+"',levelMess:'"+organization.getLevelMess()+"',organiCode:'"+organization.getOrganiCode()+"'})\">"+organization.getRganiName()+"</a>");
        List<Organi> organiList = getOrganiByParent(orgId);
        if(organiList != null && organiList.size()>0 ){
            node.append("<ul>");
            for (Organi o : organiList) {
                node.append(buildOrgnizationNode(o.getId(),clickFunction));
            }
            node.append("</ul>");
        }
        node.append("</li>");
        return node.toString();
    }
	
	
	public List<Organi> getOrganiByParent(Long parentId) {
		return organiDao.getOrganiByParent(parentId);
	}

	// -- Menu Manager --//
	@Transactional(readOnly = true)
	public Menu getMenu(Long id) {
		return menuDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Menu> getAllMenu() {
		return menuDao.getAll();
	}

	public void saveMenu(Menu entity) {
		menuDao.save(entity);
	}

	public void deleteMenu(Long id) {
		menuDao.delete(id);
	}

	public List<Menu> getMenusByLevel(Integer levelId) {// MDY

		List<Menu> menus2 = new LinkedList<Menu>();

		List<Menu> menus = menuDao.getMenusByLevel(levelId);

		if (levelId == 1) {
			OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
			User u = userDao.get(operator.getUserId());
			List<Role> roleList = u.getRoleList();// operator.getRoleList();
			for (Role role : roleList) {
				List<Menu> mList = role.getMenuOne();
				for (Menu menu : menus) {
					for (Menu menu1 : mList) {
						if (menu.getId() == menu1.getId()) {
							menus2.add(menu);
							break;
						}
					}
				}
			}
		} else {
			menus2 = menus;
		}

		return menus2;
	}
	
	public List<Menu> getMenusByLevels(Integer levelId) {// MDY
		return menuDao.getMenusByLevel(levelId);
	}

	public List<Menu> getMenusByParent(Long parentId) {
		return menuDao.getMenusByParent(parentId);
	}

	public void buildMenuByTopId(List<Menu> menus, Long parentId) {
		List<Menu> menuList = getMenusByParent(parentId);
		if (menuList != null) {
			for (Menu m : menuList) {
				menus.add(m);
				buildMenuByTopId(menus, m.getId());
			}
		}
	}

	public List<Menu> buildMenuByTopId(List<Menu> menus) {
		List<Menu> menus2 = new LinkedList<Menu>();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		User u = userDao.get(operator.getUserId());
		List<Role> roleList = u.getRoleList();// operator.getRoleList();
		for (Role role : roleList) {
			List<Menu> mList = role.getMenuList();// r1.getMenuList();
			for (Menu menu : menus) {
				for (Menu menu1 : mList) {
					if (menu.getId() == menu1.getId()) {
						menus2.add(menu);
						break;
					}
				}
			}
		}
		return menus2;
	}

	public List<Menu> isMenu(Long id) {
		return menuDao.getMenusByParent(id);
	}

	

	// -- Customer Manager --//
	@Transactional(readOnly = true)
	public Customer getCustomer(Long id) {
		return customerDao.get(id);
	}

	@Transactional(readOnly = true)
	public Page<Customer> searchCustomer(final Page<Customer> page,
			final Map<String, Object> filters) {
		return customerDao.queryCustomer(page, filters);
	}

	public Page<Tzcp> searchCp(final Page<Tzcp> page,
			final Map<String, Object> filters) {
		return tzcpDao.queryCp(page, filters);
	}

	public Page<Xydkzx> searchXydkzx(final Page<Xydkzx> page,
			final Map<String, Object> filters) {
		return xydkzxDao.queryXydkzx(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<Xydkzx> searchFcdkzx(final Page<Xydkzx> page,
			final Map<String, Object> filters) {
		return fcdkzxDao.queryXydkzx(page, filters);
	}

	public Page<Xydkzx> searchCldkzx(final Page<Xydkzx> page,
			final Map<String, Object> filters) {
		return cldkzxDao.queryXydkzx(page, filters);
	}

	public Page<Menu> searchMenu(final Page<Menu> page,
			final Map<String, Object> filters) {
		return menuDao.queryMenu(page, filters);
	}

	public Page<MiddleMan> searchMiddleMan(final Page<MiddleMan> page,
			final Map<String, Object> filters) {
		return middleManDao.queryMiddleMan(page, filters);
	}

	public Page<MateData> searchZd(final Page<MateData> page,
			final Map<String, Object> filters) {
		return mateDataDao.queryMateData(page, filters);
	}

	public List<MateDataType> getAllType() {
		return mateDataDao.findAllType();
	}

	@Transactional(readOnly = true)
	@Deprecated
	public List<MateData> getTypeByCode(String code) {
		return mateDataDao.findTypeByCode(code);
	}

	public void saveCustomer(Customer entity) {
		customerDao.save(entity);
	}

	public List<Tzcp> findTzcp(Map<String, Object> filters) {
		String hql = "from Tzcp tzcp where 1=1 ";
		if (filters.containsKey("tzcpMc")) {
			hql = hql + " and tzcp.tzcpMc like '%'||:tzcpMc||'%'";
		}
		if (filters.containsKey("tzcpFl")) {
			hql = hql + " and tzcp.tzcpFl = :tzcpFl";
		}
		return tzcpDao.find(hql, filters);
	}

	public void saveCp(Tzcp tzcp) {
		tzcpDao.save(tzcp);
	}

	public void saveXydkzx(Xydkzx xydkzx) {
		xydkzxDao.save(xydkzx);
	}

	public void saveZd(MateData mateData) {
		mateDataDao.save(mateData);
	}

	public void saveMiddleMan(MiddleMan middleMan) {
		middleManDao.save(middleMan);
	}

	public void saveMenuNew(Menu menu) {
		Menu newMenu = menuDao.get(menu.getId());
		newMenu.setMenuName(menu.getMenuName());
		newMenu.setTitle(menu.getTitle());
		newMenu.setSts(menu.getSts());
		menuDao.save(newMenu);
	}

	/**
	 * 获取当前登录用户绑定的员工对象
	 * 
	 * @return
	 */
	public Employee getUserEmployee() {
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("取得用户 " + loginName +" 对应的用户");
		Employee employee = userDao.findUniqueBy("loginName", loginName)
				.getEmployee();
		return employee;
	}
	
	/**
	 * 获取当前登录用户是否接收消息
	 * 
	 * @return
	 */
	public boolean getUserIsMess() {
		boolean isMess = false;
		String loginName = SpringSecurityUtils.getCurrentUserName();
		User user = userDao.findUniqueBy("loginName", loginName);
		List<Role> roleList = user.getRoleList();
		for(Role r : roleList){
			String auth = r.getAuthIds();
			String[] auths = auth.split(",");
			for(int i = 0 ; i < auths.length ; i++){
				if(!auths[i].equals("")){
					if(new Long(auths[i]) == 0){
						isMess = true;
						break;
					}
				}
			}
			if(isMess){
				break;
			}
		}
		return isMess;
	}

	/**
	 * 获取指定用户名绑定的员工对象
	 * 
	 * @param username
	 * @return
	 */
	public Employee getUserEmployee(String username) {
		// String loginName = SpringSecurityUtils.getCurrentUserName();

		Employee employee = userDao.findUniqueBy("loginName", username)
				.getEmployee();
		return employee;
	}
	
	public User getUserByEmployee(long employeeId) {
		User user = userDao.findUniqueBy("employee.id", employeeId);
		return user;
	}

	/**
	 * 获取序列值,返回总长4位，前补0，
	 */
	public String getSequenceFro(String sequ) {
		// 拼接编号头
		String result = "";
		// 查询该序列Sequence
		String sql = "select " + sequ + ".NEXTVAL from dual";
		int seq = jdbcDao.getSequenceFro(sql);
		// 补位
		for (int i = 0; i < 4 - (seq + "").length(); i++) {
			result = result + "0";
		}
		// 拼接Sequence
		result = result + seq;
		return result;
	}

	/**
	 * 根据地市ID获取区号
	 */
	@Transactional(readOnly = true)
	public String getAreaCode(Long id) {
		String sql = "select z.CNAME from base_city z where 1=1 ";
		sql = sql + " and z.ID = " + id;

		List<Map<String, Object>> areaList = jdbcDao.searchByMergeSql(sql);
		String result = "";
		if (areaList.size() == 1) {
			result = areaList.get(0).get("CNAME") + "";
		}
		return result;
	}
	
	/**
	 * 根据地市ID获取区号
	 */
	@Transactional(readOnly = true)
	public String getCityName(Long id) {
		String sql = "select z.NAME from base_city z where 1=1 ";
		sql = sql + " and z.ID = " + id;

		List<Map<String, Object>> areaList = jdbcDao.searchByMergeSql(sql);
		String result = "";
		if (areaList.size() == 1) {
			result = areaList.get(0).get("NAME") + "";
		}
		return result;
	}

	/**
	 * 获取序号
	 * 
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            字段名
	 * @param bh
	 *            查询编号（地区+营业部）
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getXh(String tableName, String columnName, String bh) {
		String sql = "select max(to_number(substr(" + columnName + ",length("
				+ columnName + ")-2,length(" + columnName + ")))) as xh from "
				+ tableName + " where substr(" + columnName + ",3,"
				+ bh.length() + ") = '" + bh + "'";
		List<Map<String, Object>> areaList = jdbcDao.searchByMergeSql(sql);
		String result = "";
		int xh = 0;
		if (null != areaList && areaList.size() == 1) {
			if (areaList.get(0).get("XH") != null
					&& !areaList.get(0).get("XH").toString().equals("")) {
				xh = Integer.parseInt(areaList.get(0).get("XH")+"");
			}
		}
		xh ++;
		result = xh + "";
		int len = 3 - result.length();
		for (int i = 0; i < len; i++) {
			result = "0" + result;
		}
		return result;
	}
	
	/**
	 * 获取出借编号后四位
	 * 
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            字段名
	 * @param bh
	 *            查询编号（地区+营业部）
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getCjXh(String bh,String cjrxx_id) {
		String sql = "select substr(t.tzsqbh,9,length(t.tzsqbh)-12) as XH from xh_tzsq t where t.tzsqbh is not null and t.cjrxx_id= " + cjrxx_id;
		List<Map<String, Object>> areaList = jdbcDao.searchByMergeSql(sql);
		System.out.println("areaList==size=" + areaList.size());
		String result = "";
		if(null != areaList && areaList.size()>0 && null != areaList.get(0).get("XH") && !"".equals(areaList.get(0).get("XH").toString()) && !"null".equals(areaList.get(0).get("XH").toString())){
			result = areaList.get(0).get("XH")+"";
			System.out.println("areaList==result=" + result);
		}else{
			sql = "select max(to_number(substr(t.tzsqbh,9,instr(t.tzsqbh,'-')-9))) as XH from xh_tzsq t where substr(t.tzsqbh,3,6) = '"+bh+"'";
			areaList = jdbcDao.searchByMergeSql(sql);
			System.out.println("areaList2==size=" + areaList.size());
			int xh = 0;
			if (null != areaList && areaList.size() == 1) {
				if (null != areaList.get(0).get("XH")
						&& !"".equals(areaList.get(0).get("XH"))) {
					xh = Integer.parseInt(areaList.get(0).get("XH")+"");
				}
			}
			xh ++;
			System.out.println("areaList==xh=" + xh);
			result = xh + "";
			int len = 4 - result.length();
			for (int i = 0; i < len; i++) {
				result = "0" + result;
			}
		}
		return result;
	}
	
	/**
	 * 获取客户序号
	 * 
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            字段名
	 * @param bh
	 *            查询编号（地区+营业部）
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getKhXh(String tableName, String columnName, String bh) {
		String sql = "select max(to_number(substr(" + columnName + ","
				+ (bh.length()+1) + ",length(" + columnName + ")))) as xh from "
				+ tableName + " where substr(" + columnName + ",0,"
				+ bh.length() + ") = '" + bh + "'";
		List<Map<String, Object>> areaList = jdbcDao.searchByMergeSql(sql);
		String result = "";
		int xh = 0;
		if (null != areaList && areaList.size() == 1) {
			if (areaList.get(0).get("XH") != null
					&& !areaList.get(0).get("XH").toString().equals("")) {
				xh = Integer.parseInt(areaList.get(0).get("XH")+"");
			}
		}
		xh ++;
		result = xh + "";
		int len = 4 - result.length();
		for (int i = 0; i < len; i++) {
			result = "0" + result;
		}
		return result;
	}
	
	/**
	 * 生成车借编号
	 * bh==城市编码+部门编码
	 * isExtension==是否展期 0否1是
	 * partendId==上级申请ID
	 */
	@Transactional(readOnly = true)
	public String getCarXh(String bh,String isExtension,Long partendId) {
		String result = "";
		String sql = "";
		List<Map<String, Object>> areaList = new ArrayList<Map<String, Object>>();
		//如果是非展期
		if("0".equals(isExtension)){
			sql = "select max(to_number(substr(t.loan_code,length(t.loan_code)-7,4))) as XH from xh_car_loan_apply t where substr(t.loan_code,0,length(t.loan_code)-8)='" + bh +"'";
			areaList = jdbcDao.searchByMergeSql(sql);
			System.out.println("areaList==size=" + areaList.size());
			int xh = 0;
			if (null != areaList && areaList.size() == 1) {
				if (areaList.get(0).get("XH") != null
						&& !areaList.get(0).get("XH").toString().equals("")) {
					xh = Integer.parseInt(areaList.get(0).get("XH")+"");
				}
			}
			xh ++;
			result = xh + "";
			int len = 4 - result.length();
			for (int i = 0; i < len; i++) {
				result = "0" + result;
			}
			result = bh+result+"-001";
		}else{
			//展期数据，查询上一级车借申请编号  0451010001-001  拆分为 0451010001  1
			sql = "select substr(t.loan_code,0,length(t.loan_code)-4) as beforXh,to_number(substr(t.loan_code,length(t.loan_code)-2,3)) as afterXh  from xh_car_loan_apply t where t.id=" + partendId ;
			areaList = jdbcDao.searchByMergeSql(sql);
			int xh = Integer.parseInt(areaList.get(0).get("AFTERXH")+"");
			xh ++;
			result = xh + "";
			int len = 3 - result.length();
			for (int i = 0; i < len; i++) {
				result = "0" + result;
			}
			result = areaList.get(0).get("BEFORXH")+"-"+result;
		}
		return result;
	}
	public void deleteCustomer(Long id) {
		customerDao.delete(id);
	}

	public void deleteCp(Long id) {
		tzcpDao.delete(id);
	}

	public void deleteXydkzx(Long id) {
		xydkzxDao.delete(id);
	}

	public void deleteZd(Long id) {
		mateDataDao.delete(id);
	}

	public void deleteMiddleMan(Long id) {
		middleManDao.delete(id);
	}

	public Tzcp getCp(Long id) {
		return tzcpDao.get(id);
	}

	public Xydkzx getXydkzx(Long id) {
		return xydkzxDao.get(id);
	}

	public MateData getZd(Long id) {
		return mateDataDao.get(id);
	}

	public MiddleMan getMiddleMan(Long id) {
		return middleManDao.get(id);
	}
	
	/**
	 * 查找所有中间人信息
	 * @return
	 */
	public List<MiddleMan> getAllMiddleMan(){
		return middleManDao.find();
	}

	public Organi addDep(String parentId, String rganiName, String organiCode,
			String organiFlag, String organiDes,String levelMess) {
		Organi organi = new Organi();
		organi.setParentId(Long.valueOf(parentId));
		organi.setRganiName(rganiName);
		organi.setOrganiCode(organiCode);
		organi.setOrganiFlag(organiFlag);
		organi.setOrganiDes(organiDes);
		organi.setLevelMess(levelMess);
		organiDao.save(organi);
		return organi;
	}

	public String getDep(Long id) {
		Map<String, Object> tree = new HashMap<String, Object>();
		if (id != 0) {
			Organi organi = organiDao.get(id);
			// List<Map> trees = new ArrayList<Map>();
			tree.put("success", true);
			tree.put("id", organi.getId());
			tree.put("text", organi.getRganiName());
			tree.put("code", organi.getOrganiCode());
			tree.put("des", organi.getOrganiDes());
			tree.put("levelMess", organi.getLevelMess());
			String flag = organi.getOrganiFlag();
			String flaStr = "否";
			if (flag != null && !flag.equals("")) {
				switch (Integer.valueOf(flag)) {
				case 0:
					flaStr = "是";
					break;
				case 1:
					flaStr = "否";
					break;
				}
			}
			tree.put("flag", flaStr);
		} else {
			tree.put("success", true);
			tree.put("text", "组织机构");
			tree.put("code", "");
			tree.put("flag", "");
			tree.put("des", "");
			tree.put("levelMess", "");
		}
		return new JsonView(tree).toString();
	}

	public String getCityList(Long parentId) {
		List<Organi> list = organiDao.findBy("parentId", parentId);
		List<Map> trees = new ArrayList<Map>();
		for (Organi organi : list) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", organi.getId());
			tree.put("text", organi.getRganiName());
			tree.put("leaf", getChildrenCount(organi.getId()) == 0);
			trees.add(tree);
		}
		return new JsonView(trees).toString();
	}

	public long getChildrenCount(Long id) {
		// 是否有子节点
		long res = 0;
		List<Organi> DepList = organiDao.findBy("parentId", id);
		if (DepList.size() > 0) {
			res = 1;
		}
		return res;
	}

	public boolean delDep(String id) {
		boolean flag = true;
		List<Organi> list = organiDao.findBy("parentId", Long.valueOf(id));
		if (list != null && list.size() > 0) {
			flag = false;
		} else {
			organiDao.delete(Long.valueOf(id));
		}
		return flag;
	}

	public Organi updateDep(String parentId, String name, String organiCode,
			String organiFlag, String organiDes,String levelMess) {
		Organi organi = organiDao.get(Long.valueOf(parentId));
		organi.setRganiName(name);
		organi.setOrganiCode(organiCode);
		organi.setOrganiFlag(organiFlag);
		organi.setOrganiDes(organiDes);
		organi.setLevelMess(levelMess);
		organiDao.save(organi);
		return organi;
	}
	
	@Transactional(readOnly = true)
	public List<Customer> getCustomerByCall(String caller) {
		return customerDao.getCustomerByCall(caller);
	}
	@Transactional(readOnly = true)
	public Organi gerOrgani(Long parentId){
		return organiDao.get(parentId);
	}
	@Transactional(readOnly = true)
	public Organi getOrgani(Long parentId){
		Organi upOrgani = new Organi();
		if(parentId == 0){
			upOrgani.setId(new Long(0));
			upOrgani.setRganiName("组织机构");
		}else{
			upOrgani = organiDao.get(parentId);
		}
		return upOrgani;
	}

	/**
	 * 保存借款申请历史信息
	 * 
	 * @param xhJksq
	 *            借款申请信息
	 * @param describe
	 *            历史描述
	 * @param remarks
	 *            历史备注
	 * @return
	 */
	public boolean saveXhJksqState(XhJksq xhJksq, String describe,
			String remarks) {
		boolean flag = false;
		XhJksqState xhJksqState = new XhJksqState();
		xhJksqState.setXhjksq(xhJksq);
		xhJksqState.setDescribe(describe);
		xhJksqState.setRemarks(remarks);
		xhJksqState.setState(xhJksq.getState());
		xhJksqStateDao.save(xhJksqState);
		if (null != xhJksqState.getId() && 0 != xhJksqState.getId()) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 保存借款申请历史信息 MDY 2013-7-26
	 * 
	 * @param xhJksq
	 *            借款申请信息
	 * @param describe
	 *            历史描述
	 * @param remarks
	 *            历史备注
	 * @return
	 */
	public boolean saveXhJksqState(XhJksq xhJksq,  String code) {
		boolean flag = false;
		XhJksqState xhJksqState = new XhJksqState();
		String[] log = Constants.getAttrDesByValue("JKSQ_STATE_LOG", code);
		xhJksqState.setXhjksq(xhJksq);
		xhJksqState.setDescribe(log[1]);
		xhJksqState.setRemarks(log[0]);
		xhJksqStateDao.save(xhJksqState);
		if (null != xhJksqState.getId() && 0 != xhJksqState.getId()) {
			flag = true;
		}
		return flag;
	}
	
	public boolean isLoginPass(){
		boolean isRes = false;
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		User u = userDao.get(operator.getUserId());
		if(u != null){
			String md5PassWord = EncodeUtils.getMd5PasswordEncoder("abc123",u.getLoginName());
			if(u.getPassword() != null && u.getPassword().equals(md5PassWord)){
				isRes = true;
			}
		}
		return isRes;
	}

	/**
	 * 按类型(签约、授信、信审初审、复审、终审)查询上传文件类别
	 * @param request
	 * @param Id
	 * @param upflag
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<XhUploadFiles> downLoadFile(HttpServletRequest request,
			Long Id, String upflag) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("mainId", Id);
		filters.put("flag", upflag);
		return xhUploadFilesDao.findXhUploadFiles(filters);
	}
	
	/**
	 * 查询已上传文件
	 * @param Id
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<XhUploadFiles> seeUpLoadFile(Long Id, String upflag) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("mainId", Id);
		filters.put("flag", upflag);
		List<XhUploadFiles> list = xhUploadFilesDao.findXhUploadFiles(filters);
		XhUploadFiles xhuploadFiles = null;
		if(null != list && list.size()>0){
			for(int i=0;i<list.size();i++){
				xhuploadFiles = list.get(i);
				if("0".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("签约文件");
				}else if("1".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("信审初审文件");
				}else if("2".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("信审复审文件");
				}else if("3".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("信审终审文件");
				}else if("授信".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("授信文件");
				}else if("外访".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("外访文件");
				}else if("P2P".equals(xhuploadFiles.getFlag())){
					xhuploadFiles.setFlag("P2P声明书");
				}
				
				else{
					if("XH_JKHT".equals(xhuploadFiles.getFileOwner())){
						xhuploadFiles.setFlag("合同类文件");
					}else if("XH_CJRXX".equals(xhuploadFiles.getFileOwner())){
						xhuploadFiles.setFlag("出借类文件");
					}else{
						xhuploadFiles.setFlag("未知类型");
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 查询基础属性
	 * 
	 * @param filter
	 * @return
	 */
	public List<Attr> getAttrList(Map<String, Object> filter) {
		return attrDao.getAttrList(filter);
	}

	/**
	 * 核实循环贷证件信息
	 * @param request
	 * @return
	 */
	public Map<String, Object> isIdInfo(HttpServletRequest request) {
		StringBuffer sql = new StringBuffer();
		String khmc = request.getParameter("khmc").toString();
		String zjhm = request.getParameter("zjhm").toString();
		sql.append("select *");
		sql.append(" from (select nvl(max(t.id), 0) as id");
		sql.append(" from XH_JKSQ t ");
		sql.append(" where 1 = 1");
		sql.append(" and t.zjhm = '"+zjhm+"'");
		sql.append(" and t.jkrxm = '"+khmc+"'");
		sql.append(" and t.state in ('101', '102')");
		sql.append(" order by t.create_time desc)");
		sql.append(" where rownum = 1");
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql.toString());
		Map map = (Map)listMap.get(0);
		String id = map.get("ID").toString();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", id);
		modelMap.put("success", "true");
		return modelMap;
	}
	
	/**
	 * 核实循环贷证件信息
	 * @param request
	 * @return
	 */
	public Map<String, Object> isIdInfoByZjhm(HttpServletRequest request) {
		StringBuffer sql = new StringBuffer();
		String zjhm = request.getParameter("zjhm").toString();
		String myownId =  request.getParameter("myownId");
		sql.append("select count(t.id) as count from XH_JKSQ t where 1 = 1 and t.zjhm = '"+zjhm.trim()+"'");
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql.toString());
		Map map = (Map)listMap.get(0);
		String count = map.get("COUNT").toString();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", count);
		modelMap.put("success", "true");
		return modelMap;
	}
	//判断制作时间
	@Transactional(readOnly = true)
	public String waiteTime(String tableName){
		String sql = "";
		if("xh_zqtj".equals(tableName)){
			sql = "select trunc(count(*)/6)+1 as MM from XH_MADEWORD a where a.state='0' and a.table_name='xh_zqtj'";
		}else if("xh_jkht".equals(tableName)){
			sql = "select trunc(count(*)/10)+1 as MM from XH_MADEWORD a where a.state='0' and a.table_name='xh_jkht'";
		}else if("xh_zzcapital_loan_report".equals(tableName)){
			sql = "select trunc(count(*)/10)+1 as MM from XH_MADEWORD a where a.state='0' and a.table_name='xh_zzcapital_loan_report'";
		}
		//sql = " select count(*)*5 as con from XH_MADEWORD a where a.state='0' and a.table_name='xh_capital_loan_report' ";
		List<Map<String, Object>> list = jdbcDao.searchByMergeSql(sql);
		return list.get(0).get("MM")+"";
	}
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Autowired
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}


	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Autowired
	public void setTzcpDao(TzcpDao tzcpDao) {
		this.tzcpDao = tzcpDao;
	}

	@Autowired
	public void setXydkzxDao(XydkzxDao xydkzxDao) {
		this.xydkzxDao = xydkzxDao;
	}

	@Autowired
	public void setFcdkzxDao(FcdkzxDao fcdkzxDao) {
		this.fcdkzxDao = fcdkzxDao;
	}

	@Autowired
	public void setCldkzxDao(CldkzxDao cldkzxDao) {
		this.cldkzxDao = cldkzxDao;
	}

	@Autowired
	public void setOrganiDao(OrganiDao organiDao) {
		this.organiDao = organiDao;
	}

	@Autowired
	public void setMateDataDao(MateDataDao mateDataDao) {
		this.mateDataDao = mateDataDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Autowired
	public void setMiddleManDao(MiddleManDao middleManDao) {
		this.middleManDao = middleManDao;
	}

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	public void setAttrDao(AttrDao attrDao) {
		this.attrDao = attrDao;
	}

}
