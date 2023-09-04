package in.ults.ipms.ui.utility.drainage.details;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.data.network.model.response.UtilityAssets;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class DrainageDetailsPresenter<V extends IDrainageDetailsView, I extends IDrainageDetailsInteractor>
        extends BasePresenter<V, I> implements IDrainageDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public DrainageDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String name, String place, String material, String type, String wardNumber, String remarks, String photo, GeomPolyLine geom) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(name)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_NAME,baseActivity.getResources().getString(R.string.err_drainage_details_drainage_name));
            return;
        }
        if (CommonUtils.isNullString(place)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_PLACE,baseActivity.getResources().getString(R.string.err_drainage_details_start_place));
            return;
        }
        if (CommonUtils.isNullString(material)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_MATERIAL,baseActivity.getResources().getString(R.string.err_drainage_details_drainage_material));
            return;
        }
        if (CommonUtils.isNullString(type)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_TYPE,baseActivity.getResources().getString(R.string.err_drainage_details_drainage_type));
            return;
        }
        if (CommonUtils.isNullString(wardNumber)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_WARD_NUMBER,baseActivity.getResources().getString(R.string.err_drainage_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_REMARKS,baseActivity.getResources().getString(R.string.err_drainage_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)){
            getMvpView().showRoadAddFieldError(DrainageDetailsFragment.ERROR_TYPE_PHOTO,baseActivity.getResources().getString(R.string.err_drainage_details_photo));
            return;
        }

        addUpdateContent(name, place, material, type, wardNumber, remarks, photo, geom);

    }


    void addUpdateContent(String name, String place, String material, String type, String wardNumber, String remarks, String photo, GeomPolyLine geom) {
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getDrainageDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getDrainageDetails().setDrainageName(name);
                data.getDrainageDetails().setPlace(place);
                data.getDrainageDetails().setDrainageMaterial(material);
                data.getDrainageDetails().setDrainageType(type);
                data.getDrainageDetails().setWard(Long.parseLong(wardNumber));
                data.getDrainageDetails().setRemarks(remarks);
                data.getDrainageDetails().setPhoto1(photo);
                data.getDrainageDetails().setGeom(geom);
                data.getDrainageDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Drainage details = new UtilityAssets.Drainage();
            details.setDrainageName(name);
            details.setPlace(place);
            details.setDrainageMaterial(material);
            details.setDrainageType(type);
            details.setWard(Long.parseLong(wardNumber));
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setDrainageDetails(details);
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
