<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="panel">
<div class="pageContent">
	<form id="jksqTogetherForm" name="jksqTogetherForm" method="post"
		action="${ctx}/jksq/saveJksqTogetherInfo" class="pageForm required-validate"
		onsubmit="return jksqTogetherFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" id="jksqId" name="jksqId" value="${xhjksq.id }"/>
			<input type="hidden" id="toghFlag" name="toghFlag" value="${toghFlag }"/>
			<input type="hidden" id="opt" name="opt" />
			<div class="panel"><h1>共同借款人填写</h1></div>
			<table nowrapTD="false">
				<tr>
					<td><label>共同借款人姓名:</label> 
						<input type="text" id="togetherName" name="togetherName" size="30" value="" class="required" />
					</td>
					<td><label>年龄:</label> 
						<input type="text" id="age" name="age" size="30" value="" class="number"
							minlength="1" maxlength="3"/>
					</td>
					<td><label>性别：</label> 
						<input type="radio" id="genders" name="genders" value="男"  checked="checked" />男&nbsp; 
						<input type="radio" id="genders" name="genders" value="女"  />女
					</td>
				</tr>
				<tr>
					<td><label>身份证号码：</label> 
						<input type="text" id="identification" name="identification" size="30" class="required" value="" />
					</td>
					<td><label>户籍地址：</label> 
						<input type="text" id="hjadress" name="hjadress" size="30" class="required" value="" />
					</td>
					<td><label>家庭电话：</label> 
						<input type="text" id="homePhone" name="homePhone" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td><label>手机：</label> 
						<input type="text" id="telephone" name="telephone" size="30" class="digits required" value="" />
					</td>
					<td><label>现住址：</label> 
						<input type="text" id="address" name="address" size="30" class="required" value="" />
					</td>
					<td><label>住址现电话：</label> 
						<input type="text" id="addressPhone" name="addressPhone" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td><label>邮箱：</label> 
						<input type="text" id="email" name="email" size="30" class="required email" value="" />
					</td>
					<td><label>工作单位：</label> 
						<input type="text" id="company" name="company" size="30" class="required" value="" />
					</td>
					<td><label>单位电话：</label> 
						<input type="text" id="companyPhone" name="companyPhone" size="30" class="required" value="" />
					</td>
				</tr>
				<tr>
					<td><label>QQ号码：</label> 
						<input type="text" id="qqhm" name="qqhm" size="30" class="digits" value="" />
					</td>
					<td><label>单位地址：</label> 
						<input type="text" id="companyAdress" name="companyAdress" size="30" class="required" value="" />
					</td>
					<td><label>部门名称：</label> 
						<input type="text" id="department" name="department" size="30" class="" value="" />
					</td>
				</tr>
				<tr>
					<td><label>职务：</label> 
						<input type="text" id="function" name="function" size="30" class="required" value="" />
					</td>
					<td><label>婚姻状况：</label> 
						<select id="maritalStatus" name="maritalStatus" class="combox ">
							<c:forEach items="${hyzk0009}" var="per">
								<option value="${per.value }">${per.name }</option>
							</c:forEach>
						</select>
					</td>
					<td><label>有无子女：</label> 
						<select id="ywzn" name="ywzn" class="combox ">
							<c:forEach items="${ywzn0018}" var="per">
								<option value="${per.value }" >${per.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>居住状况：</label> 
						<input type="radio" id="liveState" name="liveState" value="01" class="required" 
							 onclick="liveStateTogether('liveMessage01')" />自购房屋&nbsp;&nbsp;
						<input type="radio" id="liveState" name="liveState" value="02" class="required" 
							onclick="liveStateTogether('liveMessage02')" />借款购置房屋&nbsp;&nbsp;
						<input type="radio" id="liveState" name="liveState" value="03" class="required" 
							onclick="liveStateTogether('liveMessage03')" />借款购置房屋
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="04" class="required" 
							onclick="liveStateTogether('liveMessage04')" />租房，房租</td>
					<td>
						<input type="text" id="liveMessage04" name="liveMessage" value="" disabled="disabled"/>元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="05" class="required" 
							onclick="liveStateTogether('liveMessage05')" />其他，说明：
						</td>
					<td>
						<input type="text" id="liveMessage05" name="liveMessage" size="80" value="" disabled="disabled"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="1"><label>主要收入来源：</label> 
						<input type="checkbox" id="cBoxMonthlySalary" name="cBoxMonthlySalary" value="01" 
							 onclick="selectOne(this,'monthlySalaryIndexTogether')" />每月工资(含奖金及补助)
					</td>
					<td colspan="2">
						<input type="text" id="monthlySalaryIndexTogether" name="monthlySalary" 
							value="" disabled="disabled" class="digits"/>元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="checkbox" id="cBoxRental" name="cBoxRental" value="01" 
							onclick="selectOne(this,'rentalIndexTogether')" />房屋出租
					</td>
					<td colspan="2">
						<input type="text" id="rentalIndexTogether" name="rental" value="" 
							disabled="disabled" class="digits"/>元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="checkbox" id="cBoxOtherIncome" name="cBoxOtherIncome" value="01" 
							onclick="selectOne(this,'otherIncomeIndexTogether')" />其他所得
					</td>
					<td colspan="2">
						<input type="text" id="otherIncomeIndexTogether" name="otherIncome" 
							value="" disabled="disabled" class="digits"/>元/年
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="checkbox" id="cBoxAnnualIncome" name="cBoxAnnualIncome" value="01"
							onclick="selectOne(this,'annualIncomeIndexTogether')" />年总收入</td>
					<td colspan="2">
						<input type="text" id="annualIncomeIndexTogether" name="annualIncome" 
							value="" disabled="disabled" class="digits"/>元
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label>
					<input type="checkbox" id="cBoxSocialFund" name="cBoxSocialFund" value="01"
							onclick="selectOne(this,'socialFundIndexTogether')" />是否拥有社保基金： </td>
					<td>
						<input type="radio" id="socialFundIndexTogether1" name="socialFund" 
							value="是" disabled="disabled" />是
						<input type="radio" id="socialFundIndexTogether2" name="socialFund" 
							value="否" checked="checked" disabled="disabled"/>否
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> </td>
					<td></td>
				</tr>
				<tr>
					<td colspan="1"><label>申请额度：</label> 
						<input type="text" id="loanQuota" name="loanQuota" size="30" class="required number" value="" />
					</td>
					<td><label>申请还款期限：</label> 
						<input type="text" id="jkCycle" name="jkCycle" size="30" class="required digits" value="" />
					</td>
					<td><label></label> 
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>还款资金来源：</label> 
						<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="01" class="required" 
							onclick="sourceOfFunds1('01')" />独立还款&nbsp;&nbsp;
						<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="02" class="required" 
							onclick="sourceOfFunds1('02')" />家人协助还款&nbsp;&nbsp;
					</td>
					
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="03" class="required" 
							onclick="sourceOfFunds1('03')" />其他方式
					</td>
					<td align="left">
						<input type="text" id="sourceOfFundsInfo" name="sourceOfFundsInfo" size="30" value="" disabled="disabled"/>
					</td>
					
				</tr>
			</table>
			<br />
		
			<div class="panel"><h1>紧急联系人信息</h1></div>
			
			<table class="table" width="100%">
				<thead>
					<tr>
						<th width="60">联系人</th>
						<th width="60">姓名</th>
						<th width="90">工作单位</th>
						<th width="100">单位地址或家庭地址</th>
						<th width="80">联系电话</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">亲属<input type="hidden" name="ybrgx1" value="亲属"></td>
						<td align="left"><input type="text" name="name1"></td>
						<td align="left"><input type="text" name="jjlxrgzdw1"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz1"></td>
						<td align="left"><input type="text" name="jjlxrlxdh1"></td>
					</tr>
					<tr>
						<td align="center">亲属<input type="hidden" name="ybrgx2" value="亲属"></td>
						<td align="left"><input type="text" name="name2"></td>
						<td align="left"><input type="text" name="jjlxrgzdw2"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz2"></td>
						<td align="left"><input type="text" name="jjlxrlxdh2"></td>
					</tr>
					<tr>
						<td align="center">朋友<input type="hidden" name="ybrgx3" value="朋友"></td>
						<td align="left"><input type="text" name="name3"></td>
						<td align="left"><input type="text" name="jjlxrgzdw3"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz3"></td>
						<td align="left"><input type="text" name="jjlxrlxdh3"></td>
					</tr>
					<tr>
						<td align="center">朋友<input type="hidden" name="ybrgx4" value="朋友"></td>
						<td align="left"><input type="text" name="name4"></td>
						<td align="left"><input type="text" name="jjlxrgzdw4"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz4"></td>
						<td align="left"><input type="text" name="jjlxrlxdh4"></td>
					</tr>
					<tr>
						<td align="center">同事<input type="hidden" name="ybrgx5" value="同事"></td>
						<td align="left"><input type="text" name="name5"></td>
						<td align="left"><input type="text" name="jjlxrgzdw5"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz5"></td>
						<td align="left"><input type="text" name="jjlxrlxdh5"></td>
					</tr>
					<tr>
						<td align="center">同事<input type="hidden" name="ybrgx6" value="同事"></td>
						<td align="left"><input type="text" name="name6"></td>
						<td align="left"><input type="text" name="jjlxrgzdw6"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz6"></td>
						<td align="left"><input type="text" name="jjlxrlxdh6"></td>
					</tr>
				</tbody>
			</table>
			
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