<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>借款申请信息</span></a></li>
					<!-- 
					<c:if test='${jksq.togetherPerson == "是"}'>
					<li><a href="javascript:;"><span>共同还款人信息</span></a></li>
					</c:if>
					 -->
				</ul>
			</div>
		</div>
		<div class="tabsContent" >
			<div>
				<form id="jksqHisShowForm" name="jksqHisShowForm" method="post"
					action="${ctx}/jksq/editSaveJksqMsg" class="pageForm required-validate"
					onsubmit="return jksqHisShowFormSubmit(this);">
					<div class="pageFormContent" layoutH="56">
						<input type="hidden" id="xydkzxId" name="xydkzxId" value="${xydkzx.id }" />
						<input type="hidden" id="opt" name="opt" />
						<div class="panel"><h1>客户基本信息</h1></div>
						<table nowrapTD="false">
							<tr>
								<td><label>借款人姓名:</label> 
									<input type="text" id="jkrxm" name="jkrxm" size="30" value="${jksq.jkrxm }" class="required" readonly="readonly">
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
									<input type="text" name="birthday" 	class="date required" 
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
									<input type="text" id="hjadress" name="hjadress" size="60" class="" value="${jksq.hjadress }" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>现住址:</label> 
									<input type="text" id="homeAddress" name="homeAddress" size="60" value="${jksq.homeAddress }"/>
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
									<select id="companyNature" name="companyNature" class="combox required">
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
									<input type="text" id="telephone" name="telephone" size="30" class="" value="${jksq.telephone }" />
								</td>
								<td><label>电子邮箱：</label> 
									<input type="text" id="email" name="email" size="30" class="email" value="${jksq.email }" />
								</td>
								<td><label>婚姻状况：</label> 
									<select id="maritalStatus" name="maritalStatus" class="combox required">
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
									<input type="text" id="ywzn" name="ywzn" size="30" class="" value="${jksq.ywzn }" />
								</td>
								<td><label>QQ号码：</label> 
									<input type="text" id="qqhm" name="qqhm" size="30" class="" value="${jksq.qqhm }" />
								</td>
								<td><label>月收入(元)：</label> 
									<input type="text" id="annualIncome" name="annualIncome" size="10" 
										class="" value="${jksq.annualIncome }" /><span class="info"> (数字格式,无千分位)</span>
								</td>
							</tr>
						</table>
							<div class="divider"></div>
						<table >
							<tr>
								<td colspan="3"><label>收入说明：</label> 
									<input type="text" id="incomeIllustration" name="incomeIllustration" size="60" class="" value="${jksq.incomeIllustration }" />
								</td>
							</tr>
							<tr>
								<td colspan="1"><label>居住状态：</label> 
									<input type="radio" id="liveState" name="liveState" value="01" 
										<c:if test='${jksq.liveState == "01" }'> checked="checked" </c:if>
										 onclick="livestateHisShow('liveMessage01')" />自有房屋，有无借款，月供
								</td>
								<td>
									<input id="liveMessage01" name="liveMessage" type="text" value="" />元
								</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
									<input type="radio" id="liveState" name="liveState" value="02" 
										<c:if test='${jksq.liveState == "02" }'> checked="checked" </c:if>
										onclick="livestateHisShow('liveMessage02')" />亲属产权
								</td>
								<td><input type="hidden" id="liveMessage02" name="liveMessage" 	value="" /></td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
									<input type="radio" id="liveState" name="liveState" value="03"
										<c:if test='${jksq.liveState == "03" }'> checked="checked" </c:if>
										onclick="livestateHisShow('liveMessage03')" />租房，房租</td>
								<td>
									<input type="text" id="liveMessage03" name="liveMessage" value="" />元/月
								</td>
							</tr>
							<tr>
								<td colspan="1"><label></label> 
									<input type="radio" id="liveState" name="liveState" value="04"
										<c:if test='${jksq.liveState == "04" }'> checked="checked" </c:if>
										onclick="livestateHisShow('liveMessage04')" />其他，说明：
									</td>
								<td>
									<input type="text" id="liveMessage04" name="liveMessage" size="80" value="" />
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<dl class="nowrap">
										<dt>通讯地址：</dt>
										<dd>
											<select name="province.id" ref="jksqHisShow_city" class="combox" 
												refUrl="${ctx}/cjrxx/getCity?code={value}" >
												<option value="" <c:if test="${jksq.province==''}">selected</c:if>>所有省市</option>
												<c:forEach items="${province}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.province.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<select id="jksqHisShow_city" name="city.id" class="combox" ref="jksqHisShow_area"
												 refUrl="${ctx}/cjrxx/getArea?code={value}" >
												<option value="" <c:if test="${jksq.city==''}">selected</c:if>>所有城市</option>
												<c:forEach items="${city}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.city.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<select  id="jksqHisShow_area" name="area.id" class="combox" >
												<option value="" <c:if test="${jksq.area==''}">selected</c:if>>所有区县</option>
												<c:forEach items="${area}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.area.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<input type="text" name="txdz" size="80" value="${jksq.txdz }" maxlength="100" />
										</dd>
									</dl>
								
								</td>
								<td>
								</td>
							</tr>
						</table>
						<br />
						<div id="isMarrayhistShow">
							<div class="panel"><h1>以下为配偶信息（借款人婚姻状况为已婚时必填项）</h1></div>
							<table>
								<tr>
									<td><label>姓名:</label> 
										<input type="text" id="spouseName" name="spouseName" size="30" value="${jksq.spouseName }" />
									</td>
									<td><label>性别：</label> 
										<input type="radio" id="spouseGenders" name="spouseGenders" value="男" 
											<c:if test='${jksq.spouseGenders == "男"}'>checked="checked" </c:if>
										 />男&nbsp; 
										<input type="radio" id="spouseGenders" name="spouseGenders" value="女" 
											<c:if test='${jksq.spouseGenders == "女"}'>checked="checked" </c:if>
										 />女
									</td>
									<td><label>出生日期：</label> 
										<input type="text" name="spouseBirthday" class="date" 
											format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.spouseBirthday }" />
											<a class="inputDateButton" href="javascript:;">选择</a>
									</td>
								</tr>
								<tr>
									<td><label>现住址：</label> 
										<input type="text" id="spouseAdress" name="spouseAdress" size="30" value="${jksq.spouseAdress }" />
									</td>
									<td><label>证件类型：</label> 
										<select id="spousePocertificates" name="spousePocertificates" class="combox">
											<c:forEach items="${zjlx0005}" var="per">
												<option value="${per.value }">${per.name }</option>
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
											value="${jksq.spouseAnnualIncome }" /><span class="info"> (数字格式,无千分位)</span>
									</td>
									<td><label>服务年限(年)：</label> 
										<input type="text" id="spouseWorkinglife" name="spouseWorkinglife" size="10" 
											value="${jksq.spouseWorkinglife }" minlength="1" maxlength="3" /><span class="info"> (数字格式)</span>
									</td>
								</tr>
							</table>
							<div class="divider"></div>
						</div>
						
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
									<c:forEach items="${relatives }" var="relatives" varStatus="st">
									<tr>
										<td align="center">${relatives.ybrgx }
											<input type="hidden" name="id${st.count }" value="${relatives.id }">
											<input type="hidden" name="ybrgx${st.count }" value="${relatives.ybrgx }">
										</td>
										<td align="left"><input type="text" name="name${st.count }" value="${relatives.name }"></td>
										<td align="left"><input type="text" name="jjlxrgzdw${st.count }" value="${relatives.jjlxrgzdw }"></td>
										<td align="left"><input type="text" name="jjlxrdwdzhjtzz${st.count }" value="${relatives.jjlxrdwdzhjtzz }"></td>
										<td align="left"><input type="text" name="jjlxrlxdh${st.count }" value="${relatives.jjlxrlxdh }"></td>
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
										<td align="left"><input type="text" name="jjlxrlxdh${st.count + 2 }" value="${friend.jjlxrlxdh }"></td>
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
										<td align="left"><input type="text" name="jjlxrlxdh${st.count + 4 }" value="${workmate.jjlxrlxdh }"></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div class="panel"><h1>借款信息</h1></div>

							<table border="0" bordercolor="red">
								<tr>
									<td><label>借款申请额度(元):</label> 
										<input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" 
											class="required" value="${jksq.jkLoanQuota }"><span class="info"> (必填,无千分位)</span>
									</td>
									<td><label>申请借款期限(月):</label> 
										<input type="text" id="jkCycle" name="jkCycle" size="10" 
											value="${jksq.jkCycle }" /><span class="info"> (必填,整数)</span>
									</td>
									<td><label>还款方式：</label> 
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
										<dl>
											<dt>借款用途：</dt>
											<dd>
												<select id="jkUse" name="jkUse" class="combox">
													<option value="" >${jksq.jkUse }</option>
												</select>
											</dd>
										</dl>
									</td>
									<td><label>申请日期：</label> 
										<input type="text" id="jkLoanDate" name="jkLoanDate" class="date required" 
											format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.jkLoanDate }" />
											<a class="inputDateButton" href="javascript:;">选择</a>
									</td>
									<td><label>共同还款人：</label> 
										<input type="radio" id="togetherPerson" name="togetherPerson" value="是" checked="checked" />是&nbsp;
										<input type="radio" id="togetherPerson" name="togetherPerson" value="否" />否&nbsp;
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
								<tr>
									<td colspan="3"><label>借款类型：</label> 
										<input type="radio" id="jkType" name="jkType" 
											<c:if test='${jksq.jkType =="A" }'>checked="checked" </c:if>
											value="A" onclick="jkTypeHisShow(this)"  />老板借&nbsp;
										<input type="radio" id="jkType" name="jkType" 
											<c:if test='${jksq.jkType =="B" }'>checked="checked" </c:if>
											value="B" onclick="jkTypeHisShow(this)" />老板楼易借&nbsp; 
										<input type="radio" id="jkType" name="jkType" 
											<c:if test='${jksq.jkType =="C" }'>checked="checked" </c:if>
											value="C" onclick="jkTypeHisShow(this)" />薪水借&nbsp;
										<input type="radio" id="jkType" name="jkType" 
											<c:if test='${jksq.jkType =="D" }'>checked="checked" </c:if>
											value="D" onclick="jkTypeHisShow(this)" />薪水楼易借&nbsp; 
										<input type="radio" id="jkType" name="jkType" 
											<c:if test='${jksq.jkType =="E" }'>checked="checked" </c:if>
											value="E" onclick="jkTypeHisShow(this)" />精英借&nbsp;
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
										<div id="lbd_div_hisshow" name="lbd_div_hisshow" style="display: none;">
											${lbd }
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="lblyd_div_hisshow" name="lblyd_div_hisshow" style="display: none;">
											${lblyd }
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="xsd_div_hisshow" name="xsd_div_hisshow" style="display: none;">
											${xsd }
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="xslyd_div_hisshow" name="xslyd_div_hisshow" style="display: none;">
											${xslyd }
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="jyd_div_hisshow" name="jyd_div_hisshow" style="display: none;">
											${jyd }
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2"><label>开户行：</label> 
										<input type="text" id="bankOpen" name="bankOpen" value="${jksq.bankOpen }" size="80"/>
									</td>
									<td><label>借款编号：</label> 
										<input type="text" id="loanCode" name="loanCode" class="required digits" value="${jksq.loanCode }" size="30"/>
									</td>
								</tr>
								<tr>
									<td><label>账户号码：</label> 
										<input type="text" id="bankNum" name="bankNum" value="${jksq.bankNum }" size="30"/>
									</td>
								</tr>
								<tr>
									<td><label>账户名称：</label> 
										<input type="text" id="bankUsername" name="bankUsername" value="${jksq.bankUsername }" size="30"/>
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
								<tr>
									<td colspan="3">
										<div></div>
									</td>
								</tr>
							</table>
							<c:if test="${jksq.appState=='2' || jksq.appState=='3'}">
								<dl class="nowrap">
									<dt>审核意见：</dt>
									<dd>
										<textarea name="auditIdea" style="width: 93%; height: 80"
											disabled="disabled">${jksq.auditIdea }</textarea>
									</dd>
								</dl>
								<dl class="nowrap">
									<dt>审核结果：</dt>
									<dd>
										<input type="radio" name="appState" 
											<c:if test='${jksq.appState == "2" }'>checked="checked" </c:if>
											value="2" disabled="disabled" />通过
										<input type="radio" name="appState" 
											<c:if test='${jksq.appState == "3" }'>checked="checked" </c:if>
											value="3" disabled="disabled" />不通过
									</dd>
								</dl>
							</c:if>	
						
						<div class="formBar">
							<ul>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">取消</button>
										</div>
									</div></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
			
			<div>
						
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>



</div>
<script type="text/javascript">
	function jkTypeHisShow(obj){
		var state = "${jksq.jkType }";
		jkTypeHisShowDiv(state);
	}
	
	function jkTypeHisShowDiv(state){
		if("A" == state){
			document.getElementById('lbd_div_hisshow').style.display = "block";
			document.getElementById('lblyd_div_hisshow').style.display = "none";
			document.getElementById('xsd_div_hisshow').style.display = "none";
			document.getElementById('xslyd_div_hisshow').style.display = "none";
			document.getElementById('jyd_div_hisshow').style.display = "none";
		}else if("B" == state){
			document.getElementById('lbd_div_hisshow').style.display = "none";
			document.getElementById('lblyd_div_hisshow').style.display = "block";
			document.getElementById('xsd_div_hisshow').style.display = "none";
			document.getElementById('xslyd_div_hisshow').style.display = "none";
			document.getElementById('jyd_div_hisshow').style.display = "none";
		}else if("C" == state){
			document.getElementById('lbd_div_hisshow').style.display = "none";
			document.getElementById('lblyd_div_hisshow').style.display = "none";
			document.getElementById('xsd_div_hisshow').style.display = "block";
			document.getElementById('xslyd_div_hisshow').style.display = "none";
			document.getElementById('jyd_div_hisshow').style.display = "none";
		}else if("D" == state){
			document.getElementById('lbd_div_hisshow').style.display = "none";
			document.getElementById('lblyd_div_hisshow').style.display = "none";
			document.getElementById('xsd_div_hisshow').style.display = "none";
			document.getElementById('xslyd_div_hisshow').style.display = "block";
			document.getElementById('jyd_div_hisshow').style.display = "none";
		}else if("E" == state){
			document.getElementById('lbd_div_hisshow').style.display = "none";
			document.getElementById('lblyd_div_hisshow').style.display = "none";
			document.getElementById('xsd_div_hisshow').style.display = "none";
			document.getElementById('xslyd_div_hisshow').style.display = "none";
			document.getElementById('jyd_div_hisshow').style.display = "block";
		}
	}

	function livestateHisShow(state){
		if("liveMessage01" == state){
			document.jksqHisShowForm.liveMessage01.disabled = false;
			document.jksqHisShowForm.liveMessage02.disabled = true;
			document.jksqHisShowForm.liveMessage03.disabled = true;
			document.jksqHisShowForm.liveMessage04.disabled = true;
		}else if("liveMessage02" == state){
			document.jksqHisShowForm.liveMessage01.disabled = true;
			document.jksqHisShowForm.liveMessage02.disabled = false;
			document.jksqHisShowForm.liveMessage03.disabled = true;
			document.jksqHisShowForm.liveMessage04.disabled = true;
		}else if("liveMessage03" == state){
			document.jksqHisShowForm.liveMessage01.disabled = true;
			document.jksqHisShowForm.liveMessage02.disabled = true;
			document.jksqHisShowForm.liveMessage03.disabled = false;
			document.jksqHisShowForm.liveMessage04.disabled = true;
		}else if("liveMessage04" == state){
			document.jksqHisShowForm.liveMessage01.disabled = true;
			document.jksqHisShowForm.liveMessage02.disabled = true;
			document.jksqHisShowForm.liveMessage03.disabled = true;
			document.jksqHisShowForm.liveMessage04.disabled = false;
		}
	}

	function initJksqHisShowMsg(){
		var liveState = "${jksq.liveState }";
		if("01" == liveState){
			document.jksqHisShowForm.liveMessage01.value = "${jksq.liveMessage }";
			document.jksqHisShowForm.liveMessage02.disabled = true;
			document.jksqHisShowForm.liveMessage03.disabled = true;
			document.jksqHisShowForm.liveMessage04.disabled = true;
		}else if("02" == liveState){
			document.jksqHisShowForm.liveMessage01.disabled = true;
			document.jksqHisShowForm.liveMessage02.value = "${jksq.liveMessage }";
			document.jksqHisShowForm.liveMessage03.disabled = true;
			document.jksqHisShowForm.liveMessage04.disabled = true;
		}else if("03" == liveState){
			document.jksqHisShowForm.liveMessage01.disabled = true;
			document.jksqHisShowForm.liveMessage02.disabled = true;
			document.jksqHisShowForm.liveMessage03.value = "${jksq.liveMessage }";
			document.jksqHisShowForm.liveMessage04.disabled = true;
		}else if("04" == liveState){
			document.jksqHisShowForm.liveMessage01.disabled = true;
			document.jksqHisShowForm.liveMessage02.disabled = true;
			document.jksqHisShowForm.liveMessage03.disabled = true;
			document.jksqHisShowForm.liveMessage04.value = "${jksq.liveMessage }";
		}
	}

	function initJkType(){
		var state = "${jksq.jkType }";
		jkTypeDiv(state);
	}
	
	function initMaritalhistShow(){
		var maritalStatus = document.jksqHisShowForm.maritalStatus.value;
		var div = document.getElementById('isMarrayhistShow');
		if("已婚" == maritalStatus){
			div.style.display = "block";
		}else if("未婚" == maritalStatus){
			div.style.display = "none";
		}else{
			div.style.display = "none";
		}
	}
	function initJksqHisShow(){
		initJksqHisShowMsg();
		initMaritalhistShow();
		jkTypeHisShow();
	}
	initJksqHisShow();

</script>