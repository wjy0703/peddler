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
		action="${ctx }/report/hkzxtj" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>咨询编号:</label> <input type="text"
						name="filter_zxbm" /></td>
					<td><label>咨询人姓名:</label> <input type="text"
						name="filter_zxrxm" /></td>
					<td><label>个人团队经理：</label> <input type="hidden"
						name="employeeCca.id" value="${user.employeeCca.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employeeCca.name" value="${user.employeeCca.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCca" /> 
						<!--  <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a>-->
					</td>
					<td><label>个人经理：</label> <input type="hidden"
						name="employeeCrm.id" value="${user.employeeCrm.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employeeCrm.name" value="${user.employeeCrm.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCrm" /> 
						<!--<a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCrm"><hi:text
								key="查找带回" /></a>-->
					</td>
					
				<tr>
					
					<td><label>从咨询日期:</label> <input type="text" name="startjkrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				  <!--   <a class="inputDateButton" href="#">选择</a></td> -->
					<td><label>到咨询日期:</label> <input type="text" name="endjkrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				    <!-- <a class="inputDateButton" href="#">选择</a></td>-->
					
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
			<li><a class="icon" href="${ctx }/report/exportZxhz?filter_jkbh=${filter_zxbm }" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>

				<th width="80" orderField="xh" class="asc">序号</th>
			
				<th width="80">咨询人编号</th>
				<th width="80">咨询人名称</th>
				<th width="80">咨询时间</th>
				<th width="80">团队经理</th>
				<th width="80">客户经理</th>
				<th width="80">咨询意向</th>

			</tr>
		</thead>
		<tbody>
				<c:forEach items="${row.queryList}" var="user" varStatus="st">
			   <tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.ZXBM }</td>
				<td>${user.KHMC }</td>
				<td>${user.ZXSJ }</td>
				<td>${user.TEAMNAME }</td>
				<td>${user.SALENAME }</td>
				<td>${user.ZXYX }</td>
				
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
