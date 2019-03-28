package ado.sabgil.incheonariport.util;

import android.content.Context;

public class LogoFinder {

    private LogoFinder() {
    }

    public static int getLogoFromFlightId(Context context, String flightId) {
        StringBuilder stringBuilder = new StringBuilder(flightId.substring(0, 2).toLowerCase());

        stringBuilder
                .append("_logo")
                .insert(0, "img_");

        return context
                .getResources()
                .getIdentifier(stringBuilder.toString(), "drawable", context.getPackageName());
    }
}
