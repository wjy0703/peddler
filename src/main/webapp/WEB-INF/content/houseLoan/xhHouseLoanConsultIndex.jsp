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
		action="${ctx }/house/listXhHouseLoanConsult" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>客户姓名:</label> <input type="text"
					name="filter_customerName" value="${map.customerName}" /></li>

				<li><label>身份证号码:</label> <input type="text"
					name="filter_identificationCard" value="${map.identificationCard}" />
				</li>


				<li><label>电话:</label> <input type="text"
					name="filter_customerTel" value="${map.customerTel}" /></li>


				<li><label>客户经理:</label> <input type="text"
					name="filter_customrerManagerId" value="${map.customrerManagerId}" />
				</li>
				<li><label>团队经理:</label> <input type="text"
					name="filter_teamManagerId" value="${map.teamManagerId}" /></li>

			</ul>
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
			<li><a class="add" href="${ctx}/house/addXhHouseLoanConsult"
				target="navTab"><span>添加</span></a></li>
			<!-- 	<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhHouseLoanConsult/batchdelXhHouseLoanConsult" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			 -->
			<li><a class="edit"
				href="${ctx}/house/editXhHouseLoanConsult/{sid_user}"
				target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80">客户姓名</th>
				<th width="80">性别</th>
				<th width="80">身份证号码</th>
				<th width="80">客户来源</th>
				<th width="80">婚否</th>
				<th width="80">电话</th>

				<th width="80">房产性质</th>
				<th width="80">使用年限</th>
				<th width="80">所属区县</th>



				<th width="80">客户经理</th>
				<th width="80">团队经理</th>
				<th width="80">涉诉查询</th>

				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.customerName}</td>
					<td>
					<c:if test="${user.customerSex==1}">男</c:if>
					<c:if test="${user.customerSex==0}">女</c:if>
					</td>
					<td>${user.identificationCard}</td>
					<td>
					<c:if test="${user.customerSource==1}">老客户</c:if>
					<c:if test="${user.customerSource==2}">陌Call</c:if>
					<c:if test="${user.customerSource==3}">商超</c:if>
					
					</td>
					<td>
						<c:if test="${user.marrital==1}">是</c:if>
					<c:if test="${user.marrital==0}">否</c:if></td>
					<td>${user.customerTel}</td>

					<td>
					<c:if test="${user.houseType==1}">商品房</c:if>
					<c:if test="${user.houseType==2}">成本价房</c:if>
					<c:if test="${user.houseType==3}">央产房</c:if>
					<c:if test="${user.houseType==4}">经济适用住房</c:if>
					
					</td>
					<td>${user.houseLimit}</td>
					<td>${user.houseRegion}</td>



					<td>${user.customrerManager.name}</td>
					<td>${user.teamManager.name}</td>
					<td>${user.lodgeQuery.name}</td>

					<td>
						<!--  		<a title="删除" target="ajaxTodo" href="${ctx }/house/delXhHouseLoanConsult/${user.id}" class="btnDel">删除</a>-->
							
						<div class="buttonActive">
							<div class="buttonContent">
								<a title="编辑" target="navTab"	href="${ctx }/house/editXhHouseLoanConsult/${user.id}"
						class=""><button type="submit">查看</button></a>
							</div>
						</div>
						<div class="buttonActive">
							<div class="buttonContent">
								<a title="借款申请" target="navTab"
						href="${ctx}/houseApply/addXhHouseLoanApply/${user.id}"
						class=""><button type="submit">申请</button></a>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
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
