<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/basePosition/listBasePosition" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			
			<li>
				<label>职务名称:</label>
				<input type="text" name="filter_positionName" value="${map.positionName}"/>
			</li>
		
			
			<li>
				<label>职务权限级别:</label>
				<input type="text" name="filter_positionCode" value="${map.positionCode}"/>
			</li>
			
			<li>
				<label>职级标准:</label>
				<input type="text" name="filter_positionLevel" value="${map.positionLevel}"/>
			</li>
			<li>
				<label>职级英文编码:</label>
				<input type="text" name="filter_positionLevelCode" value="${map.positionLevelCode}"/>
			</li>
			<li>
				<label>职级VALUE:</label>
				<input type="text" name="filter_positionLevelValue" value="${map.positionLevelValue}"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/basePosition/addBasePosition" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/basePosition/batchdelBasePosition" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/basePosition/editBasePosition/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			
				
				
				<th width="80" orderField="positionName" class="asc">职务名称</th>
				
				<th width="80" orderField="positionCode" class="asc">职务权限级别</th>
				<th width="80" orderField="positionLevel" class="asc">职级标准</th>
			
				<th width="80" orderField="positionLevelCode" class="asc">职级英文编码</th>
				<th width="80" orderField="positionLevelValue" class="asc">职级VALUE</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
			
				
				<td>${user.positionName}</td>
	
			
				<td>${user.positionCode}</td>
	
				<td>${user.positionLevel}</td>
				<td>${user.positionLevelCode}</td>
				<td>${user.positionLevelValue}</td>
				<td>
				<!--  	<a title="删除" target="ajaxTodo" href="${ctx }/basePosition/delBasePosition/${user.id}" class="btnDel">删除</a>-->
					<a title="编辑" target="navTab" href="${ctx }/basePosition/editBasePosition/${user.id}" class="btnEdit">编辑</a>
				</td>
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
