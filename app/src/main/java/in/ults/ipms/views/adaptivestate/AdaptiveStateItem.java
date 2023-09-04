package in.ults.ipms.views.adaptivestate;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

/**
 * Created by Mohammed Shafeeq on 9/4/2018.
 */
public class AdaptiveStateItem {

    private final int layoutID;
    private final int[] buttonId;

    public AdaptiveStateItem(@LayoutRes int layoutID, @IdRes int... buttonId) {
        this.layoutID = layoutID;
        this.buttonId = buttonId;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public int[] getButtonId() {
        return buttonId;
    }
}
