<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<div class="pageContent">
<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span> 变更申请信息</span></a></li>
					
				</ul>
			</div>
		</div>
		<div class="tabsContent" >
			<div>
				<form id="jksqChangeInputForm" name="jksqChangeInputForm" method="post"
					action="${ctx}/xhNewJksq/saveNewjksqSpChange" class="pageForm required-validate"
					onsubmit="return validateCallback(this, navTabAjaxDone);">
					<div class="pageFormContent" layoutH="56">
						<input type="hidden" name="jksqId" value="${jksq.id }" title="变更申请ID" />
						<input type="hidden" name="id" value="${edit.id }" />
						<div class="panel"><h1>客户基本信息</h1></div>
						<table nowrapTD="false">
							<tr>
								<td><label>借款人姓名:</label> 
									<input type="text" id="jkrxm" disabled="disabled" name="jkrxm" size="30" value="${jksq.jkrxm }" class="">
									<input type="hidden" id="id" name="id" size="30" value="${jksq.id }" />
								</td>
								<td><label>证件号码：</label> 
									<input type="text" id="zjhm" name="zjhm" disabled="disabled" size="30" 
										value="${jksq.zjhm }" class=""/>
								</td>
								
							</tr>
							
							
						</table>
							<div class="divider"></div>
						
						
						<br />
						
						
						
						
				

						<table border="0" bordercolor="red" width="98%">
							<tr>
								<td><label>借款申请额度(元):</label> 
									<input type="text" id="jkLoanQuota" name="jkLoanQuota" size="30" 
										class=" number" value="${jksq.jkLoanQuota }" disabled="disabled" ><span class="info"> (必填,无千分位)</span>
								</td>
								<td><label>申请借款期限(月):</label> 
									<input type="text" id="jkCycle" name="jkCycle" size="30" class=" digits" 
										value="${jksq.jkCycle }" disabled="disabled" /><span class="info"> (必填,整数)</span>
								</td>
								<td><label>还款方式：</label> 
									 <sen:select name="hkWay" coding="loanReturnType" clazz="combox" value="${jksq.hkWay}" />
					   
								</td>
							</tr>
							<tr>
								<td colspan="1">
									<dl>
										<dt>借款用途：</dt>
										<dd>
											<sen:select name="jkUse" clazz="combox" coding="loanUseType" value="${jksq.jkUse}"/>
										<!-- 
											<textarea id="jkUse" name="jkUse" style="width: 93%;">${jksq.jkUse }</textarea>
										 -->
										</dd>
									</dl>
								</td>
								<td><label>申请日期：</label> 
									<input type="text" id="jkLoanDate" name="jkLoanDate" class="date "
										format="yyyy-MM-dd" size="27" disabled="disabled" value="${jksq.jkLoanDate }" />
										<a class="inputDateButton" href="javascript:;">选择</a><span class="info"> (必填)</span>
								</td>
								<td><label>共同还款人：</label> 
									<input type="radio" id="togetherPerson" disabled="disabled" name="togetherPerson" value="是" 
										<c:if test='${jksq.togetherPerson =="是"}'>checked="checked" </c:if>
									  />是&nbsp;
									<input type="radio" id="togetherPerson" disabled="disabled" name="togetherPerson" value="否" 
										<c:if test='${jksq.togetherPerson =="否"}'>checked="checked" </c:if>
									 />否&nbsp;
								</td>
							</tr>
							
							
							 
							<tr>
								<td colspan="3">
									<div></div>
								</td>
							</tr>
						</table>
						 <div class="panel" align="center"><h1>修改申请信息</h1></div>
						<table border="0" bordercolor="red" width="98%">
							<tr>
								<td><label>变更申请人：</label> 
									<input type="text" id="createBy" name="createBy"  size="30" value="${edit.createBy} " disabled="disabled" class=""/> 
								</td>
								 <td><label>变更申请日期：</label> 
								 	
									 <fmt:formatDate value="${ edit.createTime }"  type="both"  />
									<%-- <input type="text" id="createTime" name="createTime" class="date "
											format="yyyy-MM-dd" size="30" disabled="disabled" value="${edit.createTime }" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> --%>
									
								</td>
						</tr> 
							<tr>
								<td><label>系统名称：</label> 
									<input type="text" id="sysName" name="sysName" disabled="disabled" size="30" value="${edit.sysName} "  class="required"/> 
								</td>
								<td><label>系统模块：</label> 
									<input type="text" id="sysModule" name="sysModule" disabled="disabled"  size="30"  value="${edit.sysModule} "  class="required"/> 
								</td>
							</tr>
							<tr>
								<td><label>需求类型：</label> 
									<sen:selectRadio name="requirType"  coding="needType" split="&nbsp;&nbsp;&nbsp;&nbsp;" value="${edit.requirType}"/>
										
									
								</td>
								
							</tr>
							<tr>
								<td colspan="3">
									<dl class="nowrap">
										<dt>修改原因：</dt>
										<dd>
											<textarea id="updateCause" name="updateCause" disabled="disabled"  rows="4" style="width: 93%;">${edit.updateCause} </textarea>
										</dd>
									</dl>
								</td>
							</tr>
						</table>
							<div class="divider"></div>
						<table border="0" bordercolor="red" width="98%">
							<tr>
								<td colspan="3">
									<dl class="nowrap">
										<dt>修改内容描述(尽量详细)：</dt>
										<dd>
											<textarea id="commentInfo" name="" disabled="disabled" rows="4" style="width: 93%;" >${edit.commentInfo }</textarea>
										</dd>
									</dl>
								</td>
							</tr>
							
						</table>
							<div class="divider"></div>
						<table border="0" bordercolor="red" width="98%">
							<tr>
								<td><label>审批意见：</label> 
									<input type="radio" id="state" name="state" value="2"  class="required"
										
									/>通过&nbsp; 
									<input type="radio" id="state" name="state" value="1"  class="required"
										
									/>未通过
								</td>
							</tr> 
						</table>	
						
						
						<div class="formBar">
							<ul>
								<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" >保存</button>
									</div>
								</div></li>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">取消</button>
										</div>
									</div></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
			
			<div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>



</div>