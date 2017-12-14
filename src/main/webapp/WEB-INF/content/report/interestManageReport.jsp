<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx}/report/interestManage/listInterestManageAll" method="post">
				<div class="searchBar">
			<table width="100%">
				<tr>
					<td>
					    <label style="width: 100px">选择机构/部门：</label>
					    <select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${!empty map.yyb}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.ORGID }"
									<c:if test="${map.yyb==md.ORGID}">selected</c:if>>${md.ORGNAZATIONNAME}</option>
							</c:forEach>
					</select>
						
					</td> 
					
					<td><label style="width: 100px">实际还款日期:</label> <input 
						name="filter_realtimestart" value="${map.realtimestart }" type="text" size="8" class="date" readonly="readonly"/>
					至
					<input  name="filter_realtimeend"
						value="${map.realtimeend }" type="text" size="8" class="date" readonly="readonly"/>
						</td>
					
					<td>
						<div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button id="enableExport" type="submit">检索</button>
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
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=interestManageExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1260px" layoutH="116" nowrapTD="false">
		<thead>
			<tr>
			    <th width="30px">序号</th>	
			    <th width="50px">机构</th>	
			    <th width="50px">合同号</th>	
			    <th width="50px">期数</th>	
				<th width="50px">借款人</th>			
				<th width="60px">借款金额</th>
				<th width="60px">放款金额</th>
				<th width="60px">借款日期</th>
				<th width="60px">合同状态</th>
				<th width="60px">应还款日期</th>
				<th width="60px">实际还款日期</th>
				<th width="50px">月利率%</th>
				<th width="60px">本金</th>
				<th width="60px">利息</th>
				<th width="60px">罚息</th>
				<th width="60px">实际还款合计</th>		
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${datas}" var="singleItem" varStatus="st">
     			<tr>
					<td>${singleItem.ROWNUM}</td>	
					<td>${singleItem.YYB}</td>	
					<td>${singleItem.JKHTBM}</td>	
					<td>${singleItem.HKQS}</td>	
					
					<td>${singleItem.JKRXM}</td>			
					<td><fmt:formatNumber value="${singleItem.HTJE}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.FKJE}" pattern="#,###.##" /></td>
					<td>${singleItem.SIGNDATE}</td>
					<td>${singleItem.ACCOUNTSTATE}</td>
					<td>${singleItem.NEXTDATE}</td>
					<td>${singleItem.REALTIME}</td>
					<td>${singleItem.DKLL}</td>
					<td><fmt:formatNumber value="${singleItem.YBJJE}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.YLXJE}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.PUNISH_INTEREST}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.REALMONEY}" pattern="#,###.##" /></td>
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