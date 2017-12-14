<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<script type="text/javascript">
	function closedialoghj(param){
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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/xhCarLoanUser/listXhCarLoanUser" method="post">
	<div class="searchBar">
			<table width="100%">
			<tr>	
				<td>
					<label>借款人姓名:</label>
					<input type="text" name="filter_userName" value="${map.userName}"/>
				</td>
				<td>
					<label>管辖城市：</label>
					<sen:address names="filter_crmprovince,filter_crmcity,filter_crmarea" titles="所属省市,所属城市,所属区县" required="true" values="${map.crmprovince},${map.crmcity },${map.crmarea }" />
				</td>
				<td>
				<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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
			<li><a class="add" href="${ctx}/xhCarLoanUser/addXhCarLoanUser" target="navTab"><span>新增车借客户</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="118">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" >借款人姓名</th>
				<th width="40" >性别</th>
				<th width="80" >出生日期</th>
				<th width="80" >管辖省份</th>
				<th width="80" >管辖城市</th>
				<th width="80" >管辖区县</th>
				<th width="80" >销售</th>
				<th width="80" >销售团队</th>
				<th width="80" >团队经理</th>
				<th width="80" >客服</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listCarLoanUser}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.ID}">
				<td><input name="ids" value="${user.ID}" type="checkbox"></td>
				<td>${user.USER_NAME}</td>
				<td>${user.GENDERS}</td>
				<td>${user.BIRTHDAY}</td>
				<td>${user.CRMPROVINCENAME}</td>
				<td>${user.CRMCITYNAME}</td>
				<td>${user.CRMAREANAME}</td>
				<td>${user.CUSTOMER_LEADER}</td>
				<td>${user.KFTD}</td>
				<td>${user.TEAM_LEADER}</td>
				<td>${user.STAFFNAME}</td>
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
					
		        <c:if test="${user.CANEDIT==0}">
		        <div class="buttonActive">
					<div class="buttonContent">
						<a title="编辑车借客户信息" target="navTab" href="${ctx }/xhCarLoanUser/editXhCarLoanUser/${user.ID}" >编辑</a>
					</div>
		        </div>	
		        </c:if>
		        <c:if test="${user.STATE==1}">
		        <div class="buttonActive">
					<div class="buttonContent">
						<a title="车借申请信息" target="navTab" href="${ctx }/xhCarLoanApply/addXhCarLoanApply/${user.ID}">申请</a>
					</div>
		        </div>	
		        </c:if>
		        <div class="buttonActive">
					<div class="buttonContent">
						<a title="查看申请信息" target="navTab" href="${ctx }/xhCarLoanUser/editXhCarLoanUserLook/${user.ID}">查看</a>
					</div>
		        </div>	
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
