package cn.com.pro.util.utils;


import cn.com.pro.util.utils.enums.EnumDateStyle;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeUtil {

    /**
     * 缺省日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 缺省月格式
     */
    public static final String DEFAULT_MONTH = "MONTH";

    /**
     * 缺省年格式
     */
    public static final String DEFAULT_YEAR = "YEAR";

    /**
     * 缺省日格式
     */
    public static final String DEFAULT_DAY = "DAY";

    /**
     * 缺省小时格式
     */
    public static final String DEFAULT_HOUR = "HOUR";

    /**
     * 缺省分钟格式
     */
    public static final String DEFAULT_MINUTE = "MINUTE";

    /**
     * 缺省长日期格式,精确到秒
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

    /**
     * 星期数组
     */
    public static final String[] WEEKS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 缺省长日期格式,精确到秒
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC_T = "yyyyMMddHHmmss";

    /**
     * 年月日
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 缺省长日期格式,精确到毫秒
     */
    public static final String DEFAULT_DATETIME_FORMAT_MSEL = "yyyyMMddHHmmssSSS";



    /**
     * 取当前日期的字符串表示
     * @return 当前日期的字符串 ,如2010-05-28
     **/
    public static String today() {
        return today(DEFAULT_DATE_FORMAT);
    }

    /**
     * 根据输入的格式得到当前日期的字符串
     * @param strFormat 日期格式
     * @return
     */
    public static String today(String strFormat) {
        return toString(new Date(), strFormat);
    }

    /**
     * 取当前时间的字符串表示,
     * @return 当前时间, 如:21:10:12
     **/
    public static String currentTime() {
        return currentTime(DEFAULT_TIME_FORMAT);
    }

    /**
     * 根据输入的格式获取时间的字符串表示
     * @param strFormat,如'hh:mm:ss'
     * @return 当前时间, 如:21:10:12
     **/

    public static String currentTime(String strFormat) {
        return toString(new Date(), strFormat);
    }


    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期
     * <br>
     * 欲取得当前日期5天前的日期,可做如下调用:<br>
     * getAddDay("DAY", -5).
     *
     * @param field,段,如"year","month","day",对大小写不敏感
     * @param amount,增加的数量(减少用负数表示),如5,-1
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws ParseException
     **/

    public static String getAddDay(String field, int amount) throws ParseException {
        return getAddDay(field, amount, null);
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期,按指定格式输出
     * <p>
     * 欲取得当前日期5天前的日期,可做如下调用:<br>
     * getAddDay("DAY", -5,'yyyy-mm-dd hh:mm').
     *
     * @param field,段,如"year","month","day",对大小写不敏感
     * @param amount,增加的数量(减少用负数表示),如5,-1
     * @param strFormat,输出格式,如"yyyy-mm-dd","yyyy-mm-dd hh:mm"
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws ParseException
     **/
    public static String getAddDay(String field, int amount, String strFormat) throws ParseException {
        return getAddDay(null, field, amount, strFormat);
    }

    /**
     * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
     *
     * @param date      String 要改变的日期
     * @param field     int 日期改变的字段，YEAR,MONTH,DAY
     * @param amount    int 改变量
     * @param strFormat 日期返回格式
     * @return
     * @throws ParseException
     */
    public static String getAddDay(String date, String field, int amount, String strFormat) throws ParseException {
        if (strFormat == null) {
            strFormat = DEFAULT_DATETIME_FORMAT_SEC;
        }
        Calendar rightNow = Calendar.getInstance();
        if (date != null && !"".equals(date.trim())) {
            rightNow.setTime(parseDate(date, strFormat));
        }
        if (field == null) {
            return toString(rightNow.getTime(), strFormat);
        }
        rightNow.add(getInterval(field), amount);
        return toString(rightNow.getTime(), strFormat);
    }

    /**
     * 获取时间间隔类型
     *
     * @param field 时间间隔类型
     * @return 日历的时间间隔
     */
    protected static int getInterval(String field) {
        String tmpField = field.toUpperCase();
        if (tmpField.equals(DEFAULT_YEAR)) {
            return Calendar.YEAR;
        } else if (tmpField.equals(DEFAULT_MONTH)) {
            return Calendar.MONTH;
        } else if (tmpField.equals(DEFAULT_DAY)) {
            return Calendar.DATE;
        } else if (DEFAULT_HOUR.equals(tmpField)) {
            return Calendar.HOUR;
        } else if (DEFAULT_MINUTE.equals(tmpField)) {
            return Calendar.MINUTE;
        } else {
            return Calendar.SECOND;
        }
    }

    /**
     * 获取格式化对象
     * @param strFormat 格式化的格式 如"yyyy-MM-dd"
     * @return 格式化对象
     */
    public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
        if (strFormat != null && !"".equals(strFormat.trim())) {
            return new SimpleDateFormat(strFormat);
        } else {
            return new SimpleDateFormat();
        }
    }

    /**
     * 得到当前日期的星期数
     * @return 当前日期的星期的字符串
     * @throws ParseException
     */
    public static String getWeekOfMonth() throws ParseException {
        return getWeekOfMonth(null, null);
    }

    /**
     * 根据日期的到给定日期的在当月中的星期数
     *
     * @param date 给定日期
     * @return
     * @throws ParseException
     */
    public static String getWeekOfMonth(String date, String fromat) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        if (date != null && !"".equals(date.trim())) {
            rightNow.setTime(parseDate(date, fromat));
        }
        return WEEKS[rightNow.get(Calendar.WEEK_OF_MONTH)];
    }

    /**
     * 将java.util.date型按照指定格式转为字符串
     *
     * @param date   源对象
     * @param format 想得到的格式字符串
     * @return 如：2010-05-28
     */
    public static String toString(Date date, String format) {
        return getSimpleDateFormat(format).format(date);
    }

    /**
     * 将java.util.date型按照缺省格式转为字符串
     *
     * @param date 源对象
     * @return 如：2010-05-28
     */
    public static String toString(Date date) {
        return toString(date, DEFAULT_DATE_FORMAT);
    }


    /**
     * 强制类型转换 从串到日期
     *
     * @param strDate 源字符串，采用yyyy-MM-dd格式
     * @param format  ps
     * @return 得到的日期对象
     * @throws ParseException
     */
    public static Date parseDate(String strDate, String format) throws ParseException {
        return getSimpleDateFormat(format).parse(strDate);
    }

    /**
     * 转换日期类型 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static final Date strToFullTime(String date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC).parse(date);
        } catch (Exception e) {
        }
        return null;
    }


    /***
     * 根据传入的毫秒数和格式，对日期进行格式化输出
     * @version 2011-7-12
     * @param millisecond
     * @param format
     * @return
     */
    public static String millisecondFormat(Long millisecond, String format) {
        if (millisecond == null || millisecond <= 0) {
            throw new IllegalArgumentException(String.format("传入的时间毫秒数[%s]不合法", "" + millisecond));
        }
        if (format == null || "".equals(format.trim())) {
            format = DEFAULT_DATE_FORMAT;
        }
        return toString(new Date(millisecond), format);
    }

    /**
     * 强制类型转换 从串到时间戳
     *
     * @param strDate 源串
     * @param format  遵循格式
     * @return 取得的时间戳对象
     * @throws ParseException
     */
    public static Timestamp parseTimestamp(String strDate, String format) throws ParseException {
        Date utildate = getSimpleDateFormat(format).parse(strDate);
        return new Timestamp(utildate.getTime());
    }

    /**
     * getCurDate 取当前日期
     *
     * @return java.util.Date型日期
     **/
    public static Date getCurDate() {
        return (new Date());
    }

    /**
     * getCurTimestamp 取当前时间戳
     *
     * @return java.sql.Timestamp
     **/
    public static Timestamp getCurTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * getCurTimestamp 取遵循格式的当前时间
     *
     * @param format 遵循格式
     * @return java.sql.Timestamp
     **/
    public static Date getCurDate(String format) throws Exception {
        return getSimpleDateFormat(format).parse(toString(new Date(), format));
    }


    /**
     * Timestamp按照指定格式转为字符串
     *
     * @param timestamp 源对象
     * @param format    ps（如yyyy.mm.dd）
     * @return 如：2010-05-28 或2010-05-281 13:21
     */
    public static String toString(Timestamp timestamp, String format) {
        if (timestamp == null) {
            return "";
        }
        return toString(new Date(timestamp.getTime()), format);
    }

    /**
     * Timestamp按照缺省格式转为字符串
     *
     * @param ts 源对象
     * @return 如：2010-05-28
     */
    public static String toString(Timestamp ts) {
        return toString(ts, DEFAULT_DATE_FORMAT);
    }

    /**
     * Timestamp按照缺省格式转为字符串，可指定是否使用长格式
     *
     * @param timestamp  欲转化之变量Timestamp
     * @param fullFormat 是否使用长格式
     * @return 如：2010-05-28 或2010-05-28 21:21
     */
    public static String toString(Timestamp timestamp, boolean fullFormat) {
        if (fullFormat) {
            return toString(timestamp, DEFAULT_DATETIME_FORMAT_SEC);
        } else {
            return toString(timestamp, DEFAULT_DATE_FORMAT);
        }
    }

    /**
     * 将sqldate型按照指定格式转为字符串
     *
     * @param sqldate 源对象
     * @param sFormat ps
     * @return 如：2010-05-28 或2010-05-28 00:00
     */
    public static String toString(java.sql.Date sqldate, String sFormat) {
        if (sqldate == null) {
            return "";
        }
        return toString(new Date(sqldate.getTime()), sFormat);
    }

    /**
     * 将sqldate型按照缺省格式转为字符串
     *
     * @param sqldate 源对象
     * @return 如：2010-05-28
     */
    public static String toString(java.sql.Date sqldate) {
        return toString(sqldate, DEFAULT_DATE_FORMAT);
    }

    /**
     * 计算日期时间之间的差值， date1得时间必须大于date2的时间
     *
     * @param date1
     * @param date2
     * @return {@link Map} Map的键分别为, day(天), hour(小时),minute(分钟)和second(秒)。
     * @version 2011-7-12
     */
    public static Map<String, Long> timeDifference(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException("date1 and date2 can't null");
        }
        long mim1 = date1.getTime();
        long mim2 = date2.getTime();
        if (mim1 < mim2) {
            throw new IllegalArgumentException(String.format("date1[%s] not be less than date2[%s].", mim1 + "", mim2 + ""));
        }
        long m = (mim1 - mim2 + 1) / 1000l;
        long mday = 24 * 3600;
        final Map<String, Long> map = new HashMap<String, Long>();
        map.put("day", m / mday);
        m = m % mday;
        map.put("hour", (m) / 3600);
        map.put("minute", (m % 3600) / 60);
        map.put("second", (m % 3600 % 60));
        return map;
    }




    /**
     * 根据日期获得所在周的日期
     *
     * @param mdate
     * @return
     */
    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a - 1, fdate);
        }
        return list;
    }


    /**
     * 格式化日期
     *
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public String getLastDay(String datadate) throws Exception {
        Date date = null;
        String day_last = null;
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        date = format.parse(datadate);

        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        day_last = format.format(calendar.getTime());
        return day_last;
    }

    public String getFirstDay(String datadate) throws Exception {
        Date date = null;
        String day_first = null;
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        date = format.parse(datadate);

        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        day_first = format.format(calendar.getTime());
        return day_first;
    }


    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /*  */

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *//*
        public static String formatDate(Date date, Object... pattern) {
			String formatDate = null;
			if (pattern != null && pattern.length > 0) {
				formatDate = DateFormatUtils.format(date, pattern[0].toString());
			} else {
				formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
			}
			return formatDate;
		}
		*/
    public static Map<String, String> getStartEndDate(String aDay, int type) {
        Map<String, String> map = new HashMap<String, String>();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy年M月d日");// ("yyyy-MM-dd H:m:s");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// ("yyyy-MM-dd H:m:s");
        Calendar ca = Calendar.getInstance();
        try {
            ca.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(aDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date1 = null, date2 = null;
        if (type == 0) {
            int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);

            // 中国习惯：周一是一周的开始

            if (dayOfWeek == 1) {

                dayOfWeek = 7;

            } else {

                dayOfWeek--;

            }

//            System.out.println(("tag" + "dayofweek=" + dayOfWeek + "min= " + ca.getActualMinimum(Calendar.DAY_OF_WEEK) + " max=" + ca
//                    .getActualMaximum(Calendar.DAY_OF_WEEK)));

            Calendar cal = (Calendar) ca.clone();
            cal.add(Calendar.DATE, 1 - dayOfWeek);

            date1 = cal.getTime();

            cal = (Calendar) ca.clone();

            cal.add(Calendar.DATE, 7 - dayOfWeek);

            date2 = cal.getTime();
        } else if (type == 1) {

            int maximum = ca.getActualMaximum(Calendar.DAY_OF_MONTH);

            int minmum = ca.getActualMinimum(Calendar.DAY_OF_MONTH);

            int day = ca.get(Calendar.DAY_OF_MONTH);

            Calendar cal = (Calendar) ca.clone();

            cal.add(Calendar.DATE, minmum - day);

            date1 = cal.getTime();

            cal = (Calendar) ca.clone();

            cal.add(Calendar.DATE, maximum - day);

            date2 = cal.getTime();

        }
        String stratDate = df.format(date1);

        String endDate = df.format(date2);

//        System.out.println("tag" + "date1=" + stratDate + " date2=" + endDate);
//        System.out.println(stratDate + " " + endDate);

        map.put("stratDate", stratDate);
        map.put("endDate", endDate);

        return map;
    }


    /**
     * 根据参数日期得到上个月日期第一天
     *
     * @param dateTime 参数日期
     * @return
     * @throws ParseException
     */
    public static String getBeforeFirstMonthdate(String dateTime) throws ParseException {
        Date date = null;
        String day_BeforeFirst = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(dateTime);

        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        day_BeforeFirst = format.format(calendar.getTime());
        System.out.println(dateTime + ",上月第一天：" + day_BeforeFirst);
        return day_BeforeFirst;
    }

    /**
     * 根据参数日期获取上个月最后一天日期
     *
     * @param dateTime 参数日期
     * @return
     * @throws ParseException
     */
    public static String getBeforeLastMonthdate(String dateTime) throws ParseException {
        Date date = null;
        String day_BeforeLast = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        day_BeforeLast = format.format(calendar.getTime());
        System.out.println(dateTime + ",上月最后一天：" + day_BeforeLast);
        return day_BeforeLast;
    }


    /**
     * 根据某个日期获取 周、月、年的开始日期及结束日期 、上个月的开始结束日期
     *
     * @param date 格式 eg:2017-02-22
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getStartAndEndDate(String date, String startDate) throws ParseException {
        Map<String, Date> map = new HashMap<String, Date>();

        //日期 系统业务查询起始日期，配合累计到当前日期使用
		 /* if(!StringUtils.isNullOrEmpty(startDate)){
		  map.put("startDate", parseDate(startDate, DEFAULT_DATE_FORMAT));
		  }*/

        //日
        map.put("startDateDay", parseDate(date, DEFAULT_DATE_FORMAT));
        map.put("endDateDay", parseDate(date, DEFAULT_DATE_FORMAT));

        //周
        Map<String, String> weekMap = getStartEndDate(date, 0);
        map.put("startDateWeek", parseDate(weekMap.get("stratDate"), DEFAULT_DATE_FORMAT));
        map.put("endDateWeek", parseDate(weekMap.get("endDate"), DEFAULT_DATE_FORMAT));

        //月
        Map<String, String> monthMap = getStartEndDate(date, 1);
        map.put("startDateMonth", parseDate(monthMap.get("stratDate"), DEFAULT_DATE_FORMAT));
        map.put("endDateMonth", parseDate(monthMap.get("endDate"), DEFAULT_DATE_FORMAT));

        //年
        int year = Integer.parseInt(date.substring(0, 4));
        map.put("startDateYear", parseDate(formatDate(getYearFirst(year)), DEFAULT_DATE_FORMAT));
        map.put("endDateYear", parseDate(formatDate(getYearLast(year)), DEFAULT_DATE_FORMAT));

        //当前日期的去年开始日期结束日期
        int yearBefore = Integer.parseInt(date.substring(0, 4)) - 1;
        map.put("startDateYearBefore", parseDate(formatDate(getYearFirst(yearBefore)), DEFAULT_DATE_FORMAT));
        map.put("endDateYearBefore", parseDate(formatDate(getYearLast(yearBefore)), DEFAULT_DATE_FORMAT));


        //获取某月的上个月日期
        map.put("beforeMonthFirstDay", parseDate(getBeforeFirstMonthdate(date), DEFAULT_DATE_FORMAT));
        map.put("beforeMonthLastDay", parseDate(getBeforeLastMonthdate(date), DEFAULT_DATE_FORMAT));


        return map;
    }


    /**
     * 获取当前日期前一天的日期(格式yyyy-MM-dd)
     *
     * @return
     */
    public static String getBeforeDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(format.format(calendar.getTime()));
        return format.format(calendar.getTime());
    }

    /**
     * 获取当前日期前一天的日期(格式yyyyMMdd)
     *
     * @return
     */
    public static String getYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //System.out.println(format.format(calendar.getTime()));
        return format.format(calendar.getTime());
    }

    public static String getTheDayBeforeYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //System.out.println(format.format(calendar.getTime()));
        return format.format(calendar.getTime());
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 校验日期字符串格式
     *
     * @param date      日期  需要被校验日期
     * @param strFormat 合法字符串日期格式  eg:yyyy-MM-dd
     * @return 合法日期返回true 否则返回false
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isValidDate(String date, String strFormat) {
        if (date != null && strFormat != null && date.length() == strFormat.length()) {
            SimpleDateFormat format = new SimpleDateFormat(strFormat);
            format.setLenient(false);//设置Calendar是否宽松解析字符串如果为false，则严格解析；默认为true，宽松解析
            try {
                format.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 获取当前年份
     *
     * @return 2018年返回18
     */
    public static String getCurrnetYY() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        return sdf.format(new Date());
    }

    /**
     * 获取时间
     * 格式：
     * 2018-01-01 00:00:00
     *
     * @return
     */
    public static String getCurrentYYYYMMDDHHMMSS() {
        return new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC).format(new Date());
    }

    /**
     * 获取时间
     * 格式：
     * 2018-01-01 00:00:00
     *
     * @return
     */
    public static String getCurrentYYYYMMDDHHMMSS_T() {
        return new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC_T).format(new Date());
    }

    /**
     * 时间格式：20180101
     */
    public static String getCurrentYYYYMMDD() {
        return new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).format(new Date());
    }

    /**
     * 日期比较大小
     *
     * @param DATE1 实效时间
     * @param DATE2 当前时间
     * @return
     */

    public static int compareDate(Date DATE1, Date DATE2) {
        try {

            if (DATE1.getTime() > DATE2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (DATE1.getTime() < DATE2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    public static SimpleDateFormat getDateFormat(String parttern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        EnumDateStyle EnumDateStyle = getEnumDateStyle(date);
        if (EnumDateStyle != null) {
            Date myDate = StringToDate(date, EnumDateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, EnumDateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    public static void main(String[] args) {

        Date date = new Date();
        System.out.println("当前日期:" + date);
        System.out.println("加10天:" + DateTimeUtil.addInteger(date, Calendar.DATE, 10));
        System.out.println("加10周:" + DateTimeUtil.addInteger(date, Calendar.WEEK_OF_YEAR, 10));
        System.out.println("加10月:" + DateTimeUtil.addInteger(date, Calendar.MONTH, 10));
        System.out.println("加10年:" + DateTimeUtil.addInteger(date, Calendar.YEAR, 10));
        System.out.println("加10年:" + DateTimeUtil.addMonth("20191031", 4));
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    // 如果timestamps的size为2，这是差值只有一个，因此要给默认值
                    minAbsoluteValue = absoluteValues.get(0);
                }
                for (int i = 0; i < absoluteValues.size(); i++) {
                    for (int j = i + 1; j < absoluteValues.size(); j++) {
                        if (absoluteValues.get(i) > absoluteValues.get(j)) {
                            minAbsoluteValue = absoluteValues.get(j);
                        } else {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                    } else if (absoluteValues.size() == 1) {
                        // 当timestamps的size为2，需要与当前时间作为参照
                        long dateOne = timestampsLastTmp[0];
                        long dateTwo = timestampsLastTmp[1];
                        if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
                            timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                        } else {
                            long now = new Date().getTime();
                            if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
                                timestamp = dateOne;
                            } else {
                                timestamp = dateTwo;
                            }
                        }
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (StringToDate(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static EnumDateStyle getEnumDateStyle(String date) {
        EnumDateStyle EnumDateStyle = null;
        Map<Long, EnumDateStyle> map = new HashMap<>();
        List<Long> timestamps = new ArrayList<Long>();
        for (EnumDateStyle style : EnumDateStyle.values()) {
            Date dateTmp = StringToDate(date, style.getValue());
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        EnumDateStyle = map.get(getAccurateDate(timestamps).getTime());
        return EnumDateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        EnumDateStyle EnumDateStyle = null;
        return StringToDate(date, EnumDateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date     日期字符串
     * @param parttern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(parttern).parse(date);
            } catch (Exception e) {

            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date          日期字符串
     * @param EnumDateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, EnumDateStyle EnumDateStyle) {
        Date myDate = null;
        if (EnumDateStyle == null) {
            List<Long> timestamps = new ArrayList<Long>();
            for (EnumDateStyle style : EnumDateStyle.values()) {
                Date dateTmp = StringToDate(date, style.getValue());
                if (dateTmp != null) {
                    timestamps.add(dateTmp.getTime());
                }
            }
            myDate = getAccurateDate(timestamps);
        } else {
            myDate = StringToDate(date, EnumDateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date     日期
     * @param parttern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date          日期
     * @param EnumDateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, EnumDateStyle EnumDateStyle) {
        String dateString = null;
        if (EnumDateStyle != null) {
            dateString = DateToString(date, EnumDateStyle.getValue());
        }
        return dateString;
    }

    public static String DateToString(Date date) {
        return DateToString(date, EnumDateStyle.YYYY_MM_DD_HH_MM);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date     旧日期字符串
     * @param parttern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String parttern) {
        return StringToString(date, null, parttern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date          旧日期字符串
     * @param EnumDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, EnumDateStyle EnumDateStyle) {
        return StringToString(date, null, EnumDateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddParttern 旧日期格式
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddParttern, String newParttern) {
        String dateString = null;
        if (olddParttern == null) {
            EnumDateStyle style = getEnumDateStyle(date);
            if (style != null) {
                Date myDate = StringToDate(date, style.getValue());
                dateString = DateToString(myDate, newParttern);
            }
        } else {
            Date myDate = StringToDate(date, olddParttern);
            dateString = DateToString(myDate, newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date             旧日期字符串
     * @param olddDteStyle     旧日期风格
     * @param newEnumDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, EnumDateStyle olddDteStyle, EnumDateStyle newEnumDateStyle) {
        String dateString;
        if (olddDteStyle == null) {
            EnumDateStyle style = getEnumDateStyle(date);
            dateString = StringToString(date, style.getValue(), newEnumDateStyle.getValue());
        } else {
            dateString = StringToString(date, olddDteStyle.getValue(), newEnumDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param amount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int amount) {
        return addInteger(date, Calendar.YEAR, amount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param amount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int amount) {
        return addInteger(date, Calendar.YEAR, amount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param amount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int amount) {
        return addInteger(date, Calendar.MONTH, amount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param amount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int amount) {
        return addInteger(date, Calendar.MONTH, amount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param amount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int amount) {
        return addInteger(date, Calendar.DATE, amount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param amount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int amount) {
        return addInteger(date, Calendar.DATE, amount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期字符串
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int amount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int amount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期字符串
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int amount) {
        return addInteger(date, Calendar.MINUTE, amount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int amount) {
        return addInteger(date, Calendar.MINUTE, amount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期字符串
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int amount) {
        return addInteger(date, Calendar.SECOND, amount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int amount) {
        return addInteger(date, Calendar.SECOND, amount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH);
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, EnumDateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, EnumDateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, EnumDateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, EnumDateStyle.HH_MM_SS);
    }

    /**
     * 获得指定日期所在当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MINUTE, 59);
        date = c.getTime();
        return date;
    }

    /**
     * 获取当前月的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 比较二个日期之间相差的天数
     * @param d1
     * @param d2
     * @return
     */
    public static int betweenDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);

        return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前日期前一年的年份(格式yyyy)
     * @return
     */
    public static String getLastYear() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(calendar.getTime());
    }

}
