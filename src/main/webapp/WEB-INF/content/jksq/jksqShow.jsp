<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>借款申请信息</span></a></li>
					<c:if test='${jksq.state != "0" && jksq.togetherPerson == "是"}'>
					<li><a href="javascript:;"><span>共同还款人信息</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent" >
			<div>
				<form id="jksqShowMsgForm" name="jksqShowMsgForm" method="post"
					action="${ctx}/jksq/editSaveJksqMsg" class="pageForm required-validate"
					onsubmit="return jksqShowMsgFormSubmit(this);">
					<div class="pageFormContent" layoutH="56">
						<input type="hidden" id="xydkzxId" name="xydkzxId" value="${xydkzx.id }" />
						<input type="hidden" id="opt" name="opt" /> 
						<div class="panel"><h1>客户基本信息</h1></div>
						<table nowrapTD="false">
							<tr>
								<td><label>借款人姓名:</label> 
									<input type="text" id="jkrxm" name="jkrxm" size="30" value="${jksq.jkrxm }" 
										class="required" readonly="readonly">
									<input type="hidden" id="id" name="id" size="30" value="${jksq.id }" />
								</td>
								<td><label>性别：</label> 
									<input type="radio" id="genders" name="genders" value="男"  
										<c:if test='${jksq.genders == "男"}'>checked="checked" </c:if>
									/>男&nbsp; 
									<input type="radio" id="genders" name="genders" value="女"  
										<c:if test='${jksq.genders == "女"}'>checked="checked" </c:if>
									/>女
								</td>
								<td><label>出生日期：</label> 
									<input type="text" name="birthday" 	class="date" 
										format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.birthday }" />
										<a class="inputDateButton" href="javascript:;">选择</a>
								</td>
							</tr>
							<tr>
								<td><label>证件类型：</label> 
									<select id="pocertificates" name="pocertificates" class="combox required">
										<c:forEach items="${zjlx0005}" var="per">
											<option value="${per.value }" 
												<c:if test="${per.value==jksq.pocertificates}">selected="selected" </c:if> 
											>${per.name}</option>
										</c:forEach>
									</select>
								</td>
								<td><label>证件号码：</label> 
									<input type="text" id="zjhm" name="zjhm" size="30" class="required" value="${jksq.zjhm }" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>户籍地址：</label> 
									<input type="text" id="hjadress" name="hjadress" size="60" class="required" value="${jksq.hjadress }" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>现住址:</label> 
									<input type="text" id="homeAddress" name="homeAddress" size="60" class="required" value="${jksq.homeAddress }"/>
								</td>
							</tr>
						</table>
							<div class="divider"></div>
						<table nowrapTD="false" width="100%">
							<tr>
								<td><label>家庭电话：</label> 
									<input type="text" id="homePhone" name="homePhone" size="30" class="" value="${jksq.homePhone }" />
								</td>
								<td><label>工作单位：</label> 
									<input type="text" id="company" name="company" size="30" class="" value="${jksq.company }" />
								</td>
							</tr>
							<tr>
								<td><label>单位电话：</label> 
									<input type="text" id="companyPhone" name="companyPhone" size="30" class="" value="${jksq.companyPhone }" />
								</td>
								<td><label>单位地址：</label> 
									<input type="text" id="companyAdress" name="companyAdress" size="30" class="" value="${jksq.companyAdress }" />
								</td>
								<td><label>单位性质：</label> 
									<select id="companyNature" name="companyNature" class="combox ">
										<c:forEach items="${dwxz0006}" var="per">
											<option value="${per.value }" 
												<c:if test="${per.value==jksq.companyNature}">selected="selected" </c:if> 
											>${per.name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label>移动电话：</label> 
									<input type="text" id="telephone" name="telephone" size="30" class="required" value="${jksq.telephone }" />
								</td>
								<td><label>电子邮箱：</label> 
									<input type="text" id="email" name="email" size="30" class="email" value="${jksq.email }" />
								</td>
								<td><label>婚姻状况：</label> 
									<select id="maritalStatus" name="maritalStatus" class="combox ">
										<c:forEach items="${hyzk0009}" var="per">
											<option value="${per.value }" 
												<c:if test='${jksq.maritalStatus ==per.value }'>selected="selected" </c:if>
											>${per.name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label>有无子女：</label> 
									<select id="ywzn" name="ywzn" class="combox ">
										<c:forEach items="${ywzn0018}" var="per">
											<option value="${per.value }"  
												<c:if test='${jksq.ywzn ==per.value }'>selected="selected" </c:if>
											>${per.name }</option>
										</c:forEach>
									</select>
								</td>
								<td><label>QQ号码：</label>
									<input type="text" id="qqhm" name="qqhm" size="30" class="" value="${jksq.qqhm }" />
								</td>
								<td><label>月收入(元)：</label> 
									<input type="text" id="annualIncome" name="annualIncome" class=""
										size="10" value="${jksq.annualIncome }" /><span class="info"> (数字格式,无千分位)</span>
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>收入说明：</label> 
									<input type="text" id="incomeIllustration" name="incomeIllustration" size="60" class="" value="${jksq.incomeIllustration }" />
								</td>
							</tr>
						</table>
							<div class="divider"></div>
						<table >
							<tr>
								<td colspan="1"><label>居住状态：</label> 
									<input type="radio" id="liveState" name="liveState" value="01" 
										<c:if test='${jksq.liveState == "01" }'> checked="checked" </c:if>
										 onclick="livestateShow('liveMessage01')" />自有房屋，有无借款，月供
								</td>
								<td>
									<input id="liveMessage01" name="liveMessage" type="text" value="" />元
								</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
									<input type="radio" id="liveState" name="liveState" value="02" 
										<c:if test='${jksq.liveState == "02" }'> checked="checked" </c:if>
										onclick="livestateShow('liveMessage02')" />亲属产权
								</td>
								<td><input type="hidden" id="liveMessage02" name="liveMessage" 	value="" /></td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
									<input type="radio" id="liveState" name="liveState" value="03"
										<c:if test='${jksq.liveState == "03" }'> checked="checked" </c:if>
										onclick="livestateShow('liveMessage03')" />租房，房租</td>
								<td>
									<input type="text" id="liveMessage03" name="liveMessage" value="" />元/月
								</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
									<input type="radio" id="liveState" name="liveState" value="04"
										<c:if test='${jksq.liveState == "04" }'> checked="checked" </c:if>
										onclick="livestateShow('liveMessage04')" />其他，说明：
									</td>
								<td>
									<input type="text" id="liveMessage04" name="liveMessage" size="40" value="" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<dl class="nowrap">
										<dt>通讯地址：</dt>
										<dd>
											<select name="province" ref="loanshowbox_city" class="required combox" 
												refUrl="${ctx}/cjrxx/getCity?code={value}" >
												<option value="">所有省市*</option>
												<c:forEach items="${province}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.province.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<select id="loanshowbox_city" name="city" class="required combox" ref="loanshowbox_area"
												 refUrl="${ctx}/cjrxx/getArea?code={value}" >
												<option value="">所有城市*</option>
												<c:forEach items="${city}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.city.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<select  id="loanshowbox_area" name="area" class="required combox" >
												<option value="">所有区县*</option>
												<c:forEach items="${area}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.area.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td colspan="3"><label></label>
									<input type="text" name="txdz" size="80" value="${jksq.txdz }" maxlength="100" />
								</td>
							</tr>
						</table>
						<br />
						<div id="isMarrayShow">
							<div class="panel"><h1>以下为配偶信息（借款人婚姻状况为已婚时必填项）</h1></div>
							<table width="100%">
								<tr>
									<td><label>姓名:</label> 
										<input type="text" id="spouseName" name="spouseName" size="30" value="${jksq.spouseName }" />
									</td>
									<td><label>性别：</label> 
										<input type="radio" id="spouseGenders" name="spouseGenders" value="男" 
											<c:if test='${jksq.spouseGenders =="男" }'>checked="checked" </c:if>
										 />男&nbsp; 
										<input type="radio" id="spouseGenders" name="spouseGenders" value="女" 
											<c:if test='${jksq.spouseGenders =="女" }'>checked="checked" </c:if>
										 />女
									</td>
									<td><label>出生日期：</label> 
										<input type="text" name="spouseBirthday" class="date" 
											format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.spouseBirthday }" />
											<a class="inputDateButton" href="javascript:;" id="spouRiqiShow" name="spouRiqiShow">选择</a>
									</td>
								</tr>
								<tr>
									<td><label>现住址：</label> 
										<input type="text" id="spouseAdress" name="spouseAdress" size="30" value="${jksq.spouseAdress }" />
									</td>
									<td><label>证件类型：</label> 
										<select id="spousePocertificates" name="spousePocertificates">
											<c:forEach items="${zjlx0005}" var="per">
												<option value="${per.value }" 
													<c:if test='${jksq.spousePocertificates ==per.value }'>selected="selected" </c:if>
												>${per.name }</option>
											</c:forEach>
										</select>
									</td>
									<td><label>证件号码：</label> 
										<input type="text" id="spouseZjhm" name="spouseZjhm" size="30" value="${jksq.spouseZjhm }" />
									</td>
								</tr>
								<tr>
									<td><label>移动电话：</label> 
										<input type="text" id="spouseTelephone" name="spouseTelephone" size="30" value="${jksq.spouseTelephone }" />
									</td>
									<td><label>家庭电话：</label> 
										<input type="text" id="spouseHomePhone" name="spouseHomePhone" size="30" value="${jksq.spouseHomePhone }" />
									</td>
									<td><label>工作单位：</label> 
										<input type="text" id="spouseCompany" name="spouseCompany" size="30" value="${jksq.spouseCompany }" />
									</td>
								</tr>
								<tr>
									<td><label>部门与职务：</label> 
										<input type="text" id="spouseDepFunction" name="spouseDepFunction" size="30" value="${jksq.spouseDepFunction }" />
									</td>
									<td><label>单位电话：</label> 
										<input type="text" id="spouseCompanyPhone" name="spouseCompanyPhone" size="30" value="${jksq.spouseCompanyPhone }" />
									</td>
									<td><label>单位地址：</label> 
										<input type="text" id="spouseCompanyAdress" name="spouseCompanyAdress" size="30" value="${jksq.spouseCompanyAdress }" />
									</td>
								</tr>
			
								<tr>
									<td><label>月收入(元)：</label> 
										<input type="text" id="spouseAnnualIncome" name="spouseAnnualIncome" size="10" 
											value="${jksq.spouseAnnualIncome }" class="number"/><span class="info"> (数字格式,无千分位)</span>
									</td>
									<td><label>服务年限(年)：</label> 
										<input type="text" id="spouseWorkinglife" name="spouseWorkinglife" size="10" 
											value="${jksq.spouseWorkinglife }" minlength="1" maxlength="3" 
											class="digits"/><span class="info"> (数字格式)</span>
									</td>
								</tr>
							</table>
						<div class="divider"></div>
						</div>
						<div class="panel"><h1>紧急联系人信息</h1></div>
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
									<td align="center">${relatives.ybrgx }
										<input type="hidden" name="id${st.count }" value="${relatives.id }">
										<input type="hidden" name="ybrgx${st.count }" value="${relatives.ybrgx }">
									</td>
									<td align="left"><input type="text" name="name${st.count }" value="${relatives.name }"></td>
									<td align="left"><input type="text" name="jjlxrgzdw${st.count }" value="${relatives.jjlxrgzdw }"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count }" value="${relatives.jjlxrdwdzhjtzz }"></td>
									<td align="left"><input type="text" name="jjlxrlxdh${st.count }" value="${relatives.jjlxrlxdh }" class="digits"></td>
								</tr>
								</c:forEach>
								<c:forEach items="${friend }" var="friend" varStatus="st">
								<tr>
									<td align="center">${friend.ybrgx }
										<input type="hidden" name="id${st.count + 2 }" value="${friend.id }" />
										<input type="hidden" name="ybrgx${st.count + 2 }" value="${friend.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name${st.count + 2}" value="${friend.name }"></td>
									<td align="left"><input type="text" name="jjlxrgzdw${st.count  + 2}" value="${friend.jjlxrgzdw }"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count + 2 }" value="${friend.jjlxrdwdzhjtzz }"></td>
									<td align="left"><input type="text" name="jjlxrlxdh${st.count + 2 }" value="${friend.jjlxrlxdh }" class="digits"></td>
								</tr>
								</c:forEach>
								<c:forEach items="${workmate }" var="workmate" varStatus="st">
								<tr>
									<td align="center">${workmate.ybrgx }
										<input type="hidden" name="id${st.count + 4 }" value="${workmate.id }">
										<input type="hidden" name="ybrgx${st.count + 4 }" value="${workmate.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name${st.count + 4 }" value="${workmate.name }"></td>
									<td align="left"><input type="text" name="jjlxrgzdw${st.count  + 4}" value="${workmate.jjlxrgzdw }"></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count + 4 }" value="${workmate.jjlxrdwdzhjtzz }"></td>
									<td align="left"><input type="text" name="jjlxrlxdh${st.count + 4 }" value="${workmate.jjlxrlxdh }" class="digits"></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div class="panel"><h1>借款信息</h1></div>
						
						<table border="0" bordercolor="red" width="100%">
							<tr>
								<td><label>借款申请额度(元):</label> 
									<input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" 
										class="required" value="${jksq.jkLoanQuota }"><span class="info"> (必填,无千分位)</span>
								</td>
								<td><label>申请借款期限(月):</label> 
									<input type="text" id="jkCycle" name="jkCycle" size="10" class="required digits"
										value="${jksq.jkCycle }" /><span class="info"> (必填,整数)</span>
								</td>
								<td><label>还款方式*：</label> 
									<select id="hkWay" name="hkWay" class="required combox">
										<c:forEach items="${hkWay0019}" var="per">
											<option value="${per.value }" 
												<c:if test='${jksq.hkWay ==per.value }'>selected="selected" </c:if>
											>${per.name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="1">
									<dl >
										<dt>借款用途：</dt>
										<dd>
											<select id="jkUse" name="jkUse" class="required combox">
												<c:forEach items="${jkUse0027}" var="per">
													<option value="${per.value }" 
														<c:if test='${jksq.jkUse ==per.value }'>selected="selected" </c:if>
													>${per.name }</option>
												</c:forEach>
											</select>
										</dd>
									</dl>
								</td>
								<td><label>申请日期：</label> 
									<input type="text" id="jkLoanDate" name="jkLoanDate" class="date required" 
										format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.jkLoanDate }" />
										<a class="inputDateButton" href="javascript:;">选择</a><span class="info"> (必填)</span>
								</td>
								<td><label>共同还款人：</label> 
									<input type="radio" id="togetherPerson" name="togetherPerson" 	value="是" 
										<c:if test='${jksq.togetherPerson == "是" }'>checked="checked" </c:if>
									 />是&nbsp;
									<input type="radio" id="togetherPerson" name="togetherPerson" 	value="否" 
										<c:if test='${jksq.togetherPerson == "否" }'>checked="checked" </c:if>
									/>否&nbsp;
								</td>
							</tr>
							<tr>
								<td><label>是否加急：</label>
									<input type="radio" id="englishName" name="englishName" value="否" 
										<c:if test='${jksq.englishName == "否" }'>checked="checked" </c:if>
									/>否&nbsp;
									<input type="radio" id="englishName" name="englishName" value="是" 
										<c:if test='${jksq.englishName == "是" }'>checked="checked" </c:if>
									/>是&nbsp;
								</td>
							</tr>
							</table>
						<div class="divider"></div>
						<table border="0" bordercolor="red" width="100%">
							<tr>
								<td colspan="3"><label>借款类型：</label> 
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="A" }'>checked="checked" </c:if>
										value="A" onclick="jkUsestate(this)"  />老板借&nbsp;
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="B" }'>checked="checked" </c:if>
										value="B" onclick="jkUsestate(this)" />老板楼易借&nbsp; 
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="C" }'>checked="checked" </c:if>
										value="C" onclick="jkUsestate(this)" />薪水借&nbsp;
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="D" }'>checked="checked" </c:if>
										value="D" onclick="jkUsestate(this)" />薪水楼易借&nbsp; 
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="E" }'>checked="checked" </c:if>
										value="E" onclick="jkUsestate(this)" />精英借&nbsp;
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="Q" }'>checked="checked" </c:if>
										value="Q" onclick="jkUsestate(this)" />企业借&nbsp;
									<input type="radio" id="jkType" name="jkType" 
										<c:if test='${jksq.jkType =="W" }'>checked="checked" </c:if>
										value="W" onclick="jkUsestate(this)" />简易楼易借&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="lbd_div_show" name="lbd_div_show" style="display: none;">
										${lbd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="lblyd_div_show" name="lblyd_div_show" style="display: none;">
										${lblyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="xsd_div_show" name="xsd_div_show" style="display: none;">
										${xsd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="xslyd_div_show" name="xslyd_div_show" style="display: none;">
										${xslyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="jyd_div_show" name="jyd_div_show" style="display: none;">
										${jyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="qyd_div_show" name="qyd_div_show"
										style="display: none;">${qyd }</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="jylyd_div_show" name="jylyd_div_show"
										style="display: none;">${jylyd }</div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><label>开户行：</label> 
									<input type="text" id="bankOpen" name="bankOpen" value="${jksq.bankOpen }" size="80"/>
									<!-- 
									<select id="bankOpen" name="bankOpen" >
										<c:forEach items="${bankOpen0020}" var="per">
											<option value="${per.value }" 
												<c:if test='${jksq.bankOpen ==per.value }'>selected="selected" </c:if>
											>${per.name }</option>
										</c:forEach>
									</select>
									 -->
								</td>
								<td><label>借款编号：</label> 
									<input type="text" id="loanCode" name="loanCode" class="required digits" value="${jksq.loanCode }" />
								</td>
							</tr>
							<tr>
								<td><label>账户号码：</label> 
									<input type="text" id="bankNum" name="bankNum" class="required digits" size="30"
										value="${jksq.bankNum }" /><span class="info"> (必填)</span>
								</td>
							</tr>
							<tr>
								<td><label>账户名称：</label> 
									<input type="text" id="bankUsername" name="bankUsername" class="required" size="30" 
										value="${jksq.bankUsername }"/><span class="info"> (必填)</span>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<dl class="nowrap">
										<dt>备注：</dt>
										<dd>
											<textarea id="backup09" name="backup09" rows="4" style="width: 93%;">${jksq.backup09 }</textarea>
										</dd>
									</dl>
								</td>
							</tr>
								</table>
							
							<c:if test='${null!= firstXhCreditAudit && null!= firstXhCreditAudit.creditRefuseReason}'>	
							<div class="divider"></div>
							
							<p><label>初审审核结果：</label>
										<c:if test='${firstXhCreditAudit.creditResult eq 1}'><label><input type="radio"/>初审通过</label></c:if>
										<c:if test='${firstXhCreditAudit.creditResult eq 3}'><label><input type="radio" checked/>初审退回</label></c:if>
										<c:if test='${firstXhCreditAudit.creditResult eq 4}'><label><input type="radio" checked/>初审通过</label></c:if>
										<c:if test='${firstXhCreditAudit.creditResult eq 5}'><label><input type="radio" checked/>客户放弃</label></c:if></p>
							<dl class="nowrap">
										<dt>初审审核意见</dt>
										<dd>
											<textarea id="backup09" name="backup09" rows="4" style="width: 93%;" readonly>${firstXhCreditAudit.creditRefuseReason}</textarea>
										</dd>
									</dl>
									</c:if>
									<c:if test='${null!= secondXhCreditAudit && null!= secondXhCreditAudit.creditRefuseReason}'>	
									<div class="divider"></div>
														<p><label>复审审核结果：</label>
										<c:if test='${secondXhCreditAudit.creditResult eq 1}'><label><input type="radio"/>复审通过</label></c:if>
										<c:if test='${secondXhCreditAudit.creditResult eq 3}'><label><input type="radio" checked/>复审退回</label></c:if>
										<c:if test='${secondXhCreditAudit.creditResult eq 4}'><label><input type="radio" checked/>复审通过</label></c:if>
										<c:if test='${secondXhCreditAudit.creditResult eq 5}'><label><input type="radio" checked/>客户放弃</label></c:if></p>
							<dl class="nowrap">
										<dt>复审审核意见</dt>
										<dd>
											<textarea id="backup09" name="backup09" rows="4" style="width: 93%;" readonly>${secondXhCreditAudit.creditRefuseReason}</textarea>
										</dd>
									</dl>
									</c:if>
							<c:if test='${null!= lastXhCreditAudit  && null!= lastXhCreditAudit.creditRefuseReason}'>		
									<div class="divider"></div>
																<p><label>终审审核结果：</label>
										<c:if test='${lastXhCreditAudit.creditResult eq 1}'><label><input type="radio"/>终审通过</label></c:if>
										<c:if test='${lastXhCreditAudit.creditResult eq 3}'><label><input type="radio" checked/>终审退回</label></c:if>
										<c:if test='${lastXhCreditAudit.creditResult eq 4}'><label><input type="radio" checked/>终审通过</label></c:if>
										<c:if test='${lastXhCreditAudit.creditResult eq 5}'><label><input type="radio" checked/>客户放弃</label></c:if></p>
							<dl class="nowrap">
										<dt>终审审核意见</dt>
										<dd>
											<textarea id="backup09" name="backup09" rows="4" style="width: 93%;" readonly>${lastXhCreditAudit.creditRefuseReason}</textarea>
										</dd>
									</dl>
								
								</c:if>
								
						
						<div class="formBar">
							<ul>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
			<div>
				<form id="jksqTogetherShowForm" name="jksqTogetherShowForm" method="post"
						action="${ctx}/jksq/saveJksqTogetherInfo" class="pageForm required-validate"
						onsubmit="return jksqTogetherShowSubmit(this);">
						<div class="pageFormContent" layoutH="56">
							<input type="hidden" id="jksqId" name="jksqId" value="${xhjksq.id }"/>
							<input type="hidden" id="opt" name="opt" />
							<div class="panel"><h1>客户基本信息</h1></div>
							<table nowrapTD="false">
								<tr>
									<td><label>共同借款人姓名:</label> 
										<input type="text" id="togetherName" name="togetherName" size="30" value="${xhJksqTogether.togetherName }" class="required" />
									</td>
									<td><label>年龄:</label> 
										<input type="text" id="age" name="age" size="30" value="${xhJksqTogether.age }" />
									</td>
									<td><label>性别：</label> 
										<input type="radio" id="genders" name="genders" value="男" 
											<c:if test='${xhJksqTogether.genders == "男"}'>checked="checked" </c:if>
										 />男&nbsp; 
										<input type="radio" id="genders" name="genders" value="女" 
											<c:if test='${xhJksqTogether.genders == "女"}'>checked="checked" </c:if>
										 />女
									</td>
								</tr>
								<tr>
									<td><label>身份证号码：</label> 
										<input type="text" id="identification" name="identification" size="30" class="required" value="${xhJksqTogether.identification }" />
									</td>
									<td><label>户籍地址：</label> 
										<input type="text" id="hjadress" name="hjadress" size="30" class="required" value="${xhJksqTogether.hjadress }" />
									</td>
									<td><label>家庭电话：</label> 
										<input type="text" id="homePhone" name="homePhone" size="30" class="required" value="${xhJksqTogether.homePhone }" />
									</td>
								</tr>
								<tr>
									<td><label>手机：</label> 
										<input type="text" id="telephone" name="telephone" size="30" class="required" value="${xhJksqTogether.telephone }" />
									</td>
									<td><label>现住址：</label> 
										<input type="text" id="address" name="address" size="30" class="required" value="${xhJksqTogether.address }" />
									</td>
									<td><label>住址现电话：</label> 
										<input type="text" id="addressPhone" name="addressPhone" size="30" class="required" value="${xhJksqTogether.addressPhone }" />
									</td>
								</tr>
								<tr>
									<td><label>邮箱：</label> 
										<input type="text" id="email" name="email" size="30" class="required email" value="${xhJksqTogether.email }" />
									</td>
									<td><label>工作单位：</label> 
										<input type="text" id="company" name="company" size="30" class="required" value="${xhJksqTogether.company }" />
									</td>
									<td><label>单位电话：</label> 
										<input type="text" id="companyPhone" name="companyPhone" size="30" class="required" value="${xhJksqTogether.companyPhone }" />
									</td>
								</tr>
								<tr>
									<td><label>QQ号码：</label> 
										<input type="text" id="qqhm" name="qqhm" size="30" class="required" value="${xhJksqTogether.qqhm }" />
									</td>
									<td><label>单位地址：</label> 
										<input type="text" id="companyAdress" name="companyAdress" size="30" class="required" value="${xhJksqTogether.companyAdress }" />
									</td>
									<td><label>部门名称：</label> 
										<input type="text" id="department" name="department" size="30" class="required" value="${xhJksqTogether.department }" />
									</td>
								</tr>
								<tr>
									<td><label>职务：</label> 
										<input type="text" id="function" name="function" size="30" class="required" value="${xhJksqTogether.function }" />
									</td>
									<td><label>婚姻状况：</label> 
										<select id="maritalStatus" name="maritalStatus" class="combox">
											<c:forEach items="${hyzk0009}" var="per">
												<option value="${per.value }">${per.name }</option>
											</c:forEach>
										</select>
									</td>
									<td><label>有无子女：</label> 
										<select id="ywzn" name="ywzn" class="combox">
											<c:forEach items="${ywzn0018}" var="per">
												<option value="${per.value }"  
													<c:if test='${xhJksqTogether.ywzn ==per.value }'>selected="selected" </c:if>
												>${per.name }</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="2"><label>居住状况：</label> 
										<input type="radio" id="liveStateTogether" name="liveState" value="01" 
											<c:if test='${xhJksqTogether.liveState =="01" }'>checked="checked" </c:if>
											 onclick="liveStateTogetherShow('liveMessageShow01')" />自购房屋&nbsp;&nbsp;
										<input type="radio" id="liveStateTogether" name="liveState" value="02" 
											<c:if test='${xhJksqTogether.liveState =="02" }'>checked="checked" </c:if>
											onclick="liveStateTogetherShow('liveMessageShow02')" />借款购置房屋&nbsp;&nbsp;
										<input type="radio" id="liveStateTogether" name="liveState" value="03" 
											<c:if test='${xhJksqTogether.liveState =="03" }'>checked="checked" </c:if>
											onclick="liveStateTogetherShow('liveMessageShow03')" />借款购置房屋
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<input type="radio" id="liveStateTogether" name="liveState" value="04"
											<c:if test='${xhJksqTogether.liveState =="04" }'>checked="checked" </c:if>
											onclick="liveStateTogetherShow('liveMessageShow04')" />租房，房租</td>
									<td>
										<input type="text" id="liveMessageShow04" name="liveMessage" value="" disabled="disabled" />元/月
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<input type="radio" id="liveStateTogether" name="liveState" value="05"
											<c:if test='${xhJksqTogether.liveState =="05" }'>checked="checked" </c:if>
											onclick="liveStateTogetherShow('liveMessageShow05')" />其他，说明：
										</td>
									<td>
										<input type="text" id="liveMessageShow05" name="liveMessage" size="80" value="" disabled="disabled"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="1"><label>主要收入来源：</label> 
										<input type="checkbox" id="cBoxMonthlySalaryShow" name="cBoxMonthlySalary" value="01" 
											<c:if test='${cBoxMonthlySalaryShow == true }'> checked="checked" </c:if>
											 onclick="selectOne(this,'monthlySalaryIndex')" />每月工资(含奖金及补助)
									</td>
									<td>
										<input type="text" id="monthlySalaryIndex" name="monthlySalary" value="${xhJksqTogether.monthlySalary }" disabled="disabled" />元/月
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<input type="checkbox" id="cBoxRentalShow" name="cBoxRental" value="01" 
											<c:if test='${cBoxRentalShow == true }'> checked="checked" </c:if>
											onclick="selectOne(this,'rentalIndex')" />房屋出租
									</td>
									<td>
										<input type="text" id="rentalIndex" name="rental" value="${xhJksqTogether.rental }" disabled="disabled" />元/月
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<input type="checkbox" id="cBoxOtherIncomeShow" name="cBoxOtherIncome" value="01" 
											<c:if test='${cBoxOtherIncomeShow == true }'> checked="checked" </c:if>
											onclick="selectOne(this,'otherIncomeIndex')" />其他所得
									</td>
									<td>
										<input type="text" id="otherIncomeIndex" name="otherIncome" value="${xhJksqTogether.otherIncome }" disabled="disabled" />元/年
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<input type="checkbox" id="cBoxAnnualIncomeShow" name="cBoxAnnualIncome" value="01"
											<c:if test='${cBoxAnnualIncomeShow == true }'> checked="checked" </c:if>
											onclick="selectOne(this,'annualIncomeIndex')" />年总收入</td>
									<td>
										<input type="text" id="annualIncomeIndex" name="annualIncome" value="${xhJksqTogether.annualIncome }" disabled="disabled" />元
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label>
										<input type="checkbox" id="cBoxSocialFundShow" name="cBoxSocialFund" value="01"
											<c:if test='${cBoxSocialFundShow == true }'> checked="checked" </c:if>
											onclick="selectOne(this,'socialFund')" />是否拥有社保基金： </td>
									<td>
										<input type="radio" id="socialFund" name="socialFund" value="是" 
											<c:if test='${xhJksqTogether.socialFund =="是" }'>checked="checked" </c:if>
										/>是
										<input type="radio" id="socialFund" name="socialFund" value="否" 
											<c:if test='${xhJksqTogether.socialFund =="否" }'>checked="checked" </c:if>
										/>否
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> </td>
									<td></td>
								</tr>
								<tr>
									<td colspan="1"><label>申请额度：</label> 
										<input type="text" id="loanQuota" name="loanQuota" size="30" class="required" value="${xhJksqTogether.loanQuota }" />
									</td>
									<td><label>申请还款期限：</label> 
										<input type="text" id="jkCycle" name="jkCycle" size="30" class="required" value="${xhJksqTogether.jkCycle }" />
									</td>
									<td><label></label> 
									</td>
								</tr>
								<tr>
									<td colspan="2"><label>还款资金来源：</label> 
										<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="01"
											<c:if test='${xhJksqTogether.sourceOfFunds =="01" }'>checked="checked" </c:if>
											onclick="sourceOfFundsShow('01')" />独立还款&nbsp;&nbsp;
										<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="02"
											<c:if test='${xhJksqTogether.sourceOfFunds =="02" }'>checked="checked" </c:if>
											onclick="sourceOfFundsShow('02')" />家人协助还款&nbsp;&nbsp;
									</td>
									
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<input type="radio" id="sourceOfFunds" name="sourceOfFunds" value="03"
											<c:if test='${xhJksqTogether.sourceOfFunds =="03" }'>checked="checked" </c:if>
											onclick="sourceOfFundsShow('03')" />其他方式
									</td>
									<td align="left">
										<input type="text" id="sourceOfFundsInfo" name="sourceOfFundsInfo" size="30" value="${xhJksqTogether.sourceOfFundsInfo }" />
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
									<c:forEach items="${relativesTogether }" var="relatives" varStatus="st">
									<tr>
										<td align="center">${relatives.ybrgx }
											<input type="hidden" name="id${st.count }" value="${relatives.id }">
										</td>
										<td align="left"><input type="text" name="name${st.count }" value="${relatives.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count }" value="${relatives.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count }" value="${relatives.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count }" value="${relatives.jjlxrlxdh }"></td>
									</tr>
									</c:forEach>
									<c:forEach items="${friendTogether }" var="friend" varStatus="st">
									<tr>
										<td align="center">${friend.ybrgx }
											<input type="hidden" name="id${st.count + 2 }" value="${friend.id }">
										</td>
										<td align="left"><input type="text" name="name${st.count + 2}" value="${friend.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count  + 2}" value="${friend.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count + 2 }" value="${friend.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count + 2 }" value="${friend.jjlxrlxdh }"></td>
									</tr>
									</c:forEach>
									<c:forEach items="${workmateTogether }" var="workmate" varStatus="st">
									<tr>
										<td align="center">${workmate.ybrgx }
											<input type="hidden" name="id${st.count + 4 }" value="${workmate.id }">
										</td>
										<td align="left"><input type="text" name="name${st.count + 4 }" value="${workmate.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count  + 4}" value="${workmate.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count + 4 }" value="${workmate.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count + 4 }" value="${workmate.jjlxrlxdh }"></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="formBar">
								<ul>
									<li><div class="button">
											<div class="buttonContent">
												<button type="button" class="close">关闭</button>
											</div>
										</div></li>
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
	function jkUsestate(obj){
		var state = obj.value;
		jkTypeDiv(state);
	}
	
	function jkTypeDiv(state){
		if("A" == state){
			document.getElementById('lbd_div_show').style.display = "block";
			document.getElementById('lblyd_div_show').style.display = "none";
			document.getElementById('xsd_div_show').style.display = "none";
			document.getElementById('xslyd_div_show').style.display = "none";
			document.getElementById('jyd_div_show').style.display = "none";
			document.getElementById('qyd_div_show').style.display = "none";
			document.getElementById('jylyd_div_show').style.display = "none";
		}else if("B" == state){
			document.getElementById('lbd_div_show').style.display = "none";
			document.getElementById('lblyd_div_show').style.display = "block";
			document.getElementById('xsd_div_show').style.display = "none";
			document.getElementById('xslyd_div_show').style.display = "none";
			document.getElementById('jyd_div_show').style.display = "none";
			document.getElementById('qyd_div_show').style.display = "none";
			document.getElementById('jylyd_div_show').style.display = "none";
		}else if("C" == state){
			document.getElementById('lbd_div_show').style.display = "none";
			document.getElementById('lblyd_div_show').style.display = "none";
			document.getElementById('xsd_div_show').style.display = "block";
			document.getElementById('xslyd_div_show').style.display = "none";
			document.getElementById('jyd_div_show').style.display = "none";
			document.getElementById('qyd_div_show').style.display = "none";
			document.getElementById('jylyd_div_show').style.display = "none";
		}else if("D" == state){
			document.getElementById('lbd_div_show').style.display = "none";
			document.getElementById('lblyd_div_show').style.display = "none";
			document.getElementById('xsd_div_show').style.display = "none";
			document.getElementById('xslyd_div_show').style.display = "block";
			document.getElementById('jyd_div_show').style.display = "none";
			document.getElementById('qyd_div_show').style.display = "none";
			document.getElementById('jylyd_div_show').style.display = "none";
		}else if("E" == state){
			document.getElementById('lbd_div_show').style.display = "none";
			document.getElementById('lblyd_div_show').style.display = "none";
			document.getElementById('xsd_div_show').style.display = "none";
			document.getElementById('xslyd_div_show').style.display = "none";
			document.getElementById('jyd_div_show').style.display = "block";
			document.getElementById('qyd_div_show').style.display = "none";
			document.getElementById('jylyd_div_show').style.display = "none";
		}else if("Q" == state){
			document.getElementById('lbd_div_show').style.display = "none";
			document.getElementById('lblyd_div_show').style.display = "none";
			document.getElementById('xsd_div_show').style.display = "none";
			document.getElementById('xslyd_div_show').style.display = "none";
			document.getElementById('jyd_div_show').style.display = "none";
			document.getElementById('qyd_div_show').style.display = "block";
			document.getElementById('jylyd_div_show').style.display = "none";
		}else if("W" == state){
			document.getElementById('lbd_div_show').style.display = "none";
			document.getElementById('lblyd_div_show').style.display = "none";
			document.getElementById('xsd_div_show').style.display = "none";
			document.getElementById('xslyd_div_show').style.display = "none";
			document.getElementById('jyd_div_show').style.display = "none";
			document.getElementById('qyd_div_show').style.display = "none";
			document.getElementById('jylyd_div_show').style.display = "block";
		}
	}

	function livestateShow(state){
		if("liveMessage01" == state){
			document.jksqShowMsgForm.liveMessage01.disabled = false;
			document.jksqShowMsgForm.liveMessage02.disabled = true;
			document.jksqShowMsgForm.liveMessage03.disabled = true;
			document.jksqShowMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage02" == state){
			document.jksqShowMsgForm.liveMessage01.disabled = true;
			document.jksqShowMsgForm.liveMessage02.disabled = false;
			document.jksqShowMsgForm.liveMessage03.disabled = true;
			document.jksqShowMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage03" == state){
			document.jksqShowMsgForm.liveMessage01.disabled = true;
			document.jksqShowMsgForm.liveMessage02.disabled = true;
			document.jksqShowMsgForm.liveMessage03.disabled = false;
			document.jksqShowMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage04" == state){
			document.jksqShowMsgForm.liveMessage01.disabled = true;
			document.jksqShowMsgForm.liveMessage02.disabled = true;
			document.jksqShowMsgForm.liveMessage03.disabled = true;
			document.jksqShowMsgForm.liveMessage04.disabled = false;
		}
	}

	function jksqShowSubmit(val){
		document.jksqShowMsgForm.opt.value = val;
		return true;
	}
	
	//借款申请修改提交
	function jksqShowMsgFormSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}

		return validateCallback(obj, navTabAjaxDone);
	}
	
	//借款申请共同还款人修改提交
	function jksqTogetherShowSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}

		return validateCallback(obj, navTabAjaxDone);
	}

	function initJksqShowMsg(){
		var liveState = "${jksq.liveState }";
		if("01" == liveState){
			document.jksqShowMsgForm.liveMessage01.value = "${jksq.liveMessage }";
			document.jksqShowMsgForm.liveMessage02.disabled = true;
			document.jksqShowMsgForm.liveMessage03.disabled = true;
			document.jksqShowMsgForm.liveMessage04.disabled = true;
		}else if("02" == liveState){
			document.jksqShowMsgForm.liveMessage01.disabled = true;
			document.jksqShowMsgForm.liveMessage02.value = "${jksq.liveMessage }";
			document.jksqShowMsgForm.liveMessage03.disabled = true;
			document.jksqShowMsgForm.liveMessage04.disabled = true;
		}else if("03" == liveState){
			document.jksqShowMsgForm.liveMessage01.disabled = true;
			document.jksqShowMsgForm.liveMessage02.disabled = true;
			document.jksqShowMsgForm.liveMessage03.value = "${jksq.liveMessage }";
			document.jksqShowMsgForm.liveMessage04.disabled = true;
		}else if("04" == liveState){
			document.jksqShowMsgForm.liveMessage01.disabled = true;
			document.jksqShowMsgForm.liveMessage02.disabled = true;
			document.jksqShowMsgForm.liveMessage03.disabled = true;
			document.jksqShowMsgForm.liveMessage04.value = "${jksq.liveMessage }";
		}
	}

	function initJksqTogetherShowMsg(){
		var liveState = "${xhJksqTogether.liveState }";
		if("01" == liveState){
		}else if("02" == liveState){
		}else if("03" == liveState){
		}else if("04" == liveState){
			document.jksqTogetherShowForm.liveMessageShow04.value = "${xhJksqTogether.liveMessage }";
		}else if("05" == liveState){
			document.jksqTogetherShowForm.liveMessageShow05.value = "${xhJksqTogether.liveMessage }";
		}
	}

	function liveStateTogetherDisabled(){
		var liveStateArray = document.jksqTogetherShowForm.liveState;
		var liveState = "";
		 for(var i=0;i<liveStateArray.length;i++){
             if(liveStateArray[i].checked){
            	 liveState = liveStateArray[i].value;
                 break;
             }
         }

		if("01" == liveState){
		}else if("02" == liveState){
		}else if("03" == liveState){
		}else if("04" == liveState){
			document.jksqTogetherShowForm.liveMessageShow04.disabled = false;
		}else if("05" == liveState){
			document.jksqTogetherShowForm.liveMessageShow05.disabled = false;
		}
	}
	
	function liveStateTogetherShow(state){
		if("liveMessageShow01" == state){
			document.jksqTogetherShowForm.liveMessageShow04.disabled = true;
			document.jksqTogetherShowForm.liveMessageShow05.disabled = true;
		}else if("liveMessageShow02" == state){
			document.jksqTogetherShowForm.liveMessageShow04.disabled = true;
			document.jksqTogetherShowForm.liveMessageShow05.disabled = true;
		}else if("liveMessageShow03" == state){
			document.jksqTogetherShowForm.liveMessageShow04.disabled = true;
			document.jksqTogetherShowForm.liveMessageShow05.disabled = true;
		}else if("liveMessageShow04" == state){
			document.jksqTogetherShowForm.liveMessageShow04.disabled = false;
			document.jksqTogetherShowForm.liveMessageShow05.disabled = true;
		}else if("liveMessageShow05" == state){
			document.jksqTogetherShowForm.liveMessageShow04.disabled = true;
			document.jksqTogetherShowForm.liveMessageShow05.disabled = false;
		}
	}
	
	function initJkType(){
		var state = "${jksq.jkType }";
		jkTypeDiv(state);
	}
	
	function sourceOfFundsShow(state){
		if("01" == state){
			document.jksqTogetherShowForm.sourceOfFundsInfo.disabled = true;
		}else if("02" == state){
			document.jksqTogetherShowForm.sourceOfFundsInfo.disabled = true;
		}else if("03" == state){
			document.jksqTogetherShowForm.sourceOfFundsInfo.disabled = false;
		}
	}
	
	function initMaritalStatusShow(){
		var maritalStatus = document.jksqShowMsgForm.maritalStatus.value;
		
		var div = document.getElementById('isMarrayShow');
//		var maritalStatus = obj.value;
		if("已婚" == maritalStatus){
			div.style.display = "block";
		}else if("未婚" == maritalStatus){
			div.style.display = "none";
		}else{
			div.style.display = "none";
		}
	}
	
	function initializationShow(){
		initJksqShowMsg();
		initJkType();
		initJksqTogetherShowMsg();
		liveStateTogetherDisabled();
		initMaritalStatusShow();
	}
	initializationShow();

</script>