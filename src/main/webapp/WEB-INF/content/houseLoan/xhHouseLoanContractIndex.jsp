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
		action="${ctx }/xhHouseLoanContract/listXhHouseLoanContract"
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>合同编号:</label> <input type="text"
					name="filter_contractNum" value="${map.contractNum}" /></li>
				<!-- 	<li><label>合同金额:</label> <input type="text"
					name="filter_contractMoney" value="${map.contractMoney}" /></li> -->

				<li><label>状态:</label> <select name="filter_state"
					class="combox">
						<option value="">请选择</option>
						<option value="1">1:待签约</option>
						<option value="2">2:已签约</option>
						<option value="3">3:客户放弃</option>
				</select></li>
				<!-- 
			
			<li><label>出借人ID:</label> <input type="text"
					name="filter_middleManId" value="${map.middleManId}" /></li>
			 -->

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
	<div class="panelBar"></div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80">借款编号</th>
				<th width="80">借款人姓名</th>

				<th width="80">借款金额</th>
				<th width="80">借款期数</th>
				<th width="80">合同签订日期</th>
				<th width="80">借款状态</th>


				<th width="80">出借人</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.contractNum}</td>
					<td>${user.xhHouseLoanApply.loanerName}</td>

					<td>${user.contractMoney}</td>
					<td>${user.xhHouseLoanAuditInfo.loanMonth}</td>
					<td>${user.contractDate}<td><c:if test="${user.state==1}">待签约</c:if> 
					    <c:if test="${user.state==2}">已签约</c:if> 
						<c:if test="${user.state==3}">客户放弃</c:if>

					</td>


					<td>${user.middleMan.middleManName}</td>
					<td>
					<div class="buttonActive">
							<div class="buttonContent">
					<a title="查看" target="navTab"
						href="${ctx }/xhHouseLoanContract/editXhHouseLoanContract/${user.id}"
						class="">查看</a> 
						</div></div>
						<c:if test="user.state!=2">
						<div class="buttonActive">
							<div class="buttonContent"><a title="审核" target="navTab"
						href="${ctx }/xhHouseLoanContract/auditXhHouseLoanContract/${user.id}"
						class="">审核</a>	</div></div></c:if>
						<c:if test="user.state!=2"><div class="buttonActive">
							<div class="buttonContent">	
						 <a title="确认" target="ajaxTodo"
						href="${ctx }/xhHouseLoanContract/confirmXhHouseLoanContract/${user.id}"
						class="">签约确认</a></div></div></c:if>
				
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
