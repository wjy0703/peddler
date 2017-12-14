/**
 * @author jiangxd
 * create at 2011-11-04
 */
package cn.com.cucsi.app.web.intf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.Constants;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/services")
public class PhoneCfgController {
	private Logger logger = Logger.getLogger(PhoneCfgController.class);
	
	@RequestMapping(value="/spcfg")
	public String spcfg(HttpServletRequest request, HttpServletResponse response){
		final String host=Constants.sipserver;//"10.64.4.234";
		final String sktHost=Constants.sktHost;
		final String sktPort=Constants.sktPort;
		final String sktUser=Constants.sktUser;
		final String sktPasswd=Constants.sktPasswd;

		String user=request.getParameter("userid");
		String auth=user;
		String pass=auth==null?"":EncodeUtils.encodePassword(auth);		

		logger.debug("begin call spcfg");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");

		sb.append("<config>");
		sb.append("\t<sipHostAddr>" + host + "</sipHostAddr>\n");
		sb.append("\t<sipHostPort>5060</sipHostPort>\n");
		sb.append("\t<sipLocalPortMix>5061</sipLocalPortMix>\n");
		sb.append("\t<sipUserName>" + user + "</sipUserName>\n");
		sb.append("\t<sipAuthName>" + auth + "</sipAuthName>\n");
		sb.append("\t<sipAuthPasswd>crypto(1," + pass + ")</sipAuthPasswd>\n");
		sb.append("\t<autoAnswer>false</autoAnswer>\n");
		sb.append("\t<autoConference>false</autoConference>\n");
		sb.append("\t<dndCodeOn>*78</dndCodeOn>\n");
		sb.append("\t<dndCodeOff>*79</dndCodeOff>\n");
		
		sb.append("\t<sktHost>" + sktHost + "</sktHost>\n");
		sb.append("\t<sktPort>" + sktPort + "</sktPort>\n");
		sb.append("\t<sktUser>" + sktUser + "</sktUser>\n");
		sb.append("\t<sktPasswd>" + sktPasswd + "</sktPasswd>\n");
	
		sb.append("\t<keepAudioOpen>true</keepAudioOpen>\n");
		sb.append("\t<resample441k>false</resample441k>\n");
		sb.append("\t<useJavaSound>true</useJavaSound>\n");
		sb.append("\t<speakerMode>false</speakerMode>\n");
		sb.append("\t<speakerThreshold>1000</speakerThreshold>\n");
		sb.append("\t<speakerDelay>250</speakerDelay>\n");
		sb.append("\t<audioInput>&lt;default&gt;</audioInput>\n");
		sb.append("\t<audioInputID>0</audioInputID>\n");
		sb.append("\t<audioOutput>&lt;default&gt;</audioOutput>\n");
		sb.append("\t<audioOutputID>0</audioOutputID>\n");
		sb.append("\t<swVolForce>false</swVolForce>\n");
		
		sb.append("</config>\n");
		logger.debug(sb.toString());
		
		ServletUtils.renderXml(response, sb.toString());
		
    	return null;
    	
    	
    }
	
}
