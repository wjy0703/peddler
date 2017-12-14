<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/highcharts/initHighcharts.js"></script>

<div class="pageHeader">
	<form  onsubmit="return navTabSearch(this);"
		action="${ctx }/analysis/getRegionalSumTotal" method="post">
		<div class="searchBar">
			<table  width="100%">
				<tr>
				<!-- 隐藏统计时间
					<td>
					<label>统计时间:</label>
					<input type="text" name="filter_startDate" class="date" style="width: 65px" readonly="true" class="required" value="${map.startDate }"/>
					至
					<input type="text" name="filter_endDate" class="date" style="width: 65px" readonly="true" class="required" value="${map.endDate }"/>
					</td>
				 -->
					<td>
					<label>显示类型：</label> 
					<select name="filter_type" class="required combox">
						<option value="pie" <c:if test="${map.type=='pie'}">selected</c:if>selected>饼型</option>
						<option value="column" <c:if test="${map.type=='column'}">selected</c:if>>柱型</option>
					</select>
					</td>
					<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">统计</button>
									</div>
								</div></li>
							<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
						</ul>
					</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div id="${render}" style="min-width: 400px; height: 100%; margin: 0 auto"></div>

<script type="text/javascript">
initHighcharts("${map.type}", "${render}", "${title}", "${subtitle}", "${data}");
</script>