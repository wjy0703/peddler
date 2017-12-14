package cn.com.cucsi.app.service.OperationAnalysis;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.core.web.ServletUtils;

import com.et.mvc.JsonView;
import org.springframework.ui.Model;

/**
 * 基础信息相关实体的管理类, 经营分析类.
 * 
 * @author mdy
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class AnalysisManager extends PublicService{

	private static Logger logger = LoggerFactory.getLogger(AnalysisManager.class);

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	public String getRegionalSumTotal(Map<String, Object> filter, String title){
		StringBuffer sql = new StringBuffer("select a.rgani_name as name,nvl(REPORT_UTIL.getTzsqMoneyByOrgId(a.id),0) as sum from BASE_ZZJG a where 1 = 1");
		String value = "";
		if (filter.containsKey("startDate") && filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql.append(" ");
			}
		}
		sql.append(" and a.level_mess='CFAREA'");
		sql.append(" order by sum desc");
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql.toString());
		String json = "";
		if (filter.containsKey("type")) {
			value = String.valueOf(filter.get("type"));
			if ("pie".equals(value)) {
				json = getFormatPieJsonData(listMap);
			}else{
				json = getFormatColumnJsonData(listMap, title);
			}
		}
		return json;
	}
	
	public String getStoreSumTotal(Map<String, Object> filter, String title){
		StringBuffer sql = new StringBuffer("select a.rgani_name as name,nvl(REPORT_UTIL.getTzsqMoneyByOrgId(a.id),0) as sum from BASE_ZZJG a where 1 = 1");
		String value = "";
		if (filter.containsKey("startDate") && filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql.append(" ");
			}
		}
		sql.append(" and a.level_mess='cfmd'");
		sql.append(" order by sum desc");
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql.toString());
		String json = "";
		if (filter.containsKey("type")) {
			value = String.valueOf(filter.get("type"));
			if ("pie".equals(value)) {
				json = getFormatPieJsonData(listMap);
			}else{
				json = getFormatColumnJsonData(listMap, title);
			}
		}
		return json;
	}
	
	public String getTremSumTotal(List<Map<String,Object>> tuanDuiTree){
		String json = "";
		String date = this.getFormatDateMonth();
		for(int i = 0 ; i < tuanDuiTree.size() ; i++){
			Map<String, Object> map = tuanDuiTree.get(i);
			String id = map.get("ID").toString();
			StringBuffer sql = new StringBuffer("select * from table(REPORT_UTIL.getTzsqGroupYear("+id+",'"+date+"'))");
			List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql.toString());
			json += "{name: '"+map.get("RGANI_NAME")+"',data: [";
			json += getFormatLineJsonData(listMap);
			json += "]},";
		}
		json += getCategoriesInfo();
		return json;
	}
	
	public String getTiele(List<Map<String,Object>> mendianTree, Long oldId){
		String RGANI_NAME = "";
		for(int i = 0 ; i < mendianTree.size() ; i++){
			Map<String, Object> map = mendianTree.get(i);
			String id = map.get("ID").toString();
			if(id.equals(oldId.toString())){
				RGANI_NAME = map.get("RGANI_NAME").toString();
				break;
			}
		}
		return RGANI_NAME;
	}
	
	/**
	 * 线型Line数据处理data MDY 2013-07-32
	 * @param listMap
	 * @return
	 */
	public String getFormatLineJsonData(List<Map<String, Object>> listMap){
		StringBuffer json = new StringBuffer();
		if(listMap.size() == 12){
			for(int i = 0 ; i < listMap.size() ; i++){
				Map<String, Object> map = listMap.get(i);
				String COLUMN_VALUE = map.get("COLUMN_VALUE").toString();
				String[] newValue = COLUMN_VALUE.split("\\|");
				json.append(new Integer(newValue[0])+",");
			}
			if(json.length() > 0){
				json.deleteCharAt(json.lastIndexOf(","));
			}
		}
		return json.toString();
	}
	
	public String getCategoriesInfo(){
		StringBuffer categories = new StringBuffer();
		categories.append("{categories:[");
		String[] month = this.getYearMonths();
		for(int i = 0 ; i < month.length ; i++){
			categories.append("'"+month[i]+"',");
		}
		categories.deleteCharAt(categories.lastIndexOf(","));
		categories.append("]}");
		return categories.toString();
	}

	/**
	 * 饼型pie数据处理data MDY 2013-07-31
	 * @param listMap
	 * @return
	 */
	public String getFormatPieJsonData(List<Map<String, Object>> listMap){
		StringBuffer json = new StringBuffer();
		json.append("{data: [");
		for(int i = 0 ; i < listMap.size() ; i++){
			Map<String, Object> map = listMap.get(i);
			String name = map.get("NAME").toString();
			String sum = map.get("SUM").toString();
			json.append("['"+name+"',  "+new Integer(sum)+"],");
		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("]},{categories:['a', 'b']}");
		return json.toString();
	}
	
	/**
	 * 柱型column数据处理data MDY 2013-07-31
	 * @param listMap
	 * @return
	 */
	public String getFormatColumnJsonData(List<Map<String, Object>> listMap, String title){
		StringBuffer json = new StringBuffer();
		StringBuffer categories = new StringBuffer();
		categories.append("{categories:[");
		for(int i = 0 ; i < listMap.size() ; i++){
			Map<String, Object> map = listMap.get(i);
			String name = map.get("NAME").toString();
			String sum = map.get("SUM").toString();
			json.append("{name: '"+name+"',data: ["+new Integer(sum)+"]},");
		}
		categories.append("'"+title+"',");
		categories.deleteCharAt(categories.lastIndexOf(","));
		categories.append("]}");
		json.append(categories.toString());
		return json.toString();
	}
	
	public String getSubtitleInfo(Map<String, Object> map){
		String subtitle = "";
		if(map != null){
			if (map.containsKey("startDate") && map.containsKey("endDate")) {
				subtitle = map.get("startDate")+" 至 "+map.get("endDate");
			}
		}
		return subtitle;
	}
}
