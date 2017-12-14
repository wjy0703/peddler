<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhTzsq/overTzsqSave"
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
			<table width="100%">
				<tr>
					<td><label>赎回日期：</label> <input name="shrq" type="text"
						size="25" value="${shrq }" class="required date"
						maxlength="20" readonly="readonly" /> <a class="inputDateButton"
						href="#">选择</a></td>
				</tr>
				<tr>
					<td><label>出借编号：</label> <input name="tzsqbh" type="text"
						size="30" value="${xhTzsq.tzsqbh}" class="" maxlength="20"
						disabled="disabled" /></td>
					<td><label>计划出借日期：</label> <input name="jhtzrq" type="text"
						size="25" value="${xhTzsq.jhtzrq}" class=" date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
				</tr>
				<tr>
					<td><label>计划出借金额：</label> <input name="jhtzje" type="text"
						size="30" value="${xhTzsq.jhtzje}" class="" maxlength="20"
						disabled="disabled" /></td>
					<td><label>出借方式：</label> <select name="tzcp.id"
						class=" combox">
							<option value="" <c:if test="${xhTzsq.tzcp.id==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>是否风险金补偿：</label> <input name="sffxjbc" type="text"
						size="30" value="${xhTzsq.sffxjbc}" class=""
						maxlength="10" disabled="disabled" /></td>
					<td><label>计划划扣日期：</label> <input name="jhhkrq" type="text"
						size="25" value="${xhTzsq.jhhkrq}" class=" date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
				</tr>
				<tr>
					<td><label>申请日期：</label> <input name="sqrq" type="text"
						size="25" value="${xhTzsq.sqrq}" class=" date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
					<td><label>协议版本：</label> <%-- <select name="xybb"
						class=" combox" disabled="disabled">
							<option value="" <c:if test="${xhTzsq.xybb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${wtxybbh}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${xhTzsq.xybb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%>
					<sen:select clazz="combox"  coding="wtxybbh" name="xybb" value="${xhTzsq.xybb }" />
					</td>
				</tr>
				<tr>
					<td><label>销售折扣率(%)：</label> <input name="xszkly" type="text"
						size="30" value="${xhTzsq.xszkly}" class="" maxlength="10"
						disabled="disabled" /></td>
					<td><label>销售折扣率有效期限：</label> <input name="xszklyxqx"
						type="text" size="30" value="${xhTzsq.xszklyxqx}" class=""
						maxlength="10" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>付款方式：</label> <input name="fkfs" type="text"
						size="30" value="${xhTzsq.fkfs}" class="" maxlength="20"
						disabled="disabled" /></td>
				</tr>
			</table>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80"
						disabled="disabled">${xhTzsq.remark}</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>出借付款使用的<br>银行账户<font color="red">*</font>：</dt>
				<dd>
					<table width="100%">
						<tr>
							<td><label>开户行：</label></td>
							<td><%-- <select name="tzfkkhh" id="tzfkkhh_tzsq"
								class=" combox" disabled="disabled">
									<option value=""
										<c:if test="${xhTzsq.tzfkkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${xhTzsq.tzfkkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%>
							<sen:select clazz="combox"  coding="bank" name="tzfkkhh" value="${xhTzsq.tzfkkhh }" />
							</td>
							<td><label>具体支行：</label></td>
							<td><input name="cjrxx.tzfkyhmc" type="text" size="40"
								value="${xhTzsq.tzfkyhmc}" maxlength="80" disabled="disabled" /></td>
						</tr>
						<tr>
							<td><label>开户姓名：</label></td>
							<td><input name="cjrxx.tzfkkhmc" type="text" size="10"
								value="${xhTzsq.tzfkkhmc}" maxlength="80" disabled="disabled" /></td>
							<td><label>账户：</label></td>
							<td><input name="cjrxx.tzfkyhzh" id="tzfkyhzh"
								type="text" size="25" value="${xhTzsq.tzfkyhzh}" maxlength="80"
								disabled="disabled" /></td>
						</tr>
					</table>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>回收资金使用的<br>银行账户<font color="red">*</font>：</dt>
				<dd>
					<table width="100%">
						<tr>
							<td><label>开户行：</label></td>
							<td><%-- <select name="hszjkhh" id="hszjkhh_tzsq"
								class=" combox" disabled="disabled">
									<option value=""
										<c:if test="${xhTzsq.hszjkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${xhTzsq.hszjkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%>
							<sen:select clazz="combox"  coding="bank" name="hszjkhh" value="${xhTzsq.hszjkhh }" />
							</td>
							<td><label>具体支行：</label></td>
							<td><input name="cjrxx.hszjyhmc" type="text" size="40"
								value="${xhTzsq.hszjyhmc}" maxlength="80" disabled="disabled" /></td>
						</tr>
						<tr>
							<td><label>开户姓名：</label></td>
							<td><input name="cjrxx.hszjkhmc" type="text" size="10"
								value="${xhTzsq.hszjkhmc}" maxlength="80" disabled="disabled" /></td>
							<td><label>账户：</label></td>
							<td><input name="cjrxx.hszjyhzh" id="hszjyhzh"
								type="text" size="25" value="${xhTzsq.hszjyhzh}" maxlength="80"
								disabled="disabled" /></td>
						</tr>
					</table>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
			<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" >赎回</button>
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