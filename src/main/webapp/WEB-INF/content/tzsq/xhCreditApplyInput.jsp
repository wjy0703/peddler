<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx}/xhTzsq/saveCreditApply"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhTzsq.id}" /> 
			<div class="pageFormContent" layoutH="56">
			<div class="divider"></div>
				<div class="panel">
					<h1>客户转让意向</h1>
				</div>
				<table>
						<tr>
							<td><label>客户意向转让日：&nbsp;</label>
							 <input 
						name="creditdate" value="" type="text"  class="date required" readonly="readonly" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><label>是否加急：</label>
							<%-- <sen:selectRadio name="a" coding="isExtension" value="" /> --%>
							 <input name="isurgent" value="1" type="radio" onblur="isUrgent()" />是
							 <input name="isurgent" value="0" type="radio" onblur="isUrgent()" />否
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td><label>转让服务费：</label><input name="servicefee" value="" type="text" id="servicefee"/></td>
						</tr>
				</table>
				<div class="divider"></div>
				<div class="panel">
					<h1>上传附件</h1>
				</div>
				<div>
					上传附件：
					  <div style="position:absolute;">
					   <input name="file" type="file" id="uploadify07" />
					  <input name="fileName" type="hidden" id="fileName" />
					  <input name="newFileName" type="hidden" id="newFileName" />
					  </div>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
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
			    //alert(data.newFileName);
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
		
		//isUrgent($currentTab);
		
	});
	
	 function isUrgent(){//是否加急
		var $currentTab = navTab.getCurrentPanel();
		var livestate1 = $('input[name="isurgent"]:checked', $currentTab).val();
		var $livestate = $('#servicefee', $currentTab);
		if (livestate1 == '1') {
			$livestate.addClass("required");
		} else {
			$livestate.removeClass();
		}
	} 
</script>
<!-- mdy这里将要有错误 -->