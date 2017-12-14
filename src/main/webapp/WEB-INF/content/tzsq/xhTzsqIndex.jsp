<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<script type="text/javascript">
	function fluchUploadFile(param){
		var uploadedFlag = top.document.getElementById("uploadedFlag");
		if ("1" == uploadedFlag.value) {
			navTabAjaxDone(param);
		}
		return true;
	}
	
	if($("#rqxz").val() == ""){
		$("#date").hide();
	}else{
		$("#date").show();
	}
	//判断"时间段"的select是否选择，选择后显示时间范围的input,为"请选择"时淡出时间范围.
	function hasDate(){
		var rqxz = $("#rqxz").val();
		if(rqxz == ""){
			$("#date").hide();
		}else{
			$("#date").show();
		}
	}
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhTzsq/listXhTzsq" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>出借编号：</label> <input type="text" name="filter_tzsqbh"
						value="${map.tzsqbh }" size="15"/></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" size="15"/></td>
					<td><label>状态：</label> <select class="combox"
						name="filter_state">
							<option value="" <c:if test="${map.state==''}">selected</c:if>>全部</option>
							<option value="0" <c:if test="${map.state=='0'}">selected</c:if>>暂存</option>
							<option value="1" <c:if test="${map.state=='1'}">selected</c:if>>待上传附件</option>
							<option value="8" <c:if test="${map.state=='8'}">selected</c:if>>待审批</option>
							<option value="2" <c:if test="${map.state=='2'}">selected</c:if>>审批通过</option>
							<option value="3" <c:if test="${map.state=='3'}">selected</c:if>>审批未通过</option>
							<option value="10" <c:if test="${map.state=='10'}">selected</c:if>>划扣失败</option>
							<option value="100" <c:if test="${map.state=='100'}">selected</c:if>>已划扣</option>
							<option value="200" <c:if test="${map.state=='200'}">selected</c:if>>已完结</option>
					</select></td>
					<td><label>CRM城市：</label>
					<sen:address names="filter_crmprovince,filter_crmcity" titles="所有省市,所有城市" values="${map.crmprovince},${map.crmcity}"/>
					 
					</td>
				</tr>
				<tr>
				<td><label>出借金额：</label> <input 
						name="filter_jhtzjeMin" value="${map.jhtzjeMin }" type="text" size="3">
					至
					<input  name="filter_jhtzjeMax"
						value="${map.jhtzjeMax }" type="text" size="3"></td>
					<td><label>营业部：</label> <select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${map.yyb==''}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.YYBID }"
									<c:if test="${map.yyb==md.YYBID}">selected</c:if>>${md.YYB}</option>
							</c:forEach>
					</select></td>
					<td><label>理财经理：</label> <input type="text" name="filter_crmName"
						value="${map.crmName }" size="15"/></td>
					<td>
					<label>时间段：</label>
					<select id="rqxz" class="combox" name="filter_date" onchange="hasDate();"><!-- 淡入淡出的js在此引用 -->
						<option value="" selected="selected">请选择</option>
						<option value="JHTZRQ"<c:if test="${map.filter_date=='JHTZRQ'}">selected</c:if>>出借日期</option>
						<option value="JHHKRQ"<c:if test="${map.filter_date=='JHHKRQ'}">selected</c:if>>划扣日期</option>
						<option value="MOQI"<c:if test="${map.filter_date=='MOQI'}">selected</c:if>>到账日期</option>
					</select>
					
					<p id="date" style="display: none">
					
					 <input 
						name="filter_startdate" value="${map.startdate }" type="text" size="8" class="date" readonly="readonly" />
					至
					<input   name="filter_enddate"
						value="${map.enddate }" type="text" size="8" class="date" readonly="readonly" />
						</p></td>
					<td>
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
	
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/xhTzsq/addXhTzsq"
				target="navTab"><span>新增出借申请</span></a></li>
		</ul>
		
	</div>
	<table class="table" width="1300" layoutH="141" nowrapTD="true">
		<thead>
			<tr>
				<th width="10px"></th>
				<th width="70px">客户编号</th>	
				<th width="60px">客户姓名</th>			
				<th width="130px">出借编号</th>
				<th width="70px">计划出借日期</th>
				<th width="80px">计划出借金额</th>
				<th width="55px">出借方式</th>
				<th width="55px">资金来源</th>
				<th width="100px">CRM城市</th>
				<th width="100px">营业部</th>
				<th width="70px">团队经理</th>
				<th width="100px">开发团队</th>
				<th width="130px">状态</th>
				<th width="120px">创建时间</th>
				<th width="40px">创建人</th>
				<th width="40px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${st.count}</td>
					<td style="text-align: right">${user.KHBM }</td>
					<td>${user.CJRXM }</td>
					<td>${user.TZSQBH}</td>
					<td>${user.JHTZRQ}</td>
					<td>￥<fmt:formatNumber
							value="${user.JHTZJE}" pattern="#,#00.00" /></td>
					<td>${user.TZCP_MC}</td>
					<td>
						<sen:vtoName coding="moneyComeType" value="${user.SQTYPE}"/>
					</td>
					<td>${user.CRMPROVINCENAME }|${user.CRMCITYNAME }</td>
					<td>${user.YYB }</td>
					<td>${user.EMPLOYEE_CCA_NAME }</td>
					<td>${user.KFTD }</td>
					<td>
					<c:if
							test="${empty user.OVERSTATE || user.OVERSTATE!='2'}">
						<c:if
							test="${empty user.HKSTATE || user.HKSTATE!='2'}">
							<c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待上传附件</c:if> <c:if
							test="${user.STATE=='3'}">审批未通过</c:if><c:if
							test="${user.STATE=='8'}">待审批</c:if><c:if
							test="${user.STATE=='2'}">审批通过</c:if>
							<c:if
							test="${user.STATE=='4'}">债权转让申请</c:if>
							<c:if
							test="${user.STATE=='10'}">划扣失败</c:if> 
							</c:if>
							<c:if test="${user.HKSTATE=='0'}">-待划扣</c:if> <c:if
							test="${user.HKSTATE=='2'}">已划扣</c:if>
						</c:if>
							<c:if
							test="${user.OVERSTATE=='2'}">已完结</c:if> 
					</td>
					
					<td>${user.CREATE_TIME}
					</td>
					<td>${user.CREATE_BY}</td>
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
						href="${ctx }/xhTzsq/lookOutTzsq/${user.ID}">查看</a> 
						</div>
		            </div>
		            
						<c:if
							test="${user.STATE=='0' || user.STATE=='1'}">
						<bjdv:validateContent type="1" funcId="投资申请-删除">	
						<div class="buttonActive">
				            <div class="buttonContent">
							<a title="删除" target="ajaxTodo"
								href="${ctx }/xhTzsq/delXhTzsq/${user.ID}">删除</a>
								</div>
		                </div>
		                </bjdv:validateContent>
		                </c:if>
						<c:if
							test="${user.STATE=='0' || user.STATE=='3' || user.STATE=='10'}">
							<bjdv:validateContent type="1" funcId="投资申请-编辑">	
		                <div class="buttonActive">
				            <div class="buttonContent">
							<a title="编辑" target="navTab"
								href="${ctx }/xhTzsq/editXhTzsq/${user.ID}">编辑</a>
								</div>
		                </div>
		                </bjdv:validateContent>
		                <bjdv:validateContent type="1" funcId="投资申请-提交">	
		                <div class="buttonActive">
				            <div class="buttonContent">
							<a title="提交待审批" target="ajaxTodo"
								href="${ctx }/xhTzsq/subXhTzsq/${user.ID}">提交</a>
							</div>
		                </div>
		                </bjdv:validateContent>
						</c:if>
						<c:if
							test="${user.STATE=='1'}">
							<bjdv:validateContent type="1" funcId="投资申请-上传附件">	
						<div class="buttonActive">
				       <div class="buttonContent">
							<a title="上传附件" href="${ctx }/xhTzsq/upLoadeFile?id=${user.ID}&flag=0"
						target="dialog" mask="true" width="800" height="500"
						close="fluchUploadFile" 
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"rel_listXhTzsq", "forwardUrl":"rel_listXhTzsq", "callbackType":""}'>上传</a>
						</div>
		               </div>
		               </bjdv:validateContent>
		               </c:if>
		               <c:if test="${user.STATE=='8'}">
		               <bjdv:validateContent type="1" funcId="投资申请-已传">	
		               <div class="buttonActive">
				       <div class="buttonContent">
							<a href="${ctx}/xhTzsq/downLoadFile?id=${user.ID}&flag=1" class="" target="dialog"
								title="已传材料" mask="true" width="600" height="420" 
								>已传</a>
						</div>
		               </div>
		               </bjdv:validateContent>
		               <bjdv:validateContent type="1" funcId="投资申请-补传">
		               <div class="buttonActive">
				       <div class="buttonContent">
							<a title="上传附件" href="${ctx }/xhTzsq/upLoadeFile?id=${user.ID}&flag=1"
						target="dialog" mask="true" width="800" height="500">补传</a>
						</div>
		               </div>
		               </bjdv:validateContent>
		                 </c:if>
		               <c:if
							test="${user.STATE=='2'}">
							<bjdv:validateContent type="1" funcId="投资申请-变更">
		             <div class="buttonActive">
				          <div class="buttonContent">
						<a title="变更申请" target="navTab"
						href="${ctx }/xhTzsq/changeTzsq/${user.ID}?lentcount=${user.LENTCOUNT}">变更</a>
						</div>
		             </div>
		              </bjdv:validateContent>
		              <bjdv:validateContent type="1" funcId="投资申请-债权下载">
		             <div class="buttonActive">
				       <div class="buttonContent">
							<a href="${ctx}/xhTzsq/downloadSqFile?id=${user.ID}" class="" target="dialog"
								title="债权文件" mask="true" width="600" height="420" 
								>债权文件</a>
						</div>
		               </div>
		             </bjdv:validateContent>
		             </c:if>
		             
		             <c:if
							test="${user.STATE=='2' && user.HKSTATE=='2' && user.OVERSTATE!='2'}">
							<bjdv:validateContent type="1" funcId="投资申请-赎回">
		             <div class="buttonActive">
							<div class="buttonContent">
						<a title="申请赎回" target="navTab"
						href="${ctx }/xhTzsq/overTzsq?id=${user.ID}">赎回</a>
						</div>
		             </div>
		             </bjdv:validateContent>
		           <bjdv:validateContent type="1" funcId="投资申请-账单下载">
		             <div class="buttonActive">
				       <div class="buttonContent">
							<a href="${ctx}/xhCapitalLoanReport/downReportFile?id=${user.ID}" class="" target="dialog"
								title="账单" mask="true" width="600" height="420" 
								>账单</a>
						</div>
		               </div>
		               </bjdv:validateContent>
	               
		             
		             </c:if>
		             <c:if
							test="${user.HKSTATE=='2'}">
						<bjdv:validateContent type="1" funcId="投资申请-结算复查">	
		             		<div class="buttonActive">
					            <div class="buttonContent">
								<a title="复查" target="ajaxTodo"
									href="${ctx }/xhTzsq/checkXhTzsqMoney/${user.ID}">复查</a>
									</div>
		                	</div>
		                </bjdv:validateContent>
		                </c:if>
		                 <c:if
							test="${user.HKSTATE=='2' && user.CREDITSTATE==null ||user.CREDITSTATE=='4'}">
		                <div class="buttonActive">
							<div class="buttonContent">
							<a target="ajaxTodo" title="是否确认转让?" href="${ctx }/xhTzsq/CreditApply/${user.ID}" >
							债权转让申请</a>
							</div>
						</div>
						</c:if>
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
