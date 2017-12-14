<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCarLoanApplyComplement/saveXhCarLoanApplyComplement" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCarLoanApplyComplement.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>审核户籍地址：</label>
				<input name="auditHjadress" type="text" size="30" value="${xhCarLoanApplyComplement.auditHjadress}" class="required" maxlength="512" />
			</p>
			<p>
				<label>身份真伪验证：</label>
				<input name="auditZjhm" type="text" size="30" value="${xhCarLoanApplyComplement.auditZjhm}" class="required" maxlength="64" />
			</p>
			<p>
				<label>审核暂住证：</label>
				<input name="auditTemporary" type="text" size="30" value="${xhCarLoanApplyComplement.auditTemporary}" class="required" maxlength="64" />
			</p>
			<p>
				<label>审核客户人法：</label>
				<input name="auditPersonlaw" type="text" size="30" value="${xhCarLoanApplyComplement.auditPersonlaw}" class="required" maxlength="512" />
			</p>
			<p>
				<label>审核现住址：</label>
				<input name="auditHomeadress" type="text" size="30" value="${xhCarLoanApplyComplement.auditHomeadress}" class="required" maxlength="512" />
			</p>
			<p>
				<label>114电话查询情况：</label>
				<input name="audit114" type="text" size="30" value="${xhCarLoanApplyComplement.audit114}" class="required" maxlength="512" />
			</p>
			<p>
				<label>客户工作审核情况：</label>
				<input name="auditWork" type="text" size="30" value="${xhCarLoanApplyComplement.auditWork}" class="required" maxlength="512" />
			</p>
			<p>
				<label>征信报告显示情况：</label>
				<input name="auditCredit" type="text" size="30" value="${xhCarLoanApplyComplement.auditCredit}" class="required" maxlength="512" />
			</p>
			<p>
				<label>评估金额：</label>
				<input name="assessMoney" type="text" size="30" value="${xhCarLoanApplyComplement.assessMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>建议借款额：</label>
				<input name="suggestMoney" type="text" size="30" value="${xhCarLoanApplyComplement.suggestMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>评估师姓名：</label>
				<input name="assessPerson" type="text" size="30" value="${xhCarLoanApplyComplement.assessPerson}" class="required" maxlength="30" />
			</p>
			<p>
				<label>违章及事故情况：</label>
				<input name="breakRules" type="text" size="30" value="${xhCarLoanApplyComplement.breakRules}" class="required" maxlength="512" />
			</p>
			<p>
				<label>车辆评估报告结论：</label>
				<input name="assessFinish" type="text" size="30" value="${xhCarLoanApplyComplement.assessFinish}" class="required" maxlength="512" />
			</p>
			<p>
				<label>外观监测：</label>
				<input name="visualInspection" type="text" size="30" value="${xhCarLoanApplyComplement.visualInspection}" class="required" maxlength="512" />
			</p>
			<p>
				<label>车年检情况(有无)：</label>
				<input name="inspectionFlag" type="text" size="30" value="${xhCarLoanApplyComplement.inspectionFlag}" class="required" maxlength="10" />
			</p>
			<p>
				<label>车年检情况：</label>
				<input name="inspection" type="text" size="30" value="${xhCarLoanApplyComplement.inspection}" class="required" maxlength="512" />
			</p>
			<p>
				<label>交强险(有无)：</label>
				<input name="trafficInsuranceFlag" type="text" size="30" value="${xhCarLoanApplyComplement.trafficInsuranceFlag}" class="required" maxlength="10" />
			</p>
			<p>
				<label>交强险：</label>
				<input name="trafficInsurance" type="text" size="30" value="${xhCarLoanApplyComplement.trafficInsurance}" class="required" maxlength="20" />
			</p>
			<p>
				<label>商业险(有无)：</label>
				<input name="businessInsuranceFlag" type="text" size="30" value="${xhCarLoanApplyComplement.businessInsuranceFlag}" class="required" maxlength="10" />
			</p>
			<p>
				<label>商业险：</label>
				<input name="businessInsurance" type="text" size="30" value="${xhCarLoanApplyComplement.businessInsurance}" class="required" maxlength="20" />
			</p>
			<p>
				<label>车架号：</label>
				<input name="chassisNumber" type="text" size="30" value="${xhCarLoanApplyComplement.chassisNumber}" class="required" maxlength="64" />
			</p>
			<p>
				<label>出厂日期：</label>
				<input name="madeTime" type="text" size="30" value="${xhCarLoanApplyComplement.madeTime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>车牌号码：</label>
				<input name="plate" type="text" size="30" value="${xhCarLoanApplyComplement.plate}" class="required" maxlength="20" />
			</p>
			<p>
				<label>登记日期：</label>
				<input name="registerTime" type="text" size="30" value="${xhCarLoanApplyComplement.registerTime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>车辆厂牌型号：</label>
				<input name="lable" type="text" size="30" value="${xhCarLoanApplyComplement.lable}" class="required" maxlength="20" />
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
