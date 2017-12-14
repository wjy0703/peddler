package cn.com.cucsi.app.service.baseinfo;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;


@Component("enumsLoader")
public class EnumsLoader extends CacheLoader<String,Map<String,Object>> {

	@Autowired
	TaglibManager attrManager; 
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> load(String key) throws Exception {
		return attrManager.getAttrByValueAndTypeId(key, 1);
	}

}
