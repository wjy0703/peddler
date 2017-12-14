<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/saveZd"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${zd.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>字典名称：</label> <input name="name" type="text" size="30"
					alt="请输入名称" value="${zd.name }" class="required" />
			</p>
			<p>
				<label>字典参数：</label> <input name="value" type="text" size="30"
					alt="请输入参数" value="${zd.value }" class="required" />
			</p>
			<p>
				<label>字典类型：</label> <select name="code.id" class="required combox">
					<c:forEach items="${type}" var="type" varStatus="st">
						<option value="${type.id }"
							<c:if test="${zd.code.id==type.id}">selected</c:if>>${type.typeName
							}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>字典状态：</label> <select name="state" class="required combox">
					<option value="" <c:if test="${zd.state==''}">selected</c:if>>请选择</option>
					<option value="0" <c:if test="${zd.state=='0'}">selected</c:if>
						selected>在用</option>
					<option value="1" <c:if test="${zd.state=='1'}">selected</c:if>>历史</option>
				</select>
			</p>
					<p>
				<label>银行编码：</label> <input name="bankCode" type="text" size="30"
					alt="请输入参数" value="${zd.bankCode }" class="" />
			</p>
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