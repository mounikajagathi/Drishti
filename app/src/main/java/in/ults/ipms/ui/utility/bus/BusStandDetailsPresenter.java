package in.ults.ipms.ui.utility.bus;

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

public class BusStandDetailsPresenter<V extends IBusStandDetailsView, I extends IBusStandDetailsInteractor>
        extends BasePresenter<V, I> implements IBusStandDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public BusStandDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String name, String surveyNo, String ownership, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
      getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_bus_stand_details_place));
            return;
        }
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_bus_stand_details_name));
            return;
        }
        if (CommonUtils.isNullString(surveyNo)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_SURVEY_NO, baseActivity.getResources().getString(R.string.err_bus_stand_details_survey_no));
            return;
        }
        if (CommonUtils.isNullString(ownership)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_OWNERSHIP, baseActivity.getResources().getString(R.string.err_bus_stand_details_ownership));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_bus_stand_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_bus_stand_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_bus_stand_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(BusStandDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_bus_stand_details_location));
            return;
        }

        addUpdateContent(place, name, surveyNo, ownership, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String name, String surveyNo, String ownership,  String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getBusStandDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getBusStandDetails().setBusStandName(name);
                data.getBusStandDetails().setPlace(place);
                data.getBusStandDetails().setSurveyNo(Long.parseLong(surveyNo));
                data.getBusStandDetails().setOwnership(Long.parseLong(ownership));
                data.getBusStandDetails().setWard(Long.parseLong(wardNo));
                data.getBusStandDetails().setRemarks(remarks);
                data.getBusStandDetails().setPhoto1(photo);
                data.getBusStandDetails().setGeom(geom);
                data.getBusStandDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.BusStand details = new UtilityAssets.BusStand();
            details.setBusStandName(name);
            details.setPlace(place);
            details.setSurveyNo(Long.parseLong(surveyNo));
            details.setOwnership(Long.parseLong(ownership));
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setBusStandDetails(details);
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
