<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel">
	<div class="pageContent">
		<form name="houseLoanApplyForm" method="post"
			action="${ctx}/houseApply/saveXhHouseLoanApply"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="consultId" value="${consultId}" />
			<input type="hidden" name="jksqId" value="${jksqId}" />
			 <input
				type="hidden" name="id" value="${xhHouseLoanApply.id}" />
			<input type="hidden" id="opt" name="opt" value="0"/>
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>申请事项：</label> <select name="applyType" class="combox">
						<option selected value="">请选择</option>
						<option value="1">一次抵押</option>
						<option value="2">二次抵押</option>
					</select>
					
					<!-- ${xhHouseLoanApply.applyType} -->
				</p>
				<p>
					<label>产权证号：</label> <input name="houseRightNum" type="text"
						size="30" value="${xhHouseLoanApply.houseRightNum}" class="required"
						maxlength="50" />
				</p>

				<p>
					<label>核定利率：</label> <input name="fixedRate" type="text" size="30"
						value="${xhHouseLoanApply.fixedRate}" class="" maxlength="22" />
				</p>
				<div class="divider"></div>

				<p>
					<label>借款人姓名：</label> <input name=loanerName type="text" size="30"
						value="${xhHouseLoanConsult.customerName}" class="required" maxlength="50" />
				</p>
				<p>
					<label>借款人性别：</label> <select name="loanerSex" class="combox">
						<option selected value="">请选择</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</p>
				<p>
					<label>借款人身份证号：</label> <input name="loanerIdNumber" type="text"
						size="30" value="${xhHouseLoanApply.loanerIdNumber}" class="required"
						maxlength="50" />
				</p>
				<p>
					<label>借款人年龄：</label> <input name="loanerAge" type="text" size="7"
						value="${xhHouseLoanApply.loanerAge}" class="" maxlength="22" />
				</p>
								<p>
					<label>省份*：</label>
					<select name="province" class="combox required">
					<option value="">请选择</option>
					<option value="北京">北京</option>
					<option value="吉林">吉林</option>
					<option value="黑龙江">黑龙江</option>
					<option value="辽宁">辽宁</option>
					
					</select>	
					
				</p>
								<p>
					<label>城市：</label> 
					
					<select name="city" class="combox required">
					<option value="">请选择</option>
					<option value="北京">北京</option>
					<option value="长春">长春</option>
					<option value="哈尔滨">哈尔滨</option>
					<option value="大连">大连</option>
					</select>
					
				</p>
				<div class="divider"></div>
				<p>
					<label>产权人姓名：</label> <input name="houseOwnerName" type="text"
						size="30" value="${xhHouseLoanApply.houseOwnerName}" class="required"
						maxlength="50" />
				</p>
				<p>
					<label>产权人性别：</label> <select name="houseOwnerSex" class="combox">
						<option selected value="">请选择</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>

				</p>
				<p>
					<label>产权人身份证号：</label> <input name="houseOwnerIdNum" type="text"
						size="30" value="${xhHouseLoanApply.houseOwnerIdNum}" class=""
						maxlength="50" />
				</p>
				<p>
					<label>产权人年龄：</label> <input name="houseOwnerAge" type="text"
						size="7" value="${xhHouseLoanApply.houseOwnerAge}" class=""
						maxlength="22" />
				</p>
				<div class="divider"></div>
				<p>
					<label>共有权人姓名：</label> <input name="houseJointName" type="text"
						size="30" value="${xhHouseLoanApply.houseJointName}" class=""
						maxlength="50" />
				</p>
				<p>
					<label>共有权人性别：</label> <select name="houseJointSex" class="combox">
						<option selected value="">请选择</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>

				</p>
				<p>
					<label>共有权人身份证号：</label> <input name="houseJointIdNum" type="text"
						size="30" value="${xhHouseLoanApply.houseJointIdNum}" class=""
						maxlength="50" />
				</p>
				<p>
					<label>共有权人年龄：</label> <input name="houseJointAge" type="text"
						size="7" value="${xhHouseLoanApply.houseJointAge}" class=""
						maxlength="22" />
				</p>
				<div class="divider"></div>
				<h1>婚姻状况</h1>
				<div class="divider"></div>
				<p>
					<label>婚姻状况：</label> <select name="marital" class="combox">
						<option selected value="">请选择</option>
						<option value="1">未婚</option>
						<option value="0">已婚</option>
						<option value="0">丧偶</option>
						<option value="0">离异</option>

					</select>
					<!-- 	<input name="marital" type="text" size="30" value="${xhHouseLoanApply.marital}" class="" maxlength="50" /> -->
				</p>
				<p>

					<label>有无子女：</label> <select name="hasChild" class="combox">
						<option selected value="">请选择</option>
						<option value="1">有</option>
						<option value="0">无</option>
					</select>
					<!-- <input name="hasChild" type="text" size="30" value="${xhHouseLoanApply.hasChild}" class="" maxlength="50" /> -->
				</p>
				<div class="divider"></div>
				<dl class="nowrap">
					<dt>借款人户籍地址：</dt>
					<dd>
						<textarea name="hjadress" style="width: 100%;">${hjadress}</textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>其他住所地址：</dt>
					<dd>
						<textarea name="" style="width: 100%;"></textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>房屋座落：</dt>
					<dd>
						<textarea name="houseAddress" style="width: 100%;">${xhHouseLoanApply.houseAddress}</textarea>
					</dd>
				</dl>
				<p>
					<label>房屋面积：</label> <input name="houseArea" type="text" size="30"
						value="${xhHouseLoanApply.houseArea}" class="" maxlength="25" />
				</p>
				<p>
					<label>产权价值：</label> <input name="houseRightValue" type="text"
						size="30" value="${xhHouseLoanApply.houseRightValue}" class=""
						maxlength="50" />
				</p>


				<p class="nowrap">
					<label>有无抵押</label><input type="radio" name="isMort" value="0"
						checked>无 <input type="radio" name="isMort" value="1">有
				</p>
				<p>
					<label> 抵押金额：</label><input name="mortgAount" type="text" size="30"
						value="${xhHouseLoanApply.mortgAount}" class="" maxlength="50" />
				</p>
				<p>
					<label>手机：</label> <input name="telephone" type="text" size="30"
						value="${telephone}" class="" maxlength="32" />
				</p>
				<p>
					<label>住址电话：</label> <input name="homePhone" type="text" size="30"
						value="${xhHouseLoanApply.homePhone}" class="" maxlength="25" />
				</p>
				<p>
					<label>老家宅电：</label> <input name="oldHomePhone" type="text"
						size="30" value="${xhHouseLoanApply.oldHomePhone}" class=""
						maxlength="50" />
				</p>

				<p>
					<label>工作单位：</label> <input name="companyAdress" type="text"
						size="30" value="${xhHouseLoanApply.companyAdress}" class=""
						maxlength="50" />
				</p>
				<p>

					<label>是否自营公司：</label> <select name="" class="combox">
						<option selected value="">请选择</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
					<!-- <input name="isOwnCompany" type="text" size="30" value="${xhHouseLoanApply.isOwnCompany}" class="" maxlength="25" /> -->
				</p>
				<dl class="nowrap">
					<dt>工作单位地址：</dt>
					<dd>
						<textarea name="remark" style="width: 100%;">${xhHouseLoanApply.spCompAdress}</textarea>
					</dd>
				</dl>
				<p>
					<label>单位电话：</label> <input name="companyPhone" type="text"
						size="30" value="${xhHouseLoanApply.companyPhone}" maxlength="32" />
				</p>
				<p>
					<label>单位性质：</label> <select name="companyNature" class="combox">
						<option selected value="">请选择</option>
						<option value="1">国企</option>
						<option value="2">外企</option>
						<option value="2">私企</option>
						<option value="2">合资</option>
						<option value="2">其它</option>
					</select>
					<!--  <input name="companyNature" type="text" size="30" value="${xhHouseLoanApply.companyNature}" class="" maxlength="32" />-->
				</p>
				<p>
					<label>注册资金：</label> <input name="" type="text" size="30"
						value="${xhHouseLoanApply.companyPhone}" class="" maxlength="32" />
				</p>
				<p>
					<label>公司人数：</label> <input name="" type="text" size="30" value=""
						class="" maxlength="32" />
				</p>
				<p>
					<label>身体状况：</label> <select name="companyNature" class="combox">
						<option selected value="">请选择</option>
						<option value="1">良好</option>
						<option value="0">一般</option>
						<option value="0">较差</option>
					</select>


				</p>
				<p>
					<label>学历：</label> <select name="" class="combox">
						<option selected value="">请选择</option>
						<option value="1">研究生及以上</option>
						<option value="2">本科</option>
						<option value="3">大专</option>
						<option value="4">中专或高中</option>
					</select>


				</p>

				<div class="divider"></div>

				<p>
					<label>申请借款额度：</label> <input name="loanLoanAmount" type="text"
						size="30" value="${xhHouseLoanApply.loanLoanAmount}" class="required"
						maxlength="22" />
				</p>
				<p>
					<label>申请借款期限：</label> <input name="loanMonth" type="text"
						size="30" value="${xhHouseLoanApply.loanMonth}" class="required"
						maxlength="22" />
				</p>
				<p>
					<label>还款方式*：</label> <select name="backMoneyType" class="combox required">
						<option selected value="">请选择</option>
						<option value="1">独立还款</option>
						<option value="0">家人协助还款</option>
						<option value="0">其他方式还款</option>
					</select>
					<!-- <input name="backMoneyType" type="text" size="30" value="${xhHouseLoanApply.backMoneyType}" class="" maxlength="25" /> -->
				</p>

				<p>
					<label>申请日期：</label> <input name="loanApplyDate" type="text"
						size="30" value='<fmt:formatDate value="${xhHouseLoanApply.loanApplyDate}"pattern="yyyy-MM-dd" />'
						class="date required"
						maxlength="30" 
							 /><a class="inputDateButton" href="#">选择</a>
							 
							 
							
				</p>
				<p>
					<label>借款用途：*</label> 
					<select name="loanUse" class="combox required"><option value="" selected>请选择</option><option value="1">经营</option><option value="2">消费</option><option value="3">周转</option></select>
					
							 
							 
							
				</p>
				<!--  
				<dl class="nowrap">
					<dt>借款用途：</dt>
					<dd>
						<textarea name="loanUse" class="required" style="width: 100%;">${xhHouseLoanApply.loanUse}</textarea>
					</dd>
				</dl>-->


				<div class="divider"></div>





				<!-- 
			<p>
				<label>组织机构：</label>
				<input name="organiId" type="text" size="30" value="${xhHouseLoanApply.organiId}" class="" maxlength="22" />
			</p>
			<p>
				<label>区域：</label>
				<input name="area" type="text" size="30" value="${xhHouseLoanApply.area}" class="" maxlength="16" />
			</p>
			<p>
				<label>城市：</label>
				<input name="city" type="text" size="30" value="${xhHouseLoanApply.city}" class="" maxlength="16" />
			</p>
			<p>
				<label>省份：</label>
				<input name="province" type="text" size="30" value="${xhHouseLoanApply.province}" class="" maxlength="16" />
			</p> -->

				<div class="divider"></div>




				<h1>配偶信息</h1>
				<div class="divider"></div>
				<p>
					<label>配偶姓名：</label> <input name="spName" type="text" size="30"
						value="${xhHouseLoanApply.spName}" class="" maxlength="32" />
				</p>
				<p>
					<label>配偶性别：</label> <select name="spSex" class="combox">
						<option selected value="">请选择</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</p>
				<p>
					<label>配偶身份证号：</label> <input name="spIdNum" type="text" size="30"
						value="${xhHouseLoanApply.spIdNum}" class="" maxlength="32" />
				</p>
				<p>
					<label>配偶年龄：</label> <input name="spAge" type="text" size="30"
						value="${jksqxhHouseLoanApply.spAge}" class="" maxlength="50" />
				</p>


				<!-- <input name="spAdress" type="text" size="30" value="${xhHouseLoanApply.spAdress}" class="" maxlength="64" /> -->
				<dl class="nowrap">
					<dt>配偶现地址：</dt>
					<dd>
						<textarea name="remark" style="width: 100%;">${xhHouseLoanApply.spAdress}</textarea>
					</dd>
				</dl>

				<p>
					<label>配偶手机：</label> <input name="spTelephone" type="text"
						size="30" value="${jksqxhHouseLoanApply.spTelephone}" class=""
						maxlength="32" />
				</p>
				<p>
					<label>配偶家庭电话：</label> <input name="spHomePhone" type="text"
						size="30" value="${xhHouseLoanApply.spHomePhone}" class=""
						maxlength="32" />
				</p>

				<p>
					<label>配偶工作单位：</label> <input name="spComp" type="text" size="30"
						value="${xhHouseLoanApply.spComp}" class="" maxlength="50" />
				</p>
				<p>
					<label>配偶工作单位电话：</label> <input name="spCompPhone" type="text"
						size="30" value="${xhHouseLoanApply.spCompPhone}" class=""
						maxlength="50" />

				</p>
				<p>
					<label>配偶职务：</label> <input name="spDep" type="text" size="30"
						value="${jksqxhHouseLoanApply.spDep}" class="" maxlength="50" />
				</p>
				<p>
					<label>配偶工作年限：</label> <input name="spWorkLimit" type="text"
						size="30" value="${xhHouseLoanApply.spWorkLimit}" class=""
						maxlength="3" />
				</p>

				<p>
					<label>配偶年收入：</label> <input name="spIncome" type="text" size="30"
						value="${jksqxhHouseLoanApply.spIncome}" class="" maxlength="22" />
				</p>
				<dl class="nowrap">
					<dt>配偶工作单位地址：</dt>
					<dd>
						<textarea name="remark" style="width: 100%;">${xhHouseLoanApply.spCompAdress}</textarea>
					</dd>
				</dl>






				<div class="divider"></div>
				<h1>联系人信息</h1>
				<div class="divider"></div>
				<table class="table" width="98%">
					<thead>
						<tr>
							<th width="60">联系人</th>
							<th width="60">与本人关系</th>
							<th width="60">姓名</th>
							<th width="90">工作单位</th>
							<th width="100">单位地址或家庭地址</th>
							<th width="80">联系电话</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center">亲属<input type="hidden" name="ybrgx1"
								value="亲属"></td>
							<td align="left"><input name="firstLxrRelation" type="text"
								size="" value="${xhHouseLoanApply.firstLxrRelation}" class=""
								maxlength="50" /></td>
							<td align="left"><input name="firstLxrName" type="text"
								size="" value="${xhHouseLoanApply.firstLxrName}" class=""
								maxlength="50" /></td>

							<td align="left"><input name="firstLxrWorkUnit" type="text"
								size="" value="${xhHouseLoanApply.firstLxrWorkUnit}" class=""
								maxlength="50" /></td>

							<td align="left"><input name="firstLxrAddress" type="text"
								size="" value="${xhHouseLoanApply.firstLxrAddress}" class=""
								maxlength="80" /></td>
							<td align="left"><input name="firstLxrTelphone" type="text"
								size="" value="${xhHouseLoanApply.firstLxrTelphone}" class=""
								maxlength="50" /></td>

						</tr>
						<tr>
							<td align="center">朋友<input type="hidden" name="ybrgx3"
								value="朋友"></td>

							<td align="left"><input name="secondLxrRelation" type="text"
								size="" value="${xhHouseLoanApply.secondLxrRelation}" class=""
								maxlength="50" /></td>
							<td align="left"><input name="secondLxrName" type="text"
								size="" value="${xhHouseLoanApply.secondLxrName}" class=""
								maxlength="50" /></td>

							<td align="left"><input name="secondLxrWorkUnit" type="text"
								size="" value="${xhHouseLoanApply.secondLxrWorkUnit}" class=""
								maxlength="80" /></td>

							<td align="left"><input name="secondLxrAddress" type="text"
								size="" value="${xhHouseLoanApply.secondLxrAddress}" class=""
								maxlength="50" /></td>
							<td align="left"><input name="secondLxrTelphone" type="text"
								size="" value="${xhHouseLoanApply.secondLxrTelphone}" class=""
								maxlength="50" /></td>
						</tr>
						<tr>
							<td align="center">同事<input type="hidden" name="ybrgx1"
								value="同事"></td>
							<td align="left"><input name="thirdLxrRelation" type="text"
								size="" value="${xhHouseLoanApply.thirdLxrRelation}" class=""
								maxlength="50" /></td>
							<td align="left"><input name="thirdLxrName" type="text"
								size="" value="${xhHouseLoanApply.thirdLxrName}" class=""
								maxlength="50" /></td>

							<td align="left"><input name="thirdLxrWorkUnit" type="text"
								size="" value="${xhHouseLoanApply.thirdLxrWorkUnit}" class=""
								maxlength="50" /></td>
							<td align="left"><input name="thirdLxrAddress" type="text"
								size="" value="${xhHouseLoanApply.thirdLxrAddress}" class=""
								maxlength="80" /></td>
							<td align="left"><input name="thirdLxrTelphone" type="text"
								size="" value="${xhHouseLoanApply.thirdLxrTelphone}" class=""
								maxlength="50" /></td>
						</tr>
					</tbody>
				</table>
				<div class="divider"></div>

				<h1>银行账户信息</h1>

				<div class="divider"></div>

				<table class="" width="">

					<tbody>
						<tr>
							<td align="right">开户行：</td>
							<td><input name="bankOpen" type="text" size="30"
								value="${xhHouseLoanApply.bankOpen}" class="required" maxlength="50" /></td>
							<td align="right">户名：</td>
							<td><input name="bankAccountName" type="text" size="30"
								value="${xhHouseLoanApply.bankAccountName}" class="required"
								maxlength="50" /></td>
							<td align="right">账号：</td>
							<td><input name="bankNum" type="text" size="60"
								value="${xhHouseLoanApply.bankNum}" class="required" maxlength="50" /></td>
						</tr>
					</tbody>
				</table>

			</div>




			<div class="formBar">
				<ul>
				<c:if test="${xhHouseLoanApply.loanState==0 or xhHouseLoanApply.loanState==null}">
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit"  onclick="return setState('0');">暂存</button>
							</div>
						</div></li>
					<li>
					
					</c:if>
					<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit"  onclick="return setState('1');">提交</button>
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

<script type="text/javascript">
	function setState(val) {
		document.houseLoanApplyForm.opt.value = val;
		return true;
	}
</script>