package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhZqtjDetails;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhZqtjDetailsDao extends HibernateDao<XhZqtjDetails, Long>{

	public Page<XhZqtjDetails> queryXhZqtjDetails(Page<XhZqtjDetails> page, Map<String, Object> params){
		String hql = "from XhZqtjDetails xhZqtjDetails where 1=1";
		//债权推荐ID
		if(params.containsKey("zqtjId")){
			hql = hql + " and zqtjId = :zqtjId";
		}
		//资金
		if(params.containsKey("money")){
			hql = hql + " and money = :money";
		}
		//还款周期
		if(params.containsKey("hkzq")){
			hql = hql + " and hkzq = :hkzq";
		}
		//可用债权价值ID
		if(params.containsKey("kyzqjzId")){
			hql = hql + " and kyzqjzId = :kyzqjzId";
		}
		//债权持有比例
		if(params.containsKey("zqcybi")){
			hql = hql + " and zqcybi = :zqcybi";
		}
		
		
		//可用债权价值ID
		if(params.containsKey("zqtjstate")){
			hql = hql + " and xhZqtjDetails.xhZqtj.state != :zqtjstate";
		}
		//债权持有比例
		if(params.containsKey("overstate")){
			hql = hql + " and xhZqtjDetails.xhZqtj.xhTzsq.overstate != :overstate";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
