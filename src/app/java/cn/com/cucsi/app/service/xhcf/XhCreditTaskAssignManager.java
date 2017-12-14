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
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.xhcf.XhCreditTaskAssignDao;
import cn.com.cucsi.app.dao.xhcf.XhUploadFilesDao;
import cn.com.cucsi.app.entity.xhcf.XhCreditTaskAssign;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCreditTaskAssignManager {

	private BaseInfoManager baseInfoManager;

	private XhCreditTaskAssignDao xhCreditTaskAssignDao;
	private XhJksqDao loanApplyDao;
	private XhJksqStateDao xhJksqStateDao;
	private XhUploadFilesDao xhUploadFilesDao;
   

	
	@Autowired
	public void setLoanApplyDao(XhJksqDao loanApplyDao) {
		this.loanApplyDao = loanApplyDao;
	}

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	public void setXhCreditTaskAssignDao(
			XhCreditTaskAssignDao xhCreditTaskAssignDao) {
		this.xhCreditTaskAssignDao = xhCreditTaskAssignDao;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhUploadFilesDao(XhUploadFilesDao xhUploadFilesDao) {
		this.xhUploadFilesDao = xhUploadFilesDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhCreditTaskAssign> searchXhCreditTaskAssign(
			final Page<XhCreditTaskAssign> page,
			final Map<String, Object> filters) {
		return xhCreditTaskAssignDao.queryXhCreditTaskAssign(page, filters);
	}

	@Transactional(readOnly = true)
	public XhCreditTaskAssign getXhCreditTaskAssign(Long id) {
		return xhCreditTaskAssignDao.get(id);
	}

	/**
	 * 保存信审分派内容，同时修改申请的状态及历史状态
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveXhCreditTaskAssign(XhCreditTaskAssign entity) {
		XhJksq loanAppley = entity.getLoanApply();
		loanAppley.setState("31");
//		XhJksqState state = new XhJksqState();
//		state.setXhjksq(loanAppley);
//		state.setDescribe("完成任务分派,待初审");
//		xhJksqStateDao.save(state);
		baseInfoManager.saveXhJksqState(loanAppley, "完成任务分派,待初审", "");
		loanApplyDao.save(loanAppley);
		xhCreditTaskAssignDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCreditTaskAssign(Long id) {
		xhCreditTaskAssignDao.delete(id);
	}

	public boolean batchDelXhCreditTaskAssign(String[] ids) {
		try {
			for (String id : ids) {
				deleteXhCreditTaskAssign(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhCreditTaskAssign(String queryName,
			Map<String, Object> filter) {
		String sql = "";
		String value = "";
		// 借款申请ID
		if (filter.containsKey("loanApplyId")) {
			value = String.valueOf(filter.get("loanApplyId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_APPLY_ID = '" + value + "'";
			}
		}
		// 初审人员ID
		if (filter.containsKey("firstAuditEmployeeId")) {
			value = String.valueOf(filter.get("firstAuditEmployeeId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_AUDIT_EMPLOYEE_ID = '" + value + "'";
			}
		}
		// 復审人员ID
				if (filter.containsKey("secondAuditEmployeeId")) {
					value = String.valueOf(filter.get("secondAuditEmployeeId"));
					if (StringUtils.isNotEmpty(value)) {
						sql = sql + " and a.SECOND_AUDIT_EMPLOYEE_ID = '" + value + "'";
						System.out.println("sql=======>" + sql);
					}
				}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		//jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}
	
	
	public boolean uploadFile(HttpServletRequest request,
			List<Map<String, String>> fileName) {
		boolean flag = true;
		String values = request.getParameter("id");
		String[] value = values.split(",");
		Long Id = Long.parseLong(value[0]);
		String upFlag = value[1];// 1:初审;2:复审;3:终审

		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if (fileName.size() > 0) {
			for (Map<String, String> m : fileName) {
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner("XH_JKSQ");
				xhUploadFiles.setMainId(Id);
				xhUploadFiles.setFlag(upFlag);
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
			}
		}
		return flag;
	}

	@Transactional(readOnly = true)
	public List<XhUploadFiles> downLoadFile(HttpServletRequest request,
			Long Id, String upflag) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("mainId", Id);
		filters.put("flag", upflag);
		List<XhUploadFiles> list = xhUploadFilesDao.findXhUploadFiles(filters);

		return list;
	}

	@Transactional(readOnly = true)
	public XhUploadFiles getXhUploadFiles(Long Id) {
		return xhUploadFilesDao.get(Id);
	}
	
	/**
	 * 查询分配的记录
	 * @param loanApplyId 
	 * @return
	 */
	public XhCreditTaskAssign getLastXhCreditTaskAssign(String loanApplyId){
/*		Page<XhCreditTaskAssign> page = new Page<XhCreditTaskAssign>();
		page.setPageNo(1);
		page.setPageSize(1000);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loanApplyId", loanApplyId);
		page = xhCreditTaskAssignDao.queryXhCreditTaskAssign(page,map);
		List<XhCreditTaskAssign> tasks = page.getResult();*/
		
		//采用分分页查询，这里写错，把下面4行去掉，再把上面的块注释去掉
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loanApplyId", Long.valueOf(loanApplyId));
		String hql = "from XhCreditTaskAssign xhCreditTaskAssign where loanApply.id = :loanApplyId";
		List<XhCreditTaskAssign> tasks = xhCreditTaskAssignDao.find(hql, map);
		//
		
		if(tasks == null)
			return null;
		XhCreditTaskAssign xhCreditTaskAssign  =  tasks.get(0);

		for(int index = 1 ; index < tasks.size(); index++){
			XhCreditTaskAssign tmp = tasks.get(index);
			if (tmp.getCreateTime().getTime() > xhCreditTaskAssign.getCreateTime().getTime())
				xhCreditTaskAssign = tmp;
		}
		return xhCreditTaskAssign;			
	}
	
	public List<XhCreditTaskAssign> getXhCreditTaskAssignByJksqId(Long jksqId){
		return xhCreditTaskAssignDao.getXhCreditTaskAssignByJksqId(jksqId);
	}
}
