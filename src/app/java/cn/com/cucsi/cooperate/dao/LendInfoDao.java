package cn.com.cucsi.cooperate.dao;

import org.springframework.stereotype.Component;

import cn.com.cucsi.cooperate.entity.p2p.P2pLendInfo;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * DAO类.
 * 
 * @author MDY
 */
@Component
public class LendInfoDao extends HibernateDao<P2pLendInfo, Long> {}
