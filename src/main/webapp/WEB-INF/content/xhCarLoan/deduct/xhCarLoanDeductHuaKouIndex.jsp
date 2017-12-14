<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhCarLoanDeduct/listXhCarLoanDeductHuaKouOver" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>客户姓名:</label> <input type="text"
					name="filter_userName" value="${map.userName}" /></li>

				<li><label>放款日期：</label> <input type="text"
					name="filter_makeLoanDate" class="date" format="yyyy-MM-dd"
					value="${map.makeLoanDate }" size="15" readonly="true" /><a
				class="inputDateButton" href="javascript:;">选择</a></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" layoutH="111" width="101%">
		<thead>
			<tr>
				<th width="50"  >序号</th>
				<th width="80">客户姓名</th>
				<th width="100">合同编号</th>
				<th width="80">合同金额</th>
				<th width="80">放款金额</th>
				<th width="80">放款账户</th>
				<th width="90">开户行</th>
				<th width="80">账号</th>
				<th width="80">线下放款日期</th>
				<th width="80">线上录入时间</th>
				<th width="70">操作人员</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${xhCarLoanMakeLoans}" var="makeLoan" varStatus="st">
				<tr target="sid_makeLaon" rel="${makeLaon.ID}">
					<td>${st.count}</td>
					<td>${makeLoan.USER_NAME}</td>
					<td>${makeLoan.CONTRACT_NUM}</td>
					<td>${makeLoan.CONTRACT_MONEY}</td>
					<td>${makeLoan.FKJE}</td>
					<td>${makeLoan.MIDDLE_NAME}</td>
					<td>${makeLoan.CRED_ADDR}</td>
					<td>${makeLoan.CRED_ID}</td>
					<td>${makeLoan.MAKE_LOAN_DATE}</td>
					<td>${makeLoan.CREATE_TIME}</td>
					<td>${makeLoan.CREATE_BY}</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
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
</div>
