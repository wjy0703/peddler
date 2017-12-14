<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/saveCp"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cp.id}" /> <input
			type="hidden" name="mateIdNew" value="" />
		<div class="pageFormContent" layoutH="56">
			出借人信息
			<div class="divider"></div>
			<p>
				<label>出借人编号：</label> <input name="tzcpMc" type="text" size="30"
					value="820010010001-001" readonly="readonly" />
			</p>
			<p>
				<label>出借人姓名：</label> <input name="tzcpMc" type="text" size="30"
					value="马道永" readonly="readonly" />
			</p>
			<p>
				<label>所属城市：</label> <input name="tzcpMc" type="text" size="30"
					value="哈尔滨" readonly="readonly" />
			</p>
			<p>
				<label>出借资金状态：</label> <input name="tzcpMc" type="text" size="30"
					value="首期" readonly="readonly" />
			</p>
			<p>
				<label>计划出借日期：</label> <input name="tzcpMc" type="text" size="30"
					value="2012-8-24" readonly="readonly" />
			</p>
			<p>
				<label>计划划扣日期：</label> <input name="tzcpMc" type="text" size="30"
					value="2012-8-23" readonly="readonly" />
			</p>
			<p>
				<label>计划划扣金额：</label> <input name="tzcpMc" type="text" size="30"
					value="50000" readonly="readonly" />
			</p>
			<div class="divider"></div>
			预计出借收益
			<div class="divider"></div>
			<table class="table" width="100%">
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" group="ids"
							class="checkboxCtrl"></th>
						<th width="15%" orderField="name" class="asc">出借编号</th>
						<th width="10%" orderField="value">资金出借及回收方式</th>
						<th width="10%" orderField="code">初始出借日日期</th>
						<th width="10%">初始出借金额</th>
						<th width="10%">下一个报告日</th>
						<th width="10%">下一个报告期借款人应还款额</th>
						<th width="10%">账户管理费</th>
						<th width="10%">预计下一个报告日您的资产总额</th>
					</tr>
				</thead>
				<tbody>
					<!-- STA 模拟数据 -->
					<tr target="sid_zd" rel="0">
						<td><input name="ids" value="${zd.id}" type="checkbox"></td>
						<td>820010010001-001</td>
						<td>季度盈</td>
						<td><input name="tzcpMc" type="text" size="30"
							value="2012-11-19" /></td>
						<td>11111</td>
						<td>2012-11-19</td>
						<td>10000</td>
						<td>10</td>
						<td>6000</td>
					</tr>
					<!-- END 模拟数据 -->
					<c:forEach items="${page.result}" var="zd" varStatus="st">
						<tr target="sid_zd" rel="${zd.id}">
							<td><input name="ids" value="${zd.id}" type="checkbox"></td>
							<td>${zd.name }</td>
							<td>${zd.value }</td>
							<td>${zd.code.typeName }</td>
							<td></td>
							<td>${zd.value }</td>
							<td>${zd.value }</td>
							<td>${zd.value }</td>
							<td>${zd.value }</td>
							<td><a title="编辑" target="navTab"
								href="${ctx }/jygl/editZqtj/${zd.id}" class="btnEdit">编辑</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="divider"></div>
			债权基本信息
			<div class="divider"></div>
			<table class="table" width="100%">
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" group="ids"
							class="checkboxCtrl"></th>
						<th width="9%" orderField="name" class="asc">借款人姓名</th>
						<th width="9%" orderField="value">借款人身份证号</th>
						<th width="9%" orderField="code">本次转让债权价值</th>
						<th width="9%">需支付对价</th>
						<th width="9%">借款人职业情况</th>
						<th width="10%">借款人借款用途</th>
						<th width="10%">还款起始日期</th>
						<th width="10%">还款期限(月)</th>
						<th width="10%">剩余还款月数</th>
						<th width="10%">预计债权收益率(年)</th>
					</tr>
				</thead>
				<tbody>
					<!-- STA 模拟数据 -->
					<tr target="sid_zd" rel="0">
						<td><input name="ids" value="${zd.id}" type="checkbox"></td>
						<td>李四</td>
						<td>1111111111111</td>
						<td>30000</td>
						<td>30000</td>
						<td>程序员</td>
						<td>购房</td>
						<td>2012-11-11</td>
						<td>24</td>
						<td>24</td>
						<td>24</td>
					</tr>
					<!-- END 模拟数据 -->
					<c:forEach items="${page.result}" var="zd" varStatus="st">
						<tr target="sid_zd" rel="${zd.id}">
							<td><input name="ids" value="${zd.id}" type="checkbox"></td>
							<td>${zd.name }</td>
							<td>${zd.value }</td>
							<td>${zd.code.typeName }</td>
							<td></td>
							<td>${zd.value }</td>
							<td>${zd.value }</td>
							<td>${zd.value }</td>
							<td>${zd.value }</td>
							<td><a title="编辑" target="navTab"
								href="${ctx }/jygl/editZqtj/${zd.id}" class="btnEdit">编辑</a></td>
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
							<button type="submit">交割</button>
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
