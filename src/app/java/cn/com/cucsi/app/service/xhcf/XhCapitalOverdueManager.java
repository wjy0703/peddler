package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhCapitalOverdueDao;
import cn.com.cucsi.app.entity.xhcf.XhCapitalOverdue;
import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.web.util.ExcelOperate;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCapitalOverdueManager extends PublicService{

	private XhCapitalOverdueDao xhCapitalOverdueDao;
	
	private XhLoanerAccountManager xhLoanerAccountManager;
	
	private XhLoanerAccountInfoManager xhLoanerAccountInfoManager;
	
	private XhJksqManager xhJksqManager;
	
    private JdbcTemplate jdbcTemplate;
    
    private XhIpcConstractManager xhIpcConstractManager;

	@Autowired
	public void setXhIpcConstractManager(XhIpcConstractManager xhIpcConstractManager) {
		this.xhIpcConstractManager = xhIpcConstractManager;
	}
	
    @Autowired
	public void setXhJksqManager(XhJksqManager xhJksqManager) {
		this.xhJksqManager = xhJksqManager;
	}
	
	@Autowired
	public void setXhLoanerAccountInfoManager(
			XhLoanerAccountInfoManager xhLoanerAccountInfoManager) {
		this.xhLoanerAccountInfoManager = xhLoanerAccountInfoManager;
	}
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	public void setXhLoanerAccountManager(
			XhLoanerAccountManager xhLoanerAccountManager) {
		this.xhLoanerAccountManager = xhLoanerAccountManager;
	}
	@Autowired
	public void setXhCapitalOverdueDao(XhCapitalOverdueDao xhCapitalOverdueDao) {
		this.xhCapitalOverdueDao = xhCapitalOverdueDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCapitalOverdue> searchXhCapitalOverdue(final Page<XhCapitalOverdue> page, final Map<String,Object> filters) {
		return xhCapitalOverdueDao.queryXhCapitalOverdue(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCapitalOverdue getXhCapitalOverdue(Long id) {
		return xhCapitalOverdueDao.get(id);
	}

	public void saveXhCapitalOverdue(XhCapitalOverdue entity) {
		xhCapitalOverdueDao.save(entity);
	}
	
	public void saveOverdueDay(XhCapitalOverdue entity) {
		XhCapitalOverdue xhCapitalOverdue1 = getXhCapitalOverdue(entity.getId());
		double yhk = xhCapitalOverdue1.getLoanContract().getYhkje();
		String day = entity.getSpareField02();
		double dayNumber = Double.valueOf(day) / 30;
		Integer dayNumberInt = Integer.valueOf(day) / 30;
		String dayNumber1 = String.valueOf(dayNumber);
		if(dayNumber1.indexOf(".") > -1 && dayNumber > dayNumberInt){
			dayNumber1 = dayNumber1.substring(0, dayNumber1.lastIndexOf("."));
			dayNumber += 1;
		}
		if(dayNumber == 0){
			dayNumber = 1;
		}
		String dayNumber2 = String.valueOf(dayNumber);
		xhCapitalOverdue1.setSpareField02(day);
		xhCapitalOverdue1.setOverdueMoney(yhk * Integer.valueOf(dayNumber2.substring(0, dayNumber2.lastIndexOf("."))));
		xhCapitalOverdue1.setDamagesMoney(yhk * 0.1 * Integer.valueOf(dayNumber2.substring(0, dayNumber2.lastIndexOf("."))));
		double interest = (yhk * Double.valueOf(xhCapitalOverdue1.getSpareField01()) * Double.valueOf(entity.getSpareField02()) * 0.2) / 100;
		xhCapitalOverdue1.setPunishInterest(interest);
		xhCapitalOverdueDao.save(xhCapitalOverdue1);
	}
	
	public void setOverdueStateInfo(Long Id, String state) {
		XhCapitalOverdue xhCapitalOverdue = getXhCapitalOverdue(Id);
		xhCapitalOverdue.setOverdueStatr(state);
//		if("1".equals(state)){
//			setACCInfo(xhCapitalOverdue);
//		}
		saveXhCapitalOverdue(xhCapitalOverdue);
	}
	
	public Integer setOverdueBuyer(XhCapitalOverdue xhCapitalOverdue) {
		Integer res = 1;//0:成功 1：逾期状态，挂账金额负数为追回逾期费用 2：追回状态，挂账金额正数为追回逾期费用
		XhCapitalOverdue xhCapitalOverdue1 = getXhCapitalOverdue(xhCapitalOverdue.getId());
		String state = xhCapitalOverdue.getOverdueStatr();
		String stateOld = xhCapitalOverdue1.getOverdueStatr();
		//if("1".equals(stateOld) && "1".equals(state)){
			if(state.equals("5")){
				xhCapitalOverdue1.setSpareField04(xhCapitalOverdue.getSpareField04());
				xhCapitalOverdue1.setSpareField05(xhCapitalOverdue.getSpareField05());
				res = 0;
			}else if(state.equals("4")){
				if("1".equals(stateOld)){
					if(Double.valueOf(xhCapitalOverdue.getSpareField03()) >= 0.0){
						xhCapitalOverdue1.setSpareField03(xhCapitalOverdue.getSpareField03());
						double moneySpare0 = 0;
						if(Double.valueOf(xhCapitalOverdue1.getSpareField06()) > xhCapitalOverdue1.getOverdueMoney()){
							moneySpare0 = Double.valueOf(xhCapitalOverdue1.getSpareField06())-xhCapitalOverdue1.getOverdueMoney();
						}
						double moneySpare = (xhCapitalOverdue1.getDamagesMoney()+xhCapitalOverdue1.getPunishInterest())-Double.valueOf(xhCapitalOverdue.getSpareField03())-moneySpare0;
						double moneySpare1 = Double.valueOf(xhCapitalOverdue1.getSpareField06())+moneySpare;
						xhCapitalOverdue1.setSpareField06(String.valueOf(moneySpare1));
						res = 0;
					}else{
						res = 2;
					}
				}else if("0".equals(stateOld)){
					if(Double.valueOf(xhCapitalOverdue.getSpareField03()) < 0){
						xhCapitalOverdue1.setSpareField03(xhCapitalOverdue.getSpareField03());
						res = 0;
					}else{
						res = 1;
					}
				}
			}else if(state.equals("1")){
				double moneyDo = 0;
				double spareField03 = 0;
				String monMoney = xhCapitalOverdue.getSpareField06();
				String monMoneyOld = xhCapitalOverdue1.getSpareField06();
				if(monMoneyOld == null){
					monMoneyOld = "0";
				}
				double moneyNew = Double.valueOf(monMoney) + Double.valueOf(monMoneyOld);
				double OverdueMoney = xhCapitalOverdue1.getOverdueMoney();
				double money = OverdueMoney;
				moneyDo = OverdueMoney;//xhCapitalOverdue1.getLoanContract().getYhkje();
				if(moneyNew < OverdueMoney){
					state = "0";
					money = Double.valueOf(monMoney);
					moneyDo = Double.valueOf(monMoney);
				}else{
					double allMoney = OverdueMoney+xhCapitalOverdue1.getDamagesMoney()+xhCapitalOverdue1.getPunishInterest()-1;
					double allMoney1 = OverdueMoney+xhCapitalOverdue1.getDamagesMoney()+xhCapitalOverdue1.getPunishInterest();
					if(Double.valueOf(xhCapitalOverdue1.getSpareField03()) < 0){
						moneyNew = moneyNew - Double.valueOf(xhCapitalOverdue1.getSpareField03());
					}
					if(moneyNew < allMoney){
						spareField03 = allMoney1 - moneyNew;
						xhCapitalOverdue1.setSpareField03(String.valueOf(spareField03));
					}else{
						xhCapitalOverdue1.setSpareField03("0");
						moneyDo = Double.valueOf(monMoney) - (xhCapitalOverdue1.getDamagesMoney()+xhCapitalOverdue1.getPunishInterest());
					}
				}
				if(state.equals("1") && stateOld.equals("0") && Double.valueOf(monMoneyOld) > 0){
						money = OverdueMoney - (moneyNew - Double.valueOf(monMoney));
						moneyDo = OverdueMoney - (moneyNew - Double.valueOf(monMoney));
				}
				if(state.equals("1")){
					XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager.getAccountByJksqId(xhCapitalOverdue1.getLenderId());
					xhLoanerAccount.setAccountState(0);
					xhLoanerAccountManager.saveXhLoanerAccount(xhLoanerAccount);
					setJksqState(xhLoanerAccount.getLoanContract().getId(), xhLoanerAccount.getLoanApply().getId(), xhLoanerAccount.getId(), Double.valueOf(xhCapitalOverdue.getSpareField06()));
			}
				xhCapitalOverdue1.setSpareField06(String.valueOf(moneyNew));
				xhCapitalOverdue1.setOverdueStatr(state);
				String time = getDateStr(xhCapitalOverdue1.getOverdueDate());
				setACCInfo(xhCapitalOverdue1.getLenderId(), money, moneyDo, time);
				res = 0;
			}
			if(res == 0){
				saveXhCapitalOverdue(xhCapitalOverdue1);
			}
		//}
		return res;
	}
	
	public String[] saveOffsetOverdue(XhCapitalOverdue xhCapitalOverdue) {
		String[] msg = new String[]{"200", "冲账成功"};
		XhCapitalOverdue xhCapitalOverdue1 = getXhCapitalOverdue(xhCapitalOverdue.getId());
		XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager.getAccountByJksqId(xhCapitalOverdue1.getLenderId());
		String time = getDateStr(xhCapitalOverdue1.getOverdueDate());
		String hkr = xhLoanerAccount.getLoanContract().getHkr();
		double money = xhLoanerAccount.getLoanContract().getYhkje();
		double monMoney = Double.valueOf(xhCapitalOverdue.getSpareField06());
		Integer periods = Integer.valueOf(xhCapitalOverdue.getSpareField05());
		money = money * Double.valueOf(periods);
		if(monMoney == money){
			xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, Double.valueOf(xhCapitalOverdue.getSpareField06()), 2, time);
			xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, Double.valueOf(xhCapitalOverdue.getSpareField06()), 1, time);
		}else{
			if(monMoney > money-1){
				double bal = monMoney - money;
				xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, money, 2, time);
				xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, money, 1, time);
				xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, bal, 2, time);
				xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, bal, 1, time);
				xhCapitalOverdue1.setSpareField06(String.valueOf(bal));
			}else{
				msg[0] = "300";
				BigDecimal msgMoney = new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal msgMonMoney = new BigDecimal(monMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal msgDvMoney = new BigDecimal(money-monMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
				msg[1] = "冲抵失败，"+periods+"期期供为"+msgMoney+"元,冲抵金额为"+msgMonMoney+"元,相差"+msgDvMoney+"元!";
			}
		}
		if("200".equals(msg[0])){
			Integer surplusPeriods = Integer.valueOf(xhCapitalOverdue1.getSpareField01()) - periods;
			xhCapitalOverdue1.setSpareField01(String.valueOf(surplusPeriods));
			String timeNew = getDateStrFomat(time, hkr);
			xhCapitalOverdue1.setOverdueDate(xhLoanerAccountInfoManager.getDateTime(timeNew));
			saveXhCapitalOverdue(xhCapitalOverdue1);
		}
		return msg;
	}
	
	public void initOverDay(Long overId){
		jdbcTemplate.execute("call CHP_DAY_CAPITAL_OVERDUE("+overId+")");
	}
	
	public void deleteOverdueSetAcc(XhCapitalOverdue xhCapitalOverdue) {
		XhCapitalOverdue xhCapitalOverdue1 = getXhCapitalOverdue(xhCapitalOverdue.getId());
		XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager.getAccountByJksqId(xhCapitalOverdue1.getLenderId());
		double moneyDo = xhCapitalOverdue1.getLoanContract().getYhkje();
		String time = xhCapitalOverdue.getSpareField05();
		xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, Double.valueOf(xhCapitalOverdue.getSpareField06()), 0, time);
		xhLoanerAccountInfoManager.setACCInfo1(xhLoanerAccount, moneyDo, 1, time);
		xhLoanerAccount.setAccountState(0);
		xhCapitalOverdueDao.delete(xhCapitalOverdue1);
		xhLoanerAccountManager.saveXhLoanerAccount(xhLoanerAccount);
		setJksqState(xhLoanerAccount.getLoanContract().getId(), xhLoanerAccount.getLoanApply().getId(), xhLoanerAccount.getId(), Double.valueOf(xhCapitalOverdue.getSpareField06()));
	}
	
	/**
	 * 逾期处理状态
	 * @param htId
	 * @param jksqId
	 * @param accId
	 * @param money
	 * @return
	 */
	public String setJksqState(Long htId, Long jksqId, Long accId, double money){
		String state = xhLoanerAccountInfoManager.getAccountInfoState(htId, money);
		if(StringUtils.isNotEmpty(state)){
			XhJksq jksq = xhJksqManager.getXhJksq(jksqId);
			jksq.setState(state);
			String type = jksq.getBackup01();
			if(type != null && "IPC".equals(type)){
				XhIpcConstract xhIpcConstract = xhIpcConstractManager.getXhIpcConstractByjksqId(jksq.getId());
				xhIpcConstract.setState(state);
				xhIpcConstractManager.saveXhIpcConstractMdy(xhIpcConstract);
			}
			if("102".equals(state)){
				XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager.getXhLoanerAccount(accId);
				xhLoanerAccount.setAccountState(1);
				xhLoanerAccountManager.saveXhLoanerAccount(xhLoanerAccount);
			}
			xhJksqManager.saveXhJksq(jksq);
		}
		return null;
	}
	
	public void setACCInfo(Long jksqId, double monMoney, double moneyDo, String time) {
		XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager.getAccountByJksqId(jksqId);
		if(monMoney > 0){
			xhLoanerAccountInfoManager.setACCInfo(xhLoanerAccount, moneyDo, 2);
			xhLoanerAccountInfoManager.setACCInfo(xhLoanerAccount, monMoney, 1);
		}
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCapitalOverdue(Long id) {
		xhCapitalOverdueDao.delete(id);
	}
	
	public boolean batchDelXhCapitalOverdue(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCapitalOverdue(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCapitalOverdue(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//主键
		if(filter.containsKey("id")){
			value = String.valueOf(filter.get("id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = '" +  value + "'";
			}
		}
		//银行名称
		if(filter.containsKey("bankName")){
			value = String.valueOf(filter.get("bankName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NAME = '" +  value + "'";
			}
		}
		//银行账号
		if(filter.containsKey("bankNumber")){
			value = String.valueOf(filter.get("bankNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NUMBER = '" +  value + "'";
			}
		}
		//银行开户行
		if(filter.containsKey("bankOpen")){
			value = String.valueOf(filter.get("bankOpen"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_OPEN = '" +  value + "'";
			}
		}
		//违约金
		if(filter.containsKey("damagesMoney")){
			value = String.valueOf(filter.get("damagesMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DAMAGES_MONEY = '" +  value + "'";
			}
		}
		//借款人ID
		if(filter.containsKey("lenderId")){
			value = String.valueOf(filter.get("lenderId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_ID = '" +  value + "'";
			}
		}
		//借款人身份证号
		if(filter.containsKey("lenderIdCard")){
			value = String.valueOf(filter.get("lenderIdCard"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_ID_CARD = '" +  value + "'";
			}
		}
		//借款人名称
		if(filter.containsKey("lenderName")){
			value = String.valueOf(filter.get("lenderName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_NAME = '" +  value + "'";
			}
		}
		//借款编号
		if(filter.containsKey("lenderNumber")){
			value = String.valueOf(filter.get("lenderNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_NUMBER = '" +  value + "'";
			}
		}
		//逾期时间
		if(filter.containsKey("overdueDate")){
			value = String.valueOf(filter.get("overdueDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OVERDUE_DATE = '" +  value + "'";
			}
		}
		//逾期金额
		if(filter.containsKey("overdueMoney")){
			value = String.valueOf(filter.get("overdueMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OVERDUE_MONEY = '" +  value + "'";
			}
		}
		//逾期状态
		if(filter.containsKey("overdueStatr")){
			value = String.valueOf(filter.get("overdueStatr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OVERDUE_STATR = '" +  value + "'";
			}
		}
		//罚息
		if(filter.containsKey("punishInterest")){
			value = String.valueOf(filter.get("punishInterest"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PUNISH_INTEREST = '" +  value + "'";
			}
		}
		//备用字段1
		if(filter.containsKey("spareField01")){
			value = String.valueOf(filter.get("spareField01"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SPARE_FIELD01 = '" +  value + "'";
			}
		}
		//备用字段2
		if(filter.containsKey("spareField02")){
			value = String.valueOf(filter.get("spareField02"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SPARE_FIELD02 = '" +  value + "'";
			}
		}
		//备用字段3
		if(filter.containsKey("spareField03")){
			value = String.valueOf(filter.get("spareField03"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SPARE_FIELD03 = '" +  value + "'";
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
	
	public void excelOver() throws Exception{
    	String NAME = "";
    	String MONEY = "";
    	String TIME = "";
    	String HKR = "";
    	File file = new File("C:\\cl11.xls");
        String[][] result = ExcelOperate.getData(file, 1);
        int rowLength = result.length;
        for(int i=0;i<rowLength;i++) {
            for(int j=0;j<result[i].length;j++) {
            	NAME = result[i][0];
            	MONEY = result[i][1];
            	TIME = result[i][2];
            	HKR = result[i][3];
            }
            jdbcTemplate.execute("call excelOver_TEST_OVER('"+NAME+"','"+MONEY+"','"+HKR+"','"+TIME+"')");
        }
    }
}
