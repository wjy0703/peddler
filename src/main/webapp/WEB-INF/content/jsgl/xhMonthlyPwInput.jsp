<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhMonthlyPw/saveXhMonthlyPw" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhMonthlyPw.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>主键：</label>
				<input name="id" type="text" size="30" value="${xhMonthlyPw.id}" class="required" maxlength="22" />
			</p>
			<p>
				<label>帐外：</label>
				<input name="additional" type="text" size="30" value="${xhMonthlyPw.additional}" class="required" maxlength="22" />
			</p>
			<p>
				<label>银行名称：</label>
				<input name="bankName" type="text" size="30" value="${xhMonthlyPw.bankName}" class="required" maxlength="256" />
			</p>
			<p>
				<label>银行账号：</label>
				<input name="bankNumber" type="text" size="30" value="${xhMonthlyPw.bankNumber}" class="required" maxlength="64" />
			</p>
			<p>
				<label>银行开户行：</label>
				<input name="bankOpen" type="text" size="30" value="${xhMonthlyPw.bankOpen}" class="required" maxlength="256" />
			</p>
			<p>
				<label>利息：</label>
				<input name="interest" type="text" size="30" value="${xhMonthlyPw.interest}" class="required" maxlength="22" />
			</p>
			<p>
				<label>出借人ID：</label>
				<input name="lenderId" type="text" size="30" value="${xhMonthlyPw.lenderId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>出借人身份证号：</label>
				<input name="lenderIdCard" type="text" size="30" value="${xhMonthlyPw.lenderIdCard}" class="required" maxlength="32" />
			</p>
			<p>
				<label>出借人名称：</label>
				<input name="lenderName" type="text" size="30" value="${xhMonthlyPw.lenderName}" class="required" maxlength="64" />
			</p>
			<p>
				<label>出借编号：</label>
				<input name="lenderNumber" type="text" size="30" value="${xhMonthlyPw.lenderNumber}" class="required" maxlength="64" />
			</p>
			<p>
				<label>出借状态：</label>
				<input name="lenderState" type="text" size="30" value="${xhMonthlyPw.lenderState}" class="required" maxlength="32" />
			</p>
			<p>
				<label>金额：</label>
				<input name="money" type="text" size="30" value="${xhMonthlyPw.money}" class="required" maxlength="22" />
			</p>
			<p>
				<label>付款日期：</label>
				<input name="payDate" type="text" size="30" value="${xhMonthlyPw.payDate}" class="required" maxlength="7" />
			</p>
			<p>
				<label>付款类型：</label>
				<input name="payType" type="text" size="30" value="${xhMonthlyPw.payType}" class="required" maxlength="32" />
			</p>
			<p>
				<label>备用字段1：</label>
				<input name="spareField01" type="text" size="30" value="${xhMonthlyPw.spareField01}" class="required" maxlength="256" />
			</p>
			<p>
				<label>备用字段2：</label>
				<input name="spareField02" type="text" size="30" value="${xhMonthlyPw.spareField02}" class="required" maxlength="256" />
			</p>
			<p>
				<label>备用字段3：</label>
				<input name="spareField03" type="text" size="30" value="${xhMonthlyPw.spareField03}" class="required" maxlength="256" />
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
