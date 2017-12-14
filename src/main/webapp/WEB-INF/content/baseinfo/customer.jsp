<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/baseinfo/listcustomer" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>姓名:</label> <input type="text" name="filter_name" />
				</li>
				<li><label>状态:</label> <select class="combox" name="filter_sts">
						<option value="">全部</option>
						<option value="0">在用</option>
						<option value="1">历史</option>
				</select></li>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/baseinfo/addcustomer"
				target="navTab"><span>添加</span></a></li>
			<li><a class="edit"
				href="${ctx}/baseinfo/editcustomer/{sid_customer}" target="navTab"
				warn="请选择一个客户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80" orderField="name" class="asc">客户姓名</th>
				<th orderField="sex">姓别</th>
				<th orderField="pose">职务</th>
				<th orderField="mobile1">手机号码1</th>
				<th width="80" orderField="mobile2">手机号码2</th>
				<th width="100" orderField="tel1">电话1</th>
				<th width="100" orderField="tel2">电话2</th>
				<th width="100">邮箱</th>
				<th width="100">QQ号码</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="customer" varStatus="st">
				<tr target="sid_customer" rel="${customer.id}">
					<td><input name="ids" value="${customer.id}" type="checkbox"></td>
					<td>${customer.name }</td>
					<td><c:if test="${customer.sex=='F'}">男</c:if> <c:if
							test="${customer.sex=='M'}">女</c:if></td>
					<td>${customer.post }</td>
					<td>${customer.mobile1 }</td>
					<td>${customer.mobile2 }</td>
					<td>${customer.tel1 }</td>
					<td>${customer.tel2 }</td>
					<td>${customer.email }</td>
					<td>${customer.qq }</td>
					<td><a title="删除" target="ajaxTodo"
						href="${ctx }/baseinfo/delcustomer/${customer.id}" class="btnDel">删除</a>
						<a title="编辑" target="navTab"
						href="${ctx }/baseinfo/editcustomer/${customer.id}"
						class="btnEdit">编辑</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"
					<c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
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
</div>