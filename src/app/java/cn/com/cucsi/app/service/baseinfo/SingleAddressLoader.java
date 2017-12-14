package cn.com.cucsi.app.service.baseinfo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.entity.baseinfo.City;

import com.google.common.cache.CacheLoader;


/**
 * 缓存类，键值为: ID ->下级城市，或地区名
 * @author xjs
 *
 */
@Component
public class SingleAddressLoader extends CacheLoader<Long,String> {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public String load(Long id) throws Exception {
		 City city = cityDao.get(id);
		 return city!= null ? city.getName() : "";
	}

}