<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

	<!-- <h1>新增客户咨询</h1> -->
	<div class="pageContent">
			<div class="pageFormContent" >
			   <div id="myownDiv">
					<h1>该用户存在的申请记录</h1>
					<table width="100%" id = "myownRelatedItems">
						<tr>
							<th width="200"><label style="color: red">证件号码</label></th>
							<th width="60">客户姓名</th>
							<th width="60">城市</th>
							<th width="80">状态</th>
							<th width="60">产品</th>
							<th width="80">放款金额(元)</th>
							<th width="50">期数(月)</th>
							<th width="60">团队经理</th>
							<th width="60">客户经理</th>
							<th width="140">进件时间</th>
						</tr>						
					</table>
				</div>
				<div id="familyDiv">
					<h1>该客户作为一项申请记录的亲属存在的申请记录</h1>
					<table id="familyRelatedItems">
					   <tr>
							<th width="200"><label style="color: red">证件号码</label></th>
							<th width="60">客户姓名</th>
							<th width="60">城市</th>
							<th width="80">状态</th>
							<th width="60">产品</th>
							<th width="80">放款金额(元)</th>
							<th width="50">期数(月)</th>
							<th width="60">团队经理</th>
							<th width="60">客户经理</th>
							<th width="140">进件时间</th>
						</tr>		
					</table>
				</div>
				<div id="togetherDiv">
					<h1>该客户作为共借人存在的申请记录</h1>
					<table id="togetherRelatedItems">
					    <tr>
							<th width="200"><label style="color: red">证件号码</label></th>
							<th width="60">客户姓名</th>
							<th width="60">城市</th>
							<th width="80">状态</th>
							<th width="60">产品</th>
							<th width="80">放款金额(元)</th>
							<th width="50">期数(月)</th>
							<th width="60">团队经理</th>
							<th width="60">客户经理</th>
							<th width="140">进件时间</th>
						</tr>		
					</table>
				</div>
			</div>

			<div class="formBar">
				<ul>
					
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
