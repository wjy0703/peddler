package cn.com.cucsi.app.dao.loan;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJksqUPHistory;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqUPHistoryDao extends HibernateDao<XhJksqUPHistory, Long>{

	public Page<XhJksqUPHistory> queryXhJksqUPHistory(Page<XhJksqUPHistory> page, Map<String, Object> params){
		StringBuffer hql = new StringBuffer("from XhJksqUPHistory jksqhistory where 1=1");
		if(params.containsKey("xhjksq")){//借款申请ID
			String value = String.valueOf(params.get("xhjksq"));
			if(StringUtils.isNotEmpty(value)) {
				hql.append("  and jksqhistory.xhjksq.id='").append(value).append("'");
			}
		}
		
		if (page.getOrderBy()!=null){
			hql.append(" order by ").append(page.getOrderBy()).append("  ").append(page.getOrder());
		}
		return this.findPage(page, hql.toString(), params);
	}
	
	//以下方法未修改呢
	public boolean updJksqChange(Long Id,Map<String, Object> params){
		boolean flag = false;
		
		StringBuffer sql = new StringBuffer("update XH_JKSQ  jksq set ");
		if(params.containsKey("state")){//借款申请大的状态
			String value = String.valueOf(params.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append("jksq.STATE='").append(value).append("',");
			}
		}
		
		int temp = sql.lastIndexOf(",");
		if(temp == sql.length()-1){
			sql.deleteCharAt(temp);
		}
		sql.append(" where ID='").append(Id).append("'");
		
		int i = executeUpdateBySql(sql.toString());
		if(i != 0){
			System.out.println("修改"+i+"条XhJksq数据");
			flag = true;
		}
		return flag;
	}
}
