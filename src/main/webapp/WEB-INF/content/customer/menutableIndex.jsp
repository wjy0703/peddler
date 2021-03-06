<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.peddler.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/role/listMenu" method="post">
		<div class="searchBar">
		<table width="80%">
				<tr>
				<td><label>菜单名称：</label> <input type="text"
					name="filter_menuname" value="${map.menuname}"/></td>
				<td><label>菜单状态：</label> 
				<sen:select name="filter_vtypes" coding="vtypes" clazz="combox" title="全部" value="${map.vtypes}" />
					</td>
				<td><label>菜单类型：</label> 
				<sen:select name="filter_menutype" coding="menutype" clazz="combox" title="全部" value="${map.menutype}" />
					</td>
					<td><label>系统类型：</label> 
				<sen:select name="filter_vsystype" coding="systype" clazz="combox" title="全部" value="${map.vsystype}" />
					</td>
					</tr>
				<tr>
					<td>
					</td>
					<td>
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
					</td>
					<td>
					</td>
					<td>
			</td>
			</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<!-- <li><a class="add" href="${ctx}/role/addMenu" target="navTab"><span>添加</span></a></li>  -->
			<li><a class="edit" href="${ctx}/role/editMenu/{sid_menu}"
				target="navTab" warn="请选择一个产品"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="200" orderField="menuname" class="asc">菜单名称</th>
				<th width="100" orderField="menuurl">URL</th>
				<th width="100" align="center" orderField="menuType">菜单类型</th>
				<th width="80" align="center" orderField="title">菜单标题</th>
				<th width="80" align="center" orderField="sts">菜单状态</th>
				<th width="40">系统属性</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="menu" varStatus="st">
				<tr target="sid_menu" rel="${menu.id}">
					<td><input name="ids" value="${menu.id}" type="checkbox"></td>
					<td>${menu.menuname }</td>
					<td>${menu.menuurl }</td>
					<td><sen:vtoName coding="menutype" value="${menu.menutype}"/></td>
					<td>${menu.title }</td>
					<td><sen:vtoName coding="vtypes" value="${menu.vtypes}"/></td>
					<td><sen:vtoName coding="systype" value="${menu.vsystype}"/></td>
					<td>
						<!-- <a title="删除" target="ajaxTodo" href="${ctx }/role/delCp/${menu.id}" class="btnDel">删除</a> -->
						<a title="编辑" target="navTab"
						href="${ctx }/role/editMenu/${menu.id}" class="btnEdit">编辑</a>
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
