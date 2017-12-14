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
				<th width="20%">客户名</th>
				<th width="45%">账单期限</th>
				<th width="25%">是否生成报告</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<!-- 授信 -->
			<c:forEach items="${files}" var="accredited" varStatus="st">
				<tr target="sid_accredited" rel="${accredited.id}">
					<td align="center">${accredited.lenderName}</td>
					<td align="center">${accredited.reportCycle}</td>
					<td align="center">
					<c:if test="${accredited.state=='1'}">已生成</c:if> <c:if
							test="${accredited.state=='0'}">未生成</c:if>
					</td>
					<td align="center">
						<c:if test="${accredited.state=='1'}">
						<a class="icon" href="${ctx}/xhCapitalLoanReport/downLoadXhCapitalLoanReport?id=${accredited.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>


