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
		action="${ctx }/xhNewJksq/listLoanTask" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<!-- <li><label>团队经理:</label> <input type="text"
					name="filter_loginName" /></li> -->
				<li><label>状态:</label> <select class="combox" name="filter_sts">
						<option value="">全部</option>
						<option value="0">正在处理</option>
						<option value="1">完成处理</option>
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
			<li><a class="add" href="${ctx}/xhNewJksq/addLoanTask" target="navTab"><span>任务交割</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="80" align="center">编号</th>
				<th width="80">客户姓名</th>
				<th width="80">团队经理</th>
				<th width="80">团队经理（新）</th>
				<th width="80">客户经理</th>
				<th width="80">客户经理（新）</th>
				<th width="100">交割条数</th>
				<th width="100">状态</th>
				<th width="100">操作时间</th>
				<th width="100">操作人</th>
				<th width="200">原因</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listLoanTask}" var="loan" varStatus="st">
				<tr>
					<td>${st.index + 1 }</td>
					<td>${loan.CUSTOMER_NAME }</td>
					<td>${loan.TEAM_LEADER_OLD }</td>
					<td>${loan.TEAM_LEADER_NEW }</td>
					<td>${loan.CUSTOMER_LEADER_OLD }</td>
					<td>${loan.CUSTOMER_LEADER_NEW }</td>
					<td>${loan.DELIV_COUNT }</td>
					<td>
					<c:if test="${loan.STS=='0'}">正在处理</c:if>
					<c:if test="${loan.STS=='1'}">完成处理</c:if>
					</td>
					<td>${loan.CREATE_TIME }</td>
					<td>${loan.CREATE_BY }</td>
					<td>${loan.REASON_INFO }</td>
					<td>
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
