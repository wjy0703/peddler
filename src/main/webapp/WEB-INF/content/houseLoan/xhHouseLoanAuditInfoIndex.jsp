<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhHouseLoanAuditInfo/listXhHouseLoanAuditInfo" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>房屋性质:</label>
				<input type="text" name="filter_houseType" value="${map.houseType}"/>
			</li>
			<li>
				<label>房屋评估价值:</label>
				<input type="text" name="filter_houseEvaluateValue" value="${map.houseEvaluateValue}"/>
			</li>
			<li>
				<label>信访费:</label>
				<input type="text" name="filter_creditVisitFee" value="${map.creditVisitFee}"/>
			</li>
			<li>
				<label>综合费率:</label>
				<input type="text" name="filter_allFeeRate" value="${map.allFeeRate}"/>
			</li>
			<li>
				<label>服务费率:</label>
				<input type="text" name="filter_serviceFeeRate" value="${map.serviceFeeRate}"/>
			</li>
			<li>
				<label>月利率:</label>
				<input type="text" name="filter_monthRate" value="${map.monthRate}"/>
			</li>
			<li>
				<label>借款期数:</label>
				<input type="text" name="filter_loanMonth" value="${map.loanMonth}"/>
			</li>
			<li>
				<label>签约日期:</label>
				<input type="text" name="filter_signContractDate" value="${map.signContractDate}"/>
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
			<!--  <li><a class="add" href="${ctx}/xhHouseLoanAudit/addXhHouseLoanAuditInfo" target="navTab"><span>添加</span></a></li>
			
			<li><a class="edit" href="${ctx}/xhHouseLoanAudit/editXhHouseLoanAuditInfo/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" >房屋性质</th>
				<th width="80" >房屋评估价值</th>
				<th width="80" >信访费</th>
				<th width="80" >综合费率</th>
				<th width="80" >服务费率</th>
				<th width="80" >月利率</th>
				<th width="80" >借款期数</th>
				<th width="80" >签约日期</th>
				
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.houseType}</td>
				<td>${user.houseEvaluateValue}</td>
				<td>${user.creditVisitFee}</td>
				<td>${user.allFeeRate}</td>
				<td>${user.serviceFeeRate}</td>
				<td>${user.monthRate}</td>
				<td>${user.loanMonth}</td>
				<td><fmt:formatDate value="${user.signContractDate}"  pattern="yyyy-MM-dd" /></td>
				
				<td>
					
					<a title="编辑" target="navTab" href="${ctx }/xhHouseLoanAudit/editXhHouseLoanAuditInfo/${user.id}" class="btnEdit">查看</a>
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
