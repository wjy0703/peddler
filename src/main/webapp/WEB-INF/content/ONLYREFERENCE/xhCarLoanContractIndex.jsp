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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanContract/listXhCarLoanContract" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>合同编号:</label>
				<input type="text" name="filter_contractNum" value="${map.contractNum}"/>
			</li>
			<li>
				<label>合同金额:</label>
				<input type="text" name="filter_contractMoney" value="${map.contractMoney}"/>
			</li>
			<li>
				<label>合同签订日期:</label>
				<input type="text" name="filter_qdrq" value="${map.qdrq}"/>
			</li>
			<li>
				<label>合同日期:</label>
				<input type="text" name="filter_contractDate" value="${map.contractDate}"/>
			</li>
			<li>
				<label>0：待签约 1：已签约上传   -1：取消:</label>
				<input type="text" name="filter_state" value="${map.state}"/>
			</li>
			<li>
				<label>中间人ID:</label>
				<input type="text" name="filter_middleManId" value="${map.middleManId}"/>
			</li>
			<li>
				<label>利息率（移交：0.5%；GPS：0.475%）:</label>
				<input type="text" name="filter_dkll" value="${map.dkll}"/>
			</li>
			<li>
				<label>总费率（移交：3.5%；GPS：5%  ，客户经理可录入）:</label>
				<input type="text" name="filter_dkllComlpex" value="${map.dkllComlpex}"/>
			</li>
			<li>
				<label>利息（借款总额*利息率）:</label>
				<input type="text" name="filter_interest" value="${map.interest}"/>
			</li>
			<li>
				<label>总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)）:</label>
				<input type="text" name="filter_serveComlpexMoney" value="${map.serveComlpexMoney}"/>
			</li>
			<li>
				<label>咨询费（综合息费*59%）:</label>
				<input type="text" name="filter_adviceFee" value="${map.adviceFee}"/>
			</li>
			<li>
				<label>审核费（综合息费*5%）:</label>
				<input type="text" name="filter_auditFee" value="${map.auditFee}"/>
			</li>
			<li>
				<label>服务费（综合息费-咨询费-审核费）:</label>
				<input type="text" name="filter_serviceFee" value="${map.serviceFee}"/>
			</li>
			<li>
				<label>借款合同ID:</label>
				<input type="text" name="filter_xhJkhtId" value="${map.xhJkhtId}"/>
			</li>
			<li>
				<label>还款日:</label>
				<input type="text" name="filter_hkr" value="${map.hkr}"/>
			</li>
			<li>
				<label>展期期数:</label>
				<input type="text" name="filter_noExtension" value="${map.noExtension}"/>
			</li>
			<li>
				<label>是否展期:</label>
				<input type="text" name="filter_isExtension" value="${map.isExtension}"/>
			</li>
			<li>
				<label>备注:</label>
				<input type="text" name="filter_remark" value="${map.remark}"/>
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
			<li><a class="add" href="${ctx}/xhCarLoanContract/addXhCarLoanContract" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhCarLoanContract/batchdelXhCarLoanContract" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhCarLoanContract/editXhCarLoanContract/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="contractNum" class="asc">合同编号</th>
				<th width="80" orderField="contractMoney" class="asc">合同金额</th>
				<th width="80" orderField="qdrq" class="asc">合同签订日期</th>
				<th width="80" orderField="contractDate" class="asc">合同日期</th>
				<th width="80" orderField="state" class="asc">0：待签约 1：已签约上传   -1：取消</th>
				<th width="80" orderField="middleManId" class="asc">中间人ID</th>
				<th width="80" orderField="dkll" class="asc">利息率（移交：0.5%；GPS：0.475%）</th>
				<th width="80" orderField="dkllComlpex" class="asc">总费率（移交：3.5%；GPS：5%  ，客户经理可录入）</th>
				<th width="80" orderField="interest" class="asc">利息（借款总额*利息率）</th>
				<th width="80" orderField="serveComlpexMoney" class="asc">总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)）</th>
				<th width="80" orderField="adviceFee" class="asc">咨询费（综合息费*59%）</th>
				<th width="80" orderField="auditFee" class="asc">审核费（综合息费*5%）</th>
				<th width="80" orderField="serviceFee" class="asc">服务费（综合息费-咨询费-审核费）</th>
				<th width="80" orderField="xhJkhtId" class="asc">借款合同ID</th>
				<th width="80" orderField="hkr" class="asc">还款日</th>
				<th width="80" orderField="noExtension" class="asc">展期期数</th>
				<th width="80" orderField="isExtension" class="asc">是否展期</th>
				<th width="80" orderField="remark" class="asc">备注</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.contractNum}</td>
				<td>${user.contractMoney}</td>
				<td>${user.qdrq}</td>
				<td>${user.contractDate}</td>
				<td>${user.state}</td>
				<td>${user.middleManId}</td>
				<td>${user.dkll}</td>
				<td>${user.dkllComlpex}</td>
				<td>${user.interest}</td>
				<td>${user.serveComlpexMoney}</td>
				<td>${user.adviceFee}</td>
				<td>${user.auditFee}</td>
				<td>${user.serviceFee}</td>
				<td>${user.xhJkhtId}</td>
				<td>${user.hkr}</td>
				<td>${user.noExtension}</td>
				<td>${user.isExtension}</td>
				<td>${user.remark}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhCarLoanContract/delXhCarLoanContract/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhCarLoanContract/editXhCarLoanContract/${user.id}" class="btnEdit">编辑</a>
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
