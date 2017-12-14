<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<table class="table" width="100%" layoutH="50">
		<thead>
			<tr>
				<th width="80" >职务名称</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${positions}" var="user" varStatus="st">
				<tr>
					<td>${user.positionName }</td>
					<td><a class="btnSelect"
						href="javascript:$.bringBack({
					id:'${user.id}', positionName:'${user.positionName }'})"
						title="查找带回">选择</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
