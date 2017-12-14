<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><span>附件列表</span></li>
		</ul>
	</div>
	
	<table class="table" width="100%"  layoutH="75" nowrapTD="false" targetType="dialog">
		<thead>
			<tr>
				<th width="25%">名称</th>
				<th width="25%">上传时间</th>
				<c:if test="${flag == 1}">
				<th width="25%">操作</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<!-- 授信 -->
			<c:forEach items="${files}" var="accredited" varStatus="st">
				<tr target="sid_accredited" rel="${accredited.id}">
					<td align="center">${accredited.filename}</td>
					<td align="center">${accredited.createTime}</td>
						<c:if test="${flag == 1}">
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${accredited.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
						</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>


