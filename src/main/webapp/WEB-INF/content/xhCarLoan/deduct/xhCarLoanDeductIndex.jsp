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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanDeduct/listXhCarLoanHuaKou" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>申请人姓名:</label>
				<input type="text" name="filter_userName" value="${map.userName}"/>
			</li>
			<li>
				<label>申请日期:</label>
				<input type="text" name="filter_jk_loan_date" class="date" yearstart="-113" yearend="5" format="yyyy-MM-dd" size="15" value="${map.jk_loan_date}" readonly="readonly" /> <a
				class="inputDateButton" href="javascript:;">选择</a>
			</li>
			<li>
				<label>借款类型:</label>
			<sen:select clazz="combox"  coding="carType" name="filter_jkType" value="${map.jkType }" />
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="108">
		<thead>
			<tr>
				<th width="40"  >序号</th>
				<th width="80">申请人姓名</th>
				<th width="80">合同金额</th>
				<th width="80">综合费用</th>
				<th width="80">借款成数<!-- （借款总额/车辆评估金额） --></th>
				<th width="80">借款周期</th>
				<th width="80">借款类型</th>
				<th width="80">申请日期</th>
				<th width="80">管辖省份</th>
				<th width="80" >申请状态</th>
				<th width="80"  >借款编号</th>
				<th width="80" >是否展期</th>
				<th width="80" >是否已展期</th>
				<th width="80">车牌号码</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${xhCarLoanApply}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.ID}">
				<td>${st.count}</td>
				<td>${user.USER_NAME}</td> 
				<td>${user.CONTRACT_MONEY}</td>
				<td>${user.COMLPEX_MONEY}</td>
				<td>${user.LOAN_SCALE}</td>
				<td>${user.JK_CYCLE}</td>
				<td><sen:vtoName coding="carType" value="${user.JK_TYPE }" /></td>
				<td>${user.JK_LOAN_DATE}</td>
				<td>${user.CRMPROVINCENAME}</td> 
				<td><sen:carStateToName value="${user.STATE}"/></td>
				<td>${user.LOAN_CODE}</td>
				<td><sen:vtoName coding="isExtension" value="${user.IS_EXTENSION }" /></td>
				<td><sen:vtoName coding="isHaveextension" value="${user.IS_HAVEEXTENSION }"/></td>
				<td>${user.PLATE}</td>
				<td>
				<div class="buttonActive">
				     <div class="buttonContent">
					<a title="车借划扣审核" target="navTab" href="${ctx }/xhCarLoanDeduct/madeXhCarLoanDeduct/${user.ID}" 
					rel="rel_jksqQianShu">划扣审核</a>
						</div>
		            </div>
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
