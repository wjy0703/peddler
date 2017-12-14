package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.xhcf.XhHouseLoanApplyDao;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanApply;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhHouseLoanApplyManager {
	private BaseInfoManager baseInfoManager;
	private XhJksqManager xhjksqManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}

	private XhHouseLoanApplyDao xhHouseLoanApplyDao;
	private XhJksqDao xhJksqDao;

	@Autowired
	public void setXhJksqDao(XhJksqDao xhJksqDao) {
		this.xhJksqDao = xhJksqDao;
	}

	@Autowired
	public void setXhHouseLoanApplyDao(XhHouseLoanApplyDao xhHouseLoanApplyDao) {
		this.xhHouseLoanApplyDao = xhHouseLoanApplyDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhHouseLoanApply> searchXhHouseLoanApply(
			final Page<XhHouseLoanApply> page, final Map<String, Object> filters) {
		return xhHouseLoanApplyDao.queryXhHouseLoanApply(page, filters);
	}

	@Transactional(readOnly = true)
	public XhHouseLoanApply getXhHouseLoanApply(Long id) {
		return xhHouseLoanApplyDao.get(id);
	}

	public void saveXhHouseLoanApply(XhHouseLoanApply entity) {
		XhJksq jksq = null;
		
		if (entity.getLoanApply() != null) {
			jksq = entity.getLoanApply();
		} else {
			jksq = new XhJksq();
			entity.setLoanApply(jksq);
		}

		jksq.setJkrxm(entity.getLoanerName());
		jksq.setZjhm(entity.getLoanerIdNumber());
		jksq.setJkLoanQuota(entity.getLoanLoanAmount() + "");
		jksq.setJkCycle(entity.getLoanMonth() + "");
		jksq.setBackup01("HOUSE");
		jksq.setBankNum(entity.getBankNum());
		jksq.setBankOpen(entity.getBankOpen());
		jksq.setBankUsername(entity.getBankAccountName());
		if (null != entity.getBackMoneyType()
				&& !"".equals(entity.getBackMoneyType())) {
			if ("1".equals(entity.getBackMoneyType()))
				jksq.setHkWay("独立还款");
			else if ("2".equals(entity.getBackMoneyType()))
				jksq.setHkWay("家人协助共同还款");
			else if ("3".equals(entity.getBackMoneyType()))
				jksq.setHkWay("其他还款方式");
		}

		jksq.setJkType("F");
		if (null != entity.getLoanUse()
				&& !"".equals(entity.getLoanUse())) {
		if("1".equals(entity.getLoanUse())){
			entity.setLoanUse("经营");
		}
		else if("2".equals(entity.getLoanUse())){
			entity.setLoanUse("消费");
		}
		else if("3".equals(entity.getLoanUse())){
			entity.setLoanUse("周转");
		}
		}
		
		jksq.setJkUse(entity.getLoanUse());
		jksq.setZjhm(entity.getLoanerIdNumber());
		jksq.setState(entity.getLoanState());
		// jksq.setJkLoanQuota(entity.getLoanLoanAmount());
		// jksq.setJkCycle(entity.getLoanMonth());
		xhJksqDao.save(jksq);
		
		

		xhHouseLoanApplyDao.save(entity);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhHouseLoanApply(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 申请类型
		if (filter.containsKey("applyType")) {
			value = String.valueOf(filter.get("applyType"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.APPLY_TYPE = '" + value + "'";
			}
		}
		// 产权证号
		if (filter.containsKey("houseRightNum")) {
			value = String.valueOf(filter.get("houseRightNum"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_RIGHT_NUM = '" + value + "'";
			}
		}
		// 帐号
		if (filter.containsKey("bankNum")) {
			value = String.valueOf(filter.get("bankNum"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NUM = '" + value + "'";
			}
		}
		// 开户行
		if (filter.containsKey("bankOpen")) {
			value = String.valueOf(filter.get("bankOpen"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_OPEN = '" + value + "'";
			}
		}
		// 户名
		if (filter.containsKey("bankAccountName")) {
			value = String.valueOf(filter.get("bankAccountName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_ACCOUNT_NAME = '" + value + "'";
			}
		}
		// 工作单位
		if (filter.containsKey("companyAdress")) {
			value = String.valueOf(filter.get("companyAdress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMPANY_ADRESS = '" + value + "'";
			}
		}
		// 单位性质
		if (filter.containsKey("companyNature")) {
			value = String.valueOf(filter.get("companyNature"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMPANY_NATURE = '" + value + "'";
			}
		}
		// 单位电话
		if (filter.containsKey("companyPhone")) {
			value = String.valueOf(filter.get("companyPhone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMPANY_PHONE = '" + value + "'";
			}
		}
		// 还款方式
		if (filter.containsKey("backMoneyType")) {
			value = String.valueOf(filter.get("backMoneyType"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BACK_MONEY_TYPE = '" + value + "'";
			}
		}
		// 住址电话
		if (filter.containsKey("homePhone")) {
			value = String.valueOf(filter.get("homePhone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOME_PHONE = '" + value + "'";
			}
		}
		// 房屋座落
		if (filter.containsKey("houseAddress")) {
			value = String.valueOf(filter.get("houseAddress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_ADDRESS = '" + value + "'";
			}
		}
		// 房屋面积
		if (filter.containsKey("houseArea")) {
			value = String.valueOf(filter.get("houseArea"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_AREA = '" + value + "'";
			}
		}
		// 是否自营公司
		if (filter.containsKey("isOwnCompany")) {
			value = String.valueOf(filter.get("isOwnCompany"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.IS_OWN_COMPANY = '" + value + "'";
			}
		}
		// 借款期数
		if (filter.containsKey("loanMonth")) {
			value = String.valueOf(filter.get("loanMonth"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_MONTH = '" + value + "'";
			}
		}
		// 申请日期
		if (filter.containsKey("loanApplyDate")) {
			value = String.valueOf(filter.get("loanApplyDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_APPLY_DATE = '" + value + "'";
			}
		}
		// 借款金额
		if (filter.containsKey("loanLoanAmount")) {
			value = String.valueOf(filter.get("loanLoanAmount"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_LOAN_AMOUNT = '" + value + "'";
			}
		}
		// 借款用途
		if (filter.containsKey("loanUse")) {
			value = String.valueOf(filter.get("loanUse"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_USE = '" + value + "'";
			}
		}
		// 借款人户籍地址
		if (filter.containsKey("loanSrcAddress")) {
			value = String.valueOf(filter.get("loanSrcAddress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_SRC_ADDRESS = '" + value + "'";
			}
		}
		// 借款编号
		if (filter.containsKey("loanCode")) {
			value = String.valueOf(filter.get("loanCode"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_CODE = '" + value + "'";
			}
		}
		// 抵押价值
		if (filter.containsKey("mortgAount")) {
			value = String.valueOf(filter.get("mortgAount"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MORTG_AOUNT = '" + value + "'";
			}
		}
		// 老家宅电
		if (filter.containsKey("oldHomePhone")) {
			value = String.valueOf(filter.get("oldHomePhone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OLD_HOME_PHONE = '" + value + "'";
			}
		}
		// 产权价值
		if (filter.containsKey("houseRightValue")) {
			value = String.valueOf(filter.get("houseRightValue"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_RIGHT_VALUE = '" + value + "'";
			}
		}
		// 核定利率
		if (filter.containsKey("fixedRate")) {
			value = String.valueOf(filter.get("fixedRate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIXED_RATE = '" + value + "'";
			}
		}
		// 配偶地址
		if (filter.containsKey("spAdress")) {
			value = String.valueOf(filter.get("spAdress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_ADRESS = '" + value + "'";
			}
		}
		// 配偶年龄
		if (filter.containsKey("spAge")) {
			value = String.valueOf(filter.get("spAge"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_AGE = '" + value + "'";
			}
		}
		// 配偶年收入
		if (filter.containsKey("spIncome")) {
			value = String.valueOf(filter.get("spIncome"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_INCOME = '" + value + "'";
			}
		}
		// 配偶工作单位
		if (filter.containsKey("spComp")) {
			value = String.valueOf(filter.get("spComp"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_COMP = '" + value + "'";
			}
		}
		// 配偶工作单位地址
		if (filter.containsKey("spCompAdress")) {
			value = String.valueOf(filter.get("spCompAdress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_COMP_ADRESS = '" + value + "'";
			}
		}
		// 配偶工作单位电话
		if (filter.containsKey("spCompPhone")) {
			value = String.valueOf(filter.get("spCompPhone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_COMP_PHONE = '" + value + "'";
			}
		}
		// 配偶职务
		if (filter.containsKey("spDep")) {
			value = String.valueOf(filter.get("spDep"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_DEP = '" + value + "'";
			}
		}
		// 配偶性别
		if (filter.containsKey("spSex")) {
			value = String.valueOf(filter.get("spSex"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_SEX = '" + value + "'";
			}
		}
		// 配偶家庭电话
		if (filter.containsKey("spHomePhone")) {
			value = String.valueOf(filter.get("spHomePhone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_HOME_PHONE = '" + value + "'";
			}
		}
		// 配偶姓名
		if (filter.containsKey("spName")) {
			value = String.valueOf(filter.get("spName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_NAME = '" + value + "'";
			}
		}
		// 配偶手机
		if (filter.containsKey("spTelephone")) {
			value = String.valueOf(filter.get("spTelephone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_TELEPHONE = '" + value + "'";
			}
		}
		// 配偶工作年限
		if (filter.containsKey("spWorkLimit")) {
			value = String.valueOf(filter.get("spWorkLimit"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_WORK_LIMIT = '" + value + "'";
			}
		}
		// 配偶身份证号
		if (filter.containsKey("spIdNum")) {
			value = String.valueOf(filter.get("spIdNum"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SP_ID_NUM = '" + value + "'";
			}
		}
		// 借款状态
		if (filter.containsKey("loanState")) {
			value = String.valueOf(filter.get("loanState"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_STATE = '" + value + "'";
			}
		}
		// 手机
		if (filter.containsKey("telephone")) {
			value = String.valueOf(filter.get("telephone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TELEPHONE = '" + value + "'";
			}
		}
		// 组织机构
		if (filter.containsKey("organiId")) {
			value = String.valueOf(filter.get("organiId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ORGANI_ID = '" + value + "'";
			}
		}
		// 区域
		if (filter.containsKey("area")) {
			value = String.valueOf(filter.get("area"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AREA = '" + value + "'";
			}
		}
		// 城市
		if (filter.containsKey("city")) {
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" + value + "'";
			}
		}
		// 省份
		if (filter.containsKey("province")) {
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" + value + "'";
			}
		}
		// 借款人姓名
		if (filter.containsKey("loanerName")) {
			value = String.valueOf(filter.get("loanerName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOANER_NAME = '" + value + "'";
			}
		}
		// 借款人性别
		if (filter.containsKey("loanerSex")) {
			value = String.valueOf(filter.get("loanerSex"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOANER_SEX = '" + value + "'";
			}
		}
		// 借款人身份证号
		if (filter.containsKey("loanerIdNumber")) {
			value = String.valueOf(filter.get("loanerIdNumber"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOANER_ID_NUMBER = '" + value + "'";
			}
		}
		// 借款人年龄
		if (filter.containsKey("loanerAge")) {
			value = String.valueOf(filter.get("loanerAge"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOANER_AGE = '" + value + "'";
			}
		}
		// 产权人姓名
		if (filter.containsKey("houseOwnerName")) {
			value = String.valueOf(filter.get("houseOwnerName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_OWNER_NAME = '" + value + "'";
			}
		}
		// 产权人身份证号
		if (filter.containsKey("houseOwnerIdNum")) {
			value = String.valueOf(filter.get("houseOwnerIdNum"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_OWNER_ID_NUM = '" + value + "'";
			}
		}
		// 产权人年龄
		if (filter.containsKey("houseOwnerAge")) {
			value = String.valueOf(filter.get("houseOwnerAge"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_OWNER_AGE = '" + value + "'";
			}
		}
		// 共有权人姓名
		if (filter.containsKey("houseJointName")) {
			value = String.valueOf(filter.get("houseJointName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_JOINT_NAME = '" + value + "'";
			}
		}
		// 共有权人性别
		if (filter.containsKey("houseJointSex")) {
			value = String.valueOf(filter.get("houseJointSex"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_JOINT_SEX = '" + value + "'";
			}
		}
		// 共有权人身份证号
		if (filter.containsKey("houseJointIdNum")) {
			value = String.valueOf(filter.get("houseJointIdNum"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_JOINT_ID_NUM = '" + value + "'";
			}
		}
		// 共有权人年龄
		if (filter.containsKey("houseJointAge")) {
			value = String.valueOf(filter.get("houseJointAge"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_JOINT_AGE = '" + value + "'";
			}
		}
		// 婚姻状况
		if (filter.containsKey("marital")) {
			value = String.valueOf(filter.get("marital"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MARITAL = '" + value + "'";
			}
		}
		// 有无子女
		if (filter.containsKey("hasChild")) {
			value = String.valueOf(filter.get("hasChild"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HAS_CHILD = '" + value + "'";
			}
		}
		// 产权人性别
		if (filter.containsKey("houseOwnerSex")) {
			value = String.valueOf(filter.get("houseOwnerSex"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_OWNER_SEX = '" + value + "'";
			}
		}
		// 亲属姓名
		if (filter.containsKey("firstLxrName")) {
			value = String.valueOf(filter.get("firstLxrName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LXR_NAME = '" + value + "'";
			}
		}
		// 亲属与本人关系
		if (filter.containsKey("firstLxrRelation")) {
			value = String.valueOf(filter.get("firstLxrRelation"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LXR_RELATION = '" + value + "'";
			}
		}
		// 亲属单位
		if (filter.containsKey("firstLxrWorkUnit")) {
			value = String.valueOf(filter.get("firstLxrWorkUnit"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LXR_WORK_UNIT = '" + value + "'";
			}
		}
		// 亲属单位或家庭住址
		if (filter.containsKey("firstLxrAddress")) {
			value = String.valueOf(filter.get("firstLxrAddress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LXR_ADDRESS = '" + value + "'";
			}
		}
		// 朋友姓名
		if (filter.containsKey("secondLxrName")) {
			value = String.valueOf(filter.get("secondLxrName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SECOND_LXR_NAME = '" + value + "'";
			}
		}
		// 朋友与本人关系
		if (filter.containsKey("secondLxrRelation")) {
			value = String.valueOf(filter.get("secondLxrRelation"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SECOND_LXR_RELATION = '" + value + "'";
			}
		}
		// 朋友工作单位
		if (filter.containsKey("secondLxrWorkUnit")) {
			value = String.valueOf(filter.get("secondLxrWorkUnit"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SECOND_LXR_WORK_UNIT = '" + value + "'";
			}
		}
		// 朋友单位地址或家庭住址
		if (filter.containsKey("secondLxrAddress")) {
			value = String.valueOf(filter.get("secondLxrAddress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SECOND_LXR_ADDRESS = '" + value + "'";
			}
		}
		// 亲属联系电话
		if (filter.containsKey("firstLxrTelphone")) {
			value = String.valueOf(filter.get("firstLxrTelphone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LXR_TELPHONE = '" + value + "'";
			}
		}
		// 朋友联系电话
		if (filter.containsKey("secondLxrTelphone")) {
			value = String.valueOf(filter.get("secondLxrTelphone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SECOND_LXR_TELPHONE = '" + value + "'";
			}
		}
		// 同事姓名
		if (filter.containsKey("thirdLxrName")) {
			value = String.valueOf(filter.get("thirdLxrName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THIRD_LXR_NAME = '" + value + "'";
			}
		}
		// 同事与本人关系
		if (filter.containsKey("thirdLxrRelation")) {
			value = String.valueOf(filter.get("thirdLxrRelation"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THIRD_LXR_RELATION = '" + value + "'";
			}
		}
		// 同事单位
		if (filter.containsKey("thirdLxrWorkUnit")) {
			value = String.valueOf(filter.get("thirdLxrWorkUnit"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THIRD_LXR_WORK_UNIT = '" + value + "'";
			}
		}
		// 同事单位地址或家庭住址
		if (filter.containsKey("thirdLxrAddress")) {
			value = String.valueOf(filter.get("thirdLxrAddress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THIRD_LXR_ADDRESS = '" + value + "'";
			}
		}
		// 同事联系电话
		if (filter.containsKey("thirdLxrTelphone")) {
			value = String.valueOf(filter.get("thirdLxrTelphone"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THIRD_LXR_TELPHONE = '" + value + "'";
			}
		}
		// 放款金额
		if (filter.containsKey("makeLoanAmount")) {
			value = String.valueOf(filter.get("makeLoanAmount"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MAKE_LOAN_AMOUNT = '" + value + "'";
			}
		}

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	/**
	 * 上传后修改状态
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public boolean uploadFile(HttpServletRequest request,
			List<Map<String, String>> fileName) {
		boolean flag = false;
		String values = request.getParameter("id");
		String[] value = values.split(",");
		Long Id = Long.parseLong(value[0]);
		String upFlag = value[1];//
		String fileOwner = "";
		String state = "";

		if ("upMaterial".equals(upFlag.trim())) {
			fileOwner = "XH_HOUSE_LOAN_APPLY";
			state = "31";
		} else if ("upContract".equals(upFlag)) {
			fileOwner = "XH_HOUSE_LOAN_CONTRACT";
			state = "56";
		}
		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if (fileName.size() > 0) {
			for (Map<String, String> m : fileName) {
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner(fileOwner);
				xhUploadFiles.setMainId(Id);
				xhUploadFiles.setFlag(upFlag.trim());
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
				flag = true;
			}
		}
		if (flag) {
			XhHouseLoanApply xhHouseLoanApply = getXhHouseLoanApply(Id);
			if ("upMaterial".equals(upFlag.trim())) {
				xhHouseLoanApply.setLoanState(state);// 初审状态
				xhHouseLoanApplyDao.save(xhHouseLoanApply);
			} else if ("upContract".equals(upFlag)) {
				Long id = xhHouseLoanApply.getLoanApply().getId();
				flag = xhjksqManager.changeState(id, state);
			}
		}

		return flag;
	}
}
