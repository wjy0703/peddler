package cn.com.cucsi.app.rmi.impl;

import cn.com.cucsi.app.rmi.IHessianService;
import cn.com.cucsi.app.web.InitSetupListener;

import com.caucho.hessian.server.HessianServlet;

public class HessianServiceImpl extends HessianServlet implements IHessianService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3542828852435933947L;

	@Override
	public boolean attrSync(String coding) {
		boolean isSuccess = true;
		try{
			InitSetupListener.replaceAttr2Map(coding);
		}catch(Exception e){
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}

}
