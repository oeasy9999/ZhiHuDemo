package com.oeasy9999.zhihudemo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public class TimeUtils {

  private static final int MINUTE = 60;
  private static final int HOUR = MINUTE * 60;
  private static final int DAY = HOUR * 24;
  private static final int MONTH = DAY * 30;
  private static final int YEAR = MONTH * 12;

  public static String convertDate(String date) {
    String today = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
    if (today.equals(date)) {
      return "今日热闻";
    }
    String result = date.substring(0, 4);
    result += "年";
    result += date.substring(4, 6);
    result += "月";
    result += date.substring(6, 8);
    result += "日";
    return result;
  }
  //2016-06-08T18:07:21+08:00
  public static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
  public static String convertPublishTime(String time) {
    try {
      long s = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - FORMAT.parse(time).getTime());
      long count = s / YEAR;
      if (count != 0) {
        return count + "年前";
      }
      count = s / MONTH;
      if (count != 0) {
        return count + "月前";
      }
      count = s / DAY;
      if (count != 0) {
        return count + "天前";
      }
      count = s / HOUR;
      if (count != 0) {
        return count + "小时前";
      }
      return "刚刚";
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "未知时间";
  }
}
