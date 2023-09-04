package in.ults.ipms.ui.utility.divider;

import in.ults.ipms.ui.base.IBaseView;

public interface IDividerDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
