package cn.com.cucsi.app.service.loan;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.LoanTaskDelivDao;
import cn.com.cucsi.app.dao.loan.XhJkjjlxrDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.loan.XhJksqTogetherDao;
import cn.com.cucsi.app.dao.loan.XhJksqUPHistoryDao;
import cn.com.cucsi.app.dao.security.XydkzxDao;
import cn.com.cucsi.app.dao.templet.TempletDao;
import cn.com.cucsi.app.dao.xhcf.XhCreditAuditDao;
import cn.com.cucsi.app.dao.xhcf.XhJkhtDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.entity.xhcf.LoanTaskDeliv;
import cn.com.cucsi.app.entity.xhcf.Templet;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.entity.xhcf.XhJksqUPHistory;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.BeanUtilEx;
import cn.com.cucsi.app.web.util.Java2Excel;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhJksqManager extends PublicService {

	private final static String GET_TASK_BY_EMPLOYEE_SQL = "getTaskByStateAndEmployee";

	private BaseInfoManager baseInfoManager;

	private XhJksqDao xhJksqDao;
	private XhJksqStateDao xhJksqStateDao;
	private JdbcDao jdbcDao;
	private TempletDao templetDao;
	private XydkzxDao xydkzxDao;
	private XhJkjjlxrDao xhJkjjlxrDao;
	private XhJksqTogetherDao xhJksqTogetherDao;
	private XhJkhtDao xhJkhtDao;
	private XhJksqUPHistoryDao xhJksqUPHistoryDao;
	private XhCreditAuditDao xhCreditAuditDao;
	private LoanTaskDelivDao loanTaskDelivDao;
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	public Page<XhJksq> searchXhJksq(final Page<XhJksq> page,
			final Map<String, Object> filters) {
		return xhJksqDao.queryXhJksq(page, filters);
	}

	@Transactional(readOnly = true)
	public XhJksq getXhJksq(Long id) {
		return xhJksqDao.get(id);
	}

	public void saveXhJksq(XhJksq entity) {
		xhJksqDao.save(entity);
	}

	public void saveXhJkjjlxr(XhJkjjlxr entity) {
		xhJkjjlxrDao.save(entity);
	}

	/**
	 * 保存循环贷借款申请信息
	 * 
	 * @param request
	 * @param xhjksq
	 * @return
	 */
	public String saveLoopXhJksq(HttpServletRequest request, XhJksq xhjksq) {
		Xydkzx xydkzx = getXydkzx(request);
		if (xhjksq != null && xhjksq.getId() != null) {
			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				if ("0".equals(opt)) {
					xhjksq.setState("0");// 暂存
				} else if ("1".equals(opt)) {
					if ("是".equals(xhjksq.getTogetherPerson())) {
						xhjksq.setState("01");// 01：已提交，待填写共同还款人资料
					} else {
						xhjksq.setState("02");// 02：已提交，待上传授信资料
					}
					xydkzx.setZhuangTai("3");
					xydkzx.setLastModifyTime(new Timestamp(new Date().getTime()));
					xhjksq.setSubState("1");
				}

				// 保存紧急联系人
				List<XhJkjjlxr> xhJkjjlxrs = getXhJkjjlxrSet(request, xhjksq);
				xhjksq.setXhJkjjlxrs(xhJkjjlxrs);
				// 保存借款申请动态信息
				Templet templet = getXhjksqTemplet(request);
				xhjksq.setTemplet(templet);
				String data = getXhjksqDynamicData(request, templet);
				xhjksq.setData(data);
				// 借款咨询信息
				xhjksq.setXydkzx(xydkzx);
				xhjksq.setOrgani(xydkzx.getOrgani());

				// 贷款标志位
				xhjksq.setBackup01("CREDIT");// 信贷
				xhjksq.setBackup02(this.getFormatDate());

				xhjksq.setAppState("-1");// 无借款申请变更
				if ("01".equals(xhjksq.getState())
						|| "02".equals(xhjksq.getState())) {
					xhjksq.setSubState("1");
				}
				xhJksqDao.save(xhjksq);
				xydkzxDao.save(xydkzx);
				if ("0".equals(xhjksq.getState())) {
					baseInfoManager.saveXhJksqState(xhjksq, "101");
				} else if ("01".equals(xhjksq.getState())) {
					baseInfoManager.saveXhJksqState(xhjksq, "102");
					XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(
							xhjksq);
					xhJksqUPHistoryDao.save(xhJksqUPHistory);
					saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
				} else if ("02".equals(xhjksq.getState())) {
					baseInfoManager.saveXhJksqState(xhjksq, "103");
					XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(
							xhjksq);
					xhJksqUPHistoryDao.save(xhJksqUPHistory);
					saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
				}
			}
		}
		return "true";
	}

	/**
	 * 保存借款申请信息
	 * 
	 * @param request
	 * @param xhjksq
	 * @return
	 */
	public String saveXhJksq(HttpServletRequest request, XhJksq xhjksq) {
		String flag = "false";
		// 借款咨询信息
		Xydkzx xydkzx = getXydkzx(request);
		List<XhJksq> list = xhJksqDao.listJksqByXy(xydkzx.getId());
		if (null == list || list.size() == 0) {
			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				System.out
						.println("===================================jksqInpt页面opt 传值bug调试===============================================");
				System.out.println("jksqInpt页面opt控件 值为：" + opt);

				if ("1".equals(opt.trim())) {
					if ("是".equals(xhjksq.getTogetherPerson())) {
						xhjksq.setState("01");// 01：已提交，待填写共同还款人资料
					} else {
						xhjksq.setState("02");// 02：已提交，待上传授信资料
					}
					xydkzx.setZhuangTai("3");
					xydkzx.setLastModifyTime(new Timestamp(new Date().getTime()));
					xhjksq.setSubState("1");
				} else {// OPT不为1全部按暂存处理
					xhjksq.setState("0");// 暂存 ，opt传入值不等于1情况下,均也按"暂存"状态处理
				}
				System.out
						.println("===================================jksqInpt页面opt 传值bug调试===============================================");
			} else {// 调试opt BUG

				System.out
						.println("===================================jksqInpt页面opt 传值bug调试===============================================");
				System.out.println("jksqInpt页面opt控件 值为：" + opt);
				System.out.println("opt为Null or opt为'' .........");
				System.out.println("opt为Null or opt为'' .........");
				System.out.println("opt为Null or opt为'' .........");
				System.out.println("opt为Null or opt为'' .........");

				xhjksq.setState("0");// 暂存 ，opt值未传入也按"暂存"状态处理
				System.out
						.println("===================================jksqInpt页面opt 传值bug调试===============================================");
			}

			// 借款咨询信息
			xhjksq.setXydkzx(xydkzx);

			// 保存紧急联系人
			List<XhJkjjlxr> xhJkjjlxrs = getXhJkjjlxrSet(request, xhjksq);
			xhjksq.setXhJkjjlxrs(xhJkjjlxrs);

			// 保存借款申请动态信息
			Templet templet = getXhjksqTemplet(request);
			xhjksq.setTemplet(templet);
			String data = getXhjksqDynamicData(request, templet);
			xhjksq.setData(data);

			// 组织
			// Employee emp = baseInfoManager.getUserEmployee();
			// xhjksq.setOrgani(emp.getOrgani());
			xhjksq.setOrgani(xydkzx.getOrgani());

			// 贷款标志位
			xhjksq.setBackup01("CREDIT");// 信贷

			xhjksq.setAppState("-1");// 无借款申请变更
			if ("01".equals(xhjksq.getState())
					|| "02".equals(xhjksq.getState())) {
				xhjksq.setSubState("1");
			}
			xhJksqDao.save(xhjksq);
			if ("0".equals(xhjksq.getState())) {
				baseInfoManager.saveXhJksqState(xhjksq, "正在填写借款申请，待完善资料提交",
						"正在填写借款申请，待完善资料提交");
			} else if ("01".equals(xhjksq.getState())) {
				baseInfoManager.saveXhJksqState(xhjksq, "已提交，待填写共同还款人资料",
						"已提交，待填写共同还款人资料");
				XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhjksq);
				xhJksqUPHistoryDao.save(xhJksqUPHistory);
				saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
			} else if ("02".equals(xhjksq.getState())) {
				baseInfoManager.saveXhJksqState(xhjksq, "已提交，待上传授信资料",
						"已提交，待上传授信资料");
				XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhjksq);
				xhJksqUPHistoryDao.save(xhJksqUPHistory);
				saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
			}
			if (null != xhjksq.getId() && 0 != xhjksq.getId()) {
				flag = "true";
			}

		} else {
			flag = "exist";
		}

		return flag;
	}

	/**
	 * 修改借款申请信息
	 * 
	 * @param request
	 * @param xhjksq
	 * @return
	 */
	public boolean editSaveXhJksq(HttpServletRequest request, XhJksq xhjksq) {
		boolean flag = false;

		XhJksq xhJksq = saveEditXhJksq(xhjksq);
		// 初审退回插入时间MDY 2013-08-02
		String state = xhJksq.getState();
		if ("31.B".equals(state)) {
			String time = getFormatDate();
			xhJksq.setBackup03(time);
		}
		// 结束
		if (null != xhJksq.getState() && xhJksq.getState().contains(".")) {
			String stateTemp = xhJksq.getState();
			stateTemp = stateTemp.replace(".B", "");
			xhJksq.setState(stateTemp);
			baseInfoManager.saveXhJksqState(xhJksq, "已修正申请信息,待重新信审",
					"已修正申请信息,待重新信审");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);

		} else {
			// 借款咨询信息
			Xydkzx xydkzx = getXydkzx(request);

			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				if ("0".equals(opt)) {
					xhJksq.setState("0");// 暂存
				} else if ("1".equals(opt)) {
					if ("是".equals(xhJksq.getTogetherPerson())) {
						xhJksq.setState("01");// 01：已提交，待填写共同还款人资料
					} else {
						xhJksq.setState("02");// 02：已提交，待上传授信资料
					}
					xydkzx.setZhuangTai("3");
					xydkzx.setLastModifyTime(new Timestamp(new Date().getTime()));
					xhJksq.setSubState("1");
				}
			}
			// 借款咨询信息
			xhJksq.setXydkzx(xydkzx);
		}

		// 保存借款申请动态信息
		Templet templet = getXhjksqTemplet(request);
		xhJksq.setTemplet(templet);
		String data = getXhjksqDynamicData(request, templet);
		xhJksq.setData(data);

		// 组织
		// Employee emp = baseInfoManager.getUserEmployee();
		// xhJksq.setOrgani(emp.getOrgani());
		// xhJksq.setOrgani(xydkzx.getOrgani());

		Timestamp tempTimeStamp = xhJksq.getLastModifyTime();// 保存前的最后修改日期
		xhJksq.setLastModifyTime(new Timestamp(new Date().getTime()));

		xhJksqDao.save(xhJksq);
		if ("0".equals(xhJksq.getState())) {
			baseInfoManager.saveXhJksqState(xhJksq, "已修改，待完善信息提交",
					"已修改，待完善信息提交");
		} else if ("01".equals(xhJksq.getState())) {
			baseInfoManager.saveXhJksqState(xhJksq, "已提交，待填写共同还款人资料",
					"已提交，待填写共同还款人资料");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
		} else if ("02".equals(xhJksq.getState())) {
			baseInfoManager.saveXhJksqState(xhJksq, "已修改，待上传授信资料",
					"已修改，待上传授信资料");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
		} else if (null != xhJksq.getState() && xhJksq.getState().contains(".")) {
			baseInfoManager.saveXhJksqState(xhJksq, "已修正申请信息,待重新信审",
					"已修正申请信息,待重新信审");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
		}

		// 保存紧急联系人
		editSaveXhJkjjlxr(request, xhjksq, null);

		XhJksq newXhJksq = xhJksqDao.get(xhJksq.getId());

		if (null == tempTimeStamp && null != newXhJksq.getLastModifyTime()) {
			flag = true;
		} else {
			if (tempTimeStamp.getTime() != newXhJksq.getLastModifyTime()
					.getTime()) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 借款申请评分
	 * 
	 * @param request
	 * @param xhjksq
	 * @return
	 */
	public boolean saveJksqPf(HttpServletRequest request, XhJksq xhjksq) {
		boolean flag = false;

		XhJksq xhJksq = saveEditXhJksq(xhjksq);
		// 初审退回插入时间MDY 2013-08-02
		String state = xhJksq.getState();
		if ("31.B".equals(state)) {
			String time = getFormatDate();
			xhJksq.setBackup03(time);
		}
		// 结束
		if (null != xhJksq.getState() && xhJksq.getState().contains(".")) {
			String stateTemp = xhJksq.getState();
			stateTemp = stateTemp.replace(".B", "");
			xhJksq.setState(stateTemp);
			baseInfoManager.saveXhJksqState(xhJksq, "已修正申请信息,待重新信审",
					"已修正申请信息,待重新信审");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);

		} else {
			// 借款咨询信息
			Xydkzx xydkzx = getXydkzx(request);

			String opt = request.getParameter("opt");
			if (null != opt && !"".equals(opt)) {
				if ("0".equals(opt)) {
					xhJksq.setState("0");// 暂存
				} else if ("1".equals(opt)) {
					if ("是".equals(xhJksq.getTogetherPerson())) {
						xhJksq.setState("01");// 01：已提交，待填写共同还款人资料
					} else {
						xhJksq.setState("02");// 02：已提交，待上传授信资料
					}
					xydkzx.setZhuangTai("3");
					xydkzx.setLastModifyTime(new Timestamp(new Date().getTime()));
					xhJksq.setSubState("1");
				}
			}
			// 借款咨询信息
			xhJksq.setXydkzx(xydkzx);
		}

		// 保存借款申请动态信息
		Templet templet = getXhjksqTemplet(request);
		xhJksq.setTemplet(templet);
		String data = getXhjksqDynamicData(request, templet);
		xhJksq.setData(data);

		// 组织
		// Employee emp = baseInfoManager.getUserEmployee();
		// xhJksq.setOrgani(emp.getOrgani());
		// xhJksq.setOrgani(xydkzx.getOrgani());

		Timestamp tempTimeStamp = xhJksq.getLastModifyTime();// 保存前的最后修改日期
		xhJksq.setLastModifyTime(new Timestamp(new Date().getTime()));

		xhJksqDao.save(xhJksq);
		if ("0".equals(xhJksq.getState())) {
			baseInfoManager.saveXhJksqState(xhJksq, "已修改，待完善信息提交",
					"已修改，待完善信息提交");
		} else if ("01".equals(xhJksq.getState())) {
			baseInfoManager.saveXhJksqState(xhJksq, "已提交，待填写共同还款人资料",
					"已提交，待填写共同还款人资料");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
		} else if ("02".equals(xhJksq.getState())) {
			baseInfoManager.saveXhJksqState(xhJksq, "已修改，待上传授信资料",
					"已修改，待上传授信资料");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
		} else if (null != xhJksq.getState() && xhJksq.getState().contains(".")) {
			baseInfoManager.saveXhJksqState(xhJksq, "已修正申请信息,待重新信审",
					"已修正申请信息,待重新信审");
			XhJksqUPHistory xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
		}

		// 保存紧急联系人
		editSaveXhJkjjlxr(request, xhjksq, null);

		XhJksq newXhJksq = xhJksqDao.get(xhJksq.getId());

		if (null == tempTimeStamp && null != newXhJksq.getLastModifyTime()) {
			flag = true;
		} else {
			if (tempTimeStamp.getTime() != newXhJksq.getLastModifyTime()
					.getTime()) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 补充借款申请信息
	 * 
	 * @param request
	 * @param xhjksq
	 * @return
	 */
	public boolean complementSaveJksq(HttpServletRequest request, XhJksq xhjksq) {
		boolean flag = false;

		XhJksq xhJksq = saveEditXhJksq(xhjksq);

		Timestamp tempTimeStamp = xhJksq.getLastModifyTime();// 保存前的最后修改日期
		xhJksq.setLastModifyTime(new Timestamp(new Date().getTime()));

		xhJksqDao.save(xhJksq);

		// 保存紧急联系人
		editSaveXhJkjjlxr(request, xhjksq, null);

		XhJksq newXhJksq = xhJksqDao.get(xhJksq.getId());

		if (null == tempTimeStamp && null != newXhJksq.getLastModifyTime()) {
			flag = true;
			baseInfoManager.saveXhJksqState(xhJksq, "补充借款申请资料成功", "补充借款申请资料");
		} else {
			if (tempTimeStamp.getTime() != newXhJksq.getLastModifyTime()
					.getTime()) {
				flag = true;
				baseInfoManager.saveXhJksqState(xhJksq, "补充借款申请资料成功",
						"补充借款申请资料");
			} else {
				baseInfoManager.saveXhJksqState(xhJksq, "补充借款申请资料失败",
						"补充借款申请资料");
			}
		}

		return flag;
	}

	private XhJksq saveEditXhJksq(XhJksq old) {
		XhJksq xhJksq = xhJksqDao.get(old.getId());
		// 借款人姓名
		if (null != old.getJkrxm() && !"".equals(old.getJkrxm().trim())) {
			xhJksq.setJkrxm(old.getJkrxm().trim());
		}
		// 英文名称
		if (null != old.getEnglishName()
				&& !"".equals(old.getEnglishName().trim())) {
			xhJksq.setEnglishName(old.getEnglishName().trim());
		}
		// 性别
		if (null != old.getGenders() && !"".equals(old.getGenders().trim())) {
			xhJksq.setGenders(old.getGenders().trim());
		}
		// 出生日期
		if (null != old.getBirthday() && !"".equals(old.getBirthday().trim())) {
			xhJksq.setBirthday(old.getBirthday().trim());
		}
		// 证件类型
		if (null != old.getPocertificates()
				&& !"".equals(old.getPocertificates().trim())) {
			xhJksq.setPocertificates(old.getPocertificates().trim());
		}
		// 证件号码
		if (null != old.getZjhm() && !"".equals(old.getZjhm().trim())) {
			xhJksq.setZjhm(old.getZjhm().trim());
		}
		// 户籍地址
		if (null != old.getHjadress() && !"".equals(old.getHjadress().trim())) {
			xhJksq.setHjadress(old.getHjadress().trim());
		}
		// 现地址
		if (null != old.getHomeAddress()
				&& !"".equals(old.getHomeAddress().trim())) {
			xhJksq.setHomeAddress(old.getHomeAddress().trim());
		}
		// 家庭电话
		if (null != old.getHomePhone() && !"".equals(old.getHomePhone().trim())) {
			xhJksq.setHomePhone(old.getHomePhone().trim());
		}
		// 工作单位
		if (null != old.getCompany() && !"".equals(old.getCompany().trim())) {
			xhJksq.setCompany(old.getCompany().trim());
		}
		// 单位电话
		if (null != old.getCompanyPhone()
				&& !"".equals(old.getCompanyPhone().trim())) {
			xhJksq.setCompanyPhone(old.getCompanyPhone().trim());
		}
		// 单位地址
		if (null != old.getCompanyAdress()
				&& !"".equals(old.getCompanyAdress().trim())) {
			xhJksq.setCompanyAdress(old.getCompanyAdress().trim());
		}
		// 单位性质
		if (null != old.getCompanyNature()
				&& !"".equals(old.getCompanyNature())) {
			xhJksq.setCompanyNature(old.getCompanyNature().trim());
		}
		// 职业
		if (null != old.getZy() && !"".equals(old.getZy())) {
			xhJksq.setZy(old.getZy().trim());
		}
		// 移动电话
		if (null != old.getTelephone() && !"".equals(old.getTelephone().trim())) {
			xhJksq.setTelephone(old.getTelephone().trim());
		}
		// 电子邮箱
		if (null != old.getEmail() && !"".equals(old.getEmail().trim())) {
			xhJksq.setEmail(old.getEmail().trim());
		}
		// 婚姻状况
		if (null != old.getMaritalStatus()
				&& !"".equals(old.getMaritalStatus().trim())) {
			xhJksq.setMaritalStatus(old.getMaritalStatus().trim());
		}
		// 有无子女
		if (null != old.getYwzn() && !"".equals(old.getYwzn().trim())) {
			xhJksq.setYwzn(old.getYwzn().trim());
		}
		// QQ号码
		if (null != old.getQqhm() && !"".equals(old.getQqhm().trim())) {
			xhJksq.setQqhm(old.getQqhm().trim());
		}
		// 年收入
		if (null != old.getAnnualIncome()
				&& !"".equals(old.getAnnualIncome().trim())) {
			xhJksq.setAnnualIncome(old.getAnnualIncome().trim());
		}
		// 收入说明
		if (null != old.getIncomeIllustration()
				&& !"".equals(old.getIncomeIllustration().trim())) {
			xhJksq.setIncomeIllustration(old.getIncomeIllustration().trim());
		}
		// 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租*元/月;04:其他
		if (null != old.getLiveState() && !"".equals(old.getLiveState().trim())) {
			xhJksq.setLiveState(old.getLiveState().trim());
		}
		// 居住说明信息
		if (null != old.getLiveMessage()
				&& !"".equals(old.getLiveMessage().trim())) {
			xhJksq.setLiveMessage(old.getLiveMessage().trim());
		}
		// 通讯地址
		if (null != old.getTxdz() && !"".equals(old.getTxdz().trim())) {
			xhJksq.setTxdz(old.getTxdz().trim());
		}
		// 省份
		if (null != old.getProvince()) {
			xhJksq.setProvince(old.getProvince());
		}
		// 地市
		if (null != old.getCity()) {
			xhJksq.setCity(old.getCity());
		}
		// 区县
		if (null != old.getArea()) {
			xhJksq.setArea(old.getArea());
		}

		// 放款金额
		if (null != old.getFkje() && !"".equals(old.getFkje().trim())) {
			xhJksq.setFkje(old.getFkje().trim());
		}

		// 配偶信息
		// 配偶姓名
		if (null != old.getSpouseName()
				&& !"".equals(old.getSpouseName().trim())) {
			xhJksq.setSpouseName(old.getSpouseName().trim());
		}
		// 配偶性别
		if (null != old.getSpouseGenders()
				&& !"".equals(old.getSpouseGenders().trim())) {
			xhJksq.setSpouseGenders(old.getSpouseGenders().trim());
		}
		// 配偶出生日期
		if (null != old.getSpouseBirthday()
				&& !"".equals(old.getSpouseBirthday().trim())) {
			xhJksq.setSpouseBirthday(old.getSpouseBirthday().trim());
		}
		// 配偶现住址
		if (null != old.getSpouseAdress()
				&& !"".equals(old.getSpouseAdress().trim())) {
			xhJksq.setSpouseAdress(old.getSpouseAdress().trim());
		}
		// 配偶证件类型
		if (null != old.getSpousePocertificates()
				&& !"".equals(old.getSpousePocertificates().trim())) {
			xhJksq.setSpousePocertificates(old.getSpousePocertificates().trim());
		}
		// 配偶证件号码
		if (null != old.getSpouseZjhm()
				&& !"".equals(old.getSpouseZjhm().trim())) {
			xhJksq.setSpouseZjhm(old.getSpouseZjhm().trim());
		}
		// 配偶移动电话
		if (null != old.getSpouseTelephone()
				&& !"".equals(old.getSpouseTelephone().trim())) {
			xhJksq.setSpouseTelephone(old.getSpouseTelephone().trim());
		}
		// 配偶家庭电话
		if (null != old.getSpouseHomePhone()
				&& !"".equals(old.getSpouseHomePhone().trim())) {
			xhJksq.setSpouseHomePhone(old.getSpouseHomePhone().trim());
		}
		// 配偶工作单位
		if (null != old.getSpouseCompany()
				&& !"".equals(old.getSpouseCompany().trim())) {
			xhJksq.setSpouseCompany(old.getSpouseCompany().trim());
		}
		// 配偶部门与职务
		if (null != old.getSpouseDepFunction()
				&& !"".equals(old.getSpouseDepFunction().trim())) {
			xhJksq.setSpouseDepFunction(old.getSpouseDepFunction().trim());
		}
		// 配偶单位电话
		if (null != old.getSpouseCompanyPhone()
				&& !"".equals(old.getSpouseCompanyPhone().trim())) {
			xhJksq.setSpouseCompanyPhone(old.getSpouseCompanyPhone().trim());
		}
		// 配偶单位地址
		if (null != old.getSpouseCompanyAdress()
				&& !"".equals(old.getSpouseCompanyAdress().trim())) {
			xhJksq.setSpouseCompanyAdress(old.getSpouseCompanyAdress().trim());
		}
		// 配偶年收入
		if (null != old.getSpouseAnnualIncome()
				&& !"".equals(old.getSpouseAnnualIncome().trim())) {
			xhJksq.setSpouseAnnualIncome(old.getSpouseAnnualIncome().trim());
		}
		// 配偶工作年限
		if (null != old.getSpouseWorkinglife()
				&& !"".equals(old.getSpouseWorkinglife().trim())) {
			xhJksq.setSpouseWorkinglife(old.getSpouseWorkinglife().trim());
		}

		// 借款申请额度
		if (null != old.getJkLoanQuota()
				&& !"".equals(old.getJkLoanQuota().trim())) {
			xhJksq.setJkLoanQuota(old.getJkLoanQuota().trim());
		}
		// 借款周期
		if (null != old.getJkCycle() && !"".equals(old.getJkCycle().trim())) {
			xhJksq.setJkCycle(old.getJkCycle().trim());
		}
		// 申请日期
		if (null != old.getJkLoanDate()) {
			xhJksq.setJkLoanDate(old.getJkLoanDate());
		}
		// 借款用途
		if (null != old.getJkUse() && !"".equals(old.getJkUse().trim())) {
			xhJksq.setJkUse(old.getJkUse().trim());
		}
		// 有无共同还款人
		if (null != old.getTogetherPerson()
				&& !"".equals(old.getTogetherPerson().trim())) {
			xhJksq.setTogetherPerson(old.getTogetherPerson().trim());
		}
		// 账户开户行
		if (null != old.getBankOpen() && !"".equals(old.getBankOpen().trim())) {
			xhJksq.setBankOpen(old.getBankOpen().trim());
		}
		// 账户名称
		if (null != old.getBankUsername()
				&& !"".equals(old.getBankUsername().trim())) {
			xhJksq.setBankUsername(old.getBankUsername().trim());
		}
		// 账户号码
		if (null != old.getBankNum() && !"".equals(old.getBankNum().trim())) {
			xhJksq.setBankNum(old.getBankNum().trim());
		}
		// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
		if (null != old.getJkType() && !"".equals(old.getJkType().trim())) {
			xhJksq.setJkType(old.getJkType().trim());
		}
		// 借款编号
		if (null != old.getLoanCode() && !"".equals(old.getLoanCode().trim())) {
			xhJksq.setLoanCode(old.getLoanCode().trim());
		}
		// 还款方式
		if (null != old.getHkWay() && !"".equals(old.getHkWay().trim())) {
			xhJksq.setHkWay(old.getHkWay().trim());
		}

		// 待核算金额
		if (null != old.getWaitAccMoney()
				&& !"".equals(old.getWaitAccMoney().trim())) {
			xhJksq.setWaitAccMoney(old.getWaitAccMoney().trim());
		}

		// 备用字段01
		if (null != old.getBackup01() && !"".equals(old.getBackup01().trim())) {
			xhJksq.setBackup01(old.getBackup01().trim());
		}
		// 备用字段02
		if (null != old.getBackup02() && !"".equals(old.getBackup02().trim())) {
			xhJksq.setBackup02(old.getBackup02().trim());
		}
		// 备用字段03
		if (null != old.getBackup03() && !"".equals(old.getBackup03().trim())) {
			xhJksq.setBackup03(old.getBackup03().trim());
		}
		// 备用字段04
		if (null != old.getBackup04() && !"".equals(old.getBackup04().trim())) {
			xhJksq.setBackup04(old.getBackup04().trim());
		}
		// 备用字段05
		if (null != old.getBackup05() && !"".equals(old.getBackup05().trim())) {
			xhJksq.setBackup05(old.getBackup05().trim());
		}
		// 备用字段06
		if (null != old.getBackup06() && !"".equals(old.getBackup06().trim())) {
			xhJksq.setBackup06(old.getBackup06().trim());
		}
		// 备用字段07
		if (null != old.getBackup07() && !"".equals(old.getBackup07().trim())) {
			xhJksq.setBackup07(old.getBackup07().trim());
		}
		// 备用字段08
		if (null != old.getBackup08() && !"".equals(old.getBackup08().trim())) {
			xhJksq.setBackup08(old.getBackup08().trim());
		}
		// 备用字段09
		if (null != old.getBackup09() && !"".equals(old.getBackup09().trim())) {
			xhJksq.setBackup09(old.getBackup09().trim());
		}

		return xhJksq;
	}

	/**
	 * 整合后保存 借款申请里面的紧急联系人集合
	 * 
	 * @param request
	 * @return
	 */
	private List<XhJkjjlxr> getXhJkjjlxrSet(HttpServletRequest request,
			XhJksq xhjksq) {
		List<XhJkjjlxr> set = new ArrayList<XhJkjjlxr>();
		XhJkjjlxr xhJkjjlxr = null;
		// List<MateData> ybrgxMateDateList =
		// baseInfoManager.getTypeByCode("0014");
		// MateData mateData = null;
		for (int i = 1; i <= 6; i++) {
			xhJkjjlxr = new XhJkjjlxr();
			xhJkjjlxr.setXhJksq(xhjksq);
			String name = request.getParameter("name" + i);// 紧急联系人姓名
			String jjlxrgzdw = request.getParameter("jjlxrgzdw" + i);// 紧急联系人工作单位
			String jjlxrdwdzhjtzz = request.getParameter("jjlxrdwdzhjtzz" + i);// 单位地址或家庭住址
			String jjlxrlxdh = request.getParameter("jjlxrlxdh" + i);// 紧急联系人联系电话

			String ybrgx = request.getParameter("ybrgx" + i);// 与本人关系

			xhJkjjlxr.setName(name);
			xhJkjjlxr.setJjlxrgzdw(jjlxrgzdw);
			xhJkjjlxr.setJjlxrdwdzhjtzz(jjlxrdwdzhjtzz);
			xhJkjjlxr.setJjlxrlxdh(jjlxrlxdh);
			xhJkjjlxr.setYbrgx(ybrgx);
			set.add(xhJkjjlxr);
		}
		return set;
	}

	/**
	 * 整1紧急联系人集合
	 * 
	 * @param request
	 * @return
	 */
	private Set<XhJkjjlxr> getXhJkjjlxrUpdate(HttpServletRequest request,
			XhJksq xhjksq) {
		Set<XhJkjjlxr> set = new HashSet<XhJkjjlxr>();
		XhJkjjlxr xhJkjjlxr = null;
		for (int i = 1; i <= 6; i++) {
			String id = request.getParameter("id" + i);// 紧急联系人ID
			if (id != null && Integer.valueOf(id).intValue() != i) {
				xhJkjjlxr = xhJkjjlxrDao.get(new Long(id));
				xhJkjjlxr.setXhJksq(xhjksq);
				String name = request.getParameter("name" + i);// 紧急联系人姓名
				String jjlxrgzdw = request.getParameter("jjlxrgzdw" + i);// 紧急联系人工作单位
				String jjlxrdwdzhjtzz = request.getParameter("jjlxrdwdzhjtzz"
						+ i);// 单位地址或家庭住址
				String jjlxrlxdh = request.getParameter("jjlxrlxdh" + i);// 紧急联系人联系电话
				String ybrgx = request.getParameter("ybrgx" + i);// 与本人关系
				xhJkjjlxr.setName(name);
				xhJkjjlxr.setJjlxrgzdw(jjlxrgzdw);
				xhJkjjlxr.setJjlxrdwdzhjtzz(jjlxrdwdzhjtzz);
				xhJkjjlxr.setJjlxrlxdh(jjlxrlxdh);
				xhJkjjlxr.setYbrgx(ybrgx);
				set.add(xhJkjjlxr);
			}
		}
		return set;
	}

	/**
	 * 修改时的保存紧急联系人
	 * 
	 * @param request
	 * @param xhjksq
	 * @param xhJksqTogether
	 * @return
	 */
	private boolean editSaveXhJkjjlxr(HttpServletRequest request,
			XhJksq xhjksq, XhJksqTogether xhJksqTogether) {
		return editSaveXhJkjjlxr(request, xhjksq, xhJksqTogether, null);
	}

	/**
	 * 修改时的保存紧急联系人
	 * 
	 * @param request
	 * @param xhjksq
	 *            借款申请信息
	 * @param xhJksqTogether
	 *            共同还款人信息
	 * @param xhJksqUPHistory
	 *            借款申请历史信息 /变更信息
	 * @return
	 */
	private boolean editSaveXhJkjjlxr(HttpServletRequest request,
			XhJksq xhjksq, XhJksqTogether xhJksqTogether,
			XhJksqUPHistory xhJksqUPHistory) {
		boolean flag = false;
		XhJkjjlxr xhJkjjlxr = null;
		for (int i = 1; i <= 6; i++) {
			String id = request.getParameter("id" + i);
			System.out.println("ID is:" + id);
			String name = request.getParameter("name" + i);// 紧急联系人姓名
			System.out.println("name is:" + name);
			String jjlxrgzdw = request.getParameter("jjlxrgzdw" + i);// 紧急联系人工作单位
			System.out.println("jjlxrgzdw is" + jjlxrgzdw);
			String jjlxrdwdzhjtzz = request.getParameter("jjlxrdwdzhjtzz" + i);// 单位地址或家庭住址
			System.out.println("jjlxrdwdzhjtzz is:" + jjlxrdwdzhjtzz);
			String jjlxrlxdh = request.getParameter("jjlxrlxdh" + i);// 紧急联系人联系电话
			System.out.println("jjlxrlxdh is:" + jjlxrlxdh);
			String ybrgx = request.getParameter("ybrgx" + i);// 与本人关系
			System.out.println("ybrgx is" + ybrgx);
			if (null != id && !"".equals(id)) {
				xhJkjjlxr = xhJkjjlxrDao.get(Long.parseLong(id));
				if (null != xhjksq) {
					xhJkjjlxr.setXhJksq(xhjksq);
				}
				if (null != xhJksqTogether) {
					xhJkjjlxr.setXhJksqTogether(xhJksqTogether);
				}
				if (null != xhJksqUPHistory) {
					xhJkjjlxr.setXhjksqjphistory(xhJksqUPHistory);
				}

				xhJkjjlxr.setName(name);
				xhJkjjlxr.setJjlxrgzdw(jjlxrgzdw);
				xhJkjjlxr.setJjlxrdwdzhjtzz(jjlxrdwdzhjtzz);
				xhJkjjlxr.setJjlxrlxdh(jjlxrlxdh);
				xhJkjjlxr.setYbrgx(ybrgx);

				// Timestamp tempTimeStamp = xhJkjjlxr.getLastModifyTime();//
				// 保存前的最后修改日期
				xhJkjjlxr
						.setLastModifyTime(new Timestamp(new Date().getTime()));

				flag = xhJkjjlxrDao.update(xhJkjjlxr);
			}
		}
		return flag;
	}

	/**
	 * 根据借款类型获取 借款申请动态信息模板
	 * 
	 * @param request
	 * @return
	 */
	private Templet getXhjksqTemplet(HttpServletRequest request) {
		Templet templet = null;
		String jkType = request.getParameter("jkType");
		if (null != jkType && !"".equals(jkType)) {
			List<Templet> templetList = templetDao.findTempletByType(jkType);
			if (null != templetList && templetList.size() > 0) {
				templet = templetList.get(0);
			}
		}
		return templet;
	}

	/**
	 * 借款申请动态数据
	 * 
	 * @param request
	 * @param templet
	 * @return
	 */
	private String getXhjksqDynamicData(HttpServletRequest request,
			Templet templet) {
		StringBuffer values = new StringBuffer();
		if (null != templet) {
			String keys = templet.getKeys();
			if (null == keys || "".equals(keys.trim())) {
				return values.toString();
			}
			String[] key = keys.split(",");
			for (int i = 0; i < key.length; i++) {
				String temp = request.getParameter(key[i]);
				values.append(temp).append(" ,");
			}
			int temp = values.lastIndexOf(",");
			if (temp == values.length() - 1) {
				values.deleteCharAt(temp);
			}
		}
		return values.toString();
	}

	private Xydkzx getXydkzx(HttpServletRequest request) {
		String xydkzxId = request.getParameter("xydkzxId");
		Long Id = Long.parseLong(xydkzxId);
		Xydkzx sydkzx = xydkzxDao.get(Id);
		return sydkzx;
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksq(Long id) {
		xhJksqDao.delete(id);
	}

	public boolean batchDelXhJksq(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhJksq(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqLoop(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";

		if (filter.containsKey("jkrxm")) {// 借款人姓名
			value = String.valueOf(filter.get("jkrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.jkrxm like '%" + value + "%'";
			}
		}

		if (filter.containsKey("zjhm")) {// 证件号码
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZJHM = '" + value + "'";
			}
		}

		if (filter.containsKey("startDate")) {// 进件开始时间 - 进件结束时间
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') >= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("endDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') <= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("state")) {// 状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '" + value + "'";
			}
		}

		if (filter.containsKey("jkType")) {// 产品
			value = String.valueOf(filter.get("jkType"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"all".equals(value)) {
					sql = sql + " and a.JK_TYPE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("backup01")) {// 借款标志位
			value = String.valueOf(filter.get("backup01"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.BACKUP01 = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("state")) {// 借款状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.STATE = '" + value + "'";
				}
			}
		}
		if (filter.containsKey("state_injksp")) {// 借款申请中的借款状态查询
			value = String.valueOf(filter.get("state_injksp"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					String[] tt = value.split(",");
					StringBuffer values = new StringBuffer();
					for (int i = 0; i < tt.length; i++) {
						values.append("'").append(tt[i]).append("',");
					}
					int temp = values.lastIndexOf(",");
					if (temp == values.length() - 1) {
						values.deleteCharAt(temp);
					}
					sql = sql + " and a.state in (" + values.toString() + ")";
				}
			}
		}
		if (filter.containsKey("state_in")) {// 多借款状态
			value = String.valueOf(filter.get("state_in"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("applyIds")) {// 多借款状态
			value = String.valueOf(filter.get("applyIds"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.id in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("state_g")) {// 多借款状态--大于等于
			value = String.valueOf(filter.get("state_g"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state >= " + value;
				}
			}
		}
		// if(filter.containsKey("jkhtexamState")){//借款状态
		// value = String.valueOf(filter.get("jkhtexamState"));
		// if(StringUtils.isNotEmpty(value)) {
		// if(!"".equals(value)){
		// sql = sql + " and a.jkhtexam_State = '" + value + "'";
		// }
		// }
		// }
		// if(filter.containsKey("jkhtexamState_neq")){//借款状态
		// value = String.valueOf(filter.get("jkhtexamState_neq"));
		// if(StringUtils.isNotEmpty(value)) {
		// if(!"".equals(value)){
		// sql = sql + " and a.jkhtexam_State != '" + value + "'";
		// }
		// }
		// }
		// if(filter.containsKey("jkhtexamState_in")){//借款状态
		// value = String.valueOf(filter.get("jkhtexamState_in"));
		// if(StringUtils.isNotEmpty(value)) {
		// if(!"".equals(value)){
		// sql = sql + " and a.jkhtexam_State in (" + value + ")";
		// }
		// }
		// }
		if (filter.containsKey("province")) {// 所属省份
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" + value + "'";
			}
		}

		if (filter.containsKey("city")) {// 所属城市
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" + value + "'";
			}
		}

		// 不在借款申请表中
		StringBuffer tablesql = new StringBuffer("");
		StringBuffer unionsql = new StringBuffer("");
		if (filter.containsKey("teamName") || filter.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(filter.get("teamName"));
			String saleName = String.valueOf(filter.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				tablesql.append(" , XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID and b.type = '1'");
				if (teamNameFlag) {
					// sql = sql + " and b.TEAM_NAME like '%" + teamName + "%'";
					// 借款咨询中更改团队经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ teamName + "%' ";
					sql = sql + " and b.employee_crm in (" + tempSql + ")";
				}
				if (saleNameFlag) {
					// sql = sql + " and b.SALE_NAME like '%" + saleName + "%'";
					// 借款咨询中更改销售经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ saleName + "%' ";
					sql = sql + " and b.employee_cca in (" + tempSql + ")";
				}
			} else {
				tablesql.append(" , XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID and b.type = '1'");
			}
		} else {
			tablesql.append(" , XH_XYDKZX b  ");
			unionsql.append(" and a.XYDKZX_ID=b.ID and b.type = '1'");
		}

		if (filter.containsKey("appState")) {// 借款申请有变更申请的
			value = String.valueOf(filter.get("appState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.APP_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("subState")) {// 借款申请是否已提交
			value = String.valueOf(filter.get("subState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.SUB_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("XHJKSQ_ID")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("XHJKSQ_ID"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.XHJKSQ_ID = '" + value + "'";
				}
			}
		}

		String sql2 = "";

		if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql2 = sql2 + " and yybid = " + value + "";
				}
			}
		}

		// 级联查询sql
		sql = sql + PropertiesUtils.getLoanSql();

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		} else {
			sql = sql + " order by a.CREATE_TIME desc ";

		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);

		conditions.put("tablesql", tablesql.toString());
		conditions.put("unionsql", unionsql.toString());

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksq(String queryName,
			Map<String, Object> filter, JdbcPage page) {

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName,
				conditions(filter), page);
	}

	/**
	 * 
	 * 根据身份证件号码查询关联申请信息
	 * 
	 * @param queryName
	 * @param filter
	 * @param page
	 * @return
	 */

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqByIdCard(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		if (filter.containsKey("zjhm")) {
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				String querySQL = "select t.ID from XH_JKSQ  t where t.zjhm='"
						+ value
						+ "' or t.spouse_zjhm='"
						+ value
						+ "'  union select t2.ID  from xh_jksq_together t1,XH_JKSQ t2   where   t2.id=t1.xhjksq_id and t1.identification='"
						+ value + "'";
				List<Map<String, Object>> list = jdbcDao
						.searchByMergeSql(querySQL);
				StringBuffer sb = new StringBuffer();
				// 拼接查询结果，用","分割
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					sb.append(map.get("ID"));
					sb.append(",");
				}
				String ss = sb.toString();

				if (ss.contains(",")) {
					value = ss.substring(0, ss.length() - 1);
				}

			}

		}
		sql = " and a.ID in (" + value + ")";
		System.out.println("sql======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();

		StringBuffer tablesql = new StringBuffer("");
		StringBuffer unionsql = new StringBuffer("");

		conditions.put("sql", sql);
		conditions.put("tablesql", tablesql.toString());
		conditions.put("unionsql", unionsql.toString());
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqAll(String queryName,
			Map<String, Object> filter) {

		String sql = "";
		String value = "";
		if (filter.containsKey("pocertificates")) {
			value = String.valueOf(filter.get("pocertificates"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.POCERTIFICATES='" + value + "'";
			}
		}
		if (filter.containsKey("zjhm")) {
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.ZJHM='" + value + "'";
			}
		}
		if (filter.containsKey("id")) {
			value = String.valueOf(filter.get("id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.id !=" + value;
			}
		}
		filter.put("andConditionSql", sql);
		return jdbcDao.searchByMergeSqlTemplate(queryName, filter);
	}

	private Map<String, Object> conditions(Map<String, Object> filter) {
		String sql = "";
		String value = "";

		if (filter.containsKey("id")) {// 借款人id
			value = String.valueOf(filter.get("id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.id = '" + value + "'";
			}
		}

		if (filter.containsKey("jkrxm")) {// 借款人姓名
			value = String.valueOf(filter.get("jkrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.jkrxm like '%" + value + "%'";
			}
		}

		if (filter.containsKey("zjhm")) {// 证件号码
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZJHM = '" + value + "'";
			}
		}

		if (filter.containsKey("startDate")) {// 进件开始时间 - 进件结束时间
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') >= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("endDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') <= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("state")) {// 状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '" + value + "'";
			}
		}

		if (filter.containsKey("jkType")) {// 产品
			value = String.valueOf(filter.get("jkType"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"all".equals(value)) {
					sql = sql + " and a.JK_TYPE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("backup01")) {// 借款标志位
			value = String.valueOf(filter.get("backup01"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.BACKUP01 = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("state")) {// 借款状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.STATE = '" + value + "'";
				}
			}
		}
		if (filter.containsKey("state_injksp")) {// 借款申请中的借款状态查询
			value = String.valueOf(filter.get("state_injksp"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					String[] tt = value.split(",");
					StringBuffer values = new StringBuffer();
					for (int i = 0; i < tt.length; i++) {
						values.append("'").append(tt[i]).append("',");
					}
					int temp = values.lastIndexOf(",");
					if (temp == values.length() - 1) {
						values.deleteCharAt(temp);
					}
					sql = sql + " and a.state in (" + values.toString() + ")";
				}
			}
		}
		if (filter.containsKey("state_in")) {// 多借款状态
			value = String.valueOf(filter.get("state_in"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("applyIds")) {// 多借款状态
			value = String.valueOf(filter.get("applyIds"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.id in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("state_g")) {// 多借款状态--大于等于
			value = String.valueOf(filter.get("state_g"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state >= " + value;
				}
			}
		}
		// if(filter.containsKey("jkhtexamState")){//借款状态
		// value = String.valueOf(filter.get("jkhtexamState"));
		// if(StringUtils.isNotEmpty(value)) {
		// if(!"".equals(value)){
		// sql = sql + " and a.jkhtexam_State = '" + value + "'";
		// }
		// }
		// }
		// if(filter.containsKey("jkhtexamState_neq")){//借款状态
		// value = String.valueOf(filter.get("jkhtexamState_neq"));
		// if(StringUtils.isNotEmpty(value)) {
		// if(!"".equals(value)){
		// sql = sql + " and a.jkhtexam_State != '" + value + "'";
		// }
		// }
		// }
		// if(filter.containsKey("jkhtexamState_in")){//借款状态
		// value = String.valueOf(filter.get("jkhtexamState_in"));
		// if(StringUtils.isNotEmpty(value)) {
		// if(!"".equals(value)){
		// sql = sql + " and a.jkhtexam_State in (" + value + ")";
		// }
		// }
		// }
		if (filter.containsKey("province")) {// 所属省份
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" + value + "'";
			}
		}

		if (filter.containsKey("city")) {// 所属城市
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" + value + "'";
			}
		}

		// 不在借款申请表中
		StringBuffer tablesql = new StringBuffer("");
		StringBuffer unionsql = new StringBuffer("");
		
		if (filter.containsKey("CREDIT_TYPE")) {
			String creditType = String.valueOf(filter.get("CREDIT_TYPE"));
			boolean creditTypeFlag = StringUtils.isNotEmpty(creditType);
			if(creditTypeFlag){
				tablesql.append(" , xh_credit_audit credit ");
				unionsql.append(" and credit.loan_apply_id = a.id and credit.credit_type = '"+creditType+"' ");
			}
		}
		
		if (filter.containsKey("teamName") || filter.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(filter.get("teamName"));
			String saleName = String.valueOf(filter.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				tablesql.append(" , XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID ");// and b.type = '0'
				if (teamNameFlag) {
					// sql = sql + " and b.TEAM_NAME like '%" + teamName + "%'";
					// 借款咨询中更改团队经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ teamName + "%' ";
					sql = sql + " and b.employee_crm in (" + tempSql + ")";
				}
				if (saleNameFlag) {
					// sql = sql + " and b.SALE_NAME like '%" + saleName + "%'";
					// 借款咨询中更改销售经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ saleName + "%' ";
					sql = sql + " and b.employee_cca in (" + tempSql + ")";
				}
			} else {
				tablesql.append(" , XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID ");
			}
		} else {
			tablesql.append(" , XH_XYDKZX b  ");
			unionsql.append(" and a.XYDKZX_ID=b.ID ");
		}

		if (filter.containsKey("appState")) {// 借款申请有变更申请的
			value = String.valueOf(filter.get("appState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.APP_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("customerType")) {// 客户类型
			value = String.valueOf(filter.get("customerType"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and b.type = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("subState")) {// 借款申请是否已提交
			value = String.valueOf(filter.get("subState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.SUB_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("XHJKSQ_ID")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("XHJKSQ_ID"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.XHJKSQ_ID = '" + value + "'";
				}
			}
		}
		String sql2 = "";

		if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql2 = sql2 + " and yybid = " + value + "";
				}
			}
		}

		// 级联查询sql
		sql = sql + PropertiesUtils.getLoanSql();

		// if (page.getOrderBy() != null) {
		// sql = sql + " order by " + page.getOrderBy() + " "
		// + page.getOrder();
		// } else {
		sql = sql + " order by a.CREATE_TIME desc ";

		// }

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		conditions.put("tablesql", tablesql.toString());
		conditions.put("unionsql", unionsql.toString());
		return conditions;
	}

	/**
	 * 产品选择列表
	 * 
	 * @param queryName
	 * @param filter
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchCpType() {
		String sql = " select t.* from base_attr t where t.type_id='3' ";
		return jdbcDao.searchByMergeSql(sql);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqExpire(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";

		if (filter.containsKey("jkrxm")) {// 借款人姓名
			value = String.valueOf(filter.get("jkrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.jkrxm like '%" + value + "%'";
			}
		}

		if (filter.containsKey("zjhm")) {// 证件号码
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZJHM = '" + value + "'";
			}
		}

		if (filter.containsKey("startDate")) {// 进件开始时间 - 进件结束时间
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') >= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("endDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') <= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("jkType")) {// 产品
			value = String.valueOf(filter.get("jkType"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"all".equals(value)) {
					sql = sql + " and a.JK_TYPE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("backup01")) {// 借款标志位
			value = String.valueOf(filter.get("backup01"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.BACKUP01 = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("state_injksp")) {// 借款申请中的借款状态查询
			value = String.valueOf(filter.get("state_injksp"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					String[] tt = value.split(",");
					StringBuffer values = new StringBuffer();
					for (int i = 0; i < tt.length; i++) {
						values.append("'").append(tt[i]).append("',");
					}
					int temp = values.lastIndexOf(",");
					if (temp == values.length() - 1) {
						values.deleteCharAt(temp);
					}
					sql = sql + " and a.state in (" + values.toString() + ")";
				}
			}
		}

		if (filter.containsKey("applyIds")) {// 多借款状态
			value = String.valueOf(filter.get("applyIds"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.id in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("province")) {// 所属省份
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" + value + "'";
			}
		}

		if (filter.containsKey("city")) {// 所属城市
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" + value + "'";
			}
		}

		if (filter.containsKey("teamName") || filter.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(filter.get("teamName"));
			String saleName = String.valueOf(filter.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				if (teamNameFlag) {
					// 借款咨询中更改团队经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ teamName + "%' ";
					sql = sql + " and b.employee_crm in (" + tempSql + ")";
				}
				if (saleNameFlag) {
					// 借款咨询中更改销售经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ saleName + "%' ";
					sql = sql + " and b.employee_cca in (" + tempSql + ")";
				}
			}
		}

		if (filter.containsKey("subState")) {// 借款申请是否已提交
			value = String.valueOf(filter.get("subState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.SUB_STATE = '" + value + "'";
				}
			}
		}

		String sql1 = "";

		if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql1 = sql1 + " and yybid = " + value + "";
				}
			}
		}

		// 级联查询sql
		sql = sql + PropertiesUtils.getLoanSql();

		if (page.getOrderBy() != null) {
			sql1 = sql1 + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		} else {
			sql1 = sql1 + " order by dqrq desc ";

		}

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql1", sql1);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getXhJksq(Map<String, Object> filter,
			JdbcPage page) {
		String sql = "";
		String value = "";

		/**
		 * 初审推荐包含 FIRST_EMPLOYEEID KEY
		 */
		if (filter.get("FIRST_EMPLOYEEID") != null
				&& StringUtils.isNotEmpty(filter.get("FIRST_EMPLOYEEID")
						.toString())) {
			sql += " and TASKASSIGN.FIRST_AUDIT_EMPLOYEE_ID = "
					+ filter.get("FIRST_EMPLOYEEID").toString();
		}

		/**
		 * 复审 包含 SECOND_EMPLOYEEID KEY
		 */
		if (filter.get("SECOND_EMPLOYEEID") != null
				&& StringUtils.isNotEmpty(filter.get("SECOND_EMPLOYEEID")
						.toString())) {
			sql += " and TASKASSIGN.SECOND_AUDIT_EMPLOYEE_ID = "
					+ filter.get("SECOND_EMPLOYEEID").toString();
		}

		/**
		 * 终审 包含 FINAL_EMPLOYEEID KEY
		 */
		if (filter.get("FINAL_EMPLOYEEID") != null
				&& StringUtils.isNotEmpty(filter.get("FINAL_EMPLOYEEID")
						.toString())) {
			sql += " and TASKASSIGN.FINAL_AUDIT_EMPLOYEE_ID = "
					+ filter.get("FINAL_EMPLOYEEID").toString();
		}

		if (filter.containsKey("jkrxm")) {// 借款人姓名
			value = String.valueOf(filter.get("jkrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.jkrxm like '%" + value + "%'";
			}
		}

		if (filter.containsKey("zjhm")) {// 证件号码
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZJHM = '" + value + "'";
			}
		}

		if (filter.containsKey("startDate")) {// 进件开始时间 - 进件结束时间
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') >= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("endDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') <= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("state")) {// 状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.state = '" + value + "'";
			}
		}

		if (filter.containsKey("jkType")) {// 产品
			value = String.valueOf(filter.get("jkType"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"all".equals(value)) {
					sql = sql + " and a.JK_TYPE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("backup01")) {// 借款标志位
			value = String.valueOf(filter.get("backup01"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.BACKUP01 = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("state")) {// 借款状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.STATE = '" + value + "'";
				}
			}
		}
		if (filter.containsKey("state_injksp")) {// 借款申请中的借款状态查询
			value = String.valueOf(filter.get("state_injksp"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					String[] tt = value.split(",");
					StringBuffer values = new StringBuffer();
					for (int i = 0; i < tt.length; i++) {
						values.append("'").append(tt[i]).append("',");
					}
					int temp = values.lastIndexOf(",");
					if (temp == values.length() - 1) {
						values.deleteCharAt(temp);
					}
					sql = sql + " and a.state in (" + values.toString() + ")";
				}
			}
		}
		if (filter.containsKey("state_in")) {// 多借款状态
			value = String.valueOf(filter.get("state_in"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("applyIds")) {// 多借款状态
			value = String.valueOf(filter.get("applyIds"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.id in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("state_g")) {// 多借款状态--大于等于
			value = String.valueOf(filter.get("state_g"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state >= " + value;
				}
			}
		}

		if (filter.containsKey("province")) {// 所属省份
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" + value + "'";
			}
		}

		if (filter.containsKey("city")) {// 所属城市
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" + value + "'";
			}
		}

		// 不在借款申请表中
		StringBuffer tablesql = new StringBuffer("");
		StringBuffer unionsql = new StringBuffer("");
		if (filter.containsKey("teamName") || filter.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(filter.get("teamName"));
			String saleName = String.valueOf(filter.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				tablesql.append(" , XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID ");//

				if (teamNameFlag) {
					// sql = sql + " and b.TEAM_NAME like '%" + teamName + "%'";
					// 借款咨询中更改团队经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ teamName + "%' ";
					sql = sql + " and b.employee_crm in (" + tempSql + ")";
				}
				if (saleNameFlag) {
					// sql = sql + " and b.SALE_NAME like '%" + saleName + "%'";
					// 借款咨询中更改销售经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ saleName + "%' ";
					sql = sql + " and b.employee_cca in (" + tempSql + ")";
				}
			} else {
				tablesql.append(" , XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID ");
			}
		} else {
			tablesql.append(" , XH_XYDKZX b  ");
			unionsql.append(" and a.XYDKZX_ID=b.ID ");
		}

		if (filter.containsKey("appState")) {// 借款申请有变更申请的
			value = String.valueOf(filter.get("appState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.APP_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("subState")) {// 借款申请是否已提交
			value = String.valueOf(filter.get("subState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.SUB_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("XHJKSQ_ID")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("XHJKSQ_ID"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.XHJKSQ_ID = '" + value + "'";
				}
			}
		}

		String sql2 = "";

		if (filter.containsKey("organi.id")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql2 = sql2 + " and yybid = " + value + "";
				}
			}
		}

		// 级联查询sql
		sql = sql + PropertiesUtils.getLoanSql();

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		} else {
			sql = sql + " order by a.CREATE_TIME desc ";

		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql2", sql2);
		conditions.put("sql", sql);
		conditions.put("tablesql", tablesql.toString());
		conditions.put("unionsql", unionsql.toString());

		return jdbcDao.searchPagesByMergeSqlTemplate(GET_TASK_BY_EMPLOYEE_SQL,
				conditions, page);
	}

	/**
	 * 变更申请信息列表查询
	 * 
	 * @param queryName
	 * @param filter
	 * @param page
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqChange(String queryName,
			Map<String, Object> filter, JdbcPage page) {

		// 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料
		// 03：授信资料已上传，待信审;30：信审中;3a：信审拒绝
		// 50：信审通过，待合同制作;55：待签订合同;60 ：待放款
		// 61 ：已放款;80 ：超时冻结;81 ：客户放弃;82 ：拒贷;end ：已完成
		// filter.put("state_in",
		// "'02','03','30','50','55','60','61','80','81','82','end'");

		filter.put("subState", "1");
		return searchXhJksq(queryName, filter, page);
	}

	/**
	 * 变更申请审核信息列表查询
	 * 
	 * @param queryName
	 * @param filter
	 * @param page
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqChangeExam(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		// filter.put("subState", "1");
		filter.put("appState", "1");
		return searchXhJksq(queryName, filter, page);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhJksqUPHistory(String queryName,
			Map<String, Object> filter, JdbcPage page, XhJksq xhjksq) {
		filter.put("XHJKSQ_ID", xhjksq.getId());
		return searchXhJksq(queryName, filter, page);
	}

	/**
	 * 通过借款申请Id和借款申请的关系ybrgx，查询紧急联系人
	 * 
	 * @param jksqId
	 * @param ybrgx
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<XhJkjjlxr> getXhJkjjlxrList(Long Id, String ybrgx,
			String personFlag) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("Id", Id);
		filters.put("personFlag", personFlag);
		filters.put("ybrgx", ybrgx);
		List<XhJkjjlxr> list = xhJkjjlxrDao.getXhJkjjlxrList(filters);
		return list;
	}

	/**
	 * 借款申请补充信息时获取紧急联系人
	 * 
	 * @param Id
	 * @return
	 */
	@Transactional(readOnly = true)
	public XhJkjjlxr[] getJksqComplementJkjjlxr(Long Id) {
		return getComplementJkjjlxr(Id, "jksq");
	}

	/**
	 * 借款申请补充信息时获取紧急联系人
	 * 
	 * @param Id
	 * @param flag
	 *            区分是查询借款申请、借款变更、共同借款人紧急联系人
	 *            jksq：借款申请紧急联系人；jksqTogether：同借款人紧急联系人；jksqChange：借款申请变更紧急联系人
	 * @return
	 */
	@Transactional(readOnly = true)
	public XhJkjjlxr[] getComplementJkjjlxr(Long Id, String flag) {
		XhJkjjlxr[] xhjkjjlxrs = new XhJkjjlxr[6];
		List<XhJkjjlxr> relativesList = getXhJkjjlxrList(Id, "亲属", flag);
		if (null != relativesList && relativesList.size() > 0) {
			for (int i = 0; i < relativesList.size(); i++) {
				xhjkjjlxrs[i] = relativesList.get(i);
			}
		}
		List<XhJkjjlxr> friendList = getXhJkjjlxrList(Id, "朋友", flag);
		if (null != friendList && friendList.size() > 0) {
			for (int i = 0; i < friendList.size(); i++) {
				xhjkjjlxrs[i + 2] = friendList.get(i);
			}
		}
		List<XhJkjjlxr> workmateList = getXhJkjjlxrList(Id, "同事", flag);
		if (null != workmateList && workmateList.size() > 0) {
			for (int i = 0; i < workmateList.size(); i++) {
				xhjkjjlxrs[i + 4] = workmateList.get(i);
			}
		}
		return xhjkjjlxrs;
	}

	/**
	 * 通过借款申请Id查找借款申请流程状态历史记录
	 * 
	 * @param Id
	 * @param page
	 */
	@Transactional(readOnly = true)
	public void jksqHistoryList(Long Id, Page<XhJksqState> page) {
		xhJksqStateDao.queryXhJksqState(page, Id);
		// xhJksqStateDao.queryXhJksqState2(page, Id);
	}

	/**
	 * 上传授信/签约文件后改变 借款申请状态
	 * 
	 * @param Id
	 * @param state
	 */
	public boolean changeState(Long Id, String state) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", state);
		return xhJksqDao.updJksq(Id, params);
	}

	/**
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param params
	 *            修改的借款申请的字段参数,必须和数据库字段名称对应上
	 * @param flag
	 *            业务逻辑的判断标志
	 * @return
	 */
	public boolean changeState(Long Id, Map<String, Object> params, String flag) {
		if ("".equals(flag)) {

		}
		return xhJksqDao.updJksq(Id, params);
	}

	public boolean changeState(XhJksq xhJksq, String state) {
		boolean flag = false;
		XhJksq XhJksq = xhJksqDao.get(xhJksq.getId());
		XhJksq.setState(state);
		Timestamp tempTimeStamp = XhJksq.getLastModifyTime();// 保存前的最后修改日期
		xhJksqDao.save(XhJksq);
		XhJksq newXhJksq = xhJksqDao.get(XhJksq.getId());
		if (null == tempTimeStamp && null != newXhJksq.getLastModifyTime()) {
			flag = true;
		} else {
			if (tempTimeStamp.getTime() != newXhJksq.getLastModifyTime()
					.getTime()) {
				flag = true;
			} else {
				flag = false;
			}
		}
		// xhJksqDao.merge(XhJksq);
		// xhJksqDao.flush();
		return flag;
	}

	/**
	 * 通过借款申请信息查找共同借款人信息
	 * 
	 * @param xhJksq
	 * @return
	 */
	@Transactional(readOnly = true)
	public XhJksqTogether getXhJksqTogether(XhJksq xhJksq) {
		XhJksqTogether xhJksqTogether = null;
		List<XhJksqTogether> list = xhJksqTogetherDao.getXhJksqTogether(xhJksq);
		if (null != list && list.size() > 0) {
			xhJksqTogether = list.get(0);
		}
		return xhJksqTogether;
	}

	/**
	 * 是否有信审审核信息
	 * 
	 * @param xhJksq
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<XhCreditAudit> getXhCreditAudit(XhJksq xhJksq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loanApplyId", xhJksq.getId());
		return xhCreditAuditDao.findByXhCreditAudit(params);
	}

	/**
	 * 保存共同借款人
	 * 
	 * @param request
	 * @param xhJksqTogether
	 * @return
	 */
	public boolean saveXhJksqTogether(HttpServletRequest request,
			XhJksqTogether xhJksqTogether) {
		boolean flag = false;

		String opt = request.getParameter("opt");
		String toghFlag = request.getParameter("toghFlag");
		if (null != opt && !"".equals(opt)) {
			if ("no".equals(toghFlag)) {
				if ("0".equals(opt)) {
					xhJksqTogether.setState("0");
				} else if ("1".equals(opt)) {
					xhJksqTogether.setState("01");
				}
			}
		}
		String jksqId = request.getParameter("jksqId");
		XhJksq xhJksq = this.getXhJksq(Long.parseLong(jksqId));
		// xhJksq.setId(Long.parseLong(jksqId));
		xhJksqTogether.setXhjksq(xhJksq);

		xhJksqTogetherDao.save(xhJksqTogether);
		if (null != xhJksqTogether.getId() && 0 != xhJksqTogether.getId()) {
			saveTogetherXhJkjjlxr(request, xhJksqTogether);// 不知
			if ("no".equals(toghFlag)) {// 借款申请中“共同还款人”为：是，状态为01
				changeState(Long.parseLong(jksqId), "02");
			} else if ("yes".equals(toghFlag)) {// 借款申请中“共同还款人”为：否或者空，要填写共同还款人
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("TOGETHER_PERSON", "是");
				// params.put("state",
				// xhJksq.getState().substring(0,xhJksq.getState().lastIndexOf(".")));//判断字段未定，暂时先放这，根据具体情况去改
				changeState(Long.parseLong(jksqId), params, null);
			}
			flag = true;
		}
		if ("no".equals(toghFlag)) {
			if (flag) {
				baseInfoManager.saveXhJksqState(xhJksq, "填写共同还款人资料成功，待上传授信资料",
						"已填写共同还款人资料，待上传授信资料");
			} else {
				baseInfoManager.saveXhJksqState(xhJksq, "填写共同还款人资料失败",
						"填写共同还款人资料失败");
			}
		} else if ("yes".equals(toghFlag)) {
			if (flag) {
				baseInfoManager.saveXhJksqState(xhJksq, "填写共同还款人资料成功",
						"已填写共同还款人资料");
			} else {
				baseInfoManager.saveXhJksqState(xhJksq, "填写共同还款人资料失败",
						"填写共同还款人资料失败");
			}
		}
		return flag;
	}

	/**
	 * 补充共同借款人
	 * 
	 * @param request
	 * @param xhjksqtogether
	 * @return
	 */
	public boolean complementSaveTogetherMsg(HttpServletRequest request,
			XhJksqTogether xhjksqtogether) {
		boolean flag = true;
		XhJksqTogether newXhJksqTogether = xhJksqTogetherDao.get(xhjksqtogether
				.getId());
		Timestamp tempTimeStamp = newXhJksqTogether.getLastModifyTime();// 保存前的最后修改日期

		newXhJksqTogether = compXhJksqTogether(newXhJksqTogether,
				xhjksqtogether);
		xhJksqTogetherDao.save(newXhJksqTogether);
		// 保存紧急联系人
		editSaveXhJkjjlxr(request, null, newXhJksqTogether);

		XhJksqTogether newTogether = xhJksqTogetherDao.get(xhjksqtogether
				.getId());
		if (null == tempTimeStamp && null != newTogether.getLastModifyTime()) {
			flag = true;
		} else {
			if (tempTimeStamp.getTime() != newTogether.getLastModifyTime()
					.getTime()) {
				flag = true;
			} else {
				flag = false;
			}
		}
		String jksqId = request.getParameter("jksqId");
		XhJksq xhJksq = this.getXhJksq(Long.parseLong(jksqId));
		if (flag) {
			baseInfoManager.saveXhJksqState(xhJksq, "补充共同还款人资料成功",
					"补充共同还款人资料成功");
		} else {
			baseInfoManager.saveXhJksqState(xhJksq, "补充共同还款人资料失败",
					"补充共同还款人资料失败");
		}

		return flag;
	}

	/**
	 * 补充共同借款人信息
	 * 
	 * @param newXhJksqTogether
	 *            原信息
	 * @param xhjksqtogether
	 *            页面补充信息
	 * @return
	 */
	private XhJksqTogether compXhJksqTogether(XhJksqTogether newXhJksqTogether,
			XhJksqTogether xhjksqtogether) {
		// 共同借款人姓名
		newXhJksqTogether.setTogetherName(xhjksqtogether.getTogetherName()
				.trim());
		// 年龄
		if (null != xhjksqtogether.getAge()
				&& !"".equals(xhjksqtogether.getAge().trim())) {
			newXhJksqTogether.setAge(xhjksqtogether.getAge().trim());
		} else {
			newXhJksqTogether.setAge(xhjksqtogether.getAge());
		}
		// 性别
		newXhJksqTogether.setGenders(xhjksqtogether.getGenders());
		// 身份证号码
		if (null != xhjksqtogether.getIdentification()
				&& !"".equals(xhjksqtogether.getIdentification().trim())) {
			newXhJksqTogether.setIdentification(xhjksqtogether
					.getIdentification().trim());
		} else {
			newXhJksqTogether.setIdentification(xhjksqtogether
					.getIdentification());
		}
		// 户籍地址
		if (null != xhjksqtogether.getHjadress()
				&& !"".equals(xhjksqtogether.getHjadress().trim())) {
			newXhJksqTogether.setHjadress(xhjksqtogether.getHjadress().trim());
		} else {
			newXhJksqTogether.setHjadress(xhjksqtogether.getHjadress());
		}
		// 家庭电话
		if (null != xhjksqtogether.getHomePhone()
				&& !"".equals(xhjksqtogether.getHomePhone().trim())) {
			newXhJksqTogether
					.setHomePhone(xhjksqtogether.getHomePhone().trim());
		} else {
			newXhJksqTogether.setHomePhone(xhjksqtogether.getHomePhone());
		}
		// 手机号码
		if (null != xhjksqtogether.getTelephone()
				&& !"".equals(xhjksqtogether.getTelephone().trim())) {
			newXhJksqTogether
					.setTelephone(xhjksqtogether.getTelephone().trim());
		} else {
			newXhJksqTogether.setTelephone(xhjksqtogether.getTelephone());
		}
		// 现住址
		if (null != xhjksqtogether.getAddress()
				&& !"".equals(xhjksqtogether.getAddress().trim())) {
			newXhJksqTogether.setAddress(xhjksqtogether.getAddress().trim());
		} else {
			newXhJksqTogether.setAddress(xhjksqtogether.getAddress());
		}
		// 现住址电话
		if (null != xhjksqtogether.getAddressPhone()
				&& !"".equals(xhjksqtogether.getAddressPhone().trim())) {
			newXhJksqTogether.setAddressPhone(xhjksqtogether.getAddressPhone()
					.trim());
		} else {
			newXhJksqTogether.setAddressPhone(xhjksqtogether.getAddressPhone());
		}
		// 电子邮箱
		if (null != xhjksqtogether.getEmail()
				&& !"".equals(xhjksqtogether.getEmail().trim())) {
			newXhJksqTogether.setEmail(xhjksqtogether.getEmail().trim());
		} else {
			newXhJksqTogether.setEmail(xhjksqtogether.getEmail());
		}
		// 工作单位
		if (null != xhjksqtogether.getCompany()
				&& !"".equals(xhjksqtogether.getCompany().trim())) {
			newXhJksqTogether.setCompany(xhjksqtogether.getCompany().trim());
		} else {
			newXhJksqTogether.setCompany(xhjksqtogether.getCompany());
		}
		// 单位电话
		if (null != xhjksqtogether.getCompanyPhone()
				&& !"".equals(xhjksqtogether.getCompanyPhone().trim())) {
			newXhJksqTogether.setCompanyPhone(xhjksqtogether.getCompanyPhone()
					.trim());
		} else {
			newXhJksqTogether.setCompanyPhone(xhjksqtogether.getCompanyPhone());
		}
		// QQ号码
		if (null != xhjksqtogether.getQqhm()
				&& !"".equals(xhjksqtogether.getQqhm().trim())) {
			newXhJksqTogether.setQqhm(xhjksqtogether.getQqhm().trim());
		} else {
			newXhJksqTogether.setQqhm(xhjksqtogether.getQqhm());
		}
		// 单位地址
		if (null != xhjksqtogether.getCompanyAdress()
				&& !"".equals(xhjksqtogether.getCompanyAdress().trim())) {
			newXhJksqTogether.setCompanyAdress(xhjksqtogether
					.getCompanyAdress().trim());
		} else {
			newXhJksqTogether.setCompanyAdress(xhjksqtogether
					.getCompanyAdress());
		}
		// 部门名称
		if (null != xhjksqtogether.getDepartment()
				&& !"".equals(xhjksqtogether.getDepartment().trim())) {
			newXhJksqTogether.setDepartment(xhjksqtogether.getDepartment()
					.trim());
		} else {
			newXhJksqTogether.setDepartment(xhjksqtogether.getDepartment());
		}
		// 职务
		if (null != xhjksqtogether.getFunction()
				&& !"".equals(xhjksqtogether.getFunction().trim())) {
			newXhJksqTogether.setFunction(xhjksqtogether.getFunction().trim());
		} else {
			newXhJksqTogether.setFunction(xhjksqtogether.getFunction());
		}
		// 婚姻状况
		if (null != xhjksqtogether.getMaritalStatus()
				&& !"".equals(xhjksqtogether.getMaritalStatus().trim())) {
			newXhJksqTogether.setMaritalStatus(xhjksqtogether
					.getMaritalStatus().trim());
		} else {
			newXhJksqTogether.setMaritalStatus(xhjksqtogether
					.getMaritalStatus());
		}
		// 有无子女
		if (null != xhjksqtogether.getYwzn()
				&& !"".equals(xhjksqtogether.getYwzn().trim())) {
			newXhJksqTogether.setYwzn(xhjksqtogether.getYwzn().trim());
		} else {
			newXhJksqTogether.setYwzn(xhjksqtogether.getYwzn());
		}

		// 部门与职务
		if (null != xhjksqtogether.getDepFunction()
				&& !"".equals(xhjksqtogether.getDepFunction().trim())) {
			newXhJksqTogether.setDepFunction(xhjksqtogether.getDepFunction()
					.trim());
		} else {
			newXhJksqTogether.setDepFunction(xhjksqtogether.getDepFunction());
		}

		// 居住状况 01:自购房屋;02:贷款购置房屋;03:亲属房屋;04:租房，房租* 元/月;05:其他
		if (null != xhjksqtogether.getLiveState()
				&& !"".equals(xhjksqtogether.getLiveState().trim())) {
			newXhJksqTogether
					.setLiveState(xhjksqtogether.getLiveState().trim());
		} else {
			newXhJksqTogether.setLiveState(xhjksqtogether.getLiveState());
		}
		// 居住状况说明信息
		if (null != xhjksqtogether.getLiveMessage()
				&& !"".equals(xhjksqtogether.getLiveMessage().trim())) {
			newXhJksqTogether.setLiveMessage(xhjksqtogether.getLiveMessage()
					.trim());
		} else {
			newXhJksqTogether.setLiveMessage(xhjksqtogether.getLiveMessage());
		}
		// 主要收入来源
		// 每月工资(含奖金及补助)* 元/月
		if (null != xhjksqtogether.getMonthlySalary()
				&& !"".equals(xhjksqtogether.getMonthlySalary().trim())) {
			newXhJksqTogether.setMonthlySalary(xhjksqtogether
					.getMonthlySalary().trim());
		} else {
			newXhJksqTogether.setMonthlySalary(xhjksqtogether
					.getMonthlySalary());
		}
		// 房屋出租* 元/月
		if (null != xhjksqtogether.getRental()
				&& !"".equals(xhjksqtogether.getRental().trim())) {
			newXhJksqTogether.setRental(xhjksqtogether.getRental().trim());
		} else {
			newXhJksqTogether.setRental(xhjksqtogether.getRental());
		}
		// 其他所得* 元/年
		if (null != xhjksqtogether.getOtherIncome()
				&& !"".equals(xhjksqtogether.getOtherIncome().trim())) {
			newXhJksqTogether.setOtherIncome(xhjksqtogether.getOtherIncome()
					.trim());
		} else {
			newXhJksqTogether.setOtherIncome(xhjksqtogether.getOtherIncome());
		}
		// 年总收入* 元
		if (null != xhjksqtogether.getAnnualIncome()
				&& !"".equals(xhjksqtogether.getAnnualIncome().trim())) {
			newXhJksqTogether.setAnnualIncome(xhjksqtogether.getAnnualIncome()
					.trim());
		} else {
			newXhJksqTogether.setAnnualIncome(xhjksqtogether.getAnnualIncome());
		}
		// 是否拥有社保基金:是/否
		if (null != xhjksqtogether.getSocialFund()
				&& !"".equals(xhjksqtogether.getSocialFund().trim())) {
			newXhJksqTogether.setSocialFund(xhjksqtogether.getSocialFund()
					.trim());
		} else {
			newXhJksqTogether.setSocialFund(xhjksqtogether.getSocialFund());
		}

		// 借款申请额度
		if (null != xhjksqtogether.getLoanQuota()
				&& !"".equals(xhjksqtogether.getLoanQuota().trim())) {
			newXhJksqTogether
					.setLoanQuota(xhjksqtogether.getLoanQuota().trim());
		} else {
			newXhJksqTogether.setLoanQuota(xhjksqtogether.getLoanQuota());
		}
		// 申请还款期限
		if (null != xhjksqtogether.getJkCycle()
				&& !"".equals(xhjksqtogether.getJkCycle().trim())) {
			newXhJksqTogether.setJkCycle(xhjksqtogether.getJkCycle().trim());
		} else {
			newXhJksqTogether.setJkCycle(xhjksqtogether.getJkCycle());
		}

		// 还款资金来源
		// 01:独立还款，02:家人协助还款，03:其他方式_____
		if (null != xhjksqtogether.getSourceOfFunds()
				&& !"".equals(xhjksqtogether.getSourceOfFunds().trim())) {
			newXhJksqTogether.setSourceOfFunds(xhjksqtogether
					.getSourceOfFunds().trim());
		}
		// 还款资金来源中的 其他方式 说明
		if (null != xhjksqtogether.getSourceOfFundsInfo()
				&& !"".equals(xhjksqtogether.getSourceOfFundsInfo().trim())) {
			newXhJksqTogether.setSourceOfFundsInfo(xhjksqtogether
					.getSourceOfFundsInfo().trim());
		} else {
			newXhJksqTogether.setSourceOfFundsInfo(xhjksqtogether
					.getSourceOfFundsInfo());
		}

		// 暂存、提交等状态
		if (null != xhjksqtogether.getState()
				&& !"".equals(xhjksqtogether.getState().trim())) {
			newXhJksqTogether.setState(xhjksqtogether.getState().trim());
		}

		return newXhJksqTogether;
	}

	/**
	 * 保存共同借款人的紧急联系人
	 * 
	 * @param request
	 * @param xhJksqTogether
	 * @return
	 */
	private boolean saveTogetherXhJkjjlxr(HttpServletRequest request,
			XhJksqTogether xhJksqTogether) {
		return saveXhJkjjlxrs(request, null, xhJksqTogether, null);
	}

	/**
	 * 保存紧急联系人
	 * 
	 * @param request
	 * @param xhJksq
	 *            借款申请信息
	 * @param xhJksqTogether
	 *            共同还款人信息
	 * @param xhJksqUPHistory
	 *            借款申请历史信息 /变更信息
	 * @return
	 */
	private boolean saveXhJkjjlxrs(HttpServletRequest request, XhJksq xhJksq,
			XhJksqTogether xhJksqTogether, XhJksqUPHistory xhJksqUPHistory) {
		boolean flag = false;
		XhJkjjlxr xhJkjjlxr = null;
		for (int i = 1; i <= 6; i++) {
			xhJkjjlxr = new XhJkjjlxr();
			if (null != xhJksq) {
				xhJkjjlxr.setXhJksq(xhJksq);
			}
			if (null != xhJksqTogether) {
				xhJkjjlxr.setXhJksqTogether(xhJksqTogether);
			}
			if (null != xhJksqUPHistory) {
				xhJkjjlxr.setXhjksqjphistory(xhJksqUPHistory);
			}
			String name = request.getParameter("name" + i);// 紧急联系人姓名
			String jjlxrgzdw = request.getParameter("jjlxrgzdw" + i);// 紧急联系人工作单位
			String jjlxrdwdzhjtzz = request.getParameter("jjlxrdwdzhjtzz" + i);// 单位地址或家庭住址
			String jjlxrlxdh = request.getParameter("jjlxrlxdh" + i);// 紧急联系人联系电话

			String ybrgx = request.getParameter("ybrgx" + i);// 与本人关系

			xhJkjjlxr.setName(name);
			xhJkjjlxr.setJjlxrgzdw(jjlxrgzdw);
			xhJkjjlxr.setJjlxrdwdzhjtzz(jjlxrdwdzhjtzz);
			xhJkjjlxr.setJjlxrlxdh(jjlxrlxdh);
			xhJkjjlxr.setYbrgx(ybrgx);
			xhJkjjlxrDao.save(xhJkjjlxr);
			if (null != xhJkjjlxr.getId() && 0 != xhJkjjlxr.getId()) {
				flag = true;
			} else {
				flag = false;
				System.out.println("保存紧急联系人失败");
			}
			if (flag == false) {
				break;
			}
		}
		return flag;
	}

	public void uploadAndChangeState(String id,
			List<Map<String, String>> fileName, String state,
			String uploadFlag, String FileOwner) {
		String upFlag = "";
		Long Id = Long.parseLong(id);
		if ("签约".equals(uploadFlag)) {
			XhJkht xhJkht = xhJkhtDao.findLoanContarctByApplyID(Id);
			Id = xhJkht.getId();
			upFlag = "0";
		} else if ("授信".equals(uploadFlag)) {
			// Id = Long.parseLong(id);
			upFlag = "授信";
		} else if ("外访".equals(uploadFlag)) {
			upFlag = "外访";
		} else if ("P2P".equals(uploadFlag)) {
			upFlag = "P2P";
		}

		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if (fileName.size() > 0) {
			for (Map<String, String> m : fileName) {
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner(FileOwner);
				xhUploadFiles.setMainId(Id);
				xhUploadFiles.setFlag(upFlag);
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
			}
			// boolean flag = this.changeState(Long.parseLong(id), state);
			Map<String, Object> params = new HashMap<String, Object>();

			if (state.contains(".")) {
				if ("外访".equals(upFlag)) {
					state = "31.C";
				} else {
					state = state.replace(".B", "");
				}
			} else if ("30".equals(state)) {
				// 上传完授信资料才为进件时间
				params.put("BACKUP02", new Timestamp(new Date().getTime()));
			}

			params.put("state", state);
			boolean flag = changeState(Long.parseLong(id), params, null);
			this.saveUploadHistory(Long.parseLong(id), flag, uploadFlag);
		}
	}

	/**
	 * 上传文件但不修改状态
	 * 
	 * @param id
	 * @param fileName
	 * @param uploadFlag
	 * @param FileOwner
	 */
	public void uploadFile(String id, List<Map<String, String>> fileName,
			String uploadFlag, String FileOwner) {
		Long Id = Long.parseLong(id);

		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if (fileName.size() > 0) {
			for (Map<String, String> m : fileName) {
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner(FileOwner);
				xhUploadFiles.setMainId(Id);
				xhUploadFiles.setFlag("授信");
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
			}
			this.saveUploadHistory(Long.parseLong(id), true, uploadFlag);
		}
	}

	private void saveUploadHistory(Long id, boolean flag, String uploadFlag) {
		XhJksq xhJksq = this.getXhJksq(id);
		if ("签约".equals(uploadFlag)) {
			String describe = "上传签约资料成功";
			String remarks = "上传签约资料";
			if (flag == false) {
				describe = "上传签约资料失败";
				remarks = "上传签约资料";
			}
			baseInfoManager.saveXhJksqState(xhJksq, describe, remarks);
		} else if ("授信".equals(uploadFlag)) {
			String describe = "上传授信资料成功";
			String remarks = "上传授信资料";
			if (flag == false) {
				describe = "上传授信资料失败";
				remarks = "上传授信资料";
			}
			baseInfoManager.saveXhJksqState(xhJksq, describe, remarks);
		} else if ("补充授信".equals(uploadFlag)) {
			String describe = "上传补充授信资料成功";
			String remarks = "上传补充授信资料";
			if (flag == false) {
				describe = "上传补充授信资料失败";
				remarks = "上传补充授信资料";
			}
			baseInfoManager.saveXhJksqState(xhJksq, describe, remarks);
		} else if ("外访".equals(uploadFlag)) {
			String describe = "上传外访资料成功";
			String remarks = "上传外访资料";
			if (flag == false) {
				describe = "上传外访资料失败";
				remarks = "上传外访资料";
			}
			baseInfoManager.saveXhJksqState(xhJksq, describe, remarks);
		}
	}

	/**
	 * 借款申请中的 变更申请保存
	 * 
	 * @param request
	 * @param jksqhistory
	 * @return
	 */
	public boolean jksqChangeSave(HttpServletRequest request,
			XhJksqUPHistory jksqhistory) {
		boolean flag = false;
		XhJksq xhJksq = this.getXhJksq(jksqhistory.getXhjksq().getId());
		XhJksqUPHistory xhJksqUPHistory = null;
		if (null != jksqhistory.getId() && 0 != jksqhistory.getId()) {
			xhJksqUPHistory = xhJksq.getXhJksqUPHistory();
			xhJksqUPHistory.setUpHistory(jksqhistory);

			Timestamp tempTimeStamp = xhJksqUPHistory.getLastModifyTime();// 保存前的最后修改日期
			xhJksqUPHistory.setLastModifyTime(new Timestamp(new Date()
					.getTime()));

			// 保存借款申请动态信息
			Templet templet = getXhjksqTemplet(request);
			xhJksqUPHistory.setTemplet(templet);
			String data = getXhjksqDynamicData(request, templet);
			xhJksqUPHistory.setData(data);

			// 组织
			// Employee emp = baseInfoManager.getUserEmployee();
			// xhJksqUPHistory.setOrgani(emp.getOrgani());
			xhJksqUPHistory.setOrgani(xhJksq.getOrgani());

			xhJksq.setAppState("1");// 直接改为变更提交
			xhJksq.setXhJksqUPHistory(xhJksqUPHistory);
			xhJksqUPHistory.setAppState("1");
			xhJksqUPHistoryDao.update(xhJksqUPHistory);
			// 紧急联系人
			editSaveXhJkjjlxr(request, null, null, xhJksqUPHistory);

			XhJksqUPHistory newXhJksqUPHistory = xhJksqUPHistoryDao
					.get(xhJksqUPHistory.getId());
			if (null == tempTimeStamp
					&& null != newXhJksqUPHistory.getLastModifyTime()) {
				flag = true;
			} else {
				if (tempTimeStamp.getTime() != newXhJksqUPHistory
						.getLastModifyTime().getTime()) {
					flag = true;
				} else {
					flag = false;
					System.out.println("保存变更申请失败");
				}
			}
		} else {
			xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			xhJksqUPHistory.setUpHistory(jksqhistory);

			xhJksq.setAppState("1");// 直接改为变更提交
			xhJksqUPHistory.setAppState("1");

			// 保存借款申请动态信息
			Templet templet = getXhjksqTemplet(request);
			xhJksqUPHistory.setTemplet(templet);
			String data = getXhjksqDynamicData(request, templet);
			xhJksqUPHistory.setData(data);

			// 组织
			// Employee emp = baseInfoManager.getUserEmployee();
			// xhJksqUPHistory.setOrgani(emp.getOrgani());
			xhJksqUPHistory.setOrgani(xhJksq.getOrgani());

			xhJksqUPHistoryDao.save(xhJksqUPHistory);
			xhJksq.setXhJksqUPHistory(xhJksqUPHistory);

			// 紧急联系人
			saveXhJkjjlxrs(request, null, null, xhJksqUPHistory);
			if (null != xhJksqUPHistory.getId() && 0 != xhJksqUPHistory.getId()) {
				flag = true;
			} else {
				flag = false;
				System.out.println("保存变更申请失败");
			}
		}
		if (flag) {
			// baseInfoManager.saveXhJksqState(xhJksq, "变更修改成功，待提交审批",
			// "变更已修改，待提交审批");
			baseInfoManager.saveXhJksqState(xhJksq, "变更申请提交，待审批", "变更申请提交，待审批");
		} else {
			// baseInfoManager.saveXhJksqState(xhJksq, "变更修改失败", "变更修改失败");
			baseInfoManager.saveXhJksqState(xhJksq, "变更申请提交失败", "变更申请提交失败");
		}
		return flag;
	}

	/**
	 * 提交变更申请，等待审批
	 * 
	 * @param Id
	 *            借款申请Id
	 * @return
	 */
	public boolean subJkspChange(Long Id) {
		boolean flag = false;
		XhJksq xhJksq = xhJksqDao.get(Id);
		xhJksq.setAppState("1");

		Timestamp tempTimeStamp = xhJksq.getLastModifyTime();// 保存前的最后修改日期
		xhJksq.setLastModifyTime(new Timestamp(new Date().getTime()));
		xhJksqDao.save(xhJksq);

		XhJksq newXhJksq = xhJksqDao.get(xhJksq.getId());
		if (null == tempTimeStamp && null != newXhJksq.getLastModifyTime()) {
			flag = true;
		} else {
			if (tempTimeStamp.getTime() != newXhJksq.getLastModifyTime()
					.getTime()) {
				flag = true;
			} else {
				flag = false;
				System.out.println("提交变更申请失败");
			}
		}
		if (flag) {
			baseInfoManager.saveXhJksqState(xhJksq, "变更申请提交，待审批", "变更申请提交，待审批");
		} else {
			baseInfoManager.saveXhJksqState(xhJksq, "变更申请提交失败", "变更申请提交失败");
		}
		return flag;
	}

	/**
	 * 借款变更申请审批
	 * 
	 * @param request
	 * @param history
	 * @return
	 */
	public boolean saveJksqChangeExam(HttpServletRequest request,
			XhJksqUPHistory history) {
		boolean flag = false;
		Employee emp = baseInfoManager.getUserEmployee();
		XhJksqUPHistory xhJksqUPHistory = xhJksqUPHistoryDao.get(history
				.getId());
		xhJksqUPHistory.setAppState(history.getAppState());
		xhJksqUPHistory.setAuditIdea(history.getAuditIdea());
		xhJksqUPHistory.setAuditPerson(emp.getName());
		xhJksqUPHistory.setAuditTime(new Timestamp(new Date().getTime()));
		if ("3".equals(history.getAppState())) {
			// 审批不通过，则只修改主表的变更状态
			xhJksqUPHistory.getXhjksq().setAppState(
					history.getAppState().trim());
		} else {
			// 审批通过，则将变更表信息复制给主表，同时主表变更状态设置-1
			xhJksqUPHistory.getXhjksq().hisToXhJksq(xhJksqUPHistory, "all");
			xhJksqUPHistory.getXhjksq().setAppState("-1");
			// 把变更的紧急联系人 复制给主表的 紧急联系人
			xhJkjjlxrDao.delXhJkjjlxr(xhJksqUPHistory.getXhjksq().getId(),
					"XHJKSQ_ID");
			// xhJkjjlxrDao.flush();
			saveXhJkjjlxrs(request, xhJksqUPHistory.getXhjksq(), null, null);
		}

		Timestamp tempTimeStamp = xhJksqUPHistory.getLastModifyTime();// 保存前的最后修改日期
		xhJksqUPHistory.setLastModifyTime(new Timestamp(new Date().getTime()));
		xhJksqUPHistoryDao.save(xhJksqUPHistory);

		XhJksqUPHistory newXhJksqUPHistory = xhJksqUPHistoryDao
				.get(xhJksqUPHistory.getId());
		if (null == tempTimeStamp
				&& null != newXhJksqUPHistory.getLastModifyTime()) {
			flag = true;
		} else {
			if (tempTimeStamp.getTime() != newXhJksqUPHistory
					.getLastModifyTime().getTime()) {
				flag = true;
			} else {
				flag = false;
				System.out.println("变更申请审批失败");
			}
		}
		if (flag) {
			if ("2".equals(history.getAppState())) {
				baseInfoManager.saveXhJksqState(xhJksqUPHistory.getXhjksq(),
						"变更申请审批通过", "变更申请审批");
			} else if ("3".equals(history.getAppState())) {
				baseInfoManager.saveXhJksqState(xhJksqUPHistory.getXhjksq(),
						"变更申请审批不通过", "变更申请审批");
			}
		} else {
			baseInfoManager.saveXhJksqState(xhJksqUPHistory.getXhjksq(),
					"变更申请审批失败", "变更申请审批");
		}
		return flag;
	}

	/**
	 * 变更历史中的历史列表
	 * 
	 * @param page
	 * @param params
	 */
	@Transactional(readOnly = true)
	public void searchXhJksqUPHistory(Page<XhJksqUPHistory> page,
			Map<String, Object> params, XhJksq xhjksq) {

		params.put("xhjksq", xhjksq.getId());

		xhJksqUPHistoryDao.queryXhJksqUPHistory(page, params);
	}

	/**
	 * 变更历史详细信息
	 * 
	 * @param Id
	 * @return
	 */
	@Transactional(readOnly = true)
	public XhJksqUPHistory getXhJksqUPHistory(Long Id) {
		return xhJksqUPHistoryDao.get(Id);
	}

	public boolean userGiveUp(Long Id, String mess) {
		boolean flag = false;
		XhJksq xhjksq = this.getXhJksq(Id);
		// xhjksq.setId(Id);
		flag = changeState(xhjksq, "81");
		if (flag) {
			baseInfoManager.saveXhJksqState(xhjksq, "客户放弃成功", mess);
		} else {
			baseInfoManager.saveXhJksqState(xhjksq, "客户放弃失败", mess);
		}
		return flag;
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> listJksqCredit(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		if (filter.containsKey("jkrxm")) {// 借款人姓名
			value = String.valueOf(filter.get("jkrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.jkrxm like '%" + value + "%'";
			}
		}

		if (filter.containsKey("zjhm")) {// 证件号码
			value = String.valueOf(filter.get("zjhm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZJHM = '" + value + "'";
			}
		}

		if (filter.containsKey("startDate")) {// 进件开始时间 - 进件结束时间
			value = String.valueOf(filter.get("startDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') >= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("endDate")) {
			value = String.valueOf(filter.get("endDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and substr(a.backup02,'0', '10') <= '" + value
						+ "'";
			}
		}

		if (filter.containsKey("jkType")) {// 产品
			value = String.valueOf(filter.get("jkType"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"all".equals(value)) {
					sql = sql + " and a.JK_TYPE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("backup01")) {// 借款标志位
			value = String.valueOf(filter.get("backup01"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.BACKUP01 = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("state")) {// 借款状态
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.STATE = '" + value + "'";
				}
			}
		}
		if (filter.containsKey("state_injksp")) {// 借款申请中的借款状态查询
			value = String.valueOf(filter.get("state_injksp"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					String[] tt = value.split(",");
					StringBuffer values = new StringBuffer();
					for (int i = 0; i < tt.length; i++) {
						values.append("'").append(tt[i]).append("',");
					}
					int temp = values.lastIndexOf(",");
					if (temp == values.length() - 1) {
						values.deleteCharAt(temp);
					}
					sql = sql + " and a.state in (" + values.toString() + ")";
				}
			}
		}
		if (filter.containsKey("state_in")) {// 多借款状态
			value = String.valueOf(filter.get("state_in"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("applyIds")) {// 多借款状态
			value = String.valueOf(filter.get("applyIds"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.id in (" + value + ")";
				}
			}
		}

		if (filter.containsKey("state_g")) {// 多借款状态--大于等于
			value = String.valueOf(filter.get("state_g"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.state >= " + value;
				}
			}
		}
		if (filter.containsKey("province")) {// 所属省份
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PROVINCE = '" + value + "'";
			}
		}

		if (filter.containsKey("city")) {// 所属城市
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" + value + "'";
			}
		}

		// 不在借款申请表中
		StringBuffer tablesql = new StringBuffer(" ");
		StringBuffer unionsql = new StringBuffer(" ");
		if (filter.containsKey("teamName") || filter.containsKey("saleName")) {// 团队经理
			String teamName = String.valueOf(filter.get("teamName"));
			String saleName = String.valueOf(filter.get("saleName"));

			boolean teamNameFlag = StringUtils.isNotEmpty(teamName);
			boolean saleNameFlag = StringUtils.isNotEmpty(saleName);
			if (teamNameFlag || saleNameFlag) {
				tablesql.append(", XH_XYDKZX b  ");
				unionsql.append(" and a.XYDKZX_ID=b.ID ");
				if (teamNameFlag) {
					// sql = sql + " and b.TEAM_NAME like '%" + teamName + "%'";
					// 借款咨询中更改团队经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ teamName + "%' ";
					sql = sql + " and b.employee_crm in (" + tempSql + ")";
				}
				if (saleNameFlag) {
					// sql = sql + " and b.SALE_NAME like '%" + saleName + "%'";
					// 借款咨询中更改销售经理字段后，修改借款申请中搜索sql
					String tempSql = "select emp.id from base_employee emp where emp.name like '%"
							+ saleName + "%' ";
					sql = sql + " and b.employee_cca in (" + tempSql + ")";
				}
			}
		}

		if (filter.containsKey("appState")) {// 借款申请有变更申请的
			value = String.valueOf(filter.get("appState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.APP_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("subState")) {// 借款申请是否已提交
			value = String.valueOf(filter.get("subState"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.SUB_STATE = '" + value + "'";
				}
			}
		}

		if (filter.containsKey("XHJKSQ_ID")) {// 借款申请ID，查询变更历史
			value = String.valueOf(filter.get("XHJKSQ_ID"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql = sql + " and a.XHJKSQ_ID = '" + value + "'";
				}
			}
		}
		
		// 级联查询sql
		sql = sql + PropertiesUtils.getLoanSql();

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		} else {
			sql = sql + " order by a.CREATE_TIME desc ";

		}

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("tablesql", tablesql.toString());
		conditions.put("unionsql", unionsql.toString());
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getJksqCredit(Long Id) {
		String sql = "select t.* from XH_CREDIT_AUDIT t where t.loan_apply_id="
				+ Id + " and t.credit_result = '1' order by t.credit_type asc";
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql);
		return listMap;
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTogetherPerson(Long Id) {
		String sql = "select t.id, t.together_name, t.identification from XH_JKSQ_TOGETHER t where t.xhjksq_id = "
				+ Id + " order by t.id asc";
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql);
		return listMap;
	}

	@Transactional(readOnly = true)
	public XhJksqTogether getTogether(Long id) {
		return xhJksqTogetherDao.get(id);
	}

	public void delTogetherPerson(Long id) {
		xhJksqTogetherDao.delete(id);
	}

	public void deleteTogetherPerson(Long Id) {
		XhJksqTogether xhJksqTogether = getTogether(Id);
		List<Map<String, Object>> listTogetherPerson = getTogetherPerson(xhJksqTogether
				.getXhjksq().getId());
		if (listTogetherPerson != null && listTogetherPerson.size() == 1) {
			XhJksq xhJksq = xhJksqTogether.getXhjksq();
			xhJksq.setTogetherPerson("否");
			saveXhJksq(xhJksq);
		}
		delTogetherPerson(Id);
	}

	public Map<String, Object> updateJksqCredit(HttpServletRequest request) {
		String data = "";
		String id = request.getParameter("id").toString();
		String name = request.getParameter("name").toString();
		String value = request.getParameter("value").toString();
		XhCreditAudit xhCreditAudit = getCreditAudit(new Long(id));
		if (name != null && name.equals("creditAmount")) {
			xhCreditAudit.setCreditAmount(value);
			data = "批贷金额更新成功！";
		} else if (name != null && name.equals("creditMonth")) {
			xhCreditAudit.setCreditMonth(value);
			data = "批贷期限更新成功！";
		} else if (name != null && name.equals("creditAllRate")) {
			xhCreditAudit.setCreditAllRate(value);
			data = "综合费率更新成功！";
		} else if (name != null && name.equals("outVisitFee")) {
			xhCreditAudit.setOutVisitFee(value);
			data = "外访咨询费更新成功！";
		} else if (name != null && name.equals("urgentCreditFee")) {
			xhCreditAudit.setUrgentCreditFee(new Double(value));
			data = "加急费更新成功！";
		}
		saveCreditAudit(xhCreditAudit);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		modelMap.put("success", "true");
		return modelMap;
	}

	public Map<String, Object> updateTogetherPerson(HttpServletRequest request) {
		String data = "";
		String id = request.getParameter("id").toString();
		String name = request.getParameter("name").toString();
		String value = request.getParameter("value").toString();

		XhJksqTogether xhJksqTogether = getTogether(new Long(id));
		if (name != null && name.equals("togetherName")) {
			xhJksqTogether.setTogetherName(value);
			data = "共借人姓名更新成功！";
		} else if (name != null && name.equals("togetherCardNo")) {
			xhJksqTogether.setIdentification(value);
			data = "共借人身份证件号码更新成功！";
		}
		xhJksqTogetherDao.save(xhJksqTogether);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		modelMap.put("success", "true");
		return modelMap;
	}

	public Map<String, Object> addTogetherPerson(HttpServletRequest request) {
		String id = request.getParameter("id").toString();
		String data = request.getParameter("data").toString();
		String togetherCardNo = request.getParameter("togetherCardNo")
				.toString();
		XhJksq xhJksq = getXhJksq(new Long(id));
		if (xhJksq.getTogetherPerson() == null
				|| ("否").equals(xhJksq.getTogetherPerson())) {
			xhJksq.setTogetherPerson("是");
			saveXhJksq(xhJksq);
		}
		XhJksqTogether xhJksqTogether = new XhJksqTogether();
		xhJksqTogether.setTogetherName(data);
		xhJksqTogether.setIdentification(togetherCardNo);
		xhJksqTogether.setXhjksq(xhJksq);
		xhJksqTogetherDao.save(xhJksqTogether);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", "添加共借人成功！");
		modelMap.put("success", "true");
		return modelMap;
	}

	public Map<String, Object> loginPersonValidate(HttpServletRequest request) {
		String data = "验证通过！";
		boolean res = false;
		boolean isA = true;
		boolean isB = true;
		String idA = request.getParameter("idA").toString();
		String idB = request.getParameter("idB").toString();
		String passA = request.getParameter("passA").toString();
		String passB = request.getParameter("passB").toString();
		User userA = baseInfoManager.getUserByEmployee(new Long(idA));
		User userB = baseInfoManager.getUserByEmployee(new Long(idB));
		passA = EncodeUtils.getMd5PasswordEncoder(passA, userA.getLoginName());
		passB = EncodeUtils.getMd5PasswordEncoder(passB, userB.getLoginName());
		if (!passA.equals(userA.getPassword())) {
			isA = false;
			data = "审批经理A验证失败，请重新验证！";
		}
		if (!passB.equals(userB.getPassword())) {
			isB = false;
			data = "审批经理B验证失败，请重新验证！";
		}
		if (isA && isB) {
			res = true;
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		modelMap.put("success", res);
		return modelMap;
	}

	public XhJksq findJksqByXy(Long xydkzxId) {
		return xhJksqDao.findJksqByXy(xydkzxId);
	}

	public Long findJksqIdByXy(Long xydkzxId) {
		String sql = "select t.id from XH_JKSQ t where t.xydkzx_id = "
				+ new Long(xydkzxId);
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql);
		String jksqId = listMap.get(0).get("ID").toString();
		return new Long(jksqId);
	}

	public List<XhJksq> findJksqByZjhm(String zjhm) {
		return xhJksqDao.listJksqByZjhm(zjhm);
	}

	public List<XhJksq> findJksqByXyList(Long xydkzxId) {
		return xhJksqDao.listJksqByXy(xydkzxId);
	}

	@Transactional(readOnly = true)
	public XhCreditAudit getCreditAudit(Long id) {
		return xhCreditAuditDao.get(id);
	}

	public void saveCreditAudit(XhCreditAudit entity) {
		xhCreditAuditDao.save(entity);
	}

	/**
	 * 复制咨询信息 MDY 2013-7-24
	 * 
	 * @param xydkzxId
	 * @param xydkzx
	 * @return
	 */
	public Xydkzx copyXydkzxInfo(Long xydkzxId, Xydkzx xydkzx) {
		Xydkzx newXydkzx1 = baseInfoManager.getXydkzx(new Long(xydkzxId));
		Xydkzx newXydkzx = new Xydkzx();
		Long newId = newXydkzx.getId();
		try {
			BeanUtilEx.copyProperties(newXydkzx, newXydkzx1);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			newXydkzx = null;
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			newXydkzx = null;
			e.printStackTrace();
		}
		Date gtTime = new Date();
		SimpleDateFormat gt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String gtTimeString = gt.format(gtTime);
		newXydkzx.setId(newId);
		newXydkzx.setGtjl("");
		newXydkzx.setType("1");
		newXydkzx.setGtTime(gtTimeString);
		newXydkzx.setEmployeeCca(xydkzx.getEmployeeCca());
		newXydkzx.setEmployeeCrm(xydkzx.getEmployeeCrm());
		newXydkzx.setJklx(xydkzx.getJklx());
		newXydkzx.setPlanAmount(xydkzx.getPlanAmount());
		newXydkzx.setLoanPurpose(xydkzx.getLoanPurpose());
		newXydkzx.setZhuangTai("10");
		Employee emp = baseInfoManager.getUserEmployee();
		newXydkzx.setOrgani(emp.getOrgani());
		xydkzxDao.save(newXydkzx);
		return newXydkzx;
	}

	/**
	 * 复制咨询信息 MDY 2013-7-24
	 * 
	 * @param xydkzxId
	 * @param xydkzx
	 * @return
	 */
	public Xydkzx copyXydkzxInfoNew(Long jksqId, Xydkzx xydkzx) {
		XhJksq jksq = xhJksqDao.get(jksqId);
		Xydkzx newXydkzx = new Xydkzx();
		Date gtTime = new Date();
		SimpleDateFormat gt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String gtTimeString = gt.format(gtTime);
		newXydkzx.setKhmc(jksq.getJkrxm());
		newXydkzx.setTelephone(jksq.getTelephone());
		newXydkzx.setGtjl("");
		newXydkzx.setType("1");
		newXydkzx.setGtTime(gtTimeString);
		newXydkzx.setEmployeeCca(xydkzx.getEmployeeCca());
		newXydkzx.setEmployeeCrm(xydkzx.getEmployeeCrm());
		newXydkzx.setJklx(xydkzx.getJklx());
		newXydkzx.setPlanAmount(xydkzx.getPlanAmount());
		newXydkzx.setLoanPurpose(xydkzx.getLoanPurpose());
		newXydkzx.setZhuangTai("10");
		newXydkzx.setZdkh(String.valueOf(jksqId));
		Employee emp = baseInfoManager.getUserEmployee();
		newXydkzx.setOrgani(emp.getOrgani());
		xydkzxDao.save(newXydkzx);
		return newXydkzx;
	}

	/**
	 * 复制借款申请信息 MDY 2013-7-24
	 * 
	 * @param xydkzxId
	 * @param newXydkzx
	 * @return
	 */
	public XhJksq copyXhJksqInfo(Long xydkzxId, Xydkzx newXydkzx) {
		Long jksqId = findJksqIdByXy(new Long(xydkzxId));
		XhJksq newXhJksq = new XhJksq();
		XhJksq xhJksq = xhJksqDao.get(jksqId);
		xhJksq.setXhJkjjlxrs(null);
		// Long newJksqId = newXhJksq.getId();
		// try {
		// BeanUtilEx.copyProperties(newXhJksq, xhJksq);
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// newXhJksq = null;
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// newXhJksq = null;
		// e.printStackTrace();
		// }
		// newXhJksq.setId(newJksqId);
		newXhJksq.setJkrxm(xhJksq.getJkrxm());
		newXhJksq.setEnglishName(xhJksq.getEnglishName());
		newXhJksq.setGenders(xhJksq.getGenders());
		newXhJksq.setBirthday(xhJksq.getBirthday());
		newXhJksq.setPocertificates(xhJksq.getPocertificates());
		newXhJksq.setZjhm(xhJksq.getZjhm());
		newXhJksq.setHjadress(xhJksq.getHjadress());
		newXhJksq.setHomeAddress(xhJksq.getHomeAddress());
		newXhJksq.setHomePhone(xhJksq.getHomePhone());
		newXhJksq.setZy(xhJksq.getZy());
		newXhJksq.setCompany(xhJksq.getCompany());
		newXhJksq.setCompanyPhone(xhJksq.getCompanyPhone());
		newXhJksq.setCompanyAdress(xhJksq.getCompanyAdress());
		newXhJksq.setCompanyNature(xhJksq.getCompanyNature());
		newXhJksq.setTelephone(xhJksq.getTelephone());
		newXhJksq.setEmail(xhJksq.getEmail());
		newXhJksq.setMaritalStatus(xhJksq.getMaritalStatus());
		newXhJksq.setYwzn(xhJksq.getYwzn());
		newXhJksq.setQqhm(xhJksq.getQqhm());
		newXhJksq.setAnnualIncome(xhJksq.getAnnualIncome());
		newXhJksq.setIncomeIllustration(xhJksq.getIncomeIllustration());
		newXhJksq.setLiveState(xhJksq.getLiveState());
		newXhJksq.setLiveMessage(xhJksq.getLiveMessage());
		newXhJksq.setTxdz(xhJksq.getTxdz());
		newXhJksq.setSpouseName(xhJksq.getSpouseName());
		newXhJksq.setSpouseGenders(xhJksq.getSpouseGenders());
		newXhJksq.setSpouseBirthday(xhJksq.getSpouseBirthday());
		newXhJksq.setSpouseAdress(xhJksq.getSpouseAdress());
		newXhJksq.setSpousePocertificates(xhJksq.getSpousePocertificates());
		newXhJksq.setSpouseZjhm(xhJksq.getSpouseZjhm());
		newXhJksq.setSpouseTelephone(xhJksq.getSpouseTelephone());
		newXhJksq.setSpouseHomePhone(xhJksq.getSpouseHomePhone());
		newXhJksq.setSpouseCompany(xhJksq.getSpouseCompany());
		newXhJksq.setSpouseDepFunction(xhJksq.getSpouseDepFunction());
		newXhJksq.setSpouseCompanyPhone(xhJksq.getSpouseCompanyPhone());
		newXhJksq.setSpouseCompanyAdress(xhJksq.getSpouseAdress());
		newXhJksq.setSpouseAnnualIncome(xhJksq.getSpouseAnnualIncome());
		newXhJksq.setSpouseWorkinglife(xhJksq.getSpouseWorkinglife());
		newXhJksq.setBankOpen(xhJksq.getBankOpen());
		newXhJksq.setBankUsername(xhJksq.getBankUsername());
		newXhJksq.setBankNum(xhJksq.getBankNum());

		newXhJksq.setProvince(xhJksq.getProvince());
		newXhJksq.setCity(xhJksq.getCity());
		newXhJksq.setArea(xhJksq.getArea());
		newXhJksq.setOrgani(xhJksq.getOrgani());
		newXhJksq.setXydkzx(newXydkzx);
		newXhJksq.setXhJkjjlxrs(null);
		List<XhJkjjlxr> listXhJkjjlxr = xhJkjjlxrDao.listXhJkjjlxr(jksqId);
		List<XhJkjjlxr> xhJkjjlxrs = copyXhJkjjlxrInfo(newXhJksq, listXhJkjjlxr);
		newXhJksq.setXhJkjjlxrs(xhJkjjlxrs);
		xhJksqDao.save(newXhJksq);
		return newXhJksq;
	}

	/**
	 * 复制紧急联系人信息 MDY 2013-7-24
	 * 
	 * @param xydkzxId
	 * @param newXydkzx
	 * @return
	 */
	public List<XhJkjjlxr> copyXhJkjjlxrInfo(XhJksq newXhJksq,
			List<XhJkjjlxr> list) {
		List<XhJkjjlxr> xhJkjjlxrs = new ArrayList<XhJkjjlxr>();
		for (XhJkjjlxr j : list) {
			XhJkjjlxr XhJkjjlxr = new XhJkjjlxr();
			XhJkjjlxr.setName(j.getName());
			XhJkjjlxr.setYbrgx(j.getYbrgx());
			XhJkjjlxr.setJjlxrgzdw(j.getJjlxrgzdw());
			XhJkjjlxr.setJjlxrdwdzhjtzz(j.getJjlxrdwdzhjtzz());
			XhJkjjlxr.setJjlxrlxdh(j.getJjlxrlxdh());
			XhJkjjlxr.setLxrlx(j.getLxrlx());
			XhJkjjlxr.setOptionUser(j.getOptionUser());
			XhJkjjlxr.setOptionTime(j.getOptionTime());
			XhJkjjlxr.setXhJksq(newXhJksq);
			XhJkjjlxr.setXhjksqjphistory(null);
			XhJkjjlxr.setXhJksqTogether(null);
			xhJkjjlxrs.add(XhJkjjlxr);
		}
		return xhJkjjlxrs;
	}

	/**
	 * 保存循环贷信息业务员 MDY 2013-7-24
	 * 
	 * @param xydkzxId
	 * @param xydkzx
	 * @return
	 */
	public String[] saveLoopConsulting(Long jksqId, Xydkzx xydkzx) {
		String[] msg = new String[] { "200", "保存成功" };
		Xydkzx newXydkzx = copyXydkzxInfoNew(jksqId, xydkzx);
		// XhJksq newXhJksq = copyXhJksqInfo(xydkzxId, newXydkzx);
		// if(newXydkzx == null || newXhJksq == null){
		if (newXydkzx == null) {
			msg[0] = "300";
			msg[1] = "保存失败,循环贷数据转换异常!";
		}
		return msg;
	}

	/*
	 * 
	 * 导出借款人联系人信息
	 */

	@Transactional(readOnly = true)
	public void exportJksqlxr(String path, Long id) {
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l;

		String sql = "select a.jjlxrlxdh,a.name,a.ybrgx from xh_jkjjlxr a where a.name is not null and a.xhjksq_id="
				+ id;
		List<Map<String, Object>> list = jdbcDao.searchByMergeSql(sql);

		XhJksq xhjksq = xhJksqDao.get(id);

		inputPath = mouFilePath + "dhsqxgb.xls";

		data.put("{1}", res(xhjksq.getTelephone()));
		data.put("{2}", res(xhjksq.getHomePhone()));
		data.put("{3}", res(xhjksq.getCompanyPhone()));
		data.put("{4}", res(xhjksq.getSpouseName()));
		data.put("{5}", res(xhjksq.getSpouseTelephone()));
		data.put("{6}", res(xhjksq.getSpouseHomePhone()));
		data.put("{7}", res(xhjksq.getSpouseCompanyPhone()));
		int b;

		for (int i = 0; i < list.size(); i++) {
			b = i + 1;
			data.put("{" + b + "-1}", res(list.get(i).get("NAME") + ""));
			data.put("{" + b + "-2}", res(list.get(i).get("JJLXRLXDH") + ""));
			data.put("{" + b + "-3}", "");
			data.put("{" + b + "-4}", "");
		}

		if (list.size() < 8) {
			for (int c = list.size(); c < 8; c++) {
				b = c + 1;
				data.put("{" + b + "-1}", "");
				data.put("{" + b + "-2}", "");
				data.put("{" + b + "-3}", "");
				data.put("{" + b + "-4}", "");
			}
		}

		je.toExcel(inputPath, path, data);
	}

	private String res(String value) {
		if (StringUtils.isNotEmpty(value) && !"null".equals(value)) {
			return value;
		} else {
			return "";
		}
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhJksqDao(XhJksqDao xhJksqDao) {
		this.xhJksqDao = xhJksqDao;
	}

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Autowired
	public void setTempletDao(TempletDao templetDao) {
		this.templetDao = templetDao;
	}

	@Autowired
	public void setXydkzxDao(XydkzxDao xydkzxDao) {
		this.xydkzxDao = xydkzxDao;
	}

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	public void setXhJkjjlxrDao(XhJkjjlxrDao xhJkjjlxrDao) {
		this.xhJkjjlxrDao = xhJkjjlxrDao;
	}

	@Autowired
	public void setXhJksqTogetherDao(XhJksqTogetherDao xhJksqTogetherDao) {
		this.xhJksqTogetherDao = xhJksqTogetherDao;
	}

	@Autowired
	public void setXhJkhtDao(XhJkhtDao xhJkhtDao) {
		this.xhJkhtDao = xhJkhtDao;
	}

	@Autowired
	public void setXhJksqUPHistoryDao(XhJksqUPHistoryDao xhJksqUPHistoryDao) {
		this.xhJksqUPHistoryDao = xhJksqUPHistoryDao;
	}

	@Autowired
	public void setXhCreditAuditDao(XhCreditAuditDao xhCreditAuditDao) {
		this.xhCreditAuditDao = xhCreditAuditDao;
	}

	@Autowired
	public void setLoanTaskDelivDao(LoanTaskDelivDao loanTaskDelivDao) {
		this.loanTaskDelivDao = loanTaskDelivDao;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 证件信息
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> isIdInfoByZjhm(HttpServletRequest request) {
		StringBuffer sql = new StringBuffer();
		String zjhm = request.getParameter("zjhm").toString();
		sql.append("select count(t.id) as count from XH_JKSQ t where 1 = 1 and t.zjhm = '"
				+ zjhm.trim() + "'");
		List<Map<String, Object>> listMap = jdbcDao.searchByMergeSql(sql
				.toString());
		Map map = (Map) listMap.get(0);
		String count = map.get("COUNT").toString();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", count);

		// 配偶与借款人:如果新增的借款人是已有借款人的配偶，且未结清，则不可借
		StringBuffer sqlRelation = new StringBuffer();
		sqlRelation.append("select j.id from xh_jksq j where j.spouse_zjhm = '"
				+ zjhm.trim() + "' and j.state != '101' and j.state != '102'");
		List<Map<String, Object>> relations = jdbcDao
				.searchByMergeSql(sqlRelation.toString());
		String id = "";
		if (relations.size() != 0) {
			Map relation = (Map) relations.get(0);
			id = relation.get("ID").toString();
		}
		modelMap.put("dataRelation", id);

		modelMap.put("success", "true");
		return modelMap;
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchLoanTask(String queryName, Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		if (filter.containsKey("sts")) {// 借款人id
			value = String.valueOf(filter.get("sts"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.sts = " + Integer.valueOf(value) + "";
			}
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	public String[] saveLoanTask(HttpServletRequest request){
		String[] res = new String[7];
		String teamLeaderOld = request.getParameter("teamLeaderOld.id");
		String teamLeaderNew = request.getParameter("teamLeaderNew.id");
		String teamLeaderNewName = request.getParameter("teamLeaderNew.name");
		String customerLeaderOld = request.getParameter("customerLeaderOld.id");
		String customerLeaderNew = request.getParameter("customerLeaderNew.id");
		String reasonInfo = request.getParameter("reasonInfo");
		String customerName = request.getParameter("customerName");
		LoanTaskDeliv loanTaskDeliv = new LoanTaskDeliv();
		loanTaskDeliv.setTeamLeaderOld(Long.valueOf(teamLeaderOld));
		loanTaskDeliv.setTeamLeaderNew(Long.valueOf(teamLeaderNew));
		if(customerLeaderOld != null && !"".equals(customerLeaderOld)){
			loanTaskDeliv.setCustomerLeaderOld(Long.valueOf(customerLeaderOld));
		}
		loanTaskDeliv.setCustomerName(customerName);
		loanTaskDeliv.setCustomerLeaderNew(Long.valueOf(customerLeaderNew));
		loanTaskDeliv.setReasonInfo(reasonInfo);
		loanTaskDeliv.setSts(0);
		loanTaskDelivDao.save(loanTaskDeliv);
		res[0] = teamLeaderOld;
		res[1] = teamLeaderNew;
		res[2] = customerLeaderNew;
		res[3] = teamLeaderNewName;
		res[4] = String.valueOf(loanTaskDeliv.getId());
		res[5] = customerLeaderOld;
		res[6] = customerName;
		return res;
	}
	
	public void executeCall(String[] values){
		jdbcTemplate.execute("call LOAN_TASK_DELIVERY_ALL('"+values[0]+"','"+values[1]+"','"+values[5]+"','"+values[2]+"','"+values[3]+"','"+values[4]+"','"+values[6]+"')");
	}
}
