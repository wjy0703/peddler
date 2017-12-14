package cn.com.cucsi.app.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMaintainLog;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMaintainLogDao extends HibernateDao<XhMaintainLog, Long>{

	public Page<XhMaintainLog> queryXhMaintainLog(Page<XhMaintainLog> page, Map<String, Object> params){
		String hql = this.hql(params);
		return this.findPage(page, hql, params);
	}
	public List<XhMaintainLog> queryXhMaintainLogList(Map<String, Object> params){
		String hql = this.hql(params);
		return this.find(hql, params);
	}
	
	private String hql(Map<String, Object> params){
		String hql = " from XhMaintainLog xhMaintainLog where 1=1";
		//标题
		if(params.containsKey("title")){
			hql = hql + " and title like '%'||:title||'%'";
		}
		//详细内容
		if(params.containsKey("detContent")){
			hql = hql + " and detContent = :detContent";
		}
		//详细内容
		if(params.containsKey("createBy")){
			hql = hql + " and createBy like '%'||:createBy||'%'";
		}
		if (params.containsKey("startdate")) {
			hql = hql + " and createTime >= to_date(:startdate,'yyyy-MM-dd') ";
		}
		if (params.containsKey("enddate")) {
			hql = hql + " and to_date(to_char(createTime,'yyyy-MM-dd'),'yyyy-MM-dd') <= to_date(:enddate,'yyyy-MM-dd') ";
		}
		hql = hql + " order by createTime desc";
		return hql;
	}
}
