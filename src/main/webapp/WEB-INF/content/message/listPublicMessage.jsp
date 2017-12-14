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
		action="${ctx }/message/listPublicMessage" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>标题:</label> <input type="text" name="filter_title" value="${map.title}"/>
					</td>
					<td><label>状态:</label> <select name="filter_sts"
						class="required combox">
							<option value=""
								<c:if test="${map.sts==''}">selected</c:if> selected>请选择</option>
							<option value="0"
								<c:if test="${map.sts=='0'}">selected</c:if>>未读</option>
							<option value="1"
								<c:if test="${map.sts=='1'}">selected</c:if>>已读</option>

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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a title="确实要已读这些消息吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx }/message/batchHaveRead" class="icon"
				warn="请至少选择一个消息"><span>批量已读</span></a></li>
			<li><a title="确实要删除这些消息吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx }/message/batchDelete" class="delete"
				warn="请至少选择一个消息"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" layoutH="141" nowrapTD="true">
		<thead>
			<tr>
				<th width="100" align="center" class="asc"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="600">标题</th>
				<th width="100" align="center">状态</th>
				<th width="200" align="center">发布时间</th>
				<th width="110">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${publicMessageList}" var="message" varStatus="st">
				<tr target="sid_message" rel="${message.ID}">
					<td><input name="ids" value="${message.ID}" type="checkbox"></td>
					<td>
					<a href="${ctx }/message/getMessageContent?id=${message.ID}" target="dialog" mask="true" width="400" >${message.MESSAGE_TITLE }</a>
					</td>
					<td>
					<c:if test="${message.MESSAGE_STATE=='0'}"><span style="color: red">未读</span></c:if>
					<c:if test="${message.MESSAGE_STATE=='1'}">已读</c:if>
					</td>
					<td>${message.CREATE_TIME }</td>
					<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/message/delMessage/${message.ID}" class="btnDel">删除</a>
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
