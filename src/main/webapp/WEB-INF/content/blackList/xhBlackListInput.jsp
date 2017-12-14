<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhBlackList/saveXhBlackList" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhBlackList.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户姓名：</label>
				<input name="name" type="text" size="30" value="${xhBlackList.name}" class="required" maxlength="100" />
			</p>
			<p>
				<label>客户身份证号：</label>
				<input name="identifId" type="text" size="30" value="${xhBlackList.identifId}" class="required" maxlength="100" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
