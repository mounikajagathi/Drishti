package in.ults.ipms.ui.buildingassets.road;

import in.ults.ipms.ui.base.IBaseView;

public interface IRoadDetailsView extends IBaseView {

    void showRoadDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
