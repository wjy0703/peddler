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
				<form id="jksqComplementMsgForm" name="jksqComplementMsgForm" method="post"
					action="${ctx}/jksq/complementSaveJksqMsg" class="pageForm required-validate"
					onsubmit="return jksqComplementMsgFormSubmit(this);">
					<div class="pageFormContent" layoutH="56">
						<input type="hidden" id="xydkzxId" name="xydkzxId" value="${xydkzx.id }" />
						<input type="hidden" id="opt" name="opt" />
						<div class="panel"><h1>客户基本信息</h1></div>
						<table nowrapTD="false">
							<tr>
								<td><label>借款人姓名:</label> 
									<input type="text" id="jkrxm" name="jkrxm" size="30" value="${jksq.jkrxm }" class="" readonly="readonly">
									<input type="hidden" id="id" name="id" size="30" value="${jksq.id }" />
								</td>
								<td><label>性别：</label> 
									<input type="radio" id="genders" name="genders" size="30" value="${jksq.genders }"
										 readonly="readonly" checked="checked"/>${jksq.genders }
								</td>
								<td><label>出生日期：</label> 
									<input type="text" name="birthday" 	size="30" readonly="readonly" value="${jksq.birthday }" />
								</td>
							</tr>
							<tr>
								<td><label>证件类型：</label> 
									<input type="text" name="pocertificates" 	size="30" readonly="readonly" value="${jksq.pocertificates }" />
								</td>
								<td><label>证件号码：</label> 
									<input type="text" id="zjhm" name="zjhm" size="30" readonly="readonly" class="" value="${jksq.zjhm }" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>户籍地址：</label> 
									<input type="text" id="hjadress" name="hjadress" size="60" class="" value="${jksq.hjadress }" ${jksqcomp.hjadress }/>
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>现地址:</label> 
									<input type="text" id="homeAddress" name="homeAddress" size="60" 
										value="${jksq.homeAddress }" ${jksqcomp.homeAddress }  />
								</td>
							</tr>
						</table>
							<div class="divider"></div>
						<table nowrapTD="false" width="100%">
							<tr>
								<td><label>家庭电话：</label> 
									<input type="text" id="homePhone" name="homePhone" size="30" class="" value="${jksq.homePhone }" ${jksqcomp.homePhone } />
								</td>
								<td><label>工作单位：</label> 
									<input type="text" id="company" name="company" size="30" class="" value="${jksq.company }" ${jksqcomp.company } />
								</td>
							</tr>
							<tr>
								<td><label>单位电话：</label> 
									<input type="text" id="companyPhone" name="companyPhone" size="30" class="" value="${jksq.companyPhone }" ${jksqcomp.companyPhone } />
								</td>
								<td><label>单位地址：</label> 
									<input type="text" id="companyAdress" name="companyAdress" size="30" class="" value="${jksq.companyAdress }" ${jksqcomp.companyAdress } />
								</td>
								<td><label>单位性质：</label> 
									<input type="text" id="companyNature" name="companyNature" size="20" readonly="readonly" class="" value="${jksq.companyNature }" />
								</td>
							</tr>
							<tr>
								<td><label>移动电话：</label> 
									<input type="text" id="telephone" name="telephone" size="30" class="" value="${jksq.telephone }" ${jksqcomp.telephone } />
								</td>
								<td><label>电子邮箱：</label> 
									<input type="text" id="email" name="email" size="30" class="email" value="${jksq.email }" ${jksqcomp.email } />
								</td>
								<td><label>婚姻状况：</label> 
									<input type="text" id="maritalStatus" name="maritalStatus" size="20" readonly="readonly" class="" value="${jksq.maritalStatus }" />
								</td>
							</tr>
							<tr>
								<td><label>有无子女：</label> 
									<input type="text" id="ywzn" name="ywzn" size="30" readonly="readonly" class="" value="${jksq.ywzn }" />
								</td>
								<td><label>QQ号码：</label> 
									<input type="text" id="qqhm" name="qqhm" size="30" class="" value="${jksq.qqhm }" ${jksqcomp.qqhm } />
								</td>
								<td><label>月收入(元)：</label> 
									<input type="text" id="annualIncome" name="annualIncome" 
										size="10" class="" value="${jksq.annualIncome }" ${jksqcomp.annualIncome } /><span class="info"> (数字格式,无千分位)</span>
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>收入说明：</label> 
									<input type="text" id="incomeIllustration" name="incomeIllustration" size="60" class="" value="${jksq.incomeIllustration }" ${jksqcomp.incomeIllustration } />
								</td>
							</tr>
						</table>
							<div class="divider"></div>
						<table >
							<tr>
								<td colspan="1"><label>居住状态：</label> 
									<c:if test='${jksq.liveState == "01" || jksq.liveState == "02" || jksq.liveState == "03" || jksq.liveState == "04"}'>
										<input type="radio" id="liveState" name="liveState" value="${jksq.liveState }" checked="checked"/>
									</c:if>
									<c:if test='${jksq.liveState == "01" }'> 自有房屋，有无借款，月供 </c:if>
									<c:if test='${jksq.liveState == "02" }'> 亲属产权 </c:if>
									<c:if test='${jksq.liveState == "03" }'> 租房，房租 </c:if>
									<c:if test='${jksq.liveState == "04" }'> 租房，房租 </c:if>
								</td>
								<td>
									<c:if test='${jksq.liveState == "01" }'> 
										<input id="liveMessage01" name="liveMessage" type="text" value="${jksq.liveMessage }" ${jksqcomp.liveMessage }/>元
									</c:if>
									<c:if test='${jksq.liveState == "02" }'>  </c:if>
									<c:if test='${jksq.liveState == "03" }'> 
										<input type="text" id="liveMessage03" name="liveMessage" value="${jksq.liveMessage }" ${jksqcomp.liveMessage }/>元/月
									</c:if>
									<c:if test='${jksq.liveState == "04" }'> 
										<input type="text" id="liveMessage04" name="liveMessage" size="80" value="${jksq.liveMessage }" ${jksqcomp.liveMessage }/>
									</c:if>
								</td>
							</tr>
							<tr>
								<td colspan="1"><label>通讯地址：</label> 
									<input type="hidden" id="province.id" name="province.id" value="${jksq.province.id}" size="10" />
									<input type="hidden" id="city.id" name="city.id" value="${jksq.city.id}" size="10" />
									<input type="hidden" id="area.id" name="area.id" value="${jksq.area.id}" size="10" />
									<c:forEach items="${province}" var="md" varStatus="st">
										<c:if test="${jksq.province.id==md.id}">${md.name }</c:if>
									</c:forEach>
									<c:forEach items="${city}" var="md" varStatus="st">
										<c:if test="${jksq.city.id==md.id}">${md.name }</c:if>
									</c:forEach>
									<c:forEach items="${area}" var="md" varStatus="st">
										<c:if test="${jksq.area.id==md.id}">${md.name }</c:if>
									</c:forEach>
								</td>
								<td>
									<input type="text" name="txdz" size="30" value="${jksq.txdz }" maxlength="100" ${jksqcomp.txdz }/>
								</td>
							</tr>
						</table>
						<br />
						<div id="isMarrayComplement" style="display: none;">
						<div class="panel"><h1>以下为配偶信息（借款人婚姻状况为已婚时必填项）</h1></div>
							<table>
								<tr>
									<td><label>姓名:</label> 
										<input type="text" id="spouseName" name="spouseName" size="30" value="${jksq.spouseName }" ${jksqcomp.spouseName }/>
									</td>
									<td><label>性别：</label> 
										<input type="radio" id="spouseGenders" name=spouseGenders size="30" value="${jksq.spouseGenders }" ${jksqcomp.spouseGenders } checked="checked"/>${jksq.spouseGenders }
									</td>
									<td><label>出生日期：</label> 
										<input type="text" name="spouseBirthday" 
											<c:if test='${jksq.spouseBirthday == null }'>class="date" format="yyyy-MM-dd" </c:if>
											size="30" readonly="readonly" value="${jksq.spouseBirthday }" />
									</td>
								</tr>
								<tr>
									<td><label>现住址：</label> 
										<input type="text" id="spouseAdress" name="spouseAdress" size="30" value="${jksq.spouseAdress }" ${jksqcomp.spouseAdress }/>
									</td>
									<td><label>证件类型：</label> 
										<input type="text" id="spousePocertificates" name="spousePocertificates" size="30" value="${jksq.spousePocertificates }" ${jksqcomp.spousePocertificates }/>
									</td>
									<td><label>证件号码：</label> 
										<input type="text" id="spouseZjhm" name="spouseZjhm" size="30" value="${jksq.spouseZjhm }" ${jksqcomp.spouseZjhm }/>
									</td>
								</tr>
								<tr>
									<td><label>移动电话：</label> 
										<input type="text" id="spouseTelephone" name="spouseTelephone" size="30" value="${jksq.spouseTelephone }" ${jksqcomp.spouseTelephone }/>
									</td>
									<td><label>家庭电话：</label> 
										<input type="text" id="spouseHomePhone" name="spouseHomePhone" size="30" value="${jksq.spouseHomePhone }" ${jksqcomp.spouseHomePhone }/>
									</td>
									<td><label>工作单位：</label> 
										<input type="text" id="spouseCompany" name="spouseCompany" size="30" value="${jksq.spouseCompany }" ${jksqcomp.spouseCompany }/>
									</td>
								</tr>
								<tr>
									<td><label>部门与职务：</label> 
										<input type="text" id="spouseDepFunction" name="spouseDepFunction" size="30" value="${jksq.spouseDepFunction }" ${jksqcomp.spouseDepFunction }/>
									</td>
									<td><label>单位电话：</label> 
										<input type="text" id="spouseCompanyPhone" name="spouseCompanyPhone" size="30" value="${jksq.spouseCompanyPhone }" ${jksqcomp.spouseCompanyPhone }/>
									</td>
									<td><label>单位地址：</label> 
										<input type="text" id="spouseCompanyAdress" name="spouseCompanyAdress" size="30" value="${jksq.spouseCompanyAdress }" ${jksqcomp.spouseCompanyAdress }/>
									</td>
								</tr>
			
								<tr>
									<td><label>月收入(元)：</label> 
										<input type="text" id="spouseAnnualIncome" name="spouseAnnualIncome" size="10" 
											class="number" value="${jksq.spouseAnnualIncome }" 
											${jksqcomp.spouseAnnualIncome }/><span class="info"> (数字格式,无千分位)</span>
									</td>
									<td><label>服务年限(年)：</label> 
										<input type="text" id="spouseWorkinglife" name="spouseWorkinglife" size="10" 
											value="${jksq.spouseWorkinglife }" ${jksqcomp.spouseWorkinglife } class="digits"
											minlength="1" maxlength="3" /><span class="info"> (数字格式)</span>
									</td>
								</tr>
			
			
							</table>
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
								<tr>
									<td align="center">${xhjkjjlxr1.ybrgx }
										<input type="hidden" name="id1" value="${xhjkjjlxr1.id }" />
										<input type="hidden" name="ybrgx1" value="${xhjkjjlxr1.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name1" value="${xhjkjjlxr1.name }" ${jksqcomp.xhjkjjlxr1.name } /></td>
									<td align="left"><input type="text" name="jjlxrgzdw1" value="${xhjkjjlxr1.jjlxrgzdw }" ${jksqcomp.xhjkjjlxr1.jjlxrgzdw } /></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz1" value="${xhjkjjlxr1.jjlxrdwdzhjtzz }" ${jksqcomp.xhjkjjlxr1.jjlxrdwdzhjtzz } /></td>
									<td align="left"><input type="text" name="jjlxrlxdh1" value="${xhjkjjlxr1.jjlxrlxdh }" ${jksqcomp.xhjkjjlxr1.jjlxrlxdh } class="digits"/></td>
								</tr>
								<tr>
									<td align="center">${xhjkjjlxr2.ybrgx }
										<input type="hidden" name="id2" value="${xhjkjjlxr2.id }" />
										<input type="hidden" name="ybrgx2" value="${xhjkjjlxr2.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name2" value="${xhjkjjlxr2.name }" ${jksqcomp.xhjkjjlxr2.name } /></td>
									<td align="left"><input type="text" name="jjlxrgzdw2" value="${xhjkjjlxr2.jjlxrgzdw }" ${jksqcomp.xhjkjjlxr2.jjlxrgzdw } /></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz2" value="${xhjkjjlxr2.jjlxrdwdzhjtzz }" ${jksqcomp.xhjkjjlxr2.jjlxrdwdzhjtzz } /></td>
									<td align="left"><input type="text" name="jjlxrlxdh2" value="${xhjkjjlxr2.jjlxrlxdh }" ${jksqcomp.xhjkjjlxr2.jjlxrlxdh } class="digits"/></td>
								</tr>
								<tr>
									<td align="center">${xhjkjjlxr3.ybrgx }
										<input type="hidden" name="id3" value="${xhjkjjlxr3.id }" />
										<input type="hidden" name="ybrgx3" value="${xhjkjjlxr3.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name3" value="${xhjkjjlxr3.name }" ${jksqcomp.xhjkjjlxr3.name } /></td>
									<td align="left"><input type="text" name="jjlxrgzdw3" value="${xhjkjjlxr3.jjlxrgzdw }" ${jksqcomp.xhjkjjlxr3.jjlxrgzdw } /></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz3" value="${xhjkjjlxr3.jjlxrdwdzhjtzz }" ${jksqcomp.xhjkjjlxr3.jjlxrdwdzhjtzz } /></td>
									<td align="left"><input type="text" name="jjlxrlxdh3" value="${xhjkjjlxr3.jjlxrlxdh }" ${jksqcomp.xhjkjjlxr3.jjlxrlxdh } class="digits"/></td>
								</tr>
								<tr>
									<td align="center">${xhjkjjlxr4.ybrgx }
										<input type="hidden" name="id4" value="${xhjkjjlxr4.id }" />
										<input type="hidden" name="ybrgx4" value="${xhjkjjlxr4.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name4" value="${xhjkjjlxr4.name }" ${jksqcomp.xhjkjjlxr4.name } /></td>
									<td align="left"><input type="text" name="jjlxrgzdw4" value="${xhjkjjlxr4.jjlxrgzdw }" ${jksqcomp.xhjkjjlxr4.jjlxrgzdw } /></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz4" value="${xhjkjjlxr4.jjlxrdwdzhjtzz }" ${jksqcomp.xhjkjjlxr4.jjlxrdwdzhjtzz } /></td>
									<td align="left"><input type="text" name="jjlxrlxdh4" value="${xhjkjjlxr4.jjlxrlxdh }" ${jksqcomp.xhjkjjlxr4.jjlxrlxdh } class="digits"/></td>
								</tr>
								<tr>
									<td align="center">${xhjkjjlxr5.ybrgx }
										<input type="hidden" name="id5" value="${xhjkjjlxr5.id }" />
										<input type="hidden" name="ybrgx5" value="${xhjkjjlxr5.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name5" value="${xhjkjjlxr5.name }" ${jksqcomp.xhjkjjlxr5.name } /></td>
									<td align="left"><input type="text" name="jjlxrgzdw5" value="${xhjkjjlxr5.jjlxrgzdw }" ${jksqcomp.xhjkjjlxr5.jjlxrgzdw } /></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz5" value="${xhjkjjlxr5.jjlxrdwdzhjtzz }" ${jksqcomp.xhjkjjlxr5.jjlxrdwdzhjtzz } /></td>
									<td align="left"><input type="text" name="jjlxrlxdh5" value="${xhjkjjlxr5.jjlxrlxdh }" ${jksqcomp.xhjkjjlxr5.jjlxrlxdh } class="digits"/></td>
								</tr>
								<tr>
									<td align="center">${xhjkjjlxr6.ybrgx }
										<input type="hidden" name="id6" value="${xhjkjjlxr6.id }" />
										<input type="hidden" name="ybrgx6" value="${xhjkjjlxr6.ybrgx }" />
									</td>
									<td align="left"><input type="text" name="name6" value="${xhjkjjlxr6.name }" ${jksqcomp.xhjkjjlxr6.name } /></td>
									<td align="left"><input type="text" name="jjlxrgzdw6" value="${xhjkjjlxr6.jjlxrgzdw }" ${jksqcomp.xhjkjjlxr6.jjlxrgzdw } /></td>
									<td align="left"><input type="text" name="jjlxrdwdzhjtzz6" value="${xhjkjjlxr6.jjlxrdwdzhjtzz }" ${jksqcomp.xhjkjjlxr6.jjlxrdwdzhjtzz } /></td>
									<td align="left"><input type="text" name="jjlxrlxdh6" value="${xhjkjjlxr6.jjlxrlxdh }" ${jksqcomp.xhjkjjlxr6.jjlxrlxdh } class="digits"/></td>
								</tr>
							</tbody>
						</table>
							
							<div class="panel"><h1>借款信息</h1></div>

						<table border="0" bordercolor="red">
							<tr>
								<td><label>借款申请额度:</label> 
									<input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" class="" value="${jksq.jkLoanQuota }" ${jksqcomp.jkLoanQuota } />
								</td>
								<td><label>申请借款期限:</label> 
									<input type="text" id="jkCycle" name="jkCycle" size="30" value="${jksq.jkCycle }" ${jksqcomp.jkCycle } />
								</td>
								<td><label>还款方式：</label> 
									<c:forEach items="${hkWay0019}" var="per">
										<c:if test='${jksq.hkWay ==per.value }'>
											<input type="text" size="30" value="${per.name }" ${jksqcomp.hkWay } />
										</c:if>
									</c:forEach>
									<input type="hidden" id="hkWay" name="hkWay" size="30" value="${jksq.hkWay }"  />
								</td>
							</tr>
							<tr>
								<td colspan="1">
									<dl >
										<dt>借款用途：</dt>
										<dd>
											<input type="text" id="jkUse" name="jkUse" value="${jksq.jkUse }" ${jksqcomp.jkUse }/>
											<!-- 
											<textarea id="jkUse" name="jkUse" style="width: 93%;" ${jksqcomp.jkUse } >${jksq.jkUse }</textarea>
											 -->
										</dd>
									</dl>
								</td>
								<td><label>共同还款人：</label> 
									<input type="text" id="togetherPerson" name="togetherPerson" value="${jksq.togetherPerson }" ${jksqcomp.togetherPerson }/>
								</td>
								<td><label>申请日期：</label> 
									<input type="text" id="jkLoanDate" name="jkLoanDate" 
										size="30" readonly="readonly" value="${jksq.jkLoanDate }" />
								</td>
							</tr>
							<tr>
								<td><label>是否加急：</label>
									<input type="radio" id="englishName" name="englishName" 
										value="${jksq.englishName }" checked="checked"
									/>${jksq.englishName }&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>借款类型：</label> 
									<c:if test='${jksq.jkType =="A" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="A" onclick="jkUsestate(this)"  checked="checked" />老板借&nbsp;
									</c:if>
									<c:if test='${jksq.jkType =="B" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="B" onclick="jkUsestate(this)" checked="checked" />老板楼易借&nbsp; 
									</c:if>
									<c:if test='${jksq.jkType =="C" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="C" onclick="jkUsestate(this)" checked="checked" />薪水借&nbsp;
									</c:if>
									<c:if test='${jksq.jkType =="D" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="D" onclick="jkUsestate(this)" checked="checked" />薪水楼易借&nbsp; 
									</c:if>
									<c:if test='${jksq.jkType =="E" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="E" onclick="jkUsestate(this)" checked="checked" />精英借&nbsp;
									</c:if>
									<c:if test='${jksq.jkType =="Q" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="Q" onclick="jkUsestate(this)" checked="checked" />企业借&nbsp;
									</c:if>
									<c:if test='${jksq.jkType =="W" }'>
										<input type="radio" id="jkType" name="jkType" 
											value="W" onclick="jkUsestate(this)" checked="checked" />简易楼易借&nbsp;
									</c:if>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="lbd_div_complement" name="lbd_div_complement" style="display: none;">
										${lbd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="lblyd_div_complement" name="lblyd_div_complement" style="display: none;">
										${lblyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="xsd_div_complement" name="xsd_div_complement" style="display: none;">
										${xsd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="xslyd_div_complement" name="xslyd_div_complement" style="display: none;">
										${xslyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="jyd_div_complement" name="jyd_div_complement" style="display: none;">
										${jyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="qyd_div_complement" name="qyd_div_complement" style="display: none;">
										${qyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div id="jylyd_div_complement" name="jylyd_div_complement" style="display: none;">
										${jylyd }
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>开户行：</label> 
									<input type="text" id="bankOpen" name="bankOpen" size="80" 
										value="${jksq.bankOpen }" ${jksqcomp.bankOpen }/>
								</td>
							</tr>
							<tr>
								<td><label>账户号码：</label> 
									<input type="text" id="bankNum" name="bankNum" size="30"
										value="${jksq.bankNum }" ${jksqcomp.bankNum } class="digits"/>
								</td>
							</tr>
							<tr>
								<td><label>账户名称：</label> 
									<input type="text" id="bankUsername" name="bankUsername" size="30" 
										value="${jksq.bankUsername }" ${jksqcomp.bankUsername }/>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<dl class="nowrap">
										<dt>备注：</dt>
										<dd>
											<textarea id="backup09" name="backup09" rows="4" 
												style="width: 93%;" ${jksqcomp.backup09 }>${jksq.backup09 }</textarea>
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
						
						<div class="formBar">
							<ul>
								<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" >提交补充信息</button>
									</div>
								</div></li>
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
				<form id="jksqTogetherComplementForm" name="jksqTogetherComplementForm" method="post"
					action="${ctx}/jksq/complementSaveTogetherMsg" class="pageForm required-validate"
					onsubmit="return jksqTogetherComplementSubmit(this);">
						<div class="pageFormContent" layoutH="56">
							<input type="hidden" id="jksqId" name="jksqId" value="${jksq.id }" title="jksqId"/>
							<input type="hidden" id="id" name="id" value="${xhJksqTogether.id }"/>
							<input type="hidden" id="opt" name="opt" /> <h1>共同借款人填写</h1>
							<div class="divider"></div>
							<table nowrapTD="false">
								<tr>
									<td><label>共同借款人姓名:</label> 
										<input type="text" id="togetherName" name="togetherName" size="30" class="" 
											value="${xhJksqTogether.togetherName }" ${jksqTogetherComp.togetherName }/>
									</td>
									<td><label>年龄:</label> 
										<input type="text" id="age" name="age" size="30" 
											value="${xhJksqTogether.age }" ${jksqTogetherComp.age }/>
									</td>
									<td><label>性别：</label> 
										<input type="radio" id="genders" name="genders" size="30" value="${xhJksqTogether.genders }"
										 readonly="readonly" checked="checked"/>${xhJksqTogether.genders }
									</td>
								</tr>
								<tr>
									<td><label>身份证号码：</label> 
										<input type="text" id="identification" name="identification" size="30" class="" 
											value="${xhJksqTogether.identification }" ${jksqTogetherComp.identification }/>
									</td>
									<td><label>户籍地址：</label> 
										<input type="text" id="hjadress" name="hjadress" size="30" class="" 
											value="${xhJksqTogether.hjadress }" ${jksqTogetherComp.hjadress }/>
									</td>
									<td><label>家庭电话：</label> 
										<input type="text" id="homePhone" name="homePhone" size="30" class="" 
											value="${xhJksqTogether.homePhone }" ${jksqTogetherComp.homePhone }/>
									</td>
								</tr>
								<tr>
									<td><label>手机：</label> 
										<input type="text" id="telephone" name="telephone" size="30" class="number" 
											value="${xhJksqTogether.telephone }" ${jksqTogetherComp.telephone }/>
									</td>
									<td><label>现住址：</label> 
										<input type="text" id="address" name="address" size="30" class="" 
											value="${xhJksqTogether.address }" ${jksqTogetherComp.address }/>
									</td>
									<td><label>住址现电话：</label> 
										<input type="text" id="addressPhone" name="addressPhone" size="30" class="" 
											value="${xhJksqTogether.addressPhone }" ${jksqTogetherComp.addressPhone }/>
									</td>
								</tr>
								<tr>
									<td><label>邮箱：</label> 
										<input type="text" id="email" name="email" size="30" class="email" 
											value="${xhJksqTogether.email }" ${jksqTogetherComp.email }/>
									</td>
									<td><label>工作单位：</label> 
										<input type="text" id="company" name="company" size="30" class="" 
											value="${xhJksqTogether.company }" ${jksqTogetherComp.company }/>
									</td>
									<td><label>单位电话：</label> 
										<input type="text" id="companyPhone" name="companyPhone" size="30" class="" 
											value="${xhJksqTogether.companyPhone }" ${jksqTogetherComp.companyPhone }/>
									</td>
								</tr>
								<tr>
									<td><label>QQ号码：</label> 
										<input type="text" id="qqhm" name="qqhm" size="30" class="number" 
											value="${xhJksqTogether.qqhm }" ${jksqTogetherComp.qqhm }/>
									</td>
									<td><label>单位地址：</label> 
										<input type="text" id="companyAdress" name="companyAdress" size="30" class="" 
											value="${xhJksqTogether.companyAdress }" ${jksqTogetherComp.companyAdress }/>
									</td>
									<td><label>部门名称：</label> 
										<input type="text" id="department" name="department" size="30" class="" 
											value="${xhJksqTogether.department }" ${jksqTogetherComp.department }/>
									</td>
								</tr>
								<tr>
									<td><label>职务：</label> 
										<input type="text" id="function" name="function" size="30" class="" 
											value="${xhJksqTogether.function }" ${jksqTogetherComp.function }/>
									</td>
									<td><label>婚姻状况：</label> 
										<input type="text" id="maritalStatus" name="maritalStatus" size="30" 
											readonly="readonly" class="" value="${xhJksqTogether.maritalStatus }" />
										<!-- 
										<select id="maritalStatus" name="maritalStatus">
											<c:forEach items="${hyzk0009}" var="per">
												<option value="${per.value }">${per.name }</option>
											</c:forEach>
										</select>
										 -->
									</td>
									<td><label>有无子女：</label> 
										<input type="text" id="ywzn" name="ywzn" size="30" class="" 
											value="${xhJksqTogether.ywzn }" ${jksqTogetherComp.ywzn }/>
									</td>
								</tr>
								
								<c:if test='${xhJksqTogether.liveState =="01" && xhJksqTogether.liveState =="02" && xhJksqTogether.liveState =="03"}'>
								<tr>
									<td colspan="2"><label>居住状况：</label> 
										<c:if test='${xhJksqTogether.liveState =="01" }'> 
											<input type="radio" id="liveStateTogether" name="liveState" value="01" checked="checked"
												 onclick="liveStateTogetherComplement('liveMessageComplement01')" />自购房屋&nbsp;&nbsp;
										</c:if>
										<c:if test='${xhJksqTogether.liveState =="02" }'>
											<input type="radio" id="liveStateTogether" name="liveState" value="02" checked="checked" 
												onclick="liveStateTogetherComplement('liveMessageComplement02')" />借款购置房屋&nbsp;&nbsp;
										</c:if>
										<c:if test='${xhJksqTogether.liveState =="03" }'>
											<input type="radio" id="liveStateTogether" name="liveState" value="03" checked="checked" 
												onclick="liveStateTogetherComplement('liveMessageComplement03')" />借款购置房屋
										</c:if>
									</td>
								</tr>
								</c:if>
								
								<c:if test='${xhJksqTogether.liveState =="04" }'>
								<tr>
									<td colspan="1"><label>居住状况：</label> 
										<input type="radio" id="liveStateTogether" name="liveState" value="04"
											<c:if test='${xhJksqTogether.liveState =="04" }'>checked="checked" </c:if>
											onclick="liveStateTogetherComplement('liveMessageComplement04')" />租房，房租</td>
									<td>
										<input type="text" id="liveMessageComplement04" name="liveMessage" 
											value="${xhJksqTogether.liveMessage }" ${jksqTogetherComp.liveMessage }
										 />元/月
									</td>
								</tr>
								</c:if>
								
								<c:if test='${xhJksqTogether.liveState =="05" }'>
								<tr>
									<td colspan="1"><label>居住状况：</label> 
										<input type="radio" id="liveStateTogether" name="liveState" value="05"
											<c:if test='${xhJksqTogether.liveState =="05" }'>checked="checked" </c:if>
											onclick="liveStateTogetherComplement('liveMessageComplement05')" />其他，说明：
										</td>
									<td>
										<input type="text" id="liveMessageComplement05" name="liveMessage" 
											size="80" value="${xhJksqTogether.liveMessage }" ${jksqTogetherComp.liveMessage }
										/>
									</td>
								</tr>
								</c:if>
								
								<tr>
									<td colspan="1"><label>主要收入来源：</label> 
										<c:if test='${cBoxMonthlySalaryComplement == false }'>  
											<input type="checkbox" id="cBoxMonthlySalaryComplement" 
												name="cBoxMonthlySalary" value="01" 
												 onclick="selectCompOne(this,'monthlySalaryCompIndex')" />
										</c:if>
											 每月工资(含奖金及补助)
									</td>
									<td>
										<input type="text" id="monthlySalaryCompIndex" name="monthlySalary" 
											value="${xhJksqTogether.monthlySalary }" ${jksqTogetherComp.monthlySalary } 
											<c:if test='${cBoxMonthlySalaryComplement == false }'> disabled="disabled" </c:if>
										/>元/月
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<c:if test='${cBoxRentalComplement == false }'>
											<input type="checkbox" id="cBoxRentalComplement" name="cBoxRental" value="01" 
												onclick="selectCompOne(this,'rentalCompIndex')" />
										</c:if>
										房屋出租
									</td>
									<td>
										<input type="text" id="rentalCompIndex" name="rental" 
											value="${xhJksqTogether.rental }" ${jksqTogetherComp.rental }
											<c:if test='${cBoxRentalComplement == false }'> disabled="disabled" </c:if>
										/>元/月
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<c:if test='${cBoxOtherIncomeComplement == false }'>
											<input type="checkbox" id="cBoxOtherIncomeComplement" 
												name="cBoxOtherIncome" value="01" 
												onclick="selectCompOne(this,'otherIncomeCompIndex')" />
										</c:if>
										其他所得
									</td>
									<td>
										<input type="text" id="otherIncomeCompIndex" name="otherIncome" 
											value="${xhJksqTogether.otherIncome }" ${jksqTogetherComp.otherIncome }
											<c:if test='${cBoxOtherIncomeComplement == false }'> disabled="disabled" </c:if>
										/>元/年
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label> 
										<c:if test='${cBoxAnnualIncomeComplement == false }'>
											<input type="checkbox" id="cBoxAnnualIncomeComplement" 
												name="cBoxAnnualIncome" value="01"
												onclick="selectCompOne(this,'annualIncomeCompIndex')" />
										</c:if>
										年总收入</td>
									<td>
										<input type="text" id="annualIncomeCompIndex" name="annualIncome" 
											value="${xhJksqTogether.annualIncome }" ${jksqTogetherComp.annualIncome }
											<c:if test='${cBoxAnnualIncomeComplement == false }'> disabled="disabled" </c:if>
										/>元
									</td>
								</tr>
								<tr>
									<td colspan="1"><label></label>
										<c:if test='${cBoxSocialFundComplement == false }'>
											<input type="checkbox" id="cBoxSocialFundComplement" 
												name="cBoxSocialFund" value="01"
												onclick="selectCompOne(this,'socialFundCompIndex')" />
										</c:if>
										是否拥有社保基金： </td>
									<td>
										<c:if test='${cBoxSocialFundComplement == false }'>
											<input type="radio" id="socialFundCompIndex1" name="socialFund" 
												value="是" disabled="disabled"/>是
											<input type="radio" id="socialFundCompIndex2" name="socialFund" 
												value="否" disabled="disabled" checked="checked" />否
										</c:if>
										<c:if test='${cBoxSocialFundComplement == true }'>
											<input type="radio" id="socialFundCompIndex" name="socialFund" checked="checked" 
												value="${xhJksqTogether.socialFund }" />${xhJksqTogether.socialFund }
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="1"><label>申请额度：</label> 
										<input type="text" id="loanQuota" name="loanQuota" size="30" class="" 
											value="${xhJksqTogether.loanQuota }" ${jksqTogetherComp.loanQuota }/>
									</td>
									<td><label>申请还款期限：</label> 
										<input type="text" id="jkCycle" name="jkCycle" size="30" class="" 
											value="${xhJksqTogether.jkCycle }" ${jksqTogetherComp.jkCycle }/>
									</td>
									<td><label></label> 
									</td>
								</tr>
								<c:if test='${xhJksqTogether.sourceOfFunds =="01" && xhJksqTogether.sourceOfFunds =="02"}'>
								<tr>
									<td colspan="2"><label>还款资金来源：</label> 
										<c:if test='${xhJksqTogether.sourceOfFunds =="01" }'>
											<input type="radio" id="sourceOfFunds" name="sourceOfFunds" 
												value="01" checked="checked" 
												onclick="sourceOfFundsComplement('01')" />独立还款&nbsp;&nbsp;
										</c:if>
										<c:if test='${xhJksqTogether.sourceOfFunds =="02" }'>
											<input type="radio" id="sourceOfFunds" name="sourceOfFunds" 
												value="02" checked="checked"
												onclick="sourceOfFundsComplement('02')" />家人协助还款&nbsp;&nbsp;
										</c:if>
									</td>
								</tr>
								</c:if>
								
								<c:if test='${xhJksqTogether.sourceOfFunds =="03" }'>
								<tr>
									<td colspan="1"><label>还款资金来源：</label> 
										<input type="radio" id="sourceOfFunds" name="sourceOfFunds" 
											value="03" checked="checked"
											onclick="sourceOfFundsComplement('03')" />其他方式
									</td>
									<td align="left">
										<input type="text" id="sourceOfFundsInfo" name="sourceOfFundsInfo" size="30" 
											value="${xhJksqTogether.sourceOfFundsInfo }" ${jksqTogetherComp.sourceOfFundsInfo }/>
									</td>
								</tr>
								</c:if>
								
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
										<td align="center">${togethorXhjkjjlxr1.ybrgx }
											<input type="hidden" name="id1" value="${togethorXhjkjjlxr1.id }" />
											<input type="hidden" name="ybrgx1" value="${togethorXhjkjjlxr1.ybrgx }" />
										</td>
										<td align="left">
											<input type="text" name="name1" value="${togethorXhjkjjlxr1.name }" 
												${jksqTogetherComp.xhjkjjlxr1.name } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw1" value="${togethorXhjkjjlxr1.jjlxrgzdw }" 
												${jksqTogetherComp.xhjkjjlxr1.jjlxrgzdw } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz1" value="${togethorXhjkjjlxr1.jjlxrdwdzhjtzz }" 
												${jksqTogetherComp.xhjkjjlxr1.jjlxrdwdzhjtzz } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh1" value="${togethorXhjkjjlxr1.jjlxrlxdh }" 
												${jksqTogetherComp.xhjkjjlxr1.jjlxrlxdh } class="digits"/>
										</td>
									</tr>
									<tr>
										<td align="center">${togethorXhjkjjlxr2.ybrgx }
											<input type="hidden" name="id2" value="${togethorXhjkjjlxr2.id }" />
											<input type="hidden" name="ybrgx2" value="${togethorXhjkjjlxr2.ybrgx }" />
										</td>
										<td align="left">
											<input type="text" name="name2" value="${togethorXhjkjjlxr2.name }" 
												${jksqTogetherComp.xhjkjjlxr2.name } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw2" value="${togethorXhjkjjlxr2.jjlxrgzdw }" 
												${jksqTogetherComp.xhjkjjlxr2.jjlxrgzdw } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz2" value="${togethorXhjkjjlxr2.jjlxrdwdzhjtzz }" 
												${jksqTogetherComp.xhjkjjlxr2.jjlxrdwdzhjtzz } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh2" value="${togethorXhjkjjlxr2.jjlxrlxdh }" 
												${jksqTogetherComp.xhjkjjlxr2.jjlxrlxdh } class="digits"/>
										</td>
									</tr>
									<tr>
										<td align="center">${togethorXhjkjjlxr3.ybrgx }
											<input type="hidden" name="id3" value="${togethorXhjkjjlxr3.id }" />
											<input type="hidden" name="ybrgx3" value="${togethorXhjkjjlxr3.ybrgx }" />
										</td>
										<td align="left">
											<input type="text" name="name3" value="${togethorXhjkjjlxr3.name }" 
												${jksqTogetherComp.xhjkjjlxr3.name } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw3" value="${togethorXhjkjjlxr3.jjlxrgzdw }" 
												${jksqTogetherComp.xhjkjjlxr3.jjlxrgzdw } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz3" value="${togethorXhjkjjlxr3.jjlxrdwdzhjtzz }" 
												${jksqTogetherComp.xhjkjjlxr3.jjlxrdwdzhjtzz } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh3" value="${togethorXhjkjjlxr3.jjlxrlxdh }" 
												${jksqTogetherComp.xhjkjjlxr3.jjlxrlxdh } class="digits"/>
										</td>
									</tr>
									<tr>
										<td align="center">${togethorXhjkjjlxr4.ybrgx }
											<input type="hidden" name="id4" value="${togethorXhjkjjlxr4.id }" />
											<input type="hidden" name="ybrgx4" value="${togethorXhjkjjlxr4.ybrgx }" />
										</td>
										<td align="left">
											<input type="text" name="name4" value="${togethorXhjkjjlxr4.name }" 
												${jksqTogetherComp.xhjkjjlxr4.name } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw4" value="${togethorXhjkjjlxr4.jjlxrgzdw }" 
												${jksqTogetherComp.xhjkjjlxr4.jjlxrgzdw } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz4" value="${togethorXhjkjjlxr4.jjlxrdwdzhjtzz }" 
												${jksqTogetherComp.xhjkjjlxr4.jjlxrdwdzhjtzz } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh4" value="${togethorXhjkjjlxr4.jjlxrlxdh }" 
												${jksqTogetherComp.xhjkjjlxr4.jjlxrlxdh } class="digits"/>
										</td>
									</tr>
									<tr>
										<td align="center">${togethorXhjkjjlxr5.ybrgx }
											<input type="hidden" name="id5" value="${togethorXhjkjjlxr5.id }" />
											<input type="hidden" name="ybrgx5" value="${togethorXhjkjjlxr5.ybrgx }" />
										</td>
										<td align="left">
											<input type="text" name="name5" value="${togethorXhjkjjlxr5.name }" 
												${jksqTogetherComp.xhjkjjlxr5.name } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw5" value="${togethorXhjkjjlxr5.jjlxrgzdw }" 
												${jksqTogetherComp.xhjkjjlxr5.jjlxrgzdw } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz5" value="${togethorXhjkjjlxr5.jjlxrdwdzhjtzz }" 
												${jksqTogetherComp.xhjkjjlxr5.jjlxrdwdzhjtzz } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh5" value="${togethorXhjkjjlxr5.jjlxrlxdh }" 
												${jksqTogetherComp.xhjkjjlxr5.jjlxrlxdh } class="digits"/>
										</td>
									</tr>
									<tr>
										<td align="center">${togethorXhjkjjlxr6.ybrgx }
											<input type="hidden" name="id6" value="${togethorXhjkjjlxr6.id }" />
											<input type="hidden" name="ybrgx6" value="${togethorXhjkjjlxr6.ybrgx }" />
										</td>
										<td align="left">
											<input type="text" name="name6" value="${togethorXhjkjjlxr6.name }" 
												${jksqTogetherComp.xhjkjjlxr6.name } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw6" value="${togethorXhjkjjlxr6.jjlxrgzdw }" 
												${jksqTogetherComp.xhjkjjlxr6.jjlxrgzdw } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz6" value="${togethorXhjkjjlxr6.jjlxrdwdzhjtzz }" 
												${jksqTogetherComp.xhjkjjlxr6.jjlxrdwdzhjtzz } />
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh6" value="${togethorXhjkjjlxr6.jjlxrlxdh }" 
												${jksqTogetherComp.xhjkjjlxr6.jjlxrlxdh } class="digits"/>
										</td>
									</tr>
									<!-- 
									<c:forEach items="${relativesTogether }" var="relatives" varStatus="st">
									<tr>
										<td align="center">${relatives.ybrgx }
											<input type="hidden" name="id${st.count }" value="${relatives.id }">
										</td>
										<td align="left">
											<input type="text" name="name${st.count }" value="${relatives.name }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw${st.count }" value="${relatives.jjlxrgzdw }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz${st.count }" value="${relatives.jjlxrdwdzhjtzz }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh${st.count }" value="${relatives.jjlxrlxdh }">
										</td>
									</tr>
									</c:forEach>
									<c:forEach items="${friendTogether }" var="friend" varStatus="st">
									<tr>
										<td align="center">${friend.ybrgx }
											<input type="hidden" name="id${st.count + 2 }" value="${friend.id }">
										</td>
										<td align="left">
											<input type="text" name="name${st.count + 2}" value="${friend.name }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw${st.count  + 2}" value="${friend.jjlxrgzdw }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz${st.count + 2 }" value="${friend.jjlxrdwdzhjtzz }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh${st.count + 2 }" value="${friend.jjlxrlxdh }">
										</td>
									</tr>
									</c:forEach>
									<c:forEach items="${workmateTogether }" var="workmate" varStatus="st">
									<tr>
										<td align="center">${workmate.ybrgx }
											<input type="hidden" name="id${st.count + 4 }" value="${workmate.id }">
										</td>
										<td align="left">
											<input type="text" name="name${st.count + 4 }" value="${workmate.name }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrgzdw${st.count  + 4}" value="${workmate.jjlxrgzdw }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrdwdzhjtzz${st.count + 4 }" value="${workmate.jjlxrdwdzhjtzz }">
										</td>
										<td align="left">
											<input type="text" name="jjlxrlxdh${st.count + 4 }" value="${workmate.jjlxrlxdh }">
										</td>
									</tr>
									</c:forEach>
									 -->
								</tbody>
							</table>
							<div class="formBar">
								<ul>
									<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit" >提交共借人补充信息</button>
										</div>
									</div></li>
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
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>



</div>
<script type="text/javascript">
	//借款申请补充提交
	function jksqComplementMsgFormSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}
	
		return validateCallback(obj, navTabAjaxDone);
	}
	
	//借款申请共同还款人补充提交
	function jksqTogetherComplementSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}
	
		return validateCallback(obj, navTabAjaxDone);
	}
	
	function jkTypeComplementDiv(state){
		if("A" == state){
			document.getElementById('lbd_div_complement').style.display = "block";
			document.getElementById('lblyd_div_complement').style.display = "none";
			document.getElementById('xsd_div_complement').style.display = "none";
			document.getElementById('xslyd_div_complement').style.display = "none";
			document.getElementById('jyd_div_complement').style.display = "none";
			document.getElementById('qyd_div_complement').style.display = "none";
			document.getElementById('jylyd_div_complement').style.display = "none";
		}else if("B" == state){
			document.getElementById('lbd_div_complement').style.display = "none";
			document.getElementById('lblyd_div_complement').style.display = "block";
			document.getElementById('xsd_div_complement').style.display = "none";
			document.getElementById('xslyd_div_complement').style.display = "none";
			document.getElementById('jyd_div_complement').style.display = "none";
			document.getElementById('qyd_div_complement').style.display = "none";
			document.getElementById('jylyd_div_complement').style.display = "none";
		}else if("C" == state){
			document.getElementById('lbd_div_complement').style.display = "none";
			document.getElementById('lblyd_div_complement').style.display = "none";
			document.getElementById('xsd_div_complement').style.display = "block";
			document.getElementById('xslyd_div_complement').style.display = "none";
			document.getElementById('jyd_div_complement').style.display = "none";
			document.getElementById('qyd_div_complement').style.display = "none";
			document.getElementById('jylyd_div_complement').style.display = "none";
		}else if("D" == state){
			document.getElementById('lbd_div_complement').style.display = "none";
			document.getElementById('lblyd_div_complement').style.display = "none";
			document.getElementById('xsd_div_complement').style.display = "none";
			document.getElementById('xslyd_div_complement').style.display = "block";
			document.getElementById('jyd_div_complement').style.display = "none";
			document.getElementById('qyd_div_complement').style.display = "none";
			document.getElementById('jylyd_div_complement').style.display = "none";
		}else if("E" == state){
			document.getElementById('lbd_div_complement').style.display = "none";
			document.getElementById('lblyd_div_complement').style.display = "none";
			document.getElementById('xsd_div_complement').style.display = "none";
			document.getElementById('xslyd_div_complement').style.display = "none";
			document.getElementById('jyd_div_complement').style.display = "block";
			document.getElementById('qyd_div_complement').style.display = "none";
			document.getElementById('jylyd_div_complement').style.display = "none";
		}else if("Q" == state){
			document.getElementById('lbd_div_complement').style.display = "none";
			document.getElementById('lblyd_div_complement').style.display = "none";
			document.getElementById('xsd_div_complement').style.display = "none";
			document.getElementById('xslyd_div_complement').style.display = "none";
			document.getElementById('jyd_div_complement').style.display = "none";
			document.getElementById('qyd_div_complement').style.display = "block";
			document.getElementById('jylyd_div_complement').style.display = "none";
		}else if("W" == state){
			document.getElementById('lbd_div_complement').style.display = "none";
			document.getElementById('lblyd_div_complement').style.display = "none";
			document.getElementById('xsd_div_complement').style.display = "none";
			document.getElementById('xslyd_div_complement').style.display = "none";
			document.getElementById('jyd_div_complement').style.display = "none";
			document.getElementById('qyd_div_complement').style.display = "none";
			document.getElementById('jylyd_div_complement').style.display = "block";
		}
	}

	function initJkTypeComplement(){
		var state = "${jksq.jkType }";
		jkTypeComplementDiv(state);
	}
	
	function marrayComplement(){
		var isMarrayComplement = "${jksq.maritalStatus }";
		if("已婚" == isMarrayComplement){
			document.getElementById('isMarrayComplement').style.display = "block";
		}
	}
	
	//共借人
	function selectCompOne(obj,ziduan){
		var zd = document.getElementById(ziduan);
		if (obj.checked){
			if(ziduan == "socialFundCompIndex"){
				document.getElementById(ziduan+"1").disabled = false;
				document.getElementById(ziduan+"2").disabled = false;
			}else{
				zd.disabled = false;
			}
		} else{
			if(ziduan == "socialFundCompIndex"){
				document.getElementById(ziduan+"1").disabled = true;
				document.getElementById(ziduan+"2").disabled = true;
			}else{
				zd.disabled = true;
			}
		}
	}
	
	//初始化共同借款人居住状况
	function initJksqTogetherCompMsg(){
		var liveState = "${xhJksqTogether.liveState }";
		if("01" == liveState){
		}else if("02" == liveState){
		}else if("03" == liveState){
		}else if("04" == liveState){
			document.jksqTogetherComplementForm.liveMessageComplement04.value = "${xhJksqTogether.liveMessage }";
		}else if("05" == liveState){
			document.jksqTogetherComplementForm.liveMessageComplement05.value = "${xhJksqTogether.liveMessage }";
		}
	}
	
	function initComplement(){
		initJkTypeComplement();
		marrayComplement();
		initJksqTogetherCompMsg();
	}
	initComplement();

</script>