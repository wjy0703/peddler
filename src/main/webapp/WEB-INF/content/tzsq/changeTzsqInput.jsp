<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx}/xhTzsq/saveTzsqChange"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhTzsq.id}" /> <input
				type="hidden" name="xhTzsq.id" value="${xhTzsq.xhTzsq.id}" /> <input
				type="hidden" name="state" id="state" value="0" />
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td><label>客户编码：</label> <input type="text" class="required"
							name="cjrxx.khbm" value="${xhTzsq.cjrxx.khbm}"
							readonly="readonly" size="20" /></td>
						<td><label>客户姓名：</label> <input name="cjrxx.cjrxm"
							type="text" size="30" value="${xhTzsq.cjrxx.cjrxm }"
							maxlength="40" disabled="disabled" /></td>
						<td><label>证件号码：</label> <input name="cjrxx.zjhm"
							type="text" size="30" value="${xhTzsq.cjrxx.zjhm }"
							maxlength="40" disabled="disabled" /></td>
					</tr>
				</table>
				<div class="divider"></div>
				<div class="panel">
					<h1>出借信息</h1>
				</div>
				<table>
					<c:if test="${lentcount=='1' }">
						<tr>
							<td><label>计划出借日期：</label> <input name="jhtzrq" type="text"
								size="25" value="${xhTzsq.jhtzrq}" class="required date"
								maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>
							<td><label>计划出借金额：</label> <input name="jhtzje" type="text"
								size="30" value="${xhTzsq.jhtzje}" class="required"
								maxlength="20" /></td>
						</tr>
					</c:if>
					<c:if test="${lentcount!='1' }">
						<tr>
							<td><label>计划出借日期：</label> <input name="jhtzrq" type="text"
								size="25" value="${xhTzsq.jhtzrq}" maxlength="20" /></td>
							<td><label>计划出借金额：</label> <input name="jhtzje" type="text"
								size="30" value="${xhTzsq.jhtzje}" maxlength="20" /></td>
						</tr>
					</c:if>
					<tr>
						<td><label>出借方式：</label> <select name="tzcp.id"
							class="required combox">
								<option value=""
									<c:if test="${xhTzsq.tzcp.id==''}">selected</c:if>>请选择</option>
								<c:forEach items="${tzcp}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${xhTzsq.tzcp.id==md.id}">selected</c:if>>${md.tzcpMc}</option>
								</c:forEach>
						</select></td>
						<td><label>付款方式：</label><%--  <select name="fkfs"
							class="required combox">
								<option value="" <c:if test="${xhTzsq.fkfs==''}">selected</c:if>>请选择</option>
								<c:forEach items="${fkfs}" var="md" varStatus="st">
									<option value="${md.value }"
										<c:if test="${xhTzsq.fkfs==md.value}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select> --%>
						<sen:select clazz="combox"  coding="payType" name="fkfs" value="${xhTzsq.fkfs }" />
						</td>
					</tr>
				</table>
				<div class="divider"></div>
				<div class="panel">
					<h1>出借及回款银行账户信息</h1>
				</div>

				<p>
					<label>银行名称 <font color="red">*</font>：</label><%-- <select
						name="tzfkkhh" id="tzfkkhh" class="required combox">
						<option value=""
							<c:if test="${xhTzsq.tzfkkhh==''}">selected</c:if>>请选择</option>
						<c:forEach items="${khh}" var="md" varStatus="st">
							<option value="${md.value }"
								<c:if test="${xhTzsq.tzfkkhh==md.value}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> --%>
					<sen:select clazz="combox"  coding="bank" name="tzfkkhh" value="${xhTzsq.tzfkkhh }" />
				</p>
				<div class="divider"></div>
				<p>
					<label>具体开户支行：</label><input name="tzfkyhmc" type="text" size="40"
						value="${xhTzsq.tzfkyhmc}" maxlength="80" />
				</p>
				<div class="divider"></div>
				<p>

					<label>开户姓名：</label> <input name="cjrxx.tzfkkhmc" type="text"
						size="10" value="${xhTzsq.tzfkkhmc}" maxlength="80"
						readonly="readonly" />
				</p>
				<p>
					<label>账户：</label><input name="tzfkyhzh" id="tzfkyhzh" type="text"
						size="25" value="${xhTzsq.tzfkyhzh}" maxlength="80" />
				</p>
				<div class="divider"></div>

				<p>
					<label>确认账户：</label> <input name="cjrxx.tzfkyhzh2" type="text"
						size="25" value="${xhTzsq.tzfkyhzh}" maxlength="80"
						equalto="#tzfkyhzh" />
				</p>
				<div class="divider"></div>
				<div>
					上传附件：
					  <div style="position:absolute;">
					   <input name="file" type="file" id="uploadify07" />
					  <input name="fileName" type="hidden" id="fileName" />
					  <input name="newFileName" type="hidden" id="newFileName" />
						<c:if test="${!empty xhTzsq.id && !empty xhUploadFiles.id}">
							<a class="icon" href="${ctx}/loan/downFile/${xhUploadFiles.id}" targetType="navTab" title="下载当前文件吗?"><span>${xhUploadFiles.filename}</span></a>
						</c:if>
					  </div>
				</div>
				<c:if test="${xhTzsq.upstate=='3' }">
				<div class="divider"></div>
					<dl class="nowrap">
						<dt>审批意见：</dt>
						<dd>
							<textarea name="auditIdea" style="width: 93%; height: 80"
								disabled="disabled">${xhTzsq.auditIdea }</textarea>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>审批结果：</dt>
						<dd>
							<input type="radio" name="upstate" value="2" disabled="disabled" />通过
							<input type="radio" name="upstate" value="3" checked="checked"
								disabled="disabled" />不通过
						</dd>
					</dl>
				</c:if>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(0);">保存</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(1);">提交</button>
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

<script type="text/javascript">
	$(document).ready(function() {
	    var $currentTab = navTab.getCurrentPanel();
	  	$("#uploadify07",$currentTab).uploadify({
			'removeCompleted' : false,
			'swf' : 'uploadify/threetwo/uploadify.swf',
			'uploader' : '${ctx}/xhTzsq/saveChangeLoadFile',
			// buttonText :'批量上传放款记录',
			 buttonImage:'uploadify/threetwo/add.jpg',
			'auto' : true,
			'multi' : false,
			'successTimeout' : 99999,
			'fileTypeExts' : '*.*;',
			'fileSizeLimit' : '10MB',
			'uploadLimit': 10,
			 width:100,
     		 'onUploadSuccess':function(file,data){//上传单个文件成功调用的方法（注：uploadify多文件上传也是发送了多次请求，对应多个request）,其中第二个参数为data数据。
			    var $ajaxbg = $("#background,#progressBar");
			    $ajaxbg.hide();
			    data = $.parseJSON(data);
			    alert(data.newFileName);
			    $('#fileName').val(data.fileName);
			    $('#newFileName').val(data.newFileName);
			 },
      		 onUploadStart:function(){
      		   var $ajaxbg = $("#background,#progressBar");
      		   $ajaxbg.show();
      		}
		});
		
		setTimeout(function(){
			var left = $('#SWFUpload_uploadify07',$currentTab).position().left;
			$('#uploadify07-button',$currentTab).css({position:'absolute',left:left});
		},250);
		
	});
</script>
<!-- mdy这里将要有错误 -->