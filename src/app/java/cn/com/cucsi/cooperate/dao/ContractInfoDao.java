package cn.com.cucsi.cooperate.dao;

import org.springframework.stereotype.Component;

import cn.com.cucsi.cooperate.entity.p2p.P2pContractInfo;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * DAO类.
 * 
 * @author MDY
 */
@Component
public class ContractInfoDao extends HibernateDao<P2pContractInfo, Long> {}
