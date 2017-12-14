package cn.com.cucsi.app.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCreditAuditDao extends HibernateDao<XhCreditAudit, Long> {
	/**
	 * 查询通过信审的借款申请数
	 * 
	 * @return
	 */
	public Long getNumsOfPassAduit() {
		String hql = "select count(*) from XhCreditAudit aduit where aduit.creditState=1";
		Query query=this.getSession().createQuery(hql) ;
		return (Long) query.uniqueResult();

	}

	public Page<XhCreditAudit> queryXhCreditAudit(Page<XhCreditAudit> page,
			Map<String, Object> params) {
		String hql = "select xhCreditAudit from XhCreditAudit xhCreditAudit ";
		
		//团队经理和销售人员
		if (params.containsKey("teamName") || params.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(params.get("teamName"));
			String saleName = String.valueOf(params.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				hql = hql + " ,Xydkzx b";
				hql = hql + " where xhCreditAudit.loanApply.xydkzx.id = b.id";
				if (!("null").equals(teamName) && teamNameFlag) {
					hql = hql + " and b.employeeCrm.name like '%"+teamName+"%'";
				}
				if (!("null").equals(saleName) && saleNameFlag) {
					hql = hql + " and b.employeeCca.name like '%"+saleName+"%'";
				}
			}
		}else{
			hql = hql + " where 1=1";
		}
		
		if (params.containsKey("state")) {// 借款状态
			String value = String.valueOf(params.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					hql = hql + " and xhCreditAudit.loanApply.state = '" + value + "'";
				}
			}
		}
		// 初审日期
		if (params.containsKey("firstAuditTime")) {
			hql = hql + " and to_char(xhCreditAudit.createTime,'yyyy-MM') = :firstAuditTime";
		}
		// 初审人员
		if (params.containsKey("createBy")) {
			hql = hql + " and xhCreditAudit.createBy = :createBy";
		}
		// 客户电话
		if (params.containsKey("telephone")) {
			hql = hql + " and xhCreditAudit.loanApply.telephone = :telephone";
		}
		// 证件号码
		if (params.containsKey("zjhm")) {
			hql = hql + " and xhCreditAudit.loanApply.zjhm = :zjhm";
		}
		// 信审状态：0信审未结束 1信审结束
		if (params.containsKey("creditState")) {
			hql = hql + " and xhCreditAudit.creditState = :creditState";
		}
		// 信审结果：1通过、0拒绝
		if (params.containsKey("creditResult")) {
			hql = hql + " and xhCreditAudit.creditResult = :creditResult";
		}
		// 信审类型：初审、复审、终审
		if (params.containsKey("creditType")) {
			String value = String.valueOf(params.get("creditType"));
			hql = hql + " and xhCreditAudit.creditType = "+new Long(value);
		}
		// 借款申请ID 外键
		if (params.containsKey("jkrxm")) {
			hql = hql + " and xhCreditAudit.loanApply.jkrxm = :jkrxm";
		}
		
		// 借款申请ID 外键
		if (params.containsKey("loanApplyId")) {
			hql = hql + " and xhCreditAudit.loanApply.id = :loanApplyId";
		}
		// 主键ID
		if (params.containsKey("id")) {
			//hql = hql + " and xhCreditAudit.id = :id";
			String value = String.valueOf(params.get("id"));
			hql = hql + " and xhCreditAudit.id = "+new Long(value);
		}
		// 进件开始时间
		if (params.containsKey("startDate")) {
			hql = hql + " and xhCreditAudit.loanApply.backup02 >= :startDate";
		}
		// 进件结束时间
		if (params.containsKey("endDate")) {
			hql = hql + " and xhCreditAudit.loanApply.backup02 <= :endDate";
		}
		// 所属省份
		if (params.containsKey("province")) {
			String value = String.valueOf(params.get("province"));
			hql = hql + " and xhCreditAudit.loanApply.province.id = "+new Long(value);
		}
		// 所属城市
		if (params.containsKey("city")) {
			String value = String.valueOf(params.get("city"));
			hql = hql + " and xhCreditAudit.loanApply.province.id = "+new Long(value);
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}else{
			 
			 hql = hql + " order by xhCreditAudit.createTime desc" ;
		}
		System.out.println("hql===>" + hql);
		return this.findPage(page, hql, params);
	}
	
	/**
	 * 按条件查询信审信息。
	 * 自己加条件，自己补充dao
	 * @param params
	 * @return
	 */
	public List<XhCreditAudit> findByXhCreditAudit(Map<String, Object> params){
		
		StringBuffer hql = new StringBuffer(" from XhCreditAudit xhCreditAudit where 1=1 ");
		
		if (params.containsKey("loanApplyId")) {
			hql.append(" and xhCreditAudit.loanApply.id=").append(params.get("loanApplyId"));
		}
		hql.append( " order by xhCreditAudit.createTime desc") ;
		return this.find(hql.toString());
	}
}
