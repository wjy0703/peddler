<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<script type="text/javascript">
	function fluchUploadFile(param){
		var uploadedFlag = top.document.getElementById("uploadedFlag");
		if ("1" == uploadedFlag.value) {
			navTabAjaxDone(param);
		}
		return true;
	}
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/report/yqbb" method="post">
		<div class="searchBar">
		<table width="100%">
			<tr>
				<td>
					<label>客户名称:</label>
					<input type="text" name="filter_lenderName" value="${map.lenderName}"/>
				</td>
				<td>
					<label>合同编号:</label>
					<input type="text" name="filter_lenderNumber" value="${map.lenderNumber}"/>
				</td>
				<td>
					<label>逾期时间:</label>
					<input type="text" name="filter_overdueDate" class="date" dateFmt="yyyy-MM" readonly="true" value="${map.overdueDate }"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>逾期状态:</label>
					<sen:select clazz="combox required" name="filter_overdueStatr" coding="capitalOverdue" id="zjlx" value="${map.overdueStatr}" title="全部" titleValue=""/>
				</td>
				<td>
				<label>门店:</label>
					<input id="organiId" name="filter_organi.id" type="hidden" value="${organiId}"/>
					<input name="filter_organi.name" type="text" value="${organiName }" onblur="cleanMd(this.value)"/>
					<a href="${ctx }/loan/getMdList" lookupGroup="filter_organi" style=""><span style="color: #7F7F7F;">选择门店</span></a>
				</td>
				<td>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
						<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
					</ul>
				</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=yqbbExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1560px" layoutH="141" nowrapTD="false">
		<thead>
			<tr>
				<th width="100px">合同编号</th>
				<th width="200px">机构</th>
				<th width="100px">客户姓名</th>			
				<th width="100px">放款日期</th>
				<th width="100px">客户经理</th>
				<th width="100px">合同金额</th>
				<th width="100px">期数</th>
				<th width="100px">逾期日期</th>
				<th width="100px">逾期天数</th>
				<th width="100px">剩余期数</th>
				<th width="100px">剩余本金</th>
				<th width="100px">利率</th>
				<th width="100px">应还期供</th>
				<th width="100px">罚息利率</th>
				<th width="100px">罚息</th>
				<th width="100px">违约金利率</th>
				<th width="100px">违约金</th>
				<th width="100px">当前状态</th>
				<th width="100px">已还金额</th>
				<th width="100px">挂账金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user">
					<td>${user.JKHTBM }</td>
					<td>${user.YYB }</td>
					<td>${user.JKRXM }</td>
					<td>${user.FKDATE }</td>
					<td>${user.SALENAME }</td>
					<td>￥<fmt:formatNumber value="${user.HTJE }" pattern="#,#00.00" /></td>
					<td>${user.HKQS }</td>
					<td>${user.OVERDATE }</td>
					<td>${user.OVERDAY }</td>
					<td>${user.OVERQS }</td>
					<td>￥<fmt:formatNumber value="${user.SYJE }" pattern="#,#00.00" /></td>
					<td>${user.DKLL }%</td>
					<td>￥<fmt:formatNumber value="${user.YHKJE }" pattern="#,#00.00" /></td>
					<td>${user.INTEREST * 100 }%</td>
					<td>￥<fmt:formatNumber value="${user.PUNISH_INTEREST }" pattern="#,#00.00" /></td>
					<td>${user.DAMAGES * 100 }%</td>
					<td>￥<fmt:formatNumber value="${user.DAMAGES_MONEY }" pattern="#,#00.00" /></td>
					<td>${user.OVERDUE_STATR}</td>
					<td>￥<fmt:formatNumber value="${user.SPARE_FIELD06 }" pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber value="${user.SPARE_FIELD03 }" pattern="#,#00.00" /></td>
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
