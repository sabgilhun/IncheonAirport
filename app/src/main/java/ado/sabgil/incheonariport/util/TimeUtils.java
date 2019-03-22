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

    public static String getAfterHour(String currentHour, int intAfterHour) throws Exception {
        if (intAfterHour < 24 && intAfterHour > 0) {
            int intCurrentHour = Integer.parseInt(currentHour);
            int sum = intCurrentHour + intAfterHour * 100;

            if (sum < 2400) {
                return Integer.toString(sum);
            } else {
                return "2400";
            }

        } else {
            throw new Exception("invalid parameter");
        }
    }

}
