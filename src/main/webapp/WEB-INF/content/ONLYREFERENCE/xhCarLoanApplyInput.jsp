<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCarLoanApply/saveXhCarLoanApply" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCarLoanApply.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>借款申请额度：</label>
				<input name="jkLoanQuota" type="text" size="30" value="${xhCarLoanApply.jkLoanQuota}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款成数（借款总额/车辆评估金额）：</label>
				<input name="loanScale" type="text" size="30" value="${xhCarLoanApply.loanScale}" class="required" maxlength="22" />
			</p>
			<p>
				<label>综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）：</label>
				<input name="comlpexMoney" type="text" size="30" value="${xhCarLoanApply.comlpexMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款周期：</label>
				<input name="jkCycle" type="text" size="30" value="${xhCarLoanApply.jkCycle}" class="required" maxlength="18" />
			</p>
			<p>
				<label>借款类型GPS移交：</label>
				<input name="jkType" type="text" size="30" value="${xhCarLoanApply.jkType}" class="required" maxlength="3" />
			</p>
			<p>
				<label>申请日期：</label>
				<input name="jkLoanDate" type="text" size="30" value="${xhCarLoanApply.jkLoanDate}" class="required" maxlength="20" />
			</p>
			<p>
				<label>开卡（省/市）：</label>
				<input name="bankCity" type="text" size="30" value="${xhCarLoanApply.bankCity}" class="required" maxlength="18" />
			</p>
			<p>
				<label>账户开户行：</label>
				<input name="bankOpen" type="text" size="30" value="${xhCarLoanApply.bankOpen}" class="required" maxlength="256" />
			</p>
			<p>
				<label>账户名称：</label>
				<input name="bankUsername" type="text" size="30" value="${xhCarLoanApply.bankUsername}" class="required" maxlength="256" />
			</p>
			<p>
				<label>账户号码：</label>
				<input name="bankNum" type="text" size="30" value="${xhCarLoanApply.bankNum}" class="required" maxlength="256" />
			</p>
			<p>
				<label>组织：</label>
				<input name="organiId" type="text" size="30" value="${xhCarLoanApply.organiId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>管辖城市(做查询依据)：</label>
				<input name="crmcity" type="text" size="30" value="${xhCarLoanApply.crmcity}" class="required" maxlength="22" />
			</p>
			<p>
				<label>管辖省份(做查询依据)：</label>
				<input name="crmprovince" type="text" size="30" value="${xhCarLoanApply.crmprovince}" class="required" maxlength="22" />
			</p>
			<p>
				<label>团队经理(做查询依据)：</label>
				<input name="teamLeaderId" type="text" size="30" value="${xhCarLoanApply.teamLeaderId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>客户经理(做查询依据)：</label>
				<input name="customerLeaderId" type="text" size="30" value="${xhCarLoanApply.customerLeaderId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>客服(做查询依据)：</label>
				<input name="employeeServiceStaff" type="text" size="30" value="${xhCarLoanApply.employeeServiceStaff}" class="required" maxlength="50" />
			</p>
			<p>
				<label>销售(做查询依据)：</label>
				<input name="employeeSeller" type="text" size="30" value="${xhCarLoanApply.employeeSeller}" class="required" maxlength="50" />
			</p>
			<p>
				<label>提交状态：</label>
				<input name="subState" type="text" size="30" value="${xhCarLoanApply.subState}" class="required" maxlength="8" />
			</p>
			<p>
				<label>借款申请流程状态，状态参考实体：</label>
				<input name="state" type="text" size="30" value="${xhCarLoanApply.state}" class="required" maxlength="8" />
			</p>
			<p>
				<label>借款编号：</label>
				<input name="loanCode" type="text" size="30" value="${xhCarLoanApply.loanCode}" class="required" maxlength="64" />
			</p>
			<p>
				<label>备用字段01：</label>
				<input name="backup01" type="text" size="30" value="${xhCarLoanApply.backup01}" class="required" maxlength="32" />
			</p>
			<p>
				<label>备用字段02：</label>
				<input name="backup02" type="text" size="30" value="${xhCarLoanApply.backup02}" class="required" maxlength="128" />
			</p>
			<p>
				<label>备用字段03：</label>
				<input name="backup03" type="text" size="30" value="${xhCarLoanApply.backup03}" class="required" maxlength="32" />
			</p>
			<p>
				<label>备用字段04：</label>
				<input name="backup04" type="text" size="30" value="${xhCarLoanApply.backup04}" class="required" maxlength="32" />
			</p>
			<p>
				<label>备用字段05：</label>
				<input name="backup05" type="text" size="30" value="${xhCarLoanApply.backup05}" class="required" maxlength="32" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
