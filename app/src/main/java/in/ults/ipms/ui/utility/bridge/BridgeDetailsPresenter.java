package in.ults.ipms.ui.utility.bridge;

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

public class BridgeDetailsPresenter<V extends IBridgeDetailsView, I extends IBridgeDetailsInteractor>
        extends BasePresenter<V, I> implements IBridgeDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public BridgeDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String bridgeName, String place, String bridgeMaterial, String bridgeSubType, String carriageWidth, String bridgeWidth, String wardNo, String maintainedBy, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(bridgeName)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_BRIDGE_NAME, baseActivity.getResources().getString(R.string.err_bridge_details_name));
            return;
        }
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_bridge_details_place));
            return;
        }
        if (CommonUtils.isNullString(bridgeMaterial)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_BRIDGE_MATERIAL, baseActivity.getResources().getString(R.string.err_bridge_details_bridge_material));
            return;
        }
        if (CommonUtils.isNullString(bridgeSubType)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_SUB_TYPE, baseActivity.getResources().getString(R.string.err_bridge_details_bridge_sub_type));
            return;
        }
        if (CommonUtils.isNullString(carriageWidth)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_CARRIAGE_WIDTH, baseActivity.getResources().getString(R.string.err_bridge_details_carriage_width));
            return;
        }
        if (CommonUtils.isNullString(bridgeWidth)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_BRIDGE_WIDTH, baseActivity.getResources().getString(R.string.err_bridge_details_bridge_width));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_bridge_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(maintainedBy)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_MAINTAINED_BY, baseActivity.getResources().getString(R.string.err_bridge_details_maintained_by));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_bridge_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_bridge_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(BridgeDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_bridge_details_bridge_location));
            return;
        }

        addUpdateContent(bridgeName, place, bridgeMaterial, bridgeSubType, carriageWidth, bridgeWidth, wardNo, maintainedBy, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String bridgeName, String place, String bridgeMaterial, String bridgeSubType, String carriageWidth, String bridgeWidth, String wardNo, String maintainedBy, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getBridgeDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getBridgeDetails().setBridgeName(bridgeName);
                data.getBridgeDetails().setPlace(place);
                data.getBridgeDetails().setBridgeMaterial(bridgeMaterial);
                data.getBridgeDetails().setBridgeSubType(bridgeSubType);
                data.getBridgeDetails().setWidth(carriageWidth);
                data.getBridgeDetails().setBridgeWidth(bridgeWidth);
                data.getBridgeDetails().setWard(Long.parseLong(wardNo));
                data.getBridgeDetails().setMaintainedBy(Long.parseLong(maintainedBy));
                data.getBridgeDetails().setRemarks(remarks);
                data.getBridgeDetails().setPhoto1(photo);
                data.getBridgeDetails().setGeom(geom);
                data.getBridgeDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Bridge details = new UtilityAssets.Bridge();
            details.setBridgeName(bridgeName);
            details.setPlace(place);
            details.setBridgeMaterial(bridgeMaterial);
            details.setBridgeSubType(bridgeSubType);
            details.setWidth(carriageWidth);
            details.setBridgeWidth(bridgeWidth);
            details.setWard(Long.parseLong(wardNo));
            details.setMaintainedBy(Long.parseLong(maintainedBy));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setBridgeDetails(details);
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
