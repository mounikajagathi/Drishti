package in.ults.ipms.ui.utility.busbay;

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

public class BusBayDetailsPresenter<V extends IBusBayDetailsView, I extends IBusBayDetailsInteractor>
        extends BasePresenter<V, I> implements IBusBayDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public BusBayDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(BusBayDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_bus_bay_place));
            return;
        }

        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(BusBayDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_bus_bay_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(BusBayDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_bus_bay_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(BusBayDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_bus_bay_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(BusBayDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_bus_bay_location));
            return;
        }

        addUpdateContent(place, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getBusBayDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getBusBayDetails().setBusBayPlace(place);
                data.getBusBayDetails().setWard(Long.parseLong(wardNo));
                data.getBusBayDetails().setRemarks(remarks);
                data.getBusBayDetails().setPhoto1(photo);
                data.getBusBayDetails().setGeom(geom);
                data.getBusBayDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.BusBay details = new UtilityAssets.BusBay();
            details.setBusBayPlace(place);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setBusBayDetails(details);
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
