package cn.com.cucsi.vechicle.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;

@Component
public class XhCarLoanApplyDao extends HibernateDao<XhCarLoanApply, Long>{

	public Page<XhCarLoanApply> queryXhCarLoanApply(Page<XhCarLoanApply> page, Map<String, Object> params){
		String hql = "from XhCarLoanApply xhCarLoanApply where 1=1";
		//借款申请额度
		if(params.containsKey("jkLoanQuota")){
			hql = hql + " and jkLoanQuota = :jkLoanQuota";
		}
		//借款成数（借款总额/车辆评估金额）
		if(params.containsKey("loanScale")){
			hql = hql + " and loanScale = :loanScale";
		}
		//综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）
		if(params.containsKey("comlpexMoney")){
			hql = hql + " and comlpexMoney = :comlpexMoney";
		}
		//借款周期
		if(params.containsKey("jkCycle")){
			hql = hql + " and jkCycle = :jkCycle";
		}
		//借款类型GPS移交
		if(params.containsKey("jkType")){
			hql = hql + " and jkType = :jkType";
		}
		//申请日期
		if(params.containsKey("jkLoanDate")){
			hql = hql + " and jkLoanDate = :jkLoanDate";
		}
		//开卡（省/市）
		if(params.containsKey("bankCity")){
			hql = hql + " and bankCity = :bankCity";
		}
		//账户开户行
		if(params.containsKey("bankOpen")){
			hql = hql + " and bankOpen = :bankOpen";
		}
		//账户名称
		if(params.containsKey("bankUsername")){
			hql = hql + " and bankUsername = :bankUsername";
		}
		//账户号码
		if(params.containsKey("bankNum")){
			hql = hql + " and bankNum = :bankNum";
		}
		//组织
		if(params.containsKey("organiId")){
			hql = hql + " and organiId = :organiId";
		}
		//管辖城市(做查询依据)
		if(params.containsKey("crmcity")){
			hql = hql + " and crmcity = :crmcity";
		}
		//管辖省份(做查询依据)
		if(params.containsKey("crmprovince")){
			hql = hql + " and crmprovince = :crmprovince";
		}
		//团队经理(做查询依据)
		if(params.containsKey("teamLeaderId")){
			hql = hql + " and teamLeaderId = :teamLeaderId";
		}
		//客户经理(做查询依据)
		if(params.containsKey("customerLeaderId")){
			hql = hql + " and customerLeaderId = :customerLeaderId";
		}
		//客服(做查询依据)
		if(params.containsKey("employeeServiceStaff")){
			hql = hql + " and employeeServiceStaff = :employeeServiceStaff";
		}
		//销售(做查询依据)
		if(params.containsKey("employeeSeller")){
			hql = hql + " and employeeSeller = :employeeSeller";
		}
		//提交状态
		if(params.containsKey("subState")){
			hql = hql + " and subState = :subState";
		}
		//借款申请流程状态，状态参考实体
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//借款编号
		if(params.containsKey("loanCode")){
			hql = hql + " and loanCode = :loanCode";
		}
		//备用字段01
		if(params.containsKey("backup01")){
			hql = hql + " and backup01 = :backup01";
		}
		//备用字段02
		if(params.containsKey("backup02")){
			hql = hql + " and backup02 = :backup02";
		}
		//备用字段03
		if(params.containsKey("backup03")){
			hql = hql + " and backup03 = :backup03";
		}
		//备用字段04
		if(params.containsKey("backup04")){
			hql = hql + " and backup04 = :backup04";
		}
		//备用字段05
		if(params.containsKey("backup05")){
			hql = hql + " and backup05 = :backup05";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
