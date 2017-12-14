package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyDetail;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjksqSurveyDetailsDao extends HibernateDao<XhCjksqSurveyDetail, Long>{

	public Page<XhCjksqSurveyDetail> queryXhCjksqSurveyDetails(Page<XhCjksqSurveyDetail> page, Map<String, Object> params){
		String hql = "from XhCjksqSurveyDetails xhCjksqSurveyDetails where 1=1";
		//外访具体条目(对应枚举类中的内容)
		if(params.containsKey("itemsName")){
			hql = hql + " and itemsName = :itemsName";
		}
		//外访具体条目结果0:否  1：通过
		if(params.containsKey("itemsResult")){
			hql = hql + " and itemsResult = :itemsResult";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
