<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhLoanerAccountInfo/saveXhLoanerAccountInfo" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhLoanerAccountInfo.id}"/>
		<div class="pageFormContent" layoutH="1">
			<p>
				<label>借款人主账户ID：</label>
				<input name="loanerMainAccountId" type="text" size="30" value="${xhLoanerAccountInfo.loanerMainAccountId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>交易类型 0:还款存入   1：结算划扣：</label>
				<input name="dealingType" type="text" size="30" value="${xhLoanerAccountInfo.dealingType}" class="required" maxlength="22" />
			</p>
			<p>
				<label>交易金额：</label>
				<input name="deailingMoney" type="text" size="30" value="${xhLoanerAccountInfo.deailingMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>交易时间：</label>
				<input name="dealingTime" type="text" size="30" value="${xhLoanerAccountInfo.dealingTime}" class="required" maxlength="7" />
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
