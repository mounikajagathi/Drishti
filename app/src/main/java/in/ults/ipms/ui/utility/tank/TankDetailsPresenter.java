package in.ults.ipms.ui.utility.tank;

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

public class TankDetailsPresenter<V extends ITankDetailsView, I extends ITankDetailsInteractor>
        extends BasePresenter<V, I> implements ITankDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public TankDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, String tankOwner, String capacity, String tankType, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_tank_details_location));
            return;
        }
        if (CommonUtils.isNullString(tankOwner)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_TANK_OWNER, baseActivity.getResources().getString(R.string.err_tank_details_tank_owner));
            return;
        }
        if (CommonUtils.isNullString(capacity)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_CAPACITY, baseActivity.getResources().getString(R.string.err_tank_details_capacity));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_tank_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(tankType)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_TANK_TYPE, baseActivity.getResources().getString(R.string.err_tank_details_tank_type));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_WARD, baseActivity.getResources().getString(R.string.err_tank_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_tank_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(TankDetailsFragment.ERROR_TYPE_TANK_LOCATION, baseActivity.getResources().getString(R.string.err_tank_details_tank_location));
            return;
        }

        addUpdateContent(location, tankOwner, capacity, tankType, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, String tankOwner, String capacity, String tankType, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getTankDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getTankDetails().setLocation(location);
                data.getTankDetails().setTankOwner(tankOwner);
                data.getTankDetails().setTankType(Long.parseLong(tankType));
                data.getTankDetails().setWard(Long.parseLong(wardNo));
                data.getTankDetails().setRemarks(remarks);
                data.getTankDetails().setCapacity(Integer.parseInt(capacity));
                data.getTankDetails().setPhoto1(photo);
                data.getTankDetails().setGeom(geom);
                data.getTankDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Tank details = new UtilityAssets.Tank();
            details.setLocation(location);
            details.setTankOwner(tankOwner);
            details.setTankType(Long.parseLong(tankType));
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setCapacity(Integer.parseInt(capacity));
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setTankDetails(details);
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
