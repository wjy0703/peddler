<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/xhLoanerAccount/listXhLoanerAccount" method="post">
		<div class="searchBar">
			<table width="80%"><tr><td><label>借款合同编号:</label> <input type="text"
					name="filter_loanContractId" value="${map.loanContractId}" /></td>
			<td><label>借款人姓名:</label> <input type="text"
					name="filter_loanApplyName" value="${map.loanApplyName}" /></td>
			<td><label>起始还款日期:</label> <input type="text" class="date" format="yyyy-MM-dd" size="20" readonly
					name="filter_qshkrq" value="${map.qshkrq}" /></td>
			<td><label>状态:</label>
			<select class="combox" name="filter_accountState">
							<option value="" selected="selected">全部</option>
							<option value="0" 
									<c:if test="${map.accountState=='0'}">selected="selected" </c:if>>正常
							</option>
							<option value="1" 
									<c:if test="${map.accountState=='1'}">selected="selected" </c:if>>结清
							</option>
							<option value="2" 
									<c:if test="${map.accountState=='2'}">selected="selected" </c:if>>逾期
							</option>
							<option value="3" 
									<c:if test="${map.accountState=='3'}">selected="selected" </c:if>>提前结清
							</option>
						</select>		
			</td>
			</tr>
			<tr>
			<td>
			<!-- 
			<label>运算符:</label> <input type="text"
					name="filter_tt" value="${map.tt}" /> -->
			<label>余额:</label> <input type="text"
					name="filter_accountBalance" value="${map.accountBalance}" />
			</td>
			<td>
			</td>
			<td>
			</td>
			<td>
				<div class="subBar">
				<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
			</div>
			</td>
			
			</tr>
			
		</table>
				
				
				<!--  <li><label>账户状态:</label> <select name="filter_accountState" class="combox"><option
							value="">请选择</option>
						<option value=0 selected>正常</option>
						<option value=1>历史</option>
				</select> </li>-->
			
			
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="${ctx}/xhLoanerAccount/editXhLoanerAccount/{sid_user}"
				target="navTab" warn="请选择一个还款账户"><span>录入还款信息</span></a></li>
			<li class="line">line</li>
			<li><a class="add"
				href="${ctx}/xhLoanerAccount/advanceXhLoanerAccount/{sid_user}"
				target="navTab" warn="请选择一个还款账户"><span>提前结清</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="${ctx}/xhLoanerAccountInfo/listXhLoanerAccountInfo/{sid_user}"
				target="navTab" warn="请选择一个还款账户"><span>查看账户交易明细</span></a></li>
			<li class="line">line</li>
			<li>
				<a class="icon" href="#" >
				<input type="file" name="uploadHk" id="uploadifyHk" />
				</a>
			</li>
		</ul>
	</div>
	<table class="table" width="" layoutH="141">
		<thead>
			<tr>
				<th width="28"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80">借款合同编号</th>
				<th width="80">借款人姓名</th>
				<th width="120">证件号码</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">借款金额</th>
				<th width="40">分期</th>
				<th width="100">起始还款日期</th>
				<th width="60">还款日</th>
				<th width="100">账户创建时间</th>
				<th width="100">账户余额</th>
				<th width="60" orderField="accountState" class="asc">账户状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>


		<tbody>
			<c:forEach items="${page.result}" var="user" varStatus="st">
				<tr target="sid_user" rel="${user.id}">
					<td><input name="ids" value="${user.id}" type="checkbox"></td>
					<td>${user.loanContract.jkhtbm}</td>
					<td>${user.loanApply.jkrxm}</td>
					<td>${user.loanApply.zjhm}</td>
					<td>${user.loanApply.province.name}</td>
					<td>${user.loanApply.city.name}</td>
					<td>￥<fmt:formatNumber value="${user.loanContract.htje}"
							pattern="#,#00.00" /></td>
					<td>${user.loanContract.hkqs}期</td>
					<td>${user.loanContract.qshkrq}</td>
					<td>${user.loanContract.hkr}日</td>
					<td>${user.loanContract.createTime}</td>
					<td>￥<fmt:formatNumber value="${user.accountBalance}"
							pattern="#,#00.00" /></td>

					<td><c:if test="${user.accountState==0}">正常</c:if>
					<c:if test="${user.accountState==1}"><span style="color: red">结清</span></c:if>
					<c:if test="${user.accountState==2}"><span style="color: red">逾期</span></c:if>
					<c:if test="${user.accountState==3}"><span style="color: red">提前结清</span></c:if></td>
					<td><a title="录入还款信息" target="navTab"
						href="${ctx }/xhLoanerAccount/editXhLoanerAccount/${user.id}"
						class="btnAdd">录入</a> <a title="查看交易记录" target="navTab"
						href="${ctx }/xhLoanerAccountInfo/listXhLoanerAccountInfo/${user.id}"
						rel="rel_listAccountInfo" class="btnLook">查看交易记录</a></td>
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
<script type="text/javascript">
		
	$(document).ready(function() {
		var $currentTab = navTab.getCurrentPanel();
	  	$("#uploadifyHk",$currentTab).uploadify({
			'removeCompleted' : false,
			'movieCount' : 15,
			'swf' : 'uploadify/threetwo/uploadify.swf',
			'uploader' : '${ctx}/xhLoanerAccountInfo/multiSaveAccountInfo',
			 buttonText :'批量上传还款',
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
      		    var msg = '成功批量还款' + data.count + '条数据';
				var errors = data.errors;
				if(errors.length != 0){
					msg += '<br><font color = "red" >失败记录：</font>'; 
      		    }
				for(var index = 0 ; index < errors.length ; index++){
					var error = errors[index];
					msg += '<br> 客户为 <font color = "red" >' + error.id + ':</font> ' + error.errorMsg; 
				}
				if(errors.length != 0){
      		    	alertMsg.error(msg);
      		    }else{
      		    	alertMsg.info(msg);
      		    }
      		    setTimeout(function(){
      		        $("#alertMsgBox").find('div.alertInner>h1').html('批量还款结果')
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
