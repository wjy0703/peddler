<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
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
		action="${ctx }/xhMatchingInvestment/listXhMatchingInvestment"
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>投资申请:</label> <input type="text"
					name="filter_investApplyId" value="${map.investApplyId}" /></li>
				<li><label>出借日期:</label> <input type="text"
					name="filter_firstInvestDate" value="${map.firstInvestDate}" /></li>
				<li><label>账单日:</label> <input type="text"
					name="filter_billDay" value="${map.billDay}" /></li>
				<li><label>出借方式（ 产品类型）:</label> <input type="text"
					name="filter_investType" value="${map.investType}" /></li>
				<li><label>已匹配金额:</label> <input type="text"
					name="filter_matchedAmount" value="${map.matchedAmount}" /></li>
				<li><label>待匹配金额:</label> <input type="text"
					name="filter_matchingAmount" value="${map.matchingAmount}" /></li>
				<li><label>划扣日期:</label> <input type="text"
					name="filter_deductDate" value="${map.deductDate}" /></li>
				<li><label>交割日期:</label> <input type="text"
					name="filter_deliveryDate" value="${map.deliveryDate}" /></li>
				<li><label>状态 0: 未完成匹配 1:完成匹配 2 待审批 3 审核拒绝 4 审核通过，待订购，
						5取消订购 6 已订购，带交割 7已交割:</label> <input type="text" name="filter_investState"
					value="${map.investState}" /></li>
				<li><label>0首期 1非首期:</label> <input type="text"
					name="filter_investMode" value="${map.investMode}" /></li>
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
			<li><a class="add"
				href="${ctx}/xhMatchingInvestment/addXhMatchingInvestment"
				target="navTab"><span>审批</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				postType="string"
				href="${ctx }/xhMatchingInvestment/batchdelXhMatchingInvestment"
				class="delete" warn="请至少选择一个"><span>订购</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="${ctx}/xhMatchingInvestment/editXhMatchingInvestment/{sid_user}"
				target="navTab" warn="请选择一个用户"><span>交割</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" />
				<th width="80" orderField="investApplyId" class="asc">出借编号</th>
				<th width="80" orderField="firstInvestDate" class="asc">出借人姓名</th>
				<th width="80" orderField="billDay" class="asc">出借方式</th>
				<th width="80" orderField="investType" class="asc">初始出借金额</th>
				<th width="80" orderField="matchedAmount" class="asc">已匹配金额</th>
				<th width="80" orderField="matchingAmount" class="asc">待匹配金额</th>
				<th width="80" orderField="deductDate" class="asc">出借日期</th>
				<th width="80" orderField="deductDate" class="asc">账单日</th>
				<th width="80" orderField="deductDate" class="asc">划扣日期</th>
				<th width="80" orderField="deliveryDate" class="asc">交割日期</th>
				<th width="80" orderField="investState" class="asc">首期/非首期</th>
				<th width="80" orderField="investMode" class="asc">状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="invest" varStatus="st">
				<tr target="sid_invest" rel="${invest.id}">
					<td><input name="ids" value="${invest.id}" type="checkbox"></td>
					<td>${invest.investApply.tzsqbh }</td>
					<td>${invest.investApply.cjrxx.cjrxm }</td>
					<td>${invest.investApply.tzcp.tzcpMc }</td>
					<td style="color: blue"><fmt:formatNumber
							value="${invest.investApply.jhtzje}" pattern="#,#00.00" /></td>
					<td style="color: green"><fmt:formatNumber
							value="${invest.matchedAmount}" pattern="#,#00.00" /></td>
					<td style="color: red"><fmt:formatNumber
							value="${invest.matchingAmount}" pattern="#,#00.00" /></td>
					<td style="color: red"><fmt:formatDate
							value="${invest.firstInvestDate}" pattern="yyyy/MM/dd" /></td>

					<td style="color: red">${invest.billDay}</td>
					<td style="color: red"><fmt:formatDate
							value="${invest.deductDate}" pattern="yyyy/MM/dd" /></td>
					<td style="color: red"><fmt:formatDate
							value="${invest.deliveryDate}" pattern="yyyy/MM/dd" /></td>
					<td style="color: red"><c:if test="${invest.investMode==0}">首期</c:if>
						<c:if test="${invest.investMode==1}">非首期</c:if></td>
					<td style="color: red"><c:if test="${invest.investState==0}">待匹配</c:if></td>
					<td><a class="button"
						href="${ctx}/match/editXhMatchingInvestment/${invest.id}"
						target="navTab"><span>债权匹配</span></a>
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
