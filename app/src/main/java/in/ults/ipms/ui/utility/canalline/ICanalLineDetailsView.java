package in.ults.ipms.ui.utility.canalline;

import in.ults.ipms.ui.base.IBaseView;

public interface ICanalLineDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
