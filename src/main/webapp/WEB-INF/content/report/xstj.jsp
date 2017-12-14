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
		action="${ctx }/report/xstj" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>姓名:</label> <input type="text" name="filter_jkrxm" />
					</td>
					<td><label>进件日期:</label> <input type="text" name="startjjrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />至
				  <input type="text" name="endjjrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				  <!--   <a class="inputDateButton" href="#">选择</a></td>--> </td>
					

					<!-- <td><label>借款类别:</label> <select class="combox"
						name="filter_ztFlag">
							<option value="">全部</option>
							<option value="0">月息通</option>
							<option value="1">年年盈</option>
					</select></td> -->
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
				<li><a class="icon" href="${ctx }/report/exportXstj?filter_jkbh=${filter_jkbh }" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="200%" layoutH="138">
		<thead>
			<tr>

				<th width="80" orderField="xh" class="asc">序号</th>
				<th width="80">进件日期时间</th>
				<th width="80">分派日期时间</th>
				<th width="80">批复日期时间</th>
				<th width="80">借款人姓名</th>
				<th width="80">电话号码</th>
				<th width="80">身份证</th>
				<th width="80">现住址</th>
				<th width="100">借款类别</th>
				<th width="80">区域</th>
				<th width="120">借款本金数</th>
				<th width="80">放款金额</th>
				<th width="80">利率</th>
				<th width="80">服务费</th>
				<th width="120">信访咨询费</th>
				<th width="80">分期</th>
				<th width="80">拒借原因</th>
				<th width="80">团队经理</th>
				<th width="80">客户经理</th>
				<th width="80">审核人</th>
				<th width="100">备注</th>
				<th width="100">信审状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${row.queryList}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.JJRQ }</td>
					<td>${user.FPSJ }</td>
					<td>${user.PFRQ }</td>
					<td>${user.JKRXM }</td>
					<td>${user.TELEPHONE }</td>
					<td>${user.ZJHM }</td>
					<td>${user.HOME_ADDRESS }</td>
					<td>${user.JKLB }</td>
					<td>${user.CITYNAME }</td>
					<td>￥<fmt:formatNumber
							value="${user.CREDIT_AMOUNT }" pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber
							value="${user.FKJE }" pattern="#,#00.00" /></td>
					<td>${user.DKLL }</td>
					<td>￥<fmt:formatNumber
							value="${user.FWF }" pattern="#,#00.00" /></td>
					<td>￥<fmt:formatNumber
							value="${user.XFF }" pattern="#,#00.00" /></td>
					<td>${user.HKQS }</td>
					<td>${user.CREDIT_REFUSE_REASON }</td>
					<td>${user.SALENAME }</td>
					<td>${user.TEAMNAME }</td>
					<td>${user.CREATE_BY }</td>
					<td></td>
					<td>${user.XSZT }</td>
				</tr>
			</c:forEach>
			<c:forEach items="${row.applicCountList}" var="proref" varStatus="st">
			<tr>
				<td>合计</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>￥<fmt:formatNumber
							value="${proref.JKBJS }" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber
							value="${proref.FKJE }" pattern="#,#00.00" /></td>
				<td></td>
				<td>￥<fmt:formatNumber
							value="${proref.FWF }" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber
							value="${proref.ZXF }" pattern="#,#00.00" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<!-- <div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
				<option value="20" <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50" <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>-->
</div>
