<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhTzsq/saveTzsqAudit"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhTzsq.id}" /> <input
			type="hidden" name="cjrxx.id" value="${xhTzsq.cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			
					<p><label>客户编码：</label> <input type="text" class="required"
						name="khbmbianma" id="khbmbianma" value="${xhTzsq.cjrxx.khbm}"
						size="20" maxlength="20"/>  </p>
					<p><label>客户姓名：</label> <input name="cjrxx.cjrxm"
						type="text" size="30" value="${xhTzsq.cjrxx.cjrxm }"
						maxlength="40" readonly /></p>
					<p><label>证件号码：</label> <input name="cjrxx.zjhm"
						type="text" size="30" value="${xhTzsq.cjrxx.zjhm }" maxlength="40"
						readonly /></p>
					<p><label>客户性别：</label> 
					<input name=""
						type="text" size="30" value="${xhTzsq.cjrxx.cjrxb }" maxlength="40"
						 disabled="disabled" />
					</p>
					<p><label>联系电话：</label> <input name="" type="text" disabled="disabled"
						size="30" value="${xhTzsq.cjrxx.yddh }" class="" maxlength="20" /></p>
					<p><label>电子邮箱：</label> <input name="" type="text" disabled="disabled"
						size="30" value="${xhTzsq.cjrxx.dzyx }" class="email" maxlength="80" /></p>
					
					<p><label>紧急联系人姓名：</label> <input name="" type="text" disabled="disabled"
						size="20" value="${xhTzsq.cjrxx.jjlxrzwmc }" class=""
						maxlength="20" /></p>
					<p><label>紧急联系人电话：</label> <input name="" type="text" disabled="disabled"
						size="20" value="${xhTzsq.cjrxx.jjlxryddh }" class=""
						maxlength="20" /></p>
					<p><label>紧急联系人证件号码：</label> <input name="" type="text" disabled="disabled"
						size="20" value="${xhTzsq.cjrxx.jjlxrzjhm }" class=""
						maxlength="20" /></p>
					<p><label>母亲姓氏：</label> <input name="" type="text" disabled="disabled"
						size="20" value="${xhTzsq.cjrxx.mqxm }" class=""
						maxlength="20" /></p>
					<p><label>邮政编码：</label><input name="yb" type="text" disabled="disabled" size="10"
						value="${xhTzsq.cjrxx.yb }" class=" number" maxlength="6" minlength="6"/></p>
					<dl class="nowrap">
					<dt>
						详细通信地址<font color="red"></font>：
					</dt>
					<dd>
					<sen:address names="pro,ci,ar" titles="所有省市,所有城市,所有区县" values="${xhTzsq.cjrxx.province},${xhTzsq.cjrxx.city},${xhTzsq.cjrxx.area }"/>
						 <input name="txdz" type="text"
							size="80" value="${xhTzsq.cjrxx.txdz }" maxlength="60" disabled="disabled"/>
					</dd>
				</dl>
						<%-- <div class="divider"></div>
						
					<p><label>团队经理：</label> <input
						type="text" disabled="disabled"  class=" textInput"
						name="" value="${xhTzsq.cjrxx.employeeCca.name }" /> </p>
					<p><label>理财经理：</label> <input
						type="text" disabled="disabled"  class=" textInput"
						name="" value="${xhTzsq.cjrxx.employeeCrm.name }" /></p>
						<p><label>开发团队：</label> <input name="kftd" type="text" disabled="disabled"
						size="20" value="${xhTzsq.cjrxx.kftd }" class="" maxlength="80" /></p> --%>
			
			<div class="divider"></div>
			<p><label>资金来源:</label>
						<sen:select clazz="zjly combox" name="sqtype" coding="moneyComeType" value="${xhTzsq.sqtype}" title="请选择"/>
			</p>
				<div class="divider"></div>
					<p><label>出借编号：</label> <input name="bianma" id="bianma" type="text"
						size="30" value="${xhTzsq.tzsqbh}"  maxlength="20" class="required"/></p>
					<p><label>计划出借日期：</label> <input name="jhtzrq" type="text"
						size="25" value="${xhTzsq.jhtzrq}" class=" date"
						maxlength="20" readonly /> <a class="inputDateButton"
						href="#">选择</a></p>
				
				
					<p><label>计划出借金额：</label> <input name="jhtzje" type="text"
						size="30" value="${xhTzsq.jhtzje}"  maxlength="20"
						readonly /></p>
					<p><label>出借方式：</label> <select name="tzcp.id"
						class=" combox">
							<option value="" <c:if test="${xhTzsq.tzcp.id==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select></p>
					
					<p><label>付款方式：</label><%--  <select name="fkfs" class="combox">
							<option value="" <c:if test="${xhTzsq.fkfs==''}">selected</c:if>>请选择</option>
							<c:forEach items="${fkfs}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${xhTzsq.fkfs==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
						</select> --%>
						<sen:select clazz="combox"  coding="payType" name="fkfs" value="${xhTzsq.fkfs }" />
						</p>
				
				
					<p><label>是否风险金补偿：</label> <input name="sffxjbc" type="text"
						size="6" value="${xhTzsq.sffxjbc}" 
						maxlength="10" readonly /></p>
					<p id="jhrq"><label>计划划扣日期：</label> <input name="jhhkrq" type="text"
						size="25" value="${xhTzsq.jhhkrq}" class=" date"
						maxlength="20" readonly /> <a class="inputDateButton"
						href="#">选择</a></p>
				
				
					<p><label>申请日期：</label> <input name="sqrq" type="text"
						size="25" value="${xhTzsq.sqrq}" class=" date"
						maxlength="20" readonly /> <a class="inputDateButton"
						href="#">选择</a></p>
					<p><label>协议版本：</label> <%-- <select name="xybb"
						class=" combox" readonly>
							<option value="" <c:if test="${xhTzsq.xybb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${wtxybbh}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${xhTzsq.xybb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%>
					<sen:select clazz="combox"  coding="wtxybbh" name="xybb" value="${xhTzsq.xybb }" />
					</p>
				
				
					<p><label>销售折扣率：</label> <input name="xszkly" type="text"
						size="6" value="${xhTzsq.xszkly}%"  maxlength="10"
						readonly /></p>
					<p><label>销售折扣率有效期限：</label> <input name="xszklyxqx"
						type="text" size="25" value="${xhTzsq.xszklyxqx}" 
						maxlength="10" readonly /><a class="inputDateButton"
						href="#">选择</a></p>
						<p><label>合同编号：</label> <input name="contractNumber"
						type="text" size="30" value="${xhTzsq.contractNumber}" class=" " readonly
						 /></p>
				
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 60"
						readonly>${xhTzsq.remark}</textarea>
				</dd>
			</dl>
			<div class="divider"></div>
<table width="100%" border="1" ><tr><td>
<dl class="nowrap">
				<dd>
							<p><label><b>出借付款银行账户：</b></label>
							<p><label>开户行：</label>
							<%-- <select name="tzfkkhh" id="tzfkkhh_tzsq"
								class=" combox" readonly>
									<option value=""
										<c:if test="${xhTzsq.tzfkkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${xhTzsq.tzfkkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%>
							 	<sen:select clazz="combox"  coding="bank" name="tzfkkhh" value="${xhTzsq.tzfkkhh }" />
							</p>
							<p><label>具体支行：</label>
							<input name="cjrxx.tzfkyhmc" type="text" size="40"
								value="${xhTzsq.tzfkyhmc}" maxlength="100" readonly /></p>
						
							<p><label>开户姓名：</label>
							<input name="cjrxx.tzfkkhmc" type="text" size="40"
								value="${xhTzsq.tzfkkhmc}" maxlength="80" readonly /></p>
							<p><label>银行账号：</label>
							<input name="cjrxx.tzfkyhzh" id="tzfkyhzh"
								type="text" size="40" value="${xhTzsq.tzfkyhzh}" maxlength="80"
								readonly /></p>
						
					
				</dd></dl></td><td>
<dl class="nowrap">

					<dd>
					
							<p><label><b>回收资金银行账户：</b></label>
							<p><label>开户行：</label>
							<%-- <select name="hszjkhh" id="hszjkhh_tzsq"
								class=" combox" readonly>
									<option value=""
										<c:if test="${xhTzsq.hszjkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${xhTzsq.hszjkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%>
								<sen:select clazz="combox"  coding="bank" name="hszjkhh" value="${xhTzsq.hszjkhh }" />
							</p>
							<p><label>具体支行：</label>
							<input name="cjrxx.hszjyhmc" type="text" size="40"
								value="${xhTzsq.hszjyhmc}" maxlength="100" readonly /></p>
						
						
							<p><label>开户姓名：</label>
							<input name="cjrxx.hszjkhmc" type="text" size="40"
								value="${xhTzsq.hszjkhmc}" maxlength="80" readonly /></p>
							<p><label>银行账号：</label>
							<input name="cjrxx.hszjyhzh" id="hszjyhzh"
								type="text" size="40" value="${xhTzsq.hszjyhzh}" maxlength="80"
								readonly /></p>
						
					
				</dd></dl></td></tr></table>
			
				
			
			
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>审批意见：</dt>
				<dd>
					<textarea name="auditIdea" style="width: 93%; height: 80">${xhTzsq.auditIdea }</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>审批结果：</dt>
				<dd>
					<input type="radio" name="state" id="state" value="2" checked="checked" />通过
					<input type="radio" name="state" id="state" value="3" />不通过
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return checkBianma();">保存</button>
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
<script type="text/javascript">

$(document).ready(jhrqChange);
$(".zjly").change(jhrqChange);

//根据资金来源的选择，更改计划日期的内容。（计划划扣日期，计划转账日期，计划汇款日期）
function jhrqChange(){
	var value = $("select.zjly option:selected").val();
	if(value == ""){
		$("#jhrq label").empty();
		$("#jhrq").prepend("<label>计划划扣日期：</label>");
	}else{
		var checkedOption = "select.zjly option[value='"+value+"']";
		var optionName = $(checkedOption).html();
		if(optionName.length > 2){
			optionName = optionName.substr(2);
		}
		$("#jhrq label").empty();
		var str = "<label>计划"+optionName+"日期：</label>";
		$("#jhrq").prepend(str);
	}
}

</script>