package cn.com.cucsi.app.dao.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author 马道永
 */
@Component
public class MiddleManDao extends HibernateDao<MiddleMan, Long> {
	
	public Page<MiddleMan> queryMiddleMan(Page<MiddleMan> page, Map<String, Object> filters) {
		String hql = "from MiddleMan middleMan where 1=1";
		
		if(filters.containsKey("middleManName")){
			hql = hql + " and middleManName like '%'||:middleManName||'%'";
		}
		if(filters.containsKey("idCard")){
			hql = hql + " and idCard like '%'||:idCard||'%'";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}	
	
	public List<MiddleMan> getSuggestMiddleMan(String parentId) {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("parentId", parentId);
		String hql= "from MiddleMan middleMan where 1=1 order by id "; 
		return this.find(hql,filter);
	}
	
	/**
	 * 
	 * 根据条件查询中间人
	 * @param name                  姓名
	 * @param bankName              银行名
	 * @param bankAccountNumber     银行帐号 
	 * @return
	 * @author xjs
	 * @date 2013-8-8 下午1:28:23
	 */
	public List<MiddleMan> getMiddleManByNameAndBank(String name,String bankName,String bankAccountNumber) {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("name", name);
        filter.put("bankAccountNumber", bankAccountNumber);
        String hql= "from MiddleMan middleMan where middleMan.middleManName =:name";
        if(StringUtils.isNotEmpty(bankName)){
            filter.put("bankName", bankName);
            hql += " and middleMan.credAddr = :bankName ";
        }
        
        if(StringUtils.isNotEmpty(bankAccountNumber)){
            filter.put("bankAccountNumber", bankAccountNumber);
            hql += " and middleMan.credId = :bankAccountNumber ";             
        }
        return this.find(hql,filter);
    }
	
	
}
