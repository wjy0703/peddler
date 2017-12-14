<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
	<!-- <h1>新增客户咨询</h1> -->
	<div class="pageFormContent">
		<form method="post" action="${ctx}/zxgl/saveLoopConsulting"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<input type="hidden" name="id" value="${xydkzx.id}" />
				<input type="hidden" id="jksqId" name="jksqId" />

				<h1>基本信息</h1>
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>咨询客户姓名：</label> <input id="khmcNew" name="khmc"
							type="text" size="30" alt="请填写咨询客户姓名" value="${xydkzx.khmc }"
							class="required" /></td>
					</tr>
					<tr>
						<td><label>证件类型：</label> <sen:select clazz="combox required"
								name="pocertificates" coding="cardType" id="pocertificates"
								value="${pocertificates}" /></td>
					</tr>
					<tr>
						<td><label>证件号码：</label> <input type="text" id="zjhmNew"
							name="zjhm" size="30" class="required" value="" /> <a
							title="核实信息" href="#" class="btnSelect" onclick="isIdInfo()"><span
								style="color: red">核实信息</span></a>
							<div id="infoDiv" style="color: red">未核实</div></td>
					</tr>
				</table>
				<div class="divider"></div>
				<h1>货款信息</h1>
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>团队经理：</label> <input type="hidden"
							name="employeeCrm.id" value="${employee.id}" /> <input
							type="text" name="employeeCrm.name" value="${employee.name}"
							readonly="readonly" /></td>
						<td><label>客户经理：</label> <input type="text" id="empname"
							class="required" name="employeeCca.name"
							value="${xydkzx.employeeCca.name }" suggestFields="name"
							suggestUrl="${ctx}/baseinfo/emplookup2?lookId=${employee.organi.id}"
							lookupGroup="employeeCca" readonly /> <input type="hidden"
							name="employeeCca.id" value="${xydkzx.employeeCca.id}" /> <a
							id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
							lookupGroup="employeeCca"><hi:text key="查找带回" /></a>
							<div style="display: none;">
								<input type="text" name="employeeCrm.lookId" id="lookId"
									value="${employee.organi.id}"
									onpropertychange="lookLocation(this.value)" />
							</div></td>
					</tr>
					<tr>
						<td><label>借款类型：</label> <input type="radio" name="jklx"
							value="1" checked>个人信用借款&nbsp; <!--  	<input type="radio" name="jklx" value="2">房屋抵押借款&nbsp; <input
							type="radio" name="jklx" value="3">车辆抵押借款&nbsp;--></td>
					</tr>
					<tr>
						<td><label>计划借款金额：</label> <input name="planAmount"
							type="text"
							onafterpaste="this.value=this.value.replace(/\D/g,'')"
							onkeyup="this.value=this.value.replace(/\D/g,'')" size="30"
							value="${xydkzx.planAmount }" class="" /></td>
						<td><label>借款用途：</label> <input name="loanPurpose"
							type="text" size="30" value="${xydkzx.loanPurpose }" /></td>
					</tr>
				</table>

			</div>

			<div class="formBar">
				<ul>

					<li><div id="submitDiv" class="buttonActive"
							style="display: none">
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
<script>
	function isIdInfo() {
		var zjhm = document.getElementById("zjhmNew").value;
		var khmc = document.getElementById("khmcNew").value;
		if (zjhm == "" || khmc == "") {
			alertMsg.error("必填字段，请输入！");
			return;
		}

		$
				.ajax({
					url : "${ctx}/zxgl/isIdInfo",
					cache : false,
					global : false,
					async : false,
					type : 'post',
					data : {
						khmc : khmc,
						zjhm : zjhm
					},
					success : function(data) {
						var msg = "";
						if (data.data != "0") {
							msg = "核实成功";
							$("#jksqId",navTab.getCurrentPanel()).val(data.data);
							document.getElementById("submitDiv").style.display = "block";
						} else {
							msg = "核实失败,该用户不符合循环借条件";
							document.getElementById("submitDiv").style.display = "none";
						}
						alertMsg.info(msg);
						$("#infoDiv").html(msg);
						//navTab.reloadFlag('rel_loopConsulting');
					}
				});
	}
</script>