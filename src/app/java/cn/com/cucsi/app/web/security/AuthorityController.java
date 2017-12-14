package cn.com.cucsi.app.web.security;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/account")
public class AuthorityController {
	
	private AccountManager accountManager;
	
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	@RequestMapping(value="/listauth")
	public String listrole(HttpServletRequest request, Model model){
		Page<Authority> page = new Page<Authority>();
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
		accountManager.searchAuthority(page, params);
		model.addAttribute("page", page);

		return "account/resource";
	}
	
	@RequestMapping(value="/saveauth",method=RequestMethod.POST)
	public String save(@ModelAttribute("auth") Authority auth, HttpServletRequest request, HttpServletResponse response){

		accountManager.saveAuthority(auth);

		DwzResult success = new DwzResult("200","保存成功","rel_listauth","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}	
	@RequestMapping(value="/addauth", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("account/resource-input", "auth", new Authority());
	}
	
	@RequestMapping(value="/editauth/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		Authority auth = accountManager.getAuthority(Id);
		return new ModelAndView("account/resource-input", "auth", auth);
	}

	@RequestMapping(value="/delauth/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		accountManager.deleteAuthority(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listauth","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value="/batchdelauth")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = accountManager.batchDelAuth(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listauth","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listauth","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/findauthority")
	public String findrole( @RequestParam(value="roleId",required=false) String roleId, Model model){
		Role r = new Role();
		if(!StringUtils.isBlank(roleId)){
			r = accountManager.getRole(Long.valueOf(roleId));
		}
		
		List<Authority> list = accountManager.getMergedRoleAuthAndAllAuth(r.getAuthorityList());
		model.addAttribute("result", list);
		model.addAttribute("result1", r.getAuthorityList());
		model.addAttribute("role", r);
		return "account/resourcelookup";
	}
	
	//mdy需要更改2013-08-07
	@RequestMapping(value="/findMenu")
	public String findMenu( @RequestParam(value="roleId",required=false) String roleId, Model model){
		Role r = new Role();
		if(!StringUtils.isBlank(roleId)){
			r = accountManager.getRole(Long.valueOf(roleId));
		}
		/*
		//List<Menu> list = accountManager.getMergedRoleMenu(r.getMenuList());
		StringBuffer menuStr = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		List<Menu> topList = baseInfoManager.getMenusByLevels(new Integer(1));
		List<Map<String,Object>> menuMap = accountManager.getMergedRoleMenu(topList, r.getMenuList());
		//for(Menu topMenu: topList){
		for(int i = 0 ; i < menuMap.size() ; i++){
			String ch1 = "";
			Menu topMenu = (Menu)menuMap.get(i).get("menu");
			String checked = menuMap.get(i).get("checked").toString();
			if(checked != null && checked.equals("true")){
				ch1 = "checked="+checked;
				menuStr.append("{id:'"+topMenu.getId()+"', name:'"+topMenu.getMenuName()+"'}|");
			}
			sb.append("<li><a tname="+topMenu.getMenuName()+" tvalue="+topMenu.getId()+" "+ch1+">"+topMenu.getMenuName()+"</a><ul>");
			List<Menu> menus = new LinkedList<Menu>();
			baseInfoManager.buildMenuByTopId(menus, topMenu.getId());
			List<Map<String,Object>> menuMap1 = accountManager.getMergedRoleMenu(menus, r.getMenuList());
			int last_level = 0;
			for (int j = 0 ; j < menuMap1.size() ; j++){
				String ch = "";
				Menu menu = (Menu)menuMap1.get(j).get("menu");
				String checked1 = menuMap1.get(j).get("checked").toString();
				if(checked1 != null && checked1.equals("true")){
					ch = "checked="+checked1;
					menuStr.append("{id:'"+menu.getId()+"', name:'"+menu.getMenuName()+"'}|");
				}
				if(menu.getLevelId()==2 && last_level != 3){
					sb.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+ch+">"+menu.getMenuName()+"</a>");  
				}
				if(menu.getLevelId()==3 && last_level==2){
					sb.append("<ul>");
				}
				if(menu.getLevelId()==3){
					sb.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+ch+">"+menu.getMenuName()+"</a></li>");  
				}
				if(menu.getLevelId()==2 && last_level==3){
					sb.append("</ul>");
					sb.append("</li>");
					sb.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+ch+">"+menu.getMenuName()+"</a>"); 
				}
				last_level = menu.getLevelId();
			}
			sb.append("</ul>");
			sb.append("</li>");
			sb.append("</ul>");
			sb.append("</li>");
		}
*/
		
		//model.addAttribute("menuStr", menuStr.toString());
		String[] res = accountManager.buildMenuByTopId(r.getMenuList());
		model.addAttribute("menuStr", res[0]);
		model.addAttribute("result1", res[1]);
		model.addAttribute("role", r);
		return "account/menulookup";
	}
	
	@RequestMapping(value="/chkauth")
	public String checkLoginName(HttpServletRequest request, HttpServletResponse response) {
		
		String newValue = request.getParameter("name");
		String oldValue = request.getParameter("oldname");

		if (accountManager.isAuthNameUnique(newValue, oldValue)) {
			ServletUtils.renderText(response, "true");
		} else {
			ServletUtils.renderText(response, "false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}


	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}


}
