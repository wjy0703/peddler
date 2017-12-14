<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx}/jygl/dgZqtjfsq"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${value.ID}" />
			<input type="hidden" name="statedg_href" value="${statedg_href}" />
			<div class="pageFormContent" layoutH="56">
				<h1>非首期债权转让协议</h1>
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
						value="非首期" disabled="disabled" />
				</p>
				<p>
					<label>计划出借日期：</label> <input name="" type="text" size="30"
						value="${value.JHTZRQ}" disabled="disabled" />
				</p>
				<p>
					<label>计划出借金额(￥)：</label> <input name="" type="text" size="30"
						value="<fmt:formatNumber
							value="${value.MONEY}" pattern="#,#00.00" />"
						disabled="disabled" />
				</p>
				<div class="divider"></div>
				<h2>债权列表</h2>
				<div class="divider"></div>
				<table class="table" width="100%">
					<thead>
						<tr>
							<th width="60%">债权基本信息</th>
							<th width="40%">借款人如约还款情况下债权收益信息</th>
						</tr>
					</thead>
					</table>
				<table class="table" width="100%" nowrapTD="false">
					<thead>
						<tr>
							<th width="10%">借款人姓名</th>
							<th width="10%">借款人身份证号</th>
							<th width="10%">本次转让债权价值</th>
							<th width="10%">需支付对价</th>
							<th width="10%">借款人职业情况</th>
							<th width="10%">借款人借款用途</th>
							<th width="10%">还款起始日期</th>
							<th width="10%">还款期限（月）</th>
							<th width="10%">剩余还款月数</th>
							<th width="10%">预计债权收益率（年）</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listXhZqtjDetails}" var="mate" varStatus="st" >
							<tr target="sid_user">
								<td>${mate.JKRXM}</td>
								<td>${mate.ZJHM}</td>
								<td>￥<fmt:formatNumber value="${mate.MONEY}"
										pattern="#,#00.00" /></td>
								<td>￥<fmt:formatNumber value="${mate.MONEY}"
										pattern="#,#00.00" /></td>
								<td><c:if
										test="${mate.JK_TYPE =='A'  || mate.JK_TYPE =='B'}">业主</c:if>
									<c:if
										test="${mate.JK_TYPE =='E' || mate.JK_TYPE =='C' || mate.JK_TYPE =='D' }">职员</c:if>
										<c:if
										test="${mate.JK_TYPE =='F'}">抵押房</c:if>
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
				<div class="divider"></div>
				<h2>既有债权列表</h2>
				<div class="divider"></div>
				<table class="table"  width="100%">
					<thead>
						<tr>
							<th width="56%">债权基本信息</th>
							<th width="44%">借款人如约还款情况下债权收益信息</th>
						</tr>
					</thead>
					</table>
				<table class="table" width="100%" nowrapTD="false">
					<thead>
						<tr>
							<th width="9%">借款人姓名</th>
							<th width="9%">借款人身份证号</th>
							<th width="9%">本次转让债权价值</th>
							<th width="9%">年月日持有债权价值（元）</th>
							<th width="9%">借款人职业情况</th>
							<th width="9%">借款人借款用途</th>
							<th width="9%">还款起始日期</th>
							<th width="9%">本期还款金额</th>
							<th width="9%">还款期限（月）</th>
							<th width="9%">剩余还款月数</th>
							<th width="9%">预计债权收益率（年）</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listXhLentmoneywater}" var="mate"
							varStatus="st">
							<tr>
								<td>${mate.JKRXM}</td>
								<td>${mate.ZJHM}</td>
								<td>￥<fmt:formatNumber value="${mate.MONEY}"
										pattern="#,#00.00" /></td>
								<td>￥<fmt:formatNumber value="${mate.MONEY_SY}"
										pattern="#,#00.00" /></td>
								<td><c:if test="${mate.JK_TYPE =='A' }">业主</c:if> <c:if
										test="${mate.JK_TYPE =='B' || mate.JK_TYPE =='D' }">抵押房</c:if>
									<c:if test="${mate.JK_TYPE =='E' || mate.JK_TYPE =='C'  }">职员</c:if>
								</td>
								<td>${mate.JK_USE }</td>
								<td>${mate.SQHKRQ }</td>
								<td>￥<fmt:formatNumber
										value="${mate.FINAL_INTEREST+mate.FINAL_MONEY}"
										pattern="#,#00.00" /></td>
								<td>${mate.SYQS}</td>
								<td>${mate.HKZQ_SY }</td>
								<td>${mate.ZQYJCYL }%</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--endprint-->
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">订购</button>
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
	</div>
</div>