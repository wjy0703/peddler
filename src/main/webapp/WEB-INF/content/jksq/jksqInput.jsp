<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="panel">
<div class="pageContent">
	<form id="jksqbaseMsgForm" name="jksqbaseMsgForm" method="post"
		action="${ctx}/jksq/saveJksqBaseMsg"
		class="pageForm required-validate"
		onsubmit="return loantgFormSubmit(this);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" id="xydkzxId" name="xydkzxId" value="${xydkzx.id }" />
			<input type="hidden" id="opt" name="opt" />
			<div class="panel"><h1>客户基本信息</h1></div>
			
			<table nowrapTD="false" width="100%">
				<tr>
					<td><label>借款人姓名:</label> 
						<input type="text" id="jkrxm" name="jkrxm" size="30" value="${xydkzx.khmc }" class="required" readonly="readonly">
					</td>
					
					<td><label>证件类型：</label> 
					   <sen:select clazz="combox required" name="pocertificates" coding="cardType" id="pocertificates" value="身份证"/>
					</td>
					<td><label>证件号码：</label> 
						<input type="text" id="zjhm" name="zjhm" size="30" class="required" onblur="isZjhm(this.value)" />
						<a id="isZjhm" class="btnLook" href="${ctx }/zxgl/loadIdInfo" lookupGroup="isZjhm" style="display: none" title="该证件信息已存在，请核对是否符合进件条件！"></a>
					</td>
				</tr>
				<tr>
					<td><label>性别：</label> 
						<input type="radio" id="genders" name="genders" value="男"  class="radio" 
							<c:if test="${xydkzx.sex=='0'}">checked="checked" </c:if>  
						 />男&nbsp; 
						<input type="radio" id="genders" name="genders" value="女"  
							<c:if test="${xydkzx.sex=='1'}">checked="checked" </c:if> 
						class="radio" />女
					</td>
					<td><label>出生日期：</label> 
						<input type="text" name="birthday" 	class="date " 
							yearstart="-113" yearend="5" 
							format="yyyy-MM-dd" size="27" readonly="readonly" />
						<a class="inputDateButton" href="javascript:;">选择</a>
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>户籍地址：</label> 
						<input type="text" id="hjadress" name="hjadress" size="60" class="required" value="" />
					</td>
				</tr>
				<tr>
				<td colspan="3"><label>现住址:</label> 
						
					<input type="text" id="homeAddress" name="homeAddress" size="60"class="required"/></td>
				</tr>
				</table>
					<div class="divider"></div>
				<table width="100%">
				<tr>
					
					<td><label>家庭电话：</label> 
						<input type="text" id="homePhone" name="homePhone" size="30" class="" value="${xydkzx.telephone }" />
					</td>
					<td><label>工作单位：</label> 
						<input type="text" id="company" name="company" size="30" class="" value="" />
					</td>
				</tr>
				<tr>
					<td><label>单位电话：</label> 
						<input type="text" id="companyPhone" name="companyPhone" size="30" class="" value="" />
					</td>
					<td><label>单位地址：</label> 
						<input type="text" id="companyAdress" name="companyAdress" size="30" class="" value="" />
					</td>
					<td><label>单位性质：</label> 
					    <sen:select clazz="combox" name="companyNature" coding="officeType" id="companyNature"/>
					</td>
				</tr>
				<tr>
					<td><label>移动电话：</label> 
						<input type="text" id="telephone" name="telephone" size="30" class="required" value="${xydkzx.phone }" />
					</td>
					<td><label>电子邮箱：</label> 
						<input type="text" id="email" name="email" size="30" class="" value="" />
					</td>
					<td><label>婚姻状况：</label> 
					    <sen:select clazz="required combox" name="maritalStatus" coding="marriageType" id="maritalStatus"/>
					</td>
				</tr>
				<tr>
					<td><label>有无子女：</label> 
					    <sen:select clazz="combox" name="ywzn" coding="hasChildren" id="ywzn"/>
					</td>
					<td><label>QQ号码：</label> 
						<input type="text" id="qqhm" name="qqhm" size="30" value="" />
					</td>
					<td ><label>月收入(元)：</label> 
						<input type="text" id="annualIncome" name="annualIncome" 
							size="10" class="number" value="" /><span class="info"> (数字格式,无千分位)</span>
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>收入说明：</label> 
						<input type="text" id="incomeIllustration" name="incomeIllustration" size="60" value="" />
					</td>
				</tr>
				</table>
				<div class="divider"></div>
				<table>
					<tr>
						<td colspan="1"><label>居住状态：</label> <input type="radio" id="liveState" name="liveState" value="01" checked="checked"  />自有房屋，有无借款，月供</td>
						<td><input id="liveMessage01" name="liveMessage" type="text" value="" /><label>元</label></td>
					</tr>
					<tr>
						<td colspan="1"><label></label> <input type="radio" id="liveState" name="liveState" value="02"  />亲属产权</td>
						<td><input type="hidden" id="liveMessage02" name="liveMessage" value="" />
						</td>
					</tr>
					<tr>
						<td colspan="1"><label></label> <input type="radio" id="liveState" name="liveState" value="03"  />租房，房租</td>
						<td><input type="text" id="liveMessage03" name="liveMessage" value="" />元/月</td>
					</tr>
					<tr>
						<td colspan="1"><label></label> <input type="radio" id="liveState" name="liveState" value="04"  />其他,说明：</td>
						<td><input type="text" id="liveMessage04" name="liveMessage" size="40" value="" /></td>
					</tr>
					<tr>
					<td colspan="2">
						<dl class="nowrap">
							<dt>通讯地址：</dt>
							<dd>
							    <sen:address names="province.id,city.id,area.id" titles="所属省市,所属城市,所有区县" required="true" values="${jksq.province.id},${jksq.city.id},${jksq.area.id}"/>
							<input type="text" name="txdz" size="80" value="${jksq.txdz }" maxlength="100" /></dd>
						</dl>
					</td>
				</tr>
			</table>
			<br />
			
			<div id="isMarrayInput" style="display:block;">
				<div class="panel"><h1>以下为配偶信息（借款人婚姻状况为已婚时必填）</h1></div>
				
				<table width="100%">
					<tr>
						<td><label>姓名:</label> 
							<input type="text" id="spouseName" name="spouseName" size="30" class="required spouse"/>
						</td>
						<td><label>性别：</label> 
							<input type="radio" id="spouseGenders1" 
								name="spouseGenders" value="男" 
								<c:if test="${xydkzx.sex=='1'}">checked="checked" </c:if>
								class="radio spouse" />男&nbsp; 
							<input type="radio" id="spouseGenders2" 
								<c:if test="${xydkzx.sex=='0'}">checked="checked" </c:if>
								name="spouseGenders" value="女" class="radio spouse" />女
						</td>
						<td><label>出生日期：</label> 
							<input type="text" name="spouseBirthday" class="date spouse" 
								yearstart="-113" yearend="5" format="yyyy-MM-dd" 
								pattern="yyyy-MM-dd" size="27" readonly="readonly" />
								<a class="inputDateButton" href="javascript:;" id="spouRiqiInput" name="spouRiqiInput">选择</a>
						</td>
					</tr>
					<tr>
						<td><label>现住址：</label> 
							<input type="text" id="spouseAdress" name="spouseAdress" size="30" class="spouse"/>
						</td>
						<td><label>证件类型：</label>
						 <sen:select clazz="combox spouse" name="spousePocertificates" coding="cardType" id="spousePocertificates"/> 
						</td>
						<td><label>证件号码：</label> 
							<input type="text" class="spouse" id="spouseZjhm" name="spouseZjhm" size="30" value=""  />
						</td>
					</tr>
					<tr>
						<td><label>移动电话：</label> 
							<input type="text" class="spouse" id="spouseTelephone" name="spouseTelephone" size="30" value=""  />
						</td>
						<td><label>家庭电话：</label> 
							<input type="text" class="spouse" id="spouseHomePhone" name="spouseHomePhone" size="30" value=""  />
						</td>
						<td><label>工作单位：</label> 
							<input type="text" class="spouse" id="spouseCompany" name="spouseCompany" size="30" value=""  />
						</td>
					</tr>
					<tr>
						<td><label>部门与职务：</label> 
							<input type="text" id="spouseDepFunction" class="spouse" name="spouseDepFunction" size="30" value=""  />
						</td>
						<td><label>单位电话：</label> 
							<input type="text" id="spouseCompanyPhone" class="spouse" name="spouseCompanyPhone" size="30" value=""  />
						</td>
						<td><label>单位地址：</label> 
							<input type="text" id="spouseCompanyAdress" class="spouse" name="spouseCompanyAdress" size="30" value=""  />
						</td>
					</tr>

					<tr>
						<td><label>月收入(元)：</label> 
							<input type="text" id="spouseAnnualIncome" name="spouseAnnualIncome"  size="10" value=""  class="number spouse" />
							<span class="info"> (数字格式,无千分位)</span>
						</td>
						<td><label>服务年限(年)：</label> 
							<input type="text" id="spouseWorkinglife" name="spouseWorkinglife" 	size="10" value="" minlength="1" class="digits spouse" maxlength="3"  />
							<span class="info"> (数字格式)</span>
						</td>
					</tr>
				</table>
			</div>
		
			<div class="panel"><h1>紧急联系人信息</h1></div>
		
			<table class="table" width="98%">
				<thead>
					<tr>
						<th width="60">联系人</th>
						<th width="60">姓名</th>
						<th width="90">工作单位</th>
						<th width="100">单位地址或家庭地址</th>
						<th width="80">联系电话</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">亲属<input type="hidden" name="ybrgx1" value="亲属"></td>
						<td align="left"><input type="text" name="name1" ></td>
						<td align="left"><input type="text" name="jjlxrgzdw1"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz1"></td>
						<td align="left"><input type="text" name="jjlxrlxdh1" class="digits"></td>
					</tr>
					<tr>
						<td align="center">亲属<input type="hidden" name="ybrgx2" value="亲属"></td>
						<td align="left"><input type="text" name="name2"></td>
						<td align="left"><input type="text" name="jjlxrgzdw2"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz2"></td>
						<td align="left"><input type="text" name="jjlxrlxdh2" class="digits"></td>
					</tr>
					<tr>
						<td align="center">朋友<input type="hidden" name="ybrgx3" value="朋友"></td>
						<td align="left"><input type="text" name="name3"></td>
						<td align="left"><input type="text" name="jjlxrgzdw3"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz3"></td>
						<td align="left"><input type="text" name="jjlxrlxdh3" class="digits"></td>
					</tr>
					<tr>
						<td align="center">朋友<input type="hidden" name="ybrgx4" value="朋友"></td>
						<td align="left"><input type="text" name="name4"></td>
						<td align="left"><input type="text" name="jjlxrgzdw4"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz4"></td>
						<td align="left"><input type="text" name="jjlxrlxdh4" class="digits"></td>
					</tr>
					<tr>
						<td align="center">同事<input type="hidden" name="ybrgx5" value="同事"></td>
						<td align="left"><input type="text" name="name5"></td>
						<td align="left"><input type="text" name="jjlxrgzdw5"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz5"></td>
						<td align="left"><input type="text" name="jjlxrlxdh5" class="digits"></td>
					</tr>
					<tr>
						<td align="center">同事<input type="hidden" name="ybrgx6" value="同事"></td>
						<td align="left"><input type="text" name="name6"></td>
						<td align="left"><input type="text" name="jjlxrgzdw6"></td>
						<td align="left"><input type="text" name="jjlxrdwdzhjtzz6"></td>
						<td align="left"><input type="text" name="jjlxrlxdh6" class="digits"></td>
					</tr>
				</tbody>
			</table>
			
			<div class="panel"><h1>借款信息</h1></div>
			
			<table border="0" bordercolor="red" width="100%">
				<tr>
					<td><label>借款申请额度(元):</label> 
						<input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" value="${xydkzx.planAmount }" class="required number">
						<span class="info"> (必填,无千分位)</span>
					</td>
					<td><label>申请借款期限(月):</label> 
						<input type="text" id="jkCycle" name="jkCycle" size="10" 
							class="required digits" value="" /><span class="info"> (必填,整数)</span>
					</td>
					<td><label>还款方式：</label> 
					    <sen:select clazz="required combox" name="hkWay" coding="loanReturnType" id="hkWay"/>
						<span class="info"> (必填)</span>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<dl >
							<dt>借款用途：</dt>
							<dd>
							    <sen:select clazz="required combox" name="jkUse" coding="loanUseType" id="jkUse"/>
							</dd>
						</dl>
					</td>
					<td><label>申请日期：</label> 
						<input type="text" id="jkLoanDate" name="jkLoanDate" class="date required" 
							pattern="yyyy-MM-dd" 
							format="yyyy-MM-dd" size="27" readonly="readonly" />
						<a class="inputDateButton" href="javascript:;">选择</a>
					</td>
					<td><label>共同还款人：</label> 
						<input type="radio" id="togetherPerson" name="togetherPerson" value="否" checked="checked"/>无&nbsp;
						<input type="radio" id="togetherPerson" name="togetherPerson" value="是"  />有&nbsp;
					</td>
				</tr>
				<tr>
					<td><label>是否加急：</label>
						<input type="radio" id="englishName" name="englishName" value="否" checked="checked"/>否&nbsp;
						<input type="radio" id="englishName" name="englishName" value="是" />是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="3"><label>借款类型：</label>
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="A"  class = "required"/>老板借&nbsp;
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="B"  class = "required"/>老板楼易借&nbsp; 
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="C"  class = "required"/>薪水借&nbsp;
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="D"  class = "required"/>薪水楼易借&nbsp; 
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="E"  class = "required"/>精英借&nbsp;
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="Q"  class = "required"/>企业借&nbsp;
						<input errorTitle="借款类型" type="radio" name="jkType" class="jkType required" value="W"  class = "required"/>简易楼易借&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="3">
					    <!-- 老板借 -->
						<div id="productTypeA" name="lbd_div" >
							${lbd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						 <!-- 老板楼易借 -->
						<div id="productTypeB" name="lblyd_div">
							${lblyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					    <!-- 薪水借 -->
						<div id="productTypeC" name="xsd_div" >
							${xsd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					   <!-- 薪水楼易借 -->
						<div id="productTypeD" name="xslyd_div" >
							${xslyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					 <!-- 精英借款 -->
						<div id="productTypeE" name="jyd_div" >
							${jyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<!-- 企业借款 -->
						<div id="productTypeQ" name="qyd_div" >
							${qyd }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<!-- 简易楼易借 -->
						<div id="productTypeW" name="jylyd_div">
							${jylyd }
						</div>
					</td>
				</tr>
				</table>
	<div class="divider"></div>
	<table>
				<tr>
					<td colspan="2"><label>开户行：</label>
						<input type="text" id="bankOpen" name="bankOpen" class="required" size="80"/>
					</td>
					
				</tr>
				<tr>
				<td><label>账户号码：</label> 
						<input type="text" id="bankNum" name="bankNum" class="required digits" size="30"/>
					</td>
				</tr>
				<tr>
					<td><label>账户名称：</label> 
						<input type="text" id="bankUsername" name="bankUsername" class="required" size="30"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<dl class="nowrap">
							<dt>备注：</dt>
							<dd>
								<textarea id="backup09" name="backup09" rows="4" style="width: 93%;"></textarea>
							</dd>
						</dl>
					</td>
				</tr>
			</table>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div  class="buttonActive" >
						<div class="buttonContent">

							<button type="submit" id = "tempSave">暂存</button>
						</div>
					</div></li>
				<li><div  class="buttonActive" >
						<div class="buttonContent">
							<button type="submit" id = "realSubmit">提交</button>
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
</div>
<script type="text/javascript" src="${ctx}/ext/indentification/idTransfer.js"></script>
<script type="text/javascript">
   
	function spouseExchange(){
		var $box = navTab.getCurrentPanel();
		var $spouse = $(".spouse",$box);
		var $maritalStatus = $('#maritalStatus',$box);
		var $isMarrayInput = $('#isMarrayInput',$box);
		$spouse.attr('disabled',$maritalStatus.val() != '已婚');
		if( $maritalStatus.val() != '已婚'){
		    $spouse.removeClass('error');
			$isMarrayInput.hide();
		}else{
			$isMarrayInput.show();
		}
	}

	/* function ysubmit(val){
		var $currentTab = navTab.getCurrentPanel();
		$("#opt",$currentTab).val(val);
	} */
	
	function loantgFormSubmit(obj){
		var $form=$(obj);
		if(!$form.valid()){
			return false;
		}
        var $box = navTab.getCurrentPanel();
		var productType = $("input[name='jkType']:checked",$box).val();
		if(!productType){
			alertMsg.warn('请选择借款类型，完善信息后重新提交');
			return false;
		}
		return validateCallback(obj, navTabAjaxDone);
	}
	
	function isZjhm(value){
		if(value == ""){
			return;
		}
		var $box = navTab.getCurrentPanel();
		if($("#pocertificates").val() == '身份证'){
			if(!(value.length == 15 || value.length == 18))
				return ;
		}
		$.ajax({
		  url: "${ctx}/zxgl/isIdInfoByZjhm",
		  cache: false,
		  global: false,
		  async: false,
		  type:'post',
		  data:{zjhm:value},
		  success: function(data){
			if(data.data >= "1"){
				document.getElementById("isZjhm").href="${ctx}/zxgl/loadIdInfo?cradId="+value;
				$("#isZjhm").click(); 
			}
		  }
		});		
	}
	
	//居住状态
	function houseHandle(){
	    var $box = navTab.getCurrentPanel();
	    var selected = $("input[name='liveState']:checked",$box).val();
	    $("input[id^='liveMessage']",$box).attr('disabled',true);
	    if(selected){
	    	$("input#liveMessage"+selected,$box).attr('disabled',false).val('${xhcfDkrxx.liveMessage}');
	    }
	    
	    $("input[name='liveState']",$box).click(function(){
	    	var $this = $(this);
	    	var $houseInput = $("input[id^='liveMessage']",$box).attr('disabled',true).val("");
	    	$("input#liveMessage"+$this.val(),$box).attr('disabled',false);
	    });
	}
	//借款类型处理 老板借等
	function typeHandle(){
		var $box = navTab.getCurrentPanel();
		$("div[id^='productType']",$box).hide();
		var selected = $("input[name='jkType']:checked",$box).val();
		if(selected){
			var $divProduct = $("div#productType" + selected,$box).show();	
		    $('input',$divProduct).addClass("required");
		}
		$(".jkType",$box).click(function(){
			var $this = $(this);
			var $allProduct = $("div[id^='productType']",$box).hide();
			$('input',$allProduct).removeClass("required");
			var $divProduct = $("div#productType" + $this.val(),$box).show();	
		    $('input',$divProduct).addClass("required");	
		});
	}
	
	function cardChange($box){
		var card =  $("#pocertificates",$box).val();
		if(card != ""){
		    	$("#zjhm",$box).removeAttr("readonly").removeClass("readonly");
		    }else{
		    	$("#zjhm",$box).attr("readonly",true).addClass("readonly");
		}
		
		if(card == '身份证'){
				$("#zjhm",$box).addClass("isIdCardNo");
			}else{
				$("#zjhm",$box).removeClass("isIdCardNo");
	    }
	   	
	    //身份证验证
	    $("#pocertificates",$box).change(function(){
	   
		   	var card =  $(this).val();
		    if(card != ""){
		    	$("#zjhm",$box).removeAttr("readonly").removeClass("readonly");
		    }else{
		    	$("#zjhm",$box).attr("readonly",true).addClass("readonly");
		    }
		    if(card == '身份证'){
				$("#zjhm",$box).addClass("isIdCardNo");
			}else{
				$("#zjhm",$box).removeClass("isIdCardNo");
			}
		});
		
		$("#zjhm",$box).keyup(function(){
	        var $this =  $(this);
	        var card = $("#pocertificates",$box).val();
		    if(card == '身份证'){
		        var cardNo = $this.val();
				if(cardNo.length == 15 || cardNo.length == 18){
				    var gender = getGender(cardNo) == 0 ? '男' :'女';
					$("#genders[value='"+gender+"']",$box).click();
					$("input[name='birthday']",$box).val(getBirthday(cardNo));
				}				
			}
	    });
	}
	
	$(function(){
	   var $box = navTab.getCurrentPanel();
	   spouseExchange();
	   
	   //配偶选项
	   $("#maritalStatus",$box).change(function(){
	   		spouseExchange();
	   });
       cardChange($box);
		//借款类型处理 老板借等
	   typeHandle();
		//住房处理
	   houseHandle();
	   
	   $('#tempSave').click(function(){
	   		var $currentTab = navTab.getCurrentPanel();
			$("#opt",$currentTab).val('0');
	   });
	   
	   $('#realSubmit').click(function(){
	   		var $currentTab = navTab.getCurrentPanel();
			$("#opt",$currentTab).val('1');
	   });
	   
	});
	
</script>