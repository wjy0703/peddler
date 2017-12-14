<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/account/saverole"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${role.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名称：</label> <input name="name" type="text" size="30"
					alt="请输入角色名称，3-32字符" value="${role.name }" class="required"
					minlength="3" maxlength="32"
					remote="${ctx }/account/chkrole?oldname=${role.name}" />
			</p>
			<p>
				<label>角色描述：</label> <input name="cname" type="text" size="30"
					value="${role.cname }" />
			</p>
			<p>
				<label>状态：</label> <select name="sts" class="required combox">
					<option value="" <c:if test="${role.sts==''}">selected</c:if>>请选择</option>
					<option value="0" <c:if test="${role.sts=='0'}">selected</c:if>>在用</option>
					<option value="1" <c:if test="${role.sts=='1'}">selected</c:if>>历史</option>
				</select>
			</p>
			<div class="divider"></div>

			<dl class="nowrap">
				<dt>拥有菜单</dt>
				<dd>
					<input name="menu.id" value="${role.menuIds}" type="hidden">
					<textarea name="menu.name" class="readonly" readonly="readonly"
						style="width: 93%;">${role.menuNames}</textarea>
					<a class="btnLook"
						href="${ctx }/account/findMenu?roleId=${role.id}"
						lookupGroup="menu">选择菜单</a>
				</dd>
			</dl>

			<div class="divider"></div>

			<dl class="nowrap">
				<dt>拥有资源</dt>
				<dd>
					<input name="authoritys.id" value="${role.authIds}" type="hidden">
					<textarea name="authoritys.name" class="readonly"
						readonly="readonly" style="width: 93%;">${role.authNames}</textarea>
					<a class="btnLook"
						href="${ctx }/account/findauthority?roleId=${role.id}"
						lookupGroup="authoritys">选择资源</a>
				</dd>
			</dl>

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
</div></div>
