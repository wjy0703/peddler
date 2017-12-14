<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/ext/mendian.js"></script>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<script type="text/javascript">
	function closedialoghj(param){
		var uploadedFlag = top.document.getElementById("uploadedFlag");
		if ("1" == uploadedFlag.value) {
			navTabAjaxDone(param);
		}
		return true;
	}
</script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" /> 
	<input type="hidden" name="orderField" value="${page.orderBy}" /> 
	<input type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form id="jksqListForm" name="jksqListForm" rel="pagerForm" 
		onsubmit="return navTabSearch(this);" action="${ctx }/xhNewJksq/listJksq" method="post">
		<input type="hidden" id="type" name="type" value="${type }"/>
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td><label>客户姓名:</label> 
						<input type="text" name="filter_jkrxm" value="${filter_jkrxm }"/>
					</td>
					<td><label>客户电话:</label> 
						<input type="text" name="filter_telephone" value="${filter_telephone }"/>
					</td>
					<td><label>证件号码:</label> 
						<input type="text" name="filter_zjhm" value="${filter_zjhm }"/>
					</td>
					<td><label>产品:</label> 
					    <sen:select name="filter_jkType" coding="productType" clazz="combox" title="全部" titleValue="all"/>
					</td>
				</tr>
				<tr>
					<td><label>借款状态:</label> 
						<select class="combox" name="filter_state_injksp">
							<OPTION value="" <c:if test="${filter_state_injksp==''}">selected</c:if>>所有状态</OPTION>
							<c:forEach items="${attrList}" var="attr" varStatus="st">
								<option value="${attr.value }" 
									<c:if test="${filter_state_injksp==attr.value}">selected="selected" </c:if>>${attr.keyName }
								</option>
							</c:forEach>
						</select>
					</td>
					<td><label>所属城市:</label> 
					    <sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${filter_province},${filter_city}"/>
					</td>
					<td><label>团队经理:</label> 
						<input type="text" name="filter_teamName" value="${filter_teamName }"/>
					</td>
					<td><label>客户经理:</label> 
						<input type="text" name="filter_saleName" value="${filter_saleName }"/>
					</td>
				</tr>
				<tr>
					<td>
					<label>进件时间:</label>
					<input type="text" name="filter_startDate" class="date" style="width: 65px" readonly="true" value="${map.startDate }"/>
					至
					<input type="text" name="filter_endDate" class="date" style="width: 65px" readonly="true" value="${map.endDate }"/>
					</td>
					<td >
					<label>门店:</label>
					<input id="organiId" name="filter_organi.id" type="hidden" value="${organiId}"/>
					<input name="filter_organi.name" type="text" value="${organiName }" onblur="cleanMd(this.value)"/>
					<a href="${ctx }/loan/getMdList" lookupGroup="filter_organi" style=""><span style="color: #7F7F7F;">选择门店</span></a>
					</td>
					<td>
					</td>
					<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">检索</button>
									</div>
								</div></li>
							<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
						</ul>
					</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
<!-- 	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jksq/addJksq" target="navTab"><span>新增借款申请</span></a></li>
		</ul>
	</div> -->
	<table class="table" width="100%" layoutH="138" >
		<thead>
			<tr>
				<th width="60" >借款编号</th>
				<th width="60">共借人</th>
				<th width="60">客户姓名</th>
				<th width="60">省份</th>
				<th width="60">城市</th>
				<th width="80">状态</th>
				<th width="80">门店</th>
				<th width="60">产品</th>
				<th width="60">是否加急</th>
			
				<th width="80">申请金额(元)</th>
				<th width="80">放款金额(元)</th>
				<th width="50">期数(月)</th>
				<th width="60">团队经理</th>
				<th width="60">客户经理</th>

				<th width="140" >进件时间</th>
				<th width="40"  align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listJksq }" var="jksq" varStatus="st">
				<tr  target="sid_jksq" rel="${jksq.ID}">
					<td>${jksq.LOAN_CODE }</td>
					<td><sen:showTogether lendId="${jksq.ID}" yesOrNo="${jksq.ISTOGETHER}"/></td>
					<td>${jksq.JKRXM }</td>
					<td>${jksq.PRONAME}</td>
					<td>${jksq.CITYNAME}</td>
					
					<c:choose>
						<c:when test="${fn:contains(jksq.STATEINFO , '外')}">
							<td style="color:orange;font-weight:bold;font-size:12">${jksq.STATEINFO  }</td>
						</c:when>
						<c:when test="${fn:contains(jksq.STATEINFO , '待')}">
							<td style="color:blue;font-weight:bold;font-size:12">${jksq.STATEINFO  }</td>
						</c:when>
			
						<c:when test="${fn:contains(jksq.STATEINFO , '暂')}">
							<td style="color:orange;font-weight:bold">${jksq.STATEINFO  }</td>
						</c:when>
						<c:when test="${fn:contains(jksq.STATEINFO , '拒')}">
							<td style="color:red;font-weight:bold">${jksq.STATEINFO  }</td>
						</c:when>
						<c:when test="${fn:contains(jksq.STATEINFO , '已放款')}">
							<td style="color:green;font-weight:bold">${jksq.STATEINFO  }</td>
						</c:when>
						<c:when test="${state == '303' }">
						   <td style="color:green;font-weight:bold">等待评分</td>
						</c:when>
					  
						<c:otherwise><td style="color:red;font-weight:bold">${jksq.STATEINFO}</c:otherwise>
					</c:choose>
					<td>${jksq.YYB}</td>
					<td>${jksq.JK_TYPE_INFO}</td>
					<td>${jksq.ENGLISH_NAME }</td>
					<td>
						<c:if test='${null != jksq.JK_LOAN_QUOTA && jksq.JK_LOAN_QUOTA != 0 }'>￥<fmt:formatNumber
								value="${jksq.JK_LOAN_QUOTA }" pattern="#,#00.00" />
						</c:if>
					</td>
					<td>
						<c:if test='${null != jksq.FKJE && jksq.FKJE != 0 }'>￥<fmt:formatNumber
								value="${jksq.FKJE}" pattern="#,#00.00" />
						</c:if>
					</td>
					<td>${jksq.JK_CYCLE }</td>
					<td>${jksq.TEAMNAME }</td>
					<td>${jksq.SALENAME }</td>
		
					<td>${jksq.BACKUP02}</td>
					<td> <div class="buttonActive">
							<div class="buttonContent">
								<button onclick="return showOrHide(this);">操作</button>
							</div>
						</div>
							
					
						<div  class='OperationsPopUp' style='display: none; position: absolute;  
						
						text-align: justify; font-size: 12px;height:40px;width:300px;  filter:alpha(opacity=100);
    opacity:0.9;
    background:#fff;
    border:2px orange solid;
    padding:20px;
    padding-left:20px;
    word-wrap:break-word;
    line-height:100%;
'>

		<bjdv:validateContent type="1" funcId="借款申请-查看">
			
					<a id="freshJksp" href="${ctx }/xhNewJksq/lookXhJksq/${jksq.ID}?look=1" target="navTab" 
						title="查看借款申请信息" rel="rel_jksqShow">查看</a>
			
		</bjdv:validateContent>
		
		<bjdv:validateContent type="1" funcId="借款申请-修改">
		
		<c:if test = "${jksq.canEdit}">
			|<a href="${ctx }/xhNewJksq/editXhJksq/${jksq.ID}" target="navTab" 
						title="编辑借款申请信息" rel="rel_jksqEdit">编辑</a>
			|<a href="${ctx }/xhNewJksq/addJksqTogether/${jksq.ID}" target="navTab" 
						title="编辑共借人信息" rel="rel_jksqTogetherEdit">共借</a>
		</c:if>	
			
		</bjdv:validateContent>
		
		<bjdv:validateContent type="1" funcId="借款申请-历史状态">
		
					|<a href="${ctx }/jksq/listOptionHistory/${jksq.ID}" rel="rel_jksqhis_state"  mask="true" 
						title="历史状态" lookupGroup="authoritys">历史</a>
					<!-- 
					<a href="${ctx }/jksq/jksqHistoryList/${jksq.ID}" rel="rel_jksqhis_state" 
						target="navTab" title="历史状态"><button type="submit">历史</button></a>
					 -->
		
		</bjdv:validateContent>

		<bjdv:validateContent type="1" funcId="借款申请-上传">
		 <c:if test='${jksq.STATE == "31.A"}'>
		 |<a title="上传外访资料" href="${ctx }/jksq/newUpLoadAccredited/${jksq.ID}"
						target="dialog" mask="true" close="closedialoghj"  width="600" height="440"
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'>外访</a>
		 </c:if>
				
		<c:if test='${jksq.STATE == "02"}'>
		
				<!-- 
					<a title="上传授信资料" href="${ctx }/jksq/initUpLoad/${jksq.ID}"
						target="dialog" mask="true" close="closedialoghj" 
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'><button type="submit"  >上传</button></a>
				 -->
					|<a title="上传授信资料" href="${ctx }/jksq/newUpLoadAccredited/${jksq.ID}"
						target="dialog" mask="true" close="closedialoghj"  width="600" height="440"
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'>上传</a>
				
		</c:if>
		<c:if test='${jksq.STATE != "0" && jksq.STATE != "01" && jksq.STATE != "02"&& jksq.STATE != "31.A"&& jksq.STATE != "55"&&jksq.STATE != "81"&&jksq.STATE != "82"&&jksq.STATE != "99"}'>
			
					|<a title="补充上传授信资料" href="${ctx }/jksq/buchongUpLoadAccredited/${jksq.ID}"
						target="dialog" mask="true" close="closedialoghj" width="600" height="440"
						param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'>补传</a>
				
		</c:if>
		<c:if test='${jksq.STATE != "0" && jksq.STATE != "01" && jksq.STATE != "02"&& jksq.STATE != "55"}'>
		
				|<a href="${ctx}/jksq/seeUpLoadFile/${jksq.ID},sx:0:1:2:3:wf" class="" target="dialog"
					title="已传材料" mask="true" width="600" height="420" >已传</a>
			
		</c:if>
		</bjdv:validateContent>
		
		<bjdv:validateContent type="1" funcId="借款申请-协议查看">
		<c:if test='${jksq.STATE == "55" || jksq.STATE == "56" || jksq.STATE == "57" || jksq.STATE == "60"}'>
		
				|<a title="查看&打印合同" href="${ctx}/xhJkht/queryAgaee/${jksq.ID}" 
					target="navTab" mask="true" >查看&打印合同</a> 
		
		</c:if>
		</bjdv:validateContent>
		
		<bjdv:validateContent type="1" funcId="借款申请-上传合同">
		<c:if test='${jksq.STATE == "55"}'>
		
				<!-- 
				<a title="上传签约资料" href="${ctx }/jksq/initSignedUpUpLoad/${jksq.ID}" 
					target="dialog" mask="true" close="closedialoghj" rel="rel_signUpload" 
					param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'><button type="submit">签约</button></a>
				 -->
				|<a title="上传签约合同" href="${ctx }/jksq/newUpLoadSignedUp/${jksq.ID}" 
					target="dialog" mask="true" close="closedialoghj" rel="rel_signUpload" width="600" height="440"
					param='{"statusCode":"200", "message":"操作成功", "navTabId":"", "forwardUrl":"", "callbackType":""}'>上传签约合同</a>
		
		</c:if>
		</bjdv:validateContent>
		<c:set var="stateStr" value="${jksq.STATE}" scope="page"></c:set>
			    
		<bjdv:validateContent type="1" funcId="借款申请-客户放弃">
		<%
			        String stateStr = pageContext.getAttribute("stateStr") != null ? pageContext.getAttribute("stateStr").toString():"";	
			    	int dotPos = stateStr.indexOf(".");
			    	if(dotPos > 0){
			    		stateStr = stateStr.substring(0,dotPos);
			    	}
			    	int state = 100;
			    	try{
			    		state = Integer.parseInt(stateStr);
			    	}catch(Exception e){			    		
			    	}
			    	if(state < 61 || state == 70 || state == 71){
			     %>
	<%-- 	<c:if test='${jksq.STATE == "0" && jksq.STATE != "81"}'> --%>
		
					|<a title="客户放弃" 
						target="dialog" class = "customerAbandon" mask="true" width="600" height="290" close="closeDialogKhfq"
						href="${ctx }/jksq/userGiveUp/${jksq.ID}">客户放弃</a>
		
<%-- 		</c:if> --%>
		<% }%>
		</bjdv:validateContent>
		
		<bjdv:validateContent type="1" funcId="借款申请-变更">
			 <c:if test = "${jksq.canApplyChange }">
			  |<a href="${ctx }/xhNewJksq/addNewjksqChange/${jksq.ID}" rel="rel_jksqChangeAdd" 
	             target="navTab" title="变更申请">变更申请</a> 
			 </c:if>
		</bjdv:validateContent>
		
		<bjdv:validateContent type="1" funcId="借款申请-确定签署">
		<c:if test="${jksq.STATE == '70' }">
		
				|<a href="${ctx }/xhJkht/jksqQianShuInput/${jksq.ID}" rel="rel_jksqQianShu"
						target="navTab" title="借款申请-确定签署">签署</a>
			
		</c:if>
		</bjdv:validateContent>
		<bjdv:validateContent type="1" funcId="借款申请-评分">
			<c:if test='${jksq.STATE eq "303"}'>
			
			<%-- <c:if test="${jksq.borrowscoretype == 0 }"> --%>
						|<a id="freshJksp" href="${ctx }/xhBorrowscore/jksqGrade/${jksq.ID}?borrowscoreid=${jksq.BORROWSCOREID }&borrowscoretype=${jksq.BORROWSCORETYPE }" 
							target="dialog" close="closeDialogKhfq"
							title="借款评分信息" rel="rel_jksqShow" width="900" height="620">评分</a>
			<%-- </c:if> --%>
			</c:if>
	
		</bjdv:validateContent>
		
		<!-- </div> -->
		<div class="divider"></div>
		</div>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"   <c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20"   <c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50"   <c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200" <c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select> <span>条，共${page.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" 
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }" 
			pageNumShown="10" currentPage="${page.pageNo }">
		</div>

	</div>
</div>
<script>
function closeDialogKhfq(param){
	navTab.reloadFlag('rel_listJksq');
	var $dialog = $.pdialog.getCurrent();
	$dialog.hide();
	$("div.shadow").hide();
	if($dialog.data("mask")){
		$("#dialogBackground").hide();
	} else{
		if ($dialog.data("id")) $.taskBar.closeDialog($dialog.data("id"));
	}
}
</script>
