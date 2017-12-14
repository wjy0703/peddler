<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
	<div class="panel">
	<div class="pageContent" width="100%">
		<form method="post" id="surveyForm" name="surveyForm"
			action="${ctx}/jksqSurvey/storeSaveSurvey"
			class="pageForm required-validate"
			onsubmit="return validateForm(this)">
			<div class="pageFormContent" width="100%" layoutH="66">
				<input type="hidden" name="jksqId" value="${surveyMain.jksqId}"/>
				<input type="hidden" name="id" value="${surveyMain.id}"/>
				<input type="hidden" name="xhCjksqSurveyItems[0].surveyType" value="0"/>
				<input type="hidden" name="xhCjksqSurveyItems[1].surveyType" value="1"/>
				<input type="hidden" name="xhCjksqSurveyItems[0].id" value="${surveyHome.id }"/>
				<input type="hidden" name="xhCjksqSurveyItems[1].id" value="${surveyOffice.id }"/>
				<input type="hidden" name="xhCjksqSurveyItems[0].xhCjksqSurveyMain.id" value="${surveyMain.id }"/>
				<input type="hidden" name="xhCjksqSurveyItems[1].xhCjksqSurveyMain.id" value="${surveyMain.id }"/>
				<div class="panel collapse" >
					<h1>外访家庭</h1>
					<div>
						<table class="table" width="100%">
							<thead>
								<tr>
									<c:choose>
										<c:when test='${look=="0"}'>
											<th width="5%" align="center">选择</th>
											<th width="45.5%">初审人员选择</th>
											<th width="49%">外访人员选择</th>
										</c:when>
										
										<c:otherwise>
											<th width="5%" align="center">选择</th>
											<th width="45%">初审人员选择</th>
											<th nowrap>外访人员选择</th>
										</c:otherwise>
									</c:choose>
								</tr>
							</thead>
							<tbody>
							   <%-- home代表detail记录 --%>
								<c:forEach items="${surveyHome.xhCjksqSurveyDetails}" var="home" varStatus="st">
									<tr>
										<td><input type="checkbox" checked="checked" disabled="disabled"></td>
										<td>
										    <sen:vtoName value="${home.itemsName}" coding="surveyOffice"/>
										    <input type = "hidden" name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].itemsName" value ="${home.itemsName}">
										    <input type = "hidden" name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].id" value ="${home.id}"> <!-- 本身ID  -->
										    <input type = "hidden" name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].xhCjksqSurveyItem.id" value ="${surveyHome.id}"> <!-- 关联的item的ID -->
										</td>
										<td><sen:selectRadio clazz="required" name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].itemsResult" coding="isExtension" split="&nbsp;&nbsp;"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b8d0d6">
								<tr class="tableTr">
									<th width="49%">&nbsp;&nbsp;外访要求</th>
									<th width="49%">&nbsp;&nbsp;外访要求回复</th>
								</tr>
								<tr>
									<td height="50px" width="49%">
									<textarea name="xhCjksqSurveyItems[0].demandWords" style="width: 100%;" rows="3" readonly="readonly">${surveyHome.demandWords}</textarea>
									</td>
									<td height="50px" width="49%">
									<textarea name="xhCjksqSurveyItems[0].demandrReply" style="width: 100%;" rows="3" ></textarea>
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
									<c:choose>
										<c:when test='${look=="0"}'>
											<th width="5%" align="center">选择</th>
											<th width="45.5%">初审人员选择</th>
											<th width="49%">外访人员选择</th>
										</c:when>
										
										<c:otherwise>
											<th width="5%" align="center">选择</th>
											<th width="45%">初审人员选择</th>
											<th nowrap>外访人员选择</th>
										</c:otherwise>
									</c:choose>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${surveyOffice.xhCjksqSurveyDetails}" var="office" varStatus="st">
									<tr>
									    <td><input type="checkbox" checked="checked" disabled="disabled"></td>
										<td>
										    <sen:vtoName value="${office.itemsName}" coding="surveyHome"/>
										    <input type = "hidden" name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].itemsName" value ="${office.itemsName}">
										    <input type = "hidden" name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].id" value ="${office.id}"> <!-- 本身ID  -->
										    <input type = "hidden" name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].xhCjksqSurveyItem.id" value ="${surveyOffice.id}"> <!-- 关联的item的ID -->
										</td>
										<td><sen:selectRadio clazz="required" name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].itemsResult" coding="isExtension" split="&nbsp;&nbsp;"/></td>
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
									<textarea name="xhCjksqSurveyItems[1].demandrReply" style="width: 100%;" rows="3"></textarea>
									</td>
								</tr>
						</table>
					</div>
				</div>
				<c:if test='${look!="0"}'>
				<div class="panel">
				<h1>外访表格下载</h1>
				<table class="table" width="100%" >
					<c:forEach items="${wordTemplate}" varStatus="st" var="template">
					<tr>
						<td>
							<sen:vtoName coding="surveyWord"  value="${template}"/>
						</td>
						<td>
								<a href="${ctx}/jksqSurvey/downLoadFiles?file=${template-1},0" ><img alt="" src="resources/xiazai.png"></a>
						</td>
					</tr>
					</c:forEach>
				</table>
				</div>
				</c:if>
				<c:forEach items="${wordTemplate}" varStatus="st" var="template">
					<input type="hidden" name="demandWordTemplate" value="${template}">
				</c:forEach>
				
			</div>
			<div class="formBar">
				<c:if test='${look=="0"}'>
					<input type="hidden" name="fileName" id="fileName"/>
					<a class="buttonActive" href="${ctx }/jksqSurvey/upLoadSurveyBackInfo/${surveyMain.jksqId}" target="dialog" mask="true" close="closedialoghj"  width="600" height="440"
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'><span>上传外访反馈信息表格</span></a>
				</c:if>
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<c:if test='${look=="0"}'>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" >提交</button>
							</div>
						</div></li>
					</c:if>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
<!--
		
	function validateForm(form){
		var $box = navTab.getCurrentPanel();
		if($("#fileName",$box).val()!=null && $("#fileName",$box).val()!=""){
			return validateCallback(form, navTabAjaxDone);
		}else{
			 alertMsg.error('请先上传反馈资料,再进行提交操作');
			 return false;
		}
	}
//-->
</script>
