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
		action="${ctx}/report/loanBack/listLoanBackAll" method="post">
		 <div class="searchBar">
			<table  width="100%">
				<tr>
					<td><label>客户姓名:</label> <input type="text"
						name="filter_jkrxm" value="${map.jkrxm }" /></td>
					<td><label>放款日期:</label> <input name="filter_jhtzrq" type="text"
						size="20" value="${map.jhtzrq}" class="date" dateFmt="yyyy-MM"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>
					<td><label>机构:</label> 
					<select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${!empty map.yyb}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.ORGID }"
									<c:if test="${map.yyb==md.ORGID}">selected</c:if>>${md.ORGNAZATIONNAME}</option>
							</c:forEach>
					</select></td>
					<td>
					<!-- 未完成----------------------------- -->
						<label>当前状态:</label>
						<select class="required combox" name="filter_state">
							<option value="" <c:if test="${map.state==''}">selected</c:if>>请选择</option>
							<option value="61" <c:if test="${map.state=='61'}">selected</c:if>>已放款</option>
							<option value="100" <c:if test="${map.state=='100'}">selected</c:if>>还款中</option>
							<option value="101" <c:if test="${map.state=='101'}">selected</c:if>>提前结清</option>
							<option value="102" <c:if test="${map.state=='102'}">selected</c:if>>结清</option>
							<option value="103" <c:if test="${map.state=='103'}">selected</c:if>>逾期</option>
						</select>
					<!-- 结束----------------------------- -->
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
				</ul>
			</div>
		</div>  
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=loanBackExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1260px" layoutH="140" nowrapTD="true">
		<thead>
			<tr>
			    <th width="30px">序号</th>	
				<th width="80px">合同编号</th>	
				<th width="80px">客户名称</th>	
				<th width="60px">当前状态</th>
				<th width="60px">合同金额</th>
				<th width="40px">期数</th>
				<th width="120px">机构</th>	
				<th width="70px">产品类型</th>	
						
				<th width="80px">放款日期</th>
				<th width="80px">应还日期</th>
				<th width="60px">应还款总额</th>		
                <th width="60px">应还本金</th>	
				<th width="60px">应还利息</th>
                <th width="60px">借款余额</th>
				<th width="60px">剩余期数</th>
				
				<!-- 
				<th width="80px">还款方式</th>
				<th width="60px">综合费率%</th>
				<th width="70px">实收费率%</th>
				<th width="60px">服务费</th>
				<th width="60px">罚金</th>
				 --> 
				 
				<th width="80px">实际还款日期</th>
				<th width="60px">实还总额</th>
				<th width="70px">实还本金</th>
				<th width="60px">实还利息</th>
				<th width="60px">借款余额</th>
				<th width="60px">未付利息</th>
				<th width="60px">存款余额</th>
				<th width="60px">提前还款金额</th>
				<th width="60px">逾期借款金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="singleItem" varStatus="st">
     			<tr>
					<td>${singleItem.ROWNUM}</td>
					<td>${singleItem.JKHTBM}</td>	
					<td>${singleItem.CUSTOMERNAME}</td>	
					<td>${singleItem.ACCOUNTSTATE}</td>
					<td><fmt:formatNumber value="${singleItem.HTJE}" pattern="#,###.##" /></td>
					<td>${singleItem.HKQS}</td>
					<td>${singleItem.ORGNAZATIONNAME}</td>	
					<td>${singleItem.LOANTYPE}</td>		
						
					<td>${singleItem.MAKELOANDATE}</td>
					<td>${singleItem.NEXTDATEREAL}</td>
					<td><fmt:formatNumber value="${singleItem.YHKZE}" pattern="#,###.00" /></td>
					<td><fmt:formatNumber value="${singleItem.SJHKBJ}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.YHKLX}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.SYJE}" pattern="#,###.##" /></td>
					<td>${singleItem.SYHKYS}</td>
					<!-- 
					<td>${singleItem.RETURNWAY}</td>
					<td>${singleItem.YZHFL}</td>
					<td>${singleItem.DKLL}</td>
					<td><fmt:formatNumber value="${singleItem.FWF}" pattern="#,###.##" /></td>
					<td>${singleItem.PENAL}</td>
					 -->
					 <td>${singleItem.REALTIME}</td>
					 <td><fmt:formatNumber value="${singleItem.REALMONEY}" pattern="#,###.##" /></td>
					 <td><fmt:formatNumber value="${singleItem.SJHKBJ}" pattern="#,###.##" /></td>
					 <td><fmt:formatNumber value="${singleItem.SJHKLX}" pattern="#,###.##" /></td>
					 <td><fmt:formatNumber value="${singleItem.SYJE}" pattern="#,###.##" /></td>
					 <td><fmt:formatNumber value="${singleItem.REALDKLL}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.ACCOUNT_BALANCE}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.TQJQ}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${singleItem.YQJKZE}" pattern="#,###.##" /></td>
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