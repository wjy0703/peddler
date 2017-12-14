/**
 * 对外提供带用户名和接口特定参数的简单的单点登录的类。
 * 成功认证的前提是用户名和密码相同，或者是用户名和密码可以有逻辑对应关系
 * 实现了规范中I1，I2接口。
 * @author jiangxd
 * create at 2011-11-04
 */
package cn.com.cucsi.app.web.intf;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.cucsi.app.Constants;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;

@Controller
@RequestMapping(value="/service")
public class SimpleSSOController {
	private Logger logger = Logger.getLogger(SimpleSSOController.class);
	
	private AccountManager accountManager;
	private UserDetailsService userDetailsService;
	
	private static final String I1_REDIRECT_URL = Constants.getAttrValue("SSO_URL","I1_REDIRECT_URL");
	private static final String I2_REDIRECT_URL = Constants.getAttrValue("SSO_URL","I2_REDIRECT_URL");
	
	/**
	 * 统一知识库调用接口(I1)
	 * @param kbsStaffid 统一知识库用户工号
	 * @param oStaffid   调用系统的工号
	 * @param sChannel   服务渠道代码
	 * @param servArea   地域代码
	 * 调用规范：http://localhost:8080/unicomkbs/service/i1/{kbsStaffid}/{oStaffid}/{sChannel}/{servArea}
	 * 调用举例：http://localhost:8080/unicomkbs/service/i1/admin/111/1001/097
	 * @param model      
	 * @param request
	 * @return
	 * @author jiangxd
	 */

	@RequestMapping(value="/i1/{kbsStaffid}/{oStaffid}/{sChannel}/{servArea}", method=RequestMethod.GET)
	public String i1( @PathVariable String kbsStaffid,@PathVariable String oStaffid,
			 @PathVariable String sChannel, @PathVariable String servArea, Model model, HttpServletRequest  request){
		
		User user = accountManager.findUserByLoginName(kbsStaffid);
		if (user == null){
			logger.debug("非法的接口调用用户");
			return "";
		}
		model.addAttribute("username", kbsStaffid);
		model.addAttribute("password", kbsStaffid);
		request.getSession().setAttribute("oStaffid", oStaffid);
		request.getSession().setAttribute("kbsStaffid", kbsStaffid);
		
		request.getSession().setAttribute("sChanel", sChannel);
		request.getSession().setAttribute("servArea", servArea);

		model.addAttribute("url", I1_REDIRECT_URL);

		return "simplesso/login";
	}
	
	/**
	 * 访问指定的知识点的接口（I2）
	 * @param kbsStaffid 统一知识库用户工号
	 * @param oStaffid   调用系统的工号
	 * @param sChannel   服务渠道代码
	 * @param sCaller    主叫号码
	 * @param KBSID      知识点编号
	 * 调用规范：http://localhost:8080/unicomkbs/service/i2/{kbsStaffid}/{oStaffid}/{sChannel}/{sCaller}/{KBSID}
	 * 调用举例：http://localhost:8080/unicomkbs/service/i2/admin/111/1001/15645101527/999
	 * @param model
	 * @param request
	 * @return
	 * @author jiangxd
	 */

	@RequestMapping(value="/i2/{kbsStaffid}/{oStaffid}/{sChannel}/{sCaller}/{KBSID}", method=RequestMethod.GET)
	public String i2( @PathVariable String kbsStaffid,@PathVariable String oStaffid,
			 @PathVariable String sChannel, @PathVariable String sCaller,  @PathVariable String KBSID, 
			 Model model, HttpServletRequest  request){
		User user = accountManager.findUserByLoginName(kbsStaffid);
		if (user == null){
			logger.debug("非法的接口调用用户");
			return "";
		}
		model.addAttribute("username", kbsStaffid);
		model.addAttribute("password", kbsStaffid);
		request.getSession().setAttribute("oStaffid", oStaffid);
		request.getSession().setAttribute("kbsStaffid", kbsStaffid);

		request.getSession().setAttribute("sChanel", sChannel);
		request.getSession().setAttribute("sCaller", sCaller);
		request.getSession().setAttribute("kbsId", KBSID);

		model.addAttribute("url", I2_REDIRECT_URL);

		return "simplesso/login";
	}

	
	@RequestMapping(value="/i3/{kbsStaffid}/{oStaffid}/{sChannel}/{sCaller}/{KBSID}", method=RequestMethod.GET)
	public String i3( @PathVariable String kbsStaffid,@PathVariable String oStaffid,
			 @PathVariable String sChannel, @PathVariable String sCaller,  @PathVariable String KBSID, 
			 Model model, HttpServletRequest  request){
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(kbsStaffid);
		SpringSecurityUtils.saveUserDetailsToContext(userDetails, request);
		request.getSession().setAttribute("oStaffid", oStaffid);
		request.getSession().setAttribute("kbsStaffid", kbsStaffid);

		request.getSession().setAttribute("sChanel", sChannel);
		request.getSession().setAttribute("sCaller", sCaller);
		request.getSession().setAttribute("kbsId", KBSID);

		return "redirect:"+I2_REDIRECT_URL;
	
	}
	
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
}
