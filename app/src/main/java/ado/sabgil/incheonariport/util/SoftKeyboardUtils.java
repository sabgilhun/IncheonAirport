package ado.sabgil.incheonariport.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtils {

    private SoftKeyboardUtils() {
    }

    public static void hideKeyboard(Context context, View focusedView) {
        InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
    }
}
