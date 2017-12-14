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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanApplyComplement/listXhCarLoanApplyComplement" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>审核户籍地址:</label>
				<input type="text" name="filter_auditHjadress" value="${map.auditHjadress}"/>
			</li>
			<li>
				<label>身份真伪验证:</label>
				<input type="text" name="filter_auditZjhm" value="${map.auditZjhm}"/>
			</li>
			<li>
				<label>审核暂住证:</label>
				<input type="text" name="filter_auditTemporary" value="${map.auditTemporary}"/>
			</li>
			<li>
				<label>审核客户人法:</label>
				<input type="text" name="filter_auditPersonlaw" value="${map.auditPersonlaw}"/>
			</li>
			<li>
				<label>审核现住址:</label>
				<input type="text" name="filter_auditHomeadress" value="${map.auditHomeadress}"/>
			</li>
			<li>
				<label>114电话查询情况:</label>
				<input type="text" name="filter_audit114" value="${map.audit114}"/>
			</li>
			<li>
				<label>客户工作审核情况:</label>
				<input type="text" name="filter_auditWork" value="${map.auditWork}"/>
			</li>
			<li>
				<label>征信报告显示情况:</label>
				<input type="text" name="filter_auditCredit" value="${map.auditCredit}"/>
			</li>
			<li>
				<label>评估金额:</label>
				<input type="text" name="filter_assessMoney" value="${map.assessMoney}"/>
			</li>
			<li>
				<label>建议借款额:</label>
				<input type="text" name="filter_suggestMoney" value="${map.suggestMoney}"/>
			</li>
			<li>
				<label>评估师姓名:</label>
				<input type="text" name="filter_assessPerson" value="${map.assessPerson}"/>
			</li>
			<li>
				<label>违章及事故情况:</label>
				<input type="text" name="filter_breakRules" value="${map.breakRules}"/>
			</li>
			<li>
				<label>车辆评估报告结论:</label>
				<input type="text" name="filter_assessFinish" value="${map.assessFinish}"/>
			</li>
			<li>
				<label>外观监测:</label>
				<input type="text" name="filter_visualInspection" value="${map.visualInspection}"/>
			</li>
			<li>
				<label>车年检情况(有无):</label>
				<input type="text" name="filter_inspectionFlag" value="${map.inspectionFlag}"/>
			</li>
			<li>
				<label>车年检情况:</label>
				<input type="text" name="filter_inspection" value="${map.inspection}"/>
			</li>
			<li>
				<label>交强险(有无):</label>
				<input type="text" name="filter_trafficInsuranceFlag" value="${map.trafficInsuranceFlag}"/>
			</li>
			<li>
				<label>交强险:</label>
				<input type="text" name="filter_trafficInsurance" value="${map.trafficInsurance}"/>
			</li>
			<li>
				<label>商业险(有无):</label>
				<input type="text" name="filter_businessInsuranceFlag" value="${map.businessInsuranceFlag}"/>
			</li>
			<li>
				<label>商业险:</label>
				<input type="text" name="filter_businessInsurance" value="${map.businessInsurance}"/>
			</li>
			<li>
				<label>车架号:</label>
				<input type="text" name="filter_chassisNumber" value="${map.chassisNumber}"/>
			</li>
			<li>
				<label>出厂日期:</label>
				<input type="text" name="filter_madeTime" value="${map.madeTime}"/>
			</li>
			<li>
				<label>车牌号码:</label>
				<input type="text" name="filter_plate" value="${map.plate}"/>
			</li>
			<li>
				<label>登记日期:</label>
				<input type="text" name="filter_registerTime" value="${map.registerTime}"/>
			</li>
			<li>
				<label>车辆厂牌型号:</label>
				<input type="text" name="filter_lable" value="${map.lable}"/>
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
			<li><a class="add" href="${ctx}/xhCarLoanApplyComplement/addXhCarLoanApplyComplement" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhCarLoanApplyComplement/batchdelXhCarLoanApplyComplement" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhCarLoanApplyComplement/editXhCarLoanApplyComplement/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" orderField="auditHjadress" class="asc">审核户籍地址</th>
				<th width="80" orderField="auditZjhm" class="asc">身份真伪验证</th>
				<th width="80" orderField="auditTemporary" class="asc">审核暂住证</th>
				<th width="80" orderField="auditPersonlaw" class="asc">审核客户人法</th>
				<th width="80" orderField="auditHomeadress" class="asc">审核现住址</th>
				<th width="80" orderField="audit114" class="asc">114电话查询情况</th>
				<th width="80" orderField="auditWork" class="asc">客户工作审核情况</th>
				<th width="80" orderField="auditCredit" class="asc">征信报告显示情况</th>
				<th width="80" orderField="assessMoney" class="asc">评估金额</th>
				<th width="80" orderField="suggestMoney" class="asc">建议借款额</th>
				<th width="80" orderField="assessPerson" class="asc">评估师姓名</th>
				<th width="80" orderField="breakRules" class="asc">违章及事故情况</th>
				<th width="80" orderField="assessFinish" class="asc">车辆评估报告结论</th>
				<th width="80" orderField="visualInspection" class="asc">外观监测</th>
				<th width="80" orderField="inspectionFlag" class="asc">车年检情况(有无)</th>
				<th width="80" orderField="inspection" class="asc">车年检情况</th>
				<th width="80" orderField="trafficInsuranceFlag" class="asc">交强险(有无)</th>
				<th width="80" orderField="trafficInsurance" class="asc">交强险</th>
				<th width="80" orderField="businessInsuranceFlag" class="asc">商业险(有无)</th>
				<th width="80" orderField="businessInsurance" class="asc">商业险</th>
				<th width="80" orderField="chassisNumber" class="asc">车架号</th>
				<th width="80" orderField="madeTime" class="asc">出厂日期</th>
				<th width="80" orderField="plate" class="asc">车牌号码</th>
				<th width="80" orderField="registerTime" class="asc">登记日期</th>
				<th width="80" orderField="lable" class="asc">车辆厂牌型号</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.auditHjadress}</td>
				<td>${user.auditZjhm}</td>
				<td>${user.auditTemporary}</td>
				<td>${user.auditPersonlaw}</td>
				<td>${user.auditHomeadress}</td>
				<td>${user.audit114}</td>
				<td>${user.auditWork}</td>
				<td>${user.auditCredit}</td>
				<td>${user.assessMoney}</td>
				<td>${user.suggestMoney}</td>
				<td>${user.assessPerson}</td>
				<td>${user.breakRules}</td>
				<td>${user.assessFinish}</td>
				<td>${user.visualInspection}</td>
				<td>${user.inspectionFlag}</td>
				<td>${user.inspection}</td>
				<td>${user.trafficInsuranceFlag}</td>
				<td>${user.trafficInsurance}</td>
				<td>${user.businessInsuranceFlag}</td>
				<td>${user.businessInsurance}</td>
				<td>${user.chassisNumber}</td>
				<td>${user.madeTime}</td>
				<td>${user.plate}</td>
				<td>${user.registerTime}</td>
				<td>${user.lable}</td>
				<td>
					<a title="删除" target="ajaxTodo" href="${ctx }/xhCarLoanApplyComplement/delXhCarLoanApplyComplement/${user.id}" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="${ctx }/xhCarLoanApplyComplement/editXhCarLoanApplyComplement/${user.id}" class="btnEdit">编辑</a>
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
