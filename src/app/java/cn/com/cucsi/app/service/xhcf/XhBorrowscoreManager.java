package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhBorrowscoreDao;
import cn.com.cucsi.app.entity.xhcf.XhBorrowscore;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhBorrowscoreManager {

	private XhBorrowscoreDao xhBorrowscoreDao;
	@Autowired
	public void setXhBorrowscoreDao(XhBorrowscoreDao xhBorrowscoreDao) {
		this.xhBorrowscoreDao = xhBorrowscoreDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhBorrowscore> searchXhBorrowscore(final Page<XhBorrowscore> page, final Map<String,Object> filters) {
		return xhBorrowscoreDao.queryXhBorrowscore(page, filters);
	}
	@Transactional(readOnly = true)
	public XhBorrowscore getXhBorrowscore(Long id) {
		return xhBorrowscoreDao.get(id);
	}

	public void saveXhBorrowscore(XhBorrowscore entity) {
		xhBorrowscoreDao.save(entity);
		
	}
	/**
	 * 
	 * @param jksqId
	 * @param scoreType
	 * @return
	 */
	public XhBorrowscore queryByJksqId(Long jksqId,Integer scoreType){
		List<XhBorrowscore> list = xhBorrowscoreDao.queryByJksqId(jksqId ,scoreType);
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return new XhBorrowscore();
		}
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhBorrowscore(Long id) {
		xhBorrowscoreDao.delete(id);
	}
	
	public boolean batchDelXhBorrowscore(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhBorrowscore(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhBorrowscore(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//婚姻分数
		if(filter.containsKey("marriage")){
			value = String.valueOf(filter.get("marriage"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MARRIAGE = '" +  value + "'";
			}
		}
		//文化程度
		if(filter.containsKey("education")){
			value = String.valueOf(filter.get("education"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.EDUCATION = '" +  value + "'";
			}
		}
		//户口登记
		if(filter.containsKey("households")){
			value = String.valueOf(filter.get("households"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSEHOLDS = '" +  value + "'";
			}
		}
		//总工作年龄
		if(filter.containsKey("totalWorkyear")){
			value = String.valueOf(filter.get("totalWorkyear"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TOTAL_WORKYEAR = '" +  value + "'";
			}
		}
		//社保
		if(filter.containsKey("socialSecurity")){
			value = String.valueOf(filter.get("socialSecurity"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SOCIAL_SECURITY = '" +  value + "'";
			}
		}
		//住房情况
		if(filter.containsKey("house")){
			value = String.valueOf(filter.get("house"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE = '" +  value + "'";
			}
		}
		//车辆情况
		if(filter.containsKey("vechicle")){
			value = String.valueOf(filter.get("vechicle"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.VECHICLE = '" +  value + "'";
			}
		}
		//单位性质
		if(filter.containsKey("officeType")){
			value = String.valueOf(filter.get("officeType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE_TYPE = '" +  value + "'";
			}
		}
		//单位岗位性质
		if(filter.containsKey("officePosition")){
			value = String.valueOf(filter.get("officePosition"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE_POSITION = '" +  value + "'";
			}
		}
		//单位工作年限
		if(filter.containsKey("officeYear")){
			value = String.valueOf(filter.get("officeYear"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE_YEAR = '" +  value + "'";
			}
		}
		//职业证书
		if(filter.containsKey("certification")){
			value = String.valueOf(filter.get("certification"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CERTIFICATION = '" +  value + "'";
			}
		}
		//月收入
		if(filter.containsKey("monthSalary")){
			value = String.valueOf(filter.get("monthSalary"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONTH_SALARY = '" +  value + "'";
			}
		}
		//月供支出比
		if(filter.containsKey("consumePercent")){
			value = String.valueOf(filter.get("consumePercent"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONSUME_PERCENT = '" +  value + "'";
			}
		}
		//信用记录
		if(filter.containsKey("creditRecord")){
			value = String.valueOf(filter.get("creditRecord"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_RECORD = '" +  value + "'";
			}
		}
		//老客户
		if(filter.containsKey("oldCustomer")){
			value = String.valueOf(filter.get("oldCustomer"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OLD_CUSTOMER = '" +  value + "'";
			}
		}
		//公共记录
		if(filter.containsKey("publicRecord")){
			value = String.valueOf(filter.get("publicRecord"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PUBLIC_RECORD = '" +  value + "'";
			}
		}
		//评分人
		if(filter.containsKey("employeeId")){
			value = String.valueOf(filter.get("employeeId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.EMPLOYEE_ID = '" +  value + "'";
			}
		}
		//借款申请ID
		if(filter.containsKey("jksqId")){
			value = String.valueOf(filter.get("jksqId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JKSQ_ID = '" +  value + "'";
			}
		}
		//评分类型 0:门店评分,1：信审评分
		if(filter.containsKey("scoreType")){
			value = String.valueOf(filter.get("scoreType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SCORE_TYPE = '" +  value + "'";
			}
		}
		//年龄分数
		if(filter.containsKey("age")){
			value = String.valueOf(filter.get("age"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AGE = '" +  value + "'";
			}
		}
		
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	
}
