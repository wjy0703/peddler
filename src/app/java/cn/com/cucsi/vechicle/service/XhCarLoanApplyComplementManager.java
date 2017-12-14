package cn.com.cucsi.vechicle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyComplementDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApplyComplement;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarLoanApplyComplementManager {

	private XhCarLoanApplyComplementDao xhCarLoanApplyComplementDao;
	@Autowired
	public void setXhCarLoanApplyComplementDao(XhCarLoanApplyComplementDao xhCarLoanApplyComplementDao) {
		this.xhCarLoanApplyComplementDao = xhCarLoanApplyComplementDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCarLoanApplyComplement> searchXhCarLoanApplyComplement(final Page<XhCarLoanApplyComplement> page, final Map<String,Object> filters) {
		return xhCarLoanApplyComplementDao.queryXhCarLoanApplyComplement(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCarLoanApplyComplement getXhCarLoanApplyComplement(Long id) {
		return xhCarLoanApplyComplementDao.get(id);
	}

	public void saveXhCarLoanApplyComplement(XhCarLoanApplyComplement entity) {
		xhCarLoanApplyComplementDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCarLoanApplyComplement(Long id) {
		xhCarLoanApplyComplementDao.delete(id);
	}
	
	public boolean batchDelXhCarLoanApplyComplement(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCarLoanApplyComplement(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCarLoanApplyComplement(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//审核户籍地址
		if(filter.containsKey("auditHjadress")){
			value = String.valueOf(filter.get("auditHjadress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_HJADRESS = '" +  value + "'";
			}
		}
		//身份真伪验证
		if(filter.containsKey("auditZjhm")){
			value = String.valueOf(filter.get("auditZjhm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_ZJHM = '" +  value + "'";
			}
		}
		//审核暂住证
		if(filter.containsKey("auditTemporary")){
			value = String.valueOf(filter.get("auditTemporary"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_TEMPORARY = '" +  value + "'";
			}
		}
		//审核客户人法
		if(filter.containsKey("auditPersonlaw")){
			value = String.valueOf(filter.get("auditPersonlaw"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_PERSONLAW = '" +  value + "'";
			}
		}
		//审核现住址
		if(filter.containsKey("auditHomeadress")){
			value = String.valueOf(filter.get("auditHomeadress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_HOMEADRESS = '" +  value + "'";
			}
		}
		//114电话查询情况
		if(filter.containsKey("audit114")){
			value = String.valueOf(filter.get("audit114"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_114 = '" +  value + "'";
			}
		}
		//客户工作审核情况
		if(filter.containsKey("auditWork")){
			value = String.valueOf(filter.get("auditWork"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_WORK = '" +  value + "'";
			}
		}
		//征信报告显示情况
		if(filter.containsKey("auditCredit")){
			value = String.valueOf(filter.get("auditCredit"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_CREDIT = '" +  value + "'";
			}
		}
		//评估金额
		if(filter.containsKey("assessMoney")){
			value = String.valueOf(filter.get("assessMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ASSESS_MONEY = '" +  value + "'";
			}
		}
		//建议借款额
		if(filter.containsKey("suggestMoney")){
			value = String.valueOf(filter.get("suggestMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SUGGEST_MONEY = '" +  value + "'";
			}
		}
		//评估师姓名
		if(filter.containsKey("assessPerson")){
			value = String.valueOf(filter.get("assessPerson"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ASSESS_PERSON = '" +  value + "'";
			}
		}
		//违章及事故情况
		if(filter.containsKey("breakRules")){
			value = String.valueOf(filter.get("breakRules"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BREAK_RULES = '" +  value + "'";
			}
		}
		//车辆评估报告结论
		if(filter.containsKey("assessFinish")){
			value = String.valueOf(filter.get("assessFinish"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ASSESS_FINISH = '" +  value + "'";
			}
		}
		//外观监测
		if(filter.containsKey("visualInspection")){
			value = String.valueOf(filter.get("visualInspection"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.VISUAL_INSPECTION = '" +  value + "'";
			}
		}
		//车年检情况(有无)
		if(filter.containsKey("inspectionFlag")){
			value = String.valueOf(filter.get("inspectionFlag"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INSPECTION_FLAG = '" +  value + "'";
			}
		}
		//车年检情况
		if(filter.containsKey("inspection")){
			value = String.valueOf(filter.get("inspection"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INSPECTION = '" +  value + "'";
			}
		}
		//交强险(有无)
		if(filter.containsKey("trafficInsuranceFlag")){
			value = String.valueOf(filter.get("trafficInsuranceFlag"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TRAFFIC_INSURANCE_FLAG = '" +  value + "'";
			}
		}
		//交强险
		if(filter.containsKey("trafficInsurance")){
			value = String.valueOf(filter.get("trafficInsurance"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TRAFFIC_INSURANCE = '" +  value + "'";
			}
		}
		//商业险(有无)
		if(filter.containsKey("businessInsuranceFlag")){
			value = String.valueOf(filter.get("businessInsuranceFlag"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUSINESS_INSURANCE_FLAG = '" +  value + "'";
			}
		}
		//商业险
		if(filter.containsKey("businessInsurance")){
			value = String.valueOf(filter.get("businessInsurance"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUSINESS_INSURANCE = '" +  value + "'";
			}
		}
		//车架号
		if(filter.containsKey("chassisNumber")){
			value = String.valueOf(filter.get("chassisNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CHASSIS_NUMBER = '" +  value + "'";
			}
		}
		//出厂日期
		if(filter.containsKey("madeTime")){
			value = String.valueOf(filter.get("madeTime"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MADE_TIME = '" +  value + "'";
			}
		}
		//车牌号码
		if(filter.containsKey("plate")){
			value = String.valueOf(filter.get("plate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PLATE = '" +  value + "'";
			}
		}
		//登记日期
		if(filter.containsKey("registerTime")){
			value = String.valueOf(filter.get("registerTime"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REGISTER_TIME = '" +  value + "'";
			}
		}
		//车辆厂牌型号
		if(filter.containsKey("lable")){
			value = String.valueOf(filter.get("lable"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LABLE = '" +  value + "'";
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
