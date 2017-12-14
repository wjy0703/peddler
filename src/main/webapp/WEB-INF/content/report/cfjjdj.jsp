<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/report/cfjjdj" method="post">
			<div class="searchBar">
			<table width="100%">
				<tr>
					<td>
					
					   <label>机构：</label> 
					   
					   <select  class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${!empty map.yyb}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.YYBID }"
									<c:if test="${map.yyb==md.YYBID}">selected</c:if>>${md.YYB}</option>
							</c:forEach>
					</select>  
						
					</td>
					<td><label>匹配债权日期:</label> <input 
						name="filter_zqstartdate" value="${map.zqstartdate }" type="text" size="8" class="date" readonly="readonly"/>
					至
					<input  name="filter_zqoverdate"
						value="${map.zqoverdate }" type="text" size="8" class="date" readonly="readonly"/>
						</td>
						<td><label>制作协议日期:</label> <input 
						name="filter_xystartdate" value="${map.xystartdate }" type="text" size="8" class="date" readonly="readonly"/>
					至
					<input  name="filter_xyoverdate"
						value="${map.xyoverdate }" type="text" size="8" class="date" readonly="readonly"/>
						</td>
				
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="${ctx }/report/exportCfJjdj" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="2000px" layoutH="115" nowrapTD="ture">
		<thead>
			<tr>
				<th width="60px">序号</th>
				<th width="180px">机构</th>	
				<th width="100px">合同编号</th>
				<th width="100px">客户姓名</th>	
				<th width="100px">资金回收方式</th>
				<th width="100px">上传日期</th>
				<th width="100px">计划划扣日期</th>
				<th width="100px">计划出借日期</th>
				<th width="100px">出借金额</th>
				<th width="100px">客户经理</th>
				<th width="100px">团队经理</th>
				<th width="100px">综合内勤</th>
				<th width="100px">初审员姓名</th>
				<th width="100px">初审日期</th>
				<th width="100px">是否重签</th>
				<th width="100px">重签次数</th>
				<th width="100px">审批通过时间</th>
				<th width="100px">匹配债权日期</th>
				<th width="100px">制作协议日期</th>
				<th width="100px">划扣日期</th>
				<th width="100px">二次划扣日期</th>
				<th width="70px">共计用时</th>
				<th width="60px">备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user">
					<td>${st.count}</td>
					<td>${user.YYB }</td>
					<td>${user.CONTRACT_NUMBER }</td>
					<td>${user.CJRXM }</td>
					<td>${user.TZCP_MC }</td>
					<td>${user.UPLOADDATE }</td>
					<td>${user.JHHKRQ }</td>
					<td>${user.JHTZRQ }</td>
					<td>${user.JHTZJE }</td>
					<td>${user.EMPLOYEE_CRM_NAME }</td>
					<td>${user.EMPLOYEE_CCA_NAME }</td>
					<td>${user.CREATE_BY }</td>
					<td>${user.CHUSHEN_NAME }</td>
					<td>${user.CHUSHEN_DATE }</td>
					<td>${user.SFCQ }</td>
					<td>${user.CQCS }</td>
					<td>${user.SPTG_DATE }</td>
					<td>${user.PPZQRQ }</td>
					<td>${user.SJHKRQ}</td>
					<td>${user.HKRQ}</td>
					<td></td>
					<td>${user.ALLTIMES }</td>
					<td></td>
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