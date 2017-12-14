package cn.com.cucsi.app.service.excel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.JdbcPage;
//业绩统计
@Service
public class LoanBackExcelService extends AbstractExcelService {

		@Autowired
		JdbcDao jdbcDao;
		
		@Override
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions) {
			return jdbcDao.searchByMergeSqlTemplate("loanBackReportList", conditions(conditions));
		}
		
		public List<Map<String, Object>> queryOrgin() {
			return jdbcDao.searchByMergeSqlTemplate("queryOrginList", new HashMap<String, Object>());
		}
		
		private Map<String, Object>  conditions(Map<String,Object> filter){
			String sql="";
			String value = "";
			
			//计划投资日期
			if(filter.containsKey("jhtzrq")){
				value = String.valueOf(filter.get("jhtzrq"));
				if(StringUtils.isNotEmpty(value)) {
					sql = sql + " and to_char(h.make_loan_date,'yyyy-MM') = '" +  value + "'";
				}
			}
			if(filter.containsKey("jkrxm")){
				value = String.valueOf(filter.get("jkrxm"));
				if(StringUtils.isNotEmpty(value)) {
					sql = sql + " and b.jkrxm like '%" +  value + "%'";
				}
			}
			if(filter.containsKey("state")){
				value = String.valueOf(filter.get("state"));
				if(StringUtils.isNotEmpty(value)) {
					sql = sql + " and b.state = '" +  value + "'";
				}
			}
			
			
			//级联查询sql
			//sql = sql + PropertiesUtils.getSql(filter);
					
//			if (page.getOrderBy()!=null){
//				sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
//			}
			//sql = sql + " order by  a.create_time desc";
			String sql2="";
			if(filter.containsKey("yyb")){
				value = String.valueOf(filter.get("yyb"));
				if(StringUtils.isNotEmpty(value)) {
					sql2 = sql2 + " and ROW_.ORGID = " +  value + "";
				}
			}
			System.out.println("sql=======>" + sql);
			Map<String, Object> conditions = new HashMap<String,Object>();
			conditions.put("andConditionSql", sql);
			conditions.put("andConditionSql2", sql2);
			return conditions;
		}
		
		public List<Map<String, Object>> queryRows(Map<String,Object> conditions,JdbcPage page){
			return jdbcDao.searchPagesByMergeSqlTemplate("loanBackReportList", conditions(conditions),page);
		}
}
