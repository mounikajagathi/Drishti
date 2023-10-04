package in.ults.ipms.ui.buildingassets.tenant;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ITenantDetailsPresenter <V extends ITenantDetailsView,I extends ITenantDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String firstName, String lastName, String email, String mobile,String landphone, String gender,String placeName,String village, String street,String tenantHouse, String tenantNative,String tenantState,String tenantPostoffice,String tenantPincode,String tenantSurveyNumber, String rentAmount, String tenantStatus);
}
