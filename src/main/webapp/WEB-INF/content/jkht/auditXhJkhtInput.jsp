<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
	<h1>合同制作审批</h1>
	<div class="pageContent">
		<form method="post" id="jkhtform" name="jkhtform"
			action="${ctx}/xhJkht/saveAuditXhJkht"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhJkht.id}" /> <input
				type="hidden" name="xhJksq.id" value="${xhJkht.xhJksq.id}" /> <input
				type="hidden" id="opt" name="opt" value="1" />
			<!--  <div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/xhJkht/listJksq"><span>返回列表</span></a></li>
			</ul>		</div>	-->
			<div class="pageFormContent" layoutH="80">
				<p>
					<label>借款人姓名：</label> <input name="jkrxm" type="text" size="30"
						value="${xhJkht.xhJksq.jkrxm}" class="required" maxlength="22"
						readonly />
				</p>
				<p>
					<label>借款合同编号：</label> <input name="jkhtbm" type="text" size="30"
						value="${xhJkht.jkhtbm}" class="required" maxlength="20" readonly />
				</p>

				<div class="divider"></div>
				<table width="100%">
					<tr>

						<td><label>批借金额：</label> <input id="pdje" name="pdje"
							type="text" size="30" value="${xhJkht.pdje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>还款期数：</label> <input id="hkqs" name="hkqs"
							type="text" size="30" value="${xhJkht.hkqs}" class="required"
							maxlength="22" readonly /></td>
					</tr>
					<tr>
						<td><label>综合费率%：</label> <input id="yzhfl" name="yzhfl"
							type="text" size="30" value="${xhJkht.yzhfl}" class="required"
							maxlength="22" readonly /></td>

						<td><label>信访费：</label> <input id="xff" name="xff"
							type="text" size="30" value="${xhJkht.xff}" class="required"
							maxlength="22" readonly /></td>
						<td><label>加急费：</label> <input id="urgentCreditFee" name="urgentCreditFee"
							type="text" size="30" value="<c:if test="${xhJkht.urgentCreditFee == null || xhJkht.urgentCreditFee == ''}">0</c:if><c:if test="${xhJkht.urgentCreditFee != null && xhJkht.urgentCreditFee != ''}">${xhJkht.urgentCreditFee}</c:if>" 
							class="required"
							maxlength="22" readonly /></td>
					</tr>
					<c:if test="${xhJkht.xhJksq.togetherPerson eq '是'}">
						<tr>
						<td><label>共借人姓名</label><input
							value="<sen:showTogether yesOrNo='${xhJkht.xhJksq.togetherPerson}' lendId='${xhJkht.xhJksq.id}'/>" size="30"  readonly 	class="required"/></td>
						<td><label>共借人身份证号码：</label><input
							value="<sen:showTogetherIdentification lendId='${xhJkht.xhJksq.id}' yesOrNo='${xhJkht.xhJksq.togetherPerson}'/>" size="30"  readonly 	class="required"/></td>
						<td></td>
					</tr></c:if>
				</table>
				<div class="divider"></div>
				<table width="100%">
					<td style="color: red"><label>借款利率%：</label> <input id="dkll"
						name="dkll" type="text" size="30" value="${xhJkht.dkll}"
						class="required" maxlength="22"  readonly  />(注：此项由合同制作人员输入)</td>
				</table>
				<div class="divider"></div>
				<table width="100%">
					<tr>

						<td><label>实放金额：</label> <input id="fkje" name="fkje"
							type="text" size="30" value="${xhJkht.fkje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>剩余利率%：</label> <input id="syll" name="syll"
							type="text" size="30" value="${xhJkht.syll}" class="required"
							maxlength="22" readonly /></td>
						<td><label>剩余金额：</label> <input id="syje" name="syje"
							type="text" size="30" value="${xhJkht.syje}" class="required"
							maxlength="22" readonly /></td>

					</tr>
					<tr>
						<td><label>信用审核费：</label> <input id="xyshf" name="xyshf"
							type="text" size="30" value="${xhJkht.xyshf}" class="required"
							maxlength="22" readonly /></td>
						<td><label>服务费：</label> <input id="fwf" name="fwf"
							type="text" size="30" value="${xhJkht.fwf}" class="required"
							maxlength="22" readonly /></td>
						<td><label>咨询费：</label> <input id="zxf" name="zxf"
							type="text" size="30" value="${xhJkht.zxf}" class="required"
							maxlength="22" readonly /></td>
					</tr>

					<tr>
						<td><label>合同金额：</label> <input id="htje" name="htje"
							type="text" size="30" value="${xhJkht.htje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>月还本金金额：</label> <input id="ybjje" name="ybjje"
							type="text" size="30" value="${xhJkht.ybjje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>月利息金额：</label> <input id="ylxje" name="ylxje"
							type="text" size="30" value="${xhJkht.ylxje}" class="required"
							maxlength="22" readonly /></td>
					</tr>
					<tr>
						<td><label>实际月还款金额：</label> <input id="yhkje" name="yhkje"
							type="text" size="30" value="${xhJkht.yhkje}" class="required"
							maxlength="22" readonly /></td>

						<td></td>
						<td></td>

					</tr>

					<tr>
						<td><label>账户管理费：</label> <input id="zhglf" name="zhglf"
							type="text" size="30" value="${xhJkht.zhglf}" class="required"
							maxlength="22"  readonly /></td>


						<td><label>起始还款日期：</label> <input name="qshkrq" type="text"
							size="25" value="${xhJkht.qshkrq}" class="required date"
							maxlength="20"  readonly /> <a class="inputDateButton" href="#">选择</a></td>
						<!--  
			<td>
				<label>状态:</label>
				<input name="state" type="text" size="30" value="${xhJkht.state}" class="required" maxlength="2" />
			</td>
			-->

						<td><label>合同签订日期：</label> <input name="qdrq" type="text"
							size="25" value="${xhJkht.qdrq}" class="required date"
							maxlength="20"  readonly /> <a class="inputDateButton" href="#">选择</a></td>

					</tr>

					<tr>
						<td><label>出借人：</label> <input type="hidden"
							class="textInput" name="middleMan.id" size="10" value="${xhJkht.middleMan.id}"
							class="required" maxlength="22"  /> <input type="text"
							class="textInput" name="middleMan.middleManName" size="10"
							value="${xhJkht.middleMan.middleManName}" class="required" maxlength="22"  readonly /> 

						</td>
						<td>
						<label>还款付息方式：</label><sen:select clazz="combox" name="rePayType" coding="returnIntest" value="${xhJkht.rePayType}"/>
						</td>
						<td></td>
					</tr>


				</table>

				<dl class="nowrap">
					<dt>审核意见：</dt>
					<dd>
						<textarea name="auditIdea" style="width: 93%; height: 80">${xhJkht.auditIdea}</textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>审核结果：</dt>
					<dd>
						<input type="radio" name="state" value="1" checked="checked" />通过 <input
							type="radio" name="state" value="0" />不通过
					</dd>
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<!--  
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="ysubmit('0')">暂存</button>
							</div>
						</div></li>
						-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" >提交</button>
							</div>
						</div></li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
					<bjdv:validateContent type="1" funcId="借款合同-作废">
					<li>
						<div class="buttonActive">
						<div class="buttonContent">
							<a title="借款合同-作废" 
								href="${ctx }/xhJkht/saveInvalidXhJkht?jkhtid=${xhJkht.id}&jksqid=${xhJkht.xhJksq.id}">
								<button type="submit">作废</button>
							</a>
						</div>
					</div>
					</li>
					</bjdv:validateContent>
				</ul>
			</div>

		</form>
	</div>
</div>
<script type="text/javascript">
	function ysubmit(val) {
		document.jkhtform.opt.value = val;

		return true;
	}
</script>
