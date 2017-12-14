package cn.com.cucsi.app.service.xhcf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.dao.baseinfo.MiddleManDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.xhcf.QdHouseInfoDao;
import cn.com.cucsi.app.dao.xhcf.XhAvailableValueOfClaimsDao;
import cn.com.cucsi.app.dao.xhcf.XhJkhtDao;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.QdHouseInfo;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class QdHouseInfoManager {

	private QdHouseInfoDao qdHouseInfoDao;
	@Autowired
	public void setQdHouseInfoDao(QdHouseInfoDao qdHouseInfoDao) {
		this.qdHouseInfoDao = qdHouseInfoDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	private CityDao cityDao;
	
	
	@Autowired
	private XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao;

	@Autowired
//	private XhJkhtManager xhJkhtManager;
	private XhJkhtDao xhJkhtDao;

	@Autowired
//	private XhJksqManager xhJksqManager;
	private XhJksqDao xhJksqDao;

	@Autowired
//	private BaseInfoManager baseInfoManager;	
	private MiddleManDao middleManDao;
	
	
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<QdHouseInfo> searchQdHouseInfo(final Page<QdHouseInfo> page, final Map<String,Object> filters) {
		return qdHouseInfoDao.queryQdHouseInfo(page, filters);
	}
	@Transactional(readOnly = true)
	public QdHouseInfo getQdHouseInfo(Long id) {
		return qdHouseInfoDao.get(id);
	}

	public void saveQdHouseInfo(QdHouseInfo entity) {
		qdHouseInfoDao.save(entity);
	}
	
	/**
	 * 保存数据，同时插入另外三张表
	 * @param entity
	 */
	public void saveQdHouseInfoWithRelatee(QdHouseInfo qdHouseInfo) {
		XhJksq jksq = null;
		XhJkht jkht = null;
		MiddleMan middleMan  =middleManDao.get(qdHouseInfo.getZqr()) ;//baseInfoManager.getMiddleMan(Long.valueOf(qdHouseInfo.getZqr()));
		XhAvailableValueOfClaims kyjq = null;
		changeProperties(qdHouseInfo);
		if("提交".equals(qdHouseInfo.getMeta1())){//提交数据，保存所有的关联表
			//以下两分支代码完全一样，后来加的需求，暂时未去掉，方便以后修改
			if (qdHouseInfo.getId() == null) {
				jksq = new XhJksq();
				jkht = new XhJkht();
				kyjq = new XhAvailableValueOfClaims();
				jkht.setMiddleMan(middleMan);
				copyProperties(qdHouseInfo, jksq, jkht,kyjq);
				xhJksqDao.save(jksq);
				jkht.setXhJksq(jksq);
				xhJkhtDao.save(jkht);
				kyjq.setLoanContract(jkht);
				kyjq.setXhJksq(jksq);
				kyjq.setMiddleMan(middleMan);
				xhAvailableValueOfClaimsDao.save(kyjq);
				qdHouseInfo.setJqhtid(jkht.getId());
				qdHouseInfo.setJqsqid(jksq.getId());
				qdHouseInfo.setKyzqid(kyjq.getId());
			} else {
				
//				jksq = xhJksqDao.get(qdHouseInfo.getJqsqid());
//				jkht = xhJkhtDao.get(qdHouseInfo.getJqhtid());
//				kyjq = xhAvailableValueOfClaimsDao.get(qdHouseInfo.getKyzqid());
				jksq = new XhJksq();
				jkht = new XhJkht();
				kyjq = new XhAvailableValueOfClaims();
				jkht.setMiddleMan(middleMan);
				copyProperties(qdHouseInfo, jksq, jkht,kyjq);
				xhJksqDao.save(jksq);
				jkht.setXhJksq(jksq);
				xhJkhtDao.save(jkht);
				kyjq.setLoanContract(jkht);
				kyjq.setXhJksq(jksq);
				kyjq.setMiddleMan(middleMan);
				
				xhAvailableValueOfClaimsDao.save(kyjq);
				qdHouseInfo.setJqhtid(jkht.getId());
				qdHouseInfo.setJqsqid(jksq.getId());
				qdHouseInfo.setKyzqid(kyjq.getId());
			}
		}
		qdHouseInfoDao.save(qdHouseInfo);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteQdHouseInfo(Long id) {
		qdHouseInfoDao.delete(id);
	}
	
	public boolean batchDelQdHouseInfo(String[] ids){
		
		try {
			for(String id: ids){
				deleteQdHouseInfo(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchQdHouseInfo(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//借款编号
		if(filter.containsKey("loanCode")){
			value = String.valueOf(filter.get("loanCode"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_CODE = '" +  value + "'";
			}
		}
		//借款人状态
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" +  value + "'";
			}
		}
		//借款人姓名
		if(filter.containsKey("jkrxm")){
			value = String.valueOf(filter.get("jkrxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JKRXM = '" +  value + "'";
			}
		}
		//借款人借款用途
		if(filter.containsKey("jkUse")){
			value = String.valueOf(filter.get("jkUse"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_USE = '" +  value + "'";
			}
		}
		//城市
		if(filter.containsKey("city")){
			value = String.valueOf(filter.get("city"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" +  value + "'";
			}
		}
		//借款方式
		if(filter.containsKey("jkType")){
			value = String.valueOf(filter.get("jkType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JK_TYPE = '" +  value + "'";
			}
		}
		//债权人
		if(filter.containsKey("zqr")){
			value = String.valueOf(filter.get("zqr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQR = '" +  value + "'";
			}
		}
		//初始借款金额
		if(filter.containsKey("htje")){
			value = String.valueOf(filter.get("htje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HTJE = '" +  value + "'";
			}
		}
		//月本
		if(filter.containsKey("ybjje")){
			value = String.valueOf(filter.get("ybjje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YBJJE = '" +  value + "'";
			}
		}
		//月息
		if(filter.containsKey("ylxje")){
			value = String.valueOf(filter.get("ylxje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YLXJE = '" +  value + "'";
			}
		}
		//月帐号管理费
		if(filter.containsKey("zhglf")){
			value = String.valueOf(filter.get("zhglf"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZHGLF = '" +  value + "'";
			}
		}
		//月还款金额
		if(filter.containsKey("yhkje")){
			value = String.valueOf(filter.get("yhkje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YHKJE = '" +  value + "'";
			}
		}
		//初始借款时间
		if(filter.containsKey("qdrq")){
			value = String.valueOf(filter.get("qdrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QDRQ = '" +  value + "'";
			}
		}
		//还款期限
		if(filter.containsKey("hkqs")){
			value = String.valueOf(filter.get("hkqs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKQS = '" +  value + "'";
			}
		}
		//起始还款日期
		if(filter.containsKey("qshkrq")){
			value = String.valueOf(filter.get("qshkrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QSHKRQ = '" +  value + "'";
			}
		}
		//贷款利息
		if(filter.containsKey("dkll")){
			value = String.valueOf(filter.get("dkll"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DKLL = '" +  value + "'";
			}
		}
		//no还款截止日期
		if(filter.containsKey("returnrq")){
			value = String.valueOf(filter.get("returnrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.RETURNRQ = '" +  value + "'";
			}
		}
		//no借款人职业情况
		if(filter.containsKey("personWorkCondition")){
			value = String.valueOf(filter.get("personWorkCondition"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PERSON_WORK_CONDITION = '" +  value + "'";
			}
		}
		//证件号码
		if(filter.containsKey("zjhm")){
			value = String.valueOf(filter.get("zjhm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZJHM = '" +  value + "'";
			}
		}
		//借款申请ID
		if(filter.containsKey("jqsqid")){
			value = String.valueOf(filter.get("jqsqid"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JQSQID = '" +  value + "'";
			}
		}
		//借款合同ID
		if(filter.containsKey("jqhtid")){
			value = String.valueOf(filter.get("jqhtid"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JQHTID = '" +  value + "'";
			}
		}
		//可用债权ID
		if(filter.containsKey("kyzqid")){
			value = String.valueOf(filter.get("kyzqid"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.KYZQID = '" +  value + "'";
			}
		}
		//备用一个
		if(filter.containsKey("meta1")){
			value = String.valueOf(filter.get("meta1"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.META1 = '" +  value + "'";
			}
		}
		//省份
		if(filter.containsKey("province")){
			value = String.valueOf(filter.get("province"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" +  value + "'";
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
    * 拷贝数据
    * @param qdHouseInfo
    * @param jksq
    * @param jkht
    * @param kyjq
    */
	private void copyProperties(QdHouseInfo qdHouseInfo, XhJksq jksq,
			XhJkht jkht, XhAvailableValueOfClaims kyjq) {
		String returnRq =  CreditHarmonyComputeUtilties.getLastDateOfBackMoney(qdHouseInfo.getQshkrq(),qdHouseInfo.getHkqs().intValue());
		qdHouseInfo.setReturnrq(returnRq);
		jksq.setLoanCode(qdHouseInfo.getLoanCode());
		jksq.setBackup01("QINGDAO");
		//jksq.setState(qdHouseInfo.getState());
		jksq.setJkrxm(qdHouseInfo.getJkrxm());//姓名
		jksq.setZjhm(qdHouseInfo.getZjhm());//证件号码
		jksq.setJkUse(qdHouseInfo.getJkUse());//用途
		jksq.setCity(cityDao.get(qdHouseInfo.getCity()));//城市
		jksq.setProvince(cityDao.get(qdHouseInfo.getProvince()));//省份
		jksq.setJkType(qdHouseInfo.getJkType());//借款方式
		jksq.setJkCycle(qdHouseInfo.getHkqs().toString());//期数
		jksq.setJkLoanQuota(qdHouseInfo.getHtje().toString()); //合同金额
		jksq.setState("61");//状态

		jkht.setHtje(qdHouseInfo.getHtje());
		jkht.setYbjje(qdHouseInfo.getYbjje());
		jkht.setYlxje(qdHouseInfo.getYlxje());
		jkht.setZhglf(qdHouseInfo.getZhglf());
		jkht.setYhkje(qdHouseInfo.getYhkje());
		jkht.setQdrq(qdHouseInfo.getQdrq());
		jkht.setHkqs(qdHouseInfo.getHkqs().intValue());
		jkht.setDkll(qdHouseInfo.getDkll());
		jkht.setQshkrq(qdHouseInfo.getQshkrq());
		jkht.setState("2");//借款合同改为2  
		
		kyjq.setJkbh(qdHouseInfo.getLoanCode());//借款编号
		kyjq.setJkje(qdHouseInfo.getHtje());//sure
		kyjq.setZjrcybl(Double.parseDouble(qdHouseInfo.getZqr().toString()));
		kyjq.setZjrcybl(Double.parseDouble("100"));
		kyjq.setSyqs(qdHouseInfo.getHkqs());//check
		kyjq.setSqhkrq(qdHouseInfo.getQshkrq());
		kyjq.setKyzqjz(qdHouseInfo.getHtje());
		kyjq.setMqhkrq(qdHouseInfo.getReturnrq());
		//TODO 00000
		
		//kyjq.setSyhkys(CreditHarmonyComputeUtilties.getRemainingMonths(qdHouseInfo.getQshkrq(),qdHouseInfo.getHkqs()));
		String hkr = qdHouseInfo.getQshkrq();
		if( hkr!= null){
			int index = hkr.lastIndexOf("-");
			if(index >= 0){
				hkr = hkr.substring(index + 1);
			}
		}		
		kyjq.setHkr(hkr);
		jkht.setHkr(hkr);
		
		Long remainingMonths = reKyzqjzSyqs(kyjq.getSqhkrq(), kyjq.getHkr(), kyjq.getSyqs());
		kyjq.setSyhkys(remainingMonths);// 剩余月数
		
		double dkll = qdHouseInfo.getDkll();
		double zqyjcyl = Math.pow(dkll/100+1, 12)-1;		
		kyjq.setZqyjcyl(zqyjcyl);

	}
	/**
	 * 可用债权剩余期数处理，根据首个还款日期和账单日，期数，计算剩余期数
	 * @param qshkrq
	 * @param zdr
	 * @return
	 */
	private Long reKyzqjzSyqs(String qshkrq,String zdr,Long loanMonths){
		Long value = loanMonths;
		//获取首个结算日期
		String sgsjjsrq = CreditHarmonyComputeUtilties.reKyzqjzSgjsrq(qshkrq, zdr);
		Date nowDate = new Date();
		
		Date newDate = CreditHarmonyComputeUtilties.StringToDate(sgsjjsrq, "yyyy-MM-dd");
		//判断当前日期是否大于首个结算日期
		if(nowDate.after(newDate)){
			//执行sql，计算剩余期数
			String sql = "select "+loanMonths+"-(trunc( months_between(sysdate,to_date('"+sgsjjsrq+"','YYYY-MM-dd')))+1) as rel from dual";
			List<Map<String, Object>> searchTime = jdbcDao.searchByMergeSql(sql);
			if(searchTime.size()==1){
				String rel = "";
				if (searchTime.get(0).containsKey("REL")) {
					rel = String.valueOf(searchTime.get(0).get("REL"));
					if (StringUtils.isNotEmpty(rel)) {
						value = Long.parseLong(rel);
					}
				}
			}
		}
		return value;
	}
	private void changeProperties(QdHouseInfo qdHouseInfo) {
		String returnRq =  CreditHarmonyComputeUtilties.getLastDateOfBackMoney(qdHouseInfo.getQshkrq(),qdHouseInfo.getHkqs().intValue());
		qdHouseInfo.setReturnrq(returnRq);		
	}
}
