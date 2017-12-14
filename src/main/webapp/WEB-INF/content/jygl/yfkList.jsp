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
		action="${ctx }/jygl/listFangKuan" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>借款人编码:</label> <input type="text"
						name="filter_jkrbh" /></td>
					<td><label>借款人名称:</label> <input type="text"
						name="filter_jkrxm" /></td>
					<td><label>放款日期:</label> <input type="text" name="filter_fksj"
						class="date" pattern="yyyy-MM-dd" />- <input type="text"
						name="filter_fksj1" class="date" pattern="yyyy-MM-dd" /> <!-- readonly="true" value="${filter_fksj}" -->
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
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				
				<th width="80" orderField="xh" class="asc">序号</th>
				<th width="80" orderField="jkrbh">借款人编号</th>
				<th width="80" orderField="jkrxm">借款人姓名</th>
				<th width="80">合同编号</th>
				<th width="80">合同金额</th>
				<th width="80">放款金额</th>
				<th width="80">借款状态</th>
				<th width="80">放款时间</th>
				<th width="80">操作人员</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<%int i =1; %>
			<c:forEach items="${listKhzx}" var="FangKuangGuanLi"
				varStatus="st">
				<tr target="sid_FangKuangGuanLi" rel="${FangKuangGuanLi.ID}">
					
					<td><%=i %></td>
					<td>${FangKuangGuanLi.LOAN_CODE }</td>
					<td>${FangKuangGuanLi.JKRXM }</td>
					<td>${FangKuangGuanLi.JKHTBM }</td>
					<td>${FangKuangGuanLi.HTJE }</td>
					<td>${FangKuangGuanLi.FKJE }</td>
					<td><c:if test="${FangKuangGuanLi.JKZT=='end'}">已确认</c:if> 
					    <c:if test="${FangKuangGuanLi.JKZT=='60'}">待放款</c:if>
					    <c:if test="${FangKuangGuanLi.JKZT=='65'}">已放款</c:if>
					</td>
					<td>${FangKuangGuanLi.FKSJ }</td>
					<td>${FangKuangGuanLi.CREATE_BY }</td>	
					<td>
					<div class="buttonActive">
							<div class="buttonContent">
								<a title="查看" target="navTab"
									href="${ctx }/jygl/viewfangkuan/${FangKuangGuanLi.FKID}"><button>查看</button></a>
							</div>
					</div>	
					</td>
					<!--  <td><a title="查看" target="navTab"
						href="${ctx }/jygl/viewfangkuan/${FangKuangGuanLi.id}">查看</a> <a
						title="放款" target="navTab"
						href="${ctx }/jygl/fangkuan/${xydkzx.id}">放款</a></td>-->
				</tr>
				<%i++; %>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"
					<c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
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
