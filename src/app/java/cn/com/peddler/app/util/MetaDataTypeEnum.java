package cn.com.peddler.app.util;

public enum MetaDataTypeEnum {

	/**
	 * 客户来源
	 */
	PERSON_FROM {
		@Override
		public String toString() {
			return "0015";
		}
	},
	
	/**
	 * 减免人
	 */
	DERATE_PEOPLE {

		@Override
		public String toString() {
			return "20300";
		}
	},
	/**
	 * 客户类型
	 */
	CUSTOMER_TYPE {

		@Override
		public String toString() {
			return "20200";
		}
	},
	/**
	 * 逾期状态
	 */
	CAPITAL_OVERDUE {

		@Override
		public String toString() {
			return "20100";
		}
	},

	/**
	 * 开户行
	 */
	OPEN_BANK {

		@Override
		public String toString() {
			return "0020";
		}
	},

	/**
	 * 还款付息方式
	 */
	RETURN_TYPE_INTEST {

		@Override
		public String toString() {
			return "10006";
		}
	},

	/**
	 * 还款方式
	 */
	RETURN_TYPE {

		@Override
		public String toString() {
			return "0019";
		}
	},
	/**
	 * 银行
	 */
	BANK {

		@Override
		public String toString() {
			return "0001";
		}
	},
	/**
	 * 贷款类型
	 */
	PRODUCT_TYPE {

		@Override
		public String toString() {
			return "10005";
		}
	},
	/**
	 * 证件类型 ,身份证等
	 */
	CARD_TYPE {

		@Override
		public String toString() {
			return "0005";
		}
	},
	/**
	 * 单位性质 私企，国企等
	 */
	OFFICE_TYPE {

		@Override
		public String toString() {
			return "0006";
		}
	},
	/**
	 * 婚姻状况
	 */
	MARRIAGE_TYPE {

		@Override
		public String toString() {
			return "0009";
		}
	},
	/**
	 * 与借款人关系 ,父母等
	 */
	PERSON_RELATION {

		@Override
		public String toString() {
			return "0014";
		}
	},
	/**
	 * 有无子女 ,有，无
	 */
	HAS_CHILDREN {

		@Override
		public String toString() {
			return "0018";
		}
	},
	/**
	 * 还款方式 ,独立还款，家人协助等
	 */
	LOAN_RETURN_TYPE {

		@Override
		public String toString() {
			return "0019";
		}
	},
	/**
	 * 个人基本情况----------------------------- 评分年龄,20岁以下，20-30,30-45,45-55,55岁以上
	 */
	GRADE_AGE {

		@Override
		public String toString() {
			return "30100";
		}
	},
	/**
	 * 婚姻状况,已婚有子女，已婚无子女，未婚，再婚，离婚
	 */
	MARRY_STATE {

		@Override
		public String toString() {
			return "30200";
		}
	},
	/**
	 * 文化程度,硕士及以上，本科，大专，高中专，初中及以下
	 */
	STUDY_LEVEL {

		@Override
		public String toString() {
			return "30300";
		}
	},
	/**
	 * 户口性质,本地城镇户口，本地农村户口，外地城镇户口，外地农村户口
	 */
	HOME_NATURE {

		@Override
		public String toString() {
			return "30400";
		}
	},
	/**
	 * 工作年龄,5年以上，3到5年，1到3年，1年以下
	 */
	WORK_DATE {

		@Override
		public String toString() {
			return "30500";
		}
	},
	/**
	 * 健康状况,优秀，良好，一般，差
	 */
	HEALTH_STATE {

		@Override
		public String toString() {
			return "30600";
		}
	},
	/**
	 * 社会保险,本市五险一金，本市普通社会职工保险，人寿保险，其他商业保险，没有
	 */
	SOCIETY_SAFE {

		@Override
		public String toString() {
			return "30700";
		}
	},
	/**
	 * 个人财产情况------------------------------- 住房情况,完全产权房，按揭购房，家属产权，公租房，租房，农村房产
	 */
	HOUSE_STATE {

		@Override
		public String toString() {
			return "30800";
		}
	},
	// 完全产权房
	ALL {

		@Override
		public String toString() {
			return "30810";
		}
	},
	// 按揭购房
	MORTGAGE {

		@Override
		public String toString() {
			return "30820";
		}
	},
	// 家属产权
	FAMILY {

		@Override
		public String toString() {
			return "30830";
		}
	},

	/**
	 * 车辆情况,完全产权轿车，按揭轿车，无
	 */
	CAR_STATE {

		@Override
		public String toString() {
			return "30900";
		}
	},
	/**
	 * 职业状况-----------------------------
	 * 单位性质,国家企、事单位、社会团体、上市公司且排名前5名的，民营企业，个体工商户/自雇，退休领退休金
	 */
	COMPANY_NATURE {

		@Override
		public String toString() {
			return "31000";
		}
	},
	/**
	 * 在单位岗位性质,管理人员，专业技术人员，普通人员
	 */
	POST_NATURE {

		@Override
		public String toString() {
			return "31100";
		}
	},
	/**
	 * 在单位工作年限,5年以上，5到3年，3到2年，2到1年，1年以内
	 */
	JOB_YEAR {

		@Override
		public String toString() {
			return "31200";
		}
	},
	/**
	 * 职业资格证书拥有情况,有，无
	 */
	QUALIFICATIONS_CARD {

		@Override
		public String toString() {
			return "31300";
		}
	},
	/**
	 * 个人月收入,1万元以上，1万到8千元，8千到6千，6千到4千，4千到2千及现金方法，2千元以内
	 */
	MONEY_MONTH {

		@Override
		public String toString() {
			return "31400";
		}
	},
	/**
	 * 月供款支出收入比,0到50%，51到60%，61到70%，71到80%，80%以上
	 */
	SPEND_MONTH {

		@Override
		public String toString() {
			return "31500";
		}
	},
	/**
	 * 其他修正项----------------------------------
	 * 信用记录,有信用记录无逾期，5次逾期内，6到10次逾期，10到15次逾期，无信用记录和15次逾期以上，无逾期，1到3次逾期5天内，3次逾期以上
	 */
	BELIEVE_TEST {

		@Override
		public String toString() {
			return "31600";
		}
	},
	/**
	 * 是否我公司老客户,老客户，新客户
	 */
	OLD_CLIENT {

		@Override
		public String toString() {
			return "31700";
		}
	},
	/**
	 * 公共记录,无，拖欠记录，不良诉讼记录，治安处罚记录，行政处罚记录
	 */
	PUBLIC_TEST {

		@Override
		public String toString() {
			return "31800";
		}
	},
	/**
	 * 借款用途 : 经营，消费等
	 */
	LOAN_USE_TYPE {

		@Override
		public String toString() {
			return "0027";
		}
	},

	/**
	 * 是否选择 : yes，no
	 */
	YES_OR_NO {

		@Override
		public String toString() {
			return "40000";
		}
	},

	/**
	 * 从何处得知我公司
	 */
	KNOWN_WAY {

		@Override
		public String toString() {
			return "50000";
		}
	},

	/**
	 * 资金来源 : 划扣，内部转账等
	 */
	MONEY_COME_TYPE {

		@Override
		public String toString() {
			return "0028";
		}
	},

	/**
	 * 与谁居住：独居，父母，兄弟/姐妹，同事，配偶，子女，其他
	 */

	LIVE_WHO_TOGETER {
		@Override
		public String toString() {
			return "0029";
		}
	},

	/**
	 * 单位类型：政府机构，机关事业，私营，三资机构，国有企业，个体，民营，社会团体
	 */

	UNIT_TYPE {
		@Override
		public String toString() {
			return "0031";
		}
	},

	/**
	 * 购买方式：全款，按揭
	 */

	BUY_TYPE {
		@Override
		public String toString() {
			return "0032";
		}
	},
	/**
	 * 性别
	 */
	GENDER_TYPE {
		@Override
		public String toString() {
			return "0004";
		}
	},
	/**
	 * 职业
	 */
	OCCUPATION_TYPE {
		@Override
		public String toString() {
			return "0003";
		}
	},
	/**
	 * 单位规模
	 */
	OFFICE_MODLE {
		@Override
		public String toString() {
			return "0011";
		}
	},
	/**
	 * 账单收取方式
	 */
	SEND_MODLE {
		@Override
		public String toString() {
			return "0012";
		}
	},
	/**
	 * 卡或折
	 */
	CARD_OR_MODLE {
		@Override
		public String toString() {
			return "0013";
		}
	},
	/**
	 * 委托协议种类
	 */
	WTXYZL {
		@Override
		public String toString() {
			return "0016";
		}
	},
	/**
	 * 委托协议版本号
	 */
	WTXYBBH {
		@Override
		public String toString() {
			return "0017";
		}
	},
	/**
	 * 回收方式
	 */
	HS_TYPE {
		@Override
		public String toString() {
			return "0021";
		}
	},
	/**
	 * 付款方式
	 */
	PAY_TYPE {
		@Override
		public String toString() {
			return "0022";
		}
	},
	GUARANTY_TYPE {
		@Override
		public String toString() {
			return "50002";
		}
	},
	LIVE_STATE_TYPE {
		@Override
		public String toString() {
			return "50003";
		}
	},
	/**
	 * 需求类型：操作错误，系统错误，业务变更
	 */
	NEED_TYPE {
		@Override
		public String toString() {
			return "0033";
		}
	},

	/**
	 * 企业类型：1.个体 2.私营独资 3.私营合伙 4.私营有限责任 5.私营股份有限 6.港资/外资
	 */

	COMPANY_TYPE {
		@Override
		public String toString() {
			return "vechicle_comp";
		}
	},

	/**
	 * 客户来源：1.中介 2.客户推荐 3.陌生拜访 4.小区推广 5.短信 6.网络营销 7.插车卡 8.报纸
	 */
	CUSTOMER_SOURCE {
		@Override
		public String toString() {
			return "vechicle_custom";
		}
	},
	
	CAR_CYCLE {
		@Override
		public String toString() {
			return "carCycle";
		}
	},
	CAR_TYPE {
		@Override
		public String toString() {
			return "carType";
		}
	},
	/*
	 * 是否展期：0： 否 1：是
	 */
	IS_EXTENSION {  
		@Override
		public String toString() {
			return "isExtension";
		}
	},
	/*
	 * 是否已展期：0： 否 1：是
	 */
	IS_HAVEEXTENSION {
		@Override
		public String toString() {
			return "isHaveextension";
		}
	},
	/*
	 * 借款类型
	 */
	BACKUP01 {
		@Override
		public String toString() {
			return "backup01";
		}
	},
	
	/*
	 * 车借婚姻状况
	 */
	CAR_MARRIAGE {
		@Override
		public String toString() {
			return "car_marriage";
		}
	},
	
	/*
	 * 车借房产信息
	 */
	CAR_LIVESTATE {
		@Override
		public String toString() {
			return "car_livestate";
		}
	},
	/*
     * 外访的外访家庭
     */
    SURVEY_HOME {
        @Override
        public String toString() {
            return "survey_home";
        }
    },
    /*
     * 外访的外访单位
     */
    SURVEY_OFFICE {
        @Override
        public String toString() {
            return "survey_office";
        }
    },
    /*
     * 外访的外访单位
     */
    SURVEY_WORD {
        @Override
        public String toString() {
            return "survey_word";
        }
    },
    /*
     * 直系亲属(0-父亲，1-母亲，2-配偶，3，儿子，4-女儿)
     */
    RELATIONS {
        @Override
        public String toString() {
            return "60000";
        }
    },
    
    /*
     * 放款退回原因
     */
    BACK_TYPE {
        @Override
        public String toString() {
            return "51000";
        }
    },
    
    /*
     * 账单发送状态（0-未发送，1-正在发送，2-发送成功，3-发送失败）
     */
    BILL_SEND_STATE {
        @Override
        public String toString() {
            return "billSendState";
        }
    },
    /*
     * 债权制作状态（0-未发送，1-正在发送，2-发送成功，3-发送失败）
     */
    AGREEMENT_MAKE_STATE {
        @Override
        public String toString() {
            return "agreeMakeState";
        }
    },
    /*
     * 借款申请状态  待加入
     */
    JKSQ_STATE{
        @Override
        public String toString() {
            return "jksqState";
        }
    },
    /*
     * 验证号码重复时，可以通过对状态( 0-31.A   1->101)key无用，只用值
     */
    JKSQ_PASSED_STATE{
        @Override
        public String toString() {
            return "jksqPassedState";
        }
    },
    /*
     * 可重复借款状态控制 (myown->1 family->1 together->1) 其中的值可变，1控制  0不控制提交
     */
    JKSQ_TYPE_PASSED_STATE_CONTROL{
        @Override
        public String toString() {
            return "jksqTypeControl";
        }
    },
    
    /**
     * 债权转让申请状态
     */
    CREDIT_APPLY_TYPE{
        @Override
        public String toString() {
            return "creditApply";
        }
    }
    
	
}
