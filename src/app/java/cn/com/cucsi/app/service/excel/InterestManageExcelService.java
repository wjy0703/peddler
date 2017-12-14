package cn.com.cucsi.app.service.excel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.DateConvert;
import cn.com.cucsi.core.orm.JdbcPage;
//业绩统计
@Service
public class InterestManageExcelService extends AbstractExcelService {

		@Autowired
		NamedJdbcDao jdbcDao;
		
		@Override
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions) {
			mapToContidion(conditions);
			List<Map<String, Object>> maps = jdbcDao.searchByMergeSqlTemplate("queryInterestManageList", conditions);
			return maps;
		}
		
		private void mapToContidion(Map<String,Object> filter){
		    //
		    String sqlrq = "";
		    String sqljg = "";
		    if(StringUtils.hasText(filter.get("yyb") != null ? filter.get("yyb").toString():"")){
		    	sqljg += " and yybid = :yyb ";
		    	System.out.println(filter.get("yyb"));
		    }
		    if(StringUtils.hasText(filter.get("date") != null ? filter.get("date").toString():"")){
				sqlrq += " and to_date(hetong.qdrq,'yyyy-MM-dd') = to_date(:date,'yyyy-MM-dd')"; 
//				Date date = DateConvert.convert(filter.get("date").toString(), "yyyy-MM-dd");
//				filter.put("date", date);
			}
		    
		    if(StringUtils.hasText(filter.get("realtimestart") != null ? filter.get("realtimestart").toString():"")){
		    	sqljg += " and to_date(realtime,'yyyy-MM-dd') >= to_date(:realtimestart,'yyyy-MM-dd')"; 
//				Date date = DateConvert.convert(filter.get("date").toString(), "yyyy-MM-dd");
//				filter.put("date", date);
			}
		    if(StringUtils.hasText(filter.get("realtimeend") != null ? filter.get("realtimeend").toString():"")){
		    	sqljg += " and to_date(realtime,'yyyy-MM-dd') <= to_date(:realtimeend,'yyyy-MM-dd')"; 
//				Date date = DateConvert.convert(filter.get("date").toString(), "yyyy-MM-dd");
//				filter.put("date", date);
			}
		    filter.put("andConditionSql", sqlrq);
		    filter.put("andConditionSql2", sqljg);
		}
		//select * fdfd  fsdf....;
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions,JdbcPage page){
			mapToContidion(conditions);
			List<Map<String, Object>> maps = jdbcDao.searchPagesByMergeSqlTemplate("queryInterestManageList", conditions,page);
			return maps;
		}

		private List<Map<String, Object>> rebuild(List<Map<String, Object>> maps) {
			if(maps == null)
				return null;
			for(int index = 0 ; index < maps.size() ; index++ ){
				Map<String, Object> item = maps.get(index);
				int months = Integer.parseInt(item.get("HKQS").toString());
				String startTime = item.get("QSHKDATE").toString();
				Date endTimeDate = CreditHarmonyComputeUtilties.getEndTimeByStartTimeAndCount(startTime, months);
				Date startDate = CreditHarmonyComputeUtilties.StringToDate(startTime, "yyyy-MM-dd");
				int days =(int) ((endTimeDate.getTime() - startDate.getTime())/(1000 * 60 * 60 * 24));
				item.put("BETWEENDAYS", days);
				item.put("ENDTIMEDATE",CreditHarmonyComputeUtilties.dateToString(endTimeDate));
			}
			return maps;
		}
}
