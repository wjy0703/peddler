<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<script type="text/javascript">
	function closedialoghj(param){
		var uploadedFlag = top.document.getElementById("uploadedFlag");
		if ("1" == uploadedFlag.value) {
			navTabAjaxDone(param);
		}
		return true;
	}
</script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form id="jksqListForm" name="jksqListForm" rel="pagerForm" 
		onsubmit="return navTabSearch(this);" action="${ctx }/jksq/listJksqExpire" method="post">
		<input type="hidden" id="type" name="type" value="${map.type }"/>
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>客户姓名:</label> 
						<input type="text" name="filter_jkrxm" value="${map.jkrxm }"/>
					</td>
					<td><label>客户电话:</label> 
						<input type="text" name="filter_telephone" value="${map.telephone }"/>
					</td>
					<td><label>证件号码:</label> 
						<input type="text" name="filter_zjhm" value="${map.zjhm }"/>
					</td>
					<td><label>产品:</label> 
						<select class="combox" name="filter_jkType">
							<option value="all">全部</option>
							<c:forEach items="${jkTypeList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${map.jkType==attr.value}">selected="selected" </c:if>>${attr.description }
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>借款状态:</label> 
						<select class="combox" name="filter_state_injksp">
							<OPTION value="61,100,101,102,103" <c:if test="${map.state_injksp==''}">selected</c:if>>所有状态</OPTION>
							<OPTION value="61" <c:if test="${map.state_injksp=='61'}">selected</c:if>>已放款</OPTION>
							<c:forEach items="${attrList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${map.state_injksp==attr.value}">selected="selected" </c:if>>${attr.keyName }
								</option>
							</c:forEach>
						</select>
					</td>
					<td><label>所属城市:</label> 
						<select name="filter_province" ref="jksqlistbox_city" class="combox" 
							refUrl="${ctx}/cjrxx/getCity?code={value}" >
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select>
						<select id="jksqlistbox_city" name="filter_city" class="combox" 
							 refUrl="${ctx}/cjrxx/getArea?code={value}" >
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${city}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.city==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select>
					</td>
					<td><label>团队经理:</label> 
						<input type="text" name="filter_teamName" value="${map.teamName }"/>
					</td>
					<td><label>客户经理:</label> 
						<input type="text" name="filter_saleName" value="${map.saleName }"/>
					</td>
				</tr>
				<tr>
					<td>
					<label>进件时间:</label>
					<input type="text" name="filter_startDate" class="date" style="width: 65px" readonly="true" value="${map.startDate }"/>
					至
					<input type="text" name="filter_endDate" class="date" style="width: 65px" readonly="true" value="${map.endDate }"/>
					</td>
					<td >
					<label>门店:</label>
					<input id="organiId" name="filter_organi.id" type="hidden" value="${organiId}"/>
					<input name="filter_organi.name" type="text" value="${organiName }" onblur="cleanMd(this.value)"/>
					<a href="${ctx }/loan/getMdList" lookupGroup="filter_organi" style=""><span style="color: #7F7F7F;">选择门店</span></a>
					</td>
					<td>
					</td>
					<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">检索</button>
									</div>
								</div></li>
							<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
						</ul>
					</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
<!-- 	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jksq/addJksq" target="navTab"><span>新增借款申请</span></a></li>
		</ul>
	</div> -->
	<table class="table" width="100%" layoutH="138" >
		<thead>
			<tr>
				<th width="60" >借款编号</th>
				<th width="60">共借人</th>
				<th width="60">客户姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">状态</th>
				<th width="80">门店</th>
				<th width="60">产品</th>
				<th width="80">合同金额(元)</th>
				<th width="80">放款金额(元)</th>
				<th width="60">团队经理</th>
				<th width="60">客户经理</th>
				<th width="50" align="center">期数(月)</th>
				<th width="80" align="center">起始还款日期</th>
				<th width="80" align="center">到期日期</th>
				<th width="80" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr  target="sid_jksq" rel="${jksq.ID}">
					<td>${jksq.LOAN_CODE }</td>
					<td>${jksq.TOGETHER_PERSON}</td> 
					<td>${jksq.JKRXM }</td>
					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					
					<c:choose>
							<c:when test="${fn:contains(jksq.STATEINFO , '外')}">
						<td style="color:orange;font-weight:bold;font-size:12">${jksq.STATEINFO  }</td>
					</c:when>
					<c:when test="${fn:contains(jksq.STATEINFO , '待')}">
						<td style="color:blue;font-weight:bold;font-size:12">${jksq.STATEINFO  }</td>
					</c:when>
			
					<c:when test="${fn:contains(jksq.STATEINFO , '暂')}">
						<td style="color:orange;font-weight:bold">${jksq.STATEINFO  }</td>
					</c:when>
					<c:when test="${fn:contains(jksq.STATEINFO , '拒')}">
						<td style="color:red;font-weight:bold">${jksq.STATEINFO  }</td>
					</c:when>
					<c:when test="${fn:contains(jksq.STATEINFO , '已放款')}">
						<td style="color:green;font-weight:bold">${jksq.STATEINFO  }</td>
					</c:when>
				  
					<c:otherwise><td style="color:red;font-weight:bold">${jksq.STATEINFO}</c:otherwise>
					</c:choose>
					<td>${jksq.YYB}</td>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>
						<c:if test='${null != jksq.HTJE && jksq.HTJE != 0 }'>￥<fmt:formatNumber
								value="${jksq.HTJE }" pattern="#,#00.00" />
						</c:if>
					</td>
					<td>
						<c:if test='${null != jksq.FKJE && jksq.FKJE != 0 }'>￥<fmt:formatNumber
								value="${jksq.FKJE}" pattern="#,#00.00" />
						</c:if>
					</td>
					<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td>
					<td>${jksq.HKQS } 期</td>
					<td>${jksq.QSHKRQ}</td>
					<td>${jksq.DQRQ }</td>
					<td>
				<!--  <a title="逾期" target="ajaxTodo" href="${ctx }/jksq/jksqSettle/${jksq.ID}?state=103" class="btnEdit">逾期</a>
					<a title="还款中" target="ajaxTodo" href="${ctx }/jksq/jksqSettle/${jksq.ID}?state=100" class="btnSelect">还款中</a>
					<a title="提前结清" target="ajaxTodo" href="${ctx }/jksq/jksqSettle/${jksq.ID}?state=101" class="btnAdd">提前结清</a>
					<a title="正常结清" target="ajaxTodo" href="${ctx }/jksq/jksqSettle/${jksq.ID}?state=102" class="btnAdd">正常结清</a>-->	
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
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
