package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.dao.baseinfo.MiddleManDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.xhcf.FangKuanGuanLiDao;
import cn.com.cucsi.app.dao.xhcf.XhAvailableValueOfClaimsDao;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.xhcf.FangKuangGuanLi;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.web.util.SysConstant;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class JiaoYiManager {
	private FangKuanGuanLiDao fangKuanGuanLidao;
	private MiddleManDao middleManDao;
	private JdbcDao jdbcDao;
	private XhAvailableValueOfClaimsDao kyzqjz;
	private XhJksqStateDao xhJksqStateDao;

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	public void setXhAvailableValueOfClaimsDao(
			XhAvailableValueOfClaimsDao kyzqjz) {
		this.kyzqjz = kyzqjz;
	}

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Autowired
	public void setFangKuanGuanLiDao(FangKuanGuanLiDao FangKuanGuanLi) {
		this.fangKuanGuanLidao = FangKuanGuanLi;
	}

	@Transactional(readOnly = true)
	public Page<FangKuangGuanLi> searchFkgl(final Page<FangKuangGuanLi> page,
			final Map<String, Object> filters) {
		return fangKuanGuanLidao.queryFangKuangGuanLi(page, filters);
	}

	@Transactional(readOnly = true)
	public FangKuangGuanLi getFangKuangGuanLi(Long id) {
		return fangKuanGuanLidao.get(id);
	}

	public void savefangKuanGuanLi(FangKuangGuanLi fangKuanGuanLi) {
		fangKuanGuanLidao.save(fangKuanGuanLi);
	}

	public void deletefangKuanGuanLi(Long id) {
		fangKuanGuanLidao.delete(id);
	}

	@Transactional(readOnly = true)
	public List<MiddleMan> getSuggestMiddleMan(String parentId) {
		return middleManDao.getSuggestMiddleMan(parentId);
	}

	@Autowired
	public void setMiddleManDao(MiddleManDao middleManDao) {
		this.middleManDao = middleManDao;
	}

	public List<Map<String, Object>> searchFkglList(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		if (filter.containsKey("jkrbh")) {
			value = String.valueOf(filter.get("jkrbh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_CODE like '%" + value + "%'";
			}
		}
		if (filter.containsKey("jkrxm")) {
			value = String.valueOf(filter.get("jkrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JKRXM = '" + value + "'";
			}
		}
		if (filter.containsKey("fksj")) {
			value = String.valueOf(filter.get("fksj"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.FKSJ >= '" + value + "'";
			}
		}

		if (filter.containsKey("fksj1")) {
			value = String.valueOf(filter.get("fksj1"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and c.FKSJ <= '" + value + "'";
			}
		}

		sql = sql + PropertiesUtils.getLoanSql();

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	public List<Map<String, Object>> searchZjrList(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";

		sql = sql + PropertiesUtils.getLoanSql();

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	public void saveXhKyzqjz(FangKuangGuanLi entity) {
		XhAvailableValueOfClaims kyzq = new XhAvailableValueOfClaims();
		MiddleMan zjr = entity.getMiddleMan();
		XhJksq jksq = entity.getXhJksq();

		// XhJkht xhJkht = jksq.getXhjkht();

		// XhJkht xhJkht = jksq.getXhjkht();

		kyzq.setXhJksq(jksq);// 出借人ID
		kyzq.setJkbh(entity.getJkrbh());// 借款编码
		kyzq.setJkje(entity.getHtje());// 借款金额
		kyzq.setKyzqjz(entity.getHtje());// 可用债权价值
		kyzq.setMiddleMan(zjr);// 中间人ID
		kyzq.setZjrcybl(100.00);// 中间人ID
		kyzq.setSyqs(entity.getFkqs());// 剩余期数
		kyzq.setSyhkys(entity.getFkqs());// 剩余还款月数
		kyzq.setSytjje(entity.getHtje());// 剩余推荐金额

		// kyzq.setSqhkrq(xhJkht.getQshkrq());

		// kyzq.setSqhkrq(xhJkht.getQshkrq());

		kyzqjz.save(kyzq);// 可用债权价值新增
		XhJksqState state = new XhJksqState();
		state.setXhjksq(jksq);
		state.setDescribe("已放款!");
		xhJksqStateDao.save(state);// 接口历史记录新增
	}

}
