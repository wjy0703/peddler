<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/ext/department/department.js"></script>

<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<div class="pageContent" style="padding:5px">
<input type="hidden" id="organiId" name="organiId"/>
	<div>

		<div layoutH="12" 	style="float:left; display:block; overflow:auto; width:260px; border:solid 1px #CCC; line-height:21px; background:#fff">
			 <ul class="tree treeFolder">
	      		<li><a href="#" onclick="setSelectedOrg({id:'0', name:'组织机构',organiFlag:'0',levelMess:'组织机构',organiCode:'组织机构'})">组织机构</a>
	       			<ul>
						${result}
		  			</ul>
		  		</li>
		  </ul>
		</div>

		<div id="cfmdBox" class="unitBox" style="margin-left:246px;">
			
				<div class="panelBar">
					<ul class="toolBar">
						<li><a id="addHrefClick" class="add" href="#" target="dialog" mask="true" width="400" title="添加机构"><span>添加</span></a></li>
						<li id="panelBarMdy"><a id="editHrefClick" class="edit" href="#" target="dialog" mask="true" width="400" title="调整机构"><span>调整</span></a></li>
						<li><a class="delete" href="#" onclick="deleteTree()"><span>删除</span></a></li>
					</ul>
				</div>
			

			<div class="pageContent"
				style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
				<table class="table" width="100%" layoutH="86" nowrapTD="false">
					<thead>
						<tr>
							<th width="20px">机构名称</th>
							<th width="40px">机构编码</th>
							<th width="40px">级别描述</th>
							<th width="40px">是否在用</th>
						</tr>
					</thead>
						<tr>
							<td><div id="name"></div></td>
							<td><div id="organiCode"></div></td>
							<td><div id="levelMess"></div></td>
							<td><div id="organiFlag"></div></td>
						</tr>
				</table>
				<div class="panelBar"></div>
			</div>
		</div>
	</div>
</div>
