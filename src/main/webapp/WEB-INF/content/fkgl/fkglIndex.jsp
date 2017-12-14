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
		action="${ctx }/fkgl/listfkgl" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>借款编码:</label> <input type="text"
						name="filter_cjrbh" /></td>
					<td><label>借款人:</label> <input type="text" name="filter_jkr" />
					</td>
					<td><label>门店:</label> <input type="text" name="filter_md" />
					</td>
				</tr>
				<tr>
					<td><label>客户经理:</label> <input type="text" name="filter_hujl" />
					</td>
					<td><label>付款日期:</label> <input type="text" name="filter_fkrq"
						class="date" pattern="yyyy-MM-dd" />至 <input type="text"
						name="filter_fkrq1" class="date" pattern="yyyy-MM-dd" /></td>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/fkgl/addfkgl" target="navTab"><span>新增付款</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" orderField="khbm">借款编码</th>
				<th width="80" orderField="cjrxm" class="asc">借款人</th>
				<th width="80">门店</th>
				<th width="80">客户经理</th>
				<th width="80">付款日期</th>
				<th width="80">付款金额</th>
				<th width="80">付款账户</th>
				<th width="80">付款类型</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="fkgl" varStatus="st">
				<tr target="sid_user" rel="${fkgl.id}">
					<td>${fkgl.cjrbh }</td>
					<td>${user.jkr }</td>
					<td>${user.md }</td>
					<td>${user.khjl }</td>
					<td>${fkgl.sjfkrq }</td>
					<td>${fkgl.sjyfje }</td>
					<td>${fkgl.zh }</td>
					<td><c:if test="${fkgl.fklx=='0'}">正常付款</c:if> <c:if
							test="${fkgl.fklx=='1'}">保障金付款</c:if></td>

					<td><a class="button" title="查看" target="navTab"
						href="${ctx }/fkgl/viewfkgl/${fkgl.id}"><span>查看</span></a></td>
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
