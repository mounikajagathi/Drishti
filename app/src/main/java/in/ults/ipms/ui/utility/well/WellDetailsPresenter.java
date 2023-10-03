package in.ults.ipms.ui.utility.well;

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

public class WellDetailsPresenter<V extends IWellDetailsView, I extends IWellDetailsInteractor>
        extends BasePresenter<V, I> implements IWellDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public WellDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, String wellOwner, String purpose, String cover, String surveyNo, String nearRoad, String reWaterAvailMarks, String status, boolean seasonal, String wellType, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(wellOwner)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_WELL_OWNER, baseActivity.getResources().getString(R.string.err_well_details_owner));
            return;
        }
        if (CommonUtils.isNullString(purpose)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_WELL_PURPOSE, baseActivity.getResources().getString(R.string.err_well_details_well_purpose));
            return;
        }
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_well_details_location));
            return;
        }
        if (CommonUtils.isNullString(cover)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_WELL_COVER, baseActivity.getResources().getString(R.string.err_well_details_cover));
            return;
        }
        if (CommonUtils.isNullString(surveyNo)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_SURVEY_NO, baseActivity.getResources().getString(R.string.err_well_details_survey_no));
            return;
        }
        if (CommonUtils.isNullString(nearRoad)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_NEAR_ROAD, baseActivity.getResources().getString(R.string.err_well_details_near_road));
            return;
        }
        if (CommonUtils.isNullString(reWaterAvailMarks)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_RE_WATER_AVAILABILITY_MARKS, baseActivity.getResources().getString(R.string.err_well_details_re_water_availability_marks));
            return;
        }

        if (CommonUtils.isNullString(status)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_STATUS, baseActivity.getResources().getString(R.string.err_well_details_status));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_well_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(wellType)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_WELL_TYPE, baseActivity.getResources().getString(R.string.err_well_details_type));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_well_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_well_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(WellDetailsFragment.ERROR_TYPE_WELL_LOCATION, baseActivity.getResources().getString(R.string.err_well_details_well_location));
            return;
        }

        addUpdateContent(location, wellOwner, purpose, cover, surveyNo, nearRoad, reWaterAvailMarks, status,
                seasonal, wellType, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, String wellOwner, String purpose, String cover, String surveyNo, String nearRoad, String reWaterAvailMarks, String status, boolean seasonal, String wellType,
                          String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getWellDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getWellDetails().setLocation(location);
                data.getWellDetails().setWellCover(cover);
                data.getWellDetails().setWellPurpose(purpose);
                data.getWellDetails().setWellOwner(wellOwner);
                data.getWellDetails().setSurveyNo(surveyNo);
                data.getWellDetails().setNearRoad(nearRoad);
                data.getWellDetails().setReWaterAvailabilityMarks(reWaterAvailMarks);
                data.getWellDetails().setStatus(status);
                data.getWellDetails().setSeasonal(seasonal);
                data.getWellDetails().setWard(Long.parseLong(wardNo));
                data.getWellDetails().setWellType(Integer.parseInt(wellType));
                data.getWellDetails().setRemarks(remarks);
                data.getWellDetails().setPhoto1(photo);
                data.getWellDetails().setGeom(geom);
                data.getWellDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Well details = new UtilityAssets.Well();
            details.setLocation(location);
            details.setWellType(Long.parseLong(wellType));
            details.setWellCover(cover);
            details.setStatus(status);
            details.setSurveyNo(surveyNo);
            details.setNearRoad(nearRoad);
            details.setReWaterAvailabilityMarks(reWaterAvailMarks);
            details.setSeasonal(seasonal);
            details.setLocation(wellOwner);
            details.setWard(Long.parseLong(wardNo));
            details.setWellPurpose(purpose);
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setWellDetails(details);
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
