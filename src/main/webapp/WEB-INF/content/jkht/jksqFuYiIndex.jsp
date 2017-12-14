<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
	<h1>复议查看</h1>
	<div class="pageContent">
		<form method="post" id="jkhtform" name="jkhtform"
			action="${ctx}/loan/saveLoanFuYi"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhJkht.id}" /> <input
				type="hidden" name="xhJksq.id" value="${xhJkht.xhJksq.id}" /> <input
				type="hidden" id="opt" name="opt" />
			<%-- 	<input type="hidden" name="state" id="state" value="${xhJkht.xhJksq.state}" />  --%>
			
			<!--  <div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/xhJkht/listJksq"><span>返回列表</span></a></li>
			</ul>		</div>	-->
			<div class="pageFormContent" layoutH="80">
				<p>
					<label>借款人姓名：</label> <input name="jkrxm" type="text" size="30"
						value="${xhCreditAudit.loanApply.jkrxm}"  maxlength="22"
						disabled="disabled" />
				</p>
<p>
					<label>借款人身份证号：</label> <input name="jkrxm" type="text" size="30"
						value="${xhCreditAudit.loanApply.zjhm}"  maxlength="22"
						disabled="disabled" />
				</p>
	<c:if test="${xhCreditAudit.loanApply.togetherPerson eq '是'}">
						<div class="divider"></div>
						<p><label>共借人姓名</label><input
							value="<sen:showTogether lendId='${xhCreditAudit.loanApply.id}' yesOrNo='${xhCreditAudit.loanApply.togetherPerson}'/>" size="30"  disabled="disabled" 	/></p>
						<p><label>共借人身份证号码：</label><input
							value="<sen:showTogetherIdentification lendId='${xhCreditAudit.loanApply.id}' yesOrNo='${xhCreditAudit.loanApply.togetherPerson}'/>" size="30"  disabled="disabled" 	/></p>
						
					</c:if>

				<div class="divider"></div>
				<table>
					<tr>

						<td><label>批借金额：</label> <input id="pdje" name="pdje"
							type="text" size="30" value="${xhCreditAudit.creditAmount}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>还款期数：</label> <input id="hkqs" name="hkqs"
							type="text" size="30" value="${xhCreditAudit.creditMonth}" 
							maxlength="22" disabled="disabled" /></td>
					</tr>
					<tr>
						
						<td><label>信访费：</label> <input id="xff" name="xff"
							type="text" size="30" value="${xhCreditAudit.outVisitFee}" 
							maxlength="22" disabled="disabled" /></td>
							<td><label>加急费：</label> <input id="urgentCreditFee" name="urgentCreditFee"
							type="text" size="30" value="<c:if test="${xhCreditAudit.urgentCreditFee == null || xhCreditAudit.urgentCreditFee == 

''}">0</c:if><c:if test="${xhCreditAudit.urgentCreditFee != null && xhCreditAudit.urgentCreditFee != ''}">${xhCreditAudit.urgentCreditFee}</c:if>" 
							
							maxlength="22" disabled="disabled" /></td>
					</tr> 
				 	<tr>
						<td><label>综合费率%：</label> <input id="yzhfl" name="yzhfl"
							type="text" size="30" value="${xhCreditAudit.creditAllRate}" class="required"
							maxlength="22" disabled="disabled" /></td>
					</tr> 
				
				</table>
				<c:if test="${xhJkht!=null}">
				<div class="divider"></div>
				
				<table>
					<tr>

						<td><label>实放金额：</label> <input id="fkje" name="fkje"
							type="text" size="30" value="${xhJkht.fkje}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>合同金额：</label> <input id="htje" name="htje"
							type="text" size="30" value="${xhJkht.htje}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>月还款金额：</label> <input id="ylxje" name="yhkje"
							type="text" size="30" value="${xhJkht.yhkje}" 
							maxlength="22" disabled="disabled" /></td>
					</tr>
				</table>
				</c:if>
<div class="divider"></div>
<%-- <p>
					<label>合同签订日期：</label> <input name="qdrq" type="text"
					size="25" value="${xhJkht.qdrq}" class="required date"
					maxlength="20" /> <a class="inputDateButton" href="#">选择</a>
				</p> --%>
				<table>
					<tr>
						<!--  复议申请描述使用jksq字段backup08 -->
						<td><label>复议申请描述：</label><textarea id="backup08" name="backup08" class="required" rows="8" cols="100" disabled="disabled" >${xhCreditAudit.loanApply.backup08 }</textarea></td>
					</tr>
				</table>
				<%-- <c:if test="${xhJkht.xhJksq.state !='70' }"> --%>
					<div class="divider"></div>
				<table>
					<tr>
						<td><label>复议结果描述：</label><textarea name="fujgms" disabled="disabled" rows="8" cols="100">${xhCreditAudit.creditAuditReport }</textarea></td>
					</tr>
				</table>
				
					<div class="divider"></div>
				<table>
					<tr>
						<td><label>是否变更审批结果：</label>
							<input class="yesOrNo required" type="radio" name="yesOrNo" value="1" <c:if test="${xhCreditAudit.creditResult == '1' }">checked</c:if> />结果变更
							<input class="yesOrNo required" type="radio" name="yesOrNo" value="0" <c:if test="${xhCreditAudit.creditResult == '0' }">checked</c:if> />维持原判
						</td>
					</tr>
				</table>
				
				
				
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
<script type="text/javascript">
	function ysubmit(val) {
		var yesOrNo = $('.yesOrNo:checked').val();
		$('#opt').val(yesOrNo);
	//	document.jkhtform.opt.value = val;

		return true;
	}
	
/*  	$(function(){
		var state = $("#state").val();
		if(state=="50.F" || state=="70.F"){
			
		}
	});  */
</script>
''