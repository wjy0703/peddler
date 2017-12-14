package cn.com.cucsi.app.dao.xhcf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMonthlyDw;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMonthlyDwDao extends HibernateDao<XhMonthlyDw, Long> {

	public Page<XhMonthlyDw> queryXhMonthlyDw(Page<XhMonthlyDw> page,
			Map<String, Object> params) {
		String hql = "from XhMonthlyDw xhMonthlyDw where 1=1";
		// 帐外
		if (params.containsKey("additional")) {
			hql = hql + " and additional = :additional";
		}
		// 银行名称
		if (params.containsKey("bankName")) {
			hql = hql + " and bankName = :bankName";
		}
		// 银行账号
		if (params.containsKey("bankNumber")) {
			hql = hql + " and bankNumber = :bankNumber";
		}
		// 银行开户行
		if (params.containsKey("bankOpen")) {
			hql = hql + " and bankOpen = :bankOpen";
		}
		// 利息
		if (params.containsKey("interest")) {
			hql = hql + " and interest = :interest";
		}
		// 出借人ID
		if (params.containsKey("lenderId")) {
			hql = hql + " and lenderId = :lenderId";
		}
		// 出借人身份证号
		if (params.containsKey("lenderIdCard")) {
			hql = hql + " and lenderIdCard = :lenderIdCard";
		}
		// 出借人名称
		if (params.containsKey("lenderName")) {
			hql = hql + " and lenderName = :lenderName";
		}
		// 出借编号
		if (params.containsKey("lenderNumber")) {
			hql = hql + " and lenderNumber = :lenderNumber";
		}
		// 出借状态
		if (params.containsKey("lenderState")) {
			hql = hql + " and lenderState = :lenderState";
		}
		// 金额
		if (params.containsKey("money")) {
			hql = hql + " and money = :money";
		}
		// 付款日期
		if (params.containsKey("payDate")) {
			hql = hql + " and payDate = :payDate";
		}
		// 付款类型
		if (params.containsKey("payType")) {
			hql = hql + " and payType = :payType";
		}
		// 备用字段1
		if (params.containsKey("spareField01")) {
			hql = hql + " and spareField01 = :spareField01";
		}
		// 备用字段2
		if (params.containsKey("spareField02")) {
			hql = hql + " and spareField02 = :spareField02";
		}
		// 备用字段3
		if (params.containsKey("spareField03")) {
			hql = hql + " and spareField03 = :spareField03";
		}
		// 债权推荐ID
		if (params.containsKey("zqtjId")) {
			hql = hql + " and zqtjId = :zqtjId";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

	public List<Date> getAllPayDate() {
		String hql = "select distinct payDate from XhMonthlyDw xhMonthlyDw order by payDate ";
		return this.find(hql);
	}

	/**
	 * @param cardID
	 * @param payDate
	 * @return
	 */
	public Long[] getXhMonthlyDws(String lenderNumber, Date payDate) {
		Map param = new HashMap();
		param.put("lenderNumber", lenderNumber);
		param.put("payDate", payDate);
		String hql = "select id from XhMonthlyDw xhMonthlyDw where 1=1 and xhMonthlyDw.lenderNumber=:lenderNumber and xhMonthlyDw.payDate=:payDate";
		List<Long> list = this.find(hql, param);
		Long[] ids=new Long[list.size()]; 
		for(int i=0;i<list.size();i++){
			ids[i]=list.get(i);
		}
		 return ids;

	}
}
