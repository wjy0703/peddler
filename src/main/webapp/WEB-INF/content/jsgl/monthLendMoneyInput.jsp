<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/overDate/saveOverDate"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<table style="border: 1">
				<tr>
					<td><label>出借人：</label> ****</td>
				</tr>
			</table>
			<div class="divider"></div>
			目前出借的款项所产生的收益情况
			<table>
				<tr>
					<td>报告周期：</td>
					<td>2012-10-15</td>
					<td>--</td>
					<td>2012-11-15</td>
				</tr>
				<tr>
					<td>账户级别：</td>
					<td>1</td>
					<td>报告日：</td>
					<td>2012-11-15</td>
				</tr>
				<tr>
					<td>客户编号：</td>
					<td>23232323</td>
					<td>报告日资产总额：</td>
					<td>2323232</td>
				</tr>
				<tr>
					<td>出借编号：</td>
					<td>23232323</td>
					<td>初始出借日期：</td>
					<td>2012-11-15</td>
				</tr>
				<tr>
					<td>资金出借及回收方式：</td>
					<td>32</td>
					<td>初始出借金额：</td>
					<td>322323</td>
				</tr>
			</table>
			<div class="divider"></div>
			目前的每笔出借款项实际回收情况及出借收益
			<table class="table" width="100%">
				<thead>
					<tr>
						<th width="80" orderField="cjrxm" class="asc">报告日</th>
						<th width="80" orderField="khbm">出借编号</th>
						<th width="80">报告期内借款</th>
						<th width="80">报告期内借款人实际还款金额（或还款风险金代偿金额）</th>
						<th width="80">延迟支付应折减金额</th>
						<th width="70">账户管理费率</th>
						<th width="80">账户管理费</th>
						<th width="80">报告日您选择受让的债权金额</th>
						<th width="80">报告日您选择回收的金额</th>
						<th width="70">报告日资产总额</th>
					</tr>
				</thead>
				<tbody>
					<tr target="sid_user" rel="${user.id}">
						<td>2012-11</td>
						<td>${user.khbm }</td>
						<td>${user.gxcs }</td>
						<td>${user.employeeCrm.name }</td>
						<td>${user.employeeCca.name }</td>
						<td></td>
						<td>${user.gxcs }</td>
						<td>${user.employeeCrm.name }</td>
						<td>${user.employeeCca.name }</td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
