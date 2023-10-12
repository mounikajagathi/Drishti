package in.ults.ipms.ui.utility.stadium;

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

public class StadiumDetailsPresenter<V extends IStadiumDetailsView, I extends IStadiumDetailsInteractor>
        extends BasePresenter<V, I> implements IStadiumDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public StadiumDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String name, String location, String address, String area, String surveyNo, String noGallery, String electricity, String bathroom, String galleryCoverage, String gallery, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(name)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_NAME, baseActivity.getResources().getString(R.string.err_stadium_details_name));
            return;
        }
        if (CommonUtils.isNullString(area)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_AREA, baseActivity.getResources().getString(R.string.err_stadium_details_area));
            return;
        }
        if (CommonUtils.isNullString(location)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_LOCATION, baseActivity.getResources().getString(R.string.err_stadium_details_location));
            return;
        }
        if (CommonUtils.isNullString(address)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_ADDRESS, baseActivity.getResources().getString(R.string.err_stadium_details_address));
            return;
        }
        if (CommonUtils.isNullString(surveyNo)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_SURVEY_NO, baseActivity.getResources().getString(R.string.err_stadium_details_survey_no));
            return;
        }
        if (CommonUtils.isNullString(noGallery)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_NO_GALLERY, baseActivity.getResources().getString(R.string.err_stadium_details_no_gallery));
            return;
        }
        if (CommonUtils.isNullString(electricity)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_ELECTRICITY, baseActivity.getResources().getString(R.string.err_stadium_details_electricity));
            return;
        }
        if (CommonUtils.isNullString(bathroom)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_BATHROOM, baseActivity.getResources().getString(R.string.err_stadium_details_bathroom));
            return;
        }
        if (CommonUtils.isNullString(galleryCoverage)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_GALLERY_COVERAGE, baseActivity.getResources().getString(R.string.err_stadium_details_gallery_coverage));
            return;
        }
        if (CommonUtils.isNullString(gallery)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_GALLERY, baseActivity.getResources().getString(R.string.err_stadium_details_gallery));
            return;
        }
        if (CommonUtils.isNullString(wardNo)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_WARD_NO, baseActivity.getResources().getString(R.string.err_stadium_details_ward_number));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_REMARKS, baseActivity.getResources().getString(R.string.err_stadium_details_remarks));
            return;
        }
        if (CommonUtils.isNullString(photo)) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_PHOTO, baseActivity.getResources().getString(R.string.err_stadium_details_photo_1));
            return;
        }

        if (locationLatitude == 0.0 || locationLongitude == 0.0) {
            getMvpView().showErrors(StadiumDetailsFragment.ERROR_TYPE_STADIUM_LOCATION, baseActivity.getResources().getString(R.string.err_stadium_details_stadium_location));
            return;
        }

        addUpdateContent(name, location, address, area, surveyNo, noGallery, electricity, bathroom,
                galleryCoverage, gallery, wardNo, remarks, photo, locationLatitude, locationLongitude);
    }


    void addUpdateContent(String name, String location, String address, String area, String surveyNo, String noGallery, String electricity, String bathroom, String galleryCoverage, String gallery,
                          String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude) {
        GeomPoint geom = new GeomPoint();
        geom.setGeom(locationLongitude, locationLatitude);
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getStadiumDetails() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                data.getStadiumDetails().setName(name);
                data.getStadiumDetails().setArea(area);
                data.getStadiumDetails().setAddress(address);
                data.getStadiumDetails().setLocation(location);
                data.getStadiumDetails().setSurveyNo(surveyNo);
                data.getStadiumDetails().setNoGallery(noGallery);
                data.getStadiumDetails().setElectricity(electricity);
                data.getStadiumDetails().setBathroom(bathroom);
                data.getStadiumDetails().setGalleryCoverage(galleryCoverage);
                data.getStadiumDetails().setWard(Long.parseLong(wardNo));
                data.getStadiumDetails().setGallery(gallery);
                data.getStadiumDetails().setRemarks(remarks);
                data.getStadiumDetails().setPhoto1(photo);
                data.getStadiumDetails().setGeom(geom);
                data.getStadiumDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
            UtilityAssets.Stadium details = new UtilityAssets.Stadium();
            details.setName(name);
            details.setGallery(gallery);
            details.setArea(area);
            details.setBathroom(bathroom);
            details.setSurveyNo(surveyNo);
            details.setNoGallery(noGallery);
            details.setElectricity(electricity);
            details.setGalleryCoverage(galleryCoverage);
            details.setLocation(location);
            details.setWard(Long.parseLong(wardNo));
            details.setAddress(address);
            details.setRemarks(remarks);
            details.setPhoto1(photo);
            details.setGeom(geom);
            details.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            data.setStadiumDetails(details);
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
