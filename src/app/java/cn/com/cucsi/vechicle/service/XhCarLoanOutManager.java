package cn.com.cucsi.vechicle.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.xhcf.XhMadewordDao;
import cn.com.cucsi.app.dao.xhcf.XhMakeLoansDao;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.vechicle.dao.XhCarAuditDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanContractDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanUserDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanContract;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarLoanOutManager {
	//private XhCarloan
	@Autowired
	private XhCarLoanContractDao xhCarLoanContractDao;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	@Autowired
	private XhCarLoanApplyDao xhCarLoanApplyDao;
	@Autowired
	private XhCarAuditDao xhCarAuditDao;
	@Autowired
	private XhMadewordDao xhMadewordDao;
	
	@Autowired
	private XhJkhtManager xhJkhtManager;
	
	@Autowired 
	private XhJksqDao xhJksqDao;
	
	
	@Autowired
    private XhCarLoanUserDao xhCarLoanUserDao; 
	
	@Autowired
	private XhMakeLoansDao xhMakeLoansDao;
	
	
	@Autowired
	private XhCarLoanApplyManager xhCarLoanApplyManager;
	
	
	@Transactional(readOnly = true)
	public Page<XhCarLoanContract> searchXhCarLoanContract(final Page<XhCarLoanContract> page, final Map<String,Object> filters) {
		return xhCarLoanContractDao.queryXhCarLoanContract(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCarLoanContract getXhCarLoanContract(Long id) {
		return xhCarLoanContractDao.get(id);
	}
	@Transactional(readOnly = true)
	public List<XhCarLoanContract> queryXhCarLoanContractList(Map<String, Object> params){
		return xhCarLoanContractDao.queryXhCarLoanContractList(params);
	}
	
	//保存合同
	public void  saveXhCarLoanContract(XhCarLoanContract xhCarLoanContract) {
		//存借款申请表
		XhCarLoanContract xhCarLoanContractOracle = xhCarLoanContractDao.get(xhCarLoanContract.getId());
		XhCarLoanApply apply = xhCarLoanContract.getXhCarLoanApply();
		XhCarLoanApply xhApplyOracle = xhCarLoanApplyDao.get(apply.getId());
		XhJksq xhJksq = getXhjksqFromCarApply(xhApplyOracle);
		XhCarLoanUser xhCarLoanUserOracle =xhCarLoanUserDao.get(apply.getXhCarLoanUser().getId());
		xhJksq.setJkrxm(xhCarLoanUserOracle.getUserName());//借款人姓名
		xhJksq.setZjhm(xhCarLoanUserOracle.getCardNumber());//证件号码
		xhJksq.setYwzn(xhCarLoanUserOracle.getHasChildren());//有无子女
		try {
			new HibernateAwareBeanUtilsBean().copyProperties(xhJksq, xhCarLoanUserOracle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		xhJksq.setJkLoanQuota(xhApplyOracle.getJkLoanQuota().toString());//申请额度
		xhJksq.setJkCycle(xhApplyOracle.getJkCycle().toString());//借款周期
		xhJksq.setJkType(xhApplyOracle.getJkType());//借款类型
		xhJksq.setJkLoanDate(Date.valueOf(xhApplyOracle.getJkLoanDate()));//借款日期
		xhJksq.setBackup01("CAR");
		xhJksq.setState(CarState.DAI_FANG_KUAN.getText());
		xhJksq.setLoanCode(xhApplyOracle.getLoanCode());//借款编号
		xhJksq.setBankOpen(xhApplyOracle.getBankOpen());//开户行
		xhJksq.setBankNum(xhApplyOracle.getBankNum());//开户账号
		xhJksq.setBankUsername(xhApplyOracle.getBankUsername());//开户人姓名
		xhJkhtManager.saveXhJksq(xhJksq);
		//存借款合同表
		
		XhJkht xhJkht = getXhjkhtFromCarContract(xhCarLoanContractOracle);
		xhJkht.setHkr(xhCarLoanContractOracle.getHkr());//还款日
		xhJkht.setXyshf(xhCarLoanContractOracle.getAuditFee());//审核费
		xhJkht.setMiddleMan(xhCarLoanContractOracle.getMiddleMan());//中间人
		xhJkht.setQdrq(xhCarLoanContractOracle.getQdrq());//签订日期
		xhJkht.setJkhtbm(xhCarLoanContractOracle.getContractNum());//借款合同编号
		xhJkht.setHtje(xhCarLoanContractOracle.getContractMoney());//借款金额
		xhJkht.setZxf(xhCarLoanContractOracle.getAdviceFee());//咨询费
		xhJkht.setDkll(xhCarLoanContractOracle.getDkll());//贷款利率
		xhJkht.setYzhfl(xhCarLoanContractOracle.getDkllComlpex());//月综合费率
		xhJkht.setHkqs(Integer.parseInt(xhApplyOracle.getJkCycle().toString())/30);//还款期数
		xhJkht.setFkje(xhCarLoanContractOracle.getContractMoney()-xhCarLoanContractOracle.getComlpexMoney());//放款金额
		xhJkht.setQshkrq(CreditHarmonyComputeUtilties.getFirstDateOfBackMoney(xhApplyOracle.getJkLoanDate()));//起始还款日期
		xhJkht.setXhJksq(xhJksq);
		xhJkhtManager.saveXhJkht(xhJkht);
		/*//存制作合同表
		XhMakeLoans xhMakeLoans = new XhMakeLoans();
		xhMakeLoans.setLoanContract(xhJkht);
		xhMakeLoans.setMiddleMan(xhJkht.getMiddleMan());
		xhMakeLoans.setMakeLoanDate(Date.valueOf(xhApplyOracle.getJkLoanDate()));
		xhMakeLoansDao.save(xhMakeLoans);*/
		//在apply中加入JKSQID
		xhApplyOracle.setXhJksqId(xhJksq.getId());
		String oldState = xhApplyOracle.getState();
		xhApplyOracle.setState(CarState.DAI_FANG_KUAN.getText());
		xhCarLoanApplyDao.save(xhApplyOracle);
		//在contract表中加入JKHTID
		xhCarLoanContractOracle.setXhJkhtId(xhJkht.getId());
		xhCarLoanContractDao.save(xhCarLoanContractOracle);
		xhCarLoanApplyManager.saveHistory(oldState, xhApplyOracle, CarOperation.DAI_FANG_KUAN, CarOperation.DAI_FANG_KUAN.getMessage());
	}
	
	/**
	 * 通过车贷数据库得到借款合同
	 * @param xhCarLoanContractOracle
	 * @return
	 */
    private XhJkht getXhjkhtFromCarContract(XhCarLoanContract xhCarLoanContractOracle) {
    	if(xhCarLoanContractOracle.getXhJkhtId() == null){
			return new XhJkht();
		}else{
           XhJkht xhjkht = xhJkhtManager.getXhJkht(xhCarLoanContractOracle.getId());
           return xhjkht;		
		}
	}
    
	/**
     * 通过车贷数据库得到借款申请
     * @param xhApplyOracle
     * @return
     */
	private XhJksq getXhjksqFromCarApply(XhCarLoanApply xhApplyOracle) {
		
		if(xhApplyOracle.getXhJksqId() == null){
			return new XhJksq();
		}else{
           XhJksq xhjksq = xhJksqDao.get(xhApplyOracle.getXhJksqId());
           return xhjksq;		
		}
	}
	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCarLoanContract(Long id) {
		xhCarLoanContractDao.delete(id);
	}
	
	public boolean batchDelXhCarLoanContract(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCarLoanContract(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCarLoanContract(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//合同编号
		if(filter.containsKey("contractNum")){
			value = String.valueOf(filter.get("contractNum"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTRACT_NUM = '" +  value + "'";
			}
		}
		//合同金额
		if(filter.containsKey("contractMoney")){
			value = String.valueOf(filter.get("contractMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTRACT_MONEY = '" +  value + "'";
			}
		}
		//合同签订日期
		if(filter.containsKey("qdrq")){
			value = String.valueOf(filter.get("qdrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QDRQ = '" +  value + "'";
			}
		}
		//合同日期
		if(filter.containsKey("contractDate")){
			value = String.valueOf(filter.get("contractDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTRACT_DATE = '" +  value + "'";
			}
		}
		//0：待签约 1：已签约上传   -1：取消
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" +  value + "'";
			}
		}
		//中间人ID
		if(filter.containsKey("middleManId")){
			value = String.valueOf(filter.get("middleManId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MIDDLE_MAN_ID = '" +  value + "'";
			}
		}
		//利息率（移交：0.5%；GPS：0.475%）
		if(filter.containsKey("dkll")){
			value = String.valueOf(filter.get("dkll"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DKLL = '" +  value + "'";
			}
		}
		//总费率（移交：3.5%；GPS：5%  ，客户经理可录入）
		if(filter.containsKey("dkllComlpex")){
			value = String.valueOf(filter.get("dkllComlpex"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DKLL_COMLPEX = '" +  value + "'";
			}
		}
		//利息（借款总额*利息率）
		if(filter.containsKey("interest")){
			value = String.valueOf(filter.get("interest"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INTEREST = '" +  value + "'";
			}
		}
		//总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)）
		if(filter.containsKey("serveComlpexMoney")){
			value = String.valueOf(filter.get("serveComlpexMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SERVE_COMLPEX_MONEY = '" +  value + "'";
			}
		}
		//咨询费（综合息费*59%）
		if(filter.containsKey("adviceFee")){
			value = String.valueOf(filter.get("adviceFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADVICE_FEE = '" +  value + "'";
			}
		}
		//审核费（综合息费*5%）
		if(filter.containsKey("auditFee")){
			value = String.valueOf(filter.get("auditFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_FEE = '" +  value + "'";
			}
		}
		//服务费（综合息费-咨询费-审核费）
		if(filter.containsKey("serviceFee")){
			value = String.valueOf(filter.get("serviceFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SERVICE_FEE = '" +  value + "'";
			}
		}
		//借款合同ID
		if(filter.containsKey("xhJkhtId")){
			value = String.valueOf(filter.get("xhJkhtId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XH_JKHT_ID = '" +  value + "'";
			}
		}
		//还款日
		if(filter.containsKey("hkr")){
			value = String.valueOf(filter.get("hkr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKR = '" +  value + "'";
			}
		}
		//展期期数
		if(filter.containsKey("noExtension")){
			value = String.valueOf(filter.get("noExtension"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.NO_EXTENSION = '" +  value + "'";
			}
		}
		//是否展期
		if(filter.containsKey("isExtension")){
			value = String.valueOf(filter.get("isExtension"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.IS_EXTENSION = '" +  value + "'";
			}
		}
		//备注
		if(filter.containsKey("remark")){
			value = String.valueOf(filter.get("remark"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMARK = '" +  value + "'";
			}
		}
		
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	/**
	 * 获取信审结果，综合费率和信审金额
	 */
	public XhCarAudit getXhCarAudit(Map<String,Object> filter){
		XhCarAudit xhCarAudit = new XhCarAudit();
		//查询信审结束，且通过的信审记录
		String hql = "from XhCarAudit xhCarAudit where 1=1 and creditResult=1 and creditState=1 ";
		if(filter.containsKey("car_apply_id")){
			hql = hql + " and CAR_APPLY_ID = :car_apply_id";
		}
		List<XhCarAudit> list= xhCarAuditDao.find(hql, filter);
		if(null != list && list.size()>0){
			xhCarAudit = list.get(0);
		}
		return xhCarAudit;
	}
}
