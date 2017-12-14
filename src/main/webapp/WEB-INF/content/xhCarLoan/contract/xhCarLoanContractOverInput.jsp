<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhCarLoanContract/saveXhCarLoanContract" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCarLoanContract.id}"/>
		<%-- <input type="hidden" name="id" value="${xhCarLoanContract.xhCarLoanApply.id}"/> --%>
		<input type="hidden" name="state" id="state" value="0" />
		<div class="pageFormContent" layoutH="56">
		<div class="panel" align="center">
					<h1>个人信息</h1>
					<div >
						<table class="list" width="100%">
							<tbody>
							<tr height="30px">
								<td><label>姓名：</label>
								</td>
								<td >
									<input type="text" id="userName" disabled="disabled" name="userName"  value="${xhCarLoanContract.xhCarLoanApply.xhCarLoanUser.userName }" class="">
								</td>
								<td><label>性别：</label>
								</td>
								<td><sen:selectRadio name="genders" coding="genders" value="${xhCarLoanContract.xhCarLoanApply.xhCarLoanUser.genders }" clazz=""/></td>
							</tr>
								<tr height="30px">
									<td ><label>户籍地址:</label> </td>
									<td>
										<input type="text" id="hjadress" disabled="disabled" name="hjadress" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarLoanUser.hjadress }" class="">
									</td>
									<td ><label>暂住证:</label> </td>
									<td>
										<input type="text" id="temporaryCrede" disabled="disabled" name="temporaryCrede" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarLoanUser.temporaryCrede }" class="">
									</td>
								</tr>
								<tr height="30px">
									<td ><label>学历:</label> </td>
									<td>
										<%-- <input type="text" id="educationType" disabled="disabled" name="educationType" size="30" value="${user.educationType }" class=""> --%>
										<sen:selectRadio name="educationType"   value="${xhCarLoanContract.xhCarLoanApply.xhCarLoanUser.educationType}" coding="studyLevel" split="&nbsp;&nbsp;&nbsp;&nbsp;" />
									</td>
									<td ><label>信用卡额度:</label> </td>
									<td>
										<input type="text" id="creditLimit" disabled="disabled" name="creditLimit" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarLoanUser.creditLimit }" class="">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="panel" align="center">
					<h1>车况信息</h1>
				  	 <div >
						<table class="list" width="100%">
							<tbody>
								<tr height="30px">
									<td><label>评估金额:</label> </td>
									<td>
										<input name="xhCarMessage.assessMoney"  disabled="disabled" type="text" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarMessage.assessMoney}" maxlength="22" />
									</td>
									<td><label>建议借款额:</label> </td>
									<td>
										<input name="xhCarMessage.suggestMoney"  disabled="disabled" type="text" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarMessage.suggestMoney}" maxlength="22" />
									</td>
								</tr>	
								<tr height="30px">
									<td><label>车牌号码:</label> </td>
									<td>
										<input name="xhCarMessage.plate" type="text"  disabled="disabled" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarMessage.plate}" maxlength="22" />
								
									</td>
									<td><label>车辆厂牌型号:</label> </td>
									<td>
										<input name="xhCarMessage.lable" type="text"  disabled="disabled" size="30" value="${xhCarLoanContract.xhCarLoanApply.xhCarMessage.lable}" maxlength="22" />
									</td>
								</tr>	
							</tbody>
						</table>
					</div>
					
				</div>
			
			<div class="panel" align="center">
					<h1>借款信息</h1>
						 <div >
						<table class="list" width="100%">
							<tbody>
								<tr height="30px">
									<td><label>申请金额:</label> 
										<input name="jkLoanQuota" id="jkLoanQuota" type="hidden" size="30" value="${xhCarLoanContract.xhCarLoanApply.jkLoanQuota}" />
										<input name=""  disabled="disabled" type="text" size="30" value="${xhCarLoanContract.xhCarLoanApply.jkLoanQuota}" />元
									</td>
									<td><label>申请借款期限:</label> 
										<sen:selectRadio name="jkCycle" coding="carCycle" value="${xhCarLoanContract.xhCarLoanApply.jkCycle }"/>
									</td>
								</tr>	
								<tr height="30px">
									<td><label>借款类型:</label> 
										<input name="jkType" id="jkType" type="hidden" size="30" value="${xhCarLoanContract.xhCarLoanApply.jkType}" />
										<sen:selectRadio name=""  coding="carType" value="${xhCarLoanContract.xhCarLoanApply.jkType }"/>
									</td>
									<td><label>是否展期:</label> 
										<input name="" id="" disabled="disabled" type="text" size="30" value="<sen:vtoName coding="isExtension" value="${xhCarLoanContract.xhCarLoanApply.isExtension }" />" />
									</td>
								</tr>
							</tbody>
						</table>
						</div>
			</div>
			<input type="hidden"
					 name="middleMan.id" size="10" value="${xhCarLoanContract.middleMan.id}"
					 maxlength="22" /> 
			<div class="panel" align="center">
				<h1>合同信息</h1>
				<div >
				<table class="list" width="100%">
				<tbody>
				<tr height="30px">
				<td>
					<label>合同编号：</label>
					<input name="contractNum" type="text" size="30" value="${xhCarLoanContract.contractNum}"  maxlength="50" readonly="readonly"/>
				</td>
				<td>
					<label>中间人：</label>
					<input type="text" name="middleMan.middleManName" size="10"
					value="${xhCarLoanContract.middleMan.middleManName}"  class="required textInput"
						maxlength="22" /> <a class="btnLook"
					href="${ctx}/jygl/listMiddleMan" lookupGroup="middleMan">请选择放款
						账户信息</a>
				</td>
				</tr>	
				<tr height="30px">
				<td>
					<label>合同金额：</label>
					<input name="contractMoney" id="contractMoney" type="text" size="30" value="${xhCarLoanContract.contractMoney}"  maxlength="22" readonly="readonly"/>
				</td>
				<td>
					<label>总费率：</label>
					<input name="dkllComlpex" id="dkllComlpex" type="text" size="30" value="${xhCarLoanContract.dkllComlpex}"  maxlength="22" readonly="readonly" onblur="setMoney();"/>%
				</td>
				</tr>	
				<tr height="30px">
				<td>
					<label>违约罚息率：</label>
					<input name="breakInterestDkll" id="breakInterestDkll" type="text" size="30" value="${xhCarLoanContract.breakInterestDkll}"  maxlength="22" onblur="setMoney();"  readonly="readonly"/>%
				</td>
				<td>
					<label>违约日利息：</label>
					<input name="breakInterest" id="breakInterest" type="text" size="30" value="${xhCarLoanContract.breakInterest}"  maxlength="22" readonly="readonly"/>
				</td>
				</tr>	
				<tr height="30px">
				<td>
					<label>违约金利率：</label>
					<input name="breakMoneyDkll" id="breakMoneyDkll" type="text" size="30" value="${xhCarLoanContract.breakMoneyDkll}"  maxlength="22" onblur="setMoney();"  readonly="readonly"/>%
				</td>
				<td>
					<label>违约金：</label>
					<input name="breakMoney" id="breakMoney" type="text" size="30" value="${xhCarLoanContract.breakMoney}"  maxlength="22" readonly="readonly"/>
				</td>
				</tr>	
				<tr height="30px">
				<td>
					<label>利息率：</label>
					<input name="dkll" id="dkll" type="text" size="30" value="${xhCarLoanContract.dkll}"  maxlength="22" onblur="setMoney();"  readonly="readonly"/>%
				</td>
				<td>
					<label>利息：</label>
					<input name="interest" id="interest" type="text" size="30" value="${xhCarLoanContract.interest}"  maxlength="22" readonly="readonly"/>
				</td>
				</tr>	
				<tr height="30px">
				<td>
					<label>综合费用：</label>
					<input name="comlpexMoney" id="comlpexMoney" type="text" size="30" value="${xhCarLoanContract.comlpexMoney}"  maxlength="22" readonly="readonly"/>
				</td>
				<td>
					<label>咨询费：</label>
					<input name="adviceFee" id="adviceFee" type="text" size="30" value="${xhCarLoanContract.adviceFee}"  maxlength="22" readonly="readonly"/>
				</td>
				</tr>	
				<tr height="30px">
				<td>
					<label>审核费：</label>
					<input name="auditFee" id="auditFee" type="text" size="30" value="${xhCarLoanContract.auditFee}"  maxlength="22" readonly="readonly"/>
				</td>
				<td>
					<label>服务费：</label>
					<input name="serviceFee" id="serviceFee" type="text" size="30" value="${xhCarLoanContract.serviceFee}"  maxlength="22" readonly="readonly"/>
				</td>
				</tr>	
				</tbody>
			</table>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
			<!-- 	<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(0);">暂存</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(8);">提交</button>
							</div>
						</div></li> -->
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
