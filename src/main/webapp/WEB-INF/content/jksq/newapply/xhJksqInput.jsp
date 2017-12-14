<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 查看的时候是以tab页展示  -->
<c:if test="${xhJksq.togetherPerson =='是' && xhJksq.state != '0'}">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>借款申请信息</span> </a></li>
					<li id="togetherTab"><a href="javascript:;"><span>共同还款人信息</span>
					</a></li>
				</ul>
			</div>
		</div>
</c:if>
<div class="tabsContent">
	<div id="mainDiv">
		<form id="jksqbaseMsgForm" name="jksqbaseMsgForm" method="post"
			action="${ctx}/xhNewJksq/saveXhJksq"
			class="pageForm required-validate"
			onsubmit="return loantgFormSubmit(this);">
			<!-- 查看的时候是以tab页展示  -->
			<c:choose>
				<c:when test="${xhJksq.togetherPerson =='是' && xhJksq.state != '0'}">
					<div class="pageFormContent" layoutH="90">
				</c:when>
				<c:otherwise>
					<div class="pageFormContent" layoutH="56">
				</c:otherwise>
			</c:choose>
			<c:if test="${consultId != null}">
				<input type="hidden" id="xydkzxId" name="xydkzx.id"
					value="${consultId}" />
			</c:if>
			<input type="hidden" id="opt" name="opt" /> <input type="hidden"
				name="id" id="id" value="${xhJksq.id}" /> <input type="hidden"
				name="jksqToken" value="${sessionScope.JKSQ_FORM_TOKEN}" /> <input
				type="hidden" name="storeToCenter" value="${storeToCenter}" /> <input
				type="hidden" name="loopApply" value="${param.loopApply}" />
			<div class="panel" align="center">
				<h1>申请意向</h1>
				<div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td>产品类别：</td>
								<td colspan="5"><sen:selectRadio name="jkType"
										value="${xhJksq.jkType }" coding="productType"
										clazz="required" split="&nbsp;&nbsp;" /></td>
							</tr>

							<tr height="30px">
								<td>申请额度(元)：</td>
								<td><input type="text" id="jkLoanQuota" name="jkLoanQuota"
									value="${xhJksq.jkLoanQuota}" class="required number"
									alt="必填,无千分位"></td>
								<td>借款期限(月)：</td>
								<td><input type="text" id="jkCycle" name="jkCycle"
									size="10" class="required digits" value="${xhJksq.jkCycle}"
									alt="必填,整数" /></td>
								<td>申请日期：</td>
								<td><input type="text" id="jkLoanDate" name="jkLoanDate"
									class="date required" pattern="yyyy-MM-dd" format="yyyy-MM-dd"
									size="27" readonly="readonly" value="${xhJksq.jkLoanDate}" />
									<a class="inputDateButton" href="javascript:;">选择</a></td>
							</tr>

							<tr height="30px">
								<td>由何处得知我公司：</td>
								<td colspan="5"><sen:selectRadio name="knownWay"
										value="${xhJksq.knownWay}" coding="knownWay"
										split="&nbsp;&nbsp;" clazz="required" /></td>
							</tr>

							<tr height="30px">
								<td>借款用途：</td>
								<td><sen:selectRadio name="jkUse" coding="loanUseType"
										value="${xhJksq.jkUse}" split="&nbsp;&nbsp;&nbsp;&nbsp;"
										clazz="required" /></td>
								<td>具体用途：</td>
								<td><input type="text" name="jkTypeMore"
									value="${xhJksq.jkTypeMore}" size="50" class="required"></td>
								<td>是否加急：</td>
								<td colspan="1"><input class="required" type="radio"
									id="englishName" name="englishName" value="否"
									<c:if test='${xhJksq.englishName == "否" }'>checked="checked" </c:if> />否&nbsp;
									<input class="required" type="radio" id="englishName"
									name="englishName" value="是"
									<c:if test='${xhJksq.englishName == "是" }'>checked="checked" </c:if> />是&nbsp;</td>
							</tr>

							<tr height="30px">
								<td>还款方式：</td>
								<td colspan="2"><sen:selectRadio name="hkWay"
										coding="loanReturnType" value="${xhJksq.hkWay}"
										split="&nbsp;&nbsp;&nbsp;&nbsp;" clazz="required" /></td>
								<td></td>
								<td>共同还款人：</td>
								<td colspan="1"><input class="required" type="radio"
									id="togetherPersonYes" name="togetherPerson" value="是"
									<c:if test='${xhJksq.togetherPerson =="是"}'>checked="checked" </c:if> />有&nbsp;
									<input class="required" type="radio" id="togetherPersonNo"
									name="togetherPerson" value="否"
									<c:if test='${xhJksq.togetherPerson =="否"}'>checked="checked" </c:if> />无&nbsp;
								</td>
							</tr>

							<tr height="30px">
								<td>管辖城市：</td>
								<td colspan="5"><sen:address names="province.id,city.id"
										titles="所属省市,所属城市" required="true"
										values="${xhJksq.province.id},${xhJksq.city.id}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<table nowrapTD="false" width="100%" border="1"
				style="border-color: red">
			</table>
			<div class="divider"></div>
			<div class="panel" align="center">
				<h1>个人资料</h1>
				<div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>中文姓名：</label></td>
								<td colspan="8"><input type="text" id="jkrxm" name="jkrxm"
									value="${xhJksq.jkrxm}" class="required"></td>
							</tr>

							<tr height="30px">
								<td><label>证件类型：</label></td>
								<td colspan="5"><sen:selectRadio name="pocertificates"
										coding="cardType" split="&nbsp;&nbsp;&nbsp;&nbsp;"
										value="${xhJksq.pocertificates}" clazz="required" /></td>
							</tr>
							<tr height="30px">
								<td><label>证件号码：</label></td>
								<td><input type="text" id="zjhm" name="zjhm"
									class="required" value="${xhJksq.zjhm}" size=30 /></td>
								<td><label>性别：</label></td>
								<td><sen:selectRadio name="genders" coding="genders"
										value="${xhJksq.genders}" clazz="required" /></td>
							</tr>

							<tr height="30px">
								<td><label>出生日期：</label></td>
								<td><input type="text" name="birthday" class="date"
									yearstart="-113" yearend="5" format="yyyy-MM-dd" size="27"
									value="${xhJksq.birthday}" readonly="readonly" /> <a
									class="inputDateButton" href="javascript:;">选择</a></td>
								<td><label>年龄：</label></td>
								<td><input type="text" class="digits" min="1" max="99"
									maxlength="2" name="age" value="${xhJksq.age}" class="required" /></td>
							</tr>
							<tr height="30px">
								<td><label>婚姻状况：</label></td>
								<td><sen:selectRadio clazz="required" name="maritalStatus"
										value="${xhJksq.maritalStatus}" coding="marriageType" /></td>
								<td><label>社会保障卡号：</label></td>
								<td><input type="text" id="socialCard" name="socialCard"
									size="50" class="digits" value="${xhJksq.socialCard}" /></td>
							</tr>
							<tr height="30px">
								<td><label>教育程度：</label></td>
								<td><sen:selectRadio name="educationType" clazz="required"
										value="${xhJksq.educationType}" coding="studyLevel"
										split="&nbsp;&nbsp;&nbsp;&nbsp;" /></td>
								<td><label>户口：</label></td>
								<td><sen:select clazz="required combox" name="parhome"
										value="${xhJksq.parhome}" coding="homeNature" id="parhome" /></td>
							</tr>
							<tr height="30px">
								<td><label>户籍地址：</label></td>
								<td colspan="5"><input type="text" id="hjadress"
									name="hjadress" size="100" value="${xhJksq.hjadress}"
									class="required"></td>
							</tr>
							<tr height="30px">
								<td><label>现住址：</label></td>
								<td colspan="5"><input type="text" name="homeAddress"
									size="100" value="${xhJksq.homeAddress}" maxlength="100"
									class="required" /></td>
							</tr>
							<tr height="30px">
								<td><label>邮编：</label></td>
								<td><input type="text" id="backup05" name="backup05"
									value="${xhJksq.backup05}" class="digits"></td>
								<td><label>居住年限：</label></td>
								<td><input type="text" id="liveYear" name="liveYear"
									value="${xhJksq.liveYear}" class="digits" maxlength="2"></td>
							</tr>
							<tr height="30px">
								<td><label>电话：</label></td>
								<td><input type="text" id="homePhone" name="homePhone"
									value="${xhJksq.homePhone}" class="required phoneNumber"></td>
								<td><label>移动电话：</label></td>
								<td><input type="text" id="telephone" name="telephone"
									value="${xhJksq.telephone}" class="required phoneNumber"></td>
							</tr>
							<tr height="30px">
								<td colspan="1"><label>住宅类别：</label></td>
								<td colspan="1"><sen:selectRadio name="liveState"
										value="${xhJksq.liveState}" split="&nbsp;&nbsp;&nbsp;&nbsp;"
										coding="liveStateType" clazz="required" /></td>
								<td><label>月还款/租金</label></td>
								<td><input type="text" name="liveMessage"
									value="${xhJksq.liveMessage}" class="number">元</td>
							</tr>
							<tr height="30px">
								<td colspan="1"><label> 与谁居住：</label></td>
								<td><sen:selectCheckbox name="liveTogehter"
										value="${xhJksq.liveTogehter}" coding="liveWhoTogeter"
										split="&nbsp;&nbsp;&nbsp;&nbsp;" /></td>
								<td colspan="1" align="right"><label>共同居住人数：</label></td>
								<td><input type="text" id="liveTogetherCount"
									name="liveTogetherCount" value="${xhJksq.liveTogetherCount}"
									class="required digits" size="5"> <label>人</label></td>
							</tr>
							<tr height="30px">
								<td><label>电邮地址：</label></td>
								<td><input type="text" id="email" name="email"
									class="email" value="${xhJksq.email }" /></td>
								<td colspan="">家人是否知悉此借款：</td>

								<td>
									<!-- clazz="required combox" --> <sen:select name="contact"
										value="${xhJksq.contact}" coding="yesOrNo" id="contact"
										clazz="combox required" />
								</td>
							</tr>

							<tr height="30px">
								<td><label>家人姓名：</label></td>
								<td><input type="text" id="contactName" name="contactName"
									value="${xhJksq.contactName}" maxlength="20"></td>
								<td><label>与借款人的关系：</label></td>
								<td><input type="text" id="contactRelation"
									name="contactRelation" value="${xhJksq.contactRelation }"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			
			
			<div class="divider"></div>
			<div id="isMarrayInput" style="display: block;">
				<div class="panel" align="center">
					<h1>配偶资料</h1>
					<table class="list" width="100%">
						<tbody>
							<tr>
								<td><label>姓名：</label></td>
								<td><input type="text" id="spouseName" name="spouseName"
									class="required spouse" value="${xhJksq.spouseName }" /></td>
								<td><label>身份证号：</label></td>
								<td><input type="text" class="required isIdCardNo spouse"
									id="spouseZjhm" name="spouseZjhm" value="${xhJksq.spouseZjhm }" /></td>
								<td><label>年龄：</label></td>
								<td><input class="spouse" type="text" class="digits"
									min="1" max="99" maxlength="2" id="plnl" name="spouseAge"
									value="${xhJksq.spouseAge }" /></td>
								<td><label>联络电话：</label></td>
								<td><input type="text" class="spouse phoneNumber"
									id="spouseTelephone" name="spouseTelephone"
									value="${xhJksq.spouseTelephone }" /></td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td><label>单位名称：</label></td>
								<td><input type="text" class="spouse" id="spouseCompany"
									name="spouseCompany" value="${xhJksq.spouseCompany }" /></td>
								<td><label>职务：</label></td>
								<td><input type="text" id="spouseDepFunction"
									class="spouse" name="spouseDepFunction"
									value="${xhJksq.spouseDepFunction }" /></td>
								<td><label>单位地址：</label></td>
								<td><input type="text" id="spouseCompanyAdress"
									class="spouse" name="spouseCompanyAdress"
									value="${xhJksq.spouseCompanyAdress }" /></td>
								<td><label>单位电话：</label></td>
								<td><input type="text" id="spouseCompanyPhone"
									class="spouse phoneNumber" name="spouseCompanyPhone"
									value="${xhJksq.spouseCompanyPhone }" /></td>
							</tr>
							<tr>
								<td><label>月收入(元)：</label></td>
								<td colspan="7"><input type="text" id="spouseAnnualIncome"
									name="spouseAnnualIncome" size="10"
									value="${xhJksq.spouseAnnualIncome }" class="spouse number" />
									<span class="info"> (数字格式,无千分位)</span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="divider"></div>
			<div class="panel" align="center">
				<h1>信用资料</h1>
				<div class="panelBar test">
					<ul class="toolBar">
						<li><a id="addCredit" class="add" title="添加信用记录"><span>添加信用记录</span></a></li>
					</ul>
				</div>
				<table id="creditTable" class="list" width="100%">
					<tbody>
					<thead>

						<tr>
							<th>操作</th>
							<th>抵押类型</th>
							<th>抵押物品</th>
							<th>机构名称</th>
							<th>贷款额度(￥)</th>
							<th>每月供额度(￥)</th>
							<th>贷款余额(￥)</th>
						</tr>
					</thead>
					<c:forEach items="${xhJksq.xhJksqCredits}" var="credit"
						varStatus="creditIndex">
						<tr>
							<td>
								<%-- <button ajaxData = "${credit.id}" class="oracleDeleteCredit">删除</button> --%>
								<a title="删除信用记录" href="#" class="btnDel oracleDeleteCredits"
								ajaxTarget="credit" ajaxData="${credit.id}">删除信用记录</a> <input
								type="hidden"
								name="xhJksqCredits[${creditIndex.index}].xhJksq.id"
								value="${xhJksq.id}" /> <input type="hidden"
								name="xhJksqCredits[${creditIndex.index}].id"
								value="${credit.id}" />
							</td>
							<td><sen:selectRadio coding="guarantyType"
									name="xhJksqCredits[${creditIndex.index}].typeh"
									value="${credit.typeh}" /></td>
							<td><input errorTitle="抵押物品"
								name="xhJksqCredits[${creditIndex.index}].mortage"
								value="${credit.mortage}" class="" /></td>
							<td><input errorTitle="机构名称"
								name="xhJksqCredits[${creditIndex.index}].compBankName"
								value="${credit.compBankName}" class="required" /></td>
							<td><input errorTitle="贷款额度"
								name="xhJksqCredits[${creditIndex.index}].loanAmount"
								value="${credit.loanAmount}" class="required number" /></td>
							<td><input errorTitle="每月供额度"
								name="xhJksqCredits[${creditIndex.index}].monthReturn"
								value="${credit.monthReturn}" class="required number" /></td>
							<td><input errorTitle="贷款余额"
								name="xhJksqCredits[${creditIndex.index}].remain"
								value="${credit.remain}" class="required number" /></td>
						</tr>
					</c:forEach>
					<tr height="10px">
						<td colspan="7"></td>
					<tr>
					<tr>
						<td>信用卡总数：</td>
						<td colspan=""><input type="text" id="spouseCompanyPhone"
							class="digits" name="cardCount" value="${xhJksq.cardCount }"
							size="2" maxlength="2" />&nbsp;个</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					</tbody>
				</table>
			</div>
			<div class="divider"></div>
			<div class="panel" align="center">
				<h1>职业信息/公司资料</h1>
				<div class="panelBar officeInfoHeaderDiv">
					<ul class="toolBar">
						<li><a class="add addOfficeDiv" href="#" target=""><span>添加职业信息</span></a></li>
					</ul>
				</div>
				<c:forEach items="${xhJksq.xhJksqOffices}" var="officeInfo"
					varStatus="officeIndex">
					<div class="officeInfo">
						<table class="list" width="100%">
							<tr>
								<td colspan="1"><a title="删除" href="#"
									class="btnDel oracleDelete" ajaxTarget="office"
									ajaxData="${officeInfo.id}">删除</a></td>
								<td colspan="5" align="center">职业信息/公司资料--具体信息</td>
							</tr>
							<tr>
								<td><label>名称：</label></td>
								<td><input type="hidden" name="xhJksqOffices[0].id"
									value="${officeInfo.id}" /> <c:if test="${xhJksq.id != null}">
										<input type="hidden" name="xhJksqOffices[0].xhJksq.id"
											value="${xhJksq.id}" />
									</c:if> <input name="xhJksqOffices[0].name" type="text"
									value="${officeInfo.name}" class="required" maxlength="80"
									size="60" /></td>
								<td><label>邮编：</label></td>
								<td><input name="xhJksqOffices[0].postcode" type="text"
									value="${officeInfo.postcode}" class="digits" maxlength="10" /></td>
							</tr>
							<tr>
								<td><label>地址：</label></td>
								<td><input name="xhJksqOffices[0].address" type="text"
									value="${officeInfo.address}" class="required" maxlength="200"
									size="60" /></td>
								<td><label>单位类型：</label></td>
								<td><sen:selectRadio name="xhJksqOffices[0].typeh"
										value="${officeInfo.typeh}" coding="utilsType"
										split="&nbsp;&nbsp;&nbsp;&nbsp;" clazz="required" /></td>
							</tr>
							<tr>
								<td><label>单位电话：</label></td>
								<td><input name="xhJksqOffices[0].phone" type="text"
									value="${officeInfo.phone}" class="required phoneNumber"
									maxlength="20" /></td>
								<td><label>单位网址：</label></td>
								<td><input name="xhJksqOffices[0].website" type="text"
									value="${officeInfo.website}" class="" maxlength="70" size="60" /></td>
							</tr>
							<tr>
								<td><label>行业类别：</label></td>
								<td><input name="xhJksqOffices[0].typea" type="text"
									value="${officeInfo.typea}" class="required" maxlength="20" /></td>
								<td><label>职务：</label></td>
								<td><input name="xhJksqOffices[0].duty" type="text"
									value="${officeInfo.duty}" class="required" maxlength="20" /></td>
							</tr>
							<tr>
								<td><label>部门：</label></td>
								<td><input name="xhJksqOffices[0].department" type="text"
									value="${officeInfo.department}" class="" maxlength="30" /></td>
								<td><label>员工数量：</label></td>
								<td><input name="xhJksqOffices[0].personCount" type="text"
									value="${officeInfo.personCount}" class="digits" maxlength="22" /></td>
							</tr>
							<tr>
								<td><label>每月收入(元)：</label></td>
								<td><input name="xhJksqOffices[0].monthSalary" type="text"
									value="${officeInfo.monthSalary}" class="number" maxlength="22" /></td>
								<td><label>每月支薪日：</label></td>
								<td><input name="xhJksqOffices[0].salaryDay" type="text"
									value="${officeInfo.salaryDay}" class="digits" maxlength="22" /></td>
							</tr>
							<tr>
								<td><label>支付方式</label></td>
								<td><input name="xhJksqOffices[0].salaryType" type="text"
									value="${officeInfo.salaryType}" class="" maxlength="10" /></td>
								<td><label>服务年数：</label></td>
								<td><input name="xhJksqOffices[0].workYear" type="text"
									value="${officeInfo.workYear}" class="number" maxlength="22" /></td>
							</tr>
							<tr>
								<td><label>其他收入(元)：</label></td>
								<td><input name="xhJksqOffices[0].bonus" type="text"
									value="${officeInfo.bonus}" class="number" maxlength="22" /></td>
							</tr>
						</table>
					</div>
				</c:forEach>
			</div>
			<div class="divider"></div>
			<div class="panel companyDiv" align="center">
				<h1>补充资料（企业主适）</h1>
				<div class="panelBar companyInfoHeaderDiv">
					<ul class="toolBar">
						<li><a class="add addCompanyDiv" href="#" target=""><span>添加企业信息</span></a></li>
					</ul>
				</div>
				<c:forEach items="${xhJksq.xhJksqCompanys}" var="companyInfo"
					varStatus="companyIndex">
					<div class="companyInfo">
						<table class="list" width="100%">
							<tr>
								<td colspan="1"><a title="删除" href="#"
									class="btnDel oracleDelete" ajaxTarget="company"
									ajaxData="${companyInfo.id}">删除</a></td>
								<td colspan="5" align="center">企业具体信息</td>
							</tr>
							<tr>
								<td><label>营业执照注册号：</label></td>
								<td><input type="hidden"
									name="xhJksqCompanys[${companyIndex.index}].id"
									value="${companyInfo.id}" /> <c:if test="xhJksq.id != null">
										<input type="hidden"
											name="xhJksqCompanys[${companyIndex.index}].xhJksq.id"
											value="${xhJksq.id}" />
									</c:if> <input
									name="xhJksqCompanys[${companyIndex.index}].busiLicences"
									type="text" value="${companyInfo.busiLicences}"
									class="required" maxlength="200" /></td>
								<td width="25%"><label>成立日期：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].startDate"
									type="text" size="20" value="${companyInfo.startDate}"
									class="date required" pattern="yyyy-MM-dd" format="yyyy-MM-dd" />
									<a class="inputDateButton" href="javascript:;">选择</a></td>
							</tr>
							<tr>
								<td><label>注册资金：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].registerMoney"
									type="text" size="20" value="${companyInfo.registerMoney}"
									class="required number" /></td>
								<td><label>经营场所：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].areaType"
									type="text" value="${companyInfo.areaType}" class="" /></td>
							</tr>
							<tr>
								<td><label>营业面积(米)：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].areaSquare"
									type="text" value="${companyInfo.areaSquare}" class="number" />
								</td>
								<td><label>月还款/租金￥</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].moneyUsed"
									type="text" value="${companyInfo.moneyUsed}" class="number" />
								</td>
							</tr>
							<tr>
								<td><label>淡季月份：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].weakMonth"
									type="text" value="${companyInfo.weakMonth}" class="" /></td>
								<td><label>销售额：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].weakMonthEarn"
									type="text" value="${companyInfo.weakMonthEarn}" class="number" />
								</td>
							</tr>
							<tr>
								<td colspan="1"><label>旺季月份：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].strongMonth"
									type="text" value="${companyInfo.strongMonth}" class="" /></td>
								<td><label>销售额：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].strongMonthEarn"
									type="text" value="${companyInfo.strongMonthEarn}"
									class="number" /></td>
							</tr>
							<tr>
								<td colspan="1"><label>平季月份：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].middleMonth"
									type="text" value="${companyInfo.middleMonth}" class="" /></td>
								<td><label>销售额：</label></td>
								<td><input
									name="xhJksqCompanys[${companyIndex.index}].middleMonthEarn"
									type="text" value="${companyInfo.middleMonthEarn}"
									class="number" /></td>
							</tr>
							<tr>
								<td rowspan="3" colspan="4">
									<table>
										<tr>
											<td rowspan="3">主要供应商：
											<td width="2">1:</td>
											<td><input
												name="xhJksqCompanys[${companyIndex.index}].supplierOne"
												type="text" size="80" value="${companyInfo.supplierOne}"
												class="" /></td>
										</tr>
										<tr>
											<td width="2" colspan="1">2:</td>
											<td><input
												name="xhJksqCompanys[${companyIndex.index}].supplierTwo"
												type="text" size="80" value="${companyInfo.supplierTwo}"
												class="" /></td>
										</tr>
										<tr>
											<td width="2" colspan="1">3:</td>
											<td><input
												name="xhJksqCompanys[${companyIndex.index}].supplierThree"
												type="text" size="80" value="${companyInfo.supplierThree}"
												class="" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- <div class="companyInfo"> -->
				</c:forEach>
			</div>
			<!-- <div class="panel companyDiv" align="center"> -->

			<div class="divider"></div>
			<div class="panel" align="center">
				<h1>房产资料</h1>
				<div class="panelBar houseInfoHeaderDiv">
					<ul class="toolBar">
						<li><a class="add addHouseDiv" href="#" target=""><span>添加新房产</span></a></li>

					</ul>
				</div>

				<c:forEach items="${xhJksq.xhJksqHouses}" var="houseInfo"
					varStatus="houseIndex">
					<div class="houseInfo">
						<table width="100%" class="list">
							<tr>
								<td colspan="1"><a title="删除" href="#"
									class="btnDel oracleDelete" ajaxTarget="house"
									ajaxData="${houseInfo.id}">删除</a></td>
								<td colspan="5" align="center">房产具体信息</td>
							</tr>
							<tr>
								<td colspan=""><label>房产地址：</label></td>
								<td colspan="3"><input
									name="xhJksqHouses[${houseIndex.index }].address" type="text"
									value="${houseInfo.address}" size="100" class="required"
									maxlength="200" /> <input type="hidden"
									name="xhJksqHouses[${houseIndex.index }].id"
									value="${houseInfo.id}" /> <c:if test="xhJksq.id !=null">
										<input type="hidden"
											name="xhJksqHouses[${houseIndex.index }].xhJksq.id"
											value="${xhJksq.id}" />
									</c:if></td>
							</tr>
							<tr>
								<td><label>购买方式：</label></td>
								<td colspan="1"><sen:selectRadio
										name="xhJksqHouses[${houseIndex.index }].typeh"
										value="${houseInfo.typeh}" coding="buyType"
										split="&nbsp;&nbsp;&nbsp;&nbsp;"
										clazz="required controlBorrow" /></td>
								<td><label>购买日期：</label>
								<td colspan="1"><input type="text"
									name="xhJksqHouses[${houseIndex.index }].buyDate" class="date"
									format="yyyy-MM-dd" pattern="yyyy-MM-dd" readonly="readonly"
									value="${houseInfo.buyDate}" /> <a class="inputDateButton"
									href="javascript:;">选择</a></td>
								<td></td>
								<td></td>

							</tr>
							<tr>
								<td><label>建筑年份：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].buildYear" type="text"
									value="${houseInfo.buildYear}" /></td>
								<td><label>销售面积：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].area" type="text"
									class="number" value="${houseInfo.area}" maxlength="22" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><label>按揭银行：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].bank" type="text"
									value="${houseInfo.bank}" class="borrowMoney" maxlength="50" /></td>
								<td><label>贷款总额：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].loanMoney" type="text"
									value="${houseInfo.loanMoney}" class="borrowMoney number"
									maxlength="22" /></td>
								<td><label>贷款年限：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].loanMonth" type="text"
									value="${houseInfo.loanMonth}" class="borrowMoney number"
									maxlength="22" /></td>
								<td></td>
							</tr>
							<tr>
								<td><label>购买价格：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].buyMoney" type="text"
									value="${houseInfo.buyMoney}" class="borrowMoney number"
									maxlength="22" /></td>
								<td><label>尚欠余额：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].remainmoney"
									type="text" value="${houseInfo.remainmoney}"
									class="borrowMoney number" maxlength="22" /></td>
								<td><label>月还款：</label></td>
								<td colspan="1"><input
									name="xhJksqHouses[${houseIndex.index }].monthReturn"
									type="text" value="${houseInfo.monthReturn}"
									class="borrowMoney number" maxlength="22" /></td>
								<td></td>
							</tr>
						</table>
					</div>
				</c:forEach>
			</div>
			<div class="divider"></div>
			<div class="panel" align="center">
				<h1>联系人资料（2名亲属、2名朋友、2名同事，最少1名联系人为直系亲属）</h1>

				<table class="list" width="100%">
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
								<td align="center"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].ybrgx"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].ybrgx}"> <input
									type="hidden" name="xhJkjjlxrs[${goIndex.index}].id"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].id}" /> <input
									type="hidden" name="xhJkjjlxrs[${goIndex.index}].xhJksq.id"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].xhJksq.id}" /></td>
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].name"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].name}" /></td>
								<!-- 姓名 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].homePhone"
									class="phoneNumber"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].homePhone}" /></td>
								<!-- 住宅电话 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].jjlxrlxdh"
									class="phoneNumber"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].jjlxrlxdh}" /></td>
								<!-- 手机电话 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].jjlxrdwdzhjtzz"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].jjlxrdwdzhjtzz}" />
								</td>
								<!-- 住址 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].jjlxrgzdw"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].jjlxrgzdw}" /></td>
								<!-- 单位名称 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].officePhone"
									class="phoneNumber"
									value="${xhJksq.xhJkjjlxrs[goIndex.index].officePhone}" /></td>
								<!-- 单位电话 -->
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<div class="divider"></div>
			<table nowrapTD="false" width="100%" border="1"
				style="border-color: red">
			</table>
			<div class="divider"></div>

			<table width="100%" class="list">
				<tr>
					<td><label>申请人：</label></td>
					<td><input name="" type="text" value="${xhJksq.jkrxm}"
						class="required" maxlength="22" readonly /></td>

					<td><label>见证人：</label></td>
					<td><input name="jksqEnsurePerson" type="text"
						value="${xhJksq.jksqEnsurePerson}" maxlength="22" /></td>
				</tr>

				<tr>
					<td><label>开户行名称：</label></td>
					<td colspan="3"><input name="bankOpen" type="text" size="80"
						value="${xhJksq.bankOpen}" class="required" /><span class="info">(注：请认真核对，具体到详细支行信息)</span></td>



				</tr>
				<tr>
					<td><label>帐户姓名：</label></td>
					<td><input name="bankUsername" type="text" 
						value="${xhJksq.bankUsername}" class="required" maxlength="22" /><span class="info">(注：请认真核对帐户姓名)</span></td>

				</tr>
				<c:choose>
						<c:when test="${alwaysChange != null }">
						     <tr>
									<td><label>银行账号：</label></td>
										<td><input name="bankNum" type="text"  
											value="${xhJksq.bankNum}" class="required" maxlength="40" 
										size="40" /><span class="info">(注：请认真核对银行账号)</span></td>
							  </tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td><label>银行账号：</label></td>
											<td><input name="bankNum" type="text" id="bankNum1"
												value="${xhJksq.bankNum}" class="required" maxlength="40" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false"
										size="40" /><span class="info">(注：请认真核对银行账号)</span></td>
							</tr>
							<tr>
								<td><label>确认银行账号：</label></td>
								<td><input name="bankNum1" type="text" id="bankNum2"
									value="${xhJksq.bankNum}" class="required " equalto="#bankNum1" maxlength="40" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false"
									size="40" /><span class="info">(注：请再次输入银行帐号)</span></td>
							</tr>
						</c:otherwise>
			    </c:choose>

				<tr>
					<td><label>客服：</label></td>
					<td><input name="firstServiceId" type="hidden"
						value="${empId }" /> <input name="" type="text"
						readonly="readonly" value="<sen:employeeName id="${empId }"/>" />
					</td>
					<c:if test="${empId2 != '' }">
						<td><label>复核：</label></td>
						<td><input name="secondServiceId" type="hidden"
							value="${empId2 }" /> <input type="text" readonly="readonly"
							value="<sen:employeeName id="${empId2 }"/>" /></td>
					</c:if>
				</tr>
				<tr>
					<td><label>备注：</label></td>
					<td colspan="3"><textarea name="jksqComment" id="textarea"
							cols="100%" rows="4">${xhJksq.jksqComment }</textarea></td>
				</tr>

				<c:if test="${alwaysChange != null }">
					<tr>
						<td><label>申请ID：</label></td>
						<td>${xhJksq.id}</td>
					</tr>
				</c:if>

			</table>

			<c:if
				test='${null!= firstXhCreditAudit && null!= firstXhCreditAudit.creditRefuseReason}'>
				<div class="divider"></div>
				<p>
					<label>初审审核结果：</label>
					<c:if test='${firstXhCreditAudit.creditResult eq 1}'>
						<label><input type="radio" disabled checked />初审通过</label>
					</c:if>
					<c:if test='${firstXhCreditAudit.creditResult eq 3}'>
						<label><input type="radio" disabled checked />初审退回</label>
					</c:if>
					<c:if test='${firstXhCreditAudit.creditResult eq 4}'>
						<label><input type="radio" disabled checked />初审通过</label>
					</c:if>
					<c:if test='${firstXhCreditAudit.creditResult eq 5}'>
						<label><input type="radio" disabled checked />客户放弃</label>
					</c:if>
				</p>
				<dl class="nowrap">
					<dt>初审审核意见</dt>
					<dd>
						<textarea id="backup09" class="differenceStyle" disabled
							name="backup09" rows="4" style="width: 93%;" readonly>${firstXhCreditAudit.creditRefuseReason}</textarea>
					</dd>
				</dl>
			</c:if>
			<c:if
				test='${null!= secondXhCreditAudit && null!= secondXhCreditAudit.creditRefuseReason}'>
				<div class="divider"></div>
				<p>
					<label>复审审核结果：</label>
					<c:if test='${secondXhCreditAudit.creditResult eq 1}'>
						<label><input type="radio" disabled checked />复审通过</label>
					</c:if>
					<c:if test='${secondXhCreditAudit.creditResult eq 3}'>
						<label><input type="radio" disabled checked />复审退回</label>
					</c:if>
					<c:if test='${secondXhCreditAudit.creditResult eq 4}'>
						<label><input type="radio" disabled checked />复审通过</label>
					</c:if>
					<c:if test='${secondXhCreditAudit.creditResult eq 5}'>
						<label><input type="radio" disabled checked />客户放弃</label>
					</c:if>
				</p>
				<dl class="nowrap">
					<dt>复审审核意见</dt>
					<dd>
						<textarea id="backup09" class="differenceStyle" name="backup09"
							disabled rows="4" style="width: 93%;" readonly>${secondXhCreditAudit.creditRefuseReason}</textarea>
					</dd>
				</dl>
			</c:if>
			<c:if
				test='${null!= lastXhCreditAudit  && null!= lastXhCreditAudit.creditRefuseReason}'>
				<div class="divider"></div>
				<p>
					<label>终审审核结果：</label>
					<c:if test='${lastXhCreditAudit.creditResult eq 1}'>
						<label><input type="radio" disabled checked />终审通过</label>
					</c:if>
					<c:if test='${lastXhCreditAudit.creditResult eq 3}'>
						<label><input type="radio" disabled checked />终审退回</label>
					</c:if>
					<c:if test='${lastXhCreditAudit.creditResult eq 4}'>
						<label><input type="radio" disabled checked />终审通过</label>
					</c:if>
					<c:if test='${lastXhCreditAudit.creditResult eq 5}'>
						<label><input type="radio" disabled checked />客户放弃</label>
					</c:if>
				</p>
				<dl class="nowrap">
					<dt>终审审核意见</dt>
					<dd>
						<textarea id="backup09" class="differenceStyle" name="backup09"
							rows="4" disabled style="width: 93%;" readonly>${lastXhCreditAudit.creditRefuseReason}</textarea>
					</dd>
				</dl>
			</c:if>
	</div>
	<div class="formBar">
		<ul>
			<c:if test="${look == null }">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="tempSave">暂存</button>
						</div>
					</div>
				</li>

				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="realSubmit">提交</button>
						</div>
					</div></li>
			</c:if>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="close">取消</button>
					</div>
				</div></li>
		</ul>
	</div>
	</form>
</div>
<c:if test="${xhJksq.togetherPerson =='是' && xhJksq.state != '0'}">
	<div id="togetherDiv">
		<form id="jksqTogetherEditForm" name="jksqTogetherEditForm"
			method="post" action="${ctx}/jksq/saveJksqTogetherInfo"
			class="pageForm required-validate"
			onsubmit="return jksqTogetherEditSubmit(this);">
			<div class="pageFormContent" layoutH="56">
				<input type="hidden" id="jksqId" name="jksqId" value="${jksq.id}" />
				<input type="hidden" id="jksqTogetherId" name="id"
					value="${xhJksqTogether.id}" />
				<div class="panel" align="center">
					<h1>共同借款人信息</h1>

					<table nowrapTD="false" class="list" width="100%">
						<tbody>
							<tr>
								<td><label>共同借款人姓名:</label> <input type="text"
									id="togetherName" name="togetherName" size="30"
									value="${xhJksqTogether.togetherName }" class="required" /></td>
								<td><label>年龄:</label> <input type="text" id="age"
									name="age" size="30" value="${xhJksqTogether.age }"
									class="digits" min="1" max="99" maxlength="2" /></td>
								<td><label>性别：</label> <input type="radio" id="genders"
									name="genders" value="男" checked="checked" />男&nbsp; <input
									type="radio" id="genders" name="genders" value="女" />女</td>
							</tr>
							<tr>
								<td><label>身份证号码：</label> <input type="text"
									id="identification" name="identification" size="30"
									class="required" value="${xhJksqTogether.identification }" /></td>
								<td><label>户籍地址：</label> <input type="text" id="hjadress"
									name="hjadress" size="30" class="required"
									value="${xhJksqTogether.hjadress }" /></td>
								<td><label>家庭电话：</label> <input type="text" id="homePhone"
									name="homePhone" size="30" class="required phoneNumber"
									value="${xhJksqTogether.homePhone }" /></td>
							</tr>
							<tr>
								<td><label>手机：</label> <input type="text" id="telephone"
									name="telephone" size="30" class="required"
									value="${xhJksqTogether.telephone }" /></td>
								<td><label>现住址：</label> <input type="text" id="address"
									name="address" size="30" class="required"
									value="${xhJksqTogether.address }" /></td>
								<td><label>住址现电话：</label> <input type="text"
									id="addressPhone" name="addressPhone" size="30"
									class="required phoneNumber"
									value="${xhJksqTogether.addressPhone }" /></td>
							</tr>
							<tr>
								<td><label>邮箱：</label> <input type="text" id="email"
									name="email" size="30" class="required email"
									value="${xhJksqTogether.email }" /></td>
								<td><label>工作单位：</label> <input type="text" id="company"
									name="company" size="30" class="required"
									value="${xhJksqTogether.company }" /></td>
								<td><label>单位电话：</label> <input type="text"
									id="companyPhone" name="companyPhone" size="30"
									class="required phoneNumber"
									value="${xhJksqTogether.companyPhone }" /></td>
							</tr>
							<tr>
								<td><label>QQ号码：</label> <input type="text" id="qqhm"
									name="qqhm" size="30" class="required"
									value="${xhJksqTogether.qqhm }" /></td>
								<td><label>单位地址：</label> <input type="text"
									id="companyAdress" name="companyAdress" size="30"
									class="required" value="${xhJksqTogether.companyAdress }" /></td>
								<td><label>部门名称：</label> <input type="text" id="department"
									name="department" size="30" class="required"
									value="${xhJksqTogether.department }" /></td>
							</tr>
							<tr>
								<td><label>职务：</label> <input type="text" id="function"
									name="function" size="30" class="required"
									value="${xhJksqTogether.function }" /></td>
								<td><label>婚姻状况：</label> <sen:select clazz="combox"
										name="maritalStatus" coding="marriageType"
										value="${xhJksqTogether.maritalStatus }" /></td>
								<td><label>有无子女：</label> <sen:select clazz="combox"
										name="ywzn" coding="hasChildren"
										value="${xhJksqTogether.ywzn }" />
							</tr>
							<tr>
								<td colspan="3"><label>居住状况：${xhJksqTogether.liveState}--${xhJksqTogether.liveMessage}</label>
									<input type="radio" id="togetherliveStateOne" name="liveState"
									value="01"
									<c:if test='${xhJksqTogether.liveState =="01" }'>checked="checked" </c:if> />自购房屋&nbsp;&nbsp;
									<input type="radio" id="togetherliveStateTwo" name="liveState"
									value="02"
									<c:if test='${xhJksqTogether.liveState =="02" }'>checked="checked" </c:if> />贷款购置房屋&nbsp;&nbsp;
									<input type="radio" id="togetherliveStateThree"
									name="liveState" value="03"
									<c:if test='${xhJksqTogether.liveState =="03" }'>checked="checked" </c:if> />贷款购置房屋</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio"
									id="liveState"
									<c:if test='${xhJksqTogether.liveState == "04"}'>checked</c:if>
									name="liveState" value="04" class="required"
									onclick="liveStateTogether('liveMessage04')" />租房，房租</td>
								<td colspan="2"><input type="text" id="liveMessage04"
									name="liveMessage"
									<c:if test='${xhJksqTogether.liveState == "04"}'> value="${xhJksqTogether.liveMessage}" </c:if> />元/月
								</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio"
									id="liveState" name="liveState"
									<c:if test='${xhJksqTogether.liveState == "05"}'>checked</c:if>
									value="05" class="required"
									onclick="liveStateTogether('liveMessage05')" />其他，说明：</td>
								<td colspan="2"><input type="text" size="80"
									<c:if test='${xhJksqTogether.liveState == "05"}'> value="${xhJksqTogether.liveMessage}"</c:if> />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>主要收入来源：</label></td>
							</tr>
							<tr>
								<td colspan="1">每月工资(含奖金及补助)</td>
								<td colspan="2"><input type="text" id="textmoneyEarnOne"
									name="monthlySalary" value="${xhJksqTogether.monthlySalary }" />元/月</td>
							</tr>
							<tr>
								<td colspan="1">房屋出租</td>
								<td colspan="2"><input type="text" id="textmoneyEarnTwo"
									name="rental" value="${xhJksqTogether.rental }" />元/月</td>
							</tr>
							<tr>
								<td colspan="1">其他所得</td>
								<td colspan="2"><input type="text" id="textmoneyEarnThree"
									name="otherIncome" value="${xhJksqTogether.otherIncome }" />元/年</td>
							</tr>
							<tr>
								<td colspan="1">年总收入</td>
								<td colspan="2"><input type="text" id="annualIncomeIndex"
									name="annualIncome" value="${xhJksqTogether.annualIncome }" />元</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="checkbox"
									id="cBoxSocialFundEdit" name="cBoxSocialFund" value="01"
									<c:if test='${cBoxSocialFundEdit == true }'> checked="checked" </c:if> />是否拥有社保基金：</td>
								<td colspan="2"><input type="radio" id="socialFund1"
									name="socialFund" value="是"
									<c:if test='${xhJksqTogether.socialFund =="是" }'>checked="checked" </c:if> />是
									<input type="radio" id="socialFund2" name="socialFund"
									value="否"
									<c:if test='${xhJksqTogether.socialFund =="否" }'>checked="checked" </c:if> />否</td>
							</tr>
							<tr>
								<td colspan="1"><label>申请额度：</label> <input type="text"
									id="loanQuota" name="loanQuota" size="30" class="required"
									value="${xhJksq.jkLoanQuota }" readonly /></td>
								<td><label>申请还款期限：</label> <input type="text" id="jkCycle"
									name="jkCycle" size="30" class="required"
									value="${xhJksq.jkCycle }" readonly /></td>
								<td><label></label></td>
							</tr>
							<tr>
								<td colspan="3"><label>还款资金来源：</label> <input type="radio"
									id="sourceOfFunds" name="sourceOfFunds" value="01"
									<c:if test='${xhJksqTogether.sourceOfFunds =="01" }'>checked="checked" </c:if>
									onclick="sourceOfFundsEdit('01')" />独立还款&nbsp;&nbsp; <input
									type="radio" id="sourceOfFunds" name="sourceOfFunds" value="02"
									<c:if test='${xhJksqTogether.sourceOfFunds =="02" }'>checked="checked" </c:if>
									onclick="sourceOfFundsEdit('02')" />家人协助还款&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio"
									id="sourceOfFunds" name="sourceOfFunds" value="03"
									<c:if test='${xhJksqTogether.sourceOfFunds =="03" }'>checked="checked" </c:if>
									onclick="sourceOfFundsEdit('03')" />其他方式</td>
								<td align="left" colspan="2"><input type="text"
									id="sourceOfFundsInfo" name="sourceOfFundsInfo" size="30"
									value="${xhJksqTogether.sourceOfFundsInfo }" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel" align="center">
					<h1>联系人信息</h1>

					<table width="100%" class="list">
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

						<c:forEach items="1,2,3,4,5,6" varStatus="goIndex" var="nouse">
							<tr>
								<td align="center"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].ybrgx"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].ybrgx}">
									<input type="hidden" name="xhJkjjlxrs[${goIndex.index}].id"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].id}" /> <input
									type="hidden" name="xhJkjjlxrs[${goIndex.index}].xhJksq.id"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].xhJksq.id}" />
								</td>
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].name"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].name}" /></td>
								<!-- 姓名 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].homePhone"
									class="phoneNumber"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].homePhone}" />
								</td>
								<!-- 住宅电话 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].jjlxrlxdh"
									class="phoneNumber"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].jjlxrlxdh}" />
								</td>
								<!-- 手机电话 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].jjlxrdwdzhjtzz"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].jjlxrdwdzhjtzz}" />
								</td>
								<!-- 住址 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].jjlxrgzdw"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].jjlxrgzdw}" />
								</td>
								<!-- 单位名称 -->
								<td align="left"><input type="text"
									name="xhJkjjlxrs[${goIndex.index}].officePhone"
									class="phoneNumber"
									value="${xhJksqTogether.xhJkjjlxrs[goIndex.index].officePhone}" />
								</td>
								<!-- 单位电话 -->
							</tr>
						</c:forEach>

						</tbody>
					</table>
				</div>
				<div class="formBar">
					<ul>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" class="close">编辑共借人信息
										请在列表选择"共借"信息</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	</div>
	<div class="tabsFooter">
		<div class="tabsFooterContent"></div>
	</div>
	</div>
</c:if>
<script type="text/javascript"
	src="${ctx}/ext/indentification/idTransfer.js"></script>
<script type="text/javascript">

    //应该加入公用js
    function datePicker($p){
         $("input[type=text], input[type=password], textarea", $p).addClass("textInput").focusClass("focus");
         $('input.date', $p).each(function(){
			var $this = $(this);
			var opts = {};
			if ($this.attr("dateFmt")) opts.pattern = $this.attr("dateFmt");
			if ($this.attr("minDate")) opts.minDate = $this.attr("minDate");
			if ($this.attr("maxDate")) opts.maxDate = $this.attr("maxDate");
			if ($this.attr("mmStep")) opts.mmStep = $this.attr("mmStep");
			if ($this.attr("ssStep")) opts.ssStep = $this.attr("ssStep");
			$this.datepicker(opts);
		});
    }

    function sqrChange($box){ 
		$("#jkrxm",$box).keyup(function(){ 
			var jkrname=$(this).val(); 		
			$("#sqr",$box).val(jkrname); 
		}); 
	} 
	function spouseExchange($box) {
		var $spouse = $(".spouse", $box);
		var maritalStatus = $('input[name="maritalStatus"]:checked', $box)
				.val();
		var $isMarrayInput = $('#isMarrayInput', $box);
		$spouse.attr('disabled', maritalStatus != '已婚');
		if (maritalStatus != '已婚') {
			$spouse.removeClass('error');
			$isMarrayInput.hide();
		} else {
			$isMarrayInput.show();
		}

		//配偶选项
		$('input[name="maritalStatus"]', $box).change(function() {
				spouseExchange($box);
		});
	}

	function loantgFormSubmit(obj) {
		var $form = $(obj);
		var $box = navTab.getCurrentPanel();
        //if("1".equals($("#opt", $box).val())){		
			if (!$form.valid()) {
				return false;
			}
		//}
		var $box = navTab.getCurrentPanel();
		var $box = navTab.getCurrentPanel();
	
		return validateCallback(obj, navTabAjaxDone);
	}

	/* function isZjhm(value) {
		if (value == "") {
			return;
		}
		var $box = navTab.getCurrentPanel();
		if ($("#pocertificates").val() == '身份证') {
			if (!(value.length == 15 || value.length == 18))
				return;
		}
		$
				.ajax({
					url : "${ctx}/xhNewJksq/isIdInfoByZjhm",
					cache : false,
					global : false,
					async : false,
					type : 'post',
					data : {
						zjhm : value
					},
					success : function(data) {
						if (data.data >= "1" || data.dataRelation != "") {
							document.getElementById("isZjhm").href = "${ctx}/xhNewJksq/loadIdInfo?cradId="
									+ value + "&&ID="+data.dataRelation;
							$("#isZjhm").click();
						}
					}
				});
		
		$.post("${ctx}/xhNewJksq/checkBlackList",{zjhm:value},function(result){
			if(result=='1'){
				//alert("黑名单客户");
				alertMsg.warn("黑名单客户，系统将自动拒绝进件！");
			}
		})
	} */

	//居住状态
	function houseHandle() {
		var $box = navTab.getCurrentPanel();
		var selected = $("input[name='liveState']:checked", $box).val();
		$("input[id^='liveMessage']", $box).attr('disabled', true);
		if (selected) {
			$("input#liveMessage" + selected, $box).attr('disabled', false)
					.val('${xhcfDkrxx.liveMessage}');
		}

		$("input[name='liveState']", $box).click(
				function() {
					var $this = $(this);
					var $houseInput = $("input[id^='liveMessage']", $box).attr(
							'disabled', true).val("");
					$("input#liveMessage" + $this.val(), $box).attr('disabled',
							false);
				});
	}
	//借款类型处理 老板借等
	function typeHandle() {
		var $box = navTab.getCurrentPanel();
		$("div[id^='productType']", $box).hide();
		var selected = $("input[name='jkType']:checked", $box).val();
		if (selected) {
			var $divProduct = $("div#productType" + selected, $box).show();
			$('input', $divProduct).addClass("required");
		}
		$(".jkType", $box).click(function() {
			var $this = $(this);
			var $allProduct = $("div[id^='productType']", $box).hide();
			$('input', $allProduct).removeClass("required");
			var $divProduct = $("div#productType" + $this.val(), $box).show();
			$('input', $divProduct).addClass("required");
		});
	}
    
    /*
      
    */
	function cardChange($box) {
		//var card =  $("#pocertificates",$box).val();
		var card = $("input[name='pocertificates']:checked").val();
		if (card != "") {
			$("#zjhm", $box).removeAttr("readonly").removeClass("readonly");
		} else {
			$("#zjhm", $box).attr("readonly", true).addClass("readonly");
		}

		if (card == '身份证') {
			$("#zjhm", $box).addClass("isIdCardNo");
		} else {
			$("#zjhm", $box).removeClass("isIdCardNo");
		}

		//身份证验证
		$("input[name='pocertificates']", $box).change(
				function() {

					var card = $("input[name='pocertificates']:checked").val();
					if (card != "") {
						$("#zjhm", $box).removeAttr("readonly").removeClass(
								"readonly");
					} else {
						$("#zjhm", $box).attr("readonly", true).addClass(
								"readonly");
					}
					if (card == '身份证') {
						$("#zjhm", $box).addClass("isIdCardNo");
					} else {
						$("#zjhm", $box).removeClass("isIdCardNo");
					}
				});

		$("#zjhm", $box).keyup(function() {
			var $this = $(this);
			var card = $("input[name='pocertificates']:checked", $box).val();

			if (card == '身份证') {
				var cardNo = $this.val();
				if (cardNo.length == 15 || cardNo.length == 18) {
					var gender = getGender(cardNo) == 0 ? '男' : '女';
					$("input[value='" + gender + "']", $box).click();
					$("input[name='birthday']", $box).val(getBirthday(cardNo));
				}
			}
		});
	}

    /*
     	 根据选中的是全款还是按揭设置必填项目
    */
	function changeHouseType($houseDiv) {
		var val = $("input[name $='typeh']:checked",$houseDiv).val();
		if (val == 2) {//2 是按揭
			$(".borrowMoney",$houseDiv).addClass("required").attr('disabled',false);
		} else {
			$(".borrowMoney",$houseDiv).removeClass("required").removeClass("error").attr('disabled',true);
		}
		
	}
	
	//根据参数，是全款还是按揭设置必填项目，同时添加change事件
	function houseSet($houseDiv){
	           changeHouseType($houseDiv);
	          $("input[name $='typeh']",$houseDiv).change(function(){
	             changeHouseType($houseDiv);
	          });    
	}

	function changeBorrow($box) {
	    //对每个房产根据全款/按揭 处理必选项
	    $("div.houseInfo",$box).each(function(){
	          var $this = $(this);
	          houseSet($this);    
	    });
	}
    
    function getCreditTr($box){
    	var count = $('#creditTable',$box).find('tr').length-4;
    	var creditTr  = '<tr>';
			creditTr +=    '<td>';
			creditTr += '   <a title="删除"  href="#" class="btnDel pageCreditDelete" ajaxTarget="credit"';
			creditTr +=    '<input type="hidden" name="xhJksqCredits[{creditIndex}].xhJksq.id" value="${xhJksq.id}" /></td>';
			creditTr +=	   '<td><sen:selectRadio coding="guarantyType" name="xhJksqCredits[{creditIndex}].typeh" clazz="required"/></td>';
			creditTr +=    '<td><input errorTitle="抵押物品"  name="xhJksqCredits[{creditIndex}].mortage"  class=""/></td>';
			creditTr +=    '<td><input errorTitle="机构名称" name="xhJksqCredits[{creditIndex}].compBankName"  class="required"/></td>';
			creditTr +=	   '<td><input errorTitle="贷款额度" name="xhJksqCredits[{creditIndex}].loanAmount"  class="required number"/></td>';
			creditTr +=    '<td><input errorTitle="每月供额度" name="xhJksqCredits[{creditIndex}].monthReturn"  class="required number"/></td>';
			creditTr +=	   '<td><input errorTitle="贷款余额" name="xhJksqCredits[{creditIndex}].remain"  class="required number"/></td>';
			creditTr += '</tr>';
        creditTr =  creditTr.replace(/{creditIndex}/g,count);
        return creditTr;
    }
    
    
    
    
    
    
    function showOrHideCompany($box){

       var jkType = $("input[name='jkType']:checked",$box).val();
       var  $companyDiv  = $("div.companyDiv",$box);
       if(jkType == 'A' || jkType=='B' || jkType == 'Q'){
           $companyDiv.find('input').attr('disabled',false);
           $companyDiv.show();
       }else{
           $companyDiv.find('input').attr('disabled',true);
           $companyDiv.hide();
       }
    }
	
	function companyHandle($box){
		showOrHideCompany($box);
		$("input[name='jkType']",$box).change(function(){
			showOrHideCompany($box);
		});
	}
	
	
	function getHouseInfoDiv($box){
      var jksqId = $("input[name='id']",$box).val();
      var jksqIdInput='';
      if(jksqId)
	     jksqIdInput = '<input type="hidden" name="xhJksqHouses[{houseIndex}].xhJksq.id" value="${xhJksq.id}" />';
      
      var houseDiv = '<div class="houseInfo">\
						<table width="100%" class="list"  >\
						<tr>\
							<td colspan="1"><a title="删除"  href="#" class="btnDel deleteHouseInfo">删除</a></td>\
							<td colspan="5" align="center">房产具体信息</td>\
						</tr>\
						<tr>\
							<td colspan=""><label>房产地址：</label> </td>\
							<td colspan="3">\
  							   <input name="xhJksqHouses[{houseIndex}].address" type="text"  size="100" class="required" maxlength="200" />';
      houseDiv += jksqIdInput;
      houseDiv +='</td>\
						</tr>\
						<tr>\
							<td><label>购买方式：</label> </td>\
							<td colspan="1"> <sen:selectRadio name="xhJksqHouses[{houseIndex}].typeh" coding="buyType" split="&nbsp;&nbsp;&nbsp;&nbsp;"	clazz="required controlBorrow" /></td>\
							<td><label>购买日期：</label> </td>\
							<td colspan="1"> <input type="text" name="xhJksqHouses[{houseIndex}].buyDate" class="date" format="yyyy-MM-dd" pattern="yyyy-MM-dd"  readonly="readonly"/><a class="inputDateButton" href="javascript:;">选择</a></td>\
							<td></td>\
							<td></td>\
						</tr>\
						<tr>\
							<td><label>建筑年份：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].buildYear" type="text"  /></td>\
							<td><label>销售面积：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].area" class="number" type="text"/></td>\
							<td></td>\
							<td></td>\
						</tr>\
						<tr>\
							<td><label>按揭银行：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].bank" type="text"  class="borrowMoney" maxlength="50" /></td>\
							<td><label>贷款总额：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].loanMoney" type="text" class="borrowMoney number" maxlength="22" /></td>\
							<td><label>贷款年限：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].loanMonth" type="text"  class="borrowMoney number" maxlength="22" /></td>\
							<td></td>\
						</tr>\
						<tr>\
							<td><label>购买价格：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].buyMoney" type="text"  class="borrowMoney number" maxlength="22" /></td>\
							<td><label>尚欠余额：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].remainmoney" type="text"  class="borrowMoney number" maxlength="22" /></td>\
							<td><label>月还款：</label> </td><td colspan="1"> <input name="xhJksqHouses[{houseIndex}].monthReturn" type="text"  class="borrowMoney number" maxlength="22" /></td>\
							<td></td>\
						</tr>\
					   </table>\
				</div>';
		return houseDiv;
    }
    
    function addHouseInfos($box){
    	            //取得已有房产的个数
				    var count = $('div.houseInfo',$box).length;                  
                    var houseDiv = getHouseInfoDiv($box);
                    houseDiv =  houseDiv.replace(/{houseIndex}/g,count);
                    $houseInfoHeaderDiv = $('div.houseInfoHeaderDiv',$box);
                    var $houseDiv = $(houseDiv); 
                    $houseDiv.insertAfter($houseInfoHeaderDiv);
                    $("a.deleteHouseInfo",$houseDiv).click(function(){
                         var $parentDiv = $(this).parents("div.houseInfo");
                         $parentDiv.remove();
                         return false;
                    });
                    //全款，按揭的处理
                    houseSet($houseDiv);  
                    //时间事件的处理
                    datePicker($houseDiv);
    }
    
	function addOrRemoveHouseInfo($box){
	   
	    //绑定添加房产的事件
		$("a.addHouseDiv",$box).click(function(){
		   alertMsg.confirm("确认添加房产信息吗？", {
				okCall: function(){  
				    addHouseInfos($box);
		        }
		   });
		   return false;
		});
	}
	
	function getCompanyDiv($box){
         var jksqId = $("input[name='id']",$box).val();
         var jksqIdInput='';
         if(jksqId)
	        jksqIdInput = '<input type="hidden" name="xhJksqCompanys[{companyIndex}].xhJksq.id" value="${xhJksq.id}" />';
	       
	     var companyDiv = '<div class="companyInfo">';
			 companyDiv += '<table class="list" width="100%">';
			 companyDiv += '<tr><td colspan="1"><a title="删除"  href="#" class="btnDel pageCompanyDelete">删除</a></td><td colspan="5" align="center">企业具体信息</td></tr>';
			 companyDiv += '<tr><td><label>营业执照注册号：</label></td><td><input type="hidden" name="xhJksqCompanys[{companyIndex}].id"/><input name="xhJksqCompanys[{companyIndex}].busiLicences" type="text" class="required" maxlength="200" />';
			 companyDiv +=  jksqIdInput;
			 companyDiv += '</td><td width="25%"><label>成立日期：</label></td><td><input name="xhJksqCompanys[{companyIndex}].startDate" type="text" size="20" class="date required" pattern="yyyy-MM-dd" format="yyyy-MM-dd" /><a class="inputDateButton" href="javascript:;">选择</a></td></tr>';
			 companyDiv += '<tr><td><label>注册资金：</label></td><td><input name="xhJksqCompanys[{companyIndex}].registerMoney" type="text" size="20" class="required number" /></td><td><label>经营场所：</label></td><td><input name="xhJksqCompanys[{companyIndex}].areaType" type="text"  class="" /></td></tr>';
			 companyDiv += '<tr><td><label>营业面积：</label></td><td><input name="xhJksqCompanys[{companyIndex}].areaSquare" type="text"  class="number" /></td><td><label>月还款/租金￥</label></td><td><input name="xhJksqCompanys[{companyIndex}].moneyUsed" type="text"  class="number" /></td></tr>';
			 companyDiv += '<tr><td><label>淡季月份：</label></td><td><input name="xhJksqCompanys[{companyIndex}].weakMonth" type="text"  class="" /></td><td><label>销售额：</label></td><td><input name="xhJksqCompanys[{companyIndex}].weakMonthEarn" type="text"  class="number" /></td></tr>';
			 companyDiv += '<tr><td colspan="1"><label>旺季月份：</label></td><td><input name="xhJksqCompanys[{companyIndex}].strongMonth" type="text"  class="" /></td><td><label>销售额：</label></td><td><input name="xhJksqCompanys[{companyIndex}].strongMonthEarn" type="text"  class="number" /></td></tr>';
			 companyDiv += '<tr><td colspan="1"><label>平季月份：</label></td><td><input name="xhJksqCompanys[{companyIndex}].middleMonth" type="text"  class="" /></td><td><label>销售额：</label></td><td><input name="xhJksqCompanys[{companyIndex}].middleMonthEarn" type="text"  class="number" /></td></tr>';
			 companyDiv += '<tr><td rowspan="3" colspan="4"><table><tr><td rowspan="3" >主要供应商：<td width="2">1:</td><td><input name="xhJksqCompanys[{companyIndex}].supplierOne" type="text" size="80" class="" /></td></tr>';
			 companyDiv += '<tr><td width="2" colspan = "1">2:</td><td> <input name="xhJksqCompanys[{companyIndex}].supplierTwo" type="text" size="80" class="" /></td></tr>';
			 companyDiv += '<tr><td width="2" colspan = "1">3:</td><td><input name="xhJksqCompanys[{companyIndex}].supplierThree" type="text" size="80"  class="" /></td></tr>';
			 companyDiv += '</table></td></tr></table></div>';
	      return companyDiv;
    }
    
	function addOrRemoveCompanyInfo($box){
	    //绑定添加房产的事件
		$("a.addCompanyDiv",$box).click(function(){
		   alertMsg.confirm("确认添加企业资料吗？", {
				okCall: function(){  
				    debugger;
				    //取得已有房产的个数
				    var count = $('div.companyInfo',$box).length;                  
                    var companyDiv = getCompanyDiv($box);
                    companyDiv =  companyDiv.replace(/{companyIndex}/g,count);
                    $companyInfoHeaderDiv = $('div.companyInfoHeaderDiv',$box);
                    var $companyDiv = $(companyDiv); 
                    $companyDiv.insertAfter($companyInfoHeaderDiv);
                    $("a.pageCompanyDelete",$companyDiv).click(function(){
                         var $parentDiv = $(this).parents("div.companyInfo");
                         $parentDiv.remove();
                         return false;
                    });
                    //时间事件的处理
                    datePicker($companyDiv);
		        }
		   });
		   return false;
		});
	}
	
	function getOfficeDiv($box){
	     var jksqId = $("input[name='id']",$box).val();
         var jksqIdInput='';
         if(jksqId)
	        jksqIdInput = '<input type="hidden" name="xhJksqOffices[{officeIndex}].xhJksq.id" value="${xhJksq.id}" />';
	     var officeDiv =  '<div class="officeInfo"><table class="list" width="100%">';
	         officeDiv += '<tr><td colspan="1"><a title="删除"  href="#" class="btnDel pageOracleDelete" ajaxTarget = "office" ajaxData="${officeInfo.id}">删除</a></td><td colspan="5" align="center">职业信息/公司资料--具体信息</td></tr>';
			 officeDiv += '<tr><td><label>名称：</label></td><td><input name="xhJksqOffices[{officeIndex}].name" type="text"  class="required" maxlength="80" size="60" />';
			 officeDiv += jksqIdInput;
			 officeDiv += '</td><td><label>邮编：</label></td><td><input name="xhJksqOffices[{officeIndex}].postcode" type="text"  class="digits" maxlength="10" /></td></tr>';
		     officeDiv += '<tr><td><label>地址：</label></td><td><input name="xhJksqOffices[{officeIndex}].address" type="text"  class="required" maxlength="200" size="60" /></td><td><label>单位类型：</label></td><td><sen:selectRadio name="xhJksqOffices[{officeIndex}].typeh"  coding="utilsType" split="&nbsp;&nbsp;&nbsp;&nbsp;" clazz="required" /></td></tr>';
		     officeDiv += '<tr><td><label>单位电话：</label></td><td><input name="xhJksqOffices[{officeIndex}].phone" type="text"  class="phoneNumber" maxlength="20" /></td><td><label>单位网址：</label></td><td><input name="xhJksqOffices[{officeIndex}].website" type="text"  class="" maxlength="70" size="60" /></td></tr>';
		     officeDiv += '<tr><td><label>行业类别：</label></td><td><input name="xhJksqOffices[{officeIndex}].typea" type="text"  class="required" maxlength="20" /></td><td><label>职务：</label></td><td><input name="xhJksqOffices[{officeIndex}].duty" type="text"  class="required" maxlength="20" /></td></tr>';
             officeDiv += '<tr><td><label>部门：</label></td><td><input name="xhJksqOffices[{officeIndex}].department" type="text"  class="" maxlength="30" /></td><td><label>员工数量：</label></td><td><input name="xhJksqOffices[{officeIndex}].personCount" type="text"  class="digits" maxlength="22" /></td></tr>';
			 officeDiv += '<tr><td><label>每月收入(元)：</label></td><td><input name="xhJksqOffices[{officeIndex}].monthSalary" type="text"  class="number" maxlength="22" /></td><td><label>每月支薪日：</label></td><td><input name="xhJksqOffices[{officeIndex}].salaryDay" type="text"  class="digits" maxlength="22" /></td></tr>';
			 officeDiv += '<tr><td><label>支付方式</label></td><td><input name="xhJksqOffices[{officeIndex}].salaryType" type="text"  class="" maxlength="10" /></td><td><label>服务年数：</label></td><td><input name="xhJksqOffices[{officeIndex}].workYear" type="text"  class="number" maxlength="22" /></td></tr>';
			 officeDiv += '<tr><td><label>其他收入(元)：</label></td><td><input name="xhJksqOffices[{officeIndex}].bonus" type="text"  class="number" maxlength="22" /></td><td></td><td></td></tr></table></div>';
		 return officeDiv;
	}
	
	function addOfficeInfos($box){
		     		var count = $('div.officeInfo',$box).length;                  
                    var officeDiv = getOfficeDiv($box);
                    officeDiv =  officeDiv.replace(/{officeIndex}/g,count);
                    $officeInfoHeaderDiv = $('div.officeInfoHeaderDiv',$box);
                    var $officeDiv = $(officeDiv); 
                    $officeDiv.insertAfter($officeInfoHeaderDiv);
                    $("a.pageOracleDelete",$officeDiv).click(function(){
                         var $parentDiv = $(this).parents("div.officeInfo");
                         $parentDiv.remove();
                         return false;
                    });
                    //时间事件的处理
                    datePicker($officeDiv);
	}
	
	function addOrRemoveOfficeInfo($box){
	    //绑定添加职业资料的事件
		$("a.addOfficeDiv",$box).click(function(){
		   alertMsg.confirm("确认添加职业资料吗？", {
				okCall: function(){  
				    addOfficeInfos($box);
		        }
		   });
		   return false;
		});
	}
	

	
	function relateTr(data){
		   var tr  =  "<tbody><tr class='trbg'><td>"+data.JKRXM+"</td><td>"+data.ZJHM+"</td><td>"+data.CITY+"</td><td><b><font color=red>"+data.STATE+"</font></b></td><td>"+data.JK_TYPE+"</td><td>"+data.MONEY+"</td>";
		       tr +=  "<td>"+data.JKCYCLE+"</td><td>"+data.TEAM+"</td><td>"+data.CUSTOMER+"</td><td>"+data.APPLYTIME+"</td></tr></tbody>";
		   return tr;
		}
	
	function getContentOf(items,itemName){
	      if(!items)
	         return "";
	      var titles = {myown:"该客户本人历史申请记录",family:'该客户配偶历史申请记录',together:'该客户作为共借人的历史申请记录'};
	      var hTitle = eval("titles." + itemName);
	   	  var itemContent =  ' <div class="panel" id="' + itemName + 'Div" align="center" ><div class="panelHeader"><div class="panelHeaderContent">';
			  itemContent += '<h1 >'+ hTitle +'</h1></div></div>';
		  	  itemContent += '<div class="panelContent"><table width="100%" class="list" >\
						<thead><tr>\
							<th width="60">客户姓名</th>\
							<th width="60">身份证号</th>\
							<th width="60">城市</th>\
							<th width="100">状态</th>\
							<th width="60">产品</th>\
							<th width="80">申请金额(元)</th>\
							<th width="60">期数(月)</th>\
							<th width="60">团队经理</th>\
							<th width="60">客户经理</th>\
							<th width="140">进件时间</th>\
						</tr></thead>';
			for(var i = 0 ; i < items.length ; i++){
			    itemContent += relateTr(items[i]);
			}
			itemContent += '</tbody></table></div><div class="panelFooter"><div class="panelFooterContent"></div></div></div><div class="divider"></div>';
			return itemContent;
	}
	function getContent(data){
	   var content  = '<div class="pageFormContent" ><div class="pageForm">';
	   content += getContentOf(data.myown,'myown');
	   content += getContentOf(data.family,'family');
	   content += getContentOf(data.together,'together');	   
	   content     += '</div></div>';
	   
	   return      content;
	}
	
	function loadItems(cardId){
	    debugger;
	    var $box    =  navTab.getCurrentPanel();
	    var myownId =  $("#id",$box).val();
	    $.post("${ctx}/xhNewJksq/checkBlackList",{zjhm:cardId},function(result){
			if(result=='1'){
				//alert("黑名单客户");
				alertMsg.warn("黑名单客户，系统将自动拒绝进件！");
			}
		});
		$.ajax({
					url : "${ctx}/xhNewJksq/loadRelatedItems",
					cache : false,
					global : false,
					async : false,
					type : 'post',
					data : {
						cardId : cardId,
						myownId: myownId
					},
					success : function(data) {
					    debugger;
						if (data.myown || data.family || data.together ) {
						    var url = "${ctx}/xhNewJksq/onlyRelatedPage";
						    var options = $.extend($.pdialog._op,{width:900,height:450,mask:true});
						    
						    $.pdialog.open("none", "_blank", "该证件号码存在以下相关申请信息，请认真核对是否符合进件条件！", options);
						    var $currentDialog = $.pdialog.getCurrent();
						    $(getContent(data)).appendTo($("div.dialogContent",$currentDialog));
						}
					}
		});
	
	}
	
	
	$(function() {
		var $box = navTab.getCurrentPanel();
		/*alert('good');
		$('input').on('keypress',function(){
			 var code = (e.keyCode ? e.keyCode : e.which);
			 alert(code);
		     if(code == 13) { 
		         alert("Enter key Pressed");
		     }
		     return false;
		});*/
		companyHandle($box);
	    $("div#togetherDiv",$box).find('input').attr('disabled',true);
		
		//处理按揭方式的required
		changeBorrow($box);		
		spouseExchange($box);
		cardChange($box);
		//借款类型处理 老板借等
		typeHandle();
		//住房处理
		houseHandle();
		//企业资料处理
        
        //添加房产
        addOrRemoveHouseInfo($box);
        //添加企业资料
        addOrRemoveCompanyInfo($box);
        //添加职业信息
        addOrRemoveOfficeInfo($box);
        
        //申请人身份证验证
        $("#zjhm",$box).bind('blur',function(){
            var cardId = $(this).val();
            //加载相关申请
            loadItems(cardId);
        });
        //配偶身份证验证
        $("#spouseZjhm",$box).bind('blur',function(){
            var cardId = $(this).val();
            //加载相关申请
            loadItems(cardId);
        });
        
        
        
        var houseCount = $('div.houseInfo',$box).length;  
        if(houseCount == 0 && $("#id",$box).val() == ""){
        	 addHouseInfos($box);
        }
        
        var officeCount = $('div.officeInfo',$box).length;
        if(officeCount == 0 && $("#id",$box).val() == ""){
            addOfficeInfos($box);
        }                
        
		$('#tempSave',$box).click(function() {

			$("#opt", $box).val('0');
		});

		$('#realSubmit',$box).click(function() {

			$("#opt", $box).val('1');
		});
		
		
		//用户添加新信审记录
        $("#addCredit",$box).click(function(){
            alertMsg.confirm("确认添加新信用记录吗？", {
				okCall: function(){
					 var trStr = getCreditTr($box);
            		 $(trStr).appendTo($("table#creditTable",$box));
            		 $("a.pageCreditDelete",$box).on('click',function(){
            		 		  var $parentTr = $(this).parents('tr');
            		 		  //alertMsg.confirm("确认删除该信用记录吗？", {
							  //okCall: function(){
								   $parentTr.remove();
						      //}
							  //});
							  return false;
            		 });
				}
			});
           
       		return false;
       });
       
       
       
       
       //数据库已保存的房产资料资料删除 --- 和其他的删除事件可以合为一个方法
       $("a.oracleDeleteCredits",$box).click(function(){
          var url = "${ctx}/xhNewJksq/deleteJksqCredit/" + $(this).attr('ajaxData');
          var $parentTr = $(this).parents('tr');
          alertMsg.confirm("<font color='red'>确认删除该信用记录吗？ ，点击确定后不点击提交按钮也会直接删除该条记录</font>", {
				okCall: function(){                    
                     $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
       });
       
        //关联表的数据库删除操作
        $("a.oracleDelete",$box).click(function(){
          var $this = $(this);
          var ajaxTarget = $this.attr('ajaxTarget');
          var url = "${ctx}/xhNewJksq/delete/" + $this.attr('ajaxTarget') +"/" +  $this.attr('ajaxData');
          var message = '';
          if(ajaxTarget == 'house'){
          		message = "<font color='red'>确认删除该房产记录吗？ ，点击确定后不点击提交按钮也会直接删除该条记录</font>";
          }
          if(ajaxTarget == 'company'){//企业补充信息
          		message = "<font color='red'>确认删除该企业记录吗？ ，点击确定后不点击提交按钮也会直接删除该条记录</font>";
          }
          if(ajaxTarget == 'office'){//企业补充信息
          		message = "<font color='red'>确认删除该职业信息吗？ ，点击确定后不点击提交按钮也会直接删除该条记录</font>";
          }
          var $parentDiv = $(this).parents('div.' + ajaxTarget + 'Info');
          alertMsg.confirm(message, {
				okCall: function(){                    
                     $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
							    if(data == '1'){
									$parentDiv.remove();
								}
							}
		            });
		         }
		  });
		  return false;
       });
          
		    
       <c:if test="${look != null}">
          $('input',$box).attr('disabled',true);
          $('select',$box).attr('disabled',true);
       </c:if>
	});
</script>