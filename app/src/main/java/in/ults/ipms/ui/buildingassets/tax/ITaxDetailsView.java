package in.ults.ipms.ui.buildingassets.tax;

import in.ults.ipms.ui.base.IBaseView;

public interface ITaxDetailsView extends IBaseView {

    void showTaxDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();

}
