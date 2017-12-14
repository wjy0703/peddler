package cn.com.peddler.app.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.core.context.SecurityContext;

import cn.com.peddler.app.dao.NamedJdbcDao;
import cn.com.peddler.app.service.security.OperatelogManager;




public class SessionInvalidation implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
       HttpSession session = event.getSession();
      try{
          String login_user = (String)session.getAttribute("loginTo");
          NamedJdbcDao namedJdbcDao = OperatelogManager.staticDao;
          String ip = (String)session.getAttribute("ip");
          Object obj =  session.getAttribute("SPRING_SECURITY_CONTEXT");
          if(obj != null){
              SecurityContext sc =  (SecurityContext)obj;
              Map param = new HashMap();
              param.put("CREATEBY", login_user);
              param.put("IP",ip);
              param.put("REMARKS", "退出系统");
              namedJdbcDao.getJdbcTemplate().update("insert into operatelog (createuser,createtime,ip,remarks) values(:CREATEBY,sysdate(),:IP,:REMARKS)", param);
          }
      }catch(Exception e){
          e.printStackTrace();
      }
    }
    
}    


