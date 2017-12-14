package cn.com.cucsi.app2.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyDetail;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyItem;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyMain;
import cn.com.cucsi.app2.service.xhcf.XhCjksqSurveyDetailsManager;
import cn.com.cucsi.app2.service.xhcf.XhCjksqSurveyItemManager;
import cn.com.cucsi.app2.service.xhcf.XhCjksqSurveyMainManager;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/jksqSurvey")
public class XhCjksqSurveyController {
	@Autowired
	private XhCjksqSurveyDetailsManager surveyDetailsManager;
	
	@Autowired
	private XhCjksqSurveyItemManager surveyItemManager;
	
	@Autowired
	private XhCjksqSurveyMainManager surveyMainManager;
	
	@Autowired
	private AttrCacheManager attrCacheManager;
	@Autowired
	private BaseInfoManager baseInfoManager;
	
	@RequestMapping(value="/addSurvey/{Id}",method=RequestMethod.GET)
	public String addSurvey(@PathVariable Long Id,Model model){
		model.addAttribute("jsqkId",Id);
		List<Map<String,Object>> homeCheckBoxs=attrCacheManager.getAttrByCoding("survey_home");
		List<Map<String,Object>> officeCheckBoxs=attrCacheManager.getAttrByCoding("survey_office");
		model.addAttribute("homeCheckBoxs",homeCheckBoxs);
		model.addAttribute("officeCheckBoxs",officeCheckBoxs);
		return "jksq/newapply/survey/jksqSurveyInput";
	}
	
	@RequestMapping(value="/saveSurvey",method=RequestMethod.POST)
	public String saveSurvey(@ModelAttribute("xhCjksqSurveyMain") XhCjksqSurveyMain surveyMain,
									HttpServletRequest request,HttpServletResponse response){
		//取surveyMain下surveyItems集合进行循环用于存放关联关系对象
		List<XhCjksqSurveyItem> surveyItems=surveyMain.getXhCjksqSurveyItems();
		for(int i=0;i<surveyItems.size();i++){
			XhCjksqSurveyItem surveyItem=surveyItems.get(i);
			List<XhCjksqSurveyDetail> surveyDetails=surveyItem.getXhCjksqSurveyDetails();
			 // 判断surveyItem下的surveyDetails是否为空
			 // 为空删除surveyMain集合中的surveyItem元素
			if(surveyDetails!=null){
				for(int j=0;j<surveyDetails.size();j++){
					XhCjksqSurveyDetail surveyDetail = surveyDetails.get(j);
                    if(StringUtils.hasText(surveyDetail.getItemsName())){
                        surveyDetail.setXhCjksqSurveyItem(surveyItem);
                    }else{
                        surveyDetails.remove(j);
                        j --;
                    }
				}//end for
				surveyItem.setXhCjksqSurveyMain(surveyMain);
			}else{
				surveyMain.getXhCjksqSurveyItems().remove(i);
			}
		}//end for
		surveyMainManager.saveXhCjksqSurveyMain(surveyMain);
		DwzResult success = new DwzResult("200","保存成功","rel_listLoanApplyByState","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
     * 
     * 外访信息查看
     * @param surveyMain
     * @param request
     * @param response
     * @date 2013-10-26 上午10:13:55
     */
    @RequestMapping(value = "/editSurvey/{jksqId}", method = RequestMethod.GET)
    public String editSurvey(@PathVariable Long jksqId,Model model,
    									HttpServletRequest request) {
    	
        XhCjksqSurveyMain surveyMain = surveyMainManager.
        					getXhCjksqSurveyMainFromJksqId(jksqId);
        
       String look=request.getParameter("look");
       
        for (XhCjksqSurveyItem item :surveyMain.getXhCjksqSurveyItems()){
            if(item.getSurveyType() == 0){
                model.addAttribute("surveyHome",item);
            }else{
                model.addAttribute("surveyOffice",item);
            }
        }
        
        String [] wordTemplate = null;
        if(StringUtils.hasText(surveyMain.getDemandWordTemplate())){
            wordTemplate = surveyMain.getDemandWordTemplate().split(",");
        }
        
        model.addAttribute("wordTemplate",wordTemplate);
        model.addAttribute("surveyMain", surveyMain);
        model.addAttribute("look",look);
        return "jksq/newapply/survey/jksqSurveyLook";
    }
    
    /**
     * 门店保存结果
     *
     * @param surveyMain
     * @param request
     * @param response
     * @author xjs
     * @date 2013-10-26 上午11:09:04
     */
    @RequestMapping(value = "/storeSaveSurvey", method = RequestMethod.POST)
    public void storeSaveSurvey(@ModelAttribute("xhCjksqSurveyMain") XhCjksqSurveyMain surveyMain,
    							HttpServletRequest request, HttpServletResponse response) {
    	
    	String files=request.getParameter("fileName");
    	
    	List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
    	
    	surveyMainManager.uploadFiles(surveyMain.getJksqId(), fileName,
    								"外访反馈","XH_CJKSQ_SURVEY_MAIN");
    	
        surveyMainManager.storeSaveXhCjksqSurveyMain(surveyMain);
        
        DwzResult success = new DwzResult("200", "保存成功",
        				"rel_listJksq", "", "closeCurrent", "");
        
        ServletUtils.renderJson(response, success);
    }
    /**
     * 外访文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/downLoadFiles")
    public String downLoadFiles(HttpServletRequest request,
			HttpServletResponse response) {
    	
    	String file=request.getParameter("file");
    	String[] fileAndType = file.split(",");
    	//0为模版下载
    	if(fileAndType[1].equals("0")){ 
    		int fileIndex=Integer.parseInt(fileAndType[0]);
    		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
    				+ File.separator;
    		
    		String[] wordTemplates = {"实名家庭现场考察表.doc",
    				"实名企业现场考察表.doc","匿名家庭现场考察表.doc","工作单位现场考察表.doc"};
    		
    		String downLoadPath=mouFilePath+wordTemplates[fileIndex];
    		
    		FileUtil.downLoad(downLoadPath,wordTemplates[fileIndex],request,response);
    	}else{
    		
    		XhUploadFiles xhUploadFiles=surveyMainManager.
    							getxhUploadFile(Long.parseLong(fileAndType[0]));
    		String base = InitSetupListener.filePath + xhUploadFiles.getFilepath();
    		
    		String fileName = xhUploadFiles.getNewfilename();
    		String filePath = base + File.separator;
    		String downLoadPath = filePath + fileName;
    		
    		FileUtil.downLoad(downLoadPath, xhUploadFiles.getFilename(), request,
    				response);
    	}
    	return null;
    }
    /**
     * 外访反馈信息查看
     * @param jksqId
     * @param model
     * @return
     */
    @RequestMapping(value="/lookSurveyBackLook/{jksqId}")
    public String lookSurveyBackLook(@PathVariable Long jksqId,Model model){
    	 XhCjksqSurveyMain surveyMain = surveyMainManager.getXhCjksqSurveyMainFromJksqId(jksqId);
    	 for (XhCjksqSurveyItem item :surveyMain.getXhCjksqSurveyItems()){
             if(item.getSurveyType() == 0){
                 model.addAttribute("surveyHome",item);
             }else{
                 model.addAttribute("surveyOffice",item);
             }
         }
    	 Map<String,Object> filters = new HashMap<String,Object>();
    	 filters.put("fileOwner", "XH_CJKSQ_SURVEY_MAIN");
    	 filters.put("mainId", jksqId);
    	 List<XhUploadFiles> files=baseInfoManager.findXhUploadFiles(filters);
         model.addAttribute("surveyMain", surveyMain);
         model.addAttribute("surveyBackfiles",files);
    	return "jksq/newapply/survey/jksqSurveyBackLook";
    }
    
    /**
     * 文件上传页面
     * @param Id
     * @param request
     * @param model
     * @return
     */
	@RequestMapping(value = "/upLoadSurveyBackInfo/{Id}")
	public String newUpLoadAccredited(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("upUrl", "jksqSurvey/upLoadFile");
		return "jksq/newapply/survey/upLoadSurveyBackInfo";
	}
	
	/**
	 * 上传外访反馈资料 只上传文件不做数据库操作
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadFile")
	public void upLoadFile(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Map<String, String>> fileName;
		try {
			fileName = PropertiesUtils.upFileFullFunctions(request,"surveyBackFiles");
			ServletUtils.renderJson(response, fileName);
		} catch (Exception e) {
			ServletUtils.renderJson(response, "0");
		}
	}
}
