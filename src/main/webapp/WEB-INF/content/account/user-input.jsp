<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/account/saveuser"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${user.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label> <input name="loginName" type="text" size="30"
					alt="请输入字母,数字.下划线,3-16位" value="${user.loginName }"
					class="required alphanumeric" minlength="3" maxlength="16"
					remote="${ctx }/account/chkuser?oldLoginName=${user.loginName}" />
			</p>
			<p>
				<label>状态：</label> <select name="sts" class="required combox">
					<option value="" <c:if test="${user.sts==''}">selected</c:if>>请选择</option>
					<option value="0" <c:if test="${user.sts=='0'}">selected</c:if>
						selected>在用</option>
					<option value="1" <c:if test="${user.sts=='1'}">selected</c:if>>历史</option>
				</select>
			</p>
			<p>
				<label>密码：</label> <input id="password" name="password"
					type="password" size="30" alt="请输入密码"
					<c:if test="${user.id == null}"> class="required alphanumeric" </c:if>
					<c:if test="${user.id != null}"> class="alphanumeric" </c:if>
					minlength="3" maxlength="16" />
			</p>
			<p>
				<label>确认密码：</label> <input name="confirmPassword" type="password"
					size="30" alt="请输入密码"  />
			</p>
			<p>
				<label>选择员工：</label> <input type="hidden" name="employee.id"
					value="${user.employee.id}" class="required"/> <input type="text" id="empname"
					class="textInput" name="employee.name"
					value="${user.employee.name }" suggestFields="name,deptname"
					suggestUrl="${ctx }/baseinfo/suggestemployee"
					lookupGroup="employee"/> <a class="btnLook"
					href="${ctx }/baseinfo/emplookup" lookupGroup="employee"><hi:text
						key="查找带回" /></a>
			</p>
			<p>
				<label>所属机构：</label> <input type="text" readonly="readonly"
					name="employee.deptname" class="textInput"
					value="${user.employee.organi.rganiName }">
			</p>
			<div class="divider"></div>

			<dl class="nowrap">
				<dt>拥有角色</dt>
				<dd>
					<input name="roles.id" value="${user.roleIds}" type="hidden">
					<textarea name="roles.name" class="readonly"
						readonly="readonly" style="width: 93%;">${user.roleNames}</textarea>
					<a class="btnLook"
						href="${ctx }/account/findrole?userId=${user.id}"
						lookupGroup="roles">选择角色</a>
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
</div>
</div>