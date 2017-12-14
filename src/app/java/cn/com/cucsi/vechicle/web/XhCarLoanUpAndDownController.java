package cn.com.cucsi.vechicle.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.service.XhCarUpAndDownManager;
import cn.com.cucsi.vechicle.util.CarOperation;

@Controller
@RequestMapping(value = "/carFiles")
public class XhCarLoanUpAndDownController {

    private static final String PATH = "carFiles";

    private Logger logger = LoggerFactory.getLogger(XhCarLoanUpAndDownController.class);

    @Autowired
    private XhCarUpAndDownManager xhCarUpAndDownManager;

    @RequestMapping(value = "/applyUploadPage/{Id}")
    public String applyUploadPage(Model model, @PathVariable("Id") Long id) {
        model.addAttribute("upType", UploadFileType.APPLY_UPLOAD.getText());
        return uploadPage(model,id);
    }
    

    /**
     * 上传合同资料，与上面方法相同
     *
     * @param request
     * @param model
     * @param id
     * @return
     * @author xjs
     * @date 2013-10-17 上午10:11:51
     */
    @RequestMapping(value = "/contactUploadPage/{Id}")
    public String contactUploadPage( Model model, @PathVariable("Id") Long id) {
        model.addAttribute("upType", UploadFileType.CONTRACT_UPLOAD.getText());
        return uploadPage(model,id);
    }
    
    /**
     * 补传车借资料
     *
     * @param request
     * @param model
     * @param id
     * @return
     * @author xjs
     * @date 2013-10-14 下午2:58:27
     */
    @RequestMapping(value = "/applyUploadPageAny/{Id}")
    public String applyUploadPageAny(HttpServletRequest request, Model model, @PathVariable("Id") Long id) {
        //代表是补传
        model.addAttribute("additional", "true");
        model.addAttribute("upType", UploadFileType.ADDITIONAL_UPLOAD.getText());
        return applyUploadPage(model,id);
    }

    /**
     * 上传车贷资料 只上传文件不做数据库操作
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/upLoadFile")
    public void upLoadFile(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Map<String, String>> fileName;
        try {
            fileName = PropertiesUtils.upFileFullFunctions(request, PATH);
            ServletUtils.renderJson(response, fileName);
        } catch (Exception e) {
            ServletUtils.renderJson(response, "0");
        }
    }

    /**
     * 上传授信资料写数据库
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/upLoadFileBack")
    public void upLoadFileBack(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 对应的车贷申请ID
        Long id = Long.parseLong(request.getParameter("id"));
        // 上传的文件名
        String files = request.getParameter("fileName");
        // 上传类型
        String upType = request.getParameter("upType");
        
        //是否补传
        
        String additional = request.getParameter("additional");
        List<Map<String, String>> fileNames = P2PJsonUtils.filestrToMap(files);
        CarOperation operation = CarOperation.UPLOAD_FILE;
        if(StringUtils.hasText(additional))
            operation = CarOperation.UPLOAD_FILE_AGAIN;
        xhCarUpAndDownManager.saveFiles(id, fileNames, upType,operation,PATH);
        ServletUtils.renderJson(response, "1");
    }
    
    /**
     * 进入下载页面
     *
     * @param id       车借申请的ID
     * @param type     下载文件类型 与 本类中的UploadFileType的text值相对应 
     * @param request  
     * @param response
     * @param model
     * @return
     * @author xjs
     * @date 2013-10-8 下午12:27:09
     */
    @RequestMapping(value = "/downLoadFilePage/{id}/{type}")
    public String downLoadFilePage(@PathVariable Long id, @PathVariable String type, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<XhUploadFiles> files = xhCarUpAndDownManager.getFiles(id,type);  
        for (Iterator<XhUploadFiles> iterator = files.iterator(); iterator.hasNext();) {
            XhUploadFiles xhUploadFiles = iterator.next();
            xhUploadFiles.setFlag(UploadFileType.fromStr(xhUploadFiles.getFlag()).getMessage());
        }
        model.addAttribute("files",files);
        return "xhCarLoan/files/downloadCarFile";
    }
    
    
    /**
     * 下载文件
     *
     * @param Id
     * @param request
     * @param response
     * @author xjs
     * @date 2013-10-8 下午12:28:27
     */
    @RequestMapping(value = "/downloadFile/{Id}")
    public void downLoadFile(@PathVariable Long Id, HttpServletRequest request, HttpServletResponse response) {
        XhUploadFiles xhUploadFiles = xhCarUpAndDownManager.getXhUploadFiles(Id);
        String base = InitSetupListener.filePath + xhUploadFiles.getFilepath();
        String fileName = xhUploadFiles.getNewfilename();
        String filePath = base + File.separator;
        String downLoadPath = filePath + fileName;
        FileUtil.downLoad(downLoadPath, xhUploadFiles.getFilename(), request, response);
    }
    
    /**
     * 返回上传文件地址
     *
     * @param model
     * @param id
     * @return
     * @author xjs
     * @date 2013-10-17 上午10:13:17
     */
    private String uploadPage(Model model, Long id) {
        model.addAttribute("upUrl", "carFiles/upLoadFile");
        model.addAttribute("id", id);
        return "xhCarLoan/files/upLoadCarFile";
    }
    
    

    private static enum UploadFileType {
        APPLY_UPLOAD("1", "申请资料"),
        CONTRACT_UPLOAD("2", "合同资料"),
        ADDITIONAL_UPLOAD("3","补充资料");

        private String message;

        private String text;

        UploadFileType(String text, String message) {
            this.text = text;
            this.message = message;
        }

        public String getText() {
            return text;
        }

        public String getMessage() {
            return message;
        }

        public static UploadFileType fromStr(String text) {
            if (text != null) {
                for (UploadFileType state : UploadFileType.values()) {
                    if (text.equalsIgnoreCase(state.text)) {
                        return state;
                    }
                }
            }
            return APPLY_UPLOAD;
        }
    };
}
