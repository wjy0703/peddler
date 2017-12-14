<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="pageHeader">
	<form method="post" action="${ctx}/match/xhFindAvaliableValue"
		onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<table class="searchContent" width="100%">
				<tr>
					<td><label>借款人申请编号:</label> <input class="textInput"
						name="filter_loanCode" value="" type="text"></td>
					<td><label>未推荐金额:</label> <input class="textInput" name=""
						value="" type="text" disabled="disabled"></td>
					<td><label>出借周期:</label> <input class="textInput" name=""
						value="" type="text" disabled="disabled"></td>
				</tr>
				<tr>
					<td><label>借款人姓名:</label> <input class="textInput"
						name="filter_jkrxm" value="" type="text"></td>
					<td><label>剩余还款月数:</label> <input class="textInput"
						name="filter_syhkysMin" value="" type="text"></td>
					<td><label>至</label> <input class="textInput"
						name="filter_syhkysMax" value="" type="text"></td>
				</tr>
				<tr>
					<td><label>剩余推荐金额:</label> <input class="textInput"
						name="filter_sytjjeMin" value="" type="text"></td>
					<td><label>至</label> <input class="textInput"
						name="filter_sytjjeMax" value="" type="text"></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div></li>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" class="close" warn="请选择债权"
									onclick="onCliMs(ids)">选择带回</button>
							</div>
						</div></li>


				</ul>
			</div>
		</div>
	</form>
</div>

<div class="panel">


	<form action="${ctx}/match/bringBackLoanIds" method="post"
		name="bringBackForm" class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">

		<table class="table" layoutH="168" targetType="dialog" width="100%">
			<thead>
				<tr>
					<th width="28"><input type="checkbox" group="ids"
						class="checkboxCtrl" />
					<th width="50" class="">借款人</th>

					<th width="80" class="">借款方式</th>
					<th width="60" class="">职业</th>
					<!--	<th width="72" class="">放款日</th>-->
					<th width="80" class="">首次还款日</th>
					<th width="60" class="">还款日</th>
					<!--<th width="100" class="">初始借款金额</th> -->
					<th width="80" class="">应还款期数</th>
					<th width="100" class="">剩余还款期数</th>
					<th width="80" class="">截止还款日期</th>
					<th width="60" class="">月利率</th>
					<th width="100" class="">原始债权价值</th>
					<th width="80" class="">可用债权价值</th>
					<th width="80" class="">债权人</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="user" varStatus="st">
					<tr>
						<td><input name="ids"
							value="${user.id}|${user.loanApply.jkType=='A'}|${user.loanApply.companyNature}|${user.loanContract.qshkrq}|${fn:substring(user.loanContract.qshkrq,8,10)}|${user.availabelMonth}|<fmt:formatDate value="${user.lastBackMoneyDate}"
								pattern="yyyy-MM-dd" /><fmt:formatNumber value="${user.loanContract.dkll/100}"
								type="percent" minFractionDigits="2" />|<fmt:formatNumber value="${user.loanContract.htje}"
								pattern="#,#00.00" />|<fmt:formatNumber value="${user.availableValue}"
								pattern="#,#00.00" />|${user.makeLoan.middleMan.middleManName}"
							type="checkbox"></td>
						<td>${user.loanApply.jkrxm}</td>

						<td><c:if test="${user.loanApply.jkType=='A'}">老板借</c:if> <c:if
								test="${user.loanApply.jkType=='B'}">老板楼易借</c:if> <c:if
								test="${user.loanApply.jkType=='C'}">薪水借</c:if> <c:if
								test="${user.loanApply.jkType=='D'}">薪水楼易借</c:if> <c:if
								test="${user.loanApply.jkType=='E'}">精英借</c:if></td>
						<td>${user.loanApply.companyNature}</td>
						<!--	<td><fmt:formatDate
							value="${user.makeLoan.makeLoanDate}" pattern="yyyy-MM-dd" /></td>-->
						<td>${user.loanContract.qshkrq}</td>
						<td>${fn:substring(user.loanContract.qshkrq,8,10)}</td>
						<!-- <td>${user.loanContract.htje}</td> -->
						<td>${user.loanContract.hkqs}</td>
						<td>${user.availabelMonth}</td>
						<td><fmt:formatDate value="${user.lastBackMoneyDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatNumber value="${user.loanContract.dkll/100}"
								type="percent" minFractionDigits="2" /></td>
						<td><fmt:formatNumber value="${user.loanContract.htje}"
								pattern="#,#00.00" /></td>
						<td><fmt:formatNumber value="${user.availableValue}"
								pattern="#,#00.00" /></td>
						<td>${user.makeLoan.middleMan.middleManName}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="panelBar">
			<div class="pages">
				<span>每页显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="2"
						<c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
					<option value="10"
						<c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
					<option value="20"
						<c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
					<option value="50"
						<c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
					<option value="100"
						<c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
					<option value="200"
						<c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
				</select> <span>条，共${page.totalCount}条</span>
			</div>

			<div class="pagination" targetType="navTab"
				totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
				pageNumShown="10" currentPage="${page.pageNo }"></div>
		</div>
	</form>
</div>





