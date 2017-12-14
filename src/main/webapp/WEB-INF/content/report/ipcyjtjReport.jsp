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
		action="${ctx}/report/yjtj/listIpcyjtjAll" method="post">
		<div class="searchBar">
			
		<%-- <div class="searchBar">
			<table  width="100%">
				<tr>
					<td><label>客户姓名:</label> <input type="text"
						name="filter_jkrxm" value="${filter_jkrxm }" /></td>
					<td><label>客户电话:</label> <input type="text"
						name="filter_telephone" value="${filter_telephone }" /></td>
					<td><label>证件号码:</label> <input type="text" name="filter_zjhm"
						value="${filter_zjhm }" /></td>
				</tr>
				<tr>

					<td><label>所属城市:</label> <select name="filter_province"
						ref="jksqlistbox_city" class="combox"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${filter_province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select id="jksqlistbox_city" name="filter_city" class="combox"
						refUrl="${ctx}/cjrxx/getArea?code={value}">
							<option value="" <c:if test="${filter_city==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${city}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_city==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select></td>
					<td><label>团队经理:</label> <input type="text"
						name="filter_teamName" value="${filter_teamName }" /></td>
					<td><label>销售人员:</label> <input type="text"
						name="filter_saleName" value="${filter_saleName }" /></td>
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
		</div>  --%>
		
		<table width="100%">
		
				<tr>
				
					<td colspan="2"><label>放款日期：</label> <input 
						name="filter_startdate" value="${map.startdate }" type="text" size="8" class="date" readonly="readonly">
					至
					<input  name="filter_overdate"
						value="${map.overdate }" type="text" size="8" class="date" readonly="readonly"></td>
					<%-- <td>
						<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从放款日期：</label> 
						<input id="cfmd_date" name="filter_startdate" type="text" size="20" value="${map.startdate }" class="date required"
								dateFmt="yyyy-MM-dd"  readonly="readonly" />
					</td><td>
						<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到放款日期：</label> 
						<input id="cfmd_date" name="filter_overdate" type="text" size="20" value="${map.overdate }" class="date required"
								dateFmt="yyyy-MM-dd"  readonly="readonly" /> --%>
						
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
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=ipcyjtjExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1260px" layoutH="116" nowrapTD="false">
		<thead>
			<tr>
			    <th width="30px">序号</th>	
				<th width="80px">合同编号</th>	
				<th width="80px">机构</th>			
				<th width="60px">团队</th>
				<th width="60px">团队经理</th>
				<th width="60px">客户经理</th>
				<th width="60px">员工代码</th>
				<th width="60px">合同金额</th>
				<th width="60px">放款金额</th>
				<th width="60px">产品类型</th>
				<th width="40px">综合费率</th>
				<th width="30px">期数</th>
				<th width="60px">放款日期</th>
				<th width="60px">月还款额</th>
				<th width="60px">借款余额</th>
				<th width="60px">客户姓名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="singleItem" varStatus="st">
				<tr>
					<td>${singleItem.ROWNUM}</td>	
					<td>${singleItem.JKHTBM}</td>	
					<td>IPC</td>
					<td>IPC</td>
					<td></td>
					<td>${singleItem.KF_EMP}</td>
					<td>${singleItem.EMPLOYEEBH}</td>
					<td><fmt:formatNumber value="${singleItem.HTJE}" pattern="#,###.00" /></td>
					<td><fmt:formatNumber value="${singleItem.FKJE}" pattern="#,###.00" /></td>
					<td>${singleItem.DESCRIPTION}</td>
					<td>${singleItem.YZHFL}</td>
					<td>${singleItem.HKQS }</td>
					<td>${singleItem.MAKELOANDATE }</td>
					<td>${singleItem.YHKJE }</td>
					<td>${singleItem.SYJE }</td>
					<td>${singleItem.JKRXM}</td>
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