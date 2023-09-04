package in.ults.ipms.ui.waterbody.pond;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.data.network.model.response.WaterBodyAssets;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class PondDetailsPresenter<V extends IPondDetailsView, I extends IPondDetailsInteractor>
        extends BasePresenter<V, I> implements IPondDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public PondDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String name, String place, String obId, String area, String capacity, String maintainedBy, String usage, String odour, String pondStatus, String pondType, String presentCondition, String nature, String sideWall, String sideWallType, String pondCondition, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_pond_details_pond_name));
            return;
        }

        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_pond_details_place));
            return;
        }

        if (CommonUtils.isNullString(obId)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_OBID, baseActivity.getResources().getString(R.string.err_pond_details_ob_id));
            return;
        }

        if (CommonUtils.isNullString(area)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_AREA, baseActivity.getResources().getString(R.string.err_pond_details_area));
            return;
        }

        if (CommonUtils.isNullString(capacity)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_CAPACITY, baseActivity.getResources().getString(R.string.err_pond_details_capacity));
            return;
        }

        if (CommonUtils.isNullString(maintainedBy)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_MAINTAINED_BY, baseActivity.getResources().getString(R.string.err_pond_details_maintained_by));
            return;
        }

        if (CommonUtils.isNullString(usage)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_USAGE, baseActivity.getResources().getString(R.string.err_pond_details_usage));
            return;
        }

        if (CommonUtils.isNullString(odour)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_ODOUR, baseActivity.getResources().getString(R.string.err_pond_details_odour));
            return;
        }

        if (CommonUtils.isNullString(pondStatus)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_POND_STATUS, baseActivity.getResources().getString(R.string.err_pond_details_pond_status));
            return;
        }

        if (CommonUtils.isNullString(pondType)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_POND_TYPE, baseActivity.getResources().getString(R.string.err_pond_details_pond_type));
            return;
        }

        if (CommonUtils.isNullString(presentCondition)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_PRESENT_CONDITION, baseActivity.getResources().getString(R.string.err_pond_details_present_condition));
            return;
        }

        if (CommonUtils.isNullString(nature)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_NATURE, baseActivity.getResources().getString(R.string.err_pond_details_nature));
            return;
        }

        if (CommonUtils.isNullString(sideWall)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_SIDEWALL, baseActivity.getResources().getString(R.string.err_pond_details_sidewall));
            return;
        }

        if (CommonUtils.isNullString(sideWallType)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_SIDEWALL_TYPE, baseActivity.getResources().getString(R.string.err_pond_details_sidewall_type));
            return;
        }

        if (CommonUtils.isNullString(pondCondition)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_POND_CONDITION, baseActivity.getResources().getString(R.string.err_pond_details_pond_condition));
            return;
        }

        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_pond_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_pond_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_pond_details_photo));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(PondDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_road_junction_details_location));
            return;
        }

        addUpdateContent( name,  place,  obId,  area,  capacity,  maintainedBy,  usage,  odour,  pondStatus,  pondType,  presentCondition,  nature,  sideWall,  sideWallType,  pondCondition,  wardNo,  remarks,  photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String name, String place, String obId, String area, String capacity, String maintainedBy, String usage, String odour, String pondStatus, String pondType, String presentCondition, String nature, String sideWall, String sideWallType, String pondCondition, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getPondDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getPondDetails().setPondName(name);
                data.getPondDetails().setPlace(place);
                data.getPondDetails().setObid(Long.parseLong(obId));
                data.getPondDetails().setArea(area);
                data.getPondDetails().setCapacity(capacity);
                data.getPondDetails().setUsage(usage);
                data.getPondDetails().setOdour(odour);
                data.getPondDetails().setPondStatus(pondStatus);
                data.getPondDetails().setPondType(pondType);
                data.getPondDetails().setPondcondition(pondCondition);
                data.getPondDetails().setPresentCondition(presentCondition);
                data.getPondDetails().setNature(nature);
//                data.getPondDetails().setSidewall(sideWall);
                data.getPondDetails().setSidewallType(sideWallType);
                data.getPondDetails().setWard(Long.parseLong(wardNo));
                data.getPondDetails().setMaintainedBy(Long.parseLong(maintainedBy));
                data.getPondDetails().setRemarks(remarks);
                data.getPondDetails().setPhoto1(photo);
                data.getPondDetails().setGeom(geom);
                data.getPondDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            WaterBodyAssets.Pond details = new WaterBodyAssets.Pond();
            details.setPondName(name);
            details.setPlace(place);
            details.setObid(Long.parseLong(obId));
            details.setArea(area);
            details.setCapacity(capacity);
            details.setUsage(usage);
            details.setOdour(odour);
            details.setPondStatus(pondStatus);
            details.setPondType(pondType);
            details.setPondcondition(pondCondition);
            details.setPresentCondition(presentCondition);
            details.setNature(nature);
//                details.setSidewall(sideWall);
            details.setSidewallType(sideWallType);
            details.setWard(Long.parseLong(wardNo));
            details.setMaintainedBy(Long.parseLong(maintainedBy));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setPondDetails(details);
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
