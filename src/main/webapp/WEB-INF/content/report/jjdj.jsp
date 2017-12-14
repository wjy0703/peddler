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
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/report/jjdj" method="post">
			<div class="searchBar">
			<table width="100%">
				<tr>
					<td>
					
					   <label>机构：</label> 
					   
					   <select  class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${!empty map.yyb}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.ORGID }"
									<c:if test="${map.yyb==md.ORGID}">selected</c:if>>${md.ORGNAZATIONNAME}</option>
							</c:forEach>
					</select>  
						
					</td>
					<td><label>放款日期：</label> <input 
						name="filter_fstartdate" value="${map.fstartdate }" type="text" size="8" class="date" readonly="readonly"/>
					至
					<input  name="filter_foverdate"
						value="${map.foverdate }" type="text" size="8" class="date" readonly="readonly"/>
						</td>
						<td><label>交单日期：</label> <input 
						name="filter_jstartdate" value="${map.jstartdate }" type="text" size="8" class="date" readonly="readonly"/>
					至
					<input  name="filter_joverdate"
						value="${map.joverdate }" type="text" size="8" class="date" readonly="readonly"/>
						</td>
						<td>
						   <label>审批结果：</label>
						   <select class="combox" name="filter_jkType">
								<option value="" <c:if test="${map.jkType==''}">selected</c:if>>全部</option>
							<c:forEach items="${attrList }" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${map.jkType==attr.value}">selected</c:if>>${attr.keyName }
								</option>
							</c:forEach>
						</select>
						</td>
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
			<li><a class="icon" href="${ctx }/report/exportJjdj" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1850px" layoutH="115" nowrapTD="ture">
		<thead>
			<tr>
				<th width="60px">序号</th>
				<th width="130px">客户姓名</th>	
				<th width="260px">机构</th>	
				<th width="140px">产品种类</th>
				<th width="100px">申请金额</th>
				<th width="120px">申请期数</th>
				<th width="120px">客户经理</th>
				<th width="120px">团队经理</th>
				<th width="200px">交单时间</th>
				<th width="70px">客服</th>
				<th width="80px">初审员</th>
				<th width="200px">上传时间</th>
				<th width="200px">通知外访</th>
				<th width="200px">外访上传</th>
				<th width="140px">是否回退件</th>
				<th width="200px">补件时间</th>
				<th width="120px">审批结果</th>
				<th width="100px">拒借原因</th>
				<th width="100px">放款金额</th>
				<th width="70px">期数</th>
				<th width="200px">批复日期</th>
				<th width="200px">合同制作日期</th>
				<th width="200px">签约日期</th>
				<th width="200px">放款日期</th>
				<th width="120px">共计用时</th>
				<th width="120px">备注</th>
			</tr>
		</thead>
	
		<tbody>
			<c:forEach items="${listJjdz}" var="user" varStatus="st">
				<tr target="sid_user">
					<td>${st.count}</td>
					<td>${user.JKRXM }</td>
					<td>${user.YYB }</td>
					<td>${user.JK_TYPE_INFO }</td>
					<td>${user.JK_LOAN_QUOTA }</td>
					<td>${user.JK_CYCLE }</td>
					<td>${user.SALENAME }</td>
					<td>${user.TEAMNAME }</td>
					<td>${user.JK_LOAN_DATE }</td>
					<td>${user.CREATE_BY }</td>
					<td>${user.CHUSHEN_PERSON }</td>
					<td>${user.UPLOADTIME }</td>
					<td>${user.WAIFANGTIME }</td>
					<td>${user.UPLOADWAIFANGTIME}</td>
					<td>${user.ISBACKREGISTER}</td>
					<td>${user.RESETREGISTERTIME}</td>
					<td>${user.STATE}</td>
					<td>${user.REFUSECAUSE}</td>
					<td>${user.FKJE}</td>
					<td>${user.HKQS}</td>
					<td>${user.OVERCREDITTIME}</td>
					<td>${user.MAKEJKHTTIME}</td>
					<td>${user.QDRQ}</td>
					<td>${user.MAKELOANDATE}</td>
					<td>${user.ALLTIMES }</td>
					<td>${user.GIVEUPCAUSE}</td>
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
	
	
