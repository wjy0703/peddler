<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/baseinfo/listMenu" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>菜单名称:</label> <input type="text"
					name="filter_menuName" /></li>
				<li><label>菜单状态:</label> <select class="combox"
					name="filter_sts">
						<option value="">全部</option>
						<option value="0">在用</option>
						<option value="1">历史</option>
				</select></li>
				<li><label>菜单类型:</label> <select class="combox"
					name="filter_menuType">
						<option value="">全部</option>
						<option value="0">一级菜单</option>
						<option value="1">二级菜单</option>
						<option value="2">三级菜单</option>
						<option value="3">四级菜单</option>
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
			<!-- <li><a class="add" href="${ctx}/baseinfo/addCp" target="navTab"><span>添加</span></a></li> -->
			<li><a class="edit" href="${ctx}/baseinfo/editMenu/{sid_menu}"
				target="navTab" warn="请选择一个产品"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="200" orderField="menuName" class="asc">菜单名称</th>
				<th width="100" orderField="menuUrl">URL</th>
				<th width="100" align="center" orderField="menuType">菜单类型</th>
				<th width="80" align="center" orderField="title">菜单标题</th>
				<th width="80" align="center" orderField="sts">菜单状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="menu" varStatus="st">
				<tr target="sid_menu" rel="${menu.id}">
					<td><input name="ids" value="${menu.id}" type="checkbox"></td>
					<td>${menu.menuName }</td>
					<td>${menu.menuUrl }</td>
					<td><c:if test="${menu.menuType =='0'}">一级菜单</c:if> <c:if
							test="${menu.menuType =='1'}">二级菜单</c:if> <c:if
							test="${menu.menuType =='2'}">三级菜单</c:if> <c:if
							test="${menu.menuType =='3'}">四级菜单</c:if></td>
					<td>${menu.title }</td>
					<td><c:if test="${menu.sts =='0'}">在用</c:if> <c:if
							test="${menu.sts=='1'}">历史</c:if></td>
					<td>
						<!-- <a title="删除" target="ajaxTodo" href="${ctx }/baseinfo/delCp/${menu.id}" class="btnDel">删除</a> -->
						<a title="编辑" target="navTab"
						href="${ctx }/baseinfo/editMenu/${menu.id}" class="btnEdit">编辑</a>
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
