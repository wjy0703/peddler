<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		action="${ctx }/xhTzsq/listAuditTzsq" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>客户编码：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					<td><label>所在城市：</label> <select class="combox"
						name="filter_province" ref="combox_listAuditXhTzsqcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city"
						id="combox_listAuditXhTzsqcity">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
					</select></td>
					</tr>
					<tr>
					<td>
					<label>状态：</label>
					<select class="combox" name="filter_state">
						<option value="8" <c:if test="${map.state=='8'}">selected</c:if>>待审批</option>
						<option value="2" <c:if test="${map.state=='2'}">selected</c:if>>审批通过</option>
						<option value="3" <c:if test="${map.state=='3'}">selected</c:if>>审批未通过</option>
						<option value="10" <c:if test="${map.state=='10'}">selected</c:if>>划扣失败</option>
					</select>
					</td>
					<td colspan="2"><label>出借日期：</label> <input 
						name="filter_jhtzrqMin" value="${map.jhtzrqMin }" type="text" size="8" class="date" readonly="readonly">
					至
					<input  name="filter_jhtzrqMax"
						value="${map.jhtzrqMax }" type="text" size="8" class="date" readonly="readonly"></td>
					<td>
						<div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">检索</button>
										</div>
									</div></li>
								<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级

检索</span></a></li> -->
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
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=tzsqRefusedExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="140" nowrapTD="false">
		<thead>
			<tr>
				<th width="80">客户姓名</th>
				<th width="80">客户编码</th>
				<th width="120">出借编号</th>
				<th width="80">资金来源</th>
				<th width="80">计划出借日期</th>
				<th width="80">计划出借金额</th>
				<th width="80">出借方式</th>
				<th width="80">所在省份</th>
				<th width="80">所在城市</th>
				<th width="80">状态</th>
				<th width="60">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${user.CJRXM }</td>
					<td style="text-align: right">${user.KHBM }</td>
					<td>${user.TZSQBH}</td>
					<td><sen:vtoName coding="moneyComeType" value="${user.SQTYPE}"/>
						</td>
					<td>${user.JHTZRQ}</td>
					<td>￥<fmt:formatNumber
							value="${user.JHTZJE}" pattern="#,#00.00" /></td>
					<td>${user.TZCP_MC}</td>
					<td>${user.PRONAME }</td>
					<td>${user.CITYNAME }</td>
					<td><c:if
							test="${empty user.HKSTATE || user.HKSTATE!='2'}">
					<c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待上传附件</c:if> <c:if
							test="${user.STATE=='3'}">审批未通过</c:if><c:if
							test="${user.STATE=='8'}">待审批</c:if><c:if
							test="${user.STATE=='2'}">审批通过</c:if>
							<c:if
							test="${user.STATE=='10'}">划扣失败</c:if> 
							</c:if>
							<c:if test="${user.HKSTATE=='0'}">-待划扣</c:if> <c:if
							test="${user.HKSTATE=='2'}">已划扣</c:if>
							<c:if
							test="${user.OVERSTATE=='2'}">-已完结</c:if>  </td>
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
							test="${user.STATE=='8'}">
		              <div class="buttonActive">
				            <div class="buttonContent">
						<a title="审批"
						target="navTab" href="${ctx }/xhTzsq/auditTzsq/${user.ID}">审批</a>
						 </div>
		             </div>
		             </c:if>
		             
		             <div class="buttonActive">
				       <div class="buttonContent">
							<a href="${ctx}/xhTzsq/downLoadFile?id=${user.ID}&flag=1" class="" target="dialog"
								title="下载材料" mask="true" width="600" height="420" 
								>下载</a>
						</div>
		               </div>
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
