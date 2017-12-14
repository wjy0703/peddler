<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post"
		action="${ctx }/creditAudit/saveCreditTaskAssign/${loan_apply_id}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="56">
			
		<div class="divider"></div>
		
						<table nowrapTD="false" width="100%">
							<tr>
								<td><label>借款人姓名:</label> 
									<input type="text" id="jkrxm" name="jkrxm" size="30" value="${jksq.jkrxm }" 
										class="" readonly="readonly"/>
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
									<select id="pocertificates" name="pocertificates" class="combox ">
										<c:forEach items="${zjlx0005}" var="per">
											<option value="${per.value }" 
												<c:if test="${per.value==jksq.pocertificates}">selected="selected" </c:if> 
											>${per.name}</option>
										</c:forEach>
									</select>
								</td>
								<td><label>证件号码：</label> 
									<input type="text" id="zjhm" name="zjhm" size="30" class="" value="${jksq.zjhm }" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>户籍地址：</label> 
									<input type="text" id="hjadress" name="hjadress" size="60" class="" value="${jksq.hjadress }" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><label>现住址:</label> 
									<input type="text" id="homeAddress" name="homeAddress" size="60" class="" value="${jksq.homeAddress }"/>
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
									<input type="text" id="telephone" name="telephone" size="30" class="" value="${jksq.telephone }" />
								</td>
								<td><label>电子邮箱：</label> 
									<input type="text" id="email" name="email" size="30" class="" value="${jksq.email }" />
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
							
							<label>请选择初审人员：</label> <input type="text"
				id="empname" class="required" name="employee.name" value=""
				suggestFields="name,deptname"
				lookupGroup="employee"  class="required" readonly/><input type="hidden" name="employee.id"
				value="${xhCreditTaskAssign.employee.id}" /> 
				<a class="btnLook"
							href="${ctx}/baseinfo/emplookupByPosition/4"
							lookupGroup="employee"><hi:text key="查找带回" /></a>
					<!--  <table >
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
											<select name="province" ref="loanshowbox_city" class=" combox" 
												refUrl="${ctx}/cjrxx/getCity?code={value}" >
												<option value="">所有省市*</option>
												<c:forEach items="${province}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.province.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<select id="loanshowbox_city" name="city" class=" combox" ref="loanshowbox_area"
												 refUrl="${ctx}/cjrxx/getArea?code={value}" >
												<option value="">所有城市*</option>
												<c:forEach items="${city}" var="md" varStatus="st">
													<option value="${md.id }"
														<c:if test="${jksq.city.id==md.id}">selected</c:if>>${md.name }
													</option>
												</c:forEach>
											</select> 
											<select  id="loanshowbox_area" name="area" class=" combox" >
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
						</table>-->	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
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

