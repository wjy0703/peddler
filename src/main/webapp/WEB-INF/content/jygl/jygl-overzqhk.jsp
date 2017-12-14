<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/jygl/jyglOverHuakou" method="post">
		<input type="hidden" name="filter_subFlag" value="1" />
		<div class="searchBar">
			<table>
				<tr>
					<td width="10%"><label>客户编码：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td width="10%"><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					<td width="10%"><label>账单日：</label> 
					<select class="combox" name="filter_zdr">
						<option value="" <c:if test="${map.zdr==''}">selected</c:if>>全部</option>
						<option value="15" <c:if test="${map.zdr=='15'}">selected</c:if>>15</option>
						<option value="30" <c:if test="${map.zdr=='30'}">selected</c:if>>30</option>
					</select>
					</td>
					<td width="10%"></td>
					</tr>
					<tr>
					
					<td width="10%"><label>出借产品：</label><select name="filter_tzcp"
						class="required combox">
							<option value="" <c:if test="${map.tzcp==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.tzcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select>
					</td>
					<td width="10%"><label>出借日期：</label> <input 
						name="filter_startdate" value="${map.startdate }" type="text" size="8" class="date" readonly="readonly">
					至
					<input  name="filter_overdate"
						value="${map.overdate }" type="text" size="8" class="date" readonly="readonly"></td>
					
					<td width="10%"><label>营业部：</label> <select class="combox"
						name="filter_yyb">
							<option value="" <c:if test="${map.yyb==''}">selected</c:if>>全部</option>
							<c:forEach items="${listYyb}" var="md" varStatus="st">
								<option value="${md.YYBID }"
									<c:if test="${map.yyb==md.YYBID}">selected</c:if>>${md.YYB}</option>
							</c:forEach>
					</select></td>
					
					<td width="10%"><div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">检索</button>
										</div>
									</div></li>
								<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
							</ul>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
<div class="panelBar">
		
	</div>
	<table class="table" width="100%" layoutH="161">
		<thead>
			<tr>
				<th width="150">出借编号</th>
				<th width="80">客户姓名</th>
				<th width="80">客户编码</th>
				<th width="80">所在城市</th>
				<th width="120">营业部</th>
				<th width="80">计划出借日期</th>
				<th width="80">计划出借金额</th>
				<th width="80">出借产品</th>
				<th width="10%">推荐债权价值</th>
				<th width="10%">债权状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listZqtj}" var="zd" varStatus="st">
				<tr target="sid_zd" rel="${zd.ID}">
					<td>${zd.TZSQBH}</td>
					<td>${zd.CJRXM }</td>
					<td style="text-align: right">${zd.KHBM }</td>
					<td>${zd.CITYNAME }</td>
					<td>${zd.YYB }</td>
					<td>${zd.JHTZRQ}</td>
					<td>￥<fmt:formatNumber
							value="${zd.MONEY}" pattern="#,#00.00" /></td>
					<td>${zd.TZCP_MC}</td>
					<td>￥<fmt:formatNumber
							value="${zd.MONEY}" pattern="#,#00.00" /></td>
					<td>
					已划扣
					</td>
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
    line-height:100%;'>
					
			        
			        	<bjdv:validateContent type="1" funcId="债权交易-下载确认书">
			        		<div class="buttonActive">
			        			<div class="buttonContent">
			        				<a title="下载确认书" 
			        				href="${ctx }/jygl/downOverJqFile?id=${zd.ID}">下载确认书</a>
			        			</div>
			        		</div>
			        	</bjdv:validateContent>
			        	
			        	<bjdv:validateContent type="1" funcId="债权交易-制作确认书">
			        		<div class="buttonActive">
			        			<div class="buttonContent">
			        				<a title="制作确认书" target="ajaxTodo"
			        				href="${ctx }/jygl/madeSkqrs?id=${zd.ID}">制作确认书</a>
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

