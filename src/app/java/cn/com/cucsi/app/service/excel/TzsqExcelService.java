package cn.com.cucsi.app.service.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.core.orm.JdbcPage;

@Service
public class TzsqExcelService extends AbstractExcelService {

	@Autowired
	JdbcDao jdbcDao;

	@Override
	public List<Map<String, Object>> queryRows(Map<String, Object> filter) {
		return jdbcDao.searchByMergeSqlTemplate("exportXhTzsqList",
				conditions(filter));
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhTzsq(Map<String, Object> filter,
			JdbcPage page) {
		return jdbcDao.searchPagesByMergeSqlTemplate("exportXhTzsqList",
				conditions(filter), page);
	}

	public Map<String, Object> conditions(Map<String, Object> filter) {
		String sql = "";
		String sql2 = "";
		String value = "";
		// 协议编号
		if (filter.containsKey("tzsqbh")) {
			value = String.valueOf(filter.get("tzsqbh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZSQBH like '%" + value + "%'";
			}
		}
		if (filter.containsKey("date")) {
			value = String.valueOf(filter.get("date"));
			if (StringUtils.isNotEmpty(value)) {
				String sqlDate = " to_date(a." + value + ",'yyyy-MM-dd') ";
				if (value.equals("MOQI")) {
					//sqlDate = "z." + value;
					String sqlDate2 = " to_date(z." + value + ",'yyyy-MM-dd') ";
					if (filter.containsKey("startdate")) {
						value = String.valueOf(filter.get("startdate"));
						if (StringUtils.isNotEmpty(value)) {
							sql2 += " and " + sqlDate2 + " >= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
					if (filter.containsKey("enddate")) {
						value = String.valueOf(filter.get("enddate"));
						if (StringUtils.isNotEmpty(value)) {
							sql2 += " and " + sqlDate2 + " <= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
				}else{
					if (filter.containsKey("startdate")) {
						value = String.valueOf(filter.get("startdate"));
						if (StringUtils.isNotEmpty(value)) {
							sql += " and " + sqlDate + " >= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
					if (filter.containsKey("enddate")) {
						value = String.valueOf(filter.get("enddate"));
						if (StringUtils.isNotEmpty(value)) {
							sql += " and " + sqlDate + " <= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
				}

			}
		}
		
		// 计划投资日期
		if (filter.containsKey("jhtzrq")) {
			value = String.valueOf(filter.get("jhtzrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql
						+ " and to_char(to_date(a.JHTZRQ,'yyyy-MM-dd'),'yyyy-MM') = '"
						+ value + "'";
			}
		}

		if (filter.containsKey("jhtzrqMin")) {
			value = String.valueOf(filter.get("jhtzrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHTZRQ,'yyyy-MM-dd') >= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}

		if (filter.containsKey("jhtzrqMax")) {
			value = String.valueOf(filter.get("jhtzrqMax"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHTZRQ,'yyyy-MM-dd') <= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}

		// 计划划扣日期
		if (filter.containsKey("jhhkrqMin")) {
			value = String.valueOf(filter.get("jhhkrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHHKRQ,'yyyy-MM-dd') >= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}

		// 计划划扣日期
		if (filter.containsKey("jhhkrqMax")) {
			value = String.valueOf(filter.get("jhhkrqMax"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHHKRQ,'yyyy-MM-dd') <= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}

		// 计划投资金额
		if (filter.containsKey("jhtzje")) {
			value = String.valueOf(filter.get("jhtzje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHTZJE = '" + value + "'";
			}
		}
		// 投资方式
		if (filter.containsKey("tzfs")) {
			value = String.valueOf(filter.get("tzfs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFS = '" + value + "'";
			}
		}
		// 回收方式
		if (filter.containsKey("hsfs")) {
			value = String.valueOf(filter.get("hsfs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSFS = '" + value + "'";
			}
		}
		// 付款方式
		if (filter.containsKey("fkfs")) {
			value = String.valueOf(filter.get("fkfs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FKFS = '" + value + "'";
			}
		}
		// 是否风险金补偿
		if (filter.containsKey("sffxjbc")) {
			value = String.valueOf(filter.get("sffxjbc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SFFXJBC = '" + value + "'";
			}
		}
		// 计划划扣日期
		if (filter.containsKey("jhhkrq")) {
			value = String.valueOf(filter.get("jhhkrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHHKRQ = '" + value + "'";
			}
		}
		// 申请日期
		if (filter.containsKey("sqrq")) {
			value = String.valueOf(filter.get("sqrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SQRQ = '" + value + "'";
			}
		}
		// 部门主管
		if (filter.containsKey("bmzg")) {
			value = String.valueOf(filter.get("bmzg"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BMZG = '" + value + "'";
			}
		}
		// 协议版本
		if (filter.containsKey("xybb")) {
			value = String.valueOf(filter.get("xybb"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XYBB = '" + value + "'";
			}
		}
		// 销售折扣率
		if (filter.containsKey("xszkly")) {
			value = String.valueOf(filter.get("xszkly"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XSZKLY = '" + value + "'";
			}
		}
		// 销售折扣率有效期限
		if (filter.containsKey("xszklyxqx")) {
			value = String.valueOf(filter.get("xszklyxqx"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XSZKLYXQX = '" + value + "'";
			}
		}
		// 备注
		if (filter.containsKey("remark")) {
			value = String.valueOf(filter.get("remark"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMARK = '" + value + "'";
			}
		}
		// 申请单原件
		if (filter.containsKey("sqdyj")) {
			value = String.valueOf(filter.get("sqdyj"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SQDYJ = '" + value + "'";
			}
		}
		// 投资付款开户行
		if (filter.containsKey("tzfkkhh")) {
			value = String.valueOf(filter.get("tzfkkhh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKKHH = '" + value + "'";
			}
		}
		// 投资付款卡或折
		if (filter.containsKey("tzfkkhz")) {
			value = String.valueOf(filter.get("tzfkkhz"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKKHZ = '" + value + "'";
			}
		}
		// 投资付款银行名称
		if (filter.containsKey("tzfkyhmc")) {
			value = String.valueOf(filter.get("tzfkyhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKYHMC = '" + value + "'";
			}
		}
		// 投资付款开户名称
		if (filter.containsKey("tzfkkhmc")) {
			value = String.valueOf(filter.get("tzfkkhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKKHMC = '" + value + "'";
			}
		}
		// 投资付款银行帐号
		if (filter.containsKey("tzfkyhzh")) {
			value = String.valueOf(filter.get("tzfkyhzh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKYHZH = '" + value + "'";
			}
		}
		// 回收资金开户行
		if (filter.containsKey("hszjkhh")) {
			value = String.valueOf(filter.get("hszjkhh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJKHH = '" + value + "'";
			}
		}
		// 回收资金卡或折
		if (filter.containsKey("hszjkhz")) {
			value = String.valueOf(filter.get("hszjkhz"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJKHZ = '" + value + "'";
			}
		}
		// 回收资金银行名称
		if (filter.containsKey("hszjyhmc")) {
			value = String.valueOf(filter.get("hszjyhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJYHMC = '" + value + "'";
			}
		}
		// 回收资金开户名称
		if (filter.containsKey("hszjkhmc")) {
			value = String.valueOf(filter.get("hszjkhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJKHMC = '" + value + "'";
			}
		}
		// 回收资金银行帐号
		if (filter.containsKey("hszjyhzh")) {
			value = String.valueOf(filter.get("hszjyhzh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJYHZH = '" + value + "'";
			}
		}
		// 申请状态
		if (filter.containsKey("sqzt")) {
			value = String.valueOf(filter.get("sqzt"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SQZT = '" + value + "'";
			}
		}
		// 出借周期
		if (filter.containsKey("cjzq")) {
			value = String.valueOf(filter.get("cjzq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CJZQ = '" + value + "'";
			}
		}
		// 交割日
		if (filter.containsKey("tzjgr")) {
			value = String.valueOf(filter.get("tzjgr"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZJGR = '" + value + "'";
			}
		}
		// 首个还款日期
		if (filter.containsKey("sghkrq")) {
			value = String.valueOf(filter.get("sghkrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SGHKRQ = '" + value + "'";
			}
		}
		// 账单日
		if (filter.containsKey("zdr")) {
			value = String.valueOf(filter.get("zdr"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZDR = '" + value + "'";
			}
		}
		// 投资资金状态
		if (filter.containsKey("tzzjzt")) {
			value = String.valueOf(filter.get("tzzjzt"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZZJZT = '" + value + "'";
			}
		}
		// 使用期数
		if (filter.containsKey("syqs")) {
			value = String.valueOf(filter.get("syqs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYQS = '" + value + "'";
			}
		}
		// 匹配状态
		if (filter.containsKey("ppzt")) {
			value = String.valueOf(filter.get("ppzt"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PPZT = '" + value + "'";
			}
		}
		// 剩余出借周期
		if (filter.containsKey("lastCjzq")) {
			value = String.valueOf(filter.get("lastCjzq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LAST_CJZQ != '" + value + "'";
			}
		}
		// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除，

		sql = sql + " and a.STATE = '2'";
		sql = sql + " and a.HKSTATE = '2'";

		// 审核人
		if (filter.containsKey("auditPerson")) {
			value = String.valueOf(filter.get("auditPerson"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_PERSON = '" + value + "'";
			}
		}
		// 审核意见
		if (filter.containsKey("auditIdea")) {
			value = String.valueOf(filter.get("auditIdea"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_IDEA = '" + value + "'";
			}
		}
		// 审核时间
		if (filter.containsKey("auditTime")) {
			value = String.valueOf(filter.get("auditTime"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_TIME = '" + value + "'";
			}
		}
		// 变更状态-1，无修改，0暂存, 1待审批，3审批不通过
		if (filter.containsKey("upstate")) {
			value = String.valueOf(filter.get("upstate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.UPSTATE = '" + value + "'";
			}
		}

		// 投资产品
		if (filter.containsKey("tzcp")) {
			value = String.valueOf(filter.get("tzcp"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZCP_ID = '" + value + "'";
			}
		}

		// 剩余出借周期
		if (filter.containsKey("overstate")) {
			value = String.valueOf(filter.get("overstate"));
			if (StringUtils.isNotEmpty(value)) {
				if ("0".equals(value)) {
					sql = sql + " and a.OVERSTATE != '2'";
				} else {
					sql = sql + " and a.OVERSTATE = '" + value + "'";
				}
			}
		}
		// 首期天数
		if (filter.containsKey("firstdate")) {
			value = String.valueOf(filter.get("firstdate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and (a.LAST_CJZQ != '" + value
						+ "' or a.firstdate != '" + value + "')";
			}
		}
		if (filter.containsKey("cjrxm")) {
			value = String.valueOf(filter.get("cjrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.cjrxm like '%" + value + "%'";
			}
		}
		if (filter.containsKey("khbm")) {
			value = String.valueOf(filter.get("khbm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.khbm like '%" + value + "%'";
			}
		}

		if (filter.containsKey("province")) {
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.province = '" + value + "'";
			}
		}
		if (filter.containsKey("city")) {
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.city = '" + value + "'";
			}
		}

		// 级联查询sql
		// sql = sql + PropertiesUtils.getSql(filter);

		// if (page.getOrderBy()!=null){
		// sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		// }
		sql = sql + " order by  a.create_time desc";
		
		if (filter.containsKey("money")) {
			value = String.valueOf(filter.get("money"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2
						+ " and (to_number(MONEY)>0 or ZQTJ_ID is not null) ";
			}
		}
		if (filter.containsKey("lentcount")) {
			value = String.valueOf(filter.get("lentcount"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and LENTCOUNT=1 ";
			}
		}
		if (filter.containsKey("lenjhtzrq")) {
			value = String.valueOf(filter.get("lenjhtzrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and lenjhtzrq= '" + value + "'";
			}
		}
		if (filter.containsKey("backcount")) {
			value = String.valueOf(filter.get("backcount"));
			if (StringUtils.isNotEmpty(value)) {
				if (value.equals("0")) {
					sql2 = sql2 + " and backcount=0 ";
				} else {
					sql2 = sql2 + " and backcount>0 ";
				}
			}
		}
		// 到账日期
		if (filter.containsKey("jhjsrqMin")) {
			value = String.valueOf(filter.get("jhjsrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and JHJSRQ >= to_date('" + value
						+ "','yyyy-MM-dd')";
			}
		}

		// 到账日期
		if (filter.containsKey("jhjsrqMax")) {
			value = String.valueOf(filter.get("jhjsrqMax"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and JHJSRQ <= to_date('" + value
						+ "','yyyy-MM-dd')";
			}
		}
		if (filter.containsKey("yyb")) {
			value = String.valueOf(filter.get("yyb"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" + value + "'";
			}
		}
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return conditions;
	}
}
