package cn.com.cucsi.app.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhCreditTaskAssign;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCreditTaskAssignDao extends
		HibernateDao<XhCreditTaskAssign, Long> {

	public Page<XhCreditTaskAssign> queryXhCreditTaskAssign(
			Page<XhCreditTaskAssign> page, Map<String, Object> params) {
		String hql = "select xhCreditTaskAssign from XhCreditTaskAssign xhCreditTaskAssign ";
		
		//团队经理和销售人员
		if (params.containsKey("teamName") || params.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(params.get("teamName"));
			String saleName = String.valueOf(params.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				hql = hql + " ,Xydkzx b";
				hql = hql + " where xhCreditTaskAssign.loanApply.xydkzx.id = b.id";
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
		// 借款申请ID
		if (params.containsKey("loanApplyId")) {
			hql = hql + " and loanApply.id = :loanApplyId";
		}
		// 客户电话
		if (params.containsKey("telephone")) {
			hql = hql + " and loanApply.telephone = :telephone";
		}
		// 证件号码
		if (params.containsKey("zjhm")) {
			hql = hql + " and loanApply.zjhm = :zjhm";
		}
		// 初审人员ID
		if (params.containsKey("firstAuditEmployeeId")) {
			hql = hql + " and employee.id = :firstAuditEmployeeId";
		}
		
	
		// 借款人
		if (params.containsKey("jkrxm")) {
			String value = String.valueOf(params.get("jkrxm"));
			hql = hql + " and loanApply.jkrxm like '%"+value+"%'";
		}
		// 初审人员
		if (params.containsKey("firstAuditEmployeeName")) {
			hql = hql + " and employee.name = :firstAuditEmployeeName";
		}
		// 进件开始时间
		if (params.containsKey("startDate")) {
			hql = hql + " and loanApply.backup02 >= :startDate";
		}
		// 进件结束时间
		if (params.containsKey("endDate")) {
			hql = hql + " and loanApply.backup02 <= :endDate";
		}
		// 所属省份
		if (params.containsKey("province")) {
			String value = String.valueOf(params.get("province"));
			hql = hql + " and loanApply.province.id = "+new Long(value);
		}
		// 所属城市
		if (params.containsKey("city")) {
			String value = String.valueOf(params.get("city"));
			hql = hql + " and loanApply.province.id = "+new Long(value);
		}
		// 状态
		if (params.containsKey("state")) {
			hql = hql + " and loanApply.state = :state";
		}
		
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}else{
			 
			 hql = hql + " order by xhCreditTaskAssign.createTime desc" ;
		}
		
		
		
		return this.findPage(page, hql, params);
	}
	
	public List<XhCreditTaskAssign> getXhCreditTaskAssignByJksqId(Long jksqId){
		String hql = "from XhCreditTaskAssign xhCreditTaskAssign where xhCreditTaskAssign.loanApply.id = " + jksqId;
		return this.find(hql);
	}
}
