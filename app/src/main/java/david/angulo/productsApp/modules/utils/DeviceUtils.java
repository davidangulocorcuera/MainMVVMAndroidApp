package david.angulo.productsApp.modules.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.TimeZone;


public class DeviceUtils {
    public static final String LOG_TAG = DeviceUtils.class.getSimpleName();

    public static float getDeviceHeightDp() {
        DisplayMetrics metrics = MainApplication.instance.getApplicationContext().getResources().getDisplayMetrics();
        float dpHeight = metrics.heightPixels / metrics.density;
        return dpHeight;
    }

    public static int getDeviceWidthDp() {
        DisplayMetrics metrics = MainApplication.instance.getApplicationContext().getResources().getDisplayMetrics();
        float dpWidth = metrics.widthPixels / metrics.density;
        return (int) dpWidth;
    }

    public static int getDeviceWidthPx() {
        DisplayMetrics metrics = MainApplication.instance.getApplicationContext().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int dpToPx(int dp) {
        return dpToPx(MainApplication.instance.getApplicationContext(), dp);
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        hideKeyboard(activity, view);
    }

    public static void hideKeyboard(Activity activity, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

    public static int getDimensionInDp(Context context, int resource) {
        int dp =
            (int) (context.getResources().getDimension(resource) / context.getResources().getDisplayMetrics().density);
        return dp;
    }

}
