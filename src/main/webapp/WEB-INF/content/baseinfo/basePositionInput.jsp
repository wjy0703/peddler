<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/basePosition/saveBasePosition" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${basePosition.id}"/>
		<div class="pageFormContent" layoutH="56">
			
			
			
			<p>
				<label>职务名称：</label>
				<input name="positionName" type="text" size="30" value="${basePosition.positionName}" class="required" maxlength="100" />
			</p>
			
			<p>
				<label>职务权限级别：</label>
				<input name="positionCode" type="text" size="30" value="${basePosition.positionCode}" class="required" maxlength="16" />
			</p>
			
			<p>
				<label>职级标准：</label>
				<input name="positionLevel" type="text" size="30" value="${basePosition.positionLevel}" class="required" maxlength="16" />
			</p>
			<p>
				<label>职级英文编码：</label>
				<input name="positionLevelCode" type="text" size="30" value="${basePosition.positionLevelCode}" class="required" maxlength="100" />
			</p>
			<p>
				<label>职级VALUE：</label>
				<input name="positionLevelValue" type="text" size="30" value="${basePosition.positionLevelValue}" class="required" maxlength="100" />
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
