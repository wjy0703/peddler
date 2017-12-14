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
				<th width="45%">计划出借日期</th>
				<th width="25%">债权状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<!-- 授信 -->
			<c:forEach items="${listTzsq}" var="accredited" varStatus="st">
				<tr target="sid_accredited" rel="${accredited.ID}">
					<td align="center">${accredited.CJRXM}</td>
					<td align="center">${accredited.JHTZRQ}</td>
					<td align="center">
					<c:if test="${accredited.LENT_STATE=='1'}">非首期</c:if> <c:if
							test="${accredited.LENT_STATE=='0'}">首期</c:if>
					</td>
					<td align="center">
						<a class="icon" href="${ctx}/xhTzsq/downloadOneFile?id=${accredited.ID}&LENT_STATE=${accredited.LENT_STATE}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td> 
				</tr>
				
			</c:forEach>
		</tbody>
	</table>

</div>


