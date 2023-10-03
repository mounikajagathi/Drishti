package in.ults.ipms.ui.utility.busbay;

import in.ults.ipms.ui.base.IBaseView;

public interface IBusBayDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
