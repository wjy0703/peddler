package cn.com.cucsi.cooperate.service.webServiceClientManage;

import java.util.List;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.cooperate.web.CooperateInitSetup;

/**
 * web service client.
 * 
 * @author mdy
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class ServiceClientManager extends PublicService{

	private static Logger logger = LoggerFactory.getLogger(ServiceClientManager.class);
	
	private XhJksqManager loanApplyManager;
	
	private JdbcDao jdbcDao;
	
	/**
	 * 是否线上P2P验证，通过进行client发送
	 * @param jksqId
	 * @return 0:不是线上p2p，不需要发送；1：发送成功；2：发送失败
	 */
	public Integer serviceClient(Long jksqId){
		logger.info("客户端调用开始");
		Integer infoCode = 0;
		if(isSend(jksqId)){
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient(CooperateInitSetup.webServiceAdd);
			//服务器安全验证
			//client.getOutInterceptors().addAll(ClientCallback.getClientUserInfo("chpadmin"));
			Object[] res = null;
			try {
				String state = "";
				res = client.invoke("setTargetInfo", jksqId.toString());
				boolean isOk = (Boolean)res[0];
				if(isOk){
					infoCode = 1;
					state = "1000";
				}else{
					infoCode = 2;
					state = "1001";
				}
				updateJksqState(jksqId, state);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("客户端调用结果："+(Boolean)res[0]);
		}
		return infoCode;
	}
	
	public void updateJksqState(Long id, String state){
		logger.info("回写借款申请状态");
		XhJksq jksq = loanApplyManager.getXhJksq(id);
		jksq.setState(state);
		loanApplyManager.saveXhJksq(jksq);
	}
	
	private boolean isSend(Long jksqId){
		logger.info("验证是否线上P2P");
		boolean isRes = false;
		String sql = "select t.id from xh_jksq t, xh_xydkzx x where t.id = "+jksqId+" and t.xydkzx_id = x.id and x.is_p2p = '是'";
		List<Map<String, Object>> list = jdbcDao.searchByMergeSql(sql);
		if(list.size() > 0){
			isRes = true;
		}
		logger.info("验证线上P2P结果："+isRes);
		return isRes;
	}

	@Autowired
	public void setLoanApplyManager(XhJksqManager loanApplyManager) {
		this.loanApplyManager = loanApplyManager;
	}

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

}
