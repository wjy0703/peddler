package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhCjrgtjlDao;
import cn.com.cucsi.app.entity.xhcf.XhCjrgtjl;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCjrgtjlManager {

	private XhCjrgtjlDao xhCjrgtjlDao;
	@Autowired
	public void setXhCjrgtjlDao(XhCjrgtjlDao xhCjrgtjlDao) {
		this.xhCjrgtjlDao = xhCjrgtjlDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCjrgtjl> searchXhCjrgtjl(final Page<XhCjrgtjl> page, final Map<String,Object> filters) {
		return xhCjrgtjlDao.queryXhCjrgtjl(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCjrgtjl getXhCjrgtjl(Long id) {
		return xhCjrgtjlDao.get(id);
	}

	public void saveXhCjrgtjl(XhCjrgtjl entity) {
		xhCjrgtjlDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjrgtjl(Long id) {
		xhCjrgtjlDao.delete(id);
	}
	
	public boolean batchDelXhCjrgtjl(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjrgtjl(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjrgtjl(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//意向出资日期
		if(filter.containsKey("yxczrq")){
			value = String.valueOf(filter.get("yxczrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YXCZRQ = '" +  value + "'";
			}
		}
		//下次联系日期
		if(filter.containsKey("xclxrq")){
			value = String.valueOf(filter.get("xclxrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XCLXRQ = '" +  value + "'";
			}
		}
		//本次沟通日期
		if(filter.containsKey("bcgtrq")){
			value = String.valueOf(filter.get("bcgtrq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BCGTRQ = '" +  value + "'";
			}
		}
		//沟通开始时间
		if(filter.containsKey("gtkssj")){
			value = String.valueOf(filter.get("gtkssj"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.GTKSSJ = '" +  value + "'";
			}
		}
		//沟通结束时间
		if(filter.containsKey("gtjssj")){
			value = String.valueOf(filter.get("gtjssj"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.GTJSSJ = '" +  value + "'";
			}
		}
		//本次联系人
		if(filter.containsKey("bclxr")){
			value = String.valueOf(filter.get("bclxr"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BCLXR = '" +  value + "'";
			}
		}
		//沟通内容描述
		if(filter.containsKey("gtnrms")){
			value = String.valueOf(filter.get("gtnrms"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.GTNRMS = '" +  value + "'";
			}
		}
		//本次沟通方式
		if(filter.containsKey("bcgtfs")){
			value = String.valueOf(filter.get("bcgtfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BCGTFS = '" +  value + "'";
			}
		}
		//意向产品
		if(filter.containsKey("yxcp")){
			value = String.valueOf(filter.get("yxcp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YXCP = '" +  value + "'";
			}
		}
		//下次联系方式
		if(filter.containsKey("xclxfs")){
			value = String.valueOf(filter.get("xclxfs"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XCLXFS = '" +  value + "'";
			}
		}
		//客户意向
		if(filter.containsKey("khyx")){
			value = String.valueOf(filter.get("khyx"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.KHYX = '" +  value + "'";
			}
		}
		//客户状态。0.投资咨询，1.出借人
		if(filter.containsKey("cjrState")){
			value = String.valueOf(filter.get("cjrState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CJR_STATE = '" +  value + "'";
			}
		}
		//意向出资金额
		if(filter.containsKey("yxczje")){
			value = String.valueOf(filter.get("yxczje"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YXCZJE = '" +  value + "'";
			}
		}
		
//		if (page.getOrderBy()!=null){
//			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
//		}
		sql = sql + " order by  a.CREATE_TIME desc";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
}
