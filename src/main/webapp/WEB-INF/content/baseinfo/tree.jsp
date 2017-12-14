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
//manageTree(1);
try {
Ext.BLANK_IMAGE_URL = '${ctx }/ext/resources/images/default/s.gif';
var fNewvalue;
var manageWin = null;
var nodeId = null;
var sname = "";
var scode = "";
var sflag = "";
var sdes = "";
var slevelMess = "";

//function manageTree(value) {
	var selected; // 
	var manageRoot = new Ext.tree.AsyncTreeNode({
		text : '组织机构',
		iconCls : 'loading',
		draggable : false,
		icon : '${ctx }/ext/img/group.gif',
		id : '0'
	});
	var manageTree = new Ext.tree.TreePanel({
		el : 'manageTree',
		id : 'myManagetree',
		useArrows : false,
		root:manageRoot,
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
            click:onTreeClick.createDelegate(this)
        }
		//,
//		tools : [{
//			id : 'refresh',//
//			handler : f1
//		}]
	});
	function treeHtml(name, code, flag, des,levelMess){
		sname = name;
		scode = code;
		sflag = flag;
		sdes = des;
		slevelMess = levelMess;
		var html = "";
		html += "<table class=\"table\" targetType=\"dialog\" width=\"100%\" layoutH=\"138\">";
		html += "<thead>";
		html += "<tr>";
		html += "<th width=\"20%\" height=\"40\" bgcolor=\"#f3f8fe\" style=\"font-size: 12px\">机构名称</th>";
		html += "<th width=\"20%\" height=\"40\" bgcolor=\"#f3f8fe\" style=\"font-size: 12px\">机构编码</th>";
		html += "<th width=\"20%\" height=\"40\" bgcolor=\"#f3f8fe\" style=\"font-size: 12px\">机构描述</th>";
		html += "<th width=\"20%\" height=\"40\" bgcolor=\"#f3f8fe\" style=\"font-size: 12px\">是否在用</th>";
		html += "<th width=\"20%\" height=\"40\" bgcolor=\"#f3f8fe\" style=\"font-size: 12px\">级别描述</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody>";
		html += "<tr>";
		html += "<td height=\"30\">"+name+"</td>";
		html += "<td height=\"30\">"+code+"</td>";
		html += "<td height=\"30\">"+des+"</td>";
		html += "<td height=\"30\">"+flag+"</td>";
		html += "<td height=\"30\">"+levelMess+"</td>";
		html += "</tr>";
        html += "</tbody>";
		return html;
	}
	
	function onTreeClick(node){
		Ext.Ajax.request( {
			url :'${ctx }/baseinfo/getDep',
			method :'POST',
			params : {
    		    id :node.id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText);
				if(data.success){
					var html = treeHtml(data.text, data.code, data.flag, data.des, data.levelMess);
					$("#cityInfo").html(html);
					node.expand();
			        node.select();
			        nodeId = node.id;
				}else{
					alert("机构信息获取失败！");
				}
			}
		})
	}

	function f1(value) {
		var manageTree = Ext.getCmp('myManagetree');
		manageTree.root.reload();
		manageTree.body.mask('请稍后...', 'x-mask-loading');
		manageTree.root.expand(true, false, function() {
			manageTree.body.unmask();
		});
	}

	//manageTree.setRootNode(manageRoot);
	manageTree.on('beforeload', function(node) {
		manageTree.loader.dataUrl = '${ctx }/baseinfo/depList?id=' + node.id;
	});

	manageTree.render();


//定义右键菜单
var rightClick = new Ext.menu.Menu({
	id : 'rightClickCont',
	Target : 'contents',
	items : [{
		id : 'GroupManager',
		text : '机构建立',
		icon : '${ctx }/ext/img/add.gif',
		handler : fnew
	}, {
		id : 'GroupUpdate',
		text : '机构调整',
		icon : '${ctx }/ext/img/update.gif',
		handler : fupd
	}, {
		id : 'delNode',
		text : '机构删除',
		icon : '${ctx }/ext/img/delete.gif',
		handler : fdel
	}]

});
function f2(value) {
	if (manageWin == null) {
		var tabs = new Ext.FormPanel({
			region : 'center',
			labelWidth : 75,
			frame : true,
			bodyStyle : 'padding:5px 5px 0',
			width : 350,
			defaults : {
				width : 230
			},
			defaultType : 'textfield',
			items : [{
				fieldLabel : '机构名称',
				name : 'name',
				allowBlank : false
			},{
				fieldLabel : '机构编码',
				name : 'code',
				allowBlank : false
			},{
                xtype:'panel',
                width:350,
                id:'cityFlag',
                border:false,
                bodyStyle:'padding-left:10px;padding-bottom:5px;',
                layout:'table',
                items:[{
                    html:'是否在用:',
                    border:false,
                    bodyStyle:'padding-right:5px;'
                },{
                    xtype:'radio',
                    name:'cityFlag',
                    boxLabel:'是',
                    inputValue:'0',
                    checked:true
                },{
                    xtype:'radio',
                    name:'cityFlag',
                    boxLabel:'否',
                    inputValue:'1'
                }]
            },{
				fieldLabel : '描述',
				name : 'des',
				allowBlank : false
			},{
				fieldLabel : '级别描述',
				name : 'levelMess',
				allowBlank : true
			},{
				xtype : 'hidden',
				name : 'parentId',
				value : value
			}],

			buttons : [{
				text : '保存',
				handler : function() {
				if(tabs.getForm().isValid()){
					this.disabled=true;
                	 var name = tabs.getForm().findField('name').getValue();
                	 var parentId = tabs.getForm().findField('parentId').getValue();
                	 var code = tabs.getForm().findField('code').getValue();
                	 var des = tabs.getForm().findField('des').getValue();
                	 var levelMess = tabs.getForm().findField('levelMess').getValue();
                	 var flag = Ext.getCmp("cityFlag");
                	 var newFlag = "1";
               		 for(var i = 0 ; i < 2 ; i++){
               			 if(flag.items.items[i].checked){
               				 newFlag = flag.items.items[i].inputValue;
               			 }
               		 }
                	 if(parentId.indexOf("user_") == 0){
                	 	alert("无法对人员添加机构名称！");
                	 	manageWin.hide();
	                    manageWin = null;
                	 	return;
					}
                	 Ext.Ajax.request( {
						url :'${ctx }/baseinfo/addDep',
						method :'POST',
						params : {
                		    parentId :parentId,
							name :name,
							code:code,
							cityFlag:newFlag,
							organiDes:des,
							levelMess:levelMess
						},
						success : function(response) {
							manageTree.root.reload();
	                        manageWin.hide();
	                        manageWin = null;
						}
					})
                }
				}
			}, {
				text : '重置',
				handler : function() {
					tabs.getForm().reset();
				}
			}, {
				text : '关闭',
				handler : function() {
					manageWin.hide();
					manageWin = null;
				}
			}]
		});

		manageWin = new Ext.Window({
			title : '机构建立',
			closable : false,
			width : 350,
			height : 300,
			border : false,
			closeAction : 'hide',
			plain : true,
			layout : 'border',
			
			//Target : 'contents',
			items : [tabs]
		});
	}
	manageWin.show();
}



function f3(value) {
	// alert('您点击了删除节点！'+selected.id);//获取tree对象的选中模型下的选中节点的id
	
    Ext.MessageBox.confirm('确认', '您确定要执行此操作吗？与该机构相关相关会清除!', showResult);
    function showResult(btn){
    if(btn == "yes"){
		Ext.Ajax.request( {
			url :'${ctx }/baseinfo/delDep',
			method :'POST',
			params : {
	            id :value
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText);
				if(data.success){
					alert("删除成功!");
					manageTree.root.reload();
			        manageWin.hide();
			        manageWin = null;
				}else{
					alert(data.msg);
				}
			}
		})
    }
	};
}

function f4(value, name, code, flag, des,levelMess) {
	if (manageWin == null) {
		if (manageWin == null) {
			var tabs = new Ext.FormPanel({
				region : 'center',
				labelWidth : 75,
				frame : true,
				bodyStyle : 'padding:5px 5px 0',
				width : 350,
				defaults : {
					width : 230
				},
				defaultType : 'textfield',
				items : [{
					fieldLabel : '机构名称',
					name : 'name',
					allowBlank : false,
					value : name
				},{
					fieldLabel : '机构编码',
					name : 'code',
					allowBlank : false,
					value : code
				},{
	                xtype:'panel',
	                width:350,
	                id:'cityFlag',
	                border:false,
	                bodyStyle:'padding-left:10px;padding-bottom:5px;',
	                layout:'table',
	                items:[{
	                    html:'是否在用:',
	                    border:false,
	                    bodyStyle:'padding-right:5px;'
	                },{
	                    xtype:'radio',
	                    name:'cityFlag',
	                    boxLabel:'是',
	                    inputValue:'0',
	                    checked:flag == '是'?true:false
	                },{
	                    xtype:'radio',
	                    name:'cityFlag',
	                    boxLabel:'否',
	                    inputValue:'1',
	                    checked:flag == '否'?true:false
	                }]
	            },{
					fieldLabel : '描述',
					name : 'des',
					allowBlank : false,
					value : des
				},{
					fieldLabel : '级别描述',
					name : 'levelMess',
					allowBlank : true,
					value : levelMess
				},{
					xtype : 'hidden',
					name : 'parentId',
					value : value
				}],

				buttons : [{
					text : '保存',
					handler : function() {
					if(tabs.getForm().isValid()){
						this.disabled=true;
	                	 var name = tabs.getForm().findField('name').getValue();
	                	 var parentId = tabs.getForm().findField('parentId').getValue();
	                	 var code = tabs.getForm().findField('code').getValue();
	                	 var des = tabs.getForm().findField('des').getValue();
	                	 var levelMess = tabs.getForm().findField('levelMess').getValue();
	                	 var flag = Ext.getCmp("cityFlag");
	                	 var newFlag = "1";
	               		 for(var i = 0 ; i < 2 ; i++){
	               			 if(flag.items.items[i].checked){
	               				 newFlag = flag.items.items[i].inputValue;
	               			 }
	               		 }
	                	  if(parentId.indexOf("user_") == 0){
		                	 	alert("无法对人员更新机构名称！");
		                	 	manageWin.hide();
			                    manageWin = null;
		                	 	return;
							}
	                	 Ext.Ajax.request( {
							url :'${ctx }/baseinfo/updateDep',
							method :'POST',
							params : {
	                		    parentId :parentId,
								name :name,
								code:code,
								cityFlag:newFlag,
								organiDes:des,
								levelMess:levelMess
							},
							success : function(response) {
								manageTree.root.reload();
		                        manageWin.hide();
		                        manageWin = null;
							}
						})
	                }
					}
				}, {
					text : '重置',
					handler : function() {
						tabs.getForm().reset();
					}
				}, {
					text : '关闭',
					handler : function() {
						manageWin.hide();
						manageWin = null;
					}
				}]
			});

			manageWin = new Ext.Window({
				title : '机构调整',
				closable : false,
				width : 350,
				height : 300,
				border : false,
				closeAction : 'hide',
				plain : true,
				layout : 'border',
				
				//Target : 'contents',
				items : [tabs]
			});
		}
		manageWin.show();
}
	manageWin.show();
	
}


// 右键 单击事件
//manageTree.on('contextmenu', function(node, event) {// 声明菜单类型
//			node.select();
//			event.preventDefault();// 防止弹出默认菜单
//			rightClick.showAt(event.getXY());// 取得鼠标点击坐标，展示菜单
//			selected = new Ext.tree.TreeNode({
//				id : node.id,
//				text : node.text
//			});// 保存选中的树结点
//		});
		
		
	function fnew(){
		var res = isNodeId(nodeId);
		if(!res){
			alert("请选择！");
			return;
		}
		f2(nodeId);
	}
	
	function fupd(){
		var res = isNodeId(nodeId);
		if(!res){
			alert("请选择！");
			return;
		}
		f4(nodeId, sname, scode, sflag, sdes,slevelMess);
	}
	
	function fdel(){
		var res = isNodeId(nodeId);
		if(!res){
			alert("请选择！");
			return;
		}
		f3(nodeId);
	}
	
	function isNodeId(nodeId){
		var res = true;
		if(nodeId == null){
			res = false;
		}
		return res;
	}
	
//}

//Ext.onReady(manageTree);

} catch (e) {
location.location="tree.jsp";
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
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="250" valign="top">
				<table width="251" height="100%" border="0" cellpadding="0"
					cellspacing="0" style="width: 251px;">
					<tr>
						<td width="250" valign="top"><div id="manageTree"
								style="height: 500px;"></div></td>
						<td width="1" bgcolor="#6a96c7"></td>
					</tr>
				</table>
			</td>
			<td valign="top" valign="middle">
				<div class="panelBar">
					<ul class="toolBar">
						<li><a class="add" href="#" onclick="fnew()"><span>添加</span></a></li>
						<li><a class="edit" href="#" onclick="fupd()"><span>修改</span></a></li>
						<li><a class="delete" href="#" onclick="fdel()"><span>删除</span></a></li>
					</ul>
				</div>
				<div id="cityInfo" style="height: 500px;"></div>
			</td>
		</tr>
	</table>
</body>
</html>