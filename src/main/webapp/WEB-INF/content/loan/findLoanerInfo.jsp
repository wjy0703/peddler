<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" action="${ctx }/baseinfo/emplookup">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx }/baseinfo/emplookup"
		onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>借款人姓名:</label> <input class="textInput"
					name="filter_name" value="" type="text"></li>
				<li><label>部门名称:</label> <input class="textInput"
					name="filter_organi.rganiName" value="" type="text"></li>
			</ul>
			<ul class="searchContent">
				<li><label>员工状态:</label> <select class="combox"
					name="filter_sts">
						<option value="">全部</option>
						<option value="0">在用</option>
						<option value="1">历史</option>
				</select></li>
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
				<th orderfield="name">借款人姓名</th>
				<th>部门名称</th>
				<th orderfield="mobile">手机号码</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="emp" varStatus="st">
				<tr>
					<td>${emp.jkrxm }</td>
					<td>${emp.csrq }</td>
					<td>${emp.dzyx }</td>
					<td><a class="btnSelect"
						href="javascript:$.bringBack({id:'${emp.id}', jkrxm:'${emp.jkrxm }', dzyx:'${emp.dzyx }'})"
						title="查找带回">选择</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select name="numPerPage"
				onchange="dwzPageBreak({targetType:dialog, numPerPage:'10'})">
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