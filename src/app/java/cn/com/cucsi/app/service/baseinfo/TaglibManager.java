package cn.com.cucsi.app.service.baseinfo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.entity.baseinfo.Attr;

@Component
public class TaglibManager {
    
	@Autowired
	JdbcDao jdbcDao;
	
	/**
	 * 查找表base_attr的内容，根据value取得显示的值
	 * @param value    
	 * @param typeId
	 * @return
	 */
	public Map getAttrByValueAndTypeId(String value,int typeId) {
		String sql = "select description ,key_name as stateName, value as keyName from BASE_ATTR where  value=? and type_id = ?"; 
		List<Map<String, Object>> values = jdbcDao.selectBySql(sql, new Object[]{value,typeId});
		if(values == null )
			return null;
		if(values.size() == 0)
			return null;
		return values.get(0);	
	}
	
	/**
	 * 通过借款申请id 返回共同借款人姓名
	 * @param value
	 * @param typeId
	 * @return
	 */
	public List<Map<String, Object>> getTogetherNameTypeJksqId(int jksqId) {
		
//		String sql = "select together_Name as name from XH_JKSQ_TOGETHER where XHJKSQ_ID = ? ";
		String sql = "SELECT TOG.IDENTIFICATION AS IDENTIFICATION, TOGETHER_NAME AS name FROM XH_JKSQ_TOGETHER tog  where TOG.XHJKSQ_ID = ? ";
		List<Map<String, Object>> values = jdbcDao.selectBySql(sql, new Object[]{jksqId});
		return values;	
	}
	
	
	/**
	 * 通过借款申请id 返回初始分配时间
	 * @param value
	 * @param typeId
	 * @return
	 */
	public String getTaskAsignTimeJksqId(int jksqId) {
		String sql = "select to_char(CREATE_TIME,'yyyy-mm-dd HH24:MI:SS') as ASSIGNTIME from XH_CREDIT_TASK_ASSIGN where LOAN_APPLY_ID = ? order by CREATE_TIME desc"; 
		List<Map<String, Object>> values = jdbcDao.selectBySql(sql, new Object[]{jksqId});
		if(values != null){
			if(values.size() > 0){
				Map<String, Object> value = values.get(0);
				Object assignTime =  value.get("ASSIGNTIME");
				return assignTime == null ? "" : assignTime.toString();
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
}
