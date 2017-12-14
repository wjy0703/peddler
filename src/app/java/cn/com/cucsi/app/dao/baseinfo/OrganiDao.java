package cn.com.cucsi.app.dao.baseinfo;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author 马道永
 */
@Component
public class OrganiDao extends HibernateDao<Organi, Long> {
	
	//查询员工是否被账户引用的hql语句
	private static String hql_isReferenceToEmployee = "from User user where user.employee.id=?";
	
	public List<Organi> getOrganiByParent(Long parentId){
		String hql = "from Organi organi where organi.parentId=? and organi.organiFlag = '0' order by organi.id";
		return this.find(hql, parentId);
	}
	
	public List<Organi> queryCity(){
		String hql = "from City city where 1=? order by city.id asc";
		return this.find(hql, 1);
	}
	
	public List<Organi> getCities(final String cityFlag){
		String hql = "from City city where city.cityFlag=? order by city.id asc";
		return this.find(hql, cityFlag);
	}
	
	public List<Organi> getCities(final String cityCode,final Long cityId){
		String hql = "from City city where city.id=? ";
		if(cityCode.equals("0000")){
			hql += " or city.cityFlag='1' or city.cityFlag='0' order by city.cityCode";
		}
		return this.find(hql, cityId);
	}
	
	public List<Organi> getCitiList(final String code){
		String hql = "from City city where city.cityCode != ? order by city.id asc";
		return this.find(hql, code);
	}
}
