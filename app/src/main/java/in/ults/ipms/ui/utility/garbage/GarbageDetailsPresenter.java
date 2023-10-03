package in.ults.ipms.ui.utility.garbage;

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

public class GarbageDetailsPresenter<V extends IGarbageDetailsView, I extends IGarbageDetailsInteractor>
        extends BasePresenter<V, I> implements IGarbageDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public GarbageDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String category, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(GarbageDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_garbage_details_place));
            return;
        }
        if (CommonUtils.isNullString(category)) {
            getMvpView().showErrors(GarbageDetailsFragment.ERROR_TYPE_CATEGORY, baseActivity.getResources().getString(R.string.garbage_details_category));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(GarbageDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_garbage_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(GarbageDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_garbage_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(GarbageDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_garbage_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(GarbageDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_garbage_details_location));
            return;
        }

        addUpdateContent(place, category, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String category, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getGarbageDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getGarbageDetails().setPlace(place);
                data.getGarbageDetails().setCategory(category);
                data.getGarbageDetails().setWard(Long.parseLong(wardNo));
                data.getGarbageDetails().setRemarks(remarks);
                data.getGarbageDetails().setPhoto1(photo);
                data.getGarbageDetails().setGeom(geom);
                data.getGarbageDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Garbage details = new UtilityAssets.Garbage();
            details.setPlace(place);
            details.setCategory(category);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setGarbageDetails(details);
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
