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
		action="${ctx }/xhMonthlyDw/listXhMonthlyDw" method="post">
		<div class="searchBar">
			<ul class="searchContent">




				<table><tr><td><label>出借编号:</label> <input type="text"
					name="filter_lenderNumber" value="${map.lenderNumber}" /></td>
					<td><label>出借人:</label> <input type="text"
					name="filter_lenderName" value="${map.lenderName}" /></td>
					<td><label>账单日:</label> 
					<input type="text" name="filter_payDate" id="filter_payDate"
							class="date" format="yyyy-MM-dd" yearstart="-20"
							value="<fmt:formatDate value='${map.payDate}' pattern='yyyy-MM-dd' />" size="17" readonly="true" /> <a
							class="inputDateButton" href="javascript:;">选择</a> 
							
					</td>
					<td><label>出借状态:</label> 
				<select name="filter_lenderState" class="required combox"><option value="">请选择</option><<option value="1">出借中</option><option value="2">已到期</option></select>
				
				</tr></table>

				



			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return resetDate('filter_payDate');">检索</button>
							</div>
						</div></li>
					<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar"></div>
	<table class="table" width="" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<!-- 	<th width="80" orderField="additional" class="asc">帐外</th> -->
				<th width="120">出借编号</th>
				<th width="80">出借人</th>

				<th width="80">出借人身份证号</th>
				<th width="80">回款银行</th>
				<!-- <th width="80">开户行</th> -->

				<th width="80">账号</th>
				<th width="80">月应回合计</th>
				<th width="80">月应回本金</th>
				<th width="80">月应回利息</th>
				<!-- <th width="80" orderField="lenderId" class="asc">出借人ID</th> -->



				<th width="80">出借状态</th>

				<th width="80">账单日</th>

				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${xhMonthlyDwList}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<!-- <td>${user.additional}</td> -->
					<td>${user.LENDER_NUMBER}</td>
					<td>${user.LENDER_NAME}</td>
					<td>${user.LENDER_ID_CARD}</td>
					<td>${user.BANK_NAME}</td>

					<!--<td>${user.BANK_OPEN}</td> -->
					<td>${user.BANK_NUMBER}</td>
					<td>￥<fmt:formatNumber
							value="${user.ALL_INTEREST+user.ALL_MONEY}" pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.ALL_MONEY}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.ALL_INTEREST}"
							pattern="#,#00.00" /></td>
					<!-- <td>${user.lenderId}</td> -->


					<c:if test="${user.LENDER_STATE==1}"><td style="color:green">出借中</td></c:if> <c:if
							test="${user.LENDER_STATE==2}"><td style="color:red">已到期</td></c:if></td>
					<td><fmt:formatDate value="${user.PAY_DATE}"
							pattern="yyyy-MM-dd" /></td>

					<!-- 	<td><c:if test="${user.payType==0}"></c:if>正常还款</td>-->

					<!-- 	<td>${user.zqtjId}</td>-->
					<td>
						<!--  <a title="删除" target="ajaxTodo" href="${ctx }/xhMonthlyDw/delXhMonthlyDw/${user.id}" class="btnDel">删除</a>-->
						<a
						title="出借编号：${user.lenderNumber}-出借人：${user.lenderName}-月本金收益明细"
						target="dialog"
						href="${ctx }/xhMonthlyDwInfo/listXhMonthlyDwInfo/${user.LENDER_NUMBER},${user.PAY_DATE}"
						class="btnLook"></a>明细


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
