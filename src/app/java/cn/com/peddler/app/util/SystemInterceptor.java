package cn.com.peddler.app.util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Repository
public class SystemInterceptor extends HandlerInterceptorAdapter {


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//String servleName = request.getServletPath();
		String servleName=request.getRequestURL().toString();    
        String ctx = request.getContextPath();
        if(servleName.contains("loginSystemInterceptor")){
        	String loginTo = (String)request.getSession().getAttribute("loginTo");
            if(loginTo == null){
//            	PrintWriter out = response.getWriter();
//            	String url = ctx+"/login?error=5";
//            	out.println("<script language=\"javascript\">");
//            	out.println("window.top.location.href=\"" + url + "\";");
//            	out.println("</script>");
//            	out.close();  
            	request.getRequestDispatcher(ctx).forward(request, response);
            	
            	return false; 
            }
        }
		return super.preHandle(request, response, handler);
	}

}
