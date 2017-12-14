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
		action="${ctx }/cjrxx/listcjrxx" method="post">
		<div class="pageFormContent">
			<table width="100%">
				<tr>
					<td><label>客户编号：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					
					<td><label>客户状态：</label> <select class="combox"
						name="filter_state">
							<option value="" <c:if test="${map.state==''}">selected</c:if>>全部</option>
							<option value="0" <c:if test="${map.state=='0'}">selected</c:if>>暂存</option>
							<option value="1" <c:if test="${map.state=='1'}">selected</c:if>>待开户审批</option>
							<option value="2" <c:if test="${map.state=='2'}">selected</c:if>>已开户</option>
							<option value="3" <c:if test="${map.state=='3'}">selected</c:if>>开户拒绝</option>
							<option value="8" <c:if test="${map.state=='8'}">selected</c:if>>待开户</option>
							
					</select></td>
				</tr>
				<tr>
					<td><label>CRM城市：</label> 
					<sen:address names="filter_crmprovince,filter_crmcity" 
					titles="所有省份,所有城市" values="${map.crmprovince},${map.crmcity}"/>
					</td>
					<td><label>营业部：</label> <select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${map.yyb==''}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.YYBID }"
									<c:if test="${map.yyb==md.YYBID}">selected</c:if>>${md.YYB}</option>
							</c:forEach>
					</select></td>
				<!-- 
				<td><label>CRM城市：</label> <select class="combox"
						name="filter_crmprovince" ref="combox_cjrcrmcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value=""
								<c:if test="${map.crmprovince==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.crmprovince==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_crmcity"
						id="combox_cjrcrmcity">
							<option value="" <c:if test="${map.crmcity==''}">selected</c:if>>所有城市</option>
					</select></td>
				 -->
				<td><label>理财经理：</label> <input type="text" name="filter_crmName"
						value="${map.crmName }" /></td>
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
	<div class="panelBar">
		<bjdv:validateContent type="1" funcId="出借人-新增出借">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/cjrxx/addcjrxx" target="navTab"><span>新增出借人</span></a></li>
		</ul>
		</bjdv:validateContent>
	</div>
	<table class="table" width="100%" layoutH="160" nowrapTD="false">
		<thead>
			<tr>
				<th width="28"></th>
				<th width="70">客户名</th>
				<th width="80">客户编号</th>
				<th width="70">省份</th>
				<th width="70">CRM城市</th>
				<th width="120">营业部</th>
				<th width="70">团队经理</th>
					<th width="70">理财经理</th>
					<th width="100">开发团队</th>
				<th width="80">状态</th>
				<th width="100" orderField="createTime">创建日期</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listCjrxx}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${st.count}</td>
					<td>${user.CJRXM }</td>
					<td style="text-align: right">${user.KHBM }</td>
					<td>${user.CRMPROVINCENAME }</td>
					<td>${user.CRMCITYNAME }</td>
					<td>${user.YYB }</td>
					<td>${user.EMPLOYEE_CCA_NAME }</td>
					<td>${user.EMPLOYEE_CRM_NAME }</td>
					<td>${user.KFTD }</td>
					<td><c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待开户审批</c:if> <c:if
							test="${user.STATE=='2'}">已开户</c:if> <c:if
							test="${user.STATE=='3'}">开户拒绝</c:if><c:if
							test="${user.STATE=='8'}">待开户</c:if></td>
					<td>${user.CREATETIME }</td>
					<td>
					<div class="buttonActive">
							<div class="buttonContent">
								<button onclick="return showOrHide(this);">操作</button>
							</div>
						</div>
					
					<div  class='OperationsPopUp' style='display: none; position: absolute;  
						
						text-align: justify; font-size: 12px;height:40px;width:300px;  filter:alpha(opacity=90);
    opacity:0.6;
    background:#fff;
    border:2px orange solid;
    padding:20px;
    padding-left:20px;
   word-wrap:break-word;
    line-height:100%;
'>
					<div class="buttonActive">
				       <div class="buttonContent">
					<a title="查看" target="navTab"
						href="${ctx }/cjrxx/lookOutCjrxx/${user.ID}">查看</a> 
						</div>
		             </div>
		             <c:if
							test="${user.CREATE_BY==map.loginName}">
						<c:if
							test="${user.STATE=='0' || user.STATE=='3'}">
							<div class="buttonActive">
				       <div class="buttonContent">
							<a title="编辑" target="navTab"
								href="${ctx }/cjrxx/editcjrxx/${user.ID}">编辑</a>
								</div>
		               </div>
		               <div class="buttonActive">
				       <div class="buttonContent">
							<a title="上传附件" href="${ctx }/cjrxx/upLoadeTest/${user.ID}"
						target="dialog" mask="true" width="800" height="500">上传附件</a>
						</div>
		               </div>
		               </c:if> 
		               </c:if> 
						<c:if test="${user.STATE=='8' }">
		               <div class="buttonActive">
				       <div class="buttonContent">
							<a title="开户申请" target="navTab"
								href="${ctx }/cjrxx/openAccCjrxx/${user.ID}">开户申请</a>
								</div>
		               </div>
						</c:if> 
						<c:if test="${user.STATE=='2' }">
						<bjdv:validateContent type="1" funcId="出借人-投资申请">
						 <div class="buttonActive">
				       <div class="buttonContent">
							<a title="出借申请" target="navTab"
								href="${ctx }/cjrxx/tzsqCjrxx/${user.ID}">出借申请</a>
								</div>
		               </div>
		               </bjdv:validateContent>
		               
		               <bjdv:validateContent type="1" funcId="出借人-信息补充">
							<div class="buttonActive">
					       <div class="buttonContent">
							<a title="信息补充" target="navTab"
							href="${ctx }/cjrxx/cjrxxComplement?cjrxx_id=${user.ID}">信息补充</a>
							</div>
			               </div>
		               </bjdv:validateContent>
		               
			               <c:if test="${user.UPSTATE!='1'}">
			               <bjdv:validateContent type="1" funcId="出借人-变更申请">
						<div class="buttonActive">
				             <div class="buttonContent">
							<a title="变更申请" target="navTab"
								href="${ctx }/cjrxx/messChangeCjrxx/${user.ID}">变更申请</a>
							 </div>
		                </div>
		                </bjdv:validateContent>
						</c:if> 
						<c:if test="${user.UPSTATE=='0'}">
							<div class="buttonActive">
				             <div class="buttonContent">
							<a title="提交待审批" target="ajaxTodo"
								href="${ctx }/cjrxx/subMessChange/${user.ID}">变更申请-提交</a>
								</div>
		                	</div>
						</c:if>
						</c:if> 
						<c:if
							test="false">
							<div class="buttonActive">
				       <div class="buttonContent">
							<a title="提交待审批" target="ajaxTodo"
								href="${ctx }/cjrxx/subCjrxx/${user.ID}">提交</a>
								</div>
		               </div>
		               <div class="buttonActive">
				       <div class="buttonContent">
							<a title="首期债权" target="navTab"
								href="${ctx }/cjrxx/firstZqCjrxx/${user.ID}">首期债权</a>
							</div>
		               </div>
						</c:if> 
						
						<bjdv:validateContent type="1" funcId="出借人-咨询沟通">
							<div class="buttonActive">
					       <div class="buttonContent">
							<a title="咨询沟通" target="navTab"
							href="${ctx }/xhCjrgtjl/listXhCjrgtjl?cjrxx_id=${user.ID}&cjrState=1">咨询沟通</a>
							</div>
			               </div>
		               </bjdv:validateContent>
		               
		               
		               <div class="divider"></div>
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
