<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/hkgl/savehkgl"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${hkgl.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td>
						<!-- <input type="hidden" name="employeeCca.id" value="${cjrxx.employeeCca.id}"/>
				<input type="text" id="empname" class="textInput" name="employeeCca.name" 
				value="${cjrxx.employeeCca.name }" suggestFields="name,deptname" 
				suggestUrl="${ctx }/baseinfo/suggestemployee" lookupGroup="employeeCca"/>
				<a class="btnLook" href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text key="查找带回"/></a>	 -->
						<label>账户编码：</label> <input name="zhbm" type="text" size="30"
						value="${hkgl.zhbm }" maxlength="40" disabled="disabled" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td><label>开户名：</label> <input name="khm" type="text"
						size="30" value="${hkgl.khm }" maxlength="40" disabled="disabled" />
					</td>
					<td><label>回款银行：</label> <input name="hkyh" type="text"
						size="30" value="${hkgl.hkyh }" class="required" maxlength="40"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>开户行：</label> <input name="zjhm" type="text"
						size="30" value="${hkgl.khh }" class="required" maxlength="40"
						disabled="disabled" /></td>
					<td><label>帐号：</label> <input name="zh" type="text" size="30"
						value="${hkgl.zh }" maxlength="80" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>本月应回款日期*：</label> <input type="text" name="byykkrq"
						class="date" pattern="yyyy-MM-dd" value="${hkgl.byykkrq }"
						size="25" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
					<td><label>本月应回款金额*：</label> <input name="byyhkje" type="text"
						size="30" value="${hkgl.byyhkje }" maxlength="80"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>实际回款日期*：</label> <input type="text" name="sjhkrq"
						class="date" pattern="yyyy-MM-dd" value="${hkgl.sjhkrq }"
						size="25" disabled="disabled" /> <a class="inputDateButton"
						href="#">选择</a></td>
					<td><label>实际回款金额*：</label> <input name="sjhkje" type="text"
						size="30" value="${hkgl.sjhkje }" maxlength="80"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>欠款金额*：</label> <input name="qkje" type="text"
						size="30" value="${hkgl.qkje }" maxlength="80" disabled="disabled" />
					</td>
					<td><label>帐外处理金额*：</label> <input name="zwclje" type="text"
						size="30" value="${hkgl.zwclje }" maxlength="80"
						disabled="disabled" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar"></div>
	</form>
</div>
