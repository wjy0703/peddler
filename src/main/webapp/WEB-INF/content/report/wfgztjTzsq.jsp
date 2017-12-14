<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="ext/excel/exportSalaryExcel.js"></script>

<div class="pageContent" style="padding:0px">
					<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form id="pagerForm" onsubmit="return divSearch(this, 'cfmdBox');" action="${ctx }/report/wfgztjTzsq" method="post">
	<div class="searchBar">
		<table>
			<tr>
				<td >
					门店:
					<input type="hidden" name="filter_orgid" id="cfmd_orgid" value="${map.orgid }"/>
					<input name="filter_orgname" id="cfmd_orgname" type="text"
					size="30" value="${map.orgname }" class="required" readonly="readonly" />
				</td>
				<td>
					日期:
					</td>
				<td>
					<input id="cfmd_date" name="filter_date" type="text"
					size="30" value="${map.date }" class="date required"
					dateFmt="yyyy-MM" maxlength="20" readonly="readonly" />
					&nbsp;
					<a class="inputDateButton" href="javascript:;">选择</a>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon exportSalaryExcel" href="${ctx}/report/yjtj/exportLendExcel" targetType="navTab" title="是否要生成绩效提成核算表?"><span>生成绩效提成核算表</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="138" rel="jbsxBox">
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
		<tbody>
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
		</tbody>
	</table>
	<div class="panelBar">

	</div>
</div>
			
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
</div>
<div id="preparing-file-modal" title="Preparing report..." style="display: none;">
    We are preparing your report, please wait...
 
    <!--Throw what you'd like for a progress indicator below-->
    <div class="ui-progressbar-value ui-corner-left ui-corner-right" style="width: 100%; height:22px; margin-top: 20px;"></div>
</div>
 
<div id="error-modal" title="Error" style="display: none;">
    There was a problem generating your report, please try again.
</div>