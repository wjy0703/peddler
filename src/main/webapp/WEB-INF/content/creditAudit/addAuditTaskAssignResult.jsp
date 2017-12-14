<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCreditTaskAssign/saveXhCreditTaskAssign"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCreditTaskAssign.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>借款申请：</label> <input name="loanApplyId" type="text"
					size="30" value="${xhCreditTaskAssign.loanApplyId}" class="required"
					maxlength="22" />
			</p>
			<p>
				<label>初审人员：</label> <input name="firstAuditEmployeeId" type="text"
					size="30" value="${xhCreditTaskAssign.firstAuditEmployeeId}" class="required"
					maxlength="22" />
			</p>
		</div>
		<div class="formBar">
			<ul>
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
