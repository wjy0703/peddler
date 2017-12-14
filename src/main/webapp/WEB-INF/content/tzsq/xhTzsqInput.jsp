<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhTzsq/saveXhTzsq"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhTzsq.id}" /> <input
			type="hidden" name="cjrxx.id" value="${xhTzsq.cjrxx.id}" /> <input
			type="hidden" name="state" id="state" value="0" />
		<div class="pageFormContent" layoutH="56">
			<table width="100%">
				<tr>
					<td><label>客户编号：</label> <input type="text" 
						name="cjrxx.khbm" value="${xhTzsq.cjrxx.khbm}"
						readonly="readonly" disabled="disabled" size="20" /><c:if
							test="${xhTzsq.id == null || xhTzsq.id == ''}">
							<a class="btnLook" href="${ctx }/cjrxx/cjrxxLookUp"
								lookupGroup="cjrxx"><hi:text key="查找带回" /></a>
						</c:if></td>
					<td><label>客户姓名：</label> <input name="cjrxx.cjrxm"
						type="text" size="30" value="${xhTzsq.cjrxx.cjrxm }"
						maxlength="40" readonly="readonly" class="required"/>
						</td>
					<td><label>证件号码：</label> <input name="cjrxx.zjhm"
						type="text" size="30" value="${xhTzsq.cjrxx.zjhm }" maxlength="40"
						disabled="disabled"/></td>
				</tr>
			</table>
			<div class="divider"></div>
			<p><label>资金来源:</label>
			<sen:select clazz="zjly required combox" name="sqtype" coding="moneyComeType" value="${xhTzsq.sqtype}" title="请选择"/>
					</p>
			<div class="divider"></div>
					<p><label>申请日期：</label> <input name="sqrq" type="text"
						size="20" value="${xhTzsq.sqrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></p>
					
					<p id="jhrq"><label>计划划扣(转帐)日期：</label> <input name="jhhkrq" type="text"
						size="20" value="${xhTzsq.jhhkrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></p>
				
				
					<p><label>计划出借日期：</label> <input name="jhtzrq" type="text"
						size="20" value="${xhTzsq.jhtzrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a><span class="info"> (注：出借日期)</span></p>
					<p><label>计划出借金额(元)：</label> <input name="jhtzje" type="text"
						size="20" value="${xhTzsq.jhtzje}" class="required number" maxlength="20" /><span class="info"> (注：数字,无千分位)</span>
					</p>
					
				
					<p><label>资金出借及回收方式：</label> 
					 <select name="tzcp.id" id="tzcpid" onchange="onChange()"
						class="required combox">
							<option value="" <c:if test="${xhTzsq.tzcp.id==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select> 
					
					
					</p>
				
					
					<p><label>付款方式：</label>
						<%-- <select name="fkfs" class="required combox">
							<option value="" <c:if test="${xhTzsq.fkfs==''}">selected</c:if>>请选择</option>
							<c:forEach items="${fkfs}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${xhTzsq.fkfs==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
						</select> --%>
						<sen:select clazz="combox"  coding="payType" name="fkfs" value="${xhTzsq.fkfs }" />
					</p>
					
					
				
				
					<%-- <p><label>是否风险金补偿：</label>
						<select name="sffxjbc" class="required combox">
								<option value="否" <c:if test="${xhTzsq.sffxjbc=='否'}">selected</c:if>>否</option>
								<option value="是" <c:if test="${xhTzsq.sffxjbc=='是'}">selected</c:if>>是</option>
						</select>	
					</p> --%>
					<input name="sffxjbc" value="是" type="hidden"/>
					
					<p><label>协议版本：</label><%--  <select name="xybb"
						class="required combox">
							<option value="" <c:if test="${xhTzsq.xybb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${wtxybbh}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${xhTzsq.xybb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach></select> --%>
					<sen:select clazz="combox"  coding="wtxybbh" name="xybb" value="${xhTzsq.xybb }" /></p>
				
				
					<p><label>销售折扣率(%)：</label> <input name="xszkly" type="text" id="xszkly"
						size="10" readonly="readonly" value="${xhTzsq.xszkly}" class=" number" maxlength="5" /><span class="info"> (注：如50%,只需填50)</span>
					</p>
					<!--  <p><label>销售折扣率有效期限：</label> <input name="xszklyxqx"
						type="text" size="20" value="${xhTzsq.xszklyxqx}" class="required date"
						 /><a class="inputDateButton" href="#">选择</a></p>
						 -->
						 	<p><label>合同编号：</label> <input name="contractNumber"
						type="text" size="20" value="${xhTzsq.contractNumber}" class="required "
						 /></p>
				
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80">${xhTzsq.remark}</textarea>
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>出借付款使用的<br>银行账户<font color="red">*</font>：</dt>
				<dd>
				<table>
		<tr>
							<td><label>开户行：</label></td>
					<td>
							<%-- <select name="tzfkkhh" id="tzfkkhh_tzsq"
								class="required combox">
									<option value=""
										<c:if test="${xhTzsq.tzfkkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${xhTzsq.tzfkkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%>
							<sen:select clazz="combox required"  coding="bank" name="tzfkkhh" id="tzfkkhh_tzsq" value="${xhTzsq.tzfkkhh }" />
							</td>
							<td ><label>具体支行：</label></td>
					<td colspan="2">
							<input name="cjrxx.tzfkyhmc" type="text"
								size="40" value="${xhTzsq.tzfkyhmc}" maxlength="80" /></td>
						</tr>
				<tr>
							<td><label>开户姓名：</label></td>
					<td>
							<input name="cjrxx.tzfkkhmc" type="text" size="10"
								value="${xhTzsq.tzfkkhmc}" maxlength="80" readonly="readonly" /></td>
							<td><label>账户：</label></td>
					<td>
							<input name="cjrxx.tzfkyhzh" id="tzfkyhzh"
								type="text" size="25" value="${xhTzsq.tzfkyhzh}" maxlength="80" /></td>
							<td><label>确认账户：</label></td>
					<td>
							<input name="cjrxx.tzfkyhzh2" type="text" size="25"
								value="${xhTzsq.tzfkyhzh}" maxlength="80" equalto="#tzfkyhzh" /></td>
							</tr>
			</table>
					
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>回收资金使用的<br>银行账户<font color="red">*</font>：</dt>
				<dd>
				<table>
		<tr>
							<td><label>开户行：</label></td>
					<td>
							<%-- <select name="hszjkhh" id="hszjkhh_tzsq"
								class="required combox">
									<option value=""
										<c:if test="${xhTzsq.hszjkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${xhTzsq.hszjkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%>
							<sen:select clazz="combox required"  coding="bank" name="hszjkhh" id="hszjkhh_tzsq" value="${xhTzsq.hszjkhh }" />
							</td>
							<td><label>具体支行：</label></td>
					<td colspan="2">
							<input name="cjrxx.hszjyhmc" type="text"
								size="40" value="${xhTzsq.hszjyhmc}" maxlength="80" /></td>
						</tr>
				<tr>
							<td><label>开户姓名：</label></td>
					<td>
							<input name="cjrxx.hszjkhmc" type="text" size="10"
								value="${xhTzsq.hszjkhmc}" maxlength="80" readonly="readonly" /></td>
							<td><label>账户：</label></td>
					<td>
							<input name="cjrxx.hszjyhzh" id="hszjyhzh"
								type="text" size="25" value="${xhTzsq.hszjyhzh}" maxlength="80" /></td>
							<td><label>确认账户：</label></td>
					<td>
							<input name="cjrxx.hszjyhzh2" type="text" size="25"
								value="${xhTzsq.hszjyhzh}" maxlength="80" equalto="#hszjyhzh" /></td>
								</tr>
			</table>
				</dd>
			</dl>
			<c:if test="${xhTzsq.state=='3' }">
			<div class="divider"></div>
				<dl class="nowrap">
					<dt>审核意见：</dt>
					<dd>
						<textarea name="auditIdea" style="width: 93%; height: 80;
							color:red;font-weight:bold" >${xhTzsq.auditIdea }</textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>审核结果：</dt>
					<dd>
						<input type="radio" name="state" value="2" disabled="disabled" />通过
						<input type="radio" name="state" value="3" checked="checked"
							disabled="disabled" />不通过
					</dd>
				</dl>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return subState(0);">保存</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return subState(1);">提交</button>
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
<script>
function onChange(){
	var $box = navTab.getCurrentPanel();
    var $tzcp = $('#tzcpid', $box);
    var $xszkly = $('#xszkly',$box);
    if($tzcp.val()==90){
    	$xszkly.val(10);
    }else if($tzcp.val()==83){
    	$xszkly.val(25);
    }else if($tzcp.val()==84){
    	$xszkly.val(50);
    }else{
    	$xszkly.val("100");
    }
    
}

</script>