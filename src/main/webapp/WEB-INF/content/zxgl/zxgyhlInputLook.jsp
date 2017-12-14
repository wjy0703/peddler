<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" id="saveXhCjrgtjlForm" name="saveXhCjrgtjlForm"
		action="${ctx}/zxgl/saveZxGtjl" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${zxGtjl.id}" /> <input
			type="hidden" name="xydkzx.id" value="${xydkzx_id}" />
		<div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/zxgl/listZxgtjl?xydkzx_id=${xydkzx_id}"><span>返回列表</span></a></li>
			</ul>
		</div>

		<div class="pageFormContent" layoutH="80">
			<p>
				<label>客户姓名：</label> <input name="" type="text" size="30"
					value="${xydkzx.khmc }" disabled="disabled" />
			</p>
			<p>
				<label>手机号：</label> <input name="" type="text" size="30"
					value="${xydkzx.telephone }" disabled="disabled" />

			</p>
			<div class="divider"></div>
			<table>
			<!--	<tr>
					<td><label>客户评价：</label> <input type="radio" name="khpj"
						value="1" onClick="check(this.value);" checked>优&nbsp; <input
						type="radio" name="khpj" value="2" onClick="check(this.value);">良&nbsp;
						<input type="radio" name="khpj" value="3"
						onClick="check(this.value);">中&nbsp; <input type="radio"
						name="khpj" value="3" onClick="check(this.value);">差&nbsp;
					</td>
					<td><label>是否重点客户：</label> <input type="radio" name="zdkh"
						value="1" onClick="check(this.value);" checked>是&nbsp; <input
						type="radio" name="zdkh" value="2" onClick="check(this.value);">否&nbsp;

					</td>
				</tr>-->
				<tr>
					<td><label>本次沟通日期：</label> <input name="bcgtrq" type="text"
						size="30" value="${zxGtjl.bcgtrq}" class="date"
						pattern="yyyy-MM-dd" maxlength="40" disabled="disabled" /></td>
				</tr>
			</table>
			<dl class="nowrap">
				<dt>沟通内容描述：</dt>
				<dd>
					<textarea name="gtjl" style="width: 93%; height: 80"
						maxlength="1000" disabled="disabled">${zxGtjl.gtjl}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
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
