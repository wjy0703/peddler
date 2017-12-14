<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/saveMiddleMan"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${middleMan.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>转让债权账户名：</label> <input name="middleManName" type="text"
					size="30" alt="请输入账户名" value="${middleMan.middleManName }"
					class="required" />
			</p>
			<p>
				<label>身份证：</label> <input name="idCard" type="text" size="30"
					alt="请输入身份证" value="${middleMan.idCard }" class="required" />
			</p>
			<p>
				<label>地址：</label> <input name="addr" type="text" size="30"
					alt="请输入地址" value="${middleMan.addr }" class="required" />
			</p>
			<p>
				<label>开户行：</label> <input name="credAddr" type="text" size="30"
					alt="请输入开户行" value="${middleMan.credAddr }" class="required" />
			</p>
			<p>
				<label>银行账号：</label> <input name="credId" type="text" size="30"
					alt="请输入银行账号" value="${middleMan.credId }" class="creditcard" />
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
