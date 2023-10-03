package in.ults.ipms.ui.utility.canal;

import in.ults.ipms.ui.base.IBaseView;

public interface ICanalDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
