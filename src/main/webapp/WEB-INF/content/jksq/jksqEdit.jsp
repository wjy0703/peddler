<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li>
					   <a href="javascript:;"><span>借款申请信息</span></a>
					</li>
					
					<%-- <c:if test='${jksq.togetherPerson == "是"}'> --%>
					   <li id="togetherTab"><a href="javascript:;"><span>共同还款人信息</span></a></li>
<%-- 					</c:if>
 --%>					
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div id="mainDiv">
				<form id="jksqEditMsgForm" name="jksqEditMsgForm" method="post" action="${ctx}/jksq/editSaveJksqMsg" class="pageForm required-validate" onsubmit="return jksqEditMsgFormSubmit(this);">
					<div class="pageFormContent" layoutH="56">
						<input type="hidden" id="xydkzxId" name="xydkzxId" value="${xydkzx.id }" /> <input type="hidden" id="opt" name="opt" />
						<div class="panel">
							<h1>客户基本信息</h1>
						</div>
						<table nowrapTD="false" width="100%">
							<tr>
								<td><label>借款人姓名:</label> <input type="text" id="jkrxm" name="jkrxm" size="30" value="${jksq.jkrxm }" class="required"> <input type="hidden" id="id" name="id" size="30"
									value="${jksq.id }" /></td>
								<td><label>性别：</label> <input type="radio" id="genders" name="genders" value="男" <c:if test='${jksq.genders == "男"}'>checked="checked" </c:if> />男&nbsp; <input type="radio" id="genders"
									name="genders" value="女" <c:if test='${jksq.genders == "女"}'>checked="checked" </c:if> />女</td>
								<td><label>出生日期：</label> <input type="text" name="birthday" class="required date" yearstart="-113" yearend="35" pattern="yyyy-MM-dd" size="27" value="${jksq.birthday }" /> <a
									class="inputDateButton" href="javascript:;">选择</a></td>
							</tr>
							<tr>
								<td><label>证件类型：</label> <sen:select clazz="combox required" name="pocertificates" coding="cardType" id="pocertificates" value="${jksq.pocertificates}" /></td>
								<td><label>证件号码：</label> <input type="text" id="zjhm" name="zjhm" size="30" class="required" value="${jksq.zjhm }" /></td>
							</tr>
							<tr>
								<td colspan="3"><label>户籍地址：</label> <input type="text" id="hjadress" name="hjadress" size="60" class="required" value="${jksq.hjadress }" /></td>
							</tr>
							<tr>
								<td colspan="3"><label>现住址:</label> <input type="text" id="homeAddress" name="homeAddress" size="60" class="required" value="${jksq.homeAddress }" /></td>
							</tr>
						</table>
						<div class="divider"></div>
						<table nowrapTD="false" width="100%">
							<tr>
								<td><label>家庭电话：</label> <input type="text" id="homePhone" name="homePhone" size="30" class="" value="${jksq.homePhone }" /></td>
								<td><label>工作单位：</label> <input type="text" id="company" name="company" size="30" class="" value="${jksq.company }" /></td>
							</tr>
							<tr>
								<td><label>单位电话：</label> <input type="text" id="companyPhone" name="companyPhone" size="30" class="" value="${jksq.companyPhone }" /></td>
								<td><label>单位地址：</label> <input type="text" id="companyAdress" name="companyAdress" size="30" class="" value="${jksq.companyAdress }" /></td>
								<td><label>单位性质：</label> <sen:select value="${jksq.companyNature}" clazz="combox" name="companyNature" coding="officeType" id="companyNature" /></td>
							</tr>
							<tr>
								<td><label>移动电话：</label> <input type="text" id="telephone" name="telephone" size="30" class="required" value="${jksq.telephone }" /></td>
								<td><label>电子邮箱：</label> <input type="text" id="email" name="email" size="30" class="" value="${jksq.email }" /></td>
								<td><label>婚姻状况：</label> <sen:select value="${jksq.maritalStatus}" clazz="combox required" name="maritalStatus" coding="marriageType" id="maritalStatus" /></td>
							</tr>
							<tr>
								<td><label>有无子女：${jksq.ywzn}</label> <sen:select value="${jksq.ywzn}" clazz="combox" name="ywzn" coding="hasChildren" id="ywzn" /></td>
								<td><label>QQ号码：</label> <input type="text" id="qqhm" name="qqhm" size="30" value="${jksq.qqhm }" /></td>
								<td><label>月收入(元)：</label> <input type="text" id="annualIncome" name="annualIncome" size="10" class="number" value="${jksq.annualIncome }" /> <span class="info">(数字格式,无千分位)</span></td>
							</tr>
							<tr>
								<td colspan="3"><label>收入说明：</label> <input type="text" id="incomeIllustration" name="incomeIllustration" size="60" class="" value="${jksq.incomeIllustration }" /></td>
							</tr>
						</table>
						<div class="divider"></div>
						<table>
							<tr>
								<td colspan="1"><label>居住状态：</label> <input type="radio" id="liveState" name="liveState" value="01" <c:if test='${jksq.liveState == "01" }'> checked="checked" </c:if> />自有房屋，有无借款，月供</td>
								<td><input id="liveMessage01" name="liveMessage" type="text" value="" />元</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio" id="liveState" name="liveState" value="02" <c:if test='${jksq.liveState == "02" }'> checked="checked" </c:if> />亲属产权</td>
								<td><input type="hidden" id="liveMessage02" name="liveMessage" value="" /></td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio" id="liveState" name="liveState" value="03" <c:if test='${jksq.liveState == "03" }'> checked="checked" </c:if> />租房，房租</td>
								<td><input type="text" id="liveMessage03" name="liveMessage" value="" />元/月</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio" id="liveState" name="liveState" value="04" <c:if test='${jksq.liveState == "04" }'> checked="checked" </c:if> />其他，说明：</td>
								<td><input type="text" id="liveMessage04" name="liveMessage" size="80" value="" /></td>
							</tr>
							<tr>
								<td colspan="2">
									<dl class="nowrap">
										<dt>通讯地址：</dt>
										<dd>
											<sen:address names="province.id,city.id,area.id" titles="所属省市,所属城市,所有区县" required="true" values="${jksq.province.id},${jksq.city.id},${jksq.area.id}" />
										</dd>
									</dl></td>
							</tr>
							<tr>
								<td colspan="3"><label></label> <input type="text" name="txdz" size="80" value="${jksq.txdz }" maxlength="100" /></td>
							</tr>
						</table>
						<br />
						<div id="isMarrayEdit" style="display: block;">
							<div class="panel">
								<h1>以下为配偶信息（借款人婚姻状况为已婚时必填项）</h1>
							</div>
							<table width='100%'>
								<tr>
									<td><label>姓名:</label> <input type="text" id="spouseName" name="spouseName" size="30" value="${jksq.spouseName }" class="required spouse" /></td>
									<td><label>性别：</label> <input type="radio" id="spouseGenders" name="spouseGenders" value="男" class="spouse" <c:if test='${jksq.spouseGenders == "男" }'>checked="checked" </c:if> />男&nbsp;
										<input type="radio" id="spouseGenders" name="spouseGenders" value="女" class="spouse" <c:if test='${jksq.spouseGenders == "女" }'>checked="checked" </c:if> />女</td>
									<td><label>出生日期：</label> <input type="text" name="spouseBirthday" class="date spouse" yearstart="-113" yearend="5" format="yyyy-MM-dd" pattern="yyyy-MM-dd" size="27" readonly="readonly"
										value="${jksq.spouseBirthday }" /> <a class="inputDateButton spouse" href="javascript:;" id="spouRiqiEdit" name="spouRiqiEdit">选择</a>
									</td>
								</tr>
								<tr>
									<td><label>现住址：</label> <input type="text" id="spouseAdress" name="spouseAdress" size="30" value="${jksq.spouseAdress }" class="spouse" /></td>
									<td><label>证件类型：</label> <sen:select clazz="combox spouse" name="spousePocertificates" coding="cardType" id="spousePocertificates" value="${jksq.spousePocertificates}" /></td>
									<td><label>证件号码：</label> <input type="text" id="spouseZjhm" name="spouseZjhm" size="30" value="${jksq.spouseZjhm }" class="required spouse" /></td>
								</tr>
								<tr>
									<td><label>移动电话：</label> <input type="text" id="spouseTelephone" name="spouseTelephone" size="30" value="${jksq.spouseTelephone }" class="required spouse" /></td>
									<td><label>家庭电话：</label> <input type="text" id="spouseHomePhone" name="spouseHomePhone" size="30" value="${jksq.spouseHomePhone }" class="spouse" /></td>
									<td><label>工作单位：</label> <input type="text" id="spouseCompany" name="spouseCompany" size="30" value="${jksq.spouseCompany }" class="spouse" /></td>
								</tr>
								<tr>
									<td><label>部门与职务：</label> <input type="text" id="spouseDepFunction" name="spouseDepFunction" size="30" value="${jksq.spouseDepFunction }" class="spouse" /></td>
									<td><label>单位电话：</label> <input type="text" id="spouseCompanyPhone" name="spouseCompanyPhone" size="30" value="${jksq.spouseCompanyPhone }" class="spouse" /></td>
									<td><label>单位地址：</label> <input type="text" id="spouseCompanyAdress" name="spouseCompanyAdress" size="30" value="${jksq.spouseCompanyAdress }" class="spouse" /></td>
								</tr>
								<tr>
									<td><label>月收入(元)：</label> <input type="text" id="spouseAnnualIncome" name="spouseAnnualIncome" size="10" value="${jksq.spouseAnnualIncome }" class="number spouse" /> <span class="info">
											(数字格式,无千分位)</span></td>
									<td><label>服务年限(年)：</label> <input type="text" id="spouseWorkinglife" name="spouseWorkinglife" size="10" value="${jksq.spouseWorkinglife }" minlength="1" maxlength="3"
										class="digits spouse" /> <span class="info"> (数字格式)</span></td>
								</tr>
							</table>
						</div>
						<div class="panel">
							<h1>紧急联系人信息</h1>
						</div>
						<table class="table" width="98%">
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
							    <c:forEach items="${relatives }" var="relatives" varStatus="st">
									<tr>
										<td align="center">${relatives.ybrgx } <input type="hidden" name="id${st.count }" value="${relatives.id }"> <input type="hidden" name="ybrgx${st.count }"
											value="${relatives.ybrgx }"></td>
										<td align="left"><input type="text" name="name${st.count }" value="${relatives.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count }" value="${relatives.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count }" value="${relatives.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count }" value="${relatives.jjlxrlxdh }" class="digits"></td>
									</tr>
								</c:forEach>
								<c:forEach items="${friend }" var="friend" varStatus="st">
									<tr>
										<td align="center">${friend.ybrgx } <input type="hidden" name="id${st.count + 2 }" value="${friend.id }" /> <input type="hidden" name="ybrgx${st.count + 2 }" value="${friend.ybrgx }" />
										</td>
										<td align="left"><input type="text" name="name${st.count + 2}" value="${friend.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count  + 2}" value="${friend.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count + 2 }" value="${friend.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count + 2 }" value="${friend.jjlxrlxdh }" class="digits"></td>
									</tr>
								</c:forEach>
								<c:forEach items="${workmate }" var="workmate" varStatus="st">
									<tr>
										<td align="center">${workmate.ybrgx } <input type="hidden" name="id${st.count + 4 }" value="${workmate.id }"> <input type="hidden" name="ybrgx${st.count + 4 }"
											value="${workmate.ybrgx }" /></td>
										<td align="left"><input type="text" name="name${st.count + 4 }" value="${workmate.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count  + 4}" value="${workmate.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count + 4 }" value="${workmate.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count + 4 }" value="${workmate.jjlxrlxdh }" class="digits"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="panel">
							<h1>借款信息</h1>
						</div>
						<table border="0" bordercolor="red" width="98%">
							<tr>
								<td><label>借款申请额度(元):</label> <input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" class="required number" value="${jksq.jkLoanQuota }"> <span class="info">
										(必填,无千分位)</span></td>
								<td><label>申请借款期限(月):</label> <input type="text" id="jkCycle" name="jkCycle" size="10" class="required digits" value="${jksq.jkCycle }" /> <span class="info"> (必填,整数)</span></td>
								<td><label>还款方式：</label> <sen:select value="${jksq.hkWay}" clazz="required combox" name="hkWay" coding="loanReturnType" id="hkWay" /> <span class="info"> (必填)</span></td>
							</tr>
							<tr>
								<td colspan="1">
									<dl>
										<dt>借款用途：</dt>
										<dd>
											<sen:select value="${jksq.jkUse}" clazz="required combox" name="jkUse" coding="loanUseType" id="jkUse" />
										</dd>
									</dl></td>
								<td><label>申请日期：</label> <input type="text" id="jkLoanDate" name="jkLoanDate" class="date required" format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.jkLoanDate }" /> <a
									class="inputDateButton" href="javascript:;">选择</a> <span class="info"> (必填)</span></td>
								<td><label>共同还款人：</label> <input type="radio" id="togetherPersonYes" name="togetherPerson" value="是" <c:if test='${jksq.togetherPerson =="是"}'>checked="checked" </c:if> />有&nbsp; <input
									type="radio" id="togetherPersonNo" name="togetherPerson" value="否" <c:if test='${jksq.togetherPerson =="否"}'>checked="checked" </c:if> />无&nbsp;</td>
							</tr>
							<tr>
								<td><label>是否加急：</label> <input type="radio" id="englishName" name="englishName" value="否" <c:if test='${jksq.englishName == "否" }'>checked="checked" </c:if> />否&nbsp; <input
									type="radio" id="englishName" name="englishName" value="是" <c:if test='${jksq.englishName == "是" }'>checked="checked" </c:if> />是&nbsp;</td>
							</tr>
						</table>
						<div class="divider"></div>
						<table border="0" bordercolor="red" width="100%">
							<tr>
								<td colspan="3"><label>借款类型：</label> <input type="radio" class="jkType" name="jkType" <c:if test='${jksq.jkType =="A" }'>checked="checked" </c:if> value="A" />老板借&nbsp; <input
									type="radio" class="jkType" name="jkType" <c:if test='${jksq.jkType =="B" }'>checked="checked" </c:if> value="B" />老板楼易借&nbsp; <input type="radio" class="jkType" name="jkType"
									<c:if test='${jksq.jkType =="C" }'>checked="checked" </c:if> value="C" />薪水借&nbsp; <input type="radio" class="jkType" name="jkType"
									<c:if test='${jksq.jkType =="D" }'>checked="checked" </c:if> value="D" />薪水楼易借&nbsp; <input type="radio" class="jkType" name="jkType"
									<c:if test='${jksq.jkType =="E" }'>checked="checked" </c:if> value="E" />精英借&nbsp; <input type="radio" class="jkType" name="jkType"
									<c:if test='${jksq.jkType =="Q" }'>checked="checked" </c:if> value="Q" />企业借&nbsp; <input type="radio" class="jkType" name="jkType"
									<c:if test='${jksq.jkType =="W" }'>checked="checked" </c:if> value="W" />简易楼易借&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeA" name="lbd_div_edit" style="display: none;">${lbd }</div></td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeB" name="lblyd_div_edit" style="display: none;">${lblyd }</div></td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeC" name="xsd_div_edit" style="display: none;">${xsd }</div></td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeD" name="xslyd_div_edit" style="display: none;">${xslyd }</div></td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeE" name="jyd_div_edit" style="display: none;">${jyd }</div></td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeQ" name="qyd_div_edit" style="display: none;">${qyd }</div></td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="productTypeW" name="jylyd_div_edit" style="display: none;">${jylyd }</div></td>
							</tr>
							<tr>
								<td colspan="2"><label>开户行：</label> <input type="text" id="bankOpen" name="bankOpen" value="${jksq.bankOpen }" size="80" class="required" /></td>
							</tr>
							<tr>
								<td><label>账户号码：</label> <input type="text" id="bankNum" name="bankNum" value="${jksq.bankNum }" class="required digits" size="30" /><span class="info"> (必填)</span></td>
							</tr>
							<tr>
								<td><label>账户名称：</label> <input type="text" id="bankUsername" name="bankUsername" value="${jksq.bankUsername }" class="required" size="30" /> <span class="info"> (必填)</span></td>
							</tr>
							<tr>
								<td colspan="3">
									<dl class="nowrap">
										<dt>备注：</dt>
										<dd>
											<textarea id="backup09" name="backup09" rows="4" style="width: 93%;">${jksq.backup09 }</textarea>
										</dd>
									</dl></td>
							</tr>
							<tr>
								<td colspan="3">
									<div></div></td>
							</tr>
						</table>
						<!-- 显示结果 -->
						<c:if test='${null!= firstXhCreditAudit && null!= firstXhCreditAudit.creditRefuseReason}'>
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
									<textarea id="backup09" class="differenceStyle" disabled name="backup09" rows="4" style="width: 93%;" readonly>${firstXhCreditAudit.creditRefuseReason}</textarea>
								</dd>
							</dl>
						</c:if>
						<c:if test='${null!= secondXhCreditAudit && null!= secondXhCreditAudit.creditRefuseReason}'>
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
									<textarea id="backup09" class="differenceStyle" name="backup09" disabled rows="4" style="width: 93%;" readonly>${secondXhCreditAudit.creditRefuseReason}</textarea>
								</dd>
							</dl>
						</c:if>
						<c:if test='${null!= lastXhCreditAudit  && null!= lastXhCreditAudit.creditRefuseReason}'>
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
									<textarea id="backup09" class="differenceStyle" name="backup09" rows="4" disabled style="width: 93%;" readonly>${lastXhCreditAudit.creditRefuseReason}</textarea>
								</dd>
							</dl>
						</c:if>
						<div class="formBar">
							<ul>
								<c:if test="${fn:contains(jksq.state , '.')}">
									<li>
									    <input type = "hidden" id = "isBackCommit" value="1"/>
									    <input type = "hidden" id = "togtherCommit" value="0"/>
										<div class="buttonActive">
											<div class="buttonContent">
												<button type="submit" onclick="jksqEditSubmit('2')">重新提交</button>
											</div>
										</div>
									</li>
								</c:if>
								<c:if test="${!fn:contains(jksq.state , '.')}">
									<li><div class="buttonActive">
											<div class="buttonContent">
												<button type="submit" onclick="jksqEditSubmit('0')">暂存</button>
											</div>
										</div>
									</li>
									<li><div class="buttonActive">
											<div class="buttonContent">
												<button type="submit" onclick="jksqEditSubmit('1')">提交</button>
											</div>
										</div>
									</li>
								</c:if>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">取消</button>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</form>
			</div>
			<div id="togetherDiv">
				<form id="jksqTogetherEditForm" name="jksqTogetherEditForm" method="post" action="${ctx}/jksq/saveJksqTogetherInfo" class="pageForm required-validate"
					onsubmit="return jksqTogetherEditSubmit(this);">
					<div class="pageFormContent" layoutH="56">
						<input type="hidden" id="jksqId" name="jksqId" value="${jksq.id}" />
						<input type="hidden" id="jksqTogetherId" name="id" value="${xhJksqTogether.id}" />
					    <input type="hidden" id="opt" name="opt" />
						<h1>共同借款人填写</h1>
						<div class="divider"></div>
						<table nowrapTD="false">
							<tr>
								<td><label>共同借款人姓名:</label> <input type="text" id="togetherName" name="togetherName" size="30" value="${xhJksqTogether.togetherName }" class="required" /></td>
								<td><label>年龄:</label> <input type="text" id="age" name="age" size="30" value="${xhJksqTogether.age }" /></td>
								<td><label>性别：</label> <input type="radio" id="genders" name="genders" value="男" checked="checked" />男&nbsp; <input type="radio" id="genders" name="genders" value="女" />女</td>
							</tr>
							<tr>
								<td><label>身份证号码：</label> <input type="text" id="identification" name="identification" size="30" class="required" value="${xhJksqTogether.identification }" /></td>
								<td><label>户籍地址：</label> <input type="text" id="hjadress" name="hjadress" size="30" class="required" value="${xhJksqTogether.hjadress }" /></td>
								<td><label>家庭电话：</label> <input type="text" id="homePhone" name="homePhone" size="30" class="required" value="${xhJksqTogether.homePhone }" /></td>
							</tr>
							<tr>
								<td><label>手机：</label> <input type="text" id="telephone" name="telephone" size="30" class="required" value="${xhJksqTogether.telephone }" /></td>
								<td><label>现住址：</label> <input type="text" id="address" name="address" size="30" class="required" value="${xhJksqTogether.address }" /></td>
								<td><label>住址现电话：</label> <input type="text" id="addressPhone" name="addressPhone" size="30" class="required" value="${xhJksqTogether.addressPhone }" /></td>
							</tr>
							<tr>
								<td><label>邮箱：</label> <input type="text" id="email" name="email" size="30" class="required email" value="${xhJksqTogether.email }" /></td>
								<td><label>工作单位：</label> <input type="text" id="company" name="company" size="30" class="required" value="${xhJksqTogether.company }" /></td>
								<td><label>单位电话：</label> <input type="text" id="companyPhone" name="companyPhone" size="30" class="required" value="${xhJksqTogether.companyPhone }" /></td>
							</tr>
							<tr>
								<td><label>QQ号码：</label> <input type="text" id="qqhm" name="qqhm" size="30" class="required" value="${xhJksqTogether.qqhm }" /></td>
								<td><label>单位地址：</label> <input type="text" id="companyAdress" name="companyAdress" size="30" class="required" value="${xhJksqTogether.companyAdress }" /></td>
								<td><label>部门名称：</label> <input type="text" id="department" name="department" size="30" class="required" value="${xhJksqTogether.department }" /></td>
							</tr>
							<tr>
								<td><label>职务：</label> <input type="text" id="function" name="function" size="30" class="required" value="${xhJksqTogether.function }" /></td>
								<td><label>婚姻状况：</label> 
								<sen:select clazz="combox" name="maritalStatus" coding="marriageType" value="${xhJksqTogether.maritalStatus }"/>
								</td>
								<td><label>有无子女：</label>
								<sen:select clazz="combox" name="ywzn" coding="hasChildren" value="${xhJksqTogether.ywzn }"/>
							</tr>
							<tr>
								<td colspan="2"><label>居住状况：</label>
								 <input type="radio" id="togetherliveStateOne" name="liveState" value="01" <c:if test='${xhJksqTogether.liveState =="01" }'>checked="checked" </c:if>/>自购房屋&nbsp;&nbsp;
								 <input type="radio" id="togetherliveStateTwo" name="liveState" value="02"<c:if test='${xhJksqTogether.liveState =="02" }'>checked="checked" </c:if> />借款购置房屋&nbsp;&nbsp; 
								 <input type="radio" id="togetherliveStateThree" name="liveState" value="03" <c:if test='${xhJksqTogether.liveState =="03" }'>checked="checked" </c:if>/>借款购置房屋
								</td>
							</tr>
							<tr>
								<td colspan="1">
								     <input type="radio" id="togetherLiveStateFour" name="liveState" value="04" <c:if test='${xhJksqTogether.liveState =="04" }'>checked="checked" </c:if>/>租房，房租</td>
								<td><input type="text" id="liveMessage04" name="liveMessage" value="" />元/月</td>
							</tr>
							<tr>
								<td colspan="1">
								 <input type="radio" id="togetherLiveStateOne" name="liveState" value="05" <c:if test='${xhJksqTogether.liveState =="05" }'>checked="checked" </c:if>/>其他，说明：</td>
								<td><input type="text" id="liveMessage05" name="liveMessage" size="80" value=""/></td>
							</tr>
							<tr>
								<td colspan="2"><label>主要收入来源：</label> 
								</td>
							</tr>
							<tr>
								<td colspan="1">
									<input type="checkbox" id="moneyEarnOne" name="cBoxMonthlySalary" value="01"
									<c:if test='${cBoxMonthlySalaryEdit == true }'> checked="checked" </c:if>  />每月工资(含奖金及补助)
								</td>
								<td><input type="text" id="textmoneyEarnOne" name="monthlySalary" value="${xhJksqTogether.monthlySalary }" />元/月</td>
							</tr>
							<tr>
								<td colspan="1"> 
								   <input type="checkbox" id="moneyEarnTwo" name="cBoxRental" value="01" <c:if test='${cBoxRentalEdit == true }'> checked="checked" </c:if>
									/>房屋出租
								</td>
								<td><input type="text" id="textmoneyEarnTwo" name="rental" value="${xhJksqTogether.rental }" />元/月</td>
							</tr>
							<tr>
								<td colspan="1"> 
								  <input type="checkbox" id="moneyEarnThree" name="cBoxOtherIncome" value="01" <c:if test='${cBoxOtherIncomeEdit == true }'> checked="checked" </c:if>
									 />其他所得</td>
								<td><input type="text" id="textmoneyEarnThree" name="otherIncome" value="${xhJksqTogether.otherIncome }"/>元/年</td>
							</tr>
							<tr>
								<td colspan="1">
								<input type="checkbox" id="moneyEarnFour" name="cBoxAnnualIncome" value="01"
									<c:if test='${cBoxAnnualIncomeEdit == true }'> checked="checked" </c:if>/>年总收入</td>
								<td><input type="text" id="annualIncomeIndex" name="annualIncome" value="${xhJksqTogether.annualIncome }" />元</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
								    <input type="checkbox" id="cBoxSocialFundEdit" name="cBoxSocialFund" value="01" <c:if test='${cBoxSocialFundEdit == true }'> checked="checked" </c:if>
									 />是否拥有社保基金：</td>
								<td>
								   <input type="radio" id="socialFund1" name="socialFund" value="是" <c:if test='${xhJksqTogether.socialFund =="是" }'>checked="checked" </c:if> />是 
								   <input type="radio" id="socialFund2" name="socialFund" value="否" <c:if test='${xhJksqTogether.socialFund =="否" }'>checked="checked" </c:if> />否
								</td>
							</tr>
							
							<tr>
								<td colspan="1"><label>申请额度：</label> <input type="text" id="loanQuota" name="loanQuota" size="30" class="required" value="${jksq.jkLoanQuota }" readonly /></td>
								<td><label>申请还款期限：</label> <input type="text" id="jkCycle" name="jkCycle" size="30" class="required" value="${jksq.jkCycle }" readonly /></td>
								<td><label></label>
								</td>
							</tr>
							<tr>
								<td colspan="2"><label>还款资金来源：</label> <input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="01"
									<c:if test='${xhJksqTogether.sourceOfFunds =="01" }'>checked="checked" </c:if> onclick="sourceOfFundsEdit('01')" />独立还款&nbsp;&nbsp; <input type="radio" id="sourceOfFunds"
									name="sourceOfFunds" value="02" <c:if test='${xhJksqTogether.sourceOfFunds =="02" }'>checked="checked" </c:if> onclick="sourceOfFundsEdit('02')" />家人协助还款&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> <input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="03" <c:if test='${xhJksqTogether.sourceOfFunds =="03" }'>checked="checked" </c:if>
									onclick="sourceOfFundsEdit('03')" />其他方式</td>
								<td align="left"><input type="text" id="sourceOfFundsInfo" name="sourceOfFundsInfo" size="30" value="${xhJksqTogether.sourceOfFundsInfo }" /></td>
							</tr>
						</table>
						<br />
						<h1>联系人信息</h1>
						<div class="divider"></div>
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
									<td align="left"><input type="text" name="name1" value="${relativesTogether[0].name}"></td>
									<td align="left"><input type="text" name="jjlxrgzdw1"  value="${relativesTogether[0].jjlxrgzdw}"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz1"  value="${relativesTogether[0].jjlxrdwdzhjtzz}"></td>
									<td align="left"><input type="text" name="jjlxrlxdh1"  value="${relativesTogether[0].jjlxrlxdh}"></td>
								</tr>
								<tr>
									<td align="center">亲属<input type="hidden" name="ybrgx2" value="亲属"></td>
									<td align="left"><input type="text" name="name2" value="${relativesTogether[1].name}"></td>
									<td align="left"><input type="text" name="jjlxrgzdw2" value="${relativesTogether[1].jjlxrgzdw}"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz2" value="${relativesTogether[1].jjlxrdwdzhjtzz}"></td>
									<td align="left"><input type="text" name="jjlxrlxdh2" value="${relativesTogether[1].jjlxrlxdh}"></td>
								</tr>
								<tr>
									<td align="center">朋友<input type="hidden" name="ybrgx3" value="朋友"></td>
									<td align="left"><input type="text" name="name3" value="${friendTogether[0].name}"></td>
									<td align="left"><input type="text" name="jjlxrgzdw3" value="${friendTogether[0].jjlxrgzdw}"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz3" value="${friendTogether[0].jjlxrdwdzhjtzz}"></td>
									<td align="left"><input type="text" name="jjlxrlxdh3" value="${friendTogether[0].jjlxrlxdh}"></td>
								</tr>
								<tr>
									<td align="center">朋友<input type="hidden" name="ybrgx4" value="朋友"></td>
									<td align="left"><input type="text" name="name4" value="${friendTogether[1].name}"></td>
									<td align="left"><input type="text" name="jjlxrgzdw4" value="${friendTogether[1].jjlxrgzdw}"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz4" value="${friendTogether[1].jjlxrdwdzhjtzz}"></td>
									<td align="left"><input type="text" name="jjlxrlxdh4" value="${friendTogether[1].jjlxrlxdh}"></td>
								</tr>
								<tr>
									<td align="center">同事<input type="hidden" name="ybrgx5" value="同事"></td>
									<td align="left"><input type="text" name="name5" value="${workmateTogether[0].name}"></td>
									<td align="left"><input type="text" name="jjlxrgzdw5" value="${workmateTogether[0].jjlxrgzdw}"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz5" value="${workmateTogether[0].jjlxrdwdzhjtzz}"></td>
									<td align="left"><input type="text" name="jjlxrlxdh5" value="${workmateTogether[0].jjlxrlxdh}"></td>
								</tr>
								<tr>
									<td align="center">同事<input type="hidden" name="ybrgx6" value="同事"></td>
									<td align="left"><input type="text" name="name6" value="${workmateTogether[1].name}"></td>
									<td align="left"><input type="text" name="jjlxrgzdw6" value="${workmateTogether[1].jjlxrgzdw}"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz6" value="${workmateTogether[1].jjlxrdwdzhjtzz}"></td>
									<td align="left"><input type="text" name="jjlxrlxdh6" value="${workmateTogether[1].jjlxrlxdh}"></td>
								</tr>
							</tbody>
						</table>
						<div class="formBar">
							<ul>
							   <li>
							            <div class="buttonActive">
											<div class="buttonContent">
												<button type="submit">保存共借人</button>
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
</div>
<script type="text/javascript">
    
    function isBackSubmit(){
    	
    	var isBackCommit = $("#isBackCommit").val();
		var id = $("#jksqTogetherId").val();
		
    }
    
    //调用jksqEditMsgFormSubmit前调用
	function jksqEditSubmit(val) {
		//设计是否是暂存，还是提交还是重新提交
		var $box = navTab.getCurrentPanel();
		$("input#opt",$box).val(val);
		return true;
	}

	//借款申请修改提交
	function jksqEditMsgFormSubmit(obj) {
		//提交表单
	
		var $form = $(obj);
		if (!$form.valid()) {
			return false;
		}
        var $box = navTab.getCurrentPanel();
        var productType = $("input[name='jkType']:checked",$box).val();
		if (!productType) {
			alertMsg.warn('请选择借款类型，完善信息后重新提交！');
			return false;
		} 
		var isBackCommit = $("#isBackCommit").val();
		var id = $("#jksqTogetherId").val();
		if(isBackCommit == '1'){
			//退回状态提交 如果选中共同借款人必须首先添加了共同借款人
			var togetherSelected = $("input[name='togetherPerson']:checked").val();
			if(togetherSelected == "是"){
				//debugger;
				//无共借人
				if(id == ""){
				    if($("#togtherCommit").val() != "1"){
						alertMsg.error("请首先保存共借人信息再重新提交");
						return false;
					}
				}
			}
		}
		return validateCallback(obj, navTabAjaxDone);
	}

    function submitMain(json){
    	DWZ.ajaxDone(json);
        if (json.statusCode == DWZ.statusCode.ok){
        	$("#togtherCommit").val("1");
	     	alertMsg.correct("保存共借人成功，请务必点击提交按钮保存申请信息");
    	}else{
    	   alertMsg.error("保存共借人失败，请联系管理员");
    	} 	
    }
    
    
	//借款申请共同还款人修改提交
	function jksqTogetherEditSubmit(obj) {
		var $form = $(obj);
		if (!$form.valid()) {
			return false;
		}
		$("#togtherCommit").val("1");
		return validateCallback(obj, submitMain);
	}


	function socialFundChange(){
		var $box = navTab.getCurrentPanel();
		var $togetherDiv = $('div#togetherDiv',$box);
		var $socialFundCheck = $("#cBoxSocialFundEdit",$togetherDiv);
		if($socialFundCheck.attr('checked')){
			$("input[id^='socialFund']").attr('disabled',false);
		}else{
			$("input[id^='socialFund']").attr('disabled',true);
		}
		$socialFundCheck.click(function(){
			var $this = $(this);	
			if($this.attr('checked')){
				$("input[id^='socialFund']").attr('disabled',false);
			}else{
				$("input[id^='socialFund']").attr('disabled',true);
			}
		});
		//var 
	}

	function spouseExchange(){
		var $box = navTab.getCurrentPanel();
		var $mainDiv = $('div#mainDiv',$box);
		var $spouse = $(".spouse",$mainDiv);
		var $maritalStatus = $('#maritalStatus',$mainDiv);
		var $isMarrayEdit = $('#isMarrayEdit',$mainDiv);
		$spouse.attr('disabled',$maritalStatus.val() != '已婚');
		if( $maritalStatus.val() != '已婚'){
		    $spouse.removeClass('error');
			$isMarrayEdit.hide();
		}else{
			$isMarrayEdit.show();
		}
	}
	
	
	//居住状态
	function houseHandle(){
	    var $box = navTab.getCurrentPanel();
	    var $mainDiv = $("div#mainDiv",$box);
	    var selected = $("input[name='liveState']:checked",$mainDiv).val();
	    $("input[id^='liveMessage']",$mainDiv).attr('disabled',true);
	    if(selected){
	    	$("input#liveMessage"+selected,$mainDiv).attr('disabled',false).val('${jksq.liveMessage }');
	    }
	    
	    $("input[name='liveState']",$mainDiv).click(function(){
	    	var $this = $(this);
	    	var $houseInput = $("input[id^='liveMessage']",$mainDiv).attr('disabled',true).val("");
	    	$("input#liveMessage"+$this.val(),$mainDiv).attr('disabled',false);
	    });
	}
	
	//共借人居住状态
	function togehterHouseHandle(){
	    var $box = navTab.getCurrentPanel();
	    var $togetherDiv = $('div#togetherDiv',$box);
	    var selected = $("input[name='liveState']:checked",$togetherDiv).val();
	    $("input[id^='liveMessage']",$togetherDiv).attr('disabled',true);
	    if(selected){
	    	$("input#liveMessage"+selected,$togetherDiv).attr('disabled',false).val('${xhJksqTogether.liveMessage }');
	    }
	    
	    $("input[name='liveState']",$togetherDiv).click(function(){
	    	var $this = $(this);
	    	var $houseInput = $("input[id^='liveMessage']",$togetherDiv).attr('disabled',true).val("");
	    	$("input#liveMessage"+$this.val(),$togetherDiv).attr('disabled',false);
	    });
	}
	
	//借款类型处理 老板借等
	function typeHandle(){
		var $box = navTab.getCurrentPanel();
		var $mainDiv = $('div#mainDiv',$box);
		$("div[id^='productType']",$mainDiv).hide();
		var selected = $("input[name='jkType']:checked",$mainDiv).val();
		if(selected){
			var $divProduct = $("div#productType" + selected,$mainDiv).show();	
		    $('input',$divProduct).addClass("required");
		}
		$(".jkType",$mainDiv).click(function(){
			//隐藏所有产品相关div,同时去掉required
			var $this = $(this);
			var $allProduct = $("div[id^='productType']",$mainDiv).hide();
			$('input',$allProduct).removeClass("required");
			
			//显示选择产品对应div 
			var $divProduct = $("div#productType" + $this.val(),$mainDiv).show();	
		    $('input',$divProduct).addClass("required");	
		});
	}
	
	//证件类型处理
	function cardChange(){
	   var $box = navTab.getCurrentPanel();
	   var $mainDiv = $('div#mainDiv',$box);
	   var cardType =  $("#pocertificates",$mainDiv).val();
	   if(cardType == '身份证'){
			$("#zjhm",$mainDiv).addClass("isIdCardNo");
	   }
	   $("#pocertificates",$mainDiv).change(function(){
		    if($(this).val() == '身份证'){
				$("#zjhm",$mainDiv).addClass("isIdCardNo");				
			}else{
				$("#zjhm",$mainDiv).removeClass("isIdCardNo");
			}
	   });
	   
	}
	
	
	//收入来源
	function moneySalary(){
		var $box = navTab.getCurrentPanel();
		var $togetherDiv = $('div#togetherDiv',$box);
		$("input[id^='moneyEarn']",$togetherDiv).each(function(){
			var $this = $(this);
			if($this.attr("checked")){
				var $nextInput = $(this).parent('td').next('td').find('input').attr("disabled",false);	
			}else{
				var $nextInput = $(this).parent('td').next('td').find('input').attr("disabled",true);	
			};
		}).click(function(){
			var $this = $(this);
			if($this.attr("checked")){
				var $nextInput = $(this).parent('td').next('td').find('input').attr("disabled",false);	
			}else{
				var $nextInput = $(this).parent('td').next('td').find('input').attr("disabled",true);	
			};
		});
	}
	
	$(function(){
	   
	   var $box = navTab.getCurrentPanel();
	   var $mainDiv = $("div#mainDiv",$box);
	   //身份证类型处理
	   cardChange();
	   
	   spouseExchange();
	   
	   //共借人社保基金处理
	   socialFundChange();
	   //收入来源
	   moneySalary();
	   //共借人房屋
	   togehterHouseHandle();
	   $("#maritalStatus",$mainDiv).change(function(){
	   		spouseExchange();
	   });
	   //借款类型处理 老板借等
	   typeHandle();
		
	   //住房处理
	   houseHandle();
	   //不包含共同借款人，则隐藏工借人Tab
	   <c:if test='${jksq.togetherPerson != "是"}'>
	   		$('#togetherTab').hide();
	   </c:if>
	   
	   $("#togetherPersonYes").click(function(){
	        var isBackCommit = $("#isBackCommit").val();
	   		//退回状态 才显示共借人编辑
	   		if(isBackCommit == '1'){
	   			$('#togetherTab').show();
	   		}
	   });
	   $("#togetherPersonNo").click(function(){
	   		$('#togetherTab').hide();
	   });
	   
	   
	});
</script>