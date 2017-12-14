<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
	<h1>查看初审信息</h1>

<div class="pageFormContent" width="100%" >
		<form method="post"
			action="${ctx}/loan/saveAuditResult/${loanApplyId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
		
					<p>
							<label>借款人姓名：</label><input value="${loanApply.jkrxm}" readonly />
						</p>
						<p>
							<label>借款类型：</label><input value='<c:if test="${loanApply.jkType=='A'}">老板借</c:if><c:if
							test="${loanApply.jkType=='B'}">老板楼易借</c:if><c:if
							test="${loanApply.jkType=='C'}">薪水借</c:if><c:if
							test="${loanApply.jkType=='D'}">薪水楼易借</c:if><c:if
							test="${loanApply.jkType=='E'}">精英借</c:if>'
								readonly />
						</p>
					<p>
							<label>借款金额：</label><input value="${loanApply.jkLoanQuota}"
								readonly />
						</p>
				<p>
							<label>借款期数：</label><input value="${loanApply.jkCycle}" readonly />
						</p>


		
			
			</form></div>

	

	

			</div>
		
<div class="panel">
<div class="pageFormContent" width="100%" layoutH="">
		<form method="post"
			action="${ctx}/loan/saveAuditResult/${loanApplyId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="creditType" value="1" />
			<table
				style="border-width: 0px; padding: 0px; margin: 0px; border-spacing: 0px; border-collapse: collapse;"
				width="100%" layoutH="" nowrapTD="false">

				<tr>
					<td><label>批借金额（元）：</label> <input name="creditAmount" id="creditAmount"
						type="text" size="30" value="${xhCreditAudit.creditAmount}"
						class="required" maxlength="10" /></td>
				</tr>
				<tr>
					<td><label>批借期限（月）：</label> <input name="creditMonth" id="creditMonth"
						type="text" size="30" value="${xhCreditAudit.creditMonth}"
						maxlength="10" class="required"/></td>
				</tr>
				<tr>
					<td><label>综合费率：(%)</label> <input name="creditAllRate"
						class="required number" id="creditAllRate" type="text" size="30"
						value="${xhCreditAudit.creditAllRate}" maxlength="10" /> <span
						class="info"> (注：数字格式,如2.5%,只需要输入2.5)</span></td>
				</tr>

				<tr>

					<td><label>外访咨询费（元）：</label> <input name="outVisitFee" id="outVisitFee"
						type="text" size="30" value="${xhCreditAudit.outVisitFee}"
						maxlength="10"  class="required"/></td>
				</tr>

				<tr>
					<td>
						<dl class="nowrap">
							<dt>初审报告：</dt>
							<dd>
								<textarea name="creditAuditReport" 
									style="width: 93%; height: 70" class="required">${xhCreditAudit.creditAuditReport}</textarea>
							</dd>
						</dl>
					</td>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td><label >初审结果：</label> <input type="radio"
						name="creditResult" value="1" checked
						onclick="document.getElementById('outVisitFee').value='';document.getElementById('creditMonth').value='';document.getElementById('creditAmount').value='';document.getElementById('creditAllRate').disabled = false;document.getElementById('creditAllRate').value = '';document.getElementById('creditAmount').disabled = false;document.getElementById('outVisitFee').disabled = false;document.getElementById
('creditMonth').disabled = false;document.getElementById('creditRefuseReason').style.display='none'">
						初审通过 <input type="radio" name="creditResult" value="0"
						onclick="document.getElementById('creditAllRate').disabled = true;document.getElementById

('creditAllRate').value='0';document.getElementById('outVisitFee').value='0';document.getElementById('outVisitFee').disabled = true;document.getElementById

('creditMonth').value='0';document.getElementById('creditMonth').disabled = true;document.getElementById('creditAmount').value='0';document.getElementById

('creditAmount').disabled = true;document.getElementById('creditRefuseReason').style.display='block'">
						初审拒绝</td>
				</tr>
			</table>
			<dl class="nowrap" id="creditRefuseReason" style="display: none">
				<dt>拒借原因：</dt>
				<dd>
					<textarea name="creditRefuseReason" rows="7"
						style="width: 93%; height: 70">${xhCreditAudit.creditRefuseReason}</textarea>
				</dd>
			</dl>
<div class="formBar">
				<ul>
					
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