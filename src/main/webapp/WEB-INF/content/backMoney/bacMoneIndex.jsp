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
		action="${ctx }/hkgl/listhkgl" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>借款编码:</label> <input type="text" name="filter_jkbm" />
					</td>
					<td><label>借款人:</label> <input type="text" name="filter_jkr" />
					</td>
					<td><label>团队经理:</label> <input type="text" name="filter_tdjl" />
					</td>
				</tr>
				<tr>
					<td><label>客户经理:</label> <input type="text" name="filter_kfjl" />
					</td>
					<td><label>回款日期:</label> <input type="text" name="filter_tzrq" />
					</td>
					<td><label>至</label> <input type="text" name="filter_tzrq1" />
					</td>
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
			<li><a class="add" href="${ctx}/hkgl/addhkgl" target="navTab"><span>新增回款</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">借款编码</th>
				<th width="80" class="asc">借款人</th>
				<th width="80">回款日期</th>
				<th width="80">回款金额</th>
				<th width="80">回款账户</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="hkgl" varStatus="st">
				<tr target="sid_hkgl" rel="${hkgl.id}">
					<td>${hkgl.zhbm }</td>
					<td>${user.khbm }</td>
					<td>${hkgl.sjhkrq }</td>
					<td>${hkgl.qkje }</td>
					<td>${hkgl.zh }</td>
					<td><a title="查看" target="navTab"
						href="${ctx }/hkgl/viewfkgl/${hkgl.id}">查看</a></td>
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
