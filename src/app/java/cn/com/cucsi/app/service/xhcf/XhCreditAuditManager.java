package cn.com.cucsi.app.service.xhcf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.Constants;
import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.loan.XhJksqTogetherDao;
import cn.com.cucsi.app.dao.xhcf.AuditUploadImgDao;
import cn.com.cucsi.app.dao.xhcf.XhBlackListDao;
import cn.com.cucsi.app.dao.xhcf.XhCreditAuditDao;
import cn.com.cucsi.app.dao.xhcf.XhCreditTaskAssignDao;
import cn.com.cucsi.app.dao.xhcf.XhJkhtDao;
import cn.com.cucsi.app.dao.xhcf.XhSystemParameterDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.AuditUploadImg;
import cn.com.cucsi.app.entity.xhcf.XhBlackList;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhCreditTaskAssign;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.entity.xhcf.XhSystemParameter;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.UnZip;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCreditAuditManager extends PublicService {

	private static Logger logger = LoggerFactory
			.getLogger(XhCreditAuditManager.class);
	private XhJkhtDao xhJkhtDao;
	private XhSystemParameterDao xhSystemParameterDao;

	private AuditUploadImgDao auditUploadImgDao;
	private XhBlackListDao xhBlackListDao;
	
	@Autowired
	public void setXhJkhtDao(XhJkhtDao xhJkhtDao) {
		this.xhJkhtDao = xhJkhtDao;
	}
	
	@Autowired
	public void setXhBlackListDao(XhBlackListDao xhBlackListDao) {
		this.xhBlackListDao = xhBlackListDao;
	}

	@Autowired
	public void setAuditUploadImgDao(AuditUploadImgDao auditUploadImgDao) {
		this.auditUploadImgDao = auditUploadImgDao;
	}

	@Autowired
	public void setXhSystemParameterDao(
			XhSystemParameterDao xhSystemParameterDao) {
		this.xhSystemParameterDao = xhSystemParameterDao;
	}

	private XhJksqTogetherDao xhJksqTogetherDao;

	public XhJksqTogetherDao getXhJksqTogetherDao() {
		return xhJksqTogetherDao;
	}

	@Autowired
	public void setXhJksqTogetherDao(XhJksqTogetherDao xhJksqTogetherDao) {
		this.xhJksqTogetherDao = xhJksqTogetherDao;
	}

	private XhCreditTaskAssignDao xhCreditTaskAssignDao;

	public XhCreditTaskAssignDao getXhCreditTaskAssignDao() {
		return xhCreditTaskAssignDao;
	}

	@Autowired
	public void setXhCreditTaskAssignDao(
			XhCreditTaskAssignDao xhCreditTaskAssignDao) {
		this.xhCreditTaskAssignDao = xhCreditTaskAssignDao;
	}

	private BaseInfoManager baseInfoManager;

	public BaseInfoManager getBaseInfoManager() {
		return baseInfoManager;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	private XhCreditTaskAssignManager xhCreditTaskAssignManager;

	public XhCreditTaskAssignManager getXhCreditTaskAssignManager() {
		return xhCreditTaskAssignManager;
	}

	@Autowired
	public void setXhCreditTaskAssignManager(
			XhCreditTaskAssignManager xhCreditTaskAssignManager) {
		this.xhCreditTaskAssignManager = xhCreditTaskAssignManager;
	}

	private XhCreditAuditDao xhCreditAuditDao;
	private JdbcDao jdbcDao;
	private XhJksqStateDao xhJksqStateDao;

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	public void setXhCreditAuditDao(XhCreditAuditDao xhCreditAuditDao) {
		this.xhCreditAuditDao = xhCreditAuditDao;
	}

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhCreditAudit> searchXhCreditAudit(
			final Page<XhCreditAudit> page, final Map<String, Object> filters) {
		return xhCreditAuditDao.queryXhCreditAudit(page, filters);
	}

	@Transactional(readOnly = true)
	public XhCreditAudit getXhCreditAudit(Long id) {
		return xhCreditAuditDao.get(id);
	}

	/**
	 * 保存信审结果，同時选择下一手操作，修改借款申请状态及借款申请历史状态
	 * 
	 * @author songjf
	 * @modifyData 2013-08-10
	 * @param entity
	 */
	public void saveXhCreditAudit(XhCreditAudit entity,
			HttpServletRequest request) {

		// 获取复审最大权限金额参数
		XhSystemParameter xhSystemParameter = xhSystemParameterDao
				.findUniqueBy("sysName", "MAX_VALUE_OF_SECOND_ADUIT");

		XhJksq loanAppley = entity.getLoanApply();
		// XhJksqState state = new XhJksqState();
		// state.setXhjksq(loanAppley);
		String mess = "";
		String remark = "";
		if (entity.getCreditType().equals("1")) {// 处理初审
			if (entity.getCreditResult().equals("0")) {
				entity.setCreditState("1");// 初审拒绝,信审结束
				loanAppley.setState("34#J");// 初审拒绝,待审核
				mess = "信用初审拒绝!";
				remark = entity.getCreditRefuseReason();
				// 拒绝也需要提交到指定的复审人员
				String secondAduitEmployeeId = request
						.getParameter("employee.id");

				logger.debug("saveXhCreditAudit():secondAduitEmployeeId is:"
						+ secondAduitEmployeeId);

				XhCreditTaskAssign xhCreditTaskAssign = xhCreditTaskAssignManager
						.getLastXhCreditTaskAssign(entity.getLoanApply()
								.getId().toString());
				Employee secondAduitEmployee = baseInfoManager.getEmployee(Long
						.valueOf(secondAduitEmployeeId));
				xhCreditTaskAssign.setSecondAduitEmployee(secondAduitEmployee);
				xhCreditTaskAssignDao.save(xhCreditTaskAssign);

			} else if (entity.getCreditResult().equals("1")) {
				// if (Double.parseDouble(entity.getCreditAmount()) <=
				// SysConstant.MAX_VALUE_OF_FIRST_ADUIT) {
				// entity.setCreditState("1");
				// entity.getLoanApply().setState("50");
				// mess = "完成信用审核，待合同制作!");
				// } else {

				entity.setCreditState("0");
				entity.getLoanApply().setState("32");
				mess = "信用初审通过!";
				String secondAduitEmployeeId = request
						.getParameter("employee.id");

				logger.debug("saveXhCreditAudit():secondAduitEmployeeId is:"
						+ secondAduitEmployeeId);

				XhCreditTaskAssign xhCreditTaskAssign = xhCreditTaskAssignManager
						.getLastXhCreditTaskAssign(entity.getLoanApply()
								.getId().toString());
				Employee secondAduitEmployee = baseInfoManager.getEmployee(Long
						.valueOf(secondAduitEmployeeId));
				xhCreditTaskAssign.setSecondAduitEmployee(secondAduitEmployee);
				xhCreditTaskAssignDao.save(xhCreditTaskAssign);

				// }
			} else if (entity.getCreditResult().equals("4")) {
				loanAppley.setTogetherPerson("是");
				XhJksqTogether together = new XhJksqTogether();
				together.setTogetherName(request.getParameter("togetherName"));
				together.setIdentification(request
						.getParameter("togetherCardNo"));

				together.setXhjksq(loanAppley);
				xhJksqTogetherDao.save(together);
				// 取消初审审批权限金额限制
				// if (Double.parseDouble(entity.getCreditAmount()) <=
				// SysConstant.MAX_VALUE_OF_FIRST_ADUIT) {
				// entity.setCreditState("1");
				// entity.getLoanApply().setState("50");
				// mess = "完成信用审核，待合同制作!");
				// } else {
				entity.setCreditState("0");
				entity.getLoanApply().setState("32");
				mess = "追加共借人/信用初审通过,待复审!";
				String secondAduitEmployeeId = request
						.getParameter("employee.id");

				logger.info("secondAduitEmployeeId is:" + secondAduitEmployeeId);

				XhCreditTaskAssign xhCreditTaskAssign = xhCreditTaskAssignManager
						.getLastXhCreditTaskAssign(entity.getLoanApply()
								.getId().toString());
				Employee secondAduitEmployee = baseInfoManager.getEmployee(Long
						.valueOf(secondAduitEmployeeId));
				xhCreditTaskAssign.setSecondAduitEmployee(secondAduitEmployee);
				xhCreditTaskAssignDao.save(xhCreditTaskAssign);

				// }

			} else if (entity.getCreditResult().equals("5")) {
				entity.setCreditState("1");// 初审拒绝,信审结束
				loanAppley.setState("81");// 客戶放棄
				mess = "客户放弃!";
				remark = entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("3")) {
				entity.setCreditState("0");// 初审未结束,退回修改或补充上传
				loanAppley.setState("31.B");// 初审退回
				mess = "信用初审退回!";
				remark = "退件原因：" + entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("6")) {
				entity.setCreditState("1");// 初审拒绝,放入黑名单
				// 放入黑名单
				XhBlackList xhBlackList = new XhBlackList();
				xhBlackList.setName(loanAppley.getJkrxm());
				xhBlackList.setIdentifId(loanAppley.getZjhm());
				xhBlackListDao.save(xhBlackList);
				loanAppley.setState("34");// 初审拒绝

				mess = "黑名单客户!";
				remark = entity.getCreditRefuseReason();
			}
		} else if (entity.getCreditType().equals("2")) {// 处理复审
			if (entity.getCreditResult().equals("0")) {
				entity.setCreditState("1");//
				loanAppley.setState("35");// 复审拒绝
				mess = "信用复审拒绝!";
				remark = entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("1")) {

				if (Double.parseDouble(entity.getCreditAmount()) <= Double
						.parseDouble(xhSystemParameter.getSysValue())) {
					entity.setCreditState("1");
					entity.getLoanApply().setState("50");
					mess = "完成信用审核，待合同制作!";
				} else {
					entity.setCreditState("0");
					entity.getLoanApply().setState("33");
					mess = "信用复审通过!";
					
					//新增code 选择终审人员
					String finalAduitEmployeeId = request
							.getParameter("employee.id");

					logger.info("secondAduitEmployeeId is:" + finalAduitEmployeeId);

					XhCreditTaskAssign xhCreditTaskAssign = xhCreditTaskAssignManager
							.getLastXhCreditTaskAssign(entity.getLoanApply()
									.getId().toString());
					Employee finalAduitEmployee = baseInfoManager.getEmployee(Long
							.valueOf(finalAduitEmployeeId));
					xhCreditTaskAssign.setFinalAduitEmployee(finalAduitEmployee);
					xhCreditTaskAssignDao.save(xhCreditTaskAssign);
				}
			}

			else if (entity.getCreditResult().equals("2")) {

				if (Double.parseDouble(entity.getCreditAmount()) <= Double
						.parseDouble(xhSystemParameter.getSysValue())) {
					entity.setCreditState("1");
					entity.getLoanApply().setState("32#T");// 通过补充材料
					mess = "信用复审通过 ，需补充材料!";
				} else {
					entity.setCreditState("0");
					entity.getLoanApply().setState("33");
					mess = "信用复审通过 ,待终审!";
					
					//新增code 选择终审人员
					String finalAduitEmployeeId = request
							.getParameter("employee.id");

					logger.info("secondAduitEmployeeId is:" + finalAduitEmployeeId);

					XhCreditTaskAssign xhCreditTaskAssign = xhCreditTaskAssignManager
							.getLastXhCreditTaskAssign(entity.getLoanApply()
									.getId().toString());
					Employee finalAduitEmployee = baseInfoManager.getEmployee(Long
							.valueOf(finalAduitEmployeeId));
					xhCreditTaskAssign.setFinalAduitEmployee(finalAduitEmployee);
					xhCreditTaskAssignDao.save(xhCreditTaskAssign);
					
				}
			} else if (entity.getCreditResult().equals("3")) {
				entity.setCreditState("0");// 复审未结束,退回修改或补充上传
				loanAppley.setState("32#B");// 复审退回
				mess = "信用复审退回!";
				remark = "退件原因：" + entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("4")) {
				loanAppley.setTogetherPerson("是");
				XhJksqTogether together = new XhJksqTogether();
				together.setTogetherName(request.getParameter("togetherName"));
				together.setIdentification(request
						.getParameter("togetherCardNo"));

				together.setXhjksq(loanAppley);
				xhJksqTogetherDao.save(together);

				if (Double.parseDouble(entity.getCreditAmount()) <= Double
						.parseDouble(xhSystemParameter.getSysValue())) {
					entity.setCreditState("1");
					entity.getLoanApply().setState("50");
					mess = "追加共借人/信用复审通过,待合同制作!";
				} else {
					entity.setCreditState("0");
					entity.getLoanApply().setState("33");
					mess = "追加共借人/信用复审通过,待终审!";
					
					//新增code 选择终审人员
					String finalAduitEmployeeId = request
							.getParameter("employee.id");

					logger.info("secondAduitEmployeeId is:" + finalAduitEmployeeId);

					XhCreditTaskAssign xhCreditTaskAssign = xhCreditTaskAssignManager
							.getLastXhCreditTaskAssign(entity.getLoanApply()
									.getId().toString());
					Employee finalAduitEmployee = baseInfoManager.getEmployee(Long
							.valueOf(finalAduitEmployeeId));
					xhCreditTaskAssign.setFinalAduitEmployee(finalAduitEmployee);
					xhCreditTaskAssignDao.save(xhCreditTaskAssign);

				}

			} else if (entity.getCreditResult().equals("5")) {
				entity.setCreditState("1");// 复审拒绝,信审结束
				loanAppley.setState("81");// 客戶放棄
				mess = "客户放弃!";
				remark = entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("6")) {
				entity.setCreditState("1");// 复审拒绝,放入黑名单
				// 放入黑名单
				XhBlackList xhBlackList = new XhBlackList();
				xhBlackList.setName(loanAppley.getJkrxm());
				xhBlackList.setIdentifId(loanAppley.getZjhm());
				xhBlackListDao.save(xhBlackList);
				loanAppley.setState("35");// 复审拒绝

				mess = "黑名单客户!";
				remark = entity.getCreditRefuseReason();
			}
		} else if (entity.getCreditType().equals("3")) {// 处理终审
			if (entity.getCreditResult().equals("0")) {
				entity.setCreditState("1");//
				loanAppley.setState("36");// 终审拒绝
				mess = "信用终审拒绝!";
				remark = entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("1")) {
				entity.setCreditState("1");
				entity.getLoanApply().setState("50");
				mess = "完成信用审核，待合同制作!";
			} 
			
			else if (entity.getCreditResult().equals("2")) {

				
					entity.setCreditState("1");
					entity.getLoanApply().setState("32#T");// 通过补充材料
					mess = "信用终审通过 ，需补充材料!";
				
			}
			
			
			else if (entity.getCreditResult().equals("3")) {
				entity.setCreditState("0");// 终审未结束,退回修改或补充上传
				loanAppley.setState("33#B");// 复审退回
				mess = "信用终审退回!";
				remark = "退件原因：" + entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("4")) {
				loanAppley.setTogetherPerson("是");
				XhJksqTogether together = new XhJksqTogether();
				together.setTogetherName(request.getParameter("togetherName"));
				together.setIdentification(request
						.getParameter("togetherCardNo"));

				together.setXhjksq(loanAppley);
				xhJksqTogetherDao.save(together);

				entity.setCreditState("1");
				entity.getLoanApply().setState("50");
				mess = "追加共借人/信用终审通过,待合同制作!";

			} else if (entity.getCreditResult().equals("5")) {
				entity.setCreditState("1");// 初审拒绝,信审结束
				loanAppley.setState("81");// 客戶放棄
				mess = "客户放弃!";
				remark = entity.getCreditRefuseReason();
			} else if (entity.getCreditResult().equals("6")) {
				entity.setCreditState("1");// 终审拒绝,放入黑名单
				// 放入黑名单
				XhBlackList xhBlackList = new XhBlackList();
				xhBlackList.setName(loanAppley.getJkrxm());
				xhBlackList.setIdentifId(loanAppley.getZjhm());
				xhBlackListDao.save(xhBlackList);
				loanAppley.setState("36");// 终审拒绝

				mess = "黑名单客户!";
				remark = entity.getCreditRefuseReason();
			}
		}else if(entity.getCreditType().equals("4")){ //处理复议
		    //查询信审状态为1的结果更改状态为0
		    StringBuffer hql = new StringBuffer(" from XhCreditAudit xhCreditAudit ");
		    hql.append("where creditState='1' and loanApply.id=:loanApplyId order by createTime desc");
		    Map<String,Object> param = new HashMap<String,Object>();
		    param.put("loanApplyId", entity.getLoanApply().getId());
		    XhCreditAudit xhCreditAudit = xhCreditAuditDao.findUnique(hql.toString(), param);
		    xhCreditAudit.setCreditState("0");
		    //取之前信审客户编号存入新记录中
		    entity.setPassedCustomerNo(xhCreditAudit.getPassedCustomerNo());
		}
		//信审状态为结束的并且信审类型不为复议类型生成客户编号
		if (entity.getCreditState().equals("1") && !entity.getCreditType().equals("4")) {
			entity.setPassedCustomerNo(String.valueOf(xhCreditAuditDao
					.getNumsOfPassAduit() + 1));
		}
		baseInfoManager.saveXhJksqState(loanAppley, mess, remark);
		// xhJksqStateDao.save(state);
		
		xhCreditAuditDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCreditAudit(Long id) {
		xhCreditAuditDao.delete(id);
	}

	public boolean batchDelXhCreditAudit(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhCreditAudit(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhCreditAudit(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 信审报告
		if (filter.containsKey("creditAuditReport")) {
			value = String.valueOf(filter.get("creditAuditReport"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_AUDIT_REPORT = '" + value + "'";
			}
		}
		// 拒贷原因
		if (filter.containsKey("creditRefuseReason")) {
			value = String.valueOf(filter.get("creditRefuseReason"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_REFUSE_REASON = '" + value + "'";
			}
		}
		// 外访费
		if (filter.containsKey("outVisitFee")) {
			value = String.valueOf(filter.get("outVisitFee"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OUT_VISIT_FEE = '" + value + "'";
			}
		}
		// 综合费率
		if (filter.containsKey("creditAllRate")) {
			value = String.valueOf(filter.get("creditAllRate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_ALL_RATE = '" + value + "'";
			}
		}
		// 授信期限
		if (filter.containsKey("creditMonth")) {
			value = String.valueOf(filter.get("creditMonth"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_MONTH = '" + value + "'";
			}
		}
		// 授信金额
		if (filter.containsKey("creditAmount")) {
			value = String.valueOf(filter.get("creditAmount"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_AMOUNT = '" + value + "'";
			}
		}
		// 信审状态：0信审未结束 1信审结束
		if (filter.containsKey("creditState")) {
			value = String.valueOf(filter.get("creditState"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_STATE = '" + value + "'";
			}
		}
		// 信审结果：1通过、0拒绝
		if (filter.containsKey("creditResult")) {
			value = String.valueOf(filter.get("creditResult"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_RESULT = '" + value + "'";
			}
		}
		// 信审类型：初审、复审、终审
		if (filter.containsKey("creditType")) {
			value = String.valueOf(filter.get("creditType"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_TYPE = '" + value + "'";
			}
		}
		// 借款申请ID 外键
		if (filter.containsKey("loanApplyId")) {
			value = String.valueOf(filter.get("loanApplyId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_APPLY_ID = '" + value + "'";
			}
		}
		// 主键ID
		if (filter.containsKey("id")) {
			value = String.valueOf(filter.get("id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = '" + value + "'";
			}
		}

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		logger.debug("searchXhCreditAudit():" + sql);

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	/**
	 * @param id
	 * @param state
	 *            0:信审流程未结束 1：信审流程结束
	 */
	public List getXhCreditAudit(Long id, int state) {
		String sql = "select * from XhCreditAudit a where 1=1 and a.loandApplyId= "
				+ id;
		sql = " and a.CREDIT_STATE=" + state;
		return jdbcDao.searchByMergeSql(sql);

	}

	@Transactional(readOnly = true)
	public String searchXhCreditAuditTongGuo(Map<String, Object> filter) {
		String result = "0";
		String sql = "select tong/allcount*100 as TGL from ( ";
		String sql1 = " select count(*) as tong from XH_CREDIT_AUDIT t where t.create_by ='"
				+ filter.get("createBy")
				+ "' and t.credit_type='1' and t.credit_result = '1' ";
		String sql2 = " select count(*) as allcount from XH_CREDIT_AUDIT t where t.create_by ='"
				+ filter.get("createBy") + "' and t.credit_type='1'";
		String value = "";
		// 信审报告
		if (filter.containsKey("firstAuditTime")) {
			value = String.valueOf(filter.get("firstAuditTime"));
			if (StringUtils.isNotEmpty(value)) {
				sql1 = sql1 + " and to_char(t.create_time,'yyyy-MM') = '"
						+ value + "'";
				sql2 = sql2 + " and to_char(t.create_time,'yyyy-MM') = '"
						+ value + "'";
			}
		}
		System.out.println("sql2====>" + sql2);
		List<Map<String, Object>> allList = jdbcDao.searchByMergeSql(sql2);
		if (Integer.parseInt(allList.get(0).get("ALLCOUNT") + "") > 0) {
			sql = sql + sql1 + "),(" + sql2 + ")";
			System.out.println("sql====>" + sql);
			List<Map<String, Object>> TGL = jdbcDao.searchByMergeSql(sql);
			if (TGL.size() == 1) {
				result = TGL.get(0).get("TGL") + "";
			}
		}
		return result;
	}

	public Long getNumsOfPassAduit() {
		return xhCreditAuditDao.getNumsOfPassAduit();
	}

	/**
	 * 查询出满足提交的信审记录
	 * 
	 * @param map
	 * @return
	 */
	public List<XhCreditAudit> getAllXhCreditAudit(Map map) {
		// 加入page完全是为了使用现有的方法而已
		Page<XhCreditAudit> page = new Page<XhCreditAudit>();
		page.setPageNo(1);
		page.setPageSize(1000);
		page.setOrder("desc");
		page.setOrderBy("createTime");
		Page<XhCreditAudit> result = this.searchXhCreditAudit(page, map);
		return result == null ? null : result.getResult();
	}

	/**
	 * 
	 * 设置进件状态为等待外访
	 * 
	 * @param id
	 */
	public void waitingVisitResult(Long id) {
		XhJksq jksq = xhJksqDao.get(id);
		jksq.setState("31.A");
		// XhJksqState state = new XhJksqState();
		// state.setXhjksq(jksq);
		// mess = "等待外访结果";
		// remark = "待外访";
		baseInfoManager.saveXhJksqState(jksq, "等待外访结果", "待外访");
	}

	public String[] retrieveCreditAudit(Long jksqId) {
		String[] res = new String[] { "200", "撤回成功！" };
		XhJksq xhJksq = xhJksqDao.get(jksqId);
		List<XhCreditTaskAssign> listTask = xhCreditTaskAssignDao
				.getXhCreditTaskAssignByJksqId(xhJksq.getId());
		if (listTask != null && listTask.size() <= 1) {
			xhJksq.setState("30");
			xhJksqDao.save(xhJksq);
			xhCreditTaskAssignDao.delete(listTask.get(0).getId());
			String[] log = Constants.getAttrDesByValue("JKSQ_STATE_LOG", "100");
			baseInfoManager.saveXhJksqState(xhJksq, log[1], log[0]);
		} else {
			res[0] = "300";
			res[1] = "数据异常！";
		}
		return res;
	}

	/**
	 * 信审人员数据决策方法 全部信审人员 or 指定人员 MDY 2013-08-16
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getAuditPersonKpiControl(
			Map<String, Object> map) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapKey = new HashMap<String, Object>();
		String auditPersonName = super.getMapValue(map, "auditPersonName");// 信审人员
		if (auditPersonName != null && !"全部初审人员".equals(auditPersonName)) {
			mapKey = getAuditPersonKpi(map);
			listMap.add(mapKey);
		} else {
			List<Employee> allAuditPersons = baseInfoManager
					.findEmpByPosId(Long.parseLong("4"));
			for (Employee e : allAuditPersons) {
				map.put("auditPersonName", e.getName());
				mapKey = getAuditPersonKpi(map);
				listMap.add(mapKey);
			}
			map.put("auditPersonName", "全部初审人员");
		}
		Collections.sort(listMap, new MapComparatorTotalNo());
		return listMap;
	}

	/**
	 * 信审KPI计算方法
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getAuditPersonKpi(Map<String, Object> map) {
		String auditPersonName = super.getMapValue(map, "auditPersonName");// 信审人员
		String createTime = super.getMapValue(map, "createTime");// 月份
		String auditMoney = super.getMapValue(map, "auditMoney");// 标准审批额度
		String auditNumber = super.getMapValue(map, "auditNumber");// 目标审核量
		String overdue = super.getMapValue(map, "overdue");// 逾期率
		String risks = super.getMapValue(map, "risks");// 风险率
		String auditCheckup = super.getMapValue(map, "auditCheckup");// 个人行为
		String auditProject = super.getMapValue(map, "auditProject");// 项目考核
		String projectWeight = super.getMapValue(map, "projectWeight");// 项目考核权重
		double moneyFinish = getMoneyFinish(auditPersonName, createTime,
				auditMoney);
		Integer targetCount = getAuditTargetCount(auditPersonName, createTime);
		Integer totalCount = getAuditCount(auditPersonName);
		double auditTarget = getAuditTarget(targetCount, auditNumber);
		double timeFinish = getTimeFinish(auditPersonName, createTime,
				totalCount);
		double auditCheckupInfo = getAuditCheckup(new Integer(auditCheckup));
		double auditProjectInfo = getAuditProject(new Integer(auditProject),
				Double.valueOf(projectWeight));
		double KPI = moneyFinish + auditTarget + timeFinish
				+ Double.valueOf(overdue) + Double.valueOf(risks);
		double total = KPI + auditCheckupInfo + auditProjectInfo;

		Map<String, Object> mapKey = new HashMap<String, Object>();
		mapKey.put("auditPersonName", auditPersonName);
		mapKey.put("createTime", createTime);
		mapKey.put("moneyFinish", moneyFinish);
		mapKey.put("auditTarget", auditTarget);
		mapKey.put("timeFinish", timeFinish);
		mapKey.put("KPI", KPI);
		mapKey.put("overdue", overdue);
		mapKey.put("risks", risks);
		mapKey.put("auditCheckup", auditCheckupInfo);
		mapKey.put("auditProject", auditProjectInfo);
		mapKey.put("total", total);
		return mapKey;
	}

	/**
	 * 项目考核
	 * 
	 * @return填写的分数/100*5%-10%(可选择)*100
	 */
	private double getAuditProject(int auditProject, double projectWeight) {
		double res = 0;
		if (auditProject != 0) {
			res = Double.valueOf(auditProject) / 100 * projectWeight * 100;
		}
		return res;
	}

	/**
	 * 个人行为鉴定总计
	 * 
	 * @return个人行为鉴定总计=（100-扣减分数）/100*20%*100
	 */
	private double getAuditCheckup(int auditCheckup) {
		double res = 0;
		if (auditCheckup != 0) {
			res = Double.valueOf(100 - auditCheckup) / 100 * 0.2 * 100;
		}
		return res;
	}

	/**
	 * 审批金额完成率
	 * 
	 * @param map
	 * @return 审批金额=审批金额完成率=当月审批通过金额/标准审批额度(150万)*100%*20%*80%*100
	 */
	private double getMoneyFinish(String auditPersonName, String createTime,
			String auditMoneyInfo) {
		String sql = "select nvl(sum(x.credit_amount),'0') as credit_amount from xh_credit_audit x where 1 = 1";
		double auditMoney = 0;
		if (StringUtils.isNotEmpty(auditPersonName)) {
			sql = sql + " and x.create_by = '" + auditPersonName + "'";
		}
		if (StringUtils.isNotEmpty(createTime)) {
			sql = sql + " and  to_char(x.create_time, 'YYYY-MM') = '"
					+ createTime + "'";
		}
		if (StringUtils.isNotEmpty(auditMoneyInfo)) {
			auditMoney = Double.valueOf(auditMoneyInfo);
		}
		sql = sql
				+ " and x.credit_state = '0' and x.credit_result = '1' and x.credit_type = '1'";
		List<Map<String, Object>> listXreditAmount = jdbcDao
				.searchByMergeSql(sql);

		String creditAmount = listXreditAmount.get(0).get("CREDIT_AMOUNT")
				.toString();
		// creditAmount = "2000000";//参考数据
		return (Double.valueOf(creditAmount) / auditMoney) * 1 * 0.2 * 0.8
				* 100;
	}

	/**
	 * 当月审核件数
	 * 
	 * @param map
	 * @return
	 */
	private Integer getAuditTargetCount(String auditPersonName,
			String createTime) {
		String sql = "select count(x.id) as count from  xh_credit_audit x where 1 = 1";
		if (StringUtils.isNotEmpty(auditPersonName)) {
			sql = sql + " and x.create_by = '" + auditPersonName + "'";
		}
		if (StringUtils.isNotEmpty(createTime)) {
			sql = sql + " and  to_char(x.create_time, 'YYYY-MM') = '"
					+ createTime + "'";
		}
		sql = sql + " and x.credit_type = '1'";
		List<Map<String, Object>> listXreditAmount = jdbcDao
				.searchByMergeSql(sql);

		String count = listXreditAmount.get(0).get("COUNT").toString();
		// count = "90";//参考数据
		return new Integer(count);
	}

	/**
	 * 审核总件数
	 * 
	 * @param map
	 * @return
	 */
	private Integer getAuditCount(String auditPersonName) {
		String sql = "select count(x.id) as count from  xh_credit_audit x where 1 = 1";
		if (StringUtils.isNotEmpty(auditPersonName)) {
			sql = sql + " and x.create_by = '" + auditPersonName + "'";
		}
		sql = sql + " and x.credit_type = '1'";
		List<Map<String, Object>> listXreditAmount = jdbcDao
				.searchByMergeSql(sql);

		String count = listXreditAmount.get(0).get("COUNT").toString();
		// count = "90";//参考数据
		return new Integer(count);
	}

	/**
	 * 审核目标达成率
	 * 
	 * @param map
	 * @return审核目标达成率=当月审核件数/目标审核量(88件每月检讨)*100%*20%*80%*100
	 */
	private double getAuditTarget(Integer count, String auditNumber) {
		double targetFinish = 0;
		if (StringUtils.isNotEmpty(auditNumber)) {
			targetFinish = Double.valueOf(auditNumber);
		}
		return (Double.valueOf(count) / targetFinish) * 1 * 0.2 * 0.8 * 100;
	}

	/**
	 * 审批时效达成率
	 * 
	 * @param map
	 * @return审批时效=审批时效达成率=(审核总件数-未按时效件数)/审核总件数*100%*15%*80%*100
	 */
	private double getTimeFinish(String auditPersonName, String createTime,
			Integer count) {
		double timeFinish = 0;
		String sql = "";
		if (StringUtils.isNotEmpty(auditPersonName)) {
			sql = sql + " and x.create_by = '" + auditPersonName + "'";
		}
		if (StringUtils.isNotEmpty(createTime)) {
			sql = sql + " and  to_char(x.create_time, 'YYYY-MM') = '"
					+ createTime + "'";
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		List<Map<String, Object>> listTime = jdbcDao.searchByMergeSqlTemplate(
				"kpiTime", conditions);

		String timeCount = listTime.get(0).get("COUNT").toString();
		if (count != 0) {
			Integer countTor = count - new Integer(timeCount);
			// countTor = 80;//参考数据
			timeFinish = Double.valueOf(countTor) / Double.valueOf(count);
			timeFinish = timeFinish * 1 * 0.15 * 0.8 * 100;
		}
		return timeFinish;
	}

	public void getInitAuditInfoByJksqId(Long id) {
		System.out.println("server 进入");
		logger.info("解包开始");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listFile = jdbcDao
				.searchByMergeSql("select t.id, t.filename as OLDNAME, t.newfilename as FILENAME,t.filepath as PATH from XH_UPLOAD_FILES t  where t.main_id = "
						+ id
						+ " and t.file_owner = 'XH_JKSQ' and t.flag in ('外访', '授信')");
		String serverPath = InitSetupListener.filePath + "auditImgInfo\\";

		for (int i = 0; i < listFile.size(); i++) {
			Map<String, Object> map = listFile.get(i);
			String fileId = map.get("ID").toString();
			String fileName = map.get("FILENAME").toString();
			String fileNameOld = map.get("OLDNAME").toString();
			String zipfile = InitSetupListener.filePath
					+ map.get("PATH").toString() + "\\" + fileName;
			boolean isImg = isFileAuditImg(id, Long.valueOf(fileId));
			if (!isImg) {
				listMap = UnZip.unZip(listMap, zipfile, fileNameOld,
						serverPath, id, fileId);
			}
		}

		for (int i = 0; i < listMap.size(); i++) {
			Map<String, Object> map = listMap.get(i);
			String typeName = map.get("typeName").toString();
			String idNo = map.get("idNo").toString();
			String imgSrc = map.get("imgSrc").toString();
			String jksqId = map.get("jksqId").toString();
			String uploadId = map.get("uploadId").toString();
			AuditUploadImg AuditUploadImg = new AuditUploadImg();
			AuditUploadImg.setTypeName(typeName);
			AuditUploadImg.setIdNo(idNo);
			AuditUploadImg.setImgSrc(imgSrc);
			AuditUploadImg.setJksqId(Long.valueOf(jksqId));
			AuditUploadImg.setUploadId(Long.valueOf(uploadId));
			auditUploadImgDao.save(AuditUploadImg);
		}

		logger.info("解包结束");
	}

	public boolean isFileAuditImg(Long jksqId, Long uploadId) {
		boolean isImg = false;
		int count = jdbcDao
				.searchByMergeSqlCount("select count(t.id) as count from XH_AUDIT_IMG t where t.jksq_id = "
						+ jksqId + " and t.upload_id = " + uploadId);
		if (count > 0) {
			isImg = true;
		}
		return isImg;
	}

	public List<Map<String, Object>> listAuditImg(String jksqId,
			String typeName, JdbcPage page) {
		String sql1 = "";

		if (StringUtils.isNotEmpty(jksqId)) {
			sql1 = sql1 + " and t.jksq_id = " + Long.valueOf(jksqId) + "";
		}

		if (StringUtils.isNotEmpty(typeName)) {
			sql1 = sql1 + " and t.type_name = '" + typeName + "'";
		}

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql1", sql1.toString());
		return jdbcDao.searchPagesByMergeSqlTemplate("queryAuditImgList",
				conditions, page);
	}

	private XhJksqDao xhJksqDao;

	public XhJksqDao getXhJksqDao() {
		return xhJksqDao;
	}

	@Autowired
	public void setXhJksqDao(XhJksqDao xhJksqDao) {
		this.xhJksqDao = xhJksqDao;
	}
	
	/**
	 * 信审复议结果保存
	 * @param xhJkht
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveXhLoanFuYi(XhJkht xhJkht,XhCreditAudit xhCreditAudit, HttpServletRequest request) {
		
			XhJksq xhJksq = xhJkht.getXhJksq();
//			XhJksqState state = new XhJksqState();
			String mess = "";
			
			this.saveXhCreditAudit(xhCreditAudit, request);
			
			String opt = request.getParameter("opt");
			if("".equals(opt) || null == opt){
				xhJksq.setState("70.A");
				mess = "待复议,提交到信审审核";
				xhJkhtDao.save(xhJkht);
			}else if("1".equals(opt)){//结果变更
				xhJksq.setState("50.F");
				mess = "复议完成,待审核利率";
				xhJkhtDao.delete(xhJkht);
			}else if("0".equals(opt)){//维持原判
				xhJksq.setState("70.F");
				mess = "复议完成,待确定签署";
				xhJkhtDao.save(xhJkht);
			}
				xhJksqDao.save(xhJksq);
//				String fujgms = request.getParameter("fujgms");//复议结果描述
//				String yesOrNo = request.getParameter("yesOrNo");//是否变更审批结果
				baseInfoManager.saveXhJksqState(xhJksq,mess,mess);
//			}
			return true;
	}
	//查询xhjksqstate按jksqId
	public Page<XhJksqState> queryXhJksqState(Page<XhJksqState> page , Long jksqId){
		return this.xhJksqStateDao.queryXhJksqState2(page, jksqId);
	}

}
