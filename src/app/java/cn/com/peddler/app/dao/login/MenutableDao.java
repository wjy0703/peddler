package cn.com.peddler.app.dao.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.peddler.app.entity.login.Menutable;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.orm.hibernate.HibernateDao;

@Component
public class MenutableDao extends HibernateDao<Menutable, Long>{
	/**
	 * 查询顶级菜单
	 * @return
	 */
	public List<Menutable> getMenusByLevel(Integer levelId){
		String hql = "from Menutable menu where menu.levelid=? and menu.vtypes = '0' order by menu.sortno";
		return this.find(hql, levelId);
	}
	/**
	 * 重载查询顶级菜单增加查询条件map
	 * @return
	 */
    public List<Menutable> getMenusByLevel(Map<String,Object> params){
        String hql = "from Menutable menu where menu.levelid=:levelId and menu.vtypes = '0' ";
        if (params.containsKey("sysTypeParam")) {
            hql = hql + " and menu.vsystype like '%'||:sysTypeParam||'%'";
        }
        hql = hql + " order by menu.sortno ";
        return this.find(hql, params);
    }
    /**
     * 重载根据上级菜单查询下级菜单加入查询条件map
     * @return
     */
    public List<Menutable> getMenusByParent(Map<String,Object> params){
        String hql = "from Menutable menu where menu.levelid != 4  and menu.vtypes = '0' ";
        if (params.containsKey("menuId")) {
            hql = hql + " and menu.parent.id=:menuId ";
        }
        if (params.containsKey("sysTypeParam")) {
            hql = hql + " and menu.vsystype like '%'|| :sysTypeParam ||'%' ";
        }
        hql = hql + " order by menu.sortno ";
        return this.find(hql, params);
    }
	/**
	 * 根据上级菜单查询下级菜单
	 * @param parentId
	 * @return
	 */
	public List<Menutable> getMenusByParent(Long parentId){
		String hql = "from Menutable menu where menu.levelid != 4 and menu.parentid=? and menu.vtypes = '0' order by menu.sortno";
		return this.find(hql, parentId);
	}
	
	public List<Menutable> queryMenutable(){
		String hql = "from Menutable menu where menu.vtypes=? order by menu.id asc";
		return this.find(hql, "0");
	}
	public Page<Menutable> queryMenutable(Page<Menutable> page, Map<String, Object> params){
//		String hql = "from Menutable menutable where 1=1";
		StringBuffer hql=new StringBuffer();
		hql.append("from Menutable menutable where 1=1");
		//是否是外部页面
		if(params.containsKey("vexternal")){
//			hql = hql + " and vexternal = :vexternal";
			hql.append(" and vexternal = :vexternal");
		}
		//是否相同页面引用中打开
		if(params.containsKey("fresh")){
//			hql = hql + " and fresh = :fresh";
			hql.append(" and fresh = :fresh");
		}
		//菜单编号
		if(params.containsKey("levelid")){
//			hql = hql + " and levelid = :levelid";
			hql.append(" and levelid = :levelid");
		}
		//菜单名
		if(params.containsKey("menuname")){
//			hql = hql + " and menuname = :menuname";
			hql.append(" and menuname = :menuname");
		}
		//菜单类型
		if(params.containsKey("menutype")){
//			hql = hql + " and menutype = :menutype";
			hql.append(" and menutype = :menutype");
		}
		//菜单地址
		if(params.containsKey("menuurl")){
//			hql = hql + " and menuurl = :menuurl";
			hql.append(" and menuurl = :menuurl");
		}
		//REL引用地址
		if(params.containsKey("rel")){
//			hql = hql + " and rel = :rel";
			hql.append(" and rel = :rel");
		}
		//序号
		if(params.containsKey("sortno")){
//			hql = hql + " and sortno = :sortno";
			hql.append(" and sortno = :sortno");
		}
		//菜单状态
		if(params.containsKey("vsts")){
//			hql = hql + " and vsts = :vsts";
			hql.append(" and vsts = :vsts");
		}
		//页面目标
		if(params.containsKey("target")){
//			hql = hql + " and target = :target";
			hql.append(" and target = :target");
		}
		//页面标题
		if(params.containsKey("title")){
//			hql = hql + " and title = :title";
			hql.append(" and title = :title");
		}
		//上级菜单
		if(params.containsKey("parentid")){
//			hql = hql + " and parentid = :parentid";
			hql.append(" and parentid = :parentid");
		}
		//系统属性
		if(params.containsKey("vtypes")){
//			hql = hql + " and vtypes = :vtypes";
			hql.append(" and vtypes = :vtypes");
		}
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
		if (page.getOrderBy()!=null){
//			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
			hql.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		}
		return this.findPage(page, hql.toString(), params);
	}
}
