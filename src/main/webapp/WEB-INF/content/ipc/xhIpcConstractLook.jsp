<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="panel">
	<div class="pageContent">
		<form method="post" action="${ctx}/ipc/saveXhIpcConstract"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="id" value="${xhIpcConstract.id}" /> <input
				type="hidden" name="apply_id" value="${xhIpcApply.id}" />
			<input type="hidden" name="jkht_id" value="${xhIpcConstract.jkht.id}" />
			<div class="pageFormContent" layoutH="56">

				<p>
					<label>客户姓名：</label> <input name="customerName" type="text"
						size="30" value="${xhIpcApply.customerName}" class="required"
						maxlength="50" readonly />
				</p>
				<p>
					<label>客户编号：</label> <input name="customerNum" type="text"
						size="30" value="${xhIpcApply.customerNum}" class="required"
						maxlength="50" readonly />
				</p>

				<p>
					<label>身份证号：</label> <input name="customerCardId" type="text"
						size="30" value="${xhIpcApply.customerCardId}" class="required"
						maxlength="50" readonly />
				</p>
				<div class="divider"></div>
				<p>
					<label>批借金额(￥)：</label> <input name="pdje" type="text" size="30"
						value="${xhIpcConstract.pdje}" class="required" maxlength="22" readonly="readonly" />
				</p>


				<p>
					<label>放款金额(￥)：</label> <input name="fkje" type="text" size="30"
						value="${xhIpcConstract.fkje}" class="required" maxlength="22" readonly/>
				</p>
				<p>
					<label>还款期数(月)：</label> <input name="hkqs" type="text" size="30"
						value="${xhIpcConstract.hkqs}" class="required" maxlength="22" readonly/>
				</p>
				<p>
					<label>综合费率(%)：</label> <input name="yzhfl" type="text" size="30"
						value="${xhIpcConstract.yzhfl}" class="required" maxlength="22" readonly/>
				</p>

				<p>
					<label>借款利率(%)：</label> <input name="dkll" type="text" size="30"
						value="${xhIpcConstract.dkll}" class="required" maxlength="22" readonly/>
				</p>


				<p>
					<label>剩余利率(%)：</label> <input name="syll" type="text" size="30"
						value="${xhIpcConstract.syll}" class="required" maxlength="22" readonly/>
				</p>
				<p>
					<label>剩余金额(￥)：</label> <input name="syje" type="text" size="30"
						value="${xhIpcConstract.syje}" class="required" maxlength="22" readonly/>
				</p>


				<p>
					<label>服务费(￥)：</label> <input name="fwf" type="text" size="30"
						value="${xhIpcConstract.fwf}" class="required" maxlength="22" readonly/>
				</p>

				<p>
					<label>管理费(￥)：</label> <input name="zhglf" type="text" size="30"
						value="${xhIpcConstract.zhglf}" class="required" maxlength="22" readonly/>
				</p>

				<p>
					<label>信审费(￥)：</label> <input name="xyshf" type="text" size="30"
						value="${xhIpcConstract.xyshf}" class="required" maxlength="22" readonly/>
				</p>

				<p>
					<label>咨询费(￥)：</label> <input name="zxf" type="text" size="30"
						value="${xhIpcConstract.zxf}" class="required" maxlength="22" readonly/>
				</p>
				<p>
					<label>合同金额(￥)：</label> <input name="htje" type="text" size="30"
						value="${xhIpcConstract.htje}" class="required" maxlength="22" readonly/>
				</p>




				<p>
					<label>月本金金额(￥)：</label> <input name="ybjje" type="text" size="30"
						value="${xhIpcConstract.ybjje}" class="required" maxlength="22" readonly/>
				</p>
				<p>
					<label>月利息金额(￥)：</label> <input name="ylxje" type="text" size="30"
						value="${xhIpcConstract.ylxje}" class="required" maxlength="22" readonly/>
				</p>

				<p>
					<label>月还款金额(￥)：</label> <input name="yhkje" type="text" size="30"
						value="${xhIpcConstract.yhkje}" class="required" maxlength="22" readonly/>
				</p>

				<p>
					<label>放款日期：</label> <input name="qdrq" type="text" size="30"
						value="${xhIpcConstract.qdrq}" class="required date"
						maxlength="20" readonly/>
				</p>
				<p>
					<label>首期还款日期：</label> <input name="qshkrq" type="text" size="30"
						value="${xhIpcConstract.qshkrq}" class="required date"
						maxlength="20" readonly/>
				</p>






				<!--  

			<p>
				<label>信访费：</label>
				<input name="xff" type="text" size="30" value="${xhIpcConstract.xff}" class="required" maxlength="22" />
			</p>-->





				<div class="divider"></div>
				<p>
					<label>借款合同编号：</label> <input name="jkhtbm" type="text" size="30"
						value="${xhIpcConstract.jkhtbm}" class="required" maxlength="20" readonly/>
				</p>


				<p>
					<label>还款日${xhIpcConstract.hkr}：</label> 
					 <select name="hkr" class="combox required">
					     <option value="">请选择</option>
						 <option value="15" <c:if test="${xhIpcConstract.hkr=='15' }">selected</c:if>>15日</option>
						 <option value="30" <c:if test="${xhIpcConstract.hkr=='30' }">selected</c:if>>30日</option>
					  </select>

				</p>
				<p>
					<label>出借人：</label>
					    <input name="middleMan.middleManName" type="text" size="30"  class="required" maxlength="22" value="${middleManName}" readonly/>
						 <%-- value="<c:if test="${xhIpcConstract.middlemanId==3}">魏永华</c:if><c:if test="${xhIpcConstract.middlemanId==139036}">夏靖</c:if><c:if test="${xhIpcConstract.middlemanId==322372}">夏靖</c:if><c:if test="${xhIpcConstract.middlemanId==322391}">夏靖</c:if><c:if test="${xhIpcConstract.middlemanId==322408}">夏靖</c:if>" readonly /> --%> 
						<input id="backmiddleman"	name="middleMan.id" type="hidden" value="${xhIpcConstract.middlemanId}" size="30" maxlength="22" readonly />
						<input id="realmiddleman"	name="middlemanId" value="${xhIpcConstract.middlemanId}" type="hidden" size="30"  maxlength="22" readonly />
						<%-- <a class="btnLook" href="${ctx}/jygl/listMiddleMan" lookupGroup="middleMan">请选择放款账户信息</a> --%>
				</p>

				<div class="divider"></div>
				<p>
					<label>审核时间：</label>
				   
				    <input name="auditDate" type="text" size="30" value="<fmt:formatDate value="${xhIpcConstract.auditDate}" pattern="yyyy-MM-hh"/>" class="required date" maxlength="30" readonly />
					
				</p>
				<p>
					<label>审核意见：</label> 
					<input name="auditIdea" type="text" size="30"  value="${xhIpcConstract.auditIdea}" class="required" maxlength="512" readonly />
				</p>
				<p>
					<label>审核人：</label> 
					<input name="auditPerson" type="text" size="30" 	value="${xhIpcConstract.auditPerson}" class="required" maxlength="32" readonly/>
				</p>
				<div class="divider"></div>
				<p>
					<label>签约确认审核时间：</label> 
					<input name="auditQzqrDate" type="text" size="30" value="<fmt:formatDate value="${xhIpcConstract.auditQzqrDate}" pattern="yyyy-MM-hh" />" class="required date" maxlength="30" readonly />
						
				</p>
				<p>
					<label>签约确认审核意见：</label> <input name="auditQzqrIdea" type="text"
						size="30" value="${xhIpcConstract.auditQzqrIdea}" class="required"
						maxlength="512" readonly/>
				</p>
				<p>
					<label>签约确认审核人：</label> <input name="auditQzqrPerson" type="text"
						size="30" value="${xhIpcConstract.auditQzqrPerson}"
						class="required" maxlength="32" readonly/>
				</p>
			</div>
			<div class="formBar">
				<ul>
				<%-- <bjdv:validateContent type="1" funcId="IPC-保存合同">	
				<c:if test="${xhIpcConstract.state!=2}">
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return setMiddleId();">保存</button>
							</div>
						</div></li></c:if>
						</bjdv:validateContent> --%>
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
<script>
  function setMiddleId(){
      var $box = navTab.getCurrentPanel();
       $('#realmiddleman',$box).val($('#backmiddleman').val());
      return true;
  }
</script>