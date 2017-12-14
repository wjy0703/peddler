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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhMonthlyPw/listXhMonthlyPw" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>主键:</label>
				<input type="text" name="filter_id" value="${map.id}"/>
			</li>
			<li>
				<label>帐外:</label>
				<input type="text" name="filter_additional" value="${map.additional}"/>
			</li>
			<li>
				<label>银行名称:</label>
				<input type="text" name="filter_bankName" value="${map.bankName}"/>
			</li>
			<li>
				<label>银行账号:</label>
				<input type="text" name="filter_bankNumber" value="${map.bankNumber}"/>
			</li>
			<li>
				<label>银行开户行:</label>
				<input type="text" name="filter_bankOpen" value="${map.bankOpen}"/>
			</li>
			<li>
				<label>利息:</label>
				<input type="text" name="filter_interest" value="${map.interest}"/>
			</li>
			<li>
				<label>出借人ID:</label>
				<input type="text" name="filter_lenderId" value="${map.lenderId}"/>
			</li>
			<li>
				<label>出借人身份证号:</label>
				<input type="text" name="filter_lenderIdCard" value="${map.lenderIdCard}"/>
			</li>
			<li>
				<label>出借人名称:</label>
				<input type="text" name="filter_lenderName" value="${map.lenderName}"/>
			</li>
			<li>
				<label>出借编号:</label>
				<input type="text" name="filter_lenderNumber" value="${map.lenderNumber}"/>
			</li>
			<li>
				<label>出借状态:</label>
				<input type="text" name="filter_lenderState" value="${map.lenderState}"/>
			</li>
			<li>
				<label>金额:</label>
				<input type="text" name="filter_money" value="${map.money}"/>
			</li>
			<li>
				<label>付款日期:</label>
				<input type="text" name="filter_payDate" value="${map.payDate}"/>
			</li>
			<li>
				<label>付款类型:</label>
				<input type="text" name="filter_payType" value="${map.payType}"/>
			</li>
			<li>
				<label>备用字段1:</label>
				<input type="text" name="filter_spareField01" value="${map.spareField01}"/>
			</li>
			<li>
				<label>备用字段2:</label>
				<input type="text" name="filter_spareField02" value="${map.spareField02}"/>
			</li>
			<li>
				<label>备用字段3:</label>
				<input type="text" name="filter_spareField03" value="${map.spareField03}"/>
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
			<li><a class="add" href="${ctx}/xhMonthlyPw/addXhMonthlyPw" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhMonthlyPw/batchdelXhMonthlyPw" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhMonthlyPw/editXhMonthlyPw/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="id" class="asc">主键</th>
				<th width="80" orderField="additional" class="asc">帐外</th>
				<th width="80" orderField="bankName" class="asc">银行名称</th>
				<th width="80" orderField="bankNumber" class="asc">银行账号</th>
				<th width="80" orderField="bankOpen" class="asc">银行开户行</th>
				<th width="80" orderField="interest" class="asc">利息</th>
				<th width="80" orderField="lenderId" class="asc">出借人ID</th>
				<th width="80" orderField="lenderIdCard" class="asc">出借人身份证号</th>
				<th width="80" orderField="lenderName" class="asc">出借人名称</th>
				<th width="80" orderField="lenderNumber" class="asc">出借编号</th>
				<th width="80" orderField="lenderState" class="asc">出借状态</th>
				<th width="80" orderField="money" class="asc">金额</th>
				<th width="80" orderField="payDate" class="asc">付款日期</th>
				<th width="80" orderField="payType" class="asc">付款类型</th>
				<th width="80" orderField="spareField01" class="asc">备用字段1</th>
				<th width="80" orderField="spareField02" class="asc">备用字段2</th>
				<th width="80" orderField="spareField03" class="asc">备用字段3</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.id}</td>
				<td>${user.additional}</td>
				<td>${user.bankName}</td>
				<td>${user.bankNumber}</td>
				<td>${user.bankOpen}</td>
				<td>${user.interest}</td>
				<td>${user.lenderId}</td>
				<td>${user.lenderIdCard}</td>
				<td>${user.lenderName}</td>
				<td>${user.lenderNumber}</td>
				<td>${user.lenderState}</td>
				<td>${user.money}</td>
				<td>${user.payDate}</td>
				<td>${user.payType}</td>
				<td>${user.spareField01}</td>
				<td>${user.spareField02}</td>
				<td>${user.spareField03}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhMonthlyPw/delXhMonthlyPw/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhMonthlyPw/editXhMonthlyPw/${user.id}" class="btnEdit">编辑</a>
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
