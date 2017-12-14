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
		action="${ctx }/cjrxx/listTzZxCjr" method="post">
		<div class="searchBar">
		<table>
				<tr>
					<td>
					<label>客户名：</label> <input type="text" name="filter_cjrxm"
					value="${map.cjrxm }" />
					</td>
					<td>
					<label>城市：</label> 
					<sen:address names="filter_province,filter_city" 
					titles="所有省份,所有城市" values="${map.province},${map.city}"/>
					</td>
					<td>
					<label>理财经理：</label> <input type="text" name="filter_crmName"
						value="${map.crmName }" />
					</td>
					<td>
					<label>客户来源：</label> 
					<sen:select clazz="combox" name="filter_khly" coding="personFrom" value="${map.khly }"/>
					</td>
				</tr>
			</table>
			<%-- <ul class="searchContent">
			
				<li><label>客户名：</label> <input type="text" name="filter_cjrxm"
					value="${map.cjrxm }" /></li>
					<li><label>CRM城市：</label> 
					<sen:address names="filter_crmprovince,filter_crmcity" 
					titles="所有省份,所有城市" values="${map.crmprovince},${map.crmcity}"/></li>
					
					<li><label>理财经理：</label> <input type="text" name="filter_crmName"
						value="${map.crmName }" /></li>
					
					<li><label>客户来源：</label> 
					<sen:select clazz="combox" name="" coding="personFrom"/>
					</li>
			</ul> --%>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/cjrxx/addTzzx" target="navTab"><span>新增出借咨询</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80" >客户名</th>
				<th width="80">证件号码</th>
				<th width="80">咨询状态</th>
				<th width="80">客户状态</th>
				<th width="80">客户来源</th>
				<th width="80">销售人员</th>
				<th width="80">所属团队</th>
				<!-- <th width="100">意向</th> -->
				<th width="100" orderField="createTime">创建日期</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listCjrxx}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td><input name="ids" value="${user.ID}" type="checkbox"></td>
					<td>${user.CJRXM }</td>
					<td>${user.ZJHM }</td>
					<td><c:if test="${user.CJRSTATE=='0'}">出借咨询</c:if> <c:if
							test="${user.CJRSTATE=='1'}">已出借</c:if></td>
					<td><c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待开户审批</c:if> <c:if
							test="${user.STATE=='2'}">已开户</c:if> <c:if
							test="${user.STATE=='3'}">开户拒绝</c:if><c:if
							test="${user.STATE=='8'}">待开户</c:if></td>
					<!-- <td>${user.CJRYX }</td>-->
					<td>${user.KHLY }</td>
					<td>${user.EMPLOYEE_CRM_NAME}</td>
					<td>${user.KFTD }</td>
					<td>${user.CREATETIME }</td>
					
					<td>
							<div class="buttonActive">
							  <div class="buttonContent">
								  <button onclick="return showOrHide(this);">操作</button>
							  </div>
							</div>
							
							
							<div  class='OperationsPopUp' style='display: none; position: absolute;  
						
								  text-align: justify; font-size: 12px;height:40px;width:300px;  
								  filter:alpha(opacity=100);
								  opacity:0.9;
								  background:#fff;
								  border:2px orange solid;
								  padding:20px;
								  padding-left:20px;
								  word-wrap:break-word;
								  line-height:100%;
							'>
					<c:if test="${user.CJRSTATE == '0' }">
					<div class="buttonActive">
				       <div class="buttonContent">
							<a title="编辑" target="navTab"
								href="${ctx }/cjrxx/editTzzx/${user.ID}" >编辑</a>
								</div>
		             </div>
					<c:if test="${user.ZJHM != null && user.ZJHM != '' }">
							 <div class="buttonActive">
				      			 <div class="buttonContent">
								<a title="转正" target="ajaxTodo"
									href="${ctx }/cjrxx/toCjrTzzx/${user.ID}">转正式出借</a>
									</div>
		            		 </div>
					</c:if>
						</c:if>
					<div class="buttonActive">
				       <div class="buttonContent">
						 <a title="咨询沟通" target="navTab"
						href="${ctx }/xhCjrgtjl/listXhCjrgtjl?cjrxx_id=${user.ID}&cjrState=0">咨询沟通</a>
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
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
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
