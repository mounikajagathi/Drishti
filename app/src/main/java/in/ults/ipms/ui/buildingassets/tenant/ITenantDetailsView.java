package in.ults.ipms.ui.buildingassets.tenant;

import in.ults.ipms.ui.base.IBaseView;

public interface ITenantDetailsView extends IBaseView {

    void showTenantDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
