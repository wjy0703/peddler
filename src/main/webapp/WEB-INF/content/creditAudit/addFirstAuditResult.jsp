<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">


	<div class="pageContent" width="100%">


		<form method="post" id="creditAduitForm" name="creditAduitForm"
			action="${ctx}/loan/saveAuditResult/${loanApplyId}"
			class="pageForm required-validate"
			onsubmit="return creditAduitFormSubmit(this);">
			<input type="hidden" name="creditType" value="1" />




			<div class="pageFormContent" width="100%" layoutH="56">


				<table width="100%">
					<tr>
						<td><label>借款人姓名：</label><input value="${loanApply.jkrxm}"
							readonly /></td>
						<td><label>借款人证件号码：</label><input value="${loanApply.zjhm}"
							readonly /></td>
						<td><label>借款类型：</label><input
							value='<sen:vtoName value="${loanApply.jkType}" coding="productType"/>' readonly /></td>
					</tr>
					<tr>
						<td><label>借款金额：</label><input
							value="${loanApply.jkLoanQuota}" readonly /></td>
						<td><label>借款期数：</label><input value="${loanApply.jkCycle}"
							readonly /></td>
						<td><label>是否有共借人 ：</label><input
							value="${loanApply.togetherPerson}" readonly /></td>
					</tr>
					<c:if test="${loanApply.togetherPerson eq '是'}">
						<tr>
							<td><label>共借人姓名</label><input
								value="<sen:showTogether lendId='${loanApply.id}' yesOrNo='${loanApply.togetherPerson}'/>" readonly /></td>
							<td><label>共借人身份证号码：</label><input
								value="<sen:showTogetherIdentification lendId='${loanApply.id}' yesOrNo='${loanApply.togetherPerson}'/>"
								readonly /></td>
							<td></td>
						</tr>
					</c:if>
				</table>
				<div class="divider"></div>
				<table width="100%">



					<tr>
						<td><label>批借金额（元）：</label> <input name="creditAmount"
							id="creditAmount" type="text" size="20"
							value="${xhCreditAudit.creditAmount}" class="required number">
						</td>
						<td><label>批借期限（月）：</label> <input name="creditMonth"
							id="creditMonth" type="text" size="20"
							value="${xhCreditAudit.creditMonth}" maxlength="10"
							class="required number" /></td>
						<td><label>综合费率(%)：</label> <input name="creditAllRate"
							class="required number" id="creditAllRate" type="text" size="20"
							value="${xhCreditAudit.creditAllRate}" /></td>


					</tr>

					<tr>
						<td><label>外访咨询费（元）：</label> <input name="outVisitFee"
							id="outVisitFee" type="text" size="20"
							value="${xhCreditAudit.outVisitFee}" class="required number" /><span
							class="info"> </span></td>
						<td><label>加急费（元）：</label> <input name="urgentCreditFee"
							id="urgentCreditFee" type="text" size="20"
							value="${xhCreditAudit.urgentCreditFee}" class="required number" />
						</td>
					</tr>


				</table>
				<dl class="nowrap">
					<dt>初审报告：</dt>
					<dd>
						<textarea name="creditAuditReport" style="width: 93%; height: 70"
							class="required">${xhCreditAudit.creditAuditReport}</textarea>
					</dd>
				</dl>

				<label>初审结果：</label> <input type="radio" name="creditResult"
					id="creditResult1" value="1"
					onclick="return creditResult1Clicked();"> 初审通过 <input
					type="radio" name="creditResult" id="creditResult4" value="4"
					onclick="return creditResult4Clicked();" />初审通过(追加共借人) <input
					type="radio" name="creditResult" id="creditResult3" value="3"
					onclick="return creditResult3Clicked();" />退回 <input type="radio"
					name="creditResult" id="creditResult0" value="0"
					onclick="return creditResult0Clicked();"> 初审拒绝 <input
					type="radio" name="creditResult" id="creditResult5" value="5"
					onclick="return creditResult0Clicked();"> 客户放弃
<input
				type="radio" name="creditResult" id="creditResult5" value="6"
				onclick="return creditResult0Clicked();"> 黑名单
			

				<div class="dynamics" id="togetherDiv" style="display: none">
					<div class="divider"></div>
					<p>
						<label>共借人姓名：</label><input type="text" name="togetherName"
							id="togetherName" value="" size="" class="" />
					</p>
					<p>
						<label>共借人身份证件号码：</label><input type="text" name="togetherCardNo"
							id="togetherCardNo" value="" size="" class="" />
					</p>

				</div>

				<dl class="nowrap dynamics" id="creditRefuseReason"
					style="display: none">
					<dt>退回/拒借原因：</dt>
					<dd>
						<textarea name="creditRefuseReason" id="creditRefuseReason1"
							rows="7" style="width: 93%; height: 70">${xhCreditAudit.creditRefuseReason}</textarea>
					</dd>
				</dl>
				<div class="divider"></div>

				<div id="empDiv" class="dynamics ">
					<p>
						<label>请选择复审人员：</label> <input
							type="text" id="empname" class="required" name="employee.name"
							value="" suggestFields="name,deptname"
							suggestUrl="${ctx}/baseinfo/findEmpByPosition/23"
							lookupGroup="employee" readonly size="" /><input type="hidden" name="employee.id"
							value="${xhCreditTaskAssign.secondAduitEmployee.id}" /> 
					</p>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
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


<script type="text/javascript">
	$(function() {
		$(".dynamics").hide();
	});
	function creditResult0Clicked() {

		document.getElementById('urgentCreditFee').disabled = true;
		document.getElementById('urgentCreditFee').value = '0';
		document.getElementById('creditAllRate').disabled = true;
		document.getElementById('creditAllRate').value = '0';
		document.getElementById('outVisitFee').value = '0';
		document.getElementById('outVisitFee').disabled = true;
		document.getElementById('creditMonth').value = '0';
		document.getElementById('creditMonth').disabled = true;
		document.getElementById('creditAmount').value = '0';
		document.getElementById('creditAmount').disabled = true;

		document.getElementById('creditRefuseReason').style.display = 'block';
		document.getElementById('empDiv').style.display = 'block';
		document.getElementById('empname').value = '';

		document.getElementById('togetherDiv').style.display = 'none';
		document.getElementById('togetherName').className = '';
		document.getElementById('togetherCardNo').className = '';
		document.getElementById('creditRefuseReason1').className = 'required';

	}

	function removeDynamicsValidator() {
		$('input', $(".dynamics")).removeClass('required');
		$('textarea', $(".dynamics")).removeClass('required');
	}

	function creditResult1Clicked() {
		//debugger;
		// $(".dynamics").hide();
		// removeDynamicsValidator();
		// $("#empDiv").show();
		//  $('input',$("#empDiv")).addClass('required');
		document.getElementById('urgentCreditFee').disabled = false;
		//document.getElementById('urgentCreditFee').value = '';
		//document.getElementById('outVisitFee').value = '';
		//document.getElementById('creditMonth').value = '';
		//document.getElementById('creditAmount').value = '';
		document.getElementById('creditAllRate').disabled = false;
		//document.getElementById('creditAllRate').value = '';
		document.getElementById('creditAmount').disabled = false;
		document.getElementById('outVisitFee').disabled = false;
		document.getElementById('creditMonth').disabled = false;
		document.getElementById('creditRefuseReason').style.display = 'none';
		document.getElementById('empDiv').style.display = 'block';
		document.getElementById('empname').value = '';
		document.getElementById('togetherDiv').style.display = 'none';
		document.getElementById('togetherName').className = '';
		document.getElementById('togetherCardNo').className = '';
		document.getElementById('creditRefuseReason1').className = '';
	}

	function creditResult3Clicked() {
		document.getElementById('urgentCreditFee').disabled = true;
		document.getElementById('urgentCreditFee').value = '0';
		document.getElementById('creditAllRate').disabled = true;
		document.getElementById('creditAllRate').value = '0';
		document.getElementById('outVisitFee').value = '0';
		document.getElementById('outVisitFee').disabled = true;
		document.getElementById('creditMonth').value = '0';
		document.getElementById('creditMonth').disabled = true;
		document.getElementById('creditAmount').value = '0';
		document.getElementById('creditAmount').disabled = true;
		document.getElementById('creditRefuseReason').style.display = 'block';
		document.getElementById('empDiv').style.display = 'none';
		document.getElementById('empname').value = '0';

		document.getElementById('togetherName').className = '';
		document.getElementById('togetherCardNo').className = '';
		document.getElementById('togetherDiv').style.display = 'none';
		document.getElementById('creditRefuseReason1').className = 'required';

	}

	function creditResult4Clicked() {
		//creditResult1Clicked();
		//$('#togetherDiv').show();
		//$('input',$('#togetherDiv')).addClass('required');

		document.getElementById('urgentCreditFee').disabled = false;
		//document.getElementById('urgentCreditFee').value = '';
		//document.getElementById('outVisitFee').value = '';
		//document.getElementById('creditMonth').value = '';
		//document.getElementById('creditAmount').value = '';
		document.getElementById('creditAllRate').disabled = false;
		//document.getElementById('creditAllRate').value = '';
		document.getElementById('creditAmount').disabled = false;
		document.getElementById('outVisitFee').disabled = false;
		document.getElementById('creditMonth').disabled = false;
		document.getElementById('creditRefuseReason').style.display = 'none';
		document.getElementById('empDiv').style.display = 'block';
		document.getElementById('togetherDiv').style.display = 'block';
		document.getElementById('empname').value = '';
		document.getElementById('togetherName').className = 'required';
		document.getElementById('togetherCardNo').className = 'required isIdCardNo';
		document.getElementById('creditRefuseReason1').className = '';
	}

	function creditAduitFormSubmit(obj) {
		var creditResult = document.getElementsByName("creditResult");
		var flag = false;
		for ( var i = 0; i < creditResult.length; i++) {
			if (creditResult[i].checked) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			alertMsg.warn("请选择初审结果！");
			return false;
		}
		if (document.getElementById("creditResult4").checked) {

			var togetherName = document.creditAduitForm.togetherName.value;
			var togetherCardNo = document.creditAduitForm.togetherCardNo.value;
			if (togetherName == "" || togetherCardNo == "") {
				var message = "<ul>";
				if (togetherName == "") {
					message = message + "<li>共借人不能为空！</li>";
					document.creditAduitForm.togetherName.focus();
				} else if (togetherCardNo == "") {
					message = message + "<li>共借人身份证件不能为空！</li>";
					document.creditAduitForm.togetherCardNo.focus();
				}
				message = message + "</ul>";
				alertMsg.warn(message);

				return false;
			}

		} else if (document.getElementById("creditResult0").checked) {

			var creditRefuseReason = document.creditAduitForm.creditRefuseReason.value;

			if (creditRefuseReason == "") {
				var message = "<ul>";

				message = message + "<li>请填写退回或拒借的原因！</li>";

				message = message + "</ul>";
				alertMsg.warn(message);
				document.creditAduitForm.creditRefuseReason.focus();
				return false;
			}

		} else if (document.getElementById("creditResult3").checked) {

			var creditRefuseReason = document.creditAduitForm.creditRefuseReason.value;

			if (creditRefuseReason == "") {
				var message = "<ul>";

				message = message + "<li>请填写退回或拒借的原因！</li>";

				message = message + "</ul>";
				alertMsg.warn(message);
				document.creditAduitForm.creditRefuseReason.focus();
				return false;
			}

		}
		var $form = $(obj);
		if (!$form.valid()) {

			return false;
		}
		return validateCallback(obj, navTabAjaxDone);

	}
</script>