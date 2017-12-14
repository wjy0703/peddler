<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/savecustomer"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${customer.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户姓名：</label> <input name="name" type="text" size="30"
					alt="请输入客户姓名" value="${customer.name }" class="required" />
			</p>
			<p>
				<label>性别：</label> <select name="sex" class="required combox">
					<option value="" <c:if test="${customer.sex==''}">selected</c:if>>请选择</option>
					<option value="F" <c:if test="${customer.sex=='F'}">selected</c:if>
						selected>男</option>
					<option value="M" <c:if test="${customer.sex=='M'}">selected</c:if>>女</option>
				</select>
			</p>
			<p>
				<label>出生日期：</label> <input type="text" name="birthDay" class="date"
					format="yyyy-MM-dd"
					value="<fmt:formatDate value='${customer.birthDay}' type='both' pattern='yyyy-MM-dd' />"
					readonly="true" /> <a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>职务：</label> <input name="post" type="text" size="30"
					value="${customer.post }" />
			</p>

			<dl class="nowrap">
				<dt>简介</dt>
				<dd>
					<textarea name="briefIntroduce" style="width: 100%;">${customer.briefIntroduce}</textarea>
				</dd>
			</dl>
			<div class="divider"></div>
			<p>
				<label>手机号码1：</label> <input name="mobile1" type="text" size="30"
					value="${customer.mobile1 }" />
			</p>
			<p>
				<label>手机号码2：</label> <input name="mobile2" type="text" size="30"
					value="${customer.mobile2 }" />
			</p>
			<p>
				<label>固定电话1：</label> <input name="tel1" type="text" size="30"
					value="${customer.tel1 }" />
			</p>
			<p>
				<label>固定电话2：</label> <input name="tel2" type="text" size="30"
					value="${customer.tel2 }" />
			</p>
			<p>
				<label>QQ号码：</label> <input name="qq" type="text" size="30"
					value="${customer.qq }" />
			</p>
			<p>
				<label>MSN号码：</label> <input name="msn" type="text" size="30"
					value="${customer.msn }" />
			</p>
			<p>
				<label>邮箱：</label> <input name="email" type="text" size="30"
					value="${customer.email }" />
			</p>
			<p>
				<label>邮政编码：</label> <input name="zip" type="text" size="30"
					value="${customer.zip }" />
			</p>

			<dl class="nowrap">
				<dt>联系地址：</dt>
				<dd>
					<textarea name="address" style="width: 100%;">${customer.address}</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" style="width: 100%;">${customer.remark}</textarea>
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
</div>