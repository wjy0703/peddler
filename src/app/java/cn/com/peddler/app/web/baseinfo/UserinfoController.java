package cn.com.peddler.app.web.baseinfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.peddler.app.entity.login.Organizeinfo;
import cn.com.peddler.app.entity.security.Userinfo;
import cn.com.peddler.app.service.baseinfo.OrganizeinfoManager;
import cn.com.peddler.app.service.login.UserinfoManager;
import cn.com.peddler.app.service.security.OperatorDetails;
import cn.com.peddler.app.util.HibernateAwareBeanUtilsBean;
import cn.com.peddler.app.util.PropertiesUtils;
import cn.com.peddler.core.security.springsecurity.SpringSecurityUtils;
import cn.com.peddler.core.utils.EncodeUtils;
import cn.com.peddler.core.web.DwzResult;
import cn.com.peddler.core.web.ServletUtils;


@Controller
@RequestMapping(value="/userinfo")
public class UserinfoController {
	private Logger logger = LoggerFactory.getLogger(UserinfoController.class);
	
	@Autowired
	private OrganizeinfoManager organizeinfoManager;
	
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
		String canLook = PropertiesUtils.putBusidLook();
		if (user != null) {
			model.addAttribute("canLook", canLook);
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
	

	@RequestMapping(value = "/addemployee", method = RequestMethod.GET)
	public ModelAndView addUser() {
		return new ModelAndView("customer/employee-input", "userinfo",
				new Userinfo());
	}
	
	/**
	 * 组织机构添加员工 MDY
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTreeUser/{Id}", method = RequestMethod.GET)
    public String addTreeEmployee(@PathVariable Long Id, Model model) {
		Userinfo userinfo = new Userinfo();
	    Organizeinfo organi = organizeinfoManager.getOrganizeinfo(Id);
	    userinfo.setOrganizeinfo(organi);
	    userinfo.setBusinessinfo(organi.getBusinessinfo());
	    String canLook = PropertiesUtils.putBusidLook();
		model.addAttribute("canLook", canLook);
	    model.addAttribute("userinfo", userinfo);
        return "customer/userTreeInput";
    }
	
	/**
	 * 组织机构添加员工保存 MDY
	 * @param employee
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveTreeUser", method = RequestMethod.POST)
    public String saveTreeUser(@ModelAttribute("userinfo") Userinfo userinfo,
            HttpServletRequest request, HttpServletResponse response) {
        String code = "300";
        String msg = "保存失败！";
        
        try {
			userinfo.setPassword(EncodeUtils.getMd5PasswordEncoder("abc123",
					userinfo.getAccount()));
			userinfoManager.saveUserinfo(userinfo);
			code = "200";
			msg = "保存成功";
		} catch (Exception e) {
			logger.error("-----UserinfoController---------saveTreeUser------保存失败！----");
		}
		DwzResult success = new DwzResult(code, msg, "","", "", "");
        ServletUtils.renderJson(response, success);

        return null;
    }

	@RequestMapping(value = "/editemployee/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		Userinfo userinfo = userinfoManager.getUserinfo(Id);
		return new ModelAndView("customer/employee-input", "userinfo", userinfo);
	}
	
	@RequestMapping(value = "/getRole")
	public String getRole(HttpServletRequest request, Model model) {
		OperatorDetails operator = (OperatorDetails)SpringSecurityUtils.getCurrentUser();
		model.addAttribute("result",organizeinfoManager.buildOrganiByType());
		model.addAttribute("operator",operator);
		return "customer/treeLookup";
	}
	
	@RequestMapping(value="/chUserAccount")
	public String chUserAccount(HttpServletRequest request, HttpServletResponse response) {
		try {
			String propertyName = request.getParameter("propertyName");
			String newValue = URLDecoder.decode(request.getParameter(propertyName), "UTF-8");
			String oldValue = URLDecoder.decode(request.getParameter("oldValue"), "UTF-8");
			String errmes = URLDecoder.decode(request.getParameter("errmes"), "UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//println("propertyName===>" + propertyName + ";newValue==>" + newValue + ";oldValue==>" +oldValue + ";errmes==>" +errmes);
			if (userinfoManager.isUserAccountUnique( newValue, oldValue)) {
				//ServletUtils.renderText(response, "true");
			} else {
				//ServletUtils.render(response, "false", "hello");
				ServletUtils.renderText(response, errmes + "已经存在");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(EncodeUtils.getMd5PasswordEncoder("abc123",
					"wjy"));
	}

}
