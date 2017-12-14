<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>




<link href="uploadify/css/uploadify.css" rel="stylesheet"
	type="text/css" />
<style type="text/css" media="screen">
.my-uploadify-button {
	background: none;
	border: none;
	text-shadow: none;
	border-radius: 0;
}

.uploadify:hover .my-uploadify-button {
	background: none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>




<script type="text/javascript" src="uploadify/scripts/swfobject.js"></script>

<script type="text/javascript"
	src="uploadify/scripts/jquery.uploadify.v2.1.0.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#uploadify01").uploadify({
			'uploader' : 'uploadify/scripts/uploadify.swf',
			'buttonImg' : 'uploadify/add_1.jpg',
			buttonClass : 'my-uploadify-button',

			'script' : "cjrxx/upLoadFile?id="+${Id },
			'cancelImg' : 'uploadify/cancel.png',
			'folder' : 'UploadFile',
			'queueID' : 'fileQueue',
			'auto' : false,
			'multi' : true,
			'successTimeout' : 99999,
			'fileExt' : '*.jpg;*.png;*.pdf;*.zip;*.rar',
			'fileDesc' : '*.jpg;*.png;*.pdf;*.zip;*.rar',
			'sizeLimit' : 1024 * 1024 * 20,
			'simUploadLimit' : 5

		});
	});
</script>

<div class="pageContent">
	<div class="pageFormContent">
		<div>
			<input type="file" name="upload" id="uploadify01" />
		</div>
		<div class="divider"></div>
		<span class="info">提示：上传前请尽量压缩文件,单个上传文件不超过6M,同时上传文件数尽量不超过5个</span>
		<div id="fileQueue" style="" class="fileQueue"></div>
		<div class="divider"></div>
		<p>
			<a href="javascript:$('#uploadify01').uploadifyUpload()"><img
				src="uploadify/upload_1.jpg"></a> <a
				href="javascript:$('#uploadify01').uploadifyClearQueue()"><img
				src="uploadify/cancel_1.jpg"></a>
		</p>
	</div>
	<div class="formBar">
		<ul>

			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div></li>
		</ul>
	</div>
</div>
