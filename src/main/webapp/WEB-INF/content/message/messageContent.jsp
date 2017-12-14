<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
			<div class="pageFormContent" layoutH="55">
					${content }
			</div>
			<div class="formBar">
				<ul>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" class="close" onclick="onClose()">关闭</button>
							</div>
						</div></li>
				</ul>
			</div>
	</div>
</div>
<script>
function onClose(){
	navTab.reloadFlag('rel_listPublicMessage');
}
</script>

