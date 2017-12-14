<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/loan/listJksqCredit" method="post">
		<div class="searchBar">
			<table  width="100%">
				<tr>
					<td><label>客户姓名:</label> <input type="text"
						name="filter_jkrxm" value="${filter_jkrxm }" /></td>
					<td><label>客户电话:</label> <input type="text"
						name="filter_telephone" value="${filter_telephone }" /></td>
					<td><label>证件号码:</label> <input type="text" name="filter_zjhm"
						value="${filter_zjhm }" /></td>
				</tr>
				<tr>

					<td><label>所属城市:</label> <select name="filter_province"
						ref="jksqlistbox_city" class="combox"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${filter_province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select id="jksqlistbox_city" name="filter_city" class="combox"
						refUrl="${ctx}/cjrxx/getArea?code={value}">
							<option value="" <c:if test="${filter_city==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${city}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_city==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select></td>
					<td><label>团队经理:</label> <input type="text"
						name="filter_teamName" value="${filter_teamName }" /></td>
					<td><label>销售人员:</label> <input type="text"
						name="filter_saleName" value="${filter_saleName }" /></td>
				</tr>
				<tr>
					<td>
					<label>进件时间:</label>
					<input type="text" name="filter_startDate" class="date" style="width: 65px" readonly="true" value="${filter_startDate }"/>
					至
					<input type="text" name="filter_endDate" class="date" style="width: 65px" readonly="true" value="${filter_endDate }"/>
					</td>
					<td>
					</td>
					<td></td>
				</tr>
			</table>
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
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="162" >
		<thead>
			<tr>
				
				<th width="40" align="center">共借人</th>
				<th width="40">客户姓名</th>
				<th width="100">证件号码</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">产品</th>
				<th width="60">是否加急</th>
				<th width="60">申请金额</th>
				<th width="30">分期</th>
				<th width="60">团队经理</th>
				<th width="60">销售人员</th>
				<th width="120">审批时间</th>
				<th width="40" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksqCredit }" var="jksq" varStatus="st">
				<tr target="sid_jksq" rel="${jksq.ID}">
					<td><sen:showTogether  lendId="${jksq.ID}" yesOrNo="${jksq.ISTOGETHER}"/></td>
					
					<td>${jksq.JKRXM }</td>
					<td>${jksq.ZJHM }</td>

					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>${jksq.ENGLISH_NAME}</td>
				<td><c:if test='${null != jksq.JK_LOAN_QUOTA && jksq.JK_LOAN_QUOTA != 0 }'><fmt:formatNumber
								value="${jksq.JK_LOAN_QUOTA }" pattern="#,#00.00" />
						</c:if></td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.KHMC }</td>
					<td>${jksq.SALENAME }</td>
					<td>${jksq.CREATE_TIME}</td>
					<td>
					<div class="buttonActive">
							<div class="buttonContent">
								<button onclick="return showOrHide(this);">操作</button>
							</div>
						</div>
					
					<div  class='OperationsPopUp' style='display: none; position: absolute; text-align: justify; font-size: 12px;height:40px;width:300px;  filter:alpha(opacity=100);
					    opacity:0.9;
					    background:#fff;
					    border:2px orange solid;
					    padding:20px;
					    padding-left:20px;
					   word-wrap:break-word;
					    line-height:100%;
					'>
							<a id="freshJksp" href="${ctx }/loan/getJksqCredit/${jksq.ID}" target="navTab" 
						title="信审结果变更" rel="rel_getJksqCredit">变更</a>
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

		<div class="pagination" targetType="navTab"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>
