package in.ults.ipms.ui.buildingassets.otherproperty;

import in.ults.ipms.ui.base.IBaseView;

public interface IPropertyOtherDetailsView extends IBaseView {

    void showPropertyOtherDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
