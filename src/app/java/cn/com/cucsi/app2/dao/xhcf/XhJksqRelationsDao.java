package cn.com.cucsi.app2.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app2.entity.xhcf.XhJksqRelations;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqRelationsDao extends HibernateDao<XhJksqRelations, Long>{

	public Page<XhJksqRelations> queryXhJksqRelations(Page<XhJksqRelations> page, Map<String, Object> params){
		String hql = "from XhJksqRelations xhJksqRelations where 1=1";
		//直系亲属姓名
		if(params.containsKey("name")){
			hql = hql + " and name = :name";
		}
		//直系亲属身份证号
		if(params.containsKey("idCard")){
			hql = hql + " and idCard = :idCard";
		}
		//直系亲属年龄
		if(params.containsKey("age")){
			hql = hql + " and age = :age";
		}
		//直系亲属电话
		if(params.containsKey("phone")){
			hql = hql + " and phone = :phone";
		}
		//单位名称
		if(params.containsKey("office")){
			hql = hql + " and office = :office";
		}
		//职务
		if(params.containsKey("officePosition")){
			hql = hql + " and officePosition = :officePosition";
		}
		//单位地址
		if(params.containsKey("officeAdress")){
			hql = hql + " and officeAdress = :officeAdress";
		}
		//单位电话
		if(params.containsKey("officePhone")){
			hql = hql + " and officePhone = :officePhone";
		}
		//月收入
		if(params.containsKey("monthIncome")){
			hql = hql + " and monthIncome = :monthIncome";
		}
		//直系亲属类别（0：父母，1：配偶，2：子女）
		if(params.containsKey("relClass")){
			hql = hql + " and relClass = :relClass";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	

	
}
