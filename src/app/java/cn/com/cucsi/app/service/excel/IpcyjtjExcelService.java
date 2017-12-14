package cn.com.cucsi.app.service.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.core.orm.JdbcPage;
//业绩统计
@Service
public class IpcyjtjExcelService extends AbstractExcelService {

		@Autowired
		JdbcDao jdbcDao;
		
		@Override
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions) {
			mapToContidion(conditions);
			return jdbcDao.searchByMergeSqlTemplate("queryIpcyjtjList", conditions);
		}
		
		private void mapToContidion(Map<String,Object> filter){
			String sql = "";
			//String sqlDate = "";
		    if(StringUtils.hasText(filter.get("yyb") != null ? filter.get("yyb").toString():"")){
		    	sql += " and yybid = :yyb ";
		    	System.out.println(filter.get("yyb"));
		    }
		    if(StringUtils.hasText(filter.get("startdate") != null ? filter.get("startdate").toString():"")){
		    	sql +=  " and makeloans.make_loan_date >= to_date(:startdate,'yyyy-MM-dd')"; 
				
		    }
		    if(StringUtils.hasText(filter.get("overdate") != null ? filter.get("overdate").toString():"")){
		    	sql +=  " and makeloans.make_loan_date <= to_date(:overdate,'yyyy-MM-dd') ";
			}
		   // sql += sqlDate;
		    if(StringUtils.hasText(filter.get("backup01") != null ? filter.get("backup01").toString():"")){
		    	sql += " and jksq.backup01 = :backup01 ";
		    }
		    System.out.println(sql);
		    
		    //filter.put("","IPC");
		    filter.put("andConditionSql", sql);
		}
		
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions,JdbcPage page){
			mapToContidion(conditions);
			return jdbcDao.searchPagesByMergeSqlTemplate("queryIpcyjtjList", conditions, page);
		}
}
