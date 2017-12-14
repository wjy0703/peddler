package cn.com.cucsi.app.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.vechicle.util.CarState;

/**
 * 枚举类转换成对应的值
 *
 * @author xjs
 * @date 2013-8-9 下午3:34:40
 */
@Component
@Scope("prototype")
public class CarStateToNameTag extends SimpleTagSupport
{
	/**
	 * 类型 对应数据库BASE_ATTR_TYPE中的 coding字段
	 */
	
	String value;
	
	
	//@Autowired
	AttrCacheManager attrCacheManager;
	
	@Override
	public void doTag() throws JspException, IOException {
		//String name = attrCacheManager.getAttrName(MetaDataTypeConverter.getCodingName(coding),value);
		CarState state = CarState.fromStr(value);
		String message = state.getMessage();
		if(StringUtils.hasText(state.getColor())){
		    message = "<font color='"+state.getColor()+"'>" + message + "</font>";
		}
		getJspContext().getOut().write(message); 
	}

	
	

	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

    
}