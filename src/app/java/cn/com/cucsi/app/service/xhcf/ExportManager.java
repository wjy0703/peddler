package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.entity.xhcf.ExportJkhz;
import cn.com.cucsi.app.entity.xhcf.ExportLczx;
import cn.com.cucsi.app.entity.xhcf.ExportXstj;
import cn.com.cucsi.app.entity.xhcf.ExportZxtj;
import cn.com.cucsi.app.web.util.ExportExcel;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

/**
 * 统计分析类
 * 
 * @author 于茂林
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ExportManager {
	
    private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	/**借款申请查询*/
	public List<Map<String,Object>> getJksq(String value){
		String sql="select t.city,(select name from base_city z where z.id=t.city) as CITYNAME,t.jkrxm ,t.zjhm,t.loan_code," +
		" d.employee_cca ,(select name from BASE_EMPLOYEE z where z.id = d.employee_cca ) as TEAMNAME,"+
        " d.employee_crm ,(select name from BASE_EMPLOYEE z where z.id = d.employee_crm ) as SALENAME," +
        "decode(t.JK_TYPE,'A','老板贷','B','老板楼易贷','C','薪水贷','D','薪水楼易贷','E','精英贷') JK_TYPE_INFO," +
        " t.jk_loan_date,a.htje,a.yzhfl,a.zxf,a.hkqs,a.fkje,a.qshkrq, b.make_loan_date"+
        " from XH_JKSQ t,XH_JKHT a,XH_MAKE_LOANS b,XH_XYDKZX d"+
        " where t.id=a.jksq_id and a.id=b.loan_contract_id and  d.ID=t.XYDKZX_ID";
					sql += value;
					System.out.println("ms_acct_city  sql----->" + sql);
					return jdbcDao.searchByMergeSql(sql);
	}
	/**借款申请查询总计*/
	public List<Map<String,Object>> getJksqCount(String value){
		String sql="select sum(a.htje) as HTJE,sum(a.zxf) as ZXF,sum(a.fkje) as FKJE from XH_JKSQ t,XH_JKHT a,XH_MAKE_LOANS b where t.id=a.jksq_id and a.id=b.loan_contract_id";
		sql += value;
		System.out.println("ms_acct_city  sql----->" + sql);
		return jdbcDao.searchByMergeSql(sql);
	}
	/**申请单导出*/
	public String exportJksq(String path,String value) throws IOException{
		ExportExcel<ExportJkhz> ex = new ExportExcel<ExportJkhz>();
		String[] headers = {"序号", "机构", "借款人姓名","借款人身份证号","借款编码",
				"贷款产品","合同金额","贷款性质",
				"综合费率",
				"信访咨询费","贷款期数","放款金额","放款时间",
				"到期时间","个贷团队经理","个贷经理",
				"申请日期","外访员","外访日期","审批人","审批时间","审批团队经理"};
    	List<ExportJkhz> dataset = new ArrayList<ExportJkhz>();
    	int i = 1;
    	List<Map<String, Object>> list = this.getJksq(value);
    	for(int a= 0; a < list.size();a++){
    		dataset.add(new ExportJkhz(i+"", list.get(a).get("CITYNAME")+"", //性质
    				list.get(a).get("JKRXM")+"", //贷款姓名
    				list.get(a).get("ZJHM")+"", //贷款人身份证号
    				list.get(a).get("LOAN_CODE")+"", //贷款编码
    				list.get(a).get("JK_TYPE_INFO")+"",//贷款产品
    				list.get(a).get("HTJE")+"", //合同金额
    				"", //贷款性质
    				list.get(a).get("YZHFL")+"", //综合费率
    				list.get(a).get("ZXF")+"",//咨询费
    				list.get(a).get("HKQS")+"", //贷款期数
    				list.get(a).get("FKJE")+"", //放款金额
    				list.get(a).get("MAKE_LOAN_DATE")+"", //放款时间
    				list.get(a).get("QSHKRQ")+"", //到期时间
    				list.get(a).get("TEAMNAME")+"", //个贷团队经理
    				list.get(a).get("SALENAME")+"", //个贷经理
    				list.get(a).get("JK_LOAN_DATE")+"", //申请日期
    				"", //信访人员
    				"", //审批人
    				"", //审批时间
    				""//审批团队经理
    		));
    		i++;
    	}
    	List<Map<String, Object>> list2 = this.getJksqCount(value);
    	for(int a= 0; a < list2.size();a++){
    		dataset.add(new ExportJkhz("合计", "", 
    				"", 
    				"", 
    				"", 
    				"",
    				list2.get(a).get("HTJE")+"",
    				"", 
    				"", 
    				list2.get(a).get("ZXF")+"", 
    				"", 
    				list2.get(a).get("FKJE")+"", 
    				"", 
    				"", 
    				"",
    				"", 
    				"", 
    				"", 
    				"",
    				"", 
    				""
    		));
    	}
    	File temp = new File(path);
		if(!temp.exists()){
			temp.mkdirs();
		}
		temp = null;
		path+= "//"+UUID.randomUUID().toString()+".xls";
		OutputStream out = new FileOutputStream(path);
		ex.exportExcel("借款统计查询","", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
		out.close();
    	return path;
	}
	/**咨询统计查询*/
	public List<Map<String,Object>> getZxtj(String value){
		String sql="select a.id,a.ZXBM,a.KHMC,to_char(a.CREATE_TIME,'yyyy-mm-dd') as ZXSJ,a.EMPLOYEE_CRM," +
				"(select name from BASE_EMPLOYEE z where z.id = a.EMPLOYEE_CRM)as SALENAME ," +
				"a.EMPLOYEE_CCA,(select name from BASE_EMPLOYEE z where z.id = a.EMPLOYEE_CCA)as TEAMNAME ," +
				"decode(a.JKLX,'1','个人信用贷款','2','房屋抵押贷款','3','车辆抵押贷款') as ZXYX from XH_XYDKZX a where 1=1";
					sql += value;
					System.out.println("ms_acct_city  sql----->" + sql);
					return jdbcDao.searchByMergeSql(sql);
	}
	/**咨询统计导出*/
	public String exportZxtj(String path,String value) throws IOException{
		ExportExcel<ExportZxtj> ex = new ExportExcel<ExportZxtj>();
		String[] headers = {"序号", "咨询人编号", "咨询人姓名","咨询时间","个贷团队经理",
				"个贷经理","咨询意向"};
    	List<ExportZxtj> dataset = new ArrayList<ExportZxtj>();
    	int i = 1;
    	List<Map<String, Object>> list = this.getZxtj(value);
    	for(int a= 0; a < list.size();a++){
    		dataset.add(new ExportZxtj(i+"", list.get(a).get("ZXBM")+"", //咨询人编号
    				list.get(a).get("KHMC")+"", //咨询人姓名
    				list.get(a).get("ZXSJ")+"", //咨询时间
    				list.get(a).get("SALENAME")+"", //个贷团队经理
    				list.get(a).get("TEAMNAME")+"",//个贷经理
    				list.get(a).get("ZXYX")+""//个贷经理
    			
    				
    		));
    		i++;
    	}

    	File temp = new File(path);
		if(!temp.exists()){
			temp.mkdirs();
		}
		temp = null;
		path+= "//"+UUID.randomUUID().toString()+".xls";
		OutputStream out = new FileOutputStream(path);
		ex.exportExcel("咨询统计查询","", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
		out.close();
    	return path;
	}
	/**理财咨询查询*/
	public List<Map<String,Object>> getlczx(String value){
		String sql="select a.id,a.ZXBM,a.KHMC,to_char(a.CREATE_TIME,'yyyy-mm-dd') as ZXSJ," +
				"a.EMPLOYEE_CRM,(select name from BASE_EMPLOYEE z where z.id = a.EMPLOYEE_CRM)as SALENAME ," +
				"a.EMPLOYEE_CCA,(select name from BASE_EMPLOYEE z where z.id = a.employee_cca)as TEAMNAME ," +
				"decode(a.JKLX,'1','个人信用贷款','2','房屋抵押贷款','3','车辆抵押贷款') as ZXYX,a.PLAN_AMOUNT from XH_XYDKZX a where 1=1";
					sql += value;
					System.out.println("ms_acct_city  sql----->" + sql);
					return jdbcDao.searchByMergeSql(sql);
	}
	/**理财咨询查询总计*/
	public List<Map<String,Object>> getLczxCount(String value){
		String sql="select sum(a.PLAN_AMOUNT) as PLANAMOUNT from XH_XYDKZX a where 1=1";
		sql += value;
		System.out.println("ms_acct_city  sql----->" + sql);
		return jdbcDao.searchByMergeSql(sql);
	}
	/**理财咨询导出*/
	public String exportLczx(String path,String value) throws IOException{
		ExportExcel<ExportLczx> ex = new ExportExcel<ExportLczx>();
		String[] headers = {"序号", "咨询编码", "咨询人姓名","咨询时间","营销团队经理",
				"营销人","咨询产品","咨询额度"};
    	List<ExportLczx> dataset = new ArrayList<ExportLczx>();
    	int i = 1;
    	List<Map<String, Object>> list = this.getlczx(value);
    	for(int a= 0; a < list.size();a++){
    		dataset.add(new ExportLczx(i+"", list.get(a).get("ZXBM")+"", 
    				list.get(a).get("KHMC")+"", 
    				list.get(a).get("ZXSJ")+"",
    				list.get(a).get("TEAMNAME")+"", 
    				list.get(a).get("SALENAME")+"",
    				list.get(a).get("ZXYX")+"", 
    				list.get(a).get("PLAN_AMOUNT")+"" 

    		));
    		i++;
    	}
    	List<Map<String, Object>> list2 = this.getLczxCount(value);
    	for(int a= 0; a < list2.size();a++){
    		dataset.add(new ExportLczx("合计", "", 
    				"", 
    				"", 
    				"", 
    				"",
    				"",
    				list2.get(a).get("PLANAMOUNT")+""
    		));
    	}
    	File temp = new File(path);
		if(!temp.exists()){
			temp.mkdirs();
		}
		temp = null;
		path+= "//"+UUID.randomUUID().toString()+".xls";
		OutputStream out = new FileOutputStream(path);
		ex.exportExcel("理财咨询统计查询","", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
		out.close();
    	return path;
	}
	/**信审统计查询*/
	public List<Map<String,Object>> getXstj(String value){
		String sql="select to_char(b.create_time,'yyyy-mm-dd :HH:MM:SS') as JJRQ,decode(a.Credit_Type,'1','初审通过','2','复审通过','3','终审通过') as XSZT," +
				"to_char(c.create_time,'yyyy-mm-dd :HH:MM:SS') as FPSJ," +
				"to_char(a.create_time,'yyyy-mm-dd :HH:MM:SS') as PFRQ," +
				"b.TELEPHONE,b.ZJHM,b.HOME_ADDRESS,a.CREDIT_AMOUNT,d.FKJE,d.DKLL,d.FWF,d.XFF,d.HKQS,a.CREDIT_REFUSE_REASON,a.CREATE_BY,b.JKRXM," +
				"decode(b.jk_Type,'A','老板贷','B','老板楼易贷','C','薪水贷','D','薪水楼易贷','E','精英贷') as JKLB," +
				"(select name from BASE_EMPLOYEE z where z.id = e.employee_crm)as SALENAME," +
				"(select name from BASE_EMPLOYEE z where z.id = e.employee_cca)as TEAMNAME," +
				"(select name from base_city z where z.id=b.city) as CITYNAME " +
				"from XH_CREDIT_AUDIT a, XH_JKSQ b,XH_CREDIT_TASK_ASSIGN c,XH_JKHT d,XH_XYDKZX e where a.LOAN_APPLY_ID=b.Id and b.id=c.LOAN_APPLY_ID and b.id=d.jksq_id and b.XYDKZX_ID=e.id";
				sql += value;
				System.out.println("ms_acct_city  sql----->" + sql);
				return jdbcDao.searchByMergeSql(sql);
	}
	/**信审统计总计*/
	public List<Map<String,Object>> getXstjCount(String value){
		String sql="select sum(a.CREDIT_AMOUNT) as JKBJS,sum(d.FKJE) as FKJE,sum(d.FWF) as FWF,sum(d.XFF) as ZXF from XH_CREDIT_AUDIT a, XH_JKSQ b,XH_CREDIT_TASK_ASSIGN c,XH_JKHT d,XH_XYDKZX e " +
				"where a.LOAN_APPLY_ID=b.Id and b.id=c.LOAN_APPLY_ID and b.id=d.jksq_id and b.XYDKZX_ID=e.id";
		sql += value;
		System.out.println("ms_acct_city  sql----->" + sql);
		return jdbcDao.searchByMergeSql(sql);
	}
	/**理财咨询导出*/
	public String exportXstj(String path,String value) throws IOException{
		ExportExcel<ExportXstj> ex = new ExportExcel<ExportXstj>();
		String[] headers = {"序号", "进件日期时间", "分派日期时间","批复日期时间","电话号码",
				"身份证号","现住址","借款类别","区域","借款本金数","放款金额","利率","服务费","咨询费","分期","拒贷原因","团队经理","客户经理","审核人","备注","信审状态"};
    	List<ExportXstj> dataset = new ArrayList<ExportXstj>();
    	int i = 1;
    	List<Map<String, Object>> list = this.getXstj(value);
    	for(int a= 0; a < list.size();a++){
    		dataset.add(new ExportXstj(i+"", list.get(a).get("JJRQ")+"", 
    				list.get(a).get("FPSJ")+"", 
    				list.get(a).get("PFRQ")+"",
    				list.get(a).get("JKRXM")+"",
    				list.get(a).get("TELEPHONE")+"", 
    				list.get(a).get("ZJHM")+"", 
    				list.get(a).get("HOME_ADDRESS")+"",
    				list.get(a).get("JKLB")+"", 
    				list.get(a).get("CITYNAME")+"", 
    				list.get(a).get("CREDIT_AMOUNT")+"", 
    				list.get(a).get("FKJE")+"", 
    				list.get(a).get("DKLL")+"", 
    				list.get(a).get("FWF")+"", 
    				list.get(a).get("XFF")+"", 
    				list.get(a).get("HKQS")+"", 
    				list.get(a).get("CREDIT_REFUSE_REASON")+"", 
    				list.get(a).get("SALENAME")+"", 
    				list.get(a).get("TEAMNAME")+"", 
    				list.get(a).get("CREATE_BY")+"", 
    				"", 
    				list.get(a).get("XSZT")+"" 

    		));
    		i++;
    	}
    	List<Map<String, Object>> list2 = this.getXstjCount(value);
    	for(int a= 0; a < list2.size();a++){
    		dataset.add(new ExportXstj("合计", "", 
    				"", 
    				"", 
    				"", 
    				"", 
    				"",
    				"",
    				"",
    				"",
    				"",
    				list2.get(a).get("JKBJS")+"",
    				list2.get(a).get("FKJE")+"",
    				"",
    				list2.get(a).get("FWF")+"",
    				list2.get(a).get("ZXF")+"",
    				"",
    				"",
    				"",
    				"",
    				"",
    				""
    		));
    	}
    	File temp = new File(path);
		if(!temp.exists()){
			temp.mkdirs();
		}
		temp = null;
		path+= "//"+UUID.randomUUID().toString()+".xls";
		OutputStream out = new FileOutputStream(path);
		ex.exportExcel("信审统计查询","", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
		out.close();
    	return path;
	}
	
	
	
	
}
