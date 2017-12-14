<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);"  action="${ctx }/logControl/listAndEdit" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>类:</label> 
						<input type="text" name="clazzName" size="30"/>
						<input type="hidden" name= "setLevel" id= "setLevel"/>
					</td>
					<td><label>类型:</label> 
						<select name="level">
								<option value="debug" selected>debug</option>
								<option value="info">info</option>
								<option value="error">error</option>
								<option value="fatal">fatal</option>
								<option value="warn">warn</option>
						</select>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onClick="return changeSet(1);">设置</button>
							</div>
							<div class="buttonContent">
								<button type="submit" onClick="return changeSet(0);">检索</button>
							</div>
						</div></li>
					<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
				</ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
<!-- 	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jksq/addJksq" target="navTab"><span>新增借款申请</span></a></li>
		</ul>
	</div> -->
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="65" >类名</th>
				<th width="60">类型</th>
				<th width="40"  align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${logs }" var="log" varStatus="st">
				<tr>
					<td>${log.name}</td>
					<td>${log.level}</td>
					<td>
						<div class="buttonActive">
								<div class="buttonContent">
									<button onclick="return showOrHide(this);">操作</button>
								</div>
						</div>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
</div>

<script>
   function changeSet(val){
   		$("#setLevel").val(val);
   }
</script>