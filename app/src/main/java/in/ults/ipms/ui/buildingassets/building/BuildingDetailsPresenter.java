package in.ults.ipms.ui.buildingassets.building;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by AmalB on 6/22/2021.
 */

public class BuildingDetailsPresenter<V extends IBuildingDetailsView, I extends IBuildingDetailsInteractor>
        extends BasePresenter<V, I> implements IBuildingDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public BuildingDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String buildingName, String permitNumber, String buildingUnder, String buildingRefId, String wardNumber, boolean isLandmark, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(buildingName)) {
            getMvpView().showBuildingDetailsFieldError(BuildingDetailsFragment.ERROR_TYPE_BUILDING_NAME, baseActivity.getResources().getString(R.string.err_building_name));
            return;
        }
        if (CommonUtils.isNullString(permitNumber)) {
            getMvpView().showBuildingDetailsFieldError(BuildingDetailsFragment.ERROR_TYPE_PERMIT_NUMBER, baseActivity.getResources().getString(R.string.err_permit_no));
            return;
        }
        if (CommonUtils.isNullString(buildingUnder)) {
            getMvpView().showBuildingDetailsFieldError(BuildingDetailsFragment.ERROR_TYPE_BUILDING_UNDER, baseActivity.getResources().getString(R.string.err_building_under));
            return;
        }
        if (CommonUtils.isNullString(buildingRefId)) {
            getMvpView().showBuildingDetailsFieldError(BuildingDetailsFragment.ERROR_TYPE_BUILDING_REFID, baseActivity.getResources().getString(R.string.err_building_ref_id));
            return;
        }
        if (CommonUtils.isNullString(wardNumber)) {
            getMvpView().showBuildingDetailsFieldError(BuildingDetailsFragment.ERROR_TYPE_WARD_NUMBER, baseActivity.getResources().getString(R.string.err_ward_number));
            return;
        }
        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showBuildingDetailsFieldError(BuildingDetailsFragment.ERROR_TYPE_BUILDING_LOCATION, baseActivity.getResources().getString(R.string.err_building_location));
            return;
        }
        savingBuildingDetails(buildingName, permitNumber, buildingUnder, buildingRefId, wardNumber, isLandmark, locationLatitude, locationLongitude);
    }

    private void savingBuildingDetails(String buildingName, String permitNumber, String buildingUnder, String buildingRefId, String wardNumber, boolean isLandmark, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingDetailsData() != null &&
                    AppCacheData.getOurInstance().getBuildingDetailsData().getBuildingDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingDetailsData();
                data.getBuildingDetails().setBuildingName(buildingName);
                data.getBuildingDetails().setPermitNo(permitNumber);
                data.getBuildingDetails().setBldgUnder(Integer.parseInt(buildingUnder));
                data.getBuildingDetails().setBldgRefId(buildingRefId);
                data.getBuildingDetails().setWard(wardNumber);
                data.getBuildingDetails().setLandmark(isLandmark);
                data.getBuildingDetails().setGeom(geom);
                data.getBuildingDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            BuildingAssets.BuildingDetails details = new BuildingAssets.BuildingDetails();
            details.setBuildingName(buildingName);
            details.setPermitNo(permitNumber);
            details.setBldgUnder(Integer.parseInt(buildingUnder));
            details.setBldgRefId(buildingRefId);
            details.setWard(wardNumber);
            details.setLandmark(isLandmark);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setBuildingDetails(details);
            saveAssetDetails(data);
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        getMvpView().onAddOrUpdateSuccess();
    }
}
