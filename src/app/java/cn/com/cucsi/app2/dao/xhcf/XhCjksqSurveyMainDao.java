package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyMain;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjksqSurveyMainDao extends HibernateDao<XhCjksqSurveyMain, Long>{

	public Page<XhCjksqSurveyMain> queryXhCjksqSurveyMain(Page<XhCjksqSurveyMain> page, Map<String, Object> params){
		String hql = "from XhCjksqSurveyMain xhCjksqSurveyMain where 1=1";
		//外访要求的上传表格（多个用，隔开)
		if(params.containsKey("demandWordTemplate")){
			hql = hql + " and demandWordTemplate = :demandWordTemplate";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
