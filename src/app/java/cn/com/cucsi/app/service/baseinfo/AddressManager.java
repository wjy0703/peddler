package cn.com.cucsi.app.service.baseinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.entity.baseinfo.City;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;


@Component
public class AddressManager {
	
    @Autowired
	private CityDao cityDao;
	
    @Autowired
    AddressesLoader addressesLoader;
    
    @Autowired
    SingleAddressLoader singleAddressesLoader;
    
    @Autowired
    AttrCacheManager attrCacheManager;
    
	private  LoadingCache<Long,List<City>> cache = null;
    
	
	private  LoadingCache<Long,String> singleCache = null;
	
    /**
     * 通过父ID查询
     * @param parentId
     * @return
     */
	public List<City> getSonsByParentId(long parentId) {
		initialCacheIfNull();
		try {
				return cache.get(parentId);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
		return new ArrayList<City>();
	}
	
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 * @throws ExecutionException 
	 */
	public String getCityNameById(Long id) {
		initialCacheIfNull();
		try {
			return singleCache.get(id);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return "未知城市";
	}
	
	/**
	 * 同级别的城市
	 * @param id
	 * @return
	 */
	public List<City> getSameLevelCities(long id){
		//
		City city = cityDao.get(id);
		return getSonsByParentId(city.getParent().getId());
	}
	
	private void initialCacheIfNull() {
		if(cache == null){
			cache = CacheBuilder.newBuilder().maximumSize(200).expireAfterWrite(60, TimeUnit.MINUTES).build(addressesLoader);
		}
		if(singleCache == null){
			singleCache = CacheBuilder.newBuilder().maximumSize(200).expireAfterWrite(60, TimeUnit.MINUTES).build(singleAddressesLoader);
		}
	}
    /**
     * 清楚缓存
     */
	public void invalidateAll() {
		initialCacheIfNull();
		cache.invalidateAll();
		singleCache.invalidateAll();
	}
}
