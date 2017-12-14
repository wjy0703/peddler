<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/zxgl/listLoopConsulting" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>客户状态:</label> <select name="filter_zt"
						class="required combox">
							<option value=""
								<c:if test="${xydkzx.zhuangTai==''}">selected</c:if> selected>请选择</option>
							<option value="0"
								<c:if test="${xydkzx.zhuangTai=='0'}">selected</c:if>>继续跟踪</option>
							<option value="1"
								<c:if test="${xydkzx.zhuangTai=='1'}">selected</c:if>>客户放弃</option>
							<option value="2"
								<c:if test="${xydkzx.zhuangTai=='2'}">selected</c:if>>不符合进件条件</option>
							<option value="3"
								<c:if test="${xydkzx.zhuangTai=='3'}">selected</c:if>>已进件</option>

					</select></td>
					<td><label>客户名称:</label> <input type="text" name="filter_name" />
					</td>
					<td><label>客户电话:</label> <input type="text" name="filter_dh" />
					</td>

				</tr>
				<tr>
					<td>
						<label>城市：</label> 
						<sen:address names="filter_crmprovince,filter_crty" titles="所有省市,所有城市" values="${map.crmprovince},${map.crty}" />
					<%-- 	<select class="combox"
							name="filter_crmprovince" ref="combox_cjrcity"
							refUrl="${ctx}/cjrxx/getCity?code={value}">
								<option value=""
									<c:if test="${xydkzx.crmprovince==''}">selected</c:if>>所有省市</option>
								<c:forEach items="${crmprovince}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${xydkzx.crmprovince==md.id}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select> <select class="combox" name="filter_crty" id="combox_cjrcity">
								<option value="" <c:if test="${xydkzx.crty==''}">selected</c:if>>所有城市</option>
						</select> --%>
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
	<div class="panelBar">
		<ul class="toolBar">
			
		</ul>
	</div>
	<table class="table" layoutH="168" nowrapTD="true">
		<thead>
			<tr>
				<th width="80" class="asc">序号</th>
				<!--  <th width="80" orderField="zxbm" class="asc">咨询编号</th>-->
				<th width="80" orderField="khmc">客户姓名</th>
				<th width="80" orderField="">城市</th>
				<th width="80">门店</th>
				<th width="150">上次沟通时间</th>
				<th width="150">咨询创建时间</th>
				<th width="150">最近沟通记录</th>
				<th width="80">团队经理</th>
				<th width="80">客户经理</th>
				<th width="100">状态</th>
				<th width="110">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 1;
			%>
			<c:forEach items="${listKhzx}" var="xydkzx" varStatus="st">
				<tr target="sid_xydkzx" rel="${xydkzx.id}">
					<td><%=i%></td>
					<!-- <td>${xydkzx.ZXBM }</td>-->
					<td>${xydkzx.KHMC }</td>
					<td>${xydkzx.CITYNAME }</td>
					<td>${xydkzx.YYB}</td>
					<td>${xydkzx.GT_TIME }</td>
					<td>${xydkzx.CREARTIME }</td>
					<td>${xydkzx.GTJL }</td>
					<td>${xydkzx.EMPLOYEE_CRM_NAME }</td>
					<td>${xydkzx.EMPLOYEE_CCA_NAME }</td>
					<td>
					<c:if test="${xydkzx.ZHUANG_TAI=='3'}">已进件</c:if>
					<c:if test="${xydkzx.ZHUANG_TAI=='0' }">继续跟踪</c:if>
					<c:if test="${xydkzx.ZHUANG_TAI=='1' }">客户放弃</c:if>
					<c:if test="${xydkzx.ZHUANG_TAI=='2' }">不满足进件条件</c:if>
					<c:if test="${xydkzx.ZHUANG_TAI=='10'}">循环借</c:if>
					</td>
					<td><c:if
							test="${xydkzx.ZHUANG_TAI!='1' or xydkzx.ZHUANG_TAI!='2' }">


						<bjdv:validateContent type="1" funcId="客户咨询-咨">
							<div class="buttonActive">
								<div class="buttonContent">
									<a title="添加咨询信息" target="navTab"
										href="${ctx }/zxgl/addXhCjrgtjl/${xydkzx.ID}"><button>咨</button></a>
								</div>
							</div>
						</bjdv:validateContent>
						
						<bjdv:validateContent type="1" funcId="客户咨询-修">
							<div class="buttonActive">
								<div class="buttonContent">
									<a title="修改咨询信息" target="navTab"
										href="${ctx }/zxgl/editxydkzx/${xydkzx.ID}"><button>修</button></a>
								</div>
							</div>
						</bjdv:validateContent>
						
						<bjdv:validateContent type="1" funcId="客户咨询-记">
							<div class="buttonActive">
								<div class="buttonContent">
									<a title="查看沟通记录" target="navTab"
										href="${ctx }/zxgl/lookZxGtjl?xydkzx_id=${xydkzx.ID}"><button>记</button></a>
								</div>
							</div>
						</bjdv:validateContent>
						<bjdv:validateContent type="1" funcId="客户咨询-申">
						<c:if test="${xydkzx.ZHUANG_TAI=='0' or xydkzx.ZHUANG_TAI=='10' }">
								<div class="buttonActive">
									<div class="buttonContent">
										<a title="借款申请" target="navTab"
											href="${ctx }/xhNewJksq/addJksq/${xydkzx.ID}"><button>申</button></a>
									</div>
								</div>
						</c:if>
						</bjdv:validateContent>
							
						</c:if>
						</td>
				</tr>
				<%
					i++;
				%>
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
