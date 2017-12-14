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
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>
				<tr target="sid_accredited">
					<td align="center">借款协议</td>
					<td align="center">
						<a class="icon" href="${ctx }/xhJkht/downAgaeeFile?Id=${xhJkht.jkhtbm}&sType=loanAgreement" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
				<tr target="sid_accredited">
					<td align="center">委托扣款授权书</td>
					<td align="center">
						<a class="icon" href="${ctx }/xhJkht/downAgaeeFile?Id=${xhJkht.jkhtbm}&sType=powerOfAttorney" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
				
				
				<tr target="sid_accredited">
					<td align="center">信用咨询及管理服务协议</td>
					<td align="center">
						<a class="icon" href="${ctx }/xhJkht/downAgaeeFile?Id=${xhJkht.jkhtbm}&sType=managementServicesAgreement" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
				<tr target="sid_accredited">
					<td align="center">还款管理服务说明书</td>
					<td align="center">
						<a class="icon" href="${ctx }/xhJkht/downAgaeeFile?Id=${xhJkht.jkhtbm}&sType=repaymentSynopsis" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>
					</td>
				</tr>
			<c:forEach items="${files}" var="user" varStatus="st">
		     <c:if test="${user.filename !='' }">
				<tr>
				<td align="center">上传合同附件:${user.filename}</td>
				  <td>
						<a class="icon" href="${ctx}/loan/downFile/${user.id}" targetType="navTab" title="下载当前文件吗?"><span>下载</span></a>&nbsp;
				  </td>
				</tr>
			</c:if>
			</c:forEach>
					
		</tbody>
		
	</table>

</div>


