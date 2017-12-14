package cn.com.cucsi.app.dao.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJkhtDao extends HibernateDao<XhJkht, Long> {

	public Page<XhJkht> queryXhJkht(Page<XhJkht> page,
			Map<String, Object> params) {
		String hql = "from XhJkht xhJkht where 1=1";
		// 还款期数
		if (params.containsKey("hkqs")) {
			hql = hql + " and hkqs = :hkqs";
		}
		// 月综合费率
		if (params.containsKey("yzhfl")) {
			hql = hql + " and yzhfl = :yzhfl";
		}
		// 咨询费
		if (params.containsKey("zxf")) {
			hql = hql + " and zxf = :zxf";
		}
		// 账户管理费
		if (params.containsKey("zhglf")) {
			hql = hql + " and zhglf = :zhglf";
		}
		// 月利息金额
		if (params.containsKey("ylxje")) {
			hql = hql + " and ylxje = :ylxje";
		}
		// 月利率
		if (params.containsKey("yll")) {
			hql = hql + " and yll = :yll";
		}
		// 月还款金额
		if (params.containsKey("yhkje")) {
			hql = hql + " and yhkje = :yhkje";
		}
		// 月本金金额
		if (params.containsKey("ybjje")) {
			hql = hql + " and ybjje = :ybjje";
		}
		// 信用审核费
		if (params.containsKey("xyshf")) {
			hql = hql + " and xyshf = :xyshf";
		}
		// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除
		if (params.containsKey("state")) {
			hql = hql + " and state = :state";
		}
		// 起始还款日期
		if (params.containsKey("qshkrq")) {
			hql = hql + " and qshkrq = :qshkrq";
		}
		// 合同签订日期
		if (params.containsKey("qdrq")) {
			hql = hql + " and qdrq = :qdrq";
		}
		// 借款合同编号
		if (params.containsKey("jkhtbm")) {
			hql = hql + " and jkhtbm = :jkhtbm";
		}
		// 合同金额
		if (params.containsKey("htje")) {
			hql = hql + " and htje = :htje";
		}
		// 服务费
		if (params.containsKey("fwf")) {
			hql = hql + " and fwf = :fwf";
		}
		// 放款金额
		if (params.containsKey("fkje")) {
			hql = hql + " and fkje = :fkje";
		}
		// 审核人
		if (params.containsKey("auditPerson")) {
			hql = hql + " and auditPerson = :auditPerson";
		}
		// 审核意见
		if (params.containsKey("auditIdea")) {
			hql = hql + " and auditIdea = :auditIdea";
		}
		// 审核时间
		if (params.containsKey("auditDate")) {
			hql = hql + " and auditDate = :auditDate";
		}
		// 信访费
		if (params.containsKey("xff")) {
			hql = hql + " and xff = :xff";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

	/**
	 * @param id
	 * @return
	 */
	public XhJkht findLoanContarctByApplyID(Long id) {
		
		String hql = "from XhJkht xhJkht  where  xhJkht.xhJksq.id=" + id;
		return this.findUnique(hql);		
		
	}
	
	/**
	 * 
	 * 根据借款人，编号，放款金额查询借款申请
	 * 
	 * @param name 借款人姓名
	 * @param jkbh 借款编号
	 * @param fkje 放款金额
	 * @return
	 * @author xjs
	 * @date 2013-8-8 下午1:12:29
	 */
	
	public List<XhJkht> findByNameBHandAmout(String name,String jkbh,long fkje){	
	    String hql="from XhJkht xhJkht  where  xhJkht.xhJksq.jkrxm = :name and xhJkht.jkhtbm = :jkbh  and xhJkht.fkje = :fkje";
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("name", name);
	    map.put("jkbh", jkbh);
	    map.put("fkje", fkje);
	    return this.find(hql,map);
	}
}
