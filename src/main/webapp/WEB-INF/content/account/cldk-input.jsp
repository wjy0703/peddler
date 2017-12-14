<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/account/savecldk"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xydkzx.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>咨询编号：</label> <input name="zxbm" type="text" size="30"
					alt="请输入咨询编码" value="${xydkzx.zxbm }" class="required" />
			</p>
			<p>
				<label>咨询客户姓名：</label> <input name="khmc" type="text" size="30"
					alt="请输入客户姓名" value="${xydkzx.khmc }" class="required" />
			</p>

			<p>
				<label>性别：</label> <select name="sex" class="required combox">
					<option value="" <c:if test="${xydkzx.sex==''}">selected</c:if>>请选择</option>
					<option value="0" <c:if test="${xydkzx.sex=='0'}">selected</c:if>
						selected>男</option>
					<option value="1" <c:if test="${xydkzx.sex=='1'}">selected</c:if>>女</option>
				</select>
			</p>
			<p>
				<label>固定电话：</label> <input name="telephone" type="text" size="30"
					alt="固定电话" value="${xydkzx.telephone }" class="required" />
			</p>
			<p>
				<label>手机号码：</label> <input name="phone" type="text" size="30"
					alt="手机号码" value="${xydkzx.phone }" class="required" />
			</p>
			<p>
				<label>所在城市：</label> <input name="crty" type="text" size="30"
					alt="所在城市" value="${xydkzx.crty }" class="required" />
			</p>
			<p>
				<label>车牌号：</label> <input name="carNumber" type="text" size="30"
					alt="车牌号" value="${xydkzx.crty }" />
			</p>
			<p>
				<label>车架号：</label> <input name="carShelf" type="text" size="30"
					alt="车架号" value="${xydkzx.crty }" />
			</p>
			<p>
				<label>团队经理姓名：</label> <input name="teamName" type="text" size="30"
					alt="请输入团队经理姓名" value="${xydkzx.teamName }" class="required" />
			</p>
			<p>
				<label>销售人员姓名：</label> <input name="saleName" type="text" size="30"
					alt="请输入销售人员姓名" value="${xydkzx.saleName }" class="required" />
			</p>
			<p>
				<label>计划借款金额：</label> <input name="planAmount" type="text"
					size="30" value="${xydkzx.planAmount }" />
			</p>
			<p>
				<label>借款用途：</label> <input name="loanPurpose" type="text" size="30"
					value="${xydkzx.loanPurpose }" />
			</p>
			<p>
				<label>单据类型：</label> <input name="zhongLi" type="text" size="30"
					value="车借" readonly />
			</p>
			<dl class="nowrap">
				<dt>沟通记录：</dt>
				<dd>
					<textarea name="gtjl" style="width: 100%;">${xydkzx.gtjl}</textarea>
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
