package in.ults.ipms.ui.utility.mobiletower;

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

public class MobileTowerDetailsPresenter<V extends IMobileTowerDetailsView, I extends IMobileTowerDetailsInteractor>
        extends BasePresenter<V, I> implements IMobileTowerDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public MobileTowerDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String buildingName, String place, String ownerName, String ownerAddress, String ownerMobile,
                             String newPropertyId, String oldPropertyId, String buildingStatus, String buildingUsage,
                             String yearOfConstruction, boolean electricConnectivity, String uniqueId,
                             String serviceProvider, String consumerNo, String roadWidth, String roadType,
                             String wardNo, String remarks, String photo, String photo2, double locationLatitude,
                             double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(buildingName)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_BUILDING_NAME, baseActivity.getResources().getString(R.string.err_mobile_tower_details_building));
            return;
        }
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_mobile_tower_details_place));
            return;
        }
        if (CommonUtils.isNullString(ownerName)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_OWNER_NAME, baseActivity.getResources().getString(R.string.err_mobile_tower_details_owner_name));
            return;
        }
        if (CommonUtils.isNullString(ownerAddress)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_OWNER_ADDRESS, baseActivity.getResources().getString(R.string.err_mobile_tower_details_owner_address));
            return;
        }
        if (CommonUtils.isNullString(ownerMobile)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_OWNER_MOBILE, baseActivity.getResources().getString(R.string.err_mobile_tower_details_owner_mobile));
            return;
        }
        if (CommonUtils.isNullString(buildingStatus)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_BUILDING_STATUS, baseActivity.getResources().getString(R.string.err_mobile_tower_details_building_status));
            return;
        }
        if (CommonUtils.isNullString(buildingUsage)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_BUILDING_USAGE, baseActivity.getResources().getString(R.string.err_mobile_tower_details_building_usage));
            return;
        }
        if (CommonUtils.isNullString(newPropertyId)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_NEW_PROPERTY_ID, baseActivity.getResources().getString(R.string.err_mobile_tower_details_new_property_id));
            return;
        }
        if (CommonUtils.isNullString(oldPropertyId)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_OLD_PROPERTY_ID, baseActivity.getResources().getString(R.string.err_mobile_tower_details_old_property_id));
            return;
        }

        if (CommonUtils.isNullString(yearOfConstruction)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_YEAR_OF_CONSTRUCTION, baseActivity.getResources().getString(R.string.err_mobile_tower_details_year_of_construction));
            return;
        }
        if (CommonUtils.isNullString(serviceProvider)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_SERVICE_PROVIDER, baseActivity.getResources().getString(R.string.err_mobile_tower_details_service_provider));
            return;
        }
        if (CommonUtils.isNullString(consumerNo)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_CONSUMER_NO, baseActivity.getResources().getString(R.string.err_mobile_tower_details_consumer_no));
            return;
        }
        if (CommonUtils.isNullString(roadWidth)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_ROAD_WIDTH, baseActivity.getResources().getString(R.string.err_mobile_tower_details_road_width));
            return;
        }
        if (CommonUtils.isNullString(roadType)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_ROAD_TYPE, baseActivity.getResources().getString(R.string.err_mobile_tower_details_road_type));
            return;
        }
        if (CommonUtils.isNullString(photo2)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_PHOTO_2, baseActivity.getResources().getString(R.string.err_mobile_tower_details_photo_2));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_canal_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(uniqueId)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_UNIQUE_ID, baseActivity.getResources().getString(R.string.err_mobile_tower_details_unique_id));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_canal_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_PHOTO_1, baseActivity.getResources().getString(R.string.err_canal_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(MobileTowerDetailsFragment.ERROR_TYPE_MOBILE_TOWER_LOCATION, baseActivity.getResources().getString(R.string.err_mobile_tower_details_location));
            return;
        }

        addUpdateContent(buildingName, place, ownerName, ownerAddress, ownerMobile, newPropertyId, oldPropertyId, buildingStatus, buildingUsage, yearOfConstruction,
                electricConnectivity, uniqueId, serviceProvider, consumerNo, roadWidth, roadType, wardNo, remarks, photo, photo2, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String buildingName, String place, String ownerName, String ownerAddress, String ownerMobile, String newPropertyId, String oldPropertyId, String buildingStatus, String buildingUsage, String yearOfConstruction, boolean electricConnectivity, String uniqueId,
                          String serviceProvider, String consumerNo, String roadWidth, String roadType, String wardNo, String remarks, String photo, String photo2, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getMobileTowerDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getMobileTowerDetails().setBuildingName(buildingName);
                data.getMobileTowerDetails().setPlace(place);
                data.getMobileTowerDetails().setOwnerName(ownerName);
                data.getMobileTowerDetails().setOwnerAddress(ownerAddress);
                data.getMobileTowerDetails().setOwnerMobile(ownerMobile);
                data.getMobileTowerDetails().setNewPropertyId(newPropertyId);
                data.getMobileTowerDetails().setOldPropertyId(oldPropertyId);
                data.getMobileTowerDetails().setBuildingStatus(Long.parseLong(buildingStatus));
                data.getMobileTowerDetails().setBuildingUsage(Long.parseLong(buildingUsage));
                data.getMobileTowerDetails().setYearOfConstruction(Integer.parseInt(yearOfConstruction));
                data.getMobileTowerDetails().setElectricConnectivity(electricConnectivity);
                data.getMobileTowerDetails().setUniqueId(uniqueId);
                data.getMobileTowerDetails().setServiceProvider(serviceProvider);
                data.getMobileTowerDetails().setConsumerNo(consumerNo);
                data.getMobileTowerDetails().setRoadWidth(Double.parseDouble(roadWidth));
                data.getMobileTowerDetails().setRoadType(roadType);
                data.getMobileTowerDetails().setWard(Long.parseLong(wardNo));
                data.getMobileTowerDetails().setRemarks(remarks);
                data.getMobileTowerDetails().setPhoto1(photo);
                data.getMobileTowerDetails().setPhoto2(photo2);
                data.getMobileTowerDetails().setGeom(geom);
                data.getMobileTowerDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.MobileTower details = new UtilityAssets.MobileTower();
            details.setBuildingName(buildingName);
            details.setPlace(place);
            details.setOwnerName(ownerName);
            details.setOwnerAddress(ownerAddress);
            details.setOwnerMobile(ownerMobile);
            details.setNewPropertyId(newPropertyId);
            details.setOldPropertyId(oldPropertyId);
            details.setBuildingStatus(Long.parseLong(buildingStatus));
            details.setBuildingUsage(Long.parseLong(buildingUsage));
            details.setYearOfConstruction(Integer.parseInt(yearOfConstruction));
            details.setElectricConnectivity(electricConnectivity);
            details.setWard(Long.parseLong(wardNo));
            details.setUniqueId(uniqueId);
            details.setServiceProvider(serviceProvider);
            details.setConsumerNo(consumerNo);
            details.setRoadType(roadType);
            details.setRoadWidth(Double.parseDouble(roadWidth));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setPhoto2(photo2);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setMobileTowerDetails(details);
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
