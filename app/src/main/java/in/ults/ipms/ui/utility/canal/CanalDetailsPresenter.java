package in.ults.ipms.ui.utility.canal;

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

public class CanalDetailsPresenter<V extends ICanalDetailsView, I extends ICanalDetailsInteractor>
        extends BasePresenter<V, I> implements ICanalDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public CanalDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String name, String location, String streetName, String area, String type, String subType, String classField, String sidewall, String condition, String startPoint, String endPoint, String status, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_CANAL_NAME, baseActivity.getResources().getString(R.string.err_canal_details_name));
            return;
        }
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_canal_details_location));
            return;
        }
        if (CommonUtils.isNullString(streetName)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_STREET_NAME, baseActivity.getResources().getString(R.string.err_canal_details_street_name));
            return;
        }
        if (CommonUtils.isNullString(area)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_AREA, baseActivity.getResources().getString(R.string.err_canal_details_area));
            return;
        }
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_CANAL_TYPE, baseActivity.getResources().getString(R.string.err_canal_details_type));
            return;
        }
        if (CommonUtils.isNullString(subType)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_SUB_TYPE, baseActivity.getResources().getString(R.string.err_canal_details_sub_type));
            return;
        }
        if (CommonUtils.isNullString(classField)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_CLASS_FIELD, baseActivity.getResources().getString(R.string.err_canal_details_class_field));
            return;
        }
        if (CommonUtils.isNullString(sidewall)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_SIDE_WALL, baseActivity.getResources().getString(R.string.err_canal_details_sidewall));
            return;
        }
        if (CommonUtils.isNullString(condition)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_CONDITION, baseActivity.getResources().getString(R.string.err_canal_details_condtion));
            return;
        }

        if (CommonUtils.isNullString(startPoint)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_START_POINT, baseActivity.getResources().getString(R.string.err_canal_details_start_point));
            return;
        }
        if (CommonUtils.isNullString(endPoint)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_END_POINT, baseActivity.getResources().getString(R.string.err_canal_details_end_point));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_canal_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(status)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_STATUS, baseActivity.getResources().getString(R.string.err_canal_details_status));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_canal_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_canal_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(CanalDetailsFragment.ERROR_TYPE_CANAL_LOCATION, baseActivity.getResources().getString(R.string.err_canal_details_location));
            return;
        }

        addUpdateContent(name, location, streetName, area, type, subType, classField, sidewall, condition, startPoint,
                endPoint, status, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String name, String location, String streetName, String area, String type, String subType, String classField, String sidewall, String condition, String startPoint, String endPoint, String status,
                          String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getCanalDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getCanalDetails().setCanalName(name);
                data.getCanalDetails().setLocation(location);
                data.getCanalDetails().setCanalSteetName(streetName);
                data.getCanalDetails().setArea(area);
                data.getCanalDetails().setType(type);
                data.getCanalDetails().setCanalSubType(subType);
                data.getCanalDetails().setClassField(classField);
                data.getCanalDetails().setSidewall(sidewall);
                data.getCanalDetails().setCondition(condition);
                data.getCanalDetails().setStartPoint(startPoint);
                data.getCanalDetails().setEndPoint(endPoint);
                data.getCanalDetails().setWard(Long.parseLong(wardNo));
                data.getCanalDetails().setStatus(status);
                data.getCanalDetails().setRemarks(remarks);
                data.getCanalDetails().setPhoto1(photo);
                data.getCanalDetails().setGeom(geom);
                data.getCanalDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Canal details = new UtilityAssets.Canal();
            details.setCanalName(name);
            details.setLocation(location);
            details.setCanalSteetName(streetName);
            details.setArea(area);
            details.setType(type);
            details.setCanalSubType(subType);
            details.setClassField(classField);
            details.setSidewall(sidewall);
            details.setCondition(condition);
            details.setStartPoint(startPoint);
            details.setEndPoint(endPoint);
            details.setWard(Long.parseLong(wardNo));
            details.setStatus(status);
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setCanalDetails(details);
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
