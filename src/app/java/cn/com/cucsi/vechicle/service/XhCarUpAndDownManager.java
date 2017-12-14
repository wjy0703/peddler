package cn.com.cucsi.vechicle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhUploadFilesDao;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyComplementDao;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyDao;
import cn.com.cucsi.vechicle.dao.XhCarMessageDao;
import cn.com.cucsi.vechicle.dao.XhCarStateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarState;
import cn.com.cucsi.vechicle.util.CarStateExchange;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarUpAndDownManager {
    private Logger logger = LoggerFactory.getLogger(XhCarUpAndDownManager.class);
  
    @Autowired
    XhCarLoanApplyManager xhCarLoanApplyManager;
    
    @Autowired
    XhUploadFilesDao xhUploadFilesDao;
    
    @Autowired
    private XhCarLoanApplyDao xhCarLoanApplyDao;
    
    
    
    /**
     * 
     * 保存上传文件记录
     *
     * @param id                        车贷申请ID
     * @param fileNames                 上传文件列表
     * @param upType                    上传类型
     * @param operation                 操作类别，补传等                
     * @param path                      文件保存路径                
     * @author xjs
     * @date 2013-10-8 上午10:22:40
     */
    public  void saveFiles(Long id, List<Map<String, String>> fileNames, String upType, CarOperation operation,String path) {
        for(Map<String, String> file : fileNames){
            XhUploadFiles xhUploadFiles = new XhUploadFiles();
            xhUploadFiles.setFilename(file.get("fileName"));
            xhUploadFiles.setFilepath(path);
            xhUploadFiles.setNewfilename(file.get("newFileName"));
            xhUploadFiles.setFileOwner("XH_CAR_LOAN_APPLY");
            xhUploadFiles.setMainId(id);
            xhUploadFiles.setFlag(upType);
            xhUploadFilesDao.save(xhUploadFiles);
        }
        //上传后修改状态
		if (operation == CarOperation.UPLOAD_FILE) {
			
			XhCarLoanApply xhCarLoanApply = xhCarLoanApplyDao.get(id);

			// 展期申请为上传资料
			if (xhCarLoanApply.getState().equals( CarState.UPLOAD_FILE.getText())) {
				xhCarLoanApplyManager.changeStateAndWriteHistory(xhCarLoanApply, operation, operation.getMessage());
			} else {
				// 展期申请为上传合同的 --现在有两种上传的可能性 这里为上传合同
				String oldState = xhCarLoanApply.getState();
				if(!isExtensionApply(xhCarLoanApply)){
					xhCarLoanApply.setState(CarState.DAI_TI_JIAO_FANG_KUAN_SHENHE.getText());
					xhCarLoanApplyManager.saveXhCarLoanApply(xhCarLoanApply);
					xhCarLoanApplyManager.saveHistory(oldState, xhCarLoanApply,	CarOperation.DAI_TI_JIAO_FANG_KUAN_SHENHE,
							CarOperation.DAI_TI_JIAO_FANG_KUAN_SHENHE.getMessage());
				}else{
					xhCarLoanApply.setState(CarState.DAI_TI_JIAO_HUA_KOU_SHENHE.getText());
					xhCarLoanApplyManager.saveXhCarLoanApply(xhCarLoanApply);
					xhCarLoanApplyManager.saveHistory(oldState, xhCarLoanApply,	CarOperation.DAI_TI_JIAO_HUA_KOU_SHENHE,
					    CarOperation.DAI_TI_JIAO_HUA_KOU_SHENHE.getMessage());
				}
			}
		}
        
    }


    /**
     * 判断是否是展期申请
     * @param xhCarLoanApply
     * @return
     */
    private boolean isExtensionApply(XhCarLoanApply xhCarLoanApply) {
    	
		return "1".equals(xhCarLoanApply.getIsExtension());
	}



	public List<XhUploadFiles> getFiles(Long id, String type) {
        Map<String, Object> filters = new HashMap<String, Object>();
        
        filters.put("mainId", id);
        //type为all代表下载所有文件
        if(!"all".equals(type))
            filters.put("flag", type);
        filters.put("fileOwner", "XH_CAR_LOAN_APPLY");
        return xhUploadFilesDao.findXhUploadFiles(filters);
    }



    public XhUploadFiles getXhUploadFiles(Long id) {
        return xhUploadFilesDao.get(id);
    }



    
    
    
}
