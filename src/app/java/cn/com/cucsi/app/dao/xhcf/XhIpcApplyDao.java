package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhIpcApply;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhIpcApplyDao extends HibernateDao<XhIpcApply, Long>{

	public Page<XhIpcApply> queryXhIpcApply(Page<XhIpcApply> page, Map<String, Object> params){
		String hql = "from XhIpcApply xhIpcApply where 1=1";
		//客户姓名
		if(params.containsKey("customerName")){
			hql = hql + " and customerName = :customerName";
		}
		//客户编号
		if(params.containsKey("customerNum")){
			hql = hql + " and customerNum = :customerNum";
		}
		//客户电话
		if(params.containsKey("customerPhone")){
			hql = hql + " and customerPhone = :customerPhone";
		}
		//开户行
		if(params.containsKey("customerCardId")){
			hql = hql + " and customerCardId = :customerCardId";
		}
		//账户名
		if(params.containsKey("bankName")){
			hql = hql + " and bankName = :bankName";
		}
		//账（卡）号
		if(params.containsKey("bankCardNum")){
			hql = hql + " and bankCardNum = :bankCardNum";
		}
		//共借人姓名
		if(params.containsKey("togetherName")){
			hql = hql + " and togetherName = :togetherName";
		}
		//共借人关系
		if(params.containsKey("togetherRelation")){
			hql = hql + " and togetherRelation = :togetherRelation";
		}
		//共借人电话
		if(params.containsKey("togetherPhone")){
			hql = hql + " and togetherPhone = :togetherPhone";
		}
		//商铺（单位）地址
		if(params.containsKey("customerCompAddress")){
			hql = hql + " and customerCompAddress = :customerCompAddress";
		}
		//家庭住址
		if(params.containsKey("customerHomeAddress")){
			hql = hql + " and customerHomeAddress = :customerHomeAddress";
		}
		//共借人住址
		if(params.containsKey("togetherHomeAddress")){
			hql = hql + " and togetherHomeAddress = :togetherHomeAddress";
		}
		//通讯录 
		if(params.containsKey("txl")){
			hql = hql + " and txl = :txl";
		}
		//开发信贷员

		if(params.containsKey("kfEmp")){
			hql = hql + " and kfEmp = :kfEmp";
		}
		//负责信贷员
		if(params.containsKey("fzEmp")){
			hql = hql + " and fzEmp = :fzEmp";
		}
		//维护人员

		if(params.containsKey("whEmp")){
			hql = hql + " and whEmp = :whEmp";
		}
		//借款类型
		if(params.containsKey("loanType")){
			hql = hql + " and loanType = :loanType";
		}
		//借款用途
		if(params.containsKey("loanUse")){
			hql = hql + " and loanUse = :loanUse";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
