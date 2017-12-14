<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<li><a class="add" href="${ctx }/xhCarLoanApply/addOrEditXhCarLoanApply/${userId}" target="navTab"><span>新增车借申请</span></a></li>
		<%-- <li><a title="申请" target="navTab" href="${ctx }/xhCarLoanApply/addXhCarLoanApply/${userId}">申请</a></li> --%>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" class="asc">序号</th>
				<th width="80" orderField="userName" class="asc">申请人姓名</th>
				<th width="80">申请日期</th>
				<th width="80" >借款申请状态<!-- ，状态参考实体 --></th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 1;
			%>
			<c:forEach items="${xhCarLoanApplyHas}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.ID}">
				<td><%=i%></td>
				<td>${user.USER_NAME}</td> 
				<td>${user.JK_LOAN_DATE }</td>
				<td><sen:carStateToName value="${user.STATE}"/></td>
				
				<td>
				   <!--  <div class="buttonActive">
							<div class="buttonContent">
								<button onclick="return showOrHide(this);">操作</button>
							</div>
				    </div> -->
					
				<!-- 	<div  class='OperationsPopUp' style='display: none; position: absolute; text-align: justify; font-size: 12px;height:40px;width:300px;  filter:alpha(opacity=100);
					    opacity:0.9;
					    background:#fff;
					    border:2px orange solid;
					    padding:20px;
					    padding-left:20px;
					   word-wrap:break-word;
					    line-height:100%;
					'> -->
					
				 <div class="buttonActive">
					<div class="buttonContent">
						<a title="查看车借申请信息" target="navTab" href="${ctx }/xhCarLoanApply/editXhCarLoanApplyLook/${user.ID}" >查看</a>
					</div>
		        </div>	
					  <c:choose>
					   <c:when test="${user.editable == true and user.IS_EXTENSION==1}">
			            <div class="buttonActive">
							<div class="buttonContent">
								<a title="编辑车借展期申请信息" target="navTab" href="${ctx }/xhCarLoanApply/editExtension/${user.ID}" >编辑</a>
							</div>
			            </div>
			            </c:when>
					  <c:when test="${user.editable == true and user.IS_EXTENSION==0}"> 
						<div class="buttonActive">
							<div class="buttonContent">	
									<a title="编辑" target="navTab" href="${ctx }/xhCarLoanApply/editXhCarLoanApply/${user.ID}" >编辑</a>
							</div>
						</div>
				 </c:when>
					</c:choose> 
					
				
				<!-- 	</div> -->
					
					
		
					
		
	
				
				</td>
			</tr>
				<%
					i++;
				%>
			</c:forEach>
		</tbody>
	</table>
</div>
