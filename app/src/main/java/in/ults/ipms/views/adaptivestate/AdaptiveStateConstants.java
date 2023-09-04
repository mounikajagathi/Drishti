package in.ults.ipms.views.adaptivestate;

import android.annotation.SuppressLint;

import java.util.HashMap;

import in.ults.ipms.R;

/**
 * Created by Mohammed Shafeeq on 9/4/2018.
 */
@SuppressLint("UseSparseArrays")
public class AdaptiveStateConstants {

    public static final int ADAPTIVE_STATE_NO_NETWORK = 1;
    public static final int ADAPTIVE_STATE_SERVER_ERROR = 2;
    public static final int ADAPTIVE_STATE_PROGRESS_BAR = 3;

    public static HashMap<Integer, AdaptiveStateItem> getFullStates() {
        HashMap<Integer, AdaptiveStateItem> states = new HashMap<>();
        states.put(AdaptiveStateConstants.ADAPTIVE_STATE_NO_NETWORK, new AdaptiveStateItem(R.layout.error_state_no_internet,R.id.btnTryAgainInternet));
        states.put(AdaptiveStateConstants.ADAPTIVE_STATE_PROGRESS_BAR, new AdaptiveStateItem(R.layout.state_progress_dialog));
        states.put(AdaptiveStateConstants.ADAPTIVE_STATE_SERVER_ERROR, new AdaptiveStateItem(R.layout.error_state_server_error,R.id.btnTryAgainServerError));
        return states;
    }
}
