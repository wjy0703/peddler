package cn.com.cucsi.vechicle.service;

import java.util.ArrayList;
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
import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyComplementDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyDao;
import cn.com.cucsi.vechicle.dao.XhCarMessageDao;
import cn.com.cucsi.vechicle.dao.XhCarStateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApplyComplement;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarMessage;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarState;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;
import cn.com.cucsi.vechicle.util.CarStateExchange;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarLoanApplyManager {
    
    private Logger logger = LoggerFactory.getLogger(XhCarLoanApplyManager.class);
   
    private static  final String CAR_APPLY_AUDIT_LIST = "queryXhCarLoanApplyAuditList";
    
    private static final String CAR_APPLY_AUDIT_LIST_OVER = "queryXhCarLoanApplyAuditOverList";
    
    @Autowired
    private XhCarLoanApplyComplementDao xhCarLoanApplyComplementDao ; 
    
    @Autowired
    private XhCarStateDao xhCarStateDao;

    @Autowired
	private XhCarLoanApplyDao xhCarLoanApplyDao;

    @Autowired
    private NamedJdbcDao namedJdbcDao;
    
    @Autowired 
    private XhCarMessageDao  xhCarMessageDao;
	
    @Autowired
    private JdbcDao jdbcDao;
    
    
    public void saveApply(XhCarLoanApply xhCarLoanApply, CarOperation option){
        //代表是新的申请
   
        if(xhCarLoanApply.getId() == null){
//                Long userId = xhCarLoanApply.getXhCarLoanUser().getId();
//                xhCarLoanApply.setXhCarLoanUser(xhCarLoanUserManager.getXhCarLoanUser(userId));
                  
                  XhCarMessage xhCarMessage = xhCarLoanApply.getXhCarMessage();
                  if(logger.isDebugEnabled()){
                      logger.info("添加车辆信息 " + xhCarMessage);
                  }
                  if(xhCarMessage != null){
                      
                      List<XhCarLoanApply> xhCarLoanApplies = null;
                      List<XhCarLoanApply> originalList = xhCarMessage.getXhCarLoanApplies();//该车已经存在的借款申请
                      if(originalList!=null){
                          xhCarLoanApplies = originalList; ;
                      }else{
                          xhCarLoanApplies = new ArrayList<XhCarLoanApply>();
                      }
                      xhCarLoanApplies.add(xhCarLoanApply);
                      xhCarMessage.setXhCarLoanApplies(xhCarLoanApplies);
                      xhCarMessageDao.save(xhCarMessage);
                  }
                  if(logger.isDebugEnabled()){
                      logger.info("添加申请信息 " + xhCarLoanApply);
                  }
                  //非展期
                  xhCarLoanApply.setIsExtension("0");
                  //是否展期过
                  xhCarLoanApply.setIsHaveextension("0");
                  double loanScale ;
                  loanScale = (xhCarLoanApply.getJkLoanQuota()/xhCarLoanApply.getXhCarMessage().getAssessMoney())*100;
                  xhCarLoanApply.setLoanScale(Math.floor(loanScale));
                  //xhCarLoanApply.setLoanScale((xhCarLoanApply.getJkLoanQuota()/xhCarLoanApply.getXhCarMessage().getAssessMoney())*100);
                  CarState state = CarStateExchange.getState(xhCarLoanApply, option);
                  xhCarLoanApply.setState(state.getText());
                  xhCarLoanApplyDao.save(xhCarLoanApply);
                  
                  String oldState = "";
                  saveHistory(oldState,xhCarLoanApply,option,option.getMessage());
                  carMessageToComment(xhCarMessage,xhCarLoanApply);
         }else{
             //代表是编辑操作
             XhCarLoanApply xhCarLoanApplyData = xhCarLoanApplyDao.get(xhCarLoanApply.getId());
             
             try {
                  new HibernateAwareBeanUtilsBean().copyProperties(xhCarLoanApplyData, xhCarLoanApply);
             } catch (Exception e) {
                 if(logger.isErrorEnabled()){
                     logger.error("添加车申请信息出现错误 ");
                 }
                 e.printStackTrace();
             } 
             XhCarMessage xhCarMessage = xhCarLoanApplyData.getXhCarMessage();
             CarState state = CarStateExchange.getState(xhCarLoanApplyData, option);
             String oldState = xhCarLoanApplyData.getState();
             xhCarLoanApplyData.setState(state.getText());
             xhCarMessageDao.save(xhCarMessage);
             xhCarLoanApplyDao.save(xhCarLoanApplyData);
             saveHistory(oldState,xhCarLoanApplyData,option,option.getMessage());
             carMessageToComment(xhCarMessage,xhCarLoanApplyData);
         }
    }
    
    /**
     * 保存展期
     * @param xhCarLoanApply
     * @param option
     */
    public void saveExtension(XhCarLoanApply xhCarLoanApply,XhCarLoanApply xhCarLoanApplyOracle,CarOperation option){
        //代表是新的申请
        if(xhCarLoanApply.getId() == null){
                  XhCarMessage xhCarMessage = xhCarLoanApply.getXhCarMessage();
                  if(logger.isDebugEnabled()){
                      logger.info("添加车辆信息 " + xhCarMessage);
                  }
                  if(xhCarMessage != null){
                      List<XhCarLoanApply> xhCarLoanApplies = null;
                      List<XhCarLoanApply> originalList = xhCarMessage.getXhCarLoanApplies();//该车已经存在的借款申请
                      if(originalList!=null){
                          xhCarLoanApplies = originalList; ;
                      }else{
                          xhCarLoanApplies = new ArrayList<XhCarLoanApply>();
                      }
                      xhCarLoanApplies.add(xhCarLoanApply);
                      xhCarMessage.setXhCarLoanApplies(xhCarLoanApplies);
                      xhCarMessageDao.save(xhCarMessage);
                  }
                  if(logger.isDebugEnabled()){
                      logger.info("添加申请信息 " + xhCarLoanApply);
                  }
                //改变原始申请数据是否已展期状态
                  xhCarLoanApplyOracle.setIsHaveextension("1");
                  xhCarLoanApplyDao.save(xhCarLoanApplyOracle);
                  //是否展期
                  xhCarLoanApply.setIsExtension("1");
                  //是否已展期
                  xhCarLoanApply.setIsHaveextension("0");
                  //原始id
                  if(xhCarLoanApply.getIsHaveextension().equals("1")){
                	    xhCarLoanApply.setOriginalCarApplyId(xhCarLoanApply.getOriginalCarApplyId());
                  }else{
                	    xhCarLoanApply.setOriginalCarApplyId(xhCarLoanApply.getPartenCarApplyId());
                  }
                  double loanScale ;
                  loanScale = (xhCarLoanApply.getJkLoanQuota()/xhCarLoanApply.getXhCarMessage().getAssessMoney())*100;
                  xhCarLoanApply.setLoanScale(Math.floor(loanScale));
                  CarState state = CarStateExchange.getState(xhCarLoanApply, option);
                  xhCarLoanApply.setState(state.getText());
                  xhCarLoanApplyDao.save(xhCarLoanApply);
                  
                  String oldState = "";
                  saveHistory(oldState,xhCarLoanApply,option,option.getMessage());
                  carMessageToComment(xhCarMessage,xhCarLoanApply);
         }else{
             //代表是编辑操作
             XhCarLoanApply xhCarLoanApplyData = xhCarLoanApplyDao.get(xhCarLoanApply.getId());
             try {
                  new HibernateAwareBeanUtilsBean().copyProperties(xhCarLoanApplyData, xhCarLoanApply);
             } catch (Exception e) {
                 if(logger.isErrorEnabled()){
                     logger.error("添加车申请信息出现错误 ");
                 }
                 e.printStackTrace();
             } 
             XhCarMessage xhCarMessage = xhCarLoanApplyData.getXhCarMessage();
             CarState state = CarStateExchange.getState(xhCarLoanApplyData, option);
             String oldState = xhCarLoanApplyData.getState();
             xhCarLoanApplyData.setState(state.getText());
             xhCarMessageDao.save(xhCarMessage);
             xhCarLoanApplyDao.save(xhCarLoanApplyData);
             saveHistory(oldState,xhCarLoanApplyData,option,option.getMessage());
             carMessageToComment(xhCarMessage,xhCarLoanApplyData);
         }
    }
	
    /**
     * 保存车贷历史状态
     *
     * @param oldState
     * @param xhCarLoanApply
     * @param option
     * @author xjs
     * @date 2013-10-7 上午10:02:47
     */
    public void saveHistory(String oldState, XhCarLoanApply xhCarLoanApply, CarOperation option,String remark) {
        XhCarState xhCarState = new XhCarState();
        xhCarState.setOldState(oldState);
        xhCarState.setNewState(xhCarLoanApply.getState());
        xhCarState.setXhCarLoanApply(xhCarLoanApply);   
        xhCarState.setDescribe(option.getMessage());
        xhCarState.setRemarks(remark);
        xhCarStateDao.save(xhCarState);             
    }

    /**
     * 同步车辆信息到xhLoanapplyComment，信息冗余带来的问题，方便同一个客户循环借款时调出历史记录
     *
     * @param xhCarMessage
     * @param xhCarLoanApply
     * @author xjs
     * @date 2013-10-7 上午8:16:19
     */
	private void carMessageToComment(XhCarMessage xhCarMessage, XhCarLoanApply xhCarLoanApply) {
	    XhCarLoanApplyComplement comment = xhCarLoanApply.getXhCarLoanApplyComplement();
	    if(comment != null){	        
	        try {
                new HibernateAwareBeanUtilsBean().copyProperties(comment, xhCarMessage);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("修改车申请信息出现错误 comment错误 ");
            }
	    }else{
	        comment = new XhCarLoanApplyComplement();
	        try {
                new HibernateAwareBeanUtilsBean().copyProperties(comment, xhCarMessage);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("添加车申请信息出现错误 comment错误 ");
            }
	        comment.setXhCarLoanApply(xhCarLoanApply);	        
	    }
	    xhCarLoanApply.setXhCarLoanApplyComplement(comment);
	    xhCarLoanApplyComplementDao.save(comment);
    }

    
    @Transactional(readOnly = true)
	public Page<XhCarLoanApply> searchXhCarLoanApply(final Page<XhCarLoanApply> page, final Map<String,Object> filters) {
		return xhCarLoanApplyDao.queryXhCarLoanApply(page, filters);
	}
	
	
	@Transactional(readOnly = true)
	public XhCarLoanApply getXhCarLoanApply(Long id) {
		return xhCarLoanApplyDao.get(id);
	}

	public void saveXhCarLoanApply(XhCarLoanApply entity) {
		xhCarLoanApplyDao.save(entity);
	}

	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCarLoanApply(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//借款人ID
		if(filter.containsKey("carUserId")){
		    value = String.valueOf(filter.get("carUserId"));
            if(StringUtils.isNotEmpty(value)) {
                sql += sql + " and a.car_user_id =" + value;
            }
        }
		
		//借款申请额度
		if(filter.containsKey("jkLoanQuota")){
			value = String.valueOf(filter.get("jkLoanQuota"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_LOAN_QUOTA = '" +  value + "'";
			}
		}
		//借款成数（借款总额/车辆评估金额）
		if(filter.containsKey("loanScale")){
			value = String.valueOf(filter.get("loanScale"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_SCALE = '" +  value + "'";
			}
		}
		//综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）
		if(filter.containsKey("comlpexMoney")){
			value = String.valueOf(filter.get("comlpexMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMLPEX_MONEY = '" +  value + "'";
			}
		}
		//借款周期
		if(filter.containsKey("jkCycle")){
			value = String.valueOf(filter.get("jkCycle"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_CYCLE = '" +  value + "'";
			}
		}
		//借款类型GPS移交
		if(filter.containsKey("jkType")){
			value = String.valueOf(filter.get("jkType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_TYPE = '" +  value + "'";
			}
		}
		//申请日期
		if(filter.containsKey("jk_loan_date")){
			value = String.valueOf(filter.get("jk_loan_date"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_LOAN_DATE = '" +  value + "'";
			}
		}
		//开卡（省/市）
		if(filter.containsKey("bankCity")){
			value = String.valueOf(filter.get("bankCity"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_CITY = '" +  value + "'";
			}
		}
		//账户开户行
		if(filter.containsKey("bankOpen")){
			value = String.valueOf(filter.get("bankOpen"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_OPEN = '" +  value + "'";
			}
		}
		//账户名称
		if(filter.containsKey("bankUsername")){
			value = String.valueOf(filter.get("bankUsername"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_USERNAME = '" +  value + "'";
			}
		}
		//账户号码
		if(filter.containsKey("bankNum")){
			value = String.valueOf(filter.get("bankNum"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NUM = '" +  value + "'";
			}
		}
		//组织
		if(filter.containsKey("organiId")){
			value = String.valueOf(filter.get("organiId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ORGANI_ID = '" +  value + "'";
			}
		}
		//管辖城市(做查询依据)
		if(filter.containsKey("crmcity")){
			value = String.valueOf(filter.get("crmcity"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CRMCITY = '" +  value + "'";
			}
		}
		//管辖省份(做查询依据)
		if(filter.containsKey("crmprovince")){
			value = String.valueOf(filter.get("crmprovince"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CRMPROVINCE = '" +  value + "'";
			}
		}
		//团队经理(做查询依据)
		if(filter.containsKey("teamLeaderId")){
			value = String.valueOf(filter.get("teamLeaderId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TEAM_LEADER_ID = '" +  value + "'";
			}
		}
		//客户经理(做查询依据)
		if(filter.containsKey("customerLeaderId")){
			value = String.valueOf(filter.get("customerLeaderId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_LEADER_ID = '" +  value + "'";
			}
		}
		//客服(做查询依据)
		if(filter.containsKey("employeeServiceStaff")){
			value = String.valueOf(filter.get("employeeServiceStaff"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.EMPLOYEE_SERVICE_STAFF = '" +  value + "'";
			}
		}
		//销售(做查询依据)
		if(filter.containsKey("employeeSeller")){
			value = String.valueOf(filter.get("employeeSeller"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.EMPLOYEE_SELLER = '" +  value + "'";
			}
		}
		//提交状态
		if(filter.containsKey("subState")){
			value = String.valueOf(filter.get("subState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SUB_STATE = '" +  value + "'";
			}
		}
		//借款申请流程状态，状态参考实体
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("userName")){
            value = String.valueOf(filter.get("userName"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and b.user_name like '%"+value+"%'";
            }
        } 
		
		if(filter.containsKey("loanCode")){
            value = String.valueOf(filter.get("loanCode"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and a.loan_code like '%" + value+ "%'";
            }
        } 
		
		if(filter.containsKey("loanCode")){
            value = String.valueOf(filter.get("loanCode"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and a.loan_code like '%" + value+ "%'";
            }
        }
		
		//权限过滤查询sql
		sql = sql + PropertiesUtils.getCarSql();
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
			sql = sql + " order by a.create_time desc ";
		filter.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, filter, page);
	}
	
	/**
	 * 查询
	 *
	 * @param filter
	 * @param page
	 * @return
	 * @author xjs
	 * @date 2013-10-10 下午4:45:45
	 */
	@Transactional(readOnly = true)
    public List<Map<String,Object>> searchXhCarLoanApplyAudit(Map<String,Object> filter,JdbcPage page)
    {
        String sql="";
        String value = "";
        //借款申请人姓名
        if(filter.containsKey("userName")){
            value = String.valueOf(filter.get("userName"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and b.USER_NAME like '%"+value+"%'";
            }
        } 
        
        //借款申请时间
        if(filter.containsKey("jk_loan_date")){
            value = String.valueOf(filter.get("jk_loan_date"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and a.JK_LOAN_DATE = '"+value+"'";
            }
        }
        
      //借款类型GPS移交
      		if(filter.containsKey("jkType")){
      			value = String.valueOf(filter.get("jkType"));
      			if(StringUtils.isNotEmpty(value)) {
      				sql = sql + " and a.JK_TYPE = '" +  value + "'";
      			}
      		}
        
        if(filter.containsKey("inStates")){
            List<String> states = (List<String>)filter.get("inStates");
            if(states.size() > 0){
                if(states.size() == 1){
                    value = states.get(0);
                    sql += " and a.state = :singleState ";
                    filter.put("singleState", value);
                }else{
                    String valueList ="";
                    for(String state : states){
                        valueList += "'" + state+"',";
                    }
                    valueList = valueList.substring(0,valueList.length() - 1 );
                    sql += " and a.state in (" + valueList + ") ";
                }
            }
        }
        
        
        //权限过滤查询sql
        sql = sql + PropertiesUtils.getCarSql();
        if (page.getOrderBy()!=null){
            sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
        }
        
        filter.put("sql", sql);
        
        return jdbcDao.searchPagesByMergeSqlTemplate(CAR_APPLY_AUDIT_LIST,filter, page);
    }

    public void changeStateAndWriteHistory(XhCarLoanApply xhCarLoanApply, CarOperation operation,String remark) {
        String oldState = xhCarLoanApply.getState();
        CarState state = CarStateExchange.getState(xhCarLoanApply, operation);
        xhCarLoanApply.setState(state.getText());
        xhCarLoanApplyDao.save(xhCarLoanApply);//修改状态
        //保存历史
        saveHistory(oldState, xhCarLoanApply, operation,remark);        
    }

    public List<Map<String, Object>> searchXhCarLoanApplyAuditOver(Map<String, Object> filter, JdbcPage page) {
		String sql="";
		String insql="";
		
		String value="";
		
		 //借款申请人姓名
        if(filter.containsKey("userName")){
            value = String.valueOf(filter.get("userName"));
            if(StringUtils.isNotEmpty(value)) {
                filter.put("user_Name", "%" + value + "%");
                sql += " and b.USER_NAME like :user_Name";
            }
        } 
        
        //借款申请时间
        if(filter.containsKey("jk_loan_date")){
            value = String.valueOf(filter.get("jk_loan_date"));
            if(StringUtils.isNotEmpty(value)) {
                sql += " AND a.JK_LOAN_DATE = :jk_loan_date";
            }
        }
        
      //借款类型GPS移交
      		if(filter.containsKey("jkType")){
      			value = String.valueOf(filter.get("jkType"));
      			if(StringUtils.isNotEmpty(value)) {
      				sql +=  " AND a.JK_TYPE = :jkType ";      				
      			}
      		}
		
		if(filter.containsKey("inStates")){
			value = String.valueOf(filter.get("inStates"));
  			if(StringUtils.isNotEmpty(value)) {
  				sql += " and t.CREDIT_TYPE = :inStates ";
  				insql += " AND MULTIAUDITS.CREDIT_TYPE = :inStates ";
  			}
        }
		
        filter.put("sql", sql);
        filter.put("insql", insql);
		
        return namedJdbcDao.searchPagesByMergeSqlTemplate(CAR_APPLY_AUDIT_LIST_OVER,filter, page);
	}

}
