package cn.com.cucsi.vechicle.dao;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarMessage;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarState;

@Component
public class XhCarStateDao extends HibernateDao<XhCarState, Long>{
	
    public Page<XhCarState> query(Page<XhCarState> page,Long Id){
        String hql = "from XhCarState xhCarState where 1=1";
        hql = hql + " and xhCarState.xhCarLoanApply.id='" + Id + "' ";
        if (page.getOrder()!=null){
        	hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
        }else{
        	hql = hql + " order by createTime desc";
        }
        return this.findPage(page, hql);
    }
}
