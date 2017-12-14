package cn.com.cucsi.app.service.baseinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import cn.com.cucsi.app.dao.baseinfo.UpdatedMenuDao;
import cn.com.cucsi.app.entity.baseinfo.UpdatedMenu;

public abstract class UpdatedMenuCacheParent {

	protected  LoadingCache<String,List<UpdatedMenu>> menuCache = null;
	

	public List<UpdatedMenu> getMenuByRoleID(long roleId, long parentId) {
		initialCacheIfNull();
		String roleIdAndParentId = roleId + ":" + parentId;
		try {
			return menuCache.get(roleIdAndParentId);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<UpdatedMenu> (); 
	}
		
    
	public void invalidateAll(){
		initialCacheIfNull();
		menuCache.invalidateAll();
	}
	
	
	
	protected abstract void initialCacheIfNull();
	/*{
		if(menuCache == null){
			menuCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(60, TimeUnit.MINUTES).build(new UpdatedMenuLoader());
		}
	}*/
	
	
}
