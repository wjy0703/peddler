package cn.com.cucsi.vechicle.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApplyComplement;

@Component
public class XhCarLoanApplyComplementDao extends HibernateDao<XhCarLoanApplyComplement, Long>{

	public Page<XhCarLoanApplyComplement> queryXhCarLoanApplyComplement(Page<XhCarLoanApplyComplement> page, Map<String, Object> params){
		String hql = "from XhCarLoanApplyComplement xhCarLoanApplyComplement where 1=1";
		//审核户籍地址
		if(params.containsKey("auditHjadress")){
			hql = hql + " and auditHjadress = :auditHjadress";
		}
		//身份真伪验证
		if(params.containsKey("auditZjhm")){
			hql = hql + " and auditZjhm = :auditZjhm";
		}
		//审核暂住证
		if(params.containsKey("auditTemporary")){
			hql = hql + " and auditTemporary = :auditTemporary";
		}
		//审核客户人法
		if(params.containsKey("auditPersonlaw")){
			hql = hql + " and auditPersonlaw = :auditPersonlaw";
		}
		//审核现住址
		if(params.containsKey("auditHomeadress")){
			hql = hql + " and auditHomeadress = :auditHomeadress";
		}
		//114电话查询情况
		if(params.containsKey("audit114")){
			hql = hql + " and audit114 = :audit114";
		}
		//客户工作审核情况
		if(params.containsKey("auditWork")){
			hql = hql + " and auditWork = :auditWork";
		}
		//征信报告显示情况
		if(params.containsKey("auditCredit")){
			hql = hql + " and auditCredit = :auditCredit";
		}
		//评估金额
		if(params.containsKey("assessMoney")){
			hql = hql + " and assessMoney = :assessMoney";
		}
		//建议借款额
		if(params.containsKey("suggestMoney")){
			hql = hql + " and suggestMoney = :suggestMoney";
		}
		//评估师姓名
		if(params.containsKey("assessPerson")){
			hql = hql + " and assessPerson = :assessPerson";
		}
		//违章及事故情况
		if(params.containsKey("breakRules")){
			hql = hql + " and breakRules = :breakRules";
		}
		//车辆评估报告结论
		if(params.containsKey("assessFinish")){
			hql = hql + " and assessFinish = :assessFinish";
		}
		//外观监测
		if(params.containsKey("visualInspection")){
			hql = hql + " and visualInspection = :visualInspection";
		}
		//车年检情况(有无)
		if(params.containsKey("inspectionFlag")){
			hql = hql + " and inspectionFlag = :inspectionFlag";
		}
		//车年检情况
		if(params.containsKey("inspection")){
			hql = hql + " and inspection = :inspection";
		}
		//交强险(有无)
		if(params.containsKey("trafficInsuranceFlag")){
			hql = hql + " and trafficInsuranceFlag = :trafficInsuranceFlag";
		}
		//交强险
		if(params.containsKey("trafficInsurance")){
			hql = hql + " and trafficInsurance = :trafficInsurance";
		}
		//商业险(有无)
		if(params.containsKey("businessInsuranceFlag")){
			hql = hql + " and businessInsuranceFlag = :businessInsuranceFlag";
		}
		//商业险
		if(params.containsKey("businessInsurance")){
			hql = hql + " and businessInsurance = :businessInsurance";
		}
		//车架号
		if(params.containsKey("chassisNumber")){
			hql = hql + " and chassisNumber = :chassisNumber";
		}
		//出厂日期
		if(params.containsKey("madeTime")){
			hql = hql + " and madeTime = :madeTime";
		}
		//车牌号码
		if(params.containsKey("plate")){
			hql = hql + " and plate = :plate";
		}
		//登记日期
		if(params.containsKey("registerTime")){
			hql = hql + " and registerTime = :registerTime";
		}
		//车辆厂牌型号
		if(params.containsKey("lable")){
			hql = hql + " and lable = :lable";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
