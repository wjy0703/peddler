<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel">
	<h1>出借信息</h1>
	<div class="pageContent">

		<div class="pageFormContent">
			<p>
				<label>出借编号：</label> <input name="investApplyId" type="text"
					size="30" value="${xhMatchingInvestment.investApply.tzsqbh }"
					class="" maxlength="22" readonly />
			</p>
			<p>
				<label>出借人姓名：</label> <input name="investApplyId" type="text"
					size="30" value="${xhMatchingInvestment.investApply.cjrxx.cjrxm }"
					class="" maxlength="22" readonly />
			</p>
			<p>
				<label>出借日期：</label> <input name="firstInvestDate" type="text"
					size="30" value="${xhMatchingInvestment.investApply.jhtzrq }"
					class="" maxlength="7" readonly />
			</p>
			<p>
				<label>账单日：</label> <input name="billDay" type="text" size="30"
					value="${xhMatchingInvestment.billDay}" class="" readonly
					maxlength="22" />
			</p>
			<p>
				<label>出借方式：</label> <input name="investType" type="text" size="30"
					value="${xhMatchingInvestment.investApply.tzcp.tzcpMc}" class=""
					maxlength="22" readonly />
			</p>

			<p>
				<label>划扣日期：</label> <input name="deductDate" type="text" size="30"
					value="${xhMatchingInvestment.investApply.jhhkrq }" class=""
					maxlength="7" readonly />
			</p>
			<!-- 	<p>
				<label></label> <input name="deliveryDate" type="hidden"
					size="30" value="${xhMatchingInvestment.deliveryDate}" class="readonly"
					maxlength="7" />
			</p>
			<p>
				<label>状态 ：</label> <input name="investState" type="hidden"
					size="30" value="${xhMatchingInvestment.investState}" class="readonly"
					maxlength="22" />
			</p> -->
			<p>
				<label>出借状态：</label> <input name="investMode" type="text" size="30"
					value="<c:if test="${0==xhMatchingInvestment.investMode}">首期 </c:if><c:if test="${1==xhMatchingInvestment.investMode}">非首期 </c:if>"
					class="" maxlength="22" readonly />
			</p>
			<div class="divider"></div>
			<p>
				<label>出借金额：</label> <input name="investType" type="text" size="30"
					style="color: blue"
					value="<fmt:formatNumber
								value="${xhMatchingInvestment.investApply.jhtzje}" pattern="#,#00.00" />"
					class="" maxlength="22" readonly />
			</p>


			<p>
				<label>已匹配金额：</label> <input name="matchedAmount" type="text"
					style="color: green" size="30"
					value="<fmt:formatNumber
								value="${xhMatchingInvestment.matchedAmount}" pattern="#,#00.00" />"
					class="" maxlength="22" readonly />
			</p>

			<p>
				<label>待匹配金额：</label> <input name="matchingAmount" type="text"
					style="color: red" size="30"
					value="<fmt:formatNumber
								value="${xhMatchingInvestment.matchingAmount}" pattern="#,#00.00" />"
					class="" maxlength="22" readonly />
			</p>
		</div>

	</div>
</div>
<div class="divider"></div>
<div class="panel" id="listAvailableValue">
	<h1>债权推荐信息</h1>
	<div class="pageContent" layoutH="60">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add"
					href="${ctx}/match/xhFindAvaliableValue/${xhMatchingInvestment.id}"
					target="dialog" width="1000" height="480" mask="true"
					,  resizable="true"><span>查找可用债权</span></a></li>
				<li class="line">line</li>
				<li><a class="edit"
					href="${ctx}/xhMatchingInvestment/editXhMatchingInvestment/{sid_user}"
					target="navTab" warn="请选择一个记录"><span>修改匹配价值</span></a></li>

				<li class="line">line</li>


				<li><a title="确实要删除选中的记录吗?" target="selectedTodo" rel="ids"
					postType="string"
					href="${ctx }/xhMatchingInvestment/batchdelXhMatchingInvestment"
					class="delete" warn="请至少选择一条记录"><span>删除</span></a></li>



			</ul>
		</div>
		<form method="post" action="${ctx}/" id="listAvailableValue"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<table class="table" width="100%">
					<thead>
						<tr>
							<th width="28"><input type="checkbox" group="ids" />
							<th width="50" class="">借款人</th>
							<!--<th width="100" class="">身份证号</th>-->
							<th width="80" class="">借款方式</th>
							<th width="80" class="">职业情况</th>
							<!-- 	<th width="72" class="">放款日</th>-->
							<th width="80" class="">首次还款日期</th>
							<th width="60" class="">还款日</th>
							<!-- 	<th width="100" class="">初始借款金额</th> -->
							<th width="80" class="">应还款期数</th>
							<th width="100" class="">剩余还款期数</th>
							<th width="80" class="">截止还款日期</th>
							<th width="80" class="">月利率</th>
							<th width="100" class="">原始债权价值</th>
							<th width="80" class="">可用债权价值</th>
							<th width="80" class="">本单匹配价值</th>
							<th width="80" class="">债权人</th>
							<th width="70">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${availables}" var="user" varStatus="st">
							<tr target="sid_invest" rel="${user.id}">

								<td><input name="ids" value="${user.id}" type="checkbox"></td>
								<td>${user.loanApply.jkrxm}</td>
								<td>${user.loanApply.zjhm}</td>
								<td><c:if test="${user.loanApply.jkType=='A'}">老板借</c:if> <c:if
										test="${user.loanApply.jkType=='B'}">老板楼易借</c:if> <c:if
										test="${user.loanApply.jkType=='C'}">薪水借</c:if> <c:if
										test="${user.loanApply.jkType=='D'}">薪水楼易借</c:if> <c:if
										test="${user.loanApply.jkType=='E'}">精英借</c:if></td>
								<td>${user.loanApply.companyNature}</td>
								<td><fmt:formatDate value="${user.makeLoan.makeLoanDate}"
										pattern="yyyy-MM-dd" /></td>
								<td>${user.loanContract.qshkrq}</td>
								<!--		<td>${fn:substring(user.loanContract.qshkrq,8,10)}</td> -->
								<!-- <td>${user.loanContract.htje}</td> -->
								<td>${user.loanContract.hkqs}</td>
								<td>${user.availabelMonth}</td>
								<td><fmt:formatDate value="${user.lastBackMoneyDate}"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatNumber value="${user.loanContract.dkll/100}"
										type="percent" minFractionDigits="2" /></td>
								<td><fmt:formatNumber value="${user.loanContract.htje}"
										pattern="#,#00.00" /></td>
								<td><fmt:formatNumber value="${user.availableValue}"
										pattern="#,#00.00" /></td>
								<td>${user.makeLoan.middleMan.middleManName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<div class="formBar">
		<ul>
			<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit" onclick="return baocun();">暂存</button>
					</div>
				</div></li>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit" onclick="return tijiao();">提交</button>
					</div>
				</div></li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">取消</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
