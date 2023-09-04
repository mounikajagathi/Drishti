package in.ults.ipms.ui.utility.playground;

import in.ults.ipms.ui.base.IBaseView;

public interface IPlaygroundDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
