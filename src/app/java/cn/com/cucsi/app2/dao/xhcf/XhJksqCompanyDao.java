package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhJksqCompany;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqCompanyDao extends HibernateDao<XhJksqCompany, Long>{

	public Page<XhJksqCompany> queryXhJksqCompany(Page<XhJksqCompany> page, Map<String, Object> params){
		String hql = "from XhJksqCompany xhJksqCompany where 1=1";
		//营业执照
		if(params.containsKey("busiLicences")){
			hql = hql + " and busiLicences = :busiLicences";
		}
		//成立日期
		if(params.containsKey("startDate")){
			hql = hql + " and startDate = :startDate";
		}
		//注册资金(注册/实收资本)
		if(params.containsKey("registerMoney")){
			hql = hql + " and registerMoney = :registerMoney";
		}
		//经营场所(采用枚举类方式，自由，租用，按揭)
		if(params.containsKey("areaType")){
			hql = hql + " and areaType = :areaType";
		}
		//租金或月还款(和上面类型有关)
		if(params.containsKey("moneyUsed")){
			hql = hql + " and moneyUsed = :moneyUsed";
		}
		//营业面积
		if(params.containsKey("areaSquare")){
			hql = hql + " and areaSquare = :areaSquare";
		}
		//淡季月份
		if(params.containsKey("weakMonth")){
			hql = hql + " and weakMonth = :weakMonth";
		}
		//淡季月份收入
		if(params.containsKey("weakMonthEarn")){
			hql = hql + " and weakMonthEarn = :weakMonthEarn";
		}
		//旺季月份
		if(params.containsKey("strongMonth")){
			hql = hql + " and strongMonth = :strongMonth";
		}
		//旺季月份收入
		if(params.containsKey("strongMonthEarn")){
			hql = hql + " and strongMonthEarn = :strongMonthEarn";
		}
		//平季月份
		if(params.containsKey("middleMonth")){
			hql = hql + " and middleMonth = :middleMonth";
		}
		//平季月份收入
		if(params.containsKey("middleMonthEarn")){
			hql = hql + " and middleMonthEarn = :middleMonthEarn";
		}
		//主要供应商1
		if(params.containsKey("supplierOne")){
			hql = hql + " and supplierOne = :supplierOne";
		}
		//主要供应商2
		if(params.containsKey("supplierTwo")){
			hql = hql + " and supplierTwo = :supplierTwo";
		}
		//主要供应商3
		if(params.containsKey("supplierThree")){
			hql = hql + " and supplierThree = :supplierThree";
		}
		//公司名称
		if(params.containsKey("cname")){
			hql = hql + " and cname = :cname";
		}
		//公司型式
		if(params.containsKey("cbusniessType")){
			hql = hql + " and cbusniessType = :cbusniessType";
		}
		//公司经营地点
		if(params.containsKey("cbusinessAddr")){
			hql = hql + " and cbusinessAddr = :cbusinessAddr";
		}
		//场地合同有效期
		if(params.containsKey("cbusinessPeriod")){
			hql = hql + " and cbusinessPeriod = :cbusinessPeriod";
		}
		//股东/股权比例
		if(params.containsKey("cstockholderRatio")){
			hql = hql + " and cstockholderRatio = :cstockholderRatio";
		}
		//变更情况
		if(params.containsKey("cchangeInfo")){
			hql = hql + " and cchangeInfo = :cchangeInfo";
		}
		//业务经营情况
		if(params.containsKey("crunningStatus")){
			hql = hql + " and crunningStatus = :crunningStatus";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
