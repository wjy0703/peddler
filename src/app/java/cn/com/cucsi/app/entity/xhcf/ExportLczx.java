package cn.com.cucsi.app.entity.xhcf;

public class ExportLczx {
	
	private String xh;//序号
	private String zxbm;//咨询编码
	private String zxrxm;//咨询人姓名
	private String zxDate; // 咨询时间
	private String saleName;//个贷经理
	private String teamName;//个贷团队经理
	private String zxcp;//咨询产品
	private String zxed;//咨询额度
	
	public String getXh() {
		return xh;
	}


	public void setXh(String xh) {
		this.xh = xh;
	}


	public String getZxbm() {
		return zxbm;
	}


	public void setZxbm(String zxbm) {
		this.zxbm = zxbm;
	}


	public String getZxrxm() {
		return zxrxm;
	}


	public void setZxrxm(String zxrxm) {
		this.zxrxm = zxrxm;
	}


	public String getZxDate() {
		return zxDate;
	}


	public void setZxDate(String zxDate) {
		this.zxDate = zxDate;
	}


	public String getSaleName() {
		return saleName;
	}


	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	


	public String getZxcp() {
		return zxcp;
	}


	public void setZxcp(String zxcp) {
		this.zxcp = zxcp;
	}


	public String getZxed() {
		return zxed;
	}


	public void setZxed(String zxed) {
		this.zxed = zxed;
	}


	public ExportLczx(String xh,String zxbm,String zxrxm,String zxDate,String saleName,String teamName,String zxcp,String zxed){
		this.xh=xh;
		this.zxbm=zxbm;
		this.zxrxm=zxrxm;
		this.zxDate=zxDate;
		this.saleName=saleName;
		this.teamName=teamName;
		this.zxcp=zxcp;
		this.zxed=zxed;
		
		
	}
}
