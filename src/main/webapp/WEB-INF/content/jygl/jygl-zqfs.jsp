<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/jygl/jyglZqfs" method="post">
		<input type="hidden" name="filter_subFlag" value="1" />
		<div class="pageFormContent">
			<table>
				<tr>
					<%-- <td><label>客户编码：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td> --%>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm}" /></td>
					<td><label>账单日：</label> 
					<select class="combox" name="filter_zdr">
						<option value="" <c:if test="${map.zdr==''}">selected</c:if>>全部</option>
						<option value="15" <c:if test="${map.zdr=='15'}">selected</c:if>>15</option>
						<option value="30" <c:if test="${map.zdr=='30'}">selected</c:if>>30</option>
					</select>
					</td>
					
					<td><label>债权制作状态：</label>
					<sen:select clazz="combox"  coding="agreementMakeState" name="filter_agreementMakeState" value="${map.agreementMakeState}" title="全部"/>
					
					<td><label>邮件发送状态：</label>
					<sen:select clazz="combox"  coding="billSendState" name="filter_billSendState" value="${map.billSendState}" title="全部"/>
					</td>
					
					</tr>
					<tr>
					<td>
					<label>交割状态：</label>
					<select class="combox" name="filter_statedg">
						<option value="" <c:if test="${map.statedg==''}">selected</c:if>>全部</option>
						<option value="0" <c:if test="${map.statedg=='0'}">selected</c:if>>待订购</option>
						<option value="1" <c:if test="${map.statedg=='1'}">selected</c:if>>待交割</option>
						<option value="2" <c:if test="${map.statedg=='2'}">selected</c:if>>已交割</option>
						<option value="3" <c:if test="${map.statedg=='3'}">selected</c:if>>已结束</option>
						<option value="8" <c:if test="${map.statedg=='8'}">selected</c:if>>待提交划扣</option>
						<option value="7" <c:if test="${map.statedg=='7'}">selected</c:if>>待划扣审批</option>
						<option value="6" <c:if test="${map.statedg=='6'}">selected</c:if>>待结算划扣</option>
					</select>
					</td>
					<td><label>债权状态：</label> 
					<select class="combox" name="filter_lent_state">
						<option value="" <c:if test="${map.lent_state==''}">selected</c:if>>全部</option>
						<option value="0" <c:if test="${map.lent_state=='0'}">selected</c:if>>首期</option>
						<option value="1" <c:if test="${map.lent_state=='1'}">selected</c:if>>非首期</option>
					</select>
					</td>
					
						<td><label>所在城市：</label> <select class="combox"
						name="filter_province" ref="combox_listZqjgcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city"
						id="combox_listZqjgcity">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
					</select></td>
							
					<td><label>账单收取方式：</label>
						<sen:select clazz="combox"  coding="sendModle" name="filter_zqjsfs" value="${map.zqjsfs}" title="全部"/>
					</td>
					
					
					</tr>
					<tr>
					
					<td><label>计划出借日期：</label> <input name="filter_lenjhtzrq" type="text"
						size="17" value="${map.lenjhtzrq}" class="date"
						maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>
				
					
					<td ><label>出借产品：</label><select name="filter_tzcp"
						class="required combox">
							<option value="" <c:if test="${map.tzcp==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.tzcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select>
					</td>
					<%-- <select name="filter_tzcp"
						class="required combox">
							<option value="" <c:if test="${map.tzcp==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.tzcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select> --%>
					
					<td colspan="2"><label>邮箱地址：</label> <input type="text"
						name="filter_dzyx" value="${map.dzyx}" /></td>
					
					<td ><div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">检索</button>
										</div>
									</div></li>
								<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
							</ul>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			
			<bjdv:validateContent type="1" funcId="债权交易-批量发送">
				<li><a title="确实要发送这些记录吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx }/jygl/saveMailPl" class="add"
				warn="请至少选择一条记录"><span>批量发送</span></a></li>
			</bjdv:validateContent> 
			
			
			<c:if test="${map.statedg !='' && map.statedg!='0'}">
				<bjdv:validateContent type="1" funcId="债权订购-批量下载">
				<li><a title="确实要下载这些记录吗?" id="wordDown"  class="add" onclick="return downFile();"
				href="#">
				<span>批量下载（word格式）</span></a>
				<!-- <font color="red">(说明：仅下载当前页面记录的债权文件。)
				</font> -->
				</li>
				</bjdv:validateContent>
				</c:if>
				
				<c:if test="${map.statedg !='' && map.statedg!='0'}">
				<bjdv:validateContent type="1" funcId="债权订购-批量下载">
				<li><a title="确实要下载这些记录吗?" id="pdfDown" class="add" onclick="return downFile();" 
				href="#">
				<span>批量下载（PDF格式）</span></a>
				<font color="red">(说明：仅下载当前页面记录的债权文件。)
				</font>
				</li>
				</bjdv:validateContent>
				</c:if>
				
		</ul>
	</div>
	<table class="table" width="1300" layoutH="190" nowrapTD="true">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="150">出借编号</th>
				<th width="80">客户姓名</th>
				<th width="80">客户编码</th>
				<th width="80">所在城市</th>
				<th width="100">计划出借日期</th>
				<th width="80">计划出借金额</th>
				<th width="80">出借产品</th>
				<th width="10%">推荐债权价值</th>
				<th width="10%">债权状态</th>
				<th width="80">账单发送状态</th>
				<th width="80">账单收取方式</th>
				<th width="80">债权制作状态</th>
				<th width="60">邮箱地址</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listZqtj}" var="zd" varStatus="st">
				<tr target="sid_zd" rel="${zd.ID}">
					<td>
						<c:if test="${zd.AGREEMENT_MAKE_STATE=='2'}"><input name="ids" value="${zd.ID}" type="checkbox">
						</c:if>
					</td>
					<td>${zd.TZSQBH}</td>
					<td>${zd.CJRXM }</td>
					<td style="text-align: right">${zd.KHBM }</td>
					<td>${zd.CITYNAME }</td>
					<td>${zd.JHTZRQ}</td>
					<td>￥<fmt:formatNumber
							value="${zd.MONEY}" pattern="#,#00.00" /></td>
					<td>${zd.TZCP_MC}</td>
					<td>￥<fmt:formatNumber
							value="${zd.MONEY}" pattern="#,#00.00" /></td>
					<td>
					<c:if test="${zd.LENT_STATE=='0'}">首期</c:if>
					<c:if test="${zd.LENT_STATE=='1'}">非首期</c:if>
					
					<c:if test="${zd.STATEDG=='0'}">- 待订购</c:if>
					<c:if test="${zd.STATEDG=='1'}">- 待交割</c:if>
					<c:if test="${zd.STATEDG=='2'}">- 已交割</c:if>
					<c:if test="${zd.STATEDG=='3'}">- 已结束</c:if>
					<c:if test="${zd.STATEDG=='8'}">-待提交划扣</c:if>
					<c:if test="${zd.STATEDG=='7'}">-待划扣审批</c:if>
					<c:if test="${zd.STATEDG=='6'}">-待结算划扣</c:if>
					</td>
					<td><sen:vtoName coding="billSendState" value="${zd.BILL_SEND_STATE}" /></td>
					<td>${zd.ZQJSFS}</td>
					<td><sen:vtoName coding="agreementMakeState" value="${zd.AGREEMENT_MAKE_STATE}" /></td>
					<td>${zd.DZYX}</td>
					<%-- <td>
					<c:if test="${zd.STATEDG=='1'}">
					<bjdv:validateContent type="1" funcId="债权交割-交割">
					<div class="buttonActive">
				      <div class="buttonContent">
					<a title="交割" target="ajaxTodo"
						href="${ctx }/jygl/saveZqjg?id=${zd.ID}&statedg_href=${map.statedg}">交割</a>
						</div>
		            </div>
		            </bjdv:validateContent>
		            <bjdv:validateContent type="1" funcId="债权交割-撤销">
		            <div class="buttonActive">
				      <div class="buttonContent">
					<a title="撤销" target="ajaxTodo"
						href="${ctx }/jygl/reBack?flag=jg&id=${zd.ID}&statedg_href=${map.statedg}">撤销</a>
						</div>
		            </div>
		            </bjdv:validateContent>
					</c:if>	
					</td> --%>
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
						<div class="buttonActive">
				    	  <div class="buttonContent">
							<a title="查看协议" target="navTab"
								href="${ctx }/jygl/lookZqdgReport?id=${zd.ID}&LENT_STATE=${zd.LENT_STATE}">查看协议</a>
						  </div>
		               </div>
		               
		               <div class="buttonActive">
				    	  <div class="buttonContent">
		              			 <a title="下载资金出借月报(word格式)" 
									href="${ctx }/jygl/downFile?id=${zd.ID}&LENT_STATE=${zd.LENT_STATE}"
									> 下载（word格式）
								</a>
					  	</div>
		               </div>
		                <div class="buttonActive">
				    	  <div class="buttonContent">
		              			 <a title="下载资金出借月报(PDF格式)" 
									href="${ctx }/jygl/downFilePDF?id=${zd.ID}&LENT_STATE=${zd.LENT_STATE}"
									> 下载（PDF格式）
								</a>
					  	</div>
		               </div>
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
		var href = "${ctx }/jygl/downFileMorePDF?1=1";
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
		var href = "${ctx }/jygl/downFileMore?1=1";
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



