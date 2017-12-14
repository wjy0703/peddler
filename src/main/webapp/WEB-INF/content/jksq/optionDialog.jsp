<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post"
		class="pageForm" name="optionForm"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="buttonActive">
			<div class="buttonContent">
				<a title="上传授信资料" href="${ctx }/jksq/initUpLoad/${Id}"
					target="navTab" ><button
						type="submit"  >上传授信资料</button></a>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">查看</button>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">查看</button>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">修改</button>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">历史</button>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">补充信息</button>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">共借人</button>
			</div>
		</div>



		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">合同打印</button>
			</div>
		</div>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">上传签约合同</button>
			</div>
		</div>
	</form>
</div>

