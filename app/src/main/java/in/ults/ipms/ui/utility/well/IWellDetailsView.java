package in.ults.ipms.ui.utility.well;

import in.ults.ipms.ui.base.IBaseView;

public interface IWellDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
