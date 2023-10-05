package in.ults.ipms.ui.utility.transformer;

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

public class TransformerDetailsPresenter<V extends ITransformerDetailsView, I extends ITransformerDetailsInteractor>
        extends BasePresenter<V, I> implements ITransformerDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public TransformerDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String poleNumber, String location, String consumerNo, String applicationNo, String category, String singleTube, String doubleTube, String led, String cfl, String bulb, String sodiumVapour, String connectedLoad, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(poleNumber)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_POLE_NUMBER, baseActivity.getResources().getString(R.string.err_transformer_details_street_pole_number));
            return;
        }
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_transformer_details_location));
            return;
        }
        if (CommonUtils.isNullString(applicationNo)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_APPLICATION_NO, baseActivity.getResources().getString(R.string.err_transformer_details_application_no));
            return;
        }
        if (CommonUtils.isNullString(consumerNo)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_CONSUMER_NO, baseActivity.getResources().getString(R.string.err_transformer_details_consumer_no));
            return;
        }
        if (CommonUtils.isNullString(category)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_CANAL_CATEGORY, baseActivity.getResources().getString(R.string.err_transformer_details_category));
            return;
        }
        if (CommonUtils.isNullString(singleTube)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_SINGLE_TUBE, baseActivity.getResources().getString(R.string.err_transformer_details_single_tube));
            return;
        }
        if (CommonUtils.isNullString(doubleTube)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_DOUBLE_TUBE, baseActivity.getResources().getString(R.string.err_transformer_details_double_tube));
            return;
        }
        if (CommonUtils.isNullString(led)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_LED, baseActivity.getResources().getString(R.string.err_transformer_details_led));
            return;
        }
        if (CommonUtils.isNullString(cfl)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_CFL, baseActivity.getResources().getString(R.string.err_transformer_details_cfl));
            return;
        }

        if (CommonUtils.isNullString(bulb)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_BULB, baseActivity.getResources().getString(R.string.err_transformer_details_start_bulb));
            return;
        }
        if (CommonUtils.isNullString(sodiumVapour)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_SODIUM_VAPOUR, baseActivity.getResources().getString(R.string.err_transformer_details_end_sodium_vapour));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_transformer_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(connectedLoad)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_CONNECTED_LOAD, baseActivity.getResources().getString(R.string.err_transformer_details_connected_load));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_transformer_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_transformer_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(TransformerDetailsFragment.ERROR_TYPE_TRANSFORMER_LOCATION, baseActivity.getResources().getString(R.string.err_transformer_details_transformer_location));
            return;
        }

        addUpdateContent(poleNumber, location, consumerNo, applicationNo, category, singleTube, doubleTube, led, cfl, bulb,
                sodiumVapour, connectedLoad, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String poleNumber, String location, String consumerNo, String applicationNo, String category, String singleTube, String doubleTube, String led, String cfl, String bulb, String sodiumVapour, String connectedLoad,
                          String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getTransformerDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getTransformerDetails().setPoleNumber(poleNumber);
                data.getTransformerDetails().setLocation(location);
                data.getTransformerDetails().setConsumerNo(consumerNo);
                data.getTransformerDetails().setApplicationNo(applicationNo);
                data.getTransformerDetails().setCategory(category);
                data.getTransformerDetails().setSingleTube(singleTube);
                data.getTransformerDetails().setDoubleTube(doubleTube);
                data.getTransformerDetails().setLED(led);
                data.getTransformerDetails().setCfl(cfl);
                data.getTransformerDetails().setBulb(bulb);
                data.getTransformerDetails().setSodiumVapour(sodiumVapour);
                data.getTransformerDetails().setWard(Long.parseLong(wardNo));
                data.getTransformerDetails().setConnectedLoad(connectedLoad);
                data.getTransformerDetails().setRemarks(remarks);
                data.getTransformerDetails().setPhoto1(photo);
                data.getTransformerDetails().setGeom(geom);
                data.getTransformerDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Transformer details = new UtilityAssets.Transformer();
            details.setPoleNumber(poleNumber);
            details.setLocation(location);
            details.setConsumerNo(consumerNo);
            details.setApplicationNo(applicationNo);
            details.setCategory(category);
            details.setSingleTube(singleTube);
            details.setDoubleTube(doubleTube);
            details.setLED(led);
            details.setCfl(cfl);
            details.setBulb(bulb);
            details.setSodiumVapour(sodiumVapour);
            details.setWard(Long.parseLong(wardNo));
            details.setConnectedLoad(connectedLoad);
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setTransformerDetails(details);
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
