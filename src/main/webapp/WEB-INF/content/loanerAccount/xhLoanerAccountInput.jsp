<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<h1>还款账户信息</h1>
	<div class="pageContent">
		<div class="pageFormContent">
			<p>
				<label>借款合同编号：</label> <input name="loanContractId" type="text"
					size="20"
					value="${xhLoanerAccountInfo.loanerMainAccount.loanContract.jkhtbm}"
					class="" maxlength="" readonly />
			</p>
			<p>
				<label>借款人姓名：</label> <input name="loanApplyId" type="text"
					size="20"
					value="${xhLoanerAccountInfo.loanerMainAccount.loanApply.jkrxm}"
					class="" maxlength="" readonly />
			</p>
			<div class="divider"></div>
			<p>

				<label>开户行：</label> <input name="loanContractId" type="text"
					size="20"
					value="${xhLoanerAccountInfo.loanerMainAccount.loanApply.bankOpen}"
					class="" maxlength="" readonly />
			</p>
			<p>
				<label>账户名：</label> <input name="loanContractId" type="text"
					size="20"
					value="${xhLoanerAccountInfo.loanerMainAccount.loanApply.bankUsername}"
					class="" maxlength="" readonly />
			</p>
			<p>
				<label>账号：</label> <input name="loanContractId" type="text"
					size="20"
					value="${xhLoanerAccountInfo.loanerMainAccount.loanApply.bankNum}"
					class="" maxlength="" readonly />
			</p>

			<div class="divider"></div>

			<p>
				<label>账户余额(￥)：</label> <input name="accountBalance" type="text"
					size="20"
					value="${xhLoanerAccountInfo.loanerMainAccount.accountBalance}"
					class="" maxlength="" readonly />
			</p>
			<p>
				<label>账户状态： </label>
				<c:if
					test="${xhLoanerAccountInfo.loanerMainAccount.accountState==0}">
					<input name="accountState" type="text" size="20" value="正常"
						readonly />
				</c:if>

			</p>
		</div>


	</div>
</div>
<div class="panel" >
	<h1>录入还款明细</h1>
	<div class="pageContent">
		<form method="post"
			action="${ctx}/xhLoanerAccountInfo/saveXhLoanerAccountInfo"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhLoanerAccountInfo.id}"/>
			<input type="hidden" name="mainAccountId"
				value="${xhLoanerAccountInfo.loanerMainAccount.id}" />
			<div class="pageFormContent" layoutH="301">

				<p>
					<label>交易类型： </label> <input name="dealingType" type="hidden"
						size="20" value="0" maxlength="22" readonly /> <input
						name="dealingTypeTxt" type="text" size="20" value="还款"
						maxlength="22" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>还款金额(￥)：</label> <input name="deailingMoney" type="text"
						size="20" value="${xhLoanerAccountInfo.deailingMoney}"
						class="required" maxlength="22" />
				</p>
				<p>
					<label>还款时间：</label> <input name="dealingTime" type="text"
						size="10" value="${xhLoanerAccountInfo.dealingTime}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" 
						 readonly="true"/><a
						class="inputDateButton" href="#">选择</a><span class="info">(注：客户线下还款时间)</span>
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



</div>