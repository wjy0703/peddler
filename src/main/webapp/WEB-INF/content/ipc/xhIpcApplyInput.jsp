<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/ipc/saveXhIpcApply" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhIpcApply.id}"/>
		<input type="hidden" name="jksqId" value="${xhIpcApply.loanApply.id}"/>
		
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户姓名：</label>
				<input name="customerName" type="text" size="30" value="${xhIpcApply.customerName}" class="required" maxlength="50" />
			</p>
			<p>
				<label>客户编号：</label>
				<input name="customerNum" type="text" size="30" value="${xhIpcApply.customerNum}" class="required" maxlength="50" />
			</p>
			<p>
				<label>客户电话：</label>
				<input name="customerPhone" type="text" size="30" value="${xhIpcApply.customerPhone}" class="required" maxlength="50" />
			</p>
			<p>
				<label>身份证号：</label>
				<input name="customerCardId" type="text" size="30" value="${xhIpcApply.customerCardId}" class="required isIdCardNo" maxlength="50" />
			</p>
			<div class="divider"></div>
			<p>
				<label>开户行：</label>
				<input name="bankName" type="text" size="30" value="${xhIpcApply.bankName}" class="required" maxlength="50" />
			</p>
			<p>
				<label>账（卡）号：</label>
				<input name="bankCardNum" type="text" size="30" value="${xhIpcApply.bankCardNum}" class="required" maxlength="50" />
			</p>
			<div class="divider"></div>
			<p>
				<label>共借人姓名：</label>
				<input name="togetherName" type="text" size="30" value="${xhIpcApply.togetherName}" class="required" maxlength="50" />
			</p>
			<p>
				<label>共借人关系：</label>
				<input name="togetherRelation" type="text" size="30" value="${xhIpcApply.togetherRelation}" class="required" maxlength="50" />
			</p>
			<p>
				<label>共借人电话：</label>
				<input name="togetherPhone" type="text" size="30" value="${xhIpcApply.togetherPhone}" class="required" maxlength="50" />
			</p>
						
			
			
				<dl class="nowrap">
					<dt>共借人住址：</dt>
					<dd>
						<textarea name="togetherHomeAddress" style="width: 100%;"  class="required">${xhIpcApply.togetherHomeAddress}</textarea>
					</dd>
				</dl>
			<div class="divider"></div>
			
			
			
			<dl class="nowrap">
					<dt>商铺（单位）地址：</dt>
					<dd>
						<textarea name="customerCompAddress" style="width: 100%;"  class="required">${xhIpcApply.customerCompAddress}</textarea>
					</dd>
				</dl>
			
			
				<dl class="nowrap">
					<dt>家庭地址：</dt>
					<dd>
						<textarea name="customerHomeAddress" style="width: 100%;"  class="required">${xhIpcApply.customerHomeAddress}</textarea>
					</dd>
				</dl>
			
			
			
			

			<p>
				<label>通讯录 ：</label>
				<select  name="txl" class="combox"><option value="有">有</option ><option value="无">无</option></select>
				
			</p>
				<div class="divider"></div>
			<p>
				<label>借款类型：</label>
				<select name="loanType"
						class="combox required">
						<option value="" <c:if test="${xhIpcApply.loanType == ''}">selected</c:if>>请选择</option>
						<option value="A" <c:if test="${xhIpcApply.loanType == 'A'}">selected</c:if> >老板借</option>
						<option value="C" <c:if test="${xhIpcApply.loanType == 'C'}">selected</c:if> >薪水借</option>
				</select>
				
				
			</p>
			<p>
				<label>借款用途：</label>
				<sen:select clazz="combox required" name="loanUse" coding="loanUseType" value="${xhIpcApply.loanUse}"/> 
				
				
				<%-- <select name="loanUse"
						class="combox required"><option value="" selected>请选择</option>
						<option value="1" <c:if test="${xhIpcApply.loanUse == '经营'}">selected</c:if> >经营</option>
						<option value="2" <c:if test="${xhIpcApply.loanUse == '消费'}">selected</c:if> >消费</option>
						<option value="3" <c:if test="${xhIpcApply.loanUse == '周转'}">selected</c:if> >周转</option>
				</select> --%>
			</p>
				<div class="divider"></div>
			<div class="divider"></div>
			<p>
				<label>开发信借员
：</label>
				<input name="kfEmp" type="text" size="30" value="${xhIpcApply.kfEmp}" class="required" maxlength="50" />
			</p>
			<p>
				<label>负责信借员：</label>
				<input name="fzEmp" type="text" size="30" value="${xhIpcApply.fzEmp}" class="required" maxlength="50" />
			</p>
			<p>
				<label>维护人员
：</label>
				<input name="whEmp" type="text" size="30" value="${xhIpcApply.whEmp}" class="required" maxlength="50" />
			</p>
		</div>
		<div class="formBar">
			<ul>
			<bjdv:validateContent type="1" funcId="IPC-保存客户">	
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</bjdv:validateContent>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</div>