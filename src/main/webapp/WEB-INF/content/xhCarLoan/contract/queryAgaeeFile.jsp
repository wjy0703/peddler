<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<!-- saved from url=(0014)about:internet  #flashContent { display:none; } --> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title></title>         
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
        <style type="text/css" media="screen"> 
			html, body	{ height:100%; }
			body { margin:0; padding:0; overflow:auto; }   
			
        </style> 
		<% if (request.getProtocol().compareTo("HTTP/1.0")==0) 
response.setHeader("Pragma","no-cache"); 
if (request.getProtocol().compareTo("HTTP/1.1")==0) 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); %>
		<script type="text/javascript" src="${ctx}/resources/FlexPaper_1.4.0_flash/js/swfobject/swfobject.js"></script>
		<script type="text/javascript" src="${ctx}/resources/FlexPaper_1.4.0_flash/js/flexpaper_flash.js"></script>
		<script type="text/javascript"> 
		//alert("${ctx}/agaeeFile/${id}/${fileName}");
		window.$FlexPaperViewer = window["$FlexPaperViewer"] = function(){
			if (window['FlexPaperViewer']) 
				return window['FlexPaperViewer']; 
			else 
				window['FlexPaperViewer'] = window.FlexPaperViewer_Instance.getApi();
			
			return window['FlexPaperViewer'];
		};
            var swfVersionStr = "10.0.0";
            var xiSwfUrlStr = "playerProductInstall.swf";
            
            var flashvars = { 
                  SwfFile : "${fp}/carLoan/${id}/${fileName}",
                  // SwfFile : "${fp}/agaeeFile/${filepath}",
                  // SwfFile : "${ctx}/agaeeFile/0310001000015/信用咨询及管理服务协议.swf",
//                  SwfFile : "${ctx}/resources/scripts/FlexPaper_1.4.0_flash/test.doc.swf",
				  Scale : 0.6, 
				  ZoomTransition : "easeOut",
				  ZoomTime : 0.5,
  				  ZoomInterval : 0.1,
  				  FitPageOnLoad : false,
  				  FitWidthOnLoad : true,
  				  PrintEnabled : true,
  				  FullScreenAsMaxWindow : false,
  				  ProgressiveLoading : true,
  				  
  				  PrintToolsVisible : true,
  				  ViewModeToolsVisible : true,
  				  ZoomToolsVisible : true,
  				  FullScreenVisible : true,
  				  NavToolsVisible : true,
  				  CursorToolsVisible : true,
  				  SearchToolsVisible : true,
  				  
  				  localeChain: "zh_CN"
				  };
				  
			 var params = {
				
			    }
            params.quality = "high";
            params.bgcolor = "#ffffff";
            params.allowscriptaccess = "sameDomain";
            params.allowfullscreen = "true";
            var attributes = {};
            attributes.id = "FlexPaperViewer";
            attributes.name = "FlexPaperViewer";
            swfobject.embedSWF(
                "${ctx}/resources/FlexPaper_1.4.0_flash/FlexPaperViewer.swf", "flashContent", 
                "100%", "800",
                swfVersionStr, xiSwfUrlStr, 
                flashvars, params, attributes);
			swfobject.createCSS("#flashContent", "display:block;text-align:left;");
function printContentFile(){
	$FlexPaperViewer().printPaper();
}			
			
        </script> 
    </head> 
    <body>
<center>
    	<div style="" >
	        <div id="flashContent" > 
	        	<p> 
		        	To view this page ensure that Adobe Flash Player version 
					10.0.0 or greater is installed. 
				</p> 

	        </div>
        </div>
        </center>   
        
   </body> 
</html> 