<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCarLoanContract/saveXhCarLoanContract" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCarLoanContract.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>合同编号：</label>
				<input name="contractNum" type="text" size="30" value="${xhCarLoanContract.contractNum}" class="required" maxlength="50" />
			</p>
			<p>
				<label>合同金额：</label>
				<input name="contractMoney" type="text" size="30" value="${xhCarLoanContract.contractMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>合同签订日期：</label>
				<input name="qdrq" type="text" size="30" value="${xhCarLoanContract.qdrq}" class="required" maxlength="20" />
			</p>
			<p>
				<label>合同日期：</label>
				<input name="contractDate" type="text" size="30" value="${xhCarLoanContract.contractDate}" class="required" maxlength="7" />
			</p>
			<p>
				<label>0：待签约 1：已签约上传   -1：取消：</label>
				<input name="state" type="text" size="30" value="${xhCarLoanContract.state}" class="required" maxlength="22" />
			</p>
			<p>
				<label>中间人ID：</label>
				<input name="middleManId" type="text" size="30" value="${xhCarLoanContract.middleManId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>利息率（移交：0.5%；GPS：0.475%）：</label>
				<input name="dkll" type="text" size="30" value="${xhCarLoanContract.dkll}" class="required" maxlength="22" />
			</p>
			<p>
				<label>总费率（移交：3.5%；GPS：5%  ，客户经理可录入）：</label>
				<input name="dkllComlpex" type="text" size="30" value="${xhCarLoanContract.dkllComlpex}" class="required" maxlength="22" />
			</p>
			<p>
				<label>利息（借款总额*利息率）：</label>
				<input name="interest" type="text" size="30" value="${xhCarLoanContract.interest}" class="required" maxlength="22" />
			</p>
			<p>
				<label>总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)）：</label>
				<input name="serveComlpexMoney" type="text" size="30" value="${xhCarLoanContract.serveComlpexMoney}" class="required" maxlength="22" />
			</p>
			<p>
				<label>咨询费（综合息费*59%）：</label>
				<input name="adviceFee" type="text" size="30" value="${xhCarLoanContract.adviceFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>审核费（综合息费*5%）：</label>
				<input name="auditFee" type="text" size="30" value="${xhCarLoanContract.auditFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>服务费（综合息费-咨询费-审核费）：</label>
				<input name="serviceFee" type="text" size="30" value="${xhCarLoanContract.serviceFee}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款合同ID：</label>
				<input name="xhJkhtId" type="text" size="30" value="${xhCarLoanContract.xhJkhtId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>还款日：</label>
				<input name="hkr" type="text" size="30" value="${xhCarLoanContract.hkr}" class="required" maxlength="2" />
			</p>
			<p>
				<label>展期期数：</label>
				<input name="noExtension" type="text" size="30" value="${xhCarLoanContract.noExtension}" class="required" maxlength="8" />
			</p>
			<p>
				<label>是否展期：</label>
				<input name="isExtension" type="text" size="30" value="${xhCarLoanContract.isExtension}" class="required" maxlength="2" />
			</p>
			<p>
				<label>备注：</label>
				<input name="remark" type="text" size="30" value="${xhCarLoanContract.remark}" class="required" maxlength="200" />
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
