<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx }/baseinfo/saveTree"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone)">
			<input type="hidden" id="parentId" name="parentId" value="${upOrgani.id }"/>
			<input type="hidden" id="id" name="id" value="${organi.id }"/>
			<div class="pageFormContent" layoutH="55">
				<div class="unit">
					<label>上级机构：</label>
					<input type="text" name="name" size="30" readonly="readonly" value="${upOrgani.rganiName }" />
				</div>
				<div class="unit">
					<label>机构名称：</label>
					<input type="text" name="rganiName" size="30" class="required" value="${organi.rganiName }" ${res }/>
				</div>
				<div class="unit">
					<label>机构编码：</label>
					<input type="text" name="organiCode" size="30" value="${organi.organiCode }" ${res }/>
				</div>
				<div class="unit">
					<label>机构描述：</label>
					<input type="text" name="organiDes" size="30" class="required" value="${organi.organiDes }" ${res }/>
				</div>
				<div class="unit">
					<label>级别描述：</label>
					<input type="text" name="levelMess" size="30" value="${organi.levelMess }" ${res }/>
				</div>
				<div class="unit">
					<label>是否在用：</label>
					<input type="radio" name="organiFlag" value="0" checked="checked" <c:if test='${organi.organiFlag == "" || organi.organiFlag =="0"}'>checked="checked" </c:if>/>是&nbsp;
					<input type="radio" name="organiFlag" value="1" <c:if test='${organi.organiFlag =="1" }'>checked="checked" </c:if>/>否&nbsp;
				</div>
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
	</div>
</div>

