<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhCapitalLoanReport/listXhCapitalLoanReport"
		method="post">
		<div class="pageFormContent">
			<table>
				<tr>
				<td><label>客户编号:</label><input type="text"
					name="filter_lenderNumber" value="${map.lenderNumber}" /></td>
				<td><label>客户姓名:</label> <input type="text"
					name="filter_lenderName" value="${map.lenderName}" /></td>
				<td><label>报告日:</label> 
				<input type="text" name="filter_reportDate" id="filter_reportDate"
							class="date" format="yyyy-MM-dd" yearstart="-20"
							value="<fmt:formatDate value='${map.reportDate}' pattern='yyyy-MM-dd' />" size="17" readonly="true" /> <a
							class="inputDateButton" href="javascript:;">选择</a> 
				</td>
				
				</tr>
					<tr>
					<td><label>所在省市：</label>
					<sen:address names="filter_province,filter_city" titles="所有省市,所有城市" />
					</td>
					
					
					<td>
					<label>账单发送状态：</label>
					<sen:select clazz="combox"  coding="billSendState" name="filter_billSendState" value="${map.billSendState}" title="全部"/>
					</td>
					<td><label>投资产品：</label><select name="filter_tzcp"
						class="required combox">
							<option value="" <c:if test="${map.tzcp==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.tzcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select>
				</td>
				</tr>
				<tr>
				<!-- 
					<td>
					<label>状态：</label><select name="filter_state"
						class="required combox">
							<option value="">请选择</option>
							<option value="0">待生成</option>
							<option value="1">已生成</option>
					</select> 
					</td>
					 -->
					<td><label>账单收取方式：</label>
						<sen:select clazz="combox"  coding="sendModle" name="filter_zqjsfs" value="${map.zqjsfs}" title="全部"/>
					</td>
					<td><label>报表制作状态：</label>
					<sen:select clazz="combox"  coding="agreementMakeState" name="filter_state" value="${map.state}" title="全部"/>
				
				<td><div class="subBar">
							<ul>
								<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return resetDate('filter_reportDate');">检索</button>
							</div>
						</div></li>
								<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
							</ul>
						</div>
						</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">

			<li><a class="edit"
				href="${ctx}/xhCapitalLoanReport/editXhCapitalLoanReport/{sid_user}"
				target="navTab" warn="请选择一个报告"><span>查看报告明细</span></a></li>
			<li class="line">line</li>
			<bjdv:validateContent type="1" funcId="资金出借月报-批量生成">	
			<li><a title="确实要批量生成报告吗?" target="selectedTodo" rel="ids"
			postType="string"  href="${ctx}/xhCapitalLoanReport/batchMakeCapitalLoanReport" class="icon"
			warn="请至少选择一条记录"><span>批量生成报告</span></a></li>
			</bjdv:validateContent>	
			<bjdv:validateContent type="1" funcId="资金出借月报-批量发送">
			<li class="line">line</li>
			<li><a title="确实要发送这些记录吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx }/xhCapitalLoanReport/saveMailPl" class="add"
				warn="请至少选择一条记录"><span>批量发送</span></a></li>
				</bjdv:validateContent>
			<c:if test="${!empty page.result}">
			<bjdv:validateContent type="1" funcId="资金出借月报-批量下载">
			<li class="line">line</li>
			<li><a title="确实要下载这些记录吗?" class="add" id="wordDown"
			href="#">
			<span>批量下载(word格式)</span></a>
			<!-- <font color="red">(说明：仅下载当前页面记录的资金出借月报文件。)
			</font> -->
			</li>
			</bjdv:validateContent>	
				</c:if>
			<%-- <c:if test="${!empty page.result}">
			<bjdv:validateContent type="1" funcId="资金出借月报-批量下载">
			<li class="line">line</li>
			<li><a title="确实要下载这些记录吗?" class="add" 
			href="${ctx}/xhCapitalLoanReport/downLoadXhCapitalLoanReports?pageNum=${page.pageNo }&numPerPage=${page.pageSize }&filter_reportDate=<fmt:formatDate value='${map.reportDate}' pattern='yyyy-MM-dd' />&filter_lenderNumber=${map.lenderNumber }&filter_lenderName=${map.lenderName }&filter_tzcp=${map.tzcp }">
			<span>批量下载(word格式)</span></a>
			<!-- <font color="red">(说明：仅下载当前页面记录的资金出借月报文件。)
			</font> -->
			</li>
			</bjdv:validateContent>	
				</c:if> --%>
				
				<c:if test="${!empty page.result}">
			<bjdv:validateContent type="1" funcId="资金出借月报-批量下载">
			<li class="line">line</li>
			<li><a title="确实要下载这些记录吗?" class="add" id="pdfDown"
			href="#">
			<span>批量下载(PDF格式)</span></a>
			<font color="red">(说明：仅下载当前页面记录的资金出借月报文件。)
			</font>
			</li>
			</bjdv:validateContent>	
				</c:if>
		</ul>
	</div>
	
	<table class="table" width="1300" layoutH="180" nowrapTD="true">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="100" >出借编号</th>
				<th width="100" >客户姓名</th>
				<th width="100" >客户编号</th>
				<th width="100" >所在省市</th>
				<th width="100" >投资产品</th>
				<th width="100">报告周期</th>
				<th width="100" >账户级别</th>
				<th width="100" >报告日</th>
				<th width="100" >报告日资产总额</th>
				<th width="100" >账单发送状态</th>
				<th width="100" >账单收取方式</th>
				<th width="100" >报表制作状态</th>
				<!-- 
				<th width="100">状态</th>
				 -->
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.tzsq.tzsqbh}</td>
					<td>${user.lenderName}</td>
					<td>${user.lenderNumber}</td>
					<%-- <td>${user.tzsq.cjrxx.city}</td>
					
					<td><sen:vtoName coding="billSendState" value="${user.billSendState}" /></td> --%>
					<%-- <td><sen:address names="city" titles="所属省市" required="true" values="${user.tzsq.cjrxx.city}" /></td> --%>
					<td><%-- <sen:addressName id="${user.tzsq.cjrxx.province}" /> --%>
						<sen:addressName id="${user.tzsq.cjrxx.city}" />
					</td>
					<td>${user.tzsq.tzfs}</td>
					<td>${user.reportCycle}</td>
					<td><c:if test="${user.accountLevel=='1'}">信和账户</c:if></td>
					<td><fmt:formatDate value="${user.reportDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>￥<fmt:formatNumber value="${user.allMoneyOfCurrent}"
							pattern="#,#00.00" /></td>
					<td><sen:vtoName coding="billSendState" value="${user.billSendState}" /></td>
					<td>${user.tzsq.cjrxx.zqjsfs}</td>
					<td><sen:vtoName coding="agreementMakeState" value="${user.state}" /></td>
					<!--
					<c:if test="${user.state=='0' }">
						<td style="color: red">待生成</td>
					</c:if>
					<c:if test="${user.state=='1' }">
						<td style="color: green">已生成</td>
					</c:if>
					<td><a title="资金出借月报明细" target="navTab"
						href="${ctx }/xhCapitalLoanReport/editXhCapitalLoanReport/${user.id}"
						class=""> <img alt="" src="resources/chakan.png">
					</a> <c:if test="${user.state=='1' }">

							<a title="下载资金出借月报"
								href="${ctx }/xhCapitalLoanReport/downLoadXhCapitalLoanReport?id=${user.id}"
								> <img alt="" src="resources/xiazai.png">
							</a>
							<a title="资金出借报告" target="navTab" 
								href="${ctx }/xhCapitalLoanReport/lookXhCapitalLoanReport?id=${user.id}"
								> <img alt="" src="resources/baogao.png">
							</a>
						</c:if></td>  -->
						<td>
							<div class="buttonActive">
							  <div class="buttonContent">
								  <button onclick="return showOrHide(this);">操作</button>
							  </div>
							</div>
							
							<div  class='OperationsPopUp' style='display: none; position: absolute;  
						
								  text-align: justify; font-size: 12px;height:40px;width:300px;  
								  filter:alpha(opacity=100);
								  opacity:0.9;
								  background:#fff;
								  border:2px orange solid;
								  padding:20px;
								  padding-left:20px;
								  word-wrap:break-word;
								  line-height:100%;
							'>
								<a title="资金出借月报明细" target="navTab"
								   href="${ctx }/xhCapitalLoanReport/editXhCapitalLoanReport/${user.id}"
								   class=""> <img alt="" src="resources/chakan.png">
						        </a> 
						     <%--  <c:if test="${user.state=='1' }">  --%>
	<div class="buttonActive">
				     <div class="buttonContent">
								<a title="下载资金出借月报(PDF格式)"
									href="${ctx }/xhCapitalLoanReport/downLoadXhCapitalLoanReport?id=${user.id}"
									> 下载PDF
								</a>
								</div>
								</div>
								<div class="buttonActive">
				     <div class="buttonContent">
								<a title="下载资金出借月报(word格式)"
									href="${ctx }/xhCapitalLoanReport/downLoadXhCapitalLoanReportWord?id=${user.id}"
									> 下载WORD
								</a>
								</div>
								</div>
								<a title="资金出借报告" target="navTab" 
									href="${ctx }/xhCapitalLoanReport/lookXhCapitalLoanReport?id=${user.id}"
									> <img alt="" src="resources/baogao.png">
								</a>
							
						    	 <%-- </c:if>  --%>
							</div>
						</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="2" <c:if test="${page.pageSize=='2'}">selected</c:if>>2</option>
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
<script>
$(function(){
	var $box = navTab.getCurrentPanel();
	$("#pdfDown").click(function(){
		var href = "${ctx }/xhCapitalLoanReport/downLoadXhCapitalLoanReportsPDF?1=1&filter_reportDate=<fmt:formatDate value='${map.reportDate}' pattern='yyyy-MM-dd' />";
		if($('input[name="ids"]:checked').size() == 0){
			alertMsg.error("请选择要下载的数据！");
			return;
		}
		$('input[name="ids"]:checked').each(function(){
			href += '&needDownPDFId=' + $(this).val();
		});
		 $(this).attr('href',href);
	});
	$("#wordDown").click(function(){
		var href = "${ctx }/xhCapitalLoanReport/downLoadXhCapitalLoanReports?1=1&filter_reportDate=<fmt:formatDate value='${map.reportDate}' pattern='yyyy-MM-dd' />";
		if($('input[name="ids"]:checked').size() == 0){
			alertMsg.error("请选择要下载的数据！");
			return;
		}
		$('input[name="ids"]:checked').each(function(){
			href += '&needDownWordId=' + $(this).val();
		});
		 $(this).attr('href',href);
	});
});
</script>
