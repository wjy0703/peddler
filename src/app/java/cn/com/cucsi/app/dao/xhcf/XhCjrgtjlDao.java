package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhCjrgtjl;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjrgtjlDao extends HibernateDao<XhCjrgtjl, Long>{

	public Page<XhCjrgtjl> queryXhCjrgtjl(Page<XhCjrgtjl> page, Map<String, Object> params){
		String hql = "from XhCjrgtjl xhCjrgtjl where 1=1";
		//意向出资日期
		if(params.containsKey("yxczrq")){
			hql = hql + " and yxczrq = :yxczrq";
		}
		//下次联系日期
		if(params.containsKey("xclxrq")){
			hql = hql + " and xclxrq = :xclxrq";
		}
		//本次沟通日期
		if(params.containsKey("bcgtrq")){
			hql = hql + " and bcgtrq = :bcgtrq";
		}
		//沟通开始时间
		if(params.containsKey("gtkssj")){
			hql = hql + " and gtkssj = :gtkssj";
		}
		//沟通结束时间
		if(params.containsKey("gtjssj")){
			hql = hql + " and gtjssj = :gtjssj";
		}
		//本次联系人
		if(params.containsKey("bclxr")){
			hql = hql + " and bclxr = :bclxr";
		}
		//沟通内容描述
		if(params.containsKey("gtnrms")){
			hql = hql + " and gtnrms = :gtnrms";
		}
		//本次沟通方式
		if(params.containsKey("bcgtfs")){
			hql = hql + " and bcgtfs = :bcgtfs";
		}
		//意向产品
		if(params.containsKey("yxcp")){
			hql = hql + " and yxcp = :yxcp";
		}
		//下次联系方式
		if(params.containsKey("xclxfs")){
			hql = hql + " and xclxfs = :xclxfs";
		}
		//客户意向
		if(params.containsKey("khyx")){
			hql = hql + " and khyx = :khyx";
		}
		//客户状态。0.投资咨询，1.出借人
		if(params.containsKey("cjrState")){
			hql = hql + " and cjrState = :cjrState";
		}
		//意向出资金额
		if(params.containsKey("yxczje")){
			hql = hql + " and yxczje = :yxczje";
		}
		//意向出资金额
		if(params.containsKey("cjrxx_id")){
			params.put("cjrxx_id", Long.parseLong(params.get("cjrxx_id")+""));
			hql = hql + " and cjrxx.id = :cjrxx_id";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		System.out.println("hql===>" +hql);
		return this.findPage(page, hql, params);
	}
}
