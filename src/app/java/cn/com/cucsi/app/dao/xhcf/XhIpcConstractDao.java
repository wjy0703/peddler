package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhIpcConstractDao extends HibernateDao<XhIpcConstract, Long>{

	public Page<XhIpcConstract> queryXhIpcConstract(Page<XhIpcConstract> page, Map<String, Object> params){
		String hql = "from XhIpcConstract xhIpcConstract where 1=1";
		//审核时间
		if(params.containsKey("auditDate")){
			hql = hql + " and auditDate = :auditDate";
		}
		//审核意见
		if(params.containsKey("auditIdea")){
			hql = hql + " and auditIdea = :auditIdea";
		}
		//审核人
		if(params.containsKey("auditPerson")){
			hql = hql + " and auditPerson = :auditPerson";
		}
		//放款金额
		if(params.containsKey("fkje")){
			hql = hql + " and fkje = :fkje";
		}
		//服务费
		if(params.containsKey("fwf")){
			hql = hql + " and fwf = :fwf";
		}
		//合同金额
		if(params.containsKey("htje")){
			hql = hql + " and htje = :htje";
		}
		//借款合同编号
		if(params.containsKey("jkhtbm")){
			hql = hql + " and jkhtbm = :jkhtbm";
		}
		//合同签订日期
		if(params.containsKey("qdrq")){
			hql = hql + " and qdrq = :qdrq";
		}
		//起始还款日期
		if(params.containsKey("qshkrq")){
			hql = hql + " and qshkrq = :qshkrq";
		}
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//信用审核费
		if(params.containsKey("xyshf")){
			hql = hql + " and xyshf = :xyshf";
		}
		//月本金金额
		if(params.containsKey("ybjje")){
			hql = hql + " and ybjje = :ybjje";
		}
		//月还款金额
		if(params.containsKey("yhkje")){
			hql = hql + " and yhkje = :yhkje";
		}
		//月利息金额
		if(params.containsKey("ylxje")){
			hql = hql + " and ylxje = :ylxje";
		}
		//账户管理费
		if(params.containsKey("zhglf")){
			hql = hql + " and zhglf = :zhglf";
		}
		//咨询费
		if(params.containsKey("zxf")){
			hql = hql + " and zxf = :zxf";
		}
		//综合费率
		if(params.containsKey("yzhfl")){
			hql = hql + " and yzhfl = :yzhfl";
		}
		//还款期数
		if(params.containsKey("hkqs")){
			hql = hql + " and hkqs = :hkqs";
		}
		//信访费
		if(params.containsKey("xff")){
			hql = hql + " and xff = :xff";
		}
		//签约确认审核时间
		if(params.containsKey("auditQzqrDate")){
			hql = hql + " and auditQzqrDate = :auditQzqrDate";
		}
		//签约确认审核意见
		if(params.containsKey("auditQzqrIdea")){
			hql = hql + " and auditQzqrIdea = :auditQzqrIdea";
		}
		//签约确认审核人
		if(params.containsKey("auditQzqrPerson")){
			hql = hql + " and auditQzqrPerson = :auditQzqrPerson";
		}
		//贷款利率
		if(params.containsKey("dkll")){
			hql = hql + " and dkll = :dkll";
		}
		//批贷金额
		if(params.containsKey("pdje")){
			hql = hql + " and pdje = :pdje";
		}
		//剩余金额
		if(params.containsKey("syje")){
			hql = hql + " and syje = :syje";
		}
		//剩余利率
		if(params.containsKey("syll")){
			hql = hql + " and syll = :syll";
		}
		//居间人ID
		if(params.containsKey("middlemanId")){
			hql = hql + " and middlemanId = :middlemanId";
		}
		//还款日
		if(params.containsKey("hkr")){
			hql = hql + " and hkr = :hkr";
		}
		//临时姓名
		if(params.containsKey("customerName")){
			hql = hql + " and xhIpcConstract.ipcApply.customerName = :customerName";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	public XhIpcConstract findXhIpcConstractByApplyID(Long id) {
		
		String hql = "from XhIpcConstract xhIpcConstract  where  ipcApply.id=" + id;
		return this.findUnique(hql);
		
		
	}
}
