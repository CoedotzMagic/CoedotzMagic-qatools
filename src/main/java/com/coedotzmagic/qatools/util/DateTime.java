package com.coedotzmagic.qatools.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

    /**
     * <b>getDateTime()</b>
     * digunakan untuk mendapatkan Tanggal dan Waktu hari ini
     * biasanya fungsi ini digunakan untuk pembuatan di summary ticketing
     *
     * @since 1.0
     */
    public String getDateTime() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);
        formattedDateTime = formattedDateTime.replaceAll(":", ".");
        return formattedDateTime;
    }

}
