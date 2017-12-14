<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input type="hidden" name="numPerPage" value="${page.pageSize}" /> <input type="hidden" name="orderField" value="${page.orderBy}" />
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/makeLoans/listLoanApplyByState/60" method="post">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>客户姓名:</label> <input type="text" name="filter_jkrxm" value="${filter_jkrxm }" /></td>
					<td><label>客户电话:</label> <input type="text" name="filter_telephone" value="${filter_telephone }" /></td>
					<td><label>证件号码:</label> <input type="text" name="filter_zjhm" value="${filter_zjhm }" /></td>
					<td>
					<label>借款类型：</label>
				<sen:select clazz="combox required" name="filter_backup01" coding="backup01" id="backup01" value="${filter_backup01}" title="全部" titleValue=""/>
					</td>
				</tr>
				<tr>
					<td><label>所属城市:</label> <sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${filter_province},${filter_city}" /></td>
					<td><label>团队经理:</label> <input type="text" name="filter_teamName" value="${filter_teamName }" /></td>
					<td><label>销售人员:</label> <input type="text" name="filter_saleName" value="${filter_saleName }" />
					</td>
					<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">检索</button>
									</div>
								</div>
							</li>
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
			<bjdv:validateContent type="1" funcId="待放款-导出">
			<li>
			   <a class="icon" href="${ctx}/exportExcel?serviceName=releaseMoneyExcelService&filter_state=60" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>待放款导出</span></a>
			</li>
			</bjdv:validateContent>
			<bjdv:validateContent type="1" funcId="待放款-上传">
			<li>
				<a class="icon" href="#" >
				<input type="file" name="upload" id="uploadifyFk" />
				</a>
				
			</li>
			</bjdv:validateContent>
			<bjdv:validateContent type="1" funcId="确认放款-批量导出">
			<li>
			   <a class="icon" href="${ctx}/exportExcel?serviceName=dfkqrMoneyExcelService&filter_state=60&filter_backup=CREDIT" target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>确认放款导出</span></a>
			</li>
			</bjdv:validateContent>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="141" nowrapTD="false">
		<thead>
			<tr>
				<th width="60">合同编号</th>
				<th width="60">客户姓名</th>
				<th width="60">证件号码</th>
				<th width="80">借款类型</th>
				<th width="60">借款产品</th>
				<th width="60">申请金额</th>
				<th width="60">放款金额</th>
				<th width="30">期数</th>
				<!-- 	<th width="60">团队经理</th>
				<th width="60">客户经理</th> -->
				<th width="60">开户行</th>
				<th width="60">账号</th>
				<th width="80">开户名</th>
				<th width="40">状态</th>
				<th width="80" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr target="sid_jksq" rel="${jksq.ID}">
					<td>${jksq.LOAN_CODE }</td>
					<td>${jksq.JKRXM }</td>
					<td>${jksq.ZJHM}</td>
					<td><sen:vtoName value="${jksq.BACKUP01}" coding="backup01"/></td>
					<td><sen:vtoName value="${jksq.JK_TYPE}" coding="productType" /></td>
					<td>${jksq.JK_LOAN_QUOTA }</td>
					<td>${jksq.FKJE }</td>
					<td>${jksq.JK_CYCLE }</td>
					<%-- 	<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td> --%>
					<td>${jksq.BANK_OPEN }</td>
					<td>${jksq.BANK_NUM }</td>
					<td>${jksq.BANK_USERNAME }</td>
					<td style="color: red">待放款</td>
					<td>
					<bjdv:validateContent type="1" funcId="待放款-放款">
						<div class="buttonActive">
							<div class="buttonContent">
								<a title="放款 " href="${ctx}/makeLoans/addMakeLoans/${jksq.ID}" target="navTab">放款</a>
							</div>
						</div>
					</bjdv:validateContent>
					<bjdv:validateContent type="1" funcId="确认放款-退回">	
					<c:if test="${jksq.BACKUP01=='CREDIT'}">
					<div class="buttonActive">
							<div class="buttonContent">
								<a target="dialog" class = "customerAbandon" mask="true" width="600" height="160" close="closeDialogFkth"
								href="${ctx}/makeLoans/MakeLoansBack/${jksq.ID}" >退回</a>
							</div>
						</div>
					</c:if>
					</bjdv:validateContent>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10" <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20" <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50" <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select> <span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>
	</div>
</div>

<script type="text/javascript">
		
	$(document).ready(function() {
		var $currentTab = navTab.getCurrentPanel();
	  	$("#uploadifyFk",$currentTab).uploadify({
			'removeCompleted' : false,
			'swf' : 'uploadify/threetwo/uploadify.swf',
			'uploader' : '${ctx}/makeLoans/multiMakeLoan',
			 buttonText :'批量上传放款记录',
			// buttonImage:'uploadify/threetwo/add.jpg',
			'auto' : true,
			'multi' : false,
			'successTimeout' : 99999,
			'fileTypeExts' : '*.xlsx;*.xls;',
			'fileSizeLimit' : '10MB',
			'uploadLimit': 10,
			 width:100,
     		 'onUploadSuccess':function(file,data){//上传单个文件成功调用的方法（注：uploadify多文件上传也是发送了多次请求，对应多个request）,其中第二个参数为data数据。
			    var $ajaxbg = $("#background,#progressBar");
      		    $ajaxbg.hide();
      		    data = $.parseJSON(data);
      		    var msg = '成功批量放款' + data.count + '条数据';
				var errors = data.errors;
				if(errors.length != 0){
					msg += '<br><font color = "red" >失败记录：</font>'; 
      		    }
				for(var index = 0 ; index < errors.length ; index++){
					var error = errors[index];
					msg += '<br> 合同编号为 <font color = "red" >' + error.id + ':</font> ' + error.errorMsg; 
				}
				if(errors.length != 0){
      		    	alertMsg.error(msg);
      		    }else{
      		    	alertMsg.info(msg);
      		    }
      		    setTimeout(function(){
      		        $("#alertMsgBox").find('div.alertInner>h1').html('批量放款结果')
      		    	$("#alertMsgBox").css('width','350px');
      		    },250);
      		    navTab.reload();
			 },
      		 onUploadStart:function(){
      		   var $ajaxbg = $("#background,#progressBar");
      		   $ajaxbg.show();
      		}
		});
		
	});
</script>

<script>
function closeDialogFkth(param){
	navTab.reloadFlag('rel_listXhMakeLoans');
	var $dialog = $.pdialog.getCurrent();
	$dialog.hide();
	$("div.shadow").hide();
	if($dialog.data("mask")){
		$("#dialogBackground").hide();
	} else{
		if ($dialog.data("id")) $.taskBar.closeDialog($dialog.data("id"));
	}
}
</script>
