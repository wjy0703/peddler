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
	<form rel="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');"
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
					<div id="mon" style="display: none">
					月份:
					<input name="filter_createTime" id="filter_createTime" class="date" dateFmt="yyyy-MM"
					size="10" value="${filter_createTime }" readonly="readonly" />
				</div>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
			
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="" layoutH="100">
		<thead>
			<tr>
				<th width="60">客户编号</th>
				<th width="80">客户姓名</th>
				<th width="80">共同还款人</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="100">产品</th>
				<th width="80">申请金额(元)</th>
				<th width="80">期数(月)</th>
				<th width="80">团队经理</th>
				<th width="80">客户经理</th>
				<th width="140">进件时间</th>
				<th width="140">分派时间</th>
				<th width="80">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr target="sid_jksq" rel="${jksq.ID}">
					<td></td>
					<td>${jksq.JKRXM }</td>
					<td><sen:showTogether  lendId="${jksq.ID}" yesOrNo="${jksq.ISTOGETHER}"/></td>

					<td><sen:addressName id="${jksq.PROVINCE}"/></td>
					<td><sen:addressName id="${jksq.CITY}"/></td>
					<td><sen:vtoName value="${jksq.JK_TYPE}" coding="productType"/></td>
						<td><c:if test='${null != jksq.JK_LOAN_QUOTA && jksq.JK_LOAN_QUOTA != 0 }'><fmt:formatNumber
								value="${jksq.JK_LOAN_QUOTA }" pattern="#,#00.00" />
						</c:if></td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.KHMC }</td>
					<td>${jksq.SALENAME }</td>
					<td>${jksq.BACKUP02 }</td>
					<td><sen:assignTime jksqId="${jksq.ID}"/></td>
					<td style="color: red"><sen:showName state="${jksq.STATE}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox')">
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