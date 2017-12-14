<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader" layoutH="155">
	<table class="table" width="100%">
		<tr>
			<td colspan="6">
				<div class="panelBar">
					<ul class="toolBar">
						<li><span>借款人紧急联系人列表</span></li>
					</ul>
				</div>
			</td>
		</tr>
		<thead>
			<tr>
				<th width="60">姓名</th>
				<th width="70">与本人关系</th>
				<th width="90">工作单位</th>
				<th width="100">单位地址或家庭地址</th>
				<th width="80">联系电话</th>
				<th width="100" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="xhdkjjlxr" varStatus="st">
				<tr target="sid_XhDkjjlxr" rel="${xhdkjjlxr.id}">
					<td>${xhdkjjlxr.name}</td>
					<td>${xhdkjjlxr.ybrgx}</td>
					<td>${xhdkjjlxr.jjlxrgzdw}</td>
					<td>${xhdkjjlxr.jjlxrdwdzhjtzz}</td>
					<td>${xhdkjjlxr.jjlxrlxdh}</td>
					<td align="center"><a
						href="${ctx }/loan/editContactXhcfDkrxx/${xhdkjjlxr.id}"
						title="修改紧急联系人" lookupGroup="authoritys">修改</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tr>
			<td colspan="6">
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
			</td>
		</tr>
	</table>
</div>

<div class="panelBar">
	<ul class="toolBar">
		<li><span>新增紧急联系人信息</span></li>
	</ul>
</div>
<div class="pageFormContent">
	<form id="loanContactForm" name="loanContactForm" method="post"
		action="${ctx}/loan/saveLoanContact"
		class="pageForm required-validate"
		onsubmit="return loanContactFormSubmit(this);">
		<input id="xhdkrxxId" type="hidden" name="xhdkrxxId"
			value="${xhdkrxx.id }">
		<table class="table" width="100%">
			<tr>
				<td><label>联系人姓名:</label> <input id="name" type="text"
					name="name" size="30" class="required"></td>
				<td><label>与借款人关系：</label> <select id="ybrgx" name="ybrgx">
						<c:forEach items="${ybrgx0014}" var="per">
							<option value="${per.value }">${per.name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><label>工作单位：</label> <input type="text" id="jjlxrgzdw"
					name="jjlxrgzdw" size="30"></td>
				<td><label>联系电话：</label> <input type="text" id="jjlxrlxdh"
					name="jjlxrlxdh" size="30"></td>
			</tr>
			<tr>
				<td colspan="2"><label>单位住址或家庭住址：</label> <input type="text"
					id="jjlxrdwdzhjtzz" name="jjlxrdwdzhjtzz" size="50"></td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript">

	function loanContactFormSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
		return false;}

		return validateCallback(obj, navTabAjaxDone);
	
	}
		

</script>