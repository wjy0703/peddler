<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/cjrxx/savecjrxx"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		
		<div class="pageFormContent" layoutH="56">
				<div class="panel"><h1 width="50%">客户基本信息</h1></div>
			
		<table width="100%">
		<tr>
					<td><label>客户姓名：</label> <input name="cjrxm" type="text" disabled="disabled"
						size="20" value="${cjrxx.cjrxm }" class="" maxlength="10" ><span class="info"> (必填)</span>
					</td>
					<td><label>英文名称：</label> <input name="ywmc" type="text" disabled="disabled"
						size="20" value="${cjrxx.ywmc }" maxlength="10" class="lettersonly"/></td>
				
					<td><label>性别<font color="red">*</font>：</label>
					<sen:select clazz="combox required" name="cjrxb" id="cjrxb" coding="genders" value="${cjrxx.cjrxb }" title="请选择"/>
					 <%-- <select name="cjrxb"
						class=" combox" alt="必填">
							<option value="" <c:if test="${cjrxx.cjrxb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${xb}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrxb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%><span class="info"> (必填)</span></td>
					</tr>
			<tr>
					<td><label>出生日期：</label> <input type="text" disabled="disabled" name="csrq"
						class=" date" format="yyyy-MM-dd" yearstart="-80"
						 value="${cjrxx.csrq }" size="17" readonly="true"/>
						<a class="inputDateButton" href="javascript:;">选择</a> <span class="info"> (必填)</span>
						</td>
				
					<td><label>首次联系时间：</label> <input name="optionTime"
						type="text" disabled="disabled" size="17" value="${cjrxx.optionTime }" class="date"
						dateFmt="yyyy-MM-dd HH:mm:ss"
						 value="${cjrxx.csrq }" size="20" readonly="true" />
						<a class="inputDateButton" href="javascript:;">选择</a> 
						</td>
					<td><label>婚姻状况<font color="red">*</font>：</label> 
					<sen:select clazz="combox required" name="hyzk" coding="marriageType" value="${cjrxx.hyzk }" title="请选择"/>
					<%-- <select name="hyzk"
						class=" combox">
							<option value="" <c:if test="${cjrxx.hyzk==''}">selected</c:if>>请选择</option>
							<c:forEach items="${hyzk}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.hyzk==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%><span class="info"> (必填)</span></td>
				</tr>
			<tr>
					<td><label>证件类型<font color="red">*</font>：</label> 
						<sen:select clazz="combox required" name="zjlx" coding="cardType" id="zjlx" value="${cjrxx.zjlx }"/>
					<%-- <select name="zjlx"
						class=" combox">
							<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zjlx}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.zjlx==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%><span class="info"> (必填)</span></td>
					<td><label>证件号码：</label> <input name="zjhm" type="text" disabled="disabled"
						size="20" value="${cjrxx.zjhm }" class=" alphanumeric"
						maxlength="40"
						remote="${ctx }/cjrxx/chkValue?oldValue=${cjrxx.zjhm }&propertyName=zjhm" /><span class="info"> (必填)</span>
					</td>
				
					<td><label>签发日期：</label> <input type="text" disabled="disabled" name="zjqfrq"
						class="date" pattern="yyyy-MM-dd" value="${cjrxx.zjqfrq }"
						size="17"  yearstart="-80"/> <a class="inputDateButton" href="#">选择</a>
						<input name="changqi" value="1" type="checkbox" <c:if test="${cjrxx.sfcq=='1'}">checked</c:if> disabled="disabled">长期
						</td>
					</tr>
			<tr>
					<td><label>失效日期：</label> <input type="text" disabled="disabled" name="qjsxrq"
						class="date" pattern="yyyy-MM-dd" value="${cjrxx.qjsxrq }"
						size="17" /> <a class="inputDateButton" href="#">选择</a></td>
				
					<td><label>发证机关：</label> <input name="fzjg" type="text" disabled="disabled"
						size="20" value="${cjrxx.fzjg }" maxlength="40" /></td>
					<td><label>学历：</label> <input name="cjrxl" type="text" disabled="disabled"
						size="20" value="${cjrxx.cjrxl }" maxlength="20" /></td>
				</tr>
			<tr>
					<td><label>职业<font color="red">*</font>：</label> 
						<sen:select clazz="combox required" name="cjrzy" id="cjrzy" coding="occupationType" value="${cjrxx.cjrzy }" title="请选择"/>
					<%-- <select name="cjrzy"
						class=" combox">
							<option value="" <c:if test="${cjrxx.cjrzy==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zy}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrzy==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%><span class="info"> (必填)</span></td>
					<td><label>行业：</label> <input name="hy" type="text" disabled="disabled" size="20"
						value="${cjrxx.hy }" maxlength="40" /></td>
				
					<td><label>单位名称：</label> <input name="gzdwmc" type="text" disabled="disabled"
						size="30" value="${cjrxx.gzdwmc }" maxlength="100" /></td>
						</tr>
			<tr>
					<td><label>工作年限：</label> <input name="gznx" type="text" disabled="disabled"
						size="10" value="${cjrxx.gznx }" maxlength="3" class="number"/></td>
				
					<td><label>单位规模：</label> 
						<sen:select clazz="combox required" name="cjrdwgm" id="cjrdwgm" coding="officeModle" value="${cjrxx.cjrdwgm}" title="请选择"/>
					<%-- <select name="cjrdwgm"
						class="combox">
							<option value=""
								<c:if test="${cjrxx.cjrdwgm==''}">selected</c:if>>请选择</option>
							<c:forEach items="${dwgm}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrdwgm==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></td>
					<td><label>职务：</label> <input name="zw" type="text" disabled="disabled" size="20"
						value="${cjrxx.zw }" maxlength="20" /></td>
				</tr>
				<c:if test="${cjrxx.createBy == loginName || positionCode != '0002'}">
			<tr>
					<td><label>移动电话：</label> <input name="yddh" type="text" disabled="disabled"
						size="20" value="${cjrxx.yddh }" class="" maxlength="20" /><span class="info"> (必填)</span>
					</td>
					<td><label>固定电话：</label> <input name="gddh" type="text" disabled="disabled"
						size="20" value="${cjrxx.gddh }" class="phone" maxlength="20" /></td>
				
					<td><label>电子邮箱：</label> <input name="dzyx" type="text" disabled="disabled"
						size="20" value="${cjrxx.dzyx }" class="email" maxlength="80" /></td>
						</tr>
				</c:if>
			<tr>
					<td><label>客户传真：</label> <input name="cjrfx" type="text" disabled="disabled"
						size="20" value="${cjrxx.cjrfx }" class="phone" maxlength="20" /></td>
				
					<td><label>客户来源：</label>
						<sen:select clazz="combox required" name="khly" id="khly" coding="personFrom" value="${cjrxx.khly}" title="请选择"/>
					 <%-- <select name="khly" class="combox">
							<option value="" <c:if test="${cjrxx.khly==''}">selected</c:if>>请选择</option>
							<c:forEach items="${khly}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.khly==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></td>
					<td><label>母亲姓氏：</label> <input name="mqxm" type="text" disabled="disabled"
						size="20" value="${cjrxx.mqxm}" maxlength="80"  disabled="disabled" /></td>
				</tr>
			
			</table>	
			
			
			<div class="panel"><h1>客户通讯地址(注：请认真填写、核对通信地址，该地址将用于客户账单邮寄)</h1></div>
			
			
			<dl class="nowrap">
				<dt>详细通信地址<font color="red"></font>：</dt>
				<dd>
				<sen:address required="true"  names="province,city,area" 
					titles="所有省份,所有城市,所有区县" values="${cjrxx.province},${cjrxx.city},${cjrxx.area}"/> 
					 <input name="txdz" type="text"
							size="80" value="${cjrxx.txdz }" maxlength="60" class="required" /><span class="info"> (必填)</span>
				
				<%-- <select class="combox" name="province" ref="combox_city"
						refUrl="${ctx}/cjrxx/getCity?code={value}" class="">
						<option value=""
							<c:if test="${cjrxx.province==''}">selected</c:if>>所有省市</option>
						<c:forEach items="${province}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.province==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select><span class="info"> (必填)</span> <select class="combox" name="city" id="combox_city" class=""
						ref="combox_area" refUrl="${ctx}/cjrxx/getArea?code={value}"> 
						<option value="" <c:if test="${cjrxx.city==''}">selected</c:if>>所有城市</option>
						<c:forEach items="${city}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.city==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select><span class="info"> (必填)</span> <select class="combox" name="area" id="combox_area" class="">
						<option value="" <c:if test="${cjrxx.area==''}">selected</c:if>>所有区县</option>
						<c:forEach items="${area}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.area==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> --%><%-- </span><input name="txdz" type="text" disabled="disabled" size="80" value="${cjrxx.txdz }"
						maxlength="60" class=""/> --%></dd></dl>
				<dl class="nowrap">
				<dt>邮政编码<font color="red">*</font>：</dt>
				<dd><input name="yb" type="text" disabled="disabled" size="10"
						value="${cjrxx.yb }" class=" number" maxlength="6" minlength="6"/><span class="info"> (必填)</span></dd></dl>
			
			<div class="divider"></div>
			
			<div class="panel"><h1>紧急联系人信息</h1></div>
			
		
			<table width="100%"><tr><td><label>中文姓名：</label> <input name="jjlxrzwmc" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxrzwmc }" class=""
						maxlength="20" /><span class="info"> (必填)</span></td>
					<td><label>英文名称： </label><input name="jjlxrywmc" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxrywmc }" maxlength="10" class="lettersonly"/></td>
					<td><label>性别<font color="red">*</font>：</label> 
					<sen:select clazz="combox required" name="jjlxrxb" id="jjlxrxb" coding="genders" value="${cjrxx.jjlxrxb}" title="请选择"/>
					<%-- <select name="jjlxrxb"
						class=" combox">
							<option value=""
								<c:if test="${cjrxx.jjlxrxb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${xb}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.jjlxrxb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%><span class="info"> (必填)</span></td>
					</tr>
			<tr>
					<td><label>出生日期：</label><input type="text" disabled="disabled" name="jjlxrcsrq" yearstart="-80"
						class="date " pattern="yyyy-MM-dd" value="${cjrxx.jjlxrcsrq }"
						size="17" /><a class="inputDateButton" href="#">选择</a><span class="info"> (必填)</span></td>
					<td><label>证件类型<font color="red">*</font>：</label>
						<sen:select clazz="combox required" name="jjlxrzjlx" coding="cardType" id="jjlxrzjlx" value="${cjrxx.jjlxrzjlx }"/>
					<%-- <select name="jjlxrzjlx"
						class=" combox">
							<option value=""
								<c:if test="${cjrxx.jjlxrzjlx==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zjlx}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.jjlxrzjlx==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%><span class="info"> (必填)</span></td>
					<td><label>证件号码<font color="red">*</font>：</label><input name="jjlxrzjhm" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxrzjhm }" class=""
						maxlength="40" /><span class="info"> (必填)</span></td>
				</tr>
			<tr>
					<td><label>移动电话<font color="red">*</font>：</label><input name="jjlxryddh" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxryddh }" class=""
						maxlength="40" /><span class="info"> (必填)</span></td>
					<td><label>固定电话：</label><input name="jjlxrgddh" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxrgddh }" class="phone" class=""
						maxlength="40" /></td>
					<td><label>电子邮箱：</label><input name="jjlxrdzyx" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxrdzyx }" class="email" maxlength="80" /></td>
						</tr>
			<tr>
					<td><label>与客户的关系：</label><input name="yndgx" type="text" disabled="disabled"
						size="20" value="${cjrxx.yndgx }" maxlength="80" /></td>
					
					<td>
					</td>
					<td>
				</td>
				</tr>
				<tr>
					<td colspan="6">
			<dl class="nowrap">
							<dt>联系人通讯地址：</dt>
							<dd>
							
							<sen:address required="true"  names="jjlxrprovince,jjlxrcity,jjlxrarea" 
					titles="所有省份,所有城市,所有区县" values="${cjrxx.jjlxrprovince},${cjrxx.jjlxrcity},${cjrxx.jjlxrarea}"/>
					 <input name="jjlxrtxdz" type="text"
							size="80" value="${cjrxx.jjlxrtxdz }" maxlength="60" class="required" />
								<%-- <select class="combox" name="jjlxrprovince"
									ref="combox_jjlxrcity"
									refUrl="${ctx}/cjrxx/getCity?code={value}">
									<option value=""
										<c:if test="${cjrxx.jjlxrprovince==''}">selected</c:if>>所有省市</option>
									<c:forEach items="${province}" var="md" varStatus="st">
										<option value="${md.id }"
											<c:if test="${cjrxx.jjlxrprovince==md.id}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
								</select> <select class="combox" name="jjlxrcity" id="combox_jjlxrcity"
									ref="combox_jjlxrarea"
									refUrl="${ctx}/cjrxx/getArea?code={value}">
									<option value=""
										<c:if test="${cjrxx.jjlxrcity==''}">selected</c:if>>所有城市</option>
									<c:forEach items="${jjlxrcity}" var="md" varStatus="st">
										<option value="${md.id }"
											<c:if test="${cjrxx.jjlxrcity==md.id}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
								</select> <select class="combox" name="jjlxrarea" id="combox_jjlxrarea">
									<option value=""
										<c:if test="${cjrxx.jjlxrarea==''}">selected</c:if>>所有区县</option>
									<c:forEach items="${jjlxrarea}" var="md" varStatus="st">
										<option value="${md.id }"
											<c:if test="${cjrxx.jjlxrarea==md.id}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
								</select> <input name="jjlxrtxdz" type="text" disabled="disabled" size="80"
									value="${cjrxx.jjlxrtxdz }" maxlength="100" /> --%>
							</dd></dl>
							<dl class="nowrap">
							<dt>邮政编码：</dt>
							 <dd><input name="jjlxryb" type="text" disabled="disabled"
						size="20" value="${cjrxx.jjlxryb }"  class="number" maxlength="6" minlength="6" /></dd>
						</dl>
				</td></tr>
			</table>
			<div class="panel"><h1>客户开发及管辖信息</h1></div>
		
			<table><tr><td><label>团队经理<font color="red">*</font>：</label> <input type="hidden"
						name="employeeCca.id" value="${cjrxx.employeeCca.id}" /> <input
						type="text" disabled="disabled" id="empname" class=" textInput"
						name="employeeCca.name" value="${cjrxx.employeeCca.name }"
						suggestFields="name,deptname"
						lookupGroup="employeeCca" readonly/> <a class="btnLook"
						href="${ctx }/baseinfo/emplookup?code=0002&lookId=${employee.organi.id}" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a>
								<span class="info"> (必填)</span></td>
					<td><label>理财经理<font color="red">*</font>：</label> <input type="hidden"
						name="employeeCrm.id" value="${cjrxx.employeeCrm.id}" /> <input
						type="text" disabled="disabled" id="empname" class=" textInput"
						name="employeeCrm.name" value="${cjrxx.employeeCrm.name }"
						suggestFields="name,deptname"
						lookupGroup="employeeCrm" readonly alt=""/> <a class="btnLook"
						href="${ctx }/baseinfo/emplookup?code=0001&lookId=${employee.organi.id}" lookupGroup="employeeCrm"><hi:text
								key="查找带回" /></a><span class="info"> (必填)</span></td>
									
						<td><label>开发团队名称：</label> <input name="kftd" type="text" disabled="disabled"
						size="20" value="${cjrxx.kftd }" class="" maxlength="80" />
					<span class="info"> (必填)</span></td></tr></table>
					<dl class="nowrap">
				<dt>管辖城市<font color="red">*</font>：</dt>
				<dd>
					<sen:address required="true"  names="crmprovince,crmcity" 
					titles="所有省份,所有城市" values="${cjrxx.crmprovince},${cjrxx.crmcity}"/>
					
					<%-- <select class="combox" name="crmprovince" ref="combox_crmcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
						<option value=""
							<c:if test="${cjrxx.crmprovince==''}">selected</c:if>>所有省市</option>
						<c:forEach items="${province}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.crmprovince==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select><span class="info"> (必填)</span> <select class="combox" name="crmcity" id="combox_crmcity"
						ref="combox_crmarea" refUrl="${ctx}/cjrxx/getArea?code={value}">
						<option value="" <c:if test="${cjrxx.crmcity==''}">selected</c:if>>所有城市</option>
						<c:forEach items="${crmcity}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.crmcity==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select><span class="info"> (必填)</span> --%>
				</dd>
			</dl>
				
					
					
				
			<div class="divider"></div>
			<div class="panel"><h1 width="50%">客户银行账户信息</h1></div>
			<p><label>账单收取方式：</label>
				<sen:select clazz="combox required" name="zqjsfs" id="zqjsfs" coding="sendModle" value="${cjrxx.zqjsfs}" title="请选择"/>
			<%--  <select name="zqjsfs"
						class=" combox" >
							<option value="" <c:if test="${cjrxx.zqjsfs==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zqjsfs}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.zqjsfs==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></p>
			<dl class="nowrap">
				<dt>出借付款银行账户*：</dt>
				<dd>
							<p><label>开户行：</label>
							<sen:select clazz="combox required" name="tzfkkhh" id="tzfkkhh" coding="bank" value="${cjrxx.tzfkkhh}" title="请选择"/>
							<%-- <select name="tzfkkhh" class=" combox">
									<option value=""
										<c:if test="${cjrxx.tzfkkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${cjrxx.tzfkkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%></p>
							<p><label>卡或折：</label>
							<sen:select clazz="combox required" name="tzfkkhz" id="tzfkkhz" coding="cardOrModle" value="${cjrxx.tzfkkhz}" title="请选择"/>
							<%-- <select name="tzfkkhz" class=" combox">
									<option value=""
										<c:if test="${cjrxx.tzfkkhz==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khz}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${cjrxx.tzfkkhz==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%></p>
							<p><label>具体支行：</label>
							<input name="tzfkyhmc" type="text" disabled="disabled" size="30"
								value="${cjrxx.tzfkyhmc}" maxlength="80"  /></p>
						<div class="divider"></div>
						
							<p><label>开户姓名：</label>
							<input name="tzfkkhmc" type="text" disabled="disabled" size="30"
								value="${cjrxx.cjrxm}" maxlength="80"  /></p>
							<p><label>账户：</label>
							<input name="tzfkyhzh" type="text" disabled="disabled" size="30"
								value="${cjrxx.tzfkyhzh}" maxlength="80"  /></p>
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>回收资金银行账户*：</dt>
				<dd>
							<p><label>开户行：</label>
							<sen:select clazz="combox required" name="hszjkhh" id="hszjkhh" coding="bank" value="${cjrxx.hszjkhh}" title="请选择"/>
							<%-- <select name="hszjkhh" class=" combox">
									<option value=""
										<c:if test="${cjrxx.hszjkhh==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khh}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${cjrxx.hszjkhh==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%></p>
							<p><label>卡或折：</label>
								<sen:select clazz="combox required" name="hszjkhz" id="hszjkhz" coding="cardOrModle" value="${cjrxx.hszjkhz}" title="请选择"/>
							<%-- <select name="hszjkhz" class=" combox">
									<option value=""
										<c:if test="${cjrxx.hszjkhz==''}">selected</c:if>>请选择</option>
									<c:forEach items="${khz}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${cjrxx.hszjkhz==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select> --%></p>
							<p><label>具体支行：</label>
							<input name="hszjyhmc" type="text" disabled="disabled" size="30"
								value="${cjrxx.hszjyhmc}" maxlength="80"  /></p>
							<div class="divider"></div>
							<p><label>开户姓名：</label>
							<input name="hszjkhmc" type="text" disabled="disabled" size="30"
								value="${cjrxx.cjrxm}" maxlength="80"  /></p>
							<p><label>账户：</label>
							<input name="hszjyhzh" type="text" disabled="disabled" size="30"
								value="${cjrxx.hszjyhzh}" maxlength="80"  /></p>
					
				</dd>
			</dl>
			
				
					<p><label>是否签订委托协议：</label> 
						<sen:select clazz="combox required" name="sfqdwtxy" id="sfqdwtxy" coding="yesOrNo" value="${cjrxx.sfqdwtxy}" title="请选择"/>
					<%-- <select name="sfqdwtxy"
						class=" combox" >
							<option value=""
								<c:if test="${cjrxx.sfqdwtxy==''}">selected</c:if>>请选择</option>
							<option value="是"
								<c:if test="${cjrxx.sfqdwtxy=='是'}">selected</c:if>>是</option>
							<option value="否"
								<c:if test="${cjrxx.sfqdwtxy=='否'}">selected</c:if>>否</option>
					</select> --%></p>
					<p><label>协议签订日期：</label> <input name="qdxyrq" type="text" disabled="disabled"
						size="30" value="${cjrxx.qdxyrq}" class=" date"
						pattern="yyyy-MM-dd" maxlength="20"  /></p>
				
				<div class="divider"></div>
					<p><label>委托协议种类：</label> 
						<sen:select clazz="combox required" name="wtxyzl" id="wtxyzl" coding="wtxyzl" value="${cjrxx.wtxyzl}" title="请选择"/>
					<%-- <select name="wtxyzl"
						class=" combox" disabled="disabled">
							<option value="" <c:if test="${cjrxx.wtxyzl==''}">selected</c:if>>请选择</option>
							<c:forEach items="${wtxyzl}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.wtxyzl==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></p>
					<p><label>委托协议版本号：</label>
						<sen:select clazz="combox required" name="wtxybbh" id="wtxybbh" coding="wtxybbh" value="${cjrxx.wtxybbh}" title="请选择"/>
					<%--  <select name="wtxybbh"
						class=" combox" disabled="disabled">
							<option value=""
								<c:if test="${cjrxx.wtxybbh==''}">selected</c:if>>请选择</option>
							<c:forEach items="${wtxybbh}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.wtxybbh==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></p>
			<c:if test="${cjrxx.state=='3' }">
				<dl class="nowrap">
					<dt>审核意见：</dt>
					<dd>
						<textarea name="auditIdea" style="width: 93%; height: 80"
							>${cjrxx.auditIdea }</textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>审核结果：</dt>
					<dd>
						<input type="radio" name="state" value="2"
							<c:if test="${cjrxx.state=='2' }">checked</c:if>
							 />通过 <input type="radio" name="state"
							value="3" <c:if test="${cjrxx.state=='3' }">checked</c:if>
							disabled="disabled" />不通过
					</dd>
				</dl>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
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
	</form>
</div></div>
