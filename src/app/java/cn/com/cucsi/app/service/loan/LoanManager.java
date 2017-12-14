package cn.com.cucsi.app.service.loan;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJkjjlxrDao;
import cn.com.cucsi.app.dao.loan.XhJkrxxDao;
import cn.com.cucsi.app.dao.loan.XhJkrxxUPHistoryDao;
import cn.com.cucsi.app.dao.templet.TempletDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.Templet;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJkrxx;
import cn.com.cucsi.app.entity.xhcf.XhJkrxxUPHistory;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqtypemsg;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class LoanManager {

	private static Logger logger = LoggerFactory.getLogger(LoanManager.class);

	private AccountManager accountManager;
    
    private JdbcDao jdbcDao;
    
    private XhJkrxxDao xhJkrxxDao;
    
    private XhJkrxxUPHistoryDao xhJkrxxUPHistoryDao;
    
    private XhJkjjlxrDao xhdkjjlxrDao;
    
    private TempletDao templetDao;

    //*****借款人基础信息****start************************************************************
    /**
     * 贷款人基础信息查询
     * @param page
     * @param params
     */
    @Transactional(readOnly = true)
    public void searchXhcfDkrxx(Page<XhJkrxx> page, Map<String, Object> params){
    	page = xhJkrxxDao.queryXhcfDkrxx(page, params);
    }

    /**
     * 通过ID获取借款人基础信息
     * @param Id
     * @return 借款人基础信息
     */
    @Transactional(readOnly = true)
    public XhJkrxx getXhcfDkrxx(Long Id){
    	return xhJkrxxDao.get(Id);
    }
    
    /**
     * 通过Id查找借款人的紧急联系人信息
     * @param Id
     * @return XhDkjjlxr紧急联系人信息
     */
    public XhJkjjlxr getXhDkjjlxr(Long Id){
    	return xhdkjjlxrDao.get(Id);
    }
    
    /**
     * 借款人信息修改
     * @param request
     * @param xhcfdkrxx
     * @param xhcfdkrxxpo
     * @return
     */
    public boolean saveEditXhcfDkrxx(HttpServletRequest request,XhJkrxx xhcfdkrxx){
    	boolean flag = false;
    	XhJkrxx editxhcfdkrxx = getXhcfDkrxx(xhcfdkrxx.getId());
    	//需要把传递过来的实体再set一遍
//    	editxhcfdkrxx.setJkrxm(xhcfdkrxx.getJkrxm());
    	editxhcfdkrxx.setXhDkrxx(xhcfdkrxx);
    	String opt = request.getParameter("opt");
    	if(null != opt && !"".equals(opt)){
    		if("0".equals(opt)){
    			editxhcfdkrxx.setState("0");
    		}else if("1".equals(opt)){
    			editxhcfdkrxx.setState("1");
    		}
    	}
    	Timestamp tempTimeStamp = editxhcfdkrxx.getLastModifyTime();//保存前的最后修改日期
    	editxhcfdkrxx.setLastModifyTime(new Timestamp(new Date().getTime()));
    	XhJkrxx borrow = xhJkrxxDao.get(editxhcfdkrxx.getId());
    	if(null == tempTimeStamp && null != borrow.getLastModifyTime()){
    		flag = true;
    	}else{
	    	if(tempTimeStamp.getTime() != borrow.getLastModifyTime().getTime()){
	    		flag = true;
	    	}
    	}
    	return flag;
    }
    
    /**
     * 保存借款人开户信息
     * @param request
     * @param xhcfdkrxx
     * @param xhcfdkrxxpo
     * @return
     */
    public boolean saveOpenXhcfDkrxx(HttpServletRequest request,XhJkrxx xhcfdkrxx){
    	boolean flag = false;
    	XhJkrxx editxhcfdkrxx = getXhcfDkrxx(xhcfdkrxx.getId());
    	//需要把传递过来的实体再set一遍
    	editxhcfdkrxx.setJkrxm(xhcfdkrxx.getJkrxm());
    	editxhcfdkrxx.setDkrzt("0");
    	Timestamp tempTimeStamp = editxhcfdkrxx.getLastModifyTime();//保存前的最后修改日期
    	editxhcfdkrxx.setLastModifyTime(new Timestamp(new Date().getTime()));
    	XhJkrxx borrow = xhJkrxxDao.get(editxhcfdkrxx.getId());
    	if(null == tempTimeStamp && null != borrow.getLastModifyTime()){
    		flag = true;
    	}else{
    		if(tempTimeStamp.getTime() != borrow.getLastModifyTime().getTime()){
    			flag = true;
    		}
    	}
    	return flag;
    }
    
    /**
     * 保存借款人基础信息
     * @param request
     * @param xhcfdkrxx
     * @param xhcfdkrxxpo
     * @return
     */
    public boolean saveLoanBaseMsg(HttpServletRequest request, 
    		 String loginName, XhJksq xhjksq){
    	boolean flag = false;
    	String opt = request.getParameter("opt");
    	if(null != opt && !"".equals(opt)){
    		if("0".equals(opt)){
    			xhjksq.setState("0");
    		}else if("1".equals(opt)){
    			xhjksq.setState("1");
    		}
    	}
    	
    	Employee employee = accountManager.findUserByLoginName(loginName).getEmployee();
    	
    	
    	//保存紧急联系人
    	Set<XhJkjjlxr> shJkjjlxrs = getXhJkjjlxrSet(request);
    	
    	
    	//保存借款申请信息
    	
    	return flag;
    }
 
    private Set<XhJksq> getXhjksqSet(HttpServletRequest request, XhJksq xhjksq){
    	Set<XhJksq> set = new HashSet<XhJksq>();
    	String jkType = request.getParameter("jkType");
    	if(null != jkType && !"".equals(jkType)){
    		List<Templet> templetList = templetDao.findTempletByType(jkType);
    		if(null != templetList && templetList.size()>0){
    			Templet templet = templetList.get(0);
    			String keys = templet.getKeys();
    			String [] key = keys.split(",");
    			StringBuffer values = new StringBuffer();
    			for(int i=0;i<key.length;i++){
    				String temp = request.getParameter(key[i]);
    				values.append(temp).append(",");
    			}
    			int temp = values.lastIndexOf(",");
    			if(temp == values.length()-1){
    				values.deleteCharAt(temp);
    			}
    			XhJksqtypemsg xhjksqtypemsg = new XhJksqtypemsg();
    			xhjksqtypemsg.setXhjksq(xhjksq);
    			
    			
    		}
    	}
    	set.add(xhjksq);
    	return set;
    }
    
    /**
     * 整合后保存
     * 借款申请里面的紧急联系人集合
     * @param request
     * @return
     */
    private Set<XhJkjjlxr> getXhJkjjlxrSet(HttpServletRequest request){
    	Set<XhJkjjlxr> set = new HashSet<XhJkjjlxr>();
    	XhJkjjlxr xhJkjjlxr = null;
    	for(int i=0;i<6;i++){
    		xhJkjjlxr = new XhJkjjlxr();
    		String name = request.getParameter("name"+i);//紧急联系人姓名
    		String jjlxrgzdw = request.getParameter("jjlxrgzdw"+i);//紧急联系人工作单位
    		String jjlxrdwdzhjtzz = request.getParameter("jjlxrdwdzhjtzz"+i);//单位地址或家庭住址
    		String jjlxrlxdh = request.getParameter("jjlxrlxdh"+i);//紧急联系人联系电话
    		String ybrgx = request.getParameter("ybrgx"+i);//与本人关系
    		xhJkjjlxr.setName(name);
    		xhJkjjlxr.setJjlxrgzdw(jjlxrgzdw);
    		xhJkjjlxr.setJjlxrdwdzhjtzz(jjlxrdwdzhjtzz);
    		xhJkjjlxr.setJjlxrlxdh(jjlxrlxdh);
    		xhJkjjlxr.setYbrgx(ybrgx);
    		set.add(xhJkjjlxr);
    	}
    	return set;
    }
    
    /**
     * 借款紧急联系列表
     * @param page
     * @param params
     */
    @Transactional(readOnly = true)
    public void searchXhDkjjlxr(Page<XhJkjjlxr> page, Map<String, Object> params){
    	page = xhdkjjlxrDao.queryXhDkjjlxr(page, params);
    }
    
    /**
     * 保存借款紧急联系人
     * @param request
     * @param xhcfdkrxx
     * @return
     */
    public boolean saveLoanContact(HttpServletRequest request, XhJkjjlxr xhdkjjlxr	){
    	boolean flag = false;
    	String xhdkrxxId = request.getParameter("xhdkrxxId");
    	XhJkrxx xhcfdkrxx = new XhJkrxx();
    	xhcfdkrxx.setId(Long.parseLong(xhdkrxxId.trim()));
//    	xhdkjjlxr.setXhcfDkrxx(xhcfdkrxx);
    	xhdkjjlxrDao.save(xhdkjjlxr);
    	if(null != xhdkjjlxr.getId() && 0 != xhdkjjlxr.getId()){
    		flag = true;
    	}
    	return flag;
    }
    
    /**
     * 保存  修改的紧急联系人信息
     * @param request
     * @param xhdkjjlxr
     * @return
     */
    public boolean saveEditLoanContact(HttpServletRequest request, XhJkjjlxr xhdkjjlxr){
    	boolean flag = false;
    	XhJkjjlxr xhdkjjlxrDB = xhdkjjlxrDao.get(xhdkjjlxr.getId());
    	
    	xhdkjjlxrDB.setName(xhdkjjlxr.getName());//联系人姓名
    	xhdkjjlxrDB.setYbrgx(xhdkjjlxr.getYbrgx());//与借款人关系
    	xhdkjjlxrDB.setJjlxrgzdw(xhdkjjlxr.getJjlxrgzdw());//工作单位
    	xhdkjjlxrDB.setJjlxrlxdh(xhdkjjlxr.getJjlxrlxdh());//联系电话
    	xhdkjjlxrDB.setJjlxrdwdzhjtzz(xhdkjjlxr.getJjlxrdwdzhjtzz());//单位住址或家庭住址
    	
    	Timestamp tempTimeStamp = xhdkjjlxrDB.getLastModifyTime();//保存前的最后修改日期
    	xhdkjjlxrDB.setLastModifyTime(new Timestamp(new Date().getTime()));
    	XhJkjjlxr borrow = xhdkjjlxrDao.get(xhdkjjlxrDB.getId());
    	if(null == tempTimeStamp && null != borrow.getLastModifyTime()){
    		flag = true;
    	}else{
    		if(tempTimeStamp.getTime() != borrow.getLastModifyTime().getTime()){
    			flag = true;
    		}
    	}
    	return flag;
    }
    
    //*****借款人基础信息*****end*************************************************************
    
    
    //*****借款人信息变更申请****start***********************************************************
    /**
     * 借款人申请变更基础信息查询
     * @param page
     * @param params
     */
    @Transactional(readOnly = true)
    public void queryXhJkrxxxgjl(Page<XhJkrxx> page, Map<String, Object> params){
    	page = xhJkrxxDao.queryXhJkrxxxgjl(page, params);
    }
    
    /**
     * 借款人申请变更历史信息查询
     * @param page
     * @param params
     */
    @Transactional(readOnly = true)
    public void searchXhJkrxxxgjl(Page<XhJkrxxUPHistory> page, Map<String, Object> params, Long Id){
    	XhJkrxx xhJkrxx = new XhJkrxx();
    	xhJkrxx.setId(Id);
    	page = xhJkrxxUPHistoryDao.queryXhJkrxxxgjl(page, params, xhJkrxx);
    }

    /**
     * 添加借款人信息变更申请中的借款人带回
     * @param page
     * @param params
     */
    public void getXhJkrxxList(Page<XhJkrxx> page, Map<String, Object> params){
    	xhJkrxxDao.getXhJkrxxList(page, params);
    }
    
    /**
     * 保存借款人变更申请
     * @param request
     * @param xhcfdkrxxxgjl
     * @return
     */
    public boolean saveLoanChangeappli(HttpServletRequest request,XhJkrxxUPHistory xhcfdkrxxxgjl){
    	boolean flag = false;
    	xhJkrxxUPHistoryDao.save(xhcfdkrxxxgjl);
    	flag = true;
    	return flag;
    }
    
    /**
     * 查看借款人变更信息
     * @param Id
     * @return
     */
    @Transactional(readOnly = true)
    public XhJkrxxUPHistory getXhJkrxxUPHistory(Long Id){
    	return xhJkrxxUPHistoryDao.get(Id);
    }
    //*****借款人信息变更申请****start***********************************************************
	
	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Autowired
	public void setXhcfdkrxxDao(XhJkrxxDao xhcfdkrxxDao) {
		this.xhJkrxxDao = xhcfdkrxxDao;
	}

	@Autowired
	public void setXhcfdkrxxxgjlDao(XhJkrxxUPHistoryDao xhcfdkrxxxgjlDao) {
		this.xhJkrxxUPHistoryDao = xhcfdkrxxxgjlDao;
	}

	@Autowired
	public void setXhdkjjlxrDao(XhJkjjlxrDao xhdkjjlxrDao) {
		this.xhdkjjlxrDao = xhdkjjlxrDao;
	}

	@Autowired
	public void setTempletDao(TempletDao templetDao) {
		this.templetDao = templetDao;
	}

	

}
