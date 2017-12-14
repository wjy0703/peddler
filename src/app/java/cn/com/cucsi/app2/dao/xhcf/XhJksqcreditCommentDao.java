package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhJksqcreditComment;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqcreditCommentDao extends HibernateDao<XhJksqcreditComment, Long>{

	public Page<XhJksqcreditComment> queryXhJksqcreditComment(Page<XhJksqcreditComment> page, Map<String, Object> params){
		String hql = "from XhJksqcreditComment xhJksqcreditComment where 1=1";
		//114/10000查询 之（是，否，其他）
		if(params.containsKey("onefour")){
			hql = hql + " and onefour = :onefour";
		}
		//114/10000查询之备注
		if(params.containsKey("onefourComment")){
			hql = hql + " and onefourComment = :onefourComment";
		}
		//红盾网/工商局网之(是，否，其他)
		if(params.containsKey("gzaic")){
			hql = hql + " and gzaic = :gzaic";
		}
		//红盾网/工商局网之备注
		if(params.containsKey("gzaicComment")){
			hql = hql + " and gzaicComment = :gzaicComment";
		}
		//百度网查公司/个人信息之(是，否，其他)
		if(params.containsKey("baidu")){
			hql = hql + " and baidu = :baidu";
		}
		//百度网查公司/个人信息之备注
		if(params.containsKey("baiduComment")){
			hql = hql + " and baiduComment = :baiduComment";
		}
		//P2P网络逾期黑名单查询之(是，否，其他)
		if(params.containsKey("ptopnet")){
			hql = hql + " and ptopnet = :ptopnet";
		}
		//P2P网络逾期黑名单查询之备注
		if(params.containsKey("ptopnetComment2")){
			hql = hql + " and ptopnetComment2 = :ptopnetComment2";
		}
		//全国法院被执行人信息查询之(是，否，其他)
		if(params.containsKey("court")){
			hql = hql + " and court = :court";
		}
		//全国法院被执行人信息查询之备注
		if(params.containsKey("courtComment")){
			hql = hql + " and courtComment = :courtComment";
		}
		//其他重要资料说明及风险点
		if(params.containsKey("othercomment")){
			hql = hql + " and othercomment = :othercomment";
		}
		//114/10000查询之备注
		if(params.containsKey("onefourComment2")){
			hql = hql + " and onefourComment2 = :onefourComment2";
		}
		//其他借款统计
		if(params.containsKey("otherBorrowComment")){
			hql = hql + " and otherBorrowComment = :otherBorrowComment";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
