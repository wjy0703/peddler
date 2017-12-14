package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhMakeLoans entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_MAKE_LOANS")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhMakeLoans extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private MiddleMan middleMan;// 中间人（账户）ID

	/** 中间人（账户） */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "MIDDLE_MAN_ID")
	public MiddleMan getMiddleMan() {
		return middleMan;
	}

	/** 中间人（账户） */
	public void setMiddleMan(MiddleMan middleMan) {
		this.middleMan = middleMan;
	}

	/** 借款合同 */
	private XhJkht loanContract;// 借款合同

	/** 借款合同 */
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_CONTRACT_ID")
	public XhJkht getLoanContract() {
		return loanContract;
	}

	/** 借款合同 */
	public void setLoanContract(XhJkht loanContract) {
		this.loanContract = loanContract;
	}

	private Date makeLoanDate;//放款时间
	/**放款时间*/
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	public Date getMakeLoanDate() {
		return this.makeLoanDate;
	}
	/**放款时间*/
	public void setMakeLoanDate(Date makeLoanDate) {
		this.makeLoanDate = makeLoanDate;
	}
}
