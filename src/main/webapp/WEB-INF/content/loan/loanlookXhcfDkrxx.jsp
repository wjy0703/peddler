<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form id="lookloanbaseMsgForm" name="lookloanbaseMsgForm" method="post"
		action="${ctx}/loan/saveLoanBaseMsg"
		class="pageForm required-validate"
		onsubmit="return loantgFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
					<td><label>借款人姓名:</label> <input id="jkrxm" type="text"
						name="jkrxm" size="30" value="${xhcfDkrxx.jkrxm }"
						readonly="readonly"></td>
					<td><label>英文名:</label> <input id="ywmc" name="ywmc"
						type="text" size="30" value="${xhcfDkrxx.ywmc }"
						readonly="readonly" /></td>
					<td><label>性别：</label> <input id="xb" name="xb" type="text"
						size="30" value="${xhcfDkrxx.xb }" readonly="readonly" /></td>
				</tr>
				<tr>
					<td><label>年龄：</label> <input type="text" name="csrq"
						class="date required" value="${xhcfDkrxx.csrq }"
						pattern="yyyy-MM-dd" size="30" readonly="readonly" /></td>
					<td><label>证件类型：</label> <select id="zjlx" name="zjlx">
							<c:forEach items="${zjlx0005}" var="per">
								<c:if test="${per.name==xhcfDkrxx.zjlx}">
									<option value="${per.value }" selected="selected">${per.name
										}</option>
								</c:if>
								<c:if test="${per.value!=xhcfDkrxx.zjlx}">
									<option value="${per.value }">${per.name }</option>
								</c:if>
							</c:forEach>
					</select></td>
					<td><label>证件号码：</label> <input id="zjhm" name="zjhm"
						type="text" size="30" value="${xhcfDkrxx.zjhm }"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td><label>户籍地址：</label> <input id="hjszd" name="hjszd"
						type="text" size="30" value="${xhcfDkrxx.hjszd }"
						readonly="readonly" /></td>
					<td><label>家庭电话：</label> <input id="xzzdh" name="xzzdh"
						type="text" size="30" value="${xhcfDkrxx.xzzdh }"
						readonly="readonly" /></td>
					<td><label>工作单位：</label> <input id="gzdwmc" name="gzdwmc"
						type="text" size="30" value="${xhcfDkrxx.gzdwmc }"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td><label>单位电话：</label> <input id="dwdh" name="dwdh"
						type="text" size="30" value="${xhcfDkrxx.dwdh }"
						readonly="readonly" /></td>
					<td><label>单位地址：</label> <input id="dwdz" name="dwdz"
						type="text" size="30" value="${xhcfDkrxx.dwdz }"
						readonly="readonly"></td>
					<td><label>单位性质：</label> <select id="dwxz" name="dwxz">
							<c:forEach items="${dwxz0006}" var="per">
								<c:if test="${per.name==xhcfDkrxx.dwxz}">
									<option value="${per.value }" selected="selected">${per.name
										}</option>
								</c:if>
								<c:if test="${per.value!=xhcfDkrxx.dwxz}">
									<option value="${per.value }">${per.name }</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>移动电话：</label> <input id="yddh" name="yddh"
						type="text" size="30" value="${xhcfDkrxx.yddh }"
						readonly="readonly" class="required" /></td>
					<td><label>电子邮箱：</label> <input id="dzyx" name="dzyx"
						type="text" size="30" value="${xhcfDkrxx.yddh }"
						readonly="readonly" class="required" /></td>
					<td><label>婚姻状况：</label> <select id="hyzk" name="hyzk">
							<c:forEach items="${hyzk0009}" var="per">
								<c:if test="${per.name==xhcfDkrxx.hyzk}">
									<option value="${per.value }" selected="selected">${per.name
										}</option>
								</c:if>
								<c:if test="${per.value!=xhcfDkrxx.hyzk}">
									<option value="${per.value }">${per.name }</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>有无子女：</label> <input id="ywzn" name="ywzn"
						type="text" size="30" value="${xhcfDkrxx.ywzn }"
						readonly="readonly" class="required" /></td>
					<td><label>QQ号码：</label> <input id="qqhm" name="qqhm"
						type="text" size="30" value="${xhcfDkrxx.qqhm }"
						readonly="readonly" class="required" /></td>
					<td><label>年收入：</label> <input id="nsr" name="nsr" type="text"
						size="30" value="${xhcfDkrxx.nsr }" readonly="readonly"
						class="required" /></td>
				</tr>
				<tr>
					<td><label>收入说明：</label> <input id="srsm" name="srsm"
						type="text" size="30" value="${xhcfDkrxx.srsm }"
						readonly="readonly" class="required" /></td>
				</tr>
				<tr>
					<td colspan="1"><label>居住状态：</label> <c:if
							test='${"01"==xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="01"
								checked="checked" />自有房屋，有无借款，月供
				</c:if> <c:if test='${"01"!=xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="01" />自有房屋，有无借款，月供
				</c:if></td>
					<td><input id="liveMessage01" name="liveMessage" type="text"
						value="" />元</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> <c:if
							test='${"02"==xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="02"
								checked="checked" />亲属产权
				</c:if> <c:if test='${"02"!=xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="02" />亲属产权
				</c:if></td>
					<td><input id="liveMessage02" name="liveMessage" type="hidden"
						value="" /></td>
				</tr>
				<tr>
					<td colspan="1"><label></label> <c:if
							test='${"03"==xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="03"
								checked="checked" />租房，房租
				</c:if> <c:if test='${"03"!=xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="03" />租房，房租
				</c:if></td>
					<td><input id="liveMessage03" name="liveMessage" type="text"
						value="" />元/月</td>
				</tr>
				<tr>
					<td colspan="1"><label></label> <c:if
							test='${"04"==xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="04"
								checked="checked" />其他，说明：
				</c:if> <c:if test='${"04"!=xhcfDkrxx.liveState}'>
							<input id="liveState" name="liveState" type="radio" value="04" />其他，说明：
				</c:if></td>
					<td><input id="liveMessage04" name="liveMessage" type="text"
						size="80" value="" /></td>
				</tr>
			</table>
			<br />
			<div id="isMarray">
				以下为配偶信息（借款人已婚：必填）
				<div class="divider"></div>
				<table>
					<tr>
						<td><label>配偶姓名:</label> <input id="poxm" type="text"
							name="poxm" size="30" value="${xhcfDkrxx.poxm }"
							readonly="readonly" /></td>
						<td><label>性别：</label> ${xhcfDkrxx.poxb }</td>
						<td><label>出生日期：</label> <input type="text" name="pocsrq"
							class="date required" value="${xhcfDkrxx.pocsrq }"
							pattern="yyyy-MM-dd" size="30" readonly="readonly" /></td>
					</tr>
					<tr>
						<td><label>现住址：</label> <input type="text" id="poxzdz"
							name="poxzdz" value="${xhcfDkrxx.ponl }" readonly="readonly">
						</td>
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
							type="text" size="30" value="${xhcfDkrxx.posfzh }"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td><label>移动电话：</label> <input id="posj" name="posj"
							type="text" size="30" value="${xhcfDkrxx.posj }"
							readonly="readonly" /></td>
						<td><label>家庭电话：</label> <input id="pojtdh" name="pojtdh"
							type="text" size="30" value="${xhcfDkrxx.pojtdh }"
							readonly="readonly" /></td>
						<td><label>工作单位：</label> <input id="pogzdw" name="pogzdw"
							type="text" size="30" value="${xhcfDkrxx.pogzdw }"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td><label>部门与职务：</label> <input id="pobmyzw" name="pobmyzw"
							type="text" size="30" value="${xhcfDkrxx.pobmyzw }"
							readonly="readonly" /></td>
						<td><label>单位电话：</label> <input id="podwdh" name="podwdh"
							type="text" size="30" value="${xhcfDkrxx.podwdh }"
							readonly="readonly" /></td>
						<td><label>单位地址：</label> <input id="podwdz" name="podwdz"
							type="text" size="30" value="${xhcfDkrxx.podwdz }"
							readonly="readonly" /></td>
					</tr>

					<tr>
						<td><label>年收入：</label> <input id="ponsr" name="ponsr"
							type="text" size="30" value="${xhcfDkrxx.ponsr }"
							readonly="readonly" /></td>
						<td><label>工作年现：</label> <input id="pogzyx" name="pogzyx"
							type="text" size="30" value="${xhcfDkrxx.pogzyx }"
							readonly="readonly" /></td>
					</tr>

				</table>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!-- 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="ysubmit('0')">暂存</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="ysubmit('1')">提交审批</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="reset" class="reset">清空</button></div></div></li>
			 -->
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

	function initLoanLookXhcfDkrxx(){
		var liveStateArray = document.lookloanbaseMsgForm.liveState;
		var liveState = "";
		 for(var i=0;i<liveStateArray.length;i++){
             if(liveStateArray[i].checked){
            	 liveState = liveStateArray[i].value;
                 break;
             }
         }

		if("01" == liveState){
			document.lookloanbaseMsgForm.liveMessage01.value = "${xhcfDkrxx.liveMessage }";
		}else if("02" == liveState){
			document.lookloanbaseMsgForm.liveMessage02.value = "${xhcfDkrxx.liveMessage }";
		}else if("03" == liveState){
			document.lookloanbaseMsgForm.liveMessage03.value = "${xhcfDkrxx.liveMessage }";
		}else if("04" == liveState){
			document.lookloanbaseMsgForm.liveMessage04.value = "${xhcfDkrxx.liveMessage }";
		}
	}
	initLoanLookXhcfDkrxx();
</script>