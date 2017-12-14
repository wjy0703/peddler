<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/loan/listLoanApplyByState/30" method="post">
		<div class="searchBar">
			<table  width="100%">
				<tr>
					<td><label>客户姓名:</label> <input type="text"
						name="filter_jkrxm" value="${map.jkrxm }" /></td>
					<td><label>客户电话:</label> <input type="text"
						name="filter_telephone" value="${filter_telephone }" /></td>
					<td><label>证件号码:</label> <input type="text" name="filter_zjhm"
						value="${filter_zjhm }" /></td>
				</tr>
				<tr>

					<td><label>所属城市:</label>
					 	<sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${filter_province},${filter_city}"/>
					</td>
					<td><label>团队经理:</label> <input type="text"
						name="filter_teamName" value="${filter_teamName }" /></td>
					<td><label>销售人员:</label> <input type="text"
						name="filter_saleName" value="${filter_saleName }" /></td>
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
					<label>客户类型:</label>
					<sen:select clazz="combox required" name="filter_customerType" coding="customerType" id="customerType" value="${map.customerType}" title="全部" titleValue=""/>
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
	<div class="panelBar">
		<!--<ul class="toolBar">
			<li><a class="add" href="${ctx}/jksq/addJksq" target="navTab"><span>新增借款申请</span></a></li>
		</ul>-->
	</div>

	<table class="table" width="100%" layoutH="166" >
		<thead>
			<tr>
				
				<th width="40">共借人</th>
				<th width="40">客户姓名</th>
				
				<th width="100">证件号码</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">门店</th>
				<th width="80">产品</th>
				<th width="60">是否加急</th>
				<th width="60">申请金额</th>
				<th width="30">分期</th>
				<th width="60">团队经理</th>
				<th width="60">销售人员</th>
				<th width="120">进件时间</th>
				

				<th width="60">客户类型</th>
				<th width="40" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr target="sid_jksq" rel="${jksq.ID}">
					
					<td>${jksq.TOGETHER_PERSON}</td>
					<td>${jksq.JKRXM }</td>
					<td>${jksq.ZJHM }</td>

					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					<td>${jksq.YYB}</td>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>${jksq.ENGLISH_NAME}</td>
				<td><c:if test='${null != jksq.JK_LOAN_QUOTA && jksq.JK_LOAN_QUOTA != 0 }'><fmt:formatNumber
								value="${jksq.JK_LOAN_QUOTA }" pattern="#,#00.00" />
						</c:if></td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td>
					<td>${jksq.BACKUP02}</td>
					<td>
					<sen:vtoName value="${jksq.JKSQ_TYPE}" coding="customerType"/>
					</td>
					


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
					<a id="freshJksp" href="${ctx }/xhNewJksq/lookXhJksq/${jksq.ID}?look=1" target="navTab" 
						title="查看借款申请信息" rel="rel_jksqShow">查看</a> 
					| <a href="${ctx }/jksq/jksqHistoryList/${jksq.ID}" rel="rel_jksqhis_state"  mask="true" 
						title="历史状态" lookupGroup="authoritys">历史</a> 
					
					| <a href="${ctx}/loan/downLoadFile/${jksq.ID},sx" class=""
						target="dialog" title="下载材料" mask="true" width="600"
						height="420">下载</a> 
					| <a title="信审任务分派 "
						href="${ctx}/creditAudit/selectCreditAuditPerson/${jksq.ID}"
						target="navTab" >分派</a>
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
