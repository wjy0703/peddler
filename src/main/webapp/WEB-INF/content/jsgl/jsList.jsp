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
		action="${ctx }/tzsq/listTzsq" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>年度:</label> <select class="combox"
						name="filter_year" ref="filter_year"
						refUrl="${ctx}/cjrxx/getCity?code={value}">

							<option value="2012" selected>2012</option>
							<option value="2011" selected>2011</option>
					</select></td>
					<td><label>月度:</label> <select class="combox"
						name="filter_month" ref="filter_month"
						refUrl="${ctx}/cjrxx/getCity?code={value}">

							<option value="1" selected>1月份</option>
							<option value="2" selected>2月份</option>
					</select></td>

				</tr>

			</table>
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

	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" orderField="cjrxm" class="asc">年度</th>
				<th width="80" orderField="khbm">月度</th>

				<th width="80">账单日</th>
				<th width="100">执行状态</th>

				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="${user.id}">
				<td>2012</td>
				<td>10</td>
				<td>15</td>
				<td>执行完毕</td>
				<td><a title="查看" target="navTab" href="">执行</a>&nbsp; <a
					title="查看" target="navTab" href="">重新执行</a></td>
			</tr>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td>${user.cjrxm }</td>
					<td>${user.khbm }</td>
					<td>${user.gxcs }</td>
					<td>${user.employeeCrm.name }</td>
					<td>${user.employeeCca.name }</td>
					<td><c:if test="${user.ztFlag=='0'}">在用</c:if> <c:if
							test="${user.ztFlag=='1'}">历史</c:if></td>
					<td>${user.cjryx }</td>
					<td>${user.employeeCrm.name }</td>
					<td>${user.createTime }</td>
					<td><a title="查看" target="navTab"
						href="${ctx }/tzsq/lookOutTzsq/${user.id}">查看</a></td>
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
