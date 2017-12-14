<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent" style="padding: 0px">
	<div class="pageHeader" style="border: 1px #B8D0D6 solid">
		<form id="pagerForm" onsubmit="return divValidateSearch(this, 'jbsxBox');"
			action="${ctx}/loan/getAuditPersonKpi" method="post" class="pageForm required-validate">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td><label>信审员:</label><input type="hidden" name="filter_auditPerson"
							id="filter_auditPerson" value="${map.auditPerson }" /> <input
							name="filter_auditPersonName" id="filter_auditPersonName"
							type="text" size="10" value="${map.auditPersonName }"
							readonly="readonly" /></td>
						<td>
							<div>
								<label>月份:</label> <input name="filter_createTime" id="filter_createTime"
									class="date" dateFmt="yyyy-MM" size="10"
									value="${map.createTime }" readonly="readonly" />
							</div></td>
						<td>
						<label>逾期率:</label><input
							name="filter_overdue"
							type="text" size="10" value="${map.overdue }" class="required number"/>
						</td>
						<td>
						<label>风险率:</label><input
							name="filter_risks"
							type="text" size="10" value="${map.risks }" class="required number"/>
						</td>
					</tr>
					<tr>
						<td>
						<label>标准审批额度:</label><input
							name="filter_auditMoney"
							type="text" size="10" value="${map.auditMoney }"
							 alt="万" class="required number"/>
						</td>
						<td>
						<label>目标审核量:</label><input
							name="filter_auditNumber"
							type="text" size="10" value="${map.auditNumber }"
							 alt="个" class="required number"/>
						</td>
						<td>
						<label>个人行为:</label> <input
							name="filter_auditCheckup"
							type="text" size="10" value="${map.auditCheckup }"
							 alt="%" class="required number"/>
						</td>
						<td>
						<label>项目考核:</label><input
							name="filter_auditProject"
							type="text" size="10" value="${map.auditProject }"
							 alt="%" class="required number"/>
						</td>
						<td>
						<label>考核权重:</label><input
							name="filter_projectWeight"
							type="text" size="10" value="${map.projectWeight }"
							 alt="%" class="required number"/>
						</td>
						<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">计算</button>
							</div>
						</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent"
		style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div layoutH="81" class="panelBar" style="height: 100px;background: #fff">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="17" align="center">序号</th>
					<th width="10%">信审人员</th>
					<th width="8%">月份</th>
					<th width="8%">审批金额</th>
					<th width="8%">审核目标</th>
					<th width="8%">审批时效</th>
					<th width="8%">逾期率</th>
					<th width="8%">风险率</th>
					<th width="8%">KPI</th>
					<th width="10%">个人行为鉴定</th>
					<th width="8%">项目考核</th>
					<th width="8%" align="center">总分</th>
					<th width="17"></th>
				</tr>
				<tr>
					<th width="17" align="center"></th>
					<th width="10%"></th>
					<th width="8%"></th>
					<th width="8%">（权重20%）</th>
					<th width="8%">（权重20%）</th>
					<th width="8%">（权重15%）</th>
					<th width="8%">（权重30%）</th>
					<th width="8%">（权重15%）</th>
					<th width="8%">（权重80%）</th>
					<th width="8%">（权重20%）</th>
					<th width="8%">5%—10%加分项</th>
					<th width="8%" align="center"></th>
					<th width="17"></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listResult}" var="result" varStatus="st">
				<tr target="sid_audit" rel="">
					<td>${st.index + 1 }</td>
					<td>${result.auditPersonName }</td>
					<td>${result.createTime }</td>
					<td><fmt:formatNumber value="${result.moneyFinish}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.auditTarget}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.timeFinish}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.overdue}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.risks}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.KPI}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.auditCheckup}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.auditProject}" pattern="0.00" /></td>
					<td><fmt:formatNumber value="${result.total}" pattern="0.00" /></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	</div>
</div>

