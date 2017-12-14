<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/makeLoans/listLoanApplyByState/58" method="post">
				<div class="searchBar">
			<table>
				<tr>
					<td><label>合同编号:</label> 
						<input type="text" name="filter_loancode" value="${filter_loancode }"/>
					</td>
					<td><label>客户姓名:</label> 
						<input type="text" name="filter_jkrxm" value="${filter_jkrxm }"/>
					</td>
					<td><label>证件号码:</label> 
						<input type="text" name="filter_zjhm" value="${filter_zjhm }"/>
					</td>
				</tr>
				<tr>
					<td><label>银行卡卡号:</label> 
						<input type="text" name="filter_banknum" value="${filter_banknum }"/>
					</td>
					<td >
					<label>门店:</label>
					<input id="organiId" name="filter_organi.id" type="hidden" value="${filter_organi.id}"/>
					<input name="filter_organi.name" type="text" value="${filter_organi.name }" onblur="cleanMd(this.value)"/>
					<a href="${ctx }/loan/getMdList" lookupGroup="filter_organi" style=""><span style="color: #7F7F7F;">选择门店</span></a>
					</td>
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
<div class="panelBar">
		<ul class="toolBar">
			<bjdv:validateContent type="1" funcId="确认放款-批量导出">
			<li>
			   <a class="icon" href="${ctx}/exportExcel?serviceName=dfkqrMoneyExcelService&filter_state=58&filter_backup=CREDIT" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a>
			</li>
			</bjdv:validateContent>
			<bjdv:validateContent type="1" funcId="确认放款-批量确认">
			<li>
			   <a title="确实要订购这些记录吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx }/xhJkht/saveQrfkPl" class="delete"
				warn="请至少选择一条记录"><span>批量确认</span></a>
			</li>
			</bjdv:validateContent>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="165" nowrapTD="true">
		<thead>
			<tr>
				<th width="20"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="20">序号</th>
				<th width="100">门店</th>
				<th width="60">合同编号</th>
				<th width="60">账户名</th>
				<th width="80">证件号码</th>
				<th width="30">期数</th>
				<th width="60">合同金额</th>
				<th width="60">放款金额</th>
				<th width="100">开户行</th>
				<th width="100">支行名称</th>
				<th width="60">账号</th>
				<th width="60">状态</th>
				<th width="40" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr>
					<td><input name="ids" value="${jksq.ID}" type="checkbox"></td>
					<td>${jksq.ROWNUM }</td>
					<td>${jksq.YYB }</td>
					<td>${jksq.LOAN_CODE }</td>
					<td>${jksq.JKRXM }</td>
					<td>${jksq.ZJHM}</td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.HTJE }</td>
					<td>${jksq.FKJE }</td>
					<td>${jksq.BANK_NAME }</td>
					<td>${jksq.BANK_OPEN }</td>
					<td>${jksq.BANK_NUM }</td>
					<td style="color: red">待放款确认</td>
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
							<a title="合同制作" href="${ctx}/xhJkht/queryAgaee/${jksq.ID}"
									target="navTab" mask="true" >
								<button type="submit">协议查看</button></a>
							</div>
						</div>
						<bjdv:validateContent type="1" funcId="确认放款-批量确认">	
						<div class="buttonActive">
							<div class="buttonContent">
							<a target="ajaxTodo" title="是否确认?" href="${ctx }/xhJkht/saveQrfkPl?ids=${jksq.ID}" >
							<button type="submit">确认</button></a>
							</div>
						</div>	
						</bjdv:validateContent>
						<bjdv:validateContent type="1" funcId="确认放款-退回">	
						<div class="buttonActive">
							<div class="buttonContent">
							<a  target="dialog" class = "customerAbandon" mask="true" width="600" height="300" close="closeDialogFkth"
							href="${ctx }/xhJkht/QrfkPlBack/${jksq.ID}" >
							<button type="submit">退回</button></a>
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
<script>
function closeDialogFkth(param){
	navTab.reloadFlag('rel_listJksqFkqrsh');
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
