package in.ults.ipms.ui.buildingassets.road;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;


public class RoadDetailsPresenter <V extends IRoadDetailsView, I extends IRoadDetailsInteractor>
        extends BasePresenter<V, I> implements IRoadDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public RoadDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String nearRoad, String roadType, String roadWidth) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(nearRoad)) {
            getMvpView().showRoadDetailsFieldError(RoadDetailsFragment.ERROR_TYPE_NEAR_ROAD, baseActivity.getResources().getString(R.string.err_near_road));
            return;
        }
        if (CommonUtils.isNullString(roadType)) {
            getMvpView().showRoadDetailsFieldError(RoadDetailsFragment.ERROR_TYPE_ROAD_TYPE, baseActivity.getResources().getString(R.string.err_road_type));
            return;
        }
        if (CommonUtils.isNullString(roadWidth)) {
            getMvpView().showRoadDetailsFieldError(RoadDetailsFragment.ERROR_TYPE_ROAD_WIDTH, baseActivity.getResources().getString(R.string.err_road_width));
            return;
        }
        saveRoadDetails(nearRoad,roadType, roadWidth);
    }

    void saveRoadDetails(String nearRoad, String roadType, String roadWidth){
        if (AppCacheData.getOurInstance().isAssetUpdate() || AppCacheData.getOurInstance().isBuildingAssetPropertyAdded()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                data.setPropertyDetails(propertyDetails == null ? new BuildingAssets.PropertyDetails() : propertyDetails);
                data.getPropertyDetails().setNearRoad(nearRoad);
                data.getPropertyDetails().setRoadType(Integer.parseInt(roadType));
                data.getPropertyDetails().setRoadWidth(Integer.parseInt(roadWidth));
                data.getPropertyDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getNewProperty();
            if(propertyDetails!=null) {
                propertyDetails.setNearRoad(nearRoad);
                propertyDetails.setRoadType(Integer.parseInt(roadType));
                propertyDetails.setRoadWidth(Integer.parseInt(roadWidth));
                propertyDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                AppCacheData.getOurInstance().setNewProperty(propertyDetails);
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                data.setPropertyDetails(propertyDetails);
                saveAssetDetails(data);
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        AppCacheData.getOurInstance().getBuildingAssetData().setPropertyDetails(response.getData().getPropertyDetails());
        getMvpView().onAddOrUpdateSuccess();
    }
}
