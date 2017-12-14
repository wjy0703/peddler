<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/jksq/listChangeJksq" method="post">
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
						<select class="combox" name="filter_jkType">
							<option value="all">全部</option>
							<c:forEach items="${jkTypeList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${filter_jkType==attr.value}">selected="selected" </c:if>>${attr.description }
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>借款状态:</label> 
						<select class="combox" name="filter_state">
							<OPTION value="" <c:if test="${filter_state==''}">selected</c:if>>所有状态</OPTION>
							<c:forEach items="${attrList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${filter_state==attr.value}">selected="selected" </c:if>>${attr.description }
								</option>
							</c:forEach>
						</select>
					</td>
					<td><label>所属城市:</label> 
						<select name="filter_province" ref="jksqchange_city" class="combox" 
							refUrl="${ctx}/cjrxx/getCity?code={value}" >
							<option value="" <c:if test="${filter_province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${filter_province==md.id}">selected</c:if>>${md.name }
								</option>
							</c:forEach>
						</select>
						<select id="jksqchange_city" name="filter_city" class="combox" 
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
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="60" >借款编号</th>
				<th width="60">共同借款人</th>
				<th width="60">客户姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="60">状态</th>
				<th width="60">产品</th>
				<th width="60">申请金额</th>
				<th width="60">放款金额</th>
				<th width="30">分期</th>
				<th width="80">团队经理</th>
				<th width="60">销售人员</th>
				<th width="80" orderField="BACKUP02" class="desc">进件时间</th>
				<th width="140"  align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${jksqChange }" var="jksq" varStatus="st">
				<tr  target="sid_jksq" rel="${jksq.ID}">
					<td>${jksq.LOAN_CODE }</td>
					<td>${jksq.TOGETHER_PERSON}</td>
					<td>${jksq.JKRXM }</td>
					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					<td style="color:red">${jksq.STATEINFO  }</td>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>${jksq.JK_LOAN_QUOTA }</td>
					<td>${jksq.FKJE}</td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td>
					<td>${jksq.BACKUP02}</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<a href="${ctx }/xhNewJksq/lookXhJksq/${jksq.ID}?look=1" " rel="rel_jksqShow"
										target="navTab" title="查看借款申请信息"><button type="submit">查看</button></a>
							</div>
						</div>
						<c:if test="${jksq.APP_STATE!='1'}">
						<div class="buttonActive">
							<div class="buttonContent">
								<a href="${ctx }/jksq/addjksqChange/${jksq.ID}" rel="rel_jksqChangeAdd"
										target="navTab" title="变更申请"><button type="submit">变更申请</button></a>
							</div>
						</div>
						</c:if>
						<c:if test="${jksq.APP_STATE=='0'}">
						<div class="buttonActive">
							<div class="buttonContent">
									<a title="提交待审批" target="ajaxTodo"
										href="${ctx }/jksq/subJkspChange/${jksq.ID}"><button type="submit">提交</button></a>
							</div>
						</div>
						</c:if>
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
