package in.ults.ipms.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AutoSizeFab extends FloatingActionButton {

    public AutoSizeFab(@NonNull Context context) {
        super(context);
    }

    public AutoSizeFab(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSizeFab(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = Resources.getSystem().getDisplayMetrics().heightPixels/12;
        setCustomSize(height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
