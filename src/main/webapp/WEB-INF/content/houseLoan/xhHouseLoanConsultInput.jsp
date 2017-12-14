<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	/* function test(value){
		$("#cardNumber").addClass("required");
	}
	$(function(){
		$("input[name='identificationCard']").change(function(){
			alert($(this).val());
		});
	}); */
</script>
<div class="panel">
	<div class="pageContent">
		<form id="ProspectDtlFrm" method="post" action="${ctx}/house/saveXhHouseLoanConsult"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhHouseLoanConsult.id}" />
			<div class="pageFormContent" layoutH="56">
				<h1>客户信息</h1>
				<div class="divider"></div>
				<p>
					<label>客户姓名：</label> <input name="customerName" type="text"
						size="30" value="${xhHouseLoanConsult.customerName}"
						class="required" maxlength="25" />
				</p>
				<p>
				    <%-- <sen:select name="customerSex" clazz = "combox required" coding="jkType" value="B"/> --%>
					<label>性别：</label> <select name="customerSex" class="combox required">
						<option selected value="">请选择</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>

				</p>
				<p>
					<label>电话：</label> <input name="customerTel" type="text" size="30"
						value="${xhHouseLoanConsult.customerTel}" maxlength="25"
						class="required" />
				</p>
				<p>
					<label>身份证号码：</label> <input name="identificationCard" type="text"
						size="30" value="${xhHouseLoanConsult.identificationCard}"
						class="isIdCardNo" maxlength="25" id="cardNumber" />
				</p>

				<p>
					<label>客户来源：</label> 
					    <select name="customerSource" class="combox">
							<option selected value="">请选择</option>
							<option value="1">老客户</option>
							<option value="2">陌Call</option>
							<option value="3">商超</option>
						</select>
					<!--  <input name="customerSource" type="text"
						size="30" value="${xhHouseLoanConsult.customerSource}" class=""
						maxlength="25" />-->
				</p>
				<p>
					<label>婚否：</label>
					<!--  <input name="marrital" type="text" size="30"
						value="${xhHouseLoanConsult.marrital}" class="" maxlength="25" />-->


					<select name="marrital" class="combox">
						<option selected value="">请选择</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</p>



				<dl class="nowrap">
					<dt>现住址：</dt>
					<dd>
						<textarea name="nowAddress" style="width: 100%;">${xhHouseLoanConsult.nowAddress}</textarea>
					</dd>
				</dl>
				<div class="divider"></div>
				<h1>房产信息</h1>
				<div class="divider"></div>
				<p>
					<label>房产性质：</label>

					<!--  <input name="houseType" type="text" size="30"
						value="${xhHouseLoanConsult.houseType}" class="" maxlength="50" />-->
					<select name="houseType" class="combox">
						<option selected value="">请选择</option>
						<option value="1">商品房</option>
						<option value="0">成本价房</option>
						<option value="1">央产房</option>
						<option value="0">经济适用房</option>
					</select>
				</p>
				<p>
					<label>使用年限：</label> <input name="houseLimit" type="text" size="30"
						value="${xhHouseLoanConsult.houseLimit}" class="" maxlength="50" />
				</p>
				<p>
					<label>所属区县：</label> <input name="houseRegion" type="text"
						size="30" value="${xhHouseLoanConsult.houseRegion}" class=""
						maxlength="50" />
				</p>
				<p>
					<label>房产面积：</label> <input name="houseArea" type="text" size="30"
						value="${xhHouseLoanConsult.houseArea}" class="" maxlength="50" />
				</p>
				<p>
					<label>建成年代：</label> <input name="houseYears" type="text" size="30"
						value="${xhHouseLoanConsult.houseYears}" class="" maxlength="50" />
				</p>
				<p>
					<label>房屋楼层：</label> <input name="houseFloor" type="text" size="30"
						value="${xhHouseLoanConsult.houseFloor}" class="" maxlength="50" />
				</p>


				<dl class="nowrap">
					<dt>房屋地址：</dt>
					<dd>
						<textarea name="houseAddress" style="width: 100%;">${xhHouseLoanConsult.houseAddress}</textarea>
					</dd>
				</dl>

				<dl class="nowrap">
					<dt>房产详细情况：</dt>
					<dd>
						<textarea name="houseInfo" style="width: 100%;">${xhHouseLoanConsult.houseInfo}</textarea>
						<span class="info">属于哪种性质的房产：商品房、成本价房、央产房、经济适用房，是否进行过抵押等</span>
					</dd>
				</dl>


				<div class="divider"></div>
				<h1>借款信息</h1>
				<div class="divider"></div>
				<p>
					<label>综合利率：</label> <input name="allLoanRate" type="text"
						size="30" value="${xhHouseLoanConsult.allLoanRate}" class=""
						maxlength="22" />
				</p>
				<p>
					<label>借款额度：</label> <input name="loanAmount" type="text" size="30"
						value="${xhHouseLoanConsult.loanAmount}" class="" maxlength="22" />
				</p>
				<p>
					<label>借款期数：</label> <input name="loanMonth" type="text" size="30"
						value="${xhHouseLoanConsult.loanMonth}" class="" maxlength="22" />
				</p>
				<p>
					<label>评估价格：</label> <input name="assessPrice" type="text"
						size="30" value="${xhHouseLoanConsult.assessPrice}" class=""
						maxlength="22" />
				</p>
				<p>
					<label>借款用途：</label> <input name="loanUse" type="text" size="30"
						value="${xhHouseLoanConsult.loanUse}" class="" maxlength="50" />
				</p>
				<p>
					<label>还款来源：</label>
					<!--  <input name="backSource" type="text" size="30"
						value="${xhHouseLoanConsult.backSource}" class="" maxlength="50" /> -->

					<select name="backSource" class="combox">
						<option selected value="">请选择</option>
						<option value="1">独立还款</option>
						<option value="2">共同还款</option>
					</select>
				</p>
				<dl class="nowrap">
					<dt>客户要求:</dt>
					<dd>
						<textarea name="customerRequired" style="width: 100%;">${xhHouseLoanConsult.customerRequired}</textarea>
						<span class="info">询问客户能接受我公司能够办理的利率及借款额度，当地房产价格情况</span>
					</dd>
				</dl>



				<div class="divider"></div>

				<dl class="nowrap">
					<dt>备注：</dt>
					<dd>
						<textarea name="remark" style="width: 100%;">${xhHouseLoanConsult.remark}</textarea>
					</dd>
				</dl>


				<div class="divider"></div>

				<p>
					<label>团队经理：</label>
					 <input id ="empLookUpId"     name="teamManagerId" type="hidden" size="30" value="${xhHouseLoanConsult.teamManager.id }" class="required team" maxlength="22" />
					 <input id ="empLookUpName"   name="teamManagerName" type="text" size="30" value="${xhHouseLoanConsult.teamManager.name }"   class="required team" maxlength="22" lookupGroup="orgTeam" />
					 <a class="btnLook" href="${ctx }/employeefind/byposition/team/teamLeader" lookupGroup="orgTeam">查找带回</a>
				</p>
				<p>
					 <label>客户经理:</label>
					  <input id ="empLookUpId"     name="customerManagerId" type="hidden" size="30" value="${xhHouseLoanConsult.customrerManager.id }"  class="required customer" maxlength="22" />
					  <input id ="empLookUpName"   name="customrerManagerName" type="text" size="30" value="${xhHouseLoanConsult.customrerManager.name }"  class="required customer" maxlength="22" lookupGroup="orgTeam" />
					  <a class="btnLook" href="${ctx }/employeefind/byposition/customer/all" lookupGroup="orgTeam">查找带回</a>
					<%-- <c:if test="${ customerManager==null}">
						<select name="customerManagerId" class="combox">
							<option value="0">请选择</option>
							<c:forEach var="emp" items="${customerManagers}">
								<option value="${emp.id}">${emp.name}</option>
							</c:forEach>
							<option></option>
						</select>
					</c:if>
					<c:if test="${customerManager!=null }">
						<input name="customerManagerId" type="hidden" size="30"  value="${customerManager.id}" class="required" maxlength="22" />
						<input name="customrerManagerName" type="text" size="30" value="${customerManager.name}" class="required" maxlength="22" />
					</c:if> --%>


				</p>

				<p>

					<label>涉诉查询:</label>
                   
					 <input id ="empLookUpId"      name="lodgeQueryId" type="hidden" size="30" value="${xhHouseLoanConsult.lodgeQuery.id }" class="required lodge" maxlength="22" />
					  <input id ="empLookUpName"   name="lodgeQueryName" type="text" size="30" value="${xhHouseLoanConsult.lodgeQuery.name }" class="required lodge" maxlength="22" lookupGroup="orgTeam" />
					 <a class="btnLook" href="${ctx }/employeefind/byposition/lodge/all" lookupGroup="orgTeam">查找带回</a>
					 <%-- 
					 <c:if test="${ lodge==null}">
						<select name="lodgeQueryId" class="combox">
							<option value="0">请选择</option>
							<c:forEach var="emp2" items="${lodges}">
								<option value="${emp2.id}">${emp2.name}</option>
							</c:forEach>
							<option></option>
						</select>
					</c:if>
					<c:if test="${lodge!=null }">
						<input name="lodgeQueryId" type="hidden" size="30"
							value="${lodge.id}" class="required" maxlength="22" />
						<input name="lodgeQueryId" type="text" size="30"
							value="${lodge.name}" class="required" maxlength="22" />
					</c:if> --%>
				</p>
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