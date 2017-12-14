<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="ext/excel/excel.js" type="text/javascript"></script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>



<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/qdHouseInfo/listQdHouseInfo" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>借款编号:</label>
				<input type="text" name="filter_loanCode" value="${filter_loanCode}"/>
				<input	name="jsonData" type='hidden' value='${jsonData}'/>
			</li>
			
			<li>
				<label>借款人姓名:</label>
				<input type="text" name="filter_jkrxm" value="${map.jkrxm}"/>
			</li>
			
			<li>
				<label>证件号码:</label>
				<input type="text" name="filter_zjhm" value="${map.zjhm}" size="25"/>
			</li>
			
			<li>
				<label>城市::</label>
				 <select class="combox"
						name="filter_province" ref="combox_cjrcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${xydkzx.crmprovince==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${crmprovince}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xydkzx.crmprovince==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> 
					<select class="combox" name="filter_city" id="combox_cjrcity">
							<option value="" <c:if test="${xydkzx.crty==''}">selected</c:if>>所有城市</option>
					</select>
			</li>
			
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="return exportExcel()">导出</button></div></div></li>
			</ul>
				
		</div>
	</div>
	</form>
</div>
<div>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"  href="${ctx}/qdHouseInfo/addQdHouseInfo" target="navTab"><span>添加</span></a></li>
			<li><a class="add"  href="${ctx}/exportExcel?serviceName=testExcelService"  target="dwzExport" targetType="navTab" onclick="return exportExcel()"><span>导出</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" >借款编号</th>
				<th width="80" >借款人状态</th>
				<th width="80" >借款人姓名</th>
				<th width="80"  >借款人借款用途</th>
				<th width="80" >初始借款金额</th>
				<th width="80" >月本</th>
				<th width="80" >月利息金额</th>
				<th width="80">月账户管理费 </th>
				<th width="80" >月还款金额</th>
				<th width="80" >初始借款时间</th>
				<th width="80" >还款期限</th>
				<th width="80" >起始还款日期</th>
				<th width="80" >借款利率%</th>
				<th width="80" >还款截止日期</th>
				<th width="80" >借款人职业情况</th>
				<th width="80" >证件号码</th>
				<th width="80" >状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td>${user.loanCode}</td>
				<td>${user.state}</td>
				<td>${user.jkrxm}</td>
				<td>${user.jkUse}</td>
				<td><fmt:formatNumber value="${user.htje}" pattern="##00.00" /></td>
				<td>${user.ybjje}</td>
				<td>${user.ylxje}</td>
				<td>${user.zhglf}</td>
				<td>${user.yhkje}</td>
				<td>${user.qdrq}</td>
				<td>${user.hkqs}</td>
				<td>${user.qshkrq}</td>
				<td>${user.dkll}</td>
				<td>${user.returnrq}</td>
				<td>${user.personWorkCondition}</td>
				<td>${user.zjhm}</td>
				<td>${user.meta1}</td>
				<td>
					<c:if test="${user.meta1 != '提交'}">
					<a title="编辑" target="navTab" href="${ctx }/qdHouseInfo/editQdHouseInfo/${user.id}" class="btnEdit">编辑</a>
					</c:if>
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
