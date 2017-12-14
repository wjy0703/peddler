<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/account/listuser" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>用户名:</label> <input type="text"
					name="filter_loginName" /></li>
				<li>
				<label>机构:</label>
					<input id="organiId" name="filter_organi.id" type="hidden" value="${organiId}"/>
					<input name="filter_organi.name" type="text" value="${organiName }" onblur="cleanMd(this.value)"/>
					<a href="${ctx }/baseinfo/getTreeDept" lookupGroup="filter_organi" style=""><span style="color: #7F7F7F;">选择机构</span></a>
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
			<li><a class="add" href="${ctx}/account/adduser" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx }/account/batchdeluser" class="delete"
				warn="请至少选择一个用户"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/account/edituser/{sid_user}"
				target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li><a target="selectedTodo" rel="ids" warn="请至少选择一个用户"
				 href="${ctx }/account/resetPass" class="delete"
				><span>重置密码</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80" orderField="loginName" class="asc">用户名</th>
				<th orderField="employee.name">员工姓名</th>
				<th orderField="employee.dept.name">所属机构</th>
				<th width="80" orderField="sts">状态</th>
				<th width="100" orderField="createBy">创建人</th>
				<th width="100" orderField="createTime">创建日期</th>
				<th width="100">修改人</th>
				<th width="100">最后修改日期</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.loginName }</td>
					<td>${user.employee.name }</td>
					<td>${user.employee.organi.rganiName }</td>
					<td><c:if test="${user.sts=='0'}">在用</c:if> <c:if
							test="${user.sts=='1'}">历史</c:if></td>
					<td>${user.createBy }</td>
					<td>${user.createTime }</td>
					<td>${user.lastModifyBy }</td>
					<td>${user.lastModifyTime }</td>
					<td><a title="删除" target="ajaxTodo"
						href="${ctx }/account/deluser/${user.id}" class="btnDel">删除</a> <a
						title="编辑" target="navTab"
						href="${ctx }/account/edituser/${user.id}" class="btnEdit">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
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
