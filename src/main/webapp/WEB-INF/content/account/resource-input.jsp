<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/account/saveauth"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${auth.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>资源名称：</label> <input name="name" type="text" size="30"
					alt="请输入资源名称，3-32字符" value="${auth.name }" class="required"
					minlength="3" maxlength="32"
					remote="${ctx }/account/chkauth?oldname=${auth.name}" />
			</p>
			<p>
				<label>角色描述：</label> <input name="cname" type="text" size="30"
					value="${auth.cname }" />
			</p>
			<p>
				<label>资源路径：</label> <input name="path" type="text" size="30"
					value="${auth.path }" />
			</p>
			<p>
				<label>状态：</label> <select name="sts" class="required combox">
					<option value="" <c:if test="${auth.sts==''}">selected</c:if>>请选择</option>
					<option value="0" <c:if test="${auth.sts=='0'}">selected</c:if>>在用</option>
					<option value="1" <c:if test="${auth.sts=='1'}">selected</c:if>>历史</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
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
</div></div>
