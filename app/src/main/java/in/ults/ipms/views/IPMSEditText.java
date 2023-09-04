package in.ults.ipms.views;

import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;

import in.ults.ipms.R;

public class IPMSEditText extends TextInputEditText {
    public IPMSEditText(Context context) {
        super(context);
        init(context);
    }

    public IPMSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IPMSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        this.setPadding(context.getResources().getDimensionPixelOffset(R.dimen.standard_spacing_normal),context.getResources().getDimensionPixelOffset(R.dimen.standard_spacing_normal),context.getResources().getDimensionPixelOffset(R.dimen.standard_spacing_normal),context.getResources().getDimensionPixelOffset(R.dimen.standard_spacing_normal));
        this.setInputType(this.getInputType()  | TYPE_TEXT_FLAG_CAP_CHARACTERS);
    }
}

