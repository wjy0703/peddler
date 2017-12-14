<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/jygl/saveZqtjsp"
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
					value="${value.LENJHTZRQ}" readonly="readonly" />
			</p>
			<c:if test="${value.BACKCOUNT==0}">
			<p>
				<label>计划划扣日期：</label> <input name="" type="text" size="30"
					value="${value.JHHKRQ}" readonly="readonly" />
			</p>
			<p>
				<label>计划划扣金额：</label> <input name="" type="text" size="30"
					value="${value.JHTZJE}" readonly="readonly" />
			</p>
			</c:if>
			<p>
				<label>出借方式：</label> <input name="" type="text" size="30"
					value="${value.TZCP_MC}" readonly="readonly" />
			</p>
			<p>
				<label>账单日：</label> 
				<input type="hidden" name="ZDR" value="${value.ZDR}" /> 
				<input name="" type="text" size="30"
					value="${value.ZDR}" readonly="readonly" />
			</p>
			<p>
				<label>出借状态：</label> 
				<input name="" id="" type="text" size="30"
					value="<c:if test="${xhZqtj.lentState=='0'}">首期</c:if><c:if test="${xhZqtj.lentState=='1'}">非首期</c:if>" disabled="disabled" />
			</p>
			<div class="divider"></div>
			<p>
				<label>已推荐金额(￥)：</label> <input name="" type="text" size="30"
					value="${xhZqtj.money}" readonly="readonly" />
			</p>
			<p>
				<label>可推荐期数：</label> 
				<input name="LAST_CJZQ" id="LAST_CJZQ" type="hidden" value="${value.LAST_CJZQ}">
				<input name="" id="" type="text" size="30"
					value="<c:if test="${value.LAST_CJZQ=='-1'}">无期限</c:if><c:if test="${value.LAST_CJZQ!='-1'}">${value.LAST_CJZQ}</c:if>" disabled="disabled" />
			</p>
			<dl class="nowrap">
				<dt>审批结果：</dt>
				<dd>
					<input type="radio" name="state" value="2" checked="checked"/>通过 <input type="radio"
						name="state" value="3" />不通过
				</dd>
			</dl>
			<div class="divider"></div>
			<strong>债权推荐列表</strong>
			<div class="divider"></div>
			<table class="table" targetType="dialog" width="100%"  nowrapTD="false">
				<thead>
					<tr>
						<th width="6%">借款人姓名</th>
						<th width="8%">还款方式</th>
						<th width="9%">起始还款时间</th>
						<th width="8%">还款期数</th>
						<th width="9%">剩余还款期数</th>
						<th width="8%">借款用途</th>
						<th width="8%">借款类型</th>
						<th width="9%">原始债权价值</th>
						<th width="9%">剩余债权价值</th>
						<th width="9%">推荐额度</th>
						<th width="8%">利率</th>
						<th width="8%">持有比例</th>
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
							<td>
							<c:if test="${mate.BACKUP01=='CREDIT'}">信用借款</c:if>
							<c:if test="${mate.BACKUP01=='HOUSE'}">房屋抵押</c:if>
							<c:if test="${mate.BACKUP01=='IPC'}">IPC</c:if>
							<c:if test="${mate.BACKUP01=='QINGDAO'}">房易借</c:if>
							</td>
							<td>￥<fmt:formatNumber
							value="${mate.JKJE}" pattern="#,#00.00" /></td>
							<td>￥<fmt:formatNumber
							value="${mate.KYZQJZ}" pattern="#,#00.00" /></td>
							<td>￥<fmt:formatNumber
							value="${mate.MONEY}" pattern="#,#00.00" /></td>
							<td>${mate.DKLL }%</td>
							<td>${mate.ZQCYBI }%</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${!empty listXhLentmoneywater}">  
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
				<table class="table" targetType="dialog" width="100%"  nowrapTD="false">
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
				</c:if>
			<div class="pageContent"></div>

			<div class="pageContent" id="msList"></div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">审批</button>
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
