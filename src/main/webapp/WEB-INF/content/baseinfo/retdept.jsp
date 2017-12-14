<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${ctx }/ext/resources/css/ext-all.css"
	type="text/css"></link>
<script type="text/javascript" src="${ctx }/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx }/ext/ext-all.js"></script>
<script type="text/javascript" src="${ctx }/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/ext/department.js"></script>
<script type="text/javascript" charset="UTF-8">
//getDeptRet(1);
try {
Ext.BLANK_IMAGE_URL = '${ctx }/ext/resources/images/default/s.gif';
var fNewvalueRet;
var manageWinRet = null;
var nodeIdRet = null;
//function getDeptRet(value) {
	var selectedRet; // 
	var manageRootRet = new Ext.tree.AsyncTreeNode({
		text : '组织机构',
		iconCls : 'loading',
		draggable : false,
		icon : '${ctx }/ext/img/group.gif',
		id : '0'
	});
	var getDeptRet = new Ext.tree.TreePanel({
		el : 'getDeptRet',
		id : 'mygetDeptRet',
		useArrows : false,
		root:manageRootRet,
		bodyStyle:'background:#f3f8fe',
		collapsible : false,
		lines : true,//
		border : false,//
		width : 250,
		animate : true,
		autoScroll : true,
		enableDD : true,//
		containerScroll : true,
		dropConfig : {
			appendOnly : true
		},
		listeners:{
            click:onTreeClickRet.createDelegate(this)
        }
		//,
//		tools : [{
//			id : 'refresh',//
//			handler : f1
//		}]
	});
	function treeHtml(id,name){
		var html = "";
		html += "<a ";
		html += "href=\"javascript:$.bringBack({";
		html += "id:'"+id+"', name:'"+name+"'";
		html += "})\"";
		html += "title=\"查找带回\">选择^"+name+"^带回</a>";
		return html;
	}
	
	function onTreeClickRet(node){
		Ext.Ajax.request( {
			url :'${ctx }/baseinfo/getDep',
			method :'POST',
			params : {
    		    id :node.id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText);
				if(data.success){
					//var html = treeHtml(node.id,data.text);
					//$("#cityInfo").html(html);
					if(node.id != 0){
						javascript:$.bringBack({id:node.id, name:data.text});
					}
					node.expand();
			        node.select();
			        nodeIdRet = node.id;
				}else{
					alert("机构信息获取失败！");
				}
			}
		});
	}

	//getDeptRet.setRootNode(manageRoot);
	getDeptRet.on('beforeload', function(node) {
		getDeptRet.loader.dataUrl = '${ctx }/baseinfo/depList?id=' + node.id;
	});

	getDeptRet.render();


	
	
	function isnodeIdRet(nodeIdRet){
		var res = true;
		if(nodeIdRet == null){
			res = false;
		}
		return res;
	}
	
//}

//Ext.onReady(getDeptRet);

} catch (e) {
location.location="retdept.jsp";
}
</script>
<style type="text/css">
<!--
body {
	background-image: url();
}
-->
</style>
</head>
<body>
<center>
	<table width="250" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="250" valign="top" align="center">
				<table width="250" height="100%" border="0" cellpadding="0"
					cellspacing="0" style="width: 250px;">
					<tr>
						<td width="250" valign="top" align="center">
						<div id="getDeptRet"
								style="height: 300px;"></div></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</center>
</body>
</html>