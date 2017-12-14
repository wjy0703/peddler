package cn.com.cucsi.app.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhUploadFilesDao extends HibernateDao<XhUploadFiles, Long>{

	public Page<XhUploadFiles> queryXhUploadFiles(Page<XhUploadFiles> page, Map<String, Object> params){
		String hql = "from XhUploadFiles xhUploadFiles where 1=1";
		//文件所属表
		if(params.containsKey("fileOwner")){
			hql = hql + " and fileOwner = :fileOwner";
		}
		//文件路径
		if(params.containsKey("filepath")){
			hql = hql + " and filepath = :filepath";
		}
		//新文件名
		if(params.containsKey("newfilename")){
			hql = hql + " and newfilename = :newfilename";
		}
		//文件名
		if(params.containsKey("filename")){
			hql = hql + " and filename = :filename";
		}
		//所属表ID
		if(params.containsKey("mainId")){
			hql = hql + " and mainId = :mainId";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	public List<XhUploadFiles> findXhUploadFiles(Map<String, Object> filters){
		String hql = "from XhUploadFiles xhUploadFiles where 1=1";
		//文件所属表
		if(filters.containsKey("fileOwner")){
			hql = hql + " and fileOwner = :fileOwner";
		}
		//所属表ID
		if(filters.containsKey("mainId")){
			hql = hql + " and mainId = :mainId";
		}
		//所属表ID
		if(filters.containsKey("flag")){
			hql = hql + " and flag = :flag";
		}
		hql = hql + " order by createTime desc";
		return this.find(hql, filters);
	}
	
	
}
