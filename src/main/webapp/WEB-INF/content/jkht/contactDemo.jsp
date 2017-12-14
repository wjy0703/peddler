<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<style type="text/css">

	ul.rightTools {float:right; display:block;}

	ul.rightTools li{float:left; display:block; margin-left:5px}

</style>



<div class="pageContent" style="padding:5px">

	<div class="panel" defH="40">

		<h1>鐥呬汉鍩烘湰淇℃伅</h1>

		<div>

			鐥呬汉缂栧彿锛�<input type="text" name="patientNo" />

			<ul class="rightTools">

				<li><a class="button" target="dialog" href="demo/pagination/dialog1.html" mask="true"><span>鍒涘缓鐥呬緥</span></a></li>

				<li><div class="buttonDisabled"><div class="buttonContent"><button>鐥呬汉娌荤枟娴佺▼</button></div></div></li>

				<li><div class="buttonDisabled"><div class="buttonContent"><button>鎸夌梾浜虹紪鍙锋绱㈢梾渚�</button></div></div></li>

				<li><div class="buttonDisabled"><div class="buttonContent"><button>浠庣梾浜哄垪琛ㄩ€夊彇鐥呬緥</button></div></div></li>

			</ul>

		</div>

	</div>

	<div class="divider"></div>

	<div class="tabs">

		<div class="tabsHeader">

			<div class="tabsHeaderContent">

				<ul>

					<li><a href="javascript:;"><span>瀹為獙瀹ゆ娴�</span></a></li>

					<li><a href="javascript:;"><span>鐥呬汉澶勬柟</span></a></li>

					<li><a href="javascript:;"><span>鐥呬汉鏈嶈嵂鎯呭喌</span></a></li>

					<li><a href="javascript:;"><span>鍩虹嚎璋冩煡</span></a></li>

					<li><a href="javascript:;"><span>闅忚</span></a></li>

				</ul>

			</div>

		</div>

		<div class="tabsContent">

			<div>

	

				<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">

				    <ul class="tree treeFolder">

						<li><a href="javascript">瀹為獙瀹ゆ娴�</a>

							<ul>

								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">灏挎</a></li>

								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">HIV妫€娴�</a></li>

								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">HCV妫€娴�</a></li>

								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">TB妫€娴�</a></li>

							</ul>

						</li>

						

				     </ul>

				</div>

				

				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">

					<div class="pageHeader" style="border:1px #B8D0D6 solid">

	<form id="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');" action="demo/pagination/list1.html" method="post">

	<input type="hidden" name="pageNum" value="1" />

	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />

	<input type="hidden" name="orderField" value="${param.orderField}" />

	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />

	<div class="searchBar">

		<table class="searchContent">

			<tr>

				<td class="dateRange">

					灏挎鏃ユ湡:

					<input type="text" value="" readonly="readonly" class="date" name="dateStart">

					<span class="limit">-</span>

					<input type="text" value="" readonly="readonly" class="date" name="dateEnd">

				</td>

				<td>

					灏挎缁撴灉锛�

					<input type="radio" name="njjg" value="" checked="checked" />鍏ㄩ儴

					<input type="radio" name="njjg" value="1"/>闃存€�

					<input type="radio" name="njjg" value="2"/>闃虫€�

				</td>

				<td>

					鐥呬汉缂栧彿锛�<input type="text" name="keyword" />

				</td>

				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">妫€绱�</button></div></div></td>

			</tr>

		</table>

	</div>

	</form>

</div>



<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">

<div class="panelBar">

		<ul class="toolBar">

			<li><a class="add" href="demo/pagination/dialog2.html" target="dialog" mask="true"><span>娣诲姞灏挎妫€娴�</span></a></li>

			<li><a class="delete" href="demo/pagination/ajaxDone3.html?uid={sid_obj}" target="ajaxTodo" title="纭畾瑕佸垹闄ゅ悧?"><span>鍒犻櫎</span></a></li>

			<li><a class="edit" href="demo/pagination/dialog2.html?uid={sid_obj}" target="dialog" mask="true"><span>淇敼</span></a></li>

			<li class="line">line</li>

			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" title="瀹炶瀵煎嚭杩欎簺璁板綍鍚�?"><span>瀵煎嚭EXCEL</span></a></li>

		</ul>

	</div>

	<table class="table" width="99%" layoutH="260" rel="jbsxBox">

		<thead>

			<tr>

				<th width="80">搴忓彿</th>

				<th width="120" orderField="number" class="asc">璇婃墍缂栧彿</th>

				<th orderField="name">璇婃墍鍚嶇О</th>

				<th width="100">鐥呬汉缂栧彿</th>

				<th width="100">鐥呬汉濮撳悕</th>

				<th width="120">灏挎鏃ユ湡</th>

				<th width="100">灏挎缁撴灉</th>

				<th width="80">妫€楠屾鏁�</th>

			</tr>

		</thead>

		<tbody>

			<tr target="sid_obj" rel="1">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>寮犱笁</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="2">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>鏉庡洓</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="1">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>寮犱笁</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="2">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>鏉庡洓</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="1">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>寮犱笁</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="2">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>鏉庡洓</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="1">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>寮犱笁</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="2">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>鏉庡洓</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="1">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>寮犱笁</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="2">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>鏉庡洓</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="1">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>寮犱笁</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

			<tr target="sid_obj" rel="2">

				<td>1</td>

				<td>bj0001</td>

				<td>xxx璇婃墍</td>

				<td>xxx</td>

				<td>鏉庡洓</td>

				<td>2011-9-6</td>

				<td>xxx</td>

				<td>1</td>

			</tr>

		</tbody>

	</table>

	<div class="panelBar">

		<div class="pages">

			<span>鏄剧ず</span>

			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox')">

				<option value="20">20</option>

				<option value="50">50</option>

				<option value="100">100</option>

				<option value="200">200</option>

			</select>

			<span>鏉★紝鍏�50鏉�</span>

		</div>

		

		<div class="pagination" rel="jbsxBox" totalCount="200" numPerPage="20" pageNumShown="5" currentPage="1"></div>



	</div>

</div>

				</div>

	

			</div>

			

			<div>鐥呬汉澶勬柟</div>

			

			<div>鐥呬汉鏈嶈嵂鎯呭喌</div>

			

			<div>鍩虹嚎璋冩煡</div>

			

			<div>闅忚</div>

		</div>

		<div class="tabsFooter">

			<div class="tabsFooterContent"></div>

		</div>

	</div>

	

</div>





	




</body>
</html>