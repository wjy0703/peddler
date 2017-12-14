<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhMonthlyDwInfo/saveXhMonthlyDwInfo" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhMonthlyDwInfo.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>主键：</label>
				<input name="id" type="text" size="30" value="${xhMonthlyDwInfo.id}" class="required" maxlength="22" />
			</p>
			<p>
				<label>利息：</label>
				<input name="interest" type="text" size="30" value="${xhMonthlyDwInfo.interest}" class="required" maxlength="22" />
			</p>
			<p>
				<label>借款人名称：</label>
				<input name="loanName" type="text" size="30" value="${xhMonthlyDwInfo.loanName}" class="required" maxlength="64" />
			</p>
			<p>
				<label>金额：</label>
				<input name="money" type="text" size="30" value="${xhMonthlyDwInfo.money}" class="required" maxlength="22" />
			</p>
			<p>
				<label>月应付主表ID：</label>
				<input name="zqtjId" type="text" size="30" value="${xhMonthlyDwInfo.zqtjId}" class="required" maxlength="22" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
