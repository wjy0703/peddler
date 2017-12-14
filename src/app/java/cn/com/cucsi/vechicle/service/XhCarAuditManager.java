package cn.com.cucsi.vechicle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.vechicle.dao.XhCarAuditDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyComplementDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanUserDao;
import cn.com.cucsi.vechicle.dao.XhCarStateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApplyComplement;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;
import cn.com.cucsi.vechicle.util.CarStateExchange;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarAuditManager {
	
	private Logger logger = LoggerFactory.getLogger(XhCarAuditManager.class);

	@Autowired
	private XhCarAuditDao xhCarAuditDao;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	@Autowired
    private XhCarStateDao xhCarStateDao;

    @Autowired
	private XhCarLoanApplyDao xhCarLoanApplyDao;
    
    @Autowired
    private XhCarLoanApplyComplementDao xhCarLoanApplyComplementDao ; 
    
    @Autowired
    private XhCarLoanUserDao xhCarLoanUserDao; 
    
    @Autowired
    XhCarLoanApplyManager xhCarLoanApplyManager;
    
    @Autowired
    private BaseInfoManager baseInfoManager;
	
	
	public void saveXhCarAudit(XhCarLoanApply xhCarLoanApply,CarOperation option)
	{
        XhCarLoanUser user = xhCarLoanApply.getXhCarLoanUser();
        XhCarLoanUser userOracle = xhCarLoanUserDao.get(user.getId());
        try {
            new HibernateAwareBeanUtilsBean().copyProperties(userOracle, user);
        } catch (Exception e) {
            logger.error("----拷贝人员出现错误");
        } 
        //保存联系人审核信息
        xhCarLoanUserDao.save(userOracle);
        //保存审核信息
        XhCarLoanApplyComplement xhCarLoanApplyComplement = xhCarLoanApply.getXhCarLoanApplyComplement();
        XhCarLoanApplyComplement xhCarLoanApplyComplementOracle = xhCarLoanApplyComplementDao.get(xhCarLoanApplyComplement.getId());
        try {
            new HibernateAwareBeanUtilsBean().copyProperties(xhCarLoanApplyComplementOracle, xhCarLoanApplyComplement);
        } catch (Exception e) {
            logger.error("----拷贝人员出现错误");
        }
        xhCarLoanApplyComplementDao.save(xhCarLoanApplyComplementOracle);
        //车借审核表
        Employee emp = baseInfoManager.getUserEmployee();
        for(XhCarAudit audit :xhCarLoanApply.getXhCarAudit()){
            //判断是否审核结束
            boolean  auditFinished = isAuditFinished(audit,option);
            if(auditFinished){
                audit.setCreditState((long)1);
            }
            audit.setAuditEmployeeId(emp.getId());
        	xhCarAuditDao.save(audit);
        }
        //修改申请状态
        XhCarLoanApply xhCarLoanApplyOracle = xhCarLoanApplyDao.get(xhCarLoanApply.getId());
//        String oldState = xhCarLoanApplyOracle.getState();
//        CarState state = CarStateExchange.getState(xhCarLoanApply, option);
//        xhCarLoanApply.setState(state.getText());
//        xhCarLoanApplyDao.save(xhCarLoanApply);//修改状态
      //保存历史
        //xhCarLoanApplyManager.saveHistory(oldState, xhCarLoanApplyOracle, option);
        xhCarLoanApplyManager.changeStateAndWriteHistory(xhCarLoanApplyOracle, option,xhCarLoanApply.getXhCarAudit().get(0).getCreditAuditReport());
	}
	
	/**
	 * 判断是否应该是审核结束
	 *
	 * @param audit
	 * @param option
	 * @return
	 * @author xjs
	 * @date 2013-10-12 上午9:57:05
	 */
	private boolean isAuditFinished(XhCarAudit audit, CarOperation option) {
	    if(option == CarOperation.FINAL_AUDIT){ //代表终审通过，设置为结束
	        return true;
	    }
	    return false;
    }
	
	/**
	 * 判断是否车借老客户
	 * @return
	 */
	public  List<Map<String,Object>> isExtension(Long applyid,Long userid){
		 String sql = "select * from (select * from xh_car_loan_apply x  where x.is_extension=0 and x.id!="+applyid+" and x.car_user_id="+userid+" order by x.create_time desc) where rownum=1";
		 List<Map<String,Object>> isExtension = jdbcDao.searchByMergeSql(sql);
		 return isExtension;
	}

    @Transactional(readOnly = true)
	public Page<XhCarAudit> searchXhCarAudit(final Page<XhCarAudit> page, final Map<String,Object> filters) {
		return xhCarAuditDao.queryXhCarAudit(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCarAudit getXhCarAudit(Long id) {
		return xhCarAuditDao.get(id);
	}

	public void saveXhCarAudit(XhCarAudit entity) {
		xhCarAuditDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCarAudit(Long id) {
		xhCarAuditDao.delete(id);
	}
	
	public boolean batchDelXhCarAudit(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCarAudit(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCarAudit(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//信审类型：初审(1)、复审(2)、终审(100)
		if(filter.containsKey("creditType")){
			value = String.valueOf(filter.get("creditType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_TYPE = '" +  value + "'";
			}
		}
		//信审状态：0信审未结束 1信审结束
		if(filter.containsKey("creditState")){
			value = String.valueOf(filter.get("creditState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_STATE = '" +  value + "'";
			}
		}
		//审批金额
		if(filter.containsKey("creditAmount")){
			value = String.valueOf(filter.get("creditAmount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_AMOUNT = '" +  value + "'";
			}
		}
		//借款期限（天）
		if(filter.containsKey("creditMonth")){
			value = String.valueOf(filter.get("creditMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_MONTH = '" +  value + "'";
			}
		}
		//综合费率
		if(filter.containsKey("creditAllRate")){
			value = String.valueOf(filter.get("creditAllRate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_ALL_RATE = '" +  value + "'";
			}
		}
		//业务收费
		if(filter.containsKey("operationFee")){
			value = String.valueOf(filter.get("operationFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OPERATION_FEE = '" +  value + "'";
			}
		}
		//外访费(需求说明文档没有)
		if(filter.containsKey("outVisitFee")){
			value = String.valueOf(filter.get("outVisitFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OUT_VISIT_FEE = '" +  value + "'";
			}
		}
		//加急费需求说明文档没有)
		if(filter.containsKey("urgentCreditFee")){
			value = String.valueOf(filter.get("urgentCreditFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.URGENT_CREDIT_FEE = '" +  value + "'";
			}
		}
		//拒贷原因
		if(filter.containsKey("creditRefuseReason")){
			value = String.valueOf(filter.get("creditRefuseReason"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_REFUSE_REASON = '" +  value + "'";
			}
		}
		//信审意见
		if(filter.containsKey("creditAuditReport")){
			value = String.valueOf(filter.get("creditAuditReport"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_AUDIT_REPORT = '" +  value + "'";
			}
		}
		//信审结果：1通过、0拒绝
		if(filter.containsKey("creditResult")){
			value = String.valueOf(filter.get("creditResult"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_RESULT = '" +  value + "'";
			}
		}
		//信审通过后形成的客户编号
		if(filter.containsKey("passedCustomerNo")){
			value = String.valueOf(filter.get("passedCustomerNo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PASSED_CUSTOMER_NO = '" +  value + "'";
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

	//查询当前申请的审核信息
	public List<Map<String ,Object>> getAudit(Long id) {
		String sql = "select * from XH_CAR_AUDIT t where 1=1";
		sql = sql + " and t.CAR_APPLY_ID = "+id;
		sql = sql + " order by t.create_time";
		List<Map<String, Object>> auditList = jdbcDao.searchByMergeSql(sql);
		System.out.println(sql);
		return auditList;
	}
}
