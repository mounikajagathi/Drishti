package in.ults.ipms.ui.utility.streettap;

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

public class StreetTapDetailsPresenter<V extends IStreetTapDetailsView, I extends IStreetTapDetailsInteractor>
        extends BasePresenter<V, I> implements IStreetTapDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public StreetTapDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String location, boolean workingStatus, String fundedBy, String address, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_street_tap_details_location));
            return;
        }
        if (CommonUtils.isNullString(address)) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_ADDRESS, baseActivity.getResources().getString(R.string.err_street_tap_details_address));
            return;
        }
        if (CommonUtils.isNullString(fundedBy)) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_FUNDED_BY, baseActivity.getResources().getString(R.string.err_street_tap_details_funded_by));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_street_tap_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_street_tap_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_street_tap_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(StreetTapDetailsFragment.ERROR_TYPE_STREET_LOCATION, baseActivity.getResources().getString(R.string.err_street_tap_details_street_location));
            return;
        }

        addUpdateContent(location, workingStatus, fundedBy, address, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String location, boolean workingStatus, String fundedBy, String address, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getStreetTapDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getStreetTapDetails().setFundedBy(fundedBy);
                data.getStreetTapDetails().setLocation(location);
                data.getStreetTapDetails().setWard(Long.parseLong(wardNo));
                data.getStreetTapDetails().setRemarks(remarks);
                data.getStreetTapDetails().setAddress(address);
                data.getStreetTapDetails().setWorkingStatus(workingStatus);
                data.getStreetTapDetails().setPhoto1(photo);
                data.getStreetTapDetails().setGeom(geom);
                data.getStreetTapDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.StreetTap details = new UtilityAssets.StreetTap();
            details.setFundedBy(fundedBy);
            details.setAddress(address);
            details.setWorkingStatus(workingStatus);
            details.setLocation(location);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setStreetTapDetails(details);
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
