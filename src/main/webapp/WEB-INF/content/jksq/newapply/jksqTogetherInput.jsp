<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="panel">
<div class="pageContent">
	<form id="jksqTogetherForm" name="jksqTogetherForm" method="post"
		action="${ctx}/xhNewJksq/saveJksqTogetherInfo" class="pageForm required-validate"
		onsubmit="return jksqTogetherFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden"  name = "id" value ="${together.id}"/>
			<input type="hidden" id="xhjksq.id" name="jksqId" value="${xhjksq.id }"/>
			<div class="panel"><h1>共同借款人</h1>
			<table  class="list"  width="100%">
			<tbody>
				<tr>
					<td><label>共同借款人姓名:</label> 
						<input type="text" id="togetherName" name="togetherName" size="30" value="${together.togetherName}" class="required" />
					</td>
					<td><label>年龄:</label>
						<input type="text" id="age" name="age" size="30" value="${together.age}" class="number" minlength="1" maxlength="3"/>
					</td>
					<td><label>性别：</label> 
						<input type="radio" id="genders" name="genders" value="男" <c:if test='${together.genders eq "男" }'>checked="checked"</c:if>  />男&nbsp; 
						<input type="radio" id="genders" name="genders" value="女" <c:if test='${together.genders eq "女" }'>checked="checked"</c:if>  />女
					</td>
				</tr>
				<tr>
					<td><label>身份证号码：</label> 
						<input type="text" id="identification" name="identification" size="30" class="required" value="${together.identification}" />
					</td>
					<td><label>户籍地址：</label>
						<input type="text" id="hjadress" name="hjadress" size="30" class="required" value="${together.hjadress}" />
					</td>
					<td><label>家庭电话：</label> 
						<input type="text" id="homePhone" name="homePhone" size="30" class="required" value="${together.homePhone}" />
					</td>
				</tr>
				<tr>
					<td><label>手机：</label> 
						<input type="text" id="telephone" name="telephone" size="30" class="digits required" value="${together.telephone}" />
					</td>
					<td><label>现住址：</label> 
						<input type="text" id="address" name="address" size="30" class="required" value="${together.address}" />
					</td>
					<td><label>住址现电话：</label> 
						<input type="text" id="addressPhone" name="addressPhone" size="30" class="required" value="${together.addressPhone}" />
					</td>
				</tr>
				<tr>
					<td><label>邮箱：</label> 
						<input type="text" id="email" name="email" size="30" class="required email" value="${together.email}" />
					</td>
					<td><label>工作单位：</label> 
						<input type="text" id="company" name="company" size="30" class="required" value="${together.company}" />
					</td>
					<td><label>单位电话：</label> 
						<input type="text" id="companyPhone" name="companyPhone" size="30" class="required" value="${together.companyPhone}" />
					</td>
				</tr>
				<tr>
					<td><label>QQ号码：</label> 
						<input type="text" id="qqhm" name="qqhm" size="30" class="digits" value="${together.qqhm}" />
					</td>
					<td><label>单位地址：</label> 
						<input type="text" id="companyAdress" name="companyAdress" size="30" class="required" value="${together.companyAdress}" />
					</td>
					<td><label>部门名称：</label> 
						<input type="text" id="department" name="department" size="30" class="" value="${together.department}" />
					</td>
				</tr>
				<tr>
					<td><label>职务：</label> 
						<input type="text" id="function" name="function" size="30" class="required" value="${together.function}" />
					</td>
					<td><label>婚姻状况：</label> 
						<sen:select clazz="combox" name="maritalStatus" coding="marriageType" value="${together.maritalStatus}"/>
					</td>
					<td><label>有无子女：</label> 				
					    <sen:select clazz="combox" name="ywzn" coding="hasChildren" value="${together.ywzn}"/>					
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>居住状况${together.liveState}：</label> 
						<input type="radio" id="liveState" name="liveState" value="01"
						 <c:if test='${together.liveState == "01"}'>checked</c:if> class="required"  onclick="liveStateTogether('liveMessage01')" />自购房屋&nbsp;&nbsp;
						<input type="radio" id="liveState" name="liveState" value="02"
						<c:if test='${together.liveState == "02"}'>checked</c:if> class="required"  onclick="liveStateTogether('liveMessage02')" />借款购置房屋&nbsp;&nbsp;
						<input type="radio" id="liveState" name="liveState" value="03" 
						<c:if test='${together.liveState == "03"}'>checked</c:if> class="required"  onclick="liveStateTogether('liveMessage03')" />借款购置房屋
					</td>
				</tr>
				
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" <c:if test='${together.liveState == "04"}'>checked</c:if> name="liveState" value="04" class="required" onclick="liveStateTogether('liveMessage04')" />租房，房租</td>
					<td colspan="2">
						<input type="text" id="liveMessage04" name="liveMessage" <c:if test='${together.liveState == "04"}'> value="${together.liveMessage}" </c:if> <c:if test='${together.liveState != "04"}'>disabled="disabled"</c:if>/>元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" <c:if test='${together.liveState == "05"}'>checked</c:if> value="05" class="required"  onclick="liveStateTogether('liveMessage05')" />其他，说明：
						</td>
					<td colspan="2">
						<input type="text" id="liveMessage05" name="liveMessage" size="80" <c:if test='${together.liveState == "05"}'> value="${together.liveMessage}" </c:if> <c:if test='${together.liveState != "05"}'>disabled="disabled" </c:if>/>
					</td>
				</tr>
				
				<tr>
					<td colspan="3"><label>主要收入来源：</label> 
						
					</td>
					</tr>
					<tr><td>
					<!-- <input type="checkbox" id="cBoxMonthlySalary" name="cBoxMonthlySalary" value="01"  onclick="selectOne(this,'monthlySalaryIndexTogether')" /> -->每月工资(含奖金及补助)
					</td>
					<td colspan="2">
						<input type="text" id="monthlySalaryIndexTogether" name="monthlySalary"  value="${together.monthlySalary}"  class="digits"/>元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<!-- <input type="checkbox" id="cBoxRental" name="cBoxRental" value="01"  onclick="selectOne(this,'rentalIndexTogether')" /> -->房屋出租
					</td>
					<td colspan="2">
						<input type="text" id="rentalIndexTogether" name="rental" value="${together.rental}"   class="digits"/>元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<!-- <input type="checkbox" id="cBoxOtherIncome" name="cBoxOtherIncome" value="01" onclick="selectOne(this,'otherIncomeIndexTogether')" /> -->其他所得
					</td>
					<td colspan="2">
						<input type="text" id="otherIncomeIndexTogether" name="otherIncome"  value="${together.otherIncome}"  class="digits"/>元/年
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<!-- <input type="checkbox" id="cBoxAnnualIncome" name="cBoxAnnualIncome" value="01" onclick="selectOne(this,'annualIncomeIndexTogether')" /> -->年总收入</td>
					<td colspan="2">
						<input type="text" id="annualIncomeIndexTogether" name="annualIncome"  value="${together.annualIncome}"  class="digits"/>元
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label>
					<!-- <input type="checkbox" id="cBoxSocialFund" name="cBoxSocialFund" value="01"	onclick="selectOne(this,'socialFundIndexTogether')" /> -->是否拥有社保基金： </td>
					<td colspan="2">
						<input type="radio" id="socialFundIndexTogether1" name="socialFund"  <c:if test='${together.socialFund == "是"}'>checked</c:if> value="是"  />是
						<input type="radio" id="socialFundIndexTogether2" name="socialFund"  <c:if test='${together.socialFund == "否"}'>checked</c:if>  value="否" checked="checked" />否
					</td>
				</tr>
			
				<tr>
					<td colspan="1"><label>申请额度：</label> 
						<input type="text" id="loanQuota" name="loanQuota" size="30" class="required number" value="${xhjksq.jkLoanQuota}" readonly/>
					</td>
					<td><label>申请还款期限：</label> 
						<input type="text" id="jkCycle" name="jkCycle" size="30" class="required digits" value="${xhjksq.jkCycle}" readonly/>
					</td>
					<td><label></label> 
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>还款资金来源：</label> 
						<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="01" class="required" <c:if test='${together.sourceOfFunds == "01"}'>checked</c:if>  onclick="sourceOfFunds1('01')" />独立还款&nbsp;&nbsp;
						<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="02" class="required" <c:if test='${together.sourceOfFunds == "02"}'>checked</c:if> onclick="sourceOfFunds1('02')" />家人协助还款&nbsp;&nbsp;
					</td>
					
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="03" <c:if test='${together.socialFund == "03"}'>checked</c:if> class="required" onclick="sourceOfFunds1('03')" />其他方式
					</td>
					<td align="left" >
						<input type="text" id="sourceOfFundsInfo" name="sourceOfFundsInfo" size="30" value="${together.sourceOfFundsInfo}" disabled="disabled"/>
					</td>
					<td>
				</tr>
				</tbody>
			</table>
		</div>
		
			<div class="panel"><h1>紧急联系人信息</h1>
			
					<table  width="100%" class="list">
				<thead>
				
					<tr>
						<th width="30">联系人</th>
						<th width="30">姓名</th>
						<th width="40">住宅电话</th>
						<th width="40">手机电话</th>
						<th width="100">住址</th>
						<th width="100">单位名称</th>
						<th width="40">单位电话</th>
					</tr>
				
				</thead>
				<tbody>
				   <c:forEach items="1,2,3,4,5,6" varStatus="goIndex" var="nouse">
						<tr>
							<td align="center"><input type="text" name="xhJkjjlxrs[${goIndex.index}].ybrgx" value="${together.xhJkjjlxrs[goIndex.index].ybrgx }"> 
							   <input type="hidden" name="xhJkjjlxrs[${goIndex.index}].id" value="${together.xhJkjjlxrs[goIndex.index].id}" /> 
							   <input type="hidden" name="xhJkjjlxrs[${goIndex.index}].xhJksqTogether.id" value="${together.xhJkjjlxrs[goIndex.index].xhJksqTogether.id}" />
							</td>
							<td align="left"><input type="text" name="xhJkjjlxrs[${goIndex.index}].name" value="${together.xhJkjjlxrs[goIndex.index].name}" />
							</td>
							<!-- 姓名 -->
							<td align="left"><input type="text" name="xhJkjjlxrs[${goIndex.index}].homePhone" class="digits" value="${together.xhJkjjlxrs[goIndex.index].homePhone}" />
							</td>
							<!-- 住宅电话 -->
							<td align="left"><input type="text" name="xhJkjjlxrs[${goIndex.index}].jjlxrlxdh" class="digits" value="${together.xhJkjjlxrs[goIndex.index].jjlxrlxdh}" />
							</td>
							<!-- 手机电话 -->
							<td align="left"><input type="text" name="xhJkjjlxrs[${goIndex.index}].jjlxrdwdzhjtzz" value="${together.xhJkjjlxrs[goIndex.index].jjlxrdwdzhjtzz}" />
							</td>
							<!-- 住址 -->
							<td align="left"><input type="text" name="xhJkjjlxrs[${goIndex.index}].jjlxrgzdw" value="${together.xhJkjjlxrs[goIndex.index].jjlxrgzdw}" />
							</td>
							<!-- 单位名称 -->
							<td align="left"><input type="text" name="xhJkjjlxrs[${goIndex.index}].officePhone" class="digits" value="${together.xhJkjjlxrs[goIndex.index].officePhone}" />
							</td>
							<!-- 单位电话 -->
						</tr>
					</c:forEach>			
				</tbody>
			</table>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="togetherSubmit('1')">提交</button>
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
</div></div>
</div>
<script type="text/javascript">

	function liveStateTogether(state){
		if("liveMessage01" == state){
			document.jksqTogetherForm.liveMessage04.disabled = true;
			document.jksqTogetherForm.liveMessage05.disabled = true;
		}else if("liveMessage02" == state){
			document.jksqTogetherForm.liveMessage04.disabled = true;
			document.jksqTogetherForm.liveMessage05.disabled = true;
		}else if("liveMessage03" == state){
			document.jksqTogetherForm.liveMessage04.disabled = true;
			document.jksqTogetherForm.liveMessage05.disabled = true;
		}else if("liveMessage04" == state){
			document.jksqTogetherForm.liveMessage04.disabled = false;
			document.jksqTogetherForm.liveMessage05.disabled = true;
		}else if("liveMessage05" == state){
			document.jksqTogetherForm.liveMessage04.disabled = true;
			document.jksqTogetherForm.liveMessage05.disabled = false;
		}
	}

	function togetherSubmit(val){
		document.jksqTogetherForm.opt.value = val;
		return true;
	}
	
	
	function jksqTogetherFormSubmit(obj){
		var $form=$(obj);
		debugger;
		if(!$form.valid()){
		return false;}

		return validateCallback(obj, navTabAjaxDone);
	}

	function selectOne(obj,ziduan){
		var zd = document.getElementById(ziduan);
		if (obj.checked){
			if(ziduan == "socialFundIndexTogether"){
				document.getElementById(ziduan+"1").disabled = false;
				document.getElementById(ziduan+"2").disabled = false;
			}else{
				zd.disabled = false;
			}
		} else{
			if(ziduan == "socialFundIndexTogether"){
				document.getElementById(ziduan+"1").disabled = true;
				document.getElementById(ziduan+"2").disabled = true;
			}else{
				zd.disabled = true;
			}
		}

	}
	
	function sourceOfFunds1(state){
		if("01" == state){
			document.jksqTogetherForm.sourceOfFundsInfo.disabled = true;
		}else if("02" == state){
			document.jksqTogetherForm.sourceOfFundsInfo.disabled = true;
		}else if("03" == state){
			document.jksqTogetherForm.sourceOfFundsInfo.disabled = false;
		}
	}
	
</script>