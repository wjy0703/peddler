package cn.com.cucsi.app.dao.baseinfo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.UpdatedMenu;

/**
 * 和下拉菜单项相关的类，涉及到的表包括Base_attr和 BASE_attr_value
 * 
 * @author xjs
 * 
 */
@Component
public class AttrJdbcDao {

	private static Logger logger = LoggerFactory.getLogger(AttrJdbcDao.class);

	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	/**
	 * 根据coding的值取得相应的下拉数据项
	 * 
	 * @param coding
	 * @return
	 */
	public List<Map<String, Object>> getAttrs(String coding) {
		//String sql = "SELECT ATTR.VALUE,ATTR.DESCRIPTION from BASE_ATTR ATTR , BASE_ATTR_TYPE ATTRTYPE where ATTR.TYPE_ID = ATTRTYPE.ID AND ATTRTYPE.CODING = ? ORDER BY ATTR.SORT_NO";
		String sql = "SELECT ATTR.VALUE AS VALUE,ATTR.NAME AS DESCRIPTION  FROM XH_MATE_DATA ATTR , XH_MATEDATA_TYPE ATTRTYPE where ATTR.MATEDATATYPE_ID = ATTRTYPE.ID AND ATTRTYPE.TYPE_CODE = ? ";
		return this.jdbcTemplate.queryForList(sql, coding);
	}

	
}
