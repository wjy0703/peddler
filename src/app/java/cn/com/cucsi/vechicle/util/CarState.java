package cn.com.cucsi.vechicle.util;

/**
 * 车贷申请的状态枚举类，退回均用.B结尾,在用户编辑修改信息后，均会将.B直接去掉
 *
 * @author xjs
 * @date 2013-9-29 下午12:31:08
 */
public enum CarState {
     ZUO_FEI("009","作废"),
     TEMPSAVE("0","暂存"),                                         //暂存
     NEED_TOGETHER("01","填写共借人"),                            //填写共借人
     UPLOAD_FILE("02","待上传资料","blue"),                               //待上传资料
     FIRST_AUDIT("31","待初审"),                                    //待初审
     SECOND_AUDIT("32","待复审"),                                   //待复审
     FINAL_AUDIT("33","待终审"),                                //待终审
     FIRST_AUDIT_BACK("31.B","初审退回","red"),                       //初审退回    --退回代表退回门店处理  
     SECOND_AUDIT_BACK("32.B","复审退回","red"),                      //复审退回
     FINAL_AUDIT_BACK("33.B","终审退回","red"),                       //终审退回
     FIRST_AUDIT_REFUSE("315","初审拒绝","red"),                       //初审拒绝     
     SECOND_AUDIT_REFUSE("325","复审拒绝","red"),                      //复审拒绝
     FINAL_AUDIT_REFUSE("335","终审拒绝","red"),                       //终审拒绝
     DAI_QUE_REN_HE_TONG_DKLL("40","待确认合同利率"),                   //---  待确认合同利率
     DAI_QUE_DING_QIAN_SHU("519","待确定签署"),                       //--- 待确定签署 门店下载合同/上传，上
     DAI_ZHI_ZUO_HE_TONG("50","待制作合同","blue"),                   //---  XX部门点击制作按钮
     DAI_SHANG_CHUAN_HETONG("520","待上传合同"),                       //--- 门店下载合同/上传，上
     DAI_TI_JIAO_FANG_KUAN_SHENHE("529","待放款审核"),                //----待上传合同 小可放款审核
     DAI_TI_JIAO_HUA_KOU_SHENHE("539","待划扣审核"),                //----待上传合同 可划扣审核
     DAI_FANG_KUAN("60","待放款","blue"),                             //----待放款，整合到待放款列表
     YI_FANG_KUAN("61","已放款","blue"),
     HUAN_KUAN_ZHONG("70","还款中"),
     JIE_QING("102","结清"),
     YI_WAN_CHENG("90","已完成"),
     KE_HU_FANG_QI("81","客户放弃","red"),
     HE_TONG_JU_JUE("57","签约合同审核拒绝","red"),
     DAI_HUA_KOU("91","待划扣","red"),
     YI_HUA_HOU("92","已划扣"),
     DAI_HE_TONG_ZHI_ZUO_SHEN_HE("51","待合同制作审核"),                      //待用
     TI_QIAN_JIE_QING("101","提前结清","red");

     private String message;
     
     private String text;

     private String color;
     
     CarState(String text,String message){
         this.text = text;
         this.message = message;
     }
     
     CarState(String text,String message,String color){
         this.text = text;
         this.message = message;
         this.color = color;
     }
     
    public String getColor() {
        return color;
    }

    
    public void setColor(String color) {
        this.color = color;
    }

    public String getText(){
         return text;
     }
     
     public String getMessage(){
         return message;
     }
     
     /**
      * 通过字符串获得枚举类
      *
      * @param text  枚举类对应的字符串的值
      * @return  枚举类
      * @author xjs
      * @date 2013-9-29 下午12:31:56
      */
     public static CarState fromStr(String text) {
        if (text != null) {
            for (CarState state : CarState.values()) {
              if (text.equalsIgnoreCase(state.text)) {
                return state;
              }
            }
          }
          return TEMPSAVE;
     }
}
