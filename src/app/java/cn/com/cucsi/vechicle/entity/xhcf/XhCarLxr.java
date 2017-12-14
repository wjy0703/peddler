package cn.com.cucsi.vechicle.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCarLxr entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_LXR")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarLxr extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String jjlxrdwdzhjtzz;// 单位地址或家庭住址

    /** 单位地址或家庭住址 */
    @Column(columnDefinition = DEF_STR256)
    public String getJjlxrdwdzhjtzz() {
        return this.jjlxrdwdzhjtzz;
    }

    /** 单位地址或家庭住址 */
    public void setJjlxrdwdzhjtzz(String jjlxrdwdzhjtzz) {
        this.jjlxrdwdzhjtzz = jjlxrdwdzhjtzz;
    }

    private String jjlxrgzdw;// 紧急联系人工作单位

    /** 紧急联系人工作单位 */
    @Column(columnDefinition = DEF_STR256)
    public String getJjlxrgzdw() {
        return this.jjlxrgzdw;
    }

    /** 紧急联系人工作单位 */
    public void setJjlxrgzdw(String jjlxrgzdw) {
        this.jjlxrgzdw = jjlxrgzdw;
    }

    private String jjlxrlxdh;// 紧急联系人联系电话

    /** 紧急联系人联系电话 */
    @Column(columnDefinition = DEF_STR32)
    public String getJjlxrlxdh() {
        return this.jjlxrlxdh;
    }

    /** 紧急联系人联系电话 */
    public void setJjlxrlxdh(String jjlxrlxdh) {
        this.jjlxrlxdh = jjlxrlxdh;
    }

    private String lxrlx;// 联系人类型

    /** 联系人类型 */
    @Column(columnDefinition = DEF_STR32)
    public String getLxrlx() {
        return this.lxrlx;
    }

    /** 联系人类型 */
    public void setLxrlx(String lxrlx) {
        this.lxrlx = lxrlx;
    }

    private String name;// 紧急联系人姓名

    /** 紧急联系人姓名 */
    @Column(columnDefinition = DEF_STR32)
    public String getName() {
        return this.name;
    }

    /** 紧急联系人姓名 */
    public void setName(String name) {
        this.name = name;
    }

    private String ybrgx;// 与本人关系

    /** 与本人关系 */
    @Column(columnDefinition = DEF_STR32)
    public String getYbrgx() {
        return this.ybrgx;
    }

    /** 与本人关系 */
    public void setYbrgx(String ybrgx) {
        this.ybrgx = ybrgx;
    }

    /*
     * private Long carLoanId;//借款申请信息
     *//** 借款申请信息 */
    /*
     * @Column(columnDefinition=DEF_NUM18) public Long getCarLoanId() { return
     * this.carLoanId; }
     *//** 借款申请信息 */
    /*
     * public void setCarLoanId(Long carLoanId) { this.carLoanId = carLoanId; }
     */
    private String auditMessage;// 审核情况

    /** 审核情况 */
    @Column(columnDefinition = DEF_STR256)
    public String getAuditMessage() {
        return this.auditMessage;
    }

    /** 审核情况 */
    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    private XhCarLoanUser xhCarLoanUser;

    // 用户基本信息
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "XHCARLOANUSER_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanUser getXhCarLoanUser() {
        return xhCarLoanUser;
    }

    public void setXhCarLoanUser(XhCarLoanUser xhCarLoanUser) {
        this.xhCarLoanUser = xhCarLoanUser;
    }

}
