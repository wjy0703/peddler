package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhUploadFilesDao;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app2.dao.xhcf.XhCjksqSurveyMainDao;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyMain;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCjksqSurveyMainManager {
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	private XhCjksqSurveyMainDao xhCjksqSurveyMainDao;
	@Autowired
	public void setXhCjksqSurveyMainDao(XhCjksqSurveyMainDao xhCjksqSurveyMainDao) {
		this.xhCjksqSurveyMainDao = xhCjksqSurveyMainDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	private XhJksqManager xhjksqManager;
	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}
	
	private XhUploadFilesDao xhUploadFilesDao;
	@Autowired
	public void setXhUploadFilesDao(XhUploadFilesDao xhUploadFilesDao) {
		this.xhUploadFilesDao = xhUploadFilesDao;
	}
	
	@Transactional(readOnly = true)
	public Page<XhCjksqSurveyMain> searchXhCjksqSurveyMain(final Page<XhCjksqSurveyMain> page, final Map<String,Object> filters) {
		return xhCjksqSurveyMainDao.queryXhCjksqSurveyMain(page, filters);
	}
	

	@Transactional(readOnly = true)
	public XhCjksqSurveyMain getXhCjksqSurveyMain(Long id) {
		return xhCjksqSurveyMainDao.get(id);
	}

	public void saveXhCjksqSurveyMain(XhCjksqSurveyMain entity) {
		XhJksq jksq=xhjksqManager.getXhJksq(entity.getJksqId());
		jksq.setState("31.A");
		xhCjksqSurveyMainDao.save(entity);
		baseInfoManager.saveXhJksqState(jksq, "等待外访结果", "待外访");
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjksqSurveyMain(Long id) {
		xhCjksqSurveyMainDao.delete(id);
	}
	
	public boolean batchDelXhCjksqSurveyMain(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjksqSurveyMain(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjksqSurveyMain(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//外访要求的上传表格（多个用，隔开)
		if(filter.containsKey("demandWordTemplate")){
			value = String.valueOf(filter.get("demandWordTemplate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEMAND_WORD_TEMPLATE = '" +  value + "'";
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
	 * 通过借款申请ID取得对应的实体
	 *
	 * @param jksqId   借款申请ID
	 * @date 2013-10-26 上午10:16:04
	 */
	@Transactional(readOnly=true)
    public XhCjksqSurveyMain getXhCjksqSurveyMainFromJksqId(Long jksqId) {
              String hql = "from XhCjksqSurveyMain  xhCjksqSurveyMain where xhCjksqSurveyMain.jksqId = :jksqId order by createTime desc";
              Map<String,Object> params = new HashMap<String,Object>();
              params.put("jksqId", jksqId);
              List<XhCjksqSurveyMain> surveyMains = xhCjksqSurveyMainDao.find(hql,params);
              if(surveyMains != null && surveyMains.size() >= 1)
                  return surveyMains.get(0);
              else 
                  return new XhCjksqSurveyMain();
    }
	
	
	public void storeSaveXhCjksqSurveyMain(XhCjksqSurveyMain surveyMain) {
		XhJksq jksq=xhjksqManager.getXhJksq(surveyMain.getJksqId());
		jksq.setState("31.C");
		baseInfoManager.saveXhJksqState(jksq, "上传外访反馈资料成功", "上传外访反馈资料");
        xhCjksqSurveyMainDao.save(surveyMain);
    }
	
	public void uploadFiles(Long Id,List<Map<String, String>> fileName,
							String uploadFlag, String FileOwner){
		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if (fileName.size() > 0) {
			for (Map<String, String> m : fileName) {
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("surveyBackFiles");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner(FileOwner);
				xhUploadFiles.setMainId(Id);
				xhUploadFiles.setFlag(uploadFlag);
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
			}
		}
	}
	
	/**
	 * 根据xhUploadFiles中的id查询单条记录
	 */
	public XhUploadFiles getxhUploadFile(Long id){
		return xhUploadFilesDao.get(id);
	}

}
