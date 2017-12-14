<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" src="ext/excel/exportSalaryExcel.js"></script>
<!-- <script type="text/javascript" src="ext/excel/ajaxFileDownload.js"></script>
<script type="text/javascript" src="ext/excel/exportWithMark.js"></script> -->

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/report/yjtj/listYjkhHj" method="post">
		<div class="pageFormContent">
			<table width="100%">
				<tr>
					<td><label>机构：</label>
					<select class="combox required"
						name="filter_orgid" id="cfmd_orgid">
							<option value="" <c:if test="${map.orgid==''}">selected</c:if>>请选择</option>
							<c:forEach items="${mendianlist}" var="md" varStatus="st">
								<option value="${md.ID }"
									<c:if test="${map.orgid==md.ID}">selected</c:if>>${md.RGANI_NAME}</option>
							</c:forEach>
					</select>
					</td>
					<td><label>日期：</label> <input id="cfmd_date" name="filter_date" type="text"
					size="20" value="${map.date }" class="date required"
					dateFmt="yyyy-MM" maxlength="20" readonly="readonly" />
					<a class="inputDateButton" href="javascript:;">选择</a> 
					</td>
					<td>
						<div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button id="enableExport" type="submit">检索</button>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon exportSalaryExcel" href="${ctx}/report/yjtj/exportLendExcel" targetType="navTab" title="是否要生成绩效提成核算表?"><span>生成绩效提成核算表</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="130" nowrapTD="false">
	    <thead>
			<tr>
				<th width="20px" >序号</th>
				<th width="40px" >理财经理</th>
				<th width="40px" >团队经理</th>
				<th width="40px" >出借日期</th>
				<th width="50px" >投资编号</th>
				<th width="40px" >出借人姓名</th>
				<th width="40px" >产品类型</th>			
				<th width="40px" >出借金额</th>
				<th width="20px" >件数</th>
			</tr>
		</thead>
         <% int i = 1; %>
		 <c:forEach items="${list.TMLIST}" var="teamItem">
		    <c:set var="teamLeaderName" value="${teamItem.NAME}"></c:set>
			
				  <c:forEach items="${teamItem.KHLIST}" var="customerItem">
				    <c:set var="customerLeaderName" value="${customerItem.NAME}"></c:set>
				    <c:forEach items="${customerItem.MXLIST }" var="single">
					<tr>
						<td><%= i++ %></td>
					 	<td>${customerLeaderName}</td>
					    <td>${teamLeaderName}</td>
						<td>${single.JHTZRQ }</td>
						<td>${single.TZSQBH }</td>
						<td>${single.CJRXM }</td>
						<td>${single.TZCP_MC }</td>			
						<td>${single.JHTZJE }</td>
						<td>1</td>
					</tr>
					</c:forEach>
				  </c:forEach>
		</c:forEach>
	
		<%-- <thead>
			<tr>
				<th width="28px" rowspan="2">序号</th>
				<th width="80px" rowspan="2">门店名称</th>
				<th width="60px" rowspan="2">门店经理</th>
				<th width="60px" rowspan="2">团队名称</th>
				<th width="60px" rowspan="2">团队经理</th>			
				<th width="50px" rowspan="2">客户经理</th>
				<th width="90px" colspan="2">季度盈</th>
				<th width="90px" colspan="2">双季盈</th>
				<th width="60px" rowspan="2">投资日期</th>
				<th width="60px" rowspan="2">出借人姓名</th>
				<th width="60px" rowspan="2">产品名称</th>
				<th width="50px" rowspan="2">出借金额</th>
				<th width="30px" rowspan="2">期限</th>
			</tr>
			<tr>
				<th width="60px">金额</th>
				<th width="30px">件数</th>
				<th width="60px">金额</th>
				<th width="30px">件数</th>
			</tr>
		</thead>
		<tbody>
		<%int i=0; %>
			<%i++; %>
			<tr>
				<td width="1px"><%=i %></td>
				<td width="180px">${list.RGANI_NAME }</td>
				<td width="60px" ><c:forEach items="${list.CTLIST}" var="ctuser" varStatus="ctst">
					${ctuser.NAME }；
				</c:forEach>
				</td>
				<td width="60px" ></td>
				<td width="60px" ></td>
				<td width="50px" ></td>
				<td width="60px" ></td>
				<td width="30px" ></td>
				<td width="60px" ></td>
				<td width="30px" ></td>
				<td width="60px" ></td>
				<td width="60px" ></td>
				<td width="60px" ></td>
				<td width="50px" ></td>
				<td width="50px" ></td>
			</tr>
			<c:forEach items="${list.TMLIST}" var="stuser" varStatus="tmst">
			<%i++; %>
				<tr target="sid_user">
					<td width="28px"><%=i %></td>
					<td></td>
					<td></td>
					<td>${stuser.RGANI_NAME }</td>
					<td>${stuser.NAME }</td>
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
				<c:forEach items="${stuser.KHLIST}" var="khuser" varStatus="stson">
					<tr target="sid_user">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${stson.count }</td>
						<td>${khuser.NAME }</td>
						<td>${khuser.JDYSUM }</td>
						<td>${khuser.JDYCOUNT }</td>
						<td>${khuser.SJYSUM }</td>
						<td>${khuser.SJYCOUNT }</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach items="${khuser.MXLIST}" var="mxuser" varStatus="mxson">
					<tr target="sid_user">
						<td ></td>
						<td></td>
						<td ></td>
						<td ></td>
						<td ></td>
						<td >${khuser.NAME }</td>
						<td ></td>
						<td ></td>
						<td ></td>
						<td ></td>
						<td >${mxuser.JHTZRQ }</td>
						<td >${mxuser.CJRXM }</td>
						<td >${mxuser.TZCP_MC }</td>
						<td>${mxuser.JHTZJE }</td>
						<td>${mxuser.CJZQ }</td>
					</tr>
				</c:forEach>
				</c:forEach>
			</c:forEach>
		</tbody> --%>
	</table>
</div>

<div id="preparing-file-modal" title="Preparing report..." style="display: none;">
    We are preparing your report, please wait...
 
    <!--Throw what you'd like for a progress indicator below-->
    <div class="ui-progressbar-value ui-corner-left ui-corner-right" style="width: 100%; height:22px; margin-top: 20px;"></div>
</div>
 
<div id="error-modal" title="Error" style="display: none;">
    There was a problem generating your report, please try again.
</div>
