package in.ults.ipms.ui.utility.statue;

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

public class StatueDetailsPresenter<V extends IStatueDetailsView, I extends IStatueDetailsInteractor>
        extends BasePresenter<V, I> implements IStatueDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public StatueDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(StatueDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_statue_details_location));
            return;
        }
       
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(StatueDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_statue_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(StatueDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_statue_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(StatueDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_statue_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(StatueDetailsFragment.ERROR_TYPE_STATUE_LOCATION, baseActivity.getResources().getString(R.string.err_statue_details_location));
            return;
        }

        addUpdateContent(location, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getStatueDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getStatueDetails().setLocation(location);
                data.getStatueDetails().setWard(Long.parseLong(wardNo));
                data.getStatueDetails().setRemarks(remarks);
                data.getStatueDetails().setPhoto1(photo);
                data.getStatueDetails().setGeom(geom);
                data.getStatueDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Statue details = new UtilityAssets.Statue();
            details.setLocation(location);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setStatueDetails(details);
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
