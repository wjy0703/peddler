<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/overDate/saveOverDate"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>逾期还清日期：</label> <input type="text" name="csrq"
						class="date" pattern="yyyy-MM-dd" value="${cjrxx.csrq }" size="30" />
					</td>
				</tr>
			</table>
			<dl class="nowrap">
				<dt>备注</dt>
				<dd>
					<textarea name="remark" style="width: 93%; height: 80">${cjrxx.remark}</textarea>
				</dd>
			</dl>

		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
