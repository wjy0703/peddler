package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.MiddleManDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.xhcf.XhJkhtDao;
import cn.com.cucsi.app.dao.xhcf.XhMadewordDao;
import cn.com.cucsi.app.dao.xhcf.XhUploadFilesDao;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CnUpperCaser;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.Java2Word;
import cn.com.cucsi.app.web.util.MoneyUtil;
import cn.com.cucsi.app.web.util.converter.docConverter.DocConverter;
import cn.com.cucsi.app.web.util.converter.pdfConverter.OpenOfficePDFConverter;
import cn.com.cucsi.app.web.util.converter.pdfConverter.PDFConverter;
import cn.com.cucsi.app.web.util.converter.swfConverter.SWFConverter;
import cn.com.cucsi.app.web.util.converter.swfConverter.SWFToolsSWFConverter;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

import java.text.DecimalFormat;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhJkhtManager {

	private XhJkhtDao xhJkhtDao;
	private XhJksqDao xhJksqDao;
	private XhJksqStateDao xhJksqStateDao;
	private XhMadewordDao xhMadewordDao;
	@Autowired
	public void setXhMadewordDao(XhMadewordDao xhMadewordDao) {
		this.xhMadewordDao = xhMadewordDao;
	}
	@Autowired
	public void setXhJkhtDao(XhJkhtDao xhJkhtDao) {
		this.xhJkhtDao = xhJkhtDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Autowired
	public void setXhJksqDao(XhJksqDao xhJksqDao) {
		this.xhJksqDao = xhJksqDao;
	}

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	private BaseInfoManager baseInfoManager;
	
	@Transactional(readOnly = true)
	public Page<XhJkht> searchXhJkht(final Page<XhJkht> page,
			final Map<String, Object> filters) {
		return xhJkhtDao.queryXhJkht(page, filters);
	}

	@Transactional(readOnly = true)
	public XhJkht getXhJkht(Long id) {
		return xhJkhtDao.get(id);
	}

	private MiddleManDao middleManDao;

	@Autowired
	public void setMiddleManDao(MiddleManDao middleManDao) {
		this.middleManDao = middleManDao;
	}

	public void saveXhJkht(XhJkht entity) {
		xhJkhtDao.save(entity);
	}

	/**
	 * 
	 * @param xhJkht
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveXhJkht(XhJkht xhJkht, HttpServletRequest request) {

		
			XhJksq xhJksq = xhJkht.getXhJksq();

			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				if ("0".equals(opt)) {
					xhJksq.setState("50");
					xhJkhtDao.save(xhJkht);
				} else if ("1".equals(opt)) {
					//xhJksq.setState("51");
					//提交到待确定签署
					xhJksq.setState("70");
					xhJkhtDao.save(xhJkht);
					baseInfoManager.saveXhJksqState(xhJksq,"待审核利率完成,到待确定签署","");
				}
			}
			/*
			if ("1".equals(opt)) {
				if (createFile(xhJkht)) {
					xhJkhtDao.save(xhJkht);
					XhJksqState state = new XhJksqState();

					state.setXhjksq(xhJksq);
					state.setDescribe("完成合同制作,待审批");
					xhJksqStateDao.save(state);
				}

			}
*/
			return true;
	

	}
	
	/**
	 * 
	 * @param xhJkht
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveXhJkhtQianShu(XhJkht xhJkht, HttpServletRequest request) {
		
			XhJksq xhJksq = xhJkht.getXhJksq();
//			XhJksqState state = new XhJksqState();
			String mess = "";
			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				if ("0".equals(opt)) {
					xhJksq.setLoanCode("");
					xhJksq.setState("81");
					xhJkht.setJkhtbm("");
					mess = "待确定签署，客户放弃";
				} else if ("1".equals(opt)) {
					//xhJksq.setState("51");
					//提交到待确定签署
					xhJksq.setState("71");
					
					mess = "待确定签署完成,到待制作合同";
				}
				xhJkhtDao.save(xhJkht);
//				state.setXhjksq(xhJksq);
//				xhJksqStateDao.save(state);
				baseInfoManager.saveXhJksqState(xhJksq,mess,"");
			}
			return true;
	}
	
	/**
	 * 复议申请保存
	 * @param xhJkht
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveXhJkhtFuYi(XhJkht xhJkht, HttpServletRequest request) {
		
			XhJksq xhJksq = xhJkht.getXhJksq();
			String mess = "";
			
			xhJksq.setState("70.A");
			mess = "待复议,提交到信审审核";
			baseInfoManager.saveXhJksqState(xhJksq,mess,"");
			return true;
	}
	
	
	/**
	 * 
	 * @param xhJkht
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveXhJkhtMade(XhJkht xhJkht, HttpServletRequest request) {
		
			XhJksq xhJksq = xhJkht.getXhJksq();

			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				if ("0".equals(opt)) {
					xhJksq.setState("71");
				} else if ("1".equals(opt)) {
					//xhJksq.setState("51");
					//提交到待确定签署
					xhJksq.setState("51");
				}
			}
			
			
			if ("1".equals(opt)) {
				if (createFile(xhJkht)) {
					xhJkhtDao.save(xhJkht);
//					XhJksqState state = new XhJksqState();
//
//					state.setXhjksq(xhJksq);
//					state.setDescribe("完成合同制作,待审批");
//					xhJksqStateDao.save(state);
					baseInfoManager.saveXhJksqState(xhJksq,"完成合同制作,待审批","");
				}

			}
			return true;
	}
	/**
	 * 
	 * @param xhJkht
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean madeXhJkhtSave(XhJkht xhJkht, HttpServletRequest request) {

			if (createFile(xhJkht)) {
				//xhJkhtDao.save(xhJkht);
			}

			return true;
	}
	/**
	 * 
	 * 
	 * @param xhJkht
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveAuditXhJkht(XhJkht xhJkht) {
		try {
//			XhJksqState state = new XhJksqState();
			XhJksq xhJksq = xhJkht.getXhJksq();
			String mess="";
//			state.setXhjksq(xhJksq);
			if (xhJkht.getState().equals("1")) {
				xhJksq.setState("55");
				mess="合同制作审批通过,待签字";
			} else if (xhJkht.getState().equals("0")) {
				xhJksq.setState("52");
				mess="合同制作审批拒绝,待修改";
			}
			
			if(xhJksq.getState().equals("99")){
				System.out.println("合同签约超时,超时冻结");
				mess="合同签约超时,超时冻结";
			}
			
//			xhJksqStateDao.save(state);
			xhJkhtDao.save(xhJkht);
			baseInfoManager.saveXhJksqState(xhJksq,mess,"");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public boolean saveInvalidXhJkht(XhJkht xhJkht) {
		try {
//			XhJksqState state = new XhJksqState();
			XhJksq xhJksq = xhJkht.getXhJksq();
			xhJksq.setState("80");
			xhJkht.setState("9");
//			state.setXhjksq(xhJksq);
			
			System.out.println("合同签约超时,超时冻结");
//			state.setDescribe("合同签约超时,超时冻结");
			baseInfoManager.saveXhJksqState(xhJksq,"合同签约超时,超时冻结","");
//			xhJksqStateDao.save(state);
			xhJkhtDao.save(xhJkht);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public void saveXhJksq(XhJksq xhJksq){
		xhJksqDao.save(xhJksq);
	}
	
	public boolean saveAuditXhJkhtQzqr(XhJkht xhJkht, HttpServletRequest request) {
		boolean flag = false;
		// XhJksqState state = new XhJksqState();
		// xhJkhtDao.save(xhJkht);
		//
		// XhJksq xhJksq = xhJksqDao.get(xhJkht.getXhJksq().getId());
		//
		// if (xhJkht.getState().equals("2")) {
		// xhJksq.setState("60");
		// state.setDescribe("签字确认审批通过,待放款");
		// } else {
		// xhJksq.setState("57");
		// state.setDescribe("签字确认审批拒绝,待修改");
		// }
		// xhJksqStateDao.save(state);
		// return true;

		try {
//			XhJksqState state = new XhJksqState();
			XhJksq xhJksq = xhJkht.getXhJksq();
			String mess = "";
//			state.setXhjksq(xhJksq);
			if (xhJkht.getState().equals("2")) {
				/*xhJksq.setState("60");
				mess ="签字确认审批通过,待放款";*/
				xhJksq.setState("58");
				mess ="签字确认审批通过,待确认放款";
			} else {
//				xhJksq.setState("57");
				xhJksq.setState("55");
				mess ="签字确认审批拒绝,待修改";
			}
//			xhJksqStateDao.save(state);
			baseInfoManager.saveXhJksqState(xhJksq,mess,"");
			xhJkhtDao.save(xhJkht);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean saveXhJkhtQrfk(XhJksq xhJksq) {

		String mess = "";
		
		xhJksq.setState("60");
		mess ="待放款确认通过,待放款";
		
		baseInfoManager.saveXhJksqState(xhJksq,mess,"");
		this.xhJksqDao.save(xhJksq);
		return true;
	}
	
	public boolean saveXhJkhtQrfkBack(XhJksq xhJksq,String remark) {

		String mess = "";
		
		xhJksq.setState("88");
		mess ="待放款确认退回";
		
		baseInfoManager.saveXhJksqState(xhJksq,mess,remark);
		this.xhJksqDao.save(xhJksq);
		return true;
	}
	
	public boolean saveXhLoansBack(XhJksq xhJksq,String remark) {

		String mess = "";
		
		xhJksq.setState("89");
		mess ="放款失败退回";
		
		baseInfoManager.saveXhJksqState(xhJksq,mess,remark);
		this.xhJksqDao.save(xhJksq);
		return true;
	}
	
	/**
	 * 删除,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJkht(Long id) {
		xhJkhtDao.delete(id);
	}

	/**
	 * 删除,如果尝试删除超级管理员将抛出异常.
	 */
	public boolean deleteXhJkhtByjksq(Long jksqId) {
		XhJkht xhJkht = findLoanContarctByApplyID(jksqId);
		XhJksq xhJksq = xhJksqDao.get(jksqId);
		xhJksq.setState("50");

		xhJksqDao.save(xhJksq);
		xhJkhtDao.delete(xhJkht);
		return true;
	}
	
	public boolean batchDelXhJkht(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhJkht(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJkht(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 还款期数
		if (filter.containsKey("hkqs")) {
			value = String.valueOf(filter.get("hkqs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKQS = '" + value + "'";
			}
		}
		// 月综合费率
		if (filter.containsKey("yzhfl")) {
			value = String.valueOf(filter.get("yzhfl"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YZHFL = '" + value + "'";
			}
		}
		// 咨询费
		if (filter.containsKey("zxf")) {
			value = String.valueOf(filter.get("zxf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZXF = '" + value + "'";
			}
		}
		// 账户管理费
		if (filter.containsKey("zhglf")) {
			value = String.valueOf(filter.get("zhglf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZHGLF = '" + value + "'";
			}
		}
		// 月利息金额
		if (filter.containsKey("ylxje")) {
			value = String.valueOf(filter.get("ylxje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YLXJE = '" + value + "'";
			}
		}
		// 月利率
		if (filter.containsKey("yll")) {
			value = String.valueOf(filter.get("yll"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YLL = '" + value + "'";
			}
		}
		// 月还款金额
		if (filter.containsKey("yhkje")) {
			value = String.valueOf(filter.get("yhkje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YHKJE = '" + value + "'";
			}
		}
		// 月本金金额
		if (filter.containsKey("ybjje")) {
			value = String.valueOf(filter.get("ybjje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YBJJE = '" + value + "'";
			}
		}
		// 信用审核费
		if (filter.containsKey("xyshf")) {
			value = String.valueOf(filter.get("xyshf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XYSHF = '" + value + "'";
			}
		}
		// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除
		if (filter.containsKey("state")) {
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" + value + "'";
			}
		}
		// 起始还款日期
		if (filter.containsKey("qshkrq")) {
			value = String.valueOf(filter.get("qshkrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QSHKRQ = '" + value + "'";
			}
		}
		// 合同签订日期
		if (filter.containsKey("qdrq")) {
			value = String.valueOf(filter.get("qdrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QDRQ = '" + value + "'";
			}
		}
		// 借款合同编号
		if (filter.containsKey("jkhtbm")) {
			value = String.valueOf(filter.get("jkhtbm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JKHTBM = '" + value + "'";
			}
		}
		// 合同金额
		if (filter.containsKey("htje")) {
			value = String.valueOf(filter.get("htje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HTJE = '" + value + "'";
			}
		}
		// 服务费
		if (filter.containsKey("fwf")) {
			value = String.valueOf(filter.get("fwf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FWF = '" + value + "'";
			}
		}
		// 放款金额
		if (filter.containsKey("fkje")) {
			value = String.valueOf(filter.get("fkje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FKJE = '" + value + "'";
			}
		}
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
		if (filter.containsKey("auditDate")) {
			value = String.valueOf(filter.get("auditDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_DATE = '" + value + "'";
			}
		}
		// 信访费
		if (filter.containsKey("xff")) {
			value = String.valueOf(filter.get("xff"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XFF = '" + value + "'";
			}
		}

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

	public boolean createFile(XhJkht xhJkht) {
		//保存到制作表
		XhMadeword xhMadeword = new XhMadeword();
		xhMadeword.setState("0");
		xhMadeword.setStype("0");
		xhMadeword.setTableId(xhJkht.getId());
		xhMadeword.setTableName("xh_jkht");
		xhMadewordDao.save(xhMadeword);
		/*
		DecimalFormat df = new DecimalFormat("#.00");
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String mouFilePath2 = InitSetupListener.filePath + "agaeeFile"
				+ File.separator;
		deleteDirectory(mouFilePath2 + xhJkht.getJkhtbm());
		Java2Word j = new Java2Word();
		String htje_u = MoneyUtil.toChinese(df.format(xhJkht.getHtje()));

		HashMap htje_int = changeMap(new CnUpperCaser(df.format(xhJkht.getHtje())).getintMap());
		HashMap htje_float = new CnUpperCaser(df.format(xhJkht.getHtje()))
		.getfloatMap();


		//Double d_bxje = xhJkht.getYbjje() + xhJkht.getYlxje();
		Double d_bxje = xhJkht.getYhkje();
		String ybxje_u = MoneyUtil.toChinese(d_bxje.toString());
		HashMap bxje_int = changeMap(new CnUpperCaser(d_bxje.toString())
				.getintMap());
		HashMap bxje_float = new CnUpperCaser(d_bxje.toString()).getfloatMap();
		String s_city, s_county;
		if (xhJkht.getXhJksq().getOrgani().getOrganiDes() != null) {
			if (xhJkht.getXhJksq().getOrgani().getOrganiDes().indexOf("-") != -1) {
				String[] infos = xhJkht.getXhJksq().getOrgani().getOrganiDes()
						.split("-");

				s_city = infos[0];
				s_county = infos[1];

			} else {
				s_city = "    ";
				s_county = "    ";
			}
		} else {
			s_city = "    ";
			s_county = "    ";
		}
		//获取共借人信息
		String sql = "select t.identification,t.genders,t.together_name from XH_JKSQ_TOGETHER t where 1=1 ";
		sql = sql + " and t.xhjksq_id = "+xhJkht.getXhJksq().getId();
		List<Map<String, Object>> xhJt = jdbcDao.searchByMergeSql(sql);
		int sz = xhJt.size();
		
		HashMap data = new HashMap();
		data.put("{1}", xhJkht.getJkhtbm());// 借款编号
		data.put("seek{1}", xhJkht.getJkhtbm());// 借款编号
		data.put("{2-1}", Integer.toString(CreditHarmonyComputeUtilties
				.getYearbyDate(xhJkht.getQdrq())));// 签订日期-年
		data.put("{2-2}", Integer.toString(CreditHarmonyComputeUtilties
				.getMonthbyDate(xhJkht.getQdrq())));// 签订日期-月
		data.put("{2-3}", Integer.toString(CreditHarmonyComputeUtilties
				.getDaybyDate(xhJkht.getQdrq())));// 签订日期-日
		data.put("{3}", s_city);
		data.put("{4}", s_county);
		if(sz == 0){
			data.put("{5}", addLength(xhJkht.getXhJksq().getJkrxm(), 16, 2));// 借款人姓名
			data.put("{6}", addLength(xhJkht.getXhJksq().getZjhm(), 31, 1));// 借款人身份证号
		}else{
			data.put("{5}", addLength(xhJkht.getXhJksq().getJkrxm()+"；"+xhJt.get(0).get("TOGETHER_NAME"), 16, 2));// 借款人姓名+共借人
			data.put("{6}", addLength(xhJkht.getXhJksq().getZjhm()+"；", 31, 1));// 借款人身份证号
			data.put("{6-2}", addLength(xhJt.get(0).get("IDENTIFICATION")+"", 31, 1));// 共借人身份证号
		}
		data.put("{5-1}",
				addLength(xhJkht.getMiddleMan().getMiddleManName(), 38, 2));// 中间人姓名
		data.put("{6-1}", addLength(xhJkht.getMiddleMan().getIdCard(), 41, 1));// 中间人身份证号
		data.put("{7}", addLength(xhJkht.getXhJksq().getHomeAddress(), 37, 2));// 现住址
		data.put("{8}", xhJkht.getXhJksq().getJkUse());// 借款用途
		data.put("{9}", htje_u);// 借款本金数额（大写）

		// 借款本金数额（小写）
		data.put("{10}", MoneyUtil.toEnglish(df.format(xhJkht.getHtje())));//
		data.put("{10-y}", doubleToStr(htje_int.get(1)));
		data.put("{10-s}", doubleToStr(htje_int.get(2)));
		data.put("{10-b}", doubleToStr(htje_int.get(3)));
		data.put("{10-q}", doubleToStr(htje_int.get(4)));
		data.put("{10-w}", doubleToStr(htje_int.get(5)));
		data.put("{10-sw}", doubleToStr(htje_int.get(6)));
		data.put("{10-bw}", doubleToStr(htje_int.get(7)));
		data.put("{10-qw}", doubleToStr(htje_int.get(8)));
		data.put("{10-j}", doubleToStr(htje_float.get(1)));
		data.put("{10-f}", doubleToStr(htje_float.get(2)));

		data.put("{11}", ybxje_u);// 月还款额（大写）
		data.put("{12}", MoneyUtil.toEnglish(MoneyUtil
				.reBackTwo(doubleToStr(d_bxje))));// 月还款额或月本息
		// 月还款额（小写）
		data.put("{12-y}", doubleToStr(bxje_int.get(1)));
		data.put("{12-s}", doubleToStr(bxje_int.get(2)));
		data.put("{12-b}", doubleToStr(bxje_int.get(3)));
		data.put("{12-q}", doubleToStr(bxje_int.get(4)));
		data.put("{12-w}", doubleToStr(bxje_int.get(5)));
		data.put("{12-sw}", doubleToStr(bxje_int.get(6)));
		data.put("{12-bw}", doubleToStr(bxje_int.get(7)));
		data.put("{12-qw}", doubleToStr(bxje_int.get(8)));
		data.put("{12-j}", doubleToStr(bxje_float.get(1)));
		data.put("{12-f}", doubleToStr(bxje_float.get(2)));

		data.put("{13}", Integer.toString(xhJkht.getHkqs()));// 还款分期月数
		data.put("{14}", xhJkht.getHkr());// 还款日
		data.put(
				"{15}",
				xhJkht.getQshkrq()
						+ " 至 "
						+ CreditHarmonyComputeUtilties.getLastDateOfBackMoney(
								xhJkht.getQshkrq(), xhJkht.getHkqs()));// 还款起止日期
		data.put("{16}", xhJkht.getXhJksq().getBankUsername());// 银行卡户名
		data.put("{17}", xhJkht.getXhJksq().getBankNum());// 银行卡账号
		
		data.put("{18}", xhJkht.getXhJksq().getBankOpen());// 银行卡开户行
		data.put("{21}", MoneyUtil.toEnglish(MoneyUtil
				.reBackTwo(doubleToStr(CreditHarmonyComputeUtilties
						.getBreachMoney(d_bxje)))));// 1天逾期违约金
		data.put("{22}", MoneyUtil.toEnglish(CreditHarmonyComputeUtilties
				.getFineMoney(d_bxje, 1, xhJkht.getHkqs())));// 1天罚息
		data.put("{23}", MoneyUtil
				.toEnglish(doubleToStr(CreditHarmonyComputeUtilties
						.getBreachMoney(d_bxje))));// 16天逾期违约金
		data.put("{24}", MoneyUtil.toEnglish(MoneyUtil
				.reBackTwo(CreditHarmonyComputeUtilties.getFineMoney(d_bxje,
						16, xhJkht.getHkqs()))));// 16天罚息
		data.put("{26}", xhJkht.getXhJksq().getTelephone());// 借款人手机号
		data.put("{27}", MoneyUtil.toChinese(xhJkht.getZxf().toString()));// 咨询费（大写）
		data.put("{28}", MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhJkht.getZxf().toString())));// 咨询费（小写）
		data.put("{29}", MoneyUtil.toChinese(xhJkht.getXyshf().toString()));// 审核费（大写）
		data.put("{30}", MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhJkht
				.getXyshf().toString())));// 审核费（小写）
		data.put("{31}", MoneyUtil.toChinese(xhJkht.getFwf().toString()));// 服务费（大写）
		data.put("{32}", MoneyUtil.toEnglish(MoneyUtil.reBackTwo(xhJkht
				.getFwf().toString())));// 服务费（小写）
		Double sanfSum = (double) (Math.round((xhJkht.getZxf()
				+ xhJkht.getXyshf() + xhJkht.getFwf()) * 100)) / 100;
		data.put("{33}", MoneyUtil.toChinese(sanfSum.toString()));// 三项费用合计（大写）
		data.put("{34}", MoneyUtil.toEnglish(df.format(sanfSum)));// 三项费用合计（小写）
		data.put("{40}", reSex(xhJkht.getXhJksq().getGenders()));// 称呼 先生/女士
		double tuifei1 = sanfSum * 0.51;
		double tuifei2 = sanfSum * 0.51 * 0.915;
		double tuifei3 = tuifei1 - tuifei2;

		data.put("{tuifei1}", MoneyUtil.reBackTwo(df.format(tuifei1)));
		data.put("{tuifei2}", MoneyUtil.reBackTwo(df.format(tuifei2)));
		data.put("{tuifei3}", MoneyUtil.reBackTwo(df.format(tuifei3)));

		// 计算当期一次性回款
		double[] backMoneyOfAll = CreditHarmonyComputeUtilties
				.getBackMoneyOfAll(xhJkht.getHkqs(), xhJkht.getPdje(),
						xhJkht.getYbjje(), sanfSum);
		String[] allBackMoneyDates = CreditHarmonyComputeUtilties
				.getAllBackMoneyDates(xhJkht.getQshkrq(), xhJkht.getHkqs());

		PDFConverter pdfConverter = new OpenOfficePDFConverter();
		SWFConverter swfConverter = new SWFToolsSWFConverter();
		DocConverter converter = new DocConverter(pdfConverter, swfConverter);
		if(sz == 0){
		j.toWord(
				mouFilePath + "loanAgreement.doc",
				mouFilePath2 + xhJkht.getJkhtbm() + File.separator
						+ xhJkht.getJkhtbm() + "loanAgreement.doc", data);
		}else{
			j.toWord(
					mouFilePath + "loanAgreement2.doc",
					mouFilePath2 + xhJkht.getJkhtbm() + File.separator
							+ xhJkht.getJkhtbm() + "loanAgreement.doc", data);
		}
		converter.convert(mouFilePath2 + xhJkht.getJkhtbm() + File.separator
				+ xhJkht.getJkhtbm() + "loanAgreement.doc");
		// j.wordToSwf(mouFilePath + xhJkht.getJkhtbm() + File.separator
		// + "jkxy.doc", mouFilePath + xhJkht.getJkhtbm() + File.separator
		// + "jkxy.swf");

		j.toWord(
				mouFilePath + "powerOfAttorney.doc",
				mouFilePath2 + xhJkht.getJkhtbm() + File.separator
						+ xhJkht.getJkhtbm() + "powerOfAttorney.doc", data);
		converter.convert(mouFilePath2 + xhJkht.getJkhtbm() + File.separator
				+ xhJkht.getJkhtbm() + "powerOfAttorney.doc");
		if(sz == 0){
		j.toWord(mouFilePath + "managementServicesAgreement.doc", mouFilePath2
				+ xhJkht.getJkhtbm() + File.separator + xhJkht.getJkhtbm()
				+ "managementServicesAgreement.doc", data);
		}else{
			j.toWord(mouFilePath + "managementServicesAgreement2.doc", mouFilePath2
					+ xhJkht.getJkhtbm() + File.separator + xhJkht.getJkhtbm()
					+ "managementServicesAgreement.doc", data);
		}
		converter.convert(mouFilePath2 + xhJkht.getJkhtbm() + File.separator
				+ xhJkht.getJkhtbm() + "managementServicesAgreement.doc");

		List l1 = new ArrayList();
		*/
		/*
		 * String[] a = new String[9]; a[0] = "2"; a[1] = "3"; a[2] = "4"; a[3]
		 * = "6"; a[4] = "7"; a[5] = "8"; a[6] = "10"; a[7] = "11"; a[8] = "12";
		 * l1.add(a); a = new String[9]; int hkqs = xhJkht.getHkqs() - 1; for
		 * (int i = 0; i <= hkqs; i++) { if (i == hkqs) { a[i % 3 * 3] =
		 * allBackMoneyDates[i]; a[i % 3 * 3 + 1] =
		 * MoneyUtil.toEnglish(doubleToStr(d_bxje)); a[i % 3 * 3 + 2] =
		 * MoneyUtil.toEnglish(doubleToStr(d_bxje)); } else { a[i % 3 * 3] =
		 * allBackMoneyDates[i]; a[i % 3 * 3 + 1] =
		 * MoneyUtil.toEnglish(doubleToStr(d_bxje)); a[i % 3 * 3 + 2] =
		 * MoneyUtil .toEnglish(doubleToStr(backMoneyOfAll[i])); } if ((i + 1) %
		 * 3 == 0) { l1.add(a); a = new String[9]; } } if (xhJkht.getHkqs() % 3
		 * != 0) { for (int i = 0; i < a.length; i++) { if (a[i] == null) { a[i]
		 * = ""; } } l1.add(a); }
		 */
		/*
		String[] a = new String[12];
		for (int i = 0; i < 12; i++) {
			a[i] = String.valueOf(i + 1);
		}
		// a[0] = "1";
		// a[1] = "2";
		// a[2] = "3";
		// a[3] = "4";
		//
		// a[4] = "5";
		// a[5] = "6";
		// a[6] = "7";
		// a[7] = "8";
		//
		// a[8] = "9";
		// a[9] = "10";
		// a[10] = "11";
		// a[11] = "12";
		l1.add(a);
		a = new String[12];
		int hkqs = xhJkht.getHkqs() - 1;
		for (int i = 0; i <= hkqs; i++) {
			if (i == hkqs) {
				a[i % 3 * 3 + (i % 3)] = (i + 1) + "";
				a[i % 3 * 3 + (i % 3) + 1] = allBackMoneyDates[i];
				a[i % 3 * 3 + (i % 3) + 2] = MoneyUtil
						.toEnglish(MoneyUtil.reBackTwo(doubleToStr(d_bxje)));
				a[i % 3 * 3 + (i % 3) + 3] = MoneyUtil
						.toEnglish(MoneyUtil.reBackTwo(doubleToStr(d_bxje)));
			} else {
				a[i % 3 * 3 + (i % 3)] = (i + 1) + "";
				a[i % 3 * 3 + (i % 3) + 1] = allBackMoneyDates[i];
				a[i % 3 * 3 + (i % 3) + 2] = MoneyUtil
						.toEnglish(MoneyUtil.reBackTwo(doubleToStr(d_bxje)));
				a[i % 3 * 3 + (i % 3) + 3] = MoneyUtil
						.toEnglish(MoneyUtil.reBackTwo(doubleToStr(backMoneyOfAll[i])));
			}
			if ((i + 1) % 3 == 0) {
				l1.add(a);
				a = new String[12];
			}
		}
		if (xhJkht.getHkqs() % 3 != 0) {
			for (int i = xhJkht.getHkqs() % 3 * 4; i < a.length; i++) {
				if (a[i] == null) {
					a[i] = "";
				}
			}
			l1.add(a);
		}
		data.put("table$2@2", l1);
		j.toWord(
				mouFilePath + "repaymentSynopsis.doc",
				mouFilePath2 + xhJkht.getJkhtbm() + File.separator
						+ xhJkht.getJkhtbm() + "repaymentSynopsis.doc", data);
		converter.convert(mouFilePath2 + xhJkht.getJkhtbm() + File.separator
				+ xhJkht.getJkhtbm() + "repaymentSynopsis.doc");
		j.quit();
		*/
		return true;
	}

	@Transactional(readOnly = true)
	public List<MiddleMan> getSuggestMiddleMan(String parentId) {
		return middleManDao.getSuggestMiddleMan(parentId);
	}

	/**
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public XhJkht findLoanContarctByApplyID(Long id) {

		return xhJkhtDao.findLoanContarctByApplyID(id);
	}

	public String doubleToStr(Object obj) {
		String re = "";
		if (obj == null) {
			re = "";
		} else {
			re = obj.toString();
		}
		return re;
	}

	/**
	 * 添加空格，保持上下行下划线长度一致 入参：内容，空格数量，长度补偿（内容为汉字长度为其他的2倍）
	 */
	public String addLength(String value, int leng, int hz) {
		String result = value;
		if (value != null && !value.equals("")) {
			leng = leng - value.length() * hz;
		}
		System.out.println("leng====>" + leng);
		for (int i = 0; i < leng; i++) {
			result += " ";
		}
		System.out.println("result====>" + result);
		return result;
	}

	/**
	 * 整数位补"￥"符号
	 */
	public HashMap changeMap(HashMap value) {
		HashMap result = value;
		if (!result.isEmpty()) {
			for (int i = 1; i < 9; i++) {
				if (i < 8) {
					if (result.get(i) != null && result.get(i + 1) == null) {
						result.put(i + 1, "￥");
						break;
					}
				}
			}
		}
		return result;
	}

	public String reSex(String sex) {
		if (null != sex && "女".equals(sex)) {
			return "女士";
		} else {
			return "先生";
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除文件失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除文件成功");
			return true;
		} else {
			System.out.println("删除文件失败");
			return false;
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	/**
	 * 获取银行名称
	 * @param vale
	 * @return
	 */
	public String reBlankName(String vale){
		String result = "";
		if(null != vale && !"".equals(vale)){
			String sql = "select a.name from xh_mate_data a,XH_MATEDATA_TYPE b where a.matedatatype_id=b.id and b.type_code='0001' ";
			sql = sql + " and a.value = '"+vale+"'";
			List<Map<String, Object>> getBlankName = jdbcDao.searchByMergeSql(sql);
			if(getBlankName.size()==1){
				result = getBlankName.get(0).get("NAME")+"";
			}
		}
		return result;
	}

}
