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
		action="${ctx }/tzsq/listZhuanRaAuditTzsq" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>客户编码:</label> <input type="text" name="filter_khbm" />
					</td>
					<td><label>客户姓名:</label> <input type="text"
						name="filter_cjrxm" /></td>
					<td><label>是否紧急:</label> <select class="combox"
						name="filter_ztFlag">
							<option value="">全部</option>
							<option value="0">是</option>
							<option value="1">否</option>
					</select></td>
				</tr>
				<tr>
					<td><label>意向转让日期:</label> <input type="text"
						name="filter_tzrq" /> 至<input type="text" name="filter_tzrq" /></td>
					<td><label>CRM城市：</label> <select class="combox"
						name="filter_province" ref="combox_cjrcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${cjrxx.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city" id="combox_cjrcity">
							<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有城市</option>
					</select></td>
					<td><label>CRM:</label> <input type="text" name="filter_cjrxm" />
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" orderField="cjrxm" class="asc">客户姓名</th>
				<th width="80" orderField="khbm">客户编码</th>
				<th width="80">出借编号</th>
				<th width="80">申请日期</th>
				<th width="80">意向转让日期</th>
				<th width="100">所在城市</th>
				<th width="100">CRM</th>
				<th width="100">是否紧急</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="${user.id}">
				<td>${user.cjrxm }</td>
				<td>${user.khbm }</td>
				<td>${user.gxcs }</td>
				<td>${user.employeeCrm.name }</td>
				<td>${user.employeeCca.name }</td>
				<td>${user.cjryx }</td>
				<td>${user.employeeCrm.name }</td>
				<td>${user.createTime }</td>
				<td><a title="查看" target="navTab"
					href="${ctx }/tzsq/lookOutTzsq/${user.id}">查看</a> <a title="转让审批"
					target="navTab" href="${ctx }/tzsq/zhuanRaAuditTzsq/${user.id}">转让审批</a>
				</td>
			</tr>
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
