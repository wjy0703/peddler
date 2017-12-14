package cn.com.cucsi.app.dao.loan;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJkjjlxrDao extends HibernateDao<XhJkjjlxr, Long> {
	
	public Page<XhJkjjlxr> queryXhDkjjlxr(Page<XhJkjjlxr> page, Map<String, Object> filters) {
		String hql = "from XhJkjjlxr xhjkjjlxr where 1=1";
		
		if(filters.containsKey("name")){
			hql = hql + " and supplierName like '%'||:name||'%'";
		}
		if(filters.containsKey("sts")){
			hql = hql + " and sts = :sts";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}	
	
	/**
	 * 删除紧急联系人
	 * @param Id
	 * @return
	 */
	public boolean delXhJkjjlxr(Long Id, String tableState){
		boolean flag = false;
		StringBuffer sql = new StringBuffer("delete from XH_JKJJLXR where 1=1");
		if("XHJKSQ_ID".equals(tableState)){//借款申请
			sql.append("   and XHJKSQ_ID='").append(Id).append("'");
		}else if("XHJKSQ_TOGETHER_ID".equals(tableState)){//共同还款人
			sql.append("   and XHJKSQ_TOGETHER_ID='").append(Id).append("'");
		}else if("JKSQ_HISTORY_ID".equals(tableState)){//变更申请
			sql.append("   and JKSQ_HISTORY_ID='").append(Id).append("'");
		}else{//就是不让删除
			sql.append("   and XHJKSQ_ID='0'");
		}
//		String sql = "delete from XH_JKJJLXR where XHJKSQ_ID='"+Id+"'";
		int i = executeUpdateBySql(sql.toString());
		if(i != 0){
			System.out.println("删除"+i+"条XhJkjjlxr数据");
			flag = true;
		}
		return flag;
	}
	
	public List<XhJkjjlxr> getXhJkjjlxrList(Map<String, Object> filters){
		StringBuffer hql = new StringBuffer("from XhJkjjlxr xhjkjjlxr where 1=1 ");
		String value = "";
		String temp = "";
		if(filters.containsKey("personFlag")){//
			value = String.valueOf(filters.get("personFlag"));
			if(StringUtils.isNotEmpty(value)) {
				if("jksq".equals(value.trim())){//查找借款申请的紧急联系人
					
					if(filters.containsKey("Id")){//借款申请ID
						temp = String.valueOf(filters.get("Id"));
						if(StringUtils.isNotEmpty(temp)) {
							hql.append("   and xhjkjjlxr.xhJksq.id='").append(temp).append("'");
						}
					}
					
				}else if("jksqTogether".equals(value.trim())){//查共同借款人紧急联系人
					
					if(filters.containsKey("Id")){//共同还款人ID
						temp = String.valueOf(filters.get("Id"));
						if(StringUtils.isNotEmpty(temp)) {
							hql.append("   and xhjkjjlxr.xhJksqTogether.id='").append(temp).append("'");
						}
					}
					
				}else if("jksqChange".equals(value.trim())){//借款申请变更的紧急联系人
					
					if(filters.containsKey("Id")){//共同还款人ID
						temp = String.valueOf(filters.get("Id"));
						if(StringUtils.isNotEmpty(temp)) {
							hql.append("   and xhjkjjlxr.xhjksqjphistory.id='").append(temp).append("'");
						}
					}
					
				}
			}
		}

		if(filters.containsKey("ybrgx")){//与本人关系
			temp = String.valueOf(filters.get("ybrgx"));
			if(StringUtils.isNotEmpty(temp)) {
				hql.append("   and xhjkjjlxr.ybrgx='").append(temp).append("'");
			}
		}
		
		return this.find(hql.toString());
	}
	
	public List<XhJkjjlxr> listXhJkjjlxr(Long jksqId) {
		String hql = "from XhJkjjlxr xhJkjjlxr where xhJkjjlxr.xhJksq.id = "+jksqId;
		return this.find(hql);
	}
	
}