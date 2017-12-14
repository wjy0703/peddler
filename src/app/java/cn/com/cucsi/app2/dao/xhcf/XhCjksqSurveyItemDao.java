package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyItem;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjksqSurveyItemDao extends HibernateDao<XhCjksqSurveyItem, Long>{

	public Page<XhCjksqSurveyItem> queryXhCjksqSurveyItem(Page<XhCjksqSurveyItem> page, Map<String, Object> params){
		String hql = "from XhCjksqSurveyItem xhCjksqSurveyItem where 1=1";
		//外访要求
		if(params.containsKey("demandWords")){
			hql = hql + " and demandWords = :demandWords";
		}
		//外访回复
		if(params.containsKey("demandrReply")){
			hql = hql + " and demandrReply = :demandrReply";
		}
		//外访类型   0:外访家庭，1:外访单位
		if(params.containsKey("surveyType")){
			hql = hql + " and surveyType = :surveyType";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
