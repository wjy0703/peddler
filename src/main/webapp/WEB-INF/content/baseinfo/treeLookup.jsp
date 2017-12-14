<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
	      <ul class="tree treeFolder">
	      		<li><a href="#">组织机构</a>
	       			<ul>
						${result}
		  			</ul>
		  		</li>
		  </ul>
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>