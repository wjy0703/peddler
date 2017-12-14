package cn.com.cucsi.app.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 菜单对象的泛型DAO.
 * 
 * @author jiangxd
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {
	
	/**
	 * 查询顶级菜单
	 * @return
	 */
	public List<Menu> getMenusByLevel(Integer levelId){
		String hql = "from Menu menu where menu.levelId=? and menu.sts = '0' order by menu.sortNo";
		return this.find(hql, levelId);
	}

	/**
	 * 根据上级菜单查询下级菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> getMenusByParent(Long parentId){
		String hql = "from Menu menu where menu.levelId != 4 and menu.parent.id=? and menu.sts = '0' order by menu.sortNo";
		return this.find(hql, parentId);
	}
	
	public List<Menu> queryMenu(){
		String hql = "from Menu menu where menu.sts=? order by menu.id asc";
		return this.find(hql, "0");
	}
	
	public Page<Menu> queryMenu(Page<Menu> page, Map<String, Object> filters) {
		String hql = "from Menu menu where 1=1";
		
		if(filters.containsKey("menuName")){
			hql = hql + " and menuName like '%'||:menuName||'%'";
		}
		if(filters.containsKey("sts")){
			hql = hql + " and sts = :sts";
		}
		if(filters.containsKey("menuType")){
			String menuType = filters.get("menuType").toString();
			hql = hql + " and menuType = "+Integer.valueOf(menuType);
			filters.remove("menuType");
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}	
	
	
}
