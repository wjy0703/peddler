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

@Component
public class UpdatedThirdMenuCache extends UpdatedMenuCacheParent{
	
	@Autowired
	UpdatedMenuDao updatedMenuDao;

	@Override
	protected void initialCacheIfNull() {
		if(menuCache == null){
			menuCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(60, TimeUnit.MINUTES).build(new UpdatedThirdMenuLoader(updatedMenuDao));
		}
	} 
	
	private class UpdatedThirdMenuLoader extends UpdatedMenuLoader {
		
	    public UpdatedThirdMenuLoader(UpdatedMenuDao updatedMenuDao){
	    	super(updatedMenuDao);
	    }
	    
		@Override
		public List<UpdatedMenu> getUpdatedMenu(long roleId, long parentId) {
			return updatedMenuDao.getThirdMenuByRoleID(roleId, parentId);
		}

	}
	
	
/*
	@Autowired
	UpdatedMenuDao updatedMenuDao; 
	
	private  LoadingCache<String,List<UpdatedMenu>> menuCache = null;
	

	public List<UpdatedMenu> getThirdMenuByRoleID(long roleId, long parentId) {
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
	
	private class UpdatedMenuLoader extends CacheLoader<String,List<UpdatedMenu>> {
		@Override
		public List<UpdatedMenu> load(String roleIdAndParentId) throws Exception {
			String[] values = roleIdAndParentId.split(":");
			long roleId = Long.parseLong(values[0]);
			long parentId = Long.parseLong(values[1]);
			return updatedMenuDao.getThirdMenuByRoleID(roleId, parentId);
		}

	}
	
	private void initialCacheIfNull() {
		if(menuCache == null){
			menuCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(60, TimeUnit.MINUTES).build(new UpdatedMenuLoader());
		}
	}
*/
	

}
