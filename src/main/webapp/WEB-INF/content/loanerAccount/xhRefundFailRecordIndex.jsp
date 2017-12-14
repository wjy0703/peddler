<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhRefundFailRecord/listXhRefundFailRecord" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>创建人：</label>
						 <input type="text" name="filter_createBy" value="${map.createBy}" /> 
					</td>
					<td><label>录入时间：</label> 
						 <input type="text" name="filter_createTime"  class="date" format="yyyy-MM-dd" value="${map.createTime}" size="20" readonly/>
					</td>
					
					<td><div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">检索</button>
										</div>
									</div></li>
							</ul>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
				<th width="80">序号</th>
				<th width="80">创建人姓名</th>
				<th width="80">借款人姓名</th>
				<th width="80">录入日期</th>
				<th width="80">交易金额</th>
				<th width="80">还款日期</th>
				<th width="80">失败原因</th>
				
			</tr>
		</thead>


		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td>${st.count}</td>
					<td>${user.createBy}</td>
					<td>${user.jkrxm}</td>
					<td>${user.createTime}</td>
					<td>${user.deailingMoney}</td>
					<td>${user.dealingTime}</td>
					<td>${user.errorState}</td>
					
				
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

