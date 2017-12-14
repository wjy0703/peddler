<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<script type="text/javascript">
	function rePerName(orgid, orgname) {
		$("#filter_auditPerson", navTab.getCurrentPanel()).val(orgid);
		$("#filter_auditPersonName", navTab.getCurrentPanel()).val(orgname);
	}
</script>
<div class="pageContent" style="padding: 0px">
	<div class="tabs">
		<div class="tabsContent">
			<div>

				<div layoutH="12"
					style="float: left; display: block; overflow: auto; width: 160px; border: solid 1px #CCC; line-height: 21px; background: #fff">
					<ul class="tree treeFolder">
						<li><a href="#" onclick="rePerName('0', '全部初审人员')">全部初审人员</a>
							<ul>
								<c:forEach items="${allAuditPersons}" var="person"
									varStatus="st">
									<li><a href="javascript"
										onclick="rePerName('${person.id }','${person.name }')">${person.name
											}</a>
									</li>
								</c:forEach>
							</ul></li>
					</ul>
				</div>

				<div id="jbsxBox" class="unitBox" style="margin-left: 166px;">
					<div class="pageHeader" style="border: 1px #B8D0D6 solid">
						<form id="pagerForm" onsubmit="return divValidateSearch(this, 'jbsxBox');"
							action="${ctx}/loan/getAuditPersonKpi" method="post" class="pageForm required-validate">
							<div class="searchBar">
								<table class="searchContent">
									<tr>
										<td><label>信审员:</label><input type="hidden" name="filter_auditPerson"
											id="filter_auditPerson" value="${filter_auditPerson }" /> <input
											name="filter_auditPersonName" id="filter_auditPersonName"
											type="text" size="10" value="全部初审人员"
											readonly="readonly" /></td>
										<td>
											<div>
												<label>月份:</label> <input name="filter_createTime" id="filter_createTime"
													class="date" dateFmt="yyyy-MM" size="10"
													value="${filter_createTime }" readonly="readonly" />
											</div></td>
										<td>
										<label>逾期率:</label><input
											name="filter_overdue"
											type="text" size="10" value="0" class="required number"/>
										</td>
										<td>
										<label>风险率:</label><input
											name="filter_risks"
											type="text" size="10" value="0" class="required number"/>
										</td>
									</tr>
									<tr>
										<td>
										<label>标准审批额度:</label><input
											name="filter_auditMoney"
											type="text" size="10" value="1500000"
											 alt="万" class="required number"/>
										</td>
										<td>
										<label>目标审核量:</label><input
											name="filter_auditNumber"
											type="text" size="10" value="88"
											 alt="个" class="required number"/>
										</td>
										<td>
										<label>个人行为:</label> <input
											name="filter_auditCheckup"
											type="text" size="10" value="0"
											 alt="%" class="required number"/>
										</td>
										<td>
										<label>项目考核:</label><input
											name="filter_auditProject"
											type="text" size="10" value="0"
											 alt="%" class="required number"/>
										</td>
										<td>
										<label>考核权重:</label><input
										name="filter_projectWeight"
										type="text" size="10" value="0.5"
										class="required number"/>
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
					<div class="pageContent" style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
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
						</table>
						</div>
						
					</div>
				</div>

			</div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>

</div>