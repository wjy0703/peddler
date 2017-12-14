<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title></title>
 <base href="<%=basePath%>">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<script type="text/javascript" src="<%=path %>/resources/img.js"></script>
</head>
<body bgcolor="#90C9FD" style=" margin:0px; padding:0px;">

<table width=100% height=100%>
       <tr height=90%>
         <td width=100% onmousewheel= "return   bbimg('oImg') ">
         <div style="overflow:hidden;width:100%;height:100%;border:1;background-color:white">
         <a href="" target="_blank">
         <img id="oImg"  title="${order }"  src="<%=basePath%>${imgAddress }" style="position:relative; zoom:100%;" border=0 class = "dragme" >
        </a>
         </div>
         <td>
       </tr>
       <tr height=10%>
         <td width=100%>
         		<button  value="上一张"  onclick="p(-1)">上一张</button>
            	<button  value="下一张" onclick="p(+1)">下一张</button>
        		<button  value="放大" onmousedown="imgToSize(true)" onmouseup="window.clearTimeout(oTime)" >放大</button>
                <button  value="缩小" onmousedown="imgToSize(false)" onmouseup="window.clearTimeout(oTime)" >缩小</button>
                <button  value="还原" onmousedown="imgToBack(false)" onmouseup="window.clearTimeout(oTime)" >还原</button>
         <td>
       </tr>
    </table>
<script>
var photo=new Array(${filesCon});
<c:forEach items="${files}" var="user" varStatus="st">
photo[${st.index}]="${user.filepath}/${user.newfilename}";
</c:forEach>

function p(n){

	var filesCon=${filesCon};
	var img = document.getElementById('oImg');
	//alert(img.title);
	var n = Number(img.title) + n;
	if(n < 0) n = filesCon;
	if(n > filesCon) n = 1;
	document.getElementById("oImg").src=photo[n]; 
	img.title = n;
}
setInterval(function(){p(+1)},2998); 
</script>
</body>
</html>