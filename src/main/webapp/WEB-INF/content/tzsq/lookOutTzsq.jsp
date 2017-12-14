<%@page import="cn.com.cucsi.app.entity.xhcf.XhTzsq"%>
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
			<table width="100%">
				<tr>
					<td><label>客户编号：</label> <input type="text" class="textInput"
						name="cjrxx.khbm" value="${xhTzsq.cjrxx.khbm}"
						disabled="disabled" size="20" /> <c:if
							test="${xhTzsq.id == null || xhTzsq.id == ''}">
							<a class="btnLook" href="${ctx }/cjrxx/cjrxxLookUp"
								lookupGroup="cjrxx"><hi:text key="查找带回" /></a>
						</c:if></td>
					<td><label>客户姓名：</label> <input name="cjrxx.cjrxm"
						type="text" size="30" value="${xhTzsq.cjrxx.cjrxm }"
						maxlength="40" disabled="disabled" /></td>
					<td><label>证件号码：</label> <input name="cjrxx.zjhm"
						type="text" size="30" value="${xhTzsq.cjrxx.zjhm }" maxlength="40"
						disabled="disabled" /></td>
				</tr>
			</table>
			<div class="divider"></div>
			<p><label>资金来源:</label>
						<sen:select clazz="zjly combox" name="sqtype" coding="moneyComeType" value="${xhTzsq.sqtype}" title="请选择"/>
			</p>
			<div class="divider"></div>
			<table width="100%" border="0">
				<tr>
					<td><label>出借编号：</label> <input name="tzsqbh" type="text"
						size="30" value="${xhTzsq.tzsqbh}" class="required" maxlength="20"
						disabled="disabled" /></td>
							<td id="jhrq"><label>计划划扣日期：</label> <input name="jhhkrq" type="text"
						size="25" value="${xhTzsq.jhhkrq}" class="required date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
					<td><label>计划出借日期：</label> <input name="jhtzrq" type="text"
						size="25" value="${xhTzsq.jhtzrq}" class="required date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
				</tr>
				<tr>
					<td><label>计划出借金额：</label> <input name="jhtzje" type="text"
						size="30" value="${xhTzsq.jhtzje}" class="required" maxlength="20"
						disabled="disabled" /></td>
					<td><label>出借方式：</label> <select name="tzcp.id"
						class="required combox">
							<option value="" <c:if test="${xhTzsq.tzcp.id==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select></td>
					<%-- <td><label>是否风险金补偿：</label> <input name="sffxjbc" type="text"
						size="30" value="${xhTzsq.sffxjbc}" class="required"
						maxlength="10" disabled="disabled" /></td> --%>
				</tr>
		
				<tr>
					<%-- <td><label>申请日期：</label> <input name="sqrq" type="text"
						size="25" value="${xhTzsq.sqrq}" class="required date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td> --%>
					<td><label>协议版本：</label><%--  <select name="xybb"
						class="required combox" disabled="disabled">
							<option value="" <c:if test="${xhTzsq.xybb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${wtxybbh}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${xhTzsq.xybb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%>
					<sen:select clazz="combox"  coding="wtxybbh" name="xybb" value="${xhTzsq.xybb }" />
					</td>
					
					<td><label>付款方式：</label> <input name="fkfs" type="text"
						size="30" value="${xhTzsq.fkfs}" class="required" maxlength="20"
						disabled="disabled" /></td>
						<td><label>合同编号：</label> <input name="contractNumber"
						type="text" size="30" value="${xhTzsq.contractNumber}" class=" " readonly
						 /></td>
				</tr>
				<tr>
					<td><label>销售折扣率(%)：</label> <input name="xszkly" type="text"
						size="30" value="${xhTzsq.xszkly}" class="required" maxlength="10"
						disabled="disabled" /></td>
					<td><label>销售折扣率有效期限：</label> <input name="xszklyxqx"
						type="text" size="30" value="${xhTzsq.xszklyxqx}" class="required"
						maxlength="10" disabled="disabled" /></td>
						
				
				</tr>
				
			</table>
			<div class="divider"></div>
			<table width="80%" border="1" ><tr><td><dl class="nowrap">
				
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
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80"
						disabled="disabled">${xhTzsq.remark}</textarea>
				</dd>
			</dl>
			
			<c:if test="${xhTzsq.state=='3' }">
				<dl class="nowrap">
					<dt>审核意见：</dt>
					<dd>
						<textarea name="auditIdea" style="width: 93%; height: 80;
							color:red;font-weight:bold">${xhTzsq.auditIdea }</textarea>
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
//$(".zjly").change(jhrqChange);

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