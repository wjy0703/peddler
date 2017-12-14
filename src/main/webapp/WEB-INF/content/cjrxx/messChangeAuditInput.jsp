<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/cjrxx/saveMessChangeAudit"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" /> <input
			type="hidden" name="cjrxx.id" value="${cjrxx.cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>客户姓名：</label> <input name="" type="text" size="30"
						value="${cjrxx.cjrxx.cjrxm }" maxlength="5" disabled="disabled" />
					</td>
					<td><label>客户编号：</label> <input name="" type="text" size="30"
						value="${cjrxx.cjrxx.khbm }" maxlength="10" disabled="disabled" />
					</td>
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
					<td><label>移动电话：</label> 
					<input name="yddh" type="text" size="30" value="${cjrxx.cjrxx.yddh }" class="required sameCheck" 	maxlength="20" disabled="disabled" /></td>
					<td><label>变更为：</label> <input name="yddh" type="text" size="30" value="${cjrxx.yddh }"  maxlength="20" 	disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>固定电话：</label> 
					<input name="gddh" type="text" 	size="30" value="${cjrxx.cjrxx.gddh }" maxlength="20" disabled="disabled" class="sameCheck"/></td>
					<td><label>变更为：</label> <input name="gddh" type="text" size="30" value="${cjrxx.gddh }" maxlength="20"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>电子邮箱：</label> <input name="dzyx" type="text"
						size="30" value="${cjrxx.cjrxx.dzyx }" maxlength="80"
						disabled="disabled" class="sameCheck" /></td>
					<td><label>变更为：</label> <input name="dzyx" type="text"
						size="30" value="${cjrxx.dzyx }" maxlength="80"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>单位名称：</label> <input name="gzdwmc" type="text"
						size="30" value="${cjrxx.cjrxx.gzdwmc }" maxlength="100"
						disabled="disabled" class="sameCheck"/></td>
					<td><label>变更为：</label> <input name="gzdwmc" type="text"
						size="30" value="${cjrxx.gzdwmc }" maxlength="100"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>账单收取方式：</label> <select name="zqjsfs"
						class="required combox" disabled="disabled">
							<option value=""
								<c:if test="${cjrxx.cjrxx.zqjsfs==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zqjsfs}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrxx.zqjsfs==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select></td>
					<td><label>变更为：</label> <select name="zqjsfs"
						class="required combox" disabled="disabled">
							<option value="" <c:if test="${cjrxx.zqjsfs==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zqjsfs}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.zqjsfs==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>邮编：</label> <input name="yb" type="text" size="30"
						value="${cjrxx.cjrxx.yb }" maxlength="6" disabled="disabled" class="sameCheck"/></td>
					<td><label>变更为：</label> <input name="yb" type="text" size="30"
						value="${cjrxx.yb }" maxlength="6" disabled="disabled" /></td>
				</tr>
				<tr>
					<td>
					   <label>通讯地址：</label> 
					    <sen:address names="province,city,area" titles="所有省市,所有城市,所有区县" values="${cjrxx.cjrxx.province},${cjrxx.cjrxx.city},${cjrxx.cjrxx.area}"/>
						
				       <input name="txdz" type="text" size="60" value="${cjrxx.cjrxx.txdz }" maxlength="100" disabled="disabled"  class="sameCheck" />
					</td>
					<td>
					   <label>变更为：</label>
					    <sen:address names="province,city,area" titles="所有省市,所有城市,所有区县" values="${cjrxx.province},${cjrxx.city},${cjrxx.area}"/>
					    <input name="txdz" type="text" size="60" value="${cjrxx.txdz }" maxlength="100" disabled="disabled" />
					 </td>
				</tr>
			</table>
			<div class="divider"></div>
			<label>紧急联系人：</label>
			<table>
				<tr>
					<td><label>中文姓名：</label> <input name="jjlxrzwmc" type="text"
						size="30" value="${cjrxx.cjrxx.jjlxrzwmc }" class="required sameCheck" 
						maxlength="10" disabled="disabled" /></td>
					<td><label>变更为：</label> <input name="jjlxrzwmc" type="text"
						size="30" value="${cjrxx.jjlxrzwmc }"
						maxlength="10" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>证件类型：</label>
					  <sen:select clazz="combox" name="jjlxrzjlx" coding="cardType" value="${cjrxx.cjrxx.jjlxrzjlx}"/>
					 <%-- <select name="jjlxrzjlx"
						class="required combox" disabled="disabled">
							<option value=""
								<c:if test="${cjrxx.cjrxx.jjlxrzjlx==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zjlx}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.cjrxx.jjlxrzjlx==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> --%>
					</td>
					<td><label>变更为：</label>
					   <sen:select clazz="combox" name="jjlxrzjlx" coding="cardType" value="${cjrxx.jjlxrzjlx}"/> 
<%-- 					   <select name="jjlxrzjlx"
						class="required combox" disabled="disabled">
							<option value=""
								<c:if test="${cjrxx.jjlxrzjlx==''}">selected</c:if>>请选择</option>
							<c:forEach items="${zjlx}" var="md" varStatus="st">
								<option value="${md.value }"
									<c:if test="${cjrxx.jjlxrzjlx==md.value}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					   </select> --%>
					</td>
				</tr>
				<tr>
					<td><label>证件号码：</label> <input name="jjlxrzjhm" type="text"
						size="30" value="${cjrxx.cjrxx.jjlxrzjhm }" class="required sameCheck"
						maxlength="40" disabled="disabled" /></td>
					<td><label>变更为：</label> <input name="jjlxrzjhm" type="text"
						size="30" value="${cjrxx.jjlxrzjhm }"  
						maxlength="40" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>移动电话：</label> <input name="jjlxryddh" type="text"
						size="30" value="${cjrxx.cjrxx.jjlxryddh }" class="required sameCheck"
						maxlength="40" disabled="disabled" /></td>
					<td><label>变更为：</label> <input name="jjlxryddh" type="text"
						size="30" value="${cjrxx.jjlxryddh }" 
						maxlength="40" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label>固定电话：</label> <input name="jjlxrgddh" type="text"
						size="30" value="${cjrxx.cjrxx.jjlxrgddh }" class="required sameCheck"
						maxlength="40" disabled="disabled" /></td>
					<td><label>变更为：</label> <input name="jjlxrgddh" type="text"
						size="30" value="${cjrxx.jjlxrgddh }" 
						maxlength="40" disabled="disabled" /></td>
				</tr>
			</table>
			<dl class="nowrap">
				<dt>审批意见：</dt>
				<dd>
					<textarea name="auditIdea" style="width: 93%; height: 80">${cjrxx.auditIdea }</textarea>
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
</div>
</div>
<script>
$(function(){
	$('.sameCheck').each(function(){
		var $this = $(this);
		var $next = $this.parents('td').next('td').find('input');
		if($this.val() != $next.val()){
			$next.addClass('differenceStyle');
		}
	});
});
</script>