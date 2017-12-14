<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader" layoutH="10">
	<div class="pageContent">
		<form rel="pagerForm" id="managematerialForm"
			name="managematerialForm" method="post"
			action="${ctx}/loan/managematerial" class="pageForm" fresh="false"
			onsubmit="return navTabSearch(this);">
			<div class="pageFormContent">
				<div class="searchBar">
					<table class="searchContent" border="0" bordercolor="red">
						<tr>
							<td><label>借款人姓名:</label> <input type="text"
								name="filter_jkrxm" /></td>
							<td><label>英文名:</label> <input type="text"
								name="filter_ywmc" /></td>
							<td><label>证件号码:</label> <input type="text"
								name="filter_zjhm" /></td>
							<td><label>移动电话:</label> <input type="text"
								name="filter_yddh" /></td>
						</tr>
					</table>
					<table class="searchContent" border="0" bordercolor="red">
						<tr>
							<td><label>申请状态:</label> <input id="poxb" name="poxb"
								type="radio" value="男" checked="checked" />待审批&nbsp;</td>
							<td><input id="poxb" name="poxb" type="radio" value="男"
								checked="checked" />审批通过&nbsp;</td>
							<td><input id="poxb" name="poxb" type="radio" value="男"
								checked="checked" />审批拒绝</td>
						</tr>
					</table>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">搜索</button>
									</div>
								</div></li>
						</ul>
					</div>
				</div>
			</div>
		</form>
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/loan/addXhcfDkrxxxgjl"
					target="navTab" title="新增变更申请"><span>新增变更申请</span></a></li>
				<li><a class="icon" href="${ctx}/loan/"
					onclick="exportLoan(this)" target="dwzExport" targetType="navTab"
					title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th width="100">借款人编码</th>
					<th width="55">借款人姓名</th>
					<th width="70">证件号码</th>
					<th width="80">移动电话</th>
					<th width="80">电子邮箱</th>
					<th width="60">申请状态</th>
					<th width="90" align="center">开户状态</th>
					<th width="90" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="xhcfdkrxx" varStatus="st">
					<tr target="sid_Borrowerssingle" rel="${xhcfdkrxx.id}">
						<td>${st.count}</td>
						<td>${xhcfdkrxx.jkrxm }</td>
						<td>${xhcfdkrxx.zjhm }</td>
						<td>${xhcfdkrxx.yddh }</td>
						<td>${xhcfdkrxx.txdz }</td>
						<td>待审批</td>
						<td>未开户</td>
						<td><a target="navTab"
							href="${ctx }/loan/lookAlteration/${xhcfdkrxx.id}" rel="借款人信息变更">查看</a>
							<a target="navTab"
							href="${ctx }/loan/examAlteration/${xhcfdkrxx.id}"
							rel="审批借款人信息变更">审批</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>每页显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
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

</div>

<script type="text/javascript">

	

</script>