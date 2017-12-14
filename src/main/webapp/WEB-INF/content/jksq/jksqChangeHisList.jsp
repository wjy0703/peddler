<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="pagerForm" method="post" action="${ctx }/jksq/listChangeHis/${jksq.id }">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<form id="jksqchagehislistForm" name="jksqchagehislistForm" rel="pagerForm"
	action="${ctx }/jksq/listChangeHis" method="post" onsubmit="return navTabSearch(this);" >
<div class="panel">
<div class="pageFormContent" >
	<table border="0" bordercolor="red" nowrapTD="false">
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
				<select id="pocertificates" name="pocertificates" class="combox">
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
			<td><label>户籍地址：</label> 
				<input type="text" id="hjadress" name="hjadress" size="30" class="required" value="${jksq.hjadress }" />
			</td>
		</tr>
		<tr>
			<td><label>现住址:</label> 
				<input type="text" id="homeAddress" name="homeAddress" size="30" value="${jksq.homeAddress }"/>
			</td>
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
				<select id="companyNature" name="companyNature" class="combox">
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
				<select id="maritalStatus" name="maritalStatus" class="combox">
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
				<input type="text" id="ywzn" name="ywzn" size="30" readonly="readonly" class="" value="${jksq.ywzn }" />
			</td>
			<td><label>QQ号码：</label> 
				<input type="text" id="qqhm" name="qqhm" size="30" class="" value="${jksq.qqhm }" />
			</td>
			<td><label>年收入：</label> 
				<input type="text" id="annualIncome" name="annualIncome" size="30" class="" value="${jksq.annualIncome }" />
			</td>
		</tr>
		<tr>
			<td><label>收入说明：</label> 
				<input type="text" id="incomeIllustration" name="incomeIllustration" size="30" class="" value="${jksq.incomeIllustration }" />
			</td>
		</tr>
		<tr>
			<td colspan="1"><label>居住状态：</label> 
				<c:if test='${jksq.liveState == "01" }'>
					<input type="radio" id="liveState" name="liveState" value="01" 
						<c:if test='${jksq.liveState == "01" }'> checked="checked" </c:if>
						 onclick="livestateShow('liveMessage01')" />自有房屋，有无借款，月供
				</c:if>
				<c:if test='${jksq.liveState == "02" }'>
						<input type="radio" id="liveState" name="liveState" value="02" 
							<c:if test='${jksq.liveState == "02" }'> checked="checked" </c:if>
							onclick="livestateShow('liveMessage02')" />亲属产权
				</c:if>
				<c:if test='${jksq.liveState == "03" }'>
					<input type="radio" id="liveState" name="liveState" value="03"
						<c:if test='${jksq.liveState == "03" }'> checked="checked" </c:if>
						onclick="livestateShow('liveMessage03')" />租房，房租
				</c:if>
				<c:if test='${jksq.liveState == "04" }'>
						<input type="radio" id="liveState" name="liveState" value="04"
							<c:if test='${jksq.liveState == "04" }'> checked="checked" </c:if>
							onclick="livestateShow('liveMessage04')" />其他，说明：
				</c:if>
				
			</td>
			
			<c:if test='${jksq.liveState == "01" }'>
				<td>
					<input id="liveMessage01" name="liveMessage" type="text" value="" />元
				</td>
			</c:if>
			
			<c:if test='${jksq.liveState == "02" }'>
				<td><input type="hidden" id="liveMessage02" name="liveMessage" 	value="" /></td>
			</c:if>
		
			<c:if test='${jksq.liveState == "03" }'>
				<td>
					<input type="text" id="liveMessage03" name="liveMessage" value="" />元/月
				</td>
			</c:if>
			<c:if test='${jksq.liveState == "04" }'>
				<td>
					<input type="text" id="liveMessage04" name="liveMessage" size="80" value="" />
				</td>
			</c:if>
		</tr>
		<tr>
			<td colspan="3">
				<dl class="nowrap">
					<dt>通讯地址：</dt>
					<dd>
						<select name="province.id" ref="loanchangehisbox_city" class="combox" 
							refUrl="${ctx}/cjrxx/getCity?code={value}" >
							<option value="" <c:if test="${jksq.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${jksq.province.id==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select> 
						<select id="loanchangehisbox_city" name="city.id" class="combox" ref="loanchangehisbox_area"
							 refUrl="${ctx}/cjrxx/getArea?code={value}" >
							<option value="" <c:if test="${jksq.city==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${city}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${jksq.city.id==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select> 
						<select  id="loanchangehisbox_area" name="area.id" class="combox" >
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
</div>
</div>
</form>
<div class="pageContent" layoutH="260">
	
	<table class="table" width="99%"  nowrapTD="false">
		<thead>
			<tr>
				<th width="60" >借款编号</th>
				<th width="60">共同借款人</th>
				<th width="60">客户姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">状态</th>
				<th width="60">产品</th>
			
				<th width="60">申请金额</th>
				<th width="60">放款金额</th>
				<th width="30">分期</th>
				<th width="80">团队经理</th>
				<th width="60">销售人员</th>

				<th width="120" orderField="CREATE_TIME" class="desc">进件时间</th>
				<th width="80"  align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${his }" var="his" varStatus="st">
				<tr  target="sid_jksq" rel="${his.ID}">
					<td>${his.LOAN_CODE }</td>
					<td>${his.TOGETHER_PERSON}</td>
					<td>${his.JKRXM }</td>
					<td>${his.PRONAME}</td>
					<td>${his.CITYNAME}</td>
					<td style="color:red">${his.STATEINFO  }</td>
					<td>${his.JK_TYPE_INFO}</td>
					<td>
						<c:if test='${null != his.JK_LOAN_QUOTA && his.JK_LOAN_QUOTA != 0 }'>￥<fmt:formatNumber value="${his.JK_LOAN_QUOTA }" pattern="#,#00.00" /></c:if>
					</td>
					<td>
						<c:if test='${null != his.FKJE && his.FKJE != 0 }'>￥<fmt:formatNumber value="${his.FKJE}" pattern="#,#00.00" /></c:if>
					</td>
					<td>${his.JK_CYCLE }</td>
					<td>${his.TEAMNAME }</td>
					<td>${his.SALENAME }</td>
					<td>${his.CREATETIME}</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<a href="${ctx }/jksq/jksqHisShow/${his.ID}" rel="rel_jksqHisShow"
										target="navTab" title="查看借款变更信息"><button type="submit">查看</button></a>
							</div>
						</div>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2"   <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
				<option value="10"   <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20"   <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50"   <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select> <span>条，共${page.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }">
		</div>

	</div>
</div>
<script type="text/javascript">
	function jksqchagehisLiveState(){
		var liveState = '${jksq.liveState}';
		if("01" == liveState){
			document.jksqchagehislistForm.liveMessage01.value = "${jksq.liveMessage }";
			document.jksqchagehislistForm.liveMessage02.disabled = true;
			document.jksqchagehislistForm.liveMessage03.disabled = true;
			document.jksqchagehislistForm.liveMessage04.disabled = true;
		}else if("02" == liveState){
			document.jksqchagehislistForm.liveMessage01.disabled = true;
			document.jksqchagehislistForm.liveMessage02.value = "${jksq.liveMessage }";
			document.jksqchagehislistForm.liveMessage03.disabled = true;
			document.jksqchagehislistForm.liveMessage04.disabled = true;
		}else if("03" == liveState){
			document.jksqchagehislistForm.liveMessage01.disabled = true;
			document.jksqchagehislistForm.liveMessage02.disabled = true;
			document.jksqchagehislistForm.liveMessage03.value = "${jksq.liveMessage }";
			document.jksqchagehislistForm.liveMessage04.disabled = true;
		}else if("04" == liveState){
			document.jksqchagehislistForm.liveMessage01.disabled = true;
			document.jksqchagehislistForm.liveMessage02.disabled = true;
			document.jksqchagehislistForm.liveMessage03.disabled = true;
			document.jksqchagehislistForm.liveMessage04.value = "${jksq.liveMessage }";
		}
	}
	
	function initjksqChangeHisList(){
		jksqchagehisLiveState();
	}
	initjksqChangeHisList();
</script>