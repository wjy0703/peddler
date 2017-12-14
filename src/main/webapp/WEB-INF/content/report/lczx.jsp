
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
		action="${ctx }/report/lczx" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>借款编号:</label> <input type="text"
						name="filter_jkbh" /></td>
					<td><label>借款人姓名:</label> <input type="text"
						name="filter_jkrxm" /></td>
					<td><label>个人团队经理：</label> <input type="hidden"
						name="employeeCca.id" value="${user.employeeCca.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employeeCca.name" value="${user.employeeCca.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCca" /> 
						<!-- <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a> -->
					</td>
					<td><label>个人经理：</label> <input type="hidden"
						name="employeeCrm.id" value="${user.employeeCrm.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employeeCrm.name" value="${user.employeeCrm.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCrm" /> 
						<!-- <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCrm"><hi:text
								key="查找带回" /></a> -->
					</td>
					</td>
				<tr>
					
					<td><label>从咨询日期:</label> <input type="text" name="startjkrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				  <!--  <a class="inputDateButton" href="#">选择</a></td>-->
					<td><label>到咨询日期:</label> <input type="text" name="endjkrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				   <!-- <a class="inputDateButton" href="#">选择</a></td>-->
				<!-- 	<td><label>意向:</label> <select class="combox"
						name="filter_ztFlag">
							<option value=""></option>
							<option value="0">新增</option>
							<option value="1">展期</option>
							<option value="2">续借</option>
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
			<li><a class="icon" href="${ctx }/report/exportLczx?filter_jkbh=${filter_jkbh }" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>

	<table class="table" width="80%" layoutH="138">
		<thead>
			<tr>
				<th width="80" orderField="xh" class="asc">序号</th>
				<th width="80">咨询编号</th>
				<th width="80">咨询人名称</th>
				<th width="100" orderField="createTime">咨询时间</th>
				<th width="80">营销团队经理</th>
				<th width="80">营销人</th>
				<th width="80">咨询产品</th>
				<th width="80">咨询额度</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${row.queryList}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.ZXBM }</td>
					<td>${user.KHMC }</td>
					<td>${user.ZXSJ }</td>
					<td>${user.TEAMNAME}</td>
					<td>${user.SALENAME }</td>
					<td>${user.ZXYX }</td>
					<td>￥<fmt:formatNumber
							value="${user.PLAN_AMOUNT }" pattern="#,#00.00" /></td>
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
				<td>￥<fmt:formatNumber
							value="${proref.PLANAMOUNT }" pattern="#,#00.00" /></td>
			</tr>
			</c:forEach>
			<c:forEach items="${row.applicCountList}" var="proref" varStatus="st">
			<tr>
				<td></td>
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
</div>

