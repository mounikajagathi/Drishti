package in.ults.ipms.ui.buildingassets.floorandroof;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.FloorAreaAdapter;
import in.ults.ipms.adapters.RoofTypeAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by AmalB on 6/22/2021.
 */

public class FloorAndRoofDetailsPresenter<V extends IFloorAndRoofDetailsView, I extends IFloorAndRoofDetailsInteractor> extends BasePresenter<V, I> implements IFloorAndRoofDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public FloorAndRoofDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(ArrayList<BuildingAssets.FloorType> floorType, FloorAreaAdapter floorAreaAdapter, RoofTypeAdapter roofTypeAdapter) {
        getMvpView().clearErrors();
        if (floorType.size() == 0) {
            getMvpView().showFieldError(FloorAndRoofDetailsFragment.ERROR_TYPE_FLOOR_TYPE, baseActivity.getResources().getString(R.string.err_floor_type));
            return;
        }
        if (!floorAreaAdapter.validateData()) {
            return;
        }
        if (!roofTypeAdapter.validateData()) {
            return;
        }


        saveFloorAndRoof(floorType, floorAreaAdapter.getLocalDataSet(), roofTypeAdapter.getLocalDataSet());
    }


    private void saveFloorAndRoof(ArrayList<BuildingAssets.FloorType> floorType, ArrayList<BuildingAssets.FloorArea> floorArea, ArrayList<BuildingAssets.RoofType> roofType) {
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                long propertyID = -1;
                if (propertyDetails != null) {
                    propertyID = propertyDetails.getPk();
                }



                for (int i = 0; i < floorType.size(); i++) {
                    floorType.get(i).setProperty(propertyID);
                    floorType.get(i).setParentFeature(AppConstants.PARENT_TYPE_PROPERTY);
                    if (floorType.get(i).getPk() == -1) {
                        floorType.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    } else {
                        floorType.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                    }
                }

                for (int i = 0; i < floorArea.size(); i++) {
                    floorArea.get(i).setProperty(propertyID);
                    floorArea.get(i).setParentFeature(AppConstants.PARENT_TYPE_PROPERTY);
                    if (floorArea.get(i).getPk() == -1) {
                        floorArea.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    } else {
                        floorArea.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                    }
                }

                for (int i = 0; i < roofType.size(); i++) {
                    roofType.get(i).setProperty(propertyID);
                    roofType.get(i).setParentFeature(AppConstants.PARENT_TYPE_PROPERTY);
                    if (roofType.get(i).getPk() == -1) {
                        roofType.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    } else {
                        roofType.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                    }
                }

                data.setFloorTypes(floorType);
                data.setFloorArea(floorArea);
                data.setRoofTypes(roofType);
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();

                long propertyID = -1;
                if (propertyDetails != null) {
                    propertyID = propertyDetails.getPk();
                }
                for (int i = 0; i < floorType.size(); i++) {
                    floorType.get(i).setProperty(propertyID);
                    floorType.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                }

                for (int i = 0; i < floorArea.size(); i++) {
                    floorArea.get(i).setProperty(propertyID);
                    floorArea.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                }

                for (int i = 0; i < roofType.size(); i++) {
                    roofType.get(i).setProperty(propertyID);
                    roofType.get(i).setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                }
                data.setFloorTypes(floorType);
                data.setFloorArea(floorArea);
                data.setRoofTypes(roofType);
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
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null && response.getData() != null) {
            AppCacheData.getOurInstance().getBuildingAssetData().setFloorTypes(response.getData().getFloorTypes());
            AppCacheData.getOurInstance().getBuildingAssetData().setFloorArea(response.getData().getFloorArea());
            AppCacheData.getOurInstance().getBuildingAssetData().setRoofTypes(response.getData().getRoofTypes());
        }
        getMvpView().onAddOrUpdateSuccess();
    }

    @Override
    public void onDeleteAssetSuccess(int type, int position) {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
            if (!isViewAttached()) {
                return;
            }
            getMvpView().removeDataFromList(type, position);
        }
    }
}
