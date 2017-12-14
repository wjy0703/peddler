package cn.com.cucsi.app.service.xhcf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.EmployeeDao;
import cn.com.cucsi.app.dao.xhcf.CjrxxDao;
import cn.com.cucsi.app.dao.xhcf.XhTzsqDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.web.ServletUtils;

/**
 * 
 * 修改投资申请表里面的createby ORGANI_ID 等, 
 * @Component
 * @author xjs
 *
 */
@Component
@Transactional
public class LeaveReplaceManager {
	
	@Autowired
	private XhTzsqDao xhTzsqDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CjrxxDao cjrxxDao;
	
	@Autowired
	private JdbcDao jdbcDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	/**
	 * 根据投资申请编号查询记录
	 * @param tzsqbh
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<XhTzsq> queryByTzsqbh(String tzsqbh){
		Map map = new HashMap();
		map.put("tzsqbh", tzsqbh);
		return xhTzsqDao.queryByTzsqbh(map);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<XhTzsq> queryByCjrxxId(Long cjrxxId){
		Map map = new HashMap();
		map.put("cjrxxId", cjrxxId);
		return xhTzsqDao.queryByTzsqbh(map);
	}
	
	
	/**
	 * 离职时修改相应的字段
	 * @param xhTzsq
	 */
	private void changeTzsqReplaceProperties(XhTzsq xhTzsq){
		  String sql = "update xh_tzsq set create_by = '" + xhTzsq.getCreateBy()+ "', ORGANI_ID = " + xhTzsq.getOrgani().getId()+ " where id = " + xhTzsq.getId() ;
	      jdbcDao.updateBySql(sql);		
	}
	
	/**
	 * 离职管理，修改理财经理，团队经理等
	 */
	@Transactional
	public void changeCjrxxLeaders(String name,String oldCrmName,String crmName,String ccaName,String tzsqbh){
		
		name = removeIllegalChars(name);
		oldCrmName = removeIllegalChars(oldCrmName);
		crmName = removeIllegalChars(crmName);
		ccaName = removeIllegalChars(ccaName);
		tzsqbh = removeIllegalChars(tzsqbh);
		jdbcTemplate.execute("{call PROPERTIES_UTIL.changeCRMCCA('"+name+"','"+oldCrmName+"','"+crmName+"','"+ccaName+"','"+tzsqbh+"')}");
		/*
		List<XhcfCjrxx> infos = cjrxxDao.queryCjrxxByNameAndLeader(name, oldCrmName);
		String notExist   =  "客户 :"  +  name  +  " 经理:" + oldCrmName + "不存在,检查是否输入错误，或已经修改过";
		String notUnique  =  "客户 :"  +  name  +  " 经理:" + oldCrmName + "不唯一,请联系管理员进行修改";
		
		Employee employeeCrm =  getUniqueEmployee(crmName);
		Employee employeeCca =  getUniqueEmployee(ccaName); 
		if(empty(infos)){	
			//如果无记录
			List<XhcfCjrxx> changed = cjrxxDao.queryCjrxxByNameAndLeader(name, crmName);
			if(empty(changed))
				throw new RuntimeException(notExist);
			else{
				//
				if(changed.size()>1){
					throw new RuntimeException(notExist);	
				}
				XhcfCjrxx record = changed.get(0);
				if(ccaName.equals(record.getEmployeeCca().getName())){
					changeTzsq(tzsqbh,name,crmName,employeeCrm.getOrgani());
				    return;
				}else{//团队经理不一致,没有用了
					XhcfCjrxx cjrxx = changed.get(0);
			        cjrxx.setCreateBy(crmName);			        
			        cjrxx.setOrgani(employeeCrm.getOrgani());        
			        cjrxx.setEmployeeCrm(employeeCrm);
			        cjrxx.setEmployeeCca(employeeCca);
			        cjrxxDao.save(cjrxx);
			        String sql = "update xh_cjrxx set create_by = '" + crmName+ "' where id = " + cjrxx.getId().toString();
			        jdbcDao.updateBySql(sql);
					changeTzsq(tzsqbh,name,crmName,employeeCrm.getOrgani());
					return ;
				}
				
				
			}
		}
		if(infos.size()  > 1)
	        throw new RuntimeException(notUnique);		
        
		XhcfCjrxx cjrxx = infos.get(0);
        cjrxx.setCreateBy(crmName);

        
        cjrxx.setOrgani(employeeCrm.getOrgani());        
        cjrxx.setEmployeeCrm(employeeCrm);
        cjrxx.setEmployeeCca(employeeCca);
        cjrxxDao.save(cjrxx);
        String sql = "update xh_cjrxx set create_by = '" + crmName+ "' where id = " + cjrxx.getId().toString();
        jdbcDao.updateBySql(sql);
        
		changeTzsq(tzsqbh,name,crmName,employeeCrm.getOrgani());
		*/
	}
	/**
	 * 申请tzsq的信息，如果tzsqbh不为空，按照其修改，否则查询name 修改所有的xh_tzsq表数据
	 * @param tzsqbh	投资申请编号
	 * @param name		客户姓名
	 * @param crmName   新客户经理
	 * @param organi 所属机构
	 */
	private void changeTzsq(String tzsqbh, String name, String crmName,
			Organi organi) {
		if(StringUtils.isNotEmpty(tzsqbh)){
			//投资申请为16为，因为从excel读入时后面带特殊字符，所以这样处理
			tzsqbh =  tzsqbh.substring(0,16);
			List<XhTzsq> tzsqs = this.queryByTzsqbh(tzsqbh);
			if (empty(tzsqs)) {
				throw new RuntimeException("投资编号异常");
			}
			XhTzsq xhTzsq = tzsqs.get(0);
			xhTzsq.setCreateBy(crmName);
			xhTzsq.setOrgani(organi);
			changeTzsqReplaceProperties(xhTzsq);
			return;
		}else{//不提供tzsqbh，按照客户名修改所有的投资申请
			/*Long cjrxxId =   getCjrxxIdByName(name);
			if(cjrxxId != null){
				List<XhTzsq> lendRecords = queryByCjrxxId(cjrxxId);
				for(int index = 0 ; index < lendRecords.size() ; index++){
					XhTzsq xhTzsq = lendRecords.get(index);
					xhTzsq.setCreateBy(crmName);
					xhTzsq.setOrgani(organi);
					changeTzsqReplaceProperties(xhTzsq);
				}
			}*/
		}
	}
	
	
	/**
	 * 去除两端空格和特殊字符
	 * @param name
	 * @return
	 */
    private String removeIllegalChars(String str) {
        if(StringUtils.isNotEmpty(str)){
    	    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        	Matcher m = p.matcher(str);
        	str = m.replaceAll("");
            return str;
        }
    	return "";
	}

	private boolean empty(List infos) {
    	return infos == null ? true : infos.size() == 0 ?true:false;
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
	 * 通过姓名查找Id
	 * @param name
	 * @return
	 */
	public Long getCjrxxIdByName(String name){
		List<XhcfCjrxx> cjrxxs = cjrxxDao.queryCjrxxByName(name);
		if(!empty(cjrxxs)){
			XhcfCjrxx cjrxx = cjrxxs.get(0);
			return cjrxx.getId();
		}
		return null;
	}
	/**
	 * 通过姓名和create_by查找信息,如果create_by为空，则只按照第一个参数查找
	 * @param name
	 * @param createBy
	 * @return
	 */
	public List<XhcfCjrxx> queryCjrxxByNameAndLeader(String name,String createBy){
		return cjrxxDao.queryCjrxxByNameAndLeader(name, createBy);		
	}
}
