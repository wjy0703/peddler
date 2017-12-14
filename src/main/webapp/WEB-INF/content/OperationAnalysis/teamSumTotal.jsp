<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/highcharts/initHighcharts.js"></script>

<div class="pageContent" >
<div class="pageHeader">
	
	<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
	    <ul class="tree treeFolder">
	    	<c:forEach items="${mendianTree}" var="son" varStatus="st">
			<li><a href="${ctx }/analysis/getTeamSumTotal/${son.ID }" target="navTab" rel="rel_getTeamSumTotal" title="团队金额汇总">${son.RGANI_NAME }</a>
			</li>
			</c:forEach>
	     </ul>
	</div>
					
	<div id="cfmdBox" class="unitBox" style="margin-left:246px;">
	
		<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
			<div id="${render}" style="min-width: 400px; height: 100%; margin: 0 auto"></div>
		</div>
	</div>
		
</div>
</div>
<script type="text/javascript">
var res = "${isRes}";
if(res == "true"){
	initLineHighcharts("${render}", "${title}", "${subtitle}", "${data}");
}
</script>