<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/jygl/saveZqtjhk"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${value.ID}" /> 
		<input type="hidden" name="statehk_href" value="${statehk_href}" />
		<div class="pageFormContent" layoutH="56">
			<strong>首期债权转让协议</strong>
			<div class="divider"></div>
			<p>
				<label>出借协议编号：</label> <input name="" type="text" size="30"
					value="${value.TZSQBH}" readonly="readonly" />
			</p>
			<p>
				<label>出借人姓名：</label> <input name="" type="text" size="30"
					value="${value.CJRXM }" disabled="disabled" />
			</p>
			<p>
				<label>所属城市：</label> <input name="" type="text" size="30"
					value="${value.PRONAME }" disabled="disabled" />
			</p>
			<p>
				<label>出借资金状态：</label> <input name="" type="text" size="30"
					value="首期" disabled="disabled" />
			</p>
			<p>
				<label>计划出借日期：</label> <input name="" type="text" size="30"
					value="${value.JHTZRQ}" disabled="disabled" />
			</p>
			<p>
				<label>计划划扣日期：</label> <input name="" type="text" size="30"
					value="${value.JHHKRQ}" disabled="disabled" />
			</p>
			<p>
				<label>计划出借金额(￥)：</label> <input name="" type="text" size="30"
					value="<fmt:formatNumber
							value="${value.MONEY}" pattern="#,#00.00" />" disabled="disabled" />
			</p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>划扣结果：</dt>
				<dd>
					<input type="radio" name="statedg" id="statedg" value="1" checked="checked" />提交划扣
					<input type="radio" name="statedg" id="statedg" value="9" />取消划扣
				</dd>
			</dl>
			
<div class="divider"></div>
<strong>预计出借收益</strong>
<div class="divider"></div>
		<table class="table" targetType="dialog" width="100%">
			<thead>
				<tr>
				<th width="10%">出借编号</th>
				<th width="10%">资金出借及回收方式</th>
				<th width="10%">初始出借日日期</th>
				<th width="10%">初始出借金额</th>
				<th width="10%">下一个报告日</th>
				<th width="10%">下一个报告期借款人应还款额</th>
				<th width="10%">账户管理费</th>
				<th width="10%">预计下一个报告日您的资产总额</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${value.TZSQBH}</td>
				<td>${value.TZCP_MC}</td>
				<td>
					${value.JHTZRQ }
					</td>
				<td>￥<fmt:formatNumber
							value="${value.MONEY}" pattern="#,#00.00" /></td>
				<td>${value.XYBGRQ}</td>
				<td>￥<fmt:formatNumber
							value="${value.XYBGQJKRYHKE}" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber
							value="0" pattern="#,#00.00" /></td>
				<td>￥<fmt:formatNumber
							value="${value.YJXYBGRZCZE}" pattern="#,#00.00" /></td>
			</tr>
			</tbody>
		</table>
	<div class="divider"></div>
<strong>债权基本信息</strong>
<div class="divider"></div>
		<table class="table" targetType="dialog" width="100%">
			<thead>
			<tr>
				<th width="10%">借款人姓名</th>
				<th width="10%">借款人身份证号</th>
				<th width="10%">本次转让债权价值</th>
				<th width="10%">需支付对价</th>
				<th width="10%">借款人职业情况</th>
				<th width="10%">借款人借款用途</th>
				<th width="10%">还款起始日期</th>
				<th width="10%">还款期限(月)</th>
				<th width="10%">剩余还款月数</th>
				<th width="10%">预计债权收益率(年)</th>
			</tr>
			</thead>
			<tbody>
					<c:forEach items="${listXhZqtjDetails}" var="mate" varStatus="st">
						<tr>
							<td>${mate.JKRXM}</td>
							<td>${mate.ZJHM}</td>
							<td>￥<fmt:formatNumber
							value="${mate.MONEY}" pattern="#,#00.00" /></td>
							<td>￥<fmt:formatNumber
							value="${mate.MONEY}" pattern="#,#00.00" /></td>
							<td><c:if test="${mate.JK_TYPE =='A'  || mate.JK_TYPE =='B'}">业主</c:if>
							<c:if test="${mate.JK_TYPE =='E' || mate.JK_TYPE =='C' || mate.JK_TYPE =='D' }">职员</c:if>
							<c:if test="${mate.JK_TYPE =='F'}">抵押房</c:if>
							</td>
							<td>${mate.JK_USE }</td>
							<td>${mate.SQHKRQ }</td>
							<td>${mate.SYQS}</td>
							<td>${mate.SYHKYS }</td>
							<td>${mate.ZQYJCYL }%</td>
						</tr>
					</c:forEach>
					</tbody>
		</table>
		</div>
	<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
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
	</form>
</div></div>
