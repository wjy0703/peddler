<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx }/baseinfo/emplookupByPosition/${params.position_id}"
		onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<!-- 			<li> -->
				<!-- 				<label>公司名称:</label> -->
				<!-- 				<input class="textInput" name="filter_company.name" value="" type="text"> -->
				<!-- 			</li>	  -->
				<li><label>员工姓名:</label> <input class="textInput"
					name="filter_name" value="${params.name}" type="text"></li>
				<li><label>部门名称:</label> <input class="textInput"
					name="filter_organi.rganiName" value="" type="text"></li>
			</ul>
			
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="135" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="name">员工姓名</th>
				<th>部门名称</th>
				<th orderfield="mobile">手机号码</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="emp" varStatus="st">
				<tr>
					<td>${emp.name }</td>
					<td>${emp.organi.rganiName }</td>
					<td>${emp.mobile }</td>
					<td><a class="btnSelect"
						href="javascript:$.bringBack({id:'${emp.id}', lookId:'${emp.organi.id }', name:'${emp.name }', deptname:'${emp.organi.rganiName }'})"
						title="查找带回">选择</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select name="numPerPage"
				onchange="dwzPageBreak({targetType:'dialog',numPerPage:this.value})">
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
		<div class="pagination" targetType="dialog"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }"></div>
	</div>
</div>