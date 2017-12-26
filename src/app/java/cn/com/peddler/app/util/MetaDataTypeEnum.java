package cn.com.peddler.app.util;

public enum MetaDataTypeEnum {

	
	/**
	 * 银行
	 */
	BANK {

		@Override
		public String toString() {
			return "0001";
		}
	}
	,
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
	 * 是否选择 : yes，no
	 */
	YES_OR_NO {

		@Override
		public String toString() {
			return "40000";
		}
	}
    ,
    /**
     * 是否在用：在用、历史
     */
    VTYPES{
		@Override
		public String toString() {
			return "vtypes";
		}	
    },
	/**
	 * 性别
	 */
	SEX_TYPE {
		@Override
		public String toString() {
			return "0004";
		}
	},
	/**
	 * 职业
	 */
	POST_TYPE {
		@Override
		public String toString() {
			return "0003";
		}
	}
	
}
