<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/themes/js/checkValue.js"></script>
<div class="panel">
<div class="pageContent">
<script type="text/javascript">
$("#wjbh").hide();
$("#wjbhP").hide();
if($("#khly").val()=='客户服务节'){
	$("#wjbhP").show();
	$("#wjbh").show();
	$("#wjbh").attr("class","required");
}
$("#khly").change(function(){
	var khly = $(this).val();
	if(khly == '客户服务节'){
		$("#wjbhP").show();
		$("#wjbh").show();
		$("#wjbh").attr("class","required");
	}else{
		$("#wjbhP").hide();
		$("#wjbh").hide();
		$("#wjbh").removeAttr("class");
	}
});
</script>
	<form method="post" action="${ctx}/cjrxx/saveTzzx"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户姓名：</label> <input name="cjrxm" type="text" size="20"
					value="${cjrxx.cjrxm }" class="required" maxlength="32" />
			</p>
			<p>
				<label>证件类型：</label> 
				<sen:select clazz="combox" name="zjlx" coding="cardType" id="zjlx" value="${cjrxx.zjlx }"/>
				<%-- <sen:select clazz="combox " name="zjlx" coding="cardType" id="zjlx" value="${cjrxx.zjlx }"/> --%>
			</p>
			<p>
				<label>证件号码：</label> <input name="zjhm" id="zjhm" type="text" size="30"
					value="${cjrxx.zjhm }" class="alphanumeric" maxlength="40" 
					onblur="vProblemName('${cjrxx.zjhm }','zjhm','证件号码');" />
			</p>
			<p>
				<label>性别：</label> 
				<sen:select clazz="combox required" name="cjrxb" id="cjrxb" coding="genders" value="${cjrxx.cjrxb }" title="请选择"/>
				 
				 <%-- <select name="cjrxb" id="cjrxb" class="combox">
					<option value="" <c:if test="${cjrxx.cjrxb==''}">selected</c:if>>请选择

</option>
					<c:forEach items="${xb}" var="md" varStatus="st">
						<option value="${md.value }"
							<c:if test="${cjrxx.cjrxb==md.value}">selected</c:if>>${md.name
							}</option>
					</c:forEach>
				</select> --%>
			</p>
			<p>
				<label>移动电话：</label> <input name="yddh" id="yddh" type="text" size="30"
					value="${cjrxx.yddh }" class="required phone" maxlength="11" minlength="11"
					onblur="vProblemName('${cjrxx.yddh }','yddh','移动电话');" />
			</p>
			<p>
				<label>固定电话：</label> <input name="gddh" type="text" size="30"
					value="${cjrxx.gddh }" maxlength="40" class="phone" />
			</p>
			<p>
				<label>邮编：</label> <input name="yb" type="text" size="30"
					value="${cjrxx.yb }" maxlength="40" class="number" maxlength="6" 

minlength="6"/>
			</p>
			<p>
				<label>电子邮箱：</label> <input name="dzyx" type="text" size="30"
					value="${cjrxx.dzyx }" maxlength="80" class="email"/>
			</p>
			<p>
				<label>行业：</label> <input name="hy" type="text" size="30"
					value="${cjrxx.hy }" maxlength="40" />
			</p>
			<p>
				<label>工作单位：</label> <input name="gzdwmc" type="text" size="30"
					value="${cjrxx.gzdwmc }" maxlength="100" />
			</p>
			<p>
				<label>职务：</label> <input name="zw" type="text" size="30"
					value="${cjrxx.zw }" maxlength="40" />
			</p>
			<p>
				<label>客户传真：</label> <input name="cjrfx" type="text" size="30"
					value="${cjrxx.cjrfx }" maxlength="20" class="phone"/>
			</p>
			<!-- 
			<p>
				<label>接待人：</label> <input type="hidden" name="employeeCrm.id"
					value="${cjrxx.employeeCrm.id}" /> <input type="text" id="empname"
					class="textInput" name="employeeCrm.name"
					value="${cjrxx.employeeCrm.name }" suggestFields="name,deptname"
					lookupGroup="employeeCrm" /> <a class="btnLook"
					href="${ctx }/baseinfo/emplookup?code=0001&lookId=${employee.organi.id}" 

lookupGroup="employeeCrm"><hi:text
						key="查找带回" /></a>
			</p>
			 -->
			<p>
				<label>客户意向：</label> <select name="cjryx" class="combox">
					<option value="" <c:if test="${cjrxx.cjryx==''}">selected</c:if>>请选择

</option>
					<c:forEach items="${tzcp}" var="md" varStatus="st">
						<option value="${md.id }"
							<c:if 

test="${cjrxx.cjryx==md.id}">selected</c:if>>${md.tzcpMc}</option>
					</c:forEach>
				</select>
			</p>
			<p><label>客户来源：</label>
				<sen:select clazz="combox required" name="khly" id="khly" coding="personFrom" value="${cjrxx.khly}" title="请选择"/>
			 <%-- <select id="khly" name="khly" class="combox required">
								<option value="" <c:if 

test="${cjrxx.khly==''}">selected</c:if>>请选择</option>
								<c:forEach items="${khly}" var="md" varStatus="st">
									<option value="${md.value }"
										<c:if 

test="${cjrxx.khly==md.value}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select> --%>
						
						
						</p>
							
			<p>
				<label>首次联系时间：</label> <input name="optionTime" type="text"
					size="25" value="${cjrxx.optionTime }" class="date"
					dateFmt="yyyy-MM-dd HH:mm:ss" maxlength="20" readonly="true" />
					<a class="inputDateButton" href="javascript:;">选择</a> 
			</p>
			<p><label id="wjbhP">问卷编号：</label>
						<input id="wjbh" type="text" name="wjbh" value="${cjrxx.wjbh }" /></p>
			<dl class="nowrap">
				<dt>通讯地址：</dt>
				
				<dd>
				<sen:address required="true"  names="province,city,area" 
					titles="所有省份,所有城市,所有区县" values="${cjrxx.province},${cjrxx.city},${cjrxx.area}"/></dd>
					</dl>
					<dl class="nowrap">
					<dt>&nbsp;</dt>
					<dd>
					 <input name="txdz" type="text"
							size="70" value="${cjrxx.txdz }" maxlength="60" />
							<font color="red">(注:不需要重复录入省市区信息)</font>
				</dd>
			</dl>
					<%-- <select class="combox required" name="province" ref="combox_city"
						refUrl="${ctx}/cjrxx/getCity?code={value}" >
						<option value=""
							<c:if test="${cjrxx.province==''}">selected</c:if>>所有省市

</option>
						<c:forEach items="${province}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if 

test="${cjrxx.province==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <select class="combox required" name="city" id="combox_city"
						ref="combox_area" refUrl="${ctx}/cjrxx/getArea?code={value}" >
						<option value="" <c:if test="${cjrxx.city==''}">selected</c:if>>所有城

市</option>
						<c:forEach items="${city}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if 

test="${cjrxx.city==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <select class="combox" name="area" id="combox_area">
						<option value="" <c:if test="${cjrxx.area==''}">selected</c:if>>所有区

县</option>
						<c:forEach items="${area}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if 

test="${cjrxx.area==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <input name="txdz" type="text" size="30" value="${cjrxx.txdz }"
						maxlength="100" /> --%>
				
			<div class="divider"></div>
			<p><label>所属销售人员：</label>  <input type="text"
							id="empname" class="required" 
							name="employeeCrm.name" value="${cjrxx.employeeCrm.name }"
							suggestFields="name,deptname" lookupGroup="employeeCrm" readonly alt="" />
							<input type="hidden" name="employeeCrm.id"
							value="${cjrxx.employeeCrm.id}" /> <a class="btnLook"
							href="${ctx }/baseinfo/emplookup?
code=0001&lookId=${employee.organi.id}&code2=0002"
							lookupGroup="employeeCrm"><hi:text key="查找带回" /></a><span
							class="info"> </span></p>
						<p><label>所属销售团队：</label> <input
							name="employeeCrm.deptname" type="text" readonly="readonly"
							size="20" value="${cjrxx.kftd }" class="" maxlength="80" /></p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80">${cjrxx.remark}

</textarea>
				</dd>
			</dl>
			<c:if test="${cjrxx.id == null || cjrxx.id == ''}">
				<p>
					<label>添加沟通记录：</label> <input type="radio" name="isGouTong"
						value="0" onClick="checkGouTong(this.value);" checked>不添加&nbsp;
					<input type="radio" name="isGouTong" value="1"
						onClick="checkGouTong(this.value);">添加&nbsp;
				</p>
				<div class="divider"></div>
				<div id="toGouTongDiv" style="display: none">
					<p>
						<label>意向出资日期：</label> <input name="yxczrq" type="text" 

size="25"
							value="${xhCjrgtjl.yxczrq}" class="date" pattern="yyyy-MM-dd"
							maxlength="40" readonly="true"/>
							<a class="inputDateButton" href="#">选择</a>
					</p>
					<p>
						<label>意向出资金额：</label> <input name="yxczje" type="text" 

size="30"
							value="${xhCjrgtjl.yxczje}" maxlength="40" />
					</p>
					<p>
						<label>本次沟通日期：</label> <input name="bcgtrq" type="text" 

size="25"
							value="${xhCjrgtjl.bcgtrq}" class="date" pattern="yyyy-MM-dd"
							maxlength="40" readonly="true" />
							<a class="inputDateButton" href="#">选择</a>
					</p>
					<p>
						<label>本次沟通方式：</label> <input name="bcgtfs" type="text" 

size="30"
							value="${xhCjrgtjl.bcgtfs}" maxlength="20" />
					</p>
					<p>
						<label>沟通开始时间：</label> <input name="gtkssj" type="text" 

size="25"
							value="${xhCjrgtjl.gtkssj}" class="date" format="HH:mm:ss"
							maxlength="40" readonly="true" /> <a class="inputDateButton" 

href="#">选择</a>
					</p>
					<p>
						<label>沟通结束时间：</label> <input name="gtjssj" type="text" 

size="25"
							value="${xhCjrgtjl.gtjssj}" class="date" format="HH:mm:ss"
							maxlength="40" readonly="true" />
							<a class="inputDateButton" href="#">选择</a>
					</p>
					<p>
						<label>意向产品：</label> <select name="yxcp" class="combox">
							<option value=""
								<c:if test="${xhCjrgtjl.yxcp==''}">selected</c:if>>请选

择</option>
								<c:forEach items="${tzcp}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if 

test="${xhCjrgtjl.yxcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
								</c:forEach>
						</select>
					</p>
					<p>
						<label>客户意向：</label> <input name="khyx" type="text" size="30"
							value="${xhCjrgtjl.khyx}" maxlength="20" />
					</p>
					<p>
						<label>本次联系人：</label> <input name="bclxr" type="text" size="30"
							value="${xhCjrgtjl.bclxr}" maxlength="20" />
					</p>
					<dl class="nowrap">
						<dt>沟通内容描述：</dt>
						<dd>
							<textarea name="gtnrms" style="width: 93%; height: 80"
								maxlength="1000">${xhCjrgtjl.gtnrms}</textarea>
						</dd>
					</dl>
					<p>
						<label>下次联系日期：</label> <input name="xclxrq" type="text" 

size="25"
							value="${xhCjrgtjl.xclxrq}" class="date" pattern="yyyy-MM-dd"
							maxlength="40" readonly="true"/>
							<a class="inputDateButton" href="#">选择</a>
					</p>
					<p>
						<label>下次联系方式：</label> <input name="xclxfs" type="text" 

size="30"
							value="${xhCjrgtjl.xclxfs}" maxlength="20" />
					</p>
				</div>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">说明：证件号码为空，则咨询客户不能转为出借人

</div>
					</div></li>
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