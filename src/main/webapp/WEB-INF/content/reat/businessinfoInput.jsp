<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/businessinfo/saveBusinessinfo" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${businessinfo.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>公司账户：</label>
				<input name="busiaccount" type="text" size="30" value="${businessinfo.busiaccount}" class="required" maxlength="40" />
			</p>
			<p>
				<label>公司名：</label>
				<input name="businame" type="text" size="30" value="${businessinfo.businame}" class="required" maxlength="40" />
			</p>
			<p>
				<label>法人：</label>
				<input name="corporation" type="text" size="30" value="${businessinfo.corporation}" class="required" maxlength="10" />
			</p>
			<p>
				<label>证件号码：</label>
				<input name="card" type="text" size="30" value="${businessinfo.card}" class="required" maxlength="30" />
			</p>
			<p>
				<label>联系方式：</label>
				<input name="phone" type="text" size="30" value="${businessinfo.phone}" class="required" maxlength="20" />
			</p>
			<p>
				<label>创建时间：</label>
				<input name="createtime" type="text" size="30" value="${businessinfo.createtime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>修改时间：</label>
				<input name="modifytime" type="text" size="30" value="${businessinfo.modifytime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>创建人：</label>
				<input name="createuser" type="text" size="30" value="${businessinfo.createuser}" class="required" maxlength="40" />
			</p>
			<p>
				<label>修改人：</label>
				<input name="modifyuser" type="text" size="30" value="${businessinfo.modifyuser}" class="required" maxlength="40" />
			</p>
			<p>
				<label>属性（在用、欠费、停用）：</label>
				<input name="vtypes" type="text" size="30" value="${businessinfo.vtypes}" class="required" maxlength="2" />
			</p>
			<p>
				<label>套餐类型：</label>
				<input name="tctypes" type="text" size="30" value="${businessinfo.tctypes}" class="required" maxlength="40" />
			</p>
			<p>
				<label>生效时间：</label>
				<input name="starttime" type="text" size="30" value="${businessinfo.starttime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>到期时间：</label>
				<input name="overtime" type="text" size="30" value="${businessinfo.overtime}" class="required" maxlength="20" />
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
