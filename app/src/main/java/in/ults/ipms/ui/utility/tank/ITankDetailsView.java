package in.ults.ipms.ui.utility.tank;

import in.ults.ipms.ui.base.IBaseView;

public interface ITankDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
