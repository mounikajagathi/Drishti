package in.ults.ipms.ui.utility.junction;

import in.ults.ipms.ui.base.IBaseView;

public interface IRoadJunctionDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
