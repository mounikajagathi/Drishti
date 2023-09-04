package in.ults.ipms.utils.view;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.ViewGroup;


/**
 * Created by Mohammed Shafeeq on 22/07/18.
 */
public class ViewUtils {


    private ViewUtils() {
    }


    public static ViewGroup getNullParent() {
        return null;
    }


    public static SpannableStringBuilder addBoldText(String mainString, String customizableString) {
        final SpannableStringBuilder sb = new SpannableStringBuilder(mainString);
        int startingPosition = mainString.indexOf(customizableString);
        int endingPosition = startingPosition + customizableString.length();
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
        sb.setSpan(bss, startingPosition, endingPosition, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
        sb.setSpan(new ForegroundColorSpan(Color.BLACK), startingPosition, endingPosition, 0);
        return sb;
    }
}
