<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
		<!-- <form method="post" action="${ctx}/xhCapitalOverdue/setOverdueBuyer" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">-->
		<form method="post" action="${ctx}/xhCapitalOverdue/deleteOverdueSetAcc"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="id" value="${xhCapitalOverdue.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>存入金额：</label>
				<input name="spareField06" type="text" size="30" value="<fmt:formatNumber value="${xhCapitalOverdue.overdueMoney}" pattern="##00.00" />" class="required" maxlength="256" />
			</p>
			<p>
				<label>交易时间：</label>
				<input name="spareField05" type="text"
						size="10" value="" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" 
						 readonly="true"/><a
						class="inputDateButton" href="#">选择</a><span class="info">(注：客户线下还款时间)</span>
			</p>
			<p>
				<label>银行名称：</label>
				<input name="bankName" type="text" size="30" value="${xhCapitalOverdue.bankName}" maxlength="256" readonly="readonly"/>
			</p>
			<p>
				<label>银行账号：</label>
				<input name="bankNumber" type="text" size="30" value="${xhCapitalOverdue.bankNumber}" maxlength="64" readonly="readonly"/>
			</p>
			<p>
				<label>银行开户行：</label>
				<input name="bankOpen" type="text" size="30" value="${xhCapitalOverdue.bankOpen}" maxlength="256" readonly="readonly"/>
			</p>
			<p>
				<label>借款人身份证号：</label>
				<input name="lenderIdCard" type="text" size="30" value="${xhCapitalOverdue.lenderIdCard}" maxlength="32" readonly="readonly"/>
			</p>
			<p>
				<label>借款人名称：</label>
				<input name="lenderName" type="text" size="30" value="${xhCapitalOverdue.lenderName}" maxlength="64" readonly="readonly"/>
			</p>
			<p>
				<label>借款编号：</label>
				<input name="lenderNumber" type="text" size="30" value="${xhCapitalOverdue.lenderNumber}" maxlength="64" readonly="readonly"/>
			</p>
			<p>
				<label>逾期时间：</label>
				<input name="overdueDate1" type="text" size="30" value="${xhCapitalOverdue.overdueDate}" readonly="readonly"/>
			</p>
			<p>
				<label>逾期金额：</label>
				<input name="overdueMoney1" type="text" size="30" value="￥<fmt:formatNumber value="${xhCapitalOverdue.overdueMoney}" pattern="#,#00.00" />" maxlength="22" readonly="readonly"/>
			</p>
			<p>
				<label>违约金：</label>
				<input name="damagesMoney1" type="text" size="30" value="￥<fmt:formatNumber value="${xhCapitalOverdue.damagesMoney}" pattern="#,#00.00" />" maxlength="22" readonly="readonly"/>
			</p>
			<p>
				<label>罚息：</label>
				<input name="punishInterest1" type="text" size="30" value="￥<fmt:formatNumber value="${xhCapitalOverdue.punishInterest}" pattern="#,#00.00" />" maxlength="22" readonly="readonly"/>
			</p>
			<p>
				<label>逾期天数：</label>
				<input name="spareField02" type="text" size="30" value="${xhCapitalOverdue.spareField02}" maxlength="256" readonly="readonly" />
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
<script>
function closeDialogFreshen(param){
	navTab.reloadFlag('rel_listXhCapitalOverdue');
	navTabAjaxDone(param);
}
</script>