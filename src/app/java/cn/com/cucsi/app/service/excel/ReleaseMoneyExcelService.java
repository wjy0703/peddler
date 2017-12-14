package cn.com.cucsi.app.service.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.dao.baseinfo.MiddleManDao;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.app.web.util.MetaDataTypeEnum;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class ReleaseMoneyExcelService   extends AbstractExcelService{
    
    private static Logger logger = LoggerFactory.getLogger(ReleaseMoneyExcelService.class);
    
    private static final String QUERY_STR = "makeLoanList";
    
    @Autowired	
	private NamedJdbcDao jdbcDao;
    
    @Autowired
    private AttrCacheManager attrCacheManager;

    @Autowired
    private MiddleManDao middleManDao;
    
    
    /**
     * 
     * 根据条件查询中间人
     * @param name                  姓名
     * @param bankName              银行名
     * @param bankAccountNumber     银行帐号 
     * @return
     * @author xjs
     * @date 2013-8-8 下午1:28:23
     */
    @Transactional(readOnly = true)
    public List<MiddleMan> getMiddleManByNameAndBank(String name,String bankName,String bankAccountNumber) {
        return middleManDao.getMiddleManByNameAndBank(name, bankName, bankAccountNumber);
    }

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksq(Map<String, Object> filter, JdbcPage page) {
	    generateSql(filter,page);
		return jdbcDao.searchPagesByMergeSqlTemplate(QUERY_STR, filter,	page);
	}
	
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> searchXhJksqH(Map<String, Object> filter, JdbcPage page) {
        generateSql(filter,page);
        return jdbcDao.searchPagesByMergeSqlTemplate(QUERY_STR, filter, page);
    }

    @Override
    public List<Map<String, Object>> queryRows(Map<String, Object> params) {
        generateSql(params,null);
        List<Map<String, Object>> datas = jdbcDao.searchByMergeSqlTemplate(QUERY_STR,params);
        for(int index = 0 ; index < datas.size() ; index++ ){
            Map<String, Object> data = datas.get(index);
            data.put("JK_TYPE", attrCacheManager.getAttrName(MetaDataTypeEnum.PRODUCT_TYPE.toString(), data.get("JK_TYPE")!=null ?data.get("JK_TYPE").toString() :"") );
        }
        return datas;
    }
	
    /**
     * 根据条件形成SQL语句 
     *
     * @param filter
     * @param page
     * @author xjs
     * @date 2013-8-8 下午4:29:48
     */
    private void generateSql(Map<String, Object> filter,JdbcPage page){
        logger.debug("查询条件： " + filter);
        // and a.jkrxm like ? and a.ZJHM = ? and a.state = ? and a.PROVINCE = ? and a.CITY = ?
        
        StringBuffer  sqlBuffer = new StringBuffer();
        if (filter.containsKey("jkrxm")) {// 借款人姓名
            String jkrxm  = String.valueOf(filter.get("jkrxm"));
            if (StringUtils.isNotEmpty(jkrxm)) {
                filter.put("jkrxm", "%" + jkrxm + "%");
                sqlBuffer.append(" AND A.JKRXM LIKE :jkrxm");
            }
        }

        if (filter.containsKey("zjhm")) {// 证件号码
            if (StringUtils.isNotEmpty(String.valueOf(filter.get("zjhm")))) {
                sqlBuffer.append(" AND A.ZJHM = :zjhm");
            }
        }
        
        if (filter.containsKey("loancode")) {// 合同编号
            String loancode  = String.valueOf(filter.get("loancode"));
            if (StringUtils.isNotEmpty(loancode)) {
                filter.put("loancode", "%" + loancode + "%");
                sqlBuffer.append(" AND A.LOAN_CODE LIKE :loancode");
            }
        }
        
        if (filter.containsKey("banknum")) {// 银行卡卡号
            String banknum  = String.valueOf(filter.get("banknum"));
            if (StringUtils.isNotEmpty(banknum)) {
                filter.put("banknum", "%" + banknum + "%");
                sqlBuffer.append(" AND A.BANK_NUM LIKE :banknum");
            }
        }
        
        if (filter.containsKey("backup01")) {// 证件号码
        	String backup01 = String.valueOf(filter.get("backup01"));
            if (StringUtils.isNotEmpty(backup01)) {
            	filter.put("backup01", backup01);
                sqlBuffer.append(" AND A.BACKUP01 = :backup01");
            }
        }
               
        if (filter.containsKey("state")) {// 状态
            String state = String.valueOf(filter.get("state"));
            if (StringUtils.isNotEmpty(state)) {
                //需要转换，因为原来map中为非字符串类型。不严谨啊
                filter.put("state", state);
                sqlBuffer.append(" AND A.STATE = :state");
            }
        }
     
        if (filter.containsKey("province")) {// 所属省份
            if (StringUtils.isNotEmpty(String.valueOf(filter.get("province")))) {
                sqlBuffer.append(" AND A.PROVINCE = :province");
            }
        }

        if (filter.containsKey("city")) {// 所属城市
            if (StringUtils.isNotEmpty(String.valueOf(filter.get("city")))) {
                sqlBuffer.append(" AND A.CITY = :city");
            }
        }
        
        // and b.employee_crm in (select emp.id from base_employee emp where emp.name like '%於友军%' ) 
        //and b.employee_cca in (select emp.id from base_employee emp where emp.name like '%陶正%' )
        
      
        
        if (filter.containsKey("saleName")) {// 客户经理
            if (StringUtils.isNotEmpty(String.valueOf(filter.get("saleName")))) {
                sqlBuffer.append(" AND B.EMPLOYEE_CCA IN (SELECT EMP.ID FROM BASE_EMPLOYEE EMP WHERE EMP.NAME = :saleName)");
            }
        }
        
        if (filter.containsKey("teamName")) {// 团队经理
            if (StringUtils.isNotEmpty(String.valueOf(filter.get("teamName")))) {
                sqlBuffer.append("  AND B.EMPLOYEE_CRM IN (SELECT EMP.ID FROM BASE_EMPLOYEE EMP WHERE EMP.NAME =:teamName)");
            }
        }
       
 
        // 级联查询sql
        sqlBuffer.append(PropertiesUtils.getLoanSql());
        
        if(page != null){
            if (page.getOrderBy() != null) {
                sqlBuffer.append(" order by " + page.getOrderBy() + " " + page.getOrder());
            } else {
                sqlBuffer.append(" order by a.CREATE_TIME desc ");
    
            }
        }
        logger.debug("形成的Sql语句为： " + sqlBuffer.toString());
        String sql2 = "";
        
        if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
			String value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql2 = sql2 + " and yybid = " + value + "";
				}
			}
		}
        
        filter.put("whereClause",sqlBuffer.toString());
        filter.put("sql2",sql2);
    }
    
	
}
