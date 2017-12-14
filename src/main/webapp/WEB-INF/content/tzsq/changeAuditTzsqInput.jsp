<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhTzsq/saveTzsqChangeAudit"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhTzsq.id}" />
		<input type="hidden" name="xhTzsq.id" value="${xhTzsq.xhTzsq.id}" />
		<div class="pageFormContent" layoutH="56">
			<table width="100%">
				<tr>
					<td><label>客户编码：</label> <input type="text"
						disabled="disabled" value="${xhTzsq.cjrxx.khbm}"
						 size="20" /></td>
					<td><label>客户姓名：</label> <input 
						type="text" size="30" value="${xhTzsq.cjrxx.cjrxm }"
						maxlength="40" disabled="disabled"/></td>
					<td><label>证件号码：</label> <input 
						type="text" size="30" value="${xhTzsq.cjrxx.zjhm }" maxlength="40"
						disabled="disabled"/></td>
				</tr>
			</table>
			<div class="divider"></div>
				<table>
				<tr>
					<td><label>计划出借日期：</label> <input name="jhtzje" type="text" disabled="disabled"
						size="30" value="${xhTzsq.xhTzsq.jhtzrq}" maxlength="20" /></td>
					<td><label>变更为：</label> <input name="jhtzje" type="text" disabled="disabled"
						<c:if test="${xhTzsq.xhTzsq.jhtzrq!=xhTzsq.jhtzrq}">class="differenceStyle"</c:if>
						size="30" value="${xhTzsq.jhtzrq}" maxlength="20" />
						</td>
				</tr>
				<tr>
					<td><label>计划出借金额：</label> 
					<input name="jhtzje" type="text" disabled="disabled"
						size="30" value="${xhTzsq.xhTzsq.jhtzje}" maxlength="20" />
					</td>
					<td><label>变更为：</label> <input name="jhtzje" type="text" disabled="disabled"
					<c:if test="${xhTzsq.xhTzsq.jhtzje!=xhTzsq.jhtzje}">class="differenceStyle"</c:if>
						size="30" value="${xhTzsq.jhtzje}" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td><label>出借方式：</label> 
					<input name="jhtzje" type="text" disabled="disabled"
						size="30" value="${xhTzsq.xhTzsq.tzfs}" maxlength="20" />
					</td>
					<td><label>变更为：</label> 
						<input name="jhtzje" type="text" disabled="disabled"
						<c:if test="${xhTzsq.xhTzsq.tzfs!=xhTzsq.tzfs}">class="differenceStyle"</c:if>
						size="30" value="${xhTzsq.tzfs}" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td><label>付款方式：</label>
					<input name="jhtzje" type="text" disabled="disabled"
						size="30" value="${xhTzsq.xhTzsq.fkfs}" maxlength="20" />
					</td>
					<td><label>变更为：</label>
						<input name="jhtzje" type="text" disabled="disabled"
						<c:if test="${xhTzsq.xhTzsq.fkfs!=xhTzsq.fkfs}">class="differenceStyle"</c:if>
						size="30" value="${xhTzsq.fkfs}" maxlength="20" />
					</td>
					</tr>
			</table>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>出借/回款银行账户<font color="red">*</font>：</dt>
				<dd>
				<table>
					<tr>
							<td ><label>开户行：</label>
							</td>
							<td>
							<input name="jhtzje" type="text" disabled="disabled"
						size="50" value='<sen:vtoName coding="bank" value="${xhTzsq.xhTzsq.tzfkkhh }" />  ${xhTzsq.xhTzsq.tzfkyhmc}' maxlength="20" />
							</td>
							<td><label>变更为：</label>
							</td>
							<td>
							<input name="jhtzje" type="text" disabled="disabled"
							<c:if test="${xhTzsq.xhTzsq.tzfkkhh!=xhTzsq.tzfkkhh}">class="differenceStyle"</c:if>
							<c:if test="${xhTzsq.xhTzsq.tzfkyhmc!=xhTzsq.tzfkyhmc}">class="differenceStyle"</c:if>
						size="50" value="<sen:vtoName coding="bank" value="${xhTzsq.xhTzsq.tzfkkhh }" /> ${xhTzsq.tzfkyhmc}" maxlength="20" />
							</td>
						</tr>
				<tr>
							<td><label>账户：</label>
							</td>
							<td><input name="jhtzje" type="text" disabled="disabled"
						size="30" value="${xhTzsq.xhTzsq.tzfkyhzh}" maxlength="20" />
							</td>
							<td><label>变更为：</label>
							</td>
							<td><input name="jhtzje" type="text" disabled="disabled"
							<c:if test="${xhTzsq.xhTzsq.tzfkyhzh!=xhTzsq.tzfkyhzh}">class="differenceStyle"</c:if>
						size="30" value="${xhTzsq.tzfkyhzh}" maxlength="20" />
							</td>
							</tr>
			</table>
					
				</dd>
			</dl>
			<p>
					<label>附件：</label> 
					<a class="icon" href="${ctx}/loan/downFile/${xhUploadFiles.id}" targetType="navTab" title="下载当前文件吗?"><span>${xhUploadFiles.filename}</span></a>
				</p>
			<dl class="nowrap">
				<dt>审批意见：</dt>
				<dd>
					<textarea name="" style="width: 93%; height: 80">${xhTzsq.auditIdea }</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>审批结果：</dt>
				<dd>
					<input type="radio" name="upstate" value="2" checked="checked" />通过
					<input type="radio" name="upstate" value="3" />不通过
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
