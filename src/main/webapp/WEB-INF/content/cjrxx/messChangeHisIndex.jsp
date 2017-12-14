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
		action="${ctx }/cjrxx/listMessChangeHisCjr" method="post">
		<div class="pageFormContent">
			<table>
				<tr>
					<td><label>客户编码：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					<td><label>CRM城市：</label> <select class="combox"
						name="filter_crmprovince" ref="combox_MessChangeHisCjrcrmcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${map.crmprovince==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.crmprovince==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_crmcity"
						id="combox_MessChangeHisCjrcrmcity">
							<option value="" <c:if test="${map.crmcity==''}">selected</c:if>>所有城市</option>
					</select></td>
					<td><label>客户状态：</label> <select class="combox"
						name="filter_state">
							<option value="" <c:if test="${map.state==''}">selected</c:if>>全部</option>
							<option value="0" <c:if test="${map.state=='0'}">selected</c:if>>暂存</option>
							<option value="1" <c:if test="${map.state=='1'}">selected</c:if>>待审批</option>
							<option value="2" <c:if test="${map.state=='2'}">selected</c:if>>审批通过</option>
							<option value="3" <c:if test="${map.state=='3'}">selected</c:if>>审批不通过</option>
					</select></td>
				</tr>
				<tr>
					<td><label>所在城市：</label> <select class="combox"
						name="filter_province" ref="combox_MessChangeHisCjrcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city"
						id="combox_MessChangeHisCjrcity">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
					</select></td>
					<td></td>
					<td></td>
					<td>
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
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="134" nowrapTD="false">
		<thead>
			<tr>
				<th width="80" orderField="cjrxm" class="asc">客户名</th>
				<th width="80" orderField="khbm">客户编码</th>
				<th width="80">所在城市</th>
				<th width="80">理财经理</th>
				<th width="80">状态</th>
				<th width="100" orderField="createTime">创建日期</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listCjrxx}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${user.CJRXM }</td>
					<td>${user.KHBM }</td>
					<td>${user.PRONAME }</td>
					<td>${user.EMPLOYEE_CRM_NAME }</td>
					<td><c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待审批</c:if> <c:if
							test="${user.STATE=='2'}">审批通过</c:if> <c:if
							test="${user.STATE=='3'}">审批不通过</c:if></td>
					<td>${user.CREATETIME }</td>
					<td>
					<div class="buttonActive">
				       <div class="buttonContent">
					<a title="查看" target="navTab"
						href="${ctx }/cjrxx/lookOutCjrxx/${user.ID}">查看</a> 
						</div>
		            </div>
		            <div class="buttonActive">
				       <div class="buttonContent">
						<a title="变更历史" target="navTab"
						href="${ctx }/cjrxx/getCjrxxHis/${user.ID}">变更历史</a>
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
