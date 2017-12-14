<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanApply/listXhCarLoanApply" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>借款申请额度:</label>
				<input type="text" name="filter_jkLoanQuota" value="${map.jkLoanQuota}"/>
			</li>
			<li>
				<label>借款成数（借款总额/车辆评估金额）:</label>
				<input type="text" name="filter_loanScale" value="${map.loanScale}"/>
			</li>
			<li>
				<label>综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）:</label>
				<input type="text" name="filter_comlpexMoney" value="${map.comlpexMoney}"/>
			</li>
			<li>
				<label>借款周期:</label>
				<input type="text" name="filter_jkCycle" value="${map.jkCycle}"/>
			</li>
			<li>
				<label>借款类型GPS移交:</label>
				<input type="text" name="filter_jkType" value="${map.jkType}"/>
			</li>
			<li>
				<label>申请日期:</label>
				<input type="text" name="filter_jkLoanDate" value="${map.jkLoanDate}"/>
			</li>
			<li>
				<label>开卡（省/市）:</label>
				<input type="text" name="filter_bankCity" value="${map.bankCity}"/>
			</li>
			<li>
				<label>账户开户行:</label>
				<input type="text" name="filter_bankOpen" value="${map.bankOpen}"/>
			</li>
			<li>
				<label>账户名称:</label>
				<input type="text" name="filter_bankUsername" value="${map.bankUsername}"/>
			</li>
			<li>
				<label>账户号码:</label>
				<input type="text" name="filter_bankNum" value="${map.bankNum}"/>
			</li>
			<li>
				<label>组织:</label>
				<input type="text" name="filter_organiId" value="${map.organiId}"/>
			</li>
			<li>
				<label>管辖城市(做查询依据):</label>
				<input type="text" name="filter_crmcity" value="${map.crmcity}"/>
			</li>
			<li>
				<label>管辖省份(做查询依据):</label>
				<input type="text" name="filter_crmprovince" value="${map.crmprovince}"/>
			</li>
			<li>
				<label>团队经理(做查询依据):</label>
				<input type="text" name="filter_teamLeaderId" value="${map.teamLeaderId}"/>
			</li>
			<li>
				<label>客户经理(做查询依据):</label>
				<input type="text" name="filter_customerLeaderId" value="${map.customerLeaderId}"/>
			</li>
			<li>
				<label>客服(做查询依据):</label>
				<input type="text" name="filter_employeeServiceStaff" value="${map.employeeServiceStaff}"/>
			</li>
			<li>
				<label>销售(做查询依据):</label>
				<input type="text" name="filter_employeeSeller" value="${map.employeeSeller}"/>
			</li>
			<li>
				<label>提交状态:</label>
				<input type="text" name="filter_subState" value="${map.subState}"/>
			</li>
			<li>
				<label>借款申请流程状态，状态参考实体:</label>
				<input type="text" name="filter_state" value="${map.state}"/>
			</li>
			<li>
				<label>借款编号:</label>
				<input type="text" name="filter_loanCode" value="${map.loanCode}"/>
			</li>
			<li>
				<label>备用字段01:</label>
				<input type="text" name="filter_backup01" value="${map.backup01}"/>
			</li>
			<li>
				<label>备用字段02:</label>
				<input type="text" name="filter_backup02" value="${map.backup02}"/>
			</li>
			<li>
				<label>备用字段03:</label>
				<input type="text" name="filter_backup03" value="${map.backup03}"/>
			</li>
			<li>
				<label>备用字段04:</label>
				<input type="text" name="filter_backup04" value="${map.backup04}"/>
			</li>
			<li>
				<label>备用字段05:</label>
				<input type="text" name="filter_backup05" value="${map.backup05}"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/xhCarLoanApply/addXhCarLoanApply" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhCarLoanApply/batchdelXhCarLoanApply" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhCarLoanApply/editXhCarLoanApply/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="jkLoanQuota" class="asc">借款申请额度</th>
				<th width="80" orderField="loanScale" class="asc">借款成数（借款总额/车辆评估金额）</th>
				<th width="80" orderField="comlpexMoney" class="asc">综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）</th>
				<th width="80" orderField="jkCycle" class="asc">借款周期</th>
				<th width="80" orderField="jkType" class="asc">借款类型GPS移交</th>
				<th width="80" orderField="jkLoanDate" class="asc">申请日期</th>
				<th width="80" orderField="bankCity" class="asc">开卡（省/市）</th>
				<th width="80" orderField="bankOpen" class="asc">账户开户行</th>
				<th width="80" orderField="bankUsername" class="asc">账户名称</th>
				<th width="80" orderField="bankNum" class="asc">账户号码</th>
				<th width="80" orderField="organiId" class="asc">组织</th>
				<th width="80" orderField="crmcity" class="asc">管辖城市(做查询依据)</th>
				<th width="80" orderField="crmprovince" class="asc">管辖省份(做查询依据)</th>
				<th width="80" orderField="teamLeaderId" class="asc">团队经理(做查询依据)</th>
				<th width="80" orderField="customerLeaderId" class="asc">客户经理(做查询依据)</th>
				<th width="80" orderField="employeeServiceStaff" class="asc">客服(做查询依据)</th>
				<th width="80" orderField="employeeSeller" class="asc">销售(做查询依据)</th>
				<th width="80" orderField="subState" class="asc">提交状态</th>
				<th width="80" orderField="state" class="asc">借款申请流程状态，状态参考实体</th>
				<th width="80" orderField="loanCode" class="asc">借款编号</th>
				<th width="80" orderField="backup01" class="asc">备用字段01</th>
				<th width="80" orderField="backup02" class="asc">备用字段02</th>
				<th width="80" orderField="backup03" class="asc">备用字段03</th>
				<th width="80" orderField="backup04" class="asc">备用字段04</th>
				<th width="80" orderField="backup05" class="asc">备用字段05</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.jkLoanQuota}</td>
				<td>${user.loanScale}</td>
				<td>${user.comlpexMoney}</td>
				<td>${user.jkCycle}</td>
				<td>${user.jkType}</td>
				<td>${user.jkLoanDate}</td>
				<td>${user.bankCity}</td>
				<td>${user.bankOpen}</td>
				<td>${user.bankUsername}</td>
				<td>${user.bankNum}</td>
				<td>${user.organiId}</td>
				<td>${user.crmcity}</td>
				<td>${user.crmprovince}</td>
				<td>${user.teamLeaderId}</td>
				<td>${user.customerLeaderId}</td>
				<td>${user.employeeServiceStaff}</td>
				<td>${user.employeeSeller}</td>
				<td>${user.subState}</td>
				<td>${user.state}</td>
				<td>${user.loanCode}</td>
				<td>${user.backup01}</td>
				<td>${user.backup02}</td>
				<td>${user.backup03}</td>
				<td>${user.backup04}</td>
				<td>${user.backup05}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhCarLoanApply/delXhCarLoanApply/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhCarLoanApply/editXhCarLoanApply/${user.id}" class="btnEdit">编辑</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
				<option value="10" <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20" <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50" <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>
