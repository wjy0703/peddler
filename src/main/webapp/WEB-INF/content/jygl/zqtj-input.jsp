<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/themes/js/zqtj.js"></script>

<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/jygl/saveZqtj"
		class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="isSub" id="isSub" value="0" /> 
		<input type="hidden" id="TZSQ_ID" name="id" value="${value.ID}" /> 
		<input type="hidden" name="LENTCOUNT" value="${value.LENTCOUNT}" /> 
		<input type="hidden" name="mateIdNew" value="" /> 
		<input type="hidden" name="LENJHTZRQ" value="${value.LENJHTZRQ}" /> 
		<input type="hidden" name="TZCP_ID" id="TZCP_ID" value="${value.TZCP_ID}" /> 
		
		<input type="hidden" name="lenjhtzrq_href" value="${lenjhtzrq_href}" /> 
		<input type="hidden" name="backcount_href" value="${backcount_href}" />
		<input type="hidden" name="zdr_href" value="${zdr_href}" /> 
		<input type="hidden" name="tzcp_href" value="${tzcp_href}" /> 
		<div class="pageFormContent" layoutH="56">
		<input type="hidden" name="state" id="state" value="0" />
		<input type="hidden" name="zqtj_id" value="${xhZqtj.id}" />
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
				<input type="hidden" name="ZDR" id="ZDR" value="${value.ZDR}" /> 
				<input name="" type="text" size="30"
					value="${value.ZDR}" readonly="readonly" />
			</p>
			<p>
				<label>出借状态：</label> 
				<input name="" id="" type="text" size="30"
					value="<c:if test="${xhZqtj.lentState==null || xhZqtj.lentState==''}"><c:if test="${value.BACKCOUNT==0}">首期</c:if><c:if test="${value.BACKCOUNT>0}">非首期</c:if></c:if><c:if test="${xhZqtj.lentState=='0'}">首期</c:if><c:if test="${xhZqtj.lentState=='1'}">非首期</c:if>" disabled="disabled" />
			</p>
			<div class="divider"></div>
			<p>
				<label>已推荐金额(￥)：</label> <input name="" type="text" size="30"
					value="${value.ZQTJSMONEY}" readonly="readonly" />
			</p>
			<p>
				<label>本期需推荐金额(￥)：</label> <input name="MONEY" id="MONEY" type="text" size="30"
					value="${value.MONEY}" readonly="readonly" />
			</p>
			<input name="LAST_CJZQ" id="LAST_CJZQ" type="hidden" value="${value.LAST_CJZQ}">
			<div class="divider"></div>
			<strong>债权推荐列表</strong>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>
					 查看可用债权 <a id="http"
						class="btnLook" href="${ctx }/jygl/findZqtj?TZSQ_ID=${value.ID}&MONEY=${value.MONEY}&TZCP_ID=${value.TZCP_ID}&LAST_CJZQ=${value.LAST_CJZQ}&ZDR=${value.ZDR}&mateid=${mateid }"
						lookupGroup="authoritys" width="1100" height="600"></a>
				</dt>
			</dl>
			<div class="divider"></div>
			<table class="table" targetType="dialog" width="100%">
				<thead>
					<tr>
						<th width="40px" height="10" bgcolor="#EDF1F6">操作</th>
						<th width="100px">借款人姓名</th>
						<th width="60px">还款方式</th>
						<th width="80px">中间人</th>
						<th width="80px">还款起始日期</th>
						<th width="40px">还款日</th>
						<th width="80px">剩余还款期数</th>
						<th width="120px">原始债权价值(￥)</th>
						<th width="120px">剩余债权价值(￥)</th>
						<th width="120px">推荐额度(￥)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listXhZqtjDetails}" var="mate" varStatus="st">
						<tr id="acc${mate.KYZQJZ_ID}">
							<td width="40px"><a href="#" onClick="DeleteMateRow(${mate.KYZQJZ_ID })">移除</a></td>
							<td width="100px">${mate.JKRXM}</td>
							<td width="60px">${mate.HK_WAY }</td>
							<td width="80px">${mate.MIDDLE_MAN_NAME }</td>
							<td width="80px">${mate.SQHKRQ }</td>
							<td width="40px">${mate.HKR }</td>
							<td width="80px"><input name="qishuOld${mate.KYZQJZ_ID }" id="qishuOld${mate.KYZQJZ_ID }" type="hidden" value="${mate.SYHKYS}"/>${mate.SYHKYS }</td>
							<td width="120px">${mate.JKJE }</td>
							<td width="120px"><input name="numOld${mate.KYZQJZ_ID }" id="numOld${mate.KYZQJZ_ID }" type="hidden" value="${mate.KYZQJZ+mate.MONEY}"/>${mate.KYZQJZ+mate.MONEY}</td>
							<td width="120px"><input name="num${mate.KYZQJZ_ID }" id="num${mate.KYZQJZ_ID }" type="text" size="10" value="${mate.MONEY }" class="required" onblur="total(${mate.KYZQJZ_ID})"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
					<div id="msList"></div>
			<input name="mateId" id="mateId" type="hidden" 
			value="${mateid }"/>
			
			
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
				<table class="table" targetType="dialog" width="100%" nowrapTD="false">
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
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return baocun(this);">保存</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return tijiao(this);">提交</button>
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
