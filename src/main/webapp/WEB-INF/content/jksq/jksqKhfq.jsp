<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" id="jkhtform" name="jkhtform"
			action="${ctx}/jksq/userGiveUpSave"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
					<input type="hidden" id="id" name="id" size="30" value="${jksq.id }" />
				<div class="pageFormContent">
						<table >
							<tr>
								<td><label>借款人姓名：</label> 
									<input type="text" id="jkrxm" name="jkrxm" size="15" value="${jksq.jkrxm }" 
										class="required" readonly="readonly">
								</td>
								<td><label>性别：</label> 
									<input type="text" id="jkrxm" name="genders" size="5" value="${jksq.genders}" 
										class="required" readonly="readonly">
									
								</td>
							</tr>
							<tr>
								<td><label>证件类型：</label> 
								<input type="text" id="jkrxm" name="genders" size="15" value='<sen:vtoName coding="cardType"  

value="${jksq.pocertificates }"/>'
										class="required" readonly="readonly">
								
								</td>
								<td><label>证件号码：</label> 
									<input type="text" id="zjhm" name="zjhm" size="25" class="required" value="${jksq.zjhm }" 

readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td colspan="2"><label>出生日期：</label> 
									<input type="text" name="birthday" 	class="date" 
										format="yyyy-MM-dd" size="15" readonly="readonly" value="${jksq.birthday }" />
										<a class="inputDateButton" href="javascript:;">选择</a>
								</td>
							</tr>
							<tr>
								<td colspan="2"><label>户籍地址：</label> 
									<input type="text" id="hjadress" name="hjadress" size="60" class="required" 

value="${jksq.hjadress }" />
								</td>
							</tr>
							<tr>
								<td colspan="2"><label>现住址:</label> 
									<input type="text" id="homeAddress" name="homeAddress" size="60" class="required" 

value="${jksq.homeAddress }"/>
								</td>
							</tr>
						</table>
							<div class="divider"></div>
			<table >
				<tr>
					<td><label>放弃原因：</label> 
					</td>
					<td>
						<textarea name="mess" style="width: 93%; height: 80" class="required"></textarea>
					</td>
				</tr>
				<tr>
						<td>
						</td>
						<td>
							<span class="info"> (注：客户放弃,放弃后不能恢复请谨慎操作!!!)</span>
						</td>
				</tr>
				</table>
			</div>
			<div class="formBar">
					<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">提交</button>
							</div>
						</div></li>
						<li><div class="button">
								<div class="buttonContent">
									<button type="button" class="close">关闭</button>
								</div>
							</div></li>
					</ul>
				</div>
		</form>
</div>
