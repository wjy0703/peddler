<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="panel">
<h1>输入放款信息</h1>
	<div class="pageContent">
		<form method="post" action="${ctx}/makeLoans/saveXhMakeLoans/${makeLoans.loanContract.id}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${makeLoans.id}" />

			<div class="pageFormContent" layoutH="56">

				<table>
					<tr>
						<td>
			
				<label>借款人：</label> <input name="htbh" type="text" size="30"
					value="${makeLoans.loanContract.xhJksq.jkrxm }" class="required" readonly/>
			  </td> 
					<td>
			
				<label>合同编号：</label> <input name="htbh" type="text" size="30"
					value="${makeLoans.loanContract.jkhtbm }" class="required" readonly/>
			  </td> 
						
					</tr>
					<tr>

						<td><label>放款金额：</label> <input name="fkje" type="text"
							size="30" value="${makeLoans.loanContract.fkje }" readonly /></td>
						
					</tr>
				</table>
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>放款账户：</label> 
						<input type="hidden" name="middleMan.id" class="textInput"
						 value="${xhMakeLoans.middleMan.id} "
						 /> 
						
						<input type="text"
							class="textInput" name="middleMan.middleManName" size="10"
							value="" class="required" maxlength="22" readonly /><input type="text"
							class="textInput" name="middleMan.cred_addr" size="30"
							value="" class="required" maxlength="22" readonly /> <input type="text"
							class="textInput" name="middleMan.cred_id" size="20"
							value="" class="required" maxlength="22"  readonly/> <a class="btnLook"
							href="${ctx}/jygl/listMiddleMan" lookupGroup="middleMan" >请选择放款账户信息</a></td>
					</tr>
					<tr><td><label>放款日期：</label> <input type="text" name="makeLoanDate" class="date"
						 value="${xhMakeLoans.makeLoanDate}"
						 /> <a class="inputDateButton" href="#">选择</a></td></tr>
					
				</table>

				</p>
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

		
			</div>
			
		</form>
	</div>
</div>