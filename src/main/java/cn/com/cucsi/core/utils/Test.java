package cn.com.cucsi.core.utils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fm = "hfskajf.doc";
		String newfm = DBUUID.getID();
		String lastFm = newfm + fm.substring(fm.lastIndexOf("."), fm.length());
		System.out.println(lastFm);
	}

}
