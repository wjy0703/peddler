package cn.com.cucsi.app.web.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.baseinfo.UpdatedMenuManager;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/account")
public class RoleController {
	
    @Autowired
    private UpdatedMenuManager updatedMenuManager;
    
    @Autowired
	private AccountManager accountManager;
	
	@RequestMapping(value="/listrole")
	public String listrole(HttpServletRequest request, Model model){
		Page<Role> page = new RequestPageUtils<Role>().generatePage(request);
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		accountManager.searchRole(page, params);
		model.addAttribute("page", page);
		return "account/role";
	}
	
	public static String strFilter(String str){
		String regEx="[ ]";  
		Pattern   p   =   Pattern.compile(regEx);      
        Matcher   m   =   p.matcher(str);      
        return m.replaceAll("").trim();  
	}
	
	@RequestMapping(value="/saverole",method=RequestMethod.POST)
	public String save(@ModelAttribute("role") Role role, HttpServletRequest request, HttpServletResponse response){
		String authorityId = request.getParameter("authoritys.id");
		authorityId = strFilter(authorityId);
		if (!StringUtils.isBlank(authorityId)){
			List<Long> ids = new ArrayList<Long>();
			String[] authorityIds = authorityId.split(",");
			for (String s: authorityIds){
				ids.add(Long.valueOf(s));
			}
			List<Authority> authorityList = accountManager.getAuthorityListByIds(ids);
			role.setAuthorityList(authorityList);
		}
		String menuId = request.getParameter("menu.id");
		menuId = strFilter(menuId);
		if (!StringUtils.isBlank(menuId)){
			List<Long> ids1 = new ArrayList<Long>();
			String[] menuIdIds = menuId.split(",");
			for (String s: menuIdIds){
				ids1.add(Long.valueOf(s));
			}
			List<Menu> menuList = accountManager.getMenuListByIds(ids1);
			role.setMenuList(menuList);
		}
		accountManager.saveRole(role);
		updatedMenuManager.invalidateAll();
		DwzResult success = new DwzResult("200","保存成功","rel_listrole","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}	
	
	@RequestMapping(value="/addrole", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("account/role-input", "role", new Role());
	}
	
	@RequestMapping(value="/editrole/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		Role role = accountManager.getRole(Id);
		return new ModelAndView("account/role-input", "role", role);
	}

	@RequestMapping(value="/delrole/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		accountManager.deleteRole(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listrole","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value="/batchdelrole")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = accountManager.batchDelRole(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listrole","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listrole","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value="/findrole")
	public String findrole( @RequestParam(value="userId",required=false) String userId, Model model){
		User u = new User();
		if(!StringUtils.isBlank(userId)){
			u = accountManager.getUser(Long.valueOf(userId));
		}
		
		List<Role> list = accountManager.getMergedUserRoleAndAllRole(u.getRoleList());
		model.addAttribute("result", list);
		model.addAttribute("user", u);
		return "account/rolelookup";
	}

	@RequestMapping(value="/chkrole")
	public String checkLoginName(HttpServletRequest request, HttpServletResponse response) {
		
		String newValue = request.getParameter("name");
		String oldValue = request.getParameter("oldname");

		if (accountManager.isRoleNameUnique(newValue, oldValue)) {
			ServletUtils.renderText(response, "true");
		} else {
			ServletUtils.renderText(response, "false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

}
