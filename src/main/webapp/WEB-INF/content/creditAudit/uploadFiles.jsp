<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- <link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
 --><style type="text/css" media="screen">
.uploadify-button {
	background: none;
	border: none;
	text-shadow: none;
	border-radius: 0;
}

.uploadify:hover .uploadify-button {
	background: none;
	border: none;
}

.fileQueue {
	width: 100%;
	height: 250px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
#some_file_queue {
   width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
#successInfos{
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
	color: blue;
}

#propInfo{
	position:absolute;
    top:20px;
	left:115px;
	font-size:14px;
}
#propInfo span.info{
	color:blue;
	float:right;
	display:block;
}
#propInfo span.infoTitle{
	color:red;
}

</style>


<link rel="stylesheet" type="text/css" href="uploadify/threetwo/uploadify.css">
<script type="text/javascript">
	function beforeInitUpload(){
		$('#uploadedFlag').val("0");
		/* $('.close',$('.dialogHeader_c')).hide();
		$('.maximize',$('.dialogHeader_c')).hide(); */
		$("div.formBar",$.pdialog.getCurrent()).hide();
		$("a.closeButton",$.pdialog.getCurrent()).click(function(self){
			$('#hiddenCloseButton').click();
			var param = {};
			param.statusCode = DWZ.statusCode.ok;
			navTabAjaxDone(param);
			return false;
		});
	}
	
	$(document).ready(function() {
	
	    $('a.close',$.pdialog.getCurrent()).click(function(){
	        var param = {};
			param.statusCode = DWZ.statusCode.ok;
			navTabAjaxDone(param);
	    });
	    
		var filesUpload = [];//记录用户选择了哪些文件上传。
		var successUploadFile = [];//记录了后台服务器上传成功的文件。
		var successFileInfos = [];//记录了后台服务器上传成功的文件,后台传递回的数据，包含新文件名。
		$("#uploadify03").uploadify({
			'removeCompleted' : false,
			'swf' : 'uploadify/threetwo/uploadify.swf',
			'uploader' : '${upUrl}?id=${Id }&upflag=${upflag }',
			 buttonText :'',
			 buttonImage:'uploadify/threetwo/add.jpg',
			'auto' : false,
			'multi' : true,
			'successTimeout' : 99999,
			'fileTypeExts' : '*.jpg;*.png;*.pdf;*.zip;*.rar',
			'fileSizeLimit' : '20MB',
			'uploadLimit': 10,
			 width:100,
			 queueID:'fileQueue',
			 'onUploadSuccess':function(file,data){//上传单个文件成功调用的方法（注：uploadify多文件上传也是发送了多次请求，对应多个request）,其中第二个参数为data数据。
			    if(data != '0'){
				    successUploadFile.push(file.name);
				    successFileInfos.push(data);				    
				}
			 },
			'onQueueComplete': function(file,data){ //所有文件上传成功后触发的方法。  
   				if($(successUploadFile).not(filesUpload).length == 0 && $(filesUpload).not(successUploadFile).length == 0){
	   					$.ajax({
					     type: 'POST',
					     url: '${upUrl}Back?id=${Id }&jksqId=${jksqId }',
					     data:{fileName:successFileInfos.toString()},
					     success:function(data){
					    		if(data=='1'){
					    			//if($(successUploadFile).not(filesUpload).length == 0 && $(filesUpload).not(successUploadFile).length == 0){
					        		   $("#successInfos").html("所有文件上传成功");
					        		/* }else{
					        		   var notUploaded = $(filesUpload).not(successUploadFile);
					        		   var message = "有文件未上传成功<br/>";
					        		   for(var index = 0 ; index < notUploaded.length ; index++){
					        		   		message += notUploaded[index] + ",";
					        		   }
					        		   message = message.substring(0, message.length - 1);
					        		   $("#successInfos").html(message);
					        		}
					        		$('div.cancel',$(".uploadify-queue-item")).hide();
					        		$('.successUploaded').hide();
					        		$('#propInfo').hide(); */
					        		//$("div.formBar").show();
					    		}
					     }
				   });
   				}else{
   					 $("#successInfos").html("上传失败，请重新上传");
   				}
      		},
      		'onUploadError':function(file){//上传失败的时候调用的方法
      		    //取消时貌似也调用此方法，郁闷
      		    /* var errorHtml = $("#errorInfos").html();
			  	errorHtml += file.name;
			 	$("#successInfos").html(errorHtml); */
      		},
      		'onSelect':function(file){//选择文件的时候调用的方法
      		   filesUpload.push(file.name);
      		},
      		'onCancel':function(file){//从上传队列中去掉一个文件的时候调用
      		   filesUpload.remove(file.name);
      		}
		});
		//$('#propInfo').appendTo('#uploadify');
	    //$('#uploadify-button').css('float','left');
		//debugger;
		/* $('#closeButton').click(function(self){
			$(this).parents('div.dialog').hide();
		}); */
	});
	function startUpLoad(){
//		$('#uploadify').uploadifyUpload();
		$("#uploadify03").uploadify('upload','*');
	}
	beforeInitUpload();
</script>
<div class="pageContent">
	<div class="pageFormContent">
		<div id = "propInfo"><span  style="font-size:14px;color:red">提示:</span><span  style="font-size:14px;color:blue">请尽量压缩文件,单文件不超过20M,每次最多上传5个文件</span></div>
		<div class="successUploaded">
			<input type="file" name="upload" id="uploadify03" />
		</div>
        <div id="fileQueue" class="fileQueue"></div>
        <div id="successInfos" class="successInfos">
        </div>
		<p  style="width:100%">
			<a class="successUploaded" href="#" onclick="javascript:startUpLoad();"><img
				src="uploadify/threetwo/upload.jpg"></a>
			<a class="successUploaded" href="javascript:$('#uploadify03').uploadify('cancel','*')"><img
				src="uploadify/threetwo/cancel.jpg"></a>
		    <a  class="closeButton" style="float:right" href="#">
		    <img style="height:20px;padding-top:8px" src="uploadify/threetwo/closeButton.jpg"></a>
		</p>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button id="hiddenCloseButton" type="button" class="close">关闭</button>
					</div>
				</div></li>
		</ul>
	</div>
</div>