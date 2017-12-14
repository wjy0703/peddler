package cn.com.cucsi.app.service.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.core.orm.JdbcPage;

@Service
public class YqbbExcelService extends AbstractExcelService {

	@Autowired
	JdbcDao jdbcDao;
	
	@Override
	public List<Map<String, Object>> queryRows(Map<String,Object> filter) {
		return jdbcDao.searchByMergeSqlTemplate("exportXhCapitalOverdueList", conditions(filter));
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCapitalOverdue(Map<String,Object> filter,JdbcPage page)
	{
		return jdbcDao.searchPagesByMergeSqlTemplate("exportXhCapitalOverdueList", conditions(filter), page);
	}
	
	public Map<String, Object> conditions(Map<String,Object> filter){
		String sql="";
		String value = "";
		//借款人名称
		if(filter.containsKey("lenderName")){
			value = String.valueOf(filter.get("lenderName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and j.jkrxm like '%" +  value + "%'";
			}
		}
		//合同编号
		if(filter.containsKey("lenderNumber")){
			value = String.valueOf(filter.get("lenderNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and h.jkhtbm like '%" +  value + "%'";
			}
		}
		//逾期时间
		if(filter.containsKey("overdueDate")){
			value = String.valueOf(filter.get("overdueDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_char(t.overdue_date,'YYYY-mm') = '" +  value + "'";
			}
		}
		//逾期状态
		if(filter.containsKey("overdueStatr")){
			value = String.valueOf(filter.get("overdueStatr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.OVERDUE_STATR = '" +  value + "'";
			}
		}
		
		String sql2 = "";
		
		if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql2 = sql2 + " and yybid = " + value + "";
				}
			}
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return conditions;
	}
}
