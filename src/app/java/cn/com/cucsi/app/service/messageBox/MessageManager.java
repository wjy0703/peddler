package cn.com.cucsi.app.service.messageBox;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.dao.messageBox.MessageBoxDao;
import cn.com.cucsi.app.dao.security.UserDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.messageBox.MessageBox;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;

/**
 * 消息相关实体的管理类, 消息盒子类.
 * 
 * @author mdy
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MessageManager extends PublicService{

	private static Logger logger = LoggerFactory.getLogger(MessageManager.class);

	private NamedJdbcDao jdbcDao;
	
	private UserDao userDao;
	
	private MessageBoxDao messageBoxDao;

	@Autowired
	public void setMessageBoxDao(MessageBoxDao messageBoxDao) {
		this.messageBoxDao = messageBoxDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setJdbcDao(NamedJdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	public Map<String, Object> getConditionsSql(Employee employee, String typeCoding){
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		User user = userDao.get(operator.getUserId());
		String roles = user.getRoleIds();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		StringBuffer messageType = new StringBuffer();
		//查询单个接口人
		sql.append(" and m.employee_id = :employee_id");
		//查询单独角色in 参数只能这样,参数方式在本地和正式库没问题,但是在远程库xhcf_test中,报错
		sql2.append(" and m.role_id in ("+roles+") ");
		sql2.append(" and m.organi_scope is null");
		//查询角色与组织结构分级显示
		sql3.append(" and m.role_id in ("+roles+") ");
		sql3.append(" and m.organi_scope like '%'||:organi_scope||'%'");
		if(!typeCoding.equals("'public_message'")){
			messageType.append(" and m.message_state = '0'");
		}
		messageType.append(" and t.type_coding in ("+typeCoding+")");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("employee_id", employee.getId());
		conditions.put("organi_scope", "o"+employee.getOrgani().getId()+"o");
		conditions.put("sql", sql.toString());
		conditions.put("sql2", sql2.toString());
		conditions.put("sql3", sql3.toString());
		conditions.put("messageType", messageType.toString());
		return conditions;
	}
	
	public List<Map<String, Object>> getLoginMessage(Employee employee){
		Map<String, Object> conditions = getConditionsSql(employee, "'message', 'public_message'");
		return jdbcDao.searchByMergeSqlTemplate("queryMessageByEmployeeId", conditions);
	}
	
	public List<Map<String, Object>> getLoginAdminMessage(){
		StringBuffer messageType = new StringBuffer();
		messageType.append(" and t.type_coding in ('message', 'public_message')");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", "");
		conditions.put("messageType", messageType.toString());
		return jdbcDao.searchByMergeSqlTemplate("queryAdminMessageBoxList", conditions);
	}
	
	public List<Map<String, Object>> getExpireMessage(Employee employee){
		Map<String, Object> conditions = getConditionsSql(employee, "'expire_message'");
		return jdbcDao.searchByMergeSqlTemplate("queryMessageByEmployeeId", conditions);
	}
	
	public List<Map<String, Object>> getPublicMessage(Employee employee, Map<String, Object> filter, JdbcPage page){
		String value = "";
		String sql4 = "";
		if (filter.containsKey("title")) {// 标题
			value = String.valueOf(filter.get("title"));
			if (StringUtils.isNotEmpty(value)) {
				sql4 = sql4 + " and z.message_title like '%" + value + "%'";
			}
		}
		if (filter.containsKey("sts")) {// 标题
			value = String.valueOf(filter.get("sts"));
			if (StringUtils.isNotEmpty(value)) {
				sql4 = sql4 + " and z.message_state = '" + value + "'";
			}
		}
		
		Map<String, Object> conditions = getConditionsSql(employee, "'public_message'");
		conditions.put("sql4", sql4.toString());
		return jdbcDao.searchPagesByMergeSqlTemplate("queryMessageBoxList", conditions,
				page);
	}
	
	public boolean batchHaveRead(String[] ids) {
		try {
			for(String id: ids){
				saveHaveRead(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean batchDelete(String[] ids) {
		try {
			for(String id: ids){
				delMessage(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public void saveHaveRead(Long id){
		MessageBox messageBox = getMessageBoxById(id);
		messageBox.setMessageState("1");
		saveMessage(messageBox);
	}
	
	public void delMessage(Long id){
		messageBoxDao.delete(id);
	}
	
	public MessageBox getMessageBoxById(Long id){
		return messageBoxDao.get(id);
	}
	
	public void saveMessage(MessageBox messageBox){
		messageBoxDao.save(messageBox);
	}
}
