<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<title></title>
    <base href="<%=basePath%>">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<script type="text/javascript" src="<%=path %>/resources/img.js"></script>
</head>

<body bgcolor="#90C9FD" style=" margin:0px; padding:0px;">
    <table width=100% height=100%>
       <tr height=95%>
         <td width=100% onmousewheel= "return   bbimg('oImg') ">
         <div style="overflow:hidden;width:100%;height:100%;border:1;background-color:white">
         <a href="" target="_blank">
         <img id="oImg"  src="<%=basePath%>${imgAddress }" style="position:relative; zoom:100%;" border=0 class = "dragme" >
        </a>
         </div>
         <td>
       </tr>
       <tr height=5%>
         <td width=100%>
         <button  value="放大" onmousedown="imgToSize(true)" onmouseup="window.clearTimeout(oTime)" >放大</button>
                <button  value="缩小" onmousedown="imgToSize(false)" onmouseup="window.clearTimeout(oTime)" >缩小</button>
                <button  value="还原" onmousedown="imgToBack(false)" onmouseup="window.clearTimeout(oTime)" >还原</button>
         <td>
       </tr>
    </table>

  </body>
</html>
