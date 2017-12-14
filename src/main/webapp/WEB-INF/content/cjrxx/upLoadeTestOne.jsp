<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>SWFUpload Demos</title>
</head>
<body style="background-color: #C0D1E3; padding: 2px;">
	<iframe src="${ctx }/${nextUrl }/${Id}" width="100%" height="500px"></iframe>
</body>
</html>