<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhZqtjDetails/listXhZqtjDetails" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>债权推荐ID:</label>
				<input type="text" name="filter_zqtjId" value="${map.zqtjId}"/>
			</li>
			<li>
				<label>债权推荐ID:</label>
				<input type="text" name="filter_zqtjId" value="${map.zqtjId}"/>
			</li>
			<li>
				<label>资金:</label>
				<input type="text" name="filter_money" value="${map.money}"/>
			</li>
			<li>
				<label>资金:</label>
				<input type="text" name="filter_money" value="${map.money}"/>
			</li>
			<li>
				<label>还款周期:</label>
				<input type="text" name="filter_hkzq" value="${map.hkzq}"/>
			</li>
			<li>
				<label>还款周期:</label>
				<input type="text" name="filter_hkzq" value="${map.hkzq}"/>
			</li>
			<li>
				<label>可用债权价值ID:</label>
				<input type="text" name="filter_kyzqjzId" value="${map.kyzqjzId}"/>
			</li>
			<li>
				<label>可用债权价值ID:</label>
				<input type="text" name="filter_kyzqjzId" value="${map.kyzqjzId}"/>
			</li>
			<li>
				<label>债权持有比例:</label>
				<input type="text" name="filter_zqcybi" value="${map.zqcybi}"/>
			</li>
			<li>
				<label>债权持有比例:</label>
				<input type="text" name="filter_zqcybi" value="${map.zqcybi}"/>
			</li>
			<li>
				<label>剩余期数:</label>
				<input type="text" name="filter_hkzqSy" value="${map.hkzqSy}"/>
			</li>
			<li>
				<label>剩余期数:</label>
				<input type="text" name="filter_hkzqSy" value="${map.hkzqSy}"/>
			</li>
			<li>
				<label>剩余资金:</label>
				<input type="text" name="filter_moneySy" value="${map.moneySy}"/>
			</li>
			<li>
				<label>剩余资金:</label>
				<input type="text" name="filter_moneySy" value="${map.moneySy}"/>
			</li>
			<li>
				<label>每月还本金:</label>
				<input type="text" name="filter_moneyMonth" value="${map.moneyMonth}"/>
			</li>
			<li>
				<label>每月还本金:</label>
				<input type="text" name="filter_moneyMonth" value="${map.moneyMonth}"/>
			</li>
			<li>
				<label>每月还利息:</label>
				<input type="text" name="filter_zqlixiMonth" value="${map.zqlixiMonth}"/>
			</li>
			<li>
				<label>每月还利息:</label>
				<input type="text" name="filter_zqlixiMonth" value="${map.zqlixiMonth}"/>
			</li>
			<li>
				<label>每月还利息(首个):</label>
				<input type="text" name="filter_zqlixiMonthSg" value="${map.zqlixiMonthSg}"/>
			</li>
			<li>
				<label>每月还利息(首个):</label>
				<input type="text" name="filter_zqlixiMonthSg" value="${map.zqlixiMonthSg}"/>
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
			<li><a class="add" href="${ctx}/xhZqtjDetails/addXhZqtjDetails" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhZqtjDetails/batchdelXhZqtjDetails" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhZqtjDetails/editXhZqtjDetails/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="zqtjId" class="asc">债权推荐ID</th>
				<th width="80" orderField="money" class="asc">资金</th>
				<th width="80" orderField="money" class="asc">资金</th>
				<th width="80" orderField="hkzq" class="asc">还款周期</th>
				<th width="80" orderField="hkzq" class="asc">还款周期</th>
				<th width="80" orderField="kyzqjzId" class="asc">可用债权价值ID</th>
				<th width="80" orderField="kyzqjzId" class="asc">可用债权价值ID</th>
				<th width="80" orderField="zqcybi" class="asc">债权持有比例</th>
				<th width="80" orderField="zqcybi" class="asc">债权持有比例</th>
				<th width="80" orderField="hkzqSy" class="asc">剩余期数</th>
				<th width="80" orderField="hkzqSy" class="asc">剩余期数</th>
				<th width="80" orderField="moneySy" class="asc">剩余资金</th>
				<th width="80" orderField="moneySy" class="asc">剩余资金</th>
				<th width="80" orderField="moneyMonth" class="asc">每月还本金</th>
				<th width="80" orderField="moneyMonth" class="asc">每月还本金</th>
				<th width="80" orderField="zqlixiMonth" class="asc">每月还利息</th>
				<th width="80" orderField="zqlixiMonth" class="asc">每月还利息</th>
				<th width="80" orderField="zqlixiMonthSg" class="asc">每月还利息(首个)</th>
				<th width="80" orderField="zqlixiMonthSg" class="asc">每月还利息(首个)</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.zqtjId}</td>
				<td>${user.money}</td>
				<td>${user.money}</td>
				<td>${user.hkzq}</td>
				<td>${user.hkzq}</td>
				<td>${user.kyzqjzId}</td>
				<td>${user.kyzqjzId}</td>
				<td>${user.zqcybi}</td>
				<td>${user.zqcybi}</td>
				<td>${user.hkzqSy}</td>
				<td>${user.hkzqSy}</td>
				<td>${user.moneySy}</td>
				<td>${user.moneySy}</td>
				<td>${user.moneyMonth}</td>
				<td>${user.moneyMonth}</td>
				<td>${user.zqlixiMonth}</td>
				<td>${user.zqlixiMonth}</td>
				<td>${user.zqlixiMonthSg}</td>
				<td>${user.zqlixiMonthSg}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhZqtjDetails/delXhZqtjDetails/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhZqtjDetails/editXhZqtjDetails/${user.id}" class="btnEdit">编辑</a>
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
