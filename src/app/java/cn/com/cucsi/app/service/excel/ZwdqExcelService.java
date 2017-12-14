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
public class ZwdqExcelService extends AbstractExcelService {

	@Autowired
	JdbcDao jdbcDao;
	
	@Override
	public List<Map<String, Object>> queryRows(Map<String,Object> filter) {
		
		return jdbcDao.searchByMergeSqlTemplate("exportXhTzsqList", conditions(filter));
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhTzsq(Map<String,Object> filter,JdbcPage page)
	{
		return jdbcDao.searchPagesByMergeSqlTemplate("exportXhTzsqList", conditions(filter), page);
	}
	
	public Map<String, Object> conditions(Map<String,Object> filter){
		String sql="";
		String value = "";
		//协议编号
		if(filter.containsKey("tzsqbh")){
			value = String.valueOf(filter.get("tzsqbh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZSQBH = '" +  value + "'";
			}
		}
		
		
		sql = sql + " and a.STATE = '2'";
		sql = sql + " and a.HKSTATE = '2'";
		//sql = sql + " and a.OVERSTATE = '2'";
		sql = sql + " and (a.OVERSTATE = '2' or (a.CJZQ != '-1' and ADD_MONTHS(to_date(a.JHTZRQ,'yyyy-MM-dd'),a.CJZQ) < sysdate ))";
		
		if(filter.containsKey("cjrxm")){
			value = String.valueOf(filter.get("cjrxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.cjrxm like '%" +  value + "%'";
			}
		}
		if(filter.containsKey("khbm")){
			value = String.valueOf(filter.get("khbm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.khbm like '%" +  value + "%'";
			}
		}
		
		if(filter.containsKey("province")){
			value = String.valueOf(filter.get("province"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.province = '" +  value + "'";
			}
		}
		if(filter.containsKey("city")){
			value = String.valueOf(filter.get("city"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.city = '" +  value + "'";
			}
		}
		
		
		
		
		
		//级联查询sql
		//sql = sql + PropertiesUtils.getSql(filter);
				
//		if (page.getOrderBy()!=null){
//			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
//		}
		sql = sql + " order by  a.create_time desc";
		String sql2="";
		
		
		if(filter.containsKey("startdate")){
			value = String.valueOf(filter.get("startdate"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 +=  " and moqidate >= to_date('"+value+"','yyyy-MM-dd')"; 
			}
	    }
		if(filter.containsKey("overdate")){
			value = String.valueOf(filter.get("overdate"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 +=  " and moqidate <= to_date('"+value+"','yyyy-MM-dd') ";
			}
		}
		if(filter.containsKey("yyb")){
			value = String.valueOf(filter.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" +  value + "'";
			}
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return conditions;
	}
}
