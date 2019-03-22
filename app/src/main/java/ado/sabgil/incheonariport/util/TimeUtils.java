package ado.sabgil.incheonariport.util;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private TimeUtils() {
    }

    public static String getCurrentHourString() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("kk00", java.util.Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static int getCurrentHourInteger() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("kk", java.util.Locale.getDefault());

        return Integer.parseInt(simpleDateFormat.format(date));
    }

    public static String getAfterHour(String currentHour, int intAfterHour) {
        if (intAfterHour > 23 || intAfterHour < 0) {
            throw new InvalidParameterException();
        } else {
            int intCurrentHour = Integer.parseInt(currentHour);
            int sum = intCurrentHour + intAfterHour * 100;

            if (sum < 2400) {
                return Integer.toString(sum);
            } else {
                return "2400";
            }
        }
    }

}
