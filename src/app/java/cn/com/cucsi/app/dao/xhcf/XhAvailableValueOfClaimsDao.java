package cn.com.cucsi.app.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhAvailableValueOfClaimsDao extends HibernateDao<XhAvailableValueOfClaims, Long>{

	public Page<XhAvailableValueOfClaims> queryXhAvailableValueOfClaims(Page<XhAvailableValueOfClaims> page, Map<String, Object> params){
		String hql = "from XhAvailableValueOfClaims xhAvailableValueOfClaims where 1=1";
		//借款申请ID
//		if(params.containsKey("jkrxm")){
//			hql = hql + " and xhAvailableValueOfClaims.xhJksq.jkrxm = :jkrxm";
//		}
		hql = hql + this.getHql(params);
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		System.out.println("hql =====>" + hql);
		
		return this.findPage(page, hql, params);
	}
	
	public String getKyzqjzall(Map<String, Object> params){
		String hql = "select sum(kyzqjz)/10000 from XhAvailableValueOfClaims xhAvailableValueOfClaims where 1=1";
		//借款申请ID
//		if(params.containsKey("jkrxm")){
//			hql = hql + " and xhAvailableValueOfClaims.xhJksq.jkrxm = :jkrxm";
//		}
		hql = hql + this.getHql(params);
		List<Double> kyzqjzall = this.find(hql, params);
		String result = "";
		if(null != kyzqjzall && kyzqjzall.size() == 1 && null != kyzqjzall.get(0)){
			result = kyzqjzall.get(0)+"";
		}
		return result;
	}
	
	private String getHql(Map<String, Object> params){
		String hql = "";
		//还款日
		if(params.containsKey("hkr")){
			hql = hql + " and hkr = :hkr";
		}
		//借款编号
		if(params.containsKey("loanCode")){
			hql = hql + " and xhJksq.loanCode like '%'||:loanCode||'%'";
		}
		//借款金额
		if(params.containsKey("jkje")){
			hql = hql + " and jkje = :jkje";
		}
		//可用债权价值
		if(params.containsKey("kyzqjz")){
			hql = hql + " and kyzqjz = :kyzqjz";
		}
		//中间人_ID
		if(params.containsKey("zjrId")){
			hql = hql + " and middleMan.id = :zjrId";
		}
		//中间人持有比例
		if(params.containsKey("zjrcybl")){
			hql = hql + " and zjrcybl = :zjrcybl";
		}
		//剩余期数
		if(params.containsKey("syqs")){
			hql = hql + " and syqs = :syqs";
		}
		//首期还款日期
		if(params.containsKey("sqhkrq")){
			hql = hql + " and sqhkrq = :sqhkrq";
		}
		//末期还款日期
		if(params.containsKey("mqhkrq")){
			hql = hql + " and mqhkrq = :mqhkrq";
		}
		//剩余还款月数
		if(params.containsKey("syhkysMin")){
			params.put("syhkysMin", Long.parseLong(params.get("syhkysMin").toString()));
			hql = hql + " and syhkys >= :syhkysMin";
		}
		//剩余还款月数
		if(params.containsKey("syhkysMax")){
			params.put("syhkysMax", Long.parseLong(params.get("syhkysMax").toString()));
			hql = hql + " and syhkys <= :syhkysMax";
		}
		//剩余推荐金额
		if(params.containsKey("kyzqjzMin")){
			params.put("kyzqjzMin", Double.parseDouble(params.get("kyzqjzMin").toString()));
			hql = hql + " and kyzqjz >= :kyzqjzMin";
		}
		//剩余推荐金额
		if(params.containsKey("kyzqjzMax")){
			params.put("kyzqjzMax", Double.parseDouble(params.get("kyzqjzMax").toString()));
			hql = hql + " and kyzqjz <= :kyzqjzMax";
		}
		if(params.containsKey("jkLoanQuotaMin")){
			hql = hql + " and xhJksq.jkLoanQuota > :jkLoanQuotaMin";
		}
		if(params.containsKey("jkLoanQuotaMax")){
			hql = hql + " and xhJksq.jkLoanQuota > :jkLoanQuotaMax";
		}
		if(params.containsKey("jkrxm")){
			hql = hql + " and xhJksq.jkrxm like '%'||:jkrxm||'%'";
		}
		if(params.containsKey("dkll")){
			params.put("dkll", Double.parseDouble(params.get("dkll").toString()));
			hql = hql + " and loanContract.dkll = :dkll";
		}
		if(params.containsKey("mateid")){
			String value = params.get("mateid")+"";
			hql = hql + " and id not in ("+value.substring(0, value.length()-1)+")";
		}
		return hql;
	}
	
	
	public List<XhAvailableValueOfClaims> queryXhAvailableValueOfClaims(Map<String, Object> params){
		String hql = "from XhAvailableValueOfClaims xhAvailableValueOfClaims where 1=1";
		//借款申请ID
		if(params.containsKey("jksqId")){
			hql = hql + " and jksqId = :jksqId";
		}
		//还款日
		if(params.containsKey("hkr")){
			hql = hql + " and hkr = :hkr";
		}
		//借款编号
		if(params.containsKey("loanCode")){
			hql = hql + " and xhJksq.loanCode like '%'||:loanCode||'%'";
		}
		//借款金额
		if(params.containsKey("jkje")){
			hql = hql + " and jkje = :jkje";
		}
		//可用债权价值
		if(params.containsKey("kyzqjz")){
			hql = hql + " and kyzqjz = :kyzqjz";
		}
		//中间人_ID
		if(params.containsKey("zjrId")){
			hql = hql + " and middleMan.id = :zjrId";
		}
		//中间人持有比例
		if(params.containsKey("zjrcybl")){
			hql = hql + " and zjrcybl = :zjrcybl";
		}
		//剩余期数
		if(params.containsKey("syqs")){
			hql = hql + " and syqs = :syqs";
		}
		//首期还款日期
		if(params.containsKey("sqhkrq")){
			hql = hql + " and sqhkrq = :sqhkrq";
		}
		//末期还款日期
		if(params.containsKey("mqhkrq")){
			hql = hql + " and mqhkrq = :mqhkrq";
		}
		//剩余还款月数
		if(params.containsKey("syhkysMin")){
			params.put("syhkysMin", Long.parseLong(params.get("syhkysMin").toString()));
			hql = hql + " and syhkys >= :syhkysMin";
		}
		//剩余还款月数
		if(params.containsKey("syhkysMax")){
			params.put("syhkysMax", Long.parseLong(params.get("syhkysMax").toString()));
			hql = hql + " and syhkys <= :syhkysMax";
		}
		//剩余推荐金额
		if(params.containsKey("kyzqjzMin")){
			params.put("kyzqjzMin", Double.parseDouble(params.get("kyzqjzMin").toString()));
			hql = hql + " and kyzqjz >= :kyzqjzMin";
		}
		//剩余推荐金额
		if(params.containsKey("kyzqjzMax")){
			params.put("kyzqjzMax", Double.parseDouble(params.get("kyzqjzMax").toString()));
			hql = hql + " and kyzqjz <= :kyzqjzMax";
		}
		if(params.containsKey("jkLoanQuotaMin")){
			hql = hql + " and xhJksq.jkLoanQuota > :jkLoanQuotaMin";
		}
		if(params.containsKey("jkLoanQuotaMax")){
			hql = hql + " and xhJksq.jkLoanQuota > :jkLoanQuotaMax";
		}
		if(params.containsKey("jkrxm")){
			hql = hql + " and xhJksq.jkrxm like '%'||:jkrxm||'%'";
		}
		
		if(params.containsKey("mateid")){
			String value = params.get("mateid")+"";
			hql = hql + " and id not in ("+value.substring(0, value.length()-1)+")";
		}
		System.out.println("hql =====>" + hql);
		return this.find(hql, params);
	}
}
