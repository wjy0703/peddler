package cn.com.cucsi.app.service.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;

/**
 * 统计数据查询方法
 * @author Administrator
 *
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class StatisticalService {
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	/**
	 * 借款端数据导出
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> rebJksqStatis(String orgid,String date) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		Map<String, Object> reMap = new HashMap<String, Object>();
		
		//根据前台选择的组织机构，获取该组织所有下级的团队经理
		List<Map<String, Object>> listTeamManager = this.rebTeamManager(orgid);
		for(Map<String, Object> tm : listTeamManager){
			reMap = new HashMap<String, Object>();
			reMap.put("RGANI_NAME", tm.get("RGANI_NAME"));//团队名称
			reMap.put("NAME", tm.get("NAME"));//团队经理名称
			reMap.put("POSITION_NAME", tm.get("POSITION_NAME"));//职务名称
			reMap.put("VALIST", this.getJksq(tm.get("ID")+"", date));//客户经理列表
			reList.add(reMap);
		}
		return reList;
	}
	
	/**
	 * 借款端数据导出
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> rebJksqStatis2(String orgid,String date) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		Map<String, Object> reMap = new HashMap<String, Object>();
		//获取门店
		List<Map<String, Object>> listMendian = this.rebMendian(orgid);
		//根据前台选择的组织机构，获取该组织所有下级的团队经理
		List<Map<String, Object>> listTeamManager = new ArrayList<Map<String, Object>>();
		Map<String, Object> tmMap = new HashMap<String, Object>();
		List<Map<String, Object>> listTeamManagerNew = new ArrayList<Map<String, Object>>();
		
		for(Map<String, Object> md : listMendian){
			reMap = new HashMap<String, Object>();
			reMap.put("RGANI_NAME", md.get("RGANI_NAME"));//团队名称
			//根据前台选择的组织机构，获取该组织所有下级的城市经理
			reMap.put("CTLIST", this.rebCityManager(md.get("ID")+""));//城市经理名称
			//reMap.put("POSITION_NAME", tm.get("POSITION_NAME"));//职务名称
			//根据前台选择的组织机构，获取该组织所有下级的团队经理
			listTeamManager = this.rebTeamManager2(md.get("ID")+"");
			listTeamManagerNew = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> tm : listTeamManager){
				tmMap = new HashMap<String, Object>();
				tmMap.put("EMP_NO", tm.get("EMP_NO"));
				tmMap.put("RGANI_NAME", tm.get("RGANI_NAME"));//团队名称
				tmMap.put("NAME", tm.get("NAME"));//团队经理名称
				tmMap.put("POSITION_NAME", tm.get("POSITION_NAME"));//职务名称
				tmMap.put("VALIST", this.getJksq(tm.get("ID")+"", date));//客户经理列表
				listTeamManagerNew.add(tmMap);
			}
			reMap.put("TMLIST", listTeamManagerNew);//客户经理列表
			reList.add(reMap);
		}
		return reList;
	}
	/**
	 * 出借端数据导出
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> rebTzsqStatis(String orgid,String date) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		//获取门店
		List<Map<String, Object>> listMendian = this.rebMendianOne(orgid);
		//根据前台选择的组织机构，获取该组织所有下级的团队经理
		List<Map<String, Object>> listTeamManager = new ArrayList<Map<String, Object>>();
		Map<String, Object> tmMap = new HashMap<String, Object>();
		List<Map<String, Object>> listTeamManagerNew = new ArrayList<Map<String, Object>>();
		//客户经理
		List<Map<String, Object>> listKehuManager = new ArrayList<Map<String, Object>>();
		Map<String, Object> khMap = new HashMap<String, Object>();
		List<Map<String, Object>> listKehuManagerNew = new ArrayList<Map<String, Object>>();
		
		if(listMendian.size()==1){
			reMap = new HashMap<String, Object>();
			reMap.put("RGANI_NAME", listMendian.get(0).get("RGANI_NAME"));//团队名称
			//根据前台选择的组织机构，获取该组织所有下级的城市经理
			reMap.put("CTLIST", this.rebCityManager(listMendian.get(0).get("ID")+""));//城市经理名称
			//reMap.put("POSITION_NAME", tm.get("POSITION_NAME"));//职务名称
			//根据前台选择的组织机构，获取该组织所有下级的团队经理
			listTeamManager = this.rebTeamManager2(listMendian.get(0).get("ID")+"");
			listTeamManagerNew = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> tm : listTeamManager){
				tmMap = new HashMap<String, Object>();
				tmMap.put("RGANI_NAME", tm.get("RGANI_NAME"));//团队名称
				tmMap.put("NAME", tm.get("NAME"));//团队经理名称
				tmMap.put("POSITION_NAME", tm.get("POSITION_NAME"));//职务名称
				//tmMap.put("VALIST", this.getTzsq(tm.get("ID")+"", date));//客户经理列表
				listKehuManager = this.queryKehuYjtjList(tm.get("ID")+"", date);//出借端客户名称及汇总数据
				listKehuManagerNew = new ArrayList<Map<String, Object>>();
				for(Map<String, Object> kh : listKehuManager){
					khMap = kh;
					khMap.put("MXLIST", this.queryKehuYjmxList(kh.get("ID")+"", date));//业绩明细
					listKehuManagerNew.add(khMap);
				}
				tmMap.put("KHLIST", listKehuManagerNew);
				listTeamManagerNew.add(tmMap);
			}
			reMap.put("TMLIST", listTeamManagerNew);//客户经理列表
		}
		return reMap;
	}
	
	
	/**
	 * 团队经理查询
	 */
	@Transactional(readOnly = true)
	private List<Map<String, Object>> rebTeamManager(String orgid) {
		/*
		--出借端
	select a.id,a.rgani_name,b.name,c.position_name
from (select * from BASE_ZZJG t start with t.id = 6757
connect by nocycle t.parent_id = prior id
) a,base_employee b,BASE_POSITION c
where +a.id=b.organi_id
and b.position_id = c.id
and b.sts='0'
and c.position_code = '0002'
--借款端
select a.id,a.rgani_name,b.name,c.position_name,c.position_code
from (select * from BASE_ZZJG t start with t.id = 6760
connect by nocycle t.parent_id = prior id
) a,base_employee b,BASE_POSITION c
where +a.id=b.organi_id
and b.position_id = c.id
and b.sts='0'
and c.position_level_code in ('TUAN','CITYM')
		 */
		String sql = "select a.id,a.rgani_name,b.name,b.emp_no,c.position_name,c.position_level_code "+
						" from (select * from BASE_ZZJG t start with t.id = "+orgid+ 
						" connect by nocycle t.parent_id = prior id "+
						" ) a,base_employee b,BASE_POSITION c "+
						" where +a.id=b.organi_id "+
						" and b.position_id = c.id " +
						" and a.organi_flag='0'"+
						" and b.sts='0' "+
						" and c.position_level_code in ('TUAN','CITYM')"; 
		System.out.println("团队经理查询==>" + sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	
	/**
	 * 团队经理查询
	 */
	@Transactional(readOnly = true)
	private List<Map<String, Object>> rebTeamManager2(String orgid) {
		String sql = "select a.id,a.rgani_name,b.name,b.emp_no,c.position_name,c.position_level_code "+
						" from (select * from BASE_ZZJG t start with t.id = "+orgid+ 
						" connect by nocycle t.parent_id = prior id "+
						" ) a,base_employee b,BASE_POSITION c "+
						" where +a.id=b.organi_id "+
						" and b.position_id = c.id "+
						" and a.organi_flag='0'"+
						" and b.sts='0' "+
						" and c.position_level_code ='TUAN'"; 
		System.out.println("团队经理查询==>" + sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	/**
	 * 城市经理查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> rebCityManager(String orgid) {
		/*
	select b.name,a.rgani_name
from base_employee b,BASE_ZZJG a,BASE_POSITION c
where a.id = b.organi_id
and b.position_id = c.id
and a.id=145613
and c.position_code = '0003'
and c.position_level_code in ('TUAN','CITYM')
		 */
		String sql = "select b.name,a.rgani_name,b.emp_no " +
				" from base_employee b,BASE_ZZJG a,BASE_POSITION c " +
				" where 1=1 " +
				" and a.id = b.organi_id " +
				" and b.position_id = c.id " +
				" and a.organi_flag='0'" +
				" and b.sts='0' "+
				" and a.id= " + orgid + 
				" and c.position_level_code = 'CITYM'"; 
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	
	/**
	 * 门店查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> rebMendian(String orgid) {
		/*
	select t.id,t.rgani_name from BASE_ZZJG t 
	where t.level_mess='mendian'  start with t.id = 6759
	connect by nocycle t.parent_id = prior id
		 */
		String sql = "select t.id,t.rgani_name from BASE_ZZJG t  " +
				" where t.level_mess='mendian' and t.organi_flag='0' start with t.id = " + orgid +
				" connect by nocycle t.parent_id = prior id"; 
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	
	/**
	 * 门店查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> rebMendianOne(String orgid) {
		/*
	select t.id,t.rgani_name from BASE_ZZJG t 
	where t.level_mess='mendian'  start with t.id = 6759
	connect by nocycle t.parent_id = prior id
		 */
		String sql = "select t.id,t.rgani_name from BASE_ZZJG t  " +
				" where t.id = " + orgid;
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	
	/**
	 * 门店查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> mendianList(String mdjb) {
		/*
	select t.id,t.rgani_name from BASE_ZZJG t 
	where t.level_mess='mendian'  start with t.id = 6759
	connect by nocycle t.parent_id = prior id
		 */
		String sql = "select t.id,t.rgani_name from BASE_ZZJG t  " +
				" where t.level_mess='"+mdjb+"' and t.organi_flag='0' " +
				" start with t.parent_id=0" + 
				" connect by nocycle t.parent_id = prior id"; 
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	
	/**
	 * 团队查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> tuanduiList(String orgid) {
		/*
	select t.id,t.rgani_name from BASE_ZZJG t 
	where t.level_mess='mendian'  start with t.id = 6759
	connect by nocycle t.parent_id = prior id
		 */
		String sql = "select t.id,t.rgani_name from BASE_ZZJG t  " +
				" where 1=1 and t.organi_flag='0' and t.parent_id=" +orgid+ " order by t.rgani_name asc";
		List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		return values;
	}
	
	/**
	 * 门店查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> mendianTree(String mdjb) {
		
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> reMap = new HashMap<String, Object>();
		//获取门店
		List<Map<String, Object>> listMendian = this.mendianList(mdjb);
		for(Map<String, Object> md : listMendian){
			reMap = new HashMap<String, Object>();
			reMap.put("RGANI_NAME", md.get("RGANI_NAME"));//团队名称
			reMap.put("ID", md.get("ID"));//团队经理名称
			reMap.put("VALIST", this.tuanduiList(md.get("ID")+""));//客户经理列表
			treeList.add(reMap);
		}
		return treeList;
	}
	
	/**
	 * 门店查询
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> mendianTreeTd(Long Id) {
		return this.tuanduiList(Id+"");
	}

	/**
	 * 出借端数据
	 * @param orgid
	 * @param date
	 * @return
	 */
	@Transactional(readOnly = true)
	private List<Map<String, Object>> getTzsq(String orgid,String date) {
		/*
		 --HRB-TeamA
	select b.name,c.position_name,e.cjrxm,d.*
from base_employee b,BASE_POSITION c,xh_tzsq d,xh_cjrxx e
where 1=1
and b.position_id = c.id
and b.sts='0'
and c.position_code = '0001'
and b.organi_id = 53311
and b.id = e.employee_crm
and e.id = d.cjrxx_id
and to_char(to_date(d.jhtzrq,'yyyy-MM-dd'),'yyyy-MM') = '2013-05'
and d.state = '2'
order by b.id
		 */
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("orgid", orgid);
		conditions.put("date", date);
		//List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSqlTemplate("queryTzsqYjtjList", conditions);
		return values;
		
	}
	
	/**
	 * 借款端数据
	 * @param orgid
	 * @param date
	 * @return
	 */
	@Transactional(readOnly = true)
	private List<Map<String, Object>> getJksq(String orgid,String date) {
		/*
		--魏德怀团队
	select * from (
select b.organi_id,b.name,c.position_name,c.position_code,d.jkrxm,f.FKJE,f.HKQS 
from base_employee b,BASE_POSITION c,xh_jksq d,xh_xydkzx e,xh_jkht f
where 1=1
and b.position_id = c.id
and b.id = e.employee_cca
and e.id = d.XYDKZX_ID
and d.id = f.jksq_id
and to_char(to_date(f.QDRQ,'yyyy-MM-dd'),'yyyy-MM') = '2013-05'
and f.state = '2'
and b.sts='0'
and c.position_code in( '0007','0002')
and b.organi_id = 149607
union all
select b.organi_id,b.name,c.position_name,c.position_code,'',null,null
from base_employee b,BASE_POSITION c
where 1=1
and b.position_id = c.id
and c.position_code in( '0007','0002')
and b.organi_id = 149607
and b.id not in
(
select b.id
from base_employee b,BASE_POSITION c,xh_jksq d,xh_xydkzx e,xh_jkht f
where 1=1
and b.position_id = c.id
and b.id = e.employee_cca
and e.id = d.XYDKZX_ID
and d.id = f.jksq_id
and to_char(to_date(f.QDRQ,'yyyy-MM-dd'),'yyyy-MM') = '2013-05'
and f.state = '2'
and b.sts='0'
and c.position_code in( '0007','0002')
and b.organi_id = 149607)
) order by jkrxm  ,position_code, name
		 */
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("orgid", orgid);
		conditions.put("date", date);
		//List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSqlTemplate("queryJksqYjtjList", conditions);
		return values;
		
	}
	
	/**
	 * 借款端数据
	 * @param orgid
	 * @param date
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getJksqForPage(String orgid,String date) {
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("orgid", orgid);
		conditions.put("date", date);
		//List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSqlTemplate("queryJksqYjtjListForPage", conditions);
		return values;
		
	}
	
	/**
	 * 出借端客户名称及汇总数据
	 * @param orgid
	 * @param date
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryKehuYjtjList(String orgid,String date) {
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("orgid", orgid);
		conditions.put("date", date);
		//List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSqlTemplate("queryKehuYjtjList", conditions);
		return values;
		
	}
	
	/**
	 * 出借端业绩明细
	 * @param orgid
	 * @param date
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryKehuYjmxList(String empId,String date) {
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("empId", empId);
		conditions.put("date", date);
		//List<Map<String, Object>> values = jdbcDao.searchByMergeSql(sql);
		List<Map<String, Object>> values = jdbcDao.searchByMergeSqlTemplate("queryKehuYjmxList", conditions);
		return values;
		
	}
}
