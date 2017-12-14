package cn.com.cucsi.app.web.baseinfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.Customer;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.MateDataType;
import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.util.RandomValidateCode;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

import com.et.mvc.JsonView;


@Controller
@RequestMapping(value="/baseinfo")
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}	 

	@RequestMapping(value="/listcustomer")
	public String list(HttpServletRequest request, Model model){
		logger.debug("dd");
		// 处理分页的参数
		Page<Customer> page = new Page<Customer>();
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
		
		baseInfoManager.searchCustomer(page, params);
	
		model.addAttribute("page", page);
		return "baseinfo/customer";
	}

	
	@RequestMapping(value="/savecustomer",method=RequestMethod.POST)
	public String save(@ModelAttribute("customer") Customer customer, HttpServletRequest request, HttpServletResponse response){
		baseInfoManager.saveCustomer(customer);

		DwzResult success = new DwzResult("200","保存成功","rel_listcustomer","","closeCurrent","");
		ServletUtils.renderJson(response, success);
	
		return null;
	}

	@RequestMapping(value="/addcustomer", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("baseinfo/customer-input", "customer", new Customer());
	}
	
	@RequestMapping(value="/editcustomer/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		Customer customer = baseInfoManager.getCustomer(Id);
		return new ModelAndView("baseinfo/customer-input", "customer", customer);
	}

	@RequestMapping(value="/delcustomer/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		baseInfoManager.deleteCustomer(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listcustomer","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/listCp")
	public String listCp(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<Tzcp> page = new Page<Tzcp>();
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
		
		baseInfoManager.searchCp(page, params);
		List<MateData> list =  baseInfoManager.getTypeByCode("10001");
	
		model.addAttribute("page", page);
		model.addAttribute("type", list);
		return "baseinfo/listCp";
	}
	
	@RequestMapping(value="/getCplx")
	public String getCity(HttpServletRequest request, Model model,HttpServletResponse response) throws Exception{
		// 处理分页的参数
		String code = request.getParameter("code");
		code=new String(code.getBytes("ISO-8859-1"),"GBK");
		String value = "";
		if(code.equals("投资产品")){
			value = "0010";
		}else if(code.equals("贷款产品")){
			value = "10002";
		}
		List<MateData> list1 =  baseInfoManager.getTypeByCode(value);
		StringBuffer json = new StringBuffer();
		json.append("[");
		if(list1.size() != 0){
			for(MateData mateData : list1){
				json.append("[\""+mateData.getValue()+"\",\""+mateData.getName()+"\"],");
			}
			json.deleteCharAt(json.lastIndexOf(","));
		}
		json.append("]");
		response.getWriter().println(json.toString());
		return null;
	}
	
	@RequestMapping(value="/addCp", method=RequestMethod.GET)
	public String addCp(Model model){
		List<MateData> list =  baseInfoManager.getTypeByCode("10001");
		List<MateData> list1 =  baseInfoManager.getTypeByCode("0010");
		
		model.addAttribute("cp", new Tzcp());
		model.addAttribute("type", list);
		model.addAttribute("type1", list1);
		return "baseinfo/cp-input";
	}

	@RequestMapping(value="/saveCp",method=RequestMethod.POST)
	public String saveCp(@ModelAttribute("tzcp") Tzcp tzcp, HttpServletRequest request, HttpServletResponse response){
		String bh = tzcp.getTzcpBh();
		if(bh != null && !bh.equals("")){
			tzcp.setTzcpBh("8"+bh);
		}
		baseInfoManager.saveCp(tzcp);

		DwzResult success = new DwzResult("200","保存成功","rel_listCp","","closeCurrent","");
		ServletUtils.renderJson(response, success);
	
		return null;
	}
	
	@RequestMapping(value="/delCp/{Id}")
	public String delCp(@PathVariable Long Id, HttpServletResponse response){
		baseInfoManager.deleteCp(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listCp","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/editCp/{Id}", method=RequestMethod.GET)
	public String editCp(@PathVariable Long Id, Model model){
		Tzcp cp = baseInfoManager.getCp(Id);
		List<MateData> list =  baseInfoManager.getTypeByCode("10001");
		String value = "";
		if(cp.getTzcpFl().equals("投资产品")){
			value = "0010";
		}else if(cp.getTzcpFl().equals("贷款产品")){
			value = "10002";
		}
		
		List<MateData> list1 =  baseInfoManager.getTypeByCode(value);
		model.addAttribute("cp", cp);
		model.addAttribute("type", list);
		model.addAttribute("type1", list1);
		return "baseinfo/cp-input";
	}
	
	/**
	 * 初始化树
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/initTree")
	public String initTree(HttpServletRequest request, Model model){
	    String treeStr = baseInfoManager.buildOrganiByTopId("setSelectedOrg");
	    model.addAttribute("result",treeStr);
		return "baseinfo/departmentIndex";
	}
	
	/**
	 * 添加组织机构 mdy 2013-08-07
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addTreeDep")
	public String addTreeDep(Model model, HttpServletRequest request){
		String id = request.getParameter("id");
		Organi organi = baseInfoManager.getOrgani(new Long(id));
		model.addAttribute("upOrgani", organi);
		return "baseinfo/departmentInput";
	}
	
	/**
	 * 调整组织机构 mdy 2013-08-07
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editTreeDep")
	public String editTreeDep(Model model, HttpServletRequest request){
		String id = request.getParameter("id");
		Organi organi = baseInfoManager.gerOrgani(new Long(id));
		Organi upOrgani = baseInfoManager.getOrgani(organi.getParentId());
		model.addAttribute("organi", organi);
		model.addAttribute("upOrgani", upOrgani);
		return "baseinfo/departmentInput";
	}
	
	/**
	 * 保存组织机构 mdy 2013-08-07
	 * @param organi
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveTree",method=RequestMethod.POST)
	public String saveTree(@ModelAttribute("organi") Organi organi, HttpServletRequest request, HttpServletResponse response){
		baseInfoManager.saveOrgani(organi);
		DwzResult success = new DwzResult("200","保存成功","rel_initTree","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/addDep")
	public String addDep(HttpServletRequest request, HttpServletResponse response){
		String parentId = request.getParameter("parentId");
		String rganiName = request.getParameter("name");
		String organiCode = request.getParameter("code");
		String organiFlag = request.getParameter("cityFlag");
		String organiDes = request.getParameter("organiDes");
		String levelMess = request.getParameter("levelMess");
		Organi organi = baseInfoManager.addDep(parentId, rganiName, organiCode, organiFlag, organiDes,levelMess);
		try {
    		if(null != organi){
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("success", true);
    			response.setContentType("text/json; charset=utf-8");
    			response.getWriter().println(new JsonView(map).toString());
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getDep")
	public String getDep(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		String list = baseInfoManager.getDep(Long.valueOf(id));
		try {
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().println(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/depList")
	public String depList(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		String list = baseInfoManager.getCityList(Long.valueOf(id));
		try {
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().println(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/delDep")
	public String delDep(HttpServletRequest request, HttpServletResponse response){
		String json = "";
		String id = request.getParameter("id");
		boolean flag = baseInfoManager.delDep(id);
		try {
		if(flag){
			json = new JsonView("success:true,id:"+id).toString();
		}else{
			json = new JsonView("success:false,msg:该部门下有子部门，不允许删除！").toString();
		}
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/updateDep")
	public String updateDep(HttpServletRequest request, HttpServletResponse response){
		String parentId = request.getParameter("parentId");
		String name = request.getParameter("name");
		String organiCode = request.getParameter("code");
		String organiFlag = request.getParameter("cityFlag");
		String organiDes = request.getParameter("organiDes");
		String levelMess = request.getParameter("levelMess");
		Organi organi = baseInfoManager.updateDep(parentId, name, organiCode, organiFlag, organiDes,levelMess);
		try {
		if(null != organi){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().println(new JsonView(map).toString());
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/listZd")
	public String listZd(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<MateData> page = new Page<MateData>();
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
		
		baseInfoManager.searchZd(page, params);
		
		List<MateDataType> list = baseInfoManager.getAllType();
	
		model.addAttribute("page", page);
		model.addAttribute("type", list);
		return "baseinfo/listZd";
	}
	
	@RequestMapping(value="/addZp", method=RequestMethod.GET)
	public String addZp(HttpServletRequest request, Model model){
		List<MateDataType> list = baseInfoManager.getAllType();
		
		model.addAttribute("zd", new MateData());
		model.addAttribute("type", list);
		return "baseinfo/zd-input";
	}
	
	@RequestMapping(value="/saveZd",method=RequestMethod.POST)
	public String saveZd(@ModelAttribute("mateData") MateData mateData, HttpServletRequest request, HttpServletResponse response){
		baseInfoManager.saveZd(mateData);

		DwzResult success = new DwzResult("200","保存成功","rel_listZd","","closeCurrent","");
		ServletUtils.renderJson(response, success);
	
		return null;
	}
	
	@RequestMapping(value="/delZd/{Id}")
	public String delZd(@PathVariable Long Id, HttpServletResponse response){
		baseInfoManager.deleteZd(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listZd","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/editZd/{Id}", method=RequestMethod.GET)
	public String editZd(@PathVariable Long Id, Model model){
		MateData zd = baseInfoManager.getZd(Id);
		List<MateDataType> list = baseInfoManager.getAllType();
		
		model.addAttribute("zd", zd);
		model.addAttribute("type", list);
		return "baseinfo/zd-input";
	}
	
	@RequestMapping(value="/listMenu")
	public String listMenu(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<Menu> page = new Page<Menu>();
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
		
		baseInfoManager.searchMenu(page, params);
	
		model.addAttribute("page", page);
		return "baseinfo/listMenu";
	}
	
	@RequestMapping(value="/editMenu/{Id}", method=RequestMethod.GET)
	public String editMenu(@PathVariable Long Id, Model model){
		Menu menu = baseInfoManager.getMenu(Id);
		model.addAttribute("menu", menu);
		return "baseinfo/menu-input";
	}
	
	@RequestMapping(value="/saveMenu",method=RequestMethod.POST)
	public String saveMenu(@ModelAttribute("menu") Menu menu, HttpServletRequest request, HttpServletResponse response){
		baseInfoManager.saveMenuNew(menu);

		DwzResult success = new DwzResult("200","保存成功","rel_listMenu","rel_listMenu","closeCurrent","");
		ServletUtils.renderJson(response, success);
	
		return null;
	}
	
	@RequestMapping(value = "/getLoginInfo")
	@ResponseBody
	public Map<String, Object> getLoginInfo(HttpServletRequest request){
		Employee employee = baseInfoManager.getUserEmployee();
		String sex = employee.getSex();
		String sexs = "先生/女士";
		if(sex != null && sex.equals("F")){
			sexs = "先生";
		}else{
			sexs = "女士";
		}
		String organiName = "未分组";
		Organi organi = employee.getOrgani();
		String parentName = "";
		if(organi != null){
			organiName = organi.getRganiName();
			//System.out.println(organi.getParentId());
			if(organi.getParentId() != null && organi.getParentId() != 0){
				Organi organiPar = baseInfoManager.gerOrgani(organi.getParentId());
				if(organiPar!=null){
					parentName = organiPar.getRganiName()+"-";
				}
			}
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		boolean isMess = baseInfoManager.getUserIsMess();
        modelMap.put("name", "欢迎您，"+parentName+organiName+"-"+employee.getName()+sexs+"！");
		modelMap.put("success", "true");
		modelMap.put("isMess", isMess);
		modelMap.put("pass", baseInfoManager.isLoginPass());
		return modelMap;
	}
	
	@RequestMapping(value="/listMiddleMan")
	public String listMiddleMan(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<MiddleMan> page = new Page<MiddleMan>();
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
		
		baseInfoManager.searchMiddleMan(page, params);
	
		model.addAttribute("page", page);
		return "baseinfo/listMiddleMan";
	}
	
	@RequestMapping(value="/addMiddleMan", method=RequestMethod.GET)
	public String addMiddleMan(HttpServletRequest request, Model model){
		model.addAttribute("middleMan", new MiddleMan());
		return "baseinfo/middleMan-input";
	}
	
	@RequestMapping(value="/saveMiddleMan",method=RequestMethod.POST)
	public String saveMiddleMan(@ModelAttribute("middleMan") MiddleMan middleMan, HttpServletRequest request, HttpServletResponse response){
		baseInfoManager.saveMiddleMan(middleMan);

		DwzResult success = new DwzResult("200","保存成功","rel_listMiddleMan","","closeCurrent","");
		ServletUtils.renderJson(response, success);
	
		return null;
	}
	
	@RequestMapping(value="/delMiddleMan/{Id}")
	public String delMiddleMan(@PathVariable Long Id, HttpServletResponse response){
		baseInfoManager.deleteMiddleMan(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listMiddleMan","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/editMiddleMan/{Id}", method=RequestMethod.GET)
	public String editMiddleMan(@PathVariable Long Id, Model model){
		MiddleMan middleMan = baseInfoManager.getMiddleMan(Id);
		model.addAttribute("middleMan", middleMan);
		return "baseinfo/middleMan-input";
	}
	
	@RequestMapping(value="/getImg")
	public String getImg(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	

	
}
