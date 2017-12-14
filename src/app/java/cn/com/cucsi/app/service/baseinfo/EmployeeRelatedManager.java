package cn.com.cucsi.app.service.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;


@Component
public class EmployeeRelatedManager {
	
    @Autowired
	private JdbcDao jdbcDao;
    
    @Autowired
	private UserDao userDao;
	
    /**
     * 通过组织机构Id 及 Level（团队经理，客户经理等） 查找level对应的人员。
     * @param orgId
     * @param levels
     * @param page
     * @param params
     * @return
     */
    public List<Map<String, Object>> getEmployeesByOrgAndLevel(long orgId , List<String> levels,JdbcPage page, Map<String,Object> params){
    	Map<String,Object> conditions = new HashMap<String,Object>();
    	String LEVELCONDITION = null;
    	conditions.put("ID", orgId + "");
    	conditions.put("WHERESQL", getSqlFromMap(params));
    	
    	if(levels.size() > 0){
    		if(levels.size() == 1)
    			LEVELCONDITION = " = '" + levels.get(0) + "'";
    		else{
    			LEVELCONDITION = " in ( ";
    			for(String level : levels){
    				LEVELCONDITION  += "'"+level + "',";
    			}
    			LEVELCONDITION = LEVELCONDITION.substring(0,LEVELCONDITION.length()-1);
    			LEVELCONDITION += ")";
    		}    	
    		conditions.put("LEVELCONDITION", "AND C.POSITION_LEVEL_CODE " + LEVELCONDITION);
    	}else{//查找所有的
    		conditions.put("LEVELCONDITION", "");
    	}
    	
		return jdbcDao.searchPagesByMergeSqlTemplate("queryEmployeesByOrgAndPos", conditions, page);
    }

	private String getSqlFromMap(Map<String, Object> params) {
		StringBuffer whereSql = new StringBuffer();
		if(params.get("name") != null && StringUtils.isNotEmpty(params.get("name").toString())){
			whereSql.append(" AND B.NAME like '%" + params.get("name").toString() + "%'");
		}
		
		return whereSql.toString();
	}
    
	
	public List<Map<String, Object>>  getEmployeesByLevelWithCurrentOrgid(List<String> levels,JdbcPage page, Map<String,Object> params){
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		User u = userDao.get(operator.getUserId());
		long orgId = u.getEmployee().getOrgani().getId();
		return getEmployeesByOrgAndLevel(orgId,levels,page,params);
		
	}
    
}
