<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/fkgl/savefkgl"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${fkgl.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>出借编码：</label> <input name="cjrbh" type="text"
						size="30" value="${fkgl.cjrbh }" maxlength="40" readonly /></td>
					<td><label>付款类型*：</label> <select name="fklx"
						class="required combox" readonly>
							<option value="" <c:if test="${fkgl.fklx==''}">selected</c:if>>请选择</option>
							<option value="0" <c:if test="${fkgl.fklx=='0'}">selected</c:if>
								selected>正常付款</option>
							<option value="1" <c:if test="${fkgl.fklx=='1'}">selected</c:if>>保障金付款</option>
					</select></td>
				</tr>
				<tr>
					<td><label>开户名：</label> <input name="khm" type="text"
						size="30" value="${fkgl.khm }" maxlength="40" readonly /></td>
					<td><label>开户银行：</label> <input name="khyh" type="text"
						size="30" value="${fkgl.khyh }" class="required" maxlength="40"
						readonly /></td>
				</tr>
				<tr>
					<td><label>开户行：</label> <input name="khh" type="text"
						size="30" value="${fkgl.khh }" class="required" maxlength="40"
						readonly /></td>
					<td><label>帐号：</label> <input name="zh" type="text" size="30"
						value="${fkgl.zh }" maxlength="80" readonly /></td>
				</tr>
				<tr>
					<td><label>应付款金额*：</label> <input name="yfkje" type="text"
						size="30" value="${fkgl.yfkje }" maxlength="80" readonly /></td>
				</tr>
				<tr>
					<td><label>实际付款金额*：</label> <input name="sjyfje" type="text"
						size="30" value="${fkgl.sjyfje }" maxlength="80" readonly /></td>
					<td><label>实际付款日期*：</label> <input type="text" name="sjfkrq"
						class="date" pattern="yyyy-MM-dd" value="${fkgl.sjfkrq }"
						size="25" readonly /> <a class="inputDateButton" href="#">选择</a></td>
				</tr>
			</table>
		</div>
		<div class="formBar"></div>
	</form>
</div>
