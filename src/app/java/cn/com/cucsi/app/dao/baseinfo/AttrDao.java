package cn.com.cucsi.app.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class AttrDao extends HibernateDao<Attr, Long> {

	public List<Attr> getAttrList(Map<String, Object> filter) {
		String tempValue = null;
		StringBuffer hql = new StringBuffer("from Attr attr where 1=1");
		
		if(filter.containsKey("attrType")){//
			tempValue = String.valueOf(filter.get("attrType"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.attrType.id='").append(tempValue).append("'");
			}
		}
		
		if(filter.containsKey("value")){//
			tempValue = String.valueOf(filter.get("value"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.value='").append(tempValue).append("'");
			}
		}
		
		if(filter.containsKey("minValue")){//
			tempValue = String.valueOf(filter.get("minValue"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.value>='").append(tempValue).append("'");
			}
		}
		
		if(filter.containsKey("maxValue")){//
			tempValue = String.valueOf(filter.get("maxValue"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.value<='").append(tempValue).append("'");
			}
		}
		
		if(filter.containsKey("keyName")){//
			tempValue = String.valueOf(filter.get("keyName"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.keyName='").append(tempValue).append("'");
			}
		}
		
		if(filter.containsKey("description")){//
			tempValue = String.valueOf(filter.get("description"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.description='").append(tempValue).append("'");
			}
		}
		
		if(filter.containsKey("sortNo")){//
			tempValue = String.valueOf(filter.get("sortNo"));
			if(StringUtils.isNotEmpty(tempValue)) {
				hql.append("  and attr.sortNo='").append(tempValue).append("'");
			}
		}

		
		hql.append(" order by attr.sortNo asc ");
		
		return this.find(hql.toString());
	}
	
}
