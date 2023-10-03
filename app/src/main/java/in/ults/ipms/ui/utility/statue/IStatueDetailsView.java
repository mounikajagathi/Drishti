package in.ults.ipms.ui.utility.statue;

import in.ults.ipms.ui.base.IBaseView;

public interface IStatueDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
