<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhMonthlyDwInfo/listXhMonthlyDwInfo" method="post">
	<div class="searchBar">
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28">序号</th>
				<th width="80"  >借款人名称</th>
				<th width="80"  >本月返款金额</th>
				<th width="80"  >月本金</th>
				<th width="80"  >月利息</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td>${st.count}</td>
			<td>${user.loanName}</td>
				<td>￥<fmt:formatNumber value="${user.money+user.interest}" pattern="#,#00.00" /></td>
				
				<td>￥<fmt:formatNumber value="${user.money}"  pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber value="${user.interest}" pattern="#,#00.00" /></td>
				
			</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
				<option value="10" <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20" <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50" <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>
