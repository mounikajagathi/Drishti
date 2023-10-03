package in.ults.ipms.ui.utility.mobiletower;

import in.ults.ipms.ui.base.IBaseView;

public interface IMobileTowerDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
