package in.ults.ipms.ui.buildingassets.establishment;

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

public class EstablishmentDetailsPresenter<V extends IEstablishmentDetailsView, I extends IEstablishmentDetailsInteractor>
        extends BasePresenter<V, I> implements IEstablishmentDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public EstablishmentDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String establishmentName, String establishmentType, String establishmentPost, String establishmentYear, String mobile, String landphone, String email, String inchargeName, String inchargeRole, String inchargePost, String professionalTaxPaymentStatus, String numberOfEmployees, String license, String licenseNumber, String gst, String gstNumber) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(establishmentName)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_ESTABLISHMENT_NAME, baseActivity.getResources().getString(R.string.err_establishment_name));
            return;
        }
        if (CommonUtils.isNullString(establishmentType)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_ESTABLISHMENT_TYPE, baseActivity.getResources().getString(R.string.err_establishment_type));
            return;
        }
        if (CommonUtils.isNullString(establishmentPost)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_ESTABLISHMENT_POST, baseActivity.getResources().getString(R.string.err_establishment_post));
            return;
        }
        if (CommonUtils.isNullString(establishmentYear)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_ESTABLISHMENT_YEAR, baseActivity.getResources().getString(R.string.err_establishment_year));
            return;
        }
        if (CommonUtils.isNullString(mobile)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_MOBILE, baseActivity.getResources().getString(R.string.err_mobile));
            return;
        }
        if (CommonUtils.isNullString(landphone)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_LAND_PHONE, baseActivity.getResources().getString(R.string.err_landphone));
            return;
        }
        if (CommonUtils.isNullString(email)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_EMAIL, baseActivity.getResources().getString(R.string.err_email));
            return;
        }
        if (CommonUtils.isNullString(inchargeName)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_INCHARGE_NAME, baseActivity.getResources().getString(R.string.err_incharge_name));
            return;
        }
        if (CommonUtils.isNullString(inchargeRole)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_INCHARGE_ROLE, baseActivity.getResources().getString(R.string.err_incharge_role));
            return;
        }
        if (CommonUtils.isNullString(inchargePost)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_INCHARGE_POST, baseActivity.getResources().getString(R.string.err_incharge_post));
            return;
        }
        if (CommonUtils.isNullString(professionalTaxPaymentStatus)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_TAX_PAYMENT_STATUS, baseActivity.getResources().getString(R.string.err_tax_payment_status));
            return;
        }
        if (CommonUtils.isNullString(numberOfEmployees)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_NUMBER_OF_EMPLOYEE, baseActivity.getResources().getString(R.string.err_number_of_employees));
            return;
        }
        if (CommonUtils.isNullString(license)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_LICENSE, baseActivity.getResources().getString(R.string.err_license));
            return;
        }
        if (CommonUtils.isNullString(licenseNumber)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_LICENSE_NUMBER, baseActivity.getResources().getString(R.string.err_license_number));
            return;
        }
        if (CommonUtils.isNullString(gst)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_GST_STATUS, baseActivity.getResources().getString(R.string.err_gst));
            return;
        }
        if (CommonUtils.isNullString(gstNumber)) {
            getMvpView().showEstablishmentDetailsFieldError(EstablishmentDetailsFragment.ERROR_TYPE_GST_NUMBER, baseActivity.getResources().getString(R.string.err_gst_number));
            return;
        }
        savingEstablishmentDetails(establishmentName, establishmentPost, establishmentType, establishmentYear, mobile, landphone, email, inchargeName, inchargeRole, inchargePost, professionalTaxPaymentStatus, numberOfEmployees, license, licenseNumber, gst, gstNumber);
    }

    public void savingEstablishmentDetails(String establishmentName, String establishmentPost, String establishmentType, String establishmentYear, String mobile, String landphone, String email, String inchargeName,
                                           String inchargeRole, String inchargePost, String professionalTaxPaymentStatus, String numberOfEmployees, String license, String licenseNumber, String gst, String gstNumber) {
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                BuildingAssets.EstablishmentDetails establishmentDetails = AppCacheData.getOurInstance().getBuildingAssetData().getEstablishmentDetails();
                data.setEstablishmentDetails(establishmentDetails == null ? new BuildingAssets.EstablishmentDetails() : establishmentDetails);
                data.getEstablishmentDetails().setEstbName(establishmentName);
                data.getEstablishmentDetails().setEstbPost(establishmentPost);
                data.getEstablishmentDetails().setEstbType(establishmentType);
                data.getEstablishmentDetails().setEstbYear(Integer.parseInt(establishmentYear));
                data.getEstablishmentDetails().setMobile(mobile);
                data.getEstablishmentDetails().setLandphone(landphone);
                data.getEstablishmentDetails().setEmail(email);
                data.getEstablishmentDetails().setInchrg_name(inchargeName);
                data.getEstablishmentDetails().setInchrgRole(inchargeRole);
                data.getEstablishmentDetails().setInchrgPost(inchargePost);
                data.getEstablishmentDetails().setProfTaxPayStatus(professionalTaxPaymentStatus);
                data.getEstablishmentDetails().setNo_of_employees(Integer.parseInt(numberOfEmployees));
                data.getEstablishmentDetails().setLicence(license);
                data.getEstablishmentDetails().setLicenceNo(licenseNumber);
                data.getEstablishmentDetails().setGstNo(gstNumber);
                data.getEstablishmentDetails().setGstStatus(gst);
                data.getEstablishmentDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                BuildingAssets.EstablishmentDetails establishmentDetails = data.getEstablishmentDetails();
                if (establishmentDetails == null) {
                    establishmentDetails = new BuildingAssets.EstablishmentDetails();
                    if (data.getPropertyDetails() != null) {
                        establishmentDetails.setProperty(data.getPropertyDetails().getPk());
                    }
                    establishmentDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                } else {
                    establishmentDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                establishmentDetails.setEstbName(establishmentName);
                establishmentDetails.setEstbPost(establishmentPost);
                establishmentDetails.setEstbType(establishmentType);
                establishmentDetails.setEstbYear(Integer.parseInt(establishmentYear));
                establishmentDetails.setMobile(mobile);
                establishmentDetails.setLandphone(landphone);
                establishmentDetails.setEmail(email);
                establishmentDetails.setInchrg_name(inchargeName);
                establishmentDetails.setInchrgRole(inchargeRole);
                establishmentDetails.setInchrgPost(inchargePost);
                establishmentDetails.setProfTaxPayStatus(professionalTaxPaymentStatus);
                establishmentDetails.setNo_of_employees(Integer.parseInt(numberOfEmployees));
                establishmentDetails.setLicence(license);
                establishmentDetails.setLicenceNo(licenseNumber);
                establishmentDetails.setGstNo(gstNumber);
                establishmentDetails.setGstStatus(gst);
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setEstablishmentDetails(establishmentDetails);
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
            AppCacheData.getOurInstance().getBuildingAssetData().setEstablishmentDetails(response.getData().getEstablishmentDetails());
        }
        getMvpView().onAddOrUpdateSuccess();
    }
}