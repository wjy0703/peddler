package cn.com.cucsi.app.entity.export;

public class ExportTzsq {
	private String bianhao;
	private String jigo;
	private String cca;
	private String crm;
	public String getBianhao() {
		return bianhao;
	}
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}
	public String getJigo() {
		return jigo;
	}
	public void setJigo(String jigo) {
		this.jigo = jigo;
	}
	public String getCca() {
		return cca;
	}
	public void setCca(String cca) {
		this.cca = cca;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public ExportTzsq(String bianhao, String jigo, String cca, String crm) {
		super();
		this.bianhao = bianhao;
		this.jigo = jigo;
		this.cca = cca;
		this.crm = crm;
	}
	
	
}
