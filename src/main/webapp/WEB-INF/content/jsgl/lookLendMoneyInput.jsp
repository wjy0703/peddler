<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/overDate/saveOverDate"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>出借编号：</label> ****</td>
					<td><label>出借人：</label> ****</td>
				</tr>
			</table>
			<div class="divider"></div>
			<table class="table" width="100%">
				<thead>
					<tr>
						<th width="80" orderField="cjrxm" class="asc">月份</th>
						<th width="80" orderField="khbm">应还本金</th>
						<th width="80">应还利息</th>
						<th width="80">可出借金额</th>
						<th width="80">还款金额</th>
						<th width="70">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr target="sid_user" rel="${user.id}">
						<td>2012-11</td>
						<td>${user.khbm }</td>
						<td>${user.gxcs }</td>
						<td>${user.employeeCrm.name }</td>
						<td>${user.employeeCca.name }</td>
						<td><a title="月报明细" target="navTab"
							href="${ctx }/lend/monthLendMoney/${user.id}">月报明细</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
