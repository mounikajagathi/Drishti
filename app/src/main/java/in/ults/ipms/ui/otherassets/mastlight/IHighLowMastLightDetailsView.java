package in.ults.ipms.ui.otherassets.mastlight;

import in.ults.ipms.ui.base.IBaseView;

public interface IHighLowMastLightDetailsView extends IBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess(boolean isUpdate);
}
