<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.peddler.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/businessinfo/listBusinessinfo" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>公司账户:</label>
				<input type="text" name="filter_busiaccount" value="${map.busiaccount}"/>
			</li>
			<li>
				<label>公司名:</label>
				<input type="text" name="filter_businame" value="${map.businame}"/>
			</li>
			<li>
				<label>法人:</label>
				<input type="text" name="filter_corporation" value="${map.corporation}"/>
			</li>
			<li>
				<label>证件号码:</label>
				<input type="text" name="filter_card" value="${map.card}"/>
			</li>
			<li>
				<label>联系方式:</label>
				<input type="text" name="filter_phone" value="${map.phone}"/>
			</li>
			<li>
				<label>创建时间:</label>
				<input type="text" name="filter_createtime" value="${map.createtime}"/>
			</li>
			<li>
				<label>修改时间:</label>
				<input type="text" name="filter_modifytime" value="${map.modifytime}"/>
			</li>
			<li>
				<label>创建人:</label>
				<input type="text" name="filter_createuser" value="${map.createuser}"/>
			</li>
			<li>
				<label>修改人:</label>
				<input type="text" name="filter_modifyuser" value="${map.modifyuser}"/>
			</li>
			<li>
				<label>属性（在用、欠费、停用）:</label>
				<input type="text" name="filter_vtypes" value="${map.vtypes}"/>
			</li>
			<li>
				<label>套餐类型:</label>
				<input type="text" name="filter_tctypes" value="${map.tctypes}"/>
			</li>
			<li>
				<label>生效时间:</label>
				<input type="text" name="filter_starttime" value="${map.starttime}"/>
			</li>
			<li>
				<label>到期时间:</label>
				<input type="text" name="filter_overtime" value="${map.overtime}"/>
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
			<li><a class="add" href="${ctx}/businessinfo/addBusinessinfo" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/businessinfo/batchdelBusinessinfo" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/businessinfo/editBusinessinfo/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="busiaccount" class="asc">公司账户</th>
				<th width="80" orderField="businame" class="asc">公司名</th>
				<th width="80" orderField="corporation" class="asc">法人</th>
				<th width="80" orderField="card" class="asc">证件号码</th>
				<th width="80" orderField="phone" class="asc">联系方式</th>
				<th width="80" orderField="createtime" class="asc">创建时间</th>
				<th width="80" orderField="modifytime" class="asc">修改时间</th>
				<th width="80" orderField="createuser" class="asc">创建人</th>
				<th width="80" orderField="modifyuser" class="asc">修改人</th>
				<th width="80" orderField="vtypes" class="asc">属性（在用、欠费、停用）</th>
				<th width="80" orderField="tctypes" class="asc">套餐类型</th>
				<th width="80" orderField="starttime" class="asc">生效时间</th>
				<th width="80" orderField="overtime" class="asc">到期时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.busiaccount}</td>
				<td>${user.businame}</td>
				<td>${user.corporation}</td>
				<td>${user.card}</td>
				<td>${user.phone}</td>
				<td>${user.createtime}</td>
				<td>${user.modifytime}</td>
				<td>${user.createuser}</td>
				<td>${user.modifyuser}</td>
				<td>${user.vtypes}</td>
				<td>${user.tctypes}</td>
				<td>${user.starttime}</td>
				<td>${user.overtime}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/businessinfo/delBusinessinfo/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/businessinfo/editBusinessinfo/${user.id}" class="btnEdit">编辑</a>
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
