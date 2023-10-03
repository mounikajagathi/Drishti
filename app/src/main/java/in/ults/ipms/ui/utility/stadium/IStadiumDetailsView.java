package in.ults.ipms.ui.utility.stadium;

import in.ults.ipms.ui.base.IBaseView;

public interface IStadiumDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
