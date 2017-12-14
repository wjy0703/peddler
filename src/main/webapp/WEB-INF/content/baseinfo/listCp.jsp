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
		action="${ctx }/baseinfo/listCp" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>产品名称:</label> <input type="text"
					name="filter_tzcpMc" /></li>
				<li><label>产品状态:</label> <select class="combox"
					name="filter_sts">
						<option value="">全部</option>
						<option value="0">在用</option>
						<option value="1">历史</option>
				</select></li>
				<li><label>产品分类:</label> <select class="combox"
					name="filter_tzcpFl">
						<option value="">全部</option>
						<c:forEach items="${type}" var="type" varStatus="st">
							<option value="${type.value }">${type.name }</option>
						</c:forEach>
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
			<bjdv:validateContent type="1" funcId="产品管理-添加">
				<li><a class="add" href="${ctx}/baseinfo/addCp" target="navTab"><span>添加</span></a></li>
			</bjdv:validateContent>
			<bjdv:validateContent type="1" funcId="产品管理-修改">
				<li><a class="edit" href="${ctx}/baseinfo/editCp/{sid_cp}"
					target="navTab" warn="请选择一个产品"><span>修改</span></a></li>
			</bjdv:validateContent>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="70" align="center" orderField="tzcpBh" class="asc">产品编号</th>
				<th width="200" orderField="tzcpMc" class="asc">产品名称</th>
				<th width="70" orderField="tzcpLl">产品利率</th>
				<th width="200" orderField="tzcpMs">产品描述</th>
				<th width="80" align="center" orderField="tzcpFl">产品分类</th>
				<th width="80" align="center" orderField="tzcpLx">产品类型</th>
				<th width="70" align="center" orderField="tzcpZt">产品状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="cp" varStatus="st">
				<tr target="sid_cp" rel="${cp.id}">
					<td><input name="ids" value="${cp.id}" type="checkbox"></td>
					<td>${cp.tzcpBh }</td>
					<td>${cp.tzcpMc }</td>
					<td>${cp.tzcpLl }</td>
					<td>${cp.tzcpMs }</td>
					<td>${cp.tzcpFl }</td>
					<td>${cp.tzcpLx }</td>
					<td><c:if test="${cp.tzcpZt =='0'}">在用</c:if> <c:if
							test="${cp.tzcpZt=='1'}">历史</c:if></td>
					<td><bjdv:validateContent type="1" funcId="产品管理-删除">
							<a title="删除" target="ajaxTodo"
								href="${ctx }/baseinfo/delCp/${cp.id}" class="btnDel">删除</a>
						</bjdv:validateContent> <bjdv:validateContent type="1" funcId="产品管理-修改">
							<a title="编辑" target="navTab"
								href="${ctx }/baseinfo/editCp/${cp.id}" class="btnEdit">编辑</a>
						</bjdv:validateContent></td>
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
