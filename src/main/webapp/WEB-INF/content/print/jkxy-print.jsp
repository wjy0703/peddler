<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<style type="text/css">
.table1 {
	border-collapse: collapse;
}

.td {
	border: 1px solid black;
}

.th {
	border: 1px solid black;
	background-color: #ECECEC;
}

.title {
	background-color: #ECECEC;
	text-align: right;
}
</style>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>借款协议</title>
<meta http-equiv="content-type" content="text/html; charset=gbk" />
<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
	id=WebBrowser width=0></OBJECT>
<script language="javascript">
   function printBody(state){
   	   var noprint = document.getElementById("noPrint");
   	   //0 = 直接打印 、 1 = 打印预览 、2 = 页面属性
   	   if(state == "0"){
   	       noprint.style.visibility = "hidden";
   	   	   document.all.WebBrowser.ExecWB(6,6);
   	   	   noprint.style.visibility = "visible";
   	   }else if(state == "1"){
   	       noprint.style.visibility = "hidden";
   	   	   document.all.WebBrowser.ExecWB(7,1);
   	   	   noprint.style.visibility = "visible";
   	   }else if(state == "2"){
   	   	   document.all.WebBrowser.ExecWB(8,1);
   	   }
   }
</script>
</head>
<body>
	<!--startprint-->

	<div class="outer">
		<table cellspacing="0" cellpadding="0" width="100%">
			<col width="188" />
			<col width="192" />
			<col width="151" />
			<col width="165" />
			<col width="151" span="2" />
			<tr height="31">
				<td width="99%" height="31" colspan="5" align="center"
					valign="middle" id="clddd" style="font-size: 14px;">
					<h3>
						借款协议（编号： <input type="text" name="userName17"
							style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
						）
					</h3>
				</td>
			</tr>
			<tr height="30">
				<td height="30" colspan="2" align="left">本借款协议（“本协议”）由以下两方于 <input
					type="text" name="userName7"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
					年 <input type="text" name="userName8"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
					月 <input type="text" name="userName9"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
					日在中华人民共和国 <input type="text" name="userName10"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
					市 <input type="text" name="userName11"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
					区签署并履行：
				</td>
			</tr>
			<tr height="30">
				<td width="50%" align="left">甲方（借款人、共同借款人）： <input type="text"
					name="userName"
					style="width: 150px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
				<td width="50%" height="30" align="left">乙方（出借人）： <input
					type="text" name="userName2"
					style="width: 150px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
			</tr>
			<tr height="30">
				<td width="50%" align="left">身份证号码： <input type="text"
					name="userName3"
					style="width: 150px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
				<td width="50%" height="30" align="left">身份证号码： <input
					type="text" name="userName4"
					style="width: 150px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
			</tr>
			<tr height="30">
				<td width="50%" align="left">现住址： <input type="text"
					name="userName5"
					style="width: 150px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
				<td width="50%" height="30" align="left">地址： <input type="text"
					name="userName6"
					style="width: 150px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
			</tr>
		</table>
		<table width="100%" cellpadding="0" cellspacing="0" class="th">
			<tr>
				<th height="31" colspan="13" align="left">&nbsp;第一条
					甲方向乙方借款，借款信息如下：</th>
			</tr>
			<tr>
				<td width="10%" height="30" bgcolor="#FFFFFF" class="td">
					&nbsp;借款详细用途</td>
				<td width="30%" height="30" bgcolor="#FFFFFF" class="td"><input
					type="text" name="userName18"
					style="width: 90%; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">十万</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">万</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">千</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">百</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">十</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">元</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">角</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">分</td>
			</tr>
			<tr>
				<td width="10%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;借款本金数额</td>
				<td width="30%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;人民币（大写）
					<input type="text" name="userName19"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
				</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td"><input type="text" name="userName21"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;月偿还数额</td>
				<td width="30%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;人民币（大写）
					<input type="text" name="userName20"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
				</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td"><input type="text" name="userName22"
					style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
				<td width="5%" height="30" align="center" bgcolor="#FFFFFF"
					class="td">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;还款分期月数</td>
				<td height="30" colspan="9" bgcolor="#FFFFFF" class="td"><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="10%" height="30" align="center"><input
								type="text" name="userName12"
								style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
								个月</td>
							<td width="10%" height="30" align="center">还 款 日</td>
							<td width="50%" height="30" align="center">每月 <input
								type="text" name="userName13"
								style="width: 50px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;">
								日（12：00前，节假日不顺延）
							</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td width="10%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;还款起止日期</td>
				<td height="30" colspan="9" bgcolor="#FFFFFF" class="td"><input
					type="text" name="userName23"
					style="width: 200px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
			</tr>
			<tr>
				<td width="10%" height="30" bgcolor="#FFFFFF" class="td">&nbsp;甲方专用账户</td>
				<td height="30" colspan="9" bgcolor="#FFFFFF" class="td"><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20%" height="30" align="center">户名</td>
							<td width="20%" height="30" align="center"><input
								type="text" name="userName14"
								style="width: 100px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
							<td width="20%" height="30" align="center">账号</td>
							<td width="20%" height="30" align="center"><input
								type="text" name="userName15"
								style="width: 100px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
							<td width="20%" height="30" align="center">开户行</td>
							<td width="20%" height="30" align="center"><input
								type="text" name="userName16"
								style="width: 100px; height: 20px; background: #FFFFFF; font-size: 12px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000000; border-top-style: none; border-right-style: none; border-left-style: none;"></td>
						</tr>
					</table></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td height="30"><p>第二条 付款方式：</p>
					<p>网上银行汇款，由乙方通过网上银行汇款方式将款项汇入到本协议第一条约定的甲方专用账号中。（若遇到网上银行汇款无法支付的情况下，乙方则通过银行柜台汇款业务将借款汇入到本协议第一条甲方专用账号中。）</p></td>
			</tr>
			<tr>
				<td height="30">第三条
					甲乙双方对本协议第一条所述借款相关信息全部认可，自主自愿依据本协议第一条规定的内容，由乙方向甲方提供借款。在本协议签署后，经甲方同意及授权乙方将本协议第一条借款本金数额，在扣除代替甲方应交纳给
					信和汇金信息咨询（北京）有限公司的咨询费、信和汇诚信用管理（北京）有限公司的审核费及信和惠民投资管理（北京）有限公司的服务费后的剩余款项支付到本协议第一条规定的甲方专用账号中。（咨询费、审核费及服务费的有关事项及具体金额参见甲方与信和汇金信息咨询（北京）有限公司
					、信和汇诚信用管理（北京）有限公司及信和惠民投资管理（北京）有限公司 签订的《信用咨询及管理服务协议》）。</td>
			</tr>
			<tr>
				<td height="30">第四条
					本合同如涉及两人以上借款，任一借款人均应履行本合同项下的义务，对全部借款承担连带清偿责任，乙方有权向任一借款人追索本金利息罚息违约金及其他费用。
				</td>
			</tr>
			<tr>
				<td height="30"><p>第五条 本息偿还方式</p>
					<p>甲方必须按月足额偿还对乙方的本金和利息。
						任何应从甲方账户划扣的款项，甲方在此同意乙方委托合作机构每月从甲方指定账户中准确划扣相应数额。
						甲方须在每月还款日当日（不得迟于12：00）或之前将本协议第一条规定的月偿还本息数额存入本协议第一条规定的甲方专用账户中。
						如果还款日遇到法定假日或公休日，还款日期不进行顺延。
						如果还款日为每月30日，则遇到天数不足30天的月份，还款日为应还款当月的最后一日。
						若甲方提前还款，甲方需至少提前三个工作日提出书面申请，并在甲方和乙方商定的日期当日或之前（非还款日及节假日），由甲方一次性将当月应还款及剩余本金存入本协议第一条规定的甲方专用账号中。
					</p></td>
			</tr>
			<tr>
				<td height="30"><p>第六条 违约规定</p>
					<p>
						若甲方晚于本协议第一条规定的还款日还款，应向乙方支付罚息和逾期违约金。 罚息和逾期违约金计算方法如下；
						（1）逾期违约金：如未按本协议第一条约定的还款时间足额还款，则按照当月应还本息的10%计算，不低于100元，每月单独计算。 <br>
						（2）罚息：每日按当月至借款期结束的应还本息的0.05%收取罚息，每月单独计算。
						（3）如因甲方原因导致未能结清当月全部欠款，则按本协议第六条第一、二项执行。
						若甲方偿还金额不足，偿还顺序按照先后顺序为罚息、逾期违约金，应还利息、应还本金。
						如果甲方擅自改变本协议第一条规定的借款用途或严重违反还款义务（逾期达到15天及以上），乙方有权提前终止本协议，甲方须在乙方提出终止本协议要求的三日内一次性支付余下的所有本金、利息、罚息和逾期违约金。
						甲方提供虚假资料或者故意隐瞒重要事实，构成违约，应承担违约责任，乙方有权要求解除合同，甲方需在乙方要求解除合同后三日内一次性支付余下的款项，包括但不限于本金利息罚息违约金及其他费用：构成犯罪的，乙方有权向相关国家机关报案，追究其刑事责任。
						乙方保留将甲方违约失信的相关信息在媒体披露的权利。因甲方未还款而带来的调查及诉讼费用将由甲方承担。
					</p></td>
			</tr>
			<tr>
				<td height="30"><p>第七条 变更通知</p>
					<p>本协议签订之日起至借款全部清偿之日止，甲方有义务在下列信息变更三日内提供更新后的信息给乙方（包含但不限于）：甲方本人、甲方的家庭联系人及紧急联系人工作单位、居住地址、住所电话、手机号码、电子邮箱的变更。若因甲方不及时提供上述变更信息而带来的乙方的调查及诉讼费用将由甲方承担。</p></td>
			</tr>
			<tr>
				<td height="30">第八条
					乙方可以根据自己的意愿进行本协议下其对甲方债权的转让，无须通知甲方。在乙方的债权转让后，甲方需对债权受让人继续履行本协议下其对乙方的还款义务，不得以未接到债权转让通知为由拒绝履行还款义务。</td>
			</tr>
			<tr>
				<td height="30"><p>第九条 其他</p>
					<p>（1）甲乙双方签署本协议后，本协议于文首所载日期成立；本协议自乙方将本协议第一条所规定的借款本金数额在扣除甲方应支付给信和汇金信息咨询（北京）有限公司的咨询费、信和汇诚信用管理（北京）有限公司的审核费及信和惠民投资管理（北京）有限公司的服务费后的剩余款项支付到甲方专用账号之日起生效。甲方将本协议下全部本金、利息、罚息、逾期违约金及其他相关费用全部偿还完毕之日，本协议自动失效。
						（2）本协议及其附件的任何修改、补充均须以书面形式作出。本协议的补充协议及附件与本协议具有同等的法律效力。
						（3）本协义的传真件、复印件、扫描件等有效复本的效力与本协议原件效力一致。
						（4）甲乙双方均确认，本协议的签署、生效和履行以不违反中国的法律法规为前提。如果本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条款的效力。
						（5）如果甲乙双方在本协议履行过程中发生任何争议，应友好协商解决；如协商不成，则须提交合同签署地的人民法院进行诉讼。
						（6）本协议一式肆份，甲方保留壹份，乙方保留叁份。 附件一：借款人《还款管理服务说明书》</p></td>
			</tr>
			<tr>
				<td height="30" align="center">（以下无正文）</td>
			</tr>
			<tr>
				<td height="30">协议各方签字盖章</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>甲方（借款人）</td>
				<td height="30">乙方（出借人）</td>
			</tr>
			<tr>
				<td>签字：</td>
				<td height="30">签章：</td>
			</tr>
		</table>
	</div>
	<!--endprint-->

	<div style="text-align: center;" id="noPrint">
		<input name="Button" onClick="printBody(0)" type="button" value="直接打印">
		<input name="Button" onClick="printBody(1)" type="button" value="打印预览">
		<input name="Button" onClick="printBody(2)" type="button" value="页面设置">
	</div>
</body>
</html>
