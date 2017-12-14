<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		action="${ctx }/report/zwdq" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>客户编号：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					<td>
					<label>营业部：</label> <select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${map.yyb==''}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.YYBID }"
									<c:if test="${map.yyb==md.YYBID}">selected</c:if>>${md.YYB}</option>
							</c:forEach>
					</select></td>
					<td>
					</td>
				</tr>
				<tr>
					<td><label>所在城市：</label> 
						 <sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${map.province},${map.city}"/>
							</td>
					<td colspan="2"><label>到期日期：</label> <input 
						name="filter_startdate" value="${map.startdate }" type="text" size="8" class="date" readonly="readonly">
					至
					<input  name="filter_overdate"
						value="${map.overdate }" type="text" size="8" class="date" readonly="readonly"></td>
					
					<td>
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
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=zwdqExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1260px" layoutH="140" nowrapTD="false">
		<thead>
			<tr>
				<th width="28px">序号</th>
				<th width="60px">客户姓名</th>			
				<th width="60px">产品类型</th>
				<th width="130px">销售折扣率%</th>
				<th width="120px">销售折扣率有效期限</th>
				<th width="80px">产品到期日</th>
				<th width="80px">是否转让</th>
				<th width="70px">团队经理</th>
				<th width="70px">理财经理</th>
				<th width="80px">团队</th>
				<th width="40px">账单日</th>
				<th width="80px">第一账单日</th>
				<th width="70px">出借金额</th>
				<th width="70px">利率</th>
				<th width="100px">债权返款金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user">
					<td>${user.RONUM }</td>
					<td>${user.CJRXM }</td>
					<td>${user.TZCP_MC}</td>
					<td>${user.XSZKLY}</td>
					<td>${user.XSZKLYXQX }</td>
					<td>${user.MOQI }</td>
					<td></td>
					<td>${user.EMPLOYEE_CCA_NAME }</td>
					<td>${user.EMPLOYEE_CRM_NAME }</td>
					<td>${user.KFTD }</td>
					<td>${user.ZDR }</td>
					<td>${user.SGHKRQ }</td>
					<td>${user.JHTZJE }</td>
					<td></td>
					<td>￥<fmt:formatNumber
							value="${user.LENTMONEY}" pattern="#,#00.00" /></td>
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
