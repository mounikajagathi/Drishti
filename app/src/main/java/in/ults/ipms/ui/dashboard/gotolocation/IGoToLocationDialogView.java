package in.ults.ipms.ui.dashboard.gotolocation;


import in.ults.ipms.ui.base.DialogBaseView;

/**
 * Created by Mohammed Shafeeq on 24/05/17.
 */

public interface IGoToLocationDialogView extends DialogBaseView {
    void showErrors(int errorType, String error);
    void clearErrors();
}
