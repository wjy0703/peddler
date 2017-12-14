package cn.com.cucsi.app.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/account")
public class UserController {
	
	private AccountManager accountManager;
	
	private BaseInfoManager baseInfoManager;
	
	@RequestMapping(value="/listuser")
	public String list(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<User> page = new Page<User>();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		accountManager.searchUser(page, params);
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("page", page);

		return "account/user";
		
	}
	
	public static String strFilter(String str){
		String regEx="[ ]";  
		Pattern   p   =   Pattern.compile(regEx);      
        Matcher   m   =   p.matcher(str);      
        return m.replaceAll("").trim();  
	}
	
	@RequestMapping(value="/saveuser",method=RequestMethod.POST)
	public String save(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response){
		if (user.getEmployee().getId()==null){
			user.setEmployee(null);
		}
		else{
			user.setEmployee(baseInfoManager.getEmployee(user.getEmployee().getId()));
		}
		
		String roleId = request.getParameter("roles.id");
		roleId = strFilter(roleId);
		if (!StringUtils.isBlank(roleId)){
			List<Long> ids = new ArrayList<Long>();
			String[] roleIds = roleId.split(",");
			for (String s: roleIds){
				ids.add(Long.valueOf(s));
			}
			List<Role> roleList = accountManager.getRoleListByIds(ids);
			user.setRoleList(roleList);
		}
		
		accountManager.saveUser(user);

		DwzResult success = new DwzResult("200","保存成功","rel_listuser","","","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("account/user-input", "user", new User());
	}
	
	@RequestMapping(value="/edituser/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		User user = accountManager.getUser(Id);
		return new ModelAndView("account/user-input", "user", user);
	}

	@RequestMapping(value="/deluser/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		accountManager.deleteUser(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listuser","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdeluser")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = accountManager.batchDelUser(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listuser","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listuser","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}

	
	@RequestMapping(value="/chkuser")
	public String checkLoginName(HttpServletRequest request, HttpServletResponse response) {
		
		String newLoginName = request.getParameter("loginName");
		String oldLoginName = request.getParameter("oldLoginName");

		if (accountManager.isLoginNameUnique(newLoginName, oldLoginName)) {
			ServletUtils.renderText(response, "true");
		} else {
			ServletUtils.renderText(response, "false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
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
		return "account/password";
	}
	@RequestMapping(value="/resetPass")
	public String resetPass(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		accountManager.resetPass(Ids);
		DwzResult success = null;
		
		success = new DwzResult("200","密码修改成功！","rel_listuser","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	@RequestMapping(value="/savepassword")
	public String savepass(HttpServletRequest request, HttpServletResponse response){
		String loginName = request.getParameter("loginName");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("password");
		String passWordMd5 = EncodeUtils.getMd5PasswordEncoder(oldpassword,loginName);
		
		User u = accountManager.findUserByLoginName(loginName);
		DwzResult success = null;
		if (u.getPassword().equals(passWordMd5)){
			u.setPassword(EncodeUtils.getMd5PasswordEncoder(newpassword,loginName));
			accountManager.saveUser(u);
			success = new DwzResult("200","密码修改成功！","","","closeCurrent","");
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

	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}


}
