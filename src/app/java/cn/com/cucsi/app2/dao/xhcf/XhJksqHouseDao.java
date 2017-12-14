package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhJksqHouse;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqHouseDao extends HibernateDao<XhJksqHouse, Long>{

	public Page<XhJksqHouse> queryXhJksqHouse(Page<XhJksqHouse> page, Map<String, Object> params){
		String hql = "from XhJksqHouse xhJksqHouse where 1=1";
		//住宅地址
		if(params.containsKey("address")){
			hql = hql + " and address = :address";
		}
		//住宅类型( 全款，按揭 -- 下拉或单选)
		if(params.containsKey("typeh")){
			hql = hql + " and typeh = :typeh";
		}
		//建筑年份
		if(params.containsKey("buildYear")){
			hql = hql + " and buildYear = :buildYear";
		}
		//销售面积
		if(params.containsKey("area")){
			hql = hql + " and area = :area";
		}
		//按揭银行
		if(params.containsKey("bank")){
			hql = hql + " and bank = :bank";
		}
		//借款总额
		if(params.containsKey("loanMoney")){
			hql = hql + " and loanMoney = :loanMoney";
		}
		//贷款年限
		if(params.containsKey("loanMonth")){
			hql = hql + " and loanMonth = :loanMonth";
		}
		//够买价格
		if(params.containsKey("buyMoney")){
			hql = hql + " and buyMoney = :buyMoney";
		}
		//借款余额
		if(params.containsKey("remainmoney")){
			hql = hql + " and remainmoney = :remainmoney";
		}
		//月还款
		if(params.containsKey("monthReturn")){
			hql = hql + " and monthReturn = :monthReturn";
		}
		//产权归属
		if(params.containsKey("chouseOwner")){
			hql = hql + " and chouseOwner = :chouseOwner";
		}
		//有无抵押 0:无 1:有
		if(params.containsKey("chouseEndorse")){
			hql = hql + " and chouseEndorse = :chouseEndorse";
		}
		//估值
		if(params.containsKey("chouseValue")){
			hql = hql + " and chouseValue = :chouseValue";
		}
		//市场报价(一般是写信息例如 100米 8000元一米)
		if(params.containsKey("chouseMarchetValue")){
			hql = hql + " and chouseMarchetValue = :chouseMarchetValue";
		}
		//估值/确认途径
		if(params.containsKey("chouseValueWay")){
			hql = hql + " and chouseValueWay = :chouseValueWay";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
