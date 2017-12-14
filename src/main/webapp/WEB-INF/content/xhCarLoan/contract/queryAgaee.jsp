<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<style type="text/css">
body {width:100%;height:100%; margin:0 auto;}
#jbsxBox {height:auto !important;height:500px;min-height:400px;}
</style>
<% if (request.getProtocol().compareTo("HTTP/1.0")==0) 
response.setHeader("Pragma","no-cache"); 
if (request.getProtocol().compareTo("HTTP/1.1")==0) 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); %>
</head><body><div class="pageContent" style="padding:5px">
	
 <div class="panelBar">
			<ul class="toolBar">
				<li>借款人姓名：<input type="text" value="${xhCarLoanApply.xhCarLoanUser.userName}" readonly/></li>
				<li class="line">line</li>
				<li>合同编号：<input type="text" value="${xhCarLoanContract.contractNum}" readonly/></li>
				<li class="line">line</li>
				<li>合同金额：<input type="text" value='<fmt:formatNumber value="${xhCarLoanContract.contractMoney}" pattern="#,#00.00" />'readonly/></li>
				<li class="line">line</li>
				<li>还款期数：<input type="text" value="${xhCarLoanApply.jkCycle}" readonly/></li>
				
			</ul>
		</div>	
	<div class="divider"></div>
	<div class="tabs">
	
		<div class="tabsContent">
			<div>
	
				<div layouth="46" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
						<li><a href="">合同相关文件</a>
							<ul>
							<c:if test="${xhCarLoanApply.jkType==0}">
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carGpsLoanAgreement" target="ajax" rel="jbsxBox">借款协议</a></li>
							</c:if>
							<c:if test="${xhCarLoanApply.jkType==1}">
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carTransferLoanAgreement" target="ajax" rel="jbsxBox">借款协议</a></li>
							</c:if>
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carBusinessConsig" target="ajax" rel="jbsxBox">代理机动车业务授权委托书</a></li>
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carConsigDebit" target="ajax" rel="jbsxBox">委托扣款授权书</a></li>
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carLoanMoneyManage" target="ajax" rel="jbsxBox">还款管理服务说明书</a></li>
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carLoanReceipt" target="ajax" rel="jbsxBox">借款收条</a></li>
								<li><a href="${ctx }/xhCarLoanContract/queryAgaeeFile?Id=${xhCarLoanContract.contractNum}&sType=carServiceManage" target="ajax" rel="jbsxBox">信用咨询及管理服务协议</a></li>
							</ul>
						</li>
						
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;overflow:auto;height: 400px; ">
					<div class="pageHeader" style="border:1px #B8D0D6 solid">

	<div class="searchBar">
		<table class="searchContent">
			<tbody><tr>
				
			</tr>
		</tbody></table>
	</div>

</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">

	
</div>
				</div>
	
			</div>
			
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>

</body></html>