package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.QdHouseInfo;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class QdHouseInfoDao extends HibernateDao<QdHouseInfo, Long>{

	public Page<QdHouseInfo> queryQdHouseInfo(Page<QdHouseInfo> page, Map<String, Object> params){
		String hql = "from QdHouseInfo qdHouseInfo where 1=1";
		//借款编号
		if(params.containsKey("loanCode")){
			hql = hql + " and loanCode = :loanCode";
		}
		//借款人状态
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//借款人姓名
		if(params.containsKey("jkrxm")){
			hql = hql + " and jkrxm = :jkrxm";
		}
		//借款人借款用途
		if(params.containsKey("jkUse")){
			hql = hql + " and jkUse = :jkUse";
		}
		//城市
		if(params.containsKey("city")){
			hql = hql + " and city = :city";
		}
		//借款方式
		if(params.containsKey("jkType")){
			hql = hql + " and jkType = :jkType";
		}
		//债权人
		if(params.containsKey("zqr")){
			hql = hql + " and zqr = :zqr";
		}
		//初始借款金额
		if(params.containsKey("htje")){
			hql = hql + " and htje = :htje";
		}
		//月本
		if(params.containsKey("ybjje")){
			hql = hql + " and ybjje = :ybjje";
		}
		//月息
		if(params.containsKey("ylxje")){
			hql = hql + " and ylxje = :ylxje";
		}
		//月帐号管理费
		if(params.containsKey("zhglf")){
			hql = hql + " and zhglf = :zhglf";
		}
		//月还款金额
		if(params.containsKey("yhkje")){
			hql = hql + " and yhkje = :yhkje";
		}
		//初始借款时间
		if(params.containsKey("qdrq")){
			hql = hql + " and qdrq = :qdrq";
		}
		//还款期限
		if(params.containsKey("hkqs")){
			hql = hql + " and hkqs = :hkqs";
		}
		//起始还款日期
		if(params.containsKey("qshkrq")){
			hql = hql + " and qshkrq = :qshkrq";
		}
		//贷款利息
		if(params.containsKey("dkll")){
			hql = hql + " and dkll = :dkll";
		}
		//no还款截止日期
		if(params.containsKey("returnrq")){
			hql = hql + " and returnrq = :returnrq";
		}
		//no借款人职业情况
		if(params.containsKey("personWorkCondition")){
			hql = hql + " and personWorkCondition = :personWorkCondition";
		}
		//证件号码
		if(params.containsKey("zjhm")){
			hql = hql + " and zjhm = :zjhm";
		}
		//借款申请ID
		if(params.containsKey("jqsqid")){
			hql = hql + " and jqsqid = :jqsqid";
		}
		//借款合同ID
		if(params.containsKey("jqhtid")){
			hql = hql + " and jqhtid = :jqhtid";
		}
		//可用债权ID
		if(params.containsKey("kyzqid")){
			hql = hql + " and kyzqid = :kyzqid";
		}
		//备用一个
		if(params.containsKey("meta1")){
			hql = hql + " and meta1 = :meta1";
		}
		//省份
		if(params.containsKey("province")){
			hql = hql + " and province = :province";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
