<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/loan/listCreditAuditResultByType/3" method="post">
		<div class="searchBar">
			
			<table  width="100%">
				<tr>
					<td><label>客户姓名:</label> <input type="text"
					name="filter_jkrxm" value="${map.jkrxm}" /></td>
					<td><label>客户电话:</label> <input type="text"
						name="filter_telephone" value="${map.telephone }" /></td>
					<td><label>证件号码:</label> <input type="text" name="filter_zjhm"
						value="${map.zjhm }" /></td>
				</tr>
				<tr>
					<td>
					<label>所属城市:</label>
					<sen:address names="filter_province,filter_city" titles="所有省市,所有城市"/>
					</td>
					<td><label>团队经理:</label> <input type="text"
						name="filter_teamName" value="${map.teamName }" /></td>
					<td><label>销售人员:</label> <input type="text"
						name="filter_saleName" value="${map.saleName }" /></td>
				</tr>
				<tr>
					<td>
					<label>进件时间:</label>
					<input type="text" name="filter_startDate" class="date" style="width: 65px" readonly="true" value="${map.startDate }"/>
					至
					<input type="text" name="filter_endDate" class="date" style="width: 65px" readonly="true" value="${map.endDate }"/>
					</td>
					<td><label>终审结果:</label> <select name="filter_creditResult" class="combox">
					<option value="" <c:if test="${map.creditResult==''}">selected</c:if>>全部</option>
					<option value="1" <c:if test="${map.creditResult=='1'}">selected</c:if>>通过</option>
					<option value="0" <c:if test="${map.creditResult=='0'}">selected</c:if>>拒绝</option>
					</select></td>
					<td><label>状态:</label>
					<select name="filter_state" class="combox" >
							<OPTION value="" <c:if test="${map.state==''}">selected</c:if>>所有状态</OPTION>
							<c:forEach items="${attrList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${map.state==attr.value}">selected="selected" </c:if>>${attr.keyName }
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">

			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="189">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
					<th width="80">共借人姓名</th>
				<th width="80">借款人姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="60">产品</th>
				<th width="60">团队经理</th>
				<th width="60">销售人员</th>
				<th width="60">申请金额</th>
				<th width="80">授信金额(￥)</th>
				<th width="80">授信期限</th>
				<th width="80">综合费率(%)</th>
				<th width="80">外访费(￥)</th>

				<th width="80">状态</th>
				<th width="40">操作</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td><sen:showTogether  lendId="${user.loanApply.id}" yesOrNo="${user.loanApply.togetherPerson}"/></td>
					<td>${user.loanApply.jkrxm}</td>
					<td><sen:addressName id="${user.loanApply.province.id}"/></td>
					<td><sen:addressName id="${user.loanApply.city.id}"/></td>					
					<td><sen:vtoName value="${user.loanApply.jkType}" coding="productType"/></td>
					<td>${user.loanApply.xydkzx.employeeCrm.name}</td>
					<td>${user.loanApply.xydkzx.employeeCca.name}</td>
					<td><fmt:formatNumber value="${user.loanApply.jkLoanQuota}"
							pattern="#,#00.00" /></td>
					<td><fmt:formatNumber value="${user.creditAmount}"
							pattern="#,#00.00" /></td>
					<td>${user.creditMonth}</td>
					<td>${user.creditAllRate}</td>
					<td><fmt:formatNumber value="${user.outVisitFee}"
							pattern="#,#00.00" /></td>

					<td><sen:showName state="${user.loanApply.state}" /></td>
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
						<a href="${ctx }/jksq/jksqHistoryList/${user.loanApply.id}"
							rel="rel_jksqhis_state" mask="true" title="历史状态"
							lookupGroup="authoritys">历史</a> 

						| <a id="freshJksp" href="${ctx }/xhNewJksq/lookXhJksq/${user.loanApply.id}?look=1" target="navTab" 
						title="查看借款申请信息" rel="rel_jksqShow">查看</a>
						
						
						|<a class="icon"
					href="${ctx}/loan/listFirstAuditHistory/${user.loanApply.id}/1"
					target="dialog" rel="dlg_page2" title="查看初审结果" width="800"
					height="350"><span>初审结果</span></a>
					|<a class="icon"
					href="${ctx}/loan/listFirstAuditHistory/${user.loanApply.id}/2"
					target="dialog" rel="dlg_page2" title="查看复审结果" width="800"
					height="350"><span>复审结果</span></a>
					|<a class="icon"
					href="${ctx}/loan/listFirstAuditHistory/${user.loanApply.id}/3"
					target="dialog" rel="dlg_page2" title="查看终审结果" width="800"
					height="350"><span>终审结果</span></a>
					
					 <c:if test="${user.createBy!=null}">
						| <a href="${ctx}/loan/downLoadFile/${user.loanApply.id},sx:1:wf"
							class="" target="dialog" title="下载材料" mask="true" width="600"
							height="420">下载</a>
						</c:if>
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
