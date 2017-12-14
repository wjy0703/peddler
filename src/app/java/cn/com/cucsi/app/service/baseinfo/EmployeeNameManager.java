package cn.com.cucsi.app.service.baseinfo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.NamedJdbcDao;


@Component
@Transactional
public class EmployeeNameManager {
    
    @Autowired
    NamedJdbcDao namedJdbcDao;
    
    /**
     * 根据id取得姓名
     *
     * @param id
     * @return
     * @author xjs
     * @date 2013-10-16 上午10:24:20
     */
    public String getName(Long id) {
        String sql = "select name from base_employee where id = :id";
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("id", id);
        String name = namedJdbcDao.getJdbcTemplate().queryForObject(sql, condition, String.class);
        return name == null ? "" : name;
    }

}
