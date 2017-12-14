<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent">



	<form method="post" id="creditAduitForm" name="creditAduitForm"
		action="${ctx}/loan/checkAduitFileToPost/${xhCreditAudit.loanApply.id}"
		class="pageForm required-validate"
		onsubmit="return creditAduitFormSubmit(this);">
		<div class="pageFormContent">
			<p>
				<label>借款人姓名：</label> <input name="creditAmount" id="creditAmount"
					type="text" size="30" value="${xhCreditAudit.loanApply.jkrxm}"
					maxlength="10" readonly />
			</p>
			<p>
				<label>借款类型：</label> <input name="creditAmount" id="creditAmount"
					type="text" size="30"
					value='<c:if test="${xhCreditAudit.loanApply.jkType=='A'}">老板借</c:if><c:if
							test="${xhCreditAudit.loanApply.jkType=='B'}">老板楼易借</c:if><c:if
							test="${xhCreditAudit.loanApply.jkType=='C'}">薪水借</c:if><c:if
							test="${xhCreditAudit.loanApply.jkType=='D'}">薪水楼易借</c:if><c:if
							test="${xhCreditAudit.loanApply.jkType=='E'}">精英借</c:if>'
					readonly />
			</p>
			<div class="divider"></div>
			<p>
				<label>批借金额（元）：</label> <input name="creditAmount" id="creditAmount"
					type="text" size="30" value="${xhCreditAudit.creditAmount}"
					maxlength="10" readonly />
			</p>

			<p>
				<label>批借期限（月）：</label> <input name="creditMonth" id="creditMonth"
					type="text" size="30" value="${xhCreditAudit.creditMonth}"
					maxlength="10" readonly />
			</p>
			<p>
				<label>外访咨询费（元）：</label> <input name="outVisitFee" id="outVisitFee"
					type="text" size="30" value="${xhCreditAudit.outVisitFee}"
					maxlength="10" readonly />
			</p>
			<p>
				<label>加急费（元）：</label> <input name="outVisitFee" id="outVisitFee"
					type="text" size="30" value="${xhCreditAudit.urgentCreditFee}"
					maxlength="10" readonly />
			</p>

			<dl class="nowrap">
				<dt>审核意见</dt>
				<dd>
					<textarea name="creditAuditReport" style="width: 80%; height: 90"
						class="" readonly>${xhCreditAudit.creditAuditReport}</textarea>
				</dd>
			</dl>


			<div class="divider"></div>
			<p>
				<label>信审员：</label> <input name="" id="" type="text" size="30"
					value="******" maxlength="10" readonly />
			</p>
			<p>
				<label>操作时间：</label> <input name="outVisitFee" id="outVisitFee"
					type="text" size="30"
					value="<fmt:formatDate  value="${xhCreditAudit.createTime}" pattern="yyyy/MM/dd:HH:mm:ss"/>"
					maxlength="10" readonly />

			</p>

			<div class="divider"></div>
			<b><font color="red">注：确认资料补齐后，审核通过即可提交通知合同制作！</font></b>
			<div class="divider"></div>
			<p>
				<input type="radio" name="isOk" id="isOk0" value="0"
					class="required" >材料未齐，审核退回
				<input type="radio" name="isOk" id="isOk1" value="1"
					class="required" >材料补齐，审核通过
			</p>
		</div>
		<div class="formBar">
			<ul>

				<li><div id="submitDiv" class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>

				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div>
				</li>
			</ul>
		</div>

	</form>
</div>

<script type="text/javascript">
<!--
	
	function creditAduitFormSubmit(obj) {

		var $form = $(obj);

		if (!$form.valid()) {

			return false;
		}
		return validateCallback(obj, navTabAjaxDone);
	}
//-->
</script>

