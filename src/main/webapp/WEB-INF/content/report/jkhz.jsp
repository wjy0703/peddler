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
		action="${ctx }/report/jkhz" method="post">
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
						<!--  <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a> -->
					</td>
					<td><label>个人经理：</label> <input type="hidden"
						name="employeeCrm.id" value="${user.employeeCrm.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employeeCrm.name" value="${user.employeeCrm.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCrm" /> <!-- <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCrm"><hi:text
								key="查找带回" /></a>-->
					</td>
							
				</tr>
				<tr>
					<td><label>城市：</label> <select class="combox"
						name="filter_crmprovince" ref="combox_cjrcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${user.crmprovince==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${crmprovince}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${user.crmprovince==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_crty" id="combox_cjrcity">
							<option value="" <c:if test="${user.crty==''}">selected</c:if>>所有城市</option>
					</select></td>
					<td><label>从借款日期:</label> <input type="text" name="startjkrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				  <!--   <a class="inputDateButton" href="#">选择</a></td> -->
					<td><label>到借款日期:</label> <input type="text" name="endjkrq" class="date" pattern="yyyy-MM-dd" value="" size="20" />
				   <!--  <a class="inputDateButton" href="#">选择</a></td> -->
					<td><label>意向:</label> <select class="combox"
						name="filter_ztFlag">
							<option value=""></option>
							<option value="0">新增</option>
							<option value="1">展期</option>
							<option value="2">续借</option>
					</select></td>
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
			<li><a class="icon" href="${ctx }/report/exportJkhz?filter_jkbh=${filter_jkbh }" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>

	<table class="table" width="150%" layoutH="185" nowrapTD="false">
		<thead>
			<tr>

				<th width="80" orderField="xh" class="asc">序号</th>
				<th width="80">机构</th>
				<th width="80">借款人名称</th>
				<th width="80">借款人身份证号</th>
				<th width="80">借款编号</th>
				<th width="80">借款产品</th>
				<th width="80">合同金额</th>
				<th width="100">借款性质</th>
				<th width="80">综合费率</th>
				<th width="80">信访咨询费</th>
				<th width="80">借款期数</th>
				<th width="80">放款金额</th>
				<th width="100" orderField="createTime">放款时间</th>
				<th width="100" orderField="createTime">到期时间</th>
				<th width="80">团队经理</th>
				<th width="80">客户经理</th>
				<th width="100" orderField="createTime">申请日期</th>
				<th width="80">外访员</th>
				<th width="100" orderField="createTime">外访日期</th>
				<th width="80">审批人</th>
				<th width="100" orderField="createTime">审批日期</th>
				<th width="80">审核团队经理</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${row.queryList}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.CITYNAME }</td>
					<td>${user.JKRXM }</td>
					<td>${user.ZJHM }</td>
					<td>${user.LOAN_CODE }</td>
					<td>${user.JK_TYPE_INFO }</td>
					<td>￥<fmt:formatNumber
							value="${user.HTJE }" pattern="#,#00.00" /></td>
					<td></td>
					<td>${user.YZHFL }</td>
					<td>￥<fmt:formatNumber
							value="${user.ZXF }" pattern="#,#00.00" /></td>
					<td>${user.HKQS }</td>
					<td>￥<fmt:formatNumber
							value="${user.FKJE }" pattern="#,#00.00" /></td>
					<td>${user.MAKE_LOAN_DATE }</td>
					<td>${user.QSHKRQ }</td>
					<td>${user.SALENAME }</td>
					<td>${user.TEAMNAME }</td>
					<td>${user.JK_LOAN_DATE }</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
			        <td></td>
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
				<td>￥<fmt:formatNumber
							value="${proref.HTJE }" pattern="#,#00.00" /></td>
				<td></td>
				<td></td>
				<td>￥<fmt:formatNumber
							value="${proref.ZXF }" pattern="#,#00.00" /></td>
				<td></td>
				<td>￥<fmt:formatNumber
							value="${proref.FKJE }" pattern="#,#00.00" /></td>
				<td></td>
				<td></td>
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
