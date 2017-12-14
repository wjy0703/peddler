<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
	<!-- <h1>新增客户咨询</h1> -->
	<div class="pageFormContent">
		<form method="post" action="${ctx}/zxgl/savexydk"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<input type="hidden" name="id" value="${xydkzx.id}" />
				<input type="hidden" name="type" value="<c:if test="${xydkzx.type==null}">0</c:if><c:if test="${xydkzx.type!=null}">${xydkzx.type}</c:if>" />



				<h1>基本信息</h1>
				<div class="divider"></div>
				<table width="100%">

					<tr>
						<td><label>咨询客户姓名：</label> <input name="khmc" type="text"
							size="30" alt="请填写咨询客户姓名" value="${xydkzx.khmc }"
							class="required" /></td>
						<td><label>性别：</label> <select name="sex" 
							class="required combox" style=" background:yellow">
								<option value="" <c:if test="${xydkzx.sex==''}">selected</c:if>>请选择</option>
								<option value="0"
									<c:if test="${xydkzx.sex=='0'}">selected</c:if> selected>男</option>
								<option value="1"
									<c:if test="${xydkzx.sex=='1'}">selected</c:if>>女</option>
						</select></td>
					</tr>
					<tr>
						<td><label>固定电话：</label> <input name="telephone" type="text" 
							size="30" alt="请填写固定电话" value="${xydkzx.telephone }" class="" /></td>
						<td><label>手机号：</label> <input name="phone" type="text"
							size="30" alt="请填写手机号码" value="${xydkzx.phone }" class="required" />
						</td>
					</tr>
				</table>
				<dl class="nowrap">
					<dt>所在城市：</dt>
					<dd>
						<select class="combox required" name="crmprovince" ref="combox_crmcity"
							refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${xydkzx.crmprovince==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${crmprovince}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xydkzx.crmprovince==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
						</select> <select class="combox required" name="crty" id="combox_crmcity"
							ref="combox_crmarea" refUrl="${ctx}/cjrxx/getArea?code={value}">
							<option value="" <c:if test="${xydkzx.crty==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${crty}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${xydkzx.crty==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<div class="divider"></div>
				<h1>货款信息</h1>
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>团队经理：</label> <input type="hidden"
							name="employeeCrm.id" value="${employee.id}" /> <input
							type="text" name="employeeCrm.name" value="${employee.name}"
							readonly="readonly" /> 
						</td>
						<td><label>客户经理：</label> <input
							type="text" id="empname" class="required"
							name="employeeCca.name" value="${xydkzx.employeeCca.name }"
							suggestFields="name"
							suggestUrl="${ctx}/baseinfo/emplookup2?lookId=${employee.organi.id}"
							lookupGroup="employeeCca" readonly /> <input type="hidden"
							name="employeeCca.id" value="${xydkzx.employeeCca.id}" /> <a id="look"
							class="btnLook" href="${ctx }/baseinfo/emplookup"
							lookupGroup="employeeCca"><hi:text key="查找带回" /></a>
							<div style="display: none;">
								<input type="text" name="employeeCrm.lookId" id="lookId"
									value="${employee.organi.id}"
									onpropertychange="lookLocation(this.value)" />
							</div></td>
					</tr>
					<tr>
						<td><label>借款类型：</label> <input type="radio" name="jklx"
							value="1" onClick="check(this.value);" checked>个人信用借款&nbsp;
							<!--  <input type="radio" name="jklx" value="2"
							onClick="check(this.value);">房屋抵押借款&nbsp; <input
							type="radio" name="jklx" value="3" onClick="check(this.value);">车辆抵押借款&nbsp;-->
						</td>
					</tr>
					<tr>
						<td><label>计划借款金额：</label> <input name="planAmount"
							type="text"
							onafterpaste="this.value=this.value.replace(/\D/g,'')"
							onkeyup="this.value=this.value.replace(/\D/g,'')" size="30"
							value="${xydkzx.planAmount }" class="" /></td>
						<td><label>借款用途：</label> <input name="loanPurpose"
							type="text" size="30" value="${xydkzx.loanPurpose }" /></td>
					</tr>
				</table>

				<div class="divider"></div>
				<h1>沟通记录及下一步操作</h1>
				<div class="divider"></div>
				<dl class="nowrap">
					<dt>沟通记录：</dt>
					<dd>
						<textarea name="gtjl" style="width: 100%;" rows="6">${xydkzx.gtjl}</textarea>
					</dd>
				</dl>
				<p>
					<label>下一步：</label>

					<c:if test="${xydkzx.zhuangTai=='3'}">
						<input name="" type="text" size="30" value="已进件"
							disabled="disabled">
					</c:if>
					<c:if test="${xydkzx.zhuangTai!='3'}">
						<select name="zhuangTai" class="required combox">
							<option value=""
								<c:if test="${xydkzx.zhuangTai==''}">selected</c:if> selected>请选择</option>
							<option value="0"
								<c:if test="${xydkzx.zhuangTai=='0'}">selected</c:if>>继续跟踪</option>
							<option value="1"
								<c:if test="${xydkzx.zhuangTai=='1'}">selected</c:if>>客户放弃</option>
							<option value="2"
								<c:if test="${xydkzx.zhuangTai=='2'}">selected</c:if>>不符合进件条件</option>
							<c:if test="${xydkzx.zhuangTai=='3'}">
								<option value="3" selected>已进件</option>
							</c:if>

						</select>
					</c:if>
				</p>
			</div>

			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
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