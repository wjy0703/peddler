package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhHouseLoanConsult;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhHouseLoanConsultDao extends HibernateDao<XhHouseLoanConsult, Long>{

	public Page<XhHouseLoanConsult> queryXhHouseLoanConsult(Page<XhHouseLoanConsult> page, Map<String, Object> params){
		String hql = "from XhHouseLoanConsult xhHouseLoanConsult where 1=1";
		//客户姓名
		if(params.containsKey("customerName")){
			hql = hql + " and customerName = :customerName";
		}
		//性别
		if(params.containsKey("customerSex")){
			hql = hql + " and customerSex = :customerSex";
		}
		//身份证号码
		if(params.containsKey("identificationCard")){
			hql = hql + " and identificationCard = :identificationCard";
		}
		//客户来源
		if(params.containsKey("customerSource")){
			hql = hql + " and customerSource = :customerSource";
		}
		//婚否
		if(params.containsKey("marrital")){
			hql = hql + " and marrital = :marrital";
		}
		//电话
		if(params.containsKey("customerTel")){
			hql = hql + " and customerTel = :customerTel";
		}
		//现住址
		if(params.containsKey("nowAddress")){
			hql = hql + " and nowAddress = :nowAddress";
		}
		//房产性质
		if(params.containsKey("houseType")){
			hql = hql + " and houseType = :houseType";
		}
		//使用年限
		if(params.containsKey("houseLimit")){
			hql = hql + " and houseLimit = :houseLimit";
		}
		//所属区县
		if(params.containsKey("houseRegion")){
			hql = hql + " and houseRegion = :houseRegion";
		}
		//房产面积
		if(params.containsKey("houseArea")){
			hql = hql + " and houseArea = :houseArea";
		}
		//建成年代
		if(params.containsKey("houseYears")){
			hql = hql + " and houseYears = :houseYears";
		}
		//房屋楼层
		if(params.containsKey("houseFloor")){
			hql = hql + " and houseFloor = :houseFloor";
		}
		//房屋地址
		if(params.containsKey("houseAddress")){
			hql = hql + " and houseAddress = :houseAddress";
		}
		//房产详细情况
		if(params.containsKey("houseInfo")){
			hql = hql + " and houseInfo = :houseInfo";
		}
		//综合利率
		if(params.containsKey("allLoanRate")){
			hql = hql + " and allLoanRate = :allLoanRate";
		}
		//贷款额度
		if(params.containsKey("loanAmount")){
			hql = hql + " and loanAmount = :loanAmount";
		}
		//贷款周期
		if(params.containsKey("loanMonth")){
			hql = hql + " and loanMonth = :loanMonth";
		}
		//评估价格
		if(params.containsKey("assessPrice")){
			hql = hql + " and assessPrice = :assessPrice";
		}
		//贷款用途
		if(params.containsKey("loanUse")){
			hql = hql + " and loanUse = :loanUse";
		}
		//还款来源
		if(params.containsKey("backSource")){
			hql = hql + " and backSource = :backSource";
		}
		//客户要求
		if(params.containsKey("customerRequired")){
			hql = hql + " and customerRequired = :customerRequired";
		}
		//备注
		if(params.containsKey("remark")){
			hql = hql + " and remark = :remark";
		}
		//客户经理
		if(params.containsKey("customrerManagerId")){
			hql = hql + " and customrerManager.id = :customrerManagerId";
		}
		//团队经理
		if(params.containsKey("teamManagerId")){
			hql = hql + " and teamManager.id = :teamManagerId";
		}
		//涉诉查询
		if(params.containsKey("lodgeQueryId")){
			hql = hql + " and lodgeQueryId = :lodgeQueryId";
		}
		//组织机构
		if(params.containsKey("organId")){
			hql = hql + " and organ.id = :organId or organ.parentId=:organId" ;
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
