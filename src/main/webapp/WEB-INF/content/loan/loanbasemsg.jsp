<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form id="loanbaseMsgForm" name="loanbaseMsgForm" method="post"
		action="${ctx}/loan/saveLoanBaseMsg"
		class="pageForm required-validate"
		onsubmit="return loantgFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<input id="opt" type="hidden" name="opt"> 客户基本信息
			<div class="divider"></div>
			<table nowrapTD="false">
				<tr>
					<td><label>借款人姓名:</label> 
						<input type="text" id="jkrxm" name="jkrxm" size="30" class="required">
					</td>
					<td><label>英文名:</label> 
						<input type="text" id="englishName" name="englishName" size="30" />
					</td>
					<td><label>性别：</label> 
						<input type="radio" id="genders" name="genders" value="男" class="required" checked="checked" />男&nbsp; 
						<input type="radio" id="genders" name="genders" value="女" class="required" />女
					</td>
				</tr>
				<tr>
					<td><label>出生日期：</label> 
						<input type="text" name="birthday" 	class="date required" 
							pattern="yyyy-MM-dd" size="30" readonly="readonly" />
					</td>
					<td><label>证件类型：</label> 
						<select id="pocertificates" name="pocertificates">
							<c:forEach items="${zjlx0005}" var="per">
								<c:if test="${per.name==pocertificates}">
									<option value="${per.value }" selected="selected">${per.name}</option>
								</c:if>
								<c:if test="${per.value!=pocertificates}">
									<option value="${per.value }">${per.name }</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td><label>证件号码：</label> 
						<input type="text" id="zjhm" name="zjhm" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td><label>户籍地址：</label> 
						<input type="text" id="hjadress" name="hjadress" size="30" class="required" value="" />
					</td>
					<td><label>家庭电话：</label> 
						<input type="text" id="homePhone" name="homePhone" size="30" class="required" value="" />
					</td>
					<td><label>工作单位：</label> 
						<input type="text" id="company" name="company" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td><label>单位电话：</label> 
						<input type="text" id="companyPhone" name="companyPhone" size="30" class="required" value="" />
					</td>
					<td><label>单位地址：</label> 
						<input type="text" id="companyAdress" name="companyAdress" size="30" class="required" value="" />
					</td>
					<td><label>单位性质：</label> 
						<select id="companyNature" name="companyNature">
							<c:forEach items="${dwxz0006}" var="per">
								<option value="${per.value }">${per.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>移动电话：</label> 
						<input type="text" id="telephone" name="telephone" size="30" class="required" value="" />
					</td>
					<td><label>电子邮箱：</label> 
						<input type="text" id="email" name="email" size="30" class="required" value="" />
					</td>
					<td><label>婚姻状况：</label> 
						<select id="maritalStatus" name="maritalStatus">
							<c:forEach items="${hyzk0009}" var="per">
								<option value="${per.value }">${per.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>有无子女：</label> 
						<input type="text" id="ywzn" name="ywzn" size="30" class="required" value="" />
					</td>
					<td><label>QQ号码：</label> 
						<input type="text" id="qqhm" name="qqhm" size="30" class="required" value="" />
					</td>
					<td><label>年收入：</label> 
						<input type="text" id="annualIncome" name="annualIncome" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td><label>收入说明：</label> 
						<input type="text" id="incomeIllustration" name="incomeIllustration" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td colspan="1"><label>居住状态：</label> 
						<input type="radio" id="liveState" name="liveState" value="01" 
							checked="checked" onclick="livestate('liveMessage01')" />自有房屋，有无借款，月供
					</td>
					<td>
						<input id="liveMessage01" name="liveMessage" type="text" value="" />元
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="02" 
							onclick="livestate('liveMessage02')" />亲属产权
					</td>
					<td><input type="hidden" id="liveMessage02" name="liveMessage" 	value="" /></td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="03"
							onclick="livestate('liveMessage03')" />租房，房租</td>
					<td>
						<input type="text" id="liveMessage03" name="liveMessage" value="" />元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="04"
							onclick="livestate('liveMessage04')" />其他，说明：
						</td>
					<td>
						<input type="text" id="liveMessage04" name="liveMessage" size="80" value="" />
					</td>
				</tr>
			</table>
			<br />
			<div id="isMarray">
				<div class="divider"></div>
				以下为配偶信息（借款人已婚：必填）
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>姓名:</label> 
							<input type="text" id="spouseName" name="spouseName" size="30">
						</td>
						<td><label>性别：</label> 
							<input type="radio" id="spouseGenders" name="spouseGenders" value="男" checked="checked" />男&nbsp; 
							<input type="radio" id="spouseGenders" name="spouseGenders" value="女" />女
						</td>
						<td><label>出生日期：</label> 
							<input type="text" name="spouseBirthday" class="date required" 
								pattern="yyyy-MM-dd" size="30" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td><label>现住址：</label> 
							<input type="text" id="spouseAdress" name="spouseAdress"  />
						</td>
						<td><label>证件类型：</label> 
							<select id="spousePocertificates" name="spousePocertificates">
								<c:forEach items="${zjlx0005}" var="per">
									<option value="${per.value }">${per.name }</option>
								</c:forEach>
							</select>
						</td>
						<td><label>证件号码：</label> 
							<input type="text" id="spouseZjhm" name="spouseZjhm" size="30" value="" />
						</td>
					</tr>
					<tr>
						<td><label>移动电话：</label> 
							<input type="text" id="spouseTelephone" name="spouseTelephone" size="30" value="" />
						</td>
						<td><label>家庭电话：</label> 
							<input type="text" id="spouseHomePhone" name="spouseHomePhone" size="30" value="" />
						</td>
						<td><label>工作单位：</label> 
							<input type="text" id="spouseCompany" name="spouseCompany" size="30" value="" />
						</td>
					</tr>
					<tr>
						<td><label>部门与职务：</label> 
							<input type="text" id="spouseDepFunction" name="spouseDepFunction" size="30" value="" />
						</td>
						<td><label>单位电话：</label> 
							<input type="text" id="spouseCompanyPhone" name="spouseCompanyPhone" size="30" value="" />
						</td>
						<td><label>单位地址：</label> 
							<input type="text" id="spouseCompanyAdress" name="spouseCompanyAdress" size="30" value="" />
						</td>
					</tr>

					<tr>
						<td><label>年收入：</label> 
							<input type="text" id="spouseAnnualIncome" name="spouseAnnualIncome" size="30" value="" />
						</td>
						<td><label>工作年限：</label> 
							<input type="text" id="spouseWorkinglife" name="spouseWorkinglife" size="30" value="" />
						</td>
					</tr>

				</table>
				<div class="divider"></div>
				联系人信息
				<div class="divider"></div>
				<table class="table" width="100%">
					<thead>
						<tr>
							<th width="60">联系人</th>
							<th width="60">姓名</th>
							<!-- 
				<th width="70" >与本人关系</th>
				 -->
							<th width="90">工作单位</th>
							<th width="100">单位地址或家庭地址</th>
							<th width="80">联系电话</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center">亲属</td>
							<td align="left"><input type="text" name="name1"></td>
							<td align="left"><input type="text" name="jjlxrgzdw1"></td>
							<!-- 
				<td align="left">
					<select id="ybrgx" name="ybrgx">
						<c:forEach items="${lxrlx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
					</select>
				</td>
				 -->
							<td align="left"><input type="text" name="jjlxrdwdzhjtzz1"></td>
							<td align="left"><input type="text" name="jjlxrlxdh1"></td>
						</tr>
						<tr>
							<td align="center">亲属</td>
							<td align="left"><input type="text" name="name2"></td>
							<td align="left"><input type="text" name="jjlxrgzdw2"></td>
							<!-- 
				<td align="left">
					<select id="ybrgx" name="ybrgx">
						<c:forEach items="${lxrlx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
					</select>
				</td>
				 -->
							<td align="left"><input type="text" name="jjlxrdwdzhjtzz2"></td>
							<td align="left"><input type="text" name="jjlxrlxdh2"></td>
						</tr>
						<tr>
							<td align="center">朋友</td>
							<td align="left"><input type="text" name="name3"></td>
							<td align="left"><input type="text" name="jjlxrgzdw3"></td>
							<!-- 
				<td align="left">
					<select id="ybrgx" name="ybrgx">
						<c:forEach items="${lxrlx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
					</select>
				</td>
				 -->
							<td align="left"><input type="text" name="jjlxrdwdzhjtzz3"></td>
							<td align="left"><input type="text" name="jjlxrlxdh3"></td>
						</tr>
						<tr>
							<td align="center">朋友</td>
							<td align="left"><input type="text" name="name4"></td>
							<td align="left"><input type="text" name="jjlxrgzdw4"></td>
							<!-- 
				<td align="left">
					<select id="ybrgx" name="ybrgx">
						<c:forEach items="${lxrlx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
					</select>
				</td>
				 -->
							<td align="left"><input type="text" name="jjlxrdwdzhjtzz4"></td>
							<td align="left"><input type="text" name="jjlxrlxdh4"></td>
						</tr>
						<tr>
							<td align="center">同事</td>
							<td align="left"><input type="text" name="name5"></td>
							<td align="left"><input type="text" name="jjlxrgzdw5"></td>
							<!-- 
				<td align="left">
					<select id="ybrgx" name="ybrgx">
						<c:forEach items="${lxrlx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
					</select>
				</td>
				 -->
							<td align="left"><input type="text" name="jjlxrdwdzhjtzz5"></td>
							<td align="left"><input type="text" name="jjlxrlxdh5"></td>
						</tr>
						<tr>
							<td align="center">同事</td>
							<td align="left"><input type="text" name="name6"></td>
							<td align="left"><input type="text" name="jjlxrgzdw6"></td>
							<!-- 
				<td align="left">
					<select id="ybrgx" name="ybrgx">
						<c:forEach items="${lxrlx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
					</select>
				</td>
				 -->
							<td align="left"><input type="text" name="jjlxrdwdzhjtzz6"></td>
							<td align="left"><input type="text" name="jjlxrlxdh6"></td>
						</tr>
					</tbody>
				</table>
				<div class="divider"></div>
				借款信息
				<div class="divider"></div>
				<table border="0" bordercolor="red">
					<tr>
						<td><label>借款申请额度:</label> <input id="jkLoanQuota"
							name="jkLoanQuota" type="text" size="30" class="required">
						</td>
						<td><label>申请借款期限:</label> <input id="jkCycle" name="jkCycle"
							type="text" size="30" value="" /></td>
						<td><label>还款方式：</label> <input id="hkWay" name="hkWay"
							type="text" size="30" value="" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<dl class="nowrap">
								<dt>借款用途：</dt>
								<dd>
									<textarea id="jkUse" name="jkUse" style="width: 93%;"></textarea>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td colspan="3"><label>借款类型：</label> <input id="jkUse"
							name="jkUse" type="radio" value="A" onclick="jkUsestate(this)" />老板借&nbsp;
							<input id="jkUse" name="jkUse" type="radio" value="B"
							onclick="jkUsestate(this)" />老板楼易借&nbsp; <input id="jkUse"
							name="jkUse" type="radio" value="C" onclick="jkUsestate(this)" />薪水借&nbsp;
							<input id="jkUse" name="jkUse" type="radio" value="D"
							onclick="jkUsestate(this)" />薪水楼易借&nbsp; <input id="jkUse"
							name="jkUse" type="radio" value="E" onclick="jkUsestate(this)" />精英借&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="lbd_div" name="lbd_div" style="display: none;">
								${lbd }</div>

						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="lblyd_div" name="lblyd_div" style="display: none;">
								${lblyd }</div>

						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="xsd_div" name="xsd_div" style="display: none;">
								${xsd }</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="xslyd_div" name="xslyd_div" style="display: none;">
								${xslyd }</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="jyd_div" name="jyd_div" style="display: none;">
								${jyd }</div>
						</td>
					</tr>
					<tr>
						<td><label>共同还款人：</label> <input id="togetherPerson"
							name="togetherPerson" type="radio" value="是" checked="checked" />是&nbsp;
							<input id="togetherPerson" name="togetherPerson" type="radio"
							value="否" />否&nbsp;</td>
					</tr>
					<tr>
						<td><label>开户行：</label> <input id="bankOpen" name="bankOpen"
							type="text" /></td>
						<td><label>账户号码：</label> <input id="bankNum" name="bankNum"
							type="text" /></td>
						<td><label>账户名称：</label> <input id="bankUsername"
							name="bankUsername" type="text" /></td>
					</tr>
					<tr>
						<td><label>申请日期：</label> <input type="text" id="jkLoanDate"
							name="jkLoanDate" class="date required" pattern="yyyy-MM-dd"
							size="30" readonly="readonly" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<div></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="ysubmit('0')">暂存</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="ysubmit('1')">提交</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="reset" class="reset">清空</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function jkUsestate(obj){
		var state = obj.value;
		if("A" == state){
//			document.loanbaseMsgForm.lbd_div.style.display = "block";//老板借
			document.getElementById('lbd_div').style.display = "block";
//			document.loanbaseMsgForm.lblyd_div.style.display = "none";//老板楼易借
			document.getElementById('lblyd_div').style.display = "none";
//			document.loanbaseMsgForm.xsd_div.style.display = "none";//薪水借
			document.getElementById('xsd_div').style.display = "none";
//			document.loanbaseMsgForm.xslyd_div.style.display = "none";//薪水楼易借
			document.getElementById('xslyd_div').style.display = "none";
//			document.loanbaseMsgForm.jyd_div.style.display = "none";//精英借
			document.getElementById('jyd_div').style.display = "none";
		}else if("B" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "block";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
		}else if("C" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "block";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
		}else if("D" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "block";
			document.getElementById('jyd_div').style.display = "none";
		}else if("E" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "block";
		}
		
	}
	

	function livestate(state){
		if("liveMessage01" == state){
			document.loanbaseMsgForm.liveMessage01.disabled = false;
			document.loanbaseMsgForm.liveMessage02.disabled = true;
			document.loanbaseMsgForm.liveMessage03.disabled = true;
			document.loanbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage02" == state){
			document.loanbaseMsgForm.liveMessage01.disabled = true;
			document.loanbaseMsgForm.liveMessage02.disabled = false;
			document.loanbaseMsgForm.liveMessage03.disabled = true;
			document.loanbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage03" == state){
			document.loanbaseMsgForm.liveMessage01.disabled = true;
			document.loanbaseMsgForm.liveMessage02.disabled = true;
			document.loanbaseMsgForm.liveMessage03.disabled = false;
			document.loanbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage04" == state){
			document.loanbaseMsgForm.liveMessage01.disabled = true;
			document.loanbaseMsgForm.liveMessage02.disabled = true;
			document.loanbaseMsgForm.liveMessage03.disabled = true;
			document.loanbaseMsgForm.liveMessage04.disabled = false;
		}
	}

	function ysubmit(val){
		document.loanbaseMsgForm.opt.value = val;
		return true;
	}
	
	
	function loantgFormSubmit(obj){
		var loantgForm = document.getElementById('loanbaseMsgForm');
		var $form=$(loantgForm);
		if(!$form.valid()){
		return false;}

		return validateCallback(loantgForm, navTabAjaxDone);
	}

	function initLoanBaseXhcfDkrxx(){
		var liveStateArray = document.loanbaseMsgForm.liveState;
		var liveState = "";
		 for(var i=0;i<liveStateArray.length;i++){
             if(liveStateArray[i].checked){
            	 liveState = liveStateArray[i].value;
                 break;
             }
         }

		if("01" == liveState){
			document.loanbaseMsgForm.liveMessage01.value = "${xhcfDkrxx.liveMessage }";
			document.loanbaseMsgForm.liveMessage02.disabled = true;
			document.loanbaseMsgForm.liveMessage03.disabled = true;
			document.loanbaseMsgForm.liveMessage04.disabled = true;
		}else if("02" == liveState){
			document.loanbaseMsgForm.liveMessage01.disabled = true;
			document.loanbaseMsgForm.liveMessage02.value = "${xhcfDkrxx.liveMessage }";
			document.loanbaseMsgForm.liveMessage03.disabled = true;
			document.loanbaseMsgForm.liveMessage04.disabled = true;
		}else if("03" == liveState){
			document.loanbaseMsgForm.liveMessage01.disabled = true;
			document.loanbaseMsgForm.liveMessage02.disabled = true;
			document.loanbaseMsgForm.liveMessage03.value = "${xhcfDkrxx.liveMessage }";
			document.loanbaseMsgForm.liveMessage04.disabled = true;
		}else if("04" == liveState){
			document.loanbaseMsgForm.liveMessage01.disabled = true;
			document.loanbaseMsgForm.liveMessage02.disabled = true;
			document.loanbaseMsgForm.liveMessage03.disabled = true;
			document.loanbaseMsgForm.liveMessage04.value = "${xhcfDkrxx.liveMessage }";
		}
	}
	initLoanBaseXhcfDkrxx();
	
</script>