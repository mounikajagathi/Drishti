package in.ults.ipms.ui.utility.hump;

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

public class RoadHumpDetailsPresenter<V extends IRoadHumpDetailsView, I extends IRoadHumpDetailsInteractor>
        extends BasePresenter<V, I> implements IRoadHumpDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public RoadHumpDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String name, String length, String width,String status, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
      getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_road_hump_details_place));
            return;
        }
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_road_hump_details_name));
            return;
        }
        if (CommonUtils.isNullString(length)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_LENGTH, baseActivity.getResources().getString(R.string.err_road_hump_details_length));
            return;
        }
        if (CommonUtils.isNullString(width)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_WIDTH, baseActivity.getResources().getString(R.string.err_road_hump_details_width));
            return;
        }
        if (CommonUtils.isNullString(status)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_STATUS, baseActivity.getResources().getString(R.string.err_road_hump_details_status));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_road_hump_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_road_hump_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_road_hump_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(RoadHumpDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_road_hump_details_location));
            return;
        }

        addUpdateContent(place, name, length, width,status, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String name, String length, String width,String status,  String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getRoadHumpDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getRoadHumpDetails().setRoadName(name);
                data.getRoadHumpDetails().setPlace(place);
                data.getRoadHumpDetails().setLength(Double.parseDouble(length));
                data.getRoadHumpDetails().setWidth(Double.parseDouble(width));
                data.getRoadHumpDetails().setStatus(status);
                data.getRoadHumpDetails().setWard(Long.parseLong(wardNo));
                data.getRoadHumpDetails().setRemarks(remarks);
                data.getRoadHumpDetails().setPhoto1(photo);
                data.getRoadHumpDetails().setGeom(geom);
                data.getRoadHumpDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.RoadHump details = new UtilityAssets.RoadHump();
            details.setRoadName(name);
            details.setPlace(place);
            details.setLength(Double.parseDouble(length));
            details.setWidth(Double.parseDouble(width));
            details.setStatus(status);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setRoadHumpDetails(details);
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
