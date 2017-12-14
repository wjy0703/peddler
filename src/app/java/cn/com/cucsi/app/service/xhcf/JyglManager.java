package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.messageBox.MessageBoxDao;
import cn.com.cucsi.app.dao.xhcf.XhAvailableValueOfClaimsDao;
import cn.com.cucsi.app.dao.xhcf.XhLentmoneywaterDao;
import cn.com.cucsi.app.dao.xhcf.XhMadewordDao;
import cn.com.cucsi.app.dao.xhcf.XhSendemailDao;
import cn.com.cucsi.app.dao.xhcf.XhTzsqDao;
import cn.com.cucsi.app.dao.xhcf.XhTzsqStateDao;
import cn.com.cucsi.app.dao.xhcf.XhZqtjDao;
import cn.com.cucsi.app.dao.xhcf.XhZqtjDetailsDao;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhLentmoneywater;
import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.app.entity.xhcf.XhSendemail;
import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhTzsqState;
import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.app.entity.xhcf.XhZqtjDetails;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.Java2Excel;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class JyglManager {
	
	private XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao;
	@Autowired
	public void setXhAvailableValueOfClaimsDao(XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao) {
		this.xhAvailableValueOfClaimsDao = xhAvailableValueOfClaimsDao;
	}
	
	private XhMadewordDao xhMadewordDao;
	@Autowired
	public void setXhMadewordDao(XhMadewordDao xhMadewordDao) {
		this.xhMadewordDao = xhMadewordDao;
	}

	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	private XhZqtjDetailsDao xhZqtjDetailsDao;
	@Autowired
	public void setXhZqtjDetailsDao(XhZqtjDetailsDao xhZqtjDetailsDao) {
		this.xhZqtjDetailsDao = xhZqtjDetailsDao;
	}
	private XhZqtjDao xhZqtjDao;
	@Autowired
	public void setXhZqtjDao(XhZqtjDao xhZqtjDao) {
		this.xhZqtjDao = xhZqtjDao;
	}
	private XhLentmoneywaterDao xhLentmoneywaterDao;
	@Autowired
	public void setXhLentmoneywaterDao(XhLentmoneywaterDao xhLentmoneywaterDao) {
		this.xhLentmoneywaterDao = xhLentmoneywaterDao;
	}
	private XhTzsqDao xhTzsqDao;
	@Autowired
	public void setXhTzsqDao(XhTzsqDao xhTzsqDao) {
		this.xhTzsqDao = xhTzsqDao;
	}
	
	private XhSendemailDao xhSendemailDao;

	@Autowired
	public void setXhSendemailDao(XhSendemailDao xhSendemailDao) {
		this.xhSendemailDao = xhSendemailDao;
	}
	
	private XhTzsqStateDao xhTzsqStateDao;
	@Autowired
	public void setXhTzsqStateDao(XhTzsqStateDao xhTzsqStateDao) {
		this.xhTzsqStateDao = xhTzsqStateDao;
	}
	@Transactional(readOnly = true)
	public XhTzsq getXhTzsq(Long id) {
		return xhTzsqDao.get(id);
	}
	
	public void saveXhTzsq(XhTzsq entity){
		xhTzsqDao.save(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<XhAvailableValueOfClaims> queryXhAvailableValueOfClaims(Page<XhAvailableValueOfClaims> page, Map<String, Object> params){
		return xhAvailableValueOfClaimsDao.queryXhAvailableValueOfClaims(page, params);
	}
	
	@Transactional(readOnly = true)
	public String getKyzqjzall(Map<String, Object> params){
		return xhAvailableValueOfClaimsDao.getKyzqjzall(params);
	}
	
	@Transactional(readOnly = true)
	public List<XhAvailableValueOfClaims> queryXhAvailableValueOfClaims(Map<String, Object> params){
		return xhAvailableValueOfClaimsDao.queryXhAvailableValueOfClaims(params);
	}
	@Transactional(readOnly = true)
	public XhAvailableValueOfClaims getXhAvailableValueOfClaims(Long id){
		return xhAvailableValueOfClaimsDao.get(id);
	}
	public void saveXhZqtj(XhZqtj entity){
		xhZqtjDao.save(entity);
	}
	@Transactional(readOnly = true)
	public XhZqtj getXhZqtj(Long id){
		return xhZqtjDao.get(id);
	}
	
	public void deleteXhZqtj(Long id){
		xhZqtjDao.delete(id);
	}
	
	public void saveXhZqtjDetails(XhZqtjDetails entity){
		xhZqtjDetailsDao.save(entity);
	}
	public void deleteXhZqtjDetails(Long id){
		xhZqtjDetailsDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public XhZqtjDetails getXhZqtjDetails(Long id){
		return xhZqtjDetailsDao.get(id);
	}
	
	/**
	 * 债权推荐保存
	 */
	public synchronized void saveTztj(String zqtj_id,XhZqtj xhZqtj,XhZqtjDetails[] xhZqtjDetails,XhLentmoneywater xhLentmoneywater) throws Exception{
		//债权推荐保存前清理数据
		if (StringUtils.isNotEmpty(zqtj_id)) {
			beforSaveZqtj(Long.parseLong(zqtj_id));
		}
		//推荐保存
		saveTztjOnly(xhZqtj,xhZqtjDetails,xhLentmoneywater);
	}
	
	/**
	 * 债权推荐保存
	 */
	public synchronized void saveTztjOnly(XhZqtj xhZqtj,XhZqtjDetails[] xhZqtjDetails,XhLentmoneywater xhLentmoneywater) throws Exception{
		xhZqtjDao.save(xhZqtj);
		XhLentmoneywater xy;
		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
//		beforSaveZqtj(xhZqtj.getId());
		for(XhZqtjDetails x:xhZqtjDetails){
			x.setXhZqtj(xhZqtj);
			XhAvailableValueOfClaims xhAvailableValueOfClaims = getXhAvailableValueOfClaims(x.getKyzqjzId());
			if(xhAvailableValueOfClaims.getKyzqjz() >= x.getMoney()){
				xhAvailableValueOfClaims.setKyzqjz(xhAvailableValueOfClaims.getKyzqjz()-x.getMoney());
				xhAvailableValueOfClaims.setZjrcybl(xhAvailableValueOfClaims.getKyzqjz()/xhAvailableValueOfClaims.getJkje()*100);
				xhAvailableValueOfClaimsDao.save(xhAvailableValueOfClaims);
				x.setZqlixiMonth(CreditHarmonyComputeUtilties.getInterest(xhAvailableValueOfClaims.getLoanContract().getDkll(),x.getMoney(),xhZqtj.getJhtzrq()));
				if(xhZqtj.getLentState().equals("0")){
					x.setZqlixiMonthSg(CreditHarmonyComputeUtilties.getFirstInterest(xhAvailableValueOfClaims.getLoanContract().getDkll(),x.getMoney(),xhZqtj.getJhtzrq()));
				}else{
	//				if(xhTzsq.getLastCjzq().equals("0")){
	//					//如果期数是0，则补首期天数利息
	//					x.setZqlixiMonthSg(CreditHarmonyComputeUtilties.getOverInterest(xhAvailableValueOfClaims.getLoanContract().getDkll(),x.getMoney(),xhZqtj.getJhtzrq(),Integer.parseInt(xhTzsq.getFirstdate())));
	//				}else{
						x.setZqlixiMonthSg(x.getZqlixiMonth());
	//				}
				}
				//x.setMoneyMonth(x.getMoney()/Double.parseDouble(x.getHkzq()));
				x.setZqcybi((x.getMoney()/xhAvailableValueOfClaims.getJkje())*100);
				x.setMoneyMonth(x.getMoney()/Double.parseDouble(xhAvailableValueOfClaims.getSyhkys()+""));
				xhZqtjDetailsDao.save(x);
				xy = new XhLentmoneywater();
				xy.setKyzqjzId(xhAvailableValueOfClaims.getId());
				xy.setZqtjId(xhZqtj.getId());
				xy.setMoney(0-x.getMoney());
				xy.setState("1");
				xy.setXhTzsq(xhLentmoneywater.getXhTzsq());
				xhLentmoneywaterDao.save(xy);
			}else{
				throw(new RuntimeException());
			}
		}
		
		if(xhZqtj.getState().equals("0")){
			xhTzsq.setXhZqtj(xhZqtj);
		}else{
			xhTzsq.setXhZqtj(null);
		}
		if(xhZqtj.getLentState().equals("0")){
			xhTzsq.setFirstdate(CreditHarmonyComputeUtilties.getFirstdate(xhZqtj.getJhtzrq()));
		}
		xhTzsqDao.save(xhTzsq);
	}
	/**
	 * 债权推荐保存前清理数据
	 */
	public void beforSaveZqtj(Long id){
		//删除资金流水
		List<Map<String, Object>> waterList = waterList(id);
		for(Map<String, Object> x : waterList){
			xhLentmoneywaterDao.delete(Long.parseLong(x.get("ID")+""));
		}
		//删除推荐明细
		List<Map<String, Object>> xhZqtjDetailsList = xhZqtjDetailsList(id);
		for(Map<String, Object> x : xhZqtjDetailsList){
			//还原金额
			XhAvailableValueOfClaims xhAvailableValueOfClaims = getXhAvailableValueOfClaims(Long.parseLong(x.get("KYZQJZ_ID")+""));
			xhAvailableValueOfClaims.setKyzqjz(xhAvailableValueOfClaims.getKyzqjz()+Double.parseDouble(x.get("MONEY")+""));
			xhAvailableValueOfClaims.setZjrcybl(xhAvailableValueOfClaims.getKyzqjz()/xhAvailableValueOfClaims.getJkje()*100);
			xhAvailableValueOfClaimsDao.save(xhAvailableValueOfClaims);
			//删除明细
			xhZqtjDetailsDao.delete(Long.parseLong(x.get("ID")+""));
		}
	}
	
	
	/**
	 * 债权推荐审批不通过清理数据
	 */
	public void deleteZqtjMessage(Long id){
		XhZqtj xhZqtj = getXhZqtj(id);
		//删除推荐明细
		Set<XhZqtjDetails> xhZqtjDetails = xhZqtj.getXhZqtjDetails();
		for(XhZqtjDetails x : xhZqtjDetails){
			//还原金额
			XhAvailableValueOfClaims xhAvailableValueOfClaims = getXhAvailableValueOfClaims(x.getKyzqjzId());
			xhAvailableValueOfClaims.setKyzqjz(xhAvailableValueOfClaims.getKyzqjz()+x.getMoney());
			xhAvailableValueOfClaims.setZjrcybl(xhAvailableValueOfClaims.getKyzqjz()/xhAvailableValueOfClaims.getJkje()*100);
			xhAvailableValueOfClaimsDao.save(xhAvailableValueOfClaims);
			//删除明细
			xhZqtjDetailsDao.delete(x);
		}
		//删除资金流水
		List<Map<String, Object>> waterList = waterList(id);
		for(Map<String, Object> x : waterList){
			xhLentmoneywaterDao.delete(Long.parseLong(x.get("ID")+""));
		}
		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
		xhTzsq.setOverstate("0");
		xhTzsq.setXhZqtj(null);
		xhTzsqDao.save(xhTzsq);
		//删除债权推荐表
		xhZqtjDao.delete(id);
	}
	
	/**
	 * 债权划扣不通过清理数据
	 */
	public void deleteZqtjMessageByHk(Long id,String describe,String tzsqState){
		XhZqtj xhZqtj = getXhZqtj(id);
		//删除推荐明细
		Set<XhZqtjDetails> xhZqtjDetails = xhZqtj.getXhZqtjDetails();
		for(XhZqtjDetails x : xhZqtjDetails){
			//还原金额
			XhAvailableValueOfClaims xhAvailableValueOfClaims = getXhAvailableValueOfClaims(x.getKyzqjzId());
			xhAvailableValueOfClaims.setKyzqjz(xhAvailableValueOfClaims.getKyzqjz()+x.getMoney());
			xhAvailableValueOfClaims.setZjrcybl(xhAvailableValueOfClaims.getKyzqjz()/xhAvailableValueOfClaims.getJkje()*100);
			xhAvailableValueOfClaimsDao.save(xhAvailableValueOfClaims);
			//删除明细
			xhZqtjDetailsDao.delete(x);
		}
		//删除资金流水
		List<Map<String, Object>> waterList = waterListByTzsq(xhZqtj.getXhTzsq().getId());
		for(Map<String, Object> x : waterList){
			xhLentmoneywaterDao.delete(Long.parseLong(x.get("ID")+""));
		}
		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
		xhTzsq.setOverstate("0");
		xhTzsq.setState("10");
		xhTzsq.setHkstate("1");
		xhTzsq.setXhZqtj(null);
		xhTzsqDao.save(xhTzsq);
		//删除债权推荐表
		xhZqtjDao.delete(id);
		
		XhTzsqState xhTzsqState = new XhTzsqState();
		xhTzsqState.setDescribe(describe);
		
		xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
		xhTzsqState.setState(tzsqState);
		xhTzsqStateDao.save(xhTzsqState);
	}
	
	/**
	 * 债权划扣通过
	 */
	public void successHk(Long id,String state,String describe,String tzsqState){
		XhZqtj xhZqtj = getXhZqtj(id);
		xhZqtj.setStatedg(state);
		xhZqtjDao.save(xhZqtj);
		XhTzsqState xhTzsqState = new XhTzsqState();
		xhTzsqState.setDescribe(describe);
		
		xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
		xhTzsqState.setState(tzsqState);
		xhTzsqStateDao.save(xhTzsqState);
		
//		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
//		xhTzsq.setHkstate("2");
//		xhTzsqDao.save(xhTzsq);
	}
	
	
	/**
	 * 债权推荐-撤销
	 */
	public void reBackZqtjMessage(Long id){
		XhZqtj xhZqtj = getXhZqtj(id);
		//删除推荐明细
		Set<XhZqtjDetails> xhZqtjDetails = xhZqtj.getXhZqtjDetails();
		for(XhZqtjDetails x : xhZqtjDetails){
			//还原金额
			XhAvailableValueOfClaims xhAvailableValueOfClaims = getXhAvailableValueOfClaims(x.getKyzqjzId());
			xhAvailableValueOfClaims.setKyzqjz(xhAvailableValueOfClaims.getKyzqjz()+x.getMoney());
			xhAvailableValueOfClaims.setZjrcybl(xhAvailableValueOfClaims.getKyzqjz()/xhAvailableValueOfClaims.getJkje()*100);
			xhAvailableValueOfClaimsDao.save(xhAvailableValueOfClaims);
			//删除明细
			//xhZqtjDetailsDao.delete(x);
		}
		//删除资金流水
		List<Map<String, Object>> waterList = waterList(id);
		for(Map<String, Object> x : waterList){
			xhLentmoneywaterDao.delete(Long.parseLong(x.get("ID")+""));
		}
		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
		xhTzsq.setXhZqtj(null);
		xhTzsqDao.save(xhTzsq);
		xhZqtj.setState("9");
		//删除债权推荐表
		this.saveXhZqtj(xhZqtj);
	}
	@Transactional(readOnly = true)
	public List<Map<String, Object>> waterList(Long id){
		String sql = "select ID from XH_LENTMONEYWATER a where 1=1 ";
		sql = sql + " and a.ZQTJ_ID = "+id;
		return jdbcDao.searchByMergeSql(sql);
	}
	@Transactional(readOnly = true)
	public List<Map<String, Object>> waterListByTzsq(Long id){
		String sql = "select ID from XH_LENTMONEYWATER a where 1=1 ";
		sql = sql + " and a.tzsq_id = "+id;
		return jdbcDao.searchByMergeSql(sql);
	}
	@Transactional(readOnly = true)
	public List<Map<String, Object>> xhZqtjDetailsList(Long id){
		String sql = "select ID,MONEY,KYZQJZ_ID from XH_ZQTJ_DETAILS a where 1=1 ";
		sql = sql + " and a.ZQTJ_ID = "+id;
		return jdbcDao.searchByMergeSql(sql);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getXhZqtjSqMessage(String id,String jhtzrq){
		//String sql = "select a.jhtzrq,a.xybgrq,a.zdr,a.FSQBGRQ from XH_ZQTJ a where 1=1 and a.lent_state='0' and a.state='2' and a.statedg='2' ";
		String sql = "select a.xybgrq,a.zdr,a.FSQBGRQ from XH_ZQTJ a where 1=1 ";
		sql = sql + " and a.id in (select t.zqtj_id from xh_lentmoneywater t where t.tzsq_id = "+id + " and t.jhtzrq='"+ jhtzrq + "') group by a.xybgrq,a.zdr,a.FSQBGRQ";
		//sql = sql + " and a.TZSQ_ID = "+id + " order by a.id";
		return jdbcDao.searchByMergeSql(sql);
	}
	@Transactional(readOnly = true)
	public List<Map<String,Object>> findXhZqtj(String queryName,Map<String,Object> filter)
	{
		String sql="";
		String value = "";
		//编号
		if(filter.containsKey("id")){
			value = String.valueOf(filter.get("id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = " +  value + "";
			}
		}
		String sql2 = "";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}
	private Map<String, Object> conditions(Map<String,Object> filter){
		String sql="";
		String sql2 = "";
		String value = "";
		//资金
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" +  value + "'";
			}
		}
		//债权状态0首期,1非首期
		if(filter.containsKey("lent_state")){
			value = String.valueOf(filter.get("lent_state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENT_STATE = '" +  value + "'";
			}
		}
		//订购标记0未订购,1已订购，2已交割
		if(filter.containsKey("statedg")){
			value = String.valueOf(filter.get("statedg"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATEDG = '" +  value + "'";
			}
		}
		//投资产品
		if(filter.containsKey("tzcp")){
			value = String.valueOf(filter.get("tzcp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZCP_ID = '" +  value + "'";
			}
		}
		//协议编号
		if(filter.containsKey("tzsqbh")){
			value = String.valueOf(filter.get("tzsqbh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZSQBH = '" +  value + "'";
			}
		}
		//计划投资日期
		if(filter.containsKey("jhtzrq")){
			value = String.valueOf(filter.get("jhtzrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHTZRQ = '" +  value + "'";
			}
		}
		
		if (filter.containsKey("lenjhtzrq")) {
            value = String.valueOf(filter.get("lenjhtzrq"));
            if (StringUtils.isNotEmpty(value)) {
                sql2 = sql2 + " and lenjhtzrq= '" + value + "'";
            }
        }
		//计划投资金额
		if(filter.containsKey("jhtzje")){
			value = String.valueOf(filter.get("jhtzje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.JHTZJE = '" +  value + "'";
			}
		}
		//投资方式
		if(filter.containsKey("tzfs")){
			value = String.valueOf(filter.get("tzfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZFS = '" +  value + "'";
			}
		}
		//回收方式
		if(filter.containsKey("hsfs")){
			value = String.valueOf(filter.get("hsfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.HSFS = '" +  value + "'";
			}
		}
		//付款方式
		if(filter.containsKey("fkfs")){
			value = String.valueOf(filter.get("fkfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.FKFS = '" +  value + "'";
			}
		}
		//是否风险金补偿
		if(filter.containsKey("sffxjbc")){
			value = String.valueOf(filter.get("sffxjbc"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SFFXJBC = '" +  value + "'";
			}
		}
		//计划划扣日期
		if(filter.containsKey("jhhkrq")){
			value = String.valueOf(filter.get("jhhkrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.JHHKRQ = '" +  value + "'";
			}
		}
		//申请日期
		if(filter.containsKey("sqrq")){
			value = String.valueOf(filter.get("sqrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SQRQ = '" +  value + "'";
			}
		}
		//部门主管
		if(filter.containsKey("bmzg")){
			value = String.valueOf(filter.get("bmzg"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.BMZG = '" +  value + "'";
			}
		}
		//协议版本
		if(filter.containsKey("xybb")){
			value = String.valueOf(filter.get("xybb"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.XYBB = '" +  value + "'";
			}
		}
		//销售折扣率
		if(filter.containsKey("xszkly")){
			value = String.valueOf(filter.get("xszkly"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.XSZKLY = '" +  value + "'";
			}
		}
		//销售折扣率有效期限
		if(filter.containsKey("xszklyxqx")){
			value = String.valueOf(filter.get("xszklyxqx"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.XSZKLYXQX = '" +  value + "'";
			}
		}
		//申请状态
		if(filter.containsKey("sqzt")){
			value = String.valueOf(filter.get("sqzt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SQZT = '" +  value + "'";
			}
		}
		//出借周期
		if(filter.containsKey("cjzq")){
			value = String.valueOf(filter.get("cjzq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.CJZQ = '" +  value + "'";
			}
		}
		//交割日
		if(filter.containsKey("tzjgr")){
			value = String.valueOf(filter.get("tzjgr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZJGR = '" +  value + "'";
			}
		}
		//首个还款日期
		if(filter.containsKey("sghkrq")){
			value = String.valueOf(filter.get("sghkrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SGHKRQ = '" +  value + "'";
			}
		}
		//账单日
		if(filter.containsKey("zdr")){
			value = String.valueOf(filter.get("zdr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.ZDR = '" +  value + "'";
			}
		}
		//投资资金状态
		if(filter.containsKey("tzzjzt")){
			value = String.valueOf(filter.get("tzzjzt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZZJZT = '" +  value + "'";
			}
		}
		//使用期数
		if(filter.containsKey("syqs")){
			value = String.valueOf(filter.get("syqs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SYQS = '" +  value + "'";
			}
		}
		//匹配状态
		if(filter.containsKey("ppzt")){
			value = String.valueOf(filter.get("ppzt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.PPZT = '" +  value + "'";
			}
		}
		//剩余出借周期
		if(filter.containsKey("lastCjzq")){
			value = String.valueOf(filter.get("lastCjzq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.LAST_CJZQ = '" +  value + "'";
			}
		}
		
		
		if(filter.containsKey("cjrxm")){
			value = String.valueOf(filter.get("cjrxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.cjrxm like '%" +  value + "%'";
			}
		}
		if(filter.containsKey("khbm")){
			value = String.valueOf(filter.get("khbm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.khbm like '%" +  value + "%'";
			}
		}
		
		if(filter.containsKey("province")){
			value = String.valueOf(filter.get("province"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.province = '" +  value + "'";
			}
		}
		if(filter.containsKey("city")){
			value = String.valueOf(filter.get("city"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.city = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("billSendState")){
			value = String.valueOf(filter.get("billSendState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.bill_send_state = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("agreementMakeState")){
			value = String.valueOf(filter.get("agreementMakeState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.agreement_make_state = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("id")){
            value = String.valueOf(filter.get("id"));
            if(StringUtils.isNotEmpty(value)) {
                sql = sql + " and a.id = '" +  value + "'";
            }
        }
		
		  if(filter.containsKey("dzyx")){
	            value = String.valueOf(filter.get("dzyx"));
	            if(StringUtils.isNotEmpty(value)) {
	                sql = sql + " and b.dzyx like '%" +  value + "%'";
	            }
	        }
	        
	        if(filter.containsKey("jhtzrq")){
	            value = String.valueOf(filter.get("jhtzrq"));
	            if(StringUtils.isNotEmpty(value)) {
	                sql = sql + " and a.jhtzrq = '" +  value + "'";
	            }
	        }
	        
	        if(filter.containsKey("zqjsfs")){
	            value = String.valueOf(filter.get("zqjsfs"));
	            if(StringUtils.isNotEmpty(value)) {
	                sql = sql + " and b.zqjsfs = '" +  value + "'";
	            }
	        }
	        
		
		if(filter.containsKey("gsdq")){
			value = String.valueOf(filter.get("gsdq"));
			if(StringUtils.isNotEmpty(value)) {
				if(value.equals("0021")){
					sql = sql + " and d.coding in ('310000','330000')";
				}else{
					sql = sql + " and d.coding not in ('310000','330000')";
				}
			}
		}
		
		if(filter.containsKey("yyb")){
			value = String.valueOf(filter.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" +  value + "'";
			}
		}
		System.out.println("sql2=======>" + sql2);
		//级联查询sql
		//sql = sql + PropertiesUtils.getSql(filter);
				
//		if (page.getOrderBy()!=null){
//			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
//		}
		sql = sql + " order by  a.CREATE_TIME desc";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return conditions;
	}
	//首期   已划扣债权
	private Map<String, Object> Overconditions(Map<String,Object> filter){
		String sql="";
		String value = "";
		//资金
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//计划投资日期
		if(filter.containsKey("startdate")){
			value = String.valueOf(filter.get("startdate"));
			if(StringUtils.isNotEmpty(value)){
				sql = sql + "and to_date(a.JHTZRQ,'yyyy-MM-dd') >= to_date('"+value+"','yyyy-MM-dd')";
			}
		}
		if(filter.containsKey("overdate")){
			value = String.valueOf(filter.get("overdate"));
			if(StringUtils.isNotEmpty(value)){
				sql = sql + "and to_date(a.JHTZRQ,'yyyy-MM-dd') <= to_date('"+value+"','yyyy-MM-dd')";
			}
		}
		
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" +  value + "'";
			}
		}
		//债权状态0首期,1非首期
		sql = sql + " and a.LENT_STATE = 0";
		/*划扣状态0，未划扣，1，划扣失败，2，已划扣*/
		sql = sql + " and c.Hkstate = 2";
		
//		//订购标记0未订购,1已订购，2已交割
//		if(filter.containsKey("statedg")){
//			value = String.valueOf(filter.get("statedg"));
//			if(StringUtils.isNotEmpty(value)) {
//				sql = sql + " and a.STATEDG = '" +  value + "'";
//			}
//		}
		//投资产品
		if(filter.containsKey("tzcp")){
			value = String.valueOf(filter.get("tzcp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZCP_ID = '" +  value + "'";
			}
		}
		//协议编号
		if(filter.containsKey("tzsqbh")){
			value = String.valueOf(filter.get("tzsqbh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZSQBH = '" +  value + "'";
			}
		}
		//计划投资日期
		if(filter.containsKey("jhtzrq")){
			value = String.valueOf(filter.get("jhtzrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHTZRQ = '" +  value + "'";
			}
		}
		//计划投资金额
		if(filter.containsKey("jhtzje")){
			value = String.valueOf(filter.get("jhtzje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.JHTZJE = '" +  value + "'";
			}
		}
		//投资方式
		if(filter.containsKey("tzfs")){
			value = String.valueOf(filter.get("tzfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZFS = '" +  value + "'";
			}
		}
		//回收方式
		if(filter.containsKey("hsfs")){
			value = String.valueOf(filter.get("hsfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.HSFS = '" +  value + "'";
			}
		}
		//付款方式
		if(filter.containsKey("fkfs")){
			value = String.valueOf(filter.get("fkfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.FKFS = '" +  value + "'";
			}
		}
		//是否风险金补偿
		if(filter.containsKey("sffxjbc")){
			value = String.valueOf(filter.get("sffxjbc"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SFFXJBC = '" +  value + "'";
			}
		}
		//计划划扣日期
		if(filter.containsKey("jhhkrq")){
			value = String.valueOf(filter.get("jhhkrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.JHHKRQ = '" +  value + "'";
			}
		}
		//申请日期
		if(filter.containsKey("sqrq")){
			value = String.valueOf(filter.get("sqrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SQRQ = '" +  value + "'";
			}
		}
		//部门主管
		if(filter.containsKey("bmzg")){
			value = String.valueOf(filter.get("bmzg"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.BMZG = '" +  value + "'";
			}
		}
		//协议版本
		if(filter.containsKey("xybb")){
			value = String.valueOf(filter.get("xybb"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.XYBB = '" +  value + "'";
			}
		}
		//销售折扣率
		if(filter.containsKey("xszkly")){
			value = String.valueOf(filter.get("xszkly"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.XSZKLY = '" +  value + "'";
			}
		}
		//销售折扣率有效期限
		if(filter.containsKey("xszklyxqx")){
			value = String.valueOf(filter.get("xszklyxqx"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.XSZKLYXQX = '" +  value + "'";
			}
		}
		//申请状态
		if(filter.containsKey("sqzt")){
			value = String.valueOf(filter.get("sqzt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SQZT = '" +  value + "'";
			}
		}
		//出借周期
		if(filter.containsKey("cjzq")){
			value = String.valueOf(filter.get("cjzq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.CJZQ = '" +  value + "'";
			}
		}
		//交割日
		if(filter.containsKey("tzjgr")){
			value = String.valueOf(filter.get("tzjgr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZJGR = '" +  value + "'";
			}
		}
		//首个还款日期
		if(filter.containsKey("sghkrq")){
			value = String.valueOf(filter.get("sghkrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SGHKRQ = '" +  value + "'";
			}
		}
		//账单日
		if(filter.containsKey("zdr")){
			value = String.valueOf(filter.get("zdr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.ZDR = '" +  value + "'";
			}
		}
		//投资资金状态
		if(filter.containsKey("tzzjzt")){
			value = String.valueOf(filter.get("tzzjzt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.TZZJZT = '" +  value + "'";
			}
		}
		//使用期数
		if(filter.containsKey("syqs")){
			value = String.valueOf(filter.get("syqs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.SYQS = '" +  value + "'";
			}
		}
		//匹配状态
		if(filter.containsKey("ppzt")){
			value = String.valueOf(filter.get("ppzt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.PPZT = '" +  value + "'";
			}
		}
		//剩余出借周期
		if(filter.containsKey("lastCjzq")){
			value = String.valueOf(filter.get("lastCjzq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.LAST_CJZQ = '" +  value + "'";
			}
		}
		
		
		if(filter.containsKey("cjrxm")){
			value = String.valueOf(filter.get("cjrxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.cjrxm like '%" +  value + "%'";
			}
		}
		if(filter.containsKey("khbm")){
			value = String.valueOf(filter.get("khbm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.khbm like '%" +  value + "%'";
			}
		}
		
		if(filter.containsKey("province")){
			value = String.valueOf(filter.get("province"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.province = '" +  value + "'";
			}
		}
		if(filter.containsKey("city")){
			value = String.valueOf(filter.get("city"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.city = '" +  value + "'";
			}
		}
		
		if(filter.containsKey("gsdq")){
			value = String.valueOf(filter.get("gsdq"));
			if(StringUtils.isNotEmpty(value)) {
				if(value.equals("0021")){
					sql = sql + " and d.coding in ('310000','330000')";
				}else{
					sql = sql + " and d.coding not in ('310000','330000')";
				}
			}
		}
		String sql2="";
		if(filter.containsKey("yyb")){
			value = String.valueOf(filter.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" +  value + "'";
			}
		}
		System.out.println("sql2=======>" + sql2);
		//级联查询sql
		//sql = sql + PropertiesUtils.getSql(filter);
				
//		if (page.getOrderBy()!=null){
//			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
//		}
		sql = sql + " order by  a.JHTZRQ desc";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		
		
		return conditions;
	}
	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhZqtj(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions(filter), page);
	}
	/*
	 * 已划扣ZqtjOver
	 */
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhZqtjOver(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, Overconditions(filter), page);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhZqtjForDown(String queryName,Map<String,Object> filter)
	{
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions(filter));
	}
	@Transactional(readOnly = true)
	public List<Map<String,Object>> findXhZqtjDetails(String queryName,Map<String,Object> filter)
	{
		Map<String, Object> conditions = new HashMap<String,Object>();
		String sql="";
		String value = "";
		//编号
		if(filter.containsKey("id")){
			value = String.valueOf(filter.get("id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = " +  value + "";
			}
		}
		//编号
		if(filter.containsKey("zqtj_id")){
			value = String.valueOf(filter.get("zqtj_id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQTJ_ID = " +  value + "";
			}
		}
		
		//编号
		if(filter.containsKey("cjrxx_id")){
			value = String.valueOf(filter.get("cjrxx_id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + value ;
			}
		}
		conditions.put("sql", sql);
		
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}
	/**
	 * 计算账户管理费
	 * @param money 投资金额
	 * @param id 投资申请ID
	 */
	public Map<String,String> getZhglf(Double money,String id){
		String sql = "select sum(t.jhtzje) as je from xh_tzsq t where t.state='2' and t.cjrxx_id = (select cjrxx_id from xh_tzsq where id = "+id+")";
		List<Map<String, Object>> allMoney = jdbcDao.searchByMergeSql(sql);
		Map<String,String> result = new HashMap<String,String>();
		Double all = 0.0;
		Double allm = Double.parseDouble(allMoney.get(0).get("JE")+"")/10000;
		Double lilv = 0.0;
		String jibie = "";
		if(allm<50){
			lilv = 0.0015;
			jibie = "信和账户";
		}else if(allm >= 50 && allm < 100){
			lilv = 0.001;
			jibie = "贵宾账户";
		}else if(allm >= 100 && allm < 300){
			lilv = 0.00075;
			jibie = "金卡账户";
		}else if(allm >= 300 && allm < 500){
			lilv = 0.0005;
			jibie = "白金账户";
		}else{
			lilv = 0.00025;
			jibie = "钻石账户";
		}
		all = money * 10000 * lilv;
		result.put("zhglf", all+"");
		result.put("lilv", (lilv*100)+"");
		result.put("jibie", jibie);
		sql = "update xh_zqtj t set t.zhglf = t.money*" + lilv+ ",t.zhjb='"+jibie+"',t.fwfl="+lilv*100+" where t.lent_State = '0' and t.tzsq_id in (select t.id as je from xh_tzsq t where t.state='2' and t.cjrxx_id = (select cjrxx_id from xh_tzsq where id = "+id+"))";
		jdbcDao.updateBySql(sql);
		return result;
	}
	
	//首期债权合同
	public void sqZqHt(String id,String jhtzrq,String creTime){
		System.out.println("id===>" + id + ";jhtzrq====>"+jhtzrq);
		Long Id = Long.parseLong(id);
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		map.put("id", Id);
		List<Map<String,Object>> listTzsq = findXhZqtj("queryXhZqtjList", map);
		Map<String,Object> value = listTzsq.get(0);
		*/
		XhZqtj xhZqtj = getXhZqtj(Id);
		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
		String statedg = xhZqtj.getStatedg();
		//如果是带订购状态，则将状态设置为待划扣，否则状态不变
		if(statedg.equals("0")){
			if(xhTzsq.getSqtype().equals("0")){
				xhZqtj.setStatedg("8");
			}else{
				xhZqtj.setStatedg("1");
				xhTzsq.setHkstate("2");
			}
			xhTzsq.setSjhkrq(DateUtils.format(new Date(), "yyyy-MM-dd"));
			
			XhTzsqState xhTzsqState = new XhTzsqState();
			xhTzsqState.setDescribe("首期债权合同订购");
			
			xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
			xhTzsqState.setState("B2");
			xhTzsqStateDao.save(xhTzsqState);
		}
		//*******如果计划投资日期没变化，则不再重新计算数据******
		if(!xhZqtj.getJhtzrq().equals(jhtzrq)){
			//xhZqtj.setStatedg("1");
			xhZqtj.setJhtzrq(jhtzrq);
			xhZqtj.setXybgrq(CreditHarmonyComputeUtilties.getFirstDateOfBackMoney(jhtzrq));
			xhZqtj.setZdr(CreditHarmonyComputeUtilties.getZdr(xhZqtj.getXybgrq()));
			//同时修改每月首个还款利息
			Set<XhZqtjDetails> xhZqtjDetails = xhZqtj.getXhZqtjDetails();
			for(XhZqtjDetails x:xhZqtjDetails){
				XhAvailableValueOfClaims xhAvailableValueOfClaims = getXhAvailableValueOfClaims(x.getKyzqjzId());
				x.setZqlixiMonthSg(CreditHarmonyComputeUtilties.getFirstInterest(xhAvailableValueOfClaims.getLoanContract().getDkll(),x.getMoney(),xhZqtj.getJhtzrq()));
				xhZqtjDetailsDao.save(x);
			}
			//map = new HashMap<String, Object>();
			map.put("zqtj_id", id);
			List<Map<String,Object>> listXhZqtjDetails = findXhZqtjDetails("queryXhZqtjDetailsList", map);
			/*
			String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
					+ File.separator;
			String mouFilePath2 = InitSetupListener.filePath + "agaeeFile"
					+ File.separator;
			Java2Word j = new Java2Word();
			
			HashMap data = new HashMap();
			data.put("{host}",value.get("YB"));//邮编
			
			data.put("{address}",reAddre(value.get("PRONAME")) + 
					reAddre(value.get("CITYNAME")) + 
					reAddre(value.get("AREANAME")) +  value.get("TXDZ"));//地址
	//		data.put("{address}",value.get("TXDZ"));
			String xh = value.get("CJRXB")+"";
			String ch = "先生";
			if(xh.equals("女")){
				ch = "女士";
			}
			data.put("{chenghu}",ch);//称呼
			data.put("{cscjrq}",xhZqtj.getJhtzrq());//出借日日期
			data.put("{cscjrq-1}",CreditHarmonyComputeUtilties.getDateBefore(xhZqtj.getJhtzrq(),1));//出借日日期-1
			
			data.put("{xingming}",value.get("CJRXM"));//尊敬的***
			data.put("{shourangren}",value.get("CJRXM"));//受让人（新债权人）
			data.put("{zhengjianhaoma}",value.get("ZJHM"));//身份证（护照）号码--受让人
			data.put("{zrjedx}",MoneyUtil.toChinese(value.get("MONEY")+""));//人民币（大写）
			data.put("{zrjexx}",MoneyUtil.toEnglish(value.get("MONEY")+""));//人民币小写
			//合并单元格信息
			List hebin = new ArrayList();
			//第二个表格
			List l= new ArrayList();
			String[] a =new String[11];
			a[0]="1";
			a[1]="2";
			a[2]="3";
			a[3]="4";
			a[4]="5";
			a[5]="6";
			a[6]="7";
			a[7]="8";
			a[8]="9";
			a[9]="10";
			a[10]="11";
			l.add(a);
			String zrr = "";//转让人（原债权人）
			String zrrhm = "";//身份证（护照）号码--转让人
			Double zrzqjz = 0.0;//本次转让债权价值-合计
			*/
			int size = listXhZqtjDetails.size();
			double[] moneys =new double[size];
			double[] lilvs =new double[size];
			int[] zqs =new int[size];
			int f =0 ;
			//int xuh = 0;
			for(Map<String,Object> m : listXhZqtjDetails){
				//xuh ++;
				moneys[f] = Double.parseDouble(m.get("MONEY")+"");
	//			zqs[f] = Integer.parseInt(m.get("HKZQ")+"");HKZQ_SY
				zqs[f] = Integer.parseInt(m.get("SYHKYS")+"");
			//	zqs[f] = Integer.parseInt(m.get("SYTJJE")+"");
				lilvs[f] = Double.parseDouble(m.get("DKLL")+"");
				/*
				zrr = m.get("MIDDLE_MAN_NAME")+"";
				zrrhm = m.get("ID_CARD")+"";
				zrzqjz += Double.parseDouble(m.get("MONEY")+"");
				a =new String[11];
				a[0]=xuh+"";
				a[1]=m.get("JKRXM")+"";
				a[2]=m.get("ZJHM")+"";
				a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(m.get("MONEY")+""));
				a[4]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(m.get("MONEY")+""));
				a[5]=getZhiYe(m.get("JK_TYPE")+"");
				a[6]=m.get("JK_USE")+"";
				a[7]=m.get("SQHKRQ")+"";
				a[8]=m.get("SYQS")+"";
	//			a[9]=m.get("SYHKYS")+"";
	//			a[7]=m.get("HKZQ")+"";
				a[9]=m.get("HKZQ_SY")+"";
				a[10]=MoneyUtil.reBackTwo(m.get("ZQYJCYL")+"")+"%";
				l.add(a);
				*/
				f ++;
			}
			/*
			a =new String[11];
			a[0]="合计";
			a[1]="";
			a[2]="";
			a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(zrzqjz + ""));
			a[4]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(zrzqjz + ""));
			a[5]="";
			a[6]="";
			a[7]="";
			a[8]="";
			a[9]="";
			a[10]="";
			l.add(a);
			xuh+=3;
			//<第？个表格> 从    <第？行>@<第？列>@ 到  <第？行>@<第？列> 合并
			hebin.add("4@"+xuh+"@1@"+xuh+"@3");
	//		data.put("{zqzrjzhj}",MoneyUtil.toEnglish(zrzqjz + ""));//本次转让债权价值-合计
	//		data.put("{xzfdjhj}",MoneyUtil.toEnglish(zrzqjz + ""));//需支付对价-合计
			//第4个表格，第三行开始
			data.put("table$3@4", l);
			*/
			//应收利息金额合计
			double firstInterest = CreditHarmonyComputeUtilties.getSumFirstInterest(lilvs, moneys, jhtzrq);
			//月回款本金合计
			double backPrincipal = CreditHarmonyComputeUtilties.getSumBackPrincipal(zqs, moneys);
			xhZqtj.setXybgqjkryhke(firstInterest+backPrincipal);
			xhZqtj.setYjxybgrzcze(xhZqtj.getMoney()+firstInterest);
			//第2个表格
			/*
			l= new ArrayList();
			a =new String[8];
			a[0]="1";
			a[1]="2";
			a[2]="3";
			a[3]="4";
			a[4]="5";
			a[5]="6";
			a[6]="7";
			a[7]="8";
			l.add(a);
			a =new String[8];
			a[0]=value.get("TZSQBH")+"";
			a[1]=value.get("TZCP_MC")+"";
			a[2]=jhtzrq;
			a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhZqtj.getMoney()+""));
			a[4]=xhZqtj.getXybgrq();
			a[5]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhZqtj.getXybgqjkryhke()+""));
			//a[6]=xhZqtj.getZhglf()+"";
			a[6]="0.00";
			a[7]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhZqtj.getYjxybgrzcze()+""));
			l.add(a);
			//第二行开始,第2个表格
			data.put("table$2@2", l);
			
			data.put("{zhuanrangren}",zrr);//转让人（原债权人）
			data.put("{zrrsfzhm}",zrrhm);//身份证（护照）号码--转让人
			
	//		String filePath = mouFilePath + value.get("TZSQBH")
	//				+ File.separator + "首期债权转让及受让协议.doc";
			
			String moban = "sqzqzrjsrxy.doc";
			if(size >= 3){
				moban = "sqzqzrjsrxy2.doc";
			}
			String tzsqbh = value.get("TZSQBH")+"";
			tzsqbh = tzsqbh.substring(tzsqbh.length()-3, tzsqbh.length());
			String filePath = mouFilePath2 + "zq"+xhZqtj.getJhtzrq()
					+ File.separator + "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+tzsqbh+"("+xhZqtj.getId()+")"+xhZqtj.getJhtzrq()+".doc";
					//+ File.separator + "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
			System.out.println("**********赋值完成************filePath=" + filePath);
			j.toWord(mouFilePath + moban, filePath, data,hebin);
			j.quit();
			*/
	//		PDFConverter pdfConverter = new OpenOfficePDFConverter();
	//		DocConverter converter = new DocConverter(pdfConverter,null);
	//		converter.convertPdf(filePath);
			
			//System.out.println("**********合同完成**********");
			
			xhTzsq.setFirstdate(CreditHarmonyComputeUtilties.getFirstdate(xhZqtj.getJhtzrq()));
			xhTzsq.setJhtzrq(jhtzrq);
			xhTzsq.setZdr(xhZqtj.getZdr());
			xhTzsq.setSghkrq(xhZqtj.getXybgrq());
		}
		saveXhTzsq(xhTzsq);
		xhZqtj.setAgreementMakeState("1");
		saveXhZqtj(xhZqtj);
		//保存到制作表
		XhMadeword xhMadeword = new XhMadeword();
		xhMadeword.setState("0");
		xhMadeword.setStype("0");
		xhMadeword.setCretime(creTime);
		xhMadeword.setTableId(xhZqtj.getId());
		xhMadeword.setTableName("xh_zqtj");
		xhMadewordDao.save(xhMadeword);
//		XhTzsq tzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
//		tzsq.setJhtzrq(xhZqtj.getJhtzrq());jhtzrq
//		tzsq.setSghkrq(xhZqtj.getXybgrq());
//		tzsq.setZdr(xhZqtj.getZdr());
//		this.saveXhTzsq(tzsq);
	}
	
	//首期债权合同
		public void sqZqHtPl(XhZqtj xhZqtj,String creTime){
			String statedg = xhZqtj.getStatedg();
			//如果是带订购状态，则将状态设置为待划扣，否则状态不变
			XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
			//如果是带订购状态，则将状态设置为待划扣，否则状态不变
			if(statedg.equals("0")){
				if(xhTzsq.getSqtype().equals("0")){
					xhZqtj.setStatedg("8");
				}else{
					xhZqtj.setStatedg("1");
					xhTzsq.setHkstate("2");
				}
				xhTzsq.setSjhkrq(DateUtils.format(new Date(), "yyyy-MM-dd"));
				saveXhTzsq(xhTzsq);
//				saveXhZqtj(xhZqtj);
				XhTzsqState xhTzsqState = new XhTzsqState();
				xhTzsqState.setDescribe("首期债权合同订购");
				xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
				xhTzsqState.setState("B2");
				xhTzsqStateDao.save(xhTzsqState);
			}
			xhZqtj.setAgreementMakeState("1");
			saveXhZqtj(xhZqtj);
			//保存到制作表
			XhMadeword xhMadeword = new XhMadeword();
			xhMadeword.setState("0");
			xhMadeword.setStype("0");
			xhMadeword.setCretime(creTime);
			xhMadeword.setTableId(xhZqtj.getId());
			xhMadeword.setTableName("xh_zqtj");
			xhMadewordDao.save(xhMadeword);
		}
		public void fsqZqHtpl(XhZqtj xhZqtj,String creTime){
			/*
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Id);
			List<Map<String,Object>> listTzsq = findXhZqtj("queryXhZqtjList", map);
			Map<String,Object> value = listTzsq.get(0);
			*/
			String statedg = xhZqtj.getStatedg();
			if(statedg.equals("0")){
				xhZqtj.setStatedg("1");
//				saveXhZqtj(xhZqtj);
				XhTzsqState xhTzsqState = new XhTzsqState();
				xhTzsqState.setDescribe("非首期债权合同订购");
				
				xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
				xhTzsqState.setState("C2");
				xhTzsqStateDao.save(xhTzsqState);
			}
			xhZqtj.setAgreementMakeState("1");
			saveXhZqtj(xhZqtj);
			//保存到制作表
			XhMadeword xhMadeword = new XhMadeword();
			xhMadeword.setState("0");
			xhMadeword.setStype("0");
			xhMadeword.setCretime(creTime);
			xhMadeword.setTableId(xhZqtj.getId());
			xhMadeword.setTableName("xh_zqtj");
			xhMadewordDao.save(xhMadeword);			
		}
	//非首期债权合同
		public void fsqZqHt(String id,String creTime){
			System.out.println("id===>" + id);
			Long Id = Long.parseLong(id);
			/*
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Id);
			List<Map<String,Object>> listTzsq = findXhZqtj("queryXhZqtjList", map);
			Map<String,Object> value = listTzsq.get(0);
			*/
			XhZqtj xhZqtj = getXhZqtj(Id);
			//xhZqtj.setStatedg("1");
			String statedg = xhZqtj.getStatedg();
			if(statedg.equals("0")){
				xhZqtj.setStatedg("1");
				//saveXhZqtj(xhZqtj);
				XhTzsqState xhTzsqState = new XhTzsqState();
				xhTzsqState.setDescribe("非首期债权合同订购");
				
				xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
				xhTzsqState.setState("C2");
				xhTzsqStateDao.save(xhTzsqState);
			}
			/*
			map = new HashMap<String, Object>();
			map.put("zqtj_id", id);
			List<Map<String,Object>> listXhZqtjDetails = findXhZqtjDetails("queryXhZqtjDetailsList", map);
			
			String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
					+ File.separator;
			String mouFilePath2 = InitSetupListener.filePath + "agaeeFile"
					+ File.separator;
			Java2Word j = new Java2Word();
			HashMap data = new HashMap();
			
			data.put("{host}",value.get("YB"));//邮编
			data.put("{address}",reAddre(value.get("PRONAME")) +
					reAddre(value.get("CITYNAME")) + 
					reAddre(value.get("AREANAME")) + value.get("TXDZ"));//地址
//			data.put("{address}",value.get("TXDZ"));//地址
			String xh = value.get("CJRXB")+"";
			String ch = "先生";
			if(xh.equals("女")){
				ch = "女士";
			}
			data.put("{chenghu}",ch);//称呼
			data.put("{cscjrq}",xhZqtj.getJhtzrq());//出借日日期
			String nextDate = xhZqtj.getJhtzrq();
			if (null != nextDate && nextDate.contains("-")) {
				String[] YMD = nextDate.split("-");
				if (YMD.length == 3) {
					data.put("{NIAN}", YMD[0]);
					data.put("{YUE}", YMD[1]);
					data.put("{RI}", YMD[2]);
				}
			}
			//data.put("{cscjrq-1}",CreditHarmonyComputeUtilties.getDateBefore(xhZqtj.getJhtzrq(),1));//出借日日期-1
			data.put("{7-27}",CreditHarmonyComputeUtilties.reb7_27(xhZqtj.getJhtzrq(),xhZqtj.getZdr()));//出借日日期-1
			data.put("{cjbh}",value.get("TZSQBH"));//出借编号
			data.put("{cjfs}",value.get("TZCP_MC"));//出借方式
			
			data.put("{xingming}",value.get("CJRXM"));//尊敬的***
			data.put("{shourangren}",value.get("CJRXM"));//受让人（新债权人）
			data.put("{zhengjianhaoma}",value.get("ZJHM"));//身份证（护照）号码--受让人
			data.put("{zrjedx}",MoneyUtil.toChinese(MoneyUtil.reBackTwo(value.get("MONEY")+"")));//人民币（大写）
			data.put("{zrjexx}",MoneyUtil.toEnglish(MoneyUtil.reBackTwo(value.get("MONEY")+"")));//人民币小写
			//合并单元格信息
			List hebin = new ArrayList();
			//第二3个表格
			List l= new ArrayList();
			String[] a =new String[11];
			a[0]="1";
			a[1]="2";
			a[2]="3";
			a[3]="4";
			a[4]="5";
			a[5]="6";
			a[6]="7";
			a[7]="8";
			a[8]="9";
			a[9]="10";
			a[10]="11";
			l.add(a);
			String zrr = "";//转让人（原债权人）
			String zrrhm = "";//身份证（护照）号码--转让人
			Double zrzqjz = 0.0;//本次转让债权价值-合计
			int xuh = 0;
			for(Map<String,Object> m : listXhZqtjDetails){
				zrr = m.get("MIDDLE_MAN_NAME")+"";
				zrrhm = m.get("ID_CARD")+"";
				zrzqjz += Double.parseDouble(m.get("MONEY")+"");
				xuh++;
				a =new String[11];
				a[0]=xuh+"";
				a[1]=m.get("JKRXM")+"";
				a[2]=m.get("ZJHM")+"";
				a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(m.get("MONEY")+""));
				a[4]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(m.get("MONEY")+""));
				a[5]=getZhiYe(m.get("JK_TYPE")+"");
				a[6]=m.get("JK_USE")+"";
				a[7]=m.get("SQHKRQ")+"";
				a[8]=m.get("SYQS")+"";
//				a[9]=m.get("SYHKYS")+"";
//				a[7]=m.get("HKZQ")+"";
				a[9]=m.get("HKZQ_SY")+"";
				a[10]=MoneyUtil.reBackTwo(m.get("ZQYJCYL")+"")+"%";
				l.add(a);
			}
			
			a =new String[11];
			a[0]="合计";
			a[1]="";
			a[2]="";
//			a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(zrzqjz + ""));
			a[3]="";
			a[4]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(zrzqjz + ""));
			a[5]="";
			a[6]="";
			a[7]="";
			a[8]="";
			a[9]="";
			a[10]="";
			l.add(a);
			xuh+=3;
			//<第？个表格> 从    <第？行>@<第？列>@ 到  <第？行>@<第？列> 合并
			hebin.add("3@"+xuh+"@1@"+xuh+"@3");
			//第3个表格，第三行开始
			data.put("table$3@3", l);
			data.put("{zhuanrangren}",zrr);//转让人（原债权人）
			data.put("{zrrsfzhm}",zrrhm);//身份证（护照）号码--转让人
//			data.put("{zqzrjzhj}",MoneyUtil.toEnglish(zrzqjz + ""));//本次转让债权价值-合计
//			data.put("{xzfdjhj}",MoneyUtil.toEnglish(zrzqjz + ""));//需支付对价-合计
			
			//第四5个表格 既有债权列表
			l= new ArrayList();
			a =new String[12];
			a[0]="1";
			a[1]="2";
			a[2]="3";
			a[3]="4";
			a[4]="5";
			a[5]="6";
			a[6]="7";
			a[7]="8";
			a[8]="9";
			a[9]="10";
			a[10]="11";
			a[11]="12";
			l.add(a);
			map = new HashMap<String, Object>();
			//map.put("cjrxx_id", xhZqtj.getCjrxx().getId()+"");
			map.put("cjrxx_id", xhZqtj.getXhTzsq().getId()+"");
			List<Map<String,Object>> listXhLentmoneywater = findXhZqtjDetails("queryXhLentmoneywater", map);
			Double cssrzqjzhj = 0.0;//初始受让债权价值-合计
			Double cyzqjzhj = 0.0;//持有债权价值-合计
			Double bqhkjehj = 0.0;//本期还款金额-合计
			xuh = 0;
			for(Map<String,Object> m : listXhLentmoneywater){
				xuh ++;
				cssrzqjzhj += Double.parseDouble(m.get("MONEY")+"");
				cyzqjzhj += Double.parseDouble(m.get("MONEY_SY")+"");
				bqhkjehj += Double.parseDouble(m.get("FINAL_INTEREST")+"") + Double.parseDouble(m.get("FINAL_MONEY")+"");
				a =new String[12];
				a[0]=xuh+"";
				a[1]=m.get("JKRXM")+"";
				a[2]=m.get("ZJHM")+"";
				a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(m.get("MONEY")+""));
				a[4]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(m.get("MONEY_SY")+""));
				a[5]=getZhiYe(m.get("JK_TYPE")+"");
				a[6]=m.get("JK_USE")+"";
				//a[7]=m.get("XYBGRQ")+"";
				a[7]=m.get("SQHKRQ")+"";
				a[8]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo((Double.parseDouble(m.get("FINAL_INTEREST")+"") + Double.parseDouble(m.get("FINAL_MONEY")+""))+""));
				a[9]=m.get("SYQS")+"";
//				a[10]=m.get("SYHKYS")+"";
//				a[8]=m.get("HKZQ")+"";
				a[10]=m.get("HKZQ_SY")+"";
				a[11]=MoneyUtil.reBackTwo(m.get("ZQYJCYL")+"")+"%";
				l.add(a);
			}
			
			a =new String[12];
			a[0]="合计";
			a[1]="";
			a[2]="";
//			a[3]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(cssrzqjzhj + ""));
			a[3]="";
			a[4]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(cyzqjzhj + ""));
			a[5]="";
			a[6]="";
			a[7]="";
//			a[8]=MoneyUtil.toEnglish(MoneyUtil.reBackTwo(bqhkjehj + ""));
			a[8]="";
			a[9]="";
			a[10]="";
			a[11]="";
			l.add(a);
			xuh+=3;
			//<第？个表格> 从    <第？行>@<第？列>@ 到  <第？行>@<第？列> 合并
			hebin.add("5@"+xuh+"@1@"+xuh+"@3");
			//第三个表格，第二行开始
			data.put("table$3@5", l);
			
//			data.put("{cssrzqjzhj}",MoneyUtil.toEnglish(cssrzqjzhj + ""));//初始受让债权价值-合计
//			data.put("{cyzqjzhj}",MoneyUtil.toEnglish(cyzqjzhj + ""));//持有债权价值-合计
//			data.put("{bqhkjehj}",MoneyUtil.toEnglish(bqhkjehj + ""));//本期还款金额-合计
//			String filePath = mouFilePath + value.get("TZSQBH")
//					+ File.separator + "非首期债权转让及受让协议"+value.get("CJRXM")+xhZqtj.getJhtzrq()+".doc";
			String tzsqbh = value.get("TZSQBH")+"";
			tzsqbh = tzsqbh.substring(tzsqbh.length()-3, tzsqbh.length());
			String moban = "fsqzqzrjsrxy.doc";
			if(listXhZqtjDetails.size() >= 3){
				moban = "fsqzqzrjsrxy2.doc";
			}
			
			String filePath = mouFilePath2 + "zq"+xhZqtj.getJhtzrq()
					+ File.separator + "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+tzsqbh+"("+xhZqtj.getId()+")"+xhZqtj.getJhtzrq()+".doc";
					//+ File.separator + "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
			System.out.println("**********赋值完成************filePath=" + filePath);
			j.toWord(mouFilePath + moban, filePath, data,hebin);
			j.quit();
//			PDFConverter pdfConverter = new OpenOfficePDFConverter();
//			DocConverter converter = new DocConverter(pdfConverter,null);
//			converter.convertPdf(filePath);
			System.out.println("**********合同完成**********");
			*/
			xhZqtj.setAgreementMakeState("1");
			saveXhZqtj(xhZqtj);
			//保存到制作表
			XhMadeword xhMadeword = new XhMadeword();
			xhMadeword.setState("0");
			xhMadeword.setStype("0");
			xhMadeword.setCretime(creTime);
			xhMadeword.setTableId(xhZqtj.getId());
			xhMadeword.setTableName("xh_zqtj");
			xhMadewordDao.save(xhMadeword);
		}
	public String getZhiYe(String jkType){
		if(jkType.equals("A") || jkType.equals("B")){
			return "业主";
		}else if(jkType.equals("C") || jkType.equals("D")|| jkType.equals("E")){
			return "职员";
		}else if(jkType.equals("F")){
			return "抵押房";
		}else{
			return "业主";
		}
		
	}
	
	public String reAddre(Object value){
		if(value!=null && StringUtils.isNotEmpty(value+"")){
			return value+" ";
		}else{
			return "";
		}
	}
	
	public static void main(String[] args) {
		String ss = "dsjfk1234567";
		System.out.println(ss.substring(ss.length()-3, ss.length()));
	}
	
	
	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> queryXhAvailableValueOfClaims(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//借款
		if(filter.containsKey("jkrxm")){
			value = String.valueOf(filter.get("jkrxm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.jkrxm like '%" +  value + "%'";
			}
		}
		//账单日
		if(filter.containsKey("hkr")){
			value = String.valueOf(filter.get("hkr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.hkr = '" +  value + "'";
			}
		}
		//剩余还款月数
		if(filter.containsKey("syhkysMin")){
			value = String.valueOf(filter.get("syhkysMin"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.syhkys >= " +  value ;
			}
		}
		//剩余还款月数
		if(filter.containsKey("syhkysMax")){
			value = String.valueOf(filter.get("syhkysMax"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.syhkys <= " +  value ;
			}
		}
		//剩余推荐金额
		if(filter.containsKey("sytjjeMin")){
			value = String.valueOf(filter.get("sytjjeMin"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.kyzqjz >= " +  value ;
			}
		}
		//剩余推荐金额
		if(filter.containsKey("sytjjeMax")){
			value = String.valueOf(filter.get("sytjjeMax"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.kyzqjz <= " +  value ;
			}
		}
		//中间人_ID
		if(filter.containsKey("zjrId")){
			value = String.valueOf(filter.get("zjrId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.id = " +  value ;
			}
		}
		
		//利率
		if(filter.containsKey("dkll")){
			value = String.valueOf(filter.get("dkll"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and d.dkll = " +  value + "";
			}
		}
		
		//产品
		if(filter.containsKey("backup01")){
			value = String.valueOf(filter.get("backup01"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.backup01 = '" +  value + "'";
			}
		}
		if(filter.containsKey("mateid")){
			value = String.valueOf(filter.get("mateid"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.id not in ("+value.substring(0, value.length()-1)+")";
			}
		}
		//在次月“债权匹配”的时候，同一笔出借申请，不能多次匹配同一可用债权
		if(filter.containsKey("TZSQ_ID")){
			value = String.valueOf(filter.get("TZSQ_ID"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.id not in (select f.kyzqjz_id from xh_zqtj_details f where  f.zqtj_id in (select a.id from xh_zqtj a where a.state='2' and a.tzsq_id = "+value+"))";
			}
		}
		sql = sql + " order by  a.kyzqjz asc";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	
	
	
	
	public void exportProref(String path,Map<String, Object> map){
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l ;
		String[] a;
		List<Map<String, Object>> list = this.searchXhZqtjForDown("queryXhZqtjHuaKouList", map);
    	//String gsdq = String.valueOf(map.get("gsdq"));
    	/*
    	if("0021".equals(gsdq)){
    		Double moneyAll = 0.0;//合计
    		Double money = 0.0;//合计
    		inputPath = mouFilePath + "haoyilian.xls";
    		data.put("{1}", DateUtils.format(new Date(),"yyyyMMdd"));
    		data.put("{2}", list.size()+"");
    		data.put("table", "1");
    		data.put("table1", "0@3");// 第一个seet页，第4行开始
    		l = new ArrayList();
    		a = new String[10];
    		a[0] = "0@v";
    		a[1] = "1@v";
    		a[2] = "3@v";
    		a[3] = "5@v";
    		a[4] = "6@v";
    		a[5] = "7@v";
    		a[6] = "8@v";
    		a[7] = "9@v";
    		a[8] = "11@v";
    		a[9] = "22@v";
    		l.add(a);
    		int xu = 0;
    		for(Map<String, Object> m:list){
    			xu++;
    			a = new String[10];
    			money = Double.parseDouble(m.get("MONEY")+"")*100;
    			moneyAll += money;
        		a[0] = xu+"";
        		a[1] = m.get("ID")+"";
        		a[2] = m.get("BANK_CODE")+"";
        		a[3] = m.get("TZFKYHZH")+"";
        		a[4] = m.get("CJRXM")+"";
        		a[5] = m.get("PRONAME")+"";
        		a[6] = m.get("CITYNAME")+"";
        		a[7] = m.get("TZFKYHMC")+"";
        		a[8] = money+"";
        		a[9] = m.get("JHHKRQ")+"";
        		l.add(a);
    		}
    		data.put("table10@3", l);// 第一个seet页，第4行开始
    		data.put("{3}", moneyAll+"");
    		data.put("cellType", "1");
    		//data.put("cellType1", "0@1@4@N");
    	}else{
    	*/
    		inputPath = mouFilePath + "fuyou.xls";
    		data.put("table", "1");
    		data.put("table1", "0@1");// 第一个seet页，第2行开始
    		l = new ArrayList();
    		a = new String[8];
    		a[0] = "0@v";
    		a[1] = "1@v";
    		a[2] = "2@v";
    		a[3] = "3@v";
    		a[4] = "4@v";
    		a[5] = "5@v";
    		a[6] = "7@v";
    		a[7] = "11@v";
    		l.add(a);
    		int xu = 0;
    		for(Map<String, Object> m:list){
    			xu++;
    			a = new String[8];
        		a[0] = xu+"";
        		a[1] = m.get("ID")+"";
        		a[2] = m.get("TZFKKHH")+"";
        		a[3] = m.get("TZFKYHZH")+"";
        		a[4] = m.get("CJRXM")+"";
        		a[5] = m.get("MONEY")+"";
        		a[6] = "代收";
        		a[7] = m.get("SJHKRQ")+"";
        		l.add(a);
    		}
    		data.put("table10@1", l);// 第一个seet页，第2行开始
    	//}
    	je.toExcel(inputPath, path, data);
    }
	/**
	 * 收款确认书制作
	 * @param id
	 */
	public void skres(Long id){
		//保存到制作表
		XhMadeword xhMadeword = new XhMadeword();
		xhMadeword.setState("0");
		xhMadeword.setStype("1");
		xhMadeword.setTableId(id);
		xhMadeword.setTableName("xh_jkht");
		xhMadewordDao.save(xhMadeword);			
	}
	public int reSussHuaKou(List<Map<String,Object>> listValue){
		int re = 0;
		String res = "";
		XhZqtj xhZqtj;
		for(Map<String,Object> m:listValue){
			res = m.get("result")+"";
			if("0".equals(res)){
				xhZqtj = this.xhZqtjDao.get(Long.parseLong(m.get("id")+""));
				if(null != xhZqtj && xhZqtj.getMoney()==Double.parseDouble(m.get("money")+"") 
						&& xhZqtj.getStatedg().equals(m.get("statedg")+"")){
					xhZqtj.setStatedg("1");
					XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
					xhTzsq.setHkstate("2");
					if(StringUtils.isNotEmpty(m.get("sjhkrq")+"")){
						xhTzsq.setSjhkrq(m.get("sjhkrq")+"");
					}
					xhTzsqDao.save(xhTzsq);
					xhZqtjDao.save(xhZqtj);
					skres(xhZqtj.getId());
					saveMessage(xhTzsq);
					
					XhTzsqState xhTzsqState = new XhTzsqState();
					xhTzsqState.setDescribe("结算划扣成功");
					
					xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
					xhTzsqState.setState("6");
					xhTzsqStateDao.save(xhTzsqState);
					re++;
				}
			}
		}
		return re;
	}
	
	public void sussHuaKouSingle(Long id,String sjhkrq,String describe,String tzsqState){
		XhZqtj xhZqtj;
		xhZqtj = this.xhZqtjDao.get(id);
		xhZqtj.setStatedg("1");
		XhTzsq xhTzsq = getXhTzsq(xhZqtj.getXhTzsq().getId());
		xhTzsq.setHkstate("2");
		xhTzsq.setSjhkrq(sjhkrq);
		xhTzsqDao.save(xhTzsq);
		xhZqtjDao.save(xhZqtj);
		skres(xhZqtj.getId());
		saveMessage(xhTzsq);
		XhTzsqState xhTzsqState = new XhTzsqState();
		xhTzsqState.setDescribe(describe);
		
		xhTzsqState.setXhTzsq(xhZqtj.getXhTzsq());
		xhTzsqState.setState(tzsqState);
		xhTzsqStateDao.save(xhTzsqState);
	}
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	//划扣成功，插入一条消息
	private void saveMessage(XhTzsq xhTzsq){
		jdbcTemplate.execute("call insertTzsqHkMessage("+xhTzsq.getId()+", "+xhTzsq.getCjrxx().getId()+",'"+xhTzsq.getTzsqbh()+"','"
	+xhTzsq.getJhtzje()+"','"+xhTzsq.getTzfs()+"','"+xhTzsq.getSjhkrq()+"')");
	}
	public void saveXhSendemail(String id) {
		
				XhSendemail xsd = new XhSendemail();
				xsd.setState("0");
				xsd.setTableId(Long.parseLong(id));
				xsd.setTableName("xh_zqtj");
				xhSendemailDao.save(xsd);
			
	}
	
}
