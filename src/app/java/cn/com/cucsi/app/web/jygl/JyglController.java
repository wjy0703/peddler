package cn.com.cucsi.app.web.jygl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhLentmoneywater;
import cn.com.cucsi.app.entity.xhcf.XhSendemail;
import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhTzsqState;
import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.app.entity.xhcf.XhZqtjDetails;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.CjrxxManager;
import cn.com.cucsi.app.service.xhcf.JyglManager;
import cn.com.cucsi.app.service.xhcf.XhTzsqManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.ExportExcelAndImportExcel;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DBUUID;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/jygl")
public class JyglController {
    private Logger logger = LoggerFactory.getLogger(JyglController.class);
    private CjrxxManager cjrxxManager;
    private BaseInfoManager baseInfoManager;
    private XhTzsqManager xhTzsqManager;
    private JyglManager jyglManager;

    private static final Object countLock = new Object();
    @Autowired
    ServletContext context;
    
    @Autowired
    public void setCjrxxManager(CjrxxManager cjrxxManager) {
        this.cjrxxManager = cjrxxManager;
    }
    
    @Autowired
    public void setJyglManager(JyglManager jyglManager) {
        this.jyglManager = jyglManager;
    }

    @Autowired
    public void setXhTzsqManager(XhTzsqManager xhTzsqManager) {
        this.xhTzsqManager = xhTzsqManager;
    }

    @Autowired
    public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
        this.baseInfoManager = baseInfoManager;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    /**
     * 首期债权推荐-列表页
     */
    @RequestMapping(value = "/jyglZqtjSq")
    public String listSq(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }

        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        map.put("backcount", 0);
        String lenjhtzrq_href = (String) request.getSession().getAttribute(
                "lenjhtzrq_href");
        if (StringUtils.isNotEmpty(lenjhtzrq_href)) {
            request.getSession().removeAttribute("lenjhtzrq_href");
            map.put("lenjhtzrq", lenjhtzrq_href);
        }
        String backcount_href = (String) request.getSession().getAttribute(
                "backcount_href");
        if (StringUtils.isNotEmpty(backcount_href)) {
            request.getSession().removeAttribute("backcount_href");
            map.put("backcount", backcount_href);
        }
        String zdr_href = (String) request.getSession()
                .getAttribute("zdr_href");
        if (StringUtils.isNotEmpty(lenjhtzrq_href)) {
            request.getSession().removeAttribute("zdr_href");
            map.put("zdr", zdr_href);
        }
        String tzcp_href = (String) request.getSession().getAttribute(
                "tzcp_href");
        if (StringUtils.isNotEmpty(backcount_href)) {
            request.getSession().removeAttribute("tzcp_href");
            map.put("tzcp", tzcp_href);
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        map.put("state", "2");
        map.put("money", "1");// 可推荐金额大于0
        // map.put("lastCjzq", "0");//剩余出借周期不等于0
        map.put("overstate", "0");// 完结状态等于0
        map.put("firstdate", "0");// 完结状态等于0
        List<Map<String, Object>> listTzsq = xhTzsqManager.searchXhTzsq(
                "queryXhTzsqList", map, page);

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listTzsq", listTzsq);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqtjSq";
    }

    /**
     * 非首期债权推荐-列表页
     */
    @RequestMapping(value = "/jyglZqtjFsq")
    public String listFsq(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }

        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        map.put("backcount", 1);
        String lenjhtzrq_href = (String) request.getSession().getAttribute(
                "lenjhtzrq_href");
        if (StringUtils.isNotEmpty(lenjhtzrq_href)) {
            request.getSession().removeAttribute("lenjhtzrq_href");
            map.put("lenjhtzrq", lenjhtzrq_href);
        }
        String backcount_href = (String) request.getSession().getAttribute(
                "backcount_href");
        if (StringUtils.isNotEmpty(backcount_href)) {
            request.getSession().removeAttribute("backcount_href");
            map.put("backcount", backcount_href);
        }
        String zdr_href = (String) request.getSession()
                .getAttribute("zdr_href");
        if (StringUtils.isNotEmpty(lenjhtzrq_href)) {
            request.getSession().removeAttribute("zdr_href");
            map.put("zdr", zdr_href);
        }
        String tzcp_href = (String) request.getSession().getAttribute(
                "tzcp_href");
        if (StringUtils.isNotEmpty(backcount_href)) {
            request.getSession().removeAttribute("tzcp_href");
            map.put("tzcp", tzcp_href);
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        map.put("state", "2");
        map.put("money", "1");// 可推荐金额大于0
        // map.put("lastCjzq", "0");//剩余出借周期不等于0
        map.put("overstate", "0");// 完结状态等于0
        map.put("firstdate", "0");// 完结状态等于0
        List<Map<String, Object>> listTzsq = xhTzsqManager.searchXhTzsq(
                "queryXhTzsqList", map, page);

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listTzsq", listTzsq);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqtjFsq";
    }

    /**
     * 债权推荐-列表页
     */
    @RequestMapping(value = "/jyglZqtj")
    public String list(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }

        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");

        String lenjhtzrq_href = (String) request.getSession().getAttribute(
                "lenjhtzrq_href");
        if (StringUtils.isNotEmpty(lenjhtzrq_href)) {
            request.getSession().removeAttribute("lenjhtzrq_href");
            map.put("lenjhtzrq", lenjhtzrq_href);
        }
        String backcount_href = (String) request.getSession().getAttribute(
                "backcount_href");
        if (StringUtils.isNotEmpty(backcount_href)) {
            request.getSession().removeAttribute("backcount_href");
            map.put("backcount", backcount_href);
        }
        String zdr_href = (String) request.getSession()
                .getAttribute("zdr_href");
        if (StringUtils.isNotEmpty(lenjhtzrq_href)) {
            request.getSession().removeAttribute("zdr_href");
            map.put("zdr", zdr_href);
        }
        String tzcp_href = (String) request.getSession().getAttribute(
                "tzcp_href");
        if (StringUtils.isNotEmpty(backcount_href)) {
            request.getSession().removeAttribute("tzcp_href");
            map.put("tzcp", tzcp_href);
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        map.put("state", "2");
        map.put("money", "1");// 可推荐金额大于0
        // map.put("lastCjzq", "0");//剩余出借周期不等于0
        map.put("overstate", "0");// 完结状态等于0
        map.put("firstdate", "0");// 完结状态等于0
        List<Map<String, Object>> listTzsq = xhTzsqManager.searchXhTzsq(
                "queryXhTzsqList", map, page);

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listTzsq", listTzsq);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqtj";
    }

    /**
     * 债权推荐-编辑页
     */
    @RequestMapping(value = "/editZqtj/{Id}", method = RequestMethod.GET)
    public String editZqtj(@PathVariable Long Id, Model model,
            HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = xhTzsqManager.findXhTzsq(
                "queryXhTzsqList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        if (value.get("ZQTJ_ID") != null) {
            String zqtj_id = value.get("ZQTJ_ID") + "";
            if (StringUtils.isNotEmpty(zqtj_id)) {
                XhZqtj xhZqtj = jyglManager.getXhZqtj(Long.parseLong(zqtj_id));
                model.addAttribute("xhZqtj", xhZqtj);
                map = new HashMap<String, Object>();
                map.put("zqtj_id", zqtj_id);
                List<Map<String, Object>> listXhZqtjDetails = jyglManager
                        .findXhZqtjDetails("queryXhZqtjDetailsList", map);
                model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);
                String mateid = "";
                for (XhZqtjDetails x : xhZqtj.getXhZqtjDetails()) {
                    mateid += x.getKyzqjzId() + ",";
                }
                model.addAttribute("mateid", mateid);
            }
        }
        String lenjhtzrq_href = request.getParameter("lenjhtzrq_href");
        model.addAttribute("lenjhtzrq_href", lenjhtzrq_href);
        String backcount_href = request.getParameter("backcount_href");
        model.addAttribute("backcount_href", backcount_href);

        String zdr_href = request.getParameter("zdr_href");
        model.addAttribute("zdr_href", zdr_href);
        String tzcp_href = request.getParameter("tzcp_href");
        model.addAttribute("tzcp_href", tzcp_href);

        map = new HashMap<String, Object>();
        map.put("cjrxx_id", Id + "");
        List<Map<String, Object>> listXhLentmoneywater = jyglManager
                .findXhZqtjDetails("queryXhLentmoneywater", map);
        model.addAttribute("listXhLentmoneywater", listXhLentmoneywater);

        return "jygl/zqtj-input";
    }

    /**
     * 债权推荐-保存
     */
    @RequestMapping(value = "/saveZqtj", method = RequestMethod.POST)
    public String saveZqtj(Model model, HttpServletRequest request,
            HttpServletResponse response) {
        String mateIds = request.getParameter("mateId");
        String id = request.getParameter("id");
        String state = request.getParameter("state");
        String money = request.getParameter("MONEY");
        // 出借日期
        String LENJHTZRQ = request.getParameter("LENJHTZRQ");
        // 可推荐期数
        String LAST_CJZQ = request.getParameter("LAST_CJZQ");

        int lentcount = Integer.parseInt(request.getParameter("LENTCOUNT"));
        String zqtj_id = request.getParameter("zqtj_id");
        XhTzsq tzsq = xhTzsqManager.getXhTzsq(Long.parseLong(id));
        XhZqtj zqtj = new XhZqtj();// 推荐表
        XhZqtjDetails zqtjds = new XhZqtjDetails();// 推荐明细
        XhZqtjDetails[] zqtjd;// 推荐明细
        if (StringUtils.isNotEmpty(zqtj_id)) {
            zqtj = jyglManager.getXhZqtj(Long.parseLong(zqtj_id));
            zqtj.setXhZqtjDetails(null);
        } else {
            // Employee emp = baseInfoManager.getUserEmployee();
            // zqtj.setOrgani(emp.getOrgani());
            zqtj.setXhTzsq(tzsq);
            zqtj.setCjrxx(tzsq.getCjrxx());
            zqtj.setJhtzrq(LENJHTZRQ);
            if (lentcount > 1) {
                List<Map<String, Object>> getXhZqtjSqMessage = jyglManager
                        .getXhZqtjSqMessage(id, LENJHTZRQ);
                if (getXhZqtjSqMessage != null && getXhZqtjSqMessage.size() > 0) {
                    // zqtj.setJhtzrq(getXhZqtjSqMessage.get(0).get("JHTZRQ")+"");
                    zqtj.setXybgrq(getXhZqtjSqMessage.get(0).get("XYBGRQ") + "");
                    zqtj.setZdr(getXhZqtjSqMessage.get(0).get("ZDR") + "");
                }
                zqtj.setLentState("1");
                zqtj.setLentStateYj("1");
                zqtj.setZhglf(0.0);
            } else {
                zqtj.setXybgrq(CreditHarmonyComputeUtilties
                        .getFirstDateOfBackMoney(LENJHTZRQ));
                zqtj.setFsqbgrq(zqtj.getXybgrq());
                zqtj.setZdr(CreditHarmonyComputeUtilties.getZdr(zqtj
                        .getXybgrq()));
                zqtj.setLentState("0");
                zqtj.setLentStateYj("0");
                Map<String, String> map = jyglManager.getZhglf(
                        Double.parseDouble(money) / 1000, id);
                zqtj.setZhglf(Double.parseDouble(map.get("zhglf")));
                zqtj.setFwfl(Double.parseDouble(map.get("lilv")));
                zqtj.setZhjb(map.get("jibie"));
            }
        }
        XhLentmoneywater xhLentmoneywater = new XhLentmoneywater();// 资金流水

        zqtj.setState(state);

        // jyglManager.saveXhZqtj(zqtj);//保存推荐表
        String num = "";
        String qishu = "";
        String qishuOld = "";

        String message;
        String recode = "200";
        double count = 0.0;
        System.out.println("mateIds===>" + mateIds);
        if (mateIds != null && !mateIds.equals("")) {
            String[] mateId = mateIds.split(",");
            zqtjd = new XhZqtjDetails[mateId.length];
            for (int i = 0; i < mateId.length; i++) {
                num = request.getParameter("num" + mateId[i]);
                // numOld = request.getParameter("numOld" + mateId[i]);
                // qishu = request.getParameter("qishu" + mateId[i]);
                qishuOld = request.getParameter("qishuOld" + mateId[i]);
                qishu = qishuOld;
                /*
                 * 只要不是不定期的，取出借期数和借款期数小的那个 if(!LAST_CJZQ.equals("-1")){
                 * if(Integer.parseInt(qishu) > Integer.parseInt(LAST_CJZQ)){
                 * qishu = LAST_CJZQ; } }
                 */
                count = count + Double.parseDouble(num) * 100000;
                zqtjds = new XhZqtjDetails();// 推荐明细
                zqtjds.setHkzq(qishu);
                zqtjds.setHkzqSy(qishu);
                // zqtjds.setXhZqtj(zqtj);
                zqtjds.setMoney(Double.parseDouble(num));
                zqtjds.setMoneySy(Double.parseDouble(num));
                zqtjds.setKyzqjzId(Long.parseLong(mateId[i]));
                // zqtjds.setZqcybi((Double.parseDouble(num)/Double.parseDouble(numOld))*100);
                // jyglManager.saveXhZqtjDetails(zqtjds);
                zqtjd[i] = zqtjds;
                // System.out.println("num===>" + num + ";qishu===>" + qishu+
                // ";Zqcybi===>" + zqtjds.getZqcybi());
            }
            zqtj.setMoney(count / 100000);

            try {
                if (state.equals("0")) {
                    if (Double.parseDouble(money) >= count / 100000) {
                        // 资金流水
                        // xhLentmoneywater.setMoney(0-Double.parseDouble(money));
                        xhLentmoneywater.setXhTzsq(tzsq);
                        // xhTzsqManager.saveXhLentmoneywater(xhLentmoneywater);
                        jyglManager.saveTztj(zqtj_id, zqtj, zqtjd,
                                xhLentmoneywater);
                        message = "保存成功";
                    } else {
                        message = "保存失败，推荐额度总和不能大于未推荐金额";
                        recode = "300";
                    }
                } else {
                    if (Double.parseDouble(money) == count / 100000) {
                        // 资金流水
                        // xhLentmoneywater.setMoney(0-Double.parseDouble(money));
                        xhLentmoneywater.setXhTzsq(tzsq);
                        // xhTzsqManager.saveXhLentmoneywater(xhLentmoneywater);
                        jyglManager.saveTztj(zqtj_id, zqtj, zqtjd,
                                xhLentmoneywater);
                        message = "提交成功";
                    } else {
                        message = "提交失败，推荐额度总和必须等于未推荐金额";
                        recode = "300";
                    }
                }
            } catch (Exception e) {
                message = "保存失败，可用债权余额不足";
                recode = "300";
            }
        } else {
            message = "保存失败，没有添加可用债权";
            recode = "300";
        }
        String lenjhtzrq_href = request.getParameter("lenjhtzrq_href");
        request.getSession().setAttribute("lenjhtzrq_href", lenjhtzrq_href);
        String backcount_href = request.getParameter("backcount_href");
        request.getSession().setAttribute("backcount_href", backcount_href);

        String tzcp_href = request.getParameter("tzcp_href");
        request.getSession().setAttribute("tzcp_href", tzcp_href);
        String zdr_href = request.getParameter("zdr_href");
        request.getSession().setAttribute("zdr_href", zdr_href);
        String rel = "";
        // if(zqtj.getLentState().equals("0")){
        // rel = "rel_jyglZqtjSq";
        // }else{
        // rel = "rel_jyglZqtjFsq";
        // }
        rel = "rel_jyglZqtj";
        DwzResult success = new DwzResult(recode, message, rel, "",
                "closeCurrent", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 债权推荐-弹出选择页
     */
    @RequestMapping(value = "/findZqtj")
    public String findZqtj(HttpServletRequest request, Model model) {
        // 处理分页的参数
        JdbcPage page = JdbcPageUtils.generatePage(request);

        Map<String, Object> map = ServletUtils.getParametersStartingWith2(
                request, "filter_");
        String MONEY = request.getParameter("MONEY");
        String LAST_CJZQ = request.getParameter("LAST_CJZQ");
        String mateid = request.getParameter("mateid");
        String ZDR = request.getParameter("ZDR");
        String TZCP_ID = request.getParameter("TZCP_ID");
        String selFlag = request.getParameter("selFlag");
        String TZSQ_ID = request.getParameter("TZSQ_ID");
        Tzcp t = baseInfoManager.getCp(Long.parseLong(TZCP_ID));

        String zjrId = request.getParameter("zhongjianren.id");
        String middleManName = request.getParameter("zhongjianren.name");
        if (zjrId != null && !zjrId.equals("")) {
            map.put("zjrId", Long.parseLong(zjrId));
        }
        if (middleManName != null && !middleManName.equals("")) {
            map.put("middleManName", middleManName);
        }
        map.put("MONEY", MONEY);
        map.put("ZDR", ZDR);
        map.put("TZCP_ID", TZCP_ID);
        map.put("TZCP_MC", t.getTzcpMc());
        map.put("LAST_CJZQ", LAST_CJZQ);
        map.put("TZSQ_ID", TZSQ_ID);
        if (mateid != null && !mateid.equals("")) {
            map.put("mateid", mateid);
        }
        if (null == selFlag || "".equals(selFlag)) {
            map.put("hkr", ZDR);
        }
        // if(map.get("sytjjeMin") == null ||
        // map.get("sytjjeMin").toString().equals("")){
        // map.put("sytjjeMin", MONEY);
        // }
        // if(map.get("syhkysMin") == null ||
        // map.get("syhkysMin").toString().equals("")){
        // if(!map.get("LAST_CJZQ").equals("-1")){
        // map.put("syhkysMin", LAST_CJZQ);
        // }
        // }
        List<Map<String, Object>> listZqtj = jyglManager
                .queryXhAvailableValueOfClaims(
                        "queryXhAvailableValueOfClaimsList", map, page);
        // List<XhAvailableValueOfClaims> list =
        // jyglManager.queryXhAvailableValueOfClaims(map);
        // 贷款利率
        List<MateData> dkllMateDateList = baseInfoManager
                .getTypeByCode("10003");
        model.addAttribute("dkllList", dkllMateDateList);
        model.addAttribute("page", page);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        return "jygl/lookZqtj";
    }

    /**
     * 债权查询-列表页
     */
    @RequestMapping(value = "/jyglZqcx")
    public String jyglZqcx(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }

        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtj(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqcx";
    }

    /**
     * 债权查询-查看页
     */
    @RequestMapping(value = "/lookZqtj/{Id}", method = RequestMethod.GET)
    public ModelAndView lookZqtj(@PathVariable Long Id, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        map = new HashMap<String, Object>();
        map.put("zqtj_id", Id + "");
        List<Map<String, Object>> listXhZqtjDetails = jyglManager
                .findXhZqtjDetails("queryXhZqtjDetailsList", map);
        model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);
        return new ModelAndView("jygl/zqcx-input", "xhZqtj", xhZqtj);
    }

    /**
     * 债权推荐-审批列表页
     */
    @RequestMapping(value = "/jyglZqtjsp")
    public String listSp(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }

        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        String state = (String) request.getSession().getAttribute("state_href");
        if (StringUtils.isNotEmpty(state)) {
            request.getSession().removeAttribute("state_href");
            map.put("state", state);
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        // 状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
        if (map.containsKey("state")) {
            String value = String.valueOf(map.get("state"));
            if (StringUtils.isNotEmpty(value)) {
                map.put("state", value);
            } else {
                map.put("state", "1");
            }
        } else {
            map.put("state", "1");
        }
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtj(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqtjsp";
    }

    /**
     * 债权推荐-审批页
     */
    @RequestMapping(value = "/editZqtjsp/{Id}", method = RequestMethod.GET)
    public ModelAndView editZqtjsp(@PathVariable Long Id, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        map = new HashMap<String, Object>();
        map.put("zqtj_id", Id + "");
        List<Map<String, Object>> listXhZqtjDetails = jyglManager
                .findXhZqtjDetails("queryXhZqtjDetailsList", map);
        model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);

        if (xhZqtj.getLentState().equals("1")) {
            map = new HashMap<String, Object>();
            map.put("cjrxx_id", xhZqtj.getXhTzsq().getId() + "");
            List<Map<String, Object>> listXhLentmoneywater = jyglManager
                    .findXhZqtjDetails("queryXhLentmoneywater", map);
            model.addAttribute("listXhLentmoneywater", listXhLentmoneywater);
        }

        return new ModelAndView("jygl/zqtjsp-input", "xhZqtj", xhZqtj);
    }

    /**
     * 债权推荐-审批
     */
    @RequestMapping(value = "/saveZqtjsp", method = RequestMethod.POST)
    public String saveZqtjsp(@ModelAttribute("xhZqtj") XhZqtj xhZqtj,
            HttpServletRequest request, HttpServletResponse response) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        String mess = "";
        // 审批通过
        if (xhZqtj.getState().equals("2")) {
            XhZqtj xhZqtjT = jyglManager.getXhZqtj(xhZqtj.getId());
            XhTzsq xhTzsq = jyglManager.getXhTzsq(xhZqtjT.getXhTzsq().getId());
            // 如果是首期，则计算下一报告日总资产和应还款
            Map<String, Object> map = new HashMap<String, Object>();
            map = new HashMap<String, Object>();
            map.put("zqtj_id", xhZqtj.getId());
            List<Map<String, Object>> listXhZqtjDetails = jyglManager
                    .findXhZqtjDetails("queryXhZqtjDetailsList", map);
            int size = listXhZqtjDetails.size();
            double[] moneys = new double[size];
            double[] lilvs = new double[size];
            int[] zqs = new int[size];
            int f = 0;
            for (Map<String, Object> m : listXhZqtjDetails) {
                moneys[f] = Double.parseDouble(m.get("MONEY") + "");
                // zqs[f] = Integer.parseInt(m.get("HKZQ")+"");
                // zqs[f] = Integer.parseInt(m.get("SYTJJE")+"");
                zqs[f] = Integer.parseInt(m.get("SYHKYS") + "");
                lilvs[f] = Double.parseDouble(m.get("DKLL") + "");
                f++;
            }
            if (xhZqtjT.getLentState().equals("0")) {
                // 应收利息金额合计
                double firstInterest = CreditHarmonyComputeUtilties
                        .getSumFirstInterest(lilvs, moneys, xhZqtjT.getJhtzrq());
                // 月回款本金合计
                double backPrincipal = CreditHarmonyComputeUtilties
                        .getSumBackPrincipal(zqs, moneys);
                xhZqtjT.setXybgqjkryhke(firstInterest + backPrincipal);
                xhZqtjT.setYjxybgrzcze(xhZqtjT.getMoney() + firstInterest);
            } else {
                // if(xhTzsq.getLastCjzq().equals("0")){
                // //如果期数是0，则补首期天数利息
                // //应收利息金额合计
                // double firstInterest =
                // CreditHarmonyComputeUtilties.getSumOverInterest(lilvs,
                // moneys,
                // xhZqtjT.getJhtzrq(),Integer.parseInt(xhTzsq.getFirstdate()));
                // //月回款本金合计
                // double backPrincipal =
                // CreditHarmonyComputeUtilties.getSumBackPrincipal(zqs,
                // moneys);
                // xhZqtjT.setXybgqjkryhke(firstInterest+backPrincipal);
                // //此处为了方便结算，非首期只存利息
                // xhZqtjT.setYjxybgrzcze(firstInterest);
                // }else{
                // 应收利息金额合计
                double firstInterest = CreditHarmonyComputeUtilties
                        .getSumOtherInterest(lilvs, moneys);
                // 月回款本金合计
                double backPrincipal = CreditHarmonyComputeUtilties
                        .getSumBackPrincipal(zqs, moneys);
                xhZqtjT.setXybgqjkryhke(firstInterest + backPrincipal);
                // 此处为了方便结算，非首期只存利息
                xhZqtjT.setYjxybgrzcze(firstInterest);
                // }
            }
            xhZqtjT.setState("2");
            xhZqtjT.setStatedg("0");
            if (xhTzsq.getLastCjzq().equals("0")) {
                xhTzsq.setOverstate("1");
                jyglManager.saveXhTzsq(xhTzsq);
            }
            jyglManager.saveXhZqtj(xhZqtjT);
            mess = "审批通过成功";
        } else {
            jyglManager.deleteZqtjMessage(xhZqtj.getId());
            mess = "审批不通过成功";
        }
        DwzResult success = new DwzResult("200", mess, "rel_jyglZqtjsp", "",
                "closeCurrent", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 债权推荐-批量审批
     */
    @RequestMapping(value = "/saveZqspPl", method = RequestMethod.POST)
    public String saveZqspPl(HttpServletRequest request,
            HttpServletResponse response) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        String statedg_href = request.getParameter("state_href");
        request.getSession().setAttribute("state_href", statedg_href);
        String ids = request.getParameter("ids");
        String[] Ids = ids.split(",");
        String id = "";
        XhZqtj xhZqtjT;
        for (int i = 0; i < Ids.length; i++) {
            id = Ids[i];
            xhZqtjT = jyglManager.getXhZqtj(Long.parseLong(id));
            XhTzsq xhTzsq = jyglManager.getXhTzsq(xhZqtjT.getXhTzsq().getId());
            // 如果是首期，则计算下一报告日总资产和应还款
            Map<String, Object> map = new HashMap<String, Object>();
            map = new HashMap<String, Object>();
            map.put("zqtj_id", xhZqtjT.getId());
            List<Map<String, Object>> listXhZqtjDetails = jyglManager
                    .findXhZqtjDetails("queryXhZqtjDetailsList", map);
            int size = listXhZqtjDetails.size();
            double[] moneys = new double[size];
            double[] lilvs = new double[size];
            int[] zqs = new int[size];
            int f = 0;
            for (Map<String, Object> m : listXhZqtjDetails) {
                moneys[f] = Double.parseDouble(m.get("MONEY") + "");
                // zqs[f] = Integer.parseInt(m.get("HKZQ")+"");
                // zqs[f] = Integer.parseInt(m.get("SYTJJE")+"");
                zqs[f] = Integer.parseInt(m.get("SYHKYS") + "");
                lilvs[f] = Double.parseDouble(m.get("DKLL") + "");
                f++;
            }
            if (xhZqtjT.getLentState().equals("0")) {
                // 应收利息金额合计
                double firstInterest = CreditHarmonyComputeUtilties
                        .getSumFirstInterest(lilvs, moneys, xhZqtjT.getJhtzrq());
                // 月回款本金合计
                double backPrincipal = CreditHarmonyComputeUtilties
                        .getSumBackPrincipal(zqs, moneys);
                xhZqtjT.setXybgqjkryhke(firstInterest + backPrincipal);
                xhZqtjT.setYjxybgrzcze(xhZqtjT.getMoney() + firstInterest);
            } else {
                // if(xhTzsq.getLastCjzq().equals("0")){
                // //如果期数是0，则补首期天数利息
                // //应收利息金额合计
                // double firstInterest =
                // CreditHarmonyComputeUtilties.getSumOverInterest(lilvs,
                // moneys,
                // xhZqtjT.getJhtzrq(),Integer.parseInt(xhTzsq.getFirstdate()));
                // //月回款本金合计
                // double backPrincipal =
                // CreditHarmonyComputeUtilties.getSumBackPrincipal(zqs,
                // moneys);
                // xhZqtjT.setXybgqjkryhke(firstInterest+backPrincipal);
                // //此处为了方便结算，非首期只存利息
                // xhZqtjT.setYjxybgrzcze(firstInterest);
                // }else{
                // 应收利息金额合计
                double firstInterest = CreditHarmonyComputeUtilties
                        .getSumOtherInterest(lilvs, moneys);
                // 月回款本金合计
                double backPrincipal = CreditHarmonyComputeUtilties
                        .getSumBackPrincipal(zqs, moneys);
                xhZqtjT.setXybgqjkryhke(firstInterest + backPrincipal);
                // 此处为了方便结算，非首期只存利息
                xhZqtjT.setYjxybgrzcze(firstInterest);
                // }
            }
            xhZqtjT.setState("2");
            xhZqtjT.setStatedg("0");
            if (xhTzsq.getLastCjzq().equals("0")) {
                xhTzsq.setOverstate("1");
                jyglManager.saveXhTzsq(xhTzsq);
            }
            jyglManager.saveXhZqtj(xhZqtjT);
        }

        DwzResult success = new DwzResult("200", "批量审批成功", "rel_jyglZqtjsp",
                "", "", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 债权推荐-撤销
     */
    @RequestMapping(value = "/reBack", method = RequestMethod.POST)
    public String reBack(HttpServletRequest request,
            HttpServletResponse response) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        String statedg = request.getParameter("statedg_href");
        request.getSession().setAttribute("statedg_href", statedg);
        String id = request.getParameter("id");
        String flag = request.getParameter("flag");
        DwzResult success = new DwzResult("200", "撤销成功", "rel_jyglZqdg", "",
                "", "");
        ;
        jyglManager.reBackZqtjMessage(Long.parseLong(id));
        if (flag.equals("jg")) {
            success = new DwzResult("200", "撤销成功", "rel_jyglZqjg", "", "", "");
        }
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 债权交割
     */
    @RequestMapping(value = "/jyglZqjg")
    public String jyglZqjg(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }
        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        String statedg = (String) request.getSession().getAttribute(
                "statedg_href");
        System.out.println("statedg   list=====>" + statedg);
        if (StringUtils.isNotEmpty(statedg)) {
            request.getSession().removeAttribute("statedg_href");
            map.put("statedg", statedg);
        } else {
            if (!map.containsKey("statedg") && !map.containsKey("subFlag")) {
                map.put("statedg", "1");
            }
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        // 订购标记0未订购,1已订购，2已交割
        /*
         * if(map.containsKey("statedg")){ String value =
         * String.valueOf(map.get("statedg")); if(StringUtils.isNotEmpty(value))
         * { map.put("statedg", value); }else{ map.put("statedg", "1"); } }else{
         * map.put("statedg", "1"); }
         */
        map.put("state", "2");
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtj(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqjg";
    }
    
    /**
     * 债权发送
     */
    
    @RequestMapping(value = "/jyglZqfs")
    public String jyglZqfs(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }
        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        String statedg = (String) request.getSession().getAttribute(
                "statedg_href");
        System.out.println("statedg   list=====>" + statedg);
        if (StringUtils.isNotEmpty(statedg)) {
            request.getSession().removeAttribute("statedg_href");
            map.put("statedg", statedg);
        } else {
            if (!map.containsKey("statedg") && !map.containsKey("subFlag")) {
                map.put("statedg", "1");
            }
        }
        
        map.put("state", "2");
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtj(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqfs";
    }

    /**
     * 交割
     */
    @RequestMapping(value = "/saveZqjg")
    public String saveZqjg(HttpServletRequest request, Model model,
            HttpServletResponse response) {
        String statedg = request.getParameter("statedg_href");
        System.out.println("statedg=====>" + statedg);
        // request.setAttribute("filter_khbm", map.get("khbm"));
        request.getSession().setAttribute("statedg_href", statedg);
        String id = request.getParameter("id");
        XhZqtj xhZqtjT = jyglManager.getXhZqtj(Long.parseLong(id));
        xhZqtjT.setStatedg("2");
        jyglManager.saveXhZqtj(xhZqtjT);
        DwzResult success = new DwzResult("200", "交割成功", "rel_jyglZqjg", "",
                "", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 批量交割
     */
    @RequestMapping(value = "/saveZqjgPl")
    public String saveZqjgPl(HttpServletRequest request,
            HttpServletResponse response) {
        String statedg = request.getParameter("statedg_href");
        System.out.println("statedg=====>" + statedg);
        // request.setAttribute("filter_khbm", map.get("khbm"));
        request.getSession().setAttribute("statedg_href", statedg);
        String ids = request.getParameter("ids");
        String[] Ids = ids.split(",");
        String id = "";
        XhZqtj xhZqtjT;
        for (int i = 0; i < Ids.length; i++) {
            id = Ids[i];
            xhZqtjT = jyglManager.getXhZqtj(Long.parseLong(id));
            xhZqtjT.setStatedg("2");
            jyglManager.saveXhZqtj(xhZqtjT);
        }

        DwzResult success = new DwzResult("200", "交割成功", "rel_jyglZqjg", "",
                "", "");
        ServletUtils.renderJson(response, success);
        return null;
    }
    
    /**
     * 批量发送
     * @param Id
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveMailPl")
    public String saveMailPl(HttpServletRequest request,HttpServletResponse response){
        
        String ids = request.getParameter("ids");
        String[] Ids = ids.split(",");
        String id = "";
        for (int i = 0; i < Ids.length; i++) {
            id = Ids[i];
             XhZqtj xhZqtj = jyglManager.getXhZqtj(Long.parseLong(id));
             xhZqtj.setBillSendState("1");
             jyglManager.saveXhZqtj(xhZqtj);
             jyglManager.saveXhSendemail(id);
        }

        DwzResult success = new DwzResult("200", "发送成功", "rel_jyglZqfs", "",
                "", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    @RequestMapping(value = "/lookZqjg/{Id}", method = RequestMethod.GET)
    public String lookZqjg(@PathVariable Long Id, Model model) {
        return "jygl/zqjg-look";
    }

    /**
     * 债权订购-列表
     */
    @RequestMapping(value = "/jyglZqdg")
    public String jyglZqdg(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }
        String statedg = (String) request.getSession().getAttribute(
                "statedg_href");
        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        if (StringUtils.isNotEmpty(statedg)) {
            request.getSession().removeAttribute("statedg_href");
            map.put("statedg", statedg);
        } else {
            if (!map.containsKey("statedg") && !map.containsKey("subFlag")) {
                map.put("statedg", "0");
            }
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        // 订购标记0未订购,1已订购，2已交割
        /*
         * if(map.containsKey("statedg")){ String value =
         * String.valueOf(map.get("statedg")); if(StringUtils.isNotEmpty(value))
         * { map.put("statedg", value); }else{ map.put("statedg", "0"); } }else{
         * map.put("statedg", "0"); }
         */
        map.put("state", "2");
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtj(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqdg";
    }

    /**
     * 债权订购-查看
     */
    @RequestMapping(value = "/lookZqdg", method = RequestMethod.GET)
    public ModelAndView lookZqdg(HttpServletRequest request, Model model) {
        Long Id = Long.parseLong(request.getParameter("id"));
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        map = new HashMap<String, Object>();
        map.put("zqtj_id", Id + "");
        List<Map<String, Object>> listXhZqtjDetails = jyglManager
                .findXhZqtjDetails("queryXhZqtjDetailsList", map);
        model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);

        String statedg_href = request.getParameter("statedg_href");
        model.addAttribute("statedg_href", statedg_href);

        if ((value.get("LENT_STATE") + "").equals("0")) {
            return new ModelAndView("jygl/zqdgSq-look", "xhZqtj", xhZqtj);
        } else {
            map = new HashMap<String, Object>();
            map.put("cjrxx_id", xhZqtj.getXhTzsq().getId() + "");
            List<Map<String, Object>> listXhLentmoneywater = jyglManager
                    .findXhZqtjDetails("queryXhLentmoneywater", map);
            model.addAttribute("listXhLentmoneywater", listXhLentmoneywater);
            return new ModelAndView("jygl/zqdgFsq-look", "xhZqtj", xhZqtj);
        }
    }

    // 生成首期订购合同
    @RequestMapping(value = "/dgZqtjsq")
    public void dgZqtjsq(HttpServletRequest request, Model model,
            HttpServletResponse response) {
        DwzResult success = null;
        String first = null;
        Object wordgenerate = null;
        try {
            /*
             * synchronized(countLock){ wordgenerate =
             * context.getAttribute("wordgenerate"); if ( wordgenerate != null)
             * { success = new DwzResult("300", "债权制作进程被占用，请稍候重新制作,", "", "",
             * "", ""); ServletUtils.renderJson(response, success); return ; }
             * first = "not"; context.setAttribute("wordgenerate",
             * request.getSession().getId()); }
             */
            String statedg_href = request.getParameter("statedg_href");
            request.getSession().setAttribute("statedg_href", statedg_href);
            String id = request.getParameter("id");
            
            String jhtzrq = request.getParameter("jhtzrq");
            String creTime = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            jyglManager.sqZqHt(id, jhtzrq,creTime);
            String time = baseInfoManager.waiteTime("xh_zqtj");
            String mess = "订购成功,预计需要" + time + "分钟可以全部制作完成。";
            success = new DwzResult("200", mess, "rel_jyglZqdg", "",
                    "closeCurrent", "");
            ServletUtils.renderJson(response, success);
        } finally {
            /*
             * if("not".equals(first)){ context.removeAttribute("wordgenerate");
             * }
             */
        }
    }

    // 生成非首期订购合同
    @RequestMapping(value = "/dgZqtjfsq")
    public void dgZqtjfsq(HttpServletRequest request, Model model,
            HttpServletResponse response) {
        DwzResult success = null;
        String first = null;
        Object wordgenerate = null;
        try {
            /*
             * synchronized(countLock){ wordgenerate =
             * context.getAttribute("wordgenerate"); if ( wordgenerate != null)
             * { success = new DwzResult("300", "债权制作进程被占用，请稍候重新制作,", "", "",
             * "", ""); ServletUtils.renderJson(response, success); return ; }
             * first = "not"; context.setAttribute("wordgenerate",
             * request.getSession().getId()); }
             */
            String statedg_href = request.getParameter("statedg_href");
            request.getSession().setAttribute("statedg_href", statedg_href);
            String id = request.getParameter("id");
            
            XhZqtj xhZqtj =jyglManager.getXhZqtj(Long.parseLong(id)); 
            xhZqtj.setAgreementMakeState("1");
            jyglManager.saveXhZqtj(xhZqtj);
            
            String jhtzrq = request.getParameter("jhtzrq");
            String creTime = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            jyglManager.fsqZqHt(id,creTime);
            String time = baseInfoManager.waiteTime("xh_zqtj");
            String mess = "订购成功,预计需要" + time + "分钟可以全部制作完成。";
            success = new DwzResult("200", mess, "rel_jyglZqdg", "",
                    "closeCurrent", "");
            ServletUtils.renderJson(response, success);
        } finally {
            /*
             * if("not".equals(first)){ context.removeAttribute("wordgenerate");
             * }
             */
        }
    }

    /**
     * 批量订购
     */
    @RequestMapping(value = "/saveZqdgPl")
    public void saveZqdgPl(HttpServletRequest request,
            HttpServletResponse response) {
        DwzResult success = null;
        String first = null;
        Object wordgenerate = null;
        try {
            synchronized (countLock) {
                wordgenerate = context.getAttribute("wordgenerate");
                if (wordgenerate != null) {
                    success = new DwzResult("300", "债权制作进程被占用，请稍候重新制作,", "",
                            "", "", "");
                    ServletUtils.renderJson(response, success);
                    return;
                }
                first = "not";
                context.setAttribute("wordgenerate", request.getSession()
                        .getId());
            }
            String statedg_href = request.getParameter("statedg_href");
            request.getSession().setAttribute("statedg_href", statedg_href);
            String ids = request.getParameter("ids");
            String[] Ids = ids.split(",");
            String id = "";
            XhZqtj xhZqtjT;
            String creTime = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < Ids.length; i++) {
                id = Ids[i];
                xhZqtjT = jyglManager.getXhZqtj(Long.parseLong(id));
//              xhZqtjT.setAgreementMakeState("1");
//              jyglManager.saveXhZqtj(xhZqtjT);
                if (xhZqtjT.getLentState().equals("0")) {
                    jyglManager.sqZqHtPl(xhZqtjT,creTime);
                } else {
                    jyglManager.fsqZqHtpl(xhZqtjT,creTime);
                }
            }
            String time = baseInfoManager.waiteTime("xh_zqtj");
            String mess = "订购成功,预计需要" + time + "分钟可以全部制作完成。";
            success = new DwzResult("200", mess, "rel_jyglZqdg", "", "", "");
            ServletUtils.renderJson(response, success);
        } finally {
            if ("not".equals(first)) {
                context.removeAttribute("wordgenerate");
            }
        }
    }

    @RequestMapping(value = "/saveDgcx")
    public String saveDgcx(HttpServletRequest request,
            HttpServletResponse response) {
        // String loginName = request.getParameter("loginName");
        // String oldpassword = request.getParameter("oldpassword");
        // String newpassword = request.getParameter("password");
        // String passWordMd5 =
        // EncodeUtils.getMd5PasswordEncoder(oldpassword,loginName);
        //
        // User u = accountManager.findUserByLoginName(loginName);
        DwzResult success = null;
        // if (u.getPassword().equals(passWordMd5)){
        // u.setPassword(EncodeUtils.getMd5PasswordEncoder(newpassword,loginName));
        // accountManager.saveUser(u);
        success = new DwzResult("200", "债权订购撤销成功！", "", "", "closeCurrent", "");
        // }
        // else{
        // success = new
        // DwzResult("300","原密码不正确，修改密码不成功！","","","closeCurrent","");
        // }
        //
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 可用债权价值列表
     */
    @RequestMapping(value = "/findKyZqjz")
    public String findKyZqjz(HttpServletRequest request, Model model) {

        // 处理分页的参数
        Page<XhAvailableValueOfClaims> page = new Page<XhAvailableValueOfClaims>();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }

        Map<String, Object> map = ServletUtils.getParametersStartingWith2(
                request, "filter_");

        jyglManager.queryXhAvailableValueOfClaims(page, map);

        model.addAttribute("page", page);
        model.addAttribute("map", map);
        return "jygl/lookKyZqjz";
    }

    @RequestMapping(value = "/suggestMiddleMan")
    public String suggestMiddleMan(@RequestParam String inputValue,
            HttpServletResponse response) {
        List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> listzjzh = jyglManager.findXhZqtj(
                "queryXhZjzhList", map);
        Map<String, Object> mapzjzh = new LinkedHashMap<String, Object>();
        for (Map<String, Object> m : listzjzh) {
            mapzjzh = new LinkedHashMap<String, Object>();
            mapzjzh.put("id", m.get("ID"));
            mapzjzh.put("name", m.get("MIDDLE_MAN_NAME"));
            list1.add(mapzjzh);
        }
        logger.info("suggest query is ok!");
        ServletUtils.renderJson(response, list1);
        return null;
    }

    /**
     * 下载对账文件(word格式)
     * 
     * @return
     */
    @RequestMapping(value = "/downFile")
    public ModelAndView getInputStream(HttpServletRequest request,
            HttpServletResponse response) {
        String LENT_STATE = request.getParameter("LENT_STATE");
        String id = request.getParameter("id");
        Long Id = Long.parseLong(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);

        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        String mouFilePath2 = InitSetupListener.filePath + "agaeeFile"
                + File.separator;
        String filePath = mouFilePath2 + "zq" + xhZqtj.getJhtzrq()
                + File.separator;
        String fileName = "";
        String tzsqbh = value.get("TZSQBH") + "";
        tzsqbh = tzsqbh.substring(tzsqbh.length() - 3, tzsqbh.length());
        if (LENT_STATE.equals("0")) {
            // fileName =
            // "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
            fileName = "首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh + "("
                    + id + ")" + xhZqtj.getJhtzrq() + ".doc";
        } else {
            // fileName =
            // "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
            fileName = "非首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh
                    + "(" + id + ")" + xhZqtj.getJhtzrq() + ".doc";
        }
        System.out.println("filePath===>" + filePath);
        String downLoadPath = filePath + fileName;

        FileUtil.downLoad(downLoadPath, fileName, request, response);
        /*
         * try { response.setContentType("text/html;charset=utf-8");
         * request.setCharacterEncoding("UTF-8"); java.io.BufferedInputStream
         * bis = null; java.io.BufferedOutputStream bos = null;
         * System.out.println(downLoadPath); try { long fileLength = new
         * File(downLoadPath).length();
         * response.setContentType("application/octet-stream; charset=utf-8");
         * response.setHeader("Content-disposition", "attachment; filename=" //
         * + new String(fileName.getBytes("UTF-8"), "GB2312")); +
         * URLEncoder.encode(fileName, "utf-8"));
         * response.setHeader("Content-Length", String.valueOf(fileLength)); bis
         * = new BufferedInputStream(new FileInputStream(downLoadPath)); bos =
         * new BufferedOutputStream(response.getOutputStream()); byte[] buff =
         * new byte[1024]; int bytesRead; while (-1 != (bytesRead =
         * bis.read(buff, 0, buff.length))) { bos.write(buff, 0, bytesRead); } }
         * catch (Exception e) { // e.printStackTrace(); } finally { if (bis !=
         * null) { bis.close(); } if (bos != null) { bos.close(); } } } catch
         * (UnsupportedEncodingException e) { e.printStackTrace(); } catch
         * (IOException e) { e.printStackTrace(); }
         */
        return null;

    }
    /**
     * 下载对账文件(pdf格式)
     * 
     * @return
     */
    @RequestMapping(value = "/downFilePDF")
    public ModelAndView getInputStreamPDF(HttpServletRequest request,
            HttpServletResponse response) {
        String LENT_STATE = request.getParameter("LENT_STATE"); 
        String id = request.getParameter("id");
        Long Id = Long.parseLong(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);

        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        String mouFilePath2 = InitSetupListener.filePath + "customerUse"
                + File.separator;
        String filePath = mouFilePath2 + "zq" + xhZqtj.getJhtzrq()
                + File.separator;
        String fileName = "";
        String tzsqbh = value.get("TZSQBH") + "";
        tzsqbh = tzsqbh.substring(tzsqbh.length() - 3, tzsqbh.length());
        if (LENT_STATE.equals("0")) {
            // fileName =
            // "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
            fileName = "首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh + "("
                    + id + ")" + xhZqtj.getJhtzrq() + ".pdf";
        } else {
            // fileName =
            // "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
            fileName = "非首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh
                    + "(" + id + ")" + xhZqtj.getJhtzrq() + ".pdf";
        }
        System.out.println("filePath===>" + filePath);
        String downLoadPath = filePath + fileName;

        FileUtil.downLoad(downLoadPath, fileName, request, response);
        /*
         * try { response.setContentType("text/html;charset=utf-8");
         * request.setCharacterEncoding("UTF-8"); java.io.BufferedInputStream
         * bis = null; java.io.BufferedOutputStream bos = null;
         * System.out.println(downLoadPath); try { long fileLength = new
         * File(downLoadPath).length();
         * response.setContentType("application/octet-stream; charset=utf-8");
         * response.setHeader("Content-disposition", "attachment; filename=" //
         * + new String(fileName.getBytes("UTF-8"), "GB2312")); +
         * URLEncoder.encode(fileName, "utf-8"));
         * response.setHeader("Content-Length", String.valueOf(fileLength)); bis
         * = new BufferedInputStream(new FileInputStream(downLoadPath)); bos =
         * new BufferedOutputStream(response.getOutputStream()); byte[] buff =
         * new byte[1024]; int bytesRead; while (-1 != (bytesRead =
         * bis.read(buff, 0, buff.length))) { bos.write(buff, 0, bytesRead); } }
         * catch (Exception e) { // e.printStackTrace(); } finally { if (bis !=
         * null) { bis.close(); } if (bos != null) { bos.close(); } } } catch
         * (UnsupportedEncodingException e) { e.printStackTrace(); } catch
         * (IOException e) { e.printStackTrace(); }
         */
        return null;

    }

    /**
     * 下载已划扣债权文件
     * 
     * @return
     */
    @RequestMapping(value = "/downOverJqFile")
    public ModelAndView getInputStreamYHK(HttpServletRequest request,
            HttpServletResponse response) {
        String LENT_STATE = request.getParameter("LENT_STATE");
        String id = request.getParameter("id");
        Long Id = Long.parseLong(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);

        String mouFilePath2 = InitSetupListener.filePath + "agaeeFile"
                + File.separator;
        String filePath = mouFilePath2 + "skqhs" + File.separator;
        String fileName = "";

        fileName = "收款确认书-" + value.get("CJRXM") + "-" + value.get("TZSQBH")
                + ".doc";

        System.out.println("filePath===>" + filePath);
        String downLoadPath = filePath + fileName;

        FileUtil.downLoad(downLoadPath, fileName, request, response);

        return null;

    }

    /**
     * 
     */
    @RequestMapping(value = "/madeSkqrs")
    public ModelAndView madeSkqrs(HttpServletRequest request,
            HttpServletResponse response) {
        String id = request.getParameter("id");
        Long Id = Long.parseLong(id);
        jyglManager.skres(Id);
        DwzResult success = new DwzResult("200", "提交成功", "", "", "", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 批量下载（word格式）
     */
    @RequestMapping(value = "/downFileMore")
    public  ModelAndView downFileMore(HttpServletRequest request,
            HttpServletResponse response) {

        String[] ids = request.getParameterValues("needDownWordId");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> listZqtj;
        
        int count = ids != null ? ids.length : 0;    
       
        String fileName = "";
        String LENT_STATE = "";
        String mouFilePath2 = InitSetupListener.filePath + "agaeeFile"
                + File.separator;
       // File[] srcfile = new File[count];
         File[] srcfile = new File[count];
      
        for (int i = 0; i < count; i++) {
            String id = ids[i];
            map.put("id", id);
            
            listZqtj = jyglManager.searchXhZqtjForDown(
                    "queryXhZqtjList", map);
            String filePath = mouFilePath2 + "zq"
                    + listZqtj.get(0).get("JHTZRQ") + File.separator;
            LENT_STATE = listZqtj.get(0).get("LENT_STATE") + "";
            String tzsqbh = listZqtj.get(0).get("TZSQBH") + "";
            tzsqbh = tzsqbh.substring(tzsqbh.length() - 3, tzsqbh.length());
            if (LENT_STATE.equals("0")) {
                // fileName =
                // "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
                fileName = "首期债权转让及受让协议-" + listZqtj.get(0).get("CJRXM") + "-"
                        + tzsqbh + "(" + id + ")"
                        + listZqtj.get(0).get("JHTZRQ") + ".doc";
            } else {
                // fileName =
                // "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
                fileName = "非首期债权转让及受让协议-" + listZqtj.get(0).get("CJRXM") + "-"
                        + tzsqbh + "(" + id + ")"
                        + listZqtj.get(0).get("JHTZRQ") + ".doc";
            }
            String downLoadPath = filePath + fileName;
            System.out.println("downLoadPath" + i + "===>" + downLoadPath);
            srcfile[i] = new File(downLoadPath);
        }
        String zipFileName = DBUUID.getID() + ".zip";
        String zipUrl = mouFilePath2 + zipFileName;
        File zipfile = new File(zipUrl);
        FileUtil.zipFiles(srcfile, zipfile);
       /*目标文件不改生成位置
        String destinationDirectory =  FileUtil.zipFiles(srcfile, zipfile);
        if(StringUtils.isNotEmpty(destinationDirectory) && !"nothing".equals(destinationDirectory)){
            zipUrl = destinationDirectory + zipUrl.substring(1);
        }*/
        FileUtil.downLoad(zipUrl, zipFileName, request, response);
        FileUtil.deleteFile(zipUrl);
        return null;
    }
    
    /**
     * 批量下载（PDF格式）
     */
    @RequestMapping(value = "/downFileMorePDF")
    public  ModelAndView downFileMorePDF(HttpServletRequest request,
            HttpServletResponse response) {
        String[] ids = request.getParameterValues("needDownPDFId");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> listZqtj;
        
        int count = ids != null ? ids.length : 0;    
       
        String fileName = "";
        String LENT_STATE = "";
        String mouFilePath2 = InitSetupListener.filePath + "customerUse"
                + File.separator;
       // File[] srcfile = new File[count];
         File[] srcfile = new File[count];
      
        for (int i = 0; i < count; i++) {
            String id = ids[i];
            map.put("id", id);
            
            listZqtj = jyglManager.searchXhZqtjForDown(
                    "queryXhZqtjList", map);
           // Map<String, Object> xhZqtjT = listZqtj.get(0);
            String filePath = mouFilePath2 + "zq"
                    + listZqtj.get(0).get("JHTZRQ") + File.separator;
            LENT_STATE = listZqtj.get(0).get("LENT_STATE") + "";
            String tzsqbh = listZqtj.get(0).get("TZSQBH") + "";
            tzsqbh = tzsqbh.substring(tzsqbh.length() - 3, tzsqbh.length());
            if (LENT_STATE.equals("0")) {
                // fileName =
                // "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
                fileName = "首期债权转让及受让协议-" + listZqtj.get(0).get("CJRXM") + "-"
                        + tzsqbh + "(" + id + ")"
                        + listZqtj.get(0).get("JHTZRQ") + ".pdf";
            } else {
                // fileName =
                // "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
                fileName = "非首期债权转让及受让协议-" + listZqtj.get(0).get("CJRXM") + "-"
                        + tzsqbh + "(" + id + ")"
                        + listZqtj.get(0).get("JHTZRQ") + ".pdf";
            }
            String downLoadPath = filePath + fileName;
            System.out.println("downLoadPath" + i + "===>" + downLoadPath);
            srcfile[i] = new File(downLoadPath);
        }
        String zipFileName = DBUUID.getID() + ".zip";
        String zipUrl = mouFilePath2 + zipFileName;
        File zipfile = new File(zipUrl);
        FileUtil.zipFiles(srcfile, zipfile);
        FileUtil.downLoad(zipUrl, zipFileName, request, response);
        FileUtil.deleteFile(zipUrl);
        return null;
    }


    /**
     * 已 划扣-列表
     */
    @RequestMapping(value = "/jyglOverHuakou")
    public String jyglOverHuakou(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }
        String statedg = (String) request.getSession().getAttribute(
                "statehk_href");
        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        if (StringUtils.isNotEmpty(statedg)) {
            request.getSession().removeAttribute("statehk_href");
            map.put("statedg", statedg);
        } else {
            if (!map.containsKey("statedg") && !map.containsKey("subFlag")) {
                map.put("statedg", "8");
            }
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        // 订购标记0未订购,1已订购，2已交割
        /*
         * if(map.containsKey("statedg")){ String value =
         * String.valueOf(map.get("statedg")); if(StringUtils.isNotEmpty(value))
         * { map.put("statedg", value); }else{ map.put("statedg", "0"); } }else{
         * map.put("statedg", "0"); }
         */
        map.put("state", "2");
        
        Map<String, Object> map2 = WebUtils.getParametersStartingWith(request,
                "filter_");
        map2.put("cjrState", "1");
        
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtjOver(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        
        List<Map<String,Object>> listYyb = jyglManager.findXhZqtjDetails("queryYybList", map2);
        model.addAttribute("listYyb", listYyb);
        
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-overzqhk";
    }

    /**
     * 债权划扣-列表
     */
    @RequestMapping(value = "/jyglHuakou")
    public String jyglHuakou(HttpServletRequest request, Model model) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        // 处理分页的参数
        JdbcPage page = new JdbcPage();
        String pageSize = request.getParameter("numPerPage");
        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }
        String pageNo = request.getParameter("pageNum");
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        String orderBy = request.getParameter("orderField");
        if (StringUtils.isNotBlank(orderBy)) {
            page.setOrderBy(orderBy);
        }
        String order = request.getParameter("orderDirection");
        if (StringUtils.isNotBlank(order)) {
            page.setOrder(order);
        }
        String statedg = (String) request.getSession().getAttribute(
                "statehk_href");
        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        if (StringUtils.isNotEmpty(statedg)) {
            request.getSession().removeAttribute("statehk_href");
            map.put("statedg", statedg);
        } else {
            if (!map.containsKey("statedg") && !map.containsKey("subFlag")) {
                map.put("statedg", "8");
            }
        }
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始
        // Employee emp = baseInfoManager.getUserEmployee();
        // String loginName = operator.getCtiCode();
        // map.put("loginName", loginName);
        // map.put("emp", emp);
        // 过滤查询内容，所需条件 ----结束
        // 订购标记0未订购,1已订购，2已交割
        /*
         * if(map.containsKey("statedg")){ String value =
         * String.valueOf(map.get("statedg")); if(StringUtils.isNotEmpty(value))
         * { map.put("statedg", value); }else{ map.put("statedg", "0"); } }else{
         * map.put("statedg", "0"); }
         */
        map.put("state", "2");
        List<Map<String, Object>> listZqtj = jyglManager.searchXhZqtj(
                "queryXhZqtjList", map, page);
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tzcpFl", "投资产品");
        List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
        request.setAttribute("tzcp", tzcp);
        model.addAttribute("listZqtj", listZqtj);
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        List<City> province = baseInfoManager.getSuggestCity("0");
        request.setAttribute("province", province);
        return "jygl/jygl-zqhk";
    }

    /**
     * 债权-划扣
     */
    @RequestMapping(value = "/lookZqhk", method = RequestMethod.GET)
    public ModelAndView lookZqhk(HttpServletRequest request, Model model) {
        Long Id = Long.parseLong(request.getParameter("id"));
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        map = new HashMap<String, Object>();
        map.put("zqtj_id", Id + "");
        List<Map<String, Object>> listXhZqtjDetails = jyglManager
                .findXhZqtjDetails("queryXhZqtjDetailsList", map);
        model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);

        String statehk_href = request.getParameter("statehk_href");
        model.addAttribute("statehk_href", statehk_href);

        return new ModelAndView("jygl/zqhk-input", "xhZqtj", xhZqtj);

    }

    /**
     * 债权推荐-划扣
     */
    @RequestMapping(value = "/saveZqtjhk", method = RequestMethod.POST)
    public String saveZqtjhk(@ModelAttribute("xhZqtj") XhZqtj xhZqtj,
            HttpServletRequest request, HttpServletResponse response) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        String mess = "";
        // 提交划扣
        if (xhZqtj.getStatedg().equals("1")) {
            // 7待马晨确认
            jyglManager.successHk(xhZqtj.getId(), "7","提交划扣成功","8");
            
            mess = "划扣成功";
        } else {
            jyglManager.deleteZqtjMessageByHk(xhZqtj.getId(),"提交划扣失败","81");
            mess = "划扣失败操作成功";
        }
        DwzResult success = new DwzResult("200", "操作保存成功", "rel_jyglHuakou",
                "", "closeCurrent", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 债权-划扣审批
     */
    @RequestMapping(value = "/lookHkSp", method = RequestMethod.GET)
    public ModelAndView lookHkSp(HttpServletRequest request, Model model) {
        Long Id = Long.parseLong(request.getParameter("id"));
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        map = new HashMap<String, Object>();
        map.put("zqtj_id", Id + "");
        List<Map<String, Object>> listXhZqtjDetails = jyglManager
                .findXhZqtjDetails("queryXhZqtjDetailsList", map);
        model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);

        String statehk_href = request.getParameter("statehk_href");
        model.addAttribute("statehk_href", statehk_href);

        return new ModelAndView("jygl/zqhksp-input", "xhZqtj", xhZqtj);

    }
    
    /**
     * 债权-批量划扣审批
     */
    @RequestMapping(value = "/lookHkSpSome", method = RequestMethod.POST)
    public ModelAndView lookHkSpSome(HttpServletRequest request,HttpServletResponse response, Model model) {
    
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        
        String statehk_href = request.getParameter("statehk_href");
//      request.getSession().setAttribute("statehk_href", statehk_href);
//      String statehk_href = request.getParameter("statehk_href");
//      model.addAttribute("statehk_href", statehk_href);
        String ids = request.getParameter("ids");
        System.out.println(ids);
        String[] Ids = ids.split(",");
        String id = "";
//      XhZqtj xhZqtj = null;
        for (int i = 0; i < Ids.length; i++) {
            id = Ids[i];
            
            jyglManager.successHk(Long.parseLong(id), "6","划扣审批成功","7");
                
        }
        DwzResult success = new DwzResult("200", "批量审批成功", "rel_jyglHuakou",
                "", "", "");
        ServletUtils.renderJson(response, success);
        return null;

    }

    /**
     * 债权推荐-划扣审批
     */
    @RequestMapping(value = "/saveZqtjhksp", method = RequestMethod.POST)
    public String saveZqtjhksp(@ModelAttribute("xhZqtj") XhZqtj xhZqtj,
            HttpServletRequest request, HttpServletResponse response) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        String mess = "";
        // 提交划扣
        if (xhZqtj.getStatedg().equals("1")) {
            // 6结算划扣
            jyglManager.successHk(xhZqtj.getId(), "6","划扣审批成功","7");
            
            mess = "划扣成功";
        } else {
            jyglManager.deleteZqtjMessageByHk(xhZqtj.getId(),"划扣审批失败","71");
            mess = "划扣失败操作成功";
        }
        DwzResult success = new DwzResult("200", "操作保存成功", "rel_jyglHuakou",
                "", "closeCurrent", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    /**
     * 债权-划扣审批终审
     */
    @RequestMapping(value = "/lookHkSpEnd", method = RequestMethod.GET)
    public ModelAndView lookHkSpEnd(HttpServletRequest request, Model model) {
        Long Id = Long.parseLong(request.getParameter("id"));
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return new ModelAndView("redirect:../login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);
        model.addAttribute("value", value);
        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        map = new HashMap<String, Object>();
        map.put("zqtj_id", Id + "");
        List<Map<String, Object>> listXhZqtjDetails = jyglManager
                .findXhZqtjDetails("queryXhZqtjDetailsList", map);
        model.addAttribute("listXhZqtjDetails", listXhZqtjDetails);

        String statehk_href = request.getParameter("statehk_href");
        model.addAttribute("statehk_href", statehk_href);

        return new ModelAndView("jygl/zqhkspEnd-input", "xhZqtj", xhZqtj);

    }

    /**
     * 债权推荐-划扣审批终审
     */
    @RequestMapping(value = "/saveZqtjhkspEnd", method = RequestMethod.POST)
    public String saveZqtjhkspEnd(@ModelAttribute("xhZqtj") XhZqtj xhZqtj,
            HttpServletRequest request, HttpServletResponse response) {
        // 得到当前登录用户
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        if (operator == null) {
            return "redirect:../login";
        }
        String mess = "";
        String sjhkrq = request.getParameter("sjhkrq");
        
        // 提交划扣
        if (xhZqtj.getStatedg().equals("1")) {
            // 6结算划扣
            jyglManager.sussHuaKouSingle(xhZqtj.getId(), sjhkrq,"结算划扣成功","6");
            mess = "划扣成功";
        } else {
            jyglManager.deleteZqtjMessageByHk(xhZqtj.getId(),"结算划扣失败","61");
            mess = "划扣失败操作成功";
        }
        DwzResult success = new DwzResult("200", mess, "rel_jyglHuakou",
                "", "closeCurrent", "");
        ServletUtils.renderJson(response, success);
        return null;
    }

    // ---------------------------申请单导出、打印--------------------
    /**
     * 导出工程立项信息
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportSqTjHk")
    public ModelAndView exportSqTjHk(HttpServletRequest request,
            HttpServletResponse response){
        // 得到当前登录用户 searchXhZqtjForDown
        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
        String mouFilePath2 = InitSetupListener.filePath + "excel"
                + File.separator;
        Map<String, Object> map = WebUtils.getParametersStartingWith(request,
                "filter_");
        // Map<String, Object> map =
        // ServletUtils.getParametersStartingWith2(request, "filter_");
        // 过滤查询内容，所需条件 ----开始

        // 过滤查询内容，所需条件 ----结束
        /*
         * List<Map<String,Object>> listTzsq =
         * xhTzsqManager.searchXhTzsq("queryXhTzsqList", map); String path =
         * xhTzsqManager.exportProref(filePath, listTzsq);
         * 000191400200580_S0220130802_00001好易联
         */
        String fileName = "";
        String filePath = "";
        Date createTime = new Date();
        /*
        String gsdq = String.valueOf(map.get("gsdq"));
        if ("0021".equals(gsdq)) {
            fileName = "000191400200580_S022"
                    + DateUtils.format(createTime, "yyyyMMdd") + "_0001好易联.xls";
        } else {
        */
            fileName = "AC01_" + DateUtils.format(createTime, "yyyyMMdd")
                    + "_0001富有模板.xls";
        //}
        filePath = mouFilePath2 + fileName;
        jyglManager.exportProref(filePath, map);
        response.setContentType("APPLICATION/OCTET-STREAM");

        FileUtil.downLoad(filePath, fileName, request, response);
        System.out.println("删除单个文件===>" + filePath);
        FileUtil.deleteFile(filePath);
        System.out.println("删除单个文件   成功===>" + filePath);

        return null;
    }

    /**
     * 通过上传文件批量 放款
     * 
     * @param request
     * @author xjs
     * @date 2013-8-10 下午2:36:44
     */
    @RequestMapping(value = "/uploadHkSp")
    public synchronized void uploadHkSp(HttpServletRequest request,
            HttpServletResponse response) {
        String gsdq = request.getParameter("gsdq");
        System.out.println("gsdq==>" + gsdq);
        List<ErrorInfosBean> errors = new ArrayList<ErrorInfosBean>();
        String currentUser = baseInfoManager.getUserEmployee().getName();
        List<Map<String, Object>> listValue = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        int count = 0;
        List<String[]> list = new ArrayList<String[]>();
        ;
        if ("0021".equals(gsdq)) {

        } else {
            list = ExportExcelAndImportExcel.readFile(request, "", 1, -1, 12);
            for (String[] s : list) {
                map = new HashMap<String, Object>();
                map.put("id", s[1]);
                map.put("money", s[5]);
                map.put("statedg", "6");
                map.put("result", s[9]);
                map.put("sjhkrq", s[11]);
                listValue.add(map);
            }
        }
        int suc = jyglManager.reSussHuaKou(listValue);
        BackInfo backInfo = new BackInfo();
        backInfo.setCount(suc);
        backInfo.setErrors(errors);
        ServletUtils.renderJson(response, backInfo);
    }

    private class BackInfo {
        private int count;
        List<ErrorInfosBean> errors;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ErrorInfosBean> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorInfosBean> errors) {
            this.errors = errors;
        }

    }

    private enum ErrorEnum {
        SUCCESS, ACCOUNT_ERROR {

            @Override
            public String toString() {
                return "请核对放款人账户信息";
            }
        },
        ROWEXCEPTION {

            @Override
            public String toString() {
                return "读入行出现错误，请手工放款该条数据";
            }
        },
        DATEEXCEPTION {

            @Override
            public String toString() {
                return "请核对放款日期";
            }
        }
    }

    private class ErrorInfosBean {
        private String id;
        private String errorMsg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }
    // ---------------------------申请单导出、打印 结束--------------------
    /**
     * 查看协议报告
     */
    @RequestMapping(value="/lookZqdgReport",method=RequestMethod.GET)
    public ModelAndView lookZqdgReport(HttpServletRequest request,
                        HttpServletResponse response ,Model model){
        String LENT_STATE = request.getParameter("LENT_STATE");
        String id = request.getParameter("id");
        Long Id = Long.parseLong(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Id);
        List<Map<String, Object>> listTzsq = jyglManager.findXhZqtj(
                "queryXhZqtjList", map);
        Map<String, Object> value = listTzsq.get(0);

        XhZqtj xhZqtj = jyglManager.getXhZqtj(Id);
        String fileName = "";
        String tzsqbh = value.get("TZSQBH") + "";
        tzsqbh = tzsqbh.substring(tzsqbh.length() - 3, tzsqbh.length());
        if (LENT_STATE.equals("0")) {
            // fileName =
            // "首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
            fileName = "首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh + "("
                    + id + ")" + xhZqtj.getJhtzrq() + ".swf";
        } else {
            // fileName =
            // "非首期债权转让及受让协议-"+value.get("CJRXM")+"-"+xhZqtj.getJhtzrq()+"-"+value.get("TZSQBH")+".doc";
            fileName = "非首期债权转让及受让协议-" + value.get("CJRXM") + "-" + tzsqbh
                    + "(" + id + ")" + xhZqtj.getJhtzrq() + ".swf";
        }
        
        //${fp}/customerUse/${filePath}/${fileName}
        String filePath = "zq" + xhZqtj.getJhtzrq();
        String swffilePath = InitSetupListener.filePath + "customerUse" + File.separator + filePath + File.separator + fileName;
        File swfFile = new File(swffilePath);
        if(!swfFile.exists()){
            model.addAttribute("swfServer",InitSetupListener.swfBackServer);
        }else{
            model.addAttribute("swfServer",InitSetupListener.swfServer);
        }    
        request.setAttribute("filePath", filePath);
        System.out.println("filePath===>" + filePath + "+"+fileName);
        return new ModelAndView("jygl/jygl-lookZqdgReport","fileName",fileName);
    }
}
