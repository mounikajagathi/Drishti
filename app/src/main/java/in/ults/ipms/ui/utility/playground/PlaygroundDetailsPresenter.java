package in.ults.ipms.ui.utility.playground;

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

public class PlaygroundDetailsPresenter<V extends IPlaygroundDetailsView, I extends IPlaygroundDetailsInteractor>
        extends BasePresenter<V, I> implements IPlaygroundDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public PlaygroundDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, String name, String type,String area,String surveyNo, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_playground_details_location));
            return;
        }
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_playground_details_name));
            return;
        }
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_GROUND_TYPE, baseActivity.getResources().getString(R.string.err_playground_details_type));
            return;
        }
        if (CommonUtils.isNullString(area)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_GROUND_AREA, baseActivity.getResources().getString(R.string.err_playground_details_area));
            return;
        }
        if (Double.parseDouble(area) > 10) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_GROUND_AREA, baseActivity.getResources().getString(R.string.err_playground_details_area_validation));
            return;
        }
        if (CommonUtils.isNullString(surveyNo)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_GROUND_SURVEY_NO, baseActivity.getResources().getString(R.string.err_playground_details_survey_no));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_playground_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_playground_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_playground_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(PlaygroundDetailsFragment.ERROR_TYPE_PLAYGROUND_LOCATION, baseActivity.getResources().getString(R.string.err_playground_details_playground_location));
            return;
        }

        addUpdateContent(location, name, type,area,surveyNo, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, String name, String type,String area,String surveyNo, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getPlaygroundDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getPlaygroundDetails().setLocation(location);
                data.getPlaygroundDetails().setGroundName(name);
                data.getPlaygroundDetails().setGroundType(type);
                data.getPlaygroundDetails().setGroundArea(area);
                data.getPlaygroundDetails().setSurveyNo(surveyNo);
                data.getPlaygroundDetails().setWard(Long.parseLong(wardNo));
                data.getPlaygroundDetails().setRemarks(remarks);
                data.getPlaygroundDetails().setPhoto1(photo);
                data.getPlaygroundDetails().setGeom(geom);
                data.getPlaygroundDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Playground details = new UtilityAssets.Playground();
            details.setLocation(location);
            details.setGroundName(name);
            details.setGroundType(type);
            details.setWard(Long.parseLong(wardNo));
            details.setGroundArea(area);
            details.setSurveyNo(surveyNo);
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setPlaygroundDetails(details);
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
