<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="${ctx }/jygl/findZqtj">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" method="post"
		action="${ctx }/jygl/findZqtj"
		onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="mateid" value="${map.mateid}"/>
		<input type="hidden" name="MONEY" value="${map.MONEY}"/>
		<input type="hidden" name="ZDR" value="${map.ZDR}"/>
		<input type="hidden" name="TZCP_ID" value="${map.TZCP_ID}"/>
		<input type="hidden" name="LAST_CJZQ" value="${map.LAST_CJZQ}"/>
		<input type="hidden" class="textInput" name="zhongjianren.id" value="${map.zjrId}"/> 
		<input type="hidden" name="selFlag" value="1"/>
		<div class="pageFormContent">
			<table class="searchContent" layoutH="440" width="100%">
				<tr>
					<td>
					<table  width="100%">
					<tr>
					<td><label>账单日:</label> <input class="textInput"
						name="" value="${map.ZDR }" type="text" disabled="disabled" size="15"></td>
					<td><label>待匹配金额:</label> <input class="textInput"
						name="" value='<fmt:formatNumber
							value="${map.MONEY }" pattern="#,#00.00000" />' type="text" disabled="disabled" size="15"></td>
					<td><label>出借产品:</label> <input class="textInput"
						name="" value="${map.TZCP_MC }" type="text" 
						size="15" disabled="disabled"></td>
				</tr>
				</table >
				<div class="divider"></div>
				<table width="100%">
				<tr>
					<td><label>借款人姓名:</label> <input class="textInput"
						name="filter_jkrxm" value="${map.jkrxm }" type="text" size="15"></td>
					<td><label>剩余还款月数:</label> <input class="textInput"
					name="filter_syhkysMin" value="${map.syhkysMin }" type="text" size="15"></td>
					<td><label>至</label> 
					<input class="textInput" name="filter_syhkysMax"
					value="${map.syhkysMax }" type="text" size="15"></td>
				</tr>
				<tr>
					<td><label>还款日:</label> 
					<select class="combox" name="filter_hkr">
						<option value="" <c:if test="${map.hkr==''}">selected</c:if>>全部</option>
						<option value="15" <c:if test="${map.hkr=='15'}">selected</c:if>>15日</option>
						<option value="30" <c:if test="${map.hkr=='30'}">selected</c:if>>30日</option>
					</select></td>
					<td><label>可用债权价值:</label> <input class="textInput"
						name="filter_sytjjeMin" value="${map.sytjjeMin }" type="text" size="15"></td>
					<td><label>至</label> 
					<input class="textInput" name="filter_sytjjeMax"
						value="${map.sytjjeMax }" type="text" size="15"></td>
				</tr>
				<tr>
					<td><label>中间人:</label>
					<input type="text"
							class="textInput" name="zhongjianren.name" size="15"
							value="${map.middleManName}" class="required" maxlength="22" 
							suggestFields="name" readonly="readonly"
							suggestUrl="${ctx }/jygl/suggestMiddleMan"
							lookupGroup="zhongjianren"/>
					</td>
					<td>
					<label>月利率%：</label> 
						<select class="combox" id="filter_dkll" name="filter_dkll">
							<option value="" <c:if test="">selected</c:if>>请选择</option>
							<c:forEach items="${dkllList}" var="per">
								<option value="${per.value }" <c:if test="${map.dkll eq per.value}">selected</c:if>>${per.name }%</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label>借款类型：</label> 
						<sen:select clazz="combox required" name="filter_backup01" coding="backup01" id="filter_backup01" value="${map.backup01 }"/>
					</td>
					<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">查询</button>
									</div>
								</div>
								<div class="button">
									<div class="buttonContent">
										<button type="button" class="close" warn="请选择债权"
											onclick="onCliMs(orgId)">选择带回</button>
									</div>
								</div></li>
						</ul>
					</div>
					</td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
		</div>
	</form>
</div>

<div class="pageContent">
	<table class="table" layoutH="200" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="3%"><input type="checkbox" class="checkboxCtrl"
					group="orgId" /></th>
				<th width="8%">借款人姓名</th>
				<th width="6%">还款方式</th>
				<th width="6%">中间人</th>
				<th width="10%">还款起始日期</th>
				<th width="6%">还款日</th>
				<th width="6%">还款期数</th>
				<th width="">可用期数</th>
				<th width="8%">借款用途</th>
				<th width="8%">借款类型</th>
				<th width="8%">月利率(%)</th>
				<th width="">原始债权价值(元)</th>
				<th width="">可推荐债权价值(元)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listZqtj}" var="mate" varStatus="st">
				<tr>
					<td><input type="checkbox" name="orgId" id="orgId${mate.ID }"
						value="${mate.ID }|${mate.JKRXM}|${mate.HK_WAY }|${mate.SQHKRQ }|${mate.SYHKYS}|${mate.JK_USE}|<fmt:formatNumber
							value="${mate.JKJE}" pattern="#00.00" />|<fmt:formatNumber
							value="${mate.KYZQJZ}" pattern="#00.00000" />|${mate.MIDDLE_MAN_NAME }|${mate.HKR }" onclick="dataClick(this)"/></td>
					<td>${mate.JKRXM}</td>
					<td>${mate.HK_WAY }</td>
					<td>${mate.MIDDLE_MAN_NAME }</td>
					<td>${mate.SQHKRQ}</td>
					<td>${mate.HKR }日</td>
					<td>${mate.SYQS }</td>
					<td>${mate.SYHKYS }</td>
					<td>${mate.JK_USE}</td>
					<td>
					<sen:vtoName coding="backup01" value="${mate.BACKUP01}"/>
					</td>
					<td>${mate.DKLL}</td>
					<td><fmt:formatNumber
							value="${mate.JKJE}" pattern="#,#00.00" /></td>
					<td><fmt:formatNumber
							value="${mate.KYZQJZ}" pattern="#,#00.00" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="tocheckbox">
		<input type="checkbox" name="orgId" value="" />
	</div>
	<script type="text/javascript">
	$("#tocheckbox").hide();
	resetValue(orgId);
		</script>
	<div class="panelBar">
	<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="dwzPageBreak({targetType:'dialog',data:{numPerPage:this.value}})">
				<option value="10"
					<c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20"
					<c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50"
					<c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100"
					<c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200"
					<c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select> <span>条，共${page.totalCount}条</span>
		</div>

		<div class="pagination" targetType="dialog"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }"></div>
	
	</div>
</div>