<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="ext/operationjs/showoperation.js" type="text/javascript"></script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${page.pageNo }" /> <input
		type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
		type="hidden" name="orderField" value="${page.orderBy}" /> <input
		type="hidden" name="orderDirection" value="${page.order}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx }/jygl/jyglHuakou" method="post">
		<div class="searchBar">
			<table>
				<tr>
					<td><label>客户编码：</label> <input type="text" name="filter_khbm"
						value="${map.khbm }" /></td>
					<td><label>客户姓名：</label> <input type="text"
						name="filter_cjrxm" value="${map.cjrxm }" /></td>
					<td><label>账单日：</label> 
					<select class="combox" name="filter_zdr">
						<option value="" <c:if test="${map.zdr==''}">selected</c:if>>全部</option>
						<option value="15" <c:if test="${map.zdr=='15'}">selected</c:if>>15</option>
						<option value="30" <c:if test="${map.zdr=='30'}">selected</c:if>>30</option>
					</select>
					</td>
					<!-- 
					<td><label>归属地区：</label> 
					<select class="combox" name="filter_gsdq">
						<option value="" <c:if test="${map.gsdq==''}">selected</c:if>>全部</option>
						<option value="0021" <c:if test="${map.gsdq=='0021'}">selected</c:if>>上海、浙江</option>
						<option value="0022" <c:if test="${map.gsdq=='0022'}">selected</c:if>>其他</option>
					</select>
					</td>
					
					<td><label>归属地区：</label> <select class="combox"
						name="filter_province" ref="combox_listZqdgcity"
						refUrl="${ctx}/cjrxx/getCity?code={value}">
							<option value="" <c:if test="${map.province==''}">selected</c:if>>所有省市</option>
							<c:forEach items="${province}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.province==md.id}">selected</c:if>>${md.name
									}</option>
							</c:forEach>
					</select> <select class="combox" name="filter_city"
						id="combox_listZqdgcity">
							<option value="" <c:if test="${map.city==''}">selected</c:if>>所有城市</option>
					</select></td>
					 -->
					 <td><label>所在城市：</label>
					 <sen:address names="filter_province,filter_city" titles="所有省市,所有城市" values="${map.province},${map.city}"/>
					</td>
					</tr>
					<tr>
					<td>
					<label>订购状态：</label>
					<select class="combox" name="filter_statedg">
						<option value="" <c:if test="${map.statedg==''}">selected</c:if>>全部</option>
						<option value="0" <c:if test="${map.statedg=='0'}">selected</c:if>>待订购</option>
						<option value="1" <c:if test="${map.statedg=='1'}">selected</c:if>>待交割</option>
						<option value="2" <c:if test="${map.statedg=='2'}">selected</c:if>>已交割</option>
						<option value="3" <c:if test="${map.statedg=='3'}">selected</c:if>>已结束</option>
						<option value="8" <c:if test="${map.statedg=='8'}">selected</c:if>>待提交划扣</option>
						<option value="7" <c:if test="${map.statedg=='7'}">selected</c:if>>待划扣审批</option>
						<option value="6" <c:if test="${map.statedg=='6'}">selected</c:if>>待结算划扣</option>
					</select>
					</td>
					<!-- 
					<td><label>债权状态：</label> 
					<select class="combox" name="filter_lent_state">
						<option value="" <c:if test="${map.lent_state==''}">selected</c:if>>全部</option>
						<option value="0" <c:if test="${map.lent_state=='0'}">selected</c:if>>首期</option>
						<option value="1" <c:if test="${map.lent_state=='1'}">selected</c:if>>非首期</option>
					</select>
					</td>
					 -->
					<td><label>出借产品：</label><select name="filter_tzcp"
						class="required combox">
							<option value="" <c:if test="${map.tzcp==''}">selected</c:if>>请选择</option>
							<c:forEach items="${tzcp}" var="md" varStatus="st">
								<option value="${md.id }"
									<c:if test="${map.tzcp==md.id}">selected</c:if>>${md.tzcpMc}</option>
							</c:forEach>
					</select>
					</td>
					<td><div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">检索</button>
										</div>
									</div></li>
								<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
							</ul>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
		<c:if test="${map.statedg =='6'|| map.statedg=='7'}">
		<bjdv:validateContent type="1" funcId="债权订购-导出划扣">
			<li>
			   <a class="icon" href="${ctx}/jygl/exportSqTjHk" 
			   target="dwzExport" targetType="navTab" title="是要导出这些记录吗?"><span>导出划扣文件</span></a>
			</li>
			
		</bjdv:validateContent>
		</c:if>
		<c:if test="${map.statedg=='7'}">
					<bjdv:validateContent type="1" funcId="债权订购-批量划扣审批">
					<li>
			   <a class="icon" target="selectedTodo" rel="ids" postType="string" 
			   warn="请至少选择一条记录" title="确实要审批这些记录吗?" 
			   href="${ctx }/jygl/lookHkSpSome?statehk_href=${map.statedg}" 
			   target="dwzExport" targetType="navTab"><span>批量划扣审批</span></a>
			</li>
					</bjdv:validateContent></c:if>
		<c:if test="${map.statedg =='6'}">
		<bjdv:validateContent type="1" funcId="债权订购-导入结算">
			<li>
			   <input type="file" name="upload" id="uploadify06" /><span>导入结算文件</span>
			</li>
		</bjdv:validateContent>
		</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="141">
		<thead>
			<tr>
				<th width="28">
				<c:if test="${map.statedg=='7'}">
				<input type="checkbox" group="ids"
					class="checkboxCtrl">
					</c:if></th>
				<th width="150">出借编号</th>
				<th width="80">客户姓名</th>
				<th width="80">客户编码</th>
				<th width="80">所在城市</th>
				<th width="80">计划出借日期</th>
				<th width="80">计划出借金额</th>
				<th width="80">出借产品</th>
				<th width="10%">推荐债权价值</th>
				<th width="10%">债权状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listZqtj}" var="zd" varStatus="st">
				<tr target="sid_zd" rel="${zd.ID}">
				<td><c:if test="${zd.STATEDG=='7'}">
				<input id="ids" name="ids" value="${zd.ID}" type="checkbox"></c:if></td>
					<td>${zd.TZSQBH}</td>
					<td>${zd.CJRXM }</td>
					<td style="text-align: right">${zd.KHBM }</td>
					<td>${zd.CITYNAME }</td>
					<td>${zd.JHTZRQ}</td>
					<td>￥<fmt:formatNumber
							value="${zd.MONEY}" pattern="#,#00.00" /></td>
					<td>${zd.TZCP_MC}</td>
					<td>￥<fmt:formatNumber
							value="${zd.MONEY}" pattern="#,#00.00" /></td>
					<td>
					<c:if test="${zd.LENT_STATE=='0'}">首期</c:if>
					<c:if test="${zd.LENT_STATE=='1'}">非首期</c:if>
					
					<c:if test="${zd.STATEDG=='0'}">/待订购</c:if>
					<c:if test="${zd.STATEDG=='1'}">/待交割</c:if>
					<c:if test="${zd.STATEDG=='2'}">/已交割</c:if>
					<c:if test="${zd.STATEDG=='3'}">/已结束</c:if>
					<c:if test="${zd.STATEDG=='8'}">/待提交划扣</c:if>
					<c:if test="${zd.STATEDG=='7'}">/待划扣审批</c:if>
					<c:if test="${zd.STATEDG=='6'}">/待结算划扣</c:if>
					</td>
					<td>
					<div class="buttonActive">
							<div class="buttonContent">
								<button onclick="return showOrHide(this);">操作</button>
							</div>
						</div>
					
					<div  class='OperationsPopUp' style='display: none; position: absolute;  
						
						text-align: justify; font-size: 12px;height:40px;width:300px;  filter:alpha(opacity=90);
    opacity:0.6;
    background:#fff;
    border:2px orange solid;
    padding:20px;
    padding-left:20px;
   word-wrap:break-word;
    line-height:100%;
'>

					<c:if test="${zd.STATEDG=='8'}">
					<bjdv:validateContent type="1" funcId="债权订购-债权划扣">
					<div class="buttonActive">
				     <div class="buttonContent">
						<a title="债权划扣" target="navTab"
						href="${ctx }/jygl/lookZqhk?id=${zd.ID}&statehk_href=${map.statedg}">债权划扣</a>
						</div>
		            </div>
		            </bjdv:validateContent>
		            <bjdv:validateContent type="1" funcId="投资申请-发送债权">
			             <div class="buttonActive">
					          <div class="buttonContent">
							<a title="发送首期债权" target="ajaxTodo"
							href="${ctx }/xhTzsq/sendSqZqtj?id=${zd.TZSQ_ID}">发送首期债权</a>
							</div>
			             </div>
			        </bjdv:validateContent>
					</c:if>	
					<c:if test="${zd.STATEDG=='7'}">
					<bjdv:validateContent type="1" funcId="债权订购-划扣审批">
					<div class="buttonActive">
				     <div class="buttonContent">
						<a title="债权划扣" target="navTab"
						href="${ctx }/jygl/lookHkSp?id=${zd.ID}&statehk_href=${map.statedg}">划扣审批</a>
						</div>
		            </div>
		            </bjdv:validateContent>
					</c:if>	
					
					<c:if test="${zd.STATEDG=='6'}">
					<bjdv:validateContent type="1" funcId="债权订购-结算划扣">
					<div class="buttonActive">
				     <div class="buttonContent">
						<a title="债权划扣" target="navTab"
						href="${ctx }/jygl/lookHkSpEnd?id=${zd.ID}&statehk_href=${map.statedg}">结算划扣</a>
						</div>
		            </div>
		            </bjdv:validateContent>
					</c:if>
					<div class="divider"></div>
		</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"
					<c:if test="${page.pageSize=='10'}">selected</c:if>>10</option>
				<option value="20"
					<c:if test="${page.pageSize=='20'}">selected</c:if>>20</option>
				<option value="50"
					<c:if test="${page.pageSize=='50'}">selected</c:if>>50</option>
				<option value="100"
					<c:if test="${page.pageSize=='100'}">selected</c:if>>100</option>
				<option value="200"
					<c:if test="${page.pageSize=='200'}">selected</c:if>>200</option>
			</select> <span>条，共${page.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }"></div>

	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	  	$("#uploadify06").uploadify({
			'removeCompleted' : false,
			'swf' : 'uploadify/threetwo/uploadify.swf',
			'uploader' : '${ctx}/jygl/uploadHkSp?gsdq=${map.gsdq}',
			 buttonText :'上传结算划扣记录',
			// buttonImage:'uploadify/threetwo/add.jpg',
			'auto' : true,
			'multi' : false,
			'successTimeout' : 99999,
			'fileTypeExts' : '*.xls;',
			'fileSizeLimit' : '10MB',
			'uploadLimit': 10,
			 width:100,
     		 'onUploadSuccess':function(file,data){//上传单个文件成功调用的方法（注：uploadify多文件上传也是发送了多次请求，对应多个request）,其中第二个参数为data数据。
			    var $ajaxbg = $("#background,#progressBar");
      		    $ajaxbg.hide();
      		    data = $.parseJSON(data);
      		    var msg = '成功划扣' + data.count + '条数据';
				var errors = data.errors;
				if(errors.length != 0){
					msg += '<br><font color = "red" >失败记录：</font>'; 
      		    }
				for(var index = 0 ; index < errors.length ; index++){
					var error = errors[index];
					msg += '<br> 客户编号为 <font color = "red" >' + error.id + ':</font> ' + error.errorMsg; 
				}
				if(errors.length != 0){
      		    	alertMsg.error(msg);
      		    }else{
      		    	alertMsg.info(msg);
      		    }
      		    setTimeout(function(){
      		        $("#alertMsgBox").find('div.alertInner>h1').html('批量划扣结果')
      		    	$("#alertMsgBox").css('width','350px');
      		    },250);
      		    navTab.reload();
			 },
      		 onUploadStart:function(){
      		   var $ajaxbg = $("#background,#progressBar");
      		   $ajaxbg.show();
      		}
		});
	});
</script>

