package in.ults.ipms.ui.utility.parking;

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

public class ParkingDetailsPresenter<V extends IParkingDetailsView, I extends IParkingDetailsInteractor>
        extends BasePresenter<V, I> implements IParkingDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public ParkingDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String capacity, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_parking_details_place));
            return;
        }
        if (CommonUtils.isNullString(capacity)) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_CAPACITY, baseActivity.getResources().getString(R.string.parking_details_capacity));
            return;
        }
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_PARKING_TYPE, baseActivity.getResources().getString(R.string.err_parking_details_type));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_parking_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_parking_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_parking_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(ParkingDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_parking_details_location));
            return;
        }

        addUpdateContent(place, capacity, type, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String capacity, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getParkingAreaDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getParkingAreaDetails().setPlace(place);
                data.getParkingAreaDetails().setCapacity(Long.parseLong(capacity));
                data.getParkingAreaDetails().setParkingType(type);
                data.getParkingAreaDetails().setWard(Long.parseLong(wardNo));
                data.getParkingAreaDetails().setRemarks(remarks);
                data.getParkingAreaDetails().setPhoto1(photo);
                data.getParkingAreaDetails().setGeom(geom);
                data.getParkingAreaDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.ParkingArea details = new UtilityAssets.ParkingArea();
            details.setPlace(place);
            details.setCapacity(Long.parseLong(capacity));
            details.setParkingType(type);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setParkingAreaDetails(details);
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
