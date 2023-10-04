package in.ults.ipms.ui.utility.junction;

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

public class RoadJunctionDetailsPresenter<V extends IRoadJunctionDetailsView, I extends IRoadJunctionDetailsInteractor>
        extends BasePresenter<V, I> implements IRoadJunctionDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public RoadJunctionDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String name,String location,String noOfRoad,String pedestrian, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_road_junction_details_name));
            return;
        }

        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_LOCATION_DETAILS, baseActivity.getResources().getString(R.string.err_road_junction_details_location_details));
            return;
        }

        if (CommonUtils.isNullString(noOfRoad)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_NO_OF_ROADS, baseActivity.getResources().getString(R.string.err_road_junction_details_no_of_roads));
            return;
        }

        if (CommonUtils.isNullString(pedestrian)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_PEDESTRIAN, baseActivity.getResources().getString(R.string.err_road_junction_details_pedestrian));
            return;
        }

        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_road_junction_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_road_junction_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_road_junction_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(RoadJunctionDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_road_junction_details_location));
            return;
        }

        addUpdateContent(name,location,noOfRoad,pedestrian, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String name,String location,String noOfRoad,String pedestrian, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getRoadJunctionDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getRoadJunctionDetails().setJunctionName(name);
                data.getRoadJunctionDetails().setLocation(location);
                data.getRoadJunctionDetails().setNoOfRoads(noOfRoad);
                data.getRoadJunctionDetails().setPedestrian(pedestrian);
                data.getRoadJunctionDetails().setWard(Long.parseLong(wardNo));
                data.getRoadJunctionDetails().setRemarks(remarks);
                data.getRoadJunctionDetails().setPhoto1(photo);
                data.getRoadJunctionDetails().setGeom(geom);
                data.getRoadJunctionDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.RoadJunction details = new UtilityAssets.RoadJunction();
            details.setJunctionName(name);
            details.setLocation(location);
            details.setNoOfRoads(noOfRoad);
            details.setPedestrian(pedestrian);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setRoadJunctionDetails(details);
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
