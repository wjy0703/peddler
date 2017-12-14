<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhCarLoanApply/saveXhCarLoanExtension" class="pageForm required-validate" onsubmit="return submitApply(this);">
		<div class="pageFormContent" layoutH="56">
			<div class="panel" align="center">
				<h1>个人信息</h1>
				<div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>姓名：</label></td>
								<td><input type="text" id="userName" disabled="disabled" name="userName" value="${xhCarLoanApply.xhCarLoanUser.userName }" class=""> <input type="hidden" name="xhCarLoanUser.id"
									size="30" value="${xhCarLoanApply.xhCarLoanUser.id}" /> <input type="hidden" name="partenCarApplyId" value="${partenCarApplyId}" /> 
									<input type="hidden" name="id" value="${xhCarLoanApply.id}" /> 
									<input type="hidden" name="opt" value="" id="opt"/></td>
								<td><label>性别：</label></td>
								<td><sen:selectRadio name="genders" coding="genders" value="${xhCarLoanApply.xhCarLoanUser.genders }" clazz="required" /></td>
							</tr>
							<tr height="30px">
								<td><label>出生日期：</label></td>
								<td><input type="text" id="birthday" disabled="disabled" name="birthday" size="30" value="${xhCarLoanApply.xhCarLoanUser.birthday }" class=""></td>
								<td><label>手机号码：</label></td>
								<td><input type="text" id="telephone" disabled="disabled" name="telephone" size="30" value="${xhCarLoanApply.xhCarLoanUser.telephone }" class=""></td>
							</tr>
							<tr height="30px">
								<td><label>身份证号：</label></td>
								<td><input type="text" id="cardNumber" disabled="disabled" name="cardNumber" size="30" value="${xhCarLoanApply.xhCarLoanUser.cardNumber }" class=""></td>
								<td><label>证件有效期：</label></td>
								<td><input type="text" id="expiredDate" disabled="disabled" name="expiredDate" size="30" value="${xhCarLoanApply.xhCarLoanUser.expiredDate }" class=""></td>
							</tr>
							<tr height="30px">
								<td><label>户籍地址：</label></td>
								<td><input type="text" id="hjadress" disabled="disabled" name="hjadress" size="30" value="${xhCarLoanApply.xhCarLoanUser.hjadress }" class=""></td>
								<td><label>暂住证：</label></td>
								<td> <input type="text" id="temporaryCrede" disabled="disabled" name="temporaryCrede" size="30" value="${xhCarLoanApply.xhCarLoanUser.temporaryCrede }" class=""> 
									<%-- <sen:select clazz="combox" name="temporaryCrede" coding="hasChildren" value="${xhCarLoanApply.xhCarLoanUser.temporaryCrede}" />--%>
								</td>
							</tr>
							<tr height="30px">
								<td><label>本市住址：</label></td>
								<td><input type="text" id="homeAddress" disabled="disabled" name="homeAddress" size="30" value="${xhCarLoanApply.xhCarLoanUser.homeAddress }" class=""></td>
								<td><label>本市住址电话：</label></td>
								<td><input type="text" id="homePhone" disabled="disabled" name="homePhone" size="30" value="${xhCarLoanApply.xhCarLoanUser.homePhone }" class=""></td>
							</tr>
							<tr height="30px">
								<td><label>起始居住时间：</label></td>
								<td><input type="text" id="firstLiveDateStart" disabled="disabled" name="firstLiveDateStart" size="30" value="${xhCarLoanApply.xhCarLoanUser.firstLiveDateStart }" class=""></td>
								<td><label>出来本市年份：</label></td>
								<td><input type="text" id="firstComeYear" disabled="disabled" name="firstComeYear" size="30" value="${xhCarLoanApply.xhCarLoanUser.firstComeYear }" class=""></td>
							</tr>
							<tr height="30px">
								<td><label>婚姻状况：</label></td>
								<td><input type="text" id="maritalStatus" disabled="disabled" name="maritalStatus" size="30" value="${xhCarLoanApply.xhCarLoanUser.maritalStatus }" class=""></td>
								<td><label>子女：</label></td>
								<td><input type="text" id="hasChildren" disabled="disabled" name="hasChildren" size="30" value="${xhCarLoanApply.xhCarLoanUser.hasChildren }" class=""></td>
							</tr>
							<tr height="30px">
								<td><label>学历：</label></td>
								<td>
									<%-- <input type="text" id="educationType" disabled="disabled" name="educationType" size="30" value="${user.educationType }" class=""> --%> <sen:selectRadio name="educationType"
										value="${xhCarLoanApply.xhCarLoanUser.educationType}" coding="studyLevel" split="&nbsp;&nbsp;&nbsp;&nbsp;" /></td>
								<td><label>信用卡额度：</label></td>
								<td><input type="text" id="creditLimit" disabled="disabled" name="creditLimit" size="30" value="${xhCarLoanApply.xhCarLoanUser.creditLimit }" class=""></td>
							</tr>
							<tr height="30px">
								<td><label>供养亲属人数：</label></td>
								<td colspan="3"><input type="text" id="dependentRelatives" disabled="disabled" name="dependentRelatives" size="30" value="${xhCarLoanApply.xhCarLoanUser.dependentRelatives }" class=""></td>
							</tr>
							<tr height="30px">
							<td><label>房产：</label></td>
								<td colspan="3"><sen:selectRadio name="liveState" value="${xhCarLoanApply.xhCarLoanUser.liveState}" split="&nbsp;&nbsp;&nbsp;&nbsp;"  coding="car_livestate"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel" align="center">
				<h1>车况信息</h1>
				<div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>评估金额(元)：</label></td>
								<td><input name="xhCarMessage.xhCarLoanUser.id" type="hidden" size="30" value="${xhCarLoanApply.xhCarLoanUser.id}" /> <input name="xhCarMessage.assessMoney" type="text" size="30"
									value="${xhCarLoanApply.xhCarMessage.assessMoney}" class="required number" maxlength="22" /></td>
								<td><label>建议借款额(元)：</label></td>
								<td><input name="xhCarMessage.suggestMoney" type="text" size="30" value="${xhCarLoanApply.xhCarMessage.suggestMoney}" class="required number" maxlength="22" /></td>
							</tr>
							<tr height="30px">
								<td><label>评估师姓名：</label></td>
								<td><input name="xhCarMessage.assessPerson" type="text" size="30" value="${xhCarLoanApply.xhCarMessage.assessPerson}" class="required" maxlength="22" /></td>
								
								<td><label>外观检测：</label></td>
								<td><input name="xhCarMessage.visualInspection" type="text" size="30" value="${xhCarLoanApply.xhCarMessage.visualInspection}" class="required" maxlength="22" /></td>
							</tr>
							<tr height="30px">
								<td><label>商业险：</label></td>
								<td><input name="xhCarMessage.businessInsurance"  format="yyyy-MM-dd" type="text" size="27" value="${xhCarLoanApply.xhCarMessage.businessInsurance}" class="date required" maxlength="22" /> <a
									class="inputDateButton" href="javascript：;">选择</a></td>
								<td><label>车年检情况：</label></td>
								<td><input name="xhCarMessage.inspection"  format="yyyy-MM-dd" type="text" size="27" value="${xhCarLoanApply.xhCarMessage.inspection}" class="date required" maxlength="22" /> <a
									class="inputDateButton" href="javascript：;">选择</a></td>
							</tr>
							<tr height="30px">
								<td><label>出厂日期：</label></td>
								<td><input name="xhCarMessage.madeTime" format="yyyy-MM-dd" type="text" size="27" value="${xhCarLoanApply.xhCarMessage.madeTime}" class="date required" maxlength="22" /> <a
									class="inputDateButton" href="javascript：;">选择</a></td>
								<td><label>交强险：</label></td>
								<td><input name="xhCarMessage.trafficInsurance" format="yyyy-MM-dd" type="text" size="27" value="${xhCarLoanApply.xhCarMessage.trafficInsurance}" class="date required" maxlength="22" /> <a
									class="inputDateButton" href="javascript：;">选择</a></td>
							</tr>
							<tr height="30px">
								<td><label>登记日期：</label></td>
								<td><input name="xhCarMessage.registerTime"  type="text" format="yyyy-MM-dd" size="27" value="${xhCarLoanApply.xhCarMessage.registerTime}" class="date required" maxlength="22" /> <a
									class="inputDateButton" href="javascript：;">选择</a></td>
								<td><label>车架号：</label></td>
								<td><input name="xhCarMessage.chassisNumber" type="text" size="30" value="${xhCarLoanApply.xhCarMessage.chassisNumber}" class="required" maxlength="22" /></td>
							</tr>
							<tr height="30px">
								<td><label>车辆厂牌型号：</label></td>
								<td><input name="xhCarMessage.lable" type="text" size="30" value="${xhCarLoanApply.xhCarMessage.lable}" class="required" maxlength="22" /></td>
								<td><label>车牌号码：</label></td>
								<td><input name="xhCarMessage.plate" id="plate" type="text" size="30" value="${xhCarLoanApply.xhCarMessage.plate}" class="required" maxlength="22" onblur = "isCPHM()"/></td>
							</tr>
							 <tr height="30px">
								<td><label>违章及事故情况：</label></td>
								<td  colspan="3"><textarea name="xhCarMessage.breakRules" class="required" cols="100" >${xhCarLoanApply.xhCarMessage.breakRules}</textarea></td>
								
							</tr>
							<tr height="30px">
								<td><label>车辆评估报告结论：</label></td>
								<td  colspan="3"><textarea name="xhCarMessage.assessFinish" class="required" cols="100" >${xhCarLoanApply.xhCarMessage.assessFinish}</textarea></td>
							</tr> 
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel" align="center">
				<h1>借款信息</h1>
				<div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>借款金额(元)：</label></td>
								<td><input name="jkLoanQuota" id="jkLoanQuota2" type="text" size="30" value="${jkLoanQuota }" onblur = "Compare()" class="required number" />元
								    <input name="" id="jkLoanQuota1" type="hidden" size="30" value="${jkLoanQuota1}" class="required number" />
								</td>
								<td><label>申请日期：</label></td>
								<td><input name="jkLoanDate" format="yyyy-MM-dd" type="text" size="27" value="${jkLoanDate }" class="date required" maxlength="22" /> <a
									class="inputDateButton" href="javascript：;">选择</a><td>
							</tr>
							<tr height="30px">
								<td><label>申请借款期限：</label></td>
								<td >
								<%-- <sen:selectRadio name="jkCycle" coding="carCycle" value="${xhCarLoanApply.jkCycle }"  clazz="required"/> --%>
								<input type="radio" name="jkCycle" value="30" checked="checked"/> 30
								</td>
								<td><label>借款类型：</label></td>
								<td><sen:selectRadio name="jkType" coding="carType" value="${xhCarLoanApply.jkType }"  clazz="required" /></td>
							</tr>
							<tr height="30px">
								<td><label>开卡(省市)：</label></td>
								<td><sen:address names="openBankProvince,openBankCity" titles="所属省市,所属城市" required="true" values="${xhCarLoanApply.openBankProvince},${xhCarLoanApply.openBankCity}" /></td>
								<td><label>银行账号：</label></td>
								<td><input name="bankNum" type="text" size="30" value="${xhCarLoanApply.bankNum}" class="required" maxlength="22" /></td>
								
							</tr>
							<tr height="30px">
								
								<td><label>开卡行：</label></td>
								<td colspan="3"><input name="bankOpen" type="text" size="60" value="${xhCarLoanApply.bankOpen}" class="required" maxlength="60" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel">
				<h1>客户开发及管辖信息</h1>
				<div>
					<table class="list" width="100%">
						<tr>
							<td><label>团队经理： </label></td>
							<td> <input type="text"
							id="empname" 
							name="employeeCca.name" value="${xhCarLoanApply.employeeCca.name }" required="required"
							suggestFields="name,deptname" lookupGroup="employeeCca" readonly />
							<input type="hidden" name="employeeCca.id"
							value="${xhCarLoanApply.employeeCca.id }" />
							<a class="btnLook"
							href="${ctx }/baseinfo/emplookup?code=0002&lookId=${employee.organi.id}&parentFlag=1"
							lookupGroup="employeeCca"><hi:text key="查找带回" /></a> </td>
							<td><label>销售：</label></td>
							<td><input type="text"
							id="empname" 
							name="employeeCrm.name" value="${xhCarLoanApply.employeeCrm.name }" required="required"
							suggestFields="name,deptname" lookupGroup="employeeCrm" readonly
							alt="" /> 
							<input type="hidden" name="employeeCrm.id"
							value="${xhCarLoanApply.employeeCrm.id }" /> 
							<a class="btnLook"
							href="${ctx }/baseinfo/emplookup?code=0001&lookId=${employee.organi.id}&code2=0002"
							lookupGroup="employeeCrm"><hi:text key="查找带回" /></a></td>
						</tr>
						<tr height="30px">
							<td><label>车借客服：</label>
							</td>
							<td><input name="" type="text" readonly="readonly" size="20" value="${xhCarLoanApply.employeeService.name}" class="required" /> 
							<input name="employeeService.id" size="20" value="${xhCarLoanApply.employeeService.id}" type="hidden" /></td>
							<td><label>管辖城市：</label>
							</td>
							<td><sen:address names="crmprovince,crmcity,crmarea" titles="所属省市,所属城市,所属区县" required="true" values="${xhCarLoanApply.crmprovince},${xhCarLoanApply.crmcity },${xhCarLoanApply.crmarea }" />
							</td>
						</tr>
						<tr height="30px">
							<td><label>客户来源：</label>
							</td>
							<td colspan="5"><sen:selectRadio name="knownWay" value="${xhCarLoanApply.knownWay }" coding="customerSource" split="&nbsp;&nbsp;&nbsp;&nbsp;" clazz="required" /></td>
						</tr>
						<tr height="30px">
							<td><label>客户人法情况：</label>
							</td>
							<td colspan="5"><textarea name="personLawCase" cols="100" class="required">${xhCarLoanApply.personLawCase}</textarea></td>
						</tr>
					</table>
				</div>
			</div>
			</div>
			
			<div class="formBar" id="operation div">
				<ul>
				 <c:if test="${look == null}">
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button id="tempSave" type="submit">暂存</button>
							</div>
						</div>
					</li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button id="realSubmit" type="submit" >提交</button>
							</div>
						</div>
					</li>
				 </c:if>
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

function Compare(){
	 var jkLoanQuota1 = $("#jkLoanQuota1").val(); 
	 var jkLoanQuota2 = $("#jkLoanQuota2").val(); 
	 if(parseInt(jkLoanQuota2)>=parseInt(jkLoanQuota1)){
		 alertMsg.error('<font color="red">展期借款金额不能超过或等于原始借款金额</font>');
		 return false;
	 }
}

function submitApply(form){
		var $form = $(form);
		var $box = navTab.getCurrentPanel();
		if (!$form.valid()) {
				return false;
	    }
		return validateCallback(form, navTabAjaxDone);
}

function isCPHM(value){
	debugger;
	if(value == ""){
		return ;
	}
	var $box = navTab.getCurrentPanel();
	var plate = $('input[id="plate"]',$box).val();
	
	var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	
	if(plate.search(re)==-1){
		alertMsg.error('车牌号码：<font color="red">格式错误</font>');
		return false;
   
	}
	
	
}

$(function(){
   var $box = navTab.getCurrentPanel();
   $("#tempSave").click(function(){
   		$("input[name='opt']",$box).val("0");
   });
   
   $("#realSubmit").click(function(){
   		$("input[name='opt']",$box).val("1");
   }); 
   <c:if test="${look != null}">
          $('input',$box).attr('disabled',true);
          $('select',$box).attr('disabled',true);
   </c:if>
});
</script>
