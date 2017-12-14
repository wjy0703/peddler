<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx }/jksq/jksqHistoryList/${jksqId }" >
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><span>状态列表</span></li>
		</ul>
	</div>
	<table class="table" width="100%"  layoutH="75" nowrapTD="false" targetType="dialog">
		<thead>
			<tr>
				<th width="25%">借款状态</th>
				<th width="25%">变化时间</th>
				<th width="25%">操作人</th>
				<th width="25%">备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="xhjksqstate" varStatus="st">
				<tr target="sid_XhJksqState" rel="${xhjksqstate.id}">
					<td align="center">${xhjksqstate.describe}</td>
					<td align="center">${xhjksqstate.createTime}</td>
					<td align="center">${xhjksqstate.createBy}</td>
					<td align="center">${xhjksqstate.remarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"  <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20"  <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50"  <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select> <span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }">
		</div>
		
	</div>
</div>

