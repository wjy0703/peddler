<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="panel">
	<div class="pageContent">
	<form method="post" action="${ctx}/xhCarAudit/saveXhCarLoanApplyAudit" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		
		<input type="hidden" name="id" value="${xhCarLoanApply.id }"/>
		<input type="hidden" name="xhCarLoanApplyComplement.id" value="${xhCarLoanApply.xhCarLoanApplyComplement.id }"/>
		<input type="hidden" name="xhCarLoanUser.id" value="${xhCarLoanApply.xhCarLoanUser.id }"/>
		 <input type="hidden" name = "operation" id="operation" value="${opera }"/>
		 <input type="hidden" id="creditTypeState" value="${opera }"/>
		 
		<div class="pageFormContent" layoutH="56">
		<div class="panel">
				<h1>借款人信息审核</h1></div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>中文姓名：</label>
								</td>
								<td ><input type="text"  name="xhCarLoanUser.userName" value="${xhCarLoanApply.xhCarLoanUser.userName }" disabled="disabled"  size="40"></td>
								<td><label>身份证号：</label>
								</td>
								<td><input type="text"  name="xhCarLoanUser.cardNumber" value="${xhCarLoanApply.xhCarLoanUser.cardNumber }" disabled="disabled" size="40"></td>
							</tr>
							<tr height="30px">
								<td><label>评估金额：</label>
								</td>
								<td ><input type="text" name="xhCarLoanApplyComplement.assessMoney" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.assessMoney }"  size="40"></td>
								<td><label>借款金额：</label>
								</td>
								<td><input type="text"  name="xhCarLoanApplyComplement.suggestMoney" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.suggestMoney} "  size="40"></td>
							</tr>
							<tr height="30px">
								<td><label>借款成数：</label>
								</td>
								<td ><input type="text" name="" value="${xhCarLoanApply.loanScale }% " disabled="disabled"  size="40"></td>
								<td><label>产品类型：</label>
								</td>
								<td><sen:selectRadio name="justSelectNoUse" coding="carType" value="${xhCarLoanApply.jkType }" /></td>
							</tr>
							<tr height="30px">
								<td><label>综合息费：</label>
								</td>
								<td ><input type="text"  name="" value="${xhCarLoanApply.comlpexMoney }" disabled="disabled" size="40"></td>
								<td><label>办理期数：</label>
								</td>
								<td><input type="text"  name="" value="${xhCarLoanApply.jkCycle }" disabled="disabled" size="40"></td>
							</tr>
							<tr height="30px">
								<td><label>管辖城市：</label>
								</td>
								<td ><sen:address names="crmprovince,crmcity,crmarea"  titles="所属省市,所属城市,所属区县"  values="${xhCarLoanApply.crmprovince},${xhCarLoanApply.crmcity },${xhCarLoanApply.crmarea }" /></td>
								<td><label>团队经理：</label>
								</td>
								<td><input type="text"  value="${teamLeaderName }" disabled="disabled"  size="40"></td>
							</tr>
							<tr height="30px">
								<td><label>客户经理：</label>
								</td>
								<td ><input type="text"  name="" value="${customerLeaderName }" disabled="disabled"  size="40"></td>
								<td><label>车辆评估师：</label>
								</td>
								<td><input type="text" name="xhCarLoanApplyComplement.assessPerson" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.assessPerson }"  size="40"></td>
							</tr>
							<tr>
							    <td><label>是否展期:</label> </td>
							    <td colspan="3">
										<input name="" id="" disabled="disabled" type="text" size="40" value="<sen:vtoName coding="isExtension" value="${xhCarLoanApply.isExtension }" />" />
								</td>
							    
							</tr>
						</tbody>
					</table>
					<div class="panel">
				   <h1>审核具体情况</h1></div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>户籍地址：</label></td>
								<td>
								<input type="text"  name="xhCarLoanApplyComplement.auditHjadress" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditHjadress }"  size="50"></td>
								<td><label>身份真伪验证：</label>
								</td>
								<td><input type="text" name="xhCarLoanApplyComplement.auditZjhm" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditZjhm }"  size="50" class="required" ></td>
							</tr>
							<tr height="30px">
								<td><label>暂住证：</label>
								</td>
								<td >
								   <input type="text"  name="xhCarLoanApplyComplement.auditTemporary" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditTemporary }"  size="50">
								</td>
								<td><label>客户人法情况：</label>
								</td>
								<td><input type="text"  name="xhCarLoanApplyComplement.auditPersonlaw" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditPersonlaw }" size="50" class="required"></td>
							</tr>
							<tr height="30px">
								<td><label>客户现住址情况：</label>
								</td>
								<td ><input type="text"  name="xhCarLoanApplyComplement.auditHomeadress" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditHomeadress }" size="50"></td>
								<td><label>114电话查询情况：</label>
								</td>
								<td><input type="text"  name="xhCarLoanApplyComplement.audit114" value="${xhCarLoanApply.xhCarLoanApplyComplement.audit114 }" size="50" class="required"></td>
							</tr>
							<tr height="30px">
								<td><label>客户工作审核情况：</label>
								</td>
								<td colspan="3"><input type="text"  name="xhCarLoanApplyComplement.auditWork" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditWork }" size="80" class="required"></td>
							</tr>
							<tr height="30px">
								<td><label>征信报告显示情况：</label>
								</td>
								<td colspan="3"><input type="text"  name="xhCarLoanApplyComplement.auditCredit" value="${xhCarLoanApply.xhCarLoanApplyComplement.auditCredit }" size="80" class="required"></td>
							</tr>
							<tr height="30px">
								<td><label>车辆评估报告结论：</label>
								</td>
								<td ><input type="text" name="xhCarLoanApplyComplement.assessFinish" readonly="readonly"  value="${xhCarLoanApply.xhCarLoanApplyComplement.assessFinish }" size="50"></td>
								<td><label>外观检测：</label>
								</td>
								<td><input type="text"  name="xhCarLoanApplyComplement.visualInspection" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.visualInspection }" size="50"></td>
							</tr>
							<tr height="30px">
								<td><label>交强险：</label>
								</td>
								<td >
									<input type="text"  name="xhCarLoanApplyComplement.trafficInsurance"  readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.trafficInsurance }" size="50">
									</td>
								<td><label>商业险：</label>
								</td>
								<td>
									<input type="text"  name="xhCarLoanApplyComplement.businessInsurance"  readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.businessInsurance }" size="50">
									</td>
							</tr>
							<tr height="30px">
								<td><label>车架号：</label>
								</td>
								<td ><input type="text"  name="xhCarLoanApplyComplement.chassisNumber" readonly="readonly"  value="${xhCarLoanApply.xhCarLoanApplyComplement.chassisNumber }" size="50"></td>
								<td><label>出厂日期：</label>
								</td>
								<td><input type="text"  name="xhCarLoanApplyComplement.madeTime"  readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.madeTime }" size="50"></td>
							</tr>
							<tr height="30px">
								<td><label>车牌号码：</label>
								</td>
								<td ><input type="text"  name="xhCarLoanApplyComplement.plate" readonly="readonly"   value="${xhCarLoanApply.xhCarLoanApplyComplement.plate }" size="50"></td>
								<td><label>登记日期：</label>
								</td>
								<td><input type="text" name="xhCarLoanApplyComplement.registerTime" readonly="readonly"  value="${xhCarLoanApply.xhCarLoanApplyComplement.registerTime }" size="50"></td>
							</tr>
							<tr height="30px">
								<td><label>车辆厂牌型号：</label>
								</td>
								<td ><input type="text" name="xhCarLoanApplyComplement.lable"  readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.lable }" size="50"></td>
								<td><label>车年检情况：</label>
								</td>
								<td>
									<input type="text" name="xhCarLoanApplyComplement.inspection" readonly="readonly"  value="${xhCarLoanApply.xhCarLoanApplyComplement.inspection}" size="50"/>
									</td>
							</tr>
							<tr height="30px">
								<td><label>违章及事故情况：</label>
								</td>
								<td colspan="3"><input type="text" name="xhCarLoanApplyComplement.breakRules" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.breakRules }" size="80"></td>
							</tr>
					<tr>
						<th width="20%">联系人</th>
						<th width="20%">姓名</th>
						<th width="20%">电话</th>
						<th width="40%">审核情况</th>
					</tr>
					<tr>
						<td align="left"><input type="text" name="" disabled="disabled" value="本人手机"  size="30"/></td><!--与本人关系 -->
						<td align="left"><input type="text" name="" disabled="disabled" value="${xhCarLoanApply.xhCarLoanUser.userName }"  size="30"/></td><!-- 姓名 -->
						<td align="left"><input type="text" name="xhCarLoanApplyComplement.telephone" readonly="readonly" value="${xhCarLoanApply.xhCarLoanApplyComplement.telephone }"  size="30"/></td><!-- 电话 -->
						<td align="left"><input type="text" errorTitle="审核情况" name="xhCarLoanApplyComplement.audittelephone" value="${xhCarLoanApply.xhCarLoanApplyComplement.audittelephone }"  size="70" class="required"/></td><!-- 审核情况 -->
					</tr>
					<c:forEach items="${xhCarLoanApply.xhCarLoanUser.xhCarLxr}" var="lxr" varStatus="lxrIndex">
						<tr>
						   
							<td align="left">
							 	 <input type="hidden" name="xhCarLoanUser.xhCarLxr[${lxrIndex.index}].xhCarLoanUser.id" value="${xhCarLoanApply.xhCarLoanUser.id }"/>
		
								<input type="text" name="xhCarLoanUser.xhCarLxr[${lxrIndex.index}].ybrgx" disabled="disabled" value="${lxr.ybrgx }"  size="30"/>
							</td><!--与本人关系 -->
							<td align="left"><input type="text" disabled="disabled" name="xhCarLoanUser.xhCarLxr[${lxrIndex.index}].name" value="${lxr.name }"  size="30"/></td><!-- 姓名 -->
							<td align="left"><input type="text" disabled="disabled" name="xhCarLoanUser.xhCarLxr[${lxrIndex.index}].jjlxrlxdh"  value="${lxr.jjlxrlxdh }"  size="30"/></td><!-- 电话 -->
							<td align="left">
							   <input type="text" name="xhCarLoanUser.xhCarLxr[${lxrIndex.index}].auditMessage" value="${lxr.auditMessage }" errorTitle="联系人审核信息" class="required" size="70"/>
							  <input type="hidden" name="xhCarLoanUser.xhCarLxr[${lxrIndex.index}].id" value="${lxr.id }" />
							</td><!-- 审核情况 -->
							
						</tr>
					</c:forEach>
					
						</tbody>
					</table>
					<c:if test="${isExtension==1}">
					<div class="panel">
				<h1>车借老客户</h1></div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>是否属于结清重借：</label>
								</td>
								<td><sen:selectRadio name="b" coding="isExtension" value="${applyState}"/></td>
								<td><label>上笔还款是否正常：</label>
								</td>
								<td><sen:selectRadio name="a" coding="isExtension" value="" /></td>
							</tr>
							<tr height="30px">
								<td><label>逾期期数：</label>
								</td>
								<td ><input type="text" name="" value="" disabled="disabled"  size="40"></td>
								<td><label>逾期金额：</label>
								</td>
								<td><input type="text"  name="" value="" disabled="disabled" class="number" size="40"></td>
							</tr>
							<tr height="30px">
								<td><label>上笔借款产品类型：</label>
								</td>
								<td ><sen:selectRadio name="c" coding="carType" value="${xhCarLoanApplyOracle.jkType }"/></td>
								<td colspan="2" style="color:red" > &nbsp;&nbsp;如上笔借款为移交，本笔借款为GPS，不予办理</td>
							</tr>
						</tbody>
					</table>
					</c:if>
					<c:if test="${not empty xhCarLoanApply.xhCarAudit }">
					 <div class="panel">
				      <h1>审批结果</h1>
				     </div>
					  <table class="list" width="100%">
						<tbody>
						   <c:forEach items="${auditList }" var="audit" varStatus="index" begin="${fn:length(auditList)-1}">
						   
						   <tr>
						       <td width="20">
						          <c:if test = "${audit.CREDIT_TYPE == 1}">&nbsp;&nbsp;初审</c:if><c:if test = "${audit.CREDIT_TYPE == 2}">&nbsp;&nbsp;复审</c:if><c:if test = "${audit.CREDIT_TYPE == 100}">&nbsp;&nbsp;终审</c:if>结果：
						       </td>
							   <td colspan="3">
                                   <c:if test="${audit.CREDIT_RESULT==1 }">通过</c:if><c:if test="${audit.CREDIT_RESULT==0 }">拒绝 </c:if><c:if test="${audit.CREDIT_RESULT==2 }">退回</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
							            审批金额：     ${audit.CREDIT_AMOUNT }元 &nbsp;&nbsp;&nbsp;&nbsp;借款期限：${audit.CREDIT_MONTH }天   &nbsp;&nbsp;&nbsp;&nbsp;
							            综合费率：${audit.CREDIT_ALL_RATE }%&nbsp;&nbsp;&nbsp;&nbsp;业务收费:${audit.OPERATION_FEE }元 &nbsp;&nbsp;&nbsp;&nbsp;
							   </td>
							</tr>
							<tr height="30px">
								<td >
								    <label><c:if test = "${audit.CREDIT_TYPE == 1}">初审</c:if>
                                           <c:if test = "${audit.CREDIT_TYPE == 2}">复审</c:if>
                                           <c:if test = "${audit.CREDIT_TYPE == 100}">终审</c:if>意见：</label>
								</td>
								<td colspan="3">
								     <textarea style="width: 90%;" disabled="disabled">${audit.CREDIT_AUDIT_REPORT }</textarea>
								</td>
							</tr>
							<c:if test="${audit.creditResult==0 }">
								<tr>
							    	<td>
								    	<label>拒绝原因：</label>
									</td>
									<td colspan="3">
								     <textarea  style="width: 90%;" disabled="disabled">${audit.CREDIT_REFUSE_REASON }</textarea>
									</td>
								</tr>
							</c:if>
							<c:if test="${audit.creditResult==2 }">
								<tr>
							    	<td>
								    	<label>退回原因：</label>
									</td>
									<td colspan="3">
								     <textarea  style="width: 90%;" disabled="disabled">${audit.CREDIT_REFUSE_REASON }</textarea>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
				</c:if>	
			<div class="panel">
				<h1>审批</h1></div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>审批意见：</label>
								</td>
								<td colspan="3">
								<input type="hidden"  name="xhCarAudit[0].id" value=""  size="120">
								<textarea name="xhCarAudit[0].creditAuditReport" style="width: 90%;" class="required"></textarea>
								</td>
							</tr>
							<tr height="30px">
								<td><label>审批金额(元)：</label>
								</td>
								<td ><input type="text" name="xhCarAudit[0].creditAmount" class="required number" value="${xhCarAudit.creditAmount }"  size="40"></td>
								<td><label>借款期限：</label>
								</td>
								<td><sen:selectRadio name="xhCarAudit[0].creditMonth" coding="carCycle" value="${xhCarLoanApply.jkCycle }" clazz="required" split="&nbsp;&nbsp;&nbsp;&nbsp;"/>
								</td>
							</tr>
							<tr height="30px">
								<td><label>综合费率：</label>
								</td>
								<td ><input type="text"  name="xhCarAudit[0].creditAllRate" class="required number" value="${xhCarAudit.creditAllRate }"  size="20">%</td>
								<td><label>业务收费(元)：</label>
								</td>
								<td>
								<input type="text"  name="xhCarAudit[0].operationFee" class="required number" value="${xhCarAudit.operationFee }" size="40">
								<input type="hidden"  name="xhCarAudit[0].creditType" id="creditType" value="${creditType }" size="40">
							  <input type="hidden" name="xhCarAudit[0].xhCarLoanApply.id" value="${xhCarLoanApply.id }"/> 
								</td>
							</tr>
							<tr>
							   <td>
							      <label>审批结果：</label>
							   </td>
							   <td colspan="3"> 
							      <input type="radio" name="xhCarAudit[0].creditResult"  class="creditResultRadio required" value="1"> 审批通过
								  <input type="radio" name="xhCarAudit[0].creditResult"  class="creditResultRadio required" value="0"> 审批拒绝 
								  <input type="radio" name="xhCarAudit[0].creditResult"  class="creditResultRadio required" value="2"/>退回 
							   </td>
							</tr>
							<tr height="30px"  id = "creditRefuseReasonTr" style="display:none;">
							   <td>
							      <label>退回/拒借原因：</label>
							   </td>
							   <td colspan="3">
									<textarea name="xhCarAudit[0].creditRefuseReason"  id="creditRefuseReasonSecond1" rows="7" style="width: 93%; height: 70"></textarea>
							   </td>
							</tr>
						</tbody>
					</table>
			</div>
						
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="realSubmit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
<!--

$(function(){
	var $box = navTab.getCurrentPanel();
	//var creditResult = $('input[name="creditResult"]:checked').val();
	//var $office = $('#creditRefuseReasonFirst', $box);
	
	
	$('input[name="creditResult"]:checked',$box).each(function(){
		var $office = $('#creditRefuseReasonFirst', $box);
		var $reason = $('#creditRefuseReasonSecond1',$box);
		var creditResult = $(this).val();
		if (creditResult == 0||creditResult==2) {
			$office.show();
			$reason.addClass("required");
			} 
			else {
			$office.hide();
			}
	}); 
	//选择审核操作的按钮的点击事件
	$("input.creditResultRadio",$box).click(function(){
	   var $currentBox = navTab.getCurrentPanel();
	   var creditType = $("#creditType",$currentBox).val(); 
	   var selectedValue = $(this).val();//复审2，终审100，初审1的值
	   if(selectedValue == 0||selectedValue == 2){//选择拒绝，显示拒绝的textarea
	   		$("#creditRefuseReasonTr",$currentBox).show();
	   		$("#creditRefuseReasonSecond1",$currentBox).addClass("required");
	   }else{
	  	    $("#creditRefuseReasonTr",$currentBox).hide();
	  	  $("#creditRefuseReasonSecond1",$currentBox).removeClass();
	   }
	   //如果是退回操作
	   if(selectedValue == 2){
	       var creditTypeState = $("#creditTypeState").val();
	   	   $("#operation",$currentBox).val(creditTypeState +".B");
	   }else if(selectedValue == 0){
	      /* //代表是拒绝操作
	      if(creditType == 1){//初审
	           $("#operation",$currentBox).val(34);
	      }else if(creditType == 2){//复审
	           $("#operation",$currentBox).val(35);
	      }else {//终审
	           $("#operation",$currentBox).val(36);
	      } */
		   var creditTypeState = $("#creditTypeState").val();
	   	   $("#operation",$currentBox).val(creditTypeState +"5");
	   }
	   
	});
});

//-->
</script>

