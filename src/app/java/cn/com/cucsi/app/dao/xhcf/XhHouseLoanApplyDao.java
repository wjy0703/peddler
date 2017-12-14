package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhHouseLoanApply;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhHouseLoanApplyDao extends HibernateDao<XhHouseLoanApply, Long>{

	public Page<XhHouseLoanApply> queryXhHouseLoanApply(Page<XhHouseLoanApply> page, Map<String, Object> params){
		String hql = "from XhHouseLoanApply xhHouseLoanApply where 1=1";
		//申请类型
		if(params.containsKey("applyType")){
			hql = hql + " and applyType = :applyType";
		}
		//产权证号
		if(params.containsKey("houseRightNum")){
			hql = hql + " and houseRightNum = :houseRightNum";
		}
		//帐号
		if(params.containsKey("bankNum")){
			hql = hql + " and bankNum = :bankNum";
		}
		//开户行
		if(params.containsKey("bankOpen")){
			hql = hql + " and bankOpen = :bankOpen";
		}
		//户名
		if(params.containsKey("bankAccountName")){
			hql = hql + " and bankAccountName = :bankAccountName";
		}
		//工作单位
		if(params.containsKey("companyAdress")){
			hql = hql + " and companyAdress = :companyAdress";
		}
		//单位性质
		if(params.containsKey("companyNature")){
			hql = hql + " and companyNature = :companyNature";
		}
		//单位电话
		if(params.containsKey("companyPhone")){
			hql = hql + " and companyPhone = :companyPhone";
		}
		//还款方式
		if(params.containsKey("backMoneyType")){
			hql = hql + " and backMoneyType = :backMoneyType";
		}
		//住址电话
		if(params.containsKey("homePhone")){
			hql = hql + " and homePhone = :homePhone";
		}
		//房屋座落
		if(params.containsKey("houseAddress")){
			hql = hql + " and houseAddress = :houseAddress";
		}
		//房屋面积
		if(params.containsKey("houseArea")){
			hql = hql + " and houseArea = :houseArea";
		}
		//是否自营公司
		if(params.containsKey("isOwnCompany")){
			hql = hql + " and isOwnCompany = :isOwnCompany";
		}
		//借款期数
		if(params.containsKey("loanMonth")){
			hql = hql + " and loanMonth = :loanMonth";
		}
		//申请日期
		if(params.containsKey("loanApplyDate")){
			hql = hql + " and loanApplyDate = :loanApplyDate";
		}
		//借款金额
		if(params.containsKey("loanLoanAmount")){
			hql = hql + " and loanLoanAmount = :loanLoanAmount";
		}
		//借款用途
		if(params.containsKey("loanUse")){
			hql = hql + " and loanUse = :loanUse";
		}
		//借款人户籍地址
		if(params.containsKey("loanSrcAddress")){
			hql = hql + " and loanSrcAddress = :loanSrcAddress";
		}
		//借款编号
		if(params.containsKey("loanCode")){
			hql = hql + " and loanCode = :loanCode";
		}
		//抵押价值
		if(params.containsKey("mortgAount")){
			hql = hql + " and mortgAount = :mortgAount";
		}
		//老家宅电
		if(params.containsKey("oldHomePhone")){
			hql = hql + " and oldHomePhone = :oldHomePhone";
		}
		//产权价值
		if(params.containsKey("houseRightValue")){
			hql = hql + " and houseRightValue = :houseRightValue";
		}
		//核定利率
		if(params.containsKey("fixedRate")){
			hql = hql + " and fixedRate = :fixedRate";
		}
		//配偶地址
		if(params.containsKey("spAdress")){
			hql = hql + " and spAdress = :spAdress";
		}
		//配偶年龄
		if(params.containsKey("spAge")){
			hql = hql + " and spAge = :spAge";
		}
		//配偶年收入
		if(params.containsKey("spIncome")){
			hql = hql + " and spIncome = :spIncome";
		}
		//配偶工作单位
		if(params.containsKey("spComp")){
			hql = hql + " and spComp = :spComp";
		}
		//配偶工作单位地址
		if(params.containsKey("spCompAdress")){
			hql = hql + " and spCompAdress = :spCompAdress";
		}
		//配偶工作单位电话
		if(params.containsKey("spCompPhone")){
			hql = hql + " and spCompPhone = :spCompPhone";
		}
		//配偶职务
		if(params.containsKey("spDep")){
			hql = hql + " and spDep = :spDep";
		}
		//配偶性别
		if(params.containsKey("spSex")){
			hql = hql + " and spSex = :spSex";
		}
		//配偶家庭电话
		if(params.containsKey("spHomePhone")){
			hql = hql + " and spHomePhone = :spHomePhone";
		}
		//配偶姓名
		if(params.containsKey("spName")){
			hql = hql + " and spName = :spName";
		}
		//配偶手机
		if(params.containsKey("spTelephone")){
			hql = hql + " and spTelephone = :spTelephone";
		}
		//配偶工作年限
		if(params.containsKey("spWorkLimit")){
			hql = hql + " and spWorkLimit = :spWorkLimit";
		}
		//配偶身份证号
		if(params.containsKey("spIdNum")){
			hql = hql + " and spIdNum = :spIdNum";
		}
		//借款状态
		if(params.containsKey("loanState")){
			hql = hql + " and loanState = :loanState";
		}
		//手机
		if(params.containsKey("telephone")){
			hql = hql + " and telephone = :telephone";
		}
		//组织机构
		if(params.containsKey("organiId")){
			
			hql = hql + " and xhHouseLoanConsult.organ.id = :organiId or xhHouseLoanConsult.organ.parentId=:organiId" ;
		}
		//团队经理

		if(params.containsKey("teamManagerId")){
			hql = hql + " and xhHouseLoanConsult.teamManager.id = :teamManagerId";
		}
		//区域
		if(params.containsKey("area")){
			hql = hql + " and area = :area";
		}
		//城市
		if(params.containsKey("city")){
			hql = hql + " and city in ("+params.get("city")+")";
		}
		//省份
		if(params.containsKey("province")){
			hql = hql + " and province = :province";
		}
		//借款人姓名
		if(params.containsKey("loanerName")){
			hql = hql + " and loanerName = :loanerName";
		}
		//借款人性别
		if(params.containsKey("loanerSex")){
			hql = hql + " and loanerSex = :loanerSex";
		}
		//借款人身份证号
		if(params.containsKey("loanerIdNumber")){
			hql = hql + " and loanerIdNumber = :loanerIdNumber";
		}
		//借款人年龄
		if(params.containsKey("loanerAge")){
			hql = hql + " and loanerAge = :loanerAge";
		}
		//产权人姓名
		if(params.containsKey("houseOwnerName")){
			hql = hql + " and houseOwnerName = :houseOwnerName";
		}
		//产权人身份证号
		if(params.containsKey("houseOwnerIdNum")){
			hql = hql + " and houseOwnerIdNum = :houseOwnerIdNum";
		}
		//产权人年龄
		if(params.containsKey("houseOwnerAge")){
			hql = hql + " and houseOwnerAge = :houseOwnerAge";
		}
		//共有权人姓名
		if(params.containsKey("houseJointName")){
			hql = hql + " and houseJointName = :houseJointName";
		}
		//共有权人性别
		if(params.containsKey("houseJointSex")){
			hql = hql + " and houseJointSex = :houseJointSex";
		}
		//共有权人身份证号
		if(params.containsKey("houseJointIdNum")){
			hql = hql + " and houseJointIdNum = :houseJointIdNum";
		}
		//共有权人年龄
		if(params.containsKey("houseJointAge")){
			hql = hql + " and houseJointAge = :houseJointAge";
		}
		//婚姻状况
		if(params.containsKey("marital")){
			hql = hql + " and marital = :marital";
		}
		//有无子女
		if(params.containsKey("hasChild")){
			hql = hql + " and hasChild = :hasChild";
		}
		//产权人性别
		if(params.containsKey("houseOwnerSex")){
			hql = hql + " and houseOwnerSex = :houseOwnerSex";
		}
		//亲属姓名
		if(params.containsKey("firstLxrName")){
			hql = hql + " and firstLxrName = :firstLxrName";
		}
		//亲属与本人关系
		if(params.containsKey("firstLxrRelation")){
			hql = hql + " and firstLxrRelation = :firstLxrRelation";
		}
		//亲属单位
		if(params.containsKey("firstLxrWorkUnit")){
			hql = hql + " and firstLxrWorkUnit = :firstLxrWorkUnit";
		}
		//亲属单位或家庭住址
		if(params.containsKey("firstLxrAddress")){
			hql = hql + " and firstLxrAddress = :firstLxrAddress";
		}
		//朋友姓名
		if(params.containsKey("secondLxrName")){
			hql = hql + " and secondLxrName = :secondLxrName";
		}
		//朋友与本人关系
		if(params.containsKey("secondLxrRelation")){
			hql = hql + " and secondLxrRelation = :secondLxrRelation";
		}
		//朋友工作单位
		if(params.containsKey("secondLxrWorkUnit")){
			hql = hql + " and secondLxrWorkUnit = :secondLxrWorkUnit";
		}
		//朋友单位地址或家庭住址
		if(params.containsKey("secondLxrAddress")){
			hql = hql + " and secondLxrAddress = :secondLxrAddress";
		}
		//亲属联系电话
		if(params.containsKey("firstLxrTelphone")){
			hql = hql + " and firstLxrTelphone = :firstLxrTelphone";
		}
		//朋友联系电话
		if(params.containsKey("secondLxrTelphone")){
			hql = hql + " and secondLxrTelphone = :secondLxrTelphone";
		}
		//同事姓名
		if(params.containsKey("thirdLxrName")){
			hql = hql + " and thirdLxrName = :thirdLxrName";
		}
		//同事与本人关系
		if(params.containsKey("thirdLxrRelation")){
			hql = hql + " and thirdLxrRelation = :thirdLxrRelation";
		}
		//同事单位
		if(params.containsKey("thirdLxrWorkUnit")){
			hql = hql + " and thirdLxrWorkUnit = :thirdLxrWorkUnit";
		}
		//同事单位地址或家庭住址
		if(params.containsKey("thirdLxrAddress")){
			hql = hql + " and thirdLxrAddress = :thirdLxrAddress";
		}
		//同事联系电话
		if(params.containsKey("thirdLxrTelphone")){
			hql = hql + " and thirdLxrTelphone = :thirdLxrTelphone";
		}
		//放款金额
		if(params.containsKey("makeLoanAmount")){
			hql = hql + " and makeLoanAmount = :makeLoanAmount";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}
