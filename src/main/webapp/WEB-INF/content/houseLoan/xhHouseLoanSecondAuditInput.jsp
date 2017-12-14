<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post"
			action="${ctx}/xhHouseLoanAudit/saveXhHouseLoanSecondAudit"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhHouseLoanAudit.id}" /> <input
				type="hidden" name="house_apply_id"
				value="${xhHouseLoanAudit.xhHouseLoanApply.id}" />

			<div class="pageFormContent" layoutH="56">

				<p>
					<label>客户姓名：</label> <input name="loanerName" type="text" size="30"
						value="${xhHouseLoanAudit.xhHouseLoanApply.loanerName}" class=""
						maxlength="50" readonly />
				</p>
				<p>
					<label>申请金额：</label> <input name="loanLoanAmount" type="text"
						size="30"
						value="${xhHouseLoanAudit.xhHouseLoanApply.loanLoanAmount}"
						class="" maxlength="50" readonly />
				</p>
				<p>
					<label>用款目的：</label> <input name="loanReason" type="text" size="30"
						value="${xhHouseLoanAudit.xhHouseLoanApply.loanUse}"
						class="" maxlength="50" readonly />
				</p>
				<p>
					<label>还款来源：</label> <input name="repaySource" type="text"
						size="30"
						value="${xhHouseLoanAudit.xhHouseLoanApply.backMoneyType}"
						class="" maxlength="50" readonly />
				</p>
				<dl class="nowrap">
					<dt>房屋详细地址：</dt>
					<dd>
						<textarea name="hjadress" style="width: 100%;" readonly>${xhHouseLoanAudit.xhHouseLoanApply.houseAddress}</textarea>
					</dd>
				</dl>

				<div class="divider"></div>
				<h1>房屋审核信息</h1>
				<div class="divider"></div>


				<p>
					<label>房屋所在地：</label> <input name="houseAddress" type="text"
						size="30" value="${xhHouseLoanAudit.houseAddress}"
						class="required" maxlength="50" readonly />
				</p>
				<p>
					<label>房屋周边设施：</label> <input name="houseAroundObject" type="text"
						size="30" value="${xhHouseLoanAudit.houseAroundObject}"
						class="required" maxlength="200" readonly />
				</p>
				<p>
					<label>银行抵押金额：</label> <input name="houseBankMortgageValue"
						type="text" size="30"
						value="${xhHouseLoanAudit.houseBankMortgageValue}"
						class="required" maxlength="22" />
				</p>

				<p>
					<label>可放款金额：</label> <input name="makableValue" type="text"
						size="30" value="${xhHouseLoanAudit.makableValue}"
						class="required" maxlength="22" readonly />
				</p>




				<p>
					<label>房屋建筑面积：</label> <input name="buildingArea" type="text"
						size="30" value="${xhHouseLoanAudit.buildingArea}"
						class="required" maxlength="22" readonly />
				</p>
				<p>
					<label>房屋年限：</label> <input name="buildingYears" type="text"
						size="30" value="${xhHouseLoanAudit.buildingYears}"
						class="required" maxlength="22" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>综合评定单价：</label> <input name="aduitUnitPrice" type="text"
						size="30" value="${xhHouseLoanAudit.aduitUnitPrice}"
						class="required" maxlength="22" readonly />
				</p>
				<p>
					<label>市场价值：</label> <input name="marketUnitPrice" type="text"
						size="30" value="${xhHouseLoanAudit.marketUnitPrice}"
						class="required" maxlength="22" readonly />
				</p>

				<dl class="nowrap">
					<dt>其它审核信息：</dt>
					<dd>
						<textarea name="otherAuditInfo" style="width: 100%;" readonly>${xhHouseLoanAudit.otherAuditInfo}</textarea>
					</dd>
				</dl>
				<div class="divider"></div>

				<h1>中介收房价格</h1>
				<div class="divider"></div>
				<p>
					<label>中介①名称：</label> <input name="mediumOneName" type="text"
						size="30" value="${xhHouseLoanAudit.mediumOneName}"
						class="required" maxlength="50" readonly />
				</p>
				<p>
					<label>中介①收房价：</label> <input name="mediumOnePrice" type="text"
						size="30" value="${xhHouseLoanAudit.mediumOnePrice}"
						class="required" maxlength="22" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>中介②名称：</label> <input name="mediumTwoName" type="text"
						size="30" value="${xhHouseLoanAudit.mediumTwoName}"
						class="required" maxlength="50" readonly />
				</p>
				<p>
					<label>中介②收房价：</label> <input name="mediumTwoPrice" type="text"
						size="30" value="${xhHouseLoanAudit.mediumTwoPrice}"
						class="required" maxlength="22" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>中介③名称：</label> <input name="mediumThreeName" type="text"
						size="30" value="${xhHouseLoanAudit.mediumThreeName}"
						class="required" maxlength="50" readonly />
				</p>
				<p>
					<label>中介③收房价：</label> <input name="mediumThreePrice" type="text"
						size="30" value="${xhHouseLoanAudit.mediumThreePrice}"
						class="required" maxlength="22" readonly />
				</p>
				<div class="divider"></div>
				<h1>审核意见及结果</h1>
				<div class="divider"></div>



				<dl class="nowrap">
					<dt>审核意见：</dt>
					<dd>
						<textarea name="aduitSuggestion" style="width: 100%;">${xhHouseLoanAudit.aduitSuggestion}</textarea>
					</dd>
				</dl>
				<p>
					<label>审核结果：</label> <input name="aduitResult" type="radio"
						size="30" value="1" class="" maxlength="20" />审核通过 <input
						name="aduitResult" type="radio" size="30" value="0" class=""
						maxlength="20" />审核拒绝
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">提交</button>
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