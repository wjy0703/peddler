package cn.com.cucsi.app.web.messageBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.messageBox.MessageBox;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.messageBox.MessageManager;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/message")
public class MessageController {

	private Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private MessageManager messageManager;
	
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	/**
	 * ajax定时获取消息，时间间隔30秒，分普通用户获取和admin获取，admin获取全部消息，普通用户获取自己相关消息 	MDY 2013-08-22
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ajaxGetLoginMessage", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxGetLoginMessage(HttpServletRequest request){
		logger.info("ajaxGetLoginMessage start");
		boolean success = false;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Employee employee = baseInfoManager.getUserEmployee();
		List<Map<String, Object>> messageList = new ArrayList<Map<String,Object>>();
		if(employee != null && employee.getId() == 1 && employee.getName().equals("管理员")){
			messageList = messageManager.getLoginAdminMessage();
			logger.info("ajaxGetLoginMessage Admin Info");
		}else{
			messageList = messageManager.getLoginMessage(employee);
		}
		if(messageList != null){
			modelMap.put("count", messageList.size());
			modelMap.put("data", messageList);
			success = true;
			logger.info("ajaxGetLoginMessage is ok");
		}
		modelMap.put("success", success);
		return modelMap;
	}
	
	/**
	 * 首页显示消息获取方法 MDY 2013-08-22 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ajaxGetExpireMessage", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxGetExpireMessage(HttpServletRequest request){
		logger.info("ajaxGetExpireMessage start");
		boolean success = false;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Employee employee = baseInfoManager.getUserEmployee();
		List<Map<String, Object>> ExpireMessageList = messageManager.getExpireMessage(employee);
		modelMap.put("success", "true");
		if(ExpireMessageList != null){
			modelMap.put("expireData", ExpireMessageList);
			success = true;
			logger.info("ajaxGetExpireMessage is ok");
		}
		modelMap.put("success", success);
		return modelMap;
	}
	
	/**
	 * 获取类型为public_message类型的消息列表 MDY 2013-08-22 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listPublicMessage")
	public String listPublicMessage(HttpServletRequest request, Model model) {
		logger.info("listPublicMessage start");
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		Employee employee = baseInfoManager.getUserEmployee();
		List<Map<String, Object>> publicMessageList = new ArrayList<Map<String,Object>>();
		if(employee != null && employee.getId() == 1 && employee.getName().equals("管理员")){
			publicMessageList = messageManager.getLoginAdminMessage();
			logger.info("listPublicMessage Admin Info");
		}else{
			publicMessageList = messageManager.getPublicMessage(employee, map, page);
		}
		model.addAttribute("publicMessageList", publicMessageList);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		logger.info("listPublicMessage is ok");
		return "message/listPublicMessage";
	}
	
	/**
	 * 删除public_message类型消息 MDY 2013-08-22 
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delMessage/{Id}")
	public String delMessage(@PathVariable Long Id, HttpServletResponse response){
		messageManager.delMessage(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listPublicMessage","","","");
		ServletUtils.renderJson(response, success);
		logger.info("delMessage is ok");
		return null;
	}
	
	/**
	 * 获取public_message类型消息内容 MDY 2013-08-22 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getMessageContent")
	public String getMessageContent(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		MessageBox messageBox = messageManager.getMessageBoxById(new Long(id));
		messageBox.setMessageState("1");
		messageManager.saveMessage(messageBox);
		model.addAttribute("content", messageBox.getMessageContent());
		logger.info("getMessageContent is ok");
		return "message/messageContent";
	}
	
	/**
	 * 批量已读public_message类型消息  MDY 2013-08-22 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/batchHaveRead")
	public String batchHaveRead(HttpServletRequest request, HttpServletResponse response){
		logger.info("batchHaveRead start");
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = messageManager.batchHaveRead(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","批量已读成功","rel_listPublicMessage","","","");
			logger.info("batchHaveRead is ok");
		}
		else{
			success = new DwzResult("300","批量已读失败","rel_listPublicMessage","","","");
			logger.info("batchHaveRead is error");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 批量已读public_message类型消息  MDY 2013-08-22 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/batchDelete")
	public String batchDelete(HttpServletRequest request, HttpServletResponse response){
		logger.info("batchHaveRead start");
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = messageManager.batchDelete(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","批量删除成功","rel_listPublicMessage","","","");
			logger.info("batchHaveRead is ok");
		}
		else{
			success = new DwzResult("300","批量删除失败","rel_listPublicMessage","","","");
			logger.info("batchHaveRead is error");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}

}
