package in.ults.ipms.ui.utility.taxistand;

import in.ults.ipms.ui.base.IBaseView;

public interface ITaxiStandDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
