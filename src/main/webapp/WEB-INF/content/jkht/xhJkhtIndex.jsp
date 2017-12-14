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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhJkht/listXhJkht" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>借款人姓名</label>
				<input type="text" name="filter_jkrxm" value="${map.jkrxm}"/>
			</li>
			<li>
				<label>借款合同编号:</label>
				<input type="text" name="filter_jkhtbm" value="${map.jkhtbm}"/>
			</li>
			<li>
				<label>状态0暂存,1待审批，2审批通过，3审批不通过，9删除:</label>
				<input type="text" name="filter_state" value="${map.state}"/>
			</li>
			
			<li>
				<label>合同签订日期:</label>
				<input type="text" name="filter_qdrq" value="${map.qdrq}"/>
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
			<li><a class="add" href="${ctx}/xhJkht/addXhJkht" target="navTab"><span>添加</span></a></li>
			<!-- 
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhJkht/batchdelXhJkht" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhJkht/editXhJkht/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			 -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="jkrxm" class="asc">借款人姓名</th>
				<th width="80" orderField="hkqs" class="asc">还款期数</th>
				<th width="80" orderField="yzhfl" class="asc">月综合费率</th>
				<th width="80" orderField="zxf" class="asc">咨询费</th>
				<th width="80" orderField="zhglf" class="asc">账户管理费</th>
				<th width="80" orderField="ylxje" class="asc">月利息金额</th>
				<th width="80" orderField="yll" class="asc">月利率</th>
				<th width="80" orderField="yhkje" class="asc">月还款金额</th>
				<th width="80" orderField="ybjje" class="asc">月本金金额</th>
				<th width="80" orderField="xyshf" class="asc">信用审核费</th>
				<th width="80" orderField="state" class="asc">状态0暂存,1待审批，2审批通过，3审批不通过，9删除</th>
				<th width="80" orderField="qshkrq" class="asc">起始还款日期</th>
				<th width="80" orderField="qdrq" class="asc">合同签订日期</th>
				<th width="80" orderField="jkhtbm" class="asc">借款合同编号</th>
				<th width="80" orderField="htje" class="asc">合同金额</th>
				<th width="80" orderField="fwf" class="asc">服务费</th>
				<th width="80" orderField="fkje" class="asc">放款金额</th>
				<th width="80" orderField="auditPerson" class="asc">审核人</th>
				<th width="80" orderField="auditIdea" class="asc">审核意见</th>
				<th width="80" orderField="auditDate" class="asc">审核时间</th>
				<th width="80" orderField="xff" class="asc">信访费</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.xhJksq.jkrxm}</td>
				<td>${user.yzhfl}</td>
				<td>${user.zxf}</td>
				<td>${user.zhglf}</td>
				<td>${user.ylxje}</td>
				<td>${user.yll}</td>
				<td>${user.yhkje}</td>
				<td>${user.ybjje}</td>
				<td>${user.xyshf}</td>
				<td>${user.state}</td>
				<td>${user.qshkrq}</td>
				<td>${user.qdrq}</td>
				<td>${user.jkhtbm}</td>
				<td>${user.htje}</td>
				<td>${user.fwf}</td>
				<td>${user.fkje}</td>
				<td>${user.auditPerson}</td>
				<td>${user.auditIdea}</td>
				<td>${user.auditDate}</td>
				<td>${user.xff}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhJkht/delXhJkht/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhJkht/editXhJkht/${user.id}" class="btnEdit">编辑</a>
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
