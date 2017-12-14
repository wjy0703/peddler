<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post"
			action="${ctx}/xhHouseLoanAudit/saveXhHouseLoanAuditInfo"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhHouseLoanAuditInfo.id}" />
			<input type="hidden" name="apply_id" value="${xhHouseLoanApply.id}" />
			<input type="hidden" name="audit_id" value="${xhHouseLoanAudit.id}" />
			<div class="pageFormContent" layoutH="56">
				<h1>个人信息</h1>
				<p>
					<label>客户姓名：</label> <input name="loanerName" type="text" size="30"
						value="${xhHouseLoanApply.loanerName}" class=""
						maxlength="22" readonly />
				</p>
				<p>
					<label>身份证号：</label> <input name="loanerIdNumber" type="text"
						size="30" value="${xhHouseLoanApply.loanerIdNumber}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>现住址：</label> <input name="loanSrcAddress" type="text"
						size="30" value="${xhHouseLoanApply.loanSrcAddress}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>手机号：</label> <input name="telephone" type="text" size="30"
						value="${xhHouseLoanApply.telephone}" class=""
						maxlength="22" readonly />
				</p>
				<p>
					<label>开户行：</label> <input name="bankOpen" type="text" size="30"
						value="${xhHouseLoanApply.bankOpen}" class=""
						maxlength="22" readonly />
				</p>
				<p>
					<label>银行卡号：</label> <input name="bankNum" type="text" size="30"
						value="${xhHouseLoanApply.bankNum}" class=""
						maxlength="22" readonly />
				</p>
				

				<div class="divider"></div>
				<h1>房屋信息</h1>
				<p>
					<label>产权证号：</label> <input name="houseRightNum" type="text"
						size="30" value="${xhHouseLoanApply.houseRightNum}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>抵押房地址：</label> <input name="houseAddress" type="text"
						size="30" value="${xhHouseLoanApply.houseAddress}"
						class="" maxlength="22" readonly />
				</p>
				<p>
					<label>建筑面积：</label> <input name="houseArea" type="text" size="30"
						value="${xhHouseLoanApply.houseArea}" class=""
						maxlength="22" readonly />
				</p>

				<p>
					<label>房屋性质：</label> <select name="houseType" class="combox"><option value="1">1:商品房</option>
						<option value="2">2:经济适用住房</option>
					</select>

				</p>
				<p>
					<label>房屋评估价值(元)：</label> <input name="houseEvaluateValue"
						type="text" size="30"
						value="${xhHouseLoanAuditInfo.houseEvaluateValue}"
						class="required" maxlength="22" />
				</p>
				
				<div class="divider"></div>


				<h1>抵押权人信息</h1>
				<p>
					<label>抵押权人姓名：</label> <input name="dyqPresonName" type="text"
						size="30" value="${xhHouseLoanAuditInfo.dyqPresonName}"
						class="" maxlength="22"  />
				</p>
				<p>
					<label>身份证号：</label> <input name=dyqPresonCardId type="text"
						size="30" value="${xhHouseLoanAuditInfo.dyqPresonCardId}"
						class="" maxlength="22"  />
				</p>
				<p>
					<label>现住址：</label> <input name="dyqPresonAddress" type="text"
						size="30" value="${xhHouseLoanAuditInfo.dyqPresonAddress}"
						class="" maxlength="22"  />
				</p>
				<p>
					<label>手机号：</label> <input name="dyqPresonPhone" type="text"
						size="30" value="${xhHouseLoanAuditInfo.dyqPresonPhone}"
						class="" maxlength="22"  />
				</p>
				

				<div class="divider"></div>
				<h1>批借信息</h1>
				<div class="divider"></div>

				<p>
					<label>批借金额(元)：</label> <input name="contractMoney"
						type="text" size="30"
						value="${xhHouseLoanAuditInfo.contractMoney}"
						class="required" maxlength="22" />
				</p>

				<p>
					<label>借款期数(月)：</label> <input name="loanMonth" type="text"
						size="30" value="${xhHouseLoanAuditInfo.loanMonth}"
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
						size="30" value="${xhHouseLoanAuditInfo.allFeeRate}"
						class="required" maxlength="22" />
				</p>

				<p>
					<label>月利率(%)：</label> <input name="monthRate" type="text"
						size="30" value="1.0" class="required" maxlength="22" />
				</p>
				<p>
					<label>服务费率(%)：</label> <input name="serviceFeeRate" type="text"
						size="30" value="${xhHouseLoanAuditInfo.serviceFeeRate}"
						class="required" maxlength="22" />
				</p>
				<div class="divider"></div>

				<p>
					<label>签约日期：</label> <input name="signContractDate" type="text"
						size="30" value="${xhHouseLoanAuditInfo.signContractDate}"
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
