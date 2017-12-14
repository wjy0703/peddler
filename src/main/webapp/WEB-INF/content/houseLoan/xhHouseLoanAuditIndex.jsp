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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhHouseLoanAudit/listXhHouseLoanAudit" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>审核状态:</label>
				<input type="text" name="filter_aduitState" value="${map.aduitState}"/>
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
	
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="houseAddress" class="">房屋所在地</th>
				<th width="80" orderField="houseAroundObject" class="">房屋周边设施</th>
				<th width="80" orderField="houseBankMortgageValue" class="">银行抵押金额</th>
				<th width="80" orderField="loanReason" class="">用款目的</th>
				<th width="80" orderField="repaySource" class="">还款来源</th>
				<th width="80" orderField="makableValue" class="">可放款金额</th>
				<th width="80" orderField="otherAuditInfo" class="">其它审核信息</th>
				<th width="80" orderField="buildingArea" class="">房屋建筑面积</th>
				<th width="80" orderField="buildingYears" class="">房屋年限</th>
				<th width="80" orderField="aduitUnitPrice" class="">综合评定单价</th>
				<th width="80" orderField="marketUnitPrice" class="">市场价值</th>
				
				<th width="80" orderField="aduitState" class="asc">审核状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.houseAddress}</td>
				<td>${user.houseAroundObject}</td>
				<td>${user.houseBankMortgageValue}</td>
				<td>${user.loanReason}</td>
				<td>${user.repaySource}</td>
				<td>${user.makableValue}</td>
				<td>${user.otherAuditInfo}</td>
				<td>${user.buildingArea}</td>
				<td>${user.buildingYears}</td>
				<td>${user.aduitUnitPrice}</td>
				<td>${user.marketUnitPrice}</td>
				
				<td>${user.aduitState}</td>
				<td>
				
					<a title="编辑" target="navTab" href="${ctx }/xhHouseLoanAudit/editXhHouseLoanAudit/${user.xhHouseLoanApply.id}" class="btnEdit">查看</a>
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
