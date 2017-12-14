<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function firstHouseUploadFile(param){
		var uploadedFlag = top.document.getElementById("uploadedFlag");
		if ("1" == uploadedFlag.value) {
			navTabAjaxDone(param);
		}
		return true;
	}
</script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/houseApply/listXhHouseLoanApply" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>借款编号:</label> <input type="text"
					name="filter_loanCode" value="${map.loanCode}" /></li>
			
				<li><label>借款状态:</label> <input type="text"
					name="filter_loanState" value="${map.loanState}" /></li>
				
				<li><label>借款人姓名:</label> <input type="text"
					name="filter_loanerName" value="${map.loanerName}" /></li>
				<li><label>借款人性别:</label> <input type="text"
					name="filter_loanerSex" value="${map.loanerSex}" /></li>
				<li><label>借款人身份证号:</label> <input type="text"
					name="filter_loanerIdNumber" value="${map.loanerIdNumber}" /></li>
				<li><label>借款人年龄:</label> <input type="text"
					name="filter_loanerAge" value="${map.loanerAge}" /></li>
					<li><label>手机:</label> <input type="text"
					name="filter_telephone" value="${map.telephone}" /></li>
				<li><label>组织机构:</label> <input type="text"
					name="filter_organiId" value="${map.organiId}" /></li>
				<li><label>区域:</label> <input type="text" name="filter_area"
					value="${map.area}" /></li>
				<li><label>城市:</label> <input type="text" name="filter_city"
					value="${map.city}" /></li>
				<li><label>省份:</label> <input type="text"
					name="filter_province" value="${map.province}" /></li>
				
			</ul>
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
	<!-- <div class="panelBar">
		<ul class="toolBar">
		 	<li><a class="add" href="${ctx}/houseApply/addXhHouseLoanApply"
				target="navTab"><span>添加房贷申请</span></a></li>


			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>-->
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80"  class="">借款人姓名</th>

				<th width="80"  class="">申请类型</th>
				<th width="80"  class="">产权证号</th>

				<th width="80"  class="">申请日期</th>
				<th width="80" class="">借款金额</th>




				
				<th width="80"  class="">手机</th>
				
			
				
				<th width="80"    class="">省份</th>
				<th width="80"  class="">城市</th>

				<th width="80"    class="">放款金额</th>
				<th width="80"  class="">借款状态</th>
				<th width="80"  class="">借款编号</th>
				<th width="180">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
						<td>${user.loanerName}</td>
					<td>${user.applyType}</td>
					<td>${user.houseRightNum}</td>
					<td><fmt:formatDate value="${user.loanApplyDate}" pattern="yyyy-MM-dd" /></td>
					<td>${user.loanLoanAmount}</td>
						
					
					
					<td>${user.telephone}</td>
				
				
					<td>${user.province}</td>
				
				<td>${user.city}</td>
				<td>${user.makeLoanAmount}</td>
				<td>
				<c:if test="${user.loanState==0}">暂存</c:if>
				<c:if test="${user.loanState==1}">已提交,待上传资料</c:if>
				<c:if test="${user.loanState==31}">待初审</c:if>
				<c:if test="${user.loanState==32}">待复审</c:if>
				<c:if test="${user.loanState==61}">已放款</c:if>
				<c:if test="${user.loanState==60}">待放款</c:if>
				</td>
				<td>${user.loanCode}</td>
				<!-- 	<td>${user.bankNum}</td>
				<td>${user.bankOpen}</td>
				<td>${user.bankAccountName}</td>
				<td>${user.companyAdress}</td>
				<td>${user.companyNature}</td>
				<td>${user.companyPhone}</td>
				<td>${user.backMoneyType}</td>
				<td>${user.homePhone}</td>
				<td>${user.houseAddress}</td>
				<td>${user.houseArea}</td>
				<td>${user.isOwnCompany}</td>
				<td>${user.loanMonth}</td> -->
				
				
					<!-- 	<td>${user.loanUse}</td>
				<td>${user.loanSrcAddress}</td>
				
				<td>${user.mortgAount}</td>
				<td>${user.oldHomePhone}</td>
				<td>${user.houseRightValue}</td>
				<td>${user.fixedRate}</td>
				<td>${user.spAdress}</td>
				<td>${user.spAge}</td>
				<td>${user.spIncome}</td>
				<td>${user.spComp}</td>
				<td>${user.spCompAdress}</td>
				<td>${user.spCompPhone}</td>
				<td>${user.spDep}</td>
				<td>${user.spSex}</td>
				<td>${user.spHomePhone}</td>
				<td>${user.spName}</td>
				<td>${user.spTelephone}</td>
				<td>${user.spWorkLimit}</td>
				<td>${user.spIdNum}</td>
				
				
			

				<td>${user.loanerSex}</td>
				<td>${user.loanerIdNumber}</td>
				<td>${user.loanerAge}</td>
				<td>${user.houseOwnerName}</td>
				<td>${user.houseOwnerIdNum}</td>
				<td>${user.houseOwnerAge}</td>
				<td>${user.houseJointName}</td>
				<td>${user.houseJointSex}</td>
				<td>${user.houseJointIdNum}</td>
				<td>${user.houseJointAge}</td>
				<td>${user.marital}</td>
				<td>${user.hasChild}</td>
				<td>${user.houseOwnerSex}</td>
				<td>${user.firstLxrName}</td>
				<td>${user.firstLxrRelation}</td>
				<td>${user.firstLxrWorkUnit}</td>
				<td>${user.firstLxrAddress}</td>
				<td>${user.secondLxrName}</td>
				<td>${user.secondLxrRelation}</td>
				<td>${user.secondLxrWorkUnit}</td>
				<td>${user.secondLxrAddress}</td>
				<td>${user.firstLxrTelphone}</td>
				<td>${user.secondLxrTelphone}</td>
				<td>${user.thirdLxrName}</td>
				<td>${user.thirdLxrRelation}</td>
				<td>${user.thirdLxrWorkUnit}</td>
				<td>${user.thirdLxrAddress}</td>
				<td>${user.thirdLxrTelphone}</td> -->
				
				<td>	
					<div class="buttonActive">
				<div class="buttonContent">
					<a title="查看" target="navTab"
						href="${ctx }/houseApply/editXhHouseLoanApply/${user.id}"
						class="">查看</a></div>
						
			<c:if test="${user.loanState==0}">			<div class="buttonContent">	
		
				<a title="修改" target="navTab"
						href="${ctx }/houseApply/editXhHouseLoanApply/${user.id}"
						class="">修改</a>
						
						</div></c:if>
				<c:if test="${user.loanState==1}"><div class="buttonContent"><a href="${ctx}/houseApply/uploadFile/${user.id},upMaterial" class="" target="dialog"
						title="上传材料" mask="true" width="600" height="420" 
						close="firstHouseUploadFile" rel="rel_firstHouseUploadFile" 
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"rel_listHouseLoanApply", "callbackType":""}'>上传资料</a>
					</div></c:if>	
					
				
					<div class="buttonContent"><a href="${ctx}/houseApply/uploadFile/${user.id},upContract" class="" target="dialog"
						title="上传合同" mask="true" width="600" height="420" 
						close="firstHouseUploadFile" rel="rel_houseHeTongUploadFile" 
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"rel_listHouseLoanApply", "callbackType":""}'>上传合同</a>
					</div>
					
					
					</div></td>
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
