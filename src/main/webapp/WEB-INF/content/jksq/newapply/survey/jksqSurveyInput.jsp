<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
	<div class="panel">
	<div class="pageContent" width="100%">
		<form method="post" id="surveyForm" name="surveyForm"
			action="${ctx}/jksqSurvey/saveSurvey"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone)">
			<div class="pageFormContent" width="100%" layoutH="66">
				<input type="hidden" name="jksqId" value="${jsqkId}"/>
				<input type="hidden" name="xhCjksqSurveyItems[0].surveyType" value="0"/>
				<input type="hidden" name="xhCjksqSurveyItems[1].surveyType" value="1"/>
				<div class="panel collapse" minH="100" defH="235">
					<h1>外访家庭</h1>
					<div>
						<table class="table" width="100%">
							<thead>
								<tr>
									<th width="5%" align="center">选择</th>
									<th width="44%">初审人员选择</th>
									<th width="49%">外访人员选择</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${officeCheckBoxs}" var="home" varStatus="st">
									<tr>
										<td><input name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].itemsName" 
													value="${home.VALUE}" type="checkbox" ></td>
										<td>${home.DESCRIPTION}</td>
										<td><sen:selectRadio name="xhCjksqSurveyItems[0].xhCjksqSurveyDetails[${st.index}].itemsResult" coding="isExtension" split="&nbsp;&nbsp;"/></td>
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
									<textarea name="xhCjksqSurveyItems[0].demandWords" style="width: 100%;" rows="3" ></textarea>
									</td>
									<td height="50px">
									<textarea name="xhCjksqSurveyItems[0].demandrReply" style="width: 100%;" rows="3" disabled="disabled"></textarea>
									</td>
								</tr>
						</table>
					</div>
				</div>
				<div class="panel collapse" minH="100" defH="190">
					<h1>外访单位</h1>
					<div>
						<table class="table" width="100%">
							<thead>
								<tr>
									<th width="5%" align="center">选择</th>
									<th width="44%">初审人员选择</th>
									<th width="49%">外访人员选择</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${homeCheckBoxs}" var="office" varStatus="st">
									<tr>
										<td><input name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].itemsName" 
													value="${office.VALUE}" type="checkbox" ></td>
										<td>${office.DESCRIPTION}</td>
										<td><sen:selectRadio name="xhCjksqSurveyItems[1].xhCjksqSurveyDetails[${st.index}].itemsResult" coding="isExtension" split="&nbsp;&nbsp;"/></td>
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
									<textarea name="xhCjksqSurveyItems[1].demandWords" 
											style="width: 100%;" rows="3" ></textarea>
									</td>
									<td height="50px">
									<textarea name="xhCjksqSurveyItems[1].demandrReply" 
											style="width: 100%;" rows="3" disabled="disabled"></textarea>
									</td>
								</tr>
						</table>
					</div>
				</div>
				<table>
					<tr>
						<td><label>外访表格：</label><sen:selectCheckbox coding="surveyWord" name="demandWordTemplate" split="&nbsp;&nbsp;&nbsp;&nbsp;"/></td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div></li>
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
<script type="text/javascript" >
	$(function(){
		var $box = navTab.getCurrentPanel();
		$("input[type='radio']",$box).attr('disabled',true);
	});
</script>