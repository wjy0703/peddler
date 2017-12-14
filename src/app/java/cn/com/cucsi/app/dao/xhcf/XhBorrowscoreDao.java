package cn.com.cucsi.app.dao.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhBorrowscore;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhBorrowscoreDao extends HibernateDao<XhBorrowscore, Long>{

	public List<XhBorrowscore> queryByJksqId(Long jksqId,Integer scoreType){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("jksqId", jksqId);
		params.put("scoreType", scoreType);
		String hql = "from XhBorrowscore xhBorrowscore where jksqId = :jksqId and scoreType = :scoreType";
		return this.find(hql, params);
	}
	
	public Page<XhBorrowscore> queryXhBorrowscore(Page<XhBorrowscore> page, Map<String, Object> params){
		String hql = "from XhBorrowscore xhBorrowscore where 1=1";
		//婚姻分数
		if(params.containsKey("marriage")){
			hql = hql + " and marriage = :marriage";
		}
		//文化程度
		if(params.containsKey("education")){
			hql = hql + " and education = :education";
		}
		//户口登记
		if(params.containsKey("households")){
			hql = hql + " and households = :households";
		}
		//总工作年龄
		if(params.containsKey("totalWorkyear")){
			hql = hql + " and totalWorkyear = :totalWorkyear";
		}
		//社保
		if(params.containsKey("socialSecurity")){
			hql = hql + " and socialSecurity = :socialSecurity";
		}
		//住房情况
		if(params.containsKey("house")){
			hql = hql + " and house = :house";
		}
		//车辆情况
		if(params.containsKey("vechicle")){
			hql = hql + " and vechicle = :vechicle";
		}
		//单位性质
		if(params.containsKey("officeType")){
			hql = hql + " and officeType = :officeType";
		}
		//单位岗位性质
		if(params.containsKey("officePosition")){
			hql = hql + " and officePosition = :officePosition";
		}
		//单位工作年限
		if(params.containsKey("officeYear")){
			hql = hql + " and officeYear = :officeYear";
		}
		//职业证书
		if(params.containsKey("certification")){
			hql = hql + " and certification = :certification";
		}
		//月收入
		if(params.containsKey("monthSalary")){
			hql = hql + " and monthSalary = :monthSalary";
		}
		//月供支出比
		if(params.containsKey("consumePercent")){
			hql = hql + " and consumePercent = :consumePercent";
		}
		//信用记录
		if(params.containsKey("creditRecord")){
			hql = hql + " and creditRecord = :creditRecord";
		}
		//老客户
		if(params.containsKey("oldCustomer")){
			hql = hql + " and oldCustomer = :oldCustomer";
		}
		//公共记录
		if(params.containsKey("publicRecord")){
			hql = hql + " and publicRecord = :publicRecord";
		}
		//评分人
		if(params.containsKey("employeeId")){
			hql = hql + " and employeeId = :employeeId";
		}
		//借款申请ID
		if(params.containsKey("jksqId")){
			hql = hql + " and jksqId = :jksqId";
		}
		//评分类型 0:门店评分,1：信审评分
		if(params.containsKey("scoreType")){
			hql = hql + " and scoreType = :scoreType";
		}
		//年龄分数
		if(params.containsKey("age")){
			hql = hql + " and age = :age";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
