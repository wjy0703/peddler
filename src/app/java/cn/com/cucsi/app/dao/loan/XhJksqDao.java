package cn.com.cucsi.app.dao.loan;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqDao extends HibernateDao<XhJksq, Long>{

	public Page<XhJksq> queryXhJksq(Page<XhJksq> page, Map<String, Object> params){
		String hql = "from XhJksq xhJksq where 1=1";
		
		//借款人姓名
		if(params.containsKey("jkrxm")){
			hql = hql + " and jkrxm = :jkrxm";
		}
		//借款申请额度
		if(params.containsKey("jkLoanQuota")){
			hql = hql + " and jkLoanQuota = :jkLoanQuota";
		}
		//借款周期
		if(params.containsKey("jkCycle")){
			hql = hql + " and jkCycle = :jkCycle";
		}
		//申请日期
		if(params.containsKey("jkLoanDate")){
			hql = hql + " and jkLoanDate = :jkLoanDate";
		}
		//借款用途
		if(params.containsKey("jkUse")){
			hql = hql + " and jkUse = :jkUse";
		}
		//有无共同还款人
		if(params.containsKey("togetherPerson")){
			hql = hql + " and togetherPerson = :togetherPerson";
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
		//借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
		if(params.containsKey("jkType")){
			hql = hql + " and jkType = :jkType";
		}
		//审核状态
		if(params.containsKey("examState")){
			hql = hql + " and examState = :examState";
		}
		//审核结束状态
		if(params.containsKey("examEndState")){
			hql = hql + " and examEndState = :examEndState";
		}
		//组织
//		if(params.containsKey("organiId")){
//			hql = hql + " and organiId = :organiId";
//		}
		
		//借款合同ID
		if(params.containsKey("xhjkht")){//所属省份
			String value = String.valueOf(params.get("xhjkht"));
			if(StringUtils.isNotEmpty(value)) {
				hql = hql + " and xhJksq.xhjkht.id = '" +  value + "'";
			}
		}
		
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	public boolean updJksq(Long Id,Map<String, Object> params){
		boolean flag = false;
		
		StringBuffer sql = new StringBuffer("update XH_JKSQ  jksq set ");
		if(params.containsKey("state")){//借款申请大的状态
			String value = String.valueOf(params.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append("jksq.STATE='").append(value).append("',");
			}
		}
		if(params.containsKey("TOGETHER_PERSON")){//借款申请共同借款人
			String value = String.valueOf(params.get("TOGETHER_PERSON"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append("jksq.TOGETHER_PERSON='").append(value).append("',");
			}
		}
		if(params.containsKey("BACKUP02")){//备用字段2改为进件时间
			String value = String.valueOf(params.get("BACKUP02"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append("jksq.BACKUP02='").append(value).append("',");
			}
		}
		
		int temp = sql.lastIndexOf(",");
		if(temp == sql.length()-1){
			sql.deleteCharAt(temp);
		}
		sql.append(" where ID='").append(Id).append("'");
		
		int i = executeUpdateBySql(sql.toString());
		if(i != 0){
			System.out.println("修改"+i+"条XhJksq数据");
			flag = true;
		}
		return flag;
	}
	
	public XhJksq findJksqByXy(Long xydkzxId) {
		String hql = "from XhJksq xhJksq where xhJksq.xydkzx.id = "+xydkzxId;
		List<XhJksq> list = this.find(hql);
		return list.get(0);
	}
	
	public List<XhJksq> listJksqByXy(Long xydkzxId) {
		String hql = "from XhJksq xhJksq where xhJksq.xydkzx.id = "+xydkzxId;
		return this.find(hql);
	}
	
	public List<XhJksq> listJksqByZjhm(String zjhm) {
		String hql = "from XhJksq xhJksq where xhJksq.zjhm = '"+zjhm+"'";
		return this.find(hql);
	}
}
