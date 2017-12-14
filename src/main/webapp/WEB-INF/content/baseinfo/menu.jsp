<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="cn.com.cucsi.app.entity.baseinfo.Menu"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<div class="accordion" fillSpace="sideBar">

	<%
List<Menu> list = (List<Menu>)request.getAttribute("menus");
List<Menu> sMenu = (List<Menu>)request.getAttribute("sMenu");
String ctx = request.getContextPath();
int last_level = 0;

for (Menu menu: list){	
	
	if(menu.getLevelId()==2 && last_level != 3){
		out.println("<div class=\"accordionHeader\">");  
		out.print("<h2><span>Folder</span>");
		out.print(menu.getMenuName());
		out.println("</h2>");
		out.println("</div>");
	}
	if(menu.getLevelId()==3 && last_level==2){
		out.println("<div class=\"accordionContent\">");
		out.println("<ul class=\"tree treeFolder\">");
	}
	if(menu.getLevelId()==3){
		out.println("<li><a "+StringUtils.replace(menu.getAttrMenuUrl(), "_CTX_", ctx)+menu.getAttrTarget()+menu.getAttrRel()+menu.getAttrTitle()+menu.getAttrExternal()+menu.getAttrFresh()+">"+menu.getMenuName()+"</a>");
		if(sMenu != null && sMenu.size() != 0){
			for(Menu m : sMenu){
				if(m.getParent().getId() == menu.getId()){
					out.println("<ul>");
					out.println("<li><a "+StringUtils.replace(m.getAttrMenuUrl(), "_CTX_", ctx)+m.getAttrTarget()+m.getAttrRel()+m.getAttrTitle()+m.getAttrExternal()+m.getAttrFresh()+">"+m.getMenuName()+"</a></li>");
					out.println("</ul>");
				}
			}
		}
		out.println("</li>");
	}
	
	if(menu.getLevelId()==2 && last_level==3){
		out.println("</ul>");
		out.println("</div>");
		out.println("<div class=\"accordionHeader\">");  
		out.print("<h2><span>Folder</span>");
		out.print(menu.getMenuName());
		out.println("</h2>");
		out.println("</div>");

	}
	last_level = menu.getLevelId();

}
%>
</div>
