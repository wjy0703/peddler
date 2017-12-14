<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panelBar">
	<ul class="toolBar">
		<li><span>修改紧急联系人信息</span></li>
	</ul>
</div>
<div class="pageFormContent">
	<!-- 
	<form id="loanContactForm" name="loanContactForm" method="post" action="${ctx}/loan/saveEditLoanContact" class="pageForm required-validate" onsubmit="return loanContactFormSubmit(this);">
	<form id="loanContactForm" name="loanContactForm" method="post" action="${ctx}/loan/saveEditLoanContact" class="pageForm required-validate" onsubmit='return dialogAjaxDone({"statusCode":"200", "message":"操作xc成功", "navTabId":"", "forwardUrl":"jjlxr", "callbackType":"closeCurrent"});'>
	 -->
	<form id="loanContactForm" name="loanContactForm" method="post"
		action="${ctx}/loan/saveEditLoanContact"
		class="pageForm required-validate"
		onsubmit='return dwzSearchhj(this,"jjlxr");'>
		<table class="table" width="100%">
			<tr>
				<td><label>联系人姓名:</label> <input id="id" type="hidden"
					name="id" size="30" value="${xhdkjjlxr.id }" class="required">
					<input id="name" type="text" name="name" size="30"
					value="${xhdkjjlxr.name }" class="required"></td>
				<td><label>与借款人关系：</label> <select id="ybrgx" name="ybrgx">
						<c:forEach items="${ybrgx0014}" var="per">
							<c:if test="${per.value ==  xhdkjjlxr.ybrgx}">
								<option value="${per.value }" selected="selected">${per.name
									}</option>
							</c:if>
							<c:if test="${per.value !=  xhdkjjlxr.ybrgx}">
								<option value="${per.value }">${per.name }</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><label>工作单位：</label> <input type="text" id="jjlxrgzdw"
					name="jjlxrgzdw" value="${xhdkjjlxr.jjlxrgzdw }" size="30">
				</td>
				<td><label>联系电话：</label> <input type="text" id="jjlxrlxdh"
					name="jjlxrlxdh" value="${xhdkjjlxr.jjlxrlxdh }" size="30">
				</td>
			</tr>
			<tr>
				<td colspan="2"><label>单位住址或家庭住址：</label> <input type="text"
					id="jjlxrdwdzhjtzz" name="jjlxrdwdzhjtzz"
					value="${xhdkjjlxr.jjlxrdwdzhjtzz }" size="50"></td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">修改</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript">

	function loanContactFormSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}

		return validateCallback(obj, navTabAjaxDone);
	
	}
		

</script>