package in.ults.ipms.ui.buildingassets.tenant;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TenantDetailsPresenter <V extends ITenantDetailsView, I extends ITenantDetailsInteractor>
        extends BasePresenter<V, I> implements ITenantDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public TenantDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String firstName, String lastName, String email, String mobile,String landphone, String gender,String placeName,String village, String street,String tenantHouse, String tenantNative,String tenantState,String tenantPostoffice,String tenantPincode,String tenantSurveyNumber, String rentAmount, String tenantStatus) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(firstName)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_FIRST_NAME,baseActivity.getResources().getString(R.string.err_first_name));
            return;
        }
        if (CommonUtils.isNullString(lastName)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_LAST_NAME,baseActivity.getResources().getString(R.string.err_last_name));
            return;
        }
        if (CommonUtils.isNullString(gender)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_GENDER,baseActivity.getResources().getString(R.string.err_gender));
            return;
        }
        if (CommonUtils.isNullString(placeName)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_PLACE_NAME,baseActivity.getResources().getString(R.string.err_place_name));
            return;
        }
        if (CommonUtils.isNullString(village)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_VILLAGE,baseActivity.getResources().getString(R.string.err_village));
            return;
        }
        if (CommonUtils.isNullString(street)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_STREET,baseActivity.getResources().getString(R.string.err_street));
            return;
        }
        if (CommonUtils.isNullString(tenantHouse)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_HOUSE,baseActivity.getResources().getString(R.string.err_owner_house));
            return;
        }
        if (CommonUtils.isNullString(mobile)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_MOBILE,baseActivity.getResources().getString(R.string.err_mobile));
            return;
        }
        if (CommonUtils.isNullString(landphone)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_LAND_PHONE,baseActivity.getResources().getString(R.string.err_landphone));
            return;
        }
        if (CommonUtils.isNullString(email)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_EMAIL,baseActivity.getResources().getString(R.string.err_email));
            return;
        }
        if (CommonUtils.isNullString(tenantNative)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_NATIVE,baseActivity.getResources().getString(R.string.err_tenant_native));
            return;
        }
        if (CommonUtils.isNullString(tenantState)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_STATE,baseActivity.getResources().getString(R.string.err_state));
            return;
        }
        if (CommonUtils.isNullString(tenantPostoffice)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_POSTOFFICE,baseActivity.getResources().getString(R.string.err_postoffice));
            return;
        }
        if (CommonUtils.isNullString(tenantPincode)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_PINCODE,baseActivity.getResources().getString(R.string.err_pincode));
            return;
        }
        if (CommonUtils.isNullString(tenantSurveyNumber)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_SURVEY_NUMBER,baseActivity.getResources().getString(R.string.err_owner_survey_number));
            return;
        }
        if (CommonUtils.isNullString(rentAmount)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_RENT_AMOUNT,baseActivity.getResources().getString(R.string.err_rent_amount));
            return;
        }
        if (CommonUtils.isNullString(tenantStatus)) {
            getMvpView().showTenantDetailsFieldError(TenantDetailsFragment.ERROR_TYPE_TENANT_STATUS,baseActivity.getResources().getString(R.string.err_tenant_status));
            return;
        }
        savingTenantDetails(firstName,lastName,email,mobile,landphone,gender,placeName,village,street,tenantHouse,tenantNative,tenantState,tenantPostoffice,tenantPincode,tenantSurveyNumber,rentAmount,tenantStatus);
    }

    public void savingTenantDetails(String firstName, String lastName, String email, String mobile,String landphone, String gender,String placeName,String village, String street,String tenantHouse, String tenantNative,String tenantState,String tenantPostoffice,String tenantPincode,String tenantSurveyNumber, String rentAmount, String tenantStatus){

        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                BuildingAssets.TenantDetails tenantDetails = AppCacheData.getOurInstance().getBuildingAssetData().getTenantDetails();
                data.setTenantDetails(tenantDetails == null ? new BuildingAssets.TenantDetails() : tenantDetails);
                data.getTenantDetails().setFirstname(firstName);
                data.getTenantDetails().setLastname(lastName);
                data.getTenantDetails().setEmail(email);
                data.getTenantDetails().setMobile(mobile);
                data.getTenantDetails().setLandphone(landphone);
                data.getTenantDetails().setGender(Integer.parseInt(gender));
                data.getTenantDetails().setPlaceName(placeName);
                data.getTenantDetails().setVillage(village);
                data.getTenantDetails().setStreet(street);
                data.getTenantDetails().setTntHouseName(tenantHouse);
                data.getTenantDetails().setTntNative(tenantNative);
                data.getTenantDetails().setTenantState(tenantState);
                data.getTenantDetails().setTntPostOffice(tenantPostoffice);
                data.getTenantDetails().setTenantPincode(tenantPincode);
                data.getTenantDetails().setTntSurveyNo(tenantSurveyNumber);
                data.getTenantDetails().setRentAmount(rentAmount);
                data.getTenantDetails().setTenantStatus(tenantStatus);
                data.getTenantDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                BuildingAssets.TenantDetails tenantDetails = data.getTenantDetails();
                if (tenantDetails == null) {
                    tenantDetails = new BuildingAssets.TenantDetails();
                    if (data.getPropertyDetails() != null) {
                        tenantDetails.setProperty(data.getPropertyDetails().getPk());
                    }
                    tenantDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                } else {
                    tenantDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                tenantDetails.setFirstname(firstName);
                tenantDetails.setLastname(lastName);
                tenantDetails.setEmail(email);
                tenantDetails.setMobile(mobile);
                tenantDetails.setLandphone(landphone);
                tenantDetails.setGender(Integer.parseInt(gender));
                tenantDetails.setPlaceName(placeName);
                tenantDetails.setVillage(village);
                tenantDetails.setStreet(street);
                tenantDetails.setTntHouseName(tenantHouse);
                tenantDetails.setTntNative(tenantNative);
                tenantDetails.setTenantState(tenantState);
                tenantDetails.setTntPostOffice(tenantPostoffice);
                tenantDetails.setTenantPincode(tenantPincode);
                tenantDetails.setTntSurveyNo(tenantSurveyNumber);
                tenantDetails.setRentAmount(rentAmount);
                tenantDetails.setTenantStatus(tenantStatus);
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setTenantDetails(tenantDetails);
                saveAssetDetails(newData);
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null && response.getData() != null) {
            AppCacheData.getOurInstance().getBuildingAssetData().setTenantDetails(response.getData().getTenantDetails());
        }
        getMvpView().onAddOrUpdateSuccess();
    }
}
