<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/themes/js/checkValue.js"></script>
<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx}/cjrxx/savecjrxx"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${cjrxx.id}" /> <input
				type="hidden" name="sfcq" value="${cjrxx.sfcq}" /> <input
				type="hidden" name="state" id="state" value="0" />
			<div class="pageFormContent" layoutH="56">

				<div class="panel">
					<h1 width="50%">客户基本信息</h1>
				</div>

				<table width="100%">
					<tr>
						<td><label>客户姓名：</label> <input name="cjrxm" type="text"
							size="20" value="${cjrxx.cjrxm }" class="required" 

maxlength="30"></td>
						<td><label>英文名称：</label> <input name="ywmc" type="text"
							size="20" value="${cjrxx.ywmc }" maxlength="10"
							class="lettersonly" /></td>
						<td><label>婚姻状况：</label> 
						<sen:select clazz="combox required" name="hyzk" coding="marriageType" value="${cjrxx.hyzk }" title="请选择"/>
						</td>
					</tr>
					<tr>
						<td><label>证件类型：
						</label> 
						<sen:select clazz="combox required" name="zjlx" coding="cardType" id="zjlx" value="${cjrxx.zjlx }"/>
					</td>
						<td><label>证件号码：</label> <input name="zjhm" id="zjhm" type="text"
							size="20" value="${cjrxx.zjhm }" class="required"
							minlength="1" maxlength="18"
							onblur="vProblemName('${cjrxx.zjhm }','zjhm','证件号码');" /></td>

						<td><label>签发日期：</label> <input type="text" name="zjqfrq"
							class="date" pattern="yyyy-MM-dd" value="${cjrxx.zjqfrq }"
							size="17" yearstart="-80" /> <a class="inputDateButton" href="#">选择</a>
							<input name="changqi" value="1" type="checkbox"
							<c:if test="${cjrxx.sfcq=='1'}">checked</c:if>
							onclick="cqClick()">长期</td>
					</tr>
					<tr>
						<td><label>失效日期：</label> <input type="text" name="qjsxrq"
							class="date" pattern="yyyy-MM-dd" value="${cjrxx.qjsxrq }"
							<c:if test="${cjrxx.sfcq=='1'}">disabled</c:if> size="17" /> <a
							class="inputDateButton" href="#">选择</a></td>

						<td><label>发证机关：</label> <input name="fzjg" type="text"
							size="20" value="${cjrxx.fzjg }" maxlength="40" /></td>
						<td><label>学历：</label> <input name="cjrxl" type="text"
							size="20" value="${cjrxx.cjrxl }" maxlength="20" /></td>
					</tr>
					<tr>
						
						<td><label>性别：</label>
						
						<sen:select clazz="combox required" name="cjrxb" id="cjrxb" coding="genders" value="${cjrxx.cjrxb }" title="请选择"/>
						</td>
						<td><label>出生日期：</label> <input type="text" name="csrq" id="csrq"
							class="required date" format="yyyy-MM-dd" yearstart="-100"
							value="${cjrxx.csrq }" size="17" readonly="true" /> <a
							class="inputDateButton" href="javascript:;">选择</a> </td>

						<td><label>首次联系时间：</label> <input name="optionTime"
							type="text" size="17" value="${cjrxx.optionTime }" class="date"
							dateFmt="yyyy-MM-dd HH:mm:ss" value="${cjrxx.csrq }" size="20"
							readonly="true" /> <a class="inputDateButton"
							href="javascript:;">选择</a></td>
						
					</tr>
					<tr>
						<td><label>职业：</label> 
						<sen:select clazz="combox" name="cjrzy" id="cjrzy" coding="occupationType" value="${cjrxx.cjrzy }" title="请选择"/>
						</td>
						<td><label>行业：</label> <input name="hy" type="text" size="20"
							value="${cjrxx.hy }" maxlength="40" /></td>
						<td><label>单位名称：</label> <input name="gzdwmc" type="text"
							size="30" value="${cjrxx.gzdwmc }" maxlength="100" /></td>
					</tr>
					<tr>
						<td><label>工作年限：</label> <input name="gznx" type="text"
							size="10" value="${cjrxx.gznx }" maxlength="3" class="number" 

/></td>

						<td><label>单位规模：</label> 
						<sen:select clazz="combox" name="cjrdwgm" coding="officeModle" value="${cjrxx.cjrdwgm }" title="请选择"/>
						</td>
						<td><label>职务：</label> <input name="zw" type="text" size="20"
							value="${cjrxx.zw }" maxlength="20" /></td>
					</tr>
					<tr>
						<td><label>移动电话：</label> <input name="yddh" id="yddh" type="text"
							size="20" value="${cjrxx.yddh }" class="required phone" maxlength="20" 
						onblur="vProblemName('${cjrxx.yddh }','yddh','移动电话');" 
						/></td>
						<td><label>固定电话：</label> <input name="gddh" type="text"
							size="20" value="${cjrxx.gddh }" class="phone" maxlength="20" 

						/></td>

						<td><label>电子邮箱：</label> <input name="dzyx" type="text"
							size="20" value="${cjrxx.dzyx }" class="email" maxlength="80" 

/></td>
					</tr>
					<tr>
						<td><label>客户传真：</label> <input name="cjrfx" type="text"
							size="20" value="${cjrxx.cjrfx }" class="phone" maxlength="20" 

/></td>

						<td><label>客户来源：</label> 
						<sen:select clazz="combox required" name="khly" coding="personFrom" value="${cjrxx.khly }" title="请选择"/>
						</td>
<td>	<label>母亲姓氏：</label> <input name="mqxm" type="text" 
						size="20" value="${cjrxx.mqxm}" maxlength="80"  class="required"/>
					</td>
					</tr>

				</table>


				<div class="panel">
					<h1>客户通讯地址(注：请认真填写、核对通信地址，该地址将用于客户账单邮寄)</h1>
				</div>


				<dl class="nowrap">
					<dt>
						详细通信地址<font color="red"></font>：
					</dt>
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
				<dl class="nowrap">
					<dt>
						邮政编码：
					</dt>
					<dd>
						<input name="yb" type="text" size="10" value="${cjrxx.yb }"
							class="required number" maxlength="6" minlength="6" />
					</dd>
				</dl>

				<div class="divider"></div>

				<div class="panel">
					<h1>紧急联系人信息</h1>
				</div>


				<table width="100%">
					<tr>
						<td><label>中文姓名：</label> <input name="jjlxrzwmc" type="text"
							size="20" value="${cjrxx.jjlxrzwmc }" class="required"
							maxlength="20" /></td>
						<td><label>英文名称： </label><input name="jjlxrywmc" type="text"
							size="20" value="${cjrxx.jjlxrywmc }" maxlength="10"
							class="lettersonly" /></td>
						<td><label>性别：
						</label> 
						<sen:select clazz="combox required" name="jjlxrxb" id="jjlxrxb" coding="genders" value="${cjrxx.jjlxrxb }" title="请选择"/>
						</td>
					</tr>
					<tr>
						<td><label>出生日期：</label><input type="text" name="jjlxrcsrq"
							yearstart="-80" class="date required" pattern="yyyy-MM-dd"
							value="${cjrxx.jjlxrcsrq }" size="17" /><a
							class="inputDateButton" href="#">选择</a></td>
						<td><label>证件类型：
						</label>
						<sen:select clazz="combox required" name="jjlxrzjlx" coding="cardType" value="${cjrxx.jjlxrzjlx }"/>
						</td>
						<td><label>证件号码：
						</label><input name="jjlxrzjhm" type="text" size="20"
							value="${cjrxx.jjlxrzjhm }" class="required alphanumeric"
							minlength="1" maxlength="18" /></td>
					</tr>
					<tr>
						<td><label>移动电话：
						</label><input name="jjlxryddh" type="text" size="20"
							value="${cjrxx.jjlxryddh }" class="required phone" maxlength="40" /></td>
						<td><label>固定电话：</label><input name="jjlxrgddh" type="text"
							size="20" value="${cjrxx.jjlxrgddh }" class="phone" class=""
							maxlength="40" /></td>
						<td><label>电子邮箱：</label><input name="jjlxrdzyx" type="text"
							size="20" value="${cjrxx.jjlxrdzyx }" class="email"
							maxlength="80" /></td>
					</tr>
					<tr>
						<td><label>与客户的关系：</label><input name="yndgx" type="text"
							size="20" value="${cjrxx.yndgx }" maxlength="80" /></td>

						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="6"></td>
					</tr>
				</table>
				<dl class="nowrap">
					<dt>联系人通讯地址：</dt>
					
						<dd>
							<sen:address names="jjlxrprovince,jjlxrcity,jjlxrarea" 
					titles="所有省份,所有城市,所有区县" values="${cjrxx.jjlxrprovince},${cjrxx.jjlxrcity},${cjrxx.jjlxrarea}"/>
					</dl>
					<dl class="nowrap">
					<dt>&nbsp;</dt>
					<dd>
					 <input name="jjlxrtxdz" type="text" size="70"
							value="${cjrxx.jjlxrtxdz }" maxlength="100" />
							<font color="red">(注:不需要重复录入省市区信息)</font>
				</dd>
					
					
					
				</dl>
				<dl class="nowrap">
					<dt>邮政编码：</dt>
					<dd>
						<input name="jjlxryb" type="text" size="20"
							value="${cjrxx.jjlxryb }" class="number" maxlength="6"
							minlength="6" />
					</dd>
				</dl>

				<!--  
<div class="divider"></div>
	<dl class="nowrap">
				<dt><h1>备注：</h1></dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80">${cjrxx.remark}</textarea>
				</dd>
			</dl>-->

				<input type="hidden" name="hszjyhzh" value="${cjrxx.hszjyhzh}" /> <input
					type="hidden" name="hszjkhmc" value="${cjrxx.hszjkhmc}" /> <input
					type="hidden" name="hszjyhmc" value="${cjrxx.hszjyhmc}" /> <input
					type="hidden" name="hszjkhz" value="${cjrxx.hszjkhz}" /> <input
					type="hidden" name="hszjkhh" value="${cjrxx.hszjkhh}" /> <input
					type="hidden" name="tzfkyhzh" value="${cjrxx.tzfkyhzh}" /> <input
					type="hidden" name="tzfkkhmc" value="${cjrxx.tzfkkhmc}" /> <input
					type="hidden" name="tzfkyhmc" value="${cjrxx.tzfkyhmc}" /> <input
					type="hidden" name="tzfkkhz" value="${cjrxx.tzfkkhz}" /> <input
					type="hidden" name="tzfkkhh" value="${cjrxx.tzfkkhh}" /> <input
					type="hidden" name="zqjsfs" value="${cjrxx.zqjsfs}" /> <input
					type="hidden" name="sfqdwtxy" value="${cjrxx.sfqdwtxy}" /> <input
					type="hidden" name="qdxyrq" value="${cjrxx.qdxyrq}" /> <input
					type="hidden" name="wtxyzl" value="${cjrxx.wtxyzl}" /> <input
					type="hidden" name="wtxybbh" value="${cjrxx.wtxybbh}" />


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
							<input type="radio" name="state" value="2" disabled="disabled" />通

过
							<input type="radio" name="state" value="3" checked="checked"
								disabled="disabled" />不通过
						</dd>
					</dl>
				</c:if>
				<div class="panel">
					<h1>客户开发及管辖信息</h1>
				</div>

				<table width="100%">
					<tr>
						<td><label>团队经理：
						</label>  <input type="text"
							id="empname" class="required"
							name="employeeCca.name" value="${cjrxx.employeeCca.name }"
							suggestFields="name,deptname" lookupGroup="employeeCca" readonly />
							<input type="hidden" name="employeeCca.id"
							value="${cjrxx.employeeCca.id}" />
							<a class="btnLook"
							href="${ctx }/baseinfo/emplookup?
code=0002&lookId=${employee.organi.id}&parentFlag=1&code2=0003"
							lookupGroup="employeeCca"><hi:text key="查找带回" /></a> </td>
						<td><label>理财经理：
						</label> <input type="text"
							id="empname" class="required"
							name="employeeCrm.name" value="${cjrxx.employeeCrm.name }"
							suggestFields="name,deptname" lookupGroup="employeeCrm" readonly
							alt="" /> 
							
							<input type="hidden" name="employeeCrm.id"
							value="${cjrxx.employeeCrm.id}" /> 
							<a class="btnLook"
							href="${ctx }/baseinfo/emplookup?code=0001&lookId=${employee.organi.id}&code2=0002"
							lookupGroup="employeeCrm"><hi:text key="查找带回" /></a></td>

						<td><label>开发团队名称：</label> <input
							name="employeeCrm.deptname" type="text" readonly="readonly"
							size="20" value="${cjrxx.kftd }" class="required" maxlength="80" />
							</td>
					</tr>
				</table>
				<dl class="nowrap">
					<dt>
						管辖城市：
					</dt>
					<dd>
					<sen:address required="true" names="crmprovince,crmcity" 
					titles="所有省份,所有城市" values="${cjrxx.crmprovince},${cjrxx.crmcity}"/>
					</dd>
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(0);">暂存

</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(8);">提交

</button>
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