<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhMonthlyAr/saveXhMonthlyAr" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhMonthlyAr.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>帐外：</label>
				<input name="additional" type="text" size="30" value="${xhMonthlyAr.additional}" class="required" maxlength="22" />
			</p>
			<p>
				<label>省份：</label>
				<input name="area" type="text" size="30" value="${xhMonthlyAr.area}" class="required" maxlength="32" />
			</p>
			<p>
				<label>银行名称：</label>
				<input name="bankName" type="text" size="30" value="${xhMonthlyAr.bankName}" class="required" maxlength="256" />
			</p>
			<p>
				<label>银行账户：</label>
				<input name="bankNumber" type="text" size="30" value="${xhMonthlyAr.bankNumber}" class="required" maxlength="64" />
			</p>
			<p>
				<label>银行开户行：</label>
				<input name="bankOpen" type="text" size="30" value="${xhMonthlyAr.bankOpen}" class="required" maxlength="256" />
			</p>
			<p>
				<label>账单日：</label>
				<input name="billDay" type="text" size="30" value="${xhMonthlyAr.billDay}" class="required" maxlength="32" />
			</p>
			<p>
				<label>地市：</label>
				<input name="city" type="text" size="30" value="${xhMonthlyAr.city}" class="required" maxlength="64" />
			</p>
			<p>
				<label>利息：</label>
				<input name="interest" type="text" size="30" value="${xhMonthlyAr.interest}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款人ID：</label>
				<input name="loanId" type="text" size="30" value="${xhMonthlyAr.loanId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款人身份证号：</label>
				<input name="loanIdCard" type="text" size="30" value="${xhMonthlyAr.loanIdCard}" class="required" maxlength="32" />
			</p>
			<p>
				<label>借款人名称：</label>
				<input name="loanName" type="text" size="30" value="${xhMonthlyAr.loanName}" class="required" maxlength="64" />
			</p>
			<p>
				<label>借款人编号：</label>
				<input name="loanNumber" type="text" size="30" value="${xhMonthlyAr.loanNumber}" class="required" maxlength="64" />
			</p>
			<p>
				<label>借款产品：</label>
				<input name="loanPro" type="text" size="30" value="${xhMonthlyAr.loanPro}" class="required" maxlength="32" />
			</p>
			<p>
				<label>借款状态：</label>
				<input name="loanState" type="text" size="30" value="${xhMonthlyAr.loanState}" class="required" maxlength="32" />
			</p>
			<p>
				<label>金额：</label>
				<input name="money" type="text" size="30" value="${xhMonthlyAr.money}" class="required" maxlength="22" />
			</p>
			<p>
				<label>结算日期：</label>
				<input name="settlementDate" type="text" size="30" value="${xhMonthlyAr.settlementDate}" class="required" maxlength="7" />
			</p>
			<p>
				<label>备用字段1：</label>
				<input name="spareField01" type="text" size="30" value="${xhMonthlyAr.spareField01}" class="required" maxlength="256" />
			</p>
			<p>
				<label>备用字段2：</label>
				<input name="spareField02" type="text" size="30" value="${xhMonthlyAr.spareField02}" class="required" maxlength="256" />
			</p>
			<p>
				<label>备用字段3：</label>
				<input name="spareField03" type="text" size="30" value="${xhMonthlyAr.spareField03}" class="required" maxlength="256" />
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
