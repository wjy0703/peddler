package cn.com.cucsi.app2.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.excel.utils.LoanAuditXLS;
import cn.com.cucsi.app.service.baseinfo.AddressManager;
import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.MetaDataTypeEnum;
import cn.com.cucsi.app2.service.xhcf.XhNewJksqManager;
import cn.com.cucsi.core.test.utils.DataUtils;

/**
 * 
 * @author 
 * @date 2012-12-14
 */
@Controller
@RequestMapping(value = "/loan")
public class XhNewCreditAuditExportController {
   
    @Autowired
    private XhNewJksqManager xhNewJksqManager;
    
    @Autowired
    AttrCacheManager attrCacheManager;
    
    @Autowired
    AddressManager addressManager;

    /**
     * 初始化信审数据 MDY 2013-08-29
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportAuditXls/{Id}")
    public void initAuditInfo(@PathVariable Long Id, HttpServletRequest request,HttpServletResponse response) {
        String filePath = InitSetupListener.filePath +  DataUtils.randomName(Id+"") + ".xls";
        LoanAuditXLS loanAuditXLS = new LoanAuditXLS();
        XhJksq xhJksq = xhNewJksqManager.getXhJksq(Id);
        String jkType = attrCacheManager.getAttrName(MetaDataTypeEnum.PRODUCT_TYPE.toString(), xhJksq.getJkCycle());
        xhJksq.setJkCycle(jkType);
        String cityName = "";
        if(xhJksq.getCity() != null)
            cityName = addressManager.getCityNameById(xhJksq.getCity().getId());
        xhJksq.setBackup07(cityName);
        loanAuditXLS.generateFile(xhJksq,filePath);
        sendUserFile(response,filePath,xhJksq);
    }
    /**
     * 输出文件
     *
     * @param response
     * @param filePath
     * @author xjs
     * @date 2013-9-24 上午8:55:37
     */
    private void sendUserFile(HttpServletResponse response, String filePath,XhJksq xhJksq) {
        response.setContentType("application/xls");   
        String fileName = "审核意见书";
        try {
            fileName = java.net.URLEncoder.encode("审核意见书.xls", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment; filename=" +  fileName);
        FileInputStream is = null;
        try {
            is = new FileInputStream(filePath);
            IOUtils.copy(is, response.getOutputStream());
            File fileTemp = new File(filePath);
            fileTemp.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if( is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       
    }
   
   

}
