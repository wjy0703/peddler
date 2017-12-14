package cn.com.cucsi.cooperate.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class DynamicClient {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String wsUrl = "http://210.51.3.64:9090/ChpCooperateService/services/CooperateManage?wsdl";
		String dataInfo = "{\"TransInfo\":{\"tranCode\":\"1001\",\"tranType\":\"00\",\"tranSerial\":\"201309101001000001\",\"tranTime\":\"2013-09-01 12:12:00\",\"source\":\"Web\",\"operator\":\"600015\"},\"BodyInfo\":{\"BRInfo\":{\"sid\":\"10000\",\"cSSid\":\"0\",\"name\":\"����\",\"sex\":\"M\",\"mobilePhone\":\"18045058888\",\"idType\":\"I\",\"idNumber\":\"230229190000000000\"},\"BRApp\":{\"bRASid\":\"9999\",\"appTime\":\"2013-09-01 12:12:00\",\"bRSid\":\"9998\",\"income\":\"10000\",\"planAmount\":\"100000\"}}}";
		String contractInfo = "{\"BodyInfo\":{\"BRContInfo\":{\"contId\":\"11\",\"bRBSid\": \"55\",\"contNumber\":\"XH-001-130912-00001-001\",\"contUrl\":\"http://www.aaa.com/contnumber/201309221326000001.jpg\",\"contStateUrl\":\"http://www.bbb.com/contnumber/201309221326000002.jpg\",\"contAmount\":\"10000.0\",\"status\":\"1\",\"createTime\":\"2013-09-01 12:12:00\",\"effectTime\":\"2013-09-01 12:12:00\",\"bRId\":\"62\",\"payOutMoney\":\"10000.0\",\"bRSignTime\":\"2013-09-01 12:12:00\",\"investId\":\"11\",\"payInMoney\":\"1000.0\",\"investTime\":\"2013-09-01 12:12:00\",\"bRType\":\"0\",\"bRRate\":\"0.01\",\"iCRate\":\"0.004\",\"term\":\"12\"},\"BRUserInfo\":{\"investId\": \"000001\",\"idType\":\"I\",\"idNum\":\"1003\",\"userName\":\"666666666666666666\",\"bankCode\":\"�й���������\",\"accountBank\":\"����·�й���������\",\"accountNo\":\"666666666666666666\",\"telNo\":\"18700000000\",\"email\":\"qqq@qq.com\"}},\"TransInfo\":{\"operator\":\"600015\",\"source\":\"Web\",\"tranCode\":\"1003\",\"tranSerial\":\"201309101001000001\",\"tranTime\":\"2013-09-01 12:12:00\",\"tranType\":\"00\"}}";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsUrl);
		Object[] res = null;
		try {
			//res = client.invoke("loanApplication", dataInfo);
			//res = client.invoke("setTargetInfo", "55");
			res = client.invoke("loanContractInfo", contractInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println((String) res[0]);
	}

}
