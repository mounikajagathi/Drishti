package in.ults.ipms.ui.utility.parking;

import in.ults.ipms.ui.base.IBaseView;

public interface IParkingDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
