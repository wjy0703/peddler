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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanApply/listXhCarLoanApply" method="post">
	<div class="searchBar">
	
		<table width="100%">
			<tr>	
				<td>
					<label>申请人姓名:</label>
					<input type="text" name="filter_userName" value="${map.userName}"/>
				</td>
				<td>
					<label>借款类型:</label>
					
					<sen:select clazz="combox"  coding="carType" name="filter_jkType" value="${map.jkType }" title="全部"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>借款编号:</label>
					<input type="text" name="filter_loanCode" value="${map.loanCode}"/>
				</td>
				<td>
					<label>申请状态:</label>
					
					<sen:carStateSelected clazz="combox" name="filter_state" value="${map.state}" title="全部"/>
				</td>
			
			<td>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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
	<%-- <div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/xhCarLoanApply/addXhCarLoanApply" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx }/xhCarLoanApply/batchdelXhCarLoanApply" class="delete" warn="请至少选择一个"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/xhCarLoanApply/editXhCarLoanApply/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div> --%>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
				
				<th width="40"  >序号</th>
				<th width="120"  >借款编号</th>
				<th width="80"   >申请人姓名</th>
				<th width="80"   >借款申请额度</th>
				<th width="80"   >借款成数</th>
				<th width="80"  >借款周期</th>
				<th width="80"  >借款类型</th>
				<th width="80"   >申请日期</th>
				<th width="80"  >管辖省份</th>
				<th width="80"   >申请状态</th>
				<th width="80"  >是否展期</th>
				<th width="80"   >是否已展期</th>
				<th width="80"   >车牌号码</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${xhCarLoanApply}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.ID}">
				
				<td>${st.count}</td>
				<td>${user.LOAN_CODE}</td>
				<td>${user.USER_NAME}</td> 
				<td>${user.JK_LOAN_QUOTA}</td>
				<td>${user.LOAN_SCALE}%</td>
				<td>${user.JK_CYCLE}</td>
				<td><sen:vtoName coding="carType" value="${user.JK_TYPE }" /></td>
				<td>${user.JK_LOAN_DATE}</td>
				<td>${user.CRMPROVINCENAME}</td> 
				<td><sen:carStateToName value="${user.STATE}"/></td>
				<td><sen:vtoName coding="isExtension" value="${user.IS_EXTENSION }" /></td>
				<td><sen:vtoName coding="isHaveextension" value="${user.IS_HAVEEXTENSION }"/></td>
				<td>${user.PLATE}</td>
				
				
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
		        <!-- 判断是展期申请编辑还是车借普通申请编辑 -->
		            <c:choose>
		               <c:when test="${user.editable == true and user.IS_EXTENSION==1}">
			            <div class="buttonActive">
							<div class="buttonContent">
								<a title="编辑车借展期申请信息" target="navTab" href="${ctx }/xhCarLoanApply/editExtension/${user.ID}" >编辑</a>
							</div>
			            </div>
			            </c:when>
			             <c:when test="${user.editable == true and user.IS_EXTENSION==0}">
			            <div class="buttonActive">
							<div class="buttonContent">
								<a title="编辑车借申请信息" target="navTab" href="${ctx }/xhCarLoanApply/editXhCarLoanApply/${user.ID}" >编辑</a>
							</div>
			            </div>
			            </c:when>
		            </c:choose>
		        
		        <c:if test="${user.STATE == '02'}">
			        <div class="buttonActive">
						<div class="buttonContent">
							<a href="${ctx}/carFiles/applyUploadPage/${user.ID}" 
											target="dialog" title="上传车借材料" mask="true" width="600"
											height="420" param='{"statusCode":"200", "message":"操作成功", "navTabId":"rel_listXhCarLoanApply","forwardUrl":"rel_listXhCarLoanApply", "callbackType":""}'>上传</a>
						</div>
			        </div> 
		        </c:if>
		    <c:if test="${user.refuse == false}">   
		        <div class="buttonActive">  
		            <div class="buttonContent">
					  <a href="${ctx}/carFiles/applyUploadPageAny/${user.ID}" 
									target="dialog" title="补传车借材料" mask="true" width="600"
									height="420" param='{"statusCode":"200", "message":"操作成功", "navTabId":"rel_listXhCarLoanApply","forwardUrl":"rel_listXhCarLoanApply", "callbackType":""}'>补传</a>
				    </div>
				</div>
		    </c:if>  
		       
				<c:if test="${user.STATE == '519' }">
		        		<div class="buttonActive">
							<div class="buttonContent">	
		
									<a href="${ctx }/xhCarLoanApply/carQianShuInput/${user.ID}" rel="rel_jksqQianShu"
											target="navTab" title="签署">签署</a>
							</div>
						</div>
				</c:if>
				
				 <c:if test="${(user.STATE=='61' or user.STATE=='92') and user.IS_HAVEEXTENSION=='0'}">
		               <div class="buttonActive">
						<div class="buttonContent">
							<a title="申请展期" target="navTab" href="${ctx }/xhCarLoanApply/makeExtension/${user.ID}" >展期</a>
						</div>
		            </div>	
		          </c:if>
			    
			    <c:if test="${user.STATE == '520'}">
			        <div class="buttonActive">
						<div class="buttonContent">
							<a href="${ctx}/carFiles/contactUploadPage/${user.ID}" 
											target="dialog" title="上传合同材料" mask="true" width="600"
											height="420" param='{"statusCode":"200", "message":"操作成功", "navTabId":"rel_listXhCarLoanApply","forwardUrl":"rel_listXhCarLoanApply", "callbackType":""}'>上传</a>
						</div>
			        </div> 
		        </c:if>
		        
		        <c:if test="${user.agreement == true}">
			        <div class="buttonActive">
						<div class="buttonContent">
							<a href="${ctx}/xhCarLoanContract/isAgreementLook/${user.ID}" 
											target="navTab" title="协议查看" >协议查看</a>
						</div>
			        </div> 
		        </c:if>
		        
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
