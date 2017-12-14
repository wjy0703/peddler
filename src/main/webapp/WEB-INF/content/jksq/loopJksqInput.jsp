<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form id="jksqbaseMsgForm" name="jksqbaseMsgForm" method="post"
		action="${ctx}/jksq/saveJksqBaseMsg"
		class="pageForm required-validate"
		onsubmit="return loantgFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" id="xydkzxId" name="xydkzxId" value="${xydkzx.id }" />
			<input type="hidden" id="id" name="id" value="${jksq.id }"/>
			<input type="hidden" id="opt" name="opt" />
			<div class="panel"><h1>客户基本信息</h1></div>
			
			<table nowrapTD="false" width="100%">
				<tr>
					<td><label>借款人姓名:</label> 
						<input type="text" id="jkrxm" name="jkrxm" size="30" value="${xydkzx.khmc }" class="required" readonly="readonly">
					</td>
					<td><label>性别*：</label> 
						<input type="radio" id="genders" name="genders" value="男"  class="radio" 
							<c:if test="${xydkzx.sex=='0'}">checked="checked" </c:if>  
						 />男&nbsp; 
						<input type="radio" id="genders" name="genders" value="女"  
							<c:if test="${xydkzx.sex=='1'}">checked="checked" </c:if> 
						class="radio" />女
					</td>
					<td><label>出生日期：</label> 
						<input type="text" name="birthday" 	class="date " 
							yearstart="-113" yearend="5" 
							format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.birthday}"/>
						<a class="inputDateButton" href="javascript:;">选择</a>
					</td>
				</tr>
				<tr>
					<td><label>证件类型：</label> 
						<select id="pocertificates" name="pocertificates" class="combox required"  >
							<option value="">请选择</option>
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
						
					<input type="text" id="homeAddress" name="homeAddress" value="${jksq.homeAddress }" size="60"class="required"/></td>
				</tr>
				</table>
					<div class="divider"></div>
				<table width="100%">
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
						<input type="text" id="email" name="email" size="30" class="" value="${jksq.email }" />
					</td>
					<td><label>婚姻状况：</label> 
						<select id="maritalStatus" name="maritalStatus" onchange="jkMaritalStatusInput(this)" class="combox required">
							<c:forEach items="${hyzk0009}" var="per">
								<option value="${per.value }" 
									<c:if test='${jksq.maritalStatus ==per.value }'>selected="selected" </c:if>
								>${per.name }</option>
							</c:forEach>
						</select><span class="info"> (必选)</span>
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
						<input type="text" id="qqhm" name="qqhm" size="30" value="${jksq.qqhm }" />
					</td>
					<td ><label>月收入(元)：</label> 
						<input type="text" id="annualIncome" name="annualIncome" 
							size="10" class="number" value="${jksq.annualIncome }" /><span class="info"> (数字格式,无千分位)</span>
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>收入说明：</label> 
						<input type="text" id="incomeIllustration" name="incomeIllustration" size="60" value="${jksq.incomeIllustration }" />
					</td>
				</tr>
				</table>
				<div class="divider"></div>
				<table>
				<tr>
					<td colspan="1"><label>居住状态：</label> 
						<input type="radio" id="liveState" name="liveState" value="01" 
										<c:if test='${jksq.liveState == "01" }'> checked="checked" </c:if>
										 onclick="livestateChangeInput('liveMessage01')" />自有房屋，有无借款，月供
					</td>
					<td>
						<input id="liveMessage01" name="liveMessage" type="text" value="" /><label>元</label> 
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="02" 
										<c:if test='${jksq.liveState == "02" }'> checked="checked" </c:if>
										onclick="livestateChangeInput('liveMessage02')" />亲属产权
					</td>
					<td><input type="hidden" id="liveMessage02" name="liveMessage" 	value="" /></td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="03"
										<c:if test='${jksq.liveState == "03" }'> checked="checked" </c:if>
										onclick="livestateChangeInput('liveMessage03')" />租房，房租</td>
					<td>
						<input type="text" id="liveMessage03" name="liveMessage" value="" />元/月
					</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> 
						<input type="radio" id="liveState" name="liveState" value="04"
										<c:if test='${jksq.liveState == "04" }'> checked="checked" </c:if>
										onclick="livestateChangeInput('liveMessage04')" />其他,说明：
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
								<select name="province.id" ref="loaninputbox_city" class="required combox" 
									refUrl="${ctx}/cjrxx/getCity?code={value}" >
									<option value="">所属省市*</option>
									<c:forEach items="${province}" var="md" varStatus="st">
										<option value="${md.id }"
											<c:if test="${jksq.province.id==md.id}">selected</c:if>>${md.name }
										</option>
									</c:forEach>
								</select> 
								<select id="loaninputbox_city" name="city.id" class="required combox" ref="loaninputbox_area"
									 refUrl="${ctx}/cjrxx/getArea?code={value}" >
									<option value="">所属城市*</option>
									<c:forEach items="${city}" var="md" varStatus="st">
											<option value="${md.id }"
												<c:if test="${jksq.city.id==md.id}">selected</c:if>>${md.name }
											</option>
										</c:forEach>
								</select> 
								<select id="loaninputbox_area" name="area.id" class="required combox" >
									<option value="">所有区县*</option>
									<c:forEach items="${area}" var="md" varStatus="st">
										<option value="${md.id }"
											<c:if test="${jksq.area.id==md.id}">selected</c:if>>${md.name }
										</option>
									</c:forEach>
								</select>
							<input type="text" name="txdz" size="80" value="${jksq.txdz }" maxlength="100" /></dd>
						</dl>
					</td>
				</tr>
			</table>
			<br />
			
			<div id="isMarrayInput" style="display:block;">
				<div class="panel"><h1>以下为配偶信息（借款人婚姻状况为已婚时必填项）</h1></div>
				
				<table width="100%">
					<tr>
						<td><label>姓名:</label> 
							<input type="text" id="spouseName" name="spouseName" size="30" class="required" value="${jksq.spouseName }"/>
						</td>
						<td><label>性别：</label> 
							<input type="radio" id="spouseGenders" name="spouseGenders" value="男" 
								<c:if test='${jksq.spouseGenders == "男" }'>checked="checked" </c:if>
							  />男&nbsp; 
							<input type="radio" id="spouseGenders" name="spouseGenders" value="女" 
								<c:if test='${jksq.spouseGenders == "女" }'>checked="checked" </c:if>
							 />女
						</td>
						<td><label>出生日期：</label> 
							<input type="text" name="spouseBirthday" class="date " 
								yearstart="-113" yearend="5" format="yyyy-MM-dd" 
								pattern="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.spouseBirthday }"/>
								<a class="inputDateButton" href="javascript:;" id="spouRiqiInput" name="spouRiqiInput">选择</a>
						</td>
					</tr>
					<tr>
						<td><label>现住址：</label> 
							<input type="text" id="spouseAdress" name="spouseAdress" size="30" value="${jksq.spouseAdress }"/>
						</td>
						<td><label>证件类型：</label> 
							<select id="spousePocertificates" name="spousePocertificates" class="combox"  >
							<!-- 
							<option value="">请选择</option>
							 -->
								<c:forEach items="${zjlx0005}" var="per">
									<option value="${per.value }"  
										<c:if test='${jksq.spousePocertificates == per.value}'>selected="selected" </c:if>
									>${per.name }</option>
								</c:forEach>
							</select>
						</td>
						<td><label>证件号码：</label> 
							<input type="text" id="spouseZjhm" name="spouseZjhm" size="30" value="${jksq.spouseZjhm }"  />
						</td>
					</tr>
					<tr>
						<td><label>移动电话：</label> 
							<input type="text" id="spouseTelephone" name="spouseTelephone" size="30" value="${jksq.spouseTelephone }"  />
						</td>
						<td><label>家庭电话：</label> 
							<input type="text" id="spouseHomePhone" name="spouseHomePhone" size="30" value="${jksq.spouseHomePhone }"  />
						</td>
						<td><label>工作单位：</label> 
							<input type="text" id="spouseCompany" name="spouseCompany" size="30" value="${jksq.spouseCompany }"  />
						</td>
					</tr>
					<tr>
						<td><label>部门与职务：</label> 
							<input type="text" id="spouseDepFunction" name="spouseDepFunction" size="30" value="${jksq.spouseDepFunction }"  />
						</td>
						<td><label>单位电话：</label> 
							<input type="text" id="spouseCompanyPhone" name="spouseCompanyPhone" size="30" value="${jksq.spouseCompanyPhone }"  />
						</td>
						<td><label>单位地址：</label> 
							<input type="text" id="spouseCompanyAdress" name="spouseCompanyAdress" size="30" value="${jksq.spouseCompanyAdress }"  />
						</td>
					</tr>

					<tr>
						<td><label>月收入(元)：</label> 
							<input type="text" id="spouseAnnualIncome" name="spouseAnnualIncome" 
								size="10" value="${jksq.spouseAnnualIncome }"   class="number" /><span class="info"> (数字格式,无千分位)</span>
						</td>
						<td><label>服务年限(年)：</label> 
							<input type="text" id="spouseWorkinglife" name="spouseWorkinglife" 
								size="10" value="" minlength="1" class="digits" maxlength="3"  value="${jksq.spouseWorkinglife }"/><span class="info"> (数字格式)</span>
						</td>
					</tr>
				</table>
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
					<c:if test='${isJksq=="true"}'>
					<c:forEach items="${relatives }" var="relatives" varStatus="st">
					<tr>
						<td align="center">${relatives.ybrgx }
							<input type="hidden" name="id${st.count }" value="${relatives.id }">
							<input type="hidden" name="ybrgx${st.count }" value="${relatives.ybrgx }">
						</td>
						<td align="left"><input type="text" name="name${st.count }" value="${relatives.name }"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw${st.count }" value="${relatives.jjlxrgzdw }"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz${st.count }" value="${relatives.jjlxrdwdzhjtzz }"></td>
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
						<td align="left"><input type="text" size="50" name="jjlxrgzdw${st.count  + 2}" value="${friend.jjlxrgzdw }"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz${st.count + 2 }" value="${friend.jjlxrdwdzhjtzz }"></td>
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
						<td align="left"><input type="text" size="50" name="jjlxrgzdw${st.count  + 4}" value="${workmate.jjlxrgzdw }"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz${st.count + 4 }" value="${workmate.jjlxrdwdzhjtzz }"></td>
						<td align="left"><input type="text" name="jjlxrlxdh${st.count + 4 }" value="${workmate.jjlxrlxdh }"></td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test='${isJksq=="false"}'>
					<tr>
						<td align="center">亲属<input type="hidden" name="ybrgx1" value="亲属"></td>
						<td align="left"><input type="text" name="name1"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw1" size="50"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz1" size="50"></td>
						<td align="left"><input type="text" name="jjlxrlxdh1" class="digits"></td>
					</tr>
					<tr>
						<td align="center">亲属<input type="hidden" name="ybrgx2" value="亲属"></td>
						<td align="left"><input type="text" name="name2"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw2" size="50"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz2" size="50"></td>
						<td align="left"><input type="text" name="jjlxrlxdh2" class="digits"></td>
					</tr>
					<tr>
						<td align="center">朋友<input type="hidden" name="ybrgx3" value="朋友"></td>
						<td align="left"><input type="text" name="name3"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw3" size="50"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz3" size="50"></td>
						<td align="left"><input type="text" name="jjlxrlxdh3" class="digits"></td>
					</tr>
					<tr>
						<td align="center">朋友<input type="hidden" name="ybrgx4" value="朋友"></td>
						<td align="left"><input type="text" name="name4"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw4" size="50"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz4" size="50"></td>
						<td align="left"><input type="text" name="jjlxrlxdh4" class="digits"></td>
					</tr>
					<tr>
						<td align="center">同事<input type="hidden" name="ybrgx5" value="同事"></td>
						<td align="left"><input type="text" name="name5"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw5" size="50"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz5" size="50"></td>
						<td align="left"><input type="text" name="jjlxrlxdh5" class="digits"></td>
					</tr>
					<tr>
						<td align="center">同事<input type="hidden" name="ybrgx6" value="同事"></td>
						<td align="left"><input type="text" name="name6"></td>
						<td align="left"><input type="text" size="50" name="jjlxrgzdw6" size="50"></td>
						<td align="left"><input type="text" size="50" name="jjlxrdwdzhjtzz6" size="50"></td>
						<td align="left"><input type="text" name="jjlxrlxdh6" class="digits"></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			
			<div class="panel"><h1>借款信息</h1></div>
			
			<table border="0" bordercolor="red" width="100%">
				<tr>
					<td><label>借款申请额度(元):</label> 
						<input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" 
							value="${xydkzx.planAmount }" class="required number"><span class="info"> (必填,无千分位)</span>
					</td>
					<td><label>申请借款期限(月):</label> 
						<input type="text" id="jkCycle" name="jkCycle" size="10" 
							class="required digits" value="${jksq.jkCycle }" /><span class="info"> (必填,整数)</span>
					</td>
					<td><label>还款方式*：</label> 
						<select id="hkWay" name="hkWay" class="required combox">
						<c:forEach items="${hkWay0019}" var="per">
							<option value="${per.value }" 
								<c:if test='${jksq.hkWay ==per.value }'>selected="selected" </c:if>
							>${per.name }</option>
						</c:forEach>
						</select><span class="info"> (必填)</span>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<dl >
							<dt>借款用途*：</dt>
							<dd>
								<select id="jkUse" name="jkUse" class="required combox">
								<c:forEach items="${jkUse0027}" var="per">
									<option value="${per.value }" 
										<c:if test='${jksq.jkUse ==per.value }'>selected="selected" </c:if>
									>${per.name }</option>
								</c:forEach>
								</select><span class="info"> (必填)</span>
							</dd>
						</dl>
					</td>
					<td><label>申请日期：</label> 
						<input type="text" id="jkLoanDate" name="jkLoanDate" class="date required" 
							pattern="yyyy-MM-dd" 
							format="yyyy-MM-dd" size="27" readonly="readonly" value="${jksq.jkLoanDate }"/>
						<a class="inputDateButton" href="javascript:;">选择</a><span class="info"> (必填)</span>
					</td>
					<td><label>共同还款人：</label> 
						<input type="radio" id="togetherPerson" name="togetherPerson" value="否" checked="checked" <c:if test='${jksq.togetherPerson =="否"}'>checked="checked" </c:if>/>无&nbsp;
						<input type="radio" id="togetherPerson" name="togetherPerson" value="是" <c:if test='${jksq.togetherPerson =="是"}'>checked="checked" </c:if> />有&nbsp;
					</td>
				</tr>
				<tr>
					<td><label>是否加急：</label>
						<input type="radio" id="englishName" name="englishName" value="否" checked="checked"
						<c:if test='${jksq.englishName == "否" }'>checked="checked" </c:if>
						/>否&nbsp;
						<input type="radio" id="englishName" name="englishName" value="是" 
							<c:if test='${jksq.englishName == "是" }'>checked="checked" </c:if>
						/>是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>借款类型：</label>
						<input type="radio" id="jkType" name="jkType" value="A" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="A" }'>checked="checked" </c:if>/>老板借&nbsp;
						<input type="radio" id="jkType" name="jkType" value="B" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="B" }'>checked="checked" </c:if>/>老板楼易借&nbsp; 
						<input type="radio" id="jkType" name="jkType" value="C" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="C" }'>checked="checked" </c:if>/>薪水借&nbsp;
						<input type="radio" id="jkType" name="jkType" value="D" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="D" }'>checked="checked" </c:if>/>薪水楼易借&nbsp; 
						<input type="radio" id="jkType" name="jkType" value="E" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="E" }'>checked="checked" </c:if>/>精英借&nbsp;
						<input type="radio" id="jkType" name="jkType" value="Q" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="Q" }'>checked="checked" </c:if>/>企业借&nbsp;
						<input type="radio" id="jkType" name="jkType" value="W" onclick="jkTypeInput(this)" <c:if test='${jksq.jkType =="W" }'>checked="checked" </c:if>/>简易楼易借&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="lbd_div" name="lbd_div" style="display: none;">
							${lbd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="lblyd_div" name="lblyd_div" style="display: none;">
							${lblyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="xsd_div" name="xsd_div" style="display: none;">
							${xsd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="xslyd_div" name="xslyd_div" style="display: none;">
							${xslyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="jyd_div" name="jyd_div" style="display: none;">
							${jyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="qyd_div" name="qyd_div" style="display: none;">
							${qyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="jylyd_div" name="jylyd_div" style="display: none;">
							${jylyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="qyd_div" name="qyd_div" style="display: none;">
							${qyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="jylyd_div" name="jylyd_div" style="display: none;">
							${jylyd }
						</div>
					</td>
				</tr>
				</table>
	<div class="divider"></div>
	<table>
				<tr>
					<td colspan="2"><label>开户行：</label>
						<input type="text" id="bankOpen" name="bankOpen" class="required" size="80" value="${jksq.bankOpen }"/><span class="info"> (必填)</span>
					</td>
					
				</tr>
				<tr>
				<td><label>账户号码：</label> 
						<input type="text" id="bankNum" name="bankNum" class="required digits" size="30" value="${jksq.bankNum }"/><span class="info"> (必填)</span>
					</td>
				</tr>
				<tr>
					<td><label>账户名称：</label> 
						<input type="text" id="bankUsername" name="bankUsername" class="required" size="30" value="${jksq.bankUsername }"/><span class="info"> (必填)</span>
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
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
	function jkMaritalStatusInput(obj){
//		var maritalStatus = document.jksqbaseMsgForm.maritalStatus.value.Trim();
		var div = document.getElementById('isMarrayInput');
		var maritalStatus = obj.value;
		if("已婚" == maritalStatus){
//			document.jksqbaseMsgForm.spouseName.className = "required";
			document.jksqbaseMsgForm.spouseName.disabled = false;
			
			document.jksqbaseMsgForm.spouseGenders[0].disabled = false;
			document.jksqbaseMsgForm.spouseGenders[1].disabled = false;
			
//			document.jksqbaseMsgForm.spouseBirthday.className = "date required";
			document.jksqbaseMsgForm.spouseBirthday.disabled = false;
			document.getElementById('spouRiqiInput').disabled = false;

//			document.jksqbaseMsgForm.spouseAdress.className = "required";
			document.jksqbaseMsgForm.spouseAdress.disabled = false;
			
			document.jksqbaseMsgForm.spousePocertificates.disabled = false;

//			document.jksqbaseMsgForm.spouseZjhm.className = "required";
			document.jksqbaseMsgForm.spouseZjhm.disabled = false;

//			document.jksqbaseMsgForm.spouseTelephone.className = "required";
			document.jksqbaseMsgForm.spouseTelephone.disabled = false;

//			document.jksqbaseMsgForm.spouseHomePhone.className = "required";
			document.jksqbaseMsgForm.spouseHomePhone.disabled = false;

//			document.jksqbaseMsgForm.spouseCompany.className = "required";
			document.jksqbaseMsgForm.spouseCompany.disabled = false;

//			document.jksqbaseMsgForm.spouseDepFunction.className = "required";
			document.jksqbaseMsgForm.spouseDepFunction.disabled = false;

//			document.jksqbaseMsgForm.spouseCompanyPhone.className = "required";
			document.jksqbaseMsgForm.spouseCompanyPhone.disabled = false;

//			document.jksqbaseMsgForm.spouseCompanyAdress.className = "required";
			document.jksqbaseMsgForm.spouseCompanyAdress.disabled = false;

//			document.jksqbaseMsgForm.spouseAnnualIncome.className = "required";
			document.jksqbaseMsgForm.spouseAnnualIncome.disabled = false;

//			document.jksqbaseMsgForm.spouseWorkinglife.className = "required";
			document.jksqbaseMsgForm.spouseWorkinglife.disabled = false;

			div.style.display = "block";
//		}else if("未婚" == maritalStatus){
		}else {
//			document.jksqbaseMsgForm.spouseName.className = "";
			document.jksqbaseMsgForm.spouseName.disabled = true;
			
			document.jksqbaseMsgForm.spouseGenders[0].disabled = true;
			document.jksqbaseMsgForm.spouseGenders[1].disabled = true;
			
			document.jksqbaseMsgForm.spouseBirthday.size = "27";
//			document.jksqbaseMsgForm.spouseBirthday.className = "date";
			document.jksqbaseMsgForm.spouseBirthday.disabled = true;
			document.getElementById('spouRiqiInput').disabled = true;
			
//			document.jksqbaseMsgForm.spouseAdress.className = "";
			document.jksqbaseMsgForm.spouseAdress.disabled = true;
			//用dwz样式，这个就不好使了
			document.jksqbaseMsgForm.spousePocertificates.disabled = true;
			
//			document.jksqbaseMsgForm.spouseZjhm.className = "";
			document.jksqbaseMsgForm.spouseZjhm.disabled = true;
			
//			document.jksqbaseMsgForm.spouseTelephone.className = "";
			document.jksqbaseMsgForm.spouseTelephone.disabled = true;
			
//			document.jksqbaseMsgForm.spouseHomePhone.className = "";
			document.jksqbaseMsgForm.spouseHomePhone.disabled = true;
			
//			document.jksqbaseMsgForm.spouseCompany.className = "";
			document.jksqbaseMsgForm.spouseCompany.disabled = true;
			
//			document.jksqbaseMsgForm.spouseDepFunction.className = "";
			document.jksqbaseMsgForm.spouseDepFunction.disabled = true;
			
//			document.jksqbaseMsgForm.spouseCompanyPhone.className = "";
			document.jksqbaseMsgForm.spouseCompanyPhone.disabled = true;
			
//			document.jksqbaseMsgForm.spouseCompanyAdress.className = "";
			document.jksqbaseMsgForm.spouseCompanyAdress.disabled = true;
			
//			document.jksqbaseMsgForm.spouseAnnualIncome.className = "";
			document.jksqbaseMsgForm.spouseAnnualIncome.disabled = true;
			
//			document.jksqbaseMsgForm.spouseWorkinglife.className = "";
			document.jksqbaseMsgForm.spouseWorkinglife.disabled = true;
			
			div.style.display = "none";
		}
	}

	function jkTypeInput(obj){
		var state = obj.value;
		if("A" == state){
			document.getElementById('lbd_div').style.display = "block";//老板借
			document.getElementById('lblyd_div').style.display = "none";//老板楼易借
			document.getElementById('xsd_div').style.display = "none";//薪水借
			document.getElementById('xslyd_div').style.display = "none";//薪水楼易借
			document.getElementById('jyd_div').style.display = "none";//精英借
			document.getElementById('qyd_div').style.display = "none";//企业借
			document.getElementById('jylyd_div').style.display = "none";//简易楼易借
		}else if("B" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "block";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";//企业借
			document.getElementById('jylyd_div').style.display = "none";//简易楼易借
		}else if("C" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "block";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";//企业借
			document.getElementById('jylyd_div').style.display = "none";//简易楼易借
		}else if("D" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "block";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";//企业借
			document.getElementById('jylyd_div').style.display = "none";//简易楼易借
		}else if("E" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "block";
			document.getElementById('qyd_div').style.display = "none";//企业借
			document.getElementById('jylyd_div').style.display = "none";//简易楼易借
		}else if("Q" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "block";//企业借
			document.getElementById('jylyd_div').style.display = "none";//简易楼易借
		}else if("W" == state){
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";//企业借
			document.getElementById('jylyd_div').style.display = "block";//简易楼易借
		}
		
	}
	

	function livestate(state){
		if("liveMessage01" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = false;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage02" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = false;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage03" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = false;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage04" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = false;
		}
	}

	function ysubmit(val){
		document.jksqbaseMsgForm.opt.value = val;
		if(val == 0 || val == "0"){
//			document.jksqbaseMsgForm.className="pageForm";
		}else if(val == 1 || val == "1"){
//			document.jksqbaseMsgForm.className="pageForm required-validate";
		}
		return true;
	}
	
	
	function loantgFormSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}

		var jkType = document.jksqbaseMsgForm.jkType;
		var liveState = "";
		 for(var i=0;i<jkType.length;i++){
            if(jkType[i].checked){
           		liveState = jkType[i].value;
                break;
            }
        }

		if("" == liveState){
			alertMsg.warn('请选择借款类型，完善信息后重新提交！');
			return false;
		}else if("A" == liveState){
			var lbd_hyxz = document.jksqbaseMsgForm.lbd_hyxz.value.Trim();
			if("" == lbd_hyxz){//行业信息
				alertMsg.warn('请填写行业信息，完善信息后重新提交！');
//				alertMsg.warn('请完善借款类型信息，完善信息后重新提交！');
				return false;
			}
			var lbd_jyxm = document.jksqbaseMsgForm.lbd_jyxm.value.Trim();
			if("" == lbd_jyxm){//经营项目
				alertMsg.warn('请填写经营项目，完善信息后重新提交！');
				return false;
			}
			var lbd_clnx = document.jksqbaseMsgForm.lbd_clnx.value.Trim();
			if("" == lbd_clnx){//成立年限/时间
				alertMsg.warn('请填写成立年限/时间，完善信息后重新提交！');
				return false;
			}
			var lbd_yymj = document.jksqbaseMsgForm.lbd_yymj.value.Trim();
			if("" == lbd_yymj){//营业面积
				alertMsg.warn('请填写营业面积，完善信息后重新提交！');
				return false;
			}
			var lbd_ygsl = document.jksqbaseMsgForm.lbd_ygsl.value.Trim();
			if("" == lbd_ygsl){//员工数量
				alertMsg.warn('请填写员工数量，完善信息后重新提交！');
				return false;
			}
		}else if("B" == liveState){
			var lblyd_hyxz = document.jksqbaseMsgForm.lblyd_hyxz.value.Trim();
			if("" == lblyd_hyxz){//行业性质
				alertMsg.warn('请填写行业性质，完善信息后重新提交！');
				return false;
			}
			var lblyd_jyxm = document.jksqbaseMsgForm.lblyd_jyxm.value.Trim();
			if("" == lblyd_jyxm){//经营项目
				alertMsg.warn('请填写经营项目，完善信息后重新提交！');
				return false;
			}
			var lblyd_clnx = document.jksqbaseMsgForm.lblyd_clnx.value.Trim();
			if("" == lblyd_clnx){//成立年限/时间
				alertMsg.warn('请填写成立年限/时间，完善信息后重新提交！');
				return false;
			}
			var lblyd_yymj = document.jksqbaseMsgForm.lblyd_yymj.value.Trim();
			if("" == lblyd_yymj){//营业面积
				alertMsg.warn('请填写营业面积，完善信息后重新提交！');
				return false;
			}
			var lblyd_ygsl = document.jksqbaseMsgForm.lblyd_ygsl.value.Trim();
			if("" == lblyd_ygsl){//员工数量
				alertMsg.warn('请填写员工数量，完善信息后重新提交！');
				return false;
			}
			var lblyd_fcxz = document.jksqbaseMsgForm.lblyd_fcxz.value.Trim();
			if("" == lblyd_fcxz){//房产性质
				alertMsg.warn('请填写房产性质，完善信息后重新提交！');
				return false;
			}
			var lblyd_fcdz = document.jksqbaseMsgForm.lblyd_fcdz.value.Trim();
			if("" == lblyd_fcdz){//房产地址
				alertMsg.warn('请填写房产地址，完善信息后重新提交！');
				return false;
			}
			var lblyd_fcmj = document.jksqbaseMsgForm.lblyd_fcmj.value.Trim();
			if("" == lblyd_fcmj){//房产面积
				alertMsg.warn('请填写房产面积，完善信息后重新提交！');
				return false;
			}
			//是否抵押
//			var lblyd_sfdy = document.jksqbaseMsgForm.lblyd_sfdy;
//			var value = "";
//			 for(var i=0;i<lblyd_sfdy.length;i++){
//	            if(lblyd_sfdy[i].checked){
//	            	value = lblyd_sfdy[i].value;
//	                break;
//	            }
//	        }
//			if("" == value){//是否抵押
//				alertMsg.warn('请完善借款类型信息，完善信息后重新提交！');
//				return false;
//			}
			
		}else if("C" == liveState){
			var xsd_dwxz = document.jksqbaseMsgForm.xsd_dwxz.value.Trim();
			if("" == xsd_dwxz){//单位性质
				alertMsg.warn('请填写单位性质，完善信息后重新提交！');
				return false;
			}
			var xsd_zw = document.jksqbaseMsgForm.xsd_zw.value.Trim();
			if("" == xsd_zw){//职务
				alertMsg.warn('请填写职务，完善信息后重新提交！');
				return false;
			}
			var xsd_zgsj = document.jksqbaseMsgForm.xsd_zgsj.value.Trim();
			if("" == xsd_zgsj){//在岗时间
				alertMsg.warn('请填写在岗时间，完善信息后重新提交！');
				return false;
			}
			var xsd_ygznx = document.jksqbaseMsgForm.xsd_ygznx.value.Trim();
			if("" == xsd_ygznx){//已工作年限
				alertMsg.warn('请填写已工作年限，完善信息后重新提交！');
				return false;
			}
		}else if("D" == liveState){
			var xslyd_dwxz = document.jksqbaseMsgForm.xslyd_dwxz.value.Trim();
			if("" == xslyd_dwxz){//单位性质
				alertMsg.warn('请填写单位性质，完善信息后重新提交！');
				return false;
			}
			var xslyd_zw = document.jksqbaseMsgForm.xslyd_zw.value.Trim();
			if("" == xslyd_zw){//职务
				alertMsg.warn('请填写职务，完善信息后重新提交！');
				return false;
			}
			var xslyd_zgsj = document.jksqbaseMsgForm.xslyd_zgsj.value.Trim();
			if("" == xslyd_zgsj){//在岗时间
				alertMsg.warn('请填写在岗时间，完善信息后重新提交！');
				return false;
			}
			var xslyd_ygznx = document.jksqbaseMsgForm.xslyd_ygznx.value.Trim();
			if("" == xslyd_ygznx){//已工作年限
				alertMsg.warn('请填写已工作年限，完善信息后重新提交！');
				return false;
			}
			var xslyd_fcxz = document.jksqbaseMsgForm.xslyd_fcxz.value.Trim();
			if("" == xslyd_fcxz){//房产性质
				alertMsg.warn('请填写房产性质，完善信息后重新提交！');
				return false;
			}
			var xslyd_fcdz = document.jksqbaseMsgForm.xslyd_fcdz.value.Trim();
			if("" == xslyd_fcdz){//房产地址
				alertMsg.warn('请填写房产地址，完善信息后重新提交！');
				return false;
			}
			var xslyd_fcmj = document.jksqbaseMsgForm.xslyd_fcmj.value.Trim();
			if("" == xslyd_fcmj){//房产面积
				alertMsg.warn('请填写房产面积，完善信息后重新提交！');
				return false;
			}
			//是否抵押
//			var xslyd_sfdy = document.jksqbaseMsgForm.xslyd_sfdy;
//			var value = "";
//			 for(var i=0;i<xslyd_sfdy.length;i++){
//	            if(xslyd_sfdy[i].checked){
//	            	value = xslyd_sfdy[i].value;
//	                break;
//	            }
//	        }
//			if("" == value){//是否抵押
//				alertMsg.warn('请完善借款类型信息，完善信息后重新提交！');
//				return false;
//			}
		}else if("E" == liveState){
			var jyd_dwxz = document.jksqbaseMsgForm.jyd_dwxz.value.Trim();
			if("" == jyd_dwxz){//单位性质
				alertMsg.warn('请填写单位性质，完善信息后重新提交！');
				return false;
			}
			var jyd_zw = document.jksqbaseMsgForm.jyd_zw.value.Trim();
			if("" == jyd_zw){//职务
				alertMsg.warn('请填写职务，完善信息后重新提交！');
				return false;
			}
			var jyd_zgsj = document.jksqbaseMsgForm.jyd_zgsj.value.Trim();
			if("" == jyd_zgsj){//在岗时间
				alertMsg.warn('请填写在岗时间，完善信息后重新提交！');
				return false;
			}
			var jyd_ygznx = document.jksqbaseMsgForm.jyd_ygznx.value.Trim();
			if("" == jyd_ygznx){//已工作年限
				alertMsg.warn('请填写已服务年限，完善信息后重新提交！');
				return false;
			}
		}
		return validateCallback(obj, navTabAjaxDone);
	}

	//居住状态
	function initLoanBaseXhcfDkrxx(){
		var liveStateArray = document.jksqbaseMsgForm.liveState;
		var liveState = "";
		 for(var i=0;i<liveStateArray.length;i++){
             if(liveStateArray[i].checked){
            	 liveState = liveStateArray[i].value;
                 break;
             }
         }

		if("01" == liveState){
			document.jksqbaseMsgForm.liveMessage01.value = "${xhcfDkrxx.liveMessage }";
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("02" == liveState){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.value = "${xhcfDkrxx.liveMessage }";
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("03" == liveState){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.value = "${xhcfDkrxx.liveMessage }";
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("04" == liveState){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.value = "${xhcfDkrxx.liveMessage }";
		}
	}
	
	
	
	function initlivestateChangeInput(){
		var liveState = "${jksq.liveState }";
		if("01" == liveState){
			document.jksqbaseMsgForm.liveMessage01.value = "${jksq.liveMessage }";
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("02" == liveState){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.value = "${jksq.liveMessage }";
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("03" == liveState){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.value = "${jksq.liveMessage }";
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("04" == liveState){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.value = "${jksq.liveMessage }";
		}
	}
	
	function livestateChangeInput(state){
		if("liveMessage01" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = false;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage02" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = false;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage03" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = false;
			document.jksqbaseMsgForm.liveMessage04.disabled = true;
		}else if("liveMessage04" == state){
			document.jksqbaseMsgForm.liveMessage01.disabled = true;
			document.jksqbaseMsgForm.liveMessage02.disabled = true;
			document.jksqbaseMsgForm.liveMessage03.disabled = true;
			document.jksqbaseMsgForm.liveMessage04.disabled = false;
		}
	}
	
	function jkTypeChangeInput(obj){
		var state = obj.value;
		jkTypeInput(state);
	}
	
	function jkTypeChangeInputDiv(jkType){
		if("A" == jkType){//老板借
			document.getElementById('lbd_div').style.display = "block";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";
			document.getElementById('jylyd_div').style.display = "none";
		}else if("B" == jkType){//老板楼易借
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "block";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";
			document.getElementById('jylyd_div').style.display = "none";
		}else if("C" == jkType){//薪水借
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "block";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";
			document.getElementById('jylyd_div').style.display = "none";
		}else if("D" == jkType){//薪水楼易借
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "block";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";
			document.getElementById('jylyd_div').style.display = "none";
		}else if("E" == jkType){//精英借
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "block";
			document.getElementById('qyd_div').style.display = "none";
			document.getElementById('jylyd_div').style.display = "none";
		}else if("Q" == jkType){//企业借
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "block";
			document.getElementById('jylyd_div').style.display = "none";
		}else if("W" == jkType){//简易楼易借
			document.getElementById('lbd_div').style.display = "none";
			document.getElementById('lblyd_div').style.display = "none";
			document.getElementById('xsd_div').style.display = "none";
			document.getElementById('xslyd_div').style.display = "none";
			document.getElementById('jyd_div').style.display = "none";
			document.getElementById('qyd_div').style.display = "none";
			document.getElementById('jylyd_div').style.display = "block";
		}
	}
	
	function initJkTypeChangeInput(){
		var state = "${jksq.jkType }";
		jkTypeChangeInputDiv(state);
	}
	
	function initJkMaritalStatusInput(){
		var jkMaritalStatus = document.jksqbaseMsgForm.maritalStatus;
		jkMaritalStatusInput(jkMaritalStatus);
	}
	
	function initjksqlIndex(){
		initLoanBaseXhcfDkrxx();
		initJkMaritalStatusInput();
		initlivestateChangeInput();
		initJkTypeChangeInput();
	}
	initjksqlIndex();
	
</script>