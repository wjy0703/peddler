<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/baseinfo/saveemployee"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${employee.id}" />
		<input type="hidden" name="sts" value="0" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>员工姓名：</label> <input name="name" type="text" size="30"
					alt="请输入员工姓名" value="${employee.name }" class="required" <c:if test="${employee.id != null}">readonly</c:if>/>
			</p>
			<p>
				<label>员工工号：</label> <input name="empNo" type="text" size="30"
					alt="请输入员工工号" value="${employee.empNo }" class="required" <c:if test="${employee.id != null}">readonly</c:if>/>
			</p>
			<p>
				<label>性别：</label> <select name="sex" class="required combox">
					<option value="" <c:if test="${employee.sex==''}">selected</c:if>>请选择</option>
					<option value="F" <c:if test="${employee.sex=='F'}">selected</c:if>
						selected>男</option>
					<option value="M" <c:if test="${employee.sex=='M'}">selected</c:if>>女</option>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>手机号码：</label> <input name="mobile" type="text" size="30"
					value="${employee.mobile }" />
			</p>
			<p>
				<label>家庭电话：</label> <input name="phone" type="text" size="30"
					value="${employee.phone }" />
			</p>
			<p>
				<label>办公电话：</label> <input name="officeTel" type="text" size="30"
					value="${employee.officeTel }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>邮箱：</label> <input name="mail" type="text" size="30"
					value="${employee.mail }" />
			</p>
			<p>
				<label>选择机构/部门：</label> <input type="hidden" name="dept.id" value="1" />
				<input type="hidden" name="organi.id"  value="${employee.organi.id}" />
				<input type="text" id="deptname" class="required" 
					name="organi.name" value="${employee.organi.rganiName }"/>
				<a class="btnLook" href="${ctx }/baseinfo/getdept"
								lookupGroup="organi" <c:if test="${employee.id != null}">disabled</c:if>><hi:text key="查找带回" /></a>
			</p>

			<p>
				<label>职务：</label> 
				<input type="hidden" name="position.id" value="${employee.position.id}" />
				<input  class="required" type="text" id="positionName"
					class="textInput" name="position.positionName"
					value="${employee.position.positionName }"
					/>
				<a class="btnLook" href="${ctx }/baseinfo/openSuggestPosition"
					lookupGroup="position" <c:if test="${employee.id != null}">disabled</c:if>><hi:text key="查找带回" /></a>
			</p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>
					<textarea name="description" style="width: 100%;">${employee.description}</textarea>
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
