package in.ults.ipms.ui.utility.hump;

import in.ults.ipms.ui.base.IBaseView;

public interface IRoadHumpDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
