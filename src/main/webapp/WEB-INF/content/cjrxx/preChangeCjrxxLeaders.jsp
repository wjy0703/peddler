<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="uploadify/threetwo/uploadify.css">
	<script type="text/javascript">
		$(function() {
			/* function changeClick(name,oldCrmName,crmName,ccaName){
				console.log(name,oldCrmName,crmName,ccaName);
				if(confirm("修改"+name+ "（原客户经理:"+oldCrmName+"） 新客户经理： "+ crmName + " 团队经理: " + ccaName)){
					$.ajax({
					  url: "cjrxx/changeCjrxxLeaders",
					  cache: false,
					  data : {name:escape(name),oldCrmName:escape(oldCrmName),crmName:escape(crmName),ccaName:escape(ccaName)}
					})
				}
			}; */
			
			$('#file_upload').uploadify({
				'swf'      : 'uploadify/threetwo/uploadify.swf',
				'uploader' : 'changeCjrxx/generateChangeRecords',
				buttonText : '上传文件',
				onUploadSuccess:function(file,data,response){
				    var $table = $("#changeTable");
					var jsonData = $.parseJSON(data);
					if(jsonData!=null){
						for(var index = 0 ; index < jsonData.length ; index++){
							var $record = $(recordToTr(jsonData[index]));
				       /* 	var $button = $record.find("button");
							$button.on("click",changeClick(jsonData[index].name,jsonData[index].oldCrmName,jsonData[index].crmName,jsonData[index].ccaName)); */
		                    $record.appendTo($table);
	                    }
                    }
				}
			});
			function changeMe(num){
				alert(num);
			}
/* 			int rowNum;
	String name;
	String userNumAndLendNum;
	String lendDate;
	double lendAmount;
	String lendType;
	String oldCrmName;
	String crmName;
	String ccaName;
	String changeDate; */
			function recordToTr(record){
				var  tr = "<tr>";
				tr += "<td width= '100'>" + record.rowNum + "</td>";
				tr += "<td width= '100'>" + record.name + "</td>";
				tr += "<td width= '100'>" + record.oldCrmName + "</td>";
				tr += "<td width= '100'>" + record.crmName + "</td>";
				tr += "<td width= '100'>" + record.ccaName + "</td>";
				//var data = "{name:"+record.name+",oldCrmName:"+record.oldCrmName+",crmName:" + record.crmName + ",ccaName:" + record.ccaName+ "}";
				tr += "<td width= '100'></td>";
				//tr += "<td width= '100'><button type='button'>修改</button></td>";
				tr = tr + "</tr>";
				return tr;
			}
		});
	</script>

 


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
		</ul>
	</div>
 	<form>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
	</form>
	<table id = "changeTable" class="table" layoutH="168" nowrapTD="true">
		<thead>
			<tr>
				<th width="100" >序号</th>
				<th width="100" >客户姓名</th>
				<th width="100" >原客户经理</th>
				<th width="100" >新客户经理</th>
				<th width="100">团队经理</th>
				<th width="100">操作</th>
			</tr>			
		</thead>
		<tbody id = "changeTable">
			
		</tbody>
	</table>
	
</div>
	  		