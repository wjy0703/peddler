<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="tabs">
	<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>
				<li class="selected"><a href="#"><span>基础信息</span></a></li>
				<li><a href="#"><span>债权申请信息</span></a></li>
			</ul>
		</div>
	</div>
	<form method="post" action="${ctx}/cjrxx/savecjrxx"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${cjrxx.id}" />
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
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
		<div class="tabsContent" style="height: 100%;">
			<div>
				<div class="pageFormContent" layoutH="90">
					<table>
						<tr>
							<td><label>客户姓名：</label> <input name="cjrxm" type="text"
								size="30" value="${cjrxx.cjrxm }" class="required"
								maxlength="10" /></td>
							<td><label>英文名称：</label> <input name="ywmc" type="text"
								size="30" value="${cjrxx.ywmc }" maxlength="10" /></td>
						</tr>
						<tr>
							<td><label>客户编码：</label> <input name="khbm" type="text"
								size="30" value="${cjrxx.khbm }" maxlength="10" /></td>
							<td><label>客户称呼：</label> <select name="khch"
								class="required combox">
									<option value="0"
										<c:if test="${cjrxx.khch=='0'}">selected</c:if> selected>先生</option>
									<option value="1"
										<c:if test="${cjrxx.khch=='1'}">selected</c:if>>女士</option>
							</select></td>
						</tr>
						<tr>
							<td><label>证件类型：</label> <select name="zjlx"
								class="required combox">
									<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>请选择</option>
									<c:forEach items="${zjlx}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${cjrxx.zjlx==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select></td>
							<td><label>证件号码：</label> <input name="zjhm" type="text"
								size="30" value="${cjrxx.zjhm }" class="required" maxlength="40" />
							</td>
						</tr>
						<tr>
							<td><label>签发日期：</label> <input type="text" name="zjqfrq"
								class="date" pattern="yyyy-MM-dd" value="${cjrxx.zjqfrq }"
								size="30" /></td>
							<td><label>失效日期：</label> <input type="text" name="qjsxrq"
								class="date" pattern="yyyy-MM-dd" value="${cjrxx.qjsxrq }"
								size="30" /></td>
						</tr>
						<tr>
							<td><label>发证机关：</label> <input name="fzjg" type="text"
								size="30" value="${cjrxx.fzjg }" maxlength="40" /></td>
							<td><label>性别：</label> <select name="cjrxb"
								class="required combox">
									<option value="0"
										<c:if test="${cjrxx.cjrxb=='0'}">selected</c:if> selected>男</option>
									<option value="1"
										<c:if test="${cjrxx.cjrxb=='1'}">selected</c:if>>女</option>
							</select></td>
						</tr>
						<tr>
							<td><label>出生日期：</label> <input type="text" name="csrq"
								class="date" pattern="yyyy-MM-dd" value="${cjrxx.csrq }"
								size="30" /></td>
							<td><label>是否已婚：</label> <select name="hyzk"
								class="required combox">
									<option value="0"
										<c:if test="${cjrxx.hyzk=='0'}">selected</c:if> selected>是</option>
									<option value="1"
										<c:if test="${cjrxx.hyzk=='1'}">selected</c:if>>否</option>
							</select></td>
						</tr>
						<tr>
							<td><label>移动电话：</label> <input name="yddh" type="text"
								size="30" value="${cjrxx.yddh }" class="required" maxlength="40" />
							</td>
							<td><label>固定电话：</label> <input name="gddh" type="text"
								size="30" value="${cjrxx.gddh }" class="required" maxlength="40" />
							</td>
						</tr>
						<tr>
							<td><label>邮编：</label> <input name="yb" type="text"
								size="30" value="${cjrxx.yb }" maxlength="40" /></td>
							<td><label>电子邮箱：</label> <input name="dzyx" type="text"
								size="30" value="${cjrxx.dzyx }" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>推荐人：</label> <input name="tjr" type="text"
								size="30" value="${cjrxx.tjr }" maxlength="80" /></td>
							<td><label>客户来源：</label> <input name="khly" type="text"
								size="30" value="${cjrxx.khly }" class="required" maxlength="80" />
							</td>
						</tr>
						<tr>
							<td><label>客户开发CRM：</label> <input type="hidden"
								name="employeeCrm.id" value="${cjrxx.employeeCrm.id}" /> <input
								type="text" id="empname" class="textInput"
								name="employeeCrm.name" value="${cjrxx.employeeCrm.name }"
								suggestFields="name,deptname"
								suggestUrl="${ctx }/baseinfo/suggestemployee"
								lookupGroup="employeeCrm" /> <a class="btnLook"
								href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCrm"><hi:text
										key="查找带回" /></a></td>
							<td><label>客户开发CCA：</label> <input type="hidden"
								name="employeeCca.id" value="${cjrxx.employeeCca.id}" /> <input
								type="text" id="empname" class="textInput"
								name="employeeCca.name" value="${cjrxx.employeeCca.name }"
								suggestFields="name,deptname"
								suggestUrl="${ctx }/baseinfo/suggestemployee"
								lookupGroup="employeeCca" /> <a class="btnLook"
								href="${ctx }/baseinfo/emplookup" lookupGroup="employeeCca"><hi:text
										key="查找带回" /></a></td>
						</tr>
						<tr>
							<td><label>行业：</label> <input name="hy" type="text"
								size="30" value="${cjrxx.hy }" maxlength="40" /></td>
							<td><label>工作单位：</label> <input name="gzdwmc" type="text"
								size="30" value="${cjrxx.gzdwmc }" maxlength="100" /></td>
						</tr>
						<tr>
							<td><label>职务：</label> <input name="zw" type="text"
								size="30" value="${cjrxx.zw }" maxlength="40" /></td>
							<td><label>客户传真：</label> <input name="cjrfx" type="text"
								size="30" value="${cjrxx.cjrfx }" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>客户意向：</label> <select name="cjryx"
								class="required combox">
									<option value=""
										<c:if test="${cjrxx.cjryx==''}">selected</c:if>>请选择</option>
									<c:forEach items="${tzcp}" var="md" varStatus="st">
										<option value="${md.value }"
											<c:if test="${cjrxx.cjryx==md.value}">selected</c:if>>${md.name
											}</option>
									</c:forEach>
							</select></td>
							<td><label>使用语言：</label> <input name="yy" type="text"
								size="30" value="${cjrxx.yy }" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>办公室电话：</label> <input name="officetel"
								type="text" size="30" value="${cjrxx.officetel }" maxlength="80" />
							</td>
							<td><label>首次联系日期：</label> <input type="text"
								name="optionTime" class="date" pattern="yyyy-MM-dd"
								value="${cjrxx.optionTime }" size="30" /></td>
						</tr>
						<tr>
							<td><label>可供联系时间：</label> <input name="kglxsj" type="text"
								size="30" value="${cjrxx.kglxsj }" maxlength="80" /></td>
						</tr>
					</table>
					<dl class="nowrap">
						<dt>通讯地址：</dt>
						<dd>
							<c:if test="${cjrxx.gj!=null}">
								<input name="gj" type="text" size="5" value="${cjrxx.gj }"
									class="required" maxlength="80" />
							</c:if>
							<c:if test="${cjrxx.gj==null}">
								<input name="gj" type="text" size="5" value="中国"
									class="required" maxlength="80" />
							</c:if>
							<select class="combox" name="province" ref="combox_city"
								refUrl="${ctx}/cjrxx/getCity?code={value}">
								<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有省市</option>
								<c:forEach items="${province}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${cjrxx.province==md.id}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
							</select> <select class="combox" name="city" id="combox_city"
								ref="combox_area" refUrl="${ctx}/cjrxx/getArea?code={value}">
								<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有城市</option>
							</select> <select class="combox" name="area" id="combox_area">
								<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有区县</option>
							</select>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>CRM管辖城市：</dt>
						<dd>
							<select class="combox" name="crmprovince" ref="combox_crmcity"
								refUrl="${ctx}/cjrxx/getCity?code={value}">
								<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有省市</option>
								<c:forEach items="${province}" var="md" varStatus="st">
									<option value="${md.id }"
										<c:if test="${cjrxx.crmprovince==md.id}">selected</c:if>>${md.name
										}</option>
								</c:forEach>
							</select> <select class="combox" name="crmcity" id="combox_crmcity"
								ref="combox_crmarea" refUrl="${ctx}/cjrxx/getArea?code={value}">
								<option value="" <c:if test="${cjrxx.zjlx==''}">selected</c:if>>所有城市</option>
							</select>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>备注</dt>
						<dd>
							<textarea name="remark" style="width: 93%; height: 80">${cjrxx.remark}</textarea>
						</dd>
					</dl>
					<div class="divider"></div>
					<table>
						<tr>
							<td><label>账单邮寄地址：</label> <input name="accmailaddress"
								type="text" size="30" value="${cjrxx.accmailaddress}"
								maxlength="80" /></td>
							<td><label>母亲姓名：</label> <input name="mqxm" type="text"
								size="30" value="${cjrxx.mqxm}" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>倾向联系方式：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>账单寄送方式*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>付款方式*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>预计三个月投资总额：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
					</table>
					<dl class="nowrap">
						<dt>投资付款使用的银行账户*：</dt>
						<dd>
							<table>
								<tr>
									<td>开户行：</td>
									<td><select name="" class="required combox">
											<option value="">请选择</option>
											<option value="0">建设银行</option>
											<option value="1">交通银行</option>
									</select></td>
									<td>卡性质：</td>
									<td><select name="" class="required combox">
											<option value="">请选择</option>
											<option value="0">借记卡</option>
											<option value="1">信用卡</option>
									</select></td>
									<td>具体支行：</td>
									<td><input name="" type="text" size="20" value=""
										maxlength="80" /></td>
								</tr>
								<tr>
									<td>开户姓名：</td>
									<td><input name="" type="text" size="10" value=""
										maxlength="80" /></td>
									<td>账户：</td>
									<td><input name="" type="text" size="15" value=""
										maxlength="80" /></td>
									<td>确认账户：</td>
									<td><input name="" type="text" size="15" value=""
										maxlength="80" /></td>
								</tr>
							</table>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>回收资金使用的银行账户*：</dt>
						<dd>
							<table>
								<tr>
									<td>开户行：</td>
									<td><select name="" class="required combox">
											<option value="">请选择</option>
											<option value="0">建设银行</option>
											<option value="1">交通银行</option>
									</select></td>
									<td>卡性质：</td>
									<td><select name="" class="required combox">
											<option value="">请选择</option>
											<option value="0">借记卡</option>
											<option value="1">信用卡</option>
									</select></td>
									<td>具体支行：</td>
									<td><input name="" type="text" size="20" value=""
										maxlength="80" /></td>
								</tr>
								<tr>
									<td>开户姓名：</td>
									<td><input name="" type="text" size="10" value=""
										maxlength="80" /></td>
									<td>账户：</td>
									<td><input name="" type="text" size="15" value=""
										maxlength="80" /></td>
									<td>确认账户：</td>
									<td><input name="" type="text" size="15" value=""
										maxlength="80" /></td>
								</tr>
							</table>
						</dd>
					</dl>
					<table>
						<tr>
							<td><label>是否签订委托协议*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>协议签订日期*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>委托协议种类*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>委托协议版本号*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>广告接收方式：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>资金来源：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>年收入：</label> <input name="" type="text" size="30"
								value="" maxlength="80" /></td>
							<td><label>客户对信和兴趣的地方：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>银行存款投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>股票投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>基金投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>保险投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>债权投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>其他投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>房产投资比例%：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>高风险投资期待回报率：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>低风险投资期待回报率：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>申请历史：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
					</table>
					<dl class="nowrap">
						<dt>申请单原件：</dt>
						<dd>
							<input name="" type="file" size="30" value="" />
						</dd>
					</dl>
					<table>
						<tr>
							<td><label>紧急联系人姓名*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>与本人关系：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人移动电话*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人固定电话：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人证件类型*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人证件号码*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人身份证签发日期：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人身份证失效日期：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人英文名：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人称呼：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人行业：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人办公室电话：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人国家：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人工作单位：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人职务：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人传真：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>紧急联系人邮箱：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>紧急联系人地址：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div>
				<div class="pageFormContent" layoutH="90">
					<table>
						<tr>
							<td><label>合同编号：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>计划投资日期*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>投资方式*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>计划投资金额*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>回收方式*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>付款方式*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>是否风险金补偿*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>计划划扣日期：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>申请日期*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>部门主管*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>委托协议版本号*：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
							<td><label>销售折扣率：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
						<tr>
							<td><label>销售折扣率失效时间：</label> <input name="" type="text"
								size="30" value="" maxlength="80" /></td>
						</tr>
					</table>
					<dl class="nowrap">
						<dt>备注：</dt>
						<dd>
							<textarea name="" style="width: 93%; height: 80"></textarea>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>申请单原件：</dt>
						<dd>
							<input name="" type="file" size="30" value="" />
						</dd>
					</dl>
				</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</form>
</div>