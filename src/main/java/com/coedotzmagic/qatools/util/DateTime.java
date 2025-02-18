package com.coedotzmagic.qatools.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * write by Coedotz
 * 11-02-2025
 */

public class DateTime {

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

}
