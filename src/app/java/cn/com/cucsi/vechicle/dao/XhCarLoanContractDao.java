package cn.com.cucsi.vechicle.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanContract;

@Component
public class XhCarLoanContractDao extends HibernateDao<XhCarLoanContract, Long>{

	public Page<XhCarLoanContract> queryXhCarLoanContract(Page<XhCarLoanContract> page, Map<String, Object> params){
		String hql = this.hqlStr(params);
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	public List<XhCarLoanContract> queryXhCarLoanContractList(Map<String, Object> params){
		String hql = this.hqlStr(params);
		return this.find(hql, params);
	}
	
	
	private String hqlStr(Map<String, Object> params){
		String hql = "from XhCarLoanContract xhCarLoanContract where 1=1";
		//合同编号
		if(params.containsKey("contractNum")){
			hql = hql + " and contractNum = :contractNum";
		}
		//合同金额
		if(params.containsKey("contractMoney")){
			hql = hql + " and contractMoney = :contractMoney";
		}
		//合同签订日期
		if(params.containsKey("qdrq")){
			hql = hql + " and qdrq = :qdrq";
		}
		//合同日期
		if(params.containsKey("contractDate")){
			hql = hql + " and contractDate = :contractDate";
		}
		//0：待签约 1：已签约上传   -1：取消
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//中间人ID
		if(params.containsKey("middleManId")){
			hql = hql + " and middleManId = :middleManId";
		}
		//利息率（移交：0.5%；GPS：0.475%）
		if(params.containsKey("dkll")){
			hql = hql + " and dkll = :dkll";
		}
		//总费率（移交：3.5%；GPS：5%  ，客户经理可录入）
		if(params.containsKey("dkllComlpex")){
			hql = hql + " and dkllComlpex = :dkllComlpex";
		}
		//利息（借款总额*利息率）
		if(params.containsKey("interest")){
			hql = hql + " and interest = :interest";
		}
		//总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)）
		if(params.containsKey("serveComlpexMoney")){
			hql = hql + " and serveComlpexMoney = :serveComlpexMoney";
		}
		//咨询费（综合息费*59%）
		if(params.containsKey("adviceFee")){
			hql = hql + " and adviceFee = :adviceFee";
		}
		//审核费（综合息费*5%）
		if(params.containsKey("auditFee")){
			hql = hql + " and auditFee = :auditFee";
		}
		//服务费（综合息费-咨询费-审核费）
		if(params.containsKey("serviceFee")){
			hql = hql + " and serviceFee = :serviceFee";
		}
		//借款合同ID
		if(params.containsKey("xhJkhtId")){
			hql = hql + " and xhJkhtId = :xhJkhtId";
		}
		//还款日
		if(params.containsKey("hkr")){
			hql = hql + " and hkr = :hkr";
		}
		//展期期数
		if(params.containsKey("noExtension")){
			hql = hql + " and noExtension = :noExtension";
		}
		//是否展期
		if(params.containsKey("isExtension")){
			hql = hql + " and isExtension = :isExtension";
		}
		//备注
		if(params.containsKey("remark")){
			hql = hql + " and remark = :remark";
		}
		//申请实体
		if(params.containsKey("car_apply_id")){
			hql = hql + " and xhCarLoanApply.id = :car_apply_id";
		}
		return hql;
	}
	
}
