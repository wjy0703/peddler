<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" id="saveXhCjrgtjlForm" name="saveXhCjrgtjlForm"
		action="${ctx}/zxgl/saveZxGtjl" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<!--    <input type="hidden" name="id" value="${zxGtjl.id}"/> -->
		<input type="hidden" name="xydkzx.id" value="${xydkzx.id}" />
		<div class="panelBar">
			
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
			<p><label>本次沟通时间：</label> <input name="bcgtrq" type="text"
						size="30" value="${zxGtjl.bcgtrq}" class="date"
						pattern="yyyy-MM-dd hh:mm:ss" maxlength="40" /></p>
						<p><label>下一步：</label> 
						<c:if test="${xydkzx.zhuangTai=='3'}"><input name="" type="text" size="30" value="已进件" disabled="disabled"></c:if>
						<c:if test="${xydkzx.zhuangTai!='3'}">
						<select name="state"
						class="required combox" >
						<option value=""
							<c:if test="${xydkzx.zhuangTai==''}">selected</c:if> selected>请选择</option>
						<option value="0"
							<c:if test="${xydkzx.zhuangTai=='0'}">selected</c:if>>继续跟踪</option>
						<option value="1"
							<c:if test="${xydkzx.zhuangTai=='1'}">selected</c:if>>客户放弃</option>
						<option value="2"
							<c:if test="${xydkzx.zhuangTai=='2'}">selected</c:if>>不符合进件条件</option>
							
								<c:if test="${xydkzx.zhuangTai=='3'}"><option value="3"
							selected>已进件</option></c:if>

					</select></c:if></p>
			
			<dl class="nowrap">
				<dt>沟通内容描述：</dt>
				<dd>
					<textarea name="gtjl" style="width: 93%; height: 80"
						maxlength="1000">${zxGtjl.gtjl}</textarea>
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
