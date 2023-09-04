package in.ults.ipms.ui.utility.culvert;

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

public class CulvertDetailsPresenter<V extends ICulvertDetailsView, I extends ICulvertDetailsInteractor>
        extends BasePresenter<V, I> implements ICulvertDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public CulvertDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String name, String place, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(CulvertDetailsFragment.ERROR_TYPE_CULVERT_NAME, baseActivity.getResources().getString(R.string.err_culvert_details_name));
            return;
        }
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(CulvertDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_culvert_details_place));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(CulvertDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_culvert_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(CulvertDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_culvert_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(CulvertDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_culvert_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(CulvertDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_culvert_details_culvert_location));
            return;
        }

        addUpdateContent(name, place,  wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String name, String place, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getCulvertDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getCulvertDetails().setCulvertName(name);
                data.getCulvertDetails().setPlace(place);
                data.getCulvertDetails().setWard(Long.parseLong(wardNo));
                data.getCulvertDetails().setRemarks(remarks);
                data.getCulvertDetails().setPhoto1(photo);
                data.getCulvertDetails().setGeom(geom);
                data.getCulvertDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Culvert details = new UtilityAssets.Culvert();
            details.setCulvertName(name);
            details.setPlace(place);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setCulvertDetails(details);
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
