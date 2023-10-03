package in.ults.ipms.ui.utility.streettap;

import in.ults.ipms.ui.base.IBaseView;

public interface IStreetTapDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
