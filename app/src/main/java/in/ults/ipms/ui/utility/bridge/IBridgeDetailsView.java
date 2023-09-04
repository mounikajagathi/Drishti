package in.ults.ipms.ui.utility.bridge;

import in.ults.ipms.ui.base.IBaseView;

public interface IBridgeDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
