package cn.com.peddler.app.dao.login;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.peddler.app.entity.security.Authority;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.orm.hibernate.HibernateDao;

@Component
public class AuthorityDao extends HibernateDao<Authority, Long>{

	public Page<Authority> queryAuthority(Page<Authority> page, Map<String, Object> params){
//		String hql = "from Authority authority where 1=1";
		StringBuffer hql=new StringBuffer();
		hql.append("from Authority authority where 1=1");
		//修改人
		if(params.containsKey("modifyuser")){
//			hql = hql + " and modifyuser = :modifyuser";
			hql.append(" and modifyuser = :modifyuser");
		}
		//创建人
		if(params.containsKey("createuser")){
//			hql = hql + " and createuser = :createuser";
			hql.append(" and createuser = :createuser");
		}
		//修改时间
		if(params.containsKey("modifytime")){
//			hql = hql + " and modifytime = :modifytime";
			hql.append(" and modifytime = :modifytime");
		}
		//创建时间
		if(params.containsKey("createtime")){
//			hql = hql + " and createtime = :createtime";
			hql.append(" and createtime = :createtime");
		}
		//别名
		if(params.containsKey("cname")){
//			hql = hql + " and cname = :cname";
			hql.append(" and cname = :cname");
		}
		//标记
		if(params.containsKey("vflag")){
//			hql = hql + " and vflag = :vflag";
			hql.append(" and vflag = :vflag");
		}
		//资源名称
		if(params.containsKey("aname")){
//			hql = hql + " and aname = :aname";
			hql.append(" and aname = :aname");
		}
		//路径
		if(params.containsKey("vpath")){
//			hql = hql + " and vpath = :vpath";
			hql.append(" and vpath = :vpath");
		}
		//状态
		if(params.containsKey("vsts")){
//			hql = hql + " and vsts = :vsts";
			hql.append(" and vsts = :vsts");
		}
		//类型
		if(params.containsKey("vtype")){
//			hql = hql + " and vtype = :vtype";
			hql.append(" and vtype = :vtype");
		}
		//系统属性
		if(params.containsKey("vsystype")){
//			hql = hql + " and vsystype = :vsystype";
			hql.append(" and vsystype = :vsystype");
		}
		if (page.getOrderBy()!=null){
//			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
			hql.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		}
		return this.findPage(page, hql.toString(), params);
	}
}
