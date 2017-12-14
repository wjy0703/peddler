<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form id="loanChangeappliForm" name="loanChangeappliForm" method="post"
		action="${ctx}/loan/saveLoanChangeappli"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>借款人姓名：</label> <input id="jkrxm" name="jkrxm"
						type="text" size="30" value="${xhcfdkrxxxgjl.jkrxm }"
						class="required" maxlength="80" /></td>
					<td><label>借款人编号：</label> <input id="dkrxxbh" name="dkrxxbh"
						type="text" size="30" value="${xhcfdkrxxxgjl.dkrxxbh }"
						class="required" maxlength="40" /></td>
					<td><label>证件号码：</label> <input id="zjhm" name="zjhm"
						type="text" size="30" value="${xhcfdkrxxxgjl.zjhm }"
						class="required" maxlength="40" /></td>
				</tr>
				<tr>
					<td><label>手机号码：</label> <input id="yddh" name="yddh"
						type="text" size="30" value="${xhcfdkrxxxgjl.yddh }"
						class="required" maxlength="80" /></td>
					<td><label>CRM：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
					<td><label>CCA：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
				</tr>
			</table>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>个人信息：</dt>
				<dd>
					<table>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td><label>移动电话：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td><label>固定电话：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td><label>电子邮箱：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td><label>工作单位：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td><label>账单寄送方式：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td><label>账单寄送地址：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
					</table>
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>
					<input name="ids" value="1" type="checkbox">紧急联系人：
				</dt>
				<dd>
					<table>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td>姓名：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td>移动电话：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td>固定电话：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td>证件类型：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox"></td>
							<td>证件号码：</td>
							<td colspan="3"><input name="" type="text" size="30"
								value="" maxlength="80" /></td>
						</tr>
					</table>
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>
					<input name="ids" value="1" type="checkbox">其他：
				</dt>
				<dd>
					<table>
						<tr>
							<td><input name="ids" value="1" type="checkbox">变更项：</td>
							<td>银行账号</td>
							<td>变更为：</td>
							<td>62 10000 1111 8888</td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox">变更项：</td>
							<td>银行账号</td>
							<td>变更为：</td>
							<td>62 10000 1111 8888</td>
						</tr>
						<tr>
							<td><input name="ids" value="1" type="checkbox">变更项：</td>
							<td>银行账号</td>
							<td>变更为：</td>
							<td>62 10000 1111 8888</td>
						</tr>
					</table>
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>
					<input name="ids" value="1" type="checkbox">部门主管：
				</dt>
				<dd>
					<table>
						<tr>
							<td>孙小美</td>
						</tr>
					</table>
				</dd>
			</dl>
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>
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
