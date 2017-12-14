<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCapitalLoanReportInfo/saveXhCapitalLoanReportInfo" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCapitalLoanReportInfo.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>报告主表：</label>
				<input name="reportId" type="text" size="30" value="${xhCapitalLoanReportInfo.reportId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>出借编号：</label>
				<input name="lendNo" type="text" size="30" value="${xhCapitalLoanReportInfo.lendNo}" class="required" maxlength="64" />
			</p>
			<p>
				<label>初始出借日期：</label>
				<input name="firstLendDate" type="text" size="30" value="${xhCapitalLoanReportInfo.firstLendDate}" class="required" maxlength="7" />
			</p>
			<p>
				<label>出借及回收方式：</label>
				<input name="lendType" type="text" size="30" value="${xhCapitalLoanReportInfo.lendType}" class="required" maxlength="64" />
			</p>
			<p>
				<label>初始出借金额：</label>
				<input name="firstLendMoney" type="text" size="30" value="${xhCapitalLoanReportInfo.firstLendMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>本期应还金额：</label>
				<input name="shoudBack" type="text" size="30" value="${xhCapitalLoanReportInfo.shoudBack}" class="required" maxlength="22" />
			</p>
			<p>
				<label>本期实际还款金额：</label>
				<input name="realBack" type="text" size="30" value="${xhCapitalLoanReportInfo.realBack}" class="required" maxlength="22" />
			</p>
			<p>
				<label>延迟支付金额：</label>
				<input name="latePayMoney" type="text" size="30" value="${xhCapitalLoanReportInfo.latePayMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>账户管理费率：</label>
				<input name="mngFeeRate" type="text" size="30" value="${xhCapitalLoanReportInfo.mngFeeRate}" class="required" maxlength="22" />
			</p>
			<p>
				<label>账户管理费：</label>
				<input name="mngFee" type="text" size="30" value="${xhCapitalLoanReportInfo.mngFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>当期受让金额：</label>
				<input name="reLend" type="text" size="30" value="${xhCapitalLoanReportInfo.reLend}" class="required" maxlength="22" />
			</p>
			<p>
				<label>当期回收金额：</label>
				<input name="drawing" type="text" size="30" value="${xhCapitalLoanReportInfo.drawing}" class="required" maxlength="22" />
			</p>
			<p>
				<label>当前全部账户资产：</label>
				<input name="allMoney" type="text" size="30" value="${xhCapitalLoanReportInfo.allMoney}" class="required" maxlength="22" />
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
