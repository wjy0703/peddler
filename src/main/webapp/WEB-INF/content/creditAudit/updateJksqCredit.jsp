<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
<div class="pageFormContent" layoutH="56">
<div id="isAuditShow" class="pageFormContent" layoutH="56" style="display:block">
	信审人员验证
	<div class="divider"></div>
	<table width="100%">
			<tr>
				<td>
				<label>审批经理A：</label>
				<input type="hidden" id="employeeAId" name="employeeA.id"/>
				<input type="text" id="empnameA" class="required" name="employeeA.name" value="" suggestFields="name,deptname" suggestUrl="${ctx}/baseinfo/findEmpByPosition/23" lookupGroup="employeeA" readonly size="" />
				</td>
				<td>
				<label>登录密码A：</label>
				<input type="password" id="passWordA" name="passWordA" class="input required" value="" />
				</td>
			</tr>
		</table>
		<div class="divider"></div>
		<table width="100%">
			<tr>
				<td>
				<label>审批经理B：</label>
				<input type="hidden" id="employeeBId" name="employeeB.id"/>
				<input type="text" id="empnameB" class="required" name="employeeB.name" value="" suggestFields="name,deptname" suggestUrl="${ctx}/baseinfo/findEmpByPosition/23" lookupGroup="employeeB" readonly size="" />
				</td>
				<td>
				<label>登录密码B：</label>
				<input type="password" id="passWordB" name="passWordB" class="input required" value="" />
				</td>
			</tr>
		</table>
	<div class="divider"></div>
</div>
	<div id="isPassShow" class="pageFormContent" layoutH="56" style="display:none">
	<table width="100%">
			<tr>
				<td><label>借款人姓名：</label><input value="${xhJksq.jkrxm}"
					readonly /></td>
				<td><label>借款人证件号码：</label><input value="${xhJksq.zjhm}"
					readonly /></td>
				<td><label>借款类型：</label><input
					value='${xhJksq.templet.describe}' readonly /></td>
			</tr>
			<tr>
				<td><label>借款金额：</label><input value="${xhJksq.jkLoanQuota}"
					readonly /></td>
				<td><label>借款期数：</label><input value="${xhJksq.jkCycle}"
					readonly /></td>
				<td><label>是否有共借人 ：</label><input value="${xhJksq.togetherPerson}"
					readonly /></td>
			</tr>
			<c:if test="${loanApply.togetherPerson eq '是'}">
						<tr>
						<td><label>共借人姓名</label><input
							value="<sen:showTogether lendId='${xhJksq.id}'/>" readonly /></td>
						<td><label>共借人身份证号码：</label><input
							value="<sen:showTogetherIdentification lendId='${xhJksq.id}'/>" readonly /></td>
						<td></td>
					</tr></c:if>
		</table>
		<c:forEach items="${listMap }" var="credit" varStatus="st">
		<div class="divider"></div>
		<span style="color: red">
		<c:if test="${credit.CREDIT_TYPE=='1'}">信审初审</c:if>
		<c:if test="${credit.CREDIT_TYPE=='2'}">信审复审</c:if>
		<c:if test="${credit.CREDIT_TYPE=='3'}">信审终审</c:if>
		</span>
		<div class="divider"></div>
			<table width="100%">
				<tr>
					<td><label>批借金额（元）：</label> <input name="creditAmount"
						id="creditAmount" type="text" size="20"
						value="${credit.CREDIT_AMOUNT}" class="required number" onblur="updateInfo('${credit.ID}', 'creditAmount', '${credit.CREDIT_AMOUNT}', this.value)">
						</td>
					<td><label>批借期限（月）：</label> <input name="creditMonth"
						id="creditMonth" type="text" size="20"
						value="${credit.CREDIT_MONTH}" maxlength="10"
						class="required number"  onblur="updateInfo('${credit.ID}', 'creditMonth', '${credit.CREDIT_MONTH}', this.value)"/></td>
<td><label>综合费率(%)：</label> <input name="creditAllRate"
						class="required number" id="creditAllRate" type="text" size="20"
						value="${credit.CREDIT_ALL_RATE}"  onblur="updateInfo('${credit.ID}', 'creditAllRate', '${credit.CREDIT_ALL_RATE}', this.value)"/></td>
					

				</tr>

				<tr>
					<td><label>外访咨询费（元）：</label> <input name="outVisitFee"
						id="outVisitFee" type="text" size="20"
						value="${credit.OUT_VISIT_FEE}" 
						class="required number" onblur="updateInfo('${credit.ID}', 'outVisitFee', '${credit.OUT_VISIT_FEE}', this.value)"/><span class="info"> </span></td>
					<td><label>加急费（元）：</label> <input name="urgentCreditFee"
						id="urgentCreditFee" type="text" size="20"
						value="${credit.URGENT_CREDIT_FEE}" 
						class="required number" onblur="updateInfo('${credit.ID}', 'urgentCreditFee', '${credit.URGENT_CREDIT_FEE}', this.value)"/> </td>
				</tr>
				
				</table>
</c:forEach>
			<div class="divider"></div>
			<span style="color: red">共同借款人</span>
			<div class="divider"></div>
			<c:if test="${listTogetherPerson==null}">
			<div id="togetherDiv">
				<p>
					<label> 共借人姓名：</label><input type="text" name="togetherName"
						id="togetherName" class="required"/>
				</p>
				<p>
					<label>共借人身份证件号码：</label><input type="text" name="togetherCardNo"
						id="togetherCardNo" class="required"/>
						<a href="#" title="添加共借人" class="btnAdd" onclick="addPerson('${xhJksq.id}')"><span style="color: red">添加</span></a>
				</p>
			</div>
			</c:if>
			<c:if test="${listTogetherPerson!=null}">
			<c:forEach items="${listTogetherPerson }" var="person" varStatus="st">
			<div id="togetherDiv">
				<p>
					<label> 共借人姓名：</label><input type="text" name="togetherName"
						id="togetherName" class="required" value="${person.TOGETHER_NAME}" onblur="updatePerson('${person.ID}', 'togetherName', '${person.TOGETHER_NAME}', this.value)"/>
				</p>
				<p>
					<label>共借人身份证件号码：</label><input type="text" name="togetherCardNo"
						id="togetherCardNo" class="required" value="${person.IDENTIFICATION}" onblur="updatePerson('${person.ID}', 'togetherCardNo', '${person.IDENTIFICATION}', this.value)"/>
						<a title="删除共借人" target="ajaxTodo" href="${ctx }/loan/delTogetherPerson/${person.ID}" class="btnDel"><span style="color: red">删除</span></a>
				</p>
			</div>
			</c:forEach>
			</c:if>
		</div>
</div>
<div class="formBar">
				<ul>
				<li>
				    <div id="submitDivValidate" class="button">
						<div class="buttonContent">
							<button type="button" onclick="loginPersonValidate()">验证</button>
						</div>
					</div>
				</li>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>

			
	</div>
			</div>
<script>
function updateInfo(id, name, oldValue, newValue){
	if(newValue == ""){
		alertMsg.error("必填字段，请输入！");
		return;
	}
	if(oldValue != newValue){
		alertMsg.confirm("您修改的信息未保存，请选择保存或取消！", {
			okCall: function(){
				$.ajax({
					  url: "${ctx}/loan/updateJksqCredit",
					  cache: false,
					  global: false,
					  async: false,
					  type:'post',
					  data:{id:id,name:name,value:newValue},
					  success: function(data){
						alertMsg.info(data.data);
						navTab.reloadFlag('rel_getJksqCredit');
					  }
					});
			},
			cancelCall: function(){
				navTab.reloadFlag('rel_getJksqCredit');
			}
		});
	}
}
function updatePerson(id, name, oldValue, newValue){
	if(newValue == ""){
		alertMsg.error("必填字段，请输入！");
		return;
	}
	if(oldValue != newValue){
		alertMsg.confirm("您修改的信息未保存，请选择保存或取消！", {
			okCall: function(){
				$.ajax({
					  url: "${ctx}/loan/updateTogetherPerson",
					  cache: false,
					  global: false,
					  async: false,
					  type:'post',
					  data:{id:id,name:name,value:newValue},
					  success: function(data){
						  alertMsg.info(data.data);
						  navTab.reloadFlag('rel_getJksqCredit');
					  }
					}); 
			},
			cancelCall: function(){
				navTab.reloadFlag('rel_getJksqCredit');
			}
		});
	}
}
function addPerson(id){
	var togetherName = document.getElementById('togetherName').value;
	var togetherCardNo = document.getElementById('togetherCardNo').value;
	if(togetherName == "" || togetherCardNo == ""){
		alertMsg.error("必填字段，请输入！");
		return;
	}
	alertMsg.confirm("您增加的信息未保存，请选择保存或取消！", {
		okCall: function(){
			$.ajax({
				  url: "${ctx}/loan/addTogetherPerson",
				  cache: false,
				  global: false,
				  async: false,
				  type:'post',
				  data:{id:id,data:togetherName,togetherCardNo:togetherCardNo},
				  //contentType: "application/x-www-form-urlencoded; charset=GB2312",
				  success: function(data){
					  alertMsg.info(data.data);
					  navTab.reloadFlag('rel_listJksqCredit');
					  navTab.reloadFlag('rel_getJksqCredit');
				  }
				}); 
		},
		cancelCall: function(){
			navTab.reloadFlag('rel_getJksqCredit');
		}
	});
}

function loginPersonValidate(){
	var $employeeAId = $("#employeeAId").val();
	var $empnameA = $("#empnameA").val();
	var $passWordA = $("#passWordA").val();
	var $employeeBId = $("#employeeBId").val();
	var $empnameB = $("#empnameB").val();
	var $passWordB = $("#passWordB").val();
	if($empnameA == "" || $empnameB == "" || $passWordA == "" || $passWordB == ""){
		alertMsg.error("必填字段，请输入！");
		return;
	}
	if($employeeAId == $employeeBId){
		alertMsg.error("不能选择同一个审批经理，请重新选择！");
		return;
	}
	alertMsg.confirm("请仔细检查审批经理信息，请选择确定或取消！", {
		okCall: function(){
			$.ajax({
				  url: "${ctx}/loan/loginPersonValidate",
				  cache: false,
				  global: false,
				  async: false,
				  type:'post',
				  data:{idA:$employeeAId,idB:$employeeBId,passA:$passWordA,passB:$passWordB},
				  //contentType: "application/x-www-form-urlencoded; charset=GB2312",
				  success: function(data){
					  alertMsg.info(data.data);
					  if(data.success == true){
						  $("#submitDivValidate").hide();
						  $("#isAuditShow").hide();
						  $("#isPassShow").show();
					  }
				  }
				}); 
		},
		cancelCall: function(){
			navTab.reloadFlag('rel_getJksqCredit');
		}
	});
}

</script>