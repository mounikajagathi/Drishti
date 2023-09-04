package in.ults.ipms.utils.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Window;

import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.ults.ipms.ui.base.BaseActivity;

/**
 * Created by Mohammed Shafeeq on 01/08/18.
 */
public class CommonUtils {
    public static final int CONTENT_TYPE_STRING_ARRAY = 1;
    public static final int CONTENT_TYPE_ARRAY_LIST = 2;

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNullString(String o) {
        return o == null || o.length() <= 0;
    }

    public static String removeNullContent(String content) {
        if (content != null) {
            return content;
        } else {
            return "";
        }
    }

    public static String encryptDataToBase64(String content) {
        if(content!=null) {
            byte[] data = content.getBytes(StandardCharsets.UTF_8);
            return Base64.encodeToString(data, Base64.DEFAULT);
        }else{
            return content;
        }
    }
    public static String decryptBase64Data(String content) {
        if(content!=null) {
            byte[] data = Base64.decode(content, Base64.DEFAULT);
            return new String(data, StandardCharsets.UTF_8);
        }else{
            return content;
        }

    }


    public static int getContentSize(Object content, @CommonUtilsDataType int type) {
        if (isNull(content)) {
            return 0;
        } else {
            switch (type) {
                case CONTENT_TYPE_STRING_ARRAY:
                    return ((String[]) (content)).length;
                case CONTENT_TYPE_ARRAY_LIST:
                    return ((ArrayList) (content)).size();
                default:
                    return 0;
            }
        }
    }


    //Method to check whether the email-id is valid or not.
    public static boolean isValidEmail(String email) {
        try {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CONTENT_TYPE_ARRAY_LIST, CONTENT_TYPE_STRING_ARRAY})
    public @interface CommonUtilsDataType {
    }



    public static int getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.heightPixels;
    }


    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static void setDialogNavigationBarColor(BaseActivity activity, @NonNull Dialog dialog, @ColorRes int color) {
        Window window = dialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();
            // ...customize your dim effect here

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(ContextCompat.getColor(activity, color));

            Drawable[] layers = {dimDrawable, navigationBarDrawable};

            LayerDrawable windowBackground = new LayerDrawable(layers);
            windowBackground.setLayerInsetTop(1, metrics.heightPixels);

            window.setBackgroundDrawable(windowBackground);
        }
    }



}
