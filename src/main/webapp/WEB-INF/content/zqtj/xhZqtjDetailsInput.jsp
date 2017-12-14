<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form method="post" action="${ctx}/xhZqtjDetails/saveXhZqtjDetails" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${xhZqtjDetails.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>债权推荐ID：</label>
				<input name="zqtjId" type="text" size="30" value="${xhZqtjDetails.zqtjId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>债权推荐ID：</label>
				<input name="zqtjId" type="text" size="30" value="${xhZqtjDetails.zqtjId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>资金：</label>
				<input name="money" type="text" size="30" value="${xhZqtjDetails.money}" class="required" maxlength="22" />
			</p>
			<p>
				<label>资金：</label>
				<input name="money" type="text" size="30" value="${xhZqtjDetails.money}" class="required" maxlength="22" />
			</p>
			<p>
				<label>还款周期：</label>
				<input name="hkzq" type="text" size="30" value="${xhZqtjDetails.hkzq}" class="required" maxlength="20" />
			</p>
			<p>
				<label>还款周期：</label>
				<input name="hkzq" type="text" size="30" value="${xhZqtjDetails.hkzq}" class="required" maxlength="20" />
			</p>
			<p>
				<label>可用债权价值ID：</label>
				<input name="kyzqjzId" type="text" size="30" value="${xhZqtjDetails.kyzqjzId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>可用债权价值ID：</label>
				<input name="kyzqjzId" type="text" size="30" value="${xhZqtjDetails.kyzqjzId}" class="required" maxlength="22" />
			</p>
			<p>
				<label>债权持有比例：</label>
				<input name="zqcybi" type="text" size="30" value="${xhZqtjDetails.zqcybi}" class="required" maxlength="22" />
			</p>
			<p>
				<label>债权持有比例：</label>
				<input name="zqcybi" type="text" size="30" value="${xhZqtjDetails.zqcybi}" class="required" maxlength="22" />
			</p>
			<p>
				<label>剩余期数：</label>
				<input name="hkzqSy" type="text" size="30" value="${xhZqtjDetails.hkzqSy}" class="required" maxlength="20" />
			</p>
			<p>
				<label>剩余期数：</label>
				<input name="hkzqSy" type="text" size="30" value="${xhZqtjDetails.hkzqSy}" class="required" maxlength="20" />
			</p>
			<p>
				<label>剩余资金：</label>
				<input name="moneySy" type="text" size="30" value="${xhZqtjDetails.moneySy}" class="required" maxlength="22" />
			</p>
			<p>
				<label>剩余资金：</label>
				<input name="moneySy" type="text" size="30" value="${xhZqtjDetails.moneySy}" class="required" maxlength="22" />
			</p>
			<p>
				<label>每月还本金：</label>
				<input name="moneyMonth" type="text" size="30" value="${xhZqtjDetails.moneyMonth}" class="required" maxlength="22" />
			</p>
			<p>
				<label>每月还本金：</label>
				<input name="moneyMonth" type="text" size="30" value="${xhZqtjDetails.moneyMonth}" class="required" maxlength="22" />
			</p>
			<p>
				<label>每月还利息：</label>
				<input name="zqlixiMonth" type="text" size="30" value="${xhZqtjDetails.zqlixiMonth}" class="required" maxlength="22" />
			</p>
			<p>
				<label>每月还利息：</label>
				<input name="zqlixiMonth" type="text" size="30" value="${xhZqtjDetails.zqlixiMonth}" class="required" maxlength="22" />
			</p>
			<p>
				<label>每月还利息(首个)：</label>
				<input name="zqlixiMonthSg" type="text" size="30" value="${xhZqtjDetails.zqlixiMonthSg}" class="required" maxlength="22" />
			</p>
			<p>
				<label>每月还利息(首个)：</label>
				<input name="zqlixiMonthSg" type="text" size="30" value="${xhZqtjDetails.zqlixiMonthSg}" class="required" maxlength="22" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
