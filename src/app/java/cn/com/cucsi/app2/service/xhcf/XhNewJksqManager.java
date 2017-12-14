package cn.com.cucsi.app2.service.xhcf;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.loan.XhJksqTogetherDao;
import cn.com.cucsi.app.dao.security.XydkzxDao;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.baseinfo.AddressManager;
import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.app.service.baseinfo.EmployeeCacheManager;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.app.web.util.JksqStateUtils;
import cn.com.cucsi.app.web.util.MetaDataTypeEnum;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhNewJksqManager extends PublicService {
	
	private final static String JKSQ_LIST_CHECK = "loanApplyJksqCheckList";
	
	@Autowired
    EmployeeCacheManager employeeCacheManager;
	
	@Autowired
    AttrCacheManager attrCacheManager;
	
	@Autowired
	AddressManager addressManager;
	
	@Autowired
	private NamedJdbcDao namedJdbcDao;

    @Autowired
    private XhJksqStateDao xhJksqStateDao;

    @Autowired
    private XydkzxDao xydkzxDao;

    @Autowired
    private XhJksqTogetherDao xhJksqTogetherDao;

    @Autowired
    private XhJksqDao xhJksqDao;

    /**
     * 保存共同借款人
     * 
     * @param xhJksqTogether
     * @author xjs
     * @param xhJksq
     * @date 2013-9-6 下午2:21:59
     */
    public void saveTogtherPerson(XhJksq xhJksq, XhJksqTogether xhJksqTogether) {
        xhJksq.setTogetherPerson("是");
        String state = xhJksq.getState();
        if ("01".equals(state)) { //等待填写共同借款人
        	//门店评分
        	if(JksqStateUtils.isShouldScore(xhJksq.getJkType())){
        		xhJksq.setState("303");
        	}else{
        		xhJksq.setState("02");
        	}
        } else {
            if (StringUtils.hasText(state)) {
                int index = state.indexOf(".B");
                if (index >= 0) {
                    xhJksq.setState(state.substring(0, index));
                }
            }
        }
        List<XhJkjjlxr> list = xhJksqTogether.getXhJkjjlxrs();
        if (list != null) {
            for (int index = 0; index < list.size(); index++) {
                XhJkjjlxr person = list.get(index);
                person.setXhJksqTogether(xhJksqTogether);
            }
        }
        xhJksqTogetherDao.save(xhJksqTogether);
        xhJksqDao.save(xhJksq);
        saveXhJksqState(xhJksq, "编辑共同借款人信息提交", "编辑共同借款人信息提交");
    }

    @Transactional(readOnly = true)
    public XhJksq getXhJksq(Long id) {
        return xhJksqDao.get(id);
    }

    public void saveXhJksq(XhJksq xhJksq) {
        xhJksqDao.save(xhJksq);
    }

    public Xydkzx getXydkzx(Long id) {
        return xydkzxDao.get(id);
    }

    public int saveXhJksq(XhJksq xhJksq, String option) {
        int check = 0;
        if (xhJksq.getId() == null) {
            // 新增操作，可能是保存或者是暂存
            Xydkzx xydkzx = getXydkzx(xhJksq.getXydkzx().getId());
            // 设置借款类型为Credit，与页面的借款类型无关
            xhJksq.setBackup01("CREDIT");
            // 咨询
            xydkzx.setZhuangTai("3");
            xhJksq.setOrgani(xydkzx.getOrgani());
            if (xydkzx.getEmployeeCca() !=null )
                xhJksq.setCustomerLeaderId(xydkzx.getEmployeeCca().getId());
            if (xydkzx.getEmployeeCrm() != null )
                xhJksq.setTeamLeaderId(xydkzx.getEmployeeCrm().getId());            
            xhJksq.setAppState("-1");// 无借款申请变更
            // 取得用户操作状态，0 暂存 1 提交
            String message = "";
            if ("0".equals(option)) {                
                xhJksq.setState("0");
                message = "用户暂存申请信息";
            } else {
                // 提交时是否选择 共同借款
                xydkzx.setLastModifyTime(new Timestamp(new Date().getTime()));
                xhJksq.setSubState("1");// 从原来代码转移过来，估计代表是已经提交

                if ("是".equals(xhJksq.getTogetherPerson())) {
                    xhJksq.setState("01");
                    message = "已提交，待填写共同还款人资料";
                } else {
                	//门店评分
                	if(JksqStateUtils.isShouldScore(xhJksq.getJkType())){
                		xhJksq.setState("303");
                		message = "已提交，待评分";
                	}else{
                		xhJksq.setState("02");
                		message = "已提交，待上传授信资料";
                	}
                }
            }
            xhJksq.setXydkzx(xydkzx);
            xhJksqDao.save(xhJksq);
            saveXhJksqState(xhJksq, message, message);
        } else {
            // 编辑操作，可能是提交，暂存，或其他编辑操作
            // 从数据库取得记录
            XhJksq xhJksqData = getXhJksq(xhJksq.getId());
            if("22".equals(xhJksqData.getState()))
                check = 1;
            try {
                // 拷贝页面的值
                new HibernateAwareBeanUtilsBean().copyProperties(xhJksqData, xhJksq);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("拷贝借款申请记录出现错误，请联系管理员");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException("拷贝借款申请记录出现错误，请联系管理员");
            }
            // 如果appstate是2，代表的是客户在修改记录。

            if ("2".equals(xhJksqData.getAppState())) {
                if ("1".equals(option)) {
                    xhJksqData.setAppState("0");
                    saveXhJksqState(xhJksqData, "已重新修改借款申请记录", "已重新修改借款申请记录");
                }
            } else {
                // 暂存后，编辑页面又点击暂存操作
                if ("0".equals(xhJksqData.getState()) && "0".equals(option)) {
                    saveXhJksqState(xhJksqData, "用户暂存申请信息", "用户暂存申请信息");
                } else if ("0".equals(xhJksqData.getState()) && "1".equals(option)) { // 代表是暂存后的首次提交
                    xhJksqData.getXydkzx().setLastModifyTime(new Timestamp(new Date().getTime()));
                    xhJksqData.setSubState("1");// 从原来代码转移过来，估计代表是已经提交
                    if ("是".equals(xhJksqData.getTogetherPerson())) {
                    	xhJksqData.setState("01");
                        saveXhJksqState(xhJksqData, "已提交，待填写共同还款人资料", "已提交，待填写共同还款人资料");
                    } else {
                      //门店评分
                    	if(JksqStateUtils.isShouldScore(xhJksqData.getJkType())){
                    		xhJksqData.setState("303");
                    		saveXhJksqState(xhJksqData, "已提交，待评分", "已提交，待评分");
                    	}else{
                    		xhJksqData.setState("02");
                    		saveXhJksqState(xhJksqData, "已提交，待上传授信资料", "已提交，待上传授信资料");
                    	}
                    }
                } else { //非一定状态
                    String message = getMessageFromState(xhJksqData);
                    saveXhJksqState(xhJksqData, message, message);
                }
            }
            if(!"0".equals(option)) //不是暂存操作
              xhJksqData.setState(changeState(xhJksqData.getState(), xhJksqData.getTogetherPerson(), option));
            xhJksqDao.save(xhJksqData);

        }
        return check;

    }
    /**
     * 根据状态获取操作需要记录的操作信息
     *
     * @param state
     * @return
     * @author xjs
     * @date 2013-9-16 下午2:01:02
     */
    private String getMessageFromState(XhJksq xhJksq) {
    	String state = xhJksq.getState();
        if(StringUtils.hasText(state)){
            int index = state.lastIndexOf(".B");
            if(index >= 0){
                return "有退回操作的--正常修改申请数据";
            }else{
                return "正常修改申请数据";
            }
        }else{
            return "状态错误";
        }
    }

    /**
     * 保存借款申请历史信息
     * 
     * @param xhJksq
     *            借款申请信息
     * @param describe
     *            历史描述
     * @param remarks
     *            历史备注
     * @return
     */
    public void saveXhJksqState(XhJksq xhJksq, String describe, String remarks) {
        XhJksqState xhJksqState = new XhJksqState();
        xhJksqState.setXhjksq(xhJksq);
        xhJksqState.setDescribe(describe);
        xhJksqState.setRemarks(remarks);
        xhJksqState.setState(xhJksq.getState());
        xhJksqStateDao.save(xhJksqState);
    }

    /**
     * 根据操作设置后续的状态
     * 
     * @param state
     * @param together
     * @return
     * @author xjs
     * @param option
     * @date 2013-9-16 上午8:53:03
     */
    private String changeState(String state, String together, String option) {
        // 如果是暂存状态，不改变状态返回
        if ("0".equals(option)) {
            return state;
        }
        String newstate = "";
        if (StringUtils.hasText(state)) {
            int index = state.lastIndexOf(".B");
            if (index >= 0) {
                newstate = state.substring(0, index);
            } else {
                if ("0".equals(state)) {// 如果是暂存状态，则根据是否为工借人
                    if ("是".equals(together)) {
                        newstate = "01";// 待添加共借人
                    } else {
                        newstate = "02";// 待上传资料
                    }
                } else {
                    if("否".equals(together) && "01".equals(state)){
                        newstate = "02"; //用户原来状态为填写共借人，然后编辑改为非共借人，则状态改为贷上传资料
                    }else if("22".equals(state)){//代表是复审情况，复审后变为代任务分配
                        newstate = "30";
                    }else{
                        newstate = state;
                    }
                }
            }
        }
        return newstate;
    }
    
    @Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksq(Map<String, Object> filter, JdbcPage page) {
        String sql = "";
        String value = "";

        if (filter.containsKey("jkrxm")) {// 借款人姓名
            value = String.valueOf(filter.get("jkrxm"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and a.jkrxm like '%" + value + "%'";
            }
        }

        if (filter.containsKey("zjhm")) {// 证件号码
            value = String.valueOf(filter.get("zjhm"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and a.ZJHM = '" + value + "'";
            }
        }
        
        if (filter.containsKey("startDate")) {// 进件开始时间 - 进件结束时间
            value = String.valueOf(filter.get("startDate"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and substr(a.backup02,'0', '10') >= '" + value + "'";
            }
        }
        
        if(filter.containsKey("endDate")){
            value = String.valueOf(filter.get("endDate"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and substr(a.backup02,'0', '10') <= '" + value + "'";
            }
        }
        
        if (filter.containsKey("state")) {// 状态
            value = String.valueOf(filter.get("state"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and a.state =  :state ";
            }
        }

        if (filter.containsKey("jkType")) {// 产品
            value = String.valueOf(filter.get("jkType"));
            if (StringUtils.hasText(value)) {
                if (!"all".equals(value)) {
                    sql = sql + " and a.JK_TYPE = '" + value + "'";
                }
            }
        }

        if (filter.containsKey("backup01")) {// 借款标志位
            value = String.valueOf(filter.get("backup01"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.BACKUP01 = '" + value + "'";
                }
            }
        }

        if (filter.containsKey("state_injksp")) {// 借款申请中的借款状态查询
            value = String.valueOf(filter.get("state_injksp"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    String[] tt = value.split(",");
                    StringBuffer values = new StringBuffer();
                    for (int i = 0; i < tt.length; i++) {
                        values.append("'").append(tt[i]).append("',");
                    }
                    int temp = values.lastIndexOf(",");
                    if (temp == values.length() - 1) {
                        values.deleteCharAt(temp);
                    }
                    sql = sql + " and a.state in (" + values.toString() + ")";
                }
            }
        }
        if (filter.containsKey("state_in")) {// 多借款状态
            value = String.valueOf(filter.get("state_in"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.state in (" + value + ")";
                }
            }
        }

        if (filter.containsKey("applyIds")) {// 多借款状态
            value = String.valueOf(filter.get("applyIds"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.id in (" + value + ")";
                }
            }
        }

        if (filter.containsKey("state_g")) {// 多借款状态--大于等于
            value = String.valueOf(filter.get("state_g"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.state >= " + value;
                }
            }
        }
       
        if (filter.containsKey("province")) {// 所属省份
            value = String.valueOf(filter.get("province"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and a.PROVINCE = '" + value + "'";
            }
        }

        if (filter.containsKey("city")) {// 所属城市
            value = String.valueOf(filter.get("city"));
            if (StringUtils.hasText(value)) {
                sql = sql + " and a.CITY = '" + value + "'";
            }
        }

        // 不在借款申请表中
        StringBuffer tablesql = new StringBuffer("");
        StringBuffer unionsql = new StringBuffer("");
        if (filter.containsKey("teamName") || filter.containsKey("saleName")) {// 团队经理
            String teamName = String.valueOf(filter.get("teamName"));
            String saleName = String.valueOf(filter.get("saleName"));

            boolean teamNameFlag = StringUtils.hasText(teamName);
            boolean saleNameFlag = StringUtils.hasText(saleName);
            if (teamNameFlag || saleNameFlag) {
                tablesql.append(" , XH_XYDKZX b  ");
                unionsql.append(" and a.XYDKZX_ID=b.ID ");
                if (teamNameFlag) {
//                  sql = sql + " and b.TEAM_NAME like '%" + teamName + "%'";
                    //借款咨询中更改团队经理字段后，修改借款申请中搜索sql
                    String tempSql = "select emp.id from base_employee emp where emp.name like '%"+teamName+"%' ";
                    sql = sql + " and b.employee_crm in (" + tempSql + ")";
                }
                if (saleNameFlag) {
//                  sql = sql + " and b.SALE_NAME like '%" + saleName + "%'";
                    //借款咨询中更改销售经理字段后，修改借款申请中搜索sql
                    String tempSql = "select emp.id from base_employee emp where emp.name like '%"+saleName+"%' ";
                    sql = sql + " and b.employee_cca in (" + tempSql + ")";
                }
            }else{
                tablesql.append(" , XH_XYDKZX b  ");
                unionsql.append(" and a.XYDKZX_ID=b.ID ");
            }
        }else{
            tablesql.append(" , XH_XYDKZX b  ");
            unionsql.append(" and a.XYDKZX_ID=b.ID ");
        }
        
        if (filter.containsKey("appState")) {// 借款申请有变更申请的
            value = String.valueOf(filter.get("appState"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.APP_STATE = '" + value + "'";
                }
            }
        }
        
        if (filter.containsKey("customerType")) {// 客户类型
            value = String.valueOf(filter.get("customerType"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and b.type = '" + value + "'";
                }
            }
        }

        if (filter.containsKey("subState")) {// 借款申请是否已提交
            value = String.valueOf(filter.get("subState"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.SUB_STATE = '" + value + "'";
                }
            }
        }

        if (filter.containsKey("XHJKSQ_ID")) {// 借款申请ID，查询变更历史
            value = String.valueOf(filter.get("XHJKSQ_ID"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql = sql + " and a.XHJKSQ_ID = '" + value + "'";
                }
            }
        }
        String sql2 = "";
        
        if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
            value = String.valueOf(filter.get("organi.id"));
            if (StringUtils.hasText(value)) {
                if (!"".equals(value)) {
                    sql2 = sql2 + " and yybid = " + value + "";
                }
            }
        }

        // 级联查询sql
        sql = sql + PropertiesUtils.getLoanSql();
        sql = sql + " order by a.CREATE_TIME desc ";
        filter.put("sql", sql);
        filter.put("sql2", sql2);
        filter.put("tablesql", tablesql.toString());
        filter.put("unionsql", unionsql.toString());

		return namedJdbcDao.searchPagesByMergeSqlTemplate(this.JKSQ_LIST_CHECK, filter,
				page);
	}
    
    /**
     * 
     * 返回具体相关的申请信息
     * @param params   ---map 主要是考虑到扩展性，主要包括cardId--证件号码     myownId --本次借款申请对应的Id，可能为空
     * @return
     * @author xjs
     * @date 2013-11-2 
     */
    public Map<String,Object> getRelatedItems(Map<String, String> params) {
        Map<String,Object> results = new HashMap<String,Object>();
        List<Map<String, Object>> myownItems = getMyownItems(params);
        List<Map<String, Object>> familyItems = getFamilyItems(params);
        List<Map<String, Object>> togetherItems = getTogherItems(params);
      
        
        
        if(listNotEmpty(myownItems)){
            results.put("myown", myownItems);
        }
        if(listNotEmpty(familyItems)){
            results.put("family", familyItems);
        }
        if(listNotEmpty(togetherItems)){
            results.put("together",togetherItems );
        }
        //可提交
        boolean canSubmit = isCanSubmit(results);
        if(canSubmit){
            results.put("canSubmit", "1");
        }else{
            results.put("canSubmit", "0");
        }

        resetMapWithEnums(myownItems);
        resetMapWithEnums(familyItems);
        resetMapWithEnums(togetherItems);
        
        return results;
        
    }
    /**
     * 是否可以提交
     *
     * @param results
     * @return
     * @author xjs
     * @date 2013-11-3 
     */
    private boolean isCanSubmit(Map<String, Object> results) {
        //取得能够重复申请的状态
        List<String> passedState = new ArrayList<String>();
        List<Map<String, Object>> passedMapState = attrCacheManager.getAttrByCoding(MetaDataTypeEnum.JKSQ_PASSED_STATE);
        if(passedMapState != null){
            for(Map<String, Object> item: passedMapState){
                passedState.add(item.get("VALUE").toString());
            }
        }
        //取得是否控制 1为是
        if(results.containsKey("myown")){
            String control = attrCacheManager.getAttrName(MetaDataTypeEnum.JKSQ_TYPE_PASSED_STATE_CONTROL, "myown");
            if("1".equals(control)){
                List<Map<String, Object>> items = (List<Map<String, Object>>)results.get("myown");
                for(Map<String, Object> item: items){
                    String state = item.get("STATE") != null ? item.get("STATE").toString() : "";
                    //如果不包括在可以重复申请状态中返回假
                    if(StringUtils.hasText(state) && !passedState.contains(state))
                        return false;
                }   
            }
        }
        if(results.containsKey("family")){
            String control = attrCacheManager.getAttrName(MetaDataTypeEnum.JKSQ_TYPE_PASSED_STATE_CONTROL, "family");
            if("1".equals(control)){
                List<Map<String, Object>> items = (List<Map<String, Object>>)results.get("family");
                for(Map<String, Object> item: items){
                    String state = item.get("STATE") != null ? item.get("STATE").toString() : "";
                    //如果不包括在可以重复申请状态中返回假
                    if(StringUtils.hasText(state) && !passedState.contains(state))
                        return false;
                }   
            }
        }
        if(results.containsKey("together")){
            String control = attrCacheManager.getAttrName(MetaDataTypeEnum.JKSQ_TYPE_PASSED_STATE_CONTROL, "together");
            if("1".equals(control)){
                List<Map<String, Object>> items = (List<Map<String, Object>>)results.get("together");
                for(Map<String, Object> item: items){
                    String state = item.get("STATE") != null ? item.get("STATE").toString() : "";
                    //如果不包括在可以重复申请状态中返回假
                    if(StringUtils.hasText(state) && !passedState.contains(state))
                        return false;
                }   
            }
        }
      
        
        return true;
    }

    /**
     * 用枚举类的值替换变量
     *
     * @param items
     * @return
     * @author xjs
     * @date 2013-11-2 
     */
    private void resetMapWithEnums(List<Map<String, Object>> items) {
          //"SELECT JKSQ.ID,JKSQ.JKRXM,JKSQ.STATE,JKSQ.BACKUP01 AS APPLYTIME, JKSQ.JK_TYPE,JKSQ.CITY ";
          if(items == null)
              return;
          for (Iterator<Map<String, Object>> iterator = items.iterator(); iterator.hasNext();) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            if(map.get("CITY") != null && StringUtils.hasText(map.get("CITY").toString())){
                map.put("CITY", addressManager.getCityNameById(Long.parseLong(map.get("CITY").toString())));
            }
            if(map.get("JK_TYPE") != null && StringUtils.hasText(map.get("JK_TYPE").toString())){
                map.put("JK_TYPE", attrCacheManager.getAttrName(MetaDataTypeEnum.PRODUCT_TYPE.toString(), map.get("JK_TYPE").toString()));
            }
            if(map.get("STATE") != null && StringUtils.hasText(map.get("STATE").toString())){
                map.put("STATE", attrCacheManager.getAttrName(MetaDataTypeEnum.JKSQ_STATE.toString(), map.get("STATE").toString()));
            }
            
            if(map.get("TEAM") != null && StringUtils.hasText(map.get("TEAM").toString())){
                map.put("TEAM", employeeCacheManager.getName(Long.parseLong(map.get("TEAM").toString())));
            }
            
            if(map.get("CUSTOMER") != null && StringUtils.hasText(map.get("CUSTOMER").toString())){
                map.put("CUSTOMER", employeeCacheManager.getName(Long.parseLong(map.get("CUSTOMER").toString())));
            }
            
            
            
        }
    }

    /**
     * 判断list 不为空
     *
     * @param myownItems
     * @return
     * @author xjs
     * @date 2013-11-2 
     */
    private boolean listNotEmpty(List<Map<String, Object>> list) {
        return list != null && !list.isEmpty();
    }

    private  String ITEM_SQL = "SELECT JKSQ.ID,JKSQ.JKRXM,JKSQ.ZJHM,JKSQ.STATE,JKSQ.BACKUP02 AS APPLYTIME, JKSQ.JK_TYPE,JKSQ.CITY,JKSQ.CUSTOMER_LEADER_ID AS CUSTOMER,JKSQ.TEAM_LEADER_ID AS TEAM,JKSQ.JK_LOAN_QUOTA AS MONEY,JKSQ.JK_CYCLE AS JKCYCLE ";

   

    private List<Map<String, Object>> getFamilyItems(Map<String, String> params) {
        String myownId = params.get("myownId");
        String sql = ITEM_SQL + " FROM XH_JKSQ JKSQ WHERE JKSQ.SPOUSE_ZJHM = :cardId ";
        if(StringUtils.hasText(myownId)){
            sql += " AND JKSQ.ID != :myownId ";
        }
        return namedJdbcDao.searchBySql(sql, params);
    }
    private List<Map<String, Object>> getMyownItems(Map<String, String> params) {
        String myownId = params.get("myownId");
        String sql = ITEM_SQL + " FROM XH_JKSQ JKSQ WHERE JKSQ.ZJHM = :cardId ";
        if(StringUtils.hasText(myownId)){
            sql += " AND JKSQ.ID != :myownId ";
        }
        return namedJdbcDao.searchBySql(sql, params);
    }
    
    private List<Map<String, Object>> getTogherItems(Map<String, String> params) {
        String myownId = params.get("myownId");
        String sql = ITEM_SQL + " FROM XH_JKSQ_TOGETHER TOGETHER  JOIN XH_JKSQ JKSQ ON (TOGETHER.XHJKSQ_ID = JKSQ.ID) AND IDENTIFICATION = :cardId ";
        if(StringUtils.hasText(myownId)){
            sql += " AND JKSQ.ID != :myownId ";
        }
        return namedJdbcDao.searchBySql(sql, params);
    }
    
    
    
}
