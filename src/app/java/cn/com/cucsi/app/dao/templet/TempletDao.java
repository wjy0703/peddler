package cn.com.cucsi.app.dao.templet;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.Templet;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class TempletDao extends HibernateDao<Templet, Long>{

	public List<Templet> findTempletByType(String type){
		String hql = "from Templet templet where state = '0' and templet.type = '"+type+"'";
		return find(hql);
	}
	
}
