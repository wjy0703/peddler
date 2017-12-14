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
function reOrgName(orgid,orgname){
	$("#filter_orgid").val(orgid);
	$("#filter_orgname").val(orgname);
}
</script>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>绩效提成统计</span></a></li>
					<li><a href="javascript:;"><span>信借业绩</span></a></li>
					
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
	
				<div layoutH="46" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
				    	<c:forEach items="${mendianTree}" var="son" varStatus="st">
						<li><a href="javascript" onclick="reOrgName('${son.ID }','${son.RGANI_NAME }')">${son.RGANI_NAME }</a>
							<ul >
								<c:forEach items="${son.VALIST}" var="vason" varStatus="vast">
								<li><a href="#" >${vason.RGANI_NAME }</a></li>
								</c:forEach>
							</ul>
						</li>
						</c:forEach>
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form id="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');" action="${ctx }/report/wfgztjHj" method="post">
	<div class="searchBar">
		<table>
			<tr>
				<td >
					门店:
					<input type="hidden" name="filter_orgid" id="filter_orgid" value="${map.orgid }"/>
					<input name="filter_orgname" id="filter_orgname" type="text"
					size="30" value="${map.orgname }" class="required" readonly="readonly" />
				</td>
				<td>
					日期:
					</td>
				<td>
					<input name="filter_date" type="text"
					size="30" value="${map.date }" class="date required"
					dateFmt="yyyy-MM" maxlength="20" readonly="readonly" />
					&nbsp;
					<a class="inputDateButton" href="javascript:;">选择</a>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="#" ><span>生成绩效提成核算表</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="138" rel="jbsxBox">
		<thead>
			<tr>
				<th width="28px">序号</th>
				<th width="80px">员工编号</th>
				<th width="80px">销售人员</th>
				<th width="60px">团队名称</th>
				<th width="60px">放款日期</th>
				<th width="100px">合同编号</th>
				<th width="60px">借款人姓名</th>
				<th width="60px">产品类型</th>
				<th width="50px">合同金额</th>
				<th width="50px">放款额</th>
				<th width="30px">期限</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user" varStatus="st">
				<tr target="sid_user">
					<td>${st.count }</td>
					<td>${user.EMP_NO }</td>
					<td>${user.NAME }</td>
					<td>${user.RGANI_NAME }</td>
					<td>${user.QDRQ }</td>
					<td>${user.JKHTBM }</td>
					<td>${user.JKRXM }</td>
					<td>${user.JKTYPE }</td>
					<td>${user.HTJE }</td>
					<td>${user.FKJE }</td>
					<td>${user.HKQS }</td>
				</tr>
			</c:forEach>
		</tbody>
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