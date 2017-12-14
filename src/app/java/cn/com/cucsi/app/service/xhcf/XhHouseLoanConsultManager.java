package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhHouseLoanConsultDao;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanConsult;
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
public class XhHouseLoanConsultManager {

	private XhHouseLoanConsultDao xhHouseLoanConsultDao;
	@Autowired
	public void setXhHouseLoanConsultDao(XhHouseLoanConsultDao xhHouseLoanConsultDao) {
		this.xhHouseLoanConsultDao = xhHouseLoanConsultDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhHouseLoanConsult> searchXhHouseLoanConsult(final Page<XhHouseLoanConsult> page, final Map<String,Object> filters) {
		return xhHouseLoanConsultDao.queryXhHouseLoanConsult(page, filters);
	}
	@Transactional(readOnly = true)
	public XhHouseLoanConsult getXhHouseLoanConsult(Long id) {
		return xhHouseLoanConsultDao.get(id);
	}

	public void saveXhHouseLoanConsult(XhHouseLoanConsult entity) {
		xhHouseLoanConsultDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhHouseLoanConsult(Long id) {
		xhHouseLoanConsultDao.delete(id);
	}
	
	public boolean batchDelXhHouseLoanConsult(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhHouseLoanConsult(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhHouseLoanConsult(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//客户姓名
		if(filter.containsKey("customerName")){
			value = String.valueOf(filter.get("customerName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_NAME = '" +  value + "'";
			}
		}
		//性别
		if(filter.containsKey("customerSex")){
			value = String.valueOf(filter.get("customerSex"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_SEX = '" +  value + "'";
			}
		}
		//身份证号码
		if(filter.containsKey("identificationCard")){
			value = String.valueOf(filter.get("identificationCard"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.IDENTIFICATION_CARD = '" +  value + "'";
			}
		}
		//客户来源
		if(filter.containsKey("customerSource")){
			value = String.valueOf(filter.get("customerSource"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_SOURCE = '" +  value + "'";
			}
		}
		//婚否
		if(filter.containsKey("marrital")){
			value = String.valueOf(filter.get("marrital"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MARRITAL = '" +  value + "'";
			}
		}
		//电话
		if(filter.containsKey("customerTel")){
			value = String.valueOf(filter.get("customerTel"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_TEL = '" +  value + "'";
			}
		}
		//现住址
		if(filter.containsKey("nowAddress")){
			value = String.valueOf(filter.get("nowAddress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.NOW_ADDRESS = '" +  value + "'";
			}
		}
		//房产性质
		if(filter.containsKey("houseType")){
			value = String.valueOf(filter.get("houseType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_TYPE = '" +  value + "'";
			}
		}
		//使用年限
		if(filter.containsKey("houseLimit")){
			value = String.valueOf(filter.get("houseLimit"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_LIMIT = '" +  value + "'";
			}
		}
		//所属区县
		if(filter.containsKey("houseRegion")){
			value = String.valueOf(filter.get("houseRegion"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_REGION = '" +  value + "'";
			}
		}
		//房产面积
		if(filter.containsKey("houseArea")){
			value = String.valueOf(filter.get("houseArea"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_AREA = '" +  value + "'";
			}
		}
		//建成年代
		if(filter.containsKey("houseYears")){
			value = String.valueOf(filter.get("houseYears"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_YEARS = '" +  value + "'";
			}
		}
		//房屋楼层
		if(filter.containsKey("houseFloor")){
			value = String.valueOf(filter.get("houseFloor"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_FLOOR = '" +  value + "'";
			}
		}
		//房屋地址
		if(filter.containsKey("houseAddress")){
			value = String.valueOf(filter.get("houseAddress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_ADDRESS = '" +  value + "'";
			}
		}
		//房产详细情况
		if(filter.containsKey("houseInfo")){
			value = String.valueOf(filter.get("houseInfo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_INFO = '" +  value + "'";
			}
		}
		//综合利率
		if(filter.containsKey("allLoanRate")){
			value = String.valueOf(filter.get("allLoanRate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ALL_LOAN_RATE = '" +  value + "'";
			}
		}
		//贷款额度
		if(filter.containsKey("loanAmount")){
			value = String.valueOf(filter.get("loanAmount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_AMOUNT = '" +  value + "'";
			}
		}
		//贷款周期
		if(filter.containsKey("loanMonth")){
			value = String.valueOf(filter.get("loanMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_MONTH = '" +  value + "'";
			}
		}
		//评估价格
		if(filter.containsKey("assessPrice")){
			value = String.valueOf(filter.get("assessPrice"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ASSESS_PRICE = '" +  value + "'";
			}
		}
		//贷款用途
		if(filter.containsKey("loanUse")){
			value = String.valueOf(filter.get("loanUse"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_USE = '" +  value + "'";
			}
		}
		//还款来源
		if(filter.containsKey("backSource")){
			value = String.valueOf(filter.get("backSource"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BACK_SOURCE = '" +  value + "'";
			}
		}
		//客户要求
		if(filter.containsKey("customerRequired")){
			value = String.valueOf(filter.get("customerRequired"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_REQUIRED = '" +  value + "'";
			}
		}
		//备注
		if(filter.containsKey("remark")){
			value = String.valueOf(filter.get("remark"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMARK = '" +  value + "'";
			}
		}
		//客户经理
		if(filter.containsKey("customrerManagerId")){
			value = String.valueOf(filter.get("customrerManagerId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMRER_MANAGER_ID = '" +  value + "'";
			}
		}
		//团队经理
		if(filter.containsKey("teamManagerId")){
			value = String.valueOf(filter.get("teamManagerId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TEAM_MANAGER_ID = '" +  value + "'";
			}
		}
		//涉诉查询
		if(filter.containsKey("lodgeQueryId")){
			value = String.valueOf(filter.get("lodgeQueryId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LODGE_QUERY_ID = '" +  value + "'";
			}
		}
		//组织机构
		if(filter.containsKey("organId")){
			value = String.valueOf(filter.get("organId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ORGAN_ID = '" +  value + "'";
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
