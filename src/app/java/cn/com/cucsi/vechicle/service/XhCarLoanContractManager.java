package cn.com.cucsi.vechicle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMadewordDao;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.vechicle.dao.XhCarAuditDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanContractDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanContract;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarLoanContractManager {

	private XhCarLoanContractDao xhCarLoanContractDao;
	@Autowired
	public void setXhCarLoanContractDao(XhCarLoanContractDao xhCarLoanContractDao) {
		this.xhCarLoanContractDao = xhCarLoanContractDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Autowired
	private XhCarLoanApplyDao xhCarLoanApplyDao;
	@Autowired
	private XhCarAuditDao xhCarAuditDao;
	@Autowired
	private XhMadewordDao xhMadewordDao;
	@Autowired
	private BaseInfoManager baseInfoManager;
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
	@Transactional(readOnly = true)
	public XhCarLoanApply getXhCarLoanApplyById(Long id) {
		return xhCarLoanApplyDao.get(id);
	}
	
	public XhCarLoanApply getXhCarLoanApply(Long id) {
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyDao.get(id);
		String jkbh="";
		//0451 01 0001-001
		if(null == xhCarLoanApply.getLoanCode() || "".equals(xhCarLoanApply.getLoanCode())){
			//获取城市编码
			String ciryName=baseInfoManager.getAreaCode(xhCarLoanApply.getXhCarLoanUser().getCrmcity());
			//城市名字+当前月份
			//jkbh=ciryName+""+DateUtils.format(new Date(), "MM");
			//城市名字+当前月份 + 3位序号
			//jkbh += baseInfoManager.getCarXh(jkbh);
			jkbh = ciryName;
			Organi org = baseInfoManager.gerOrgani(xhCarLoanApply.getXhCarLoanUser().getEmployeeCrm().getOrgani().getParentId());
			String orgcode = org.getOrganiCode();
			jkbh += orgcode;
			jkbh = baseInfoManager.getCarXh(jkbh,xhCarLoanApply.getIsExtension(),xhCarLoanApply.getPartenCarApplyId());
			System.out.println("jkbh===>"+jkbh);
			xhCarLoanApply.setLoanCode(jkbh);
			xhCarLoanApplyDao.save(xhCarLoanApply);
		}
		return xhCarLoanApply;
	}
	//保存合同
	public void saveXhCarLoanContract(XhCarLoanContract entity) {
		if(entity.getState()!=0){
			entity.setState(Long.parseLong("1"));
			//保存到制作表
//			entity.getXhCarLoanApply().setState(CarState.DAI_QUE_DING_QIAN_SHU.getText());
//			xhCarLoanApplyDao.save(entity.getXhCarLoanApply());
			CarOperation  option = CarOperation.DAI_QUE_DING_QIAN_SHU;//0应该改成optionStr
			xhCarLoanApplyManager.changeStateAndWriteHistory(entity.getXhCarLoanApply(), option,option.getMessage());
		}
		xhCarLoanContractDao.save(entity);
		
		//如果是提交
		/*
		if(entity.getState()!=0){
			//保存到制作表
			XhMadeword xhMadeword = new XhMadeword();
			xhMadeword.setState("0");
			xhMadeword.setStype("0");
			xhMadeword.setTableId(entity.getId());
			xhMadeword.setTableName("xh_car_loan_contract");
			xhMadewordDao.save(xhMadeword);
		}
		*/
	}
	
	//保存合同
	public void saveXhCarLoanContractQs(XhCarLoanContract entity) {
		xhCarLoanContractDao.save(entity);
		//设置申请状态为待上传合同
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyDao.get(entity.getXhCarLoanApply().getId());
		CarOperation  option = CarOperation.DAI_ZHI_ZUO_HE_TONG;
		xhCarLoanApplyManager.changeStateAndWriteHistory(xhCarLoanApply, option,option.getMessage());
//		xhCarLoanApply.setState(CarState.DAI_SHANG_CHUAN_HETONG.getText());
//		xhCarLoanApplyDao.save(xhCarLoanApply);
	}
	
	//制作合同
	public void saveXhCarLoanContractMade(XhCarLoanContract entity) {
		XhCarLoanContract xhCarLoanContract = this.getXhCarLoanContract(entity.getId());
		xhCarLoanContract.setState(Long.parseLong(CarState.DAI_HE_TONG_ZHI_ZUO_SHEN_HE.getText()));
		xhCarLoanContractDao.save(xhCarLoanContract);
		//设置申请状态为待上传合同
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyDao.get(xhCarLoanContract.getXhCarLoanApply().getId());
		CarOperation  option = CarOperation.DAI_SHANG_CHUAN_HETONG;
		xhCarLoanApplyManager.changeStateAndWriteHistory(xhCarLoanApply, option,option.getMessage());
//			xhCarLoanApply.setState(CarState.DAI_SHANG_CHUAN_HETONG.getText());
//			xhCarLoanApplyDao.save(xhCarLoanApply);
		//保存到制作表
		XhMadeword xhMadeword = new XhMadeword();
		xhMadeword.setState("0");
		xhMadeword.setStype("0");
		xhMadeword.setTableId(xhCarLoanContract.getId());
		xhMadeword.setTableName("xh_car_loan_contract");
		xhMadewordDao.save(xhMadeword);
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
				sql = sql + " and c.STATE = '" +  value + "'";
			}
		}
		//0：待签约 1：已签约上传   -1：取消
		if(filter.containsKey("hetongstate")){
			value = String.valueOf(filter.get("hetongstate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = " +  value ;
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
		//applyID
		if(filter.containsKey("carApplyId")){
			value = String.valueOf(filter.get("carApplyId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CAR_APPLY_ID = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("userName")){
            value = String.valueOf(filter.get("userName"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and b.user_name like '%"+value+"%'";
            }
        } 
		
		//申请日期
		if(filter.containsKey("jk_loan_date")){
			value = String.valueOf(filter.get("jk_loan_date"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.JK_LOAN_DATE = '" +  value + "'";
			}
		}
		
		//借款类型GPS移交
		if(filter.containsKey("jkType")){
			value = String.valueOf(filter.get("jkType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.JK_TYPE = '" +  value + "'";
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
	
	
	public XhCarLoanContract getAgreementLook(Long id) {
		String sql="";
		sql = "select * from XH_CAR_LOAN_CONTRACT t where 1=1 ";
		sql = sql + " and t.CAR_APPLY_ID = "+id;
		List<Map<String, Object>> carList = jdbcDao.searchByMergeSql(sql);
	    XhCarLoanContract xhCarLoanContract = xhCarLoanContractDao.get(Long.parseLong(carList.get(0).get("ID")+""));
		return xhCarLoanContract;
}
}
