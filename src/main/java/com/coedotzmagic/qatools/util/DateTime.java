package com.coedotzmagic.qatools.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/*
 * write by Coedotz
 * 11-02-2025
 */

public class DateTime {
    static Map<String, Integer> dateTime = getCurrentDateTimeParts();

    /**
     * <b>getDateTime()</b>
     * used to get today's Date and Time
     *
     * @since 1.0
     */
    public static String getDateTime() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);
        formattedDateTime = formattedDateTime.replaceAll(":", ".");
        return formattedDateTime;
    }

    /**
     * <b>getCurrentYear()</b>
     * used to get current year
     *
     * @since 1.5.0
     */
    public static String getCurrentYear() {
        return dateTime.get("year").toString();
    }

    /**
     * <b>getCurrentMonth()</b>
     * used to get current month
     *
     * @since 1.5.0
     */
    public static String getCurrentMonth() {
        return dateTime.get("month").toString();
    }

    /**
     * <b>getCurrentDay()</b>
     * used to get current day
     *
     * @since 1.5.0
     */
    public static String getCurrentDay() {
        return dateTime.get("day").toString();
    }

    /**
     * <b>getCurrentHour()</b>
     * used to get current hour
     *
     * @since 1.5.0
     */
    public static String getCurrentHour() {
        return dateTime.get("hour").toString();
    }

    /**
     * <b>getCurrentMinute()</b>
     * used to get current minute
     *
     * @since 1.5.0
     */
    public static String getCurrentMinute() {
        return dateTime.get("minute").toString();
    }
    /**
     * <b>getCurrentSecond()</b>
     * used to get current second
     *
     * @since 1.5.0
     */
    public static String getCurrentSecond() {
        return dateTime.get("second").toString();
    }

    /**
     * <b>getCurrentDateTimeParts()</b>
     * used to get today's Date and Time separate & specific
     *
     * @since 1.5.0
     */
    private static Map<String, Integer> getCurrentDateTimeParts() {
        LocalDateTime now = LocalDateTime.now();

        Map<String, Integer> dateTimeParts = new HashMap<>();
        dateTimeParts.put("year", now.getYear());
        dateTimeParts.put("month", now.getMonthValue()); // 1 to 12
        dateTimeParts.put("day", now.getDayOfMonth());
        dateTimeParts.put("hour", now.getHour());
        dateTimeParts.put("minute", now.getMinute());
        dateTimeParts.put("second", now.getSecond());

        return dateTimeParts;
    }

}