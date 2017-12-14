<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>历史数据分析明细</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<script type="text/javascript" src="${ctx}/dwz/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>highcharts/highcharts.js"></script>
		<script type="text/javascript" src="<%=basePath%>highcharts/exporting.js"></script>
	</head>

	<body>
		<script type="text/javascript">
$(function() {
	var chart;
	$(document).ready(function() {
		 var options = {
		     chart: {
			      renderTo: 'container',//图表的页面显示容器
			      defaultSeriesType: 'pie',//图表的显示类型（line,spline,scatter,splinearea bar,pie,area,column）
			      marginRight: 125,//上下左右空隙
			      marginBottom: 25 //上下左右空隙
		     },
		     title: {
			      text: '历史数据分析明细',//主标题
			      x: -20 //center
		     },
		     subtitle: {
			      text: '${analyName}',//副标题
			      x: -20 //center
		     },
		     xAxis: {   //横坐标
		     },
		     yAxis: {
			       title: {
			       		text: '量' //纵坐标
		     	   },
			      plotLines: [{  //标线属性
				       value: 0,
				       width: 1,
				       color: 'red'
			      }]
    		 },
    		 tooltip: {
                 formatter: function() {
                     return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                 }
             },
             plotOptions: {
                 pie: {
                     allowPointSelect: true,
                     cursor: 'pointer',
                     dataLabels: {
                         enabled: false
                     },
                     showInLegend: true
                 }
             },
		     legend: {
			      layout: 'vertical',
			      align: 'right',
			      verticalAlign: 'top',
			      x: -10,
			      y: 100,
			      borderWidth: 0
		     },
		     series: []
    	};
		 var json = "${data}";
		 var data = eval("["+json+"]");
		 options.xAxis=data[data.length-1];
         for(var i=0;i<data.length-1;i++){
          	 options.series.push(data[i]);
         }
         chart = new Highcharts.Chart(options);
	});
	
})
		</script>
		<div id="container"
			style="min-width: 400px; height: 100%; margin: 0 auto">
			</div>
	</body>
</html>
