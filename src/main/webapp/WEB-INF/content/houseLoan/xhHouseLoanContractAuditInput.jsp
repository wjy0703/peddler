<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post"
			action="${ctx}/xhHouseLoanContract/saveXhHouseLoanContract"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id"
				value="${xhHouseLoanContract.id}" /> <input
				type="hidden" name="apply_id"
				value="${xhHouseLoanContract.xhHouseLoanApply.id}" /> <input
				type="hidden" name="auditInfo_id" value="${xhHouseLoanAuditInfo.id}" />
				<input type="hidden" name="jkht_id" value="${xhHouseLoanContract.jkht.id}" />

			<div class="pageFormContent" layoutH="56">

				<h1>个人信息</h1>
				<p>
					<label>客户姓名：</label> <input name="loanerName" type="text" size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.loanerName}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>身份证号：</label> <input name="loanerIdNumber" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.loanerIdNumber}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>现住址：</label> <input name="loanSrcAddress" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.loanSrcAddress}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>手机号：</label> <input name="telephone" type="text" size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.telephone}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>开户行：</label> <input name="bankOpen" type="text" size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.bankOpen}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>银行卡号：</label> <input name="bankNum" type="text" size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.bankNum}"
						class="" maxlength="22" readonly />
				</p>
				<div class="divider"></div>


				<h1>抵押权人信息</h1>
				<p>
					<label>抵押权人姓名：</label> <input name="dyqPresonName" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.dyqPresonName}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>身份证号：</label> <input name="dyqPresonCardId" type="text"
						size="30"                                         
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.dyqPresonCardId}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>现住址：</label> <input name="dyqPresonAddress" type="text" size="30" value="${xhHouseLoanContract.xhHouseLoanAuditInfo.dyqPresonAddress}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>手机号：</label> <input name="dyqPresonPhone" type="text" size="30" value="${xhHouseLoanContract.xhHouseLoanAuditInfo.dyqPresonPhone}"
						class="" maxlength="22" readonly />
				</p>


				<div class="divider"></div>
				<h1>房屋信息</h1>
				<p>
					<label>产权证号：</label> <input name="houseRightNum" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.houseRightNum}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>抵押房地址：</label> <input name="houseAddress" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.houseAddress}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>建筑面积：</label> <input name="houseArea" type="text" size="30"
						value="${xhHouseLoanContract.xhHouseLoanApply.houseArea}"
						class="" maxlength="22" readonly />
				</p>

				<p>
					<label>房屋性质：</label> <select name="houseType" class="combox"><option
							value="1">1:商品房</option>
						<option value="2">2:经济适用住房</option>
					</select>

				</p>
				<p>
					<label>房屋评估价值(元)：</label> <input name="houseEvaluateValue"
						type="text" size="30"
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.houseEvaluateValue}"
						class="" maxlength="22" />
				</p>

				<div class="divider"></div>
				<h1>批借信息</h1>
				<div class="divider"></div>

				<p>
					<label>批借(合同)金额(元)：</label> <input name="contractMoney" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.contractMoney}"
						class="" maxlength="22" />
				</p>

				<p>
					<label>借款期数(月)：</label> <input name="loanMonth" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.loanMonth}"
						class="required" maxlength="22" />
				</p>
				<div class="divider"></div>
				<p>
					<label>信访费(元)：</label> <input name="creditVisitFee" type="text"
						size="30" value="0.00" class="required" maxlength="22" />
				</p>
				<div class="divider"></div>
				<p>
					<label>综合费率(%)：</label> <input name="allFeeRate" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.allFeeRate}"
						class="required" maxlength="22" />
				</p>

				<p>
					<label>月利率(%)：</label> <input name="monthRate" type="text"
						size="30" value="1.0" class="required" maxlength="22" />
				</p>
				<p>
					<label>服务费率(%)：</label> <input name="serviceFeeRate" type="text"
						size="30"
						value="${xhHouseLoanContract.xhHouseLoanAuditInfo.serviceFeeRate}"
						class="required" maxlength="22" />
				</p>
				<div class="divider"></div>

				<p>
					<label>签约日期：</label> <input name="contractDate" type="text"
						size="30"
						value='<fmt:formatDate value="${xhHouseLoanContract.xhHouseLoanAuditInfo.signContractDate}" pattern="yyyy-MM-dd" />'
						class="date" maxlength="30" />
						
						
							
				</p>
				<div class="divider"></div>
				<p>
					<label>收费方式：</label> <select name="loanMonth" class="combox"><option
							value="1">1:一次性</option>
						<option value="2">2:月月收</option>
					</select>
					<!-- <input name="loanMonth" type="text" size="30"
						value="${xhHouseLoanAuditInfo.chargeType}" class="required"
						maxlength="22" /> -->
				</p>

				<div class="divider"></div>

				<p>
					<label>合同编号：</label> <input name="contractNum" type="text"
						size="30" value="${xhHouseLoanContract.contractNum}"
						class="required" maxlength="50" />
				</p>




				<p>
					<label>出借人：</label> <input type="hidden" class="textInput"
						name="middleMan.id" size="10"
						value="${xhHouseLoanContract.middleMan.id}" class="required"
						maxlength="22" /> <input type="text" class="textInput"
						name="middleMan.middleManName" size="10"
						value="${xhHouseLoanContract.middleMan.middleManName}"
						class="required" maxlength="22" /> <a class="btnLook"
						href="${ctx}/jygl/listMiddleMan" lookupGroup="middleMan">请选择出借人</a>
				</p>
		
				
				<div class="divider"></div>
				<h1>审核意见及结果</h1>
				<div class="divider"></div>



				<dl class="nowrap">
					<dt>审核意见：</dt>
					<dd>
						<textarea name="contractAduitResult" style="width: 100%;" >${xhHouseLoanContract.contractAduitResult}</textarea>
					</dd>
				</dl>
				<p>
					<label>审核结果：</label> <input name="contractAduitState" type="radio"
						size="30" value="1" class="" maxlength="20" <c:if test="${xhHouseLoanContract.contractAduitState eq 1}">checked </c:if>/>审核通过 <input
						name="contractAduitState" type="radio" size="30" value="0" class=""
						maxlength="20" <c:if test="${xhHouseLoanContract.contractAduitState eq 0}">checked </c:if>/>审核拒绝
				</p>
			</div>
			<div class="formBar">
				<ul>
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








