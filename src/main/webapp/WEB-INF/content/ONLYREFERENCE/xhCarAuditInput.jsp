<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCarAudit/saveXhCarAudit" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCarAudit.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>信审类型：初审(1)、复审(2)、终审(100)：</label>
				<input name="creditType" type="text" size="30" value="${xhCarAudit.creditType}" class="required" maxlength="22" />
			</p>
			<p>
				<label>信审状态：0信审未结束 1信审结束：</label>
				<input name="creditState" type="text" size="30" value="${xhCarAudit.creditState}" class="required" maxlength="22" />
			</p>
			<p>
				<label>审批金额：</label>
				<input name="creditAmount" type="text" size="30" value="${xhCarAudit.creditAmount}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款期限（天）：</label>
				<input name="creditMonth" type="text" size="30" value="${xhCarAudit.creditMonth}" class="required" maxlength="22" />
			</p>
			<p>
				<label>综合费率：</label>
				<input name="creditAllRate" type="text" size="30" value="${xhCarAudit.creditAllRate}" class="required" maxlength="22" />
			</p>
			<p>
				<label>业务收费：</label>
				<input name="operationFee" type="text" size="30" value="${xhCarAudit.operationFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>外访费(需求说明文档没有)：</label>
				<input name="outVisitFee" type="text" size="30" value="${xhCarAudit.outVisitFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>加急费需求说明文档没有)：</label>
				<input name="urgentCreditFee" type="text" size="30" value="${xhCarAudit.urgentCreditFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>拒借原因：</label>
				<input name="creditRefuseReason" type="text" size="30" value="${xhCarAudit.creditRefuseReason}" class="required" maxlength="1000" />
			</p>
			<p>
				<label>信审意见：</label>
				<input name="creditAuditReport" type="text" size="30" value="${xhCarAudit.creditAuditReport}" class="required" maxlength="1000" />
			</p>
			<p>
				<label>信审结果：1通过、0拒绝：</label>
				<input name="creditResult" type="text" size="30" value="${xhCarAudit.creditResult}" class="required" maxlength="22" />
			</p>
			<p>
				<label>信审通过后形成的客户编号：</label>
				<input name="passedCustomerNo" type="text" size="30" value="${xhCarAudit.passedCustomerNo}" class="required" maxlength="22" />
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
