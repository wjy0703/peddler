package cn.com.cucsi.app.web.baseinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.service.baseinfo.AddressManager;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/addresses")
public class AddressController {
	
	@Autowired
	AddressManager addressManager;
	
	@RequestMapping(value = "/getSons/{parentId}")
    public void getSonsByParentId(@PathVariable String parentId ,HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "title",required=false) String title){	
	    StringBuffer sonsBuffer = new StringBuffer();
		sonsBuffer.append("[");
		String idStr = parentId.substring(3);
		Long id = null;
		if(StringUtils.isNotEmpty(title)){
			sonsBuffer.append("[\"\",\"" + title+"\"]");
		}
		if(StringUtils.isNotEmpty(idStr)){
			id = Long.parseLong(idStr);
			List<City> sons = addressManager.getSonsByParentId(id);
			if(StringUtils.isNotEmpty(title) && sons.size() > 0 )
				sonsBuffer.append(",");
			int index = 0 ;
			for (; index < sons.size() - 1; index++) {
				City son  = sons.get(index);
				sonsBuffer.append("[");
				sonsBuffer.append("\"" + son.getId() + "\",\"");
				sonsBuffer.append(son.getName() + "\"");
				sonsBuffer.append("],");
			}
			
			if(index < sons.size()){
				City son  = sons.get(index);
				sonsBuffer.append("[");
				sonsBuffer.append("\"" + son.getId() + "\",\"");
				sonsBuffer.append(son.getName() + "\"");
				sonsBuffer.append("]");
			}
		}
		
		sonsBuffer.append("]");
	    ServletUtils.renderText(response, sonsBuffer.toString());
    }
	
	@RequestMapping(value = "/getSons")
    public void getSons(@PathVariable Long id ,HttpServletResponse response,@RequestParam(value = "title",required=false) String title){	
		StringBuffer sonsBuffer = new StringBuffer();
		sonsBuffer.append("[");
		if(StringUtils.isNotEmpty(title)){
			sonsBuffer.append("[\"\",\"" + title+"\"]");
		}
		sonsBuffer.append("]");
	    
	    ServletUtils.renderText(response, sonsBuffer.toString());
    }
	
	/**
	 * 清楚缓存
	 * @return
	 */
	@RequestMapping(value = "/invalidateAll", method = RequestMethod.GET)
	@ResponseBody
    public String invalidateAll(){		
		addressManager.invalidateAll();
		return "success";
    }
	
	
}
