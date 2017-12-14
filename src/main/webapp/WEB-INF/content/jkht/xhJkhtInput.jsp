<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(function(){
		var vv = $('#dkll option:selected').val();
		f(vv);
	});

	function f(vv) {
		//alert(value);
		var dkll = vv;	
		
		var yzhf1 = document.getElementById("yzhfl").value;
		var pdje = document.getElementById("pdje").value;
		var hkqs = document.getElementById("hkqs").value;
		var xff = document.getElementById("xff").value;
		var zhglf = document.getElementById("zhglf").value;
		var urgentCreditFee = document.getElementById("urgentCreditFee").value;
		
		var syll = yzhf1 - dkll;
		var syje = pdje * syll * hkqs / 100;
		
		var fwf = syje * 0.41;
		
		var xyshf = syje * 0.08;
		var zxf = syje * 0.51;
		var temp = fwf + xyshf + zxf;
		var htje = parseFloat(temp) + parseFloat(pdje);
		var ybjje = htje / hkqs;
		var ylxje = htje * dkll / 100;
		
		var yhkje = Number(ybjje.toFixed(2)) + Number(ylxje.toFixed(2));
		//alert("ybjje==>"+ybjje+";ylxje==>" + ylxje + ";yhkje==>"+yhkje);
		
		var fkje = pdje - xff-urgentCreditFee;

		
		document.getElementById("ybjje").value = ybjje.toFixed(2);
		document.getElementById("ylxje").value = ylxje.toFixed(2);
		document.getElementById("yhkje").value = yhkje.toFixed(2);
		document.getElementById("syll").value = syll.toFixed(2);
		document.getElementById("syje").value = syje.toFixed(2);
		document.getElementById("fwf").value = fwf.toFixed(2);
		document.getElementById("zxf").value = zxf.toFixed(2);
		document.getElementById("xyshf").value = xyshf.toFixed(2);
		document.getElementById("fkje").value = fkje.toFixed(2);
		document.getElementById("htje").value = htje.toFixed(2);
		

	}
</script>
<div class="panel">
	<h1>审核利率</h1>
	<div class="pageContent">
		<form method="post" id="jkhtform" name="jkhtform"
			action="${ctx}/xhJkht/saveXhJkht" class="pageForm required-validate"
			onsubmit="return jkhtFormSubmit(this);">
			<input type="hidden" name="id" value="${xhJkht.id}" /> <input
				type="hidden" name="xhJksq.id" value="${xhJkht.xhJksq.id}" /> <input
				type="hidden" id="opt" name="opt" />
			<!--  <div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/xhJkht/listJksq"><span>返回列表</span></a></li>
			</ul>		</div>	-->
			<div class="pageFormContent" layoutH="80">
			<table width="100%">
			<tr>
			<td><label>借款人姓名：</label> <input name="jkrxm" type="text" size="30"
						value="${xhJkht.xhJksq.jkrxm}" class="required" maxlength="22"
						readonly /></td>
			<td><label>身份证号码：</label> <input name="jkrxm" type="text" size="30"
						value="${xhJkht.xhJksq.zjhm}" class="required" maxlength="22"
						readonly /></td>
			<td><label>联系电话：</label> <input name="jkrxm" type="text" size="30"
						value="${xhJkht.xhJksq.telephone}" class="required" maxlength="22"
						readonly /></td></tr></table>
				

				<div class="divider"></div>
				<table width="100%">
					<tr>

						<td><label>批借金额：</label> <input id="pdje" name="pdje"
							type="text" size="30" value='<fmt:formatNumber value="${xhJkht.pdje}" pattern="##00.00" />' class="required"
							maxlength="22" readonly /></td>
						<td><label>还款期数：</label> <input id="hkqs" name="hkqs"
							type="text" size="30" value="${xhJkht.hkqs}" class="required"
							maxlength="22" readonly /></td>
							<td><label>综合费率%：</label> <input id="yzhfl" name="yzhfl"
							type="text" size="30" value="${xhJkht.yzhfl}" class="required"
							maxlength="22" readonly /></td>
					</tr>
					<tr>
						

						<td><label>信访费：</label> <input id="xff" name="xff"
							type="text" size="30" value="${xhJkht.xff}" class="required"
							maxlength="22" readonly /></td>
							
						<td><label>加急费：</label> <input id="urgentCreditFee" name="urgentCreditFee"
							type="text" size="30" value="<c:if test="${xhJkht.urgentCreditFee == null || xhJkht.urgentCreditFee == ''}">0</c:if><c:if test="${xhJkht.urgentCreditFee != null && xhJkht.urgentCreditFee != ''}">${xhJkht.urgentCreditFee}</c:if>" 
							class="required"
							maxlength="22" readonly /></td>
					</tr>
					<c:if test="${xhJkht.xhJksq.togetherPerson eq '是'}">
						<tr>
						<td><label>共借人姓名</label><input
							value="<sen:showTogether lendId='${xhJkht.xhJksq.id}' yesOrNo='${xhJkht.xhJksq.togetherPerson}'/>" size="30"  readonly 	class="required"/></td>
						<td><label>共借人身份证号码：</label><input
							value="<sen:showTogetherIdentification lendId='${xhJkht.xhJksq.id}' yesOrNo='${xhJkht.xhJksq.togetherPerson}'/>" size="30"  readonly 	class="required"/></td>
						<td></td>
					</tr></c:if>
				</table>
				<div class="divider"></div>

				<table>
				<tr>
					<td style="color: red"><label>借款利率%：</label> 
						<select   class="combox required" id="dkll" name="dkll" onchange="f(this.value)">
							<option value="0" <c:if test="${xhJkht.dkll==0}">selected</c:if>>请选择</option>
							<c:forEach items="${dkllList}" var="per">
								<option value="${per.value }" <c:if test="${xhJkht.dkll==per.value}">selected</c:if>>${per.name }%</option>
							</c:forEach>
						</select>
						<label></label>
						</td><td>(注：请合同制作人员审核审核利率)</td></tr>
				</table>
				<div class="divider"></div>
				<table width="100%">
					<tr>

						<td><label>实放金额：</label> <input id="fkje" name="fkje"
							type="text" size="30" value="${xhJkht.fkje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>剩余利率%：</label> <input id="syll" name="syll"
							type="text" size="30" value="${xhJkht.syll}" class="required"
							maxlength="22" readonly /></td>
						<td><label>剩余金额：</label> <input id="syje" name="syje"
							type="text" size="30" value="${xhJkht.syje}" class="required"
							maxlength="22" readonly /></td>

					</tr>
					<tr>
						<td><label>信用审核费：</label> <input id="xyshf" name="xyshf"
							type="text" size="30" value="${xhJkht.xyshf}" class="required"
							maxlength="22" readonly /></td>
						<td><label>服务费：</label> <input id="fwf" name="fwf"
							type="text" size="30" value="${xhJkht.fwf}" class="required"
							maxlength="22" readonly /></td>
						<td><label>咨询费：</label> <input id="zxf" name="zxf"
							type="text" size="30" value="${xhJkht.zxf}" class="required"
							maxlength="22" readonly /></td>
					</tr>

					<tr>
						<td><label>合同金额：</label> <input id="htje" name="htje"
							type="text" size="30" value="${xhJkht.htje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>月还本金金额：</label> <input id="ybjje" name="ybjje"
							type="text" size="30" value="${xhJkht.ybjje}" class="required"
							maxlength="22" readonly /></td>
						<td><label>月利息金额：</label> <input id="ylxje" name="ylxje"
							type="text" size="30" value="${xhJkht.ylxje}" class="required"
							maxlength="22" readonly /></td>
					</tr>
					<tr>
						<td><label>实际月还款金额：</label> <input id="yhkje" name="yhkje"
							type="text" size="30" value="${xhJkht.yhkje}" class="required"
							maxlength="22" readonly /></td>

						<td></td>
						<td></td>

					</tr>

					<tr>
						<td><label>月账户管理费：</label> <input id="zhglf" name="zhglf"
							type="text" size="30" value="0" class="required"
							maxlength="22" readonly/></td>

<!--  
						<td><label>起始还款日期：</label> <input name="qshkrq"  id="qshkrq" 

type="text"
							size="25" value="${xhJkht.qshkrq}" class="required date"
							maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>
						

						<td><label>合同签订日期：</label> <input name="qdrq" type="text"
							size="25" value="${xhJkht.qdrq}" class="required date"
							maxlength="20" /> <a class="inputDateButton" href="#">选择</a></td>

					</tr>

					<tr>
						<td><label>出借人：</label> <input type="text"
							class="required" name="middleMan.middleManName" size="10"
							value="${xhJkht.middleMan.middleManName}"   readonly

maxlength="22" /> <a class="btnLook"
							href="${ctx}/jygl/listMiddleMan" lookupGroup="middleMan">请选择放款

账户信息</a>
<input type="hidden"
							name="middleMan.id" size="10" 

value="${xhJkht.middleMan.id}"
							maxlength="22"  /> 
						</td>
						<td><label>借款合同编号：</label> <input name="jkhtbm" type="text" size="30"
						value="${xhJkht.jkhtbm}" class="required" maxlength="20"  /></td>
						<td><label>还款付息方式：</label> 
						
						<select   class="combox required" id="rePayType" name="rePayType" >
							<option value="" >请选择</option>
							<c:forEach items="${rePayTypeList}" var="rePayType">
								<option value="${rePayType.value }">${rePayType.name }</option>
							</c:forEach>
						</select>
						</td>
						-->
					</tr>
				</table>
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
								<button type="submit" onclick="ysubmit('1')">提交</button>
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
</div>
<script type="text/javascript">
function getLastDay(year,month)   
{   
    var new_year = year;    //取当前的年份          
    var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）          
    if(month>12) {         
     new_month -=12;        //月份减          
     new_year++;            //年份增          
    }         
    var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天  


    return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期   
}
 
	function ysubmit(val) {
		
		document.jkhtform.opt.value = val;
		return true;
	}
	function jkhtFormSubmit(obj){
		
		var $form=$(obj);
		if(!$form.valid()){
			return false;
		}
		var dkll = document.jkhtform.dkll.value;
		var yzhfl = document.jkhtform.yzhfl.value;
		if (dkll>yzhfl || dkll<0){
			alertMsg.warn("借款利率不符合范围");
			return false;
		}
		/*
		var qshkrq = document.jkhtform.qshkrq.value;
		var aQshkrq=qshkrq.split("-");
		if (aQshkrq[1]=="02"){
			if (getLastDay(aQshkrq[0],aQshkrq[1])!=aQshkrq[2]){
				alertMsg.warn("起始还款日期为2月份时，只能选择2月15日或最后一天");
				return false;
			}
		}else{
			if (aQshkrq[2]!="15" && aQshkrq[2]!="30"){
				alertMsg.warn("起始还款日期只能选择15日或30日");
				return false;
			}
		}
		*/
		//return false;
		
		return validateCallback(obj, navTabAjaxDone);
	}
</script>
