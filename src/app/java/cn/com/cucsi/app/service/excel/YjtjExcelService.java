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
public class YjtjExcelService extends AbstractExcelService {

		@Autowired
		JdbcDao jdbcDao;
		
		@Override
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions) {
			mapToContidion(conditions);
			return jdbcDao.searchByMergeSqlTemplate("queryYjtjList", conditions);
		}
		
		private void mapToContidion(Map<String,Object> filter){
			String sql = "";
			//String sqlDate = "";
			String sql1 ="";
			String value = "";
		    if(StringUtils.hasText(filter.get("yyb") != null ? filter.get("yyb").toString():"")){
		    	sql += " and yybid = :yyb ";
		    	System.out.println(filter.get("yyb"));
		    }
		    if(StringUtils.hasText(filter.get("startdate") != null ? filter.get("startdate").toString():"")){
		    	sql += " and MAKE_LOAN_DATE >= to_date(:startdate,'yyyy-MM-dd')"; 
				
		    }
		    if(StringUtils.hasText(filter.get("overdate") != null ? filter.get("overdate").toString():"")){
		    	sql += " and MAKE_LOAN_DATE <= to_date(:overdate,'yyyy-MM-dd') ";
			}
		    //sql += sqlDate;
		    if(StringUtils.hasText(filter.get("backup01") != null ? filter.get("backup01").toString():"")){
		    	sql += " and backup01 = :backup01 ";
		    }
		    System.out.println(sql);
		    if(StringUtils.hasText(filter.get("province") != null ? filter.get("province").toString():"")){
		    	sql1 += " and jksq.province = :province ";
		    }
		    
		    if(StringUtils.hasText(filter.get("city") != null ? filter.get("city").toString():"")){
		    	sql1 += " and jksq.city = :city ";
		    }
		    if(StringUtils.hasText(filter.get("state") != null ? filter.get("state").toString():"")){
		    	sql1 += " and jksq.state = :state ";
		    }
		    if(StringUtils.hasText(filter.get("khxm") != null ? filter.get("khxm").toString():"")){
		    	 value = String.valueOf(filter.get("khxm"));
		    	sql1 += " and jksq.jkrxm like '%"+value+"%'";
		    }
		    if(StringUtils.hasText(filter.get("hkr") != null ? filter.get("hkr").toString():"")){
		    	sql1 += " and hetong.hkr = :hkr ";
		    }
		    //filter.put("","IPC");
		    filter.put("andConditionSql", sql);
		    filter.put("sql", sql1);
		}
		
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions,JdbcPage page){
			mapToContidion(conditions);
			return jdbcDao.searchPagesByMergeSqlTemplate("queryYjtjList", conditions, page);
		}
}
