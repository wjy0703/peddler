<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhSystemParameter/saveXhSystemParameter" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhSystemParameter.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>变量名：</label>
				<input name="sysName" type="text" size="30" value="${xhSystemParameter.sysName}" class="required" maxlength="64" />
			</p>
			<p>
				<label>变量说明：</label>
				<input name="sysCname" type="text" size="30" value="${xhSystemParameter.sysCname}" class="required" maxlength="64" />
			</p>
			<p>
				<label>变量值：</label>
				<input name="sysValue" type="text" size="30" value="${xhSystemParameter.sysValue}" class="required" maxlength="64" />
			</p>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="reamrk" style="width:93%;height:80">${xhSystemParameter.reamrk}</textarea>
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
</div>