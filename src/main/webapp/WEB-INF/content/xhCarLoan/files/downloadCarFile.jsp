<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><span>状态列表</span></li>
		</ul>
	</div>
	
	<table class="table" width="100%"  layoutH="75" nowrapTD="false" targetType="dialog">
		<thead>
			<tr>
				<th width="25%">类型</th>
				<th width="25%">名称</th>
				<th width="25%">上传时间</th>
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${files}" var="file" varStatus="st">
				<tr >
					<td align="center">
						${file.flag}
					</td>
					<td align="center">${file.filename}</td>
					<td align="center">${file.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/carFiles/downloadFile/${file.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>


