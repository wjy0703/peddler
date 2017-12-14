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
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhTzsq/listTzsqDateOver/30" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>客户编号：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" size="15"/></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" size="15"/></td>
					<td><label>所在城市：</label>
					<sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${map.province},${map.city}"/>
					 <%-- <select class="combox"
						name="filter_province" ref="combox_listXhTzsqcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city"
						id="combox_listXhTzsqcity">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
					</select> --%>
					
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
	<bjdv:validateContent type="1" funcId="投资申请-导出回款明细">	
		<ul class="toolBar">
			<li><a class="icon" href="${ctx}/xhTzsq/downTzsqDateOver/30"
				target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出回款明细</span></a></li>
		</ul>
		</bjdv:validateContent>
	</div>
	<table class="table" width="100%" layoutH="139" nowrapTD="false">
		<thead>
			<tr>
				<th width="10px"></th>
				<th width="70px">客户编号</th>	
				<th width="60px">客户姓名</th>			
				<th width="130px">出借编号</th>
				<th width="70px">计划出借日期</th>
				<th width="80px">计划出借金额</th>
				<th width="55px">出借方式</th>
				<th width="100px">所在城市</th>
				<th width="100px">营业部</th>
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
					<td>${user.PRONAME }|${user.CITYNAME }</td>
					<td>${user.YYB }</td>
					<td>
					
					<c:if test="${user.STATE=='0'}">暂存</c:if> <c:if
							test="${user.STATE=='1'}">待上传附件</c:if> <c:if
							test="${user.STATE=='3'}">审批未通过</c:if><c:if
							test="${user.STATE=='8'}">待审批</c:if><c:if
							test="${user.STATE=='2'}">审批通过</c:if>
							
							<c:if
							test="${user.STATE=='10'}">划扣失败</c:if> 
							<c:if test="${user.HKSTATE=='0'}">-待划扣</c:if> <c:if
							test="${user.HKSTATE=='2'}">-已划扣</c:if>
							<c:if
							test="${user.OVERSTATE=='2'}">-已完结</c:if> 
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
