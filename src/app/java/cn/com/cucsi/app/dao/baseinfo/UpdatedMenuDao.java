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
 * 和菜单相关的操作
 * 
 * @author xjs
 * 
 */
@Component
public class UpdatedMenuDao {

	private static Logger logger = LoggerFactory
			.getLogger(UpdatedMenuDao.class);

	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	/**
	 * 根据角色id 获取一级菜单
	 * 
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings({ "deprecation" })
	public List<UpdatedMenu> getTopMenuByRoleID(long roleId) {
		String sql = "SELECT MENU.ID as id , MENU.FRESH as fresh , MENU.EXTERNAL as external, MENU.LEVEL_ID as levelId , MENU.MENU_NAME as menuName , MENU.MENU_TYPE as menuType ,MENU.MENU_URL as menuUrl ,MENU.REL as rel , MENU.SORT_NO as sortNo , MENU.TARGET as target , MENU.TITLE as title FROM BASE_MENU MENU , BASE_MENU_ROLE MR  where MENU.ID = MR.MENU_ID AND MR.ROLE_ID = ? AND MENU.LEVEL_ID = 1 AND MENU.STS = '0' ORDER BY MENU.SORT_NO ";
		return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(UpdatedMenu.class), roleId);
	}

	/**
	 * 根据角色id 获取二级菜单
	 * 
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings({ "deprecation" })
	public List<UpdatedMenu> getSecondMenuByRoleID(long roleId, long parentId) {
		String sql = "SELECT MENU.ID as id , MENU.FRESH as fresh ,  MENU.EXTERNAL as external, MENU.LEVEL_ID as levelId , MENU.MENU_NAME as menuName , MENU.MENU_TYPE as menuType ,MENU.MENU_URL as menuUrl ,MENU.REL as rel , MENU.SORT_NO as sortNo , MENU.TARGET as target , MENU.TITLE as title FROM BASE_MENU MENU , BASE_MENU_ROLE MR  where MENU.ID = MR.MENU_ID AND MR.ROLE_ID = ? AND MENU.LEVEL_ID = 2 AND MENU.HEIGER_MENU = ? AND MENU.STS = '0' ORDER BY MENU.SORT_NO";
		return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(UpdatedMenu.class),
				new Object[] { roleId, parentId });
	}

	/**
	 * 根据角色id 获取三级菜单
	 * 
	 * @param roleId
	 * @param firstLevelId
	 * @return
	 */
	@SuppressWarnings({ "deprecation" })
	public List<UpdatedMenu> getThirdMenuByRoleID(long roleId, long parentId) {
		String sql = "SELECT MENU.ID as id , MENU.FRESH as fresh , MENU.EXTERNAL as external,  MENU.LEVEL_ID as levelId , MENU.MENU_NAME as menuName , MENU.MENU_TYPE as menuType ,MENU.MENU_URL as menuUrl ,MENU.REL as rel , MENU.SORT_NO as sortNo , MENU.TARGET as target , MENU.TITLE as title FROM BASE_MENU MENU , BASE_MENU_ROLE MR  where MENU.ID = MR.MENU_ID AND MR.ROLE_ID = ? AND MENU.LEVEL_ID = 3 AND MENU.HEIGER_MENU = ? AND MENU.STS = '0' ORDER BY MENU.SORT_NO";
		return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(UpdatedMenu.class),
				new Object[] { roleId, parentId });
	}

	/**
	 * select ACCT_USER_ROLE.Role_Id from ACCT_USER_ROLE left join ACCT_USER on
	 * ACCT_USER.id = ACCT_USER_ROLE.User_Id where ACCT_USER.id = 7848
	 */
	public List<Map<String, Object>> getRoleIdsByUserId(long userId) {
		String sql = " select ACCT_USER_ROLE.Role_Id as ROLEID from ACCT_USER_ROLE left join ACCT_USER on ACCT_USER.id = ACCT_USER_ROLE.User_Id  where ACCT_USER.id = ?";
		return jdbcTemplate.queryForList(sql, userId);
	}
}
