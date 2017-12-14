<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="${ctx }/jygl/listMiddleMan">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dwzSearch(this, 'dialog');"
		action="${ctx }/jygl/listMiddleMan" method="post">
		<div class="pageFormContent">
			<table>
				<tr>
					<td><label>开户行：</label> <input type="text" name="filter_khh"
						value="${map.khbm }" /></td>
					<td><label>中间人：</label> <input type="text"
						name="filter_zjzhmc" value="${map.middleManName }" /></td>
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
				<th width="80" orderField="middleManName" class="asc">中间人</th>
				<th width="80">证件号码</th>
				<th width="80" >开户行</th>
				<th width="80">银行账号</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listZjr}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${user.MIDDLE_MAN_NAME }</td>
					<td style="text-align: right">${user.ID_CARD }</td>
					<td>${user.CRED_ADDR }</td>
					<td style="text-align: right">${user.CRED_ID }</td>
					<td><a class="btnSelect"
						href="javascript:$.bringBack({
					id:'${user.ID}', middleManName:'${user.MIDDLE_MAN_NAME }', id_card:'${user.ID_CARD }',cred_addr:'${user.CRED_ADDR }',
					cred_id:'${user.CRED_ID }'})"
						title="查找带回">选择</a></td>
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
