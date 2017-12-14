<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/cjrxx/saveTzsqCjrxx"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="cjrxx.id" value="${cjrxx.id}" />
		<input type="hidden" name="state" id="state" value="0" />
		<div class="pageFormContent" layoutH="56">
			<table width="100%">
				<tr>
					 <td><label>客户编号：</label> <input type="text" class="textInput"
						name="khbm" value="${cjrxx.khbm}" disabled="disabled" /></td>
					<td><label>客户姓名：</label> <input name="cjrxm" type="text"
						size="20" value="${cjrxx.cjrxm }" maxlength="40"
						disabled="disabled" /></td>
					<td><label>证件号码：</label> <input name="zjhm" type="text"
						size="20" value="${cjrxx.zjhm }" maxlength="40"
						disabled="disabled" /></td>
					
				</tr>
			</table>
				<div class="divider"></div>
				<label>资金来源：</label>
				<sen:select clazz="required combox" name="sqtype" coding="moneyComeType" value="0" title="请选择"/>
			<div class="divider"></div>
			<table width="100%">
			<tr><td><label>申请日期：</label> <input name="sqrq" type="text"
						size="20" value="${xhTzsq.sqrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td><td><label>计划划扣日期：</label> <input name="jhhkrq" type="text"
						size="20" value="${xhTzsq.jhhkrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td><td><label>计划出借日期：</label> <input name="jhtzrq" type="text"
						size="20" value="${xhTzsq.jhtzrq}" class="required date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a><span class="info"> (注：出借日期)</span></td>
						</tr>
						<tr>
						<td><label>计划出借金额(元)：</label> <input name="jhtzje" type="text"
						size="20" value="${xhTzsq.jhtzje}" class="required number"  maxlength="20" /><span class="info"> (注：数字,无千分位)</span></td>
						<td><label>资金出借及回收方式：</label><select name="tzcp.id" id="tzcpid"  onchange="onChange()"
						class="required combox">
							<option value="" <c:if test="${xhTzsq.tzcp.id==''}">selected</c:if> >请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if> >${md.tzcpMc}</option>
							</c:forEach>
					</select></td><td><label>付款方式：</label> 
					<sen:select clazz="required combox" name="fkfs" coding="payType" value="${xhTzsq.fkfs}" title="请选择"/>
					</td>
						</tr>
						<tr>
						<td><label>是否风险金补偿：</label> 
						<select name="sffxjbc" class="required combox">
								<option value="否" <c:if test="${xhTzsq.sffxjbc=='否'}">selected</c:if>>否</option>
								<option value="是" <c:if test="${xhTzsq.sffxjbc=='是'}">selected</c:if>>是</option>
						</select>	</td>
							<td><label>协议版本：</label> 
							<sen:select clazz="combox required" name="xybb" coding="wtxybbh" value="${xhTzsq.xybb }" title="请选择"/>
							</td>
								<td><p><label>合同编号：</label> <input name="contractNumber"
						type="text" size="20" value="${xhTzsq.contractNumber}" class="required" 
						 /></p></td>
						</tr>
						
						</table>
			<div class="divider"></div>
					
				
					<p><label>销售折扣率(%)：</label> <input name="xszkly" type="text" id="xszkly"
						size="10" value="${xhTzsq.xszkly}" class="number" maxlength="5" readonly="readonly"/><span class="info"> (注：如50%,只需填50)</span>
					</p>
					<!--  <p><label>销售折扣率有效期限：</label> <input name="xszklyxqx"
						type="text" size="20" value="${xhTzsq.xszklyxqx}" class="required date"
						maxlength="" /><a class="inputDateButton" href="#">选择</a></p>-->
						
						
				
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80">${xhTzsq.remark}</textarea>
				</dd>
			</dl>
			
			
				<div class="divider"></div>
			<table width="60%" border="1" ><tr><td><dl class="nowrap">
				
				<dd>
					
							<p><label><b>出借付款银行账户：</b></label>
							<p><label>开户行：</label>
							<sen:select clazz="combox required" name="tzfkkhh" coding="bank" value="${cjrxx.tzfkkhh }" title="请选择"/>
							</p>
							<p><label>具体支行：</label>
							<input name="tzfkyhmc" type="text" size="40"
						value="${cjrxx.tzfkyhmc}" maxlength="80" /></p>
						
							<p><label>开户姓名：</label>
							<input name="tzfkkhmc" type="text" size="10"
						value="${cjrxx.cjrxm}" maxlength="80" readonly="readonly" /></p>
							<p><label>银行账号：</label>
							<input name="tzfkyhzh" id="tzfkyhzh" type="text"
						size="25" value="${cjrxx.tzfkyhzh}" alt="请输入账户" maxlength="80" /></p>
						
					<p><label>确认账户：</label><input name="tzfkyhzh2" type="text" size="25"
						value="${cjrxx.tzfkyhzh}" alt="请输入账户" maxlength="80"
						equalto="#tzfkyhzh" /></p>
				</dd></dl></td><td>
<dl class="nowrap">

					<dd>
					
							<p><label><b>回收资金银行账户：</b></label>
							<p><label>开户行：</label>
							<sen:select clazz="combox required" name="hszjkhh" coding="bank" value="${cjrxx.hszjkhh }" title="请选择"/>
							</p>
							<p><label>具体支行：</label>
							<input name="hszjyhmc" type="text" size="40"
						value="${cjrxx.hszjyhmc}" maxlength="80" /></p>
						
						
							<p><label>开户姓名：</label>
							<input name="hszjkhmc" type="text" size="10"
						value="${cjrxx.cjrxm}" maxlength="80" readonly="readonly" /></p>
							<p><label>银行账号：</label>
						<input name="hszjyhzh" id="hszjyhzh" type="text"
						size="25" value="${cjrxx.hszjyhzh}" alt="请输入账户" maxlength="80" /></p>
						<p><label>确认账户：</label>
						<input name="hszjyhzh2" type="text"
						size="25" value="${cjrxx.hszjyhzh}" alt="请输入账户" maxlength="80"
						equalto="#hszjyhzh" /></p>
						
					
				</dd></dl></td></tr></table>
			<div class="divider"></div>
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return subState(0);">暂存</button>
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