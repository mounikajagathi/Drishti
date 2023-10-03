package in.ults.ipms.ui.otherassets.mastlight;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.data.network.model.response.OtherAssets;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class HighLowMastLightDetailsPresenter<V extends IHighLowMastLightDetailsView, I extends IHighLowMastLightDetailsInteractor>
        extends BasePresenter<V, I> implements IHighLowMastLightDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public HighLowMastLightDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String type, String lightType, String fundedBy, String workingStatus, String locationDetails, String address, String power, String height, String bulbNumber, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_TYPE, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_type));
            return;
        }

        if (CommonUtils.isNullString(lightType)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_LIGHT_TYPE, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_light_type));
            return;
        }

        if (CommonUtils.isNullString(fundedBy)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_FUNDED_BY, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_funded_by));
            return;
        }

        if (CommonUtils.isNullString(workingStatus)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_WORKING_STATUS, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_working_status));
            return;
        }

        if (CommonUtils.isNullString(locationDetails)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_LOCATION_DETAILS, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_location));
            return;
        }

        if (CommonUtils.isNullString(address)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_ADDRESS, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_address));
            return;
        }

        if (CommonUtils.isNullString(power)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_POWER, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_power));
            return;
        }

        if (CommonUtils.isNullString(height)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_HIGH_MAST_LIGHT_HEIGHT, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_height));
            return;
        }

        if (CommonUtils.isNullString(bulbNumber)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_NO_OF_BULBS, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_no_of_bulbs));
            return;
        }

        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(HighLowMastLightDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_high_and_low_mast_details_high_or_low_mast_location));
            return;
        }

        addUpdateContent(type, lightType, fundedBy, workingStatus, locationDetails, address, power, height, bulbNumber, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String type, String lightType, String fundedBy, String workingStatus, String locationDetails, String address, String power, String height, String bulbNumber, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getPondDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getHighLowMastLight().setType(type);
                data.getHighLowMastLight().setLightType(lightType);
                data.getHighLowMastLight().setFundedBy(fundedBy);
                data.getHighLowMastLight().setWorkingStatus(workingStatus);
                data.getHighLowMastLight().setLocationDetails(locationDetails);
                data.getHighLowMastLight().setAddress(address);
                data.getHighLowMastLight().setPower(power);
                data.getHighLowMastLight().setHeight(height);
                data.getHighLowMastLight().setBulbNumber(bulbNumber);
                data.getHighLowMastLight().setWard(Long.parseLong(wardNo));
                data.getHighLowMastLight().setRemarks(remarks);
                data.getHighLowMastLight().setPhoto1(photo);
                data.getHighLowMastLight().setGeom(geom);
                data.getHighLowMastLight().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            OtherAssets.HighLowMastLight details = new OtherAssets.HighLowMastLight();
            data.getHighLowMastLight().setType(type);
            data.getHighLowMastLight().setLightType(lightType);
            data.getHighLowMastLight().setFundedBy(fundedBy);
            data.getHighLowMastLight().setWorkingStatus(workingStatus);
            data.getHighLowMastLight().setLocationDetails(locationDetails);
            data.getHighLowMastLight().setAddress(address);
            data.getHighLowMastLight().setPower(power);
            data.getHighLowMastLight().setHeight(height);
            data.getHighLowMastLight().setBulbNumber(bulbNumber);
            data.getHighLowMastLight().setWard(Long.parseLong(wardNo));
            data.getHighLowMastLight().setRemarks(remarks);
            data.getHighLowMastLight().setPhoto1(photo);
            data.getHighLowMastLight().setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setHighLowMastLight(details);
            saveAssetDetails(data);
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        getMvpView().onAddOrUpdateSuccess(AppCacheData.getOurInstance().isAssetUpdate());
    }


}
