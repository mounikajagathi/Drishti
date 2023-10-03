package in.ults.ipms.ui.utility.taxistand;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.data.network.model.response.UtilityAssets;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TaxiStandDetailsPresenter<V extends ITaxiStandDetailsView, I extends ITaxiStandDetailsInteractor>
        extends BasePresenter<V, I> implements ITaxiStandDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public TaxiStandDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, boolean authorised, String capacity, String authDetails, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_taxi_stand_details_location));
            return;
        }
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_PARKING_TYPE, baseActivity.getResources().getString(R.string.err_taxi_stand_details_parking_type));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_taxi_stand_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_taxi_stand_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(capacity)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_CAPACITY, baseActivity.getResources().getString(R.string.err_taxi_stand_details_capacity));
            return;
        }
        if (CommonUtils.isNullString(authDetails)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_AUTHORISATION_DETAILS, baseActivity.getResources().getString(R.string.err_taxi_stand_details_auth_details));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_taxi_stand_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(TaxiStandDetailsFragment.ERROR_TYPE_TAXI_LOCATION, baseActivity.getResources().getString(R.string.err_taxi_stand_details_taxi_location));
            return;
        }

        addUpdateContent(location, authorised, capacity, authDetails, type, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, boolean authorised, String capacity, String authDetails, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getTaxiStandDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getTaxiStandDetails().setLocation(location);
                data.getTaxiStandDetails().setAuthorised(authorised);
                data.getTaxiStandDetails().setParkingType(Long.parseLong(type));
                data.getTaxiStandDetails().setWard(Long.parseLong(wardNo));
                data.getTaxiStandDetails().setRemarks(remarks);
                data.getTaxiStandDetails().setCapacity(Integer.parseInt(capacity));
                data.getTaxiStandDetails().setAuthorisationDetails(authDetails);
                data.getTaxiStandDetails().setPhoto1(photo);
                data.getTaxiStandDetails().setGeom(geom);
                data.getTaxiStandDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.TaxiStand details = new UtilityAssets.TaxiStand();
            details.setLocation(location);
            details.setAuthorised(authorised);
            details.setParkingType(Long.parseLong(type));
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setCapacity(Integer.parseInt(capacity));
            details.setAuthorisationDetails(authDetails);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setTaxiStandDetails(details);
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
