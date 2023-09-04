package in.ults.ipms.ui.utility.signboard;

import in.ults.ipms.ui.base.IBaseView;

public interface IRoadSignboardDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
