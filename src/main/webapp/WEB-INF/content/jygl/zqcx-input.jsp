<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/jygl/jyglZqtjsp"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${value.ID}" /> 
		<div class="pageFormContent" layoutH="56">
		<input type="hidden" name="tzsq.id" value="" />
			<strong>债权申请信息</strong>
			<div class="divider"></div>
			<p>
				<label>出借协议编号：</label> <input name="" type="text" size="30"
					value="${value.TZSQBH}" readonly="readonly" />
			</p>
			<p>
				<label>出借人姓名：</label> <input name="" type="text" size="30"
					value="${value.CJRXM }" readonly="readonly" />
			</p>
			<p>
				<label>所属城市：</label> <input name="" type="text" size="30"
					value="${value.PRONAME }" readonly="readonly" />
			</p>
			<p>
				<label>计划出借日期：</label> <input name="" type="text" size="30"
					value="${value.JHTZRQ}" readonly="readonly" />
			</p>
			<p>
				<label>计划划扣日期：</label> <input name="" type="text" size="30"
					value="${value.JHHKRQ}" readonly="readonly" />
			</p>
			<p>
				<label>计划划扣金额：</label> <input name="" type="text" size="30"
					value="<fmt:formatNumber
							value="${value.MONEY}" pattern="#,#00.00" />" readonly="readonly" />
			</p>
			<p>
				<label>出借方式：</label> <input name="" type="text" size="30"
					value="${value.TZCP_MC}" readonly="readonly" />
			</p>
			<div class="divider"></div>
			<p>
				<label>已推荐金额(￥)：</label> <input name="" type="text" size="30"
					value="<fmt:formatNumber
							value="${value.LENTMONEY}" pattern="#,#00.00" />" readonly="readonly" />
			</p>
			<p>
				<label>可推荐期数：</label> 
				<input name="LAST_CJZQ" id="LAST_CJZQ" type="hidden" value="${value.LAST_CJZQ}">
				<input name="" id="" type="text" size="30"
					value="<c:if test="${value.LAST_CJZQ=='-1'}">无期限</c:if><c:if test="${value.LAST_CJZQ!='-1'}">${value.LAST_CJZQ}</c:if>" disabled="disabled" />
			</p>
			<div class="divider"></div>
			<strong>债权推荐列表</strong>
			<div class="divider"></div>
			<table class="table" targetType="dialog" width="100%">
				<thead>
					<tr>
						<th width="10%">借款人姓名</th>
						<th width="10%">还款方式</th>
						<th width="10%">起始还款时间</th>
						<th width="10%">还款期数</th>
						<th width="10%">剩余还款期数</th>
						<th width="10%">借款用途</th>
						<th width="10%">原始债权价值</th>
						<th width="10%">剩余债权价值</th>
						<th width="10%">推荐额度</th>
						<th width="10%">持有比例</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listXhZqtjDetails}" var="mate" varStatus="st">
						<tr>
							<td>${mate.JKRXM}</td>
							<td>${mate.HK_WAY }</td>
							<td>${mate.SQHKRQ }</td>
							<td>${mate.SYQS}期</td>
							<td>${mate.SYHKYS }期</td>
							<td>${mate.JK_USE }</td>
							<td>￥<fmt:formatNumber
							value="${mate.JKJE}" pattern="#,#00.00" /></td>
							<td>￥<fmt:formatNumber
							value="${mate.KYZQJZ}" pattern="#,#00.00" /></td>
							<td>￥<fmt:formatNumber
							value="${mate.MONEY }" pattern="#,#00.00" /></td>
							<td>${mate.ZQCYBI }%</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="pageContent"></div>

			<div class="pageContent" id="msList"></div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
</div>