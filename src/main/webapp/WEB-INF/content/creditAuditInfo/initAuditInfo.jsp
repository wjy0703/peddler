<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/dwz/creditAuditPhoto/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${ctx}/ext/publicjs/datepicker.js"></script>
<div class="pageContent"  >
						<div class="pageHeader">
						<form method="post" id="creditAduitForm" name="creditAduitForm"
			action="${ctx }/loan/saveAuditInfo/${loanApply.id}"
			class="pageForm required-validate"
			onsubmit="return creditAduitFormSubmit(this);">
							<div id="cc">
								<div style="margin-right: 6px;">
									<div class="tabs" currentIndex="0" eventType="click">
											<div class="tabsHeader">
												<div class="tabsHeaderContent">
													<ul>
														<li><a href="javascript:tabIIframeSrc('#tab1','001');"><span>申请表</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab2','002');"><span>身份证明</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab3','003');"><span>信用报告</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab4','004');"><span>银行流水</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab5','005');"><span>现住址证明</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab6','006');"><span>房产证明</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab7','007');"><span>其他</span></a></li>
														<li><a href="javascript:tabIIframeSrc('#tab8','99');"><span>旧版数据</span></a></li>
													</ul>
												</div>
											</div>
											<div class="tabsContent" layoutH="49">
												<div>
													<iframe id="tab1" src="${ctx }/loan/listAuditImg?jksqId=${loanApply.id}&typeName=001" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab2" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab3" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab4" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab5" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab6" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab7" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
												<div>
													<iframe id="tab8" src="#" frameBorder="1" width="100%" height="773px" scrolling="no"></iframe>
												</div>
											</div>
											<div class="tabsFooter">
												<div class="tabsFooterContent"></div>
											</div>
										</div>
								</div>
							</div>
							
								<div class="odiv">
									<div layoutH="12" style="border:solid 0px #b8d0d6; background:#fff">
									    <div class="accordion">
											<div class="accordionHeader">
												<h2><span>icon</span>申请信息</h2>
											</div>
											<div class="accordionContent" style="rright: 200px" id="accContentInfo1">
												<div class="accordionInfoContent">
												
													<p>
														<label>进件地区：</label>
														<!-- 咨询列表中取得------------------------------------------------------- -->
														<label>
														<c:forEach items="${province}" var="md" varStatus="st">
														<%-- <input class="contentpInput" name="crmprovince" type="text" value="${xydkzx.crmprovince }" />  --%>
														<c:if test="${xydkzx.crmprovince==md.id}">${md.name}</c:if>
														</c:forEach>
														</label>
														<input type="hidden" name="id" value="${loanApply.id }" />
														<input type="hidden" id="option" name="option"  />
														<%-- <input class="contentpInput" name="jjdq" type="text" value="${xydkzx.crmprovince }"/> --%>
													</p>
													<p>
														<label>申请产品：</label>
														 <sen:vtoName value="${loanApply.jkType}" coding="productType"/>
													</p>
													<p>
														<label>申请金额：</label>
														<label>
														<input class="contentpInput" name="jkLoanQuota" type="text" value="${loanApply.jkLoanQuota }" />
														</label>
													</p>
													
													<p>
														<label>申请期限：</label>
													<!-- 	6、12、18、24、30、36、自定义 -->
														<label>
														<input class="contentpInput" name="jkCycle" type="text" value="${loanApply.jkCycle }" />
														</label>  
													</p>
													<dl>
														<label>客户来源：</label>
														<label>
														<input class="contentpInput" name="knownWay" type="text" value="${loanApply.knownWay }" />
														</label>
														<%-- <c:if test="${loanApply.knownWay == '渠道' }"><label>渠道</label></c:if>
														<c:if test="${loanApply.knownWay == '展业' }"><label>展业</label></c:if>
														<c:if test="${loanApply.knownWay == '转介绍' }"><label>转介绍</label></c:if>
														<c:if test="${loanApply.knownWay == '循环借' }"><label>循环借</label></c:if>
														<c:if test="${loanApply.knownWay == '线上' }"><label>线上</label></c:if>
														<c:if test="${loanApply.knownWay == '' }"><label>${loanApply.knownWay }</label></c:if> --%>
														<!-- <input type="radio" name="knownWay" value="渠道"  />渠道&nbsp;&nbsp;
														<input type="radio" name="knownWay" value="展业"  />展业&nbsp;&nbsp;
														<input type="radio" name="knownWay" value="转介绍"  />转介绍&nbsp;&nbsp;
														<input type="radio" name="knownWay" value="循环借"  />循环借&nbsp;&nbsp;
														<input type="radio" name="knownWay" value="线上"  />线上&nbsp;&nbsp;
														<input type="radio" name="knownWay" value=""  />其他
														<input class="contentDlInput" name="knownWay" type="text" value=""/> -->
													</dl> 
													<dl>
														<label>借款目的：</label>
														<input class="required" style="width: 80%;border-top-style: none;border-right-style: none;border-bottom-style: solid;border-left-style: none;border-bottom-width: 1px;border-bottom-color: #000000;" name="jkTypeMore" type="text" value="${loanApply.jkTypeMore }"/>
													</dl>
												</div>
											</div>
											<div class="accordionHeader">
												<h2><span>icon</span>身份信息</h2>
											</div>
											<div class="accordionContent" id="accContentInfo2">
												<div class="accordionInfoContent">
													<p>
														<label>客户姓名：</label>
														<label>
														<input class="contentpInput" name="jkrxm" type="text" value="${loanApply.jkrxm }" />
														</label>
														<%-- <input class="contentpInput" name="jkrxm" type="text" value="${loanApply.jkrxm }" />  --%>
													</p>
													<p>
														<label>身份证号：</label>
														<label>
														<input class="contentpInput" name="zjhm" type="text" value="${loanApply.zjhm }" />
														</label>
														<%-- <input class="contentpInput" name="zjhm" type="text" value="${loanApply.zjhm }" /> --%>
													</p>
													<p>
														
														<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
														<input type="radio" name="genders" value="男" <c:if test="${loanApply.genders == '男' }">checked</c:if>  />男&nbsp;&nbsp;
														<input type="radio" name="genders" value="女" <c:if test="${loanApply.genders == '女' }">checked</c:if>  />女
														
													</p>
													<p>
														<label>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</label>
														<input id="birthday" class="contentpInput" name="birthday" type="hidden" value="${loanApply.birthday }" />
														<input id="nl" class="contentpInput" type="text" value=""   />
													</p>
													<p>
														<label>省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</label>
														<label>
														<input class="contentpInput" type="text" value="${city.name }" />
														</label>
														<input id="sf" type="hidden" value="${city.name }" />
														<input id="hjd" type="hidden" value="${loanApply.hjadress }" />
														
													</p>
													<dl>
													
														<label>户&nbsp;&nbsp;籍&nbsp;&nbsp;地：</label>
														<input class="contentpInput" name="hjadress" type="text" value="${loanApply.hjadress }" />
														<!-- 暂用radio代替---将省份和户籍地中的省份对比判断本省或外省------------------------- -->
														(
														<input class="contentpInput" type="text" size="2" id="BsOrWs" value="" readonly="readonly" style="border: 0px;BACKGROUND-COLOR: transparent" />
														)
														<%-- <input class="contentDlInput" name="hjadress" type="text" value="${loanApply.hjadress }" /> --%>
													</dl>
													<dl>
														<label>婚姻状况：</label>
														<sen:selectRadio name="marriageType" coding="marriageType" value="${loanApply.maritalStatus }" split="&nbsp;&nbsp;" />
														<%-- <input class="contentDlInput" name="maritalStatus" type="text" value="${loanApply.maritalStatus }" /> --%>
													</dl>
												</div>
											</div>
											<div class="accordionHeader">
												<h2><span>icon</span>信用卡信息</h2>
											</div>
											<div class="accordionContent" id="accContentInfo3">
												<div class="accordionInfoContent xykxx">
													
													
														 <!--  <ul><li>
														<div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrXyk" type="button">添加</button>
															</div>
														</div>
														</li>
														</ul> -->
														
														<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li><a id="getAtrXyk" class="add addOfficeDiv" href="#" target=""><span>添加信用卡信息</span></a></li>						
															</ul>
			 										   </div>
														<div class="unit">
														  <c:forEach items="${loanApply.xhCjksqCards}" var="card" varStatus="cardIndex">
														<table id="cardT" class="cardContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														
														  <tr>
														  	<td rowspan="4" height="30" bgcolor="#ededed">
														  	<a title="删除"  href="#" class="oracleDeleteCards btnDel" ajaxTarget = "office" ajaxData="${card.id}">删除</a>
														  	<%-- <button ajaxData = "${card.id}" class="oracleDeleteCards">删除</button> --%></td>
														    <td height="30" bgcolor="#ededed">&nbsp;激活账户总数</td>
														    <td height="30" bgcolor="#ededed">&nbsp;授信总额</td>
														    <td height="30" bgcolor="#ededed">&nbsp;在用账户数</td>
														    <td height="30" bgcolor="#ededed">&nbsp;单张最高授信</td>
														    <td height="30" bgcolor="#ededed">&nbsp;单张最低</td>
														    <td height="30" bgcolor="#ededed">&nbsp;已使用额度</td>
														    <td height="30" bgcolor="#ededed">&nbsp;月还款（10%）</td>
														    <td height="30" bgcolor="#ededed">&nbsp;信用卡使用率</td>
														  </tr>
											  
													
														  <tr>
														  
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange digits" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].activeCount" value="${card.activeCount}" />
														    <input type="hidden" name="xhCjksqCards[${cardIndex.index}].xhJksq.id" value="${loanApply.id }" />
														    <input class="shouldchange" type="hidden" name="xhCjksqCards[${cardIndex.index}].id" value="${card.id }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].moneySum" value="${card.moneySum}" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange digits" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].cardInuse" value="${card.cardInuse}" /> 
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].singleCardUpper" value="${card.singleCardUpper}" />
														    </td> 
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].singleCardLower" value="${card.singleCardLower}" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].amountUsed" value="${card.amountUsed}" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].estimateValue" value="${card.estimateValue}" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[${cardIndex.index}].useFrequency" value="${card.useFrequency}" />
														    </td>
														  </tr> 
											
															
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;逾期情况说明</td>
														    <td height="22" colspan="7" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentDlInput shouldchange" type="text" name="xhCjksqCards[${cardIndex.index}].exceedComment" value="${card.exceedComment}" />
														  </td>
														  </tr>
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;本人近6个月查询记录</td>
														    <td height="22" colspan="7" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentDlInput shouldchange" type="text" name="xhCjksqCards[${cardIndex.index}].recentRecord" value="${card.recentRecord}" />
														    </td>
														  </tr>
														  
														</table>
														</c:forEach>
														<table id="cardT" class="cardContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														<tbody id="addTrXyk"></tbody>
														</table>
														
													</div>
												
														
													</div>
														
														
											</div>
											<div class="accordionHeader">
												<h2><span>icon</span>借款信息</h2>
											</div>
											<div class="accordionContent" id="accContentInfo4">
												<div class="accordionInfoContent">
													<div class="unit">
														<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li><a id="getAtrJkxx" class="add addOfficeDiv" href="#" target=""><span>添加借款信息</span></a></li>						
															</ul>
			 										   </div>
														<table id="JkxxloadCom" class="creditsContent" width="130%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														  <tr>
														  	<td height="30" bgcolor="#ededed"><!-- <ul><li>
														<div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrJkxx" type="button" >添加</button>
															</div>
														</div>
														</li>
														</ul> --></td>
														    <td height="30" bgcolor="#ededed">&nbsp;银行或公司</td>
														    <td height="30" bgcolor="#ededed">&nbsp;借款类别</td>
														    <td height="30" bgcolor="#ededed">&nbsp;借款总额</td>
														    <td height="30" bgcolor="#ededed">&nbsp;借款期限(月)</td>
														    <td height="30" bgcolor="#ededed">&nbsp;借款佘额</td>
														    <td height="30" bgcolor="#ededed">&nbsp;月还款</td>
														    <td height="30" bgcolor="#ededed" width="100px">&nbsp;有无还款证明</td>
														    <td height="30" bgcolor="#ededed">&nbsp;还款情况说明</td>
														  </tr>
														  <c:forEach items="${loanApply.xhJksqCredits}" var="credits" varStatus="creditsIndex">
														  
														  <tr id="cloneJkxx" >
														  <td height="22" bgcolor="#FFFFFF">
														  <%-- <button ajaxData = "${credits.id}" class="oracleDeleteCredits">删除</button> --%>
														  <a title="删除"  href="#" class="oracleDeleteCredits btnDel" ajaxTarget = "office" ajaxData="${credits.id}">删除</a>
														  </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input id="creditsIndex" type="hidden" value="${creditsIndex.index }" />
														    <input class="shouldchange required" style="width: 80%;border-top-style: none;border-right-style: none;border-bottom-style: solid;border-left-style: none;border-bottom-width: 1px;border-bottom-color: #000000;" type="text" size="30" name="xhJksqCredits[${creditsIndex.index}].compBankName" value="${credits.compBankName}" />
														    <input type="hidden" name="xhJksqCredits[${creditsIndex.index}].xhJksq.id" value="${loanApply.id }" />
														    <input class="shouldchange" type="hidden" name="xhJksqCredits[${creditsIndex.index}].id" value="${credits.id }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <%-- <sen:select name="xhJksqCredits[${creditsIndex.index}].typeh" value="${credits.typeh }" coding="guarantyType"/>
														    --%>
														    <sen:select clazz="contentpInput combox" coding="guarantyType" name="xhJksqCredits[${creditsIndex.index}].typeh" value="${credits.typeh }" />
														   <%--  <input class="contentpInput shouldchange" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].typeh" value="${credits.typeh }" />   --%>
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input id="jkze" class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].loanAmount" value="${credits.loanAmount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].cloanCount" value="${credits.cloanCount }" />
														     
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].remain" value="${credits.remain }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input id="yhk" class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].monthReturn" value="${credits.monthReturn }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <sen:selectRadio name="xhJksqCredits[${creditsIndex.index}].cloanReturnFile" coding="qualificationsCard" value="${credits.cloanReturnFile }"/>
<%-- 														    <input class="contentpInput shouldchange" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].cloanReturnFile" value="${credits.cloanReturnFile }" />
 --%>														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="20" name="xhJksqCredits[${creditsIndex.index}].cloanComment" value="${credits.cloanComment }" />
														    </td>
														  </tr>
														  </c:forEach>
														  <tbody id="addTrJkxx"></tbody>
														  <tr>
														 
														    <td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;总计</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input id="jkzezj" name="jkzezj" value="" disabled="disabled"/>
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input id="yhkzj" name="yhkzj" value="" disabled="disabled"/>
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														  <tr>
														
														    <td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;其它借款统计</td>
														    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														  
														</table>
													</div>
												
														
													</div>
											</div>
											<div class="accordionHeader">
												<h2><span>icon</span>银行流水信息</h2>
											</div>
											<div class="accordionContent" id="accContentInfo5">
												<div class="accordionInfoContent yhlsxx">
												<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li><a id="getAtrYhls" class="add addOfficeDiv" href="#" target=""><span>添加银行流水信息</span></a></li>						
															</ul>
			 										   </div>
													<div class="unit">
													<!-- <ul><li>
														<div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrYhls" type="button" >添加</button>
															</div>
														</div>
														</li>
														</ul> -->
														
													 <c:forEach items="${loanApply.xhCjksqBankRecords}" var="bank" varStatus="bankIndex">
														<table class="bankContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														  <tr>
														    <td height="30" colspan="4" bgcolor="#FFFFFF">
														   <%--  <button ajaxData = "${bank.id}" class="oracleDeleteBank">删除此条银行流水</button> --%>
														    <a title="删除"  href="#" class="oracleDeleteBank btnDel" ajaxTarget = "office" ajaxData="${bank.id}">删除</a>
														    &nbsp;银行名：
														    <sen:select clazz="required" name="xhCjksqBankRecords[${bankIndex.index}].bank" coding="bank" value="${bank.bank}"/>
														    <%-- <input class="required" style="width: 80%;border-top-style: none;border-right-style: none;border-bottom-style: solid;border-left-style: none;border-bottom-width: 1px;border-bottom-color: #000000;" type="text" size="30" name="xhCjksqBankRecords[${bankIndex.index}].bank" value="${bank.bank}" /> --%>
														    <input type="hidden" name="xhCjksqBankRecords[${bankIndex.index}].xhJksq.id" value="${loanApply.id }" />
														    <input type="hidden" name="xhCjksqBankRecords[${bankIndex.index}].id" value="${bank.id }" />
															<!-- <input name="sn" class="contentInput" type="text" value="100001"/> -->
															</td>
														    <td height="30" colspan="4" bgcolor="#FFFFFF">
														 	&nbsp;盖章（每个月）及余额加减是否正确：
														 	<input type="radio" name="xhCjksqBankRecords[${bankIndex.index}].yesOrNo" value="是" <c:if test="${bank.yesOrNo  == '是' }">checked</c:if>  />是
														 	<input type="radio" name="xhCjksqBankRecords[${bankIndex.index}].yesOrNo" value="否" <c:if test="${bank.yesOrNo  == '否' }">checked</c:if>  />否
														 	
															<!-- <input name="name" class="contentInput" type="text" value=""/> -->
															
															</td>
														  </tr>
														  <tr>
														    <td height="30" bgcolor="#ededed">&nbsp;</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].one" value="${bank.one }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].two" value="${bank.two }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].three" value="${bank.three }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].four" value="${bank.four }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].five" value="${bank.five }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].six" value="${bank.six }" />
														    月</td>
														    
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;流水存入</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].onecount" value="${bank.onecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].twocount" value="${bank.twocount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].threecount" value="${bank.threecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].fourcount" value="${bank.fourcount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].fivecount" value="${bank.fivecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].sixcount3" value="${bank.sixcount3 }" />
														    </td>
														  </tr>
														  <tr>
														    <td colspan="7" height="22" bgcolor="#FFFFFF">&nbsp;备注：
														    <input class="contentpInput" type="text" name="xhCjksqBankRecords[${bankIndex.index}].bankComments" size="100" value="${bank.bankComments }" /></td>
														  </tr>
														  <tr>
														    <td height="22" colspan="8" bgcolor="#FFFFFF">&nbsp;截止到
														    <input type="text" name="xhCjksqBankRecords[${bankIndex.index}].currentDate"
																	class="date" pattern="yyyy-MM-dd" value="${bank.currentDate }"
																	size="17" yearstart="-80" /><a class="inputDateButton" href="#">选择</a>
														   <%--  <input id="jzrq" class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].currentDate" value="${bank.currentDate }" /> --%>
														    	<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>年
																<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>月
																<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>日  -->
																尚有存款余额：<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/> -->
														    <input class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[${bankIndex.index}].remainAmount" value="${bank.remainAmount }" />
																</td>
														  </tr>
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;其它流水统计</td>
														    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
													
														</table>
														</c:forEach>
														
													</div>
												</div>
											</div>
											
											
											<div class="accordionHeader">
												<h2><span>icon</span>住址信息</h2>
											</div>
											<div class="accordionContent" id="accContentInfo6">
												<div class="accordionInfoContent addDivHouses" id="addDivHouses">
											
													<dl>
														<label>现&nbsp;&nbsp;住&nbsp;&nbsp;址：</label>
														<input class="contentDlInput" name="homeAddress" type="text" value="${loanApply.homeAddress }"/>
													</dl>
													<dl>
														<label>居住成员：</label>
														<sen:selectCheckbox name="liveTogehter" value="${loanApply.liveTogehter}" coding="liveWhoTogeter" split="&nbsp;&nbsp;&nbsp;&nbsp;" />
														<%-- <input class="contentDlInput" name="liveTogehter"  type="text" value="${loanApply.liveTogehter }"/> --%>
													</dl>
													<dl>
														<label>产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权：</label>
														<input type="radio" name="liveState" value="01" <c:if test="${loanApply.liveState == '01' }">checked</c:if>  />自有房屋&nbsp;&nbsp;
														<input type="radio" name="liveState" value="02" <c:if test="${loanApply.liveState == '02' }">checked</c:if>  />亲属产权&nbsp;&nbsp;
														<input type="radio" name="liveState" value="03" <c:if test="${loanApply.liveState == '03' }">checked</c:if>  />租房&nbsp;&nbsp;
														<input type="radio" name="liveState" value="04" <c:if test="${loanApply.liveState == '04' }">checked</c:if>  />其他
													</dl>
													<dl>
														<label>租金月供：</label>
														<input class="number" style="width: 80%;border-top-style: none;border-right-style: none;border-bottom-style: solid;border-left-style: none;border-bottom-width: 1px;border-bottom-color: #000000;" type="text" size="30" name="liveMessage" value="${loanApply.liveMessage }" />
													</dl>
													</div></div>
													<div class="accordionHeader">
												<h2><span>icon</span>资产信息</h2>
											</div>
											<div class="accordionContent" id="accContentInfo7">
												<div class="accordionInfoContent zcxx">
													<div class="unit">
													<!-- <ul><li>
														<div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrFc" type="button" >添加房产</button>
															</div>
														</div>
														</li>
														</ul> -->
														
														<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li><a id="getAtrFc" class="add addOfficeDiv" href="#" target=""><span>添加房产</span></a></li>						
															</ul>
			 										   </div>
												<c:forEach items="${loanApply.xhJksqHouses}" var="houses" varStatus="housesIndex">
														<table class="zcxxContent" width="100%" border="0" cellpadding="5" 
															cellspacing="1" bgcolor="#000000">
															<tr>
																<td height="30" colspan="3" bgcolor="#ededed">
																<%-- <button ajaxData = "${houses.id}" class="oracleDeleteHouses">删除此房产</button> --%>
																<a title="删除"  href="#" class="oracleDeleteHouses btnDel" ajaxTarget = "office" ajaxData="${houses.id}">删除</a>
																&nbsp;房产地址</td>
																<td height="30" bgcolor="#ededed">&nbsp;产权归属</td>
																<td height="30" bgcolor="#ededed">&nbsp;抵押或无抵押</td>
																<td height="30" bgcolor="#ededed">&nbsp;借款佘额</td>
																<td height="30" bgcolor="#ededed">&nbsp;估 值</td>
															</tr>
															
															<tr>
																<td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput" type="text" size="30" name="xhJksqHouses[${housesIndex.index}].address" value="${houses.address }" />
																<input type="hidden" name="xhJksqHouses[${housesIndex.index}].xhJksq.id" value="${loanApply.id }" />
														    	<input type="hidden" name="xhJksqHouses[${housesIndex.index}].id" value="${houses.id }" /> 
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput" type="text" size="30" name="xhJksqHouses[${housesIndex.index}].chouseOwner" value="${houses.chouseOwner }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<sen:select clazz="contentpInput combox" coding="guarantyType" name="xhJksqHouses[${housesIndex.index}].chouseEndorse" value="${houses.chouseEndorse }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput number" type="text" size="30" name="xhJksqHouses[${housesIndex.index}].remainmoney" value="${houses.remainmoney }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput number" type="text" size="30" name="xhJksqHouses[${housesIndex.index}].chouseValue" value="${houses.chouseValue }" />
																</td>
															</tr>
															<tr>
																<td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;估值/确认途径：
																<input class="contentpInput" type="text" size="30" name="xhJksqHouses[${housesIndex.index}].chouseValueWay" value="${houses.chouseValueWay }" />
																</td>
																<td height="22" colspan="4" bgcolor="#FFFFFF">&nbsp;市场报价（㎡）：
																<input class="contentpInput number" type="text" size="30" name="xhJksqHouses[${housesIndex.index}].chouseMarchetValue" value="${houses.chouseMarchetValue }" />
																</td>
															</tr>
															</table>
															</c:forEach>
															<div class="fc"></div>
															<!-- <ul><li>
														<div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrCc" type="button" >添加车产</button>
															</div>
														</div>
														</li>
														</ul> -->
														<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li><a id="getAtrCc" class="add addOfficeDiv" href="#" target=""><span>添加车产</span></a></li>						
															</ul>
			 										   </div>
															<c:forEach items="${loanApply.xhCjksqVechicles}" var="vechicles" varStatus="vechiclesIndex">
															<table class="zcxxContent" width="100%" border="0" cellpadding="5" 
															cellspacing="1" bgcolor="#000000">
															<tr>
																<td height="22" bgcolor="#ededed">
																<%-- <button ajaxData = "${vechicles.id}" class="oracleDeleteVechicles">删除此车产</button> --%>
																<a title="删除"  href="#" class="oracleDeleteVechicles btnDel" ajaxTarget = "office" ajaxData="${vechicles.id}">删除</a>
																&nbsp;车产/品牌型号</td>
																<td height="22" bgcolor="#ededed">&nbsp;注册日期</td>
																<td height="22" bgcolor="#ededed">&nbsp;登记日期</td>
																<td height="22" bgcolor="#ededed">&nbsp;产权归属</td>
																<td height="22" bgcolor="#ededed">&nbsp;抵押或无抵押</td>
																<td height="22" bgcolor="#ededed">&nbsp;借款佘额</td>
																<td height="22" bgcolor="#ededed">&nbsp;估 值</td>
															</tr>
															<tr id="vechicles">
																<td height="22" bgcolor="#FFFFFF">
																&nbsp;
																<input class="contentpInput" type="text" size="30" name="" value="" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].registerDate" value="${vechicles.registerDate }" />
																<input type="hidden" name="xhCjksqVechicles[${vechiclesIndex.index}].xhJksq.id" value="${loanApply.id }" />
														    	<input type="hidden" name="xhCjksqVechicles[${vechiclesIndex.index}].id" value="${vechicles.id }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].dengjiDate" value="${vechicles.dengjiDate }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].owener" value="${vechicles.owener }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<sen:select clazz="contentpInput combox" coding="guarantyType" name="xhCjksqVechicles[${vechiclesIndex.index}].endorse" value="${vechicles.endorse }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput number" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].borrowmoney" value="${vechicles.borrowmoney }" />
																</td>
																<td height="22" bgcolor="#FFFFFF">&nbsp;
																<input class="contentpInput number" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].estimateValue" value="${vechicles.estimateValue }" />
																</td>
															</tr>
															
															<tr>
																<td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;估值/确认途径：
																<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].valueWay" value="${vechicles.valueWay }" />
																</td>
																<td height="22" colspan="4" bgcolor="#FFFFFF">&nbsp;市场报价：
																<input class="contentpInput number" type="text" size="30" name="xhCjksqVechicles[${vechiclesIndex.index}].marchetValueComment" value="${vechicles.marchetValueComment }" />
																</td>
															</tr>
														</table>
															</c:forEach>
															<div class="cc"></div>
											</div>
														</div>
												</div>
											<div class="accordionHeader">
												<h2><span>icon</span>单位基本情况</h2>
											</div>
											<div class="accordionContent" id="accContentInfo8">
												<div class="accordionInfoContent" id="addDivOffices">
												<c:forEach items="${loanApply.xhJksqOffices}" var="offices" varStatus="officesIndex">
													<dl>
														<label>公司名称：</label>
														<%-- <input class="contentDlInput" name="name" type="text" value="${offices.name }"/> --%>
														<input class="contentDlInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].name" value="${offices.name }" />
														<input type="hidden" name="xhJksqOffices[${officesIndex.index}].xhJksq.id" value="${loanApply.id }" />
														<input id="officeId" type="hidden" name="xhJksqOffices[${officesIndex.index}].id" value="${offices.id }" />
													</dl>
													<p>
														<label>公司地址：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].address" value="${offices.address }" />
														<%-- <input class="contentDlInput" name="address" type="text" value="${offices.address }"/> --%>
													</p>
													<p>
														<label>岗位名称：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].department" value="${offices.department }" />
														<%-- <input class="contentpInput" name="department" type="text" value="${offices.department }"/> --%>
													</p>
													<p>
														<label>任职年期：</label>
														<input class="contentpInput number" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].workYear" value="${offices.workYear }" />
														<%-- <input class="contentpInput" name="workYear" type="text" value="${offices.workYear }"/> --%>
													</p>
													<p>
														<label>工作职责：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].duty" value="${offices.duty }" />
														<%-- <input class="contentDlInput" name="duty" type="text" value="${offices.duty }"/> --%>
													</p>
													<p>
														<label>工资收入：</label>
													<input class="contentpInput number" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].monthSalary" value="${offices.monthSalary }" /> 
														<!-- <input class="contentpInput" name="sn" type="text" value=""/> -->
													</p>
													<p>
														<label>工资证明：</label>
													<input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].cofficeSalaryEnsure" value="${offices.cofficeSalaryEnsure }" /> 
													</p>
													<p>
														<label>银行代发：</label>
													<input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].cofficeSalaryBankEnsure" value="${offices.cofficeSalaryBankEnsure }" /> 
													</p>
													<p>
														<label>社保/公积金：</label>
														<sen:selectRadio name="xhJksqOffices[${officesIndex.index}].cofficeFund" coding="qualificationsCard" value="${offices.cofficeFund }"/>
													<%-- <input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].cofficeFund" value="${offices.cofficeFund }" />  --%>
													</p>
													<p>
														<label>缴费基数：</label>
													<input class="contentpInput number" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].cofficeFundNumber" value="${offices.cofficeFundNumber }" /> 
													</p>
													<p>
														<label>成立时间：</label>
														<input class="contentpInput date" type="text" name="xhJksqOffices[${officesIndex.index}].cofficeStartyear"
																 pattern="yyyy-MM-dd" value="${offices.cofficeStartyear }"
																	size="17" yearstart="-80" /><a class="inputDateButton" href="#">选择</a>
													<%-- <input class="contentpInput" type="text" size="30" name="xhJksqOffices[${officesIndex.index}].cofficeStartyear" value="${offices.cofficeStartyear }" />  --%>
													</p>
													</c:forEach>
												</div>
													<div id="addDivDwqk"></div>
											</div>
											<div class="accordionHeader">
												<h2><span>icon</span>信息确认</h2>
											</div>
											<div class="accordionContent" id="accContentInfo9">
												<div class="accordionInfoContent">
													<div class="unit" id="addTableXxsh">
													<c:set var="comments" value="${loanApply.xhJksqcreditComments[0]}"/>
													<%-- <c:forEach items="${loanApply.xhJksqcreditComments}" var="comments" varStatus="commentsIndex"> --%>
														<table class="commentsContect" width="100%" border="0" bgcolor="#000000" cellspacing="1" >
														  <tr>
														    <td height="30" bgcolor="#ededed">&nbsp;确认途径</td>
														    <td height="30" bgcolor="#ededed">&nbsp;一致性</td>
														    <td height="30" bgcolor="#ededed">&nbsp;情况说明</td>
														    
														  </tr>
														  <tr><!--   □是  □否  □其他 -->
														    <td height="22" bgcolor="#FFFFFF">&nbsp;114 / 10000查询</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;信息一致&nbsp;&nbsp;
														    <input type="hidden" name="xhJksqcreditComments[0].xhJksq.id" value="${loanApply.id }" />
															<input id="xxqrId" type="hidden" name="xhJksqcreditComments[0].id" value="${comments.id }" />
														    <input type="radio" name="xhJksqcreditComments[0].onefour" value="是"<c:if test="${comments.onefour =='是' }">checked</c:if> />是&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].onefour" value="否"<c:if test="${comments.onefour =='否' }">checked</c:if> />否&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].onefour" value="其他"<c:if test="${comments.onefour =='其他' }">checked</c:if> />其他
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input name="xhJksqcreditComments[0].onefourComment" class="contentInputSize" type="text" value="${comments.onefourComment }" style="width: 80%"/>
														    </td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;红盾网/工商局网站查询</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;信息一致&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].gzaic" value="是"<c:if test="${comments.gzaic =='是' }">checked</c:if> />是&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].gzaic" value="否"<c:if test="${comments.gzaic =='否' }">checked</c:if> />否&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].gzaic" value="其他"<c:if test="${comments.gzaic =='其他' }">checked</c:if> />其他
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 80%"/> -->
														    <input name="xhJksqcreditComments[0].gzaicComment" class="contentInputSize" type="text" value="${comments.gzaicComment }" style="width: 80%"/>
														    </td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;百度网查公司/个人信息</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;信息一致&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].baidu" value="是"<c:if test="${comments.baidu =='是' }">checked</c:if> />是&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].baidu" value="否"<c:if test="${comments.baidu =='否' }">checked</c:if> />否&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].baidu" value="其他"<c:if test="${comments.baidu =='其他' }">checked</c:if> />其他
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input name="xhJksqcreditComments[0].baiduComment" class="contentInputSize" type="text" value="${comments.baiduComment }" style="width: 80%"/>
														    </td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;P2P网络逾期黑名单查询</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;有无记录&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].ptopnet" value="是"<c:if test="${comments.ptopnet =='是' }">checked</c:if> />是&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].ptopnet" value="否"<c:if test="${comments.ptopnet =='否' }">checked</c:if> />否&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].ptopnet" value="其他"<c:if test="${comments.ptopnet =='其他' }">checked</c:if> />其他
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input name="xhJksqcreditComments[0].ptopnetComment2" class="contentInputSize" type="text" value="${comments.ptopnetComment2 }" style="width: 80%"/>
														    </td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;全国法院被执行人信息查询</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;有无记录&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].court" value="是"<c:if test="${comments.court =='是' }">checked</c:if> />是&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].court" value="否"<c:if test="${comments.court =='否' }">checked</c:if> />否&nbsp;&nbsp;
														    <input type="radio" name="xhJksqcreditComments[0].court" value="其他"<c:if test="${comments.court =='其他' }">checked</c:if> />其他
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input name="xhJksqcreditComments[0].courtComment" class="contentInputSize" type="text" value="${comments.courtComment }" style="width: 80%"/>
														    </td>
														  </tr>
														</table>
													</div>
												</div>
											</div>
											
											<div class="accordionHeader">
												<h2><span>icon</span>其他重要资料说明及风险点</h2>
											</div>
											<div class="accordionContent" id="accContentInfo10">
												<div class="accordionInfoContent">
													<div class="unit" id="addTableZyzl">
														<table width="100%" border="0" bgcolor="#000000" cellspacing="1" >
															<tr>
															    <td height="22" bgcolor="#FFFFFF">&nbsp;其他重要资料说明及风险点:</td>
															</tr>
															<tr>
															    <td height="22" bgcolor="#FFFFFF"><textarea name="xhJksqcreditComments[0].othercomment" id="textarea" cols="45" rows="5" class="contentInputSize" style="width: 80%" >${comments.othercomment }</textarea></td>
															</tr>
														</table>
											
											
													</div>
												</div>
											</div>
											<div class="accordionHeader">
												<h2><span>icon</span>审核建议</h2>
											</div>
											 <div class="accordionContent" id="accContentInfo11">
												<div class="accordionInfoContent">
													<div class="unit">
														<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														  <!-- <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;我司老客户</td>
														    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;□是   □否
														    <input type="radio" name="lkh" value="是" />是
														    <input type="radio" name="lkh" value="否" />是
														     </td>
														  </tr>
														  <tr>
														    <td height="30" bgcolor="#ededed">&nbsp;进件日期</td>
														    <td height="30" bgcolor="#ededed">&nbsp;借款金额</td>
														    <td height="30" bgcolor="#ededed">&nbsp;借款期限</td>
														    <td height="30" bgcolor="#ededed">&nbsp;约定月还款</td>
														    <td height="30" bgcolor="#ededed">&nbsp;是否已结清</td>
														    <td height="30" bgcolor="#ededed">&nbsp;提前期限</td>
														    <td height="30" bgcolor="#ededed">&nbsp;是否有逾期</td>
														    <td height="30" bgcolor="#ededed">&nbsp;有无重要信息变更</td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr> -->
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;审核建议</td>
														    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;其它：
														    
														    <input class="contentpInput" type="text" name="xhJksqcreditComments[0].auditInfomations" value="${comments.auditInfomations }" />
														    </td>
														  </tr>
														  <tr>
														    <td height="30" bgcolor="#ededed">&nbsp;产品类别</td>
														    <td height="30" bgcolor="#ededed">&nbsp;是否本地人</td>
														    <td height="30" bgcolor="#ededed">&nbsp;信用情况</td>
														    <td height="30" bgcolor="#ededed">&nbsp;房产/车产</td>
														    <td height="30" bgcolor="#ededed">&nbsp;月均流水</td>
														    <td height="30" bgcolor="#ededed">&nbsp;外访费</td>
														    <td height="30" bgcolor="#ededed">&nbsp;建议额度</td>
														    <td height="30" bgcolor="#ededed">&nbsp;建议期限</td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														     <sen:vtoName value="${loanApply.jkType}" coding="productType"/>
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <c:if test="${loanApply.parhome == 5 || loanApply.parhome == 3 }">本地</c:if>
														    <c:if test="${loanApply.parhome == 2 || loanApply.parhome == 1 }">外地</c:if>
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput" type="text" name="xhJksqcreditComments[0].creditCondition" value="${comments.creditCondition}" />
														    	
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput" type="text" name="xhJksqcreditComments[0].houseVechicle" value="${comments.houseVechicle}" />
														    	
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput number" type="text" name="xhJksqcreditComments[0].averageMonthBankRecord" value="${comments.averageMonthBankRecord}" />
														    	
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput number" type="text" name="xhJksqcreditComments[0].outVisitFee" value="${comments.outVisitFee}" />
														    	
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput number" type="text" name="xhJksqcreditComments[0].creditAmount" value="${comments.creditAmount}" />
														    	
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput digits" type="text" min='1' name="xhJksqcreditComments[0].creditMonth" value="${comments.creditMonth}" />
														    <%-- <input class="contentpInput date" type="text" name="xhJksqcreditComments[0].creditMonth"
																 pattern="yyyy-MM-dd" value="${comments.creditMonth}"
																	size="17" yearstart="-80" /><a class="inputDateButton" href="#">选择</a>	 --%>
														    </td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;加急费</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput number" type="text" name="xhJksqcreditComments[0].urgentCreditFee" value="${comments.urgentCreditFee}" />
														    	
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;共同借款人</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    	${xhJksqTogether.togetherName}
														   	</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;共借人身码</td>
														    <td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;
														    	${xhJksqTogether.identification}
														    </td>
														  </tr>
														  <!-- <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;DE1:</td>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;DE2: </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;初 审：</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;日 期：</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;DR1:</td>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;DR2: </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;LL1:</td>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;LL2: </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr> -->
														</table>
														
													</div>
												</div>
											</div> 
														<%-- </c:forEach> --%>
											
											
											<c:forEach items="${loanApply.xhJksqCompanys}" var="companys" varStatus="companysIndex">
											<div class="companyCount accordionHeader">
												<h2><span>icon</span>企业基本信息</h2>
											</div>
											<div class="accordionContent accContentInfo${companysIndex.index}" id="accContentInfo12">
												<div class="accordionInfoContent qyxx${companysIndex.index}" id="qyxx">
												
													<p>
														<label>公司名称：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].cname" value="${companys.cname}" >
														<input type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhJksq.id" value="${loanApply.id }" />
														<input id="gsId" type="hidden" name="xhJksqCompanys[${companysIndex.index}].id" value="${companys.id }" />
													</p>
													<p>
														<label>公司型式：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].cbusniessType" value="${companys.cbusniessType}" >
													</p>
													<p>
														<label>经营地点：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].cbusinessAddr" value="${companys.cbusinessAddr}" >
													</p>
													<p>
														<label>成立时间：</label>
														<input class="contentpInput date" type="text" name="xhJksqCompanys[${companysIndex.index}].startDate"
																 pattern="yyyy-MM-dd" value="${companys.startDate}"
																	size="17" yearstart="-80" /><a class="inputDateButton" href="#">选择</a>
														<%-- <input class="contentpInput" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].startDate" value="${companys.startDate}" > --%>
													</p>
													<p>
														<label>场地合同有效期：</label>
														<input class="contentpInput number" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].cbusinessPeriod" value="${companys.cbusinessPeriod}" >
													</p>
													
													<p>
														<label>公司资本：</label>
														<input class="contentpInput number" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].registerMoney" value="${companys.registerMoney}" >
													</p>
													<p>
														<label>股权比例：</label>
														<input class="contentpInput number" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].cstockholderRatio" value="${companys.cstockholderRatio}" >
													</p>
													<p>
														<label>变更情况：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].cchangeInfo" value="${companys.cchangeInfo}" >
													</p>
													<p>
														<label>经营情况：</label>
														<input class="contentpInput" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].crunningStatus" value="${companys.crunningStatus}" >
													</p>
														
												</div>
												
											</div>
											
											<!-- a -->
											<div class="accordionHeader">
												<h2><span>icon</span>企业详细信息[上游游销售合同核实]</h2>
											</div>
											<div class="accordionContent" style="rright: 200px" id="accContentInfo1">
												<div class="accordionInfoContent">
												<h2></h2>
														<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li>
																
																<a id="getAtrUp" class="add addOfficeDiv" href="#" target="" value="${companysIndex.index}" companyId="${companys.id }"><span>添加上游销售合同</span></a></li>						
															</ul>
			 										   </div>
													<table id="uploadCom" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														  <tr>
														    <td height="30" bgcolor="#ededed"><%-- <ul><li>
														<div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrUp" type="button" value="${companysIndex.index}" companyId="${companys.id }" >添加</button>
															</div>
														</div> 
														</li>
														</ul> --%></td>
														    <td height="30" bgcolor="#ededed">&nbsp;公司名称</td>
														    <td height="30" bgcolor="#ededed">&nbsp;合同类型</td>
														    <td height="30" bgcolor="#ededed">&nbsp;合同金额</td>
														    <td height="30" bgcolor="#ededed">&nbsp;合同期限</td>
														    <td height="30" bgcolor="#ededed">&nbsp;结算方式</td>
														    <td height="30" bgcolor="#ededed">&nbsp;电话及来源</td>
														    <td height="30" bgcolor="#ededed">&nbsp;电话核实情况</td>
														  </tr>
														  <c:forEach items="${companys.xhJksqcompanyUprelateds }" var="companyUp" varStatus="companyUpIndex" >
														
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">
														   <%--  <button ajaxData = "${companyUp.id}" class="oracleDeleteCompanyUp">删除</button> --%>
														    <a title="删除"  href="#" class="oracleDeleteCompanyUp btnDel" ajaxTarget = "office" ajaxData="${companyUp.id}">删除</a>
														    </td>
														    <td height="22" bgcolor="#FFFFFF">
														    &nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].companyName" value="${companyUp.companyName }" >
															<input type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].xhJksqCompany.id" value="${companys.id }" />
															<input class = "shouldchange" id="companyUpId" type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].id" value="${companyUp.id }" />
															
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].contactType" value="${companyUp.contactType }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].contactMoney" value="${companyUp.contactMoney }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].contactDue" value="${companyUp.contactDue }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].contactHandleType" value="${companyUp.contactHandleType }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].phoneOther" value="${companyUp.phoneOther }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyUprelateds[${companyUpIndex.index}].phoneBackinfo" value="${companyUp.phoneBackinfo }" >
														    </td>
														  </tr>
															</c:forEach>
															<tbody id="addTrUp"></tbody>
														</table>
													
														
												</div>
											</div>
											
											<div class="accordionHeader">
												<h2><span>icon</span>企业详细信息[下游游销售合同核实]</h2>
											</div>
											<div class="accordionContent" style="rright: 200px" id="accContentInfo1">
												<div class="accordionInfoContent">
												<h2></h2>
												<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li>
																
																<a id="getAtrDown" class="add addOfficeDiv" href="#" target="" value="${companysIndex.index}" companyId="${companys.id }"><span>添加下游销售合同</span></a></li>						
															</ul>
			 										   </div>
														<table class="downContent" id="downloadCom" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														  <tr>
														    <td height="30" bgcolor="#ededed"><%-- <ul><li><div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrDown" type="button" value="${companysIndex.index}" companyId="${companys.id }" >添加</button>
															</div>
														</div>
														</li>
														</ul> --%></td>
														    <td height="30" bgcolor="#ededed">&nbsp;公司名称</td>
														    <td height="30" bgcolor="#ededed">&nbsp;合同类型</td>
														    <td height="30" bgcolor="#ededed">&nbsp;合同金额</td>
														    <td height="30" bgcolor="#ededed">&nbsp;合同期限</td>
														    <td height="30" bgcolor="#ededed">&nbsp;结算方式</td>
														    <td height="30" bgcolor="#ededed">&nbsp;电话及来源</td>
														    <td height="30" bgcolor="#ededed">&nbsp;电话核实情况</td>
														  </tr>
														  <c:forEach items="${companys.xhJksqcompanyDownrelateds }" var="companyDown" varStatus="companyDownIndex" >
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">
													<%--  <button ajaxData = "${companyDown.id}" class="oracleDeleteCompanyDown">删除</button> --%>
													 <a title="删除"  href="#" class="oracleDeleteCompanyDown btnDel" ajaxTarget = "office" ajaxData="${companyDown.id}">删除</a>
													 </td>
														    <td height="22" bgcolor="#FFFFFF">
														    &nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].companyName" value="${companyDown.companyName }" >
															<input type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].xhJksqCompany.id" value="${companys.id }" />
															<input class="shouldchange" type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].id" value="${companyDown.id }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].contactType" value="${companyDown.contactType }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange number" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].contactMoney" value="${companyDown.contactMoney }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].contactDue" value="${companyDown.contactDue }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].contactHandleType" value="${companyDown.contactHandleType }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].phoneOther" value="${companyDown.phoneOther }" >
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhJksqcompanyDownrelateds[${companyDownIndex.index}].phoneBackinfo" value="${companyDown.phoneBackinfo }" >
														    </td>
														  </tr>
														  </c:forEach>
														  <tbody id="addTrDown"></tbody>
														</table>
												</div>
											</div>
											
											<div class="accordionHeader">
												<h2><span>icon</span>企业详细信息[对公银行流水信息]</h2>
											</div>
											<div class="accordionContent" style="rright: 200px" id="accContentInfo1">
												<div class="accordionInfoContent">
												<%-- <ul><li><div class="buttonActive">
															<div class="buttonContent">
																<button id="getAtrGongBank" type="button" value="${companysIndex.index}" companyId="${companys.id }">添加</button>
															</div>
														</div>
														</li>
														</ul> --%>
														<div class="panelBar officeInfoHeaderDiv">
															<ul class="toolBar">
																<li>
																
																<a id="getAtrGongBank" class="add addOfficeDiv" href="#" target="" value="${companysIndex.index}" companyId="${companys.id }"><span>添加对公银行流水信息</span></a></li>						
															</ul>
			 										   </div>
												<div id="dgyhlsxx">
												
													 <c:forEach items="${companys.xhCjksqBankRecords}" var="bank" varStatus="bankIndex">
												<table class="dgyhls" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
														  <tr>
														    <td height="30" colspan="4" bgcolor="#FFFFFF">
														    <%-- <button ajaxData = "${bank.id}" class="oracleDeleteBank">删除此条银行流水</button> --%>
														    <a title="删除"  href="#" class="oracleDeleteBank btnDel" ajaxTarget = "office" ajaxData="${bank.id}">删除</a>
														    &nbsp;银行名：
														    <sen:select clazz="required" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].bank" coding="bank" value="${bank.bank}"/>
														    <%-- <input class="contentpInput required" type="text" size="30" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].bank" value="${bank.bank}" /> --%>
														    <input type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].xhJksqCompany.id" value="${companys.id }" />
														    <input type="hidden" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].id" value="${bank.id }" />
															<!-- <input name="sn" class="contentInput" type="text" value="100001"/> -->
															</td>
														    <td height="30" colspan="4" bgcolor="#FFFFFF">
														 	&nbsp;盖章（每个月）及余额加减是否正确：
														 	<input type="radio" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].yesOrNo" value="是" <c:if test="${bank.yesOrNo  == '是' }">checked</c:if>  />是
														 	<input type="radio" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].yesOrNo" value="否" <c:if test="${bank.yesOrNo  == '否' }">checked</c:if>  />否
														 	<%--<input class="" type="radio" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].yesOrNo" value="${bank.yesOrNo }" />
														 	 是
														 	<input class="" type="radio" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].yesOrNo" value="否" />否 --%>
															<!-- <input name="name" class="contentInput" type="text" value=""/> -->
															</td>
														  </tr>
														  <tr>
														    <td height="30" bgcolor="#ededed">&nbsp;</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].one" value="${bank.one }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].two" value="${bank.two }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].three" value="${bank.three }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].four" value="${bank.four }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].five" value="${bank.five }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].six" value="${bank.six }" />
														    月</td>
														    
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;流水存入</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].onecount" value="${bank.onecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].twocount" value="${bank.twocount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].threecount" value="${bank.threecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].fourcount" value="${bank.fourcount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].fivecount" value="${bank.fivecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].sixcount3" value="${bank.sixcount3 }" />
														    </td>
														  </tr>
														  <tr>
														    <td colspan="7" height="22" bgcolor="#FFFFFF">&nbsp;备注：
														    <input class="contentpInput" type="text" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].bankComments" size="100" value="${bank.bankComments }" />
														    <%-- <input class="contentpInput" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].comments" value="${bank.comments }" /> --%>
														  </td>
														  </tr>
														  <tr>
														    <td height="22" colspan="8" bgcolor="#FFFFFF">&nbsp;截止到
														    <input type="text" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].currentDate"
																	class="date" pattern="yyyy-MM-dd" value="${bank.currentDate }"
																	size="17" yearstart="-80" /><a class="inputDateButton" href="#">选择</a>
														    <!-- <input class="contentpInput" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].currentDate" value="${bank.currentDate }" />
														    	<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>年
																<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>月
																<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>日 -->
																尚有存款余额：<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/> -->
														    <input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[${companysIndex.index}].xhCjksqBankRecords[${bankIndex.index}].remainAmount" value="${bank.remainAmount }" />
																</td>
														  </tr>
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;其它流水统计</td>
														    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														</table>
														</c:forEach>
														</div>
												</div>
												
											</div>
											</c:forEach>
											<!-- b -->
											
											<%--<div class="accordionHeader">
												<h2><span>icon</span>对公银行流水信息</h2>
											</div>
											<d iv class="accordionContent" id="accContentInfo15">
												<div class="accordionInfoContent">
													<div class="unit">
														<table class="cardContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">
													 <c:forEach items="${loanApply.xhCjksqBankRecords}" var="bank" varStatus="bankIndex">
														  <tr>
														    <td height="30" colspan="4" bgcolor="#FFFFFF">
														    &nbsp;银行名：
														    <input class="contentpInput" type="text" size="30" name="" value="${bank.bank}" />
														    <input type="hidden" name="" value="${loanApply.id }" />
														    <input type="hidden" name="" value="${bank.id }" />
															<!-- <input name="sn" class="contentInput" type="text" value="100001"/> -->
															</td>
														    <td height="30" colspan="4" bgcolor="#FFFFFF">
														 	&nbsp;盖章（每个月）及余额加减是否正确：
														 	<input class="" type="radio" name="name" value="是" />是
														 	<input class="" type="radio" name="name" value="否" />否
															<!-- <input name="name" class="contentInput" type="text" value=""/> -->
															</td>
														  </tr>
														  <tr>
														    <td height="30" bgcolor="#ededed">&nbsp;</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.one }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.two }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.three }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.four }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.five }" />
														    月</td>
														    <td height="30" bgcolor="#ededed">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.six }" />
														    月</td>
														    
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;流水存入</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.onecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.twocount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.threecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.fourcount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.fivecount }" />
														    </td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;
														  	<input class="contentpInput" type="text" size="20" name="" value="${bank.sixcount3 }" />
														    </td>
														  </tr>
														  <tr>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;备注：</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														    <td height="22" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														  <tr>
														    <td height="22" colspan="8" bgcolor="#FFFFFF">&nbsp;截止到
														    <input class="contentpInput" type="text" size="20" name="" value="${bank.currentDate }" />
														    	<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>年
																<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>月
																<input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/>日 -->
																尚有存款余额：<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 50px"/> -->
														    <input class="contentpInput" type="text" size="20" name="" value="${bank.remainAmount }" />
																</td>
														  </tr>
														  <tr>
														    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;其它流水统计</td>
														    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;</td>
														  </tr>
														</c:forEach>
														</table>
														
													</div>
													
												</div>
											</div> --%>
											<!-- 结束 -->
										</div>
										
										
										<div class="formBar">
				<ul>
				
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="realSave(0)">暂存</button>
							</div>
						</div></li>
					<li>
					<li><div class="buttonActive">
							<div class="buttonContent" >
								<button type="submit" onclick="realSave(1)">保存</button>
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
									</div>
										</div>
								</form>	
								
								
							</div>

								
						</div>
<script>

function realSave(option){
	var $current = navTab.getCurrentPanel();
	$("#option",$current).val(option);
	return true;
}

$(function() {
	$('[id^=slides]').each(function(){
		   $(this).slidesjs({
		        width: 800,
		        height: 1130
		      });
		});
  });
$(document).ready(function(){
	$("#cc").jsplit({ 
		MaxW:"700px",
		MinW:"300px",
		FloatD:"left",
		IsClose:false,
		BgUrl:"url(${ctx}/dwz/jsplit/sp_bg.gif)",
		Bg:"right 0 repeat-y",
		Btn:{btn:true,
			oBg:{Out:"0 0",Hover:"-6px 0"},
			cBg:{Out:"-12px 0",Hover:"-18px 0"}},
		Fn:function(){}});
});
function tabOnClick(rel){
	$('[id^=accContentInfo]').each(function(){
			$(this).css("height", "0px");
		   $(this).hide();
		});
	$("#"+rel).css("height", "278px");
	$("#"+rel).show();
}



//------------------计算年龄-------------------
	var birthday = $("#birthday").val();
	var birthYear = birthday.substr(0,4);
	var systemDate = new Date();
	var systemYear = systemDate.getFullYear();
	var nl = systemYear - birthYear;
	$('#nl').val(nl);
	
	//------------辨别本省、外省-----------
	var sf = $("#sf").val();
	var hjd = $("#hjd").val();
	hjd = hjd.substr(0,sf.length);
	if(sf == hjd){
		$("#BsOrWs").val("本省");
	}else {
		$("#BsOrWs").val("外省");
	}
	//借款总额总计
	var i = 0;
	$("#jkze").each(function(){
		if($("#jkze").val())
		i += parseFloat($("#jkze").val());
	});
	$("#jkzezj").val(i);
	//每月还款总计
	var j = 0;
	$("#yhk").each(function(){
		if($("#yhk").val())
		j += parseFloat($("#yhk").val());
	});
	$("#yhkzj").val(j);
	
	function creditAduitFormSubmit(obj) {
	var $form = $(obj);
		if (!$form.valid()) {

			return false;
		}
	return validateCallback(obj, navTabAjaxDone);
	}
	
	
	
	/* //用户添加新信审记录
    $("#addCredit",$box).click(function(){
        alertMsg.confirm("确认添加新信用记录么！", {
			okCall: function(){
				 var trStr = getCreditTr($box);
        		 $(trStr).appendTo($("table#creditTable",$box));
        		 $("button.pageCreditDelete",$box).on('click',function(){
        		 		  var $parentTr = $(this).parents('tr');
        		 		  //alertMsg.confirm("确认删除该信用记录么！", {
						  //okCall: function(){
							   $parentTr.remove();
					      //}
						  //});
						  return false;
        		 });
			}
		});
       
   		return false;
   }); */
   //数据库已保存的信用资料删除
   $(function() {
        var $box = navTab.getCurrentPanel();
   
    $("a.oracleDeleteCards",$box).click(function(){
      var url = "${ctx}/loan/oracleDeleteCards/" + $(this).attr('ajaxData');
      var $parentTable = $(this).parents('table');
      alertMsg.confirm("<font color='red'>确认删除该信用记录么!!!</font>", {
			okCall: function(){                    
                 $.ajax({
						url : url,
						cache : false,
						global : false,
						async : false,
						type : 'post',
						success : function(data) {
							if(data == "1")
							$parentTable.remove();
						}
	            });
	         }
	  });
	  return false;
   });
	
    $("a.oracleDeleteCredits",$box).click(function(){
        var url = "${ctx}/loan/oracleDeleteCredits/" + $(this).attr('ajaxData');
        var $parentTr = $(this).parents('tr');
        alertMsg.confirm("<font color='red'>确认删除该借款信息么!!!</font>", {
				okCall: function(){                    
                   $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								if(data == "1")
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
     });
    
    $("a.oracleDeleteBank",$box).click(function(){
        var url = "${ctx}/loan/oracleDeleteBank/" + $(this).attr('ajaxData');
        var $parentTr = $(this).parents('table');
        alertMsg.confirm("<font color='red'>确认删除该银行流水么!!!</font>", {
				okCall: function(){                    
                   $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								if(data == "1")
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
     });
     
     
    
    $("a.oracleDeleteHouses",$box).click(function(){
        var url = "${ctx}/loan/oracleDeleteHouses/" + $(this).attr('ajaxData');
        var $parentTr = $(this).parents('table');
        alertMsg.confirm("<font color='red'>确认删除该房产记录么!!!</font>", {
				okCall: function(){                    
                   $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								if(data == "1")
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
     });
    $("a.oracleDeleteVechicles",$box).click(function(){
        var url = "${ctx}/loan/oracleDeleteVechicles/" + $(this).attr('ajaxData');
        var $parentTr = $(this).parents('table');
        alertMsg.confirm("<font color='red'>确认删除该车辆记录么!!!</font>", {
				okCall: function(){                    
                   $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								if(data == "1")
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
     });
    
    $("a.oracleDeleteCompanyUp",$box).click(function(){
        var url = "${ctx}/loan/oracleDeleteCompanyUp/" + $(this).attr('ajaxData');
        var $parentTr = $(this).parents('tr');
        alertMsg.confirm("<font color='red'>确认删除上游记录么!!!</font>", {
				okCall: function(){                    
                   $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								if(data == "1")
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
     });
    $("a.oracleDeleteCompanyDown",$box).click(function(){
        var url = "${ctx}/loan/oracleDeleteCompanyDown/" + $(this).attr('ajaxData');
        var $parentTr = $(this).parents('tr');
        alertMsg.confirm("<font color='red'>确认删除该下游记录么!!!</font>", {
				okCall: function(){                    
                   $.ajax({
							url : url,
							cache : false,
							global : false,
							async : false,
							type : 'post',
							success : function(data) {
								if(data == "1")
								$parentTr.remove();
							}
		            });
		         }
		  });
		  return false;
     });
});
</script>
<script type="text/javascript">
											var count = $("table.commentsContect").length;
											if(count==0){
												$str='';
												$str+='<table class="commentsContect" width="100%" border="0" bgcolor="#000000" cellspacing="1" >\
												  <tr>\
												    <td height="30" bgcolor="#ededed">&nbsp;确认途径</td>\
												    <td height="30" bgcolor="#ededed">&nbsp;一致性</td>\
												    <td height="30" bgcolor="#ededed">&nbsp;情况说明</td>\
												  </tr>\
												  <tr><!--   □是  □否  □其他 -->\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;114 / 10000查询</td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;信息一致&nbsp;&nbsp;\
												    <input type="hidden" name="xhJksqcreditComments[#commentsIndex].xhJksq.id" value="${loanApply.id }" />\
													<input id="xxqrId" type="hidden" name="xhJksqcreditComments[#commentsIndex].id" value="" />\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].onefour" value="是" />是&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].onefour" value="否" />否&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].onefour" value="其他" />其他\
												    </td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;\
												    <input name="xhJksqcreditComments[#commentsIndex].onefourComment" class="contentInputSize" type="text" value="" style="width: 80%"/>\
												    </td>\
												  </tr>\
												  <tr>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;红盾网/工商局网站查询</td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;信息一致&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].gzaic" value="是" />是&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].gzaic" value="否" />否&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].gzaic" value="其他" />其他\
												    </td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;<!-- <input name="name" class="contentInputSize" type="text" value="" style="width: 80%"/> -->\
												    <input name="xhJksqcreditComments[#commentsIndex].gzaicComment" class="contentInputSize" type="text" value="" style="width: 80%"/>\
												    </td>\
												  </tr>\
												  <tr>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;百度网查公司/个人信息</td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;信息一致&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].baidu" value="是" />是&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].baidu" value="否" />否&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].baidu" value="其他" />其他\
												    </td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;\
												    <input name="xhJksqcreditComments[#commentsIndex].baiduComment" class="contentInputSize" type="text" value="" style="width: 80%"/>\
												    </td>\
												  </tr>\
												  <tr>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;P2P网络逾期黑名单查询</td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;有无记录&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].ptopnet" value="是" />是&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].ptopnet" value="否" />否&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].ptopnet" value="其他" />其他\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;\
												    <input name="xhJksqcreditComments[#commentsIndex].ptopnetComment2" class="contentInputSize" type="text" value="" style="width: 80%"/>\
												    </td>\
												  </tr>\
												  <tr>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;全国法院被执行人信息查询</td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;有无记录&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].court" value="是" />是&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].court" value="否" />否&nbsp;&nbsp;\
												    <input type="radio" name="xhJksqcreditComments[#commentsIndex].court" value="其他" />其他\
												    </td>\
												    <td height="22" bgcolor="#FFFFFF">&nbsp;\
												    <input name="xhJksqcreditComments[#commentsIndex].courtComment" class="contentInputSize" type="text" value="" style="width: 80%"/>\
												    </td>\
												  </tr>\
												</table>\
											</div>\
										</div>\
									</div>\
									<table width="100%" border="0" bgcolor="#000000" cellspacing="1" >\
													<tr>\
													    <td height="22" bgcolor="#FFFFFF">&nbsp;其他重要资料说明及风险点:</td>\
													</tr>\
													<tr>\
													    <td height="22" bgcolor="#FFFFFF"><textarea name="xhJksqcreditComments[#commentsIndex].othercomment" id="textarea" cols="45" rows="5" class="contentInputSize" style="width: 80%" >\
													    </textarea></td>\
													</tr>\
												</table>';
											var addTableXxsh = $str.replace(/#commentsIndex/g,count);
									        $("#addTableXxsh").append(addTableXxsh);
									        }
											</script>
											<script type="text/javascript">
											var count = $("#addDivOffices dl").length/11;
											if(count==0){
												$str='';
												$str+='<dl>';
												$str+='<label>公司名称：</label>';
												$str+='<input class="contentDlInput" type="text" size="30" name="xhJksqOffices[#officesIndex].name" value="" />';
												$str+='<input type="hidden" name="xhJksqOffices[#officesIndex].xhJksq.id" value="${loanApply.id }" />';
												$str+='<input id="officeId" type="hidden" name="xhJksqOffices[#officesIndex].id" value="" />';
												$str+='</dl>'; 
												$str+='<p>';
												$str+='<label>公司地址：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].address" value="" />';
												$str+='</p>';
												$str+='<p>';
												$str+='<label>岗位名称：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].department" value="" />';
												$str+='</p>';
												$str+='<p>';
												$str+='<label>任职年期：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].workYear" value="" />';
												$str+='</p>';
												$str+='<p>';
												$str+='<label>工作职责：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].duty" value="" />';
												$str+='</p>';
												$str+='<p>';
												$str+='<label>工资收入：</label>';
												$str+='<input class="contentpInput number" type="text" size="30" name="xhJksqOffices[#officesIndex].monthSalary" value="" />'; 
												$str+='</p>';
												$str+='<p>';
												$str+='<label>工资证明：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].cofficeSalaryEnsure" value="" />'; 
												$str+='</p>';
												$str+='<p>';
												$str+='<label>银行代发：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].cofficeSalaryBankEnsure" value="" />'; 
												$str+='</p>';
												$str+='<p>';
												$str+='<label>社保/公积金：</label>';
												$str+='<sen:selectRadio name="xhJksqOffices[#officesIndex].cofficeFund" coding="qualificationsCard" value=""/>';
												$str+='</p>';
												$str+='<p>';
												$str+='<label>缴费基数：</label>';
												$str+='<input class="contentpInput number" type="text" size="30" name="xhJksqOffices[#officesIndex].cofficeFundNumber" value="" /> ';
												$str+='</p>';
												$str+='<p>';
												$str+='<label>成立时间：</label>';
												$str+='<input class="contentpInput" type="text" size="30" name="xhJksqOffices[#officesIndex].cofficeStartyear" value="" /> ';
												$str+='</p>';
												var addTableXxsh = $str.replace(/#officesIndex/g,count);
												$("#addDivOffices").append(addTableXxsh);
									    	  }
											</script> 
											<script type="text/javascript">
													$(function(){  
													    $("#getAtrUp").click(function(){ 
													    	var i = $(this).attr("value");
													    	var clazz = '.qyxx#i p';
													    	var i = clazz.replace(/#i/g,i);
													    	var index = $(i).length/9-1;
													    	var companyId = $(this).attr("companyId");
													    	var count =$("table#uploadCom tr").length-1;
													        $str='';
													        $str+='<tr><td height="22" bgcolor="#FFFFFF">\
													        <a title="删除"  href="#" class="btnDel pageUpDelete">删除</a></td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].companyName" value="" >\
																<input type="hidden" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].xhJksqCompany.id" value="'+companyId+'" />\
																<input class="shouldchange" type="hidden" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].id" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].contactType" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange number" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].contactMoney" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].contactDue" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].contactHandleType" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].phoneOther" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyUprelateds[#companyUpIndex].phoneBackinfo" value="" >\
															    </td>\
															  </tr>';
															
													        var addTrUp = $str.replace(/#companysIndex/g,index);
													        addTrUp = addTrUp.replace(/#companyUpIndex/g,count);
													        $("#addTrUp").append(addTrUp);   
													        var $box = navTab.getCurrentPanel();
													   		$("a.pageUpDelete",$box).on('click',function(){
															  var $parentTr = $(this).parents('tr');
															  //alertMsg.confirm("确认删除该信用记录么！", {
															  //okCall: function(){
																   $parentTr.remove();
														      //}
															  //});
															  return false;
															});
													    });
													    
													});
														</script>
														<script type="text/javascript">
														$(function(){  
															    $("#getAtrXyk").click(function(){  
															    	var count = $("table.cardContent tr").length;
															        $str='';
															        $str+='<table id="cardT" class="cardContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">';
															        $str+='<tr><td rowspan="4" height="30" bgcolor="#ededed"><a title="删除"  href="#" class="btnDel pageCardDelete">删除</a></td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;激活账户总数</td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;授信总额</td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;在用账户数</td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;单张最高授信</td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;单张最低</td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;已使用额度</td>';
															        $str+='<td height="30" bgcolor="#ededed">&nbsp;月还款（10%）</td>';
																    $str+='<td height="30" bgcolor="#ededed">&nbsp;信用卡使用率</td></tr>';
																    $str+=' <tr><td height="22" bgcolor="#FFFFFF">&nbsp;\
																    		<input class="contentpInput shouldchange digits" type="text" size="20" name="xhCjksqCards[#cardIndex].activeCount" value="" />\
																    		<input type="hidden" name="xhCjksqCards[#cardIndex].xhJksq.id" value="${loanApply.id }" />\
																    		<input class="shouldchange" type="hidden" name="xhCjksqCards[#cardIndex].id" value="" />\
																    		</td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																    		<input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[#cardIndex].moneySum" value="" />\
																    		</td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentpInput shouldchange digits" type="text" size="20" name="xhCjksqCards[#cardIndex].cardInuse" value="" /> \
																		    </td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[#cardIndex].singleCardUpper" value=""/>\
																		    </td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[#cardIndex].singleCardLower" value=""/>\
																		    </td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[#cardIndex].amountUsed" value="" />\
																		    </td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[#cardIndex].estimateValue" value="" />\
																		    </td><td height="22" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentpInput shouldchange number" type="text" size="20" name="xhCjksqCards[#cardIndex].useFrequency" value="" />\
																		    </td></tr>\
																		    <tr>\
																		    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;逾期情况说明</td>\
																		    <td height="22" colspan="7" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentDlInput shouldchange" type="text" name="xhCjksqCards[#cardIndex].exceedComment" value="" />\
																		  </td>\
																		  </tr>\
																		  <tr>\
																		    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;本人近6个月查询记录</td>\
																		    <td height="22" colspan="7" bgcolor="#FFFFFF">&nbsp;\
																		    <input class="contentDlInput shouldchange" type="text" name="xhCjksqCards[#cardIndex].recentRecord" value="" />\
																		    </td>\
																		  </tr></table>';
															        var addTrXyk = $str.replace(/#cardIndex/g,count);
															        $(".xykxx").append(addTrXyk);   
															        
															        var $box = navTab.getCurrentPanel();
															   		$("a.pageCardDelete",$box).on('click',function(){
																	  var $parentTr = $(this).parents('table');
																	  //alertMsg.confirm("确认删除该信用记录么！", {
																	  //okCall: function(){
																		   $parentTr.remove();
																      //}
																	  //});
																	  return false;
																	});
															    });
															});
														
														/* function addXyk() {
															var $uploadCom = $("#xykloadCom");
															var count = $uploadCom.find("tr").length -1 ;
															var $newTr = $uploadCom.find("tr").clone();
															$newTr.find("input").each(function(){
																var $this = $(this);
																var oldName = $this.attr('name');
																var first = oldName.lastIndexOf("[");
																first = oldName.substr(0,first+1);
																var last = oldName.lastIndexOf("]");
																last = oldName.substr(last);
																$this.attr("name",first+count+last);
															});
															$newTr.find("input.shouldchange").val("");
															$newTr.appendTo($uploadCom.find('tbody:last'));
														}  */
														
														</script>
														<script type="text/javascript">
														$(function(){  
														$("#getAtrDown").click(function(){ 
													    	var i = $(this).attr("value");
													    	var clazz = '.qyxx#i p';
													    	var i = clazz.replace(/#i/g,i);
													    	var index = $(i).length/9-1;
													    	var companyId = $(this).attr("companyId");
													    	var count = $("table#downloadCom tr").length - 1;
													    	
													        $str='';
													        $str+='<tr><td height="22" bgcolor="#FFFFFF">\
														        <a title="删除"  href="#" class="btnDel pageDownDelete">删除</a></td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].companyName" value="" />\
																<input type="hidden" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].xhJksqCompany.id" value="'+companyId+'" />\
																<input class="shouldchange" type="hidden" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].id" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].contactType" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange number" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].contactMoney" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].contactDue" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].contactHandleType" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].phoneOther" value="" >\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															    <input class="contentpInput shouldchange" type="text" size="30" name="xhJksqCompanys[#companysIndex].xhJksqcompanyDownrelateds[#companyDownIndex].phoneBackinfo" value="" >\
															    </td>\
															  </tr>';
													        var addTrDown = $str.replace(/#companysIndex/g,index);
													        addTrDown = addTrDown.replace(/#companyDownIndex/g,count);
													        $("#addTrDown").append(addTrDown); 
													        var $box = navTab.getCurrentPanel();
													   		$("a.pageDownDelete",$box).on('click',function(){
															  var $parentTr = $(this).parents('tr');
															  //alertMsg.confirm("确认删除该信用记录么！", {
															  //okCall: function(){
																   $parentTr.remove();
														      //}
															  //});
															  return false;
															});
													    });
													});
														</script>
														<script type="text/javascript">
												$(function(){  
												    $("#getAtrJkxx").click(function(){  
												    	var count = $("table.creditsContent #cloneJkxx").length;
												    	
												        $str='';
												        $str+='<tr id="cloneJkxx">\
												        	<td height="22" bgcolor="#FFFFFF"><a title="删除"  href="#" class="btnDel pageCreditDelete">删除</a></td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input class="shouldchange required" style="width: 80%;border-top-style: none;border-right-style: none;border-bottom-style: solid;border-left-style: none;border-bottom-width: 1px;border-bottom-color: #000000;" type="text" size="30" name="xhJksqCredits[#creditsIndex].compBankName" value="" />\
														    <input type="hidden" name="xhJksqCredits[#creditsIndex].xhJksq.id" value="${loanApply.id }" />\
														    <input class="shouldchange" type="hidden" name="xhJksqCredits[#creditsIndex].id" value="" />\
														    </td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input class="contentpInput shouldchange" type="text" size="20" name="xhJksqCredits[#creditsIndex].typeh" value="" /> \
														    </td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input id="jkze" class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[#creditsIndex].loanAmount" value="" />\
														    </td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input class="contentpInput shouldchange" type="text" size="20" name="xhJksqCredits[#creditsIndex].cloanCount" value="" />\
														    </td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[#creditsIndex].remain" value="" />\
														    </td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input id="yhk" class="contentpInput shouldchange number" type="text" size="20" name="xhJksqCredits[#creditsIndex].monthReturn" value="" />\
														    </td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;';
														    
														   $str += '<sen:selectRadio name="xhJksqCredits[#creditsIndex].cloanReturnFile" coding="qualificationsCard"/>';
														  
														   $str += '</td>\
														    <td height="22" bgcolor="#FFFFFF">&nbsp;\
														    <input class="contentpInput shouldchange" type="text" size="20" name="xhJksqCredits[#creditsIndex].cloanComment" value="" />\
														    </td>\
														  </tr>';
												        var addTrJkxx = $str.replace(/#creditsIndex/g,count);
												        $("#addTrJkxx").append(addTrJkxx);  
												        var $box = navTab.getCurrentPanel();
												   		$("a.pageCreditDelete",$box).on('click',function(){
														  var $parentTr = $(this).parents('tr');
														  //alertMsg.confirm("确认删除该信用记录么！", {
														  //okCall: function(){
															   $parentTr.remove();
													      //}
														  //});
														  return false;
														});
												    });
												});
														/* function addJkxx() {
															var $uploadCom = $("#JkxxloadCom");
															var count = $uploadCom.find("tr").length -1 ;
															var $newTr = $uploadCom.find("#cloneJkxx:last").clone();
															$newTr.find("input").each(function(){
																var $this = $(this);
																var oldName = $this.attr('name');
																var first = oldName.lastIndexOf("[");
																first = oldName.substr(0,first+1);
																var last = oldName.lastIndexOf("]");
																last = oldName.substr(last);
																$this.attr("name",first+count+last);
															});
															$newTr.find("input.shouldchange").val("");
															$newTr.appendTo($uploadCom.find('#newJkxx'));
														}  */
														</script>
														<script type="text/javascript">
														$(function(){  
														    $("#getAtrYhls").click(function(){  
														    	debugger;
														    	var count = $("table.bankContent tr").length;
														    	$str='';
														        $str+='<table class="bankContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000"><tr>';
														        	$str+='	<td height="30" colspan="4" bgcolor="#FFFFFF"><a title="删除"  href="#" class="btnDel pageBankDelete">删除</a>';
														        		$str+='&nbsp;银行名：';
														        			$str+='<sen:select clazz="required" name="xhCjksqBankRecords[#bankIndex].bank" coding="bank" value=""/>';
														        			$str+='<input type="hidden" name="xhCjksqBankRecords[#bankIndex].xhJksq.id" value="${loanApply.id }" />\
															    <input type="hidden" name="xhCjksqBankRecords[#bankIndex].id" value="" />\
																</td>\
															    <td height="30" colspan="4" bgcolor="#FFFFFF">\
															 	&nbsp;盖章（每个月）及余额加减是否正确：\
															 	<input class="" type="radio" name="name" value="是" />是\
															 	<input class="" type="radio" name="name" value="否" />否\
																</td>\
															  </tr>\
															  <tr>\
															    <td height="30" bgcolor="#ededed">&nbsp;</td>\
															    <td height="30" bgcolor="#ededed">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].one" value="" />\
															    月</td>\
															    <td height="30" bgcolor="#ededed">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].two" value="" />\
															    月</td>\
															    <td height="30" bgcolor="#ededed">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].three" value="" />\
															    月</td>\
															    <td height="30" bgcolor="#ededed">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].four" value="" />\
															    月</td>\
															    <td height="30" bgcolor="#ededed">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].five" value="" />\
															    月</td>\
															    <td height="30" bgcolor="#ededed">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].six" value="" />\
															    月</td>\
															  </tr>\
															  <tr>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;流水存入</td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].onecount" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].twocount" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].threecount" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].fourcount" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].fivecount" value="" />\
															    </td>\
															    <td height="22" bgcolor="#FFFFFF">&nbsp;\
															  	<input class="contentpInput" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].sixcount3" value="" /></td></tr><tr>\
															  	<td colspan="7" height="22" bgcolor="#FFFFFF">&nbsp;备注：\
															    <input class="contentpInput" type="text" name="xhCjksqBankRecords[#bankIndex].bankComments" size="100" value="" /></td></tr>\
															  <tr><td height="22" colspan="8" bgcolor="#FFFFFF">&nbsp;截止到\
															  <input type="text" name="xhCjksqBankRecords[#bankIndex].currentDate" class="date" pattern="yyyy-MM-dd" value="" size="17" yearstart="-80" />\
															  &nbsp;尚有存款余额：\
															  <input  class="contentpInput number" type="text" size="20" name="xhCjksqBankRecords[#bankIndex].remainAmount" value="" /></td></tr>\
															  <tr><td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;其它流水统计</td>\
															    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;</td></tr></table>';
															  
														        var addTrYhls = $str.replace(/#bankIndex/g,count);
														        var $addTrYhls = $(addTrYhls);
														        
														        var $box = navTab.getCurrentPanel();
														        $(".yhlsxx",$box).append($addTrYhls);   
														        datePicker($addTrYhls);
														        
														   		$("a.pageBankDelete",$box).on('click',function(){
																  var $parentTr = $(this).parents('table');
																  //alertMsg.confirm("确认删除该信用记录么！", {
																  //okCall: function(){
																	   $parentTr.remove();
															      //}
																  //});
																  return false;
																});
														    });
														});
														</script>
														<script type="text/javascript">
											var count = $(".addDivHouses dl").length/4;
											
											if(count==0){
												$str='';
												$str+='<dl><label>现&nbsp;&nbsp;住&nbsp;&nbsp;址：</label>\
													<input class="contentDlInput" name="homeAddress" type="text" value=""/>\
													<input type="hidden" name="xhJksqHouses[#housesIndex].xhJksq.id" value="${loanApply.id }" />\
											    	<input type="hidden" name="xhJksqHouses[#housesIndex].id" value="" />\
												</dl>\
												<dl>\
													<label>居住成员：</label>\
													<input class="contentDlInput" name="liveTogehter"  type="text" value=""/>\
												</dl>\
												<dl>\
													<label>产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权：</label>\
													<input type="radio" name="liveState" value="01"   />自有房屋&nbsp;&nbsp;\
													<input type="radio" name="liveState" value="02"   />亲属产权&nbsp;&nbsp;\
													<input type="radio" name="liveState" value="03"   />租房&nbsp;&nbsp;\
													<input type="radio" name="liveState" value="04"   />其他\
												</dl>\
												<dl>\
													<label>租金月供：</label>\
													<input class="contenDlInput number" type="text" size="30" name="xhJksqHouses[#housesIndex].monthReturn" value="" />\
												</dl>';
												var addDivHouses = $str.replace(/#housesIndex/g,count);
												$("#addDivHouses").prepend(addDivHouses);  
											}
											//添加房产
											$(function(){  
											$("#getAtrFc").click(function(){ 
												var count = $("#addDivHouses dl").length/4;
												$str2 = '';
												$str2 += '<table class="zcxxContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000"><tr>\
													<td height="30" colspan="3" bgcolor="#ededed">\
													<a title="删除"  href="#" class="btnDel pageHousesDelete">删除</a>&nbsp;&nbsp;房产地址</td>\
														<td height="30" bgcolor="#ededed">&nbsp;产权归属</td>\
														<td height="30" bgcolor="#ededed">&nbsp;抵押或无抵押</td>\
														<td height="30" bgcolor="#ededed">&nbsp;借款佘额</td>\
														<td height="30" bgcolor="#ededed">&nbsp;估 值</td>\
													</tr>\
													<tr>\
														<td height="22" colspan="3" bgcolor="#FFFFFF">\
														<input class="contentpInput" type="text" size="30" name="xhJksqHouses[#housesIndex].address" value="" />\
														<input type="hidden" name="xhJksqHouses[#housesIndex].xhJksq.id" value="${loanApply.id }" />\
												    	<input type="hidden" name="xhJksqHouses[#housesIndex].id" value="${houses.id }" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput" type="text" size="30" name="xhJksqHouses[#housesIndex].chouseOwner" value="" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;';
												  $str2 += '<sen:select clazz="contentpInput combox" coding="guarantyType" name="xhJksqHouses[#housesIndex].chouseEndorse" />';
												  $str2 += '</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput number" type="text" size="30" name="xhJksqHouses[#housesIndex].remainmoney" value="" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput" type="text" size="30" name="xhJksqHouses[#housesIndex].chouseValue" value="" />\
														</td>\
													</tr>\
													<tr>\
													<td height="22" colspan="4" bgcolor="#FFFFFF">&nbsp;估值/确认途径：\
														<input class="contentpInput" type="text" size="30" name="xhJksqHouses[#housesIndex].chouseValueWay" value="" />\
														</td>\
														<td height="22" colspan="4" bgcolor="#FFFFFF">&nbsp;市场报价（㎡）：\
														<input class="contentpInput number" type="text" size="30" name="xhJksqHouses[#housesIndex].chouseMarchetValue" value="" />\
														</td>\
													</tr></table>';
												var addTableHouse = $str2.replace(/#housesIndex/g,count);
												$(".fc").append(addTableHouse);
												var $box = navTab.getCurrentPanel();
										   		$("a.pageHousesDelete",$box).on('click',function(){
												  var $parentTr = $(this).parents('table');
												  //alertMsg.confirm("确认删除该信用记录么！", {
												  //okCall: function(){
													   $parentTr.remove();
											      //}
												  //});
												  return false;
												});
											});
											});
											$(function(){ 
													$("#getAtrCc").click(function(){ 
														var count = $("#addDivHouses dl").length/4;
													$str3='';
													$str3+='<table class="zcxxContent" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000"><tr>\
														<td height="22" bgcolor="#ededed"></td><td height="22" bgcolor="#ededed"><a title="删除"  href="#" class="btnDel pageVechiclesDelete">删除</a>&nbsp;&nbsp;车产/品牌型号</td>\
														<td height="22" bgcolor="#ededed">&nbsp;注册日期</td>\
														<td height="22" bgcolor="#ededed">&nbsp;登记日期</td>\
														<td height="22" bgcolor="#ededed">&nbsp;产权归属</td>\
														<td height="22" bgcolor="#ededed">&nbsp;抵押或无抵押</td>\
														<td height="22" bgcolor="#ededed">&nbsp;借款佘额</td>\
														<td height="22" bgcolor="#ededed">&nbsp;估 值</td>\
													</tr>\
													<tr id="vechicles">\
													<td height="22" bgcolor="#FFFFFF">\
													\
													</td>\
													<td height="22" bgcolor="#FFFFFF">&nbsp;<input class="contentpInput" type="text" name="" value="" /></td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].registerDate" value="" />\
												    	<input type="hidden" name="xhCjksqVechicles[#vechiclesIndex].xhJksq.id" value="${loanApply.id }" />\
												    	<input type="hidden" name="xhCjksqVechicles[#vechiclesIndex].id" value="" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].dengjiDate" value="" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].owener" value="" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;';
													$str3  += '<sen:select clazz="contentpInput combox" coding="guarantyType" name="xhCjksqVechicles[#vechiclesIndex].endorse" />';
													$str3 +='</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput number" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].borrowmoney" value="" />\
														</td>\
														<td height="22" bgcolor="#FFFFFF">&nbsp;\
														<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].estimateValue" value="" />\
														</td>\
													</tr>\
													<tr>\
														<td height="22" colspan="3" bgcolor="#FFFFFF">&nbsp;估值/确认途径：\
														<input class="contentpInput" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].valueWay" value="" />\
														</td>\
														<td height="22" colspan="5" bgcolor="#FFFFFF">&nbsp;市场报价：\
														<input class="contentpInput number" type="text" size="30" name="xhCjksqVechicles[#vechiclesIndex].marchetValueComment" value="" />\
														</td>\
													</tr>\
												</table>';
												var addTableCcxx = $str3.replace(/#vechiclesIndex/g,count);
												$(".cc").append(addTableCcxx);
												var $box = navTab.getCurrentPanel();
										   		$("a.pageVechiclesDelete",$box).on('click',function(){
												  var $parentTr = $(this).parents('table');
												  //alertMsg.confirm("确认删除该信用记录么！", {
												  //okCall: function(){
													   $parentTr.remove();
											      //}
												  //});
												  return false;
												});
											});
											}); 
											
											</script>
											
														<script type="text/javascript">
														$(function(){  
														    $("#getAtrGongBank").click(function(){  
														    	var i = $(this).attr("value");
														    	var clazz = '.qyxx#i p';
														    	var i = clazz.replace(/#i/g,i);
														    	var index = $(i).length/9-1;
														    	var companyId = $(this).attr("companyId");
														    	var count = $("table.dgyhls tr").length;
														    	$str='';
														        $str+='<table class="dgyhls" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#000000">\
																  <tr>\
																    <td height="30" colspan="4" bgcolor="#FFFFFF">\
																    <a title="删除"  href="#" class="btnDel pageGongBankDelete">删除</a>\
																    &nbsp;银行名：';
																    $str+='<sen:select clazz="required" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].bank" coding="bank" value=""/>';
																    	$str+='<input type="hidden" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].xhJksqCompany.id" value="'+companyId+'" />\
																    <input type="hidden" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].id" value="" />\
																	</td>\
																    <td height="30" colspan="4" bgcolor="#FFFFFF">\
																 	&nbsp;盖章（每个月）及余额加减是否正确：\
																 	<input class="" type="radio" name="name" value="是" />是\
																 	<input class="" type="radio" name="name" value="否" />否\
																	</td>\
																  </tr>\
																  <tr>\
																    <td height="30" bgcolor="#ededed">&nbsp;</td>\
																    <td height="30" bgcolor="#ededed">&nbsp;\
																  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].one" value="" />\
																    月</td>\
																    <td height="30" bgcolor="#ededed">&nbsp;\
																  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].two" value="" />\
																    月</td>\
																    <td height="30" bgcolor="#ededed">&nbsp;\
																  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].three" value="" />\
																    月</td>\
																    <td height="30" bgcolor="#ededed">&nbsp;\
																  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].four" value="" />\
																    月</td>\
																    <td height="30" bgcolor="#ededed">&nbsp;\
																  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].five" value="" />\
																    月</td>\
																    <td height="30" bgcolor="#ededed">&nbsp;\
																  	<input class="contentpInput digits" min="1" max="12" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].six" value="" />\
																    月</td>\
																  </tr>\
																  <tr>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;流水存入</td>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;\
																  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].onecount" value="" />\
																    </td>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;\
																  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].twocount" value="" />\
																    </td>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;\
																  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].threecount" value="" />\
																    </td>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;\
																  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].fourcount" value="" />\
																    </td>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;\
																  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].fivecount" value="" />\
																    </td>\
																    <td height="22" bgcolor="#FFFFFF">&nbsp;\
																  	<input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].sixcount3" value="" />\
																    </td>\
																  </tr>\
																  <tr>\
																  <td colspan="7" height="22" bgcolor="#FFFFFF">&nbsp;备注：\
																    <input class="contentpInput" type="text" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].bankComments" size="100" value="" /></td>\
																  </tr>\
																  <tr>\
																    <td height="22" colspan="8" bgcolor="#FFFFFF">&nbsp;截止到\
																    <input type="text" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].currentDate" class="date" pattern="yyyy-MM-dd" value="" size="17" yearstart="-80" />\
																		尚有存款余额：\
																    <input class="contentpInput number" type="text" size="20" name="xhJksqCompanys[#companysIndex].xhCjksqBankRecords[#bankIndex].remainAmount" value="" />\
																		</td>\
																  </tr>\
																  <tr>\
																    <td height="22" colspan="2" bgcolor="#FFFFFF">&nbsp;其它流水统计</td>\
																    <td height="22" colspan="6" bgcolor="#FFFFFF">&nbsp;</td>\
																  </tr>\
																</table>';
														   		
														   		var addTrDown = $str.replace(/#companysIndex/g,index);
														        addTrDown = addTrDown.replace(/#bankIndex/g,count);
																var $addTrDown = $(addTrDown);
														        var $box = navTab.getCurrentPanel();
														        $("#dgyhlsxx").append($addTrDown); 
														        
														        datePicker($addTrDown);
														        
														   		$("a.pageGongBankDelete",$box).on('click',function(){
																  var $parentTr = $(this).parents('table');
																  //alertMsg.confirm("确认删除该信用记录么！", {
																  //okCall: function(){
																	   $parentTr.remove();
															      //}
																  //});
																  return false;
																});
														    });
														});
														</script>
<script>
function tabIIframeSrc(str, value){
	  $(str).attr("src","${ctx }/loan/listAuditImg?jksqId=${loanApply.id}&typeName="+value);
} 
</script>