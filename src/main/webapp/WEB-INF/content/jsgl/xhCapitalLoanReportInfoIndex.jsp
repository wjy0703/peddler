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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCapitalLoanReportInfo/listXhCapitalLoanReportInfo" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>报告主表:</label>
				<input type="text" name="filter_reportId" value="${map.reportId}"/>
			</li>
			<li>
				<label>出借编号:</label>
				<input type="text" name="filter_lendNo" value="${map.lendNo}"/>
			</li>
			<li>
				<label>初始出借日期:</label>
				<input type="text" name="filter_firstLendDate" value="${map.firstLendDate}"/>
			</li>
			<li>
				<label>出借及回收方式:</label>
				<input type="text" name="filter_lendType" value="${map.lendType}"/>
			</li>
			<li>
				<label>初始出借金额:</label>
				<input type="text" name="filter_firstLendMoney" value="${map.firstLendMoney}"/>
			</li>
			<li>
				<label>本期应还金额:</label>
				<input type="text" name="filter_shoudBack" value="${map.shoudBack}"/>
			</li>
			<li>
				<label>本期实际还款金额:</label>
				<input type="text" name="filter_realBack" value="${map.realBack}"/>
			</li>
			<li>
				<label>延迟支付金额:</label>
				<input type="text" name="filter_latePayMoney" value="${map.latePayMoney}"/>
			</li>
			<li>
				<label>账户管理费率:</label>
				<input type="text" name="filter_mngFeeRate" value="${map.mngFeeRate}"/>
			</li>
			<li>
				<label>账户管理费:</label>
				<input type="text" name="filter_mngFee" value="${map.mngFee}"/>
			</li>
			<li>
				<label>当期受让金额:</label>
				<input type="text" name="filter_reLend" value="${map.reLend}"/>
			</li>
			<li>
				<label>当期回收金额:</label>
				<input type="text" name="filter_drawing" value="${map.drawing}"/>
			</li>
			<li>
				<label>当前全部账户资产:</label>
				<input type="text" name="filter_allMoney" value="${map.allMoney}"/>
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
			<li><a class="add" href="${ctx}/xhCapitalLoanReportInfo/addXhCapitalLoanReportInfo" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhCapitalLoanReportInfo/batchdelXhCapitalLoanReportInfo" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhCapitalLoanReportInfo/editXhCapitalLoanReportInfo/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="reportId" class="asc">报告主表</th>
				<th width="80" orderField="lendNo" class="asc">出借编号</th>
				<th width="80" orderField="firstLendDate" class="asc">初始出借日期</th>
				<th width="80" orderField="lendType" class="asc">出借及回收方式</th>
				<th width="80" orderField="firstLendMoney" class="asc">初始出借金额</th>
				<th width="80" orderField="shoudBack" class="asc">本期应还金额</th>
				<th width="80" orderField="realBack" class="asc">本期实际还款金额</th>
				<th width="80" orderField="latePayMoney" class="asc">延迟支付金额</th>
				<th width="80" orderField="mngFeeRate" class="asc">账户管理费率</th>
				<th width="80" orderField="mngFee" class="asc">账户管理费</th>
				<th width="80" orderField="reLend" class="asc">当期受让金额</th>
				<th width="80" orderField="drawing" class="asc">当期回收金额</th>
				<th width="80" orderField="allMoney" class="asc">当前全部账户资产</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.reportId}</td>
				<td>${user.lendNo}</td>
				<td>${user.firstLendDate}</td>
				<td>${user.lendType}</td>
				<td>${user.firstLendMoney}</td>
				<td>${user.shoudBack}</td>
				<td>${user.realBack}</td>
				<td>${user.latePayMoney}</td>
				<td>${user.mngFeeRate}</td>
				<td>${user.mngFee}</td>
				<td>${user.reLend}</td>
				<td>${user.drawing}</td>
				<td>${user.allMoney}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhCapitalLoanReportInfo/delXhCapitalLoanReportInfo/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhCapitalLoanReportInfo/editXhCapitalLoanReportInfo/${user.id}" class="btnEdit">编辑</a>
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
