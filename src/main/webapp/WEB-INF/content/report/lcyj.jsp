<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		action="${ctx }/report/lcyj" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>出借编号：</label> <input type="text" name="filter_tzsqbh"
						value="${map.tzsqbh }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					
					<td><label>账户状态：</label> <select class="combox"
						name="filter_overstate">
							<option value="" <c:if test="${map.overstate==''}">selected</c:if>>全部</option>
							<option value="0" <c:if test="${map.overstate=='0'}">selected</c:if>>合同中</option>
							<option value="2" <c:if test="${map.overstate=='2'}">selected</c:if>>已到期</option>
							<%-- <option value="3" <c:if test="${map.state=='2' }">selected</c:if>>审批通过</option>
							<option value="4" <c:if test="${map.state=='3' }">selected</c:if>>审批未通过</option>
							<option value="6" <c:if test="${map.hkstate=='1' }">selected</c:if>>划扣失败</option>
							<option value="5" <c:if test="${map.hkstate=='2' }">selected</c:if>>划扣成功</option>
							<option value="7" >已到账</option> --%>
					</select></td>
				</tr>
				<tr>
				
					<td><label>所在城市：</label>
					 <sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${map.province},${map.city}"/>
					</td>
					<td><label>机构：</label> <select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${map.yyb==''}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.YYBID }"
									<c:if test="${map.yyb==md.YYBID}">selected</c:if>>${md.YYB}</option>
							</c:forEach>
					</select></td>
					
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
			<li><a class="icon" href="${ctx}/exportExcel?serviceName=tzsqExcelService" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1400px" layoutH="140" nowrapTD="true">
		<thead>
			<tr>
				<th width="28px">序号</th>
				<th width="130px">出借编号</th>
				<th width="120px">机构</th>
					<th width="80px">团队</th>
				<th width="70px">团队经理</th>
					<th width="70px">理财经理</th>
				<th width="60px">员工</th>
				<th width="100px">规模业绩</th>
				<th width="60px">产品类型</th>
				<th width="80px">划扣日期</th>
				<th width="80px">出借日期</th>
				<th width="60px">客户姓名</th>			
				<th width="120px">所在城市</th>
				<th width="40px">账单日</th>
				<th width="80px">第一账单日</th>
				<th width="80px">债权到期日</th>
				<th width="70px">状态</th>
				<th width="100px">债权返款金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user">
					<td>${user.RONUM }</td>
					<td>${user.TZSQBH}</td>
					<td>${user.YYB }</td>
					<td>${user.KFTD }</td>
					<td>${user.EMPLOYEE_CCA_NAME }</td>
					<td>${user.EMPLOYEE_CRM_NAME }</td>
					<td>${user.EMP_NO}</td>
					<td>￥<fmt:formatNumber
							value="${user.JHTZJE}" pattern="#,#00.00" /></td>
					<td>${user.TZCP_MC}</td>
					<td>${user.JHHKRQ }</td>
					<td>${user.JHTZRQ}</td>
					<td>${user.CJRXM }</td>
					<td>${user.CHENGSHINAME }</td>
					<td>${user.ZDR }</td>
					<td>${user.SGHKRQ }</td>
					<td>${user.MOQI }</td>
					<td>${user.STATENAME }</td>
					<td>￥<fmt:formatNumber
							value="${user.LENTMONEY}" pattern="#,#00.00" /></td>
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
