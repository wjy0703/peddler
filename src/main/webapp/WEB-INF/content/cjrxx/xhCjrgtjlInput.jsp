<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" id="saveXhCjrgtjlForm" name="saveXhCjrgtjlForm"
		action="${ctx}/xhCjrgtjl/saveXhCjrgtjl"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhCjrgtjl.id}" /> <input
			type="hidden" name="cjrxx.id" value="${cjrxx_id}" /> <input
			type="hidden" name="cjrState" value="${cjrState}" />
		<div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/xhCjrgtjl/listXhCjrgtjl?cjrxx_id=${cjrxx_id}&cjrState=${cjrState}"><span>返回列表</span></a></li>
			</ul>
		</div>

		<div class="pageFormContent" layoutH="80">
			<p>
				<label>客户姓名：</label> <input name="" type="text" size="30"
					value="${cjrxx.cjrxm }" disabled="disabled" />
			</p>
			<p>
				<label>证件号码：</label> <input name="" type="text" size="30"
					value="${cjrxx.zjhm }" disabled="disabled" />
			</p>
			<div class="divider"></div>
			
				
					<p><label>意向出资日期：</label> <input name="yxczrq" type="text"
						size="30" value="${xhCjrgtjl.yxczrq}" class="date"
						pattern="yyyy-MM-dd" maxlength="40" /></p>
					<p><label>意向出资金额：</label> <input name="yxczje" type="text"
						size="30" value="${xhCjrgtjl.yxczje}" class="required"
						maxlength="40" /></p>
				
				
					<p><label>本次沟通日期：</label> <input name="bcgtrq" type="text"
						size="30" value="${xhCjrgtjl.bcgtrq}" class="date"
						pattern="yyyy-MM-dd" maxlength="40" /></p>
					<p><label>本次沟通方式：</label> <input name="bcgtfs" type="text"
						size="30" value="${xhCjrgtjl.bcgtfs}" class="required"
						maxlength="20" /></p>
				
				
					<p><label>沟通开始时间：</label> <input name="gtkssj" type="text"
						size="30" value="${xhCjrgtjl.gtkssj}" class="date"
						pattern="yyyy-MM-dd" maxlength="40" /></p>
					<p><label>沟通结束时间：</label> <input name="gtjssj" type="text"
						size="30" value="${xhCjrgtjl.gtjssj}" class="date"
						pattern="yyyy-MM-dd" maxlength="40" /></p>
				
				
					<p><label>意向产品：</label> <select name="yxcp"
						class="required combox">
							<option value=""
								<c:if test="${xhCjrgtjl.yxcp==''}">selected</c:if>>请选择</option>
								<c:forEach items="${tzcp}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${xhCjrgtjl.yxcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
								</c:forEach>
					</select></p>
					<p><label>客户意向：</label> <input name="khyx" type="text"
						size="30" value="${xhCjrgtjl.khyx}" class="required"
						maxlength="20" /></p>
				
				
					<p><label>本次联系人：</label> <input name="bclxr" type="text"
						size="30" value="${xhCjrgtjl.bclxr}" class="required"
						maxlength="20" /></p>
				
			
			<dl class="nowrap">
				<dt>沟通内容描述：</dt>
				<dd>
					<textarea name="gtnrms" style="width: 93%; height: 80"
						maxlength="1000">${xhCjrgtjl.gtnrms}</textarea>
				</dd>
			</dl>
			
				
					<p><label>下次联系日期：</label> <input name="xclxrq" type="text"
						size="30" value="${xhCjrgtjl.xclxrq}" class="date"
						pattern="yyyy-MM-dd" maxlength="40" /></p>
					<p><label>下次联系方式：</label> <input name="xclxfs" type="text"
						size="30" value="${xhCjrgtjl.xclxfs}" class="required"
						maxlength="20" /></p>
				
			
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
</div>