package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhUploadFiles entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_UPLOAD_FILES")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhUploadFiles extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String fileOwner;//文件所属表
	/**文件所属表*/
	@Column(columnDefinition=DEF_STR64)
	public String getFileOwner() {
		return this.fileOwner;
	}
	/**文件所属表*/
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}
	private String filepath;//文件路径
	/**文件路径*/
	@Column(columnDefinition=DEF_STR512)
	public String getFilepath() {
		return this.filepath;
	}
	/**文件路径*/
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	private String newfilename;//新文件名
	/**新文件名*/
	@Column(columnDefinition=DEF_STR512)
	public String getNewfilename() {
		return this.newfilename;
	}
	/**新文件名*/
	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}
	private String filename;//文件名
	/**文件名*/
	@Column(columnDefinition=DEF_STR512)
	public String getFilename() {
		return this.filename;
	}
	/**文件名*/
	public void setFilename(String filename) {
		this.filename = filename;
	}
	private Long mainId;//所属表ID
	/**所属表ID*/
	@Column(name="MAIN_ID")
	public Long getMainId() {
		return this.mainId;
	}
	/**所属表ID*/
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	/**标记*/
	private String flag;
	/**标记*/
	@Column(columnDefinition=DEF_STR10)
	public String getFlag() {
		return flag;
	}
	/**标记*/
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
