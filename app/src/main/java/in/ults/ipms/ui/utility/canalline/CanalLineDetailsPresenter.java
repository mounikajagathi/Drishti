package in.ults.ipms.ui.utility.canalline;

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

public class CanalLineDetailsPresenter<V extends ICanalLineDetailsView, I extends ICanalLineDetailsInteractor>
        extends BasePresenter<V, I> implements ICanalLineDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public CanalLineDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String streetName, String area, String type, String subType, String classField, String width, String length, String startPoint, String endPoint, String status, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(streetName)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_STREET_NAME, baseActivity.getResources().getString(R.string.err_canal_line_details_street_name));
            return;
        }
        if (CommonUtils.isNullString(area)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_AREA, baseActivity.getResources().getString(R.string.err_canal_line_details_area));
            return;
        }
        if (CommonUtils.isNullString(type)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_CANAL_TYPE, baseActivity.getResources().getString(R.string.err_canal_line_details_type));
            return;
        }
        if (CommonUtils.isNullString(subType)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_SUB_TYPE, baseActivity.getResources().getString(R.string.err_canal_line_details_sub_type));
            return;
        }
        if (CommonUtils.isNullString(classField)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_CLASS_FIELD, baseActivity.getResources().getString(R.string.err_canal_line_details_class_field));
            return;
        }
        if (CommonUtils.isNullString(width)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_CANAL_WIDTH, baseActivity.getResources().getString(R.string.err_canal_line_details_canal_width));
            return;
        }
        if (CommonUtils.isNullString(length)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_CANAL_LENGTH, baseActivity.getResources().getString(R.string.err_canal_line_details_canal_length));
            return;
        }

        if (CommonUtils.isNullString(startPoint)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_START_POINT, baseActivity.getResources().getString(R.string.err_canal_line_details_start_point));
            return;
        }
        if (CommonUtils.isNullString(endPoint)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_END_POINT, baseActivity.getResources().getString(R.string.err_canal_line_details_end_point));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_canal_line_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(status)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_STATUS, baseActivity.getResources().getString(R.string.err_canal_line_details_status));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_canal_line_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_canal_line_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(CanalLineDetailsFragment.ERROR_TYPE_CANAL_LOCATION, baseActivity.getResources().getString(R.string.err_canal_line_details_canal_location));
            return;
        }

        addUpdateContent(streetName, area, type, subType, classField, width, length, startPoint,
                endPoint, status, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String streetName, String area, String type, String subType, String classField, String width, String length, String startPoint, String endPoint, String status,
                          String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getCanalLineDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getCanalLineDetails().setCanalLineSteetName(streetName);
                data.getCanalLineDetails().setArea(area);
                data.getCanalLineDetails().setType(type);
                data.getCanalLineDetails().setCanalLineSubType(subType);
                data.getCanalLineDetails().setClassField(classField);
                data.getCanalLineDetails().setWidth(width);
                data.getCanalLineDetails().setLength(length);
                data.getCanalLineDetails().setStartPoint(startPoint);
                data.getCanalLineDetails().setEndPoint(endPoint);
                data.getCanalLineDetails().setWard(Long.parseLong(wardNo));
                data.getCanalLineDetails().setStatus(status);
                data.getCanalLineDetails().setRemarks(remarks);
                data.getCanalLineDetails().setPhoto1(photo);
                data.getCanalLineDetails().setGeom(geom);
                data.getCanalLineDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.CanalLine details = new UtilityAssets.CanalLine();
            details.setCanalLineSteetName(streetName);
            details.setArea(area);
            details.setType(type);
            details.setCanalLineSubType(subType);
            details.setClassField(classField);
            details.setWidth(width);
            details.setLength(length);
            details.setStartPoint(startPoint);
            details.setEndPoint(endPoint);
            details.setWard(Long.parseLong(wardNo));
            details.setStatus(status);
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setCanalLineDetails(details);
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
