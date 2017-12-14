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
		action="${ctx }/cjrxx/listcjrxx" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>出借编号:</label> <input type="text"
						name="filter_jkrbh" /></td>
					<td><label>出借人姓名:</label> <input type="text"
						name="filter_jkrxm" /></td>
					<td><label>CRM:</label> <input type="text" name="filter_cjrxm" /></td>
					<td><label>CCA:</label> <input type="text" name="filter_cjrxm" />
					</td>
				<tr>
					<td><label>CRM城市：</label> <select class="combox"
						name="filter_crmprovince" ref="combox_cjrcrmcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${cjrxx.crmprovince==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_crmcity"
						id="combox_cjrcrmcity" ref="combox_crmarea"
						refUrl="${ctx}/cjrxx/getArea?code={value}">
							<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有城市</option>
					</select></td>
					<td><label>逾期日期:</label> <input type="text" name="filter_tzrq" />-<input
						type="text" name="filter_kurq" /></td>
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
				<th width="80">出借人</th>
				<th width="100">出借人身份证号</th>
				<th width="100" orderField="createTime">出借时间</th>
				<th width="80">出借人编号</th>
				<th width="80">理财产品</th>
				<th width="80">营销团队经理</th>
				<th width="80">营销人</th>
				<th width="80">实收手续费</th>
				<th width="80">利息期止期限</th>
			</tr>
		</thead>
		<tr target="sid_user" rel="${user.id}">
			<td>1</td>
			<td>张三</td>
			<td>2311020301</td>
			<td>2012-01-02</td>
			<td>001</td>
			<td>年年盈</td>
			<td>李四</td>
			<td>test</td>
			<td>test</td>
			<td>test</td>
		</tr>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.cjrxm }</td>
					<td>${user.khbm }</td>
					<td>${user.gxcs }</td>
					<td>${user.employeeCrm.name }</td>
					<td>${user.employeeCca.name }</td>
					<td><c:if test="${user.ztFlag=='0'}">在用</c:if> <c:if
							test="${user.ztFlag=='1'}">历史</c:if></td>
					<td>${user.cjryx }</td>
					<td>${user.createTime }</td>
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
