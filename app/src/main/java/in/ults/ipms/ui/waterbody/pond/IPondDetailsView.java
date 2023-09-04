package in.ults.ipms.ui.waterbody.pond;

import in.ults.ipms.ui.base.IBaseView;

public interface IPondDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
