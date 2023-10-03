package in.ults.ipms.ui.utility.bus;

import in.ults.ipms.ui.base.IBaseView;

public interface IBusStandDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
