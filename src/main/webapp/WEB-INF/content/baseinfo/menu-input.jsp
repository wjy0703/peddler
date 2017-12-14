<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/saveMenu"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${menu.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>菜单名称：</label> <input name="menuName" type="text" size="30"
					alt="请输入菜单名称" value="${menu.menuName }" class="required" />
			</p>
			<p></p>
			<p></p>
			<p>
				<label>菜单标题：</label> <input name="title" type="text" size="30"
					value="${menu.title }" />
			</p>
			<p></p>
			<p></p>
			<p>
				<label>菜单状态：</label> <select name="sts" class="required combox">
					<option value="0" <c:if test="${menu.sts=='0'}">selected</c:if>
						selected>在用</option>
					<option value="1" <c:if test="${menu.sts=='1'}">selected</c:if>>历史</option>
				</select>
			</p>
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
