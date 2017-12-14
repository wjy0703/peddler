<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="pageContent">
<!-- 	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jksq/addJksq" target="navTab"><span>新增借款申请</span></a></li>
		</ul>
	</div> -->
	<table class="table" width="100%" layoutH="60" >
		<thead>
			<tr>
				
				<th width="100" >借款编号</th>
				<%-- <c:if test="${checkRelation =='1'}">
					<th width="200"><label style="color: blue;">借款人证件号码</label></th>
				</c:if>  --%>
				<th width="200">
					<c:if test="${checkRelation =='1'}"><label style="color: red">配偶证件号码</label></c:if>
					<c:if test="${checkRelation =='0'}"><label style="color: red">已存在相同的证件号码</label></c:if>
				</th>
				<th width="60">客户姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">状态</th>
				<th width="60">产品</th>
			
				<th width="80">申请金额(元)</th>
				<th width="80">放款金额(元)</th>
				<th width="50">期数(月)</th>
				<th width="60">团队经理</th>
				<th width="60">客户经理</th>

				<th width="140" >进件时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${result }" var="jksq" varStatus="st">
				<tr  target="sid_jksq" rel="${jksq.ID}">
					<td>${jksq.LOAN_CODE }</td>
					<%-- <c:if test="${checkRelation =='1'}">
						<td>${idCard }</td>
					</c:if>  --%>
					<td>${jksq.ZJHM}</td> 
					<td>${jksq.JKRXM }</td>
					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					
					<c:choose>
							<c:when test="${fn:contains(jksq.STATEINFO , '外')}">
						<td style="color:orange;font-weight:bold;font-size:12">${jksq.STATEINFO  }</td>
					</c:when>
					<c:when test="${fn:contains(jksq.STATEINFO , '待')}">
						<td style="color:blue;font-weight:bold;font-size:12">${jksq.STATEINFO  }</td>
					</c:when>
			
					<c:when test="${fn:contains(jksq.STATEINFO , '暂')}">
						<td style="color:orange;font-weight:bold">${jksq.STATEINFO  }</td>
					</c:when>
					<c:when test="${fn:contains(jksq.STATEINFO , '拒')}">
						<td style="color:red;font-weight:bold">${jksq.STATEINFO  }</td>
					</c:when>
					<c:when test="${fn:contains(jksq.STATEINFO , '已放款')}">
						<td style="color:green;font-weight:bold">${jksq.STATEINFO  }</td>
					</c:when>
				  
					<c:otherwise><td style="color:red;font-weight:bold">${jksq.STATEINFO}</c:otherwise>
					</c:choose>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>
						<c:if test='${null != jksq.JK_LOAN_QUOTA && jksq.JK_LOAN_QUOTA != 0 }'>￥<fmt:formatNumber
								value="${jksq.JK_LOAN_QUOTA }" pattern="#,#00.00" />
						</c:if>
					</td>
					<td>
						<c:if test='${null != jksq.FKJE && jksq.FKJE != 0 }'>￥<fmt:formatNumber
								value="${jksq.FKJE}" pattern="#,#00.00" />
						</c:if>
					</td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td>
		
					<td>${jksq.BACKUP02}</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
</div>
