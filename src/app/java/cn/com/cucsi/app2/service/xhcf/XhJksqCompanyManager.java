package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhJksqCompanyDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqCompany;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhJksqCompanyManager {

	private XhJksqCompanyDao xhJksqCompanyDao;
	@Autowired
	public void setXhJksqCompanyDao(XhJksqCompanyDao xhJksqCompanyDao) {
		this.xhJksqCompanyDao = xhJksqCompanyDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqCompany> searchXhJksqCompany(final Page<XhJksqCompany> page, final Map<String,Object> filters) {
		return xhJksqCompanyDao.queryXhJksqCompany(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqCompany getXhJksqCompany(Long id) {
		return xhJksqCompanyDao.get(id);
	}

	public void saveXhJksqCompany(XhJksqCompany entity) {
		xhJksqCompanyDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void delete(Long id) {
		xhJksqCompanyDao.delete(id);
	}
	
	public boolean batchDelXhJksqCompany(String[] ids){
		
		try {
			for(String id: ids){
				delete(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqCompany(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//营业执照
		if(filter.containsKey("busiLicences")){
			value = String.valueOf(filter.get("busiLicences"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUSI_LICENCES = '" +  value + "'";
			}
		}
		//成立日期
		if(filter.containsKey("startDate")){
			value = String.valueOf(filter.get("startDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.START_DATE = '" +  value + "'";
			}
		}
		//注册资金(注册/实收资本)
		if(filter.containsKey("registerMoney")){
			value = String.valueOf(filter.get("registerMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REGISTER_MONEY = '" +  value + "'";
			}
		}
		//经营场所(采用枚举类方式，自由，租用，按揭)
		if(filter.containsKey("areaType")){
			value = String.valueOf(filter.get("areaType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AREA_TYPE = '" +  value + "'";
			}
		}
		//租金或月还款(和上面类型有关)
		if(filter.containsKey("moneyUsed")){
			value = String.valueOf(filter.get("moneyUsed"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY_USED = '" +  value + "'";
			}
		}
		//营业面积
		if(filter.containsKey("areaSquare")){
			value = String.valueOf(filter.get("areaSquare"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AREA_SQUARE = '" +  value + "'";
			}
		}
		//淡季月份
		if(filter.containsKey("weakMonth")){
			value = String.valueOf(filter.get("weakMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.WEAK_MONTH = '" +  value + "'";
			}
		}
		//淡季月份收入
		if(filter.containsKey("weakMonthEarn")){
			value = String.valueOf(filter.get("weakMonthEarn"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.WEAK_MONTH_EARN = '" +  value + "'";
			}
		}
		//旺季月份
		if(filter.containsKey("strongMonth")){
			value = String.valueOf(filter.get("strongMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STRONG_MONTH = '" +  value + "'";
			}
		}
		//旺季月份收入
		if(filter.containsKey("strongMonthEarn")){
			value = String.valueOf(filter.get("strongMonthEarn"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STRONG_MONTH_EARN = '" +  value + "'";
			}
		}
		//平季月份
		if(filter.containsKey("middleMonth")){
			value = String.valueOf(filter.get("middleMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MIDDLE_MONTH = '" +  value + "'";
			}
		}
		//平季月份收入
		if(filter.containsKey("middleMonthEarn")){
			value = String.valueOf(filter.get("middleMonthEarn"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MIDDLE_MONTH_EARN = '" +  value + "'";
			}
		}
		//主要供应商1
		if(filter.containsKey("supplierOne")){
			value = String.valueOf(filter.get("supplierOne"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SUPPLIER_ONE = '" +  value + "'";
			}
		}
		//主要供应商2
		if(filter.containsKey("supplierTwo")){
			value = String.valueOf(filter.get("supplierTwo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SUPPLIER_TWO = '" +  value + "'";
			}
		}
		//主要供应商3
		if(filter.containsKey("supplierThree")){
			value = String.valueOf(filter.get("supplierThree"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SUPPLIER_THREE = '" +  value + "'";
			}
		}
		//公司名称
		if(filter.containsKey("cname")){
			value = String.valueOf(filter.get("cname"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CNAME = '" +  value + "'";
			}
		}
		//公司型式
		if(filter.containsKey("cbusniessType")){
			value = String.valueOf(filter.get("cbusniessType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CBUSNIESS_TYPE = '" +  value + "'";
			}
		}
		//公司经营地点
		if(filter.containsKey("cbusinessAddr")){
			value = String.valueOf(filter.get("cbusinessAddr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CBUSINESS_ADDR = '" +  value + "'";
			}
		}
		//场地合同有效期
		if(filter.containsKey("cbusinessPeriod")){
			value = String.valueOf(filter.get("cbusinessPeriod"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CBUSINESS_PERIOD = '" +  value + "'";
			}
		}
		//股东/股权比例
		if(filter.containsKey("cstockholderRatio")){
			value = String.valueOf(filter.get("cstockholderRatio"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CSTOCKHOLDER_RATIO = '" +  value + "'";
			}
		}
		//变更情况
		if(filter.containsKey("cchangeInfo")){
			value = String.valueOf(filter.get("cchangeInfo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CCHANGE_INFO = '" +  value + "'";
			}
		}
		//业务经营情况
		if(filter.containsKey("crunningStatus")){
			value = String.valueOf(filter.get("crunningStatus"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CRUNNING_STATUS = '" +  value + "'";
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
