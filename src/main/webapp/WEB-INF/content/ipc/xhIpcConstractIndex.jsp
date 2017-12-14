<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/ipc/listXhIpcConstract" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			
			
			<li>
				<label>借款人姓名:</label>
				<input type="text" name="filter_customerName" value="${map.state}"/>
			</li>
			<li>
				<label>借款合同编号:</label>
				<input type="text" name="filter_jkhtbm" value="${map.jkhtbm}"/>
			</li>

			<li>
				<label>起始还款日期:</label>
				<input type="text" name="filter_qshkrq" value="${map.qshkrq}" class="date"/>
				<a class="inputDateButton" href="#">选择</a>
			</li>
		
	
			
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
 		<bjdv:validateContent type="1" funcId="IPC-新增合同">	
			<li><a class="add" href="${ctx}/ipc/addXhIpcConstract/10" target="navTab"><span>添加</span></a></li>
	 	</bjdv:validateContent>
	 	<!-- 
		<bjdv:validateContent type="1" funcId="IPC-修改合同"> 
			<li><a class="edit" href="${ctx}/ipc/editXhIpcConstract/{sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
		 </bjdv:validateContent> 
		  -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="80" >借款合同编号</th>
				<th width="80" >借款人姓名</th>
				<th width="80" >放款金额</th>
				<th width="80" >服务费</th>
				<th width="80" >合同金额</th>
				
				<th width="80" >合同签订日期</th>
				<th width="80" >起始还款日期</th>
				<th width="80" >状态</th>
				
				<th width="80" >还款期数</th>

				<th width="80" >借款利率</th>

				<th width="80" >出借人</th>
				<th width="80" >还款日</th>

				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.jkhtbm}</td>
				<td>${user.ipcApply.customerName}</td>
				<td>${user.fkje}</td>
				<td>${user.fwf}</td>
				<td>${user.htje}</td>
				
				<td>${user.qdrq}</td>
				<td>${user.qshkrq}</td>
				<td>
				<c:if test="${user.state==2}">已提交</c:if>
				<c:if test="${user.state==103}">提前结清</c:if>
				<c:if test="${user.state==1}">待放款</c:if>
				<c:if test="${user.state==61}">已放款</c:if>
				<c:if test="${user.state==100}">还款中</c:if>
				<c:if test="${user.state==102}">结清</c:if>
				</td>
				
				<td>${user.hkqs}</td>
				
				<td>${user.dkll}</td>

				<td><c:if test="${user.middlemanId==3}">魏永华</c:if>
				<c:if test="${user.middlemanId==139036}">夏靖</c:if>
				</td>
				<td>${user.hkr}</td>

				<td>
					<%-- <bjdv:validateContent type="1" funcId="IPC-查看合同"> --%>
				<a title="查看" target="navTab" href="${ctx }/ipc/lookXhIpcConstract/${user.id}" class="btnLook">查看</a>
				<%-- </bjdv:validateContent> --%>
					<bjdv:validateContent type="1" funcId="IPC-修改合同">
						<c:if test="${user.state==0}">
						|<a title="编辑" target="navTab" href="${ctx }/ipc/editXhIpcConstract/${user.id}" class="btnEdit">编辑</a>
						</c:if>
					</bjdv:validateContent> 
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
