package cn.com.cucsi.app.dao.xhcf;

import java.util.Calendar;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhLoanReturnRecords;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhLoanReturnRecordsDao extends HibernateDao<XhLoanReturnRecords, Long>{

	public Page<XhLoanReturnRecords> queryXhLoanReturnRecords(Page<XhLoanReturnRecords> page, Map<String, Object> params){
		
		String hql = "from XhLoanReturnRecords xhLoanReturnRecords where 1=1";
		//交易金额
		if(params.containsKey("deailingMoney")){
			hql = hql + " and deailingMoney = :deailingMoney";
		}
		//交易时间
		if(params.containsKey("dealingTime")){
			hql = hql + " and dealingTime = :dealingTime";
		}
		//交易类型
		if(params.containsKey("dealingType")){
			hql = hql + " and dealingType = :dealingType";
		}
		//主账户ID
		if(params.containsKey("loanerMainAccountId")){
			hql = hql + " and loanerMainAccountId = :loanerMainAccountId";
		}
		if(params.containsKey("createBy")){
			hql = hql + " and createBy = :createBy";
		}
		if(params.containsKey("createTime")){
			java.sql.Date startDate = java.sql.Date.valueOf(params.get("createTime").toString());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);//取当前日期的后一天
            
			params.put("startDate",startDate);
			java.util.Date endDate = cal.getTime();
			params.put("endDate", endDate);
			
			hql = hql + " and createTime < :endDate and createTime>=:startDate";
		}
		//合同编码
		if(params.containsKey("htbm")){
			hql = hql + " and htbm = :htbm";
		}
		//错误原因
		if(params.containsKey("errorState")){
			hql = hql + " and errorState = :errorState";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
