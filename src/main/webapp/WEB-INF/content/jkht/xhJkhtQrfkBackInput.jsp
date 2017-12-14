<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<div class="pageContent">
		<form method="post" id="jkhtform" name="jkhtform" action="${ctx}/xhJkht/saveQrfkPlBack" class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="panel">
	<h1>待确认放款退回</h1> 
	
			<input type="hidden" name="id" value="${xhJksq.id}" />
			<div class="pageFormContent">
				<table width="100%">
					<tr>
						<td><label>退回原因：</label> <!-- <input id="remark" name="remark"
							type="text" size="30" value="" class="required"
							maxlength="22" readonly/> -->
							<textarea rows="10" cols="40" id="remark" name="remark"></textarea>
							</td>
					</tr>
				</table>
			</div>
			</div>
				<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button id="realSubmit" type="submit" >提交</button>
							</div>
						</div>
					</li>
					<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
					</ul>
			</div>

		</form>
	</div>

