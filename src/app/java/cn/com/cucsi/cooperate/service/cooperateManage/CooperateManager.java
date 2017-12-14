package cn.com.cucsi.cooperate.service.cooperateManage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.cooperate.dao.ContractInfoDao;
import cn.com.cucsi.cooperate.dao.LendInfoDao;

/**
 * 线上合作业务类.
 * 
 * @author mdy
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class CooperateManager extends PublicService{

	private static Logger logger = LoggerFactory.getLogger(CooperateManager.class);
	
	private ContractInfoDao contractInfoDao;
	
	private LendInfoDao lendInfoDao;

	@Autowired
	public void setContractInfoDao(ContractInfoDao contractInfoDao) {
		this.contractInfoDao = contractInfoDao;
	}

	@Autowired
	public void setLendInfoDao(LendInfoDao lendInfoDao) {
		this.lendInfoDao = lendInfoDao;
	}
	
	
}
