package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhJksqOffice;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqOfficeDao extends HibernateDao<XhJksqOffice, Long>{

	public Page<XhJksqOffice> queryXhJksqOffice(Page<XhJksqOffice> page, Map<String, Object> params){
		String hql = "from XhJksqOffice xhJksqOffice where 1=1";
		//单位名称
		if(params.containsKey("name")){
			hql = hql + " and name = :name";
		}
		//邮编
		if(params.containsKey("postcode")){
			hql = hql + " and postcode = :postcode";
		}
		//单位地址
		if(params.containsKey("address")){
			hql = hql + " and address = :address";
		}
		//行业类别
		if(params.containsKey("typea")){
			hql = hql + " and typea = :typea";
		}
		//单位类型（下拉）
		if(params.containsKey("typeh")){
			hql = hql + " and typeh = :typeh";
		}
		//单位电话
		if(params.containsKey("phone")){
			hql = hql + " and phone = :phone";
		}
		//有无抵押 0:无 1:有
		if(params.containsKey("website")){
			hql = hql + " and website = :website";
		}
		//部门
		if(params.containsKey("department")){
			hql = hql + " and department = :department";
		}
		//职务
		if(params.containsKey("duty")){
			hql = hql + " and duty = :duty";
		}
		//员工数量
		if(params.containsKey("personCount")){
			hql = hql + " and personCount = :personCount";
		}
		//月收入
		if(params.containsKey("monthSalary")){
			hql = hql + " and monthSalary = :monthSalary";
		}
		//支薪日
		if(params.containsKey("salaryDay")){
			hql = hql + " and salaryDay = :salaryDay";
		}
		//支付方式
		if(params.containsKey("salaryType")){
			hql = hql + " and salaryType = :salaryType";
		}
		//服务年薪
		if(params.containsKey("workYear")){
			hql = hql + " and workYear = :workYear";
		}
		//其他收入
		if(params.containsKey("bonus")){
			hql = hql + " and bonus = :bonus";
		}
		//公司成立时间
		if(params.containsKey("cofficeStartyear")){
			hql = hql + " and cofficeStartyear = :cofficeStartyear";
		}
		//社保/公积金 0:无 1：有
		if(params.containsKey("cofficeFund")){
			hql = hql + " and cofficeFund = :cofficeFund";
		}
		//社保/公积金基数
		if(params.containsKey("cofficeFundNumber")){
			hql = hql + " and cofficeFundNumber = :cofficeFundNumber";
		}
		//工作证明工资
		if(params.containsKey("cofficeSalaryEnsure")){
			hql = hql + " and cofficeSalaryEnsure = :cofficeSalaryEnsure";
		}
		//银行代发工资
		if(params.containsKey("cofficeSalaryBankEnsure")){
			hql = hql + " and cofficeSalaryBankEnsure = :cofficeSalaryBankEnsure";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
