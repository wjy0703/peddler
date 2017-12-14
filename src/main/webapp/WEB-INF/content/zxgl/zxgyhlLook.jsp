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
		action="${ctx }/zxgl/listZxgtjl" method="post">
		<input type="hidden" name="xydkzx_id" value="${map.xydkzx_id}" />
		<!--  <input type="hidden" name="cjrState" value="${map.cjrState}"/>-->
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>客户姓名：</label> <input name="" type="text" size="30"
					value="${xydkzx.khmc }" disabled="disabled" /></li>
				<li><label>手机号：</label> <input name="" type="text" size="30"
					value="${xydkzx.phone }" disabled="disabled" /></li>
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<!-- <ul class="toolBar">
			<li><a  href="${ctx}/zxgl/inputZxGtjlLook/{sid_xydkzx}" target="navTab" warn="请选择一个用户"><span>查看</span></a></li>
		</ul> -->
	</div>
	<table class="table" width="50%" layoutH="138">
		<thead>
			<tr>
				<!-- 	<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
				<th width="80">序号</th>
				<!-- <th width="80">客户评价</th>
				<th width="80">是否重点客户</th> -->
				<th width="80">本次沟通日期</th>
				<th width="80">沟通记录</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<%int i =1; %>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<!-- <td><input name="ids" value="${user.id}" type="checkbox"></td> -->
					<td><%=i %></td>
					<!--<td><c:if test="${user.khpj=='1'}">优</c:if> <c:if
							test="${user.khpj=='2'}">良</c:if> <c:if test="${user.khpj=='3'}">中</c:if>
						<c:if test="${user.khpj=='4'}">差</c:if></td>
					<td><c:if test="${user.zdkh=='1'}">是</c:if> <c:if
							test="${user.zdkh=='2'}">否</c:if></td> -->
					<td>${user.bcgtrq}</td>
					<td>${user.gtjl}</td>
					<td><a title="查看" target="navTab"
						href="${ctx }/zxgl/inputZxGtjlLook/${user.id}" class="btnEdit">查看</a>
					</td>
				</tr>
				<%i++; %>
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
