<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<div class="tabs" currentIndex="0" eventType="click">
<script type="text/javascript">
</script>		
		<div class="tabsContent" >
			<div>
			   <c:if test="${mendian != null}">
			      <b><font color="red">提示：以下内容为门店评分结果，保存后则新增一条信审评分结果(历史数据门店无评分)</font></font></b>  
			   </c:if>
				<form id="jksqShowMsgForm" name="jksqShowMsgForm" method="post"
					action="${ctx}/xhBorrowscore/saveXhBorrowscore" class="pageForm required-validate"
					onsubmit="return validateCallback(this, dialogAjaxDone);">
					<div class="searchBar" layoutH="56">
						<input type="hidden" id="jksqId" name="jksqId" value="${jksq_id }" />
						<input type="hidden" id="employeeId" name="employeeId" value="${employeeId }" />
						<input type="hidden" id="opt" name="opt" /> 
						<input type="hidden" id="id" name="id" value="${score.id }" />
						<input type="hidden" id="scoreType" name="scoreType" value="${scoreType }"/>
						<div class="panel"><h1>个人基本情况
						<input id="countgrjb" type="text" readonly="readonly" size="1" style="border: 0px;color: red;BACKGROUND-COLOR: transparent;text-align: center"   value="0" />
							分
						</h1></div>
						<table>
						<tr>
						<td width="20%">
						<label>年龄：</label> 
						<sen:selectRadio clazz="singlegrjb required" name = "age" coding="gradeAge" 
						 start="0"   split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.age}"  />
							<%-- <sen:select clazz="singlegrjb combox required" 
							name="gradeAge" coding="gradeAge" value=""/> --%>
						</td> 
						</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td width="20%">
						<label>婚姻状况：</label>
						<sen:selectRadio clazz="singlegrjb" name = "marriage" coding="marryState" value="${score.marriage}"
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" />
							<%-- <sen:select clazz="singlegrjb combox required" 
							name="marryState" coding="marryState" value=""/> --%>
							
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td width="20%">
						<label>文化程度：</label>
						<sen:selectRadio clazz="singlegrjb" name = "education" coding="studyLevel" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.education}" />
						<%-- 	<sen:select clazz="singlegrjb combox required" 
							name="studyLevel" coding="studyLevel" value=""/> --%>
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
						<tr>
						<td>
						<label>户口性质：</label>
						<sen:selectRadio clazz="singlegrjb" name = "households" coding="homeNature" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.households}" />
									<%-- <sen:select clazz="singlegrjb combox required" 
									name="homeNature" coding="homeNature" value=""/> --%>
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td>
						<label>工作年龄：</label>
						<sen:selectRadio clazz="singlegrjb required" name = "totalWorkyear" coding="workDate" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.totalWorkyear}" />
									<%-- <sen:select clazz="singlegrjb combox required" 
									name="workDate" coding="workDate" value=""/> --%>
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td>
						<label>健康状况：</label>
						<sen:selectRadio clazz="singlegrjb" name = "healthState" coding="healthState" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.healthState}" />
								<%-- 	<sen:select clazz="singlegrjb combox required" 
									name="healthState" coding="healthState" value=""/> --%>
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
						<tr><td colspan="3" >
						<label>社会保险：</label>
						<!-- 
							<p>
							本市社会职工险：五险一金<input id="shbxData" type="radio" name="shbx" 
							value=""  />
						
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							本市普通社会职工保险<input id="shbxData" type="radio" name="shbx" 
							value=""  />
						
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							人寿保险<input id="shbxData" type="radio" name="shbx" 
							value=""  />
							</p>
						 -->
						 <sen:selectRadio clazz="singlegrjb" name = "socialSecurity" coding="societySafe" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.socialSecurity}" />
						 
								
						
						 <%-- 
							<sen:select clazz="combox required grjb" 
									name="societySafe" coding="societySafe" value=""/> --%>
						</td> 
						</tr>
						 
						 
						 
						</table>
						
						<div class="panel"><h1>个人财产情况
							<input type="text" readonly="readonly" size="1" style="border: 0px;color: red;BACKGROUND-COLOR: transparent;text-align: center"  id="countgrcc" value="0" />
							分
						</h1></div>
						<table>
						<tr><td width="60%">
						<label>&nbsp;住房情况：</label>
						<sen:selectRadio clazz="singlegrcc" name = "house" coding="houseState" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.house}" />
									<%-- <sen:select clazz="singlegrcc combox required" 
									name="houseState" coding="houseState" value=""/> --%>
						</td>
						<td width="10%"></td>
						</tr></table>
						<%-- <p id="all"><label>&nbsp;住房面积：</label>
						<sen:select clazz="singlegrcc combox" 
									name="all" coding="all" value="0"/>70㎡以上
						</p>
						<p id="mortgage"><label>&nbsp;住房面积：</label>
						<sen:select clazz="singlegrcc combox" 
									name="mortgage" coding="mortgage" value="0"/>70㎡以上
						</p>
						<p id="family"><label>&nbsp;住房面积：</label>
						<sen:select clazz="singlegrcc combox" 
									name="family" coding="family" value="0"/>70㎡以上
						</p>
						 --%>
						<div class="divider"></div>
						<p>
						<label>&nbsp;车辆情况：</label>
						<sen:selectRadio clazz="singlegrcc" name = "vechicle" coding="carState" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.vechicle}" />
								<%-- 	<sen:select clazz="singlegrcc combox required"  
									name="carState" coding="carState" value=""/> --%>
						</p>
						<div class="panel"><h1>职业状况
						<input id="countzyzk" type="text" readonly="readonly" size="1" style="border: 0px;color: red;BACKGROUND-COLOR: transparent;text-align: center"  value="0" />
							分
						</h1></div>
						<table>
							
						<tr>
						<td width="20%">
						<label>岗位性质：</label>
						<sen:selectRadio clazz="singlezyzk required" name = "officePosition" coding="postNature" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.officePosition}" />
							<%-- 		<sen:select clazz="singlezyzk combox required" 
									name="postNature" coding="postNature" value=""/> --%>
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td width="20%">
						<label>工作年限：</label>
						<sen:selectRadio clazz="singlezyzk required" name = "officeYear" coding="jobYear" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.officeYear}" />
								<%-- 	<sen:select clazz="singlezyzk combox required" 
									name="jobYear" coding="jobYear" value=""/> --%>
						</td>
							</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td width="20%">
						<label>职业证书：</label>
						<sen:selectRadio clazz="singlezyzk" name = "certification" coding="qualificationsCard" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.certification}" />
								<%-- 	<sen:select clazz="singlezyzk combox required" 
									name="qualificationsCard" coding="qualificationsCard" value=""/> --%>
						</td>	
						</tr>
						</table>
						<div class="divider"></div>
						<table>
							<tr>
						<td>
						<label>个人月收入：</label>
						<sen:selectRadio clazz="singlezyzk required" name = "monthSalary" coding="moneyMonth" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.monthSalary}" />
									<%-- <sen:select clazz="singlezyzk combox required" 
									name="moneyMonth" coding="moneyMonth" value=""/> --%>
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td>
						月供款支出收入比：
						<sen:selectRadio clazz="singlezyzk required" name = "consumePercent" coding="spendMonth" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.consumePercent}" />
						</td>
						</tr>
						</table>
						<div class="divider"></div>
						<table>
							<tr>
								<td colspan="3">
						<label>单位性质：</label>
						 <sen:selectRadio clazz="singlezyzk required" name = "officeType" coding="companyNature" 
						 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.officeType}" />
								<%-- 	<sen:select clazz="combox required zyzk" 
									name="companyNature" coding="companyNature" value=""/> --%>
						</td></tr>
						</table>
						<div class="panel"><h1>其他修正项
						<input id="countqtxz" type="text" readonly="readonly" size="1" style="border: 0px;color: red;BACKGROUND-COLOR: transparent;text-align: center"   value="0" />
							分
						</h1></div>
						<table>
						<tr>
						<td>
						<label>信用记录：</label>
							<sen:selectRadio clazz="singleqtxz" name = "creditRecord" coding="believeTest" 
							 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.creditRecord}" />
									<%-- <sen:select clazz="combox required qtxz" 
									name="believeTest" coding="believeTest" value=""/> --%>
						</td></tr>
						</table>
						<div class="divider"></div>
						<table>
						<tr>
						<td>
						<label>公共记录：</label>
							<sen:selectRadio clazz="singleqtxz" name = "publicRecord" coding="publicTest" 
							 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.publicRecord}" />
									<%-- <sen:select clazz="combox required qtxz" 
									name="publicTest" coding="publicTest" value=""/> --%>
						</td>
							</tr>
						</table>
						<div class="divider"></div>
						<table>
								<tr>
						<td>
						是否为我公司老客户：
							<sen:selectRadio clazz="singleqtxz required" name = "oldCustomer" coding="oldClient" 
							 start="0"  split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${score.oldCustomer}" />
									<%-- <sen:select clazz="combox required qtxz" 
									name="oldClient" coding="oldClient" value=""/> --%>
						</td></tr>
							
						</table>  
						</div>
							<div class="formBar">
								<ul>
									<li>
										<div>
											<div>
												总<input id="zf" name="zf" type="text" readonly="readonly" size="4" style="border: 0px;color: red;BACKGROUND-COLOR: transparent;text-align: center"   value="" />
												分
											</div>
										</div>
									</li>
									
							<c:if test="${look == null}">		
									<li><div  class="buttonActive" >
										<div class="buttonContent">
									    <button type="submit" onclick="return subState(1);">提交</button>
										</div>
									    </div>
									</li>
							</c:if>
									
									<li><div class="button">
											<div class="buttonContent">
												<button type="button" class="close">关闭</button>
											</div>
										</div></li>
								</ul>
							</div>
							
						
					</form>			
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>



</div>

<script>
var zf = 0;
/* $("#all").hide();
$("#mortgage").hide();
$("#family").hide();
$(function(){ //当住房情况选择为：完全房产、按揭房产、亲属房产时显示对应的住房面积选项的js
	$(".zfqk").change(function(){
		var zfqk = parseInt($(this).val());
		if(zfqk == -1){
			$("#zfqk").hide();
			
			$("#all").show();
			$("#mortgage").hide();
			$("#family").hide();
		}else if(zfqk == -2) {
			$("#zfqk").hide();
			
			$("#mortgage").show();
			$("#all").hide();
			$("#family").hide();
		}else if(zfqk == -3) {
			$("#zfqk").hide();
			
			$("#family").show();
			$("#all").hide();
			$("#mortgage").hide();
		}else {
			$("#all").hide();
			$("#mortgage").hide();
			$("#family").hide();
		}
	});
});  
//针对住房单独处理的js
function setZfqk(){
	var sum = 0;
    $(".zfqk:checked").each(function(){
    	score = $(this).val();
		if(score){
			if(parseInt(score)>=0){
				sum += parseInt(score);
			}else {
				sum += 0;
			}
    	}
    });
	$("#countgrcc").val(sum);
}
$(function(){
	var sum = 0;
	$(".zfqk").change(function(){
		setZfqk();
	});
	setZfqk();
}); */
//针对整个select和radio进行评分
function setGrade(module){
	var sum = 0;
	$("select.single"+module).each(function(){
		if($(this).val() != ""){
			sum += parseInt($(this).val());
		}
	});
    $("input.single"+module+":checked").each(function(){
    	score = $(this).val();
    	if(parseInt(score)>=0){
			if(score)
				sum += parseInt(score);
    	}
    });
	$("#count" + module).val(sum);
}
$(function(){
	var sum = 0;
	$("select[class^=single]").change(function(){
		var module;
		var classes = $(this).attr("class").split(" ");
		for(var i = 0 ; i < classes.length ; i++){
			if(classes[i].indexOf('single') == 0){
				module = classes[i].substring(6);
				break;
			}
		}
		setGrade(module);
	});
	$("input[class^=single]").change(function(){
		var classes = $(this).attr("class").split(" ");
		for(var i = 0 ; i < classes.length ; i++){
			if(classes[i].indexOf('single') == 0){
				module = classes[i].substring(6);
				break;
			}
		}
		setGrade(module);
	});
});
//当有评分时，对页面中各项进行赋值
	var grjb = 0;
	var grcc = 0;
	var zyzk = 0;
	var qtxz = 0;
	$(".singlegrjb:checked").each(function(){
		grjb += parseInt($(this).val());
	});
	$("#countgrjb").val(grjb);
	$(".singlegrcc:checked").each(function(){
		grcc += parseInt($(this).val());
	});
	$("#countgrcc").val(grcc);
	$(".singlezyzk:checked").each(function(){
		zyzk += parseInt($(this).val());
	});
	$("#countzyzk").val(zyzk);
	$(".singleqtxz:checked").each(function(){
		qtxz += parseInt($(this).val());
	});
	$("#countqtxz").val(qtxz);
	zf = grjb + grcc + zyzk + qtxz;
	$("#zf").val(zf);
//总分
$(function(){
	var $box = $.pdialog.getCurrent();
	$("[class^=single]",$box).change(function(){
		var grjb = parseInt($("#countgrjb").val());
		var grcc = parseInt($("#countgrcc").val());
		var zyzk = parseInt($("#countzyzk").val());
		var qtxz = parseInt($("#countqtxz").val());
		zf = grjb + grcc + zyzk + qtxz;
		$("#zf",$box).val(zf);
	});
	
	$("#zf",$box).val(zf);
	$("input[type='radio']",$box).addClass("required");
});
</script>