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
		action="${ctx }/ipc/listXhIpcApply" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>客户姓名:</label> <input type="text"
					name="filter_customerName" value="${map.customerName}" /></li>
				<li><label>客户编号:</label> <input type="text"
					name="filter_customerNum" value="${map.customerNum}" /></li>
				<li><label>客户电话:</label> <input type="text"
					name="filter_customerPhone" value="${map.customerPhone}" /></li>
				
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
		<bjdv:validateContent type="1" funcId="IPC-新增客户">	
			<li><a class="add" href="${ctx}/ipc/addXhIpcApply"
				target="navTab"><span>新增IPC客户信息</span></a></li>
				</bjdv:validateContent>
				<bjdv:validateContent type="1" funcId="IPC-变更客户">	
			<li><a class="edit" href="${ctx}/ipc/editXhIpcApply/{sid_user}"
				target="navTab" warn="请选择一个用户"><span>变更IPC客户信息</span></a></li>
				</bjdv:validateContent>
			<!--  <li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80" >客户姓名</th>
				<th width="80" >客户编号</th>
				<th width="80" >客户电话</th>
				<th width="80" >身份证号</th>
				
				<th width="80" >共借人姓名</th>
				<th width="80" >共借人关系</th>
				<th width="80" >共借人电话</th>
				
				
				<th width="80" >开发信借员</th>
				<th width="80" >负责信借员</th>
				<th width="80" >维护人员</th>
				<th width="80" >借款类型</th>
				<th width="80" >是否录入合同</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.customerName}</td>
					<td>${user.customerNum}</td>
					<td>${user.customerPhone}</td>
					<td>${user.customerCardId}</td>
					
					<td>${user.togetherName}</td>
					<td>${user.togetherRelation}</td>
					<td>${user.togetherPhone}</td>

					
					<td>${user.kfEmp}</td>
					<td>${user.fzEmp}</td>
					<td>${user.whEmp}</td>
					<td>${user.loanType}</td>
					<td>
					<c:if test="${user.state=='0'}">否</c:if>
					<c:if test="${user.state=='1'}">是</c:if>
					</td>
				<!-- <td>${user.loanUse}</td> -->	
					<td>
					<div class="buttonActive">
							<div class="buttonContent">
					<a title="修改IPC客户信息" target="navTab"
						href="${ctx }/ipc/editXhIpcApply/${user.id}" class="">变更</a>	</div></div>
						<c:if test="${user.state=='0' || user.state==''}">
				<div class="buttonActive">	<div class="buttonContent">
					
						<a title="录入合同信息" target="navTab"
						href="${ctx }/ipc/addXhIpcConstract/${user.id}" class="">录入合同</a></div></div>
						</c:if>
						
		
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
