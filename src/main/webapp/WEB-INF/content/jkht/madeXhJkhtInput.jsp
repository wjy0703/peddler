<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
	<h1>合同制作</h1>
	<div class="pageContent">
		<form method="post" id="jkhtform" name="jkhtform"
			action="${ctx}/xhJkht/saveMadeXhJkht"
			class="pageForm required-validate"
			onsubmit="return jkhtFormSubmit(this);">
			<input type="hidden" name="id" value="${xhJkht.id}" /> <input
				type="hidden" name="xhJksq.id" value="${xhJkht.xhJksq.id}" /> <input
				type="hidden" id="opt" name="opt" />
			
			<!--  <div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/xhJkht/listJksq"><span>返回列表</span></a></li>
			</ul>		</div>	-->
			<div class="pageFormContent" layoutH="80">
				<p>
					<label>借款人姓名：</label> <input name="jkrxm" type="text" size="30"
						value="${xhJkht.xhJksq.jkrxm}"  maxlength="22"
						disabled="disabled" />
				</p>
				<p>
					<label>借款合同编号：</label> <input name="jkhtbm" type="text" size="30" class="required"
						value="${xhJkht.jkhtbm}"  maxlength="20"/>
				</p>
				<p>
					<label>起始还款日期：</label> <input name="qshkrq"  id="qshkrq" 
type="text"
							size="25" value="${xhJkht.qshkrq}" class="required date"
							maxlength="20" /> <a class="inputDateButton" href="#">选择</a>
				</p>
				<p>
					<label>出借人*：</label> <input type="hidden"
							class="textInput" name="middleMan.id" size="10" value="${xhJkht.middleMan.id}"
							class="required" maxlength="22"  /> <input type="text"
							class="textInput" name="middleMan.middleManName" size="10"
							value="${xhJkht.middleMan.middleManName}" class="required" 
								maxlength="22" /> <a class="btnLook"
							href="${ctx}/jygl/listMiddleMan" lookupGroup="middleMan">请选择放款
								账户信息</a>
				</p>
				<p><label>还款付息方式：</label> 
						<select   class="combox required" id="rePayType" name="rePayType" >
							<option value="" <c:if test="${xhJkht.rePayType==''}">selected</c:if>>请选择</option>
							<c:forEach items="${rePayTypeList}" var="per">
								<option value="${per.value }" <c:if test="${xhJkht.rePayType==per.value}">selected</c:if>>${per.name }</option>
							</c:forEach>
						</select>
						</p>
				<div class="divider"></div>
				<table>
					<tr>

						<td><label>批借金额：</label> <input id="pdje" name="pdje"
							type="text" size="30" value="${xhJkht.pdje}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>还款期数：</label> <input id="hkqs" name="hkqs"
							type="text" size="30" value="${xhJkht.hkqs}" 
							maxlength="22" disabled="disabled" /></td>
					</tr>
					<tr>
						<td><label>综合费率%：</label> <input id="yzhfl" name="yzhfl"
							type="text" size="30" value="${xhJkht.yzhfl}" 
							maxlength="22" disabled="disabled" /></td>

						<td><label>信访费：</label> <input id="xff" name="xff"
							type="text" size="30" value="${xhJkht.xff}" 
							maxlength="22" disabled="disabled" /></td>
							<td><label>加急费：</label> <input id="urgentCreditFee" name="urgentCreditFee"
							type="text" size="30" value="<c:if test="${xhJkht.urgentCreditFee == null || xhJkht.urgentCreditFee == ''}">0</c:if><c:if test="${xhJkht.urgentCreditFee != null && xhJkht.urgentCreditFee != ''}">${xhJkht.urgentCreditFee}</c:if>" 
							
							maxlength="22" disabled="disabled" /></td>
					</tr>
					<c:if test="${xhJkht.xhJksq.togetherPerson eq '是'}">
						<tr>
						<td><label>共借人姓名</label><input
							value="<sen:showTogether lendId='${xhJkht.xhJksq.id}' yesOrNo='${xhJkht.xhJksq.togetherPerson}'/>" size="30"  disabled="disabled" 	/></td>
						<td><label>共借人身份证号码：</label><input
							value="<sen:showTogetherIdentification lendId='${xhJkht.xhJksq.id}' yesOrNo='${xhJkht.xhJksq.togetherPerson}'/>" size="30"  disabled="disabled" 	/></td>
						<td></td>
					</tr></c:if>
				</table>
				<div class="divider"></div>
				<table>
					<td style="color: red"><label>借款利率%：</label> <input id="dkll"
						name="dkll" type="text" size="30" value="${xhJkht.dkll}"
						 maxlength="22"  disabled="disabled" />(注：此项由合同制作人员输入)</td>
				</table>
				<div class="divider"></div>
				<table>
					<tr>

						<td><label>实放金额：</label> <input id="fkje" name="fkje"
							type="text" size="30" value="${xhJkht.fkje}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>剩余利率%：</label> <input id="syll" name="syll"
							type="text" size="30" value="${xhJkht.syll}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>剩余金额：</label> <input id="syje" name="syje"
							type="text" size="30" value="${xhJkht.syje}" 
							maxlength="22" disabled="disabled" /></td>

					</tr>
					<tr>
						<td><label>信用审核费：</label> <input id="xyshf" name="xyshf"
							type="text" size="30" value="${xhJkht.xyshf}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>服务费：</label> <input id="fwf" name="fwf"
							type="text" size="30" value="${xhJkht.fwf}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>咨询费：</label> <input id="zxf" name="zxf"
							type="text" size="30" value="${xhJkht.zxf}" 
							maxlength="22" disabled="disabled" /></td>
					</tr>

					<tr>
						<td><label>合同金额：</label> <input id="htje" name="htje"
							type="text" size="30" value="${xhJkht.htje}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>月还本金金额：</label> <input id="ybjje" name="ybjje"
							type="text" size="30" value="${xhJkht.ybjje}" 
							maxlength="22" disabled="disabled" /></td>
						<td><label>月利息金额：</label> <input id="ylxje" name="ylxje"
							type="text" size="30" value="${xhJkht.ylxje}" 
							maxlength="22" disabled="disabled" /></td>
					</tr>
					<tr>
						<td><label>实际月还款金额：</label> <input id="yhkje" name="yhkje"
							type="text" size="30" value="${xhJkht.yhkje}" 
							maxlength="22" disabled="disabled" /></td>

						<td></td>
						<td></td>

					</tr>

					<tr>
						<td><label>账户管理费：</label> <input id="zhglf" name="zhglf"
							type="text" size="30" value="${xhJkht.zhglf}" 
							maxlength="22"  disabled="disabled" /></td>
						<td><label>合同签订日期：</label> <input name="qdrq" type="text"
						size="25" value="${xhJkht.qdrq}" class="date" disabled="disabled"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>


					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="ysubmit('0')">保存</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="ysubmit('1')">提交</button>
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
	function ysubmit(val) {
		document.jkhtform.opt.value = val;

		return true;
	}
		function jkhtFormSubmit(obj){
		
		var $form=$(obj);
		if(!$form.valid()){
			return false;
		}
		
		var qshkrq = document.jkhtform.qshkrq.value;
		var aQshkrq=qshkrq.split("-");
		if (aQshkrq[1]=="02"){
			if (getLastDay(aQshkrq[0],aQshkrq[1])!=aQshkrq[2]){
				alertMsg.warn("起始还款日期为2月份时，只能选择2月15日或最后一天");
				return false;
			}
		}else{
			if (aQshkrq[2]!="15" && aQshkrq[2]!="30"){
				alertMsg.warn("起始还款日期只能选择15日或30日");
				return false;
			}
		}
		//return false;
		
		return validateCallback(obj, navTabAjaxDone);
	}
</script>
