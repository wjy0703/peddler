<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<div class="pageContent">
	<form method="post" action="${ctx}/xhNewJksq/saveLoanTask"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户姓名：</label>
				<input name="customerName" type="text" size="30"/>
			</p>
			<p>
			<span style="color: red">
			注意：如果通过客户姓名变更团队经理、客户经理，原客户经理一定要选择上，防止重名问题。
			</span>
			</p>
			<p>
				<label>团队经理：</label>
				<input type="hidden" name="teamLeaderOld.id"/>
				<input name="teamLeaderOld.name" type="text"  size="30"  class="required" readonly="readonly"/>	
				<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
				lookupGroup="teamLeaderOld"><hi:text key="查找带回" /></a>
			</p>
			<p>
				<label>团队经理（新）：</label> 
				<input type="hidden" name="teamLeaderNew.id"/>
				<input name="teamLeaderNew.name" type="text"  size="30"  class="required" readonly="readonly"/>	
				<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
				lookupGroup="teamLeaderNew"><hi:text key="查找带回" /></a>
			</p>
			<p>
				<label>客户经理：</label> 
				<input type="hidden" name="customerLeaderOld.id"/>
				<input name="customerLeaderOld.name" type="text"  size="30" readonly="readonly"/>	
				<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
				lookupGroup="customerLeaderOld"><hi:text key="查找带回" /></a>
			</p>
			<p>
				<label>客户经理（新）：</label> 
				<input type="hidden" name="customerLeaderNew.id"/>
				<input name="customerLeaderNew.name" type="text"  size="30"  class="required" readonly="readonly"/>	
				<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
				lookupGroup="customerLeaderNew"><hi:text key="查找带回" /></a>
			</p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>交割原因：</dt>
				<dd>
					<textarea name="reasonInfo" style="width: 100%;"></textarea>
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
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div></div>
