package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.EmployeeDao;
import cn.com.cucsi.app.dao.xhcf.CjrxxDao;
import cn.com.cucsi.app.dao.xhcf.UpdateCjrxxHistoryDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.UpdateCjrxxHistory;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.Java2Excel;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class CjrxxManager {

	@Autowired
	private EmployeeDao employeeDao;
	
	private CjrxxDao cjrxxDao;
	@Autowired
	public void setCjrxxDao(CjrxxDao cjrxxDao) {
		this.cjrxxDao = cjrxxDao;
	}
	
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	private UpdateCjrxxHistoryDao updateCjrxxHistoryDao;
	
	@Autowired
	public void setUpdateCjrxxHistoryDao(UpdateCjrxxHistoryDao updateCjrxxHistoryDao) {
		this.updateCjrxxHistoryDao = updateCjrxxHistoryDao;
	}
	
	public void saveUpdateCjrxx(UpdateCjrxxHistory entity) {
		updateCjrxxHistoryDao.save(entity);
	}
	@Transactional(readOnly = true)
	public UpdateCjrxxHistory getUpdateCjrxx(Long id) {
		return updateCjrxxHistoryDao.get(id);
	}
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchCjrxx(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		if(filter.containsKey("cjrxm")){
			value = String.valueOf(filter.get("cjrxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.cjrxm like '%" +  value + "%'";
			}
		}
		if(filter.containsKey("khbm")){
			value = String.valueOf(filter.get("khbm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.khbm like '%" +  value + "%'";
			}
		}
		if(filter.containsKey("ztFlag")){
			value = String.valueOf(filter.get("ztFlag"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.zt_Flag = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("cjrState")){
			value = String.valueOf(filter.get("cjrState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.cjr_State = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("upstate")){
			value = String.valueOf(filter.get("upstate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.upstate = '" +  value + "'";
			}
		}
		
		
		if(filter.containsKey("crmprovince")){
			value = String.valueOf(filter.get("crmprovince"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.crmprovince = '" +  value + "'";
			}
		}
		if(filter.containsKey("crmcity")){
			value = String.valueOf(filter.get("crmcity"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.crmcity = '" +  value + "'";
			}
		}
		if(filter.containsKey("province")){
			value = String.valueOf(filter.get("province"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.province = '" +  value + "'";
			}
		}
		if(filter.containsKey("city")){
			value = String.valueOf(filter.get("city"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.city = '" +  value + "'";
			}
		}
		if(filter.containsKey("tzrq")){
			value = String.valueOf(filter.get("tzrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.tzrq = '" +  value + "'";
			}
		}
		if(filter.containsKey("kurq")){
			value = String.valueOf(filter.get("kurq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.kurq = '" +  value + "'";
			}
		}
		if(filter.containsKey("khly")){
			value = String.valueOf(filter.get("khly"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.khly = '" +  value + "'";
			}
		}
		
		//级联查询sql
		sql = sql + PropertiesUtils.getLendSql();
		
		//if (page.getOrderBy()!=null){
		//	sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		//}
		sql = sql + " order by a.create_time desc";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		String sql2="";
		
		if(filter.containsKey("yyb")){
			value = String.valueOf(filter.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" +  value + "'";
			}
		}
		if(filter.containsKey("crmName")){
			value = String.valueOf(filter.get("crmName"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and EMPLOYEE_CRM_name like '%" +  value + "%'";
			}
		}
		System.out.println("sql2=======>" + sql2);
		conditions.put("sql2", sql2);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchYyb(String queryName,Map<String,Object> filter)
	{
		String sql="";
		String value="";
		if(filter.containsKey("cjrState")){
			value = String.valueOf(filter.get("cjrState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.cjr_State = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("upstate")){
			value = String.valueOf(filter.get("upstate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.upstate = '" +  value + "'";
			}
		}
		//级联查询sql
		sql = sql + PropertiesUtils.getLendSql();
		
		System.out.println("searchYyt sql=======>" + sql);
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}
	
	@Transactional(readOnly = true)
	public Page<XhcfCjrxx> searchCjrxx(final Page<XhcfCjrxx> page, final Map<String,Object> filters) {
		return cjrxxDao.queryCjrxx(page, filters);
	}
	@Transactional(readOnly = true)
	public XhcfCjrxx getCjrxx(Long id) {
		return cjrxxDao.get(id);
	}

	public void saveCjrxx(XhcfCjrxx entity) {
		cjrxxDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteCjrxx(Long id) {
		cjrxxDao.delete(id);
	}
	
	public boolean batchDelcjrxx(String[] ids){
		
		try {
			for(String id: ids){
				deleteCjrxx(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 检查是否唯一.
	 *propertyName 所要验证的字段名
	 * @return newValue在数据库中唯一或等于oldValue时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isCheckUnique(String propertyName,String newValue, String oldValue) {
		return cjrxxDao.isPropertyUnique(propertyName, newValue, oldValue);
	}
	
	/**
	 * 离职管理，修改理财经理，团队经理等
	 */
	@Transactional
	public void changeCjrxxLeaders(String name,String oldCrmName,String crmName,String ccaName){
		List<XhcfCjrxx> infos = cjrxxDao.queryCjrxxByNameAndLeader(name, oldCrmName);
		String notExist = "客户 :" + name + " 经理:" + oldCrmName + "不存在";
		String notUnique = "客户 :" + name + " 经理:" + oldCrmName + "不唯一";
		if(infos == null)
			throw new RuntimeException(notExist );
        if(infos.size() == 0)		   
        	throw new RuntimeException(notExist );
        if(infos.size()  > 1)
        	throw new RuntimeException(notUnique);
        XhcfCjrxx cjrxx = infos.get(0);
        cjrxx.setCreateBy(crmName);

        Employee employeeCrm =  getUniqueEmployee(crmName);
        Employee employeeCca =  getUniqueEmployee(ccaName);     
        
        cjrxx.setOrgani(employeeCrm.getOrgani());        
        cjrxx.setEmployeeCrm(employeeCrm);
        cjrxx.setEmployeeCca(employeeCca);
        cjrxxDao.save(cjrxx);
        String sql = "update xh_cjrxx set create_by = '" + crmName+ "' where id = " + cjrxx.getId().toString();
        jdbcDao.updateBySql(sql);
	}
    /**
     * 查看是否存在，或唯一，否则抛出异常
     * @param crms
     */
	private Employee getUniqueEmployee(String name) {
		String notExist = "人员 :" + name + "不存在";
		String notUnique = "人员 :" + name + "不唯一";
		List<Employee> employees = employeeDao.queryByName(name);
		if(employees == null)
        	throw new RuntimeException(notExist );
		if(employees.size() == 0)		   
        	throw new RuntimeException(notExist );
		if(employees.size() > 1 )		   
        	throw new RuntimeException(notUnique );
		return employees.get(0);
			
	}
	
	
	/**
	 * 进件登记信息
	 */
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchJjdj(Map<String,Object> filter,JdbcPage page)
	{
		List<Map<String,Object>> list = jdbcDao.searchPagesByMergeSqlTemplate("queryJksqJinJianList", condition(filter), page);
		for(int i=0 ; i < list.size() ; i ++){
			String id = list.get(i).get("ID")+"";
			String UPLOADTIME = getBacktime("backtime",id,"上传授信资料成功","").get("V");
			list.get(i).put("UPLOADTIME", UPLOADTIME);
			list.get(i).put("ALLTIMES", getBacktime("alltimes","",res(list.get(i).get("MAKELOANDATE")+""),UPLOADTIME).get("V"));
			
			list.get(i).put("WAIFANGTIME", getBacktime("backtime",id,"","31.A").get("V"));
			list.get(i).put("UPLOADWAIFANGTIME", getBacktime("backtime",id,"上传外访资料成功","").get("V"));
			list.get(i).put("ISBACKREGISTER", getBacktime("isBackRegister",id,"信用初审退回!","").get("V"));
			list.get(i).put("RESETREGISTERTIME", getBacktime("resetRegisterTime",id,"信用初审退回!","").get("V"));
			list.get(i).put("REFUSECAUSE", getBacktime("refuseCause",id,"","").get("V"));
			list.get(i).put("OVERCREDITTIME", getBacktime("overcredittime",id,"","").get("V"));
			list.get(i).put("MAKEJKHTTIME", getBacktime("backtime",id,"","51").get("V"));
			
			list.get(i).put("GIVEUPCAUSE", getBacktime("giveupcause",id,"","81").get("V"));
		}
		
		return list;
	}
	
	private Map<String,String> getBacktime(String type,String id,String des,String state){
		String sql = "select JKSQ_REGISTER_UTIL." ;
		if("backtime".equals(type)){
			sql = sql + "backtime("+
					id +
					",'" +
					des +
					"','" +
					state+
					"')";
		}else if ("isBackRegister".equals(type)){
			sql = sql + "isBackRegister("+
					id +
					",'" +
					des +
					"')";
		}else if ("resetRegisterTime".equals(type)){
			sql = sql + "resetRegisterTime("+
					id +
					",'" +
					des +
					"')";
		}else if ("refuseCause".equals(type)){
			sql = sql + "refuseCause("+
					id +
					")";
		}else if ("overcredittime".equals(type)){
			sql = sql + "overcredittime("+
					id +
					")";
		}else if ("giveupcause".equals(type)){
			sql = sql + "giveupcause("+
					id +
					",'" +
					state +
					"')";
		}else if ("alltimes".equals(type)){
			sql = sql + "alltimes('"+
					des +
					"','" +
					state +
					"')";
		}
		sql = sql + " as v from dual";
		List<Map<String,Object>> list = jdbcDao.searchByMergeSql(sql);
		Map<String,String> result = new HashMap<String,String>();
		result.put("V", "");
		if(null != list && list.size() > 0){
			result.put("V", res(list.get(0).get("V")+""));
		}
		return result;
	}
	
	
	public Map<String, Object> condition(Map<String,Object> filter){
		
		String sql="";
		String sql1="";
		String value = "";
		if(filter.containsKey("khxm")){
			value = String.valueOf(filter.get("khxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.jkrxm like '%" +  value + "%'";
			}
		}
		
		if(filter.containsKey("fstartdate")){
			value = String.valueOf(filter.get("fstartdate"));
			if(StringUtils.isNotEmpty(value)) {
				sql1 = sql1 + " and MAKE_LOAN_DATE >= to_date('"+value+"','yyyy-MM-dd')";
			}
		}
		if(filter.containsKey("foverdate")){
			value = String.valueOf(filter.get("foverdate"));
			if(StringUtils.isNotEmpty(value)) {
				sql1 = sql1 + " and MAKE_LOAN_DATE  <=to_date('"+value+"','yyyy-MM-dd')";
			}
		}
		if(filter.containsKey("jstartdate")){
			value = String.valueOf(filter.get("jstartdate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_LOAN_DATE >= to_date('"+value+"','yyyy-MM-dd')";
			}
		}
		if(filter.containsKey("joverdate")){
			value = String.valueOf(filter.get("joverdate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_LOAN_DATE <= to_date('"+value+"','yyyy-MM-dd')";
			}
		}
		if(filter.containsKey("jkType")){
			value = String.valueOf(filter.get("jkType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '"+value+"'";
			}
		}
		//级联查询sql
		sql = sql + PropertiesUtils.getLendSql();
		if(filter.containsKey("yyb")){
			value = String.valueOf(filter.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				sql1 = sql1 + " and xx.yybid =" +  value + "";
			}
		}
		Map<String, Object> conditions = new HashMap<String,Object>();
	    conditions.put("sql", sql);
	    conditions.put("sql1", sql1);
//		conditions.put("sql2", sql2);
		return conditions;
	}


	public void exportProref(String path,Map<String, Object> map,String orgName){
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l ;
		String[] a;
		List<Map<String, Object>> list = this.searchJjdjForDown("queryJksqJinJianList", map);
		data.put("{1}", DateUtils.format(new Date(),"yyyy年MM月dd日"));
		data.put("{2}", orgName);
		inputPath = mouFilePath + "jjdj.xls";
		data.put("table", "1");
		data.put("table1", "0@3");// 第一个seet页，第2行开始
		l = new ArrayList();
		a = new String[25];
		a[0] = "0@v";
		a[1] = "1@v";
		a[2] = "2@v";
		a[3] = "3@n2";
		a[4] = "4@v";
		a[5] = "5@v";
		a[6] = "6@v";
		a[7] = "7@v";
		a[8] = "8@v";
		a[9] = "9@v";
		a[10] = "10@v";
		a[11] = "11@v";
		a[12] = "12@v";
		a[13] = "13@v";
		a[14] = "14@v";
		a[15] = "15@v";
		a[16] = "16@v";
		a[17] = "17@v";
		a[18] = "18@N";
		a[19] = "19@v";
		a[20] = "20@v";
		a[21] = "21@v";
		a[22] = "22@v";
		a[23] = "23@v";
		a[24] = "24@v";
		l.add(a);
		int xu = 0;
		for(Map<String, Object> m:list){
			xu++;
			String id = m.get("ID")+"";
			String UPLOADTIME = getBacktime("backtime",id,"上传授信资料成功","").get("V");
			m.put("UPLOADTIME", UPLOADTIME);
			m.put("ALLTIMES", getBacktime("alltimes","",res(m.get("MAKELOANDATE")+""),UPLOADTIME).get("V"));
			m.put("WAIFANGTIME", getBacktime("backtime",id,"","31.A").get("V"));
			m.put("UPLOADWAIFANGTIME", getBacktime("backtime",id,"上传外访资料成功","").get("V"));
			m.put("ISBACKREGISTER", getBacktime("isBackRegister",id,"信用初审退回!","").get("V"));
			m.put("RESETREGISTERTIME", getBacktime("resetRegisterTime",id,"信用初审退回!","").get("V"));
			m.put("REFUSECAUSE", getBacktime("refuseCause",id,"","").get("V"));
			m.put("OVERCREDITTIME", getBacktime("overcredittime",id,"","").get("V"));
			m.put("MAKEJKHTTIME", getBacktime("backtime",id,"","51").get("V"));
			
			m.put("GIVEUPCAUSE", getBacktime("giveupcause",id,"","81").get("V"));
			
			a = new String[25];
    		a[0] = xu+"";
    		a[1] = m.get("JKRXM")+"";
    		a[2] = m.get("JK_TYPE_INFO")+"";
    		a[3] = m.get("JK_LOAN_QUOTA")+"";
    		a[4] = m.get("JK_CYCLE")+"";
    		a[5] = m.get("SALENAME")+"";
    		a[6] = m.get("TEAMNAME")+"";
    		a[7] = res(m.get("JK_LOAN_DATE")+"");
    		a[8] = res(m.get("CREATE_BY")+"");
    		a[9] = res(m.get("CHUSHEN_PERSON")+"");
    		a[10] = res(m.get("UPLOADTIME")+"");
    		a[11] = res(m.get("WAIFANGTIME")+"");
    		a[12] = res(m.get("UPLOADWAIFANGTIME")+"");
    		a[13] = res(m.get("ISBACKREGISTER")+"");
    		a[14] = res(m.get("RESETREGISTERTIME")+"");
    		a[15] = res(m.get("STATE")+"");
    		a[16] = res(m.get("REFUSECAUSE")+"");
    		a[17] = res(m.get("FKJE")+"");
    		a[18] = res(m.get("HKQS")+"");
    		a[19] = res(m.get("OVERCREDITTIME")+"");//res(m.get("OVERCREDITTIME")+"");
    		a[20] = res(m.get("MAKEJKHTTIME")+"");//res(m.get("MAKEJKHTTIME")+"");
    		a[21] = res(m.get("QDRQ")+"");//res(m.get("QDRQ")+"");
    		a[22] = res(m.get("MAKELOANDATE")+"");//res(m.get("MAKELOANDATE")+"");
    		a[23] = res(m.get("ALLTIMES")+"");
    		a[24] = res(m.get("GIVEUPCAUSE")+"");
    		l.add(a);
		}
		data.put("table10@3", l);// 第一个seet页，第3行开始
    	//}
    	je.toExcel(inputPath, path, data);
    }
	
	public void exportProref2(String path,Map<String, Object> map,String orgName){
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l ;
		String[] a;
		List<Map<String, Object>> list = this.searchJjdjForDown("queryJksqJinJianList", map);
		data.put("{1}", DateUtils.format(new Date(),"yyyy年MM月dd日"));
		data.put("{2}", orgName);
		inputPath = mouFilePath + "jjdj.xls";
		data.put("table", "1");
		data.put("table1", "0@3");// 第一个seet页，第2行开始
		l = new ArrayList();
		a = new String[25];
		a[0] = "0@v";
		a[1] = "1@v";
		a[2] = "2@v";
		a[3] = "3@n2";
		a[4] = "4@v";
		a[5] = "5@v";
		a[6] = "6@v";
		a[7] = "7@v";
		a[8] = "8@v";
		a[9] = "9@v";
		a[10] = "10@v";
		a[11] = "11@v";
		a[12] = "12@v";
		a[13] = "13@v";
		a[14] = "14@v";
		a[15] = "15@v";
		a[16] = "16@v";
		a[17] = "17@v";
		a[18] = "18@N";
		a[19] = "19@v";
		a[20] = "20@v";
		a[21] = "21@v";
		a[22] = "22@v";
		a[23] = "23@v";
		a[24] = "24@v";
		l.add(a);
		int xu = 0;
		for(Map<String, Object> m:list){
			String id = m.get("ID")+"";
			String UPLOADTIME = getBacktime("backtime",id,"上传授信资料成功","").get("V");
			m.put("UPLOADTIME", UPLOADTIME);
			m.put("ALLTIMES", getBacktime("alltimes","",res(m.get("MAKELOANDATE")+""),UPLOADTIME).get("V"));
			m.put("WAIFANGTIME", getBacktime("backtime",id,"","31.A").get("V"));
			m.put("UPLOADWAIFANGTIME", getBacktime("backtime",id,"上传外访资料成功","").get("V"));
			m.put("ISBACKREGISTER", getBacktime("isBackRegister",id,"信用初审退回!","").get("V"));
			m.put("RESETREGISTERTIME", getBacktime("resetRegisterTime",id,"信用初审退回!","").get("V"));
			m.put("REFUSECAUSE", getBacktime("refuseCause",id,"","").get("V"));
			m.put("OVERCREDITTIME", getBacktime("overcredittime",id,"","").get("V"));
			m.put("MAKEJKHTTIME", getBacktime("backtime",id,"","51").get("V"));
			
			m.put("GIVEUPCAUSE", getBacktime("giveupcause",id,"","81").get("V"));
			xu++;
			a = new String[25];
    		a[0] = xu+"";
    		a[1] = m.get("JKRXM")+"";
    		a[2] = m.get("JK_TYPE_INFO")+"";
    		a[3] = m.get("JK_LOAN_QUOTA")+"";
    		a[4] = m.get("JK_CYCLE")+"";
    		a[5] = m.get("SALENAME")+"";
    		a[6] = m.get("TEAMNAME")+"";
    		a[7] = res(m.get("JK_LOAN_DATE")+"");
    		a[8] = res(m.get("CREATE_BY")+"");
    		a[9] = res(m.get("CHUSHEN_PERSON")+"");
    		a[10] = res(m.get("UPLOADTIME")+"");
    		a[11] = resColor((m.get("UPLOADTIME")+""),(m.get("WAIFANGTIME")+""),3);
    		a[12] = resColor((m.get("WAIFANGTIME")+""),(m.get("UPLOADWAIFANGTIME")+""),5);
    		a[13] = res(m.get("ISBACKREGISTER")+"");
    		a[14] = resColor((m.get("WAIFANGTIME")+""),(m.get("RESETREGISTERTIME")+""),5);
    		a[15] = res(m.get("STATE")+"");
    		a[16] = res(m.get("REFUSECAUSE")+"");
    		a[17] = res(m.get("FKJE")+"");
    		a[18] = res(m.get("HKQS")+"");
    		a[19] = resColor((m.get("UPLOADWAIFANGTIME")+""),(m.get("OVERCREDITTIME")+""),1);//res(m.get("OVERCREDITTIME")+"");
    		a[20] = resColor((m.get("OVERCREDITTIME")+""),(m.get("MAKEJKHTTIME")+""),1);//res(m.get("MAKEJKHTTIME")+"");
    		a[21] = resColor((m.get("MAKEJKHTTIME")+""),(m.get("QDRQ")+""),7);//res(m.get("QDRQ")+"");
    		a[22] = resColor((m.get("QDRQ")+""),(m.get("MAKELOANDATE")+""),1);//res(m.get("MAKELOANDATE")+"");
    		a[23] = res(m.get("ALLTIMES")+"");
    		a[24] = res(m.get("GIVEUPCAUSE")+"");
    		l.add(a);
		}
		data.put("table10@3", l);// 第一个seet页，第3行开始
    	//}
    	je.toExcel(inputPath, path, data);
    }
	
	
	public void exportProref3(String path,Map<String, Object> map){
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l ;
		String oldYyb = "";
		String newYyb = "";
		String[] a;
		List<Map<String, Object>> list = this.searchJjdjForDown("queryJksqJinJianList", map);
		data.put("{1}", DateUtils.format(new Date(),"yyyy年MM月dd日"));
		//data.put("{2}", orgName);
		inputPath = mouFilePath + "jjdj2.xls";
		data.put("table", "1");
		data.put("table1", "0@3");// 第一个seet页，第2行开始
		l = new ArrayList();
		a = new String[26];
		a[0] = "0@v";
		a[1] = "1@v";
		a[2] = "2@v";
		a[3] = "3@v";
		a[4] = "4@n2";
		a[5] = "5@v";
		a[6] = "6@v";
		a[7] = "7@v";
		a[8] = "8@v";
		a[9] = "9@v";
		a[10] = "10@v";
		a[11] = "11@v";
		a[12] = "12@v";
		a[13] = "13@v";
		a[14] = "14@v";
		a[15] = "15@v";
		a[16] = "16@v";
		a[17] = "17@v";
		a[18] = "18@v";
		a[19] = "19@N";
		a[20] = "20@v";
		a[21] = "21@v";
		a[22] = "22@v";
		a[23] = "23@v";
		a[24] = "24@v";
		a[25] = "25@v";
		l.add(a);
		int xu = 0;
		for(Map<String, Object> m:list){
			String id = m.get("ID")+"";
			String UPLOADTIME = getBacktime("backtime",id,"上传授信资料成功","").get("V");
			m.put("UPLOADTIME", UPLOADTIME);
			m.put("ALLTIMES", getBacktime("alltimes","",res(m.get("MAKELOANDATE")+""),UPLOADTIME).get("V"));
			m.put("WAIFANGTIME", getBacktime("backtime",id,"","31.A").get("V"));
			m.put("UPLOADWAIFANGTIME", getBacktime("backtime",id,"上传外访资料成功","").get("V"));
			m.put("ISBACKREGISTER", getBacktime("isBackRegister",id,"信用初审退回!","").get("V"));
			m.put("RESETREGISTERTIME", getBacktime("resetRegisterTime",id,"信用初审退回!","").get("V"));
			m.put("REFUSECAUSE", getBacktime("refuseCause",id,"","").get("V"));
			m.put("OVERCREDITTIME", getBacktime("overcredittime",id,"","").get("V"));
			m.put("MAKEJKHTTIME", getBacktime("backtime",id,"","51").get("V"));
			
			m.put("GIVEUPCAUSE", getBacktime("giveupcause",id,"","81").get("V"));
			
			xu++;
			a = new String[26];
    		a[0] = xu+"";
    		newYyb = res(m.get("YYB")+"");
    		/*
    		if(StringUtils.isNotEmpty(oldYyb)){
    			if(oldYyb.equals(newYyb)){
    				newYyb = "同上@red";
    			}else{
    				oldYyb = newYyb;
    			}
    		}else{
    			oldYyb = newYyb;
    		}
    		*/
    		a[1] = newYyb;
    		a[2] = m.get("JKRXM")+"";
    		a[3] = m.get("JK_TYPE_INFO")+"";
    		a[4] = m.get("JK_LOAN_QUOTA")+"";
    		a[5] = m.get("JK_CYCLE")+"";
    		a[6] = m.get("SALENAME")+"";
    		a[7] = m.get("TEAMNAME")+"";
    		a[8] = res(m.get("JK_LOAN_DATE")+"");
    		a[9] = res(m.get("CREATE_BY")+"");
    		a[10] = res(m.get("CHUSHEN_PERSON")+"");
    		a[11] = res(m.get("UPLOADTIME")+"");
    		a[12] = resColor((m.get("UPLOADTIME")+""),(m.get("WAIFANGTIME")+""),3);
    		a[13] = resColor((m.get("WAIFANGTIME")+""),(m.get("UPLOADWAIFANGTIME")+""),5);
    		a[14] = res(m.get("ISBACKREGISTER")+"");
    		a[15] = resColor((m.get("WAIFANGTIME")+""),(m.get("RESETREGISTERTIME")+""),5);
    		a[16] = res(m.get("STATE")+"");
    		a[17] = res(m.get("REFUSECAUSE")+"");
    		a[18] = res(m.get("FKJE")+"");
    		a[19] = res(m.get("HKQS")+"");
    		a[20] = resColor((m.get("UPLOADWAIFANGTIME")+""),(m.get("OVERCREDITTIME")+""),1);//res(m.get("OVERCREDITTIME")+"");
    		a[21] = resColor((m.get("OVERCREDITTIME")+""),(m.get("MAKEJKHTTIME")+""),1);//res(m.get("MAKEJKHTTIME")+"");
    		a[22] = resColor((m.get("MAKEJKHTTIME")+""),(m.get("QDRQ")+""),7);//res(m.get("QDRQ")+"");
    		a[23] = resColor((m.get("QDRQ")+""),(m.get("MAKELOANDATE")+""),1);//res(m.get("MAKELOANDATE")+"");
    		a[24] = res(m.get("ALLTIMES")+"");
    		a[25] = res(m.get("GIVEUPCAUSE")+"");
    		l.add(a);
		}
		data.put("table10@3", l);// 第一个seet页，第3行开始
    	//}
    	je.toExcel(inputPath, path, data);
    }
	private String res(String value){
		if(StringUtils.isNotEmpty(value) && !"null".equals(value)) {
			return value;
		}else{
			return "";
		}
	}

	private synchronized String resColor(String firstday,String nextday,int days){
		String value = "";
		if(StringUtils.isNotEmpty(nextday) && nextday.indexOf("年")>0){
			value = nextday;
			if(StringUtils.isNotEmpty(firstday) && firstday.indexOf("年")>0 ){
				try {
					Date first = CreditHarmonyComputeUtilties.getDateAfter(DateUtils.parse(firstday, "yyyy年MM月dd日"),days);
					Date next = DateUtils.parse(nextday, "yyyy年MM月dd日");
					if(next.after(first)){
						value = nextday + "@red";
					}
				} catch (ParseException e) {
					System.out.println("firstday==" + firstday + ";;nextday==" + nextday + ";;;" + e);
				}
			}
		}
		return value;
	}
	private List<Map<String,Object>> searchJjdjForDown(String queryName,Map<String,Object> filter)
	{
		return jdbcDao.searchByMergeSqlTemplate(queryName, condition(filter));
	}
}
