<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#" onsubmit="return divSearch(this, 'jbsxBox');">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form id="thisPageForm" rel="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');"
		action="${ctx }/loan/listByAuditPersonNext" method="post">
		<div class="searchBar">
		    <table class="searchContent">
			<tr>
				<td >
					信审员:
					<input type="hidden" name="filter_auditPerson" id="filter_auditPerson" value="${filter_auditPerson }"/>
					<input name="filter_auditPersonName" id="filter_auditPersonName" type="text"
					size="10" value="${filter_auditPersonName }" readonly="readonly" />
				</td>
				<td>
				
			    	 <select name="filter_successState" onchange="checkMonth(this)" class="combox">
						<option value="0" <c:if test="${filter_successState == '0'}">selected</c:if> >待处理</option>
						<option value="1" <c:if test="${filter_successState == '1'}">selected</c:if> >已处理</option>
					</select>
				</td>
				<td >
					<div id="mon" style="display: block">
						月份:
						<input name="filter_createTime" id="filter_createTime" class="date" dateFmt="yyyy-MM"
						size="10" value="${filter_createTime }" readonly="readonly" />
					</div>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
				<td >
					<c:if test="${!empty tgl}">
						通过率：<fmt:formatNumber
							value="${tgl}" pattern="#,#00.00" />%
					</c:if>
				</td>
			</tr>
		</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="" layoutH="100">
		<thead>
			<tr>
				<th width="80">借款人姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="60">产品</th>
				<th width="60">团队经理</th>
				<th width="60">销售人员</th>
				<th width="60">申请金额(元)</th>
				<th width="80">批借金额(元)</th>
				<th width="80">批借期数(月)</th>
				<th width="80">综合费率(%)</th>
				<th width="80">外访费(元)</th>
				<th width="120">进件时间</th>
				<th width="80">分派时间</th>
				<th width="80">初审时间</th>
				<th width="80">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td>${user.loanApply.jkrxm}</td>
					<td>${user.loanApply.province.name}</td>
					<td>${user.loanApply.city.name}</td>					
					<td>${user.loanApply.templet.describe}</td>
				    <td>${user.loanApply.xydkzx.employeeCrm.name}</td>
					<td>${user.loanApply.xydkzx.employeeCca.name}</td>
					
					
					<td><fmt:formatNumber
								value="${user.loanApply.jkLoanQuota}" pattern="#,#00.00" /></td>
					<td><fmt:formatNumber
								value="${user.creditAmount}" pattern="#,#00.00" /></td>
				    <td>${user.creditMonth}</td>	
				    <td>${user.creditAllRate}</td>
					<td>${user.outVisitFee}</td>
					<td>${user.loanApply.backup02}</td>
					<td><sen:assignTime jksqId="${user.loanApply.id}"/></td>
					<td>${user.createTime}</td>
					<td style="color:blue"><sen:showName state="${user.loanApply.state}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox')">
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

		<div class="pagination" rel="jbsxBox"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>
