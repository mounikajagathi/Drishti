package in.ults.ipms.ui.utility.garbage;

import in.ults.ipms.ui.base.IBaseView;

public interface IGarbageDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
