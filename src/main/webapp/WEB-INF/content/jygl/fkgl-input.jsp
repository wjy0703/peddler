<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/jygl/savefangkuan"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${FangKuangGuanLi.id}"/>
			<input type="hidden" name="middleMan.id" value="${FangKuangGuanLi.middleMan.id}"/>
			<input type="hidden" name="xhJksq.id" value="${xhJksq.id}"/>
			<input type="hidden" id="opt" name="opt" />
			<input type="hidden" name="jkhtbm" type="text" size="30" value="${xhJksq.loanCode}">
		<div class="pageFormContent" layoutH="56">
		<table>
				<tr>
				<td>
			
				<label>借款编码：</label> <input name="jkrbh" type="text" size="30"
					value="${xhJksq.loanCode }" class="required" disabled="disabled"/>
			
			</td>
			<td>
			
				<label>借款人姓名：</label> <input name="jkrxm" type="text" size="30"
					value="${xhJksq.jkrxm }" class="required" disabled="disabled"/>
			
			</td>
			</tr>
			</table>
			<div class="divider"></div>
			<table>
			<tr>
				<!-- <td>
			
				<label>合同编号：</label> <input name="htbh" type="text" size="30"
					value="${FangKuangGuanLi.htbh }" class="required" disabled="disabled"/>
			  </td> -->
			<td>
				<label>放款账户：</label> 
					<input type="text" class="textInput"
						name="middleMan.middleManName" value="${FangKuangGuanLi.middleMan.middleManName}"
						disabled="disabled" size="20" /> <c:if
							test="${FangKuangGuanLi.id == null || FangKuangGuanLi.id == ''}">
							<a class="btnLook" href="${ctx }/jygl/listMiddleMan"
								lookupGroup="middleMan"><hi:text key="查找带回" /></a>
						</c:if>
			</td>
			<td><label>放款账号：</label> <input name="middleMan.cred_id"
						type="text" size="30" value="${FangKuangGuanLi.middleMan.credId }"
						maxlength="40" disabled="disabled" /></td>
			
			</tr>
			<tr>
		
			<td>
				<label>放款金额：</label> <input name="fkje" type="text" size="30"
					value="${FangKuangGuanLi.fkje }" class="required" />
			</td>
			<td>
				<label>放款时间：</label> <input type="text" name="fksj" class="date"
					pattern="yyyy-MM-dd" value="${FangKuangGuanLi.fksj }" size="25" />
				<a class="inputDateButton" href="#">选择</a>
			</td>
			</tr>
			<tr>
			
			<td>
				<label>账户名称：</label> <input name="zhmc" type="text" size="30"
					value="${xhJksq.bankUsername }" class="required" disabled="disabled"/>
			</td>
			<td>
				<label>银行账号：</label> <input name="yhzh" type="text" size="30"
					value="${xhJksq.bankNum }" class="required" disabled="disabled"/>
			</td>
			</tr>
			<tr>
			
			<td>
				<label>账户开户行：</label> <input name="zhkhh" type="text" size="30"
					value="${xhJksq.bankOpen }" class="required" disabled="disabled"/>
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
