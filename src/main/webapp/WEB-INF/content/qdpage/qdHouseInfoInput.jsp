<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
	function setState(val) {
        $("#qdmeta1").val(val);
       	return true;
	}
</script>
<div class="panel">
<div class="pageFormContent">
	<form id="qdform" method="post" action="${ctx}/qdHouseInfo/saveQdHouseInfo" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${qdHouseInfo.id}"/>
		<input type="hidden" id="qdmeta1" name="meta1"/>
		<div class="pageFormContent" layoutH="56">
			<h1>基本信息</h1>
			<div class="divider"></div>
		<table width="100%">
		<tr>
			
			<td>
				<label>借款人姓名：</label>
				<input name="jkrxm" type="text" size="30" value="${qdHouseInfo.jkrxm}" class="required" maxlength="1000" />
			</td>
			
			<td>
				<label>证件号码：</label>
				<input name="zjhm" type="text" size="30" value="${qdHouseInfo.zjhm}" class="required" maxlength="255" />
			</td>
			
			<td>
				<label>城市：</label>
				<select class="combox required" name="province" ref="combox_crmcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
						<option value=""
							<c:if test="${qdHouseInfo.province==''}">selected</c:if>>所有省市</option>
						<c:forEach items="${crmprovince}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${qdHouseInfo.province==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> 
					<select class="combox required" name="city" id="combox_crmcity"
						ref="combox_crmarea" refUrl="${ctx}/cjrxx/getArea?code={value}">
						<option value="" <c:if test="${qdHouseInfo.city==''}">selected</c:if>>所有城市</option>
						<c:forEach items="${cities}" var="md" varStatus="st">
							<option value="${md.id }"
								<c:if test="${qdHouseInfo.city==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
			  </select>
			</td>
			
		</tr>
		
		</table>
		<div class="divider"></div>
			<h1>货款相关信息</h1>
			<div class="divider"></div>
		<table width="100%">
		<tr>
		   <td>
				<label>借款编号：</label>
				<input name="kyzqid" type="hidden" value="${qdHouseInfo.kyzqid}">
				<input name="jqhtid" type="hidden" value="${qdHouseInfo.jqhtid}">
				<input name="jqsqid" type="hidden" value="${qdHouseInfo.jqsqid}">
				<input name="loanCode" type="text" size="30" value="${qdHouseInfo.loanCode}" class="required" maxlength="32" />
			</td>
			<td>
				<label>借款人借款用途：</label>
				<select name="jkUse" class="required combox">
				   <option value="">请选择</option>
				   <option <c:if test="${qdHouseInfo.jkUse == '经营'}" >selected</c:if> value="经营">经营</option>
				   <option <c:if test="${qdHouseInfo.jkUse == '周转'}" >selected</c:if> value="周转">周转</option>
				   <option <c:if test="${qdHouseInfo.jkUse == '消费'}" >selected</c:if> value="消费">消费</option>
				</select>
			</td>
			
			
		</tr>
		<tr>
			<td>
				<label>债权人：</label>
				<select name="zqr" class="required combox">
				    <option value="">请选择</option>
				    <c:forEach items="${middleMans}" var="md" varStatus="st">
				    	<option <c:if test="${qdHouseInfo.zqr == md.id}">selected</c:if> value="${md.id}">${md.middleManName}</option>
				    </c:forEach>
				</select>
			</td>
			<td colspan="">
				<label>初始借款金额：</label>
				<input name="htje" type="text" size="30" value="${qdHouseInfo.htje}" class="required number" maxlength="22" />
			</td>
			<td>
				<label>借款方式：</label>
				<select name="jkType" class="required combox">
				   <option value="">请选择</option>
				   <option <c:if test="${qdHouseInfo.jkType=='F'}">selected</c:if> value="F">楼易借</option>
				</select>
			</td>
		</tr>
		<tr>
		    <td>
				<label>月本：</label>
				<input name="ybjje" type="text" size="30" value="${qdHouseInfo.ybjje}" class="required number" maxlength="22" />
			</td>
			<td>
				<label>月利息金额：</label>
				<input name="ylxje" type="text" size="30" value="${qdHouseInfo.ylxje}" class="required number" maxlength="22" />
			</td>
			<td>
				<label>月账户管理费：</label>
				<input name="zhglf" type="text" size="30" value="${qdHouseInfo.zhglf}" class="required number" maxlength="22" />
			</td>
			
		</tr>
		<tr>
		    <td>
				<label>月还款金额：</label>
				<input name="yhkje" type="text" size="30" value="${qdHouseInfo.yhkje}" class="required number" maxlength="22" />
			</td>
			<td>
				<label>初始借款时间：</label>
				<input name="qdrq"
						type="text" readonly size="17" value="${qdHouseInfo.qdrq}" class="date"
						format="yyyy-MM-dd"
						 value="${cjrxx.csrq }" size="20" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a> 
			</td>
			<td>
				<label>还款期限：</label>
				<input name="hkqs" type="text" size="30" value="${qdHouseInfo.hkqs}" class="required number" maxlength="22" />
			</td>
			
		</tr>
		<tr>
			<td>
				<label>起始还款日期：</label>
				<input name="qshkrq"
						type="text" readonly size="17" value="${qdHouseInfo.qshkrq}" class="date"
						format="yyyy-MM-dd"
						 value="${cjrxx.csrq }" size="20" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a> 
			</td>
			<td colspan="2">
				<label>借款利率%：</label>
				<input name="dkll" type="text" size="30" value="${qdHouseInfo.dkll}" class="required number" maxlength="22" />
			</td>
			
			
		</tr>
		<tr>
		    <td>
				<label>借款人状态：</label>
				<select name="state" class="required combox">
				   <option value="">请选择</option>
				   <option <c:if test="${qdHouseInfo.state == '还款中'}">selected</c:if> value="还款中">还款中</option>
				   <option <c:if test="${qdHouseInfo.state == '待还款'}">selected</c:if> value="待还款">待还款</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<label>借款人职业情况：</label>
				<input name="personWorkCondition" type="text" size="30" value="${qdHouseInfo.personWorkCondition}" class="required" maxlength="255" />
			</td>
		</tr>
		
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="return setState('暂存')" type="submit">暂存</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="return setState('提交')" type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

</div>

