package in.ults.ipms.ui.buildingassets.owner;

import in.ults.ipms.ui.base.IBaseView;

public interface IOwnerDetailsView extends IBaseView {

    void showOwnerDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
