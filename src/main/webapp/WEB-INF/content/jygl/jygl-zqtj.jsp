<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>


<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/jygl/jyglZqtj" method="post">
		<div class="pageFormContent">
			<table>
				<tr>
					<td><label>客户编码：</label> <input type="text" name="filter_khbm" size="20"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" size="20"/></td>
					<td><label>账单日：</label> 
					<select class="combox" name="filter_zdr">
						<option value="" <c:if test="${map.zdr==''}">selected</c:if>>全部</option>
						<option value="15" <c:if test="${map.zdr=='15'}">selected</c:if>>15</option>
						<option value="30" <c:if test="${map.zdr=='30'}">selected</c:if>>30</option>
					</select>
					</td>
					<td><label>所在城市：</label> <select class="combox"
						name="filter_province" ref="combox_listZqtjcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city"
						id="combox_listZqtjcity">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
					</select></td>
					</tr>
					<tr>
					<td><label>债权状态：</label> 
					<select class="combox" name="filter_backcount">
						<option value="" <c:if test="${map.backcount==''}">selected</c:if>>全部</option>
						<option value="0" <c:if test="${map.backcount=='0'}">selected</c:if>>首期</option>
						<option value="1" <c:if test="${map.backcount=='1'}">selected</c:if>>非首期</option>
					</select>
					</td>
					<td><label>计划出借日期：</label> <input name="filter_lenjhtzrq" type="text"
						size="17" value="${map.lenjhtzrq}" class="date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>
					<td><label>出借产品：</label><select name="filter_tzcp"
						class="required combox">
							<option value="" <c:if test="${map.tzcp==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.tzcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select>
					</td>
					<td><div class="subBar">
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
	<table class="table" width="100%" layoutH="133" nowrapTD="false">
		<thead>
			<tr>
				<th width="130px">出借编号</th>
				<th width="100px">客户姓名</th>
				<th width="90px">客户编码</th>
				<th width="60px">所在城市</th>
				<th width="80px">计划出借日期</th>
				<th width="140px">计划出借金额</th>
				<th width="60px">出借产品</th>
				<th width="60px">债权状态</th>
				<th width="140px">已推荐债权金额</th>
				<th width="80px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listTzsq}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.ID}">
					<td>${user.TZSQBH}</td>
					<td>${user.CJRXM }</td>
					<td style="text-align: right">${user.KHBM }</td>
					<td>${user.CITYNAME }</td>
					<td>${user.LENJHTZRQ}</td>
					<td>￥<fmt:formatNumber
							value="${user.MONEY}" pattern="#,#00.00" /></td>
				    <td>${user.TZCP_MC}</td>
					<td>
					<c:if test="${user.BACKCOUNT==0}">首期</c:if>
					<c:if test="${user.BACKCOUNT>0}">非首期</c:if>
					</td>
					<td>
					<c:if test="${user.ZQTJSMONEY==null}">￥0.00</c:if>
					<c:if test="${user.ZQTJSMONEY!=null}">￥<fmt:formatNumber
							value="${user.ZQTJSMONEY}" pattern="#,#00.00" /></c:if>
					</td>
					<td>
				<div class="buttonActive">
				     <div class="buttonContent">
					<a title="债权推荐" target="navTab"
					href="${ctx }/jygl/editZqtj/${user.ID}?backcount_href=${map.backcount}&lenjhtzrq_href=${map.lenjhtzrq}&zdr_href=${map.zdr}&tzcp_href=${map.tzcp}">债权推荐</a>
					</div>
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