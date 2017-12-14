<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/loan/saveOpenXhcfDkrxx"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhcfDkrxx.id}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户编号：</label> <input name="khbm" type="text" size="30"
					value="${xhcfDkrxx.khbm }" maxlength="10" />
			</p>
			<p>
				<label>客户姓名：</label> <input name="jkrxm" type="text" size="30"
					value="${xhcfDkrxx.jkrxm }" class="required" maxlength="10" />
			</p>
			<p>
				<label>证件号码：</label> <input name="zjhm" type="text" size="30"
					value="${xhcfDkrxx.zjhm }" class="required" maxlength="40" />
			</p>
			<p>
				<label>性别：</label> <select name="xb" class="required combox">
					<option value="男" <c:if test="${xhcfDkrxx.xb=='男'}">selected</c:if>
						selected>男</option>
					<option value="女" <c:if test="${xhcfDkrxx.xb=='女'}">selected</c:if>>女</option>
				</select>
			</p>
			<p>
				<label>出生日期：</label> <input type="text" name="csrq" class="date"
					pattern="yyyy-MM-dd" value="${xhcfDkrxx.csrq }" size="30" />
			</p>
			<p>
				<label>身份证签发日期：</label> <input type="text" name="zjqfrq"
					class="date" pattern="yyyy-MM-dd" value="${xhcfDkrxx.zjqfrq }"
					size="30" />
			</p>
			<p>
				<label>身份证失效日期：</label> <input type="text" name="qjsxrq"
					class="date" pattern="yyyy-MM-dd" value="${xhcfDkrxx.qjsxrq }"
					size="30" />
			</p>
			<p>
				<label>发证机关所在地：</label> <input name="fzjg" type="text" size="30"
					value="${xhcfDkrxx.fzjg }" maxlength="40" />
			</p>
			<p>
				<label>行业：</label> <input name="dkrhy" type="text" size="30"
					value="${xhcfDkrxx.dkrhy }" maxlength="40" />
			</p>
			<p>
				<label>工作单位：</label> <input name="gzdwmc" type="text" size="30"
					value="${xhcfDkrxx.gzdwmc }" maxlength="100" />
			</p>
			<p>
				<label>职务：</label> <input name="zw" type="text" size="30"
					value="${xhcfDkrxx.zw }" maxlength="40" />
			</p>
			<p>
				<label>移动电话：</label> <input name="yddh" type="text" size="30"
					value="${xhcfDkrxx.yddh }" class="required" maxlength="40" />
			</p>
			<p>
				<label>家庭电话：</label> <input name="jtdh" type="text" size="30"
					value="${xhcfDkrxx.jtdh }" class="required" maxlength="40" />
			</p>

			<p>
				<label>邮政编码：</label> <input name="yb" type="text" size="30"
					value="${xhcfDkrxx.yb }" maxlength="40" />
			</p>
			<p>
				<label>固定电话：</label> <input name="gddh" type="text" size="30"
					value="" class="required" maxlength="40" />
			</p>
			<p>
				<label>传真：</label> <input name="cz" type="text" size="30" value=""
					maxlength="80" />
			</p>
			<p>
				<label>电子邮箱：</label> <input name="dzyx" type="text" size="30"
					value="${xhcfDkrxx.dzyx }" maxlength="80" />
			</p>

			<dl class="nowrap">
				<dt>所在城市：</dt>
				<dd>
					<c:if test="${xhcfDkrxx.gj!=null}">
						<input name="gj" type="text" size="5" value="${xhcfDkrxx.gj }"
							class="required" maxlength="80" />
					</c:if>
					<c:if test="${xhcfDkrxx.gj==null}">
						<input name="gj" type="text" size="5" value="中国" class="required"
							maxlength="80" />
					</c:if>
					<select class="combox" name="province" ref="combox_city"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
						<option value=""
							<c:if test="${xhcfDkrxx.zjlx==''}">selected</c:if>>所有省市</option>
						<c:forEach items="${province}" var="md" varStatus="st">
							<!-- 暂时 -->
							<option value="${md.id }"
								<c:if test="${xhcfDkrxx.qybm==md.id}">selected</c:if>>${md.name
								}</option>
						</c:forEach>
					</select> <select class="combox" name="city" id="combox_city"
						ref="combox_area" refUrl="${ctx}/cjrxx/getArea?code={value}">
						<option value=""
							<c:if test="${xhcfDkrxx.zjlx==''}">selected</c:if>>所有城市</option>
					</select> <select class="combox" name="area" id="combox_area">
						<option value=""
							<c:if test="${xhcfDkrxx.zjlx==''}">selected</c:if>>所有区县</option>
					</select>
				</dd>
			</dl>
			<div class="divider"></div>
			<div class="formBar">
				<ul>
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
	</form>
</div>
