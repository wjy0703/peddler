package cn.com.cucsi.app.service.baseinfo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

@Component
public class EmployeeCacheManager {
    
	@Autowired
	EmployeeNameLoader employeeNameLoader;
	
	
	private  LoadingCache<Long,String> employeeNameCache = null;

	/**
	 * 
	 * 确定名字
	 * @param value
	 * @return
	 */
	public String getName(Long value) {
		initialCacheIfNull();
		try {
			return employeeNameCache.get(value);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
		return "";
	}
	

	
	
	private void initialCacheIfNull() {
		if(employeeNameCache == null){
		    employeeNameCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(60, TimeUnit.MINUTES).build(employeeNameLoader);
		}
	}
	
	 /**
     * 清楚缓存
     */
	public void invalidateAll() {
		initialCacheIfNull();
		employeeNameCache.invalidateAll();
	}
	
	
}
