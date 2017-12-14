<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhTzsq/saveTzsqHuakou"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhTzsq.id}" /> <input
			type="hidden" name="cjrxx.id" value="${xhTzsq.cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
					<p><label>出借编号：</label> <input name="bianma" id="bianma" type="text"
						size="30" value="${xhTzsq.tzsqbh}"  maxlength="20" disabled="disabled"/></p>
					<p><label>客户编码：</label> <input type="text" 
						name="khbmbianma" id="khbmbianma" value="${xhTzsq.cjrxx.khbm}"
						size="20" maxlength="20" disabled="disabled"/>  </p>
						
					<p><label>客户姓名：</label> <input name="cjrxx.cjrxm"
						type="text" size="30" value="${xhTzsq.cjrxx.cjrxm }"
						maxlength="40" disabled="disabled" /></p>
					<p><label>证件号码：</label> <input name="cjrxx.zjhm"
						type="text" size="30" value="${xhTzsq.cjrxx.zjhm }" maxlength="40"
						disabled="disabled" /></p>
			<div class="divider"></div>
					<p><label>出借日期：</label> <input name="jhtzrq" type="text"
						size="25" value="${xhTzsq.jhtzrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton"
						href="#">选择</a></p>
					<p><label>划扣日期：</label> <input name="jhhkrq" type="text"
						size="25" value="${xhTzsq.jhhkrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton"
						href="#">选择</a></p>
						
				
					<p><label>申请日期：</label> <input name="sqrq" type="text"
						size="25" value="${xhTzsq.sqrq}" class=" date"
						maxlength="20" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></p>
					<p><label>出借金额：</label> <input name="jhtzje" type="text"
						size="30" value="${xhTzsq.jhtzje}"  maxlength="20"
						disabled="disabled" /></p>
						
					<p><label>出借方式：</label> <select name="tzcp.id"
						class=" combox" disabled="disabled">
							<option value="" <c:if test="${xhTzsq.tzcp.id==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select></p>
					<p><label>付款方式：</label><%--  <select name="fkfs" class="combox" disabled="disabled">
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
						maxlength="10" disabled="disabled" /></p>
					<p><label>协议版本：</label><%--  <select name="xybb"
						class=" combox" disabled="disabled">
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
						disabled="disabled" /></p>
					<p><label>销售折扣率有效期限：</label> <input name="xszklyxqx"
						type="text" size="25" value="${xhTzsq.xszklyxqx}" 
						maxlength="10" disabled="disabled" /><a class="inputDateButton"
						href="#">选择</a></p>
				
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>划扣结果：</dt>
				<dd>
					<input type="radio" name="state" id="state" value="2" checked="checked" />划扣成功
					<input type="radio" name="state" id="state" value="10" />划扣失败
				</dd>
			</dl>
			
			<div class="divider"></div>
<table width="80%" border="1"><tr><td><dl class="nowrap">
				
				<dd>
					
							<p><label><b>出借付款银行账户：</b></label>
							<p><label>开户行：</label>
							<%-- <select name="tzfkkhh" id="tzfkkhh_tzsq"
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
							</p>
							<p><label>具体支行：</label>
							<input name="cjrxx.tzfkyhmc" type="text" size="40"
								value="${xhTzsq.tzfkyhmc}" maxlength="100" disabled="disabled" /></p>
						
							<p><label>开户姓名：</label>
							<input name="cjrxx.tzfkkhmc" type="text" size="40"
								value="${xhTzsq.tzfkkhmc}" maxlength="80" disabled="disabled" /></p>
							<p><label>银行账号：</label>
							<input name="cjrxx.tzfkyhzh" id="tzfkyhzh"
								type="text" size="40" value="${xhTzsq.tzfkyhzh}" maxlength="80"
								disabled="disabled" /></p>
						
					
				</dd></dl></td><td>
<dl class="nowrap">

					<dd>
					
							<p><label><b>回收资金银行账户：</b></label>
							<p><label>具体支行：</label>
							<%-- <select name="hszjkhh" id="hszjkhh_tzsq"
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
							</p>
							<p><label>具体支行：</label>
							<input name="cjrxx.hszjyhmc" type="text" size="40"
								value="${xhTzsq.hszjyhmc}" maxlength="100" disabled="disabled" /></p>
						
						
							<p><label>开户姓名：</label>
							<input name="cjrxx.hszjkhmc" type="text" size="40"
								value="${xhTzsq.hszjkhmc}" maxlength="80" disabled="disabled" /></p>
							<p><label>银行账号：</label>
							<input name="cjrxx.hszjyhzh" id="hszjyhzh"
								type="text" size="40" value="${xhTzsq.hszjyhzh}" maxlength="80"
								disabled="disabled" /></p>
						
					
				</dd></dl></td></tr></table>
			
				
			
			
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 60"
						disabled="disabled">${xhTzsq.remark}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
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