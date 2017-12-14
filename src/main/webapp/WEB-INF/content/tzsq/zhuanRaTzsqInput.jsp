<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/tzsq/saveTzsq"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>客户编码：</label> <input type="hidden"
						name="employeeCca.id" value="${cjrxx.employeeCca.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employeeCca.name" value="${cjrxx.employeeCca.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employeeCca" /> <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text
								key="查找带回" /></a></td>
					<td><label>客户姓名：</label> <input name="hy" type="text"
						size="30" value="${cjrxx.hy }" maxlength="40" /></td>
					<td><label>证件号码：</label> <input name="zjhm" type="text"
						size="30" value="${cjrxx.zjhm }" class="required" maxlength="40" />
					</td>
				</tr>
				<tr>
					<td><label>CRM：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
					<td><label>是否定投：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
					<td><label>回收方式：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
				</tr>
			</table>
			<div class="divider"></div>
			<table>
				<tr>
					<td><label>协议编号：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
					<td><label>申请日期*：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
				</tr>
				<tr>
					<td><label>意向转让日期*：</label> <input name="" type="text"
						size="30" value="" maxlength="80" /></td>
					<td><label>是否紧急*：</label> <input type="radio" name="c1"
						value="2" />是 <input type="radio" name="c1" value="2" />否</td>
				</tr>
				<tr>
					<td><label>是否愿意提前转让*：</label> <input type="radio" name="c2"
						value="2" />是 <input type="radio" name="c2" value="2" />否</td>
					<td><label>初始出借日期*：</label> <input name="" type="text"
						size="30" value="" maxlength="80" /></td>
				</tr>
				<tr>
					<td><label>转让需求*：</label> <select name="khch"
						class="required combox">
							<option value="0">其他</option>
					</select></td>
					<td><label>初始出借金额：</label> <input name="" type="text"
						size="30" value="" maxlength="80" /></td>
				</tr>
				<tr>
					<td><label>转让协议方式*：</label> <select name="khch"
						class="required combox">
							<option value="0">快递</option>
					</select> <input name="" type="text" size="5" value="" maxlength="80" /></td>
					<td><label>部门主管*：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
				</tr>
			</table>
			<dl class="nowrap">
				<dt>转让原因：</dt>
				<dd>
					<select name="khch" class="required combox">
						<option value="0">其他</option>
					</select>
					<textarea name="" style="width: 93%; height: 80"></textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>对信和评价：</dt>
				<dd>
					<textarea name="" style="width: 93%; height: 80"></textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>银行账户*：</dt>
				<dd>
					<table>
						<tr>
							<td>开户行：</td>
							<td><select name="" class="required combox">
									<option value="">请选择</option>
									<option value="0">建设银行</option>
									<option value="1">交通银行</option>
							</select></td>
							<td>卡或折：</td>
							<td><select name="" class="required combox">
									<option value="">请选择</option>
									<option value="0">借记卡</option>
									<option value="1">信用卡</option>
							</select></td>
							<td>具体支行：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td>开户姓名：</td>
							<td><input name="" type="text" size="10" value=""
								maxlength="80" /></td>
							<td>账户：</td>
							<td colspan="3"><input name="" type="text" size="30"
								value="" maxlength="80" /></td>
						</tr>
					</table>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>申请单原件：</dt>
				<dd>
					<input name="" type="file" size="30" value="" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="" style="width: 93%; height: 80"></textarea>
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
</div></div>
