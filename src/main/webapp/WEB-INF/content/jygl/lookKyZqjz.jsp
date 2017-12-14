<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" onsubmit="return navTabSearch(this);"
		action="${ctx }/jygl/findKyZqjz" >
		<div class="searchBar">
			<table class="searchContent" layoutH="378" width="100%">
				<tr>
					<td><label>借款人申请编号:</label> <input class="textInput"
						name="filter_loanCode" value="${map.loanCode }" type="text"></td>
					<td><label>剩余还款月数:</label> <input class="textInput"
					name="filter_syhkysMin" value="${map.syhkysMin }" type="text"></td>
					<td><label>至</label> 
					<input class="textInput" name="filter_syhkysMax"
					value="${map.syhkysMax }" type="text"></td>
				</tr>
				<tr>
					<td><label>借款人姓名:</label> <input class="textInput"
						name="filter_jkrxm" value="${map.jkrxm }" type="text"></td>
					<td><label>剩余推荐金额:</label> <input class="textInput"
						name="filter_sytjjeMin" value="${map.sytjjeMin }" type="text"></td>
					<td><label>至</label> 
					<input class="textInput" name="filter_sytjjeMax"
						value="${map.sytjjeMax }" type="text"></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<table class="table" layoutH="146" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="10%">借款人姓名</th>
				<th width="10%">还款方式</th>
				<th width="10%">申请日期</th>
				<th width="10%">剩余还款期数</th>
				<th width="10%">借款用途</th>
				<th width="10%">原始债权价值</th>
				<th width="10%">可推荐债权价值</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="mate" varStatus="st">
				<tr>
					<td>${mate.xhJksq.jkrxm}</td>
					<td>${mate.xhJksq.hkWay }</td>
					<td>${mate.xhJksq.jkLoanDate }</td>
					<td>${mate.syqs }</td>
					<td>${mate.xhJksq.jkUse }</td>
					<td>${mate.kyzqjz }</td>
					<td>${mate.sytjje }</td>
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