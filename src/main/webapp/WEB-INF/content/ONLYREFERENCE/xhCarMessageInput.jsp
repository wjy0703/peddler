<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCarMessage/saveXhCarMessage" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCarMessage.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>评估金额：</label>
				<input name="assessMoney" type="text" size="30" value="${xhCarMessage.assessMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>建议借款额：</label>
				<input name="suggestMoney" type="text" size="30" value="${xhCarMessage.suggestMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>评估师姓名：</label>
				<input name="assessPerson" type="text" size="30" value="${xhCarMessage.assessPerson}" class="required" maxlength="30" />
			</p>
			<p>
				<label>违章及事故情况：</label>
				<input name="breakRules" type="text" size="30" value="${xhCarMessage.breakRules}" class="required" maxlength="512" />
			</p>
			<p>
				<label>车辆评估报告结论：</label>
				<input name="assessFinish" type="text" size="30" value="${xhCarMessage.assessFinish}" class="required" maxlength="512" />
			</p>
			<p>
				<label>外观监测：</label>
				<input name="visualInspection" type="text" size="30" value="${xhCarMessage.visualInspection}" class="required" maxlength="512" />
			</p>
			<p>
				<label>车年检情况(有无)：</label>
				<input name="inspectionFlag" type="text" size="30" value="${xhCarMessage.inspectionFlag}" class="required" maxlength="10" />
			</p>
			<p>
				<label>车年检情况：</label>
				<input name="inspection" type="text" size="30" value="${xhCarMessage.inspection}" class="required" maxlength="512" />
			</p>
			<p>
				<label>交强险(有无)：</label>
				<input name="trafficInsuranceFlag" type="text" size="30" value="${xhCarMessage.trafficInsuranceFlag}" class="required" maxlength="10" />
			</p>
			<p>
				<label>交强险：</label>
				<input name="trafficInsurance" type="text" size="30" value="${xhCarMessage.trafficInsurance}" class="required" maxlength="20" />
			</p>
			<p>
				<label>商业险(有无)：</label>
				<input name="businessInsuranceFlag" type="text" size="30" value="${xhCarMessage.businessInsuranceFlag}" class="required" maxlength="10" />
			</p>
			<p>
				<label>商业险：</label>
				<input name="businessInsurance" type="text" size="30" value="${xhCarMessage.businessInsurance}" class="required" maxlength="20" />
			</p>
			<p>
				<label>车架号：</label>
				<input name="chassisNumber" type="text" size="30" value="${xhCarMessage.chassisNumber}" class="required" maxlength="64" />
			</p>
			<p>
				<label>出厂日期：</label>
				<input name="madeTime" type="text" size="30" value="${xhCarMessage.madeTime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>车牌号码：</label>
				<input name="plate" type="text" size="30" value="${xhCarMessage.plate}" class="required" maxlength="20" />
			</p>
			<p>
				<label>登记日期：</label>
				<input name="registerTime" type="text" size="30" value="${xhCarMessage.registerTime}" class="required" maxlength="20" />
			</p>
			<p>
				<label>车辆厂牌型号：</label>
				<input name="lable" type="text" size="30" value="${xhCarMessage.lable}" class="required" maxlength="20" />
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
