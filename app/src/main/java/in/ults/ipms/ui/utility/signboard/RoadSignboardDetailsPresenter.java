package in.ults.ipms.ui.utility.signboard;

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

public class RoadSignboardDetailsPresenter<V extends IRoadSignboardDetailsView, I extends IRoadSignboardDetailsInteractor>
        extends BasePresenter<V, I> implements IRoadSignboardDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public RoadSignboardDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String category, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(RoadSignboardDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_road_signboard_details_place));
            return;
        }
        if (CommonUtils.isNullString(category)) {
            getMvpView().showErrors(RoadSignboardDetailsFragment.ERROR_TYPE_CATEGORY, baseActivity.getResources().getString(R.string.err_road_signboard_details_category));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(RoadSignboardDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_road_signboard_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(RoadSignboardDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_road_signboard_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(RoadSignboardDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_road_signboard_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(RoadSignboardDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_road_signboard_details_location));
            return;
        }

        addUpdateContent(place, category, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String category, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getRoadSignboardDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getRoadSignboardDetails().setCategory(category);
                data.getRoadSignboardDetails().setPlace(place);
                data.getRoadSignboardDetails().setWard(Long.parseLong(wardNo));
                data.getRoadSignboardDetails().setRemarks(remarks);
                data.getRoadSignboardDetails().setPhoto1(photo);
                data.getRoadSignboardDetails().setGeom(geom);
                data.getRoadSignboardDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.RoadSignboard details = new UtilityAssets.RoadSignboard();
            details.setCategory(category);
            details.setPlace(place);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setRoadSignboardDetails(details);
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
