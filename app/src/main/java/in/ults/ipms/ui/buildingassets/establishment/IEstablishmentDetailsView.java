package in.ults.ipms.ui.buildingassets.establishment;

import in.ults.ipms.ui.base.IBaseView;

public interface IEstablishmentDetailsView extends IBaseView {

    void showEstablishmentDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
