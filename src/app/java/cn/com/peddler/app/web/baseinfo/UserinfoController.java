package cn.com.peddler.app.web.baseinfo;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.peddler.app.entity.security.Userinfo;
import cn.com.peddler.app.service.login.UserinfoManager;
import cn.com.peddler.app.service.security.OperatorDetails;
import cn.com.peddler.app.util.HibernateAwareBeanUtilsBean;
import cn.com.peddler.core.security.springsecurity.SpringSecurityUtils;
import cn.com.peddler.core.utils.EncodeUtils;
import cn.com.peddler.core.web.DwzResult;
import cn.com.peddler.core.web.ServletUtils;


@Controller
@RequestMapping(value="/userinfo")
public class UserinfoController {
	private Logger logger = LoggerFactory.getLogger(UserinfoController.class);
	
	private UserinfoManager userinfoManager;
	@Autowired
	public void setUserinfoManager(UserinfoManager userinfoManager) {
		this.userinfoManager = userinfoManager;
	}
	//listTzsq.get(i).put("YYB", departmentManager.getDepartmentName(Long.parseLong(listTzsq.get(i).get("ORGANI_ID")+"")));
	//list.get(i).put("PROVINCE", addressManager.getCityNameById(Long.parseLong(list.get(i).get("PROVINCE").toString())));
	@RequestMapping(value = "/editLoginUser", method = RequestMethod.GET)
	public String editLoginUser(Model model, HttpServletResponse response) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		Userinfo user = userinfoManager.getUserinfo(operator.getUserId());
		if (user != null) {
			model.addAttribute("user", user);
			return "customer/userInput";
		} else {
			DwzResult success = new DwzResult("300", "用户不存在", "", "", "", "");
			ServletUtils.renderJson(response, success);
			return null;
		}
	}
	
	@RequestMapping(value="/saveuser",method=RequestMethod.POST)
	public String save(@ModelAttribute("user") Userinfo user, HttpServletRequest request, HttpServletResponse response){
		Userinfo userinfo = userinfoManager.getUserinfo(user.getId());
       
        try {
            // 拷贝页面的值
        	 new HibernateAwareBeanUtilsBean().copyProperties(userinfo, user);
        	 userinfoManager.saveUserinfo(userinfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("拷贝借款申请记录出现错误，请联系管理员");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("拷贝借款申请记录出现错误，请联系管理员");
        }
		DwzResult success = new DwzResult("200","保存成功","","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/chksession")
	public String sessionTimeOut(HttpServletResponse response){
		DwzResult success = new DwzResult("301","会话过期，请重新登录！","","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/password")
	public String password(Model model){
		String loginName = SpringSecurityUtils.getCurrentUserName();
		model.addAttribute("loginName", loginName);
		return "customer/password";
	}
	@RequestMapping(value="/resetPass")
	public String resetPass(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		userinfoManager.resetPass(Ids);
		DwzResult success = null;
		
		success = new DwzResult("200","密码修改成功！","rel_listuser","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	@RequestMapping(value="/savepassword",method=RequestMethod.POST)
	public String savepass(HttpServletRequest request, HttpServletResponse response){
		String loginName = request.getParameter("loginName");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("password");
		String passWordMd5 = EncodeUtils.getMd5PasswordEncoder(oldpassword,loginName);
		
		Userinfo u = userinfoManager.findUserByLoginName(loginName);
		DwzResult success = new DwzResult("200","密码修改成功!","","","closeCurrent","");
		if (u.getPassword().equals(passWordMd5)){
			u.setPassword(EncodeUtils.getMd5PasswordEncoder(newpassword,loginName));
			userinfoManager.saveUser(u);
		}
		else{
			success = new DwzResult("300","原密码不正确，修改密码不成功！","","","closeCurrent","");
		}
	
		ServletUtils.renderJson(response, success);
		return null;
	}
	@RequestMapping(value="/ajaxloginsuccess")
	public String ajaxLoginSuccess(HttpServletResponse response){
		DwzResult success = new DwzResult("200","登录成功！","","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
}
