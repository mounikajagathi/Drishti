package in.ults.ipms.ui.utility.divider;

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

public class DividerDetailsPresenter<V extends IDividerDetailsView, I extends IDividerDetailsInteractor>
        extends BasePresenter<V, I> implements IDividerDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public DividerDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String place, String length, String material, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(place)) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_PLACE, baseActivity.getResources().getString(R.string.err_divider_details_place));
            return;
        }
        if (CommonUtils.isNullString(length)) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_LENGTH, baseActivity.getResources().getString(R.string.err_divider_details_length));
            return;
        }
        if (CommonUtils.isNullString(material)) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_MATERIAL, baseActivity.getResources().getString(R.string.err_divider_details_material));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_divider_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_divider_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_divider_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(DividerDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_divider_details_location));
            return;
        }

        addUpdateContent(place, length, material, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String place, String length, String material, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getDividerDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getDividerDetails().setPlace(place);
                data.getDividerDetails().setLength(length);
                data.getDividerDetails().setDividerMaterial(material);
                data.getDividerDetails().setWard(Long.parseLong(wardNo));
                data.getDividerDetails().setRemarks(remarks);
                data.getDividerDetails().setPhoto1(photo);
                data.getDividerDetails().setGeom(geom);
                data.getDividerDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Divider details = new UtilityAssets.Divider();
            details.setPlace(place);
            details.setLength(length);
            details.setDividerMaterial(material);
            details.setWard(Long.parseLong(wardNo));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setDividerDetails(details);
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
