package cn.com.peddler.app.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.tool.hbm2x.StringUtils;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

/**
 * 将jsp页面传递过来的参数，转换成数据库对应的字段的值或值得集合 例如： 传递
 * teamLeader，代表的是团队经理,则转换成TUAN,即枚举类对应的EmployeeLeveCodeEnum.teamLeader对应的字符串的值.
 * 
 * @author xjs
 * 
 */
public class MetaDataTypeConverter {

    public static String getCodingName(String coding) {

    	if (StringUtils.equals("deratePeople", coding)) {                   // 减免人(夏靖-->夏靖)
            
            return MetaDataTypeEnum.DERATE_PEOPLE.toString();
            
        } else if (StringUtils.equals("personFrom", coding)){			//客户来源(商超/老客户介绍/陌CALL/其它/客户服务节)
        	
        	return MetaDataTypeEnum.PERSON_FROM.toString();
        	
        }else if (StringUtils.equals("knownWay", coding)){			//从何处得知我公司
        	
        	return MetaDataTypeEnum.KNOWN_WAY.toString();
        	
        }else if (StringUtils.equals("customerType", coding)) {                   // 客户类型(0-->新客户,1-->老客户)
            
            return MetaDataTypeEnum.CUSTOMER_TYPE.toString();
            
        } else if (StringUtils.equals("capitalOverdue", coding)) {                   // 逾期状态(0-->逾期,1-->追回,4-->挂账,5-->减免,6--坏账)
            
            return MetaDataTypeEnum.CAPITAL_OVERDUE.toString();
            
        } else if (StringUtils.equals("openBank", coding)) {                   // 开户行
            
            return MetaDataTypeEnum.OPEN_BANK.toString();
            
        } else if (StringUtils.equals("bank", coding)) {                 // 银行(01-->中国工商银行,02-->中国建设银行,03-->中国邮政储蓄银行有限责任公司,04-->中国银行,05-->招商银行,06-->中国农业银行,07-->中国光大银行,09-->中国民生银行,10-->兴业银行,12-->中国交通银行,13-->中国农商银行,14-->广东发展银行,15-->中国深发银行,11-->中信银行)
            
            return MetaDataTypeEnum.BANK.toString();
        
        } else if (StringUtils.equals("productType", coding)) {          // 贷款类型(C-->薪水借,D-->薪水楼易借,E-->精英借,A-->老板借,B-->老板楼易借,Q-->企业借,W-->简易楼易借)
        
            return MetaDataTypeEnum.PRODUCT_TYPE.toString();
        
        } else if (StringUtils.equals("cardType", coding)) {             // 证件类型(身份证-->身份证，军官证-->军官证，护照-->护照，户口簿-->户口簿，港澳居民往来大陆通行证-->港澳居民往来大陆通行证)
        
            return MetaDataTypeEnum.CARD_TYPE.toString();
        
        }else if (StringUtils.equals("officeType", coding)) {           // 单位性质(私企-->私企，国企-->国企，外企-->外企)
        
            return MetaDataTypeEnum.OFFICE_TYPE.toString();
        
        } else if (StringUtils.equals("marriageType", coding)) {         // 婚姻状况(已婚-->已婚，未婚-->未婚，离异-->离异，丧偶-->丧偶)
            
            return MetaDataTypeEnum.MARRIAGE_TYPE.toString();
        
        } else if (StringUtils.equals("personRelation", coding)) {       // 与借款人关系(亲属-->亲属，朋友-->朋友，同事-->同事)
                                                                   
            return MetaDataTypeEnum.PERSON_RELATION.toString();
        
        } else if (StringUtils.equals("hasChildren", coding)) {          // 有无子女 (有-->有，无-->无)
        
            return MetaDataTypeEnum.HAS_CHILDREN.toString();
        
        } else if (StringUtils.equals("loanReturnType", coding)) {       // 还款方式 (独立还款-->独立还款，家人协助-->家人协助,其他方式还款-->其他方式还款)
        
            return MetaDataTypeEnum.LOAN_RETURN_TYPE.toString();
        
        } else if (StringUtils.equals("loanUseType", coding)) {          // 借款用途(经营-->经营，周转-->周转，消费-->消费)
        
            return MetaDataTypeEnum.LOAN_USE_TYPE.toString();
        
        }else if (StringUtils.equals("moneyComeType", coding)) {          // 资金来源(0-->划扣，1-->内部转账)
        
            return MetaDataTypeEnum.MONEY_COME_TYPE.toString();
        
        }else if(StringUtils.equals("gradeAge", coding)){				//评分年龄(0-->20岁以下， 3-->20-30,5-->30-45,2-->45-55,0-->55岁以上)
        	
        	return MetaDataTypeEnum.GRADE_AGE.toString();				
        	
        }else if(StringUtils.equals("marryState", coding)){				//婚姻状况(5-->已婚有子女，3-->已婚无子女，2-->未婚，2-->再婚，0-->离婚)
        	
        	return MetaDataTypeEnum.MARRY_STATE.toString();
        	
        }else if(StringUtils.equals("studyLevel", coding)){     		//文化程度(4-->硕士及以上，3-->本科，2-->大专，1-->高中专，0-->初中及以下)
        	
        	return MetaDataTypeEnum.STUDY_LEVEL.toString();
        	
        }else if(StringUtils.equals("homeNature", coding)){				//户口性质(5-->本地城镇户口，3-->本地农村户口，2-->外地城镇户口，1-->外地农村户口)
        	
        	return MetaDataTypeEnum.HOME_NATURE.toString();
        	
        }else if(StringUtils.equals("workDate", coding)){				//工作年龄(4-->5年以上，3-->3到5年，2-->1到3年，1-->1年以下)
        	
        	return MetaDataTypeEnum.WORK_DATE.toString();
        	
        }else if(StringUtils.equals("healthState", coding)){			//健康状况(3-->优秀，2-->良好，1-->一般，0-->差)
        	
        	return MetaDataTypeEnum.HEALTH_STATE.toString();
        	
        }else if(StringUtils.equals("societySafe", coding)){			//社会保险(5-->本市五险一金，3-->本市普通社会职工保险，3-->人寿保险，2-->其他商业保险，0-->没有)
        	
        	return MetaDataTypeEnum.SOCIETY_SAFE.toString();
        	
        }else if(StringUtils.equals("houseState", coding)){				//住房情况(8-->完全产权房70平米以下，10-->完全产权70以上，6-->按揭购房80以下，8-->按揭购房70以上
        																		//，5-->家属产权70以下，6-->家属产权70以上，5-->公租房，3-->租房，5-->农村房产)
        	return MetaDataTypeEnum.HOUSE_STATE.toString();
        	
        }else if(StringUtils.equals("carState", coding)){				//车辆情况(5-->完全产权轿车，2-->按揭轿车，0-->无)
        	
        	return MetaDataTypeEnum.CAR_STATE.toString();
        	
        }else if(StringUtils.equals("companyNature", coding)){			//单位性质(5-->国家企、事单位、社会团体、上市公司且排名前5名的，3-->民营企业，2-->个体工商户/自雇，1-->退休领退休金)
        	
        	return MetaDataTypeEnum.COMPANY_NATURE.toString();
        	
        }else if(StringUtils.equals("postNature", coding)){				//在单位岗位性质(3-->管理人员，2-->专业技术人员，1-->普通人员)
        	
        	return MetaDataTypeEnum.POST_NATURE.toString();  
        	
        }else if(StringUtils.equals("jobYear", coding)){				//在单位工作年限(5-->5年以上，4-->5到3年，3-->3到2年，2-->2到1年，1-->1年以内)
        	
        	return MetaDataTypeEnum.JOB_YEAR.toString();
        	
        }else if(StringUtils.equals("qualificationsCard", coding)){			//职业资格证书拥有情况(3-->有，1-->无)
        	
        	return MetaDataTypeEnum.QUALIFICATIONS_CARD.toString();
        		
        }else if(StringUtils.equals("moneyMonth", coding)){				// 个人月收入(8-->1万元以上，7-->1万到8千元，6-->8千到6千，5-->6千到4千，4-->4千到2千及现金方法，0-->2千元以内)
        	
        	return MetaDataTypeEnum.MONEY_MONTH.toString();
        	
        }else if(StringUtils.equals("spendMonth", coding)){				//月供款支出收入比(8-->0到50%，6-->51到60%，4-->61到70%，3-->71到80%，0-->80%以上)
        	
        	return MetaDataTypeEnum.SPEND_MONTH.toString();
        	
        }else if(StringUtils.equals("believeTest", coding)){			//信用记录(10-->有信用记录无逾期，8-->5次逾期内，5-->6到10次逾期，2-->10到15次逾期，0-->无信用记录和15次逾期以上)
        	
        	return MetaDataTypeEnum.BELIEVE_TEST.toString();
        	
        }else if(StringUtils.equals("oldClient", coding)){				//是否我公司老客户(6-->无逾期，4-->1到3次逾期5天内，0-->3次逾期以上，2-->新客户)
        	
        	return MetaDataTypeEnum.OLD_CLIENT.toString();
        	
        }else if(StringUtils.equals("publicTest", coding)){				//公共记录(6-->无，0-->拖欠记录，0-->不良诉讼记录，0-->治安处罚记录，0-->行政处罚记录)
        	
        	return MetaDataTypeEnum.PUBLIC_TEST.toString();
        	
        }else if (StringUtils.equals("returnIntest", coding)) {          // 还款付息方式(0-->等额本息,1-->按月结息一次性还本)
        
            return MetaDataTypeEnum.RETURN_TYPE_INTEST.toString();
        

        }else if(StringUtils.equals("yesOrNo", coding)){    //是否选择内容(1-->是，0-->否)
        	
        	return MetaDataTypeEnum.YES_OR_NO.toString();
        	
        }else if(StringUtils.equals("liveWhoTogeter", coding)){ 		//与谁居住(1-->独居，2-->父母，3-->兄弟/姐妹，4-->同事，5-->配偶，6-->子女，7-->其他)
        	
        	return MetaDataTypeEnum.LIVE_WHO_TOGETER.toString();
        	
        }else if(StringUtils.equals("utilsType", coding)){				//单位类型(1-->政府机构，2-->机关事业，3-->私营，4-->三资机构，5-->国有企业，6-->个体)
        	
        	return MetaDataTypeEnum.UNIT_TYPE.toString();
        	
        }else if(StringUtils.equals("buyType", coding)){				//购买方式(1-->全额，2-->按揭)
        	
        	return MetaDataTypeEnum.BUY_TYPE.toString();
        	
        }else if(StringUtils.equals("genders", coding)){                //性别(男-->男，女-->女)
            
            return MetaDataTypeEnum.GENDER_TYPE.toString();
            
        }else if(StringUtils.equals("occupationType", coding)){                //职业
            
            return MetaDataTypeEnum.OCCUPATION_TYPE.toString();
            
        }else if(StringUtils.equals("officeModle", coding)){                //单位规模
            
            return MetaDataTypeEnum.OFFICE_MODLE.toString();
            
        }else if(StringUtils.equals("sendModle", coding)){                //账单收取方式
            
            return MetaDataTypeEnum.SEND_MODLE.toString();
            
        }else if(StringUtils.equals("cardOrModle", coding)){                //卡或折
            
            return MetaDataTypeEnum.CARD_OR_MODLE.toString();
            
        }else if(StringUtils.equals("wtxyzl", coding)){                //委托协议种类
            
            return MetaDataTypeEnum.WTXYZL.toString();
            
        }else if(StringUtils.equals("wtxybbh", coding)){                //委托协议版本号
            
            return MetaDataTypeEnum.WTXYBBH.toString();
            
        }else if(StringUtils.equals("hsType", coding)){                //回收方式
            
            return MetaDataTypeEnum.HS_TYPE.toString();
            
        }else if(StringUtils.equals("payType", coding)){                //付款方式
            
            return MetaDataTypeEnum.PAY_TYPE.toString();
            
        }else if(StringUtils.equals("guarantyType", coding)){            //有无抵押   0-->无抵押   1->有抵押
            
            return MetaDataTypeEnum.GUARANTY_TYPE.toString();           
            
        }else if(StringUtils.equals("liveStateType", coding)){            //有无抵押   0-->无抵押   1->有抵押
            
            return MetaDataTypeEnum.LIVE_STATE_TYPE.toString();           //居住类型 01-->自购            02-->家族拥有              03-->朋友拥有          04-->宿舍          05-->租用
            
        }else if(StringUtils.equals("needType", coding)){            //需求类型：（1->操作错误，2->系统错误，3->业务变更）
            
            return MetaDataTypeEnum.NEED_TYPE.toString();           //
            
        }else if(StringUtils.equals("companyType", coding)){            //需求类型：（1->操作错误，2->系统错误，3->业务变更）
            
            return MetaDataTypeEnum.COMPANY_TYPE.toString();           //
            
        }else if(StringUtils.equals("customerSource", coding)){            //需求类型：（1->操作错误，2->系统错误，3->业务变更）
            
            return MetaDataTypeEnum.CUSTOMER_SOURCE.toString();           //
            
        }else if(StringUtils.equals("carCycle", coding)){            //需求类型：（1->操作错误，2->系统错误，3->业务变更）
            
            return MetaDataTypeEnum.CAR_CYCLE.toString();           //
            
        }else if(StringUtils.equals("carType", coding)){            //需求类型：（1->操作错误，2->系统错误，3->业务变更）
            
            return MetaDataTypeEnum.CAR_TYPE.toString();           //
            
        }else if(StringUtils.equals("isExtension", coding)){            //是否展期：（0->否，1->是）
            
            return MetaDataTypeEnum.IS_EXTENSION.toString();           //
            
        }else if(StringUtils.equals("isHaveextension", coding)){            //是否已展期：（0->否，1->是）
            
            return MetaDataTypeEnum.IS_HAVEEXTENSION.toString();           //
            
        }else if(StringUtils.equals("backup01", coding)){            //借款类型
            
            return MetaDataTypeEnum.BACKUP01.toString();           //
            
        }else if(StringUtils.equals("car_marriage", coding)){            //车借婚姻类型
            
            return MetaDataTypeEnum.CAR_MARRIAGE.toString();           //
            
        }else if(StringUtils.equals("car_livestate", coding)){            //车借房产类型
            
            return MetaDataTypeEnum.CAR_LIVESTATE.toString();           //
            
        }else if(StringUtils.equals("surveyHome", coding)){            //外访家庭
            
            return MetaDataTypeEnum.SURVEY_HOME.toString();           //
            
        }else if(StringUtils.equals("surveyOffice", coding)){            //外访单位
            
            return MetaDataTypeEnum.SURVEY_OFFICE.toString();           //
            
        }else if(StringUtils.equals("surveyWord", coding)){            //外访调查表
            
            return MetaDataTypeEnum.SURVEY_WORD.toString();           //
            
        }else if(StringUtils.equals("relations", coding)){          //直系亲属(0-父亲，1-母亲，2-配偶，3，儿子，4-女儿)
            
            return MetaDataTypeEnum.RELATIONS.toString();
            
        }else if(StringUtils.equals("back_type", coding)){          //直系亲属(0-父亲，1-母亲，2-配偶，3，儿子，4-女儿)
            
            return MetaDataTypeEnum.BACK_TYPE.toString();
            
        }else if(StringUtils.equals("billSendState", coding)){          //邮件发送状态
            
            return MetaDataTypeEnum.BILL_SEND_STATE.toString();
            
        }else if(StringUtils.equals("agreementMakeState", coding)){          //债权制作状态
            
            return MetaDataTypeEnum.AGREEMENT_MAKE_STATE.toString();
            
        }else if(StringUtils.equals("credit_apply_type", coding)){          //债权转让申请状态
            
            return MetaDataTypeEnum.CREDIT_APPLY_TYPE.toString();
            
        }
    	
    	//50003 liveStateType
        else {
            return "";
        }
    }
}
