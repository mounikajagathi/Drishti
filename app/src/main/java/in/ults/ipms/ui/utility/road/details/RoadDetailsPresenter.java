package in.ults.ipms.ui.utility.road.details;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.data.network.model.response.UtilityAssets;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class RoadDetailsPresenter<V extends IRoadDetailsView, I extends IRoadDetailsInteractor>
        extends BasePresenter<V, I> implements IRoadDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public RoadDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void saveRoadDetails(String roadName, String startPoint, String endPoint, String roadMaterial, String roadCategory, String maintainedBy, String lengthMtr, String carriageWidthMtr, String roadwayWidthMtr, String footpathWidthMtr, String rightWayWidthMtr, String remarks, String photo, GeomPolyLine geom) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(roadName)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_ROAD_NAME, baseActivity.getResources().getString(R.string.err_road_utility_road_name));
            return;
        }
        if (CommonUtils.isNullString(startPoint)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_START_POINT, baseActivity.getResources().getString(R.string.err_road_utility_start_point));
            return;
        }
        if (CommonUtils.isNullString(endPoint)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_END_POINT, baseActivity.getResources().getString(R.string.err_road_utility_end_point));
            return;
        }
        if (CommonUtils.isNullString(roadMaterial)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_ROAD_MATERIAL, baseActivity.getResources().getString(R.string.err_road_utility_road_material));
            return;
        }
        if (CommonUtils.isNullString(roadCategory)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_ROAD_CATEGORY, baseActivity.getResources().getString(R.string.err_road_utility_road_category));
            return;
        }
        if (CommonUtils.isNullString(maintainedBy)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_MAINTAINED_BY, baseActivity.getResources().getString(R.string.err_road_utility_maintained_by));
            return;
        }
        if (CommonUtils.isNullString(lengthMtr)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_LENGTH_MTR, baseActivity.getResources().getString(R.string.err_road_utility_length_mtr));
            return;
        }
        if (CommonUtils.isNullString(carriageWidthMtr)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_CARRIAGE_WIDTH_MTR, baseActivity.getResources().getString(R.string.err_road_utility_carriage_width_mtr));
            return;
        }
        if (CommonUtils.isNullString(roadwayWidthMtr)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_ROADWAY_WIDTH_MTR, baseActivity.getResources().getString(R.string.err_road_utility_roadway_width_mtr));
            return;
        }
        if (CommonUtils.isNullString(footpathWidthMtr)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_FOOTPATH_WIDTH_MTR, baseActivity.getResources().getString(R.string.err_road_utility_footpath_width_mtr));
            return;
        }
        if (CommonUtils.isNullString(rightWayWidthMtr)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_RIGHTWAY_WIDTH_MTR, baseActivity.getResources().getString(R.string.err_road_utility_right_of_way_width_mtr));
            return;//
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_road_utility_remarks));
            return;//
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showRoadAddFieldError(RoadDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_road_utility_photo));
            return;//
        }
        addUpdateContent(roadName, startPoint, endPoint, roadMaterial, roadCategory, maintainedBy, lengthMtr, carriageWidthMtr, roadwayWidthMtr, footpathWidthMtr, rightWayWidthMtr, remarks, photo, geom);
    }

    void addUpdateContent(String roadName, String startPoint, String endPoint, String roadMaterial, String roadCategory, String maintainedBy,
                          String lengthMtr, String carriageWidthMtr, String roadwayWidthMtr, String footpathWidthMtr, String rightWayWidthMtr, String remarks, String photo, GeomPolyLine geom) {

        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getRoadUtility() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();//add update
                data.getRoadUtility().setRoadName(roadName);
                data.getRoadUtility().setStartPoint(startPoint);
                data.getRoadUtility().setEndPoint(endPoint);
                data.getRoadUtility().setRoadMaterial(roadMaterial);
                data.getRoadUtility().setRoadCategory(roadCategory);
                data.getRoadUtility().setLength(lengthMtr);
                data.getRoadUtility().setCarriageWidth(carriageWidthMtr);
                data.getRoadUtility().setRightOfWayWidth(rightWayWidthMtr);
                data.getRoadUtility().setRoadwayWidth(roadwayWidthMtr);
                data.getRoadUtility().setFootpathWidth(footpathWidthMtr);
                data.getRoadUtility().setMaintainedBy(maintainedBy);
                data.getRoadUtility().setRemarks(remarks);
                data.getRoadUtility().setPhoto(photo);
                data.getRoadUtility().setGeom(geom);
                data.getRoadUtility().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.RoadUtility details = new UtilityAssets.RoadUtility();
            details.setRoadName(roadName);
            details.setStartPoint(startPoint);
            details.setEndPoint(endPoint);
            details.setRoadMaterial(roadMaterial);
            details.setRoadCategory(roadCategory);
            details.setLength(lengthMtr);
            details.setCarriageWidth(carriageWidthMtr);
            details.setRightOfWayWidth(rightWayWidthMtr);
            details.setRoadwayWidth(roadwayWidthMtr);
            details.setFootpathWidth(footpathWidthMtr);
            details.setMaintainedBy(maintainedBy);
            details.setRemarks(remarks);
            details.setPhoto(photo);//
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setRoadUtility(details);
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
