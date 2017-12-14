<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="ext/changeCjrxx/changeSingle.js" type="text/javascript"></script>
<div class="panel">
	<div class="pageFormContent">
		<form method="post" action="${ctx}/changeCjrxx/changeCjrxxLeaders"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<h1>基本信息</h1>
				<div class="divider"></div>
				<table>
					<tr>
						<td>
							<label>客户姓名：</label>
							<input name="name" type="text"  size="30" />
							
						</td>
						<td>
							<label>出借编号：</label> 
							<input name="tzsqbh" type="text"  size="30" />	
						</td>
					</tr>
						<tr>
						<td>
							<label>原客户经理：</label> 
							<%--<select id="oldCrmName" name = "dwz.employBefore.name">								
							</select>	--%>
							 <input name="employBefore.name" type="text"  size="30" class="required" readonly="readonly"/>
							<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookAll"
							lookupGroup="employBefore"><hi:text key="查找带回" /></a> 
							<input type="hidden" name="employBefore.id" value="" />	
						</td>
					</tr>
					<tr>
						<td>
							<label>新客户经理：</label> 
							<input name="employeeCrm.name" type="text"  size="30"  class="required" readonly="readonly"/>	
							<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
							lookupGroup="employeeCrm"><hi:text key="查找带回" /></a>
							<input type="hidden" name="employeeCrm.id" value="" />	
						</td>
						<td>
							<label>新团队经理：</label> 
						    <input name="employeeCca.name" type="text"  size="30"  class="required" readonly="readonly"/>	
							<a id="look" class="btnLook" href="${ctx }/baseinfo/emplookup"
							lookupGroup="employeeCca"><hi:text key="查找带回" /></a>
							<input type="hidden" name="employeeCca.id" value="" />	
						</td>
					</tr>	
				</table>

			</div>
			
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div></li>
				</ul>
			</div>


		</form>
	</div>
	
	

</div>