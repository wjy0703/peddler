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
 * �źͽ����ؼ��㹤��
 * 
 * @author Songjf
 * 
 */
public class CreditHarmonyComputeUtilties {
	// ���������Ƿ�����Ȼ���㣬true���ǣ�false���񣬰���30����
	private static boolean dateFlag = true;
	private static int day = 30;

	/**
	 * �ַ���ת����ʱ���ʽ
	 * 
	 * @param dateStr
	 *            ��Ҫת�����ַ���
	 * @param formatStr
	 *            ��Ҫ��ʽ��Ŀ���ַ��� ���� yyyy-MM-dd
	 * @return Date ����ת�����ʱ��
	 * @throws ParseException
	 *             ת���쳣
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
	 * Date����תString����
	 * 
	 * @param date
	 * @return �ַ���ʱ���ʽ
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
	 * ������ʼ�������ȡ��ÿ�»����� ����ͬ�ļ��л�������ÿ��15��30 ��ʽ�в�����2��һ��� �����2�·ݣ��ͻ��Ļ���������2�·����һ��
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
	 * ������ʼ�������ȡ��ÿ�»����� ����ͬ�ļ��л�������ÿ��15��30 �����getFirstDateOfBackMoney������ȡ�׸������� ʹ��
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
	 * �����һ���������� ��������������15��֮ǰ����һ���������Ǳ���30�� ����2�·ݵ�һ���������Ǳ���29�� ƽ��2�·ݵ�һ���������Ǳ���28��,
	 * ���������15��֮�����ڻ�����Ϊ����15��
	 * 
	 * @param loan_date
	 *            �������
	 * @return ��һ��������
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
	 * �������ڻ������ڼ� �������,�������һ����������
	 * 
	 * @param firstDayOfBackMoney
	 *            �׸���������
	 * @return ���һ����������
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
				+ calendar.get(Calendar.DATE);// 12�·�

		return returnStringDate;*/

	}

	/**
	 * ���ݵ�ǰ�������ڻ�ȡ��һ����������
	 * 
	 * @param firstDayOfBackMoney
	 *            �׸���������
	 * @return ���һ����������
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
				+ calendar.get(Calendar.DATE);// 12�·�

		return returnStringDate;

	}
	/**
	 * �������ʣ�����ޣ��·ݣ�
	 * 
	 * @param firstDateOfBackMoney
	 *            ���ڻ�������
	 * @param loanMonths
	 *            �����·�
	 * @return ����ʣ���·�
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
			String returnStringDate = "";// 12�·�
			int yy = 0;
			if(!date.after(nowDate)){
				yy++;
				for(long i = 1 ; i < loanMonths;i++){
					//calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + Integer.parseInt((i+1)+""));//�����ڼ�1 
					returnStringDate = getNextDateOfBackMoney(calendar);// 12�·�
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
			if (Calendar.getInstance().get(Calendar.YEAR) < calendar// ���ڻ������>��ǰ���
					.get(Calendar.YEAR)) {

				remainingMonths = loanMonths;//
				System.out.println("a");

			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// ���ڻ������>��ǰ���
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) < calendar// ���ڻ������>��ǰ���
							.get(Calendar.MONTH)) {
				remainingMonths = loanMonths;//
			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// ���ڻ������>��ǰ���
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) == calendar// ���ڻ������>��ǰ���
							.get(Calendar.MONTH)) {
				if (Calendar.getInstance().get(Calendar.DATE) < calendar// ���ڻ������>��ǰ���
						.get(Calendar.DATE)) {
					remainingMonths = loanMonths;//
				} else {
					remainingMonths = loanMonths - 1;
				}

			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// ���ڻ������>��ǰ���
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) < calendar// ���ڻ������>��ǰ���
							.get(Calendar.MONTH)) {
				remainingMonths = loanMonths;//

			} else if (Calendar.getInstance().get(Calendar.YEAR) == calendar// ���ڻ������>��ǰ���
					.get(Calendar.YEAR)
					&& Calendar.getInstance().get(Calendar.MONTH) > calendar// ���ڻ������>��ǰ���
							.get(Calendar.MONTH)) {
				if (Calendar.getInstance().get(Calendar.DATE) < calendar// ���ڻ������>��ǰ���
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
			if (Calendar.getInstance().get(Calendar.YEAR) > calendar// ���ڻ������>��ǰ���
					.get(Calendar.YEAR)) {
				if (Calendar.getInstance().get(Calendar.DATE) < calendar// ���ڻ������>��ǰ���
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
	 * ����������������������ֹ��������֮�������
	 * 
	 * @param firstDate
	 *            �������
	 * @param secondDate
	 *            ��ֹ��������
	 * @return �������
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
					/ (1000 * 3600 * 24);// �Ӽ�������ɼ������
		} catch (ParseException e) {
		}
		return days;

	}

	/**
	 * �õ�ָ���µ�����
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��
		a.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ�������һ��
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * ȡָ���������
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
	 * ȡָ�������·�
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
	 * ȡָ�������շ�
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
	 * �׸�������Ӧ����Ϣ���
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static double getFirstInterest(double dkll, double sjcjje,
			String loanDate) {
		String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// ���ڳ�������
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// ������ڱ�������
		} else {
			byts = day;
		}
		// int byts = getMonthLastDay(getYearbyDate(loanDate),
		// getMonthbyDate(loanDate));// ������ڱ�������
		// int byts = 30;
		double sumFirstInterest = 0;
		sumFirstInterest += sjcjje * dkll / 100 / byts * cjts;
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * ĩ�ڻ�����Ӧ����Ϣ���
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static double getOverInterest(double dkll, double sjcjje,
			String loanDate, int firstdate) {
		// String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		// long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// ���ڳ�������
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// ������ڱ�������
		} else {
			byts = day;
		}
		// int byts = getMonthLastDay(getYearbyDate(loanDate),
		// getMonthbyDate(loanDate));// ������ڱ�������
		// int byts = 30;
		long cjts = byts - firstdate;
		double sumFirstInterest = 0;
		sumFirstInterest += sjcjje * dkll / 100 / byts * cjts;
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * �׸������ռ�Ϣ����
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static String getFirstdate(String loanDate) {
		String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// ���ڳ�������
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// ������ڱ�������
		} else {
			byts = day;
		}
		// int byts = getMonthLastDay(getYearbyDate(loanDate),
		// getMonthbyDate(loanDate));// ������ڱ�������
		// int byts = 30;
		long cjtsy = byts - cjts;
		return cjtsy + "";
	}

	/**
	 * ���׸�������Ӧ����Ϣ���
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static double getInterest(double dkll, double sjcjje, String loanDate) {
		double sumFirstInterest = 0;
		sumFirstInterest += sjcjje * dkll / 100;
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * ����Ӧ����Ϣ���ϼ�
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static double getSumFirstInterest(double dkll[], double sjcjje[],
			String loanDate) {
		String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// ���ڳ�������
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// ������ڱ�������
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
	 * ĩ��Ӧ����Ϣ���ϼ�
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static double getSumOverInterest(double dkll[], double sjcjje[],
			String loanDate, int firstdate) {
		// String firstDateOfBackMoney = getFirstDateOfBackMoney(loanDate);
		// long cjts = getDaysOfDates(loanDate, firstDateOfBackMoney);// ���ڳ�������
		int byts = 1;
		if (dateFlag) {
			byts = getMonthLastDay(getYearbyDate(loanDate),
					getMonthbyDate(loanDate));// ������ڱ�������
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
	 * ������Ӧ����Ϣ���ϼ�
	 * 
	 * @param dkll
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
	 * @return
	 */
	public static double getSumOtherInterest(double dkll[], double sjcjje[]) {
		// ���������¼�Ϣ
		double sumFirstInterest = 0;
		for (int i = 0; i < sjcjje.length; i++) {
			sumFirstInterest += sjcjje[i] * dkll[i] / 100;
		}
		// return (double) (Math.round(sumFirstInterest * 100)) / 100;
		return sumFirstInterest;
	}

	/**
	 * �»ؿ��ϼ�
	 * 
	 * @param cjqs
	 *            ��������
	 * @param sjcjje
	 *            ʵ�ʳ�����
	 * @param loanDate
	 *            �״ν������
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
	 * �����»���� �»����=��ͬ���/�������
	 * 
	 * @param allLoanMoney
	 *            �����
	 * @param loanMonths
	 *            ��������
	 * @return �»����
	 */
	public static double getCapitalOfMonth(double allLoanMoney, int loanMonths) {
		return allLoanMoney / loanMonths;
	}

	/**
	 * ��������Ϣ ����Ϣ=�����*������/������*��ʵ�ʳ�������
	 * 
	 * @param allLoanMoney
	 *            �����
	 * @param rateOfMonth
	 *            ������
	 * @return ����Ϣ
	 */
	public static double getInterestOfMonth(double allLoanMoney,
			double rateOfMonth) {
		return allLoanMoney * rateOfMonth;
	}

	/**
	 * �����»�����
	 * 
	 * @param allLoanMoney
	 *            �����
	 * @param rateOfMonth
	 *            ������
	 * @param loanMonths
	 *            ��������
	 * @return �»�����
	 */
	public static double getBackMoneyOfMonth(double allLoanMoney,
			double rateOfMonth, int loanMonths) {
		return getCapitalOfMonth(allLoanMoney, loanMonths)
				+ getInterestOfMonth(allLoanMoney, rateOfMonth);
	}

	/**
	 * �����ձ����ձ���=��ʼ�����/�������
	 * 
	 * @param allLoanMoney
	 *            ��ʼ�����
	 * @param firstLoanDate
	 *            ��ʼ�������
	 * @param ListBackMoneyDate
	 *            ���һ��������
	 * @return �ձ���
	 */
	public static double getCapitalOfDay(double allLoanMoney,
			String firstLoanDate, String LastBackMoneyDate) {
		return allLoanMoney / getDaysOfDates(firstLoanDate, LastBackMoneyDate);
	}

	/**
	 * ��������Ϣ������Ϣ=��ʼ�����*�������*������/�������
	 * 
	 * @param allLoanMoney
	 *            ��ʼ�����
	 * @param rateOfMonth
	 *            ������
	 * @param loanMonths
	 *            �������
	 * @param loanMonths
	 *            �������
	 * @return ����Ϣ
	 */
	public static double getInterestOfDay(double allLoanMoney,
			double rateOfMonth, long loanMonths, long days) {
		return allLoanMoney * rateOfMonth * loanMonths / days;
	}

	/**
	 * �����ջ�����
	 * 
	 * @param allLoanMoney
	 *            �����
	 * @param rateOfMonth
	 *            ������
	 * @param loanMonths
	 *            ��������
	 * @param days
	 *            ��������
	 * @param firstLoanDate
	 *            �������
	 * @param LastBackMoneyDate
	 *            ���һ����������
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
	 * ���㵱��һ���Ի�����
	 * 
	 * @param hkqs
	 *            ��������
	 * @param pdje
	 *            �������
	 * @param yhkbj
	 *            �»����
	 * @return
	 */
	public static double[] getBackMoneyOfAll(Integer hkqs, double pdje,
			double yhkbj, double sanFsum) {
		double[] ychkje = new double[hkqs];
		for (int i = 0; i < hkqs; i++) {

			if (i == 0) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51;// 1��
			} else if (i == 1) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51 - yhkbj * i;// 2��
			} else if (i == 2) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51 - pdje / hkqs * i;// 3��
			} else if (i == 3) {
				ychkje[i] = pdje + sanFsum - sanFsum * 0.51 * 0.915 - pdje
						/ hkqs * i;// 4��
			} else {
				// =��!$E$12+��!$F$10-(��!$E$3*(I4-1))-($H$12-($G$13*(I4-4)))
				// �������+�����ۺ�-�±���*������-1��-�˷�
				ychkje[i] = pdje
						+ sanFsum
						- yhkbj
						* i
						- (sanFsum * 0.51 * 0.915 - (sanFsum * 0.51 - sanFsum * 0.51 * 0.915)
								* (i - 3));// ����4��
			}

			ychkje[i] = (double) (Math.round(ychkje[i] * 100)) / 100;
			DecimalFormat df = new DecimalFormat("#.00");
			ychkje[i] = Double.parseDouble(df.format(ychkje[i]));
		}
		return ychkje;
	}

	/**
	 * ��������ΥԼ��
	 * 
	 * @param ybxje
	 *            �»��Ϣ���
	 * @param yqts
	 *            ��������
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
	 * ���㷣Ϣ
	 * 
	 * @param ybxje
	 *            �»��Ϣ���
	 * @param yqts
	 *            ��������
	 * @return
	 */
	public static String getFineMoney(double ybxje, Integer yqts, Integer qs) {
		DecimalFormat df = new DecimalFormat("#.00");

		return df
				.format((double) (Math.round((ybxje * 0.2 * yqts * qs))) / 100);
	}

	/**
	 * �������е��»ؿ����ڣ������ڣ�
	 * 
	 * @param firstDayOfBackMoney
	 * @param monthsOfLoan
	 * @return
	 */
	public static String[] getAllBackMoneyDates(String firstDayOfBackMoney,
			int monthsOfLoan) {

		String backMoneyDates[] = new String[monthsOfLoan];

		backMoneyDates[0] = firstDayOfBackMoney;// ����ֱ�Ӵ�������
		for (int i = 1; i < monthsOfLoan; i++) {
			backMoneyDates[i] = getLastDateOfBackMoney(firstDayOfBackMoney,
					i + 1);
		}

		return backMoneyDates;
	}

	/**
	 * ��Ŀ�곤�Ȳ���ԭʼ��ֵ������λ��ǰ�油0
	 * 
	 * @param iԭʼ��ֵ
	 * @param lenĿ�곤��
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
	 * �õ�����ǰ��ʱ�� return string
	 */
	public static String getDateBefore(String d, int day) {
		return dateToString(getDateBefore(StringToDate(d, "yyyy-MM-dd"), day));
	}

	/**
	 * �õ�����ǰ��ʱ�� return date
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	/**
	 * �õ�������ʱ�� return string
	 */
	public static String getDateAfter(String d, int day) {
		return dateToString(getDateAfter(StringToDate(d, "yyyy-MM-dd"), day));
	}
	/**
	 * �õ�������ʱ��
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
	 * ͨ����ʼ������ǰ���ͽ���������õ����ջ�����
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
			//��2��28�գ�����29�գ�������򷵻ؽ������ڵ����һ��
			cal.add(Calendar.MONTH,months-1);  
			int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH) == 31 ? 30 : cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
			return cal.getTime();
		}
	}
	/**
	 * ����ծȨʣ���������������׸��������ں��˵��գ���ȡ�׸���������
	 * @param qshkrq
	 * @param zdr
	 * @return
	 */
	public static String reKyzqjzSgjsrq(String qshkrq,String zdr){
		Date startDate =  StringToDate(qshkrq,"yyyy-MM-dd");
		Date newDate = new Date();
		Calendar cal = Calendar.getInstance();  
		cal.setTime(startDate);  
		//�Ƚ���������Ϊ15����ֹ����2�·ݳ�����
		cal.set(Calendar.DATE, 15);
		//����˵���Ϊ30�����������Ϊ����15�ţ�����Ϊ����30
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
		// System.out.println("��" + (i + 1) + "�ڻ����գ�" + dates[i]);
		// }

		// String[] dates = getAllBackMoneyDates("2012-2-28", 24);
		// for (int i = 0; i < dates.length; i++) {
		// System.out.println("��"+(i+1)+"�ڻ����գ�"+dates[i]);
		// }
		 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = null;
		 try {
		 date = sdf.parse("2013-09-22");
		 System.out.println("����"+getWeek(date));
		 } catch (ParseException e) {
		 e.printStackTrace();
		 }

		// System.out.println(getLastDateOfBackMoney("2013-01-15", 12));
		// System.out.println(StringToDate(getLastDateOfBackMoney("2013-01-15",
		// 12), "yyyy-MM-dd"));getBackMoneyOfMonth
		// System.out.println("����Ϣ��" + getFineMoney(12445.43, 16));
		// System.out.println("�±���" + getBackMoneyOfMonth(251580, 0.01, 12));
		// 0+12%12
		// System.out.println(getLastDateOfBackMoney("2012-7-27", 12));
		// System.out.println(getDaysOfDates("2012-8-3", "2013-1-30"));// 364+31
		// System.out
		// .println((getCapitalOfDay(54470.00, "2012-8-3", "2013-1-30")));
		// System.out.println((getInterestOfDay(54470.00, 0.0101, 6,
		// getDaysOfDates("2012-8-3", "2013-1-30"))));
		// System.out.println(getRemainingMonths("2013-1-14", 6));
		

	}
	/*��ǰ�������ڼ�
	 * 
	 */
	public static int getWeek(Date d) {
		Calendar cal = Calendar.getInstance();  
		cal.setTime(d);
		int w = cal.get(Calendar.DAY_OF_WEEK)-1;
		if (w <= 0) w = 7;
		//���6������ ,����Ϊ0
	  return w;
	  
	}
	/**
	 * ͨ���������ں��ʽ����ؿʽ���������ۿ�������Ч����
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
			if(tzcpId==82){           //��Ϣͨ 12����
				cd.add(Calendar.YEAR, 1);
			}else if(tzcpId==83){     //����ӯ 3����
				cd.add(Calendar.MONTH, 3);
			}else if(tzcpId==85){     //����ӯ 12����
				cd.add(Calendar.YEAR, 1);
			}else if(tzcpId==84){     //˫��ӯ 6����
				cd.add(Calendar.MONTH, 6);
			}else if(tzcpId==81){     //�ź�ͨ 12����
				cd.add(Calendar.YEAR, 1);
			}else if(tzcpId==252512){ //�źͱ� 2��
				cd.add(Calendar.YEAR, 2);
			}else if(tzcpId==88){     //����� 1��
				cd.add(Calendar.YEAR, 1);
			}
			sqrqDate = format.format(cd.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqrqDate;
	}

}