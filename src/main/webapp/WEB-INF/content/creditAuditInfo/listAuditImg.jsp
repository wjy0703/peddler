<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/dwz/jquery-1.7.2.js" type="text/javascript"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a,img {
	border: 0;
}

/* shortcut */
.shortcut {
	position: fixed;
	top: 0;
	left: 0;
	z-index: 9999;
	width: 100%;
}

* html,* html body /* 修正IE6振动bug */ {
	background-image: url(about:blank);
	background-attachment: fixed;
}

* html .shortcut {
	position: absolute;
	top: expression(eval(document.documentElement.scrollTop) );
}

.shortcut {
	height: 28px;
	background: #EEEEEE;
	box-shadow: 1px 0px 2px rgba(0, 0, 0, 0.2);
	border-bottom: 1px solid #DDDDDD;
	padding: 0px 0 0 0;
}

.shortcut li {
	float: left;
	cursor: pointer;
	margin: 5px 0 0 15px;
	display: inline;
}
/* #picbbox */
.down_showpic {
	overflow: hidden;
	width: auto;
	text-align: center;
}

.pictio {
	position: absolute;
	z-index: 1;
	background-color: #666;
	cursor: move;
}
</style>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/message/listPublicMessage" method="post">
	</form>

<div class="shortcut">
	<ul>
		<li><span>共${page.totalCount}条</span></li>
		<c:if test="${page.totalCount > 0}">
		<li>
		<c:if test="${page.pageNo!='1'}">
		<a href="${ctx }/loan/listAuditImg?jksqId=${jksqId}&typeName=${typeName}&pageNum=${page.pageNo - 1}">上一张</a>
		</c:if>
		</li>
		<li>
		<c:if test="${page.pageNo!=page.totalCount}">
		<a href="${ctx }/loan/listAuditImg?jksqId=${jksqId}&typeName=${typeName}&pageNum=${page.pageNo + 1}">下一张</a>
		</c:if>
		</li>
		</c:if>
	</ul>	
</div>

<c:forEach items="${imgList}" var="img" varStatus="st">
				<img class="down_showpic" src="/CHPxhFile/auditImgInfo/${img.IMG_SRC }" alt="点击打开原图" style="cursor:pointer" onclick="onCli('/CHPxhFile/auditImgInfo/${img.IMG_SRC }')"/>
</c:forEach>

<script type="text/javascript">
$(document).ready(function(){
	boxw=$(window).width();
	boxh=$(window).height();
	//$(".pictio").width($("#picbbox").width()).height($("#picbbox").height());
	//if(w<boxw){
	//	alert(1);
	//}else{
		$(".down_showpic").css({"width":boxw,height:"auto"});
	//}
	//$(document).scrollTop($(".down_showpic").position().top);
});
function onCli(value){
	window.open(value,'newwindow','height=800,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no, z-look=yes');  
}
</script>