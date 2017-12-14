<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhJkht/listJksq/0" method="post">
				<div class="searchBar">
			<table>
				<tr>
					<td><label>客户姓名:</label> 
						<input type="text" name="filter_jkrxm" value="${filter_jkrxm }"/>
					</td>
					<td><label>客户电话:</label> 
						<input type="text" name="filter_telephone" value="${filter_telephone }"/>
					</td>
					<td><label>证件号码:</label> 
						<input type="text" name="filter_zjhm" value="${filter_zjhm }"/>
					</td>
					<td><label>产品:</label> 
						<!-- <select class="combox" name="filter_jkType">
								<option value="all">全部</option>
								<c:if test='${filter_jkType == "A"}'>
									<option value="A" selected="selected">老板借</option>
								</c:if>
								<c:if test='${filter_jkType != "A"}'>
									<option value="A" >老板借</option>
								</c:if>
								<c:if test='${filter_jkType == "B"}'>
									<option value="B" selected="selected">老板楼易借</option>
								</c:if>
								<c:if test='${filter_jkType != "B"}'>
									<option value="B" >老板楼易借</option>
								</c:if>
								<c:if test='${filter_jkType == "C"}'>
									<option value="C" selected="selected">薪水借</option>
								</c:if>
								<c:if test='${filter_jkType != "C"}'>
									<option value="C" >薪水借</option>
								</c:if>
								<c:if test='${filter_jkType == "D"}'>
									<option value="D" selected="selected">薪水楼易借</option>
								</c:if>
								<c:if test='${filter_jkType != "D"}'>
									<option value="D" >薪水楼易借</option>
								</c:if>
								<c:if test='${filter_jkType == "E"}'>
									<option value="E" selected="selected">精英借</option>
								</c:if>
								<c:if test='${filter_jkType != "E"}'>
									<option value="E">精英借</option>
								</c:if>
						</select> -->
						 <select class="combox"	name="filter_jkType" >
						<option value="" <c:if test="${filter_jkType==''}">selected</c:if>>全部</option>
						<c:forEach items="${cpList}" var="type" varStatus="st">
							<option value="${type.VALUE }" <c:if test="${filter_jkType==type.VALUE}">selected</c:if>>${type.DESCRIPTION}</option>
						</c:forEach>
				</select>
					</td>
				</tr>
				<tr>
					
					<td><label>所属城市:</label> 
						<select name="filter_province" ref="jksqlistbox_city" class="combox" 
							refUrl="${ctx}/cjrxx/getCity?code={value}" >
							<option value="" <c:if test="${filter_province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_province==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select>
						<select id="jksqlistbox_city" name="filter_city" class="combox" 
							 refUrl="${ctx}/cjrxx/getArea?code={value}" >
							<option value="" <c:if test="${filter_city==''}">selected</c:if>>所有城市</option>
							<c:forEach items="${city}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_city==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select>
					</td>
					<td><label>团队经理:</label> 
						<input type="text" name="filter_teamName" value="${filter_teamName }"/>
					</td>
					<td><label>销售人员:</label> 
						<input type="text" name="filter_saleName" value="${filter_saleName }"/>
					</td>
					<td >
					<label>门店:</label>
					<input id="organiId" name="filter_organi.id" type="hidden" value="${organiId}"/>
					<input name="filter_organi.name" type="text" value="${organiName }" onblur="cleanMd(this.value)"/>
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
<!-- 	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jksq/addJksq" target="navTab"><span>新增借款申请</span></a></li>
		</ul>
	</div> -->
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="60px">借款编号</th>
				<th width="60px">共同借款人</th>
				<th width="60px">客户姓名</th>
				<th width="50px">省份</th>
				<th width="50px">城市</th>
				<th width="80">门店</th>
				<th width="80px">状态</th>
				<th width="80px">产品</th>
			
				<th width="70px">申请金额</th>
				<th width="30px">分期</th>
				<th width="60px">团队经理</th>
				<th width="60px">销售人员</th>

				<th width="100px" orderField="CREATE_TIME" class="desc">进件时间</th>
				<th width="40px"  align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr  target="sid_jksq" rel="${jksq.ID}">
					<td>${jksq.LOAN_CODE }</td>
					<td><sen:showTogether  lendId="${jksq.ID}" yesOrNo="${jksq.ISTOGETHER}"/></td>
					<td>${jksq.JKRXM }</td>
					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					<td>${jksq.YYB}</td>
					<td style="color:red">${jksq.STATEINFO  }</td>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>￥<fmt:formatNumber value="${jksq.JK_LOAN_QUOTA }" pattern="#,#00.00" /></td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td>
		
					<td>${jksq.CREATETIME}</td>
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
					
<a href="${ctx }/jksq/jksqHistoryList/${jksq.ID}" rel="rel_jksqhis_state"  mask="true" 
						title="历史状态" lookupGroup="authoritys">历史</a> 
						</div>
		</div>
<div class="buttonActive">
			<div class="buttonContent">
			<a title="协议查看" href="${ctx}/xhJkht/queryAgaee/${jksq.ID}"
					target="navTab" mask="true" >
				<button type="submit">协议查看</button></a>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<a title="查看" target="navTab"
					href="${ctx }/xhJkht/queryXhJkht/${jksq.ID}" target="navTab"
					mask="true">
					<button type="submit">查看</button>
				</a>
			</div>
		</div>
		
		
	<c:if test="${jksq.STATE > 51}">
	<div class="buttonActive">
       <div class="buttonContent">
			<a href="${ctx}/xhJkht/downLoadFile/${jksq.ID}" class="" target="dialog"
				title="下载合同" mask="true" width="600" height="420" 
				><button type="submit">下载</button></a>
		</div>
             </div>
	<c:if test="${jksq.STATE < 61 }">
	<div class="buttonActive">
				       <div class="buttonContent">
							<a title="合同制作" target="navTab"
								href="${ctx }/xhJkht/madeXhJkhtReset/${jksq.ID}">
								<button type="submit">重新制作</button>
								</a>
								</div>
		               </div>
		               </c:if>
		              </c:if> 
		              <bjdv:validateContent type="1" funcId="借款合同-重置利率">	
		              <div class="buttonActive">
						<div class="buttonContent">
						<a title="重置利率" target="navTab" href="${ctx }/xhJkht/resetXhJkht/${jksq.ID}" target="navTab" mask="true">
						<button type="submit">重置利率</button></a>
						</div>
					</div>
		              </bjdv:validateContent>
		              
		              <bjdv:validateContent type="1" funcId="借款合同-重置利率(旧)">	
		              <div class="buttonActive">
						<div class="buttonContent">
						<a title="重置利率(旧)" target="navTab" href="${ctx }/xhJkht/resetOldXhJkht/${jksq.ID}" target="navTab" mask="true">
						<button type="submit">重置利率(旧)</button></a>
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
