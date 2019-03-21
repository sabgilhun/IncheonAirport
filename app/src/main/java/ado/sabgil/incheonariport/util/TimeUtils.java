package ado.sabgil.incheonariport.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private TimeUtils() {

    }

    public static String getCurrentHour() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("kk00", java.util.Locale.getDefault());
        return simpleDateFormat.format(date);
    }

}
