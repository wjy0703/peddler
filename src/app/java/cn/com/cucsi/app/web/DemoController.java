
/**
 * author jiangxd
 * 1.演示JSON的返回和提交
 * 2。演示@RequireParam 参数类型的用法。
 */

package cn.com.cucsi.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.security.User;

@Controller
@RequestMapping(value="/demo")
public class DemoController {

	private Logger logger = LoggerFactory.getLogger(DemoController.class); 
	

	@RequestMapping(value="",  method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("demo");
	}
	
	/**
	 * 使用springMVC3返回JSON
	 * 注意：如果使用hibernateDao查询的对象，没有OpenSessionInView 的延迟加载，
	 * @RequestParam(value="id",required=false) 表示这个参数在地址栏 中可以不写,即可以写成/demo/list
	 * @RequestParam("id") 标识在地址栏中必须写，即可以写成 /demo/list?id=5
	 * 另外，web页面中的表单域的值也可以使用@RequestParam 参数直接传递进来。这个可以用来处理页面中某个非实体属性的表单域的值传递。
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserList( @RequestParam(value="id",required=false) String id) {
		logger.info("列表");
		logger.info("@RequestParam(\"id\")=" + id);
		List<User> list = new ArrayList<User>();
		
		User user = new User();
		user.setId(new Long(1));
		user.setLoginName("admin");
		user.setPassword("admin");
		Employee e = new Employee();
		e.setName("张三");
		user.setEmployee(e);
		list.add(user);
		
	    user = new User();
		user.setId(new Long(2));
		user.setLoginName("user");
		user.setPassword("user");
		e = new Employee();
		e.setName("李四");
		user.setEmployee(e);	    
		list.add(user);
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", "2");
		modelMap.put("data", list);
		modelMap.put("success", "true");
		return modelMap;
	}
	
    /**
     * 接收web页面传 递过来的JSON,以User对象的形式传递到函数体
     * @param user
     * @return
     */
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addUser(@RequestBody User user) {
		logger.info("新增");
		logger.info("捕获到前台传递过来的Model，名称为：" + user.getLoginName());
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("success", "true");
		return map;
	}
	
	@RequestMapping(value = "/jstl", method = RequestMethod.GET)
	public String jstl(Model model){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", "admin");
		map.put("age", new Integer(20));
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "user");
		map.put("age", new Integer(21));
		
		list.add(map);
		
		model.addAttribute("result",list);
		return "test/jstl";
	}
	
	@RequestMapping(value = "/ttt", method = RequestMethod.GET)
	public String ttt(){
		return "test/phone-v2";
	}

	@RequestMapping(value = "/button", method = RequestMethod.GET)
	public String button(){
		return "button";
	}

}
