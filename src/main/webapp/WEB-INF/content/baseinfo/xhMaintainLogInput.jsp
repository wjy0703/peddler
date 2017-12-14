<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhMaintainLog/saveXhMaintainLog" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhMaintainLog.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题：</label>
				<input name="title" type="text" size="30" value="${xhMaintainLog.title}" class="required" maxlength="128" />
			</p>
			<div class="divider"></div>
				<dl class="nowrap">
						<dt>详细内容：</dt>
						<dd>
							<textarea name="detContent" style="width: 93%; height: 200px"
								maxlength="1024">${xhMaintainLog.detContent }</textarea>
						</dd>
					</dl>
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
