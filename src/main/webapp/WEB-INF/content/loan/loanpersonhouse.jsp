<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form id="loanbaseMsgForm" name="loanbaseMsgForm" method="post"
		action="${ctx}/loan/saveLoanBaseMsg"
		class="pageForm required-validate"
		onsubmit="return loantgFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>借款人姓名:</label> <input id="jkrxm" type="text"
						name="jkrxm" size="30" class="required"></td>
					<td><label>性别:</label> <input id="xb" name="xb" type="radio"
						value="男" class="required" checked="checked" />男&nbsp; <input
						id="xb" name="xb" type="radio" value="女" class="required" />女</td>
					<td><label>年龄：</label> <input id="ywmc" name="ywmc"
						type="text" size="30" value="" /></td>
				</tr>
				<tr>
					<td><label>身份证号：</label> <input id="nl" name="nl" type="text"
						size="30" value="" class="required" /></td>
					<td><label>抵押地址：</label></td>
					<td><label>证件号码：</label> <input id="zjhm" name="zjhm"
						type="text" size="30" value="" class="required" /></td>
				</tr>
				<tr>
					<td><label>户籍地址：</label> <input id="hjszd" name="hjszd"
						type="text" size="30" value="" class="required" /></td>
					<td><label>家庭电话：</label> <input id="xzzdh" name="xzzdh"
						type="text" size="30" value="" class="required" /></td>
					<td><label>工作单位：</label> <input id="gzdwmc" name="gzdwmc"
						type="text" size="30" value="" class="required" /></td>
				</tr>
				<tr>
					<td><label>单位电话：</label> <input id="dwdh" name="dwdh"
						type="text" size="30" value="" class="required" /></td>
					<td><label>单位地址：</label> <input id="dwdz" name="dwdz"
						type="text" size="30" value="" class="required" /></td>
					<td><label>单位性质：</label> <select id="dwxz" name="dwxz">
							<c:forEach items="${dwxz0006}" var="per">
								<option value="${per.value }">${per.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>移动电话：</label> <input id="yddh" name="yddh"
						type="text" size="30" value="" class="required" /></td>
					<td><label>电子邮箱：</label> <input id="dzyx" name="dzyx"
						type="text" size="30" value="" class="required" /></td>
					<td><label>婚姻状况：</label> <select id="hyzk" name="hyzk">
							<c:forEach items="${hyzk0009}" var="per">
								<option value="${per.value }">${per.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>有无子女：</label> <input id="ywzn" name="ywzn"
						type="text" size="30" value="" class="required" /></td>
					<td><label>QQ号码：</label> <input id="qqhm" name="qqhm"
						type="text" size="30" value="" class="required" /></td>
					<td><label>年收入：</label> <input id="nsr" name="nsr" type="text"
						size="30" value="" class="required" /></td>
				</tr>
				<tr>
					<td><label>收入说明：</label> <input id="srsm" name="srsm"
						type="text" size="30" value="" class="required" /></td>
				</tr>
				<tr>
					<td colspan="1"><label>居住状态：</label> <input id="zzzksm"
						name="zzzksm" type="radio" value="女" checked="checked" />自有房屋，有无借款，月供
					</td>
					<td><input id="" name="" type="text" value="" />元</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> <input id="zzzksm"
						name="zzzksm" type="radio" value="女" />租房，房租</td>
					<td><input id="" name="" type="text" value="" />元/月</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> <input id="zzzksm"
						name="zzzksm" type="radio" value="女" />其他，说明：</td>
					<td><input id="xzzdh" name="xzzdh" type="text" size="80"
						value="" /></td>
				</tr>
			</table>
			<br />
			<div id="isMarray">
				以下为配偶信息（借款人已婚：必填）
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>配偶姓名:</label> <input id="poxm" type="text"
							name="poxm" size="30" class="required"></td>
						<td><label>性别：</label> <input id="poxb" name="poxb"
							type="radio" value="男" checked="checked" />男&nbsp; <input
							id="poxb" name="poxb" type="radio" value="女" />女</td>
						<td><label>年龄：</label> <input id="ponl" name="ponl"
							type="text" size="30" value="" /></td>
					</tr>
					<tr>
						<td><label>现住址：</label> <input type="text" id="poxzdz"
							name="poxzdz"></td>
						<td><label>证件类型：</label> <select id="pozjlx" name="pozjlx">
								<!-- 实体没有这个字段 -->
								<c:forEach items="${zjlx0005}" var="per">
									<c:if test="${per.name==pozjlx}">
										<option value="${per.value }" selected="selected">${per.name
											}</option>
									</c:if>
									<c:if test="${per.value!=pozjlx}">
										<option value="${per.value }">${per.name }</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td><label>证件号码：</label> <input id="posfzh" name="posfzh"
							type="text" size="30" value="" /></td>
					</tr>
					<tr>
						<td><label>移动电话：</label> <input id="posj" name="posj"
							type="text" size="30" value="" /></td>
						<td><label>家庭电话：</label> <input id="pojtdh" name="pojtdh"
							type="text" size="30" value="" /></td>
						<td><label>工作单位：</label> <input id="pogzdw" name="pogzdw"
							type="text" size="30" value="" /></td>
					</tr>
					<tr>
						<td><label>部门与职务：</label> <input id="pobmyzw" name="pobmyzw"
							type="text" size="30" value="" /></td>
						<td><label>单位电话：</label> <input id="podwdh" name="podwdh"
							type="text" size="30" value="" /></td>
						<td><label>单位地址：</label> <input id="podwdz" name="podwdz"
							type="text" size="30" value="" /></td>
					</tr>

					<tr>
						<td><label>年收入：</label> <input id="ponsr" name="ponsr"
							type="text" size="30" value="" /></td>
						<td><label>工作年现：</label> <input id="pogzyx" name="pogzyx"
							type="text" size="30" value="" /></td>
					</tr>

				</table>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="ysubmit('0')">暂存</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="ysubmit('1')">提交审批</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="reset" class="reset">清空</button>
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
<script type="text/javascript">
	function ysubmit(val){
		document.loanbaseMsgForm.opt.value = val;
		return true;
	}
	
	
	function loantgFormSubmit(obj){
		var loantgForm = document.getElementById('loanbaseMsgForm');
		var $form=$(loantgForm);
		if(!$form.valid()){
		return false;}

		return validateCallback(loantgForm, navTabAjaxDone);
	}


	
</script>