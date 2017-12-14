<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
	<div class="panel">
	<div class="pageContent" width="100%">
			<div class="pageFormContent" width="100%" layoutH="66">
				<div class="panel collapse"  >
					<h1>外访家庭</h1>
					<div>
						<table class="table" width="100%">
							<thead>
								<tr>
									<th width="5%" align="center">选择</th>
									<th width="45%">初审人员选择</th>
									<th nowrap>外访人员选择</th>
								</tr>
							</thead>
							<tbody>
							   <%-- home代表detail记录 --%>
								<c:forEach items="${surveyHome.xhCjksqSurveyDetails}" var="home" varStatus="st">
									<tr>
										<td><input type="checkbox" checked="checked" disabled="disabled"></td>
										<td align="right">
										    <sen:vtoName value="${home.itemsName}" coding="surveyOffice"/>
										</td>
										<td><sen:selectRadio name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].itemsResult" coding="isExtension" split="&nbsp;&nbsp;" value="${home.itemsResult}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b8d0d6">
								<tr class="tableTr">
									<th width="80">&nbsp;&nbsp;外访要求</th>
									<th width="80">&nbsp;&nbsp;外访要求回复</th>
								</tr>
								<tr>
									<td height="50px">
									<textarea name="xhCjksqSurveyItems[0].demandWords" style="width: 100%;" rows="3" readonly="readonly">${surveyHome.demandWords}</textarea>
									</td>
									<td height="50px">
									<textarea name="xhCjksqSurveyItems[0].demandrReply" style="width: 100%;" rows="3" readonly="readonly">${surveyHome.demandrReply}</textarea>
									</td>
								</tr>
						</table>
					</div>
				</div>
				<div class="panel collapse" >
					<h1>外访单位</h1>
					<div>
						<table class="table" width="100%">
							<thead>
								<tr>
									<th width="5%" align="center">选择</th>
									<th width="45%">初审人员选择</th>
									<th nowrap>外访人员选择</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${surveyOffice.xhCjksqSurveyDetails}" var="office" varStatus="st">
									<tr>
									    <td><input type="checkbox" checked="checked" disabled="disabled"></td>
										<td>
										    <sen:vtoName value="${office.itemsName}" coding="surveyHome"/>
										</td>
										<td><sen:selectRadio name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].itemsResult" coding="isExtension" split="&nbsp;&nbsp;" value="${office.itemsResult}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b8d0d6">
								<tr class="tableTr">
									<th width="80">&nbsp;&nbsp;外访要求</th>
									<th width="80">&nbsp;&nbsp;外访要求回复</th>
								</tr>
								<tr>
									<td height="50px">
									<textarea name="xhCjksqSurveyItems[1].demandWords" style="width: 100%;" rows="3" readonly="readonly">${surveyOffice.demandWords}</textarea>
									</td>
									<td height="50px">
									<textarea name="xhCjksqSurveyItems[1].demandrReply" style="width: 100%;" rows="3" readonly="readonly">${surveyOffice.demandrReply}</textarea>
									</td>
								</tr>
						</table>
					</div>
				</div>
				<div class="panel">
				<h1>外访表格下载</h1>
				<table class="table" width="100%" >
					<c:forEach items="${surveyBackfiles}" varStatus="st" var="files">
					<tr>
						<td align="center">
							${files.filename}
						</td>
						<td>
								<a href="${ctx}/jksqSurvey/downLoadFiles?file=${files.id},1" ><img alt="" src="resources/xiazai.png"></a>
						</td>
					</tr>
					</c:forEach>
				</table>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
	</div>
</div>
