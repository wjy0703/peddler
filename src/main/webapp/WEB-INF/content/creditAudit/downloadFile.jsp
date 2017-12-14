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
			<!-- 授信 -->
			<c:forEach items="${accredited}" var="accredited" varStatus="st">
				<tr target="sid_accredited" rel="${accredited.id}">
					<td align="center">
						<c:if test='${accredited.flag == "授信"}'>授信文件</c:if>
						<c:if test='${accredited.flag == "0"}'>签约文件</c:if>
						<c:if test='${accredited.flag == "1"}'>信审初审文件</c:if>
						<c:if test='${accredited.flag == "2"}'>信审复审文件</c:if>
						<c:if test='${accredited.flag == "3"}'>信审终审文件</c:if>
						<c:if test='${accredited.flag == "外访"}'>外访文件</c:if>
					</td>
					<td align="center">${accredited.filename}</td>
					<td align="center">${accredited.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${accredited.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
			<!-- 信审初审 -->
			<c:forEach items="${first}" var="first" varStatus="st">
				<tr target="sid_first" rel="${first.id}">
					<td align="center">
						<c:if test='${first.flag == "授信"}'>授信文件</c:if>
						<c:if test='${first.flag == "0"}'>签约文件</c:if>
						<c:if test='${first.flag == "1"}'>信审初审文件</c:if>
						<c:if test='${first.flag == "2"}'>信审复审文件</c:if>
						<c:if test='${first.flag == "3"}'>信审终审文件</c:if>
						<c:if test='${first.flag == "外访"}'>外访文件</c:if>
					</td>
					<td align="center">${first.filename}</td>
					<td align="center">${first.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${first.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
			<!-- 信审复审 -->
			<c:forEach items="${second}" var="second" varStatus="st">
				<tr target="sid_second" rel="${second.id}">
					<td align="center">
						<c:if test='${second.flag == "授信"}'>授信文件</c:if>
						<c:if test='${second.flag == "0"}'>签约文件</c:if>
						<c:if test='${second.flag == "1"}'>信审初审文件</c:if>
						<c:if test='${second.flag == "2"}'>信审复审文件</c:if>
						<c:if test='${second.flag == "3"}'>信审终审文件</c:if>
						<c:if test='${second.flag == "外访"}'>外访文件</c:if>
					</td>
					<td align="center">${second.filename}</td>
					<td align="center">${second.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${second.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
			<!-- 信审终审 -->
			<c:forEach items="${last}" var="last" varStatus="st">
				<tr target="sid_last" rel="${last.id}">
					<td align="center">
						<c:if test='${last.flag == "授信"}'>授信文件</c:if>
						<c:if test='${last.flag == "0"}'>签约文件</c:if>
						<c:if test='${last.flag == "1"}'>信审初审文件</c:if>
						<c:if test='${last.flag == "2"}'>信审复审文件</c:if>
						<c:if test='${last.flag == "3"}'>信审终审文件</c:if>
						<c:if test='${last.flag == "外访"}'>外访文件</c:if>
					</td>
					<td align="center">${last.filename}</td>
					<td align="center">${last.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${last.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
			<!-- 签约 -->
			<c:forEach items="${signed}" var="signed" varStatus="st">
				<tr target="sid_signed" rel="${signed.id}">
					<td align="center">
						<c:if test='${signed.flag == "授信"}'>授信文件</c:if>
						<c:if test='${signed.flag == "0"}'>签约文件</c:if>
						<c:if test='${signed.flag == "1"}'>信审初审文件</c:if>
						<c:if test='${signed.flag == "2"}'>信审复审文件</c:if>
						<c:if test='${signed.flag == "3"}'>信审终审文件</c:if>
						<c:if test='${signed.flag == "外访"}'>外访文件</c:if>
					</td>
					<td align="center">${signed.filename}</td>
					<td align="center">${signed.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${signed.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
			<!-- 外访 -->
			<c:forEach items="${waifang}" var="waifang" varStatus="st">
				<tr target="sid_signed" rel="${waifang.id}">
					<td align="center">
						<c:if test='${waifang.flag == "授信"}'>授信文件</c:if>
						<c:if test='${waifang.flag == "0"}'>签约文件</c:if>
						<c:if test='${waifang.flag == "1"}'>信审初审文件</c:if>
						<c:if test='${waifang.flag == "2"}'>信审复审文件</c:if>
						<c:if test='${waifang.flag == "3"}'>信审终审文件</c:if>
						<c:if test='${waifang.flag == "外访"}'>外访文件</c:if>
					</td>
					<td align="center">${waifang.filename}</td>
					<td align="center">${waifang.createTime}</td>
					<td align="center">
						<a class="icon" href="${ctx}/loan/downFile/${waifang.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>


