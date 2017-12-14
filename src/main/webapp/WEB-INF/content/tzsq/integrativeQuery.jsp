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
		action="${ctx }/xhTzsq/IntegrativeQuery" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td>
					<label>时间段：</label>
					<select id="rqxz" class="combox" name="filter_date" onchange="hasDate();"><!-- 淡入淡出的js在此引用 -->
						<option value="" selected="selected">请选择</option>
						<option value="JHTZRQ"<c:if test="${map.filter_date=='JHTZRQ'}">selected</c:if>>计划出借日期</option>
						<option value="CREATE_TIME"<c:if test="${map.filter_date=='CREATE_TIME'}">selected</c:if>>创建日期</option>
						<option value="JHHKRQ"<c:if test="${map.filter_date=='JHHKRQ'}">selected</c:if>>划扣日期</option>
					</select>
					
					<p id="date" style="display: none">
					
					 <input 
						name="filter_startdate" value="${map.startdate }" type="text" size="8" class="date" readonly="readonly" />
					至
					<input   name="filter_enddate"
						value="${map.enddate }" type="text" size="8" class="date" readonly="readonly" />
						</p></td>
					
					<td><label>出借编号：</label> <input type="text" name="filter_tzsqbh"
						value="${map.tzsqbh }" size="15"/></td>	
					
					<td width="20%">
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
	
	</div>
	<table class="table" width="1300" layoutH="115" nowrapTD="false">
		<thead>
			<tr>
				<th width="10px"></th>
				<th width="70px">客户编号</th>	
				<th width="60px">客户姓名</th>			
				<th width="130px">出借编号</th>
				<th width="70px">计划出借日期</th>
				<th width="80px">计划出借金额</th>
				<th width="80px">计划划扣日期</th>
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
					<td>${user.JHHKRQ }</td>
					<td>${user.TZCP_MC}</td>
					<td>${user.PRONAME }|${user.CITYNAME }</td>
					<td>${user.YYB }</td>
					<td>
					<c:if
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
	<script type="text/javascript">hasDate();</script>
</div>
