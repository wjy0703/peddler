<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<script type="text/javascript">
function rePerName(orgid,orgname){
	$("#filter_auditPerson",navTab.getCurrentPanel()).val(orgid);
	$("#filter_auditPersonName",navTab.getCurrentPanel()).val(orgname);
}

function checkMonth(obj){
	var val = obj.value;
	if(val == 0){
		$("#mon",navTab.getCurrentPanel()).hide();
	}else{
		$("#mon",navTab.getCurrentPanel()).show();
	}
}
</script>
<div class="pageContent" style="padding:0px">
	<div class="tabs">
		<div class="tabsContent">
			<div>
	
				<div layoutH="12" style="float:left; display:block; overflow:auto; width:160px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
				    <li><a href="#" >初审人员</a>
					<ul >
						<c:forEach items="${allAuditPersons}" var="person" varStatus="st">
						<li><a href="javascript" onclick="rePerName('${person.id }','${person.name }')">${person.name }</a></li>
						</c:forEach>
					</ul>
					</li>
					</ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:166px;">
					<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form id="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');" action="${ctx}/loan/listByAuditPersonNext" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td >
					信审员:
					<input type="hidden" name="filter_auditPerson" id="filter_auditPerson" value="${filter_auditPerson }"/>
					<input name="filter_auditPersonName" id="filter_auditPersonName" type="text"
					size="10" value="${filter_auditPersonName }" readonly="readonly" />
				</td>
				<td>
					
			    	 <select name="filter_successState" onchange="checkMonth(this)" class="combox">
						<option value="0" <c:if test="${filter_successState == '0'}">selected</c:if> >待处理</option>
						<option value="1" <c:if test="${filter_successState == '1'}">selected</c:if> >已处理</option>
					</select>
				</td>
				<td >
				<div id="mon" style="display: none">
					月份:
					<input name="filter_createTime" id="filter_createTime" class="date" dateFmt="yyyy-MM"
					size="10" value="${filter_createTime }" readonly="readonly" />
				</div>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<table class="table" width="99%" layoutH="138" rel="jbsxBox">
		<thead>
			<tr>
				<th width="60">客户编号</th>
				<th width="80">客户姓名</th>
				<th width="80">共同还款人</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="100">产品</th>
				<th width="80">申请金额(元)</th>
				<th width="80">期数(月)</th>
				<th width="80">团队经理</th>
				<th width="80">客户经理</th>
				<th width="140">进件时间</th>
				<th width="140">分派时间</th>
				<th width="80">状态</th>
			</tr>
		</thead>
	</table>
	<div class="panelBar">
	</div>
</div>
				</div>
	
			</div>
		
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>