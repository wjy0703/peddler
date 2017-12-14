<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx}/cjrxx/saveMessChange"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${cjrxx.id}" /> <input
				type="hidden" name="cjrxx.id" value="${cjrxx.cjrxx.id}" /> <input
				type="hidden" name="state" id="state" value="0" />
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td><label>出借人姓名：</label> <input name="" type="text"
							size="30" value="${cjrxx.cjrxx.cjrxm }" maxlength="5"
							disabled="disabled" /></td>
						<td><label>出借人编号：</label> <input name="" type="text"
							size="30" value="${cjrxx.cjrxx.khbm }" maxlength="10"
							disabled="disabled" /></td>
					</tr>
					<tr>
						<td><label>证件号码：</label> <input name="" type="text" size="30"
							value="${cjrxx.cjrxx.zjhm }" maxlength="40" disabled="disabled" />
						</td>
						<td><label>移动电话：</label> <input name="" type="text" size="30"
							value="${cjrxx.cjrxx.yddh }" maxlength="20" disabled="disabled" />
						</td>
					</tr>
				</table>
				<div class="divider"></div>
				<label>个人信息：</label>
				<table>
					<tr>
						<td><label>移动电话：</label> <input name="yddh" type="text"
							size="30" value="${cjrxx.yddh }" class="required" maxlength="20" />
						</td>
					</tr>
					<tr>
						<td><label>固定电话：</label> <input name="gddh" type="text"
							size="30" value="${cjrxx.gddh }" maxlength="20" /></td>
					</tr>
					<tr>
						<td><label>电子邮箱：</label> <input name="dzyx" type="text"
							size="30" value="${cjrxx.dzyx }" maxlength="80" /></td>
					</tr>
					<tr>
						<td><label>单位名称：</label> <input name="gzdwmc" type="text"
							size="30" value="${cjrxx.gzdwmc }" maxlength="100" /></td>
					</tr>
					<tr>
						<td><label>账单收取方式：</label> <select name="zqjsfs"
							class="required combox">
								<option value=""
									<c:if test="${cjrxx.zqjsfs==''}">selected</c:if>>请选择</option>
								<c:forEach items="${zqjsfs}" var="md" varStatus="st">
									<option value="${md.value }"
										<c:if test="${cjrxx.zqjsfs==md.value}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>邮编：</label> <input name="yb" type="text" size="30"
							value="${cjrxx.yb }" maxlength="6" /></td>
					</tr>
					<tr>
						<td><label>通讯地址：</label> <select class="combox"
							name="province" ref="combox_city"
							refUrl="${ctx}/cjrxx/getCity?code={value}">
								<option value=""
									<c:if test="${cjrxx.province==''}">selected</c:if>>所有省市</option>
								<c:forEach items="${province}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${cjrxx.province==md.id}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select> <select class="combox" name="city" id="combox_city"
							ref="combox_area" refUrl="${ctx}/cjrxx/getArea?code={value}">
								<option value="" <c:if test="${cjrxx.city==''}">selected</c:if>>所有城市</option>
								<c:forEach items="${city}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${cjrxx.city==md.id}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select> <select class="combox" name="area" id="combox_area">
								<option value="" <c:if test="${cjrxx.area==''}">selected</c:if>>所有区县</option>
								<c:forEach items="${area}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${cjrxx.area==md.id}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select> <input name="txdz" type="text" size="60" value="${cjrxx.txdz }"
							maxlength="100" /></td>
					</tr>
				</table>
				<div class="divider"></div>
				<label>紧急联系人：</label>
				<table>
					<tr>
						<td><label>中文姓名：</label> <input name="jjlxrzwmc" type="text"
							size="30" value="${cjrxx.jjlxrzwmc }" class="required"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td><label>证件类型：</label> <select name="jjlxrzjlx"
							class="combox">
								<option value=""
									<c:if test="${cjrxx.jjlxrzjlx==''}">selected</c:if>>请选择</option>
								<c:forEach items="${zjlx}" var="md" varStatus="st">
									<option value="${md.value }"
										<c:if test="${cjrxx.jjlxrzjlx==md.value}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>证件号码：</label> <input name="jjlxrzjhm" type="text"
							size="30" value="${cjrxx.jjlxrzjhm }" class="" maxlength="40" /></td>
					</tr>
					<tr>
						<td><label>移动电话：</label> <input name="jjlxryddh" type="text"
							size="30" value="${cjrxx.jjlxryddh }" class="" maxlength="40" /></td>
					</tr>
					<tr>
						<td><label>固定电话：</label> <input name="jjlxrgddh" type="text"
							size="30" value="${cjrxx.jjlxrgddh }" class="" maxlength="40" /></td>
					</tr>
				</table>
				<c:if test="${cjrxx.upstate=='3' }">
					<dl class="nowrap">
						<dt>审批意见：</dt>
						<dd>
							<textarea name="auditIdea" style="width: 93%; height: 80"
								disabled="disabled">${cjrxx.auditIdea }</textarea>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>审批结果：</dt>
						<dd>
							<input type="radio" name="upstate" value="2" disabled="disabled" />通过
							<input type="radio" name="upstate" value="3" checked="checked"
								disabled="disabled" />不通过
						</dd>
					</dl>
				</c:if>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(0);">保存</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return subState(1);">提交</button>
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
</div>
