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
	<form rel="pagerForm" onsubmit="return dialogSearch(this);"
		action="${ctx }/xhMonthlyDw/listAssetsAccountingInfo" method="post">
		<input type="hidden" name="filter_tzsq_id" value="${map.tzsq_id}" />
		<div class="searchBar">
			<ul class="searchContent">
				<table>
				<tr>
					<td>
					<label>账单日:</label> 
					<input type="text" name="filter_payDate" id="filter_payDate"
							class="date" format="yyyy-MM-dd" yearstart="-20"
							value="<fmt:formatDate value='${map.payDate}' pattern='yyyy-MM-dd' />" size="17" readonly="true" /> <a
							class="inputDateButton" href="javascript:;">选择</a> 
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
					<!-- 
						<label>出借状态:</label> 
						<select name="filter_lenderState" class="required combox"><option value="">请选择</option><<option value="1">出借中</option><option value="2">已到期</option></select>
					 -->
					</td>
				</tr>
				</table>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return resetDate('filter_payDate');">检索</button>
							</div>
						</div></li>
					<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="5%" align="center">编号</th>
				<th width="10%">出借人</th>
				<th width="20%">出借人身份证号</th>
				<th width="20%">月资产本金</th>
				<th width="20%">月资产利息</th>
				<th width="20%">账单日</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${xhMonthlyDwList}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td>${st.index + 1}</td>
					<td>${user.LENDER_NAME}</td>
					<td>${user.LENDER_ID_CARD}</td>
					<td>￥<fmt:formatNumber value="${user.ADD_UP_MONEY}" pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.ADD_UP_INTEREST}" pattern="#,#00.00" /></td>
					<td>${user.PAY_DATE}</td>
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
