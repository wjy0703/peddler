package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhJksqOfficeDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqOffice;
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
public class XhJksqOfficeManager {

	private XhJksqOfficeDao xhJksqOfficeDao;
	@Autowired
	public void setXhJksqOfficeDao(XhJksqOfficeDao xhJksqOfficeDao) {
		this.xhJksqOfficeDao = xhJksqOfficeDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqOffice> searchXhJksqOffice(final Page<XhJksqOffice> page, final Map<String,Object> filters) {
		return xhJksqOfficeDao.queryXhJksqOffice(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqOffice getXhJksqOffice(Long id) {
		return xhJksqOfficeDao.get(id);
	}

	public void saveXhJksqOffice(XhJksqOffice entity) {
		xhJksqOfficeDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqOffice(Long id) {
		xhJksqOfficeDao.delete(id);
	}
	
	public void delete(Long id) {
        xhJksqOfficeDao.delete(id);
    }
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqOffice(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//单位名称
		if(filter.containsKey("name")){
			value = String.valueOf(filter.get("name"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.NAME = '" +  value + "'";
			}
		}
		//邮编
		if(filter.containsKey("postcode")){
			value = String.valueOf(filter.get("postcode"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSTCODE = '" +  value + "'";
			}
		}
		//单位地址
		if(filter.containsKey("address")){
			value = String.valueOf(filter.get("address"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADDRESS = '" +  value + "'";
			}
		}
		//行业类别
		if(filter.containsKey("typea")){
			value = String.valueOf(filter.get("typea"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TYPEA = '" +  value + "'";
			}
		}
		//单位类型（下拉）
		if(filter.containsKey("typeh")){
			value = String.valueOf(filter.get("typeh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TYPEH = '" +  value + "'";
			}
		}
		//单位电话
		if(filter.containsKey("phone")){
			value = String.valueOf(filter.get("phone"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PHONE = '" +  value + "'";
			}
		}
		//有无抵押 0:无 1:有
		if(filter.containsKey("website")){
			value = String.valueOf(filter.get("website"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.WEBSITE = '" +  value + "'";
			}
		}
		//部门
		if(filter.containsKey("department")){
			value = String.valueOf(filter.get("department"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEPARTMENT = '" +  value + "'";
			}
		}
		//职务
		if(filter.containsKey("duty")){
			value = String.valueOf(filter.get("duty"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DUTY = '" +  value + "'";
			}
		}
		//员工数量
		if(filter.containsKey("personCount")){
			value = String.valueOf(filter.get("personCount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PERSON_COUNT = '" +  value + "'";
			}
		}
		//月收入
		if(filter.containsKey("monthSalary")){
			value = String.valueOf(filter.get("monthSalary"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONTH_SALARY = '" +  value + "'";
			}
		}
		//支薪日
		if(filter.containsKey("salaryDay")){
			value = String.valueOf(filter.get("salaryDay"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SALARY_DAY = '" +  value + "'";
			}
		}
		//支付方式
		if(filter.containsKey("salaryType")){
			value = String.valueOf(filter.get("salaryType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SALARY_TYPE = '" +  value + "'";
			}
		}
		//服务年薪
		if(filter.containsKey("workYear")){
			value = String.valueOf(filter.get("workYear"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.WORK_YEAR = '" +  value + "'";
			}
		}
		//其他收入
		if(filter.containsKey("bonus")){
			value = String.valueOf(filter.get("bonus"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BONUS = '" +  value + "'";
			}
		}
		//公司成立时间
		if(filter.containsKey("cofficeStartyear")){
			value = String.valueOf(filter.get("cofficeStartyear"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COFFICE_STARTYEAR = '" +  value + "'";
			}
		}
		//社保/公积金 0:无 1：有
		if(filter.containsKey("cofficeFund")){
			value = String.valueOf(filter.get("cofficeFund"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COFFICE_FUND = '" +  value + "'";
			}
		}
		//社保/公积金基数
		if(filter.containsKey("cofficeFundNumber")){
			value = String.valueOf(filter.get("cofficeFundNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COFFICE_FUND_NUMBER = '" +  value + "'";
			}
		}
		//工作证明工资
		if(filter.containsKey("cofficeSalaryEnsure")){
			value = String.valueOf(filter.get("cofficeSalaryEnsure"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COFFICE_SALARY_ENSURE = '" +  value + "'";
			}
		}
		//银行代发工资
		if(filter.containsKey("cofficeSalaryBankEnsure")){
			value = String.valueOf(filter.get("cofficeSalaryBankEnsure"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COFFICE_SALARY_BANK_ENSURE = '" +  value + "'";
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
