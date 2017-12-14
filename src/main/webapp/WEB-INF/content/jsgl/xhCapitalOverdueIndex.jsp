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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCapitalOverdue/listXhCapitalOverdue" method="post">
	<div class="searchBar">
		<table width="100%">
			<tr>
				<td>
					<label>客户名称:</label>
					<input type="text" name="filter_lenderName" value="${map.lenderName}"/>
				</td>
				<td>
					<label>客户身份证号:</label>
					<input type="text" name="filter_lenderIdCard" value="${map.lenderIdCard}"/>
				</td>
				<td>
					<label>客户编号:</label>
					<input type="text" name="filter_lenderNumber" value="${map.lenderNumber}"/>
				</td>
				<td>
					<label>逾期时间:</label>
					<input type="text" name="filter_overdueDate" class="date" dateFmt="yyyy-MM" readonly="true" value="${map.overdueDate }"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>逾期状态:</label>
					<sen:select clazz="combox required" name="filter_overdueStatr" coding="capitalOverdue" id="zjlx" value="${map.overdueStatr}" title="全部" titleValue=""/>
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
		</table>
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
	<div class="panelBar">
		<ul class="toolBar">
		
			<li><a class="edit" href="${ctx}/xhCapitalOverdue/editXhCapitalOverdue/{sid_user}?type=4" target="dialog" mask="true" width="450" warn="请选择一条记录" close="closeDialogFreshen"><span>挂账</span></a></li>
			<li><a class="edit" href="${ctx}/xhCapitalOverdue/editXhCapitalOverdue/{sid_user}?type=5" target="dialog" mask="true" width="450" warn="请选择一条记录" close="closeDialogFreshen"><span>减免</span></a></li>
			<!-- <li><a class="edit" href="${ctx}/xhCapitalOverdue/editXhCapitalOverdue/{sid_user}" target="navTab" warn="请选择一條記錄"><span>取消呆死账</span></a></li>
			
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			 -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="164">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80">合同编号</th>
				<th width="80">客户名称</th>
				<th width="80">客户编号</th>
				<th width="80">客户身份证号</th>
				<!-- 
				<th width="80">银行名称</th>
				<th width="80">银行开户行</th>
				<th width="80">银行账号</th>
				 -->
				<th width="80">逾期时间</th>
				<th width="80">逾期天数</th>
				<th width="80">逾期金额</th>
				<th width="160">违约金及罚息总计</th>
				<th width="160">应追回款项共计</th>
				<th width="160">实追回款项共计</th>
				<th width="80">挂账金额</th>
				<th width="80">减免金额</th>
				<th width="80">减免人</th>
				<th width="80">状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.loanContract.jkhtbm}</td>
				<td>${user.lenderName}</td>
				<td>${user.lenderNumber}</td>
				<td>${user.lenderIdCard}</td>
				<!-- 
				<td><sen:vtoName value="${user.bankName}" coding="bank"/> </td>
				<td><sen:vtoName value="${user.bankOpen}" coding="bank"/></td>
				<td>${user.bankNumber}</td>
				 -->
				<td>${user.overdueDate}</td>
				<td>${user.spareField02}天</td>
				<td>￥<fmt:formatNumber value="${user.overdueMoney }" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber value="${user.damagesMoney+user.punishInterest }" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber value="${user.overdueMoney+user.damagesMoney+user.punishInterest }" pattern="#,#00.00" /></td>
				<td>
				<c:if test="${user.overdueStatr=='6'}">
				0.00
				</c:if>
				<c:if test="${user.overdueStatr!='6'}">
				￥<fmt:formatNumber value="${user.spareField06 }" pattern="#,#00.00" />
				</c:if>
				</td>
				<td>￥<fmt:formatNumber value="${user.spareField03 }" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber value="${user.spareField05 }" pattern="#,#00.00" /></td>
				<td>${user.spareField04 }</td>
				<td><sen:vtoName value="${user.overdueStatr}" coding="capitalOverdue"/></td>
				<td>
					<div class="buttonActive">
							<div class="buttonContent">
								<button onclick="return showOrHide(this);">操作</button>
							</div>
						</div>
							
					
						<div  class='OperationsPopUp' style='display: none; position: absolute;  
						
						text-align: justify; font-size: 12px;height:40px;width:300px;  filter:alpha(opacity=100);
						    opacity:0.9;
						    background:#fff;
						    border:2px orange solid;
						    padding:20px;
						    padding-left:20px;
						   word-wrap:break-word;
						    line-height:100%;
						'>
					<a title="逾期天数调整" target="dialog" mask="true" width="450" warn="请选择一条记录" close="closeDialogFreshen" href="${ctx}/xhCapitalOverdue/xhOverdueDay/${user.id}">逾期天数调整</a> 
					<c:if test="${user.overdueStatr=='0'}">
					| <a title="确认追回" target="dialog" mask="true" width="450" warn="请选择一条记录" close="closeDialogFreshen" href="${ctx}/xhCapitalOverdue/editXhCapitalOverdue/${user.id}?type=1">追回</a>
					| <a title="确认冲抵" target="dialog" mask="true" width="450" warn="请选择一条记录" close="closeDialogFreshen" href="${ctx}/xhCapitalOverdue/offsetXhCapitalOverdue/${user.id}">冲抵</a>
					| <a title="取消逾期" target="dialog" mask="true" width="450" warn="请选择一条记录" close="closeDialogFreshen" href="${ctx}/xhCapitalOverdue/cancelXhCapitalOverdue/${user.id}">取消逾期</a>
					<!-- | <a title="确认未缴" target="ajaxTodo" href="${ctx }/xhCapitalOverdue/setOverdueStateInfo/${user.id}?state=2">未缴</a>  -->
					<!-- | <a title="确认未付" target="ajaxTodo" href="${ctx }/xhCapitalOverdue/setOverdueStateInfo/${user.id}?state=3">未付</a>  -->
					<!-- | <a title="确认挂账" target="ajaxTodo" href="${ctx }/xhCapitalOverdue/setOverdueStateInfo/${user.id}?state=4">挂账</a> --> 
					<!-- | <a title="确认减免" target="ajaxTodo" href="${ctx }/xhCapitalOverdue/setOverdueStateInfo/${user.id}?state=5">减免</a>  -->
					| <a title="确认坏账" target="ajaxTodo" href="${ctx }/xhCapitalOverdue/setOverdueStateInfo/${user.id}?state=6">坏账</a> 
					</c:if>
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
<script>
function closeDialogFreshen(param){
	navTab.reloadFlag('rel_listXhCapitalOverdue');
	var $dialog = $.pdialog.getCurrent();
	$dialog.hide();
	$("div.shadow").hide();
	if($dialog.data("mask")){
		$("#dialogBackground").hide();
	} else{
		if ($dialog.data("id")) $.taskBar.closeDialog($dialog.data("id"));
	}
}
</script>
