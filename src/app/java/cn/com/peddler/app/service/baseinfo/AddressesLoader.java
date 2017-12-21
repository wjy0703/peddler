package cn.com.peddler.app.service.baseinfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.peddler.app.dao.JdbcDao;
import cn.com.peddler.app.entity.login.Cityinfo;
import cn.com.peddler.app.util.MapVsBean;

import com.google.common.cache.CacheLoader;


/**
 * 缓存类，键值为: ID ->下级城市，或地区名
 * @author xjs
 *
 */
@Component
public class AddressesLoader extends CacheLoader<Long,List<Cityinfo>> {

	@Autowired
	private JdbcDao jdbcDao;
	
	@Override
	public List<Cityinfo> load(Long parentId) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", " a.parentid="+parentId + " order by id");
		List<Map<String, Object>> cityMap = jdbcDao.searchBySqlTemplate("queryCityinfoList", conditions);
		/*List<Cityinfo> cityList = new ArrayList<Cityinfo>();
		for(Map<String, Object> city:cityMap){
			cityList.add(getCityinfoFromMap(city));
		}*/
		return MapVsBean.mapsToObjects(cityMap,Cityinfo.class);
	}
	
	private Map<String, Object> fromCityinfoEntity(Cityinfo cityinfo){
    	Map<String, Object> conditions = new HashMap<String, Object>();
    	conditions.put("id", cityinfo.getId());
    	conditions.put("cname", cityinfo.getCname());
    	conditions.put("coding", cityinfo.getCoding());
    	conditions.put("deptlevel", cityinfo.getDeptlevel());
    	conditions.put("vname", cityinfo.getVname());
    	conditions.put("pinyin", cityinfo.getPinyin());
    	conditions.put("sortno", cityinfo.getSortno());
    	conditions.put("vtypes", cityinfo.getVtypes());
    	conditions.put("parentid", cityinfo.getParentid());
    	conditions.put("createtime", cityinfo.getCreatetime());
    	conditions.put("modifytime", cityinfo.getModifytime());
    	conditions.put("createuser", cityinfo.getCreateuser());
    	conditions.put("modifyuser", cityinfo.getModifyuser());
    	return conditions;
    }
	
	private Cityinfo getCityinfoFromMap(Map<String, Object> map){
		Cityinfo cityinfo=new Cityinfo();
    	cityinfo.setId(Long.parseLong(map.get("id")+""));
		cityinfo.setCname(map.get("cname")+"");
		cityinfo.setCoding(map.get("coding")+"");
		cityinfo.setDeptlevel(Long.parseLong(map.get("deptlevel")+""));
		cityinfo.setVname(map.get("vname")+"");
		cityinfo.setPinyin(map.get("pinyin")+"");
		cityinfo.setSortno(Long.parseLong(map.get("sortno")+""));
		cityinfo.setVtypes(map.get("vtypes")+"");
		cityinfo.setParentid(Long.parseLong(map.get("parentid")+""));
		cityinfo.setCreateuser(map.get("createuser")+"");
		cityinfo.setModifyuser(map.get("modifyuser")+"");
    	return cityinfo;
    }
}