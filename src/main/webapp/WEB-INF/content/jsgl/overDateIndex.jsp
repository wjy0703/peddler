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
		action="${ctx }/overDate/listOverDate" method="post">
		<div class="pageFormContent">
			<table>
				<tr>
					<td><label>借款编码:</label> <input type="text" name="filter_khbm"
						size="30" /></td>
					<td><label>借款人姓名:</label> <input type="text"
						name="filter_cjrxm" size="30" /></td>
					<td><label>日期:</label> <input type="text" name="csrq"
						class="date" pattern="yyyy-MM" value="${cjrxx.csrq }" size="10" />~
					</td>
					<td><input type="text" name="csrq" class="date"
						pattern="yyyy-MM" value="${cjrxx.csrq }" size="10" /></td>
				</tr>
				<tr>
					<td><label>CCA:</label> <input type="hidden" name=""
						value="${cjrxx.employeeCca.id}" /> <input type="text" id="empname"
						class="textInput" name="" value="${cjrxx.employeeCca.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCca" size="20" /><a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a></td>
					<td><label>借款人状态</label> <select class="combox"
						name="filter_ztFlag">
							<option value="">全部</option>
							<option value="0">呆账</option>
							<option value="1">死账</option>
							<option value="2">已撤销</option>
					</select></td>
					<td></td>
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
	<table class="table" width="100%" layoutH="130">
		<thead>
			<tr>
				<th width="80" orderField="cjrxm" class="asc">借款编号</th>
				<th width="80" orderField="khbm">借款人姓名</th>
				<th width="80">借款人身份证号</th>
				<th width="80">开户行</th>
				<th width="80">银行账号</th>
				<th width="80">逾期天数</th>
				<th width="100">逾期金额</th>
				<th width="100">违约金</th>
				<th width="100">罚息</th>
				<th width="100">逾期已撤销</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="${user.id}">
				<td>${user.cjrxm }</td>
				<td>${user.khbm }</td>
				<td>${user.gxcs }</td>
				<td>${user.employeeCrm.name }</td>
				<td>${user.employeeCca.name }</td>
				<td></td>
				<td>${user.cjryx }</td>
				<td>${user.employeeCrm.name }</td>
				<td></td>
				<td>否</td>
				<td><a title="查看" target="navTab"
					href="${ctx }/overDate/lookOverDate/${user.id}">查看</a> <a
					title="撤销" target="navTab"
					href="${ctx }/overDate/revoOverDate/${user.id}">撤销</a> <a
					title="呆账" target="navTab"
					href="${ctx }/overDate/lookOverDate/${user.id}">呆账</a> <a
					title="死账" target="navTab"
					href="${ctx }/overDate/revoOverDate/${user.id}">死账</a></td>
			</tr>
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
