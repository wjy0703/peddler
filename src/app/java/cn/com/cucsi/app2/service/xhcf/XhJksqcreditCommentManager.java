package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhJksqcreditCommentDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqcreditComment;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhJksqcreditCommentManager {

	private XhJksqcreditCommentDao xhJksqcreditCommentDao;
	@Autowired
	public void setXhJksqcreditCommentDao(XhJksqcreditCommentDao xhJksqcreditCommentDao) {
		this.xhJksqcreditCommentDao = xhJksqcreditCommentDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqcreditComment> searchXhJksqcreditComment(final Page<XhJksqcreditComment> page, final Map<String,Object> filters) {
		return xhJksqcreditCommentDao.queryXhJksqcreditComment(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqcreditComment getXhJksqcreditComment(Long id) {
		return xhJksqcreditCommentDao.get(id);
	}

	public void saveXhJksqcreditComment(XhJksqcreditComment entity) {
		xhJksqcreditCommentDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqcreditComment(Long id) {
		xhJksqcreditCommentDao.delete(id);
	}
	
	public boolean batchDelXhJksqcreditComment(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhJksqcreditComment(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqcreditComment(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//114/10000查询 之（是，否，其他）
		if(filter.containsKey("onefour")){
			value = String.valueOf(filter.get("onefour"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ONEFOUR = '" +  value + "'";
			}
		}
		//114/10000查询之备注
		if(filter.containsKey("onefourComment")){
			value = String.valueOf(filter.get("onefourComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ONEFOUR_COMMENT = '" +  value + "'";
			}
		}
		//红盾网/工商局网之(是，否，其他)
		if(filter.containsKey("gzaic")){
			value = String.valueOf(filter.get("gzaic"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.GZAIC = '" +  value + "'";
			}
		}
		//红盾网/工商局网之备注
		if(filter.containsKey("gzaicComment")){
			value = String.valueOf(filter.get("gzaicComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.GZAIC_COMMENT = '" +  value + "'";
			}
		}
		//百度网查公司/个人信息之(是，否，其他)
		if(filter.containsKey("baidu")){
			value = String.valueOf(filter.get("baidu"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BAIDU = '" +  value + "'";
			}
		}
		//百度网查公司/个人信息之备注
		if(filter.containsKey("baiduComment")){
			value = String.valueOf(filter.get("baiduComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BAIDU_COMMENT = '" +  value + "'";
			}
		}
		//P2P网络逾期黑名单查询之(是，否，其他)
		if(filter.containsKey("ptopnet")){
			value = String.valueOf(filter.get("ptopnet"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PTOPNET = '" +  value + "'";
			}
		}
		//P2P网络逾期黑名单查询之备注
		if(filter.containsKey("ptopnetComment2")){
			value = String.valueOf(filter.get("ptopnetComment2"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PTOPNET_COMMENT2 = '" +  value + "'";
			}
		}
		//全国法院被执行人信息查询之(是，否，其他)
		if(filter.containsKey("court")){
			value = String.valueOf(filter.get("court"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COURT = '" +  value + "'";
			}
		}
		//全国法院被执行人信息查询之备注
		if(filter.containsKey("courtComment")){
			value = String.valueOf(filter.get("courtComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COURT_COMMENT = '" +  value + "'";
			}
		}
		//其他重要资料说明及风险点
		if(filter.containsKey("othercomment")){
			value = String.valueOf(filter.get("othercomment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OTHERCOMMENT = '" +  value + "'";
			}
		}
		//114/10000查询之备注
		if(filter.containsKey("onefourComment2")){
			value = String.valueOf(filter.get("onefourComment2"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ONEFOUR_COMMENT2 = '" +  value + "'";
			}
		}
		//其他借款统计
		if(filter.containsKey("otherBorrowComment")){
			value = String.valueOf(filter.get("otherBorrowComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OTHER_BORROW_COMMENT = '" +  value + "'";
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
}
