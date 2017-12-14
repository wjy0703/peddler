package cn.com.cucsi.app.dao.messageBox;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.messageBox.MessageBox;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 消息对象的泛型DAO.
 * 
 * @author 马道永
 */
@Component
public class MessageBoxDao extends HibernateDao<MessageBox, Long> {
	
}
