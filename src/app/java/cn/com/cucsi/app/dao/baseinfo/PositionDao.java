package cn.com.cucsi.app.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.BasePosition;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;


@Component
public class PositionDao extends HibernateDao<BasePosition, Long> {

	public List<BasePosition> getSuggestPosition() {
		String hql = "from BasePosition";

		return this.find(hql);
	}
	
	public Page<BasePosition> queryBasePosition(Page<BasePosition> page, Map<String, Object> params){
		String hql = "from BasePosition basePosition where 1=1";
		//职务ID
		if(params.containsKey("id")){
			hql = hql + " and id = :id";
		}
		
		
		if(params.containsKey("positionName")){
			hql = hql + " and positionName = :positionName";
		}
		//职务权限级别
		if(params.containsKey("positionCode")){
			hql = hql + " and positionCode = :positionCode";
		}
		
	
		//职级标准
		if(params.containsKey("positionLevel")){
			hql = hql + " and positionLevel = :positionLevel";
		}
		//职级英文编码
		if(params.containsKey("positionLevelCode")){
			hql = hql + " and positionLevelCode = :positionLevelCode";
		}
		//职级VALUE
		if(params.containsKey("positionLevelValue")){
			hql = hql + " and positionLevelValue = :positionLevelValue";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
}
