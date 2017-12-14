package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhTzsqState;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhTzsqStateDao extends HibernateDao<XhTzsqState, Long>{

	public Page<XhTzsqState> queryXhTzsqState(Page<XhTzsqState> page, Map<String, Object> params){
		String hql = "from XhTzsqState xhTzsqState where 1=1";
		//主键
		if(params.containsKey("id")){
			hql = hql + " and id = :id";
		}
		//创建人/操作人
		if(params.containsKey("createBy")){
			hql = hql + " and createBy = :createBy";
		}
		//创建时间/操作时间
		if(params.containsKey("createTime")){
			hql = hql + " and createTime = :createTime";
		}
		//最后修改人
		if(params.containsKey("lastModifyBy")){
			hql = hql + " and lastModifyBy = :lastModifyBy";
		}
		//最后修改时间
		if(params.containsKey("lastModifyTime")){
			hql = hql + " and lastModifyTime = :lastModifyTime";
		}
		//借款申请流程状态说明
		if(params.containsKey("describe")){
			hql = hql + " and describe = :describe";
		}
		//投资申请ID
		if(params.containsKey("tzsqId")){
			hql = hql + " and tzsqId = :tzsqId";
		}
		//备注
		if(params.containsKey("remarks")){
			hql = hql + " and remarks = :remarks";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
