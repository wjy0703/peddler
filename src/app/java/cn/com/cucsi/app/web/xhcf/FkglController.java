package cn.com.cucsi.app.web.xhcf;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.xhcf.FangKuangGuanLi;
import cn.com.cucsi.app.entity.xhcf.XhFkgl;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.JiaoYiManager;
import cn.com.cucsi.app.service.xhcf.XhfkglManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/fkgl")
public class FkglController {
	private XhfkglManager xhfkglManager;
	
	@Autowired
	public void setXhfkglManager(XhfkglManager xhfkglManager) {
		this.xhfkglManager = xhfkglManager;
	}
	@RequestMapping(value="/listfkgl")
	public String list(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhFkgl> page = new Page<XhFkgl>();
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
		
		
		xhfkglManager.searchXhFkgl(page,params);
		
		model.addAttribute("page", page);
		
		return "fkgl/fkglIndex";
	}
	@RequestMapping(value="/addfkgl", method=RequestMethod.GET)
	public String add(HttpServletRequest request, Model model){
		//付款管理新增
		Date date = new Date();
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd");
		String fksj = fat.format(date);
		model.addAttribute("sjfkrq", fksj);
		return "fkgl/fkglInput";
	}
	@RequestMapping(value="/savefkgl",method=RequestMethod.POST)
	public String savefkgl(@ModelAttribute("xhFkgl") XhFkgl xhFkgl, HttpServletRequest request, HttpServletResponse response){
		//付款管理保存
		xhfkglManager.saveXhFkgl(xhFkgl);

		DwzResult success = new DwzResult("200","保存成功1","rel_listhkgl","","closeCurrent","");
		ServletUtils.renderJson(response, success);
	
		return null;
	}
	@RequestMapping(value="/viewfkgl/{Id}", method=RequestMethod.GET)
	public ModelAndView view(@PathVariable Long Id){
		//付款管理查看
		XhFkgl fkgl = xhfkglManager.getXhFkgl(Id);
		return new ModelAndView("fkgl/fkglLook", "fkgl", fkgl);
	}
}
