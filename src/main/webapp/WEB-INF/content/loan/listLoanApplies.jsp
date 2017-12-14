<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader" layoutH="10">
	<form rel="pagerForm" id="managematerialForm" name="managematerialForm"
		method="post" action="${ctx}/loan/managematerial" class="pageForm"
		fresh="false" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td><label>客户姓名:</label> 
							<input type="text" name="filter_jkrxm" />
						</td>
						<td><label>客户电话:</label> 
							<input type="text" name="filter_yddh" />
						</td>
						<td><label>证件号码:</label> 
							<input type="text" name="filter_zjhm" />
						</td>
						<td><label>产品:</label> 
							<select class="combox" name="filter_sts">
									<option value="">全部</option>
									<option value="0">老板借</option>
									<option value="1">薪水借</option>
									<option value="2">精英借</option>
									<option value="3">老板楼易借</option>
									<option value="4">薪水楼易借</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>借款状态:</label> 
							<select class="combox" name="filter_sts">
								<OPTION selected value="所有状态">所有状态</OPTION>
								<OPTION  value="暂存">暂存</OPTION>
								<OPTION  value="已提交，待审核">已提交，待审核</OPTION>
								<OPTION  value="审核拒绝">审核拒绝</OPTION>
								<OPTION  value="审核通过，待上传资料">审核通过，待上传资料</OPTION>
								<OPTION  value="资料已上传，待信审">资料已上传，待信审</OPTION>
								<OPTION  value="信审中">信审中</OPTION>
								<OPTION  value="信审通过，待合同制作">信审通过，待合同制作</OPTION>
								<OPTION  value="待签订合同 ">待签订合同 </OPTION>
								<OPTION  value="待放款">待放款</OPTION>
								<OPTION  value="已放款">已放款</OPTION>
								<OPTION  value="拒借">拒借</OPTION>
								<OPTION  value="超时冻结">超时冻结</OPTION>
								<OPTION  value="客户放弃">客户放弃</OPTION>
								<OPTION  value="已完成">已完成</OPTION>
							</select>
						</td>
						<td><label>所属城市:</label> 
							<input type="text" name="filter_yddh" />
						</td>
						<td><label>团队经理:</label> 
							<input type="text" name="filter_zjhm" />
						</td>
						<td><label>销售人员:</label> 
							<input type="text" name="filter_yddh" />
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
		</div>
	</form>


	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/loan/addXhcfDkrxx"
					target="navTab" title="添加借款人基础信息"><span>添加</span></a></li>
				<li><a class="icon" href="${ctx}/loan/"
					onclick="exportLoan(this)" target="dwzExport" targetType="navTab"
					title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" nowrapTD="false">
			<thead>
				<tr>
					<th width="60">借款编号</th>
					<th width="80">客共同借款人</th>
					<th width="80">客户姓名</th>
					<th width="80">状态</th>
					<th width="60">产品</th>
					<th width="60">是否加急</th>
					<th width="60">申请金额</th>
					<th width="60">放款金额</th>
					<th width="60">分期</th>
					<th width="80">咨询人</th>
					<th width="60">直销人</th>
					<th width="80">合同告知信息</th>
					<th width="80">进件时间</th>
					<th width="100"  align="center">操作</th>
				</tr>
			</thead>
			<tr target="sid_user" rel="${user.id}">
				<td></td>
				<td>无</td>
				<td>张三</td>
				<td>待审核</td>
				<td>信和通</td>
				<td>否</td>
				<td>50,000</td>
				<td></td>
				<td></td>
				<td>徐春雷</td>
				<td>徐春雷</td>
				<td>自行上门领取</td>
				<td>2012/12/7 14:35:00</td>
				<td ><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">查看</button>
								</div>
							</div><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">历史</button>
								</div>
							</div>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">上传</button>
								</div>
							</div>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">合同</button>
								</div>
							</div><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">共还</button>
								</div>
							</div></td>


			</tr>
			<tbody>
				<!-- 
				<c:forEach items="${page.result}" var="xhcfdkrxx" varStatus="st">
					<tr target="sid_xhdkrxx" rel="${xhcfdkrxx.id}">
						<td>${st.count}</td>
						<td><a target="navTab"
							href="${ctx }/loan/lookXhcfDkrxx/${xhcfdkrxx.id}"
							title="${xhcfdkrxx.jkrxm }">${xhcfdkrxx.jkrxm }</a></td>
						<td>${xhcfdkrxx.zjhm }</td>
						<td>${xhcfdkrxx.yddh }</td>
						<td>${xhcfdkrxx.dzyx }</td> 
				
						
				<td>${xhcfdkrxx.txdz }</td>
				
					<td><c:if test='${"0"==xhcfdkrxx.state}'>暂存</c:if> <c:if
								test='${"1"==xhcfdkrxx.state}'>在用</c:if></td>
						<td><c:if test='${"0"==xhcfdkrxx.state}'>
								<a target="navTab"
									href="${ctx }/loan/initEditXhcfDkrxx/${xhcfdkrxx.id}"
									title="修改借款人基础信息">修改</a>
							</c:if>
					<a target="navTab" href="${ctx }/loan/initOpenXhcfDkrxx/${xhcfdkrxx.id}" title="借款人开户申请" >开户申请</a>
					<a href="${ctx }/loan/contactXhcfDkrxx/${xhcfdkrxx.id}" title="紧急联系人" lookupGroup="authoritys"  width="900" height="405" rel="llhj">联系人</a>
					 <a href="${ctx }/loan/contactXhcfDkrxx/${xhcfdkrxx.id}"
							title="紧急联系人" target="navTab" rel="jjlxr">联系人</a></td>
					</tr>
				</c:forEach> -->
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

</div>

<script type="text/javascript">
	
</script>