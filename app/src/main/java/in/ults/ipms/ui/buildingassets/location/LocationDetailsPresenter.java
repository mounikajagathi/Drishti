package in.ults.ipms.ui.buildingassets.location;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class LocationDetailsPresenter <V extends ILocationDetailsView, I extends ILocationDetailsInteractor>
        extends BasePresenter<V, I> implements ILocationDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public LocationDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String placeName, String zone, String wardNumber, String postoffice, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(placeName)) {
            getMvpView().showLocationDetailsFieldError(LocationDetailsFragment.ERROR_TYPE_PLACE_NAME, baseActivity.getResources().getString(R.string.err_place_name));
            return;
        }
        if (CommonUtils.isNullString(zone)) {
            getMvpView().showLocationDetailsFieldError(LocationDetailsFragment.ERROR_TYPE_ZONE, baseActivity.getResources().getString(R.string.err_zone));
            return;
        }
        if (CommonUtils.isNullString(wardNumber)) {
            getMvpView().showLocationDetailsFieldError(LocationDetailsFragment.ERROR_TYPE_WARD_NUMBER, baseActivity.getResources().getString(R.string.err_ward_number));
            return;
        }
        if (CommonUtils.isNullString(postoffice)) {
            getMvpView().showLocationDetailsFieldError(LocationDetailsFragment.ERROR_TYPE_POSTOFFICE, baseActivity.getResources().getString(R.string.err_postoffice));
            return;
        }
        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showLocationDetailsFieldError(LocationDetailsFragment.ERROR_TYPE_PROPERTY_LOCATION, baseActivity.getResources().getString(R.string.err_property_location));
            return;
        }

        savingLocationDetails(placeName,zone,wardNumber,postoffice,locationLatitude,locationLongitude);
    }

    public void savingLocationDetails(String placeName,String  zone,String  wardNumber,String  postoffice,double  locationLatitude,double  locationLongitude){
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate() || AppCacheData.getOurInstance().isBuildingAssetPropertyAdded()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                data.setPropertyDetails(propertyDetails == null ? new BuildingAssets.PropertyDetails() : propertyDetails);
                data.getPropertyDetails().setPlace_name(placeName);
                data.getPropertyDetails().setBldgZone(Integer.parseInt(zone));
                data.getPropertyDetails().setWard(Integer.parseInt(wardNumber));
                data.getPropertyDetails().setPostOffice(Integer.parseInt(postoffice));
                data.getPropertyDetails().setGeom(geom);
                data.getPropertyDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getNewProperty();
            if(propertyDetails!=null) {
                propertyDetails.setPlace_name(placeName);
                propertyDetails.setBldgZone(Integer.parseInt(zone));
                propertyDetails.setWard(Integer.parseInt(wardNumber));
                propertyDetails.setPostOffice(Integer.parseInt(postoffice));
                propertyDetails.setGeom(geom);
                propertyDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                AppCacheData.getOurInstance().setNewProperty(propertyDetails);
                getMvpView().goToNext();
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        getMvpView().onAddOrUpdateSuccess();
    }
}
