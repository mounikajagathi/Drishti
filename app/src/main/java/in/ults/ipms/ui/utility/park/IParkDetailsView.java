package in.ults.ipms.ui.utility.park;

import in.ults.ipms.ui.base.IBaseView;

public interface IParkDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
