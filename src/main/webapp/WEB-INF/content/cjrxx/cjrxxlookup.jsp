<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="${ctx }/cjrxx/cjrxxLookUp">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dwzSearch(this, 'dialog');"
		action="${ctx }/cjrxx/cjrxxLookUp" method="post">
		<div class="pageFormContent">
			<table>
				<tr>
					<td><label>客户编号：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
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
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="165">
		<thead>
			<tr>
				<th width="80" orderField="cjrxm" class="asc">客户名</th>
				<th width="80" orderField="khbm">客户编码</th>
				<th width="80">证件号码</th>
				<th width="80">状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listCjrxx}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${user.CJRXM }</td>
					<td style="text-align: right">${user.KHBM }</td>
					<td>${user.ZJHM }</td>
					<td><c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待审批</c:if> <c:if
							test="${user.STATE=='2'}">审批通过</c:if> <c:if
							test="${user.STATE=='3'}">审批不通过</c:if></td>
					<td><a class="btnSelect"
						href="javascript:$.bringBack({
					id:'${user.ID}', cjrxm:'${user.CJRXM }', khbm:'${user.KHBM }',zjhm:'${user.ZJHM }',
					tzfkkhh:'${user.TZFKKHH }',tzfkyhmc:'${user.TZFKYHMC }',tzfkkhmc:'${user.TZFKKHMC }',tzfkyhzh:'${user.TZFKYHZH }',tzfkyhzh2:'${user.TZFKYHZH }',
					hszjkhh:'${user.HSZJKHH }',hszjyhmc:'${user.HSZJYHMC }',hszjkhmc:'${user.HSZJKHMC }',hszjyhzh:'${user.HSZJYHZH }',hszjyhzh2:'${user.HSZJYHZH }'})"
						title="查找带回"
						onclick="toKhh('${user.TZFKKHH }','${user.HSZJKHH }');">选择</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
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
