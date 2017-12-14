<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
<script type="text/javascript">
//账单收取方式的js
$(function(){
	$('#sellbill').change(function(){
		var selectValue = $('#sellbill option:selected').val();
		if(selectValue == "电子邮件" || selectValue == "二者都收"){
			$("#x").before('<font id="xID" color="red">*</font>');
			$("#email").after('<span id="eID" class="info"> (必填)</span>');
		}else{
			$("#xID").remove();
			$("#eID").remove();
		}
	});
});

	/* function billChange(){
		var selectValue = $("#sellbill").val();
		if(selectValue == "电子邮件" || selectValue == "二者都收"){
			$("#x").before('<font id="xID" color="red">*</font>');
			$("#email").after('<span id="eID" class="info"> (必填)</span>');
		}else{
			$("#xID").remove();
			$("#eID").remove();
		}
	} */
	function subState(val){
		var selectValue = $("#sellbill").val();
		if(selectValue == "电子邮件" || selectValue == "二者都收"){
			var email = $("#email").val();
			if(email == ""){
				alertMsg.error('电子邮箱必填');
				return false;
			}
		}
		$("#state").val(val);
		return true;
	}
</script>
	<form method="post" name="saveOpenAccForm"
		action="${ctx}/cjrxx/saveOpenAccCjrxx"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" /> <input
			type="hidden" name="state" id="state" value="0" />
		<div class="pageFormContent" layoutH="65">
			<div class="panel"><h1 width="50%">客户基本信息</h1></div>
		<table width="100%">
		<tr>
					<td><label>客户姓名：</label> <input name="cjrxm" type="text"
						size="20" value="${cjrxx.cjrxm }" class="" maxlength="10" disabled="disabled"><span class="info"> (必填)</span>
					</td>
					<td><label>英文名称：</label> <input name="ywmc" type="text"
						size="20" value="${cjrxx.ywmc }" maxlength="10" class="lettersonly" disabled="disabled"/></td>
				
					<td><label>性别<font color="red">*</font>：</label> 
					<sen:select clazz="combox required" name="cjrxb" id="cjrxb" coding="genders" value="${cjrxx.cjrxb }" title="请选择"/><span class="info"> (必填)</span></td>
					</tr>
			<tr>
					<td><label>出生日期：</label> <input type="text" name="csrq"
						class=" date" format="yyyy-MM-dd" yearstart="-80"
						 value="${cjrxx.csrq }" size="17" readonly="true" disabled="disabled"/>
						<a class="inputDateButton" href="javascript:;">选择</a> <span class="info"> (必填)</span>
						</td>
				
					<td><label>首次联系时间：</label> <input name="optionTime"
						type="text" size="17" value="${cjrxx.optionTime }" class="date"
						dateFmt="yyyy-MM-dd HH:mm:ss"
						 value="${cjrxx.csrq }" size="20" readonly="true" disabled="disabled"/>
						<a class="inputDateButton" href="javascript:;">选择</a> 
						</td>
					<td><label>婚姻状况<font color="red">*</font>：</label> 
					<sen:select clazz="combox required" name="hyzk" coding="marriageType" value="${cjrxx.hyzk }" title="请选择"/><span class="info"> (必填)</span></td>
				</tr>
			<tr>
					<td><label>证件类型<font color="red">*</font>：</label> 
					<sen:select clazz="combox required" name="zjlx" coding="cardType" id="zjlx" value="${cjrxx.zjlx }"/>
					<span class="info"> (必填)</span></td>
					<td><label>证件号码：</label> <input name="zjhm" type="text"
						size="20" value="${cjrxx.zjhm }" class=" alphanumeric"
						maxlength="40"
						remote="${ctx }/cjrxx/chkValue?oldValue=${cjrxx.zjhm }&propertyName=zjhm" disabled="disabled"/><span class="info"> (必填)</span>
					</td>
				
					<td><label>签发日期：</label> <input type="text" name="zjqfrq"
						class="date" pattern="yyyy-MM-dd" value="${cjrxx.zjqfrq }"
						size="17"  yearstart="-80" disabled="disabled"/> <a class="inputDateButton" href="#">选择</a>
						<input name="changqi" value="1" type="checkbox" <c:if test="${cjrxx.sfcq=='1'}">checked</c:if> disabled="disabled">长期
						</td>
					</tr>
			<tr>
					<td><label>失效日期：</label> <input type="text" name="qjsxrq"
						class="date" pattern="yyyy-MM-dd" value="${cjrxx.qjsxrq }"
						size="17" disabled="disabled"/> <a class="inputDateButton" href="#">选择</a></td>
				
					<td><label>发证机关：</label> <input name="fzjg" type="text"
						size="20" value="${cjrxx.fzjg }" maxlength="40" disabled="disabled"/></td>
					<td><label>学历：</label> <input name="cjrxl" type="text"
						size="20" value="${cjrxx.cjrxl }" maxlength="20" disabled="disabled"/></td>
				</tr>
			<tr>
					<td><label>职业<font color="red">*</font>：</label> 
					<sen:select clazz="combox required" name="cjrzy" id="cjrzy" coding="occupationType" value="${cjrxx.cjrzy }" title="请选择"/><span class="info"> (必填)</span></td>
					<td><label>行业：</label> <input name="hy" type="text" disabled="disabled" size="20"
						value="${cjrxx.hy }" maxlength="40" /></td>
				
					<td><label>单位名称：</label> <input name="gzdwmc" type="text" disabled="disabled"
						size="30" value="${cjrxx.gzdwmc }" maxlength="100" /></td>
						</tr>
			<tr>
					<td><label>工作年限：</label> <input name="gznx" type="text" disabled="disabled"
						size="10" value="${cjrxx.gznx }" maxlength="3" class="number"/></td>
				
					<td><label>单位规模：</label> 
					<sen:select clazz="combox" name="cjrdwgm" coding="officeModle" value="${cjrxx.cjrdwgm }" title="请选择"/></td>
					<td><label>职务：</label> <input name="zw" type="text" disabled="disabled" size="20"
						value="${cjrxx.zw }" maxlength="20" /></td>
				</tr>
			<tr>
					<td><label>移动电话：</label> <input name="yddh" type="text" disabled="disabled"
						size="20" value="${cjrxx.yddh }" class="" maxlength="20" /><span class="info"> (必填)</span>
					</td>
					<td><label>固定电话：</label> <input name="gddh" type="text" disabled="disabled"
						size="20" value="${cjrxx.gddh }" class="phone" maxlength="20" /></td>
				
					<td><label>电子邮箱<b id="x"></b>：</label> <input id="email" name="dzyx" type="text" 
						size="20" value="${cjrxx.dzyx }" class="email" maxlength="80" /></td>
						</tr>
			<tr>
					<td><label>客户传真：</label> <input name="cjrfx" type="text"
						size="20" value="${cjrxx.cjrfx }" class="phone" maxlength="20" disabled="disabled"/></td>
				
					<td><label>客户来源：</label> 
					<sen:select clazz="combox" name="khly" coding="personFrom" value="${cjrxx.khly }" title="请选择"/>
					</td>
					<td><label>母亲姓氏：</label> <input name="mqxm" type="text"
						size="20" value="${cjrxx.mqxm}" maxlength="80" class="required"/><span class="info"> (必填)</span></td>
				</tr>
			
			</table>	
			
			
			<div class="panel"><h1>客户通讯地址</h1><span class="info">(注：请认真填写、核对通信地址，该地址将用于客户账单邮寄)</span></div>
			
			
			<dl class="nowrap">
				<dt>详细通信地址<font color="red"></font>：</dt>
				<dd>
				<sen:address names="province,city,area" 
					titles="所有省份,所有城市,所有区县" values="${cjrxx.province},${cjrxx.city},${cjrxx.area}"/>
				<span class="info"> (必填)</span><label>详细通信地址</label><input name="txdz" type="text" size="80" value="${cjrxx.txdz }"
						maxlength="60" class="" disabled="disabled"/><span class="info"> (必填)</span></dd></dl>
				<dl class="nowrap">
				<dt>邮政编码<font color="red">*</font>：</dt>
				<dd><input name="yb" type="text" size="10"
						value="${cjrxx.yb }" class=" number" maxlength="6" minlength="6" disabled="disabled"/><span class="info"> (必填)</span></dd></dl>
			
			<div class="divider"></div>
			
			<div class="panel"><h1>客户开发及管辖信息</h1></div>
		
			<table><tr><td><label>团队经理<font color="red">*</font>：</label> <input type="hidden"
						name="employeeCca.id" value="${cjrxx.employeeCca.id}" /> <input
						type="text" id="empname" class=" textInput"
						name="employeeCca.name" value="${cjrxx.employeeCca.name }"
						suggestFields="name,deptname"
						lookupGroup="employeeCca" readonly disabled="disabled"/> <a class="btnLook"
						href="${ctx }/baseinfo/emplookup?code=0002&lookId=${employee.organi.id}" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a>
								<span class="info"> (必填)</span></td>
					<td><label>理财经理<font color="red">*</font>：</label> <input type="hidden"
						name="employeeCrm.id" value="${cjrxx.employeeCrm.id}" /> <input
						type="text" id="empname" class=" textInput"
						name="employeeCrm.name" value="${cjrxx.employeeCrm.name }"
						suggestFields="name,deptname"
						lookupGroup="employeeCrm" readonly alt="" disabled="disabled"/> <a class="btnLook"
						href="${ctx }/baseinfo/emplookup?code=0001&lookId=${employee.organi.id}" lookupGroup="employeeCrm"><hi:text
								key="查找带回" /></a><span class="info"> (必填)</span></td>
									
						<td><label>开发团队名称：</label> <input name="kftd" type="text"
						size="20" value="${cjrxx.kftd }" class="" maxlength="80" disabled="disabled"/>
					<span class="info"> (必填)</span></td></tr></table>
					<dl class="nowrap">
				<dt>管辖城市<font color="red">*</font>：</dt>
				<dd>
				<sen:address required="true" names="crmprovince,crmcity" 
					titles="所有省份,所有城市" values="${cjrxx.crmprovince},${cjrxx.crmcity}"/>
					<span class="info"> (必填)</span>
				</dd>
			</dl>
				
				<div class="divider"></div>
			<div class="panel"><h1 width="50%">客户银行账户信息</h1></div>
			<p><label>账单收取方式：</label> 
			<sen:select clazz="combox required" id="sellbill" name="zqjsfs" coding="sendModle" value="${cjrxx.zqjsfs }" title="请选择"
			/>
			</p>
			<dl class="nowrap">
				<dt>出借付款银行账户*：</dt>
				<dd>
					
							<p><label>开户行：</label>
							<sen:select clazz="combox required" name="tzfkkhh" coding="bank" value="${cjrxx.tzfkkhh }" title="请选择"/>
							</p>
							<p><label>卡或折：</label>
							<sen:select clazz="combox required" name="tzfkkhz" coding="cardOrModle" value="${cjrxx.tzfkkhz }" title="请选择"/>
							</p>
							<p><label>具体支行：</label>
							<input name="tzfkyhmc" type="text" size="30"
								value="${cjrxx.tzfkyhmc}" maxlength="80" /></p>
						
						<div class="divider"></div>
							<p><label>开户姓名：</label>
							<input name="tzfkkhmc" type="hidden" size="10"
								value="${cjrxx.cjrxm}" maxlength="80"/>
							<input name="" type="text" size="30"
								value="${cjrxx.cjrxm }" maxlength="80" readonly="readonly" /></p>
							<p><label>账户：</label>
							<input name="tzfkyhzh" id="tzfkyhzh" type="text"
								size="30" value="${cjrxx.tzfkyhzh}" alt="请输入账户" maxlength="80" /></p>
							<p><label>确认账户：</label>
							<input name="tzfkyhzh2" type="text" size="30"
								value="${cjrxx.tzfkyhzh}" alt="请输入账户" maxlength="80"
								equalto="#tzfkyhzh" /></p>
				</dd>
			</dl>
			<dl class="nowrap">
			<dt>回收账户是否同上：</dt>
				<dd>
					<input type="radio" name="isGouTong"
						value="0" onClick="checkGouTong(this.value);">相同&nbsp;
					<input type="radio" name="isGouTong" value="1"
						onClick="checkGouTong(this.value);" checked>不相同&nbsp;
				</dd>
			</dl>	
			<div id="toGouTongDiv" style="display: block">
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>回收资金银行账户*：</dt>
				<dd>
					
							<p><label>开户行：</label>
							<sen:select clazz="combox" name="hszjkhh" coding="bank" value="${cjrxx.hszjkhh }" title="请选择"/>
							</p>
							<p><label>卡或折：</label>
							<sen:select clazz="combox" name="hszjkhz" coding="cardOrModle" value="${cjrxx.hszjkhz }" title="请选择"/>
							</p>
							<p><label>具体支行：</label>
							<input name="hszjyhmc" type="text" size="30"
								value="${cjrxx.hszjyhmc}" maxlength="80" /></p>
						<div class="divider"></div>
							<p><label>开户姓名：</label>
							<input name="hszjkhmc" type="hidden" size="30"
								value="${cjrxx.cjrxm}" maxlength="80"/>
							<input name="" type="text" size="30"
								value="${cjrxx.cjrxm}" maxlength="80" readonly="readonly" /></p>
							<p><label>账户：</label>
							<input name="hszjyhzh" id="hszjyhzh" type="text"
								size="30" value="${cjrxx.hszjyhzh}" alt="请输入账户" maxlength="80" /></p>
							<p><label>确认账户：</label>
							<input name="hszjyhzh2" type="text" size="30"
								value="${cjrxx.hszjyhzh}" alt="请输入账户" maxlength="80"
								equalto="#hszjyhzh" /></p>
				</dd>
			</dl>
			</div>
				<div class="divider"></div>
					<p><label>是否签订委托协议：</label> 
					<sen:select clazz="combox required" name="sfqdwtxy" id="sfqdwtxy" coding="yesOrNo" value="${cjrxx.sfqdwtxy}" title="请选择"/>
					<%-- <select name="sfqdwtxy"
						class="required combox">
							<option value=""
								<c:if test="${cjrxx.sfqdwtxy==''}">selected</c:if>>请选择</option>
							<option value="是"
								<c:if test="${cjrxx.sfqdwtxy=='是'}">selected</c:if>>是</option>
							<option value="否"
								<c:if test="${cjrxx.sfqdwtxy=='否'}">selected</c:if>>否</option>
					</select> --%></p>
					<p><label>协议签订日期：</label> <input name="qdxyrq" type="text"
						size="30" value="${cjrxx.qdxyrq}" class="required date"
						pattern="yyyy-MM-dd" maxlength="20" /></p>
				
				<div class="divider"></div>
					<p><label>委托协议种类：</label> 
					<sen:select clazz="combox required" name="wtxyzl" coding="wtxyzl" value="${cjrxx.wtxyzl }" title="请选择"/>
					</p>
					<p><label>委托协议版本号：</label> 
					<sen:select clazz="combox required" name="wtxybbh" coding="wtxybbh" value="${cjrxx.wtxybbh }" title="请选择"/>
					</p>
				
			
			<c:if test="${cjrxx.state=='3' }">
			<div class="divider"></div>
				<dl class="nowrap">
					<dt>审批意见：</dt>
					<dd>
						<textarea name="auditIdea" style="width: 93%; height: 80"
							disabled="disabled">${cjrxx.auditIdea }</textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>审批结果：</dt>
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
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return subState(8);">暂存</button>
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
</div></div>
