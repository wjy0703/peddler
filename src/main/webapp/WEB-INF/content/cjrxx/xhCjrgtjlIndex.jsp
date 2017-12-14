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
		action="${ctx }/xhCjrgtjl/listXhCjrgtjl" method="post">
		<input type="hidden" name="cjrxx_id" value="${map.cjrxx_id}" /> <input
			type="hidden" name="cjrState" value="${map.cjrState}" />
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>客户姓名：</label> <input name="" type="text" size="30"
					value="${cjrxx.cjrxm }" disabled="disabled" /></li>
				<li><label>证件号码：</label> <input name="" type="text" size="30"
					value="${cjrxx.zjhm }" disabled="disabled" /></li>
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="${ctx}/xhCjrgtjl/addXhCjrgtjl?cjrxx_id=${map.cjrxx_id}&cjrState=${map.cjrState}"
				target="navTab"><span>添加</span></a></li>
			
			<li><a class="edit"
				href="${ctx}/xhCjrgtjl/editXhCjrgtjl/{sid_user}" target="navTab"
				warn="请选择一个用户"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80">意向出资日期</th>
				<th width="80">下次联系日期</th>
				<th width="80">本次沟通日期</th>
				<th width="80">沟通开始时间</th>
				<th width="80">沟通结束时间</th>
				<th width="80">本次联系人</th>
				<th width="80">沟通内容描述</th>
				<th width="80">本次沟通方式</th>
				<th width="80">意向产品</th>
				<th width="80">下次联系方式</th>
				<th width="80">客户意向</th>
				<th width="80">意向出资金额</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.yxczrq}</td>
					<td>${user.xclxrq}</td>
					<td>${user.bcgtrq}</td>
					<td>${user.gtkssj}</td>
					<td>${user.gtjssj}</td>
					<td>${user.bclxr}</td>
					<td>${user.gtnrms}</td>
					<td>${user.bcgtfs}</td>
					<td>${user.yxcp}</td>
					<td>${user.xclxfs}</td>
					<td>${user.khyx}</td>
					<td>￥${user.yxczje}</td>
					<td>
						<a title="编辑" target="navTab"
						href="${ctx }/xhCjrgtjl/editXhCjrgtjl/${user.id}" class="btnEdit">编辑</a>
					</td>
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
