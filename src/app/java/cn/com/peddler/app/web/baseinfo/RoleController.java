package cn.com.peddler.app.web.baseinfo;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.peddler.app.entity.login.Menutable;
import cn.com.peddler.app.entity.security.Authority;
import cn.com.peddler.app.entity.security.Roleinfo;
import cn.com.peddler.app.entity.security.Userinfo;
import cn.com.peddler.app.service.baseinfo.AuthorityManager;
import cn.com.peddler.app.service.baseinfo.RoleinfoManager;
import cn.com.peddler.app.service.baseinfo.UpdatedMenuManager;
import cn.com.peddler.app.service.login.UserinfoManager;
import cn.com.peddler.app.service.security.OperatorDetails;
import cn.com.peddler.app.util.AvoidDuplicateSubmission;
import cn.com.peddler.app.util.PropertiesUtils;
import cn.com.peddler.app.util.RequestPageUtils;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.security.springsecurity.SpringSecurityUtils;
import cn.com.peddler.core.web.DwzResult;
import cn.com.peddler.core.web.ServletUtils;


@Controller
@RequestMapping(value="/role")
public class RoleController {
	
    @Autowired
    private UpdatedMenuManager updatedMenuManager;
    
    @Autowired
	private AuthorityManager authorityManager;
	@Autowired
	private UserinfoManager userinfoManager;
    
    @Autowired
    private RoleinfoManager roleinfoManager;
    
/*	替换
    @RequestMapping(value="/listrole")
    public String listrole(HttpServletRequest request, Model model){
        Page<Roleinfo> page = new RequestPageUtils<Roleinfo>().generatePage(request);
        Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
        accountManager.searchRole(page, params);
        model.addAttribute("page", page);
        return "customer/role";
    }
*/
	@RequestMapping(value="/listrole")
	public String listrole(HttpServletRequest request, Model model){
		Page<Roleinfo> page = new RequestPageUtils<Roleinfo>().generatePage(request);
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		PropertiesUtils.putBusidCheck(params);
		userinfoManager.searchRole(page, params);
		model.addAttribute("page", page);
		model.addAttribute("map", params);
		return "customer/roleinfoIndex";
	}
	
	public static String strFilter(String str){
		String regEx="[ ]";  
		Pattern   p   =   Pattern.compile(regEx);      
        Matcher   m   =   p.matcher(str);      
        return m.replaceAll("").trim();  
	}
	
	@RequestMapping(value="/saverole",method=RequestMethod.POST)
	@AvoidDuplicateSubmission(tokenName = "tokenmdy", needRemoveToken = true)
	public String save(@ModelAttribute("role") Roleinfo role, HttpServletRequest request, HttpServletResponse response){
		String authorityId = request.getParameter("authoritys.id");
		authorityId = strFilter(authorityId);
		if (!StringUtils.isBlank(authorityId)){
			List<Long> ids = new ArrayList<Long>();
			String[] authorityIds = authorityId.split(",");
			for (String s: authorityIds){
				ids.add(Long.valueOf(s));
			}
			List<Authority> authorityList = userinfoManager.getAuthorityListByIds(ids);
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
			List<Menutable> menuList = userinfoManager.getMenuListByIds(ids1);
			role.setMenuList(menuList);
		}
		userinfoManager.saveRole(role);
		updatedMenuManager.invalidateAll();
		DwzResult success = new DwzResult("200","保存成功","rel_listrole","","closeCurrent","");
		ServletUtils.renderSubmitResult(request, success);
		ServletUtils.renderJson(response, success);
		return null;
	}	
	
	@RequestMapping(value="/addrole", method=RequestMethod.GET)
	@AvoidDuplicateSubmission(tokenName = "tokenmdy", needSaveToken = true)
	public ModelAndView add(){
		return new ModelAndView("customer/roleinfoInput", "role", new Roleinfo());
	}
	
	@RequestMapping(value="/editrole/{Id}", method=RequestMethod.GET)
	@AvoidDuplicateSubmission(tokenName = "tokenmdy", needSaveToken = true)
	public ModelAndView edit(@PathVariable Long Id){
		Roleinfo role = userinfoManager.getRole(Id);
		return new ModelAndView("customer/roleinfoInput", "role", role);
	}

	@RequestMapping(value="/delrole/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		userinfoManager.deleteRole(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listrole","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value="/batchdelrole")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = userinfoManager.batchDelRole(Ids);
		
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
	
/*	替换
    @RequestMapping(value="/findrole")
    public String findrole( @RequestParam(value="userId",required=false) String userId, Model model){
        Userinfo u = new Userinfo();
        if(!StringUtils.isBlank(userId)){
            u = accountManager.getUser(Long.valueOf(userId));
        }
        
        List<Roleinfo> list = accountManager.getMergedUserRoleAndAllRole(u.getRoleList());
        model.addAttribute("result", list);
        model.addAttribute("user", u);
        return "customer/rolelookup";
    }
*/    
	
	@RequestMapping(value="/findrole")
	public String findrole( @RequestParam(value="userId",required=false) String userId,
	                HttpServletRequest request , Model model){
		Userinfo u = new Userinfo();
		if(!StringUtils.isBlank(userId)){
			u = userinfoManager.getUserinfo(Long.valueOf(userId));
		
			Map<String,Object> params = new HashMap<String,Object>(1);
			String sysTypeParam = request.getParameter("sysTypeParam");
			if (StringUtils.isNotBlank(sysTypeParam)) {
	            params.put("sysTypeParam", sysTypeParam);
	        }
			List<Roleinfo> userRoles = new ArrayList<Roleinfo>();
			userRoles.add(u.getRoleinfo());
			List<Roleinfo> list = authorityManager.getMergedUserRoleAndAllRole(userRoles,params);
			model.addAttribute("result", list);
			model.addAttribute("user", u);
		}
		return "customer/rolelookup";
	}

	@RequestMapping(value="/chkrole")
	public String chkroleName(HttpServletRequest request, HttpServletResponse response) {
		try {
			String propertyName = request.getParameter("propertyName");
			String newValue = URLDecoder.decode(request.getParameter(propertyName), "UTF-8");
			String oldValue = URLDecoder.decode(request.getParameter("oldValue"), "UTF-8");
			String errmes = URLDecoder.decode(request.getParameter("errmes"), "UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//println("propertyName===>" + propertyName + ";newValue==>" + newValue + ";oldValue==>" +oldValue + ";errmes==>" +errmes);
			if (userinfoManager.isRoleNameUnique( newValue, oldValue)) {
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

}
