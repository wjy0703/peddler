<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/dwz/common/cardNumber.js"> </script>

<script type="text/javascript" src="${ctx}/themes/js/checkCarValue.js"></script>
<div class="panel">
	<div class="pageContent">
	<form method="post" action="${ctx}/xhCarLoanUser/saveXhCarLoanUser" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<input type="hidden" name="id" value="${xhCarLoanUser.id}"/>
		<div class="pageFormContent" layoutH="56">
		<div class="panel">
				<h1>个人资料</h1></div>
					<table class="list" width="100%">
						<tbody>
							<tr height="30px">
								<td><label>姓名：</label>
								</td>
								<td ><input type="text" id="userName" name="userName" value="${xhCarLoanUser.userName}" class="required"></td>
								<td><label>性别：</label>
								</td>
								<td><sen:selectRadio name="genders"  coding="genders" value="${xhCarLoanUser.genders}" clazz="required"/></td>
							</tr>
							<tr height="30px">
								<td><label>学历：</label>
								</td>
								<td ><sen:selectRadio name="educationType" value="${xhCarLoanUser.educationType}" clazz="required"  coding="studyLevel" split="&nbsp;&nbsp;&nbsp;&nbsp;" /></td>
							    <td><label>手机号码：</label></td>
								<td><input type="text" class="required phoneNumber" id="telephone" name="telephone" value="${xhCarLoanUser.telephone}" onblur="vProblemName('${xhCarLoanUser.telephone}','telephone','手机号码');"></td>
							</tr>
							<tr height="30px">
								<input name="pocertificates"  id="pocertificates" value="身份证" type="hidden">
							    <td><label>证件号码：</label>
								</td>
								<td><input type="text" id="cardNumber" name="cardNumber" maxlength="18" class="required" onblur="vProblemName('${xhCarLoanUser.cardNumber}','cardNumber','证件号码');" value="${xhCarLoanUser.cardNumber}" size=30 /></td>
							    <td><label>证件有效期：</label></td>
								<td><input name="expiredDate" readonly="readonly" type="text" class="date required" format="yyyy-MM-dd"  value="${xhCarLoanUser.expiredDate}" class="required" size="17"/>
									<a
									class="inputDateButton" href="javascript:;">选择</a>
								</td>
							</tr>
							<tr height="30px">
								<td><label>出生日期：</label></td>
								<td><input type="text" name="birthday" id="birthday" class="date required" yearstart="-113" yearend="5" format="yyyy-MM-dd" size="17" value="${xhCarLoanUser.birthday}" readonly="readonly" required="required"/> <a
									class="inputDateButton" href="javascript:;">选择</a></td>
									<td><label>暂住证：</label></td>
								<td> <sen:selectRadio clazz="required" name="temporaryCrede" coding="hasChildren" value="${xhCarLoanUser.temporaryCrede}" /></td>
							</tr>
							<tr height="30px">
								<td><label>户籍地址：</label>
								</td>
								<td ><input type="text" id="hjadress" name="hjadress" size="55" value="${xhCarLoanUser.hjadress}" class="required"></td>
								<td colspan="2"></td>
							</tr>
							<tr height="30px">
								<td><label>本市住址：</label>
								</td>
								<td ><input type="text" name="homeAddress" size="55" value="${xhCarLoanUser.homeAddress}" maxlength="100" class="required" />
								</td>
								<td><label>本市住址电话：</label></td>
								<td><input name="homePhone" type="text"  value="${xhCarLoanUser.homePhone}" class="required phoneNumber" maxlength="20" /></td>
							</tr>
							<tr height="30px">
								<td><label>起始居住时间：</label></td>
								<td><input type="text" name="firstLiveDateStart" class="date required" yearstart="-113" yearend="5" format="yyyy-MM-dd" size="17" value="${xhCarLoanUser.firstLiveDateStart}" readonly="readonly" /> <a
									class="inputDateButton" href="javascript:;">选择</a></td>
									<td><label>初来本市年份：</label></td>
								<td><input name="firstComeYear" type="text"  value="${xhCarLoanUser.firstComeYear }" dateFmt="yyyy-MM" class="required date" size="17" readonly="readonly" />
									<a class="inputDateButton" href="javascript:;">选择</a>
								</td>
							<tr height="30px">
								<td><label>供养亲属人数：</label>
								</td>
								<td><input type="text" id="dependentRelatives" name="dependentRelatives" value="${xhCarLoanUser.dependentRelatives}" class="required number"></td>
								<td><label>信用卡额度：</label></td>
								<td><input name="creditLimit" type="text"  value="${xhCarLoanUser.creditLimit }" class="required number" maxlength="20" />万元</td>
							</tr>
							<tr height="30px">
								<td><label>婚姻状况：</label></td>
								<td><sen:selectRadio clazz="hyzk required" name="maritalStatus" value="${xhCarLoanUser.maritalStatus }" coding="car_marriage" /></td>
								<td><label>子女有无：</label></td>
								<td> <sen:selectRadio clazz="required" name="hasChildren" coding="hasChildren" value="${xhCarLoanUser.hasChildren }" /></td>
							</tr>
							<tr height="30px">
								<td colspan="1"><label>房产：</label>
								</td>
								<td colspan="3">
								    <sen:selectRadio name="liveState" value="${xhCarLoanUser.liveState }" split="&nbsp;&nbsp;&nbsp;&nbsp;"  coding="car_livestate" clazz="required"/>
								</td>
							</tr>
							<tr style="display:none;" id="livestate">
							   <td><label>月还款/租金：</label></td>
 								<td colspan="3">  <input type="text" id="liveMonthRent" name="liveMonthRent" value="${xhCarLoanUser.liveMonthRent }" class="number">元
 								</td>
							</tr>
						</tbody>
					</table>
				<div class="divider"></div>
				<div class="panel">
				<h1>工作信息</h1></div>
			      <div class="officeInfo">
				    <table class="list" width="100%">
							  <input type="hidden" name="xhCarUserOffice[0].id" value="${xhCarLoanUser.xhCarUserOffice[0].id }" />
						<tr>
							<td><label>名称：</label>
							</td>
							<td>
						     <input name="xhCarUserOffice[0].name" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].name }" class="required" maxlength="20" size="60" /></td>
							<td><label>所属部门：</label></td>
							<td><input name="xhCarUserOffice[0].department" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].department }" class="required" maxlength="30" /></td>
						</tr>
						<tr>
							<td><label>单位地址：</label>
							</td>
							<td><input name="xhCarUserOffice[0].address" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].address }" class="required" maxlength="200" size="60" /></td>
							<td><label>单位电话：</label></td>
							<td><input name="xhCarUserOffice[0].phone" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].phone }" class="required phoneNumber" maxlength="20" /></td>
						</tr>
						<tr>
							<td><label>职位级别：</label>
							</td>
							<td><input name="xhCarUserOffice[0].duty" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].duty }" class="required" maxlength="20" /></td>
							<td><label>起始服务时间：</label></td>
								<td><input type="text" name="xhCarUserOffice[0].startWorkDate" class="date" yearstart="-113" yearend="5" dateFmt="yyyy-MM" size="17" value="${xhCarLoanUser.xhCarUserOffice[0].startWorkDate }" readonly="readonly" /> <a
									class="inputDateButton" href="javascript:;">选择</a></td>
						</tr>
						<tr>
							<td><label>月基本薪金(元)：</label>
							</td>
							<td><input name="xhCarUserOffice[0].monthSalary" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].monthSalary }" class="number required" maxlength="22" /></td>
							<td><label>其他收入(元)：</label></td>
							<td><input name="xhCarUserOffice[0].bonus" type="text" value="${xhCarLoanUser.xhCarUserOffice[0].bonus }" class="number" maxlength="22" /></td>
						</tr>
						<tr>
						<td><label>单位性质：</label></td>
							<td colspan="5"><sen:selectRadio name="xhCarUserOffice[0].typeh" value="${xhCarLoanUser.xhCarUserOffice[0].typeh }" coding="utilsType" split="&nbsp;&nbsp;&nbsp;&nbsp;" clazz="required" /></td>
						</tr>
				    </table>
				 </div>
				 <div id="office" style="display:none;">
				 <div class="panel companyDiv" >
			   <h1>如申请人为私营业主，请补充以下信息</h1></div>
				<div class="panel">
				    <table class="list" width="100%">
						<tr>
							<td><label>企业类型：</label>
							</td>
							<td>
							<sen:selectRadio name="businessType"  value="${xhCarLoanUser.businessType }" coding="companyType" split="&nbsp;&nbsp;&nbsp;&nbsp;" /></td>
						</tr>
						<tr>
							<td><label>成立时间：</label>
							</td>
							<td><input type="text" id="establishedDate" name="establishedDate" class="date" yearstart="-113" yearend="5" dateFmt="yyyy-MM" size="27" value="${xhCarLoanUser.establishedDate }" readonly="readonly" /> <a
									class="inputDateButton" href="javascript:;" >选择</a></td>
						</tr>
				    </table>
				 </div>
				 </div>
				 <div class="divider"></div>
			<div class="panel">
				<h1>联系人资料</h1>
		</div>
				<div class="panel">
				<div class="panelBar officeInfoHeaderDiv">
					<ul class="toolBar">
						<li><a id="addLxr" class="add addOfficeDiv" href="#" target="">
						<span>添加联系人</span></a>
						</li>						
					</ul>
			 	</div>
			<table id = "contactsTable" class="list" width="100%">
				<thead>
					<tr>
						<th width="3%">
							
						</th>
						<th width="30">姓名</th>
						<th width="40">与本人关系</th>
						<th width="100">单位名称</th>
						<th width="100">家庭住址或单位地址</th>
						<th width="40">手机电话</th>
					</tr>
				</thead>
				<tbody class="lxr">
			 	<c:forEach items="${xhCarLoanUser.xhCarLxr}" var="lxr" varStatus="lxrIndex"> 
					<tr>
						<td>
							<a title="删除"  href="#" class="oracleDeleteLxr btnDel" ajaxTarget = "office" ajaxData="${lxr.id}">删除</a>
					   	 	<input type="hidden" name="xhCarLxr[${lxrIndex.index }].id" value="${lxr.id}" />
					   	 	<input type="hidden" name="xhCarLxr[${lxrIndex.index }].xhCarLoanUser.id" value="${xhCarLoanUser.id}" />
						</td>
						<td align="left"><input type="text" name="xhCarLxr[${lxrIndex.index }].name" value="${lxr.name}" /></td><!-- 姓名 -->
						<td align="left"><input class="ybrgx" type="text" name="xhCarLxr[${lxrIndex.index }].ybrgx" value="${lxr.ybrgx}" /></td><!-- 与本人关系 -->
						<td align="left"><input type="text" name="xhCarLxr[${lxrIndex.index }].jjlxrgzdw"  value="${lxr.jjlxrgzdw}" /></td><!-- 工作单位 -->
						<td align="left"><input type="text" name="xhCarLxr[${lxrIndex.index }].jjlxrdwdzhjtzz" size="50" value="${lxr.jjlxrdwdzhjtzz}" /></td><!-- 住址 -->
						<td align="left"><input type="text" name="xhCarLxr[${lxrIndex.index }].jjlxrlxdh" value="${lxr.jjlxrlxdh}" /></td><!-- 联系电话 --> 
					</tr>
				</c:forEach>
				</tbody>
			</table>
				</div>
				<div class="panel">
				<h1>客户开发及管辖信息</h1>
				</div>
					<div>		<table width="100%">
					<tr>
						<td><label>团队经理：
						</label> </td><td> <input type="text"
							id="empname" 
							name="employeeCca.name" value="${xhCarLoanUser.employeeCca.name }" required="required"
							suggestFields="name,deptname" lookupGroup="employeeCca" readonly />
							<input type="hidden" name="employeeCca.id"
							value="${xhCarLoanUser.employeeCca.id }" />
							<a class="btnLook"
							href="${ctx }/baseinfo/emplookup?code=0002&lookId=${employee.organi.id}&parentFlag=1"
							lookupGroup="employeeCca"><hi:text key="查找带回" /></a> </td>
								<td><label>销售：
						</label> </td><td><input type="text"
							id="empname" 
							name="employeeCrm.name" value="${xhCarLoanUser.employeeCrm.name }" required="required"
							suggestFields="name,deptname" lookupGroup="employeeCrm" readonly
							alt="" /> 
							
							<input type="hidden" name="employeeCrm.id"
							value="${xhCarLoanUser.employeeCrm.id }" /> 
							<a class="btnLook"
							href="${ctx }/baseinfo/emplookup?code=0001&lookId=${employee.organi.id}&code2=0002"
							lookupGroup="employeeCrm"><hi:text key="查找带回" /></a></td>
						<td><label>销售团队：</label></td><td> <input
							name="employeeCrm.deptname" type="text" readonly="readonly" required="required"
							size="20" value="${xhCarLoanUser.kftd }"  maxlength="80" />
							</td>
					</tr>
					<tr height="30px">
					        <td><label>车借客服：</label></td><td> <input
							name="" type="text" readonly="readonly"
							size="20" value="${staffname}" class="required" />
							 <input
							name="employeeService.id" 
							size="20" value="${staffid}" type="hidden" />
							</td>
								<td><label>管辖城市：</label></td>
								<td ><sen:address names="crmprovince,crmcity,crmarea" titles="所属省市,所属城市,所属区县" required="true" values="${xhCarLoanUser.crmprovince},${xhCarLoanUser.crmcity },${xhCarLoanUser.crmarea }" /></td>
					</tr>
					<tr height="30px">
					        <td><label>客户来源：</label></td>
					        <td colspan="3"> 
					        <sen:selectRadio name="knownWay" value="${xhCarLoanUser.knownWay }" coding="customerSource" split="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" clazz="required" />
							</td>
					        <td align="left" ><input  name="backup01" style="display:none;" id="knowWay1" value="${xhCarLoanUser.backup01 }"/></td>
					</tr>
					<tr height="30px">
					        <td><label>客户人法情况：</label></td>
					        <td colspan="5"> 
					        <input
							name="personLawCase" type="text" size="60" value="${xhCarLoanUser.personLawCase }" class="required"/>
							</td>
					</tr>
				</table>
		</div>
		       
			</div>
			<input type="hidden" id="opt" name="opt" />
		<div class="formBar">
			<ul>
			<c:if test="${look == null}">
			<li>
			   <div class="buttonActive"><div class="buttonContent"><button type="submit" id="tempSave">暂存</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="realSubmit">提交</button></div></div></li>
				</c:if>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
<!--
//单位性质为私企时显示企业类型
function spouseExchange($box) {
	var maritalStatus = $('input[name="xhCarUserOffice[0].typeh"]:checked', $box).val();
	var $office = $('#office', $box);
	//var $bussinessType = $('input[name="bussinessType"]',$box);
	var $establishedDate = $('#establishedDate',$box);
	if (maritalStatus == '3') {
		$office.show();
		//$bussinessType.addClass("required");
		$establishedDate.addClass("required");
	} else {
		$office.hide();
		$establishedDate.removeClass();
	}
	$('input[name="xhCarUserOffice[0].typeh"]', $box).change(function() {
			spouseExchange($box);
	});
}

//房产为租用时显示租金
function liveStateExchange($box) {
	var livestate1 = $('input[name="liveState"]:checked', $box).val();
	var $livestate = $('#livestate', $box);
	var $liveMonthRent = $('#liveMonthRent',$box);
	if (livestate1 == '05') {
		$livestate.show();
		$liveMonthRent.addClass("required");
	} else {
		$livestate.hide();
		$liveMonthRent.removeClass();
	}
	$('input[name="liveState"]', $box).change(function() {
		liveStateExchange($box);
	});
}

//客户来源为其他是显示输入信息
function knownWayExchange($box) {
	var knownWay1 = $('input[name="knownWay"]:checked', $box).val();
	var $knownWay = $('#knowWay1', $box);
	if (knownWay1 == '9') {
		$knownWay.show();
	} else {
		$knownWay.hide();
	}
	$('input[name="knownWay"]', $box).change(function() {
		knownWayExchange($box);
	});
}

$(function() {
	var $box = navTab.getCurrentPanel();
	spouseExchange($box);
	liveStateExchange($box);
	knownWayExchange($box);
	
	$('#tempSave',$box).click(function() {

		$("#opt", $box).val('0');
	});

	$('#realSubmit',$box).click(function() {

		$("#opt", $box).val('1');
	})
	
	<c:if test="${look != null}">
    $('input',$box).attr('disabled',true);
    $('select',$box).attr('disabled',true);
</c:if>
});
//-->
</script>
<script type="text/javascript">
//动态添加联系人
$(function(){  
    $("#addLxr").click(function(){  
    	var count = $("tbody.lxr tr").length;
    	$str='';
    	$str+='<tr><td><a title="删除"  href="#" class="btnDel pageLxrDelete">删除</a>';
    	$str+='<input type="hidden" name="xhCarLxr[#lxrIndex].id" value="" /><input type="hidden" name="xhCarLxr[#lxrIndex].xhCarLoanUser.id" value="${xhCarLoanUser.id}" /></td>';
    	$str+='<td align="left"><input type="text" name="xhCarLxr[#lxrIndex].name" errorTitle="联系人姓名" class="required" value="" /></td>\
			  <td align="left"><input class="ybrgx required" errorTitle="与本人关系" type="text" name="xhCarLxr[#lxrIndex].ybrgx" value="" /></td>\
			  <td align="left"><input type="text" errorTitle="联系人工作单位" class="required" name="xhCarLxr[#lxrIndex].jjlxrgzdw"  value="" /></td>\
			  <td align="left"><input type="text"  errorTitle="联系人家庭住址" class="required" name="xhCarLxr[#lxrIndex].jjlxrdwdzhjtzz" value="" size="50"/></td>\
			  <td align="left"><input type="text" errorTitle="联系人联系电话" class="required" name="xhCarLxr[#lxrIndex].jjlxrlxdh" value="" /></td>\
    		  </tr>';
    	var addTrXyk = $str.replace(/#lxrIndex/g,count);
        $(".lxr").append(addTrXyk);   
        var $box = navTab.getCurrentPanel();
        //针对动态添加的新html进行删除
   		$("a.pageLxrDelete",$box).on('click',function(){
   			//删除增加同时引起bug的修复----------
			var $parentTr = $(this).parents('tr');
			var name = $("input:first",$parentTr).attr('name');
			var index = name.substring(name.indexOf('[')+1,name.indexOf(']'));
			var count = $("tbody.lxr tr").length;
			if(count != index+1 ){
				var $last = $("tbody.lxr tr:last");
				$('input',$last).each(function(){
					var $this = $(this);
					var iname = $this.attr('name');
					$this.attr('name',iname.replace(count-1,index));
				});
			}
			//-----------------
			$parentTr.remove();
			return false;
		});
	});
});
//对数据库中已存在的联系人数据通过调用ajax动态删除
$(function() {
    var $box = navTab.getCurrentPanel();

	$("a.oracleDeleteLxr",$box).click(function(){
  		var url = "${ctx}/xhCarLoanUser/oracleDeleteLxr/" + $(this).attr('ajaxData');
  		var $parentTable = $(this).parents('tr');
  		//------------
  		var name = $("input:first",$parentTable).attr('name');
		var index = name.substring(name.indexOf('[')+1,name.indexOf(']'));
		//-----------
  		alertMsg.confirm("<font color='red'>确认删除该信用记录么!!!</font>", {
		okCall: function(){                    
             $.ajax({
					url : url,
					cache : false,
					global : false,
					async : false,
					type : 'post',
					success : function(data) {
						if(data == "1"){
							//----------
							var count = $("tbody.lxr tr").length;
							if(count != index+1 ){
								var $last = $("tbody.lxr tr:last");
								$('input',$last).each(function(){
									var $this = $(this);
									var iname = $this.attr('name');
									$this.attr('name',iname.replace(count-1,index));
								});
							}
							//-----------
						    $parentTable.remove();
						}
					}
            });
        }
  	});
  	return false;
	});
});
</script>
<script type="text/javascript">

/* $(".hyzk:checked").each(function(){
	if($(this).val() == '已婚'){
		$("#addLxr").click();
		var count = $("tbody.lxr tr").length;
		$('.lxr tr:last').find('.ybrgx').val('配偶');
	}
}); */
	
$(function(){
	$('.hyzk').change(function(){
		if($(this).val() == '已婚'){
			$("#addLxr").click();
			var count = $("tbody.lxr tr").length;
			$('.ybrgx').each(function(){
				var a = $(this).val();
				if(a == '配偶'){
					
				}else{
					$('.lxr tr:last').find('.ybrgx').val('配偶');
				}
			});
		}else{
			$('.ybrgx').each(function(){
				var a = $(this).val();
				if(a == '配偶'){
					$(this).parents('tr').find('.btnDel').click();
				}
			});
		}
	});
});

//.click('#addLxr');

</script>
