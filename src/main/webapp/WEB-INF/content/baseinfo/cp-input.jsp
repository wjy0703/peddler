<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/saveCp"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cp.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>产品编号：</label> <input name="tzcpBh" type="text" size="30"
					max="9" alt="请输入产品编号" value="${cp.tzcpBh }" class="required digits" />
			</p>
			<p>
				<label>产品名称：</label> <input name="tzcpMc" type="text" size="30"
					alt="请输入产品名称" value="${cp.tzcpMc }" class="required" />
			</p>
			<p>
				<label>产品利率：</label> <input name="tzcpLl" type="text" size="30"
					alt="请输入产品利率" value="${cp.tzcpLl }" class="required" />
			</p>
			<p>
				<label>产品周期：</label> <input name="tzcpZq" type="text" size="30"
					alt="请输入产品周期" value="${cp.tzcpZq }" class="required digits" />
			</p>
			<p>
				<label>产品分类：</label> <select name="tzcpFl" class="required combox"
					ref="combox_tzcpLx" refUrl="${ctx}/baseinfo/getCplx?code={value}">
					<c:forEach items="${type}" var="type" varStatus="st">
						<option value="${type.value }"
							<c:if test="${cp.tzcpFl==type.value}">selected</c:if>>${type.name
							}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>产品类型：</label> <select id="combox_tzcpLx" name="tzcpLx"
					class="required combox">
					<c:forEach items="${type1}" var="type" varStatus="st">
						<option value="${type.value }"
							<c:if test="${cp.tzcpLx==type.value}">selected</c:if>>${type.name
							}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>是否有管理费：</label> <select name="isAdmin"
					class="required combox">
					<option value="0" <c:if test="${cp.isAdmin=='0'}">selected</c:if>
						selected>是</option>
					<option value="1" <c:if test="${cp.isAdmin=='1'}">selected</c:if>>否</option>
				</select>
			</p>
			<p>
				<label>产品状态：</label> <select name="tzcpZt" class="required combox">
					<option value="" <c:if test="${cp.tzcpZt==''}">selected</c:if>>请选择</option>
					<option value="0" <c:if test="${cp.tzcpZt=='0'}">selected</c:if>
						selected>在用</option>
					<option value="1" <c:if test="${cp.tzcpZt=='1'}">selected</c:if>>历史</option>
				</select>
			</p>

			<dl class="nowrap">
				<dt>产品描述：</dt>
				<dd>
					<textarea name="tzcpMs" style="width: 100%;">${cp.tzcpMs}</textarea>
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
