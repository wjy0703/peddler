package cn.com.cucsi.vechicle.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;

/**
 * 车辆状态类，用来控制状态之间的转移操作
 * 
 * @author xjs
 * @date 2013-9-29 下午12:17:37
 */
public class CarStateExchange {
    
    private static Logger logger = LoggerFactory.getLogger(CarStateExchange.class);
    
    //**可编辑的状态
    private static List<String> editableState;
    //
    private static List<String> agreementState;
    //
    private static List<String> refuseState;
    
    
    static{
        editableState = new ArrayList<String>();
        editableState.add(CarState.TEMPSAVE.getText());
        editableState.add(CarState.NEED_TOGETHER.getText());
        editableState.add(CarState.UPLOAD_FILE.getText());
        editableState.add(CarState.FIRST_AUDIT_BACK.getText());
        editableState.add(CarState.FIRST_AUDIT.getText());
    	agreementState= new ArrayList<String>();
    	agreementState.add(CarState.DAI_SHANG_CHUAN_HETONG.getText());
    	agreementState.add(CarState.DAI_TI_JIAO_FANG_KUAN_SHENHE.getText());
    	agreementState.add(CarState.DAI_FANG_KUAN.getText());
    	agreementState.add(CarState.YI_FANG_KUAN.getText());
    	agreementState.add(CarState.HUAN_KUAN_ZHONG.getText());
    	agreementState.add(CarState.JIE_QING.getText());
    	agreementState.add(CarState.YI_WAN_CHENG.getText());
    	agreementState.add(CarState.DAI_HUA_KOU.getText());
    	agreementState.add(CarState.YI_HUA_HOU.getText());
    	agreementState.add(CarState.TI_QIAN_JIE_QING.getText());
    	refuseState =  new ArrayList<String>();
    	refuseState.add(CarState.FIRST_AUDIT_REFUSE.getText());
    	refuseState.add(CarState.SECOND_AUDIT_REFUSE.getText());
    	refuseState.add(CarState.FINAL_AUDIT_REFUSE.getText());
    	refuseState.add(CarState.KE_HU_FANG_QI.getText());
    	
    	
    }
    /**
     * 
     * 
     * @param apply
     * @param option
     * @return
     * @author xjs
     * @date 2013-9-29 下午12:18:54
     */
    public static CarState getState(final XhCarLoanApply apply, CarOperation option) {
        //日志info级别输出
        if(logger.isInfoEnabled()){
            logger.info(String.format("修改申请ID为%s的内容，操作是:%s  当前状态为:%s ", apply.getId(),option.getText(),apply.getState()));
        }
        // 如果是暂存操作，则一般不改变原来的状态，只有当原来状态为null的时候才把状态改为暂存
        String state = apply.getState();
        //option为VechicleOperation.TEMPSAVE 和 VechicleOperation.SUBMIT均为页面修改了申请的操作
        //其他操作直接将状态设置为VechicleState都应的状态即可
        if (option == CarOperation.TEMPSAVE) {
            if (isNewApply(apply))                                      // 用户的首次暂存操作
                return CarState.TEMPSAVE;
            else
                return CarState.fromStr(state);
        }else if (option == CarOperation.SUBMIT) {
            if (isNewApply(apply)) {                                    // 用户的首次提交操作
                if ("是".equals(apply.getTogetherPerson())) {               // 有共借人，填写共借人
                    return CarState.NEED_TOGETHER;
                } else {                                                    // 首次提交后转入初审
                    return CarState.FIRST_AUDIT;
                }
            }else{
                if(state != null){
                    //日志的debugger级别输出
                   if(logger.isDebugEnabled())
                       logger.debug(String.format("修改申请ID为%s的内容，操作是:%s  当前状态为:%s ", apply.getId(),option.getText(),apply.getState()));
                   int indexOfDotB = state.indexOf(".B");
                   if(indexOfDotB > 0 ){//有退回操作的编辑，返回到初始页面
                       return CarState.fromStr(state.substring(0,indexOfDotB));
                   }else{
                       if(CarState.fromStr(state) == CarState.TEMPSAVE){               //暂存后的首次提交
                           if ("是".equals(apply.getTogetherPerson())) {               // 有共借人，填写共借人
                               return CarState.NEED_TOGETHER;
                           } else {                                                    // 首次提交后转入初审
                               return CarState.FIRST_AUDIT;
                           }
                       }else                                                          //其他情况的编辑操作不修改状态
                           return  CarState.fromStr(state);
                   }
                }else{
                    logger.error("执行时出现错误，车贷出现异常状态");
                    return CarState.TEMPSAVE;
                }
            }
        }else if(option == CarOperation.FIRST_AUDIT){
            if(CarState.fromStr(state) == CarState.SECOND_AUDIT_BACK)
               //如果是复审退回的则转向复审
               return CarState.SECOND_AUDIT;
            else
                //初审后转入上传资料
                return CarState.UPLOAD_FILE;
        }else if(option == CarOperation.UPLOAD_FILE){//上传资料后转入复审
            if(CarState.fromStr(state) == CarState.UPLOAD_FILE){
                return CarState.SECOND_AUDIT;
            }else{// if(CarState.fromStr(state) == CarState.DAI_QUE_DING_QIAN_SHU){
                return CarState.DAI_TI_JIAO_FANG_KUAN_SHENHE;
            }
        }else if(option == CarOperation.UPLOAD_FILE_AGAIN){
            //相对于补传,返回原状态
            return CarState.fromStr(state);
        }else if(option == CarOperation.SECOND_AUDIT){
            //相对于补传,返回原状态
            return CarState.FINAL_AUDIT;
        }else if(option == CarOperation.FINAL_AUDIT){
            return CarState.DAI_QUE_REN_HE_TONG_DKLL;
        }
        else{
            return CarState.fromStr(option.getText());
        }
    }
    /**
     * 判断是否是新的申请
     *
     * @param apply
     * @return
     * @author xjs
     * @date 2013-10-17 下午3:54:58
     */
    private static boolean isNewApply(XhCarLoanApply apply) {
        return apply.getId() == null || null == apply.getState();
    }

    /**
     * 取得列表 都应的列表状态
     *
     * @param type  例如初审，复审，终审，借用了CarOperation这个枚举类。加入一个新的枚举类可能更好。
     * @return
     * @author xjs
     * @date 2013-10-10 下午4:08:27
     */
    public static List<String> getStateList(CarOperation type){
        List<String> states = new ArrayList<String>();
        if(type == CarOperation.FIRST_AUDIT){
            states.add(CarState.FIRST_AUDIT.getText());
            states.add(CarState.SECOND_AUDIT_BACK.getText());
            return states;
        }else if(type == CarOperation.SECOND_AUDIT){
            states.add(CarState.SECOND_AUDIT.getText());
            states.add(CarState.FINAL_AUDIT_BACK.getText());
            return states;
        }else if(type == CarOperation.FINAL_AUDIT){
            states.add(CarState.FINAL_AUDIT.getText());
            return states;            
        }
        return states;
    }
    /**
     * 判断是否可以编辑，主要是根据状态来判断
     *
     * @param item   查询的借款申请的实体对应的map，主要用到状态，考虑到可扩展性，因此传入了整个map。
     * @return
     * @author xjs
     * @date 2013-10-14 下午3:24:33
     */
    public static boolean isEditable(Map<String, Object> item) {
        Object state = item.get("STATE");
            if(state != null && StringUtils.hasText(state.toString())){
                return editableState.contains(state.toString());
            }
        return false;
    }
    
    /**
     * 判断是否可以进行协议查看，主要是根据状态来判断
     *
     * @param item   查询的借款申请的实体对应的map，主要用到状态，考虑到可扩展性，因此传入了整个map。
     * @return
     * @author fjh
     * @date 2013-10-23 下午4:45
     */
    
    public static boolean isAgreement(Map<String, Object> item) {
        Object state = item.get("STATE");
            if(state != null && StringUtils.hasText(state.toString())){
                return agreementState.contains(state.toString());
            }
        return false;
    }
    
    /**
     * 判断是否为审核拒绝以及客户放弃状态
     *
     * @param item   查询的借款申请的实体对应的map，主要用到状态，考虑到可扩展性，因此传入了整个map。
     * @return
     * @author fjh
     * @date 2013-10-23 下午4:45
     */
    
    public static boolean isRefuse(Map<String, Object> item) {
        Object state = item.get("STATE");
            if(state != null && StringUtils.hasText(state.toString())){
                return refuseState.contains(state.toString());
            }
        return false;
    }
}
