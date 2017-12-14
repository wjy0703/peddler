package cn.com.cucsi.vechicle.util;

public enum CarOperation {
     TEMPSAVE("0","暂存操作"),                      //暂存
     SUBMIT("1","编辑或提交操作"),                   //提交
     UPLOAD_FILE_AGAIN("011","重新上传资料"),       //重新上传资料
     UPLOAD_FILE("02","上传资料"),                 //上传资料
     NEED_TOGETHER("01","填写共借人"),                            
     FIRST_AUDIT("31","初审操作通过"),                                 
     SECOND_AUDIT("32","复审操作通过"),                                  
     FINAL_AUDIT("33","终审操作通过"),                                //待终审
     FIRST_AUDIT_BACK("31.B","初审退回"),                       //初审退回    --退回代表退回门店处理  
     SECOND_AUDIT_BACK("32.B","复审退回"),                      //复审退回
     FINAL_AUDIT_BACK("33.B","终审退回"),                       //终审退回
     FIRST_AUDIT_REFUSE("315","初审拒绝"),                       //初审拒绝     
     SECOND_AUDIT_REFUSE("325","复审拒绝"),                      //复审拒绝
     FINAL_AUDIT_REFUSE("335","终审拒绝"),                       //终审拒绝
     DAI_QUE_REN_HE_TONG_DKLL("40","待确认合同利率"),                   //---  待确认合同利率
     DAI_QUE_DING_QIAN_SHU("519","待确定签署"),                       //--- 门店下载合同/上传，上
     DAI_ZHI_ZUO_HE_TONG("50","待制作合同"),                   //---  XX部门点击制作按钮
     DAI_SHANG_CHUAN_HETONG("520","待上传合同"),                       //--- 门店下载合同/上传，上
     DAI_TI_JIAO_HUA_KOU_SHENHE("539","待划扣审核"),                //----待上传合同 可划扣审核
     DAI_TI_JIAO_FANG_KUAN_SHENHE("529","待放款审核"),                //----小可放款审核
     DAI_FANG_KUAN("60","待放款"),                             //----待放款，整合到待放款列表
     YI_FANG_KUAN("61","已放款"),
     HUAN_KUAN_ZHONG("70","还款中"),
     JIE_QING("102","结清"),
     YI_WAN_CHENG("90","已完成"),
     KE_HU_FANG_QI("81","客户放弃"),
     HE_TONG_JU_JUE("57","签约合同审核拒绝"),
     DAI_HUA_KOU("91","待划扣"),
     YI_HUA_HOU("92","已划扣"),
     DAI_HE_TONG_ZHI_ZUO_SHEN_HE("51","待合同制作审核"),                      //待用
     TI_QIAN_JIE_QING("101","提前结清");
     
     private String text;
      
     private String message;
     
     CarOperation(String text,String message){
         this.text = text;
         this.message = message;
     }
     public String getText(){
         return text;
     }
     
     public String getMessage(){
         return message;
     }
     
     public static CarOperation fromStr(String text) {
         if (text != null) {
           for (CarOperation state : CarOperation.values()) {
             if (text.equalsIgnoreCase(state.text)) {
               return state;
             }
           }
         }
         return TEMPSAVE;
     }
}
