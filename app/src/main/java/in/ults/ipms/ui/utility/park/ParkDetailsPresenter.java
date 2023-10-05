package in.ults.ipms.ui.utility.park;

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

public class ParkDetailsPresenter<V extends IParkDetailsView, I extends IParkDetailsInteractor>
        extends BasePresenter<V, I> implements IParkDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public ParkDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, String name, String area, String surveyNo, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_park_details_location));
            return;
        }
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_park_details_name));
            return;
        }
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_PARK_TYPE, baseActivity.getResources().getString(R.string.err_park_details_type));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_park_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_park_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(surveyNo)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_PARK_SURVEY_NO, baseActivity.getResources().getString(R.string.err_park_details_survey_no));
            return;
        }
        if (CommonUtils.isNullString(area)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_PARK_AREA, baseActivity.getResources().getString(R.string.err_park_details_area));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_park_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(ParkDetailsFragment.ERROR_TYPE_PARK_LOCATION, baseActivity.getResources().getString(R.string.err_park_details_park_location));
            return;
        }

        addUpdateContent(location, name,area, surveyNo, type, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, String name,String area, String surveyNo, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getParkDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getParkDetails().setLocation(location);
                data.getParkDetails().setParkName(name);
                data.getParkDetails().setParkType(type);
                data.getParkDetails().setWard(Long.parseLong(wardNo));
                data.getParkDetails().setRemarks(remarks);
                data.getParkDetails().setArea(area);
                data.getParkDetails().setSurveyNo(surveyNo);
                data.getParkDetails().setPhoto1(photo);
                data.getParkDetails().setGeom(geom);
                data.getParkDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Park details = new UtilityAssets.Park();
            details.setLocation(location);
            details.setParkName(name);
            details.setParkType(type);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setArea(area);
            details.setSurveyNo(surveyNo);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setParkDetails(details);
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
