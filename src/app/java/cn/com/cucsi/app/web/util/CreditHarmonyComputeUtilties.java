package cn.com.cucsi.app.web.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ibm.icu.text.DecimalFormat;

/**
 * 信和借贷相关计算工具
 * 
 * @author Songjf
 * 
 */
public class CreditHarmonyComputeUtilties {
	// 计算周期是否按照自然月算，true：是；false：否，按照30天算
	private static boolean dateFlag = true;
	private static int day = 30;

	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date类型转String类型
	 * 
	 * @param date
	 * @return 字符串时间格式
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatDate.format(date);
		Date time = null;
		try {
			time = formatDate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatDate.format(time);
	}

	/**
	 * 根据起始借款日期取出每月还款日 借款合同文件中还款日是每月15或30 公式中不考虑2月一天份 如果是2月份，客户的还款日是在2月份最后一天
	 * 
	 * @param firstDateOfBackMoney
	 * @return
	 */
	public static String getBackMoneyDateOfMonth(String firstDateOfBackMoney) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

		Date date;
		try {
			date = sdf.parse(firstDateOfBackMoney);
			calendar.setTime(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return String.valueOf(calendar.get(Calendar.DATE));
	}

	/**
	 * 根据起始借款日期取出每月还款日 借款合同文件中还款日是每月15或30 需配合getFirstDateOfBackMoney方法获取首个还款日 使用
	 * 
	 * @param firstDateOfBackMoney
	 * @return
	 */
	public static String getZdr(String firstDateOfBackMoney) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

		Date date;
		try {
			date = sdf.parse(firstDateOfBackMoney);
			calendar.setTime(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		String zdr = String.valueOf(calendar.get(Calendar.DATE));
		if (!zdr.equals("15")) {
			zdr = "30";
		}
		return zdr;
	}

	/**
	 * 计算第一个还款日期 ：如果借款日期是15日之前，第一个还款日是本月30日 闰年2月份第一个还款日是本月29日 平年2月份第一个还款日是本月28日,
	 * 如果日期是15日之后，首期还款日为下月15日
	 * 
	 * @param loan_date
	 *            借款日期
	 * @return 第一个还款日
	 */
	public static String getFirstDateOfBackMoney(String loanDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = sdf.parse(loanDate);

			calendar.setTime(date);
			if (calendar.get(Calendar.DATE) < 15) {
				if (calendar.get(Calendar.MONTH) == 1
						&& calendar.isLeapYear(calendar.get(Calendar.YEAR))){
					calendar.set(Calendar.DATE, 29);
				}else if (calendar.get(Calendar.MONTH) == 1
						&& !calendar.isLeapYear(calendar.get(Calendar.YEAR))){
					calendar.set(Calendar.DATE, 28);
				}else{
					calendar.set(Calendar.DATE, 30);
				}
				
			} else if(calendar.get(Calendar.DATE) == 31){
				if (calendar.get(Calendar.MONTH) == 0
						&& calendar.isLeapYear(calendar.get(Calendar.YEAR))){
					calendar.set(Calendar.DATE, 29);
				}else if (calendar.get(Calendar.MONTH) == 0
						&& !calendar.isLeapYear(calendar.get(Calendar.YEAR))){
					calendar.set(Calendar.DATE, 28);
				}else{
					calendar.set(Calendar.DATE, 30);
				}
				if (calendar.get(Calendar.MONTH) < 11) {
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				} else {
					calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
					calendar.set(Calendar.MONTH, 0);
				}
			}
			else{
				calendar.set(Calendar.DATE, 15);
				if (calendar.get(Calendar.MONTH) < 11) {
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				} else {
					calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
					calendar.set(Calendar.MONTH, 0);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (calendar.get(Calendar.YEAR)) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DATE);
	}

	/**
	 * 根据首期还款日期及 借款期限,计算最后一个还款日期
	 * 
	 * @param firstDayOfBackMoney
	 *            首个还款日期
	 * @return 最后一个还款日期
	 */
	public static String getLastDateOfBackMoney(String firstDayOfBackMoney,
			int monthsOfLoan) {
		Date lastDate = getEndTimeByStartTimeAndCount(firstDayOfBackMoney, monthsOfLoan);
		return dateToString(lastDate);
		/*//TODO ----
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");// 2012-11-30
		// 1

		try {
			Date date = sdf.parse(firstDayOfBackMoney);
			calendar.setTime(date);

			if (((calendar.get(Calendar.MONTH) + monthsOfLoan - 1) % 12 == 1)
					&& calendar.get(Calendar.DATE) == 30) {

				calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH)
						+ monthsOfLoan - 1));
				if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
					calendar.set(Calendar.MONTH, 1);
					calendar.set(Calendar.DATE, 29);
				} else if (!calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
					calendar.set(Calendar.MONTH, 1);
					calendar.set(Calendar.DATE, 28);
				}

			} else {
				calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH)
						+ monthsOfLoan - 1));

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (calendar.get(Calendar.MONTH) != 1
				&& (calendar.get(Calendar.DATE) == 29 || calendar
						.get(Calendar.DATE) == 28)) {

			calendar.set(Calendar.DATE, 30);
		}

		String returnStringDate = (calendar.get(Calendar.YEAR)) + "-"
				+ ((calendar.get(Calendar.MONTH)) + 1) + "-"
				+ calendar.get(Calendar.DATE);// 12月份

		return returnStringDate;*/

	}

	/**
	 * 根据当前还款日期获取下一个还款日期
	 * 
	 * @param firstDayOfBackMoney
	 *            首个还款日期
	 * @return 最后一个还款日期
	 */
	public static String getNextDateOfBackMoney(GregorianCalendar calendar) {

		try {

			if(calendar.get(Calendar.DATE) > 27){
				if(calendar.get(Calendar.MONTH) == 0 ){
					if (calendar.isLeapYear(calendar.get(Calendar.YEAR))){
						calendar.set(Calendar.DATE, 29);
					}else if (!calendar.isLeapYear(calendar.get(Calendar.YEAR))){
						calendar.set(Calendar.DATE, 28);
					}else{
						calendar.set(Calendar.DATE, 30);
					}
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				}else{
					if (calendar.get(Calendar.MONTH) < 11) {
						calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
					} else {
						calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
						calendar.set(Calendar.MONTH, 0);
					}
					calendar.set(Calendar.DATE, 30);
				}
			}else{
				calendar.set(Calendar.DATE, 15);
				if (calendar.get(Calendar.MONTH) < 11) {
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				} else {
					calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
					calendar.set(Calendar.MONTH, 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String returnStringDate = (calendar.get(Calendar.YEAR)) + "-"
				+ ((calendar.get(Calendar.MONTH)) + 1) + "-"
				+ calendar.get(Calendar.DATE);// 12月份

		return returnStringDate;

	}
	/**
	 * 计算贷款剩余期限（月份）
	 * 
	 * @param firstDateOfBackMoney
	 *            首期还款日期
	 * @param loanMonths
	 *            贷款月份
	 * @return 贷款剩余月份
	 */
	public static Long getRemainingMonths(String firstDateOfBackMoney,
			Long loanMonths) {
		Long remainingMonths = new Long(0);
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = sdf.parse(firstDateOfBackMoney);
			Date nowDate = new Date();
			Date cd;
			calendar.setTime(date);
			String returnStringDate = "";// 12月份
			int yy = 0;
			if(!date.after(nowDate)){
				yy++;
				for(long i = 1 ; i < loanMonths;i++){
					//calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + Integer.parseInt((i+1)+""));//让日期加1 
					returnStringDate = getNextDateOfBackMoney(calendar);// 12月份
					cd = sdf.parse(returnStringDate);
					calendar.setTime(cd);
					System.out.println(sdf.format(cd)+"==="+cd.after(nowDate));
					System.out.println(returnStringDate);
					if(cd.after(nowDate)){
						break;
					}else{
						yy++;
					}
				}
			}
			remainingMonths = loanMonths-yy;
			/*
			if (Calendar.getInstance().get(Calendar.YEAR) < calendar// 首期还款年份>当前年份
					.get(Calendar.YEAR)) {

				remainingMonths = loanMonths;//
				System.out.println("a");

			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// 首期还款年份>当前年份
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) < calendar// 首期还款年份>当前年份
							.get(Calendar.MONTH)) {
				remainingMonths = loanMonths;//
			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// 首期还款年份>当前年份
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) == calendar// 首期还款年份>当前年份
							.get(Calendar.MONTH)) {
				if (Calendar.getInstance().get(Calendar.DATE) < calendar// 首期还款年份>当前年份
						.get(Calendar.DATE)) {
					remainingMonths = loanMonths;//
				} else {
					remainingMonths = loanMonths - 1;
				}

			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// 首期还款年份>当前年份
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) < calendar// 首期还款年份>当前年份
							.get(Calendar.MONTH)) {
				remainingMonths = loanMonths;//

			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// 首期还款年份>当前年份
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) > calendar// 首期还款年份>当前年份
							.get(Calendar.MONTH)) {
				if (Calendar.getInstance().get(Calendar.DATE) < calendar// 首期还款年份>当前年份
						.get(Calendar.DATE)) {
					remainingMonths = loanMonths
							- (Calendar.getInstance().get(Calendar.MONTH)
									- calendar.get(Calendar.MONTH) + (Calendar
									.getInstance().get(Calendar.YEAR) - calendar
									.get(Calendar.YEAR)) * 12) - 1;
				} else {
					remainingMonths = loanMonths
							- (Calendar.getInstance().get(Calendar.MONTH)
									- calendar.get(Calendar.MONTH) + (Calendar
									.getInstance().get(Calendar.YEAR) - calendar
									.get(Calendar.YEAR)) * 12) - 2;
				}
			}
			if (Calendar.getInstance().get(Calendar.YEAR) > calendar// 首期还款年份>当前年份
					.get(Calendar.YEAR)) {
				if (Calendar.getInstance().get(Calendar.DATE) < calendar// 首期还款年份>当前年份
						.get(Calendar.DATE)) {
					remainingMonths = loanMonths
							- (Calendar.getInstance().get(Calendar.MONTH)
									- calendar.get(Calendar.MONTH) + (Calendar
									.getInstance().get(Calendar.YEAR) - calendar
									.get(Calendar.YEAR)) * 12) - 1;
				} else {
					remainingMonths = loanMonths
							- (Calendar.getInstance().get(Calendar.MONTH)
									- calendar.get(Calendar.MONTH) + (Calendar
									.getInstance().get(Calendar.YEAR) - calendar
									.get(Calendar.YEAR)) * 12) - 2;
				}
			}
*/
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return remainingMonths;

	}

	/**
	 * 计算借款天数，借款日期与截止还款日期之间的天数
	 * 
	 * @param firstDate
	 *            借款日期
	 * @param secondDate
	 *            截止还款日期
	 * @return 借款天数
	 */
	public static long getDaysOfDates(String firstDate, String secondDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long days = 0;
		try {
			Date date1 = sdf.parse(firstDate);
			Date date2 = sdf.parse(secondDate);
			Calendar cal1 = new GregorianCalendar();
			Calendar cal2 = new GregorianCalendar();

			cal1.setTime(date1);
			cal2.setTime(date2);

			days = (cal2.getTimeInMillis() - cal1.getTimeInMillis())
					/ (1000 * 3600 * 24);// 从间隔毫秒变成间隔天数
		} catch (ParseException e) {
		}
		return days;

	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 取指定日期年份
	 * 
	 * @param loanDate
	 * @return
	 */
	public static int getYearbyDate(String loanDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int re = 0;
		try {
			Date date = sdf.parse(loanDate);
			Calendar c = new GregorianCalendar();
			c.setTime(date);
			re = c.get(java.util.Calendar.YEAR);
		} catch (ParseException e) {
		}

		return re;
	}

	/**
	 * 取指定日期月份
	 * 
	 * @param loanDate
	 * @return
	 */
	public static int getMonthbyDate(String loanDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int re = 0;
		try {
			Date date = sdf.parse(loanDate);
			Calendar c = new GregorianCalendar();
			c.setTime(date);
			re = c.get(java.util.Calendar.MONTH) + 1;
		} catch (ParseException e) {
		}

		return re;
	}

	/**
	 * 取指定日期日份
	 * 
	 * @param loanDate
	 * @return
	 */
	public static int getDaybyDate(String loanDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int re = 0;
		try {
			Date date = sdf.parse(loanDate);
			Calendar c = new GregorianCalendar();
			c.setTime(date);
			re = c.get(java.util.Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
		}

		return re;
	}

	/**
	 * 首个还款日应收利息金额
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getFirstInterest(double dkll, double sjcjje,
			String loanDate) {
		String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// 首期出借天数
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// 借款日期本月天数
		} else {
			byts = day;
		}
		// int byts = getMonthLastDay(getYearbyDate(loanDate),
		// getMonthbyDate(loanDate));// 借款日期本月天数
		// int byts = 30;
		double sumFirstInterest = 0;
		sumFirstInterest += sjcjje * dkll / 100 / byts * cjts;
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * 末期还款日应收利息金额
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getOverInterest(double dkll, double sjcjje,
			String loanDate, int firstdate) {
		// String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		// long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// 首期出借天数
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// 借款日期本月天数
		} else {
			byts = day;
		}
		// int byts = getMonthLastDay(getYearbyDate(loanDate),
		// getMonthbyDate(loanDate));// 借款日期本月天数
		// int byts = 30;
		long cjts = byts - firstdate;
		double sumFirstInterest = 0;
		sumFirstInterest += sjcjje * dkll / 100 / byts * cjts;
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * 首个还款日计息天数
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static String getFirstdate(String loanDate) {
		String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// 首期出借天数
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// 借款日期本月天数
		} else {
			byts = day;
		}
		// int byts = getMonthLastDay(getYearbyDate(loanDate),
		// getMonthbyDate(loanDate));// 借款日期本月天数
		// int byts = 30;
		long cjtsy = byts - cjts;
		return cjtsy + "";
	}

	/**
	 * 非首个还款日应收利息金额
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getInterest(double dkll, double sjcjje, String loanDate) {
		double sumFirstInterest = 0;
		sumFirstInterest += sjcjje * dkll / 100;
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * 首期应收利息金额合计
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getSumFirstInterest(double dkll[], double sjcjje[],
			String loanDate) {
		String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// 首期出借天数
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// 借款日期本月天数
		} else {
			byts = day;
		}
		double sumFirstInterest = 0;
		for (int i = 0; i < sjcjje.length; i++) {
			sumFirstInterest += sjcjje[i] * dkll[i] / 100 / byts * cjts;
		}
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * 末期应收利息金额合计
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getSumOverInterest(double dkll[], double sjcjje[],
			String loanDate, int firstdate) {
		// String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		// long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// 首期出借天数
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// 借款日期本月天数
		} else {
			byts = day;
		}
		long cjts = byts - firstdate;
		double sumFirstInterest = 0;
		for (int i = 0; i < sjcjje.length; i++) {
			sumFirstInterest += sjcjje[i] * dkll[i] / 100 / byts * cjts;
		}
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * 非首期应收利息金额合计
	 * 
	 * @param dkll
	 *            贷款利率
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getSumOtherInterest(double dkll[], double sjcjje[]) {
		// 非首期足月计息
		double sumFirstInterest = 0;
		for (int i = 0; i < sjcjje.length; i++) {
			sumFirstInterest += sjcjje[i] * dkll[i] / 100;
		}
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * 月回款本金合计
	 * 
	 * @param cjqs
	 *            出借期数
	 * @param sjcjje
	 *            实际出借金额
	 * @param loanDate
	 *            首次借款日期
	 * @return
	 */
	public static double getSumBackPrincipal(int cjqs[], double sjcjje[]) {
		double sumBackPrincipal = 0;
		for (int i = 0; i < sjcjje.length; i++) {
			sumBackPrincipal += sjcjje[i] / cjqs[i];
		}
		// return (double) (Math.round(sumBackPrincipal * 100)) / 100;
		return sumBackPrincipal;
	}

	/**
	 * 计算月还款本金 月还款本金=合同金额/借款周期
	 * 
	 * @param allLoanMoney
	 *            借款金额
	 * @param loanMonths
	 *            贷款周期
	 * @return 月还款本金
	 */
	public static double getCapitalOfMonth(double allLoanMoney, int loanMonths) {
		return allLoanMoney / loanMonths;
	}

	/**
	 * 计算月利息 月利息=借款金额*月利率/月天数*月实际出借天数
	 * 
	 * @param allLoanMoney
	 *            借款金额
	 * @param rateOfMonth
	 *            月利率
	 * @return 月利息
	 */
	public static double getInterestOfMonth(double allLoanMoney,
			double rateOfMonth) {
		return allLoanMoney * rateOfMonth;
	}

	/**
	 * 计算月还款金额
	 * 
	 * @param allLoanMoney
	 *            借款金额
	 * @param rateOfMonth
	 *            月利率
	 * @param loanMonths
	 *            贷款周期
	 * @return 月还款金额
	 */
	public static double getBackMoneyOfMonth(double allLoanMoney,
			double rateOfMonth, int loanMonths) {
		return getCapitalOfMonth(allLoanMoney, loanMonths)
				+ getInterestOfMonth(allLoanMoney, rateOfMonth);
	}

	/**
	 * 计算日本金：日本金=初始借款金额/借款天数
	 * 
	 * @param allLoanMoney
	 *            初始借款金额
	 * @param firstLoanDate
	 *            初始借款日期
	 * @param ListBackMoneyDate
	 *            最后一个还款日
	 * @return 日本金
	 */
	public static double getCapitalOfDay(double allLoanMoney,
			String firstLoanDate, String LastBackMoneyDate) {
		return allLoanMoney / getDaysOfDates(firstLoanDate, LastBackMoneyDate);
	}

	/**
	 * 计算日利息：日利息=初始借款金额*借款期限*月利率/借款天数
	 * 
	 * @param allLoanMoney
	 *            初始借款金额
	 * @param rateOfMonth
	 *            月利率
	 * @param loanMonths
	 *            借款周期
	 * @param loanMonths
	 *            借款天数
	 * @return 日利息
	 */
	public static double getInterestOfDay(double allLoanMoney,
			double rateOfMonth, long loanMonths, long days) {
		return allLoanMoney * rateOfMonth * loanMonths / days;
	}

	/**
	 * 计算日还款金额
	 * 
	 * @param allLoanMoney
	 *            借款金额
	 * @param rateOfMonth
	 *            月利率
	 * @param loanMonths
	 *            贷款周期
	 * @param days
	 *            贷款天数
	 * @param firstLoanDate
	 *            借款日期
	 * @param LastBackMoneyDate
	 *            最后一个还款日期
	 * @return
	 */
	public static double getBackMoneyOfDay(double allLoanMoney,
			double rateOfMonth, long loanMonths, long days,
			String firstLoanDate, String LastBackMoneyDate) {
		return getInterestOfDay(allLoanMoney, rateOfMonth, loanMonths, days)
				+ getCapitalOfDay(allLoanMoney, firstLoanDate,
						LastBackMoneyDate);
	}

	/**
	 * 计算当期一次性还款金额
	 * 
	 * @param hkqs
	 *            还款期数
	 * @param pdje
	 *            批贷金额
	 * @param yhkbj
	 *            月还款本金
	 * @return
	 */
	public static double[] getBackMoneyOfAll(Integer hkqs, double pdje,
			double yhkbj, double sanFsum) {
		double[] ychkje = new double[hkqs];
		for (int i = 0; i < hkqs; i++) {

			if (i == 0) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51;// 1期
			} else if (i == 1) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51 - yhkbj * i;// 2期
			} else if (i == 2) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51 - pdje / hkqs * i;// 3期
			} else if (i == 3) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51 * 0.915 - pdje
						/ hkqs * i;// 4期
			} else {
				// =总!$E$12+总!$F$10-(总!$E$3*(I4-1))-($H$12-($G$13*(I4-4)))
				// 批贷金额+三项综合-月本金*（期数-1）-退费
				ychkje[i] = pdje
						+ sanFsum
						- yhkbj
						* i
						- (sanFsum * 0.51 * 0.915 - (sanFsum * 0.51 - sanFsum * 0.51 * 0.915)
								* (i - 3));// 大于4期
			}

			ychkje[i] = (double) (Math.round(ychkje[i] * 100)) / 100;
			DecimalFormat df = new DecimalFormat("#.00");
			ychkje[i] = Double.parseDouble(df.format(ychkje[i]));
		}
		return ychkje;
	}

	/**
	 * 计算逾期违约金
	 * 
	 * @param ybxje
	 *            月还款本息金额
	 * @param yqts
	 *            逾期天数
	 * @return
	 */
	public static double getBreachMoney(double ybxje) {
		double weiYueJin = 0.00;
		if (ybxje < 1000)
			weiYueJin = 100;
		else
			weiYueJin = (double) ybxje * 0.1;
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(weiYueJin));
	}

	/**
	 * 计算罚息
	 * 
	 * @param ybxje
	 *            月还款本息金额
	 * @param yqts
	 *            逾期天数
	 * @return
	 */
	public static String getFineMoney(double ybxje, Integer yqts, Integer qs) {
		DecimalFormat df = new DecimalFormat("#.00");

		return df
				.format((double) (Math.round((ybxje * 0.2 * yqts * qs))) / 100);
	}

	/**
	 * 计算所有的月回款日期（含首期）
	 * 
	 * @param firstDayOfBackMoney
	 * @param monthsOfLoan
	 * @return
	 */
	public static String[] getAllBackMoneyDates(String firstDayOfBackMoney,
			int monthsOfLoan) {

		String backMoneyDates[] = new String[monthsOfLoan];

		backMoneyDates[0] = firstDayOfBackMoney;// 首期直接存入数组
		for (int i = 1; i < monthsOfLoan; i++) {
			backMoneyDates[i] = getLastDateOfBackMoney(firstDayOfBackMoney,
					i + 1);
		}

		return backMoneyDates;
	}

	/**
	 * 按目标长度补充原始数值，不足位数前面补0
	 * 
	 * @param i原始数值
	 * @param len目标长度
	 * @return
	 */
	public static String getAddStrLen(int i, int len) {
		int n = i;
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(len);
		formatter.setGroupingUsed(false);
		return formatter.format(n);
	}

	// /**
	// *
	// // * @param args
	// // * @throws ParseException
	// // */
	/**
	 * 得到几天前的时间 return string
	 */
	public static String getDateBefore(String d, int day) {
		return dateToString(getDateBefore(StringToDate(d, "yyyy-MM-dd"), day));
	}

	/**
	 * 得到几天前的时间 return date
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	/**
	 * 得到几天后的时间 return string
	 */
	public static String getDateAfter(String d, int day) {
		return dateToString(getDateAfter(StringToDate(d, "yyyy-MM-dd"), day));
	}
	/**
	 * 得到几天后的时间
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	public static String reb7_27(String riqi,String zdr){
		String r = riqi;
		String n = getDateBefore(riqi, 1);
		Date date = StringToDate(n,"yyyy-MM-dd");
		
		SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
		SimpleDateFormat mm = new SimpleDateFormat("MM");
		//SimpleDateFormat dd = new SimpleDateFormat("dd");
		String yy = yyyy.format(date);
		String m = mm.format(date);
		//String d = dd.format(date);
		
		if("30".equals(zdr)){
			r = yy + "-" + m + "-27";
		}else{
			r = yy + "-" + m + "-07";
		}
		return r;
	}
	
	/**
	 * 通过起始还款日前，和借款期数，得到最终还款日
	 * @param startTime
	 * @param months
	 * @return
	 */
	public static Date getEndTimeByStartTimeAndCount(String startTime , int months){
		Date startDate =  StringToDate(startTime,"yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();  
		cal.setTime(startDate);  
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(month != Calendar.FEBRUARY || day <20){
			cal.add(Calendar.MONTH,months-1);  
			return cal.getTime();   
		}else{
			//是2月28日，或者29日，如果是则返回截至日期的最后一天
			cal.add(Calendar.MONTH,months-1);  
			int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH) == 31 ? 30 : cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
			return cal.getTime();
		}
	}
	/**
	 * 可用债权剩余期数处理，根据首个还款日期和账单日，获取首个结算日期
	 * @param qshkrq
	 * @param zdr
	 * @return
	 */
	public static String reKyzqjzSgjsrq(String qshkrq,String zdr){
		Date startDate =  StringToDate(qshkrq,"yyyy-MM-dd");
		Date newDate = new Date();
		Calendar cal = Calendar.getInstance();  
		cal.setTime(startDate);  
		//先将日期设置为15，防止遇到2月份出问题
		cal.set(Calendar.DATE, 15);
		//如果账单日为30，则结算日期为当月15号，否则为上月30
		if("15".equals(zdr)){
			cal.add(Calendar.MONTH, -1);
			if (cal.get(Calendar.MONTH) == 1){
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}else{
				cal.set(Calendar.DATE, 30);
			}
		}
		newDate = cal.getTime();
		return dateToString(newDate);
	}
	public static void main(String[] args) throws ParseException {
		//System.out.println(reKyzqjzSgjsrq("2012-02-28","30"));
		// System.out.println(getFirstDateOfBackMoney("2012-9-30"));
		// System.out.println(getLastDateOfBackMoney("2012-9-30",2))
		//System.out.println(reb7_27("2012-01-16", "15"));
		//System.out.println(getDaysOfDates("2012-4-24","2012-5-15"));
		//System.out.println(getFirstDateOfBackMoney("2013-01-31"));
		
		//
		// System.out
		// .println(getRemainingMonths("2012-10-15", Long.valueOf("6")));
		//System.out.println(getZdr(getFirstDateOfBackMoney("2012-1-7")));

		// System.out.println(getLastDateOfBackMoney("2012-2-28", 2));
		// String[] dates = getAllBackMoneyDates("2012-2-28", 24);
		// for (int i = 0; i < dates.length; i++) {
		// System.out.println("第" + (i + 1) + "期还款日：" + dates[i]);
		// }

		// String[] dates = getAllBackMoneyDates("2012-2-28", 24);
		// for (int i = 0; i < dates.length; i++) {
		// System.out.println("第"+(i+1)+"期还款日："+dates[i]);
		// }
		 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = null;
		 try {
		 date = sdf.parse("2013-09-22");
		 System.out.println("星期"+getWeek(date));
		 } catch (ParseException e) {
		 e.printStackTrace();
		 }

		// System.out.println(getLastDateOfBackMoney("2013-01-15", 12));
		// System.out.println(StringToDate(getLastDateOfBackMoney("2013-01-15",
		// 12), "yyyy-MM-dd"));getBackMoneyOfMonth
		// System.out.println("月利息：" + getFineMoney(12445.43, 16));
		// System.out.println("月本金：" + getBackMoneyOfMonth(251580, 0.01, 12));
		// 0+12%12
		// System.out.println(getLastDateOfBackMoney("2012-7-27", 12));
		// System.out.println(getDaysOfDates("2012-8-3", "2013-1-30"));// 364+31
		// System.out
		// .println((getCapitalOfDay(54470.00, "2012-8-3", "2013-1-30")));
		// System.out.println((getInterestOfDay(54470.00, 0.0101, 6,
		// getDaysOfDates("2012-8-3", "2013-1-30"))));
		// System.out.println(getRemainingMonths("2013-1-14", 6));
		

	}
	/*当前日期星期几
	 * 
	 */
	public static int getWeek(Date d) {
		Calendar cal = Calendar.getInstance();  
		cal.setTime(d);
		int w = cal.get(Calendar.DAY_OF_WEEK)-1;
		if (w <= 0) w = 7;
		//如果6是周六 ,周日为0
	  return w;
	  
	}
	/**
	 * 通过首期日期和资金出借回款方式计算销售折扣利率有效期限
	 * @param sqrq
	 * @param tzcpId
	 * @return
	 */
	public static String getXszklyxqx(String sqrq,Long tzcpId){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sqrqDate = "";
		try {
			Date date = format.parse(sqrq);
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			if(tzcpId==82){           //月息通 12个月
				cd.add(Calendar.YEAR, 1);
			}else if(tzcpId==83){     //季度盈 3个月
				cd.add(Calendar.MONTH, 3);
			}else if(tzcpId==85){     //年年盈 12个月
				cd.add(Calendar.YEAR, 1);
			}else if(tzcpId==84){     //双季盈 6个月
				cd.add(Calendar.MONTH, 6);
			}else if(tzcpId==81){     //信和通 12个月
				cd.add(Calendar.YEAR, 1);
			}else if(tzcpId==252512){ //信和宝 2年
				cd.add(Calendar.YEAR, 2);
			}else if(tzcpId==88){     //年年金 1年
				cd.add(Calendar.YEAR, 1);
			}
			sqrqDate = format.format(cd.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqrqDate;
	}

}