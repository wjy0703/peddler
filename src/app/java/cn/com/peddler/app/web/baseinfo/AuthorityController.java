package cn.com.peddler.app.web.baseinfo;

import java.util.HashMap;
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

import cn.com.peddler.app.entity.security.Authority;
import cn.com.peddler.app.entity.security.Roleinfo;
import cn.com.peddler.app.service.baseinfo.AuthorityManager;
import cn.com.peddler.app.service.login.UserinfoManager;
import cn.com.peddler.app.util.RequestPageUtils;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.web.DwzResult;
import cn.com.peddler.core.web.ServletUtils;

@Controller
@RequestMapping(value="/authority")
public class AuthorityController {
	
	@Autowired
	private AuthorityManager authorityManager;
	@Autowired
	private UserinfoManager userinfoManager;
	
	@RequestMapping(value="/listauth")
	public String listrole(HttpServletRequest request, Model model){
		Page<Authority> page = new RequestPageUtils<Authority>()
                .generatePage(request);
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		authorityManager.searchAuthority(page, params);
		model.addAttribute("page", page);
		model.addAttribute("map", params);
		return "customer/authorityIndex";
	}
	
	@RequestMapping(value="/saveauth",method=RequestMethod.POST)
	public String save(@ModelAttribute("auth") Authority auth, HttpServletRequest request, HttpServletResponse response){

		authorityManager.saveAuthority(auth);

		DwzResult success = new DwzResult("200","保存成功","rel_listauth","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}	
	@RequestMapping(value="/addauth", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("customer/authorityInput", "auth", new Authority());
	}
	
	@RequestMapping(value="/editauth/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		Authority auth = authorityManager.getAuthority(Id);
		return new ModelAndView("customer/authorityInput", "auth", auth);
	}

	@RequestMapping(value="/delauth/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		authorityManager.deleteAuthority(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listauth","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value="/batchdelauth")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = authorityManager.batchDelAuthority(Ids);
		
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
/* 替换	
    @RequestMapping(value="/findauthority")
    public String findrole( @RequestParam(value="roleId",required=false) String roleId, Model model){
        Roleinfo r = new Roleinfo();
        if(!StringUtils.isBlank(roleId)){
            r = authorityManager.getRoleinfo(Long.valueOf(roleId));
        }
        
        List<Authority> list = authorityManager.getMergedRoleinfoAuthAndAllAuth(r.getAuthorityList());
        model.addAttribute("result", list);
        model.addAttribute("result1", r.getAuthorityList());
        model.addAttribute("role", r);
        return "account/resourcelookup";
    }
   */ 
	@RequestMapping(value="/findauthority")
	public String findrole( @RequestParam(value="roleId",required=false) String roleId, 
	        HttpServletRequest request, Model model){
		Roleinfo r = new Roleinfo();
		if(!StringUtils.isBlank(roleId)){
			r = authorityManager.getRoleinfo(Long.valueOf(roleId));
		}
		Map<String,Object> params = new HashMap<String,Object>(1);
		String sysTypeParam = request.getParameter("sysTypeParam");
		if (StringUtils.isNotBlank(sysTypeParam)) {
		    params.put("sysTypeParam", sysTypeParam);
        }
		List<Authority> list = authorityManager.getMergedRoleinfoAuthAndAllAuth(r.getAuthorityList(),params);
		model.addAttribute("result", list);
		model.addAttribute("result1", r.getAuthorityList());
		model.addAttribute("role", r);
		return "customer/resourcelookup";
	}
/*
    //mdy需要更改2013-08-07
    @RequestMapping(value="/findMenu")
    public String findMenu( @RequestParam(value="roleId",required=false) String roleId, Model model){
        Roleinfo r = new Roleinfo();
        if(!StringUtils.isBlank(roleId)){
            r = authorityManager.getRoleinfo(Long.valueOf(roleId));
        }
        
        //List<Menu> list = authorityManager.getMergedRoleinfoMenu(r.getMenuList());
        //StringBuffer menuStr = new StringBuffer();
        //StringBuffer sb = new StringBuffer();
       // List<Menu> topList = baseInfoManager.getMenusByLevels(new Integer(1));
        //List<Map<String,Object>> menuMap = authorityManager.getMergedRoleinfoMenu(topList, r.getMenuList());
        //for(Menu topMenu: topList){
      //  for(int i = 0 ; i < menuMap.size() ; i++){
       //     String ch1 = "";
       //     Menu topMenu = (Menu)menuMap.get(i).get("menu");
       //     String checked = menuMap.get(i).get("checked").toString();
       //     if(checked != null && checked.equals("true")){
       //         ch1 = "checked="+checked;
      //          menuStr.append("{id:'"+topMenu.getId()+"', name:'"+topMenu.getMenuName()+"'}|");
      //      }
      //      sb.append("<li><a tname="+topMenu.getMenuName()+" tvalue="+topMenu.getId()+" "+ch1+">"+topMenu.getMenuName()+"</a><ul>");
      //      List<Menu> menus = new LinkedList<Menu>();
      //      baseInfoManager.buildMenuByTopId(menus, topMenu.getId());
      //      List<Map<String,Object>> menuMap1 = authorityManager.getMergedRoleinfoMenu(menus, r.getMenuList());
      //      int last_level = 0;
      //      for (int j = 0 ; j < menuMap1.size() ; j++){
      //          String ch = "";
      //          Menu menu = (Menu)menuMap1.get(j).get("menu");
      //          String checked1 = menuMap1.get(j).get("checked").toString();
      //          if(checked1 != null && checked1.equals("true")){
      //              ch = "checked="+checked1;
      //              menuStr.append("{id:'"+menu.getId()+"', name:'"+menu.getMenuName()+"'}|");
      //          }
      //          if(menu.getLevelId()==2 && last_level != 3){
      //              sb.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+ch+">"+menu.getMenuName()+"</a>");  
      //         }
      //          if(menu.getLevelId()==3 && last_level==2){
      //              sb.append("<ul>");
      //          }
      //          if(menu.getLevelId()==3){
      //              sb.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+ch+">"+menu.getMenuName()+"</a></li>");  
      //          }
      //          if(menu.getLevelId()==2 && last_level==3){
      //              sb.append("</ul>");
      //              sb.append("</li>");
      //              sb.append("<li><a tname="+menu.getMenuName()+" tvalue="+menu.getId()+" "+ch+">"+menu.getMenuName()+"</a>"); 
      //          }
      //          last_level = menu.getLevelId();
      //      }
     //       sb.append("</ul>");
     //       sb.append("</li>");
     //       sb.append("</ul>");
     //       sb.append("</li>");
     //   }

        
        //model.addAttribute("menuStr", menuStr.toString());
        String[] res = authorityManager.buildMenuByTopId(r.getMenuList());
        model.addAttribute("menuStr", res[0]);
        model.addAttribute("result1", res[1]);
        model.addAttribute("role", r);
        return "account/menulookup";
    }
*/
	//mdy需要更改2013-08-07
	@RequestMapping(value="/findMenu")
	public String findMenu( @RequestParam(value="roleId",required=false) String roleId,
	        HttpServletRequest request, Model model){
		Roleinfo r = new Roleinfo();
		if(!StringUtils.isBlank(roleId)){
			r = authorityManager.getRoleinfo(Long.valueOf(roleId));
		}
		Map<String,Object> params = new HashMap<String,Object>(4);
		params.put("levelId", 1);
		String sysTypeParam = request.getParameter("sysTypeParam");
//		sysTypeParam = StringUtils.isNotBlank(sysTypeParam) ? sysTypeParam : "0" ;
		if (StringUtils.isNotBlank(sysTypeParam)) {
		    params.put("sysTypeParam", sysTypeParam);
        }
		String[] res = userinfoManager.buildMenuByTopId(r.getMenuList(),params);
		model.addAttribute("menuStr", res[0]);
		model.addAttribute("result1", res[1]);
		model.addAttribute("role", r);
		return "customer/menulookup";
	}
	
	@RequestMapping(value="/chkauth")
	public String checkLoginName(HttpServletRequest request, HttpServletResponse response) {
		
		String newValue = request.getParameter("name");
		String oldValue = request.getParameter("oldname");

		if (authorityManager.isAuthNameUnique(newValue, oldValue)) {
			ServletUtils.renderText(response, "true");
		} else {
			ServletUtils.renderText(response, "false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}


}
