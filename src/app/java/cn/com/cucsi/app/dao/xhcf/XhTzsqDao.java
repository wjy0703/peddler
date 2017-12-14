package cn.com.cucsi.app.dao.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhTzsqDao extends HibernateDao<XhTzsq, Long>{

	public Page<XhTzsq> queryXhTzsq(Page<XhTzsq> page, Map<String, Object> params){
		String hql = "from XhTzsq xhTzsq where 1=1";
		//协议编号ORGANI_ID
		if(params.containsKey("tzsqbh")){
			hql = hql + " and tzsqbh = :tzsqbh";
		}
		//计划投资日期
		if(params.containsKey("jhtzrq")){
			hql = hql + " and jhtzrq = :jhtzrq";
		}
		//计划投资金额
		if(params.containsKey("jhtzje")){
			hql = hql + " and jhtzje = :jhtzje";
		}
		//投资方式
		if(params.containsKey("tzfs")){
			hql = hql + " and tzfs = :tzfs";
		}
		//回收方式
		if(params.containsKey("hsfs")){
			hql = hql + " and hsfs = :hsfs";
		}
		//付款方式
		if(params.containsKey("fkfs")){
			hql = hql + " and fkfs = :fkfs";
		}
		//是否风险金补偿
		if(params.containsKey("sffxjbc")){
			hql = hql + " and sffxjbc = :sffxjbc";
		}
		//计划划扣日期
		if(params.containsKey("jhhkrq")){
			hql = hql + " and jhhkrq = :jhhkrq";
		}
		//申请日期
		if(params.containsKey("sqrq")){
			hql = hql + " and sqrq = :sqrq";
		}
		//部门主管
		if(params.containsKey("bmzg")){
			hql = hql + " and bmzg = :bmzg";
		}
		//协议版本
		if(params.containsKey("xybb")){
			hql = hql + " and xybb = :xybb";
		}
		//销售折扣率
		if(params.containsKey("xszkly")){
			hql = hql + " and xszkly = :xszkly";
		}
		//销售折扣率有效期限
		if(params.containsKey("xszklyxqx")){
			hql = hql + " and xszklyxqx = :xszklyxqx";
		}
		//备注
		if(params.containsKey("remark")){
			hql = hql + " and remark = :remark";
		}
		//申请单原件
		if(params.containsKey("sqdyj")){
			hql = hql + " and sqdyj = :sqdyj";
		}
		//投资付款开户行
		if(params.containsKey("tzfkkhh")){
			hql = hql + " and tzfkkhh = :tzfkkhh";
		}
		//投资付款卡或折
		if(params.containsKey("tzfkkhz")){
			hql = hql + " and tzfkkhz = :tzfkkhz";
		}
		//投资付款银行名称
		if(params.containsKey("tzfkyhmc")){
			hql = hql + " and tzfkyhmc = :tzfkyhmc";
		}
		//投资付款开户名称
		if(params.containsKey("tzfkkhmc")){
			hql = hql + " and tzfkkhmc = :tzfkkhmc";
		}
		//投资付款银行帐号
		if(params.containsKey("tzfkyhzh")){
			hql = hql + " and tzfkyhzh = :tzfkyhzh";
		}
		//回收资金开户行
		if(params.containsKey("hszjkhh")){
			hql = hql + " and hszjkhh = :hszjkhh";
		}
		//回收资金卡或折
		if(params.containsKey("hszjkhz")){
			hql = hql + " and hszjkhz = :hszjkhz";
		}
		//回收资金银行名称
		if(params.containsKey("hszjyhmc")){
			hql = hql + " and hszjyhmc = :hszjyhmc";
		}
		//回收资金开户名称
		if(params.containsKey("hszjkhmc")){
			hql = hql + " and hszjkhmc = :hszjkhmc";
		}
		//回收资金银行帐号
		if(params.containsKey("hszjyhzh")){
			hql = hql + " and hszjyhzh = :hszjyhzh";
		}
		//申请状态
		if(params.containsKey("sqzt")){
			hql = hql + " and sqzt = :sqzt";
		}
		//出借周期
		if(params.containsKey("cjzq")){
			hql = hql + " and cjzq = :cjzq";
		}
		//交割日
		if(params.containsKey("tzjgr")){
			hql = hql + " and tzjgr = :tzjgr";
		}
		//首个还款日期
		if(params.containsKey("sghkrq")){
			hql = hql + " and sghkrq = :sghkrq";
		}
		//账单日
		if(params.containsKey("zdr")){
			hql = hql + " and zdr = :zdr";
		}
		//投资资金状态
		if(params.containsKey("tzzjzt")){
			hql = hql + " and tzzjzt = :tzzjzt";
		}
		//使用期数
		if(params.containsKey("syqs")){
			hql = hql + " and syqs = :syqs";
		}
		//匹配状态
		if(params.containsKey("ppzt")){
			hql = hql + " and ppzt = :ppzt";
		}
		//剩余出借周期
		if(params.containsKey("lastCjzq")){
			hql = hql + " and lastCjzq = :lastCjzq";
		}
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除， 
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//审核人
		if(params.containsKey("auditPerson")){
			hql = hql + " and auditPerson = :auditPerson";
		}
		//审核意见
		if(params.containsKey("auditIdea")){
			hql = hql + " and auditIdea = :auditIdea";
		}
		//审核时间
		if(params.containsKey("auditTime")){
			hql = hql + " and auditTime = :auditTime";
		}
		//变更状态-1，无修改，0暂存, 1待审批，3审批不通过
		if(params.containsKey("upstate")){
			hql = hql + " and upstate = :upstate";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	/**
	 * 通过调节查询记录
	 * @param tzsqbh
	 * @return
	 */
	public List<XhTzsq> queryByTzsqbh(Map<String, Object> params){
		String hql = "from XhTzsq xhTzsq where 1=1";
		//协议编号ORGANI_ID
		if(params.containsKey("tzsqbh")){
					hql = hql + " and tzsqbh = :tzsqbh";
		}
		if(params.containsKey("cjrxxId")){
			hql = hql + " and cjrxx.id = :cjrxxId";
		}	
		return this.find(hql, params);
	}
}
