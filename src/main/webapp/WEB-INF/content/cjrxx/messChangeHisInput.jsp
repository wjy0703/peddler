<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>
<div class="panel">
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/cjrxx/listMessChangeHisCjr" method="post">
		<div class="pageFormContent">
			<table>
				<tr>
					<td><label>客户姓名：</label> <input name="cjrxm" type="text"
						size="30" value="${cjrxx.cjrxm }" class="required" maxlength="10" />
					</td>
					<td><label>英文名称：</label> <input name="ywmc" type="text"
						size="30" value="${cjrxx.ywmc }" maxlength="10" /></td>
				</tr>
				<tr>
					<td><label>性别：</label> 
					<sen:select clazz="combox required" name="cjrxb" id="cjrxb" coding="genders" value="${cjrxx.cjrxb }" title="请选择"/>
					<%-- <select name="cjrxb"
						class="required combox">
							<option value="" <c:if test="${cjrxx.cjrxb==''}">selected</c:if>>请选择</option>
							<c:forEach items="${xb}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrxb==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%>
					</td>
					<td><label>出生日期：</label> <input type="text" name="csrq"
						class="date" pattern="yyyy-MM-dd" value="${cjrxx.csrq }" size="25" />
						<a class="inputDateButton" href="#">选择</a></td>
				</tr>
				<tr>
					<td><label>证件类型：</label> 
					<sen:select clazz="combox required" name="zjlx" coding="cardType" id="zjlx" value="${cjrxx.zjlx }"/>
					<%-- <select name="zjlx"
						class="required combox">
							<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zjlx}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.zjlx==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></td>
					<td><label>证件号码：</label> <input name="zjhm" type="text"
						size="30" value="${cjrxx.zjhm }" class="required" maxlength="40" />
					</td>
				</tr>
				<tr>
					<td><label>学历：</label> <input name="cjrxl" type="text"
						size="30" value="${cjrxx.cjrxl }" maxlength="20" /></td>
					<td><label>职业：</label> 
					<sen:select clazz="combox" name="cjrzy" id="cjrzy" coding="occupationType" value="${cjrxx.cjrzy }" title="请选择"/>
					<%-- <select name="cjrzy"
						class="required combox">
							<option value="" <c:if test="${cjrxx.cjrzy==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zy}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrzy==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></td>
				</tr>
				<tr>
					<td><label>行业：</label> <input name="hy" type="text" size="30"
						value="${cjrxx.hy }" maxlength="40" /></td>
					<td><label>单位名称：</label> <input name="gzdwmc" type="text"
						size="30" value="${cjrxx.gzdwmc }" maxlength="100" /></td>
				</tr>
				<tr>
					<td><label>移动电话：</label> <input name="yddh" type="text"
						size="30" value="${cjrxx.yddh }" class="required" maxlength="20" />
					</td>
					<td><label>固定电话：</label> <input name="gddh" type="text"
						size="30" value="${cjrxx.gddh }" class="required" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td><label>电子邮箱：</label> <input name="dzyx" type="text"
						size="30" value="${cjrxx.dzyx }" maxlength="80" /></td>
					<td><label>客户传真：</label> <input name="cjrfx" type="text"
						size="30" value="${cjrxx.cjrfx }" maxlength="20" /></td>
				</tr>
				<tr>
					<td><label>账单收取方式：</label> 
						<sen:select clazz="combox" name="zqjsfs" id="zqjsfs" coding="sendModle" value="${cjrxx.zqjsfs}" title="请选择"/>
					<%-- <select name="zqjsfs"
						class="required combox">
							<option value="" <c:if test="${cjrxx.zqjsfs==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zqjsfs}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.zqjsfs==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%></td>
					<td><label>邮编：</label> <input name="yb" type="text" size="30"
						value="${cjrxx.yb }" maxlength="6" /></td>
				</tr>
			</table>
			<dl class="nowrap">
				<dt>通讯地址：</dt>
				<dd>
				<sen:address required="true"  names="province,city,area" 
					titles="所有省份,所有城市,所有区县" values="${cjrxx.province},${cjrxx.city},${cjrxx.area}"/>
					 <input name="txdz" type="text"
							size="80" value="${cjrxx.txdz }" maxlength="60" class="required" />
					<%-- <select class="combox" name="province" ref="combox_city"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
						<option value=""
							<c:if test="${cjrxx.province==''}">selected</c:if>>所有省市</option>
						<c:forEach items="${province}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.province==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <select class="combox" name="city" id="combox_city"
						ref="combox_area" refUrl="${ctx}/cjrxx/getArea?code={value}">
						<option value="" <c:if test="${cjrxx.city==''}">selected</c:if>>所有城市</option>
						<c:forEach items="${city}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.city==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <select class="combox" name="area" id="combox_area">
						<option value="" <c:if test="${cjrxx.area==''}">selected</c:if>>所有区县</option>
						<c:forEach items="${area}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${cjrxx.area==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <input name="txdz" type="text" size="30" value="${cjrxx.txdz }"
						maxlength="100" /> --%>
				</dd>
			</dl>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="165">
		<thead>
			<tr>
				<th width="80" orderField="cjrxm" class="asc">客户名</th>
				<th width="80">CRM</th>
				<th width="80">修改状态</th>
				<th width="100" orderField="createTime">创建日期</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cjrxx.upHistorys}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td>${user.cjrxm }</td>
					<td>${user.employeeCrm.name }</td>
					<td><c:if test="${user.upstate=='0'}">暂存</c:if> <c:if
							test="${user.upstate=='1'}">待审批</c:if> <c:if
							test="${user.upstate=='2'}">审批通过</c:if> <c:if
							test="${user.upstate=='3'}">审批不通过</c:if></td>
					<td>${user.createTime }</td>
					<td><a title="查看" target="navTab"
						href="${ctx }/cjrxx/getCjrxxHisDetailed/${user.id}">查看</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>