package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMaintainLogDao;
import cn.com.cucsi.app.entity.xhcf.XhMaintainLog;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.Java2Excel;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhMaintainLogManager {

	private XhMaintainLogDao xhMaintainLogDao;
	@Autowired
	public void setXhMaintainLogDao(XhMaintainLogDao xhMaintainLogDao) {
		this.xhMaintainLogDao = xhMaintainLogDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhMaintainLog> searchXhMaintainLog(final Page<XhMaintainLog> page, final Map<String,Object> filters) {
		return xhMaintainLogDao.queryXhMaintainLog(page, filters);
	}
	@Transactional(readOnly = true)
	public XhMaintainLog getXhMaintainLog(Long id) {
		return xhMaintainLogDao.get(id);
	}

	public void saveXhMaintainLog(XhMaintainLog entity) {
		xhMaintainLogDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhMaintainLog(Long id) {
		xhMaintainLogDao.delete(id);
	}
	
	public boolean batchDelXhMaintainLog(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhMaintainLog(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhMaintainLog(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//标题
		if(filter.containsKey("title")){
			value = String.valueOf(filter.get("title"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TITLE = '" +  value + "'";
			}
		}
		//详细内容
		if(filter.containsKey("detContent")){
			value = String.valueOf(filter.get("detContent"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DET_CONTENT = '" +  value + "'";
			}
		}
		
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	
	/**
	 * 导出系统日志维护报表
	 * @param path
	 * @param map
	 */
	@Transactional(readOnly = true)
	public void exportXhMaintainLog(String path,Map<String, Object> map){
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l ;
		String[] a;
		List<XhMaintainLog> list = xhMaintainLogDao.queryXhMaintainLogList(map);
		
		inputPath = mouFilePath + "xtwhrz.xls";
		
		if(map.size()==0){
			data.put("{1}", "");
			data.put("{2}", "");
			data.put("{3}", "");
			data.put("{4}", "xxxx年 x 月周总结（   .  .-   .  .)");
		}
		else{
			data.put("{1}", res(map.get("createBy")+""));
			data.put("{2}", res(map.get("startdate")+""));
			data.put("{3}", res(map.get("enddate")+""));
			
			//2013 年 9 月周总结（   .  .-   .  .)
			if(map.get("startdate")!=null&&map.get("enddate")!=null){
				int year = CreditHarmonyComputeUtilties.getYearbyDate(map.get("startdate").toString());
				int month = CreditHarmonyComputeUtilties.getMonthbyDate(map.get("startdate").toString());
				int month2 = CreditHarmonyComputeUtilties.getMonthbyDate(map.get("enddate").toString());
			    int day = CreditHarmonyComputeUtilties.getDaybyDate(map.get("startdate").toString());
			    int day2 = CreditHarmonyComputeUtilties.getDaybyDate(map.get("enddate").toString());
			    String in = year+"年"+month+"月周总结（"+month+"."+day+".-"+month2+"."+day2+".）";
			    data.put("{4}", in);
			}
			else if(map.get("enddate")==null&&map.get("startdate")!=null){
				int year = CreditHarmonyComputeUtilties.getYearbyDate(map.get("startdate").toString());
				int month = CreditHarmonyComputeUtilties.getMonthbyDate(map.get("startdate").toString());
				int day = CreditHarmonyComputeUtilties.getDaybyDate(map.get("startdate").toString());
				String in = year+"年xx月周总结（"+month+"."+day+".-至今）";
				data.put("{4}", in);
			}
			else {
				data.put("{4}", "xxxx年 x 月周总结（   .  .-   .  .)");
			}
			
		}

		
	    
		data.put("table", "1");
		data.put("table1", "0@4");// 第一个seet页，第2行开始
		l = new ArrayList();
		a = new String[10];
		for(int i = 0 ; i < a.length;i++){
			a[i] = i+"@v";
		}
	
		l.add(a);
		int xu = 0;
		for(XhMaintainLog m:list){
			xu++;
			a = new String[10];
    		a[0] = xu+"";
    		a[1] = res(m.getTitle()+"");
    		a[2] = res(m.getDetContent()+"");
    		this.checkWeek(m.getCreateTime(), m.getLastModifyTime(), a);
    		
    		l.add(a);
		}
		data.put("table10@4", l);// 第一个seet页，第3行开始
		
		
		je.toExcel(inputPath, path, data);
    }
	
	private String res(String value){
		if(StringUtils.isNotEmpty(value) && !"null".equals(value)) {
			return value;
		}else{
			return "";
		}
	}
	
	private void checkWeek(Date creatime,Date updatime,String a[]){
		int creweek = 0;
		int upweek = 0;
		if(creatime != null){
		 creweek=CreditHarmonyComputeUtilties.getWeek(creatime);
		}
		if(updatime != null){
		 upweek=CreditHarmonyComputeUtilties.getWeek(updatime);
		}
		int tag=2;
				
		for(int i=1;i < 8 ; i++){
			a[tag+i] = "";
			if(i==creweek){
				a[tag+i] = "√";
			}
			if(i==upweek){
				a[tag+i] = "√";
			}
		}
	}
	
}
