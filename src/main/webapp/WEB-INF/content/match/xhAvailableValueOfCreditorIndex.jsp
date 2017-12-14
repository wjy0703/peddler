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
		action="${ctx }/match/listXhAvailableValueOfCreditor${OLD}"
		method="post">
		<input type="hidden" id="old" name="old" value="${OLD }" /><!-- 历史债权标志 -->
		<div class="searchBar">
			<ul class="searchContent">
				
				<table><tr>
<td><label>借款人:</label> <input type="text"
					name="filter_jkrxm" value="${map.jkrxm}" size="10"/></td>
					<c:if test="${OLD==''}">
<td><label>可用期数范围:</label> <input type="text"
					name="filter_syhkysMin" value="${map.syhkysMin}" size="5"/>~<input type="text"
					name="filter_syhkysMax" value="${map.syhkysMax}" size="5"/></td>
<td><label>可用金额范围:</label> <input type="text"
					name="filter_kyzqjzMin" value="${map.kyzqjzMin}" size="5"/>~<input type="text"
					name="filter_kyzqjzMax" value="${map.kyzqjzMax}" size="5"/></td>
					</c:if>
<td><label>还款日:</label> <input type="text"
					name="filter_hkr" value="${map.hkr}" size="5"/></td>

<td><label>利率:</label> <input type="text"
					name="filter_dkll" value="${map.dkll}" size="10"/></td>
</tr>
</table>
				
			
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

			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				
			<li><span>总金额：${kyzqjzall}万元</span></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" />
				<th width="50" class="">借款人</th>
				<th width="100" class="">身份证号</th>
				<th width="80" class="">借款方式</th>
				<th width="80" class="">职业情况</th>
				<th width="72" class="">放款日</th>
				<th width="80" class="">首次还款日</th>
				<th width="60" class="">还款日</th>
				<!-- 	<th width="100" class="">初始借款金额</th> -->
				<th width="80" class="">借款期数</th>
				<th width="100" class="">可用期数</th>
				<th width="80" class="">截止还款日期</th>
				<th width="80" class="">月利率</th>
				<th width="100" class="">原始债权价值</th>
				<th width="80" class="">可用债权价值</th>
				<th width="80" class="">债权人</th>
				<th width="80" class="">债权持有比例</th>
				<th width="80" class="">年预计债权收益率</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.xhJksq.jkrxm}</td>
					<td>${user.xhJksq.zjhm}</td>
					<td><c:if test="${user.xhJksq.jkType=='A'}">老板借</c:if> <c:if
							test="${user.xhJksq.jkType=='B'}">老板楼易借</c:if> <c:if
							test="${user.xhJksq.jkType=='C'}">薪水借</c:if> <c:if
							test="${user.xhJksq.jkType=='D'}">薪水楼易借</c:if> <c:if
							test="${user.xhJksq.jkType=='E'}">精英借</c:if>
							<c:if
							test="${user.xhJksq.jkType=='F'}">房易借</c:if></td>
					<td><c:if
							test="${user.xhJksq.jkType=='A' or user.xhJksq.jkType=='B' }">业主</c:if>
						<c:if
							test="${user.xhJksq.jkType=='C' or user.xhJksq.jkType=='D' or user.xhJksq.jkType=='E'}">职工 </c:if>
							<c:if
							test="${user.xhJksq.jkType=='F' }">抵押房 </c:if>
							</td>
					<td><fmt:formatDate value="${user.makeLoan.makeLoanDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${user.loanContract.qshkrq}</td>
					<td>${user.hkr}</td>
					<!-- <td>${user.loanContract.htje}</td> -->
					<td>${user.syqs}</td>
					<td>${user.syhkys}</td>
					<td>${user.mqhkrq}</td>
					<td><fmt:formatNumber value="${user.loanContract.dkll/100}"
							type="percent" minFractionDigits="2" /></td>
					<td><fmt:formatNumber value="${user.loanContract.htje}"
							pattern="#,#00.00" /></td>
					<td><fmt:formatNumber value="${user.kyzqjz}"
							pattern="#,#00.00" /></td>
					<td>${user.middleMan.middleManName}</td>
					<td><fmt:formatNumber value="${user.zjrcybl/100}" type="percent"  minFractionDigits="2" /></td>
					<td><fmt:formatNumber value="${user.zqyjcyl}" type="percent"  minFractionDigits="2" /></td>
					<td>
					 
					<a title="查看推荐" target="navTab"
						href="${ctx }/xhZqtjDetails/listXhZqtjDetails/${user.id}"
						class="btnLook">查看匹配信息</a></td>
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
