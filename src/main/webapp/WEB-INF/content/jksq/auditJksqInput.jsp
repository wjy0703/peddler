<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script language="javascript"> 
 
function check(val){
	if(val==1){   
		$("#fwDiv").show();
		$("#clDiv").hide();
		$("#syDiv").hide();
		$("#qyDiv").hide();
	}else if(val==2) {   
		$("#fwDiv").hide();
		$("#clDiv").show();
		$("#syDiv").hide();
		$("#qyDiv").hide();
	} else if(val==3) {   
		$("#fwDiv").hide();
		$("#clDiv").hide();
		$("#syDiv").show();
		$("#qyDiv").hide();
	} else {   
		$("#fwDiv").hide();
		$("#clDiv").hide();
		$("#syDiv").hide();
		$("#qyDiv").show();
	} 
}	
check('1');
</script>
<div class="pageContent">
	<form method="post" action="${ctx}/tzsq/saveTzsq"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>借款人编码：</label> <input type="hidden"
						name="employee.id" value="${dkrxx.employee.id}" /> <input
						type="text" id="empname" class="textInput"
						name="employee.name" value="${dkrxx.employee.name }"
						suggestFields="name,deptname"
						suggestUrl="${ctx }/baseinfo/suggestemployee"
						lookupGroup="employee" /> <a class="btnLook"
						href="${ctx }/baseinfo/emplookup" lookupGroup="employee"><hi:text
								key="查找带回" /></a></td>
					<td><label>借款人姓名：</label> <input name="hy" type="text"
						size="30" value="${dkrxx.jkrxm }" maxlength="40" /></td>
					<td><label>证件号码：</label> <input name="zjhm" type="text"
						size="30" value="${dkrxx.zjhm }" class="required" maxlength="40" />
					</td>
					<td><label>联系电话：</label> <input name="yddh" type="text"
						size="30" value="${dkrxx.yddh }" class="required" maxlength="40" />
					</td>
				</tr>
			</table>
			<div class="divider"></div>
			<table>
				<tr>
					<td><label>申请额度*：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
					<td><label>借款周期*：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
				</tr>

				<tr>
					<td><label>申请日期*：</label> <input name="" type="text" size="30"
						value="" maxlength="80" /></td>
					<td><label>有无共同还款人：</label> <input type="radio" name="gthkr"
						value="1" onClick="check(this.value);" checked>无&nbsp; <input
						type="radio" name="gthkr" value="2" onClick="check(this.value);">有&nbsp;
					</td>
				<tr>
			</table>
			<dl class="nowrap">
				<dt>借款用途：</dt>
				<dd>
					<textarea name="" style="width: 93%; height: 80"></textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>借款类型：</dt>
				<dd>
					<input type="radio" name="jklx" value="1"
						onClick="check(this.value);" checked>房屋质押借款&nbsp; <input
						type="radio" name="jklx" value="2" onClick="check(this.value);">车辆质押借款&nbsp;
					<input type="radio" name="jklx" value="3"
						onClick="check(this.value);">个借（私营业主）&nbsp; <input
						type="radio" name="jklx" value="4" onClick="check(this.value);">个借（企业员工）&nbsp;
				</dd>
			</dl>
			<!-- 房屋质押借款 -->
			<div id="fwDiv">
				<table>
					<tr>
						<td><label>房屋面积*：</label> <input name="" type="text"
							size="30" value="" maxlength="80" /></td>
						<td><label>房屋价值*：</label> <input name="" type="text"
							size="30" value="" maxlength="80" /></td>
						<td><label>有无抵押*：</label> <input name="" type="text"
							size="30" value="" maxlength="80" /></td>
					</tr>

					<tr>
						<td><label>抵押权人*：</label> <input name="" type="text"
							size="30" value="" maxlength="80" /></td>
						<td><label>抵押金额：</label> <input name="" type="text" size="30"
							value="" maxlength="80" /></td>
					</tr>

				</table>
			</div>
			<!-- 车辆质押借款 -->
			<div id="clDiv">
				<table>
					<tr>
						<td><label>车辆牌号*：</label> <input name="" type="text"
							size="20" value="" maxlength="80" /></td>
						<td><label>发动机号*：</label> <input name="" type="text"
							size="20" value="" maxlength="80" /></td>
						<td><label>使用公里*：</label> <input name="" type="text"
							size="10" value="" maxlength="80" /></td>
					</tr>

					<tr>
						<td><label>车辆年限*：</label> <input name="" type="text"
							size="10" value="" maxlength="80" /></td>
						<td><label>抵押金额：</label> <input name="" type="text" size="10"
							value="" maxlength="80" /></td>
					</tr>

				</table>
			</div>
			<!--个借（私营业主） -->
			<div id="syDiv">
				<table>
					<tr>
						<td><label>成立年限*：</label> <input name="" type="text"
							size="20" value="" maxlength="80" /></td>
						<td><label>注册资本*：</label> <input name="" type="text"
							size="20" value="" maxlength="80" /></td>
						<td><label>营业面积*：</label> <input name="" type="text"
							size="10" value="" maxlength="80" /></td>
					</tr>

					<tr>
						<td><label>员工数量 ：</label> <input name="" type="text"
							size="10" value="" maxlength="80" /></td>

					</tr>

				</table>
			</div>
			<!--个借（企业员工） -->
			<div id="qyDiv">
				<table>
					<tr>
						<td><label>部门：</label> <input name="" type="text" size="20"
							value="" maxlength="80" /></td>
						<td><label>职务：</label> <input name="" type="text" size="20"
							value="" maxlength="80" /></td>

					</tr>

				</table>
			</div>
			<dl class="nowrap">
				<dt>放款使用的银行账户*：</dt>
				<dd>
					<table>
						<tr>
							<td>开户行：</td>
							<td><select name="" class="required combox">
									<option value="">请选择</option>
									<option value="0">建设银行</option>
									<option value="1">交通银行</option>
							</select></td>
							<td>卡或折：</td>
							<td><select name="" class="required combox">
									<option value="">请选择</option>
									<option value="0">借记卡</option>
									<option value="1">信用卡</option>
							</select></td>
							<td>具体支行：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td>开户姓名：</td>
							<td><input name="" type="text" size="10" value=""
								maxlength="80" /></td>
							<td>账户：</td>
							<td colspan="3"><input name="" type="text" size="30"
								value="" maxlength="80" /></td>
						</tr>
					</table>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>回款使用的银行账户*：</dt>
				<dd>
					<table>
						<tr>
							<td>开户行：</td>
							<td><select name="" class="required combox">
									<option value="">请选择</option>
									<option value="0">建设银行</option>
									<option value="1">交通银行</option>
							</select></td>
							<td>卡或折：</td>
							<td><select name="" class="required combox">
									<option value="">请选择</option>
									<option value="0">借记卡</option>
									<option value="1">信用卡</option>
							</select></td>
							<td>具体支行：</td>
							<td><input name="" type="text" size="20" value=""
								maxlength="80" /></td>
						</tr>
						<tr>
							<td>开户姓名：</td>
							<td><input name="" type="text" size="10" value=""
								maxlength="80" /></td>
							<td>账户：</td>
							<td colspan="3"><input name="" type="text" size="30"
								value="" maxlength="80" /></td>
						</tr>
					</table>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>申请单原件：</dt>
				<dd>
					<input name="" type="file" size="30" value="" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>审核意见：</dt>
				<dd>
					<textarea name="" style="width: 93%; height: 80"></textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>审核结果：</dt>
				<dd>
					<input type="radio" name="c1" value="2" />通过 <input type="radio"
						name="c1" value="2" />不通过
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
