<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanApply/listXhCarLoanApplyAuditOver/${type}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>申请人姓名:</label>
				<input type="text" name="filter_userName" value="${map.userName}"/>
			</li>
			<li>
				<label>申请日期:</label>
				<input type="text" name="filter_jk_loan_date" class="date" yearstart="-113" yearend="5" format="yyyy-MM-dd" size="15" value="${map.jk_loan_date}" readonly="readonly" /> <a
				class="inputDateButton" href="javascript:;">选择</a>
			</li>
			<li>
				<label>借款类型:</label>
			<sen:select clazz="combox"  coding="carType" name="filter_jkType" value="${map.jkType }" />
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="40" >序号</th>
				<th width="60" >申请人姓名</th>
				<th width="100">借款编号</th>
			
				<th width="80" >借款申请额度</th>
				<th width="80"  >借款周期</th>
				
				<th width="80" >借款类型</th>
				<th width="80" >申请日期</th>
				<th width="80" >管辖省份</th>
				
				<th width="80" >申请状态</th>
				
				<th width="80" >是否展期</th>
				<th width="80" >车牌号码</th>
				<th width="80" >审批结果</th>
				
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${xhCarLoanApply}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.ID}">
				<td>${st.count}</td>
				
				<td>${user.USER_NAME}</td> 
				<td>${user.LOAN_CODE}</td>
				<td>${user.JK_LOAN_QUOTA}</td>
				
				<td>${user.JK_CYCLE}</td>
				
				<td><sen:vtoName coding="carType" value="${user.JK_TYPE }"/></td>
				<td>${user.JK_LOAN_DATE}</td>
				<td>${user.CRMPROVINCENAME}</td> 
				
				<td><sen:carStateToName value="${user.STATE}"/></td>
				
				<td><sen:vtoName coding="isExtension" value="${user.IS_EXTENSION }" /></td>
				<td>${user.PLATE}</td>
				<td>
				<c:if test="${user.CREDIT_RESULT==1 }">审批通过</c:if>
				<c:if test="${user.CREDIT_RESULT==0 }">审批拒绝</c:if>
				<c:if test="${user.CREDIT_RESULT==2 }">审批退回</c:if>
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
					
					<div class="buttonActive">
						<div class="buttonContent">
							<a title="查看车借申请信息" target="navTab" href="${ctx }/xhCarLoanApply/editXhCarLoanApplyLook/${user.ID}" >查看</a>
						</div>
		            </div>	
		            
		            <div class="buttonActive">
			            <div class="buttonContent">	
						  <a href="${ctx}/carFiles/downLoadFilePage/${user.ID}/all"
											class="" target="dialog" title="下载材料" mask="true" width="600"
											height="420">下载</a> 
					    </div>
			        </div>
		            <div class="buttonActive">
						<div class="buttonContent">	
							<a href="${ctx }/CarState/listCarStateHistory/${user.ID}"   mask="true" 
								title="历史状态" lookupGroup="authoritys">历史</a>
						</div>
		            </div>	
		            </div>
				</td>
			</tr>
	
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
				<option value="10" <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20" <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50" <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>
