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
public class AddressesLoader extends CacheLoader<Long,List<City>> {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> load(Long parentId) throws Exception {
		 return cityDao.getSuggestCity(parentId+"");
	}

}