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
		action="${ctx }/xhMonthlyAr/listXhMonthlyAr" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td>
					<label>客户名称:</label>
					<input type="text" name="filter_loanName" value="${map.loanName}" />
					</td>
					<td>
					<label>客户身份证号:</label>
					<input type="text" name="filter_loanIdCard" value="${map.loanIdCard}" />
					</td>
					<td>
					<label>客户编号:</label>
					<input type="text" name="filter_loanNumber" value="${map.loanNumber}" />
					</td>
				</tr>
				<tr>
					<td><label>产品:</label> 
						<select class="combox" name="filter_jkType">
							<option value="all">全部</option>
							<c:forEach items="${jkTypeList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${filter_jkType==attr.value}">selected="selected" </c:if>>${attr.description }
								</option>
							</c:forEach>
						</select>
					</td>
					<td>
					<label>所属城市:</label> <select name="filter_area"
						ref="jksqlistbox_city" class="combox"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${map.area==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.area==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select id="jksqlistbox_city" name="filter_city" class="combox"
						refUrl="${ctx}/cjrxx/getArea?code={value}">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${city}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.city==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select>
					</td>
					<td>
					
					</td>
				</tr>
			</table>
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="162">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<!-- <th width="80" orderField="additional" class="asc">帐外</th> -->
				<th width="80">借款编号</th>
				<th width="80">借款人</th>
				<th width="80">借款人身份证号</th>
				<th width="80">省份</th>
				<th width="80">城市</th>
				<!-- 	<th width="80">银行名称</th> -->
				<th width="80">回款开户行</th>
				<th width="80">回款银行账户</th>

				<th width="80">月应收合计</th>
				<th width="80">月应收本金</th>
				<th width="80">月应收利率</th>
				<!-- <th width="80" >借款人ID</th> -->
				<th width="80">借款产品</th>
				<th width="80">借款状态</th>

				<th width="80">本期截止还款日期</th>
				<th width="80">还款日</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<!-- <td>${user.additional}</td>-->

					<td>${user.loanNumber}</td>
					<td>${user.loanName}</td>
					<td>${user.loanIdCard}</td>
					<td><sen:addressName id="${user.area}"/></td>
					<td><sen:addressName id="${user.city}"/></td>
					<!--	<td>${user.bankName}</td> -->
					<td>
					<sen:vtoName coding="bank" value="${user.bankOpen}"/>
					</td>
					<td>${user.bankNumber}</td>

					<td>￥<fmt:formatNumber value="${user.money+user.interest}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.money}"
							pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.interest}"
							pattern="#,#00.00" /></td>
					<!--  <td>${user.loanId}</td>-->


					<td><c:if test="${user.loanPro=='A'}">老板借</c:if> <c:if
							test="${user.loanPro=='B'}">老板楼易借</c:if> <c:if
							test="${user.loanPro=='C'}">薪水借</c:if> <c:if
							test="${user.loanPro=='D'}">薪水楼易借</c:if> <c:if
							test="${user.loanPro=='E'}">精英借</c:if></td>
					<td><sen:showName state="${user.loanState}"/></td>

					<td><fmt:formatDate value="${user.settlementDate}"
							pattern="yyyy/MM/dd" /></td>
					<td>${user.billDay}</td>
					<!--<td>
						  <a title="删除" target="ajaxTodo" href="${ctx }/xhMonthlyAr/delXhMonthlyAr/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhMonthlyAr/editXhMonthlyAr/${user.id}" class="btnEdit">编辑</a>
					</td>-->
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
