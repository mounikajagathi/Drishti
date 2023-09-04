package in.ults.ipms.ui.utility.road.details;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by AmalB on 17/06/21.
 */

public interface IRoadDetailsView extends IBaseView {
    void showRoadAddFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
