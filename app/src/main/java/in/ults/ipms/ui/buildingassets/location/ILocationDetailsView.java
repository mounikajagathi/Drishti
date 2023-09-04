package in.ults.ipms.ui.buildingassets.location;

import in.ults.ipms.ui.base.IBaseView;

public interface ILocationDetailsView extends IBaseView {
    void showLocationDetailsFieldError(int errorType, String error);
    void clearErrors();
    void goToNext();
    void onAddOrUpdateSuccess();
}
