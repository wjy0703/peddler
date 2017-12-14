<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarAudit/listXhCarAudit" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>信审类型：初审(1)、复审(2)、终审(100):</label>
				<input type="text" name="filter_creditType" value="${map.creditType}"/>
			</li>
			<li>
				<label>信审状态：0信审未结束 1信审结束:</label>
				<input type="text" name="filter_creditState" value="${map.creditState}"/>
			</li>
			<li>
				<label>审批金额:</label>
				<input type="text" name="filter_creditAmount" value="${map.creditAmount}"/>
			</li>
			<li>
				<label>借款期限（天）:</label>
				<input type="text" name="filter_creditMonth" value="${map.creditMonth}"/>
			</li>
			<li>
				<label>综合费率:</label>
				<input type="text" name="filter_creditAllRate" value="${map.creditAllRate}"/>
			</li>
			<li>
				<label>业务收费:</label>
				<input type="text" name="filter_operationFee" value="${map.operationFee}"/>
			</li>
			<li>
				<label>外访费(需求说明文档没有):</label>
				<input type="text" name="filter_outVisitFee" value="${map.outVisitFee}"/>
			</li>
			<li>
				<label>加急费需求说明文档没有):</label>
				<input type="text" name="filter_urgentCreditFee" value="${map.urgentCreditFee}"/>
			</li>
			<li>
				<label>拒借原因:</label>
				<input type="text" name="filter_creditRefuseReason" value="${map.creditRefuseReason}"/>
			</li>
			<li>
				<label>信审意见:</label>
				<input type="text" name="filter_creditAuditReport" value="${map.creditAuditReport}"/>
			</li>
			<li>
				<label>信审结果：1通过、0拒绝:</label>
				<input type="text" name="filter_creditResult" value="${map.creditResult}"/>
			</li>
			<li>
				<label>信审通过后形成的客户编号:</label>
				<input type="text" name="filter_passedCustomerNo" value="${map.passedCustomerNo}"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/xhCarAudit/addXhCarAudit" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhCarAudit/batchdelXhCarAudit" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhCarAudit/editXhCarAudit/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="creditType" class="asc">信审类型：初审(1)、复审(2)、终审(100)</th>
				<th width="80" orderField="creditState" class="asc">信审状态：0信审未结束 1信审结束</th>
				<th width="80" orderField="creditAmount" class="asc">审批金额</th>
				<th width="80" orderField="creditMonth" class="asc">借款期限（天）</th>
				<th width="80" orderField="creditAllRate" class="asc">综合费率</th>
				<th width="80" orderField="operationFee" class="asc">业务收费</th>
				<th width="80" orderField="outVisitFee" class="asc">外访费(需求说明文档没有)</th>
				<th width="80" orderField="urgentCreditFee" class="asc">加急费需求说明文档没有)</th>
				<th width="80" orderField="creditRefuseReason" class="asc">拒借原因</th>
				<th width="80" orderField="creditAuditReport" class="asc">信审意见</th>
				<th width="80" orderField="creditResult" class="asc">信审结果：1通过、0拒绝</th>
				<th width="80" orderField="passedCustomerNo" class="asc">信审通过后形成的客户编号</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.creditType}</td>
				<td>${user.creditState}</td>
				<td>${user.creditAmount}</td>
				<td>${user.creditMonth}</td>
				<td>${user.creditAllRate}</td>
				<td>${user.operationFee}</td>
				<td>${user.outVisitFee}</td>
				<td>${user.urgentCreditFee}</td>
				<td>${user.creditRefuseReason}</td>
				<td>${user.creditAuditReport}</td>
				<td>${user.creditResult}</td>
				<td>${user.passedCustomerNo}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhCarAudit/delXhCarAudit/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhCarAudit/editXhCarAudit/${user.id}" class="btnEdit">编辑</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
				<option value="10" <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20" <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50" <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>
