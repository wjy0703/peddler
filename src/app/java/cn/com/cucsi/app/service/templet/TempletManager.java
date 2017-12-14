package cn.com.cucsi.app.service.templet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.templet.TempletDao;
import cn.com.cucsi.app.entity.xhcf.Templet;
import cn.com.cucsi.app.entity.xhcf.XhJksq;

/**
 * 
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class TempletManager {

	private static Logger logger = LoggerFactory.getLogger(TempletManager.class);

	private TempletDao templetDao;

	
	/**
	 * 通过类型查找模板
	 * @param type
	 * @return List<Templet>
	 */
	@Transactional(readOnly = true)
	public List<Templet> getTempletByType(String type) {
		return templetDao.findTempletByType(type);
	}
	
	/**
	 * 借款申请中
	 * 老板贷、老板楼易贷、薪水贷、薪水楼易贷、精英贷
	 * 的动态信息
	 * @return
	 */
	public String getLoanDATA(String typeCode){
		List<Templet> list = getTempletByType(typeCode);
		StringBuffer sb = new StringBuffer();
		if(null != list && list.size()>0){
			Templet templet = list.get(0);
			String types = templet.getTypes();//页面组件类型格式：text、select等（采用数组方式存储数据）
			if(null == types || "".equals(types)){
//				return sb.append("模板组件类型配置有误！").toString();
				return sb.toString();
			}
			String names = templet.getNames();//页面显示名称格式：单位性质、职务、在岗时间等（采用数组方式存储数据）
			if(null == names || "".equals(names)){
//				return sb.append("模板组件页面显示名称格式配置有误！").toString();
				return sb.toString();
			}
			String keys = templet.getKeys();//页面表单名称格式：formData_key1、formData_key2、formData_key3等（采用数组方式存储数据）
			if(null == keys || "".equals(keys)){
//				return sb.append("模板组件类型名称配置有误！").toString();
				return sb.toString();
			}
			String [] type = types.split(",");
			String [] name = names.split(",");
			String [] key = keys.split(",");
			int count = 0;
			int mod = type.length % 3;
			if(mod == 0){
				count = type.length /3;
			}else{
				count = type.length /3 + 1;
			}
			sb.append("<table>");
			for(int k=0;k<count;k++){
				sb.append("<tr>");
				if(k == 0){
					for(int i=0;i<((k+1)*3<type.length?(k+1)*3:type.length);i++){
						sb.append("<td><label>").append(name[i]).append("</label>");
						sb.append(inputStr(type[i], key[i], null, null));
						sb.append("</td>");
					}
				}else{
					for(int i=k*3;i<((k+1)*3<type.length?(k+1)*3:type.length);i++){
						sb.append("<td><label>").append(name[i]).append("</label>");
						sb.append(inputStr(type[i], key[i], null, null));
						sb.append("</td>");
					}
				}
				sb.append("</tr>");
			}
			sb.append("</table>");
		}
		return sb.toString();
	}
	
	private String inputStr(String type, String key, String value, String stateFlag){
		StringBuffer sb = new StringBuffer();
		if("text".equals(type)){
			sb.append("<input id=\"").append(key).append("\" name=\"").append(key).append("\" ");
			if(null != value && !"".equals(value.trim())){
				sb.append(" value=\"").append(value).append("\" ");
				if(null != stateFlag && "C".equals(stateFlag)){
					sb.append(" readonly=\"readonly\" ");
				}
			}
			sb.append("type=\"text\" size=\"30\" />");
		}else if("date".equals(type)){
			sb.append("<input id=\"").append(key).append("\" name=\"").append(key).append("\" ");
			if(null != stateFlag && "C".equals(stateFlag)){
				if(null != value && !"".equals(value.trim())){
					sb.append(" readonly=\"readonly\" ");
				}else{
					sb.append(" class=\"date \" pattern=\"yyyy-MM-dd\" readonly=\"readonly\" ");
				}
			}else{
				sb.append(" class=\"date \" pattern=\"yyyy-MM-dd\" readonly=\"readonly\" ");
			}
			if(null != value && !"".equals(value)){
				sb.append(" value=\"").append(value).append("\" ");
			}
			sb.append("type=\"text\" size=\"30\" />");
			
		}else if("radio".equals(type)){
			//判断key单独写方法 
			sb.append(radioStr(key, value, stateFlag));
		}else if("select".equals(type)){
			//判断key单独写方法 
			
		}
		return sb.toString();
	}
	
	private String radioStr(String key, String value, String stateFlag){
		StringBuffer sb = new StringBuffer();
		if("lblyd_sfdy".equals(key) || "xslyd_sfdy".equals(key)){//老板楼易贷/薪水楼易贷中的  “是否抵押”
			if(null != stateFlag && "C".equals(stateFlag)){
				sb.append("<input id=\"").append(key).append("\" name=\"").append(key).append("\" ");
				sb.append(" value=\"").append(value).append("\" ");
				sb.append(" readonly=\"readonly\" ");
				sb.append(" type=\"text\"  size=\"30\" />");
			}else{
				sb.append("<input id=\"").append(key).append("\" name=\"").append(key).append("\" ");
				sb.append(" value=\"是 \" ");
				if("是".equals(value)){
					sb.append("checked=\"checked\" ");
				}
				sb.append(" type=\"radio\"  />");
				sb.append("是&nbsp;&nbsp;");
				
				sb.append("<input id=\"").append(key).append("\" name=\"").append(key).append("\" ");
				sb.append(" value=\"否 \" ");
				if(!"是".equals(value)){
					sb.append("checked=\"checked\" ");
				}
				sb.append(" type=\"radio\"  />");
				sb.append("否&nbsp;&nbsp;");
			}
		}

		return sb.toString();
	}
	
	/**
	 * 	查看或编辑 的借款申请中
	 * 老板贷、老板楼易贷、薪水贷、薪水楼易贷、精英贷
	 * 的动态信息
	 * @param xhJksq			借款申请信息
	 * @param typeCode		借款类型
	 * @param flag				是否查找到用户已选择的借款类型，true为一直没找到
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String,String> getLoanShowDATA(XhJksq xhJksq, String typeCode, 
			String flag){
		
		return getLoanShowDATA(xhJksq, typeCode, flag, null);
	}
	
	/**
	 * 	查看或编辑或补充 的借款申请中
	 * 老板贷、老板楼易贷、薪水贷、薪水楼易贷、精英贷
	 * 的动态信息
	 * @param xhJksq			借款申请信息
	 * @param typeCode		借款类型
	 * @param flag				是否查找到用户已选择的借款类型，true为一直没找到
	 * @param stateFlag       C为借款申请的信息补充
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String,String> getLoanShowDATA(XhJksq xhJksq, String typeCode, 
			String flag, String stateFlag){
		Map<String,String> map = new HashMap<String,String>();
		if("true".equals(flag)){
			if(typeCode.equals(xhJksq.getJkType())){
				map.put("key", getLoanDATAValue(xhJksq, typeCode, stateFlag));
				map.put("flag", "false");
			}else{
				map.put("key", getLoanDATA(typeCode));
				map.put("flag", "true");
			}
		}else{
			map.put("key", getLoanDATA(typeCode));
			map.put("flag", "false");
		}
		return map;
	}
	
	private String getLoanDATAValue(XhJksq xhJksq, String typeCode, String stateFlag){
		StringBuffer sb = new StringBuffer();
		List<Templet> list = getTempletByType(typeCode);
		if(null != list && list.size()>0){
			Templet templet = list.get(0);
			String types = templet.getTypes();//页面组件类型格式：text、select等（采用数组方式存储数据）
			if(null == types || "".equals(types)){
//				return sb.append("模板组件类型配置有误！").toString();
				return sb.toString();
			}
			String names = templet.getNames();//页面显示名称格式：单位性质、职务、在岗时间等（采用数组方式存储数据）
			if(null == names || "".equals(names)){
//				return sb.append("模板组件页面显示名称格式配置有误！").toString();
				return sb.toString();
			}
			String keys = templet.getKeys();//页面表单名称格式：formData_key1、formData_key2、formData_key3等（采用数组方式存储数据）
			if(null == keys || "".equals(keys)){
//				return sb.append("模板组件类型名称配置有误！").toString();
				return sb.toString();
			}
			
			String values = xhJksq.getData();
			String [] type = types.split(",");
			String [] name = names.split(",");
			String [] key = keys.split(",");
			String [] value = values.split(",");
			if(value.length != 0 && (value.length != type.length || 
												value.length != name.length || 
												value.length != key.length) ){
//				return sb.append("模板组件配置有误,导致数据错误！").toString();
				return sb.toString();
			}
			
			int count = 0;
			int mod = type.length % 3;
			if(mod == 0){
				count = type.length /3;
			}else{
				count = type.length /3 + 1;
			}
			sb.append("<table>");
			for(int k=0;k<count;k++){
				sb.append("<tr>");
				if(k == 0){
					for(int i=0;i<((k+1)*3<type.length?(k+1)*3:type.length);i++){
						sb.append("<td><label>").append(name[i]).append("</label>");
						sb.append(inputStr(type[i], key[i], value[i].trim(), stateFlag));
						sb.append("</td>");
					}
				}else{
					for(int i=k*3;i<((k+1)*3<type.length?(k+1)*3:type.length);i++){
						sb.append("<td><label>").append(name[i]).append("</label>");
						sb.append(inputStr(type[i], key[i], value[i].trim(), stateFlag));
						sb.append("</td>");
					}
				}
				sb.append("</tr>");
			}
			sb.append("</table>");
		}
		return sb.toString();
	}
	
	@Autowired
	public void setTempletDao(TempletDao templetDao) {
		this.templetDao = templetDao;
	}
	


	
}
