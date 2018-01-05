package cn.com.peddler.app.dao.reat;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.peddler.app.entity.login.Reatpackage;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.orm.hibernate.HibernateDao;

@Component
public class ReatpackageDao extends HibernateDao<Reatpackage, Long>{

	public Page<Reatpackage> queryReatpackage(Page<Reatpackage> page, Map<String, Object> params){
//		String hql = "from Reatpackage reatpackage where 1=1";
		StringBuffer hql=new StringBuffer();
		hql.append("from Reatpackage reatpackage where 1=1");
		//套餐名称
		if(params.containsKey("reatname")){
//			hql = hql + " and reatname = :reatname";
			hql.append(" and reatname like '%'||:reatname||'%'");
		}
		//周期（月）
		if(params.containsKey("cycke")){
//			hql = hql + " and cycke = :cycke";
			hql.append(" and cycke = :cycke");
		}
		//套餐价格
		if(params.containsKey("price")){
//			hql = hql + " and price = :price";
			hql.append(" and price = :price");
		}
		//创建时间
		if(params.containsKey("createtime")){
//			hql = hql + " and createtime = :createtime";
			hql.append(" and createtime = :createtime");
		}
		//修改时间
		if(params.containsKey("modifytime")){
//			hql = hql + " and modifytime = :modifytime";
			hql.append(" and modifytime = :modifytime");
		}
		//创建人
		if(params.containsKey("createuser")){
//			hql = hql + " and createuser = :createuser";
			hql.append(" and createuser = :createuser");
		}
		//修改人
		if(params.containsKey("modifyuser")){
//			hql = hql + " and modifyuser = :modifyuser";
			hql.append(" and modifyuser = :modifyuser");
		}
		//属性
		if(params.containsKey("vtypes")){
//			hql = hql + " and vtypes = :vtypes";
			hql.append(" and vtypes = :vtypes");
		}
		if (page.getOrderBy()!=null){
//			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
			hql.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		}
		return this.findPage(page, hql.toString(), params);
	}
}
