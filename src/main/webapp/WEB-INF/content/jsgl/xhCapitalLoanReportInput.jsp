<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="formBar">
	<ul>
		<!-- <li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">导出资金出借报告</button>
					</div>
				</div></li> -->
		<li>
			<div class="button">
				<div class="buttonContent">
					<button type="button" class="close">返回</button>
				</div>
			</div>
		</li>
	</ul>
</div>

<div class="panelBar">
	<ul class="toolBar">

		<li><a class="icon"
			href="xhCapitalLoanReport/makeCapitalLoanReportFile/${xhCapitalLoanReport.id}"
			title="导出资金出借报吗?" target="ajaxTodo"><span>导出资金出借报告</span></a></li>
	</ul>
</div>
<div class="panel">

	<h1>目前出借的款项所产生的收益情况</h1>
	<div class="pageContent">
		<form method="post"
			action="${ctx}/xhCapitalLoanReport/saveXhCapitalLoanReport"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhCapitalLoanReport.id}" />

			<div class="pageFormContent">


				<p>
					<label>客户姓名：</label> <input name="lenderName" type="text" size="30"
						value="${xhCapitalLoanReport.lenderName}" class="" maxlength="64"
						readonly />
				</p>

				<p>
					<label>报告周期：</label> <input name="reportCycle" type="text"
						size="30" value="${xhCapitalLoanReport.reportCycle}" class=""
						maxlength="64" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>账户级别：</label>
					<c:if test="${xhCapitalLoanReport.accountLevel=='1'}">
						<input name="accountLevel" type="text" size="30" value="信和账户"
							class="" maxlength="64" readonly />
					</c:if>
				</p>
				<p>
					<label>报告日：</label> <input name="reportDate" type="text" size="30"
						value="<fmt:formatDate value="${xhCapitalLoanReport.reportDate}"
							pattern="yyyy/MM/dd" />"
						class="" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>客户编号：</label> <input name="lenderNumber" type="text"
						size="30" value="${xhCapitalLoanReport.lenderNumber}" class=""
						maxlength="64" readonly />
				</p>
				<p>
					<label>报告日资产总额：</label> <input name="allMoneyOfCurrent" type="text"
						size="30"
						value="￥<fmt:formatNumber value='${Current_All_Money}' pattern='#,#00.00' />"
						class="" maxlength="22" readonly />
				</p>


			</div>

		</form>
	</div>
</div>
<div class="panel">
	<h1>目前的每笔出借款项实际回收情况及出借收益</h1>

	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="28">序号</th>

				<th width="80">出借编号</th>
				<th width="80">初始出借日期</th>
				<th width="80">出借及回收方式</th>
				<th width="80">初始出借金额</th>
				<th width="80">本期应还金额</th>
				<th width="80">本期实际还款金额</th>
				<th width="80">延迟支付金额</th>
				<th width="80">账户管理费率</th>
				<th width="80">账户管理费</th>
				<th width="80">当期受让金额</th>
				<th width="80">当期回收金额</th>
				<th width="80">当前全部账户资产</th>


			</tr>
		</thead>
		<tbody>


			<c:forEach items="${map}" var="entry" varStatus="st">
				<tr target="sid_user" rel="">
					<td>${st.count}</td>

					<td>${entry.key}</td>
					<td><fmt:formatDate value="${entry.value.firstLendDate}"
							pattern='yyyy-MM-dd' /></td>
					<td>${entry.value.lendType}</td>

					<td>￥<fmt:formatNumber value="${entry.value.firstLendMoney}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.shoudBack}"
							pattern="#,#00.00" /></td>

					<td>￥<fmt:formatNumber value="${entry.value.realBack}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.latePayMoney}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.mngFeeRate}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.mngFee}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.reLend}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.drawing}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${entry.value.allMoney}"
							pattern="#,#00.00" /></td>

					<!-- 
					<td><fmt:formatDate value="${user.firstLendDate}"
							pattern="yyyy/MM/dd" /></td>
					<td>${user.lendType}</td>
					<td>￥<fmt:formatNumber value="${user.firstLendMoney}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.shoudBack}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.realBack}"
							pattern="#,#00.00" /></td>
					<td>${user.latePayMoney}</td>
					<td>${user.mngFeeRate}</td>
					<td>${user.mngFee}</td>
					<td>${user.reLend}</td>
					<td>${user.drawing}</td>
					<td>${user.allMoney}</td> -->

				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>