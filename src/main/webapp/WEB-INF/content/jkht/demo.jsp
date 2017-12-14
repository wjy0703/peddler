<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

</head><body><div class="pageContent" style="padding:5px">
<!-- 
	<div class="panel" defh="40">
		<h1>合同基本信息</h1>
		<div>
			合同编号：<input type="text" name="patientNo">
			借款人：<input type="text" name="patientNo">
			<ul class="rightTools">
				<li><a class="button" target="dialog" href="" mask="true"><span>查找合同</span></a></li>
				
			</ul>
		</div>
	</div>
 -->	
 <div class="panelBar">
			<ul class="toolBar">
				<li><a title="返回列表" target="navTab"
					href="${ctx }/xhJkht/listJksqHtzzsh"><span>返回列表</span></a></li>
			</ul>
		</div>	
	<div class="divider"></div>
	<div class="tabs">
	<!-- 
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>借款协议</span></a></li>
					<li><a href="javascript:;"><span>委托扣款授权书</span></a></li>
					<li><a href="javascript:;"><span>信用咨询及管理服务协议</span></a></li>
					<li><a href="javascript:;"><span>信用咨询及管理服务协议</span></a></li>
					
				</ul>
			</div>
		</div>
		 -->
		<div class="tabsContent">
			<div>
	
				<div layouth="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
						<li><a href="">合同相关文件</a>
							<ul>
								<li><a href="file1.jsp" target="ajax" rel="jbsxBox">借款协议</a></li>
								<li><a href="file2.jsp" target="ajax" rel="jbsxBox">委托扣款授权书</a></li>
								<li><a href="file3.jsp" target="ajax" rel="jbsxBox">信用咨询及管理服务协议</a></li>
								<li><a href="file4.jsp" target="ajax" rel="jbsxBox">还款管理服务说明书</a></li>
							</ul>
						</li>
						
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<div class="pageHeader" style="border:1px #B8D0D6 solid">

	<div class="searchBar">
		<table class="searchContent">
			<tbody><tr>
				
			</tr>
		</tbody></table>
	</div>
	</form>
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