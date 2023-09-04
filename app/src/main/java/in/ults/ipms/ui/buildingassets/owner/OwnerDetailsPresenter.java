package in.ults.ipms.ui.buildingassets.owner;

import static in.ults.ipms.ui.buildingassets.owner.OwnerDetailsFragment.selectedPosition;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;


public class OwnerDetailsPresenter<V extends IOwnerDetailsView, I extends IOwnerDetailsInteractor>
        extends BasePresenter<V, I> implements IOwnerDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public OwnerDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String firstName, String lastName, String email, String mobile, String landphone, String gender, String placeName, String village, String street, String ownerHouse, String ownerOccupation, String ownerPostoffice, String ownerSurveyNumber) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(firstName)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_FIRST_NAME, baseActivity.getResources().getString(R.string.err_first_name));
            return;
        }
        if (CommonUtils.isNullString(lastName)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_LAST_NAME, baseActivity.getResources().getString(R.string.err_last_name));
            return;
        }
        if (CommonUtils.isNullString(gender)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_GENDER, baseActivity.getResources().getString(R.string.err_gender));
            return;
        }
        if (CommonUtils.isNullString(placeName)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_PLACE_NAME, baseActivity.getResources().getString(R.string.err_place_name));
            return;
        }
        if (CommonUtils.isNullString(village)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_VILLAGE, baseActivity.getResources().getString(R.string.err_village));
            return;
        }
        if (CommonUtils.isNullString(street)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_STREET, baseActivity.getResources().getString(R.string.err_street));
            return;
        }
        if (CommonUtils.isNullString(ownerHouse)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_OWNER_HOUSE, baseActivity.getResources().getString(R.string.err_owner_house));
            return;
        }
        if (CommonUtils.isNullString(mobile)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_MOBILE, baseActivity.getResources().getString(R.string.err_mobile));
            return;
        }
        if (CommonUtils.isNullString(landphone)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_LAND_PHONE, baseActivity.getResources().getString(R.string.err_landphone));
            return;
        }
        if (CommonUtils.isNullString(email)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_EMAIL, baseActivity.getResources().getString(R.string.err_email));
            return;
        }
        if (CommonUtils.isNullString(ownerOccupation)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_OWNER_OCCUPATION, baseActivity.getResources().getString(R.string.err_occupation));
            return;
        }
        if (CommonUtils.isNullString(ownerPostoffice)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_OWNER_POSTOFFICE, baseActivity.getResources().getString(R.string.err_owner_postoffice));
            return;
        }
        if (CommonUtils.isNullString(ownerSurveyNumber)) {
            getMvpView().showOwnerDetailsFieldError(OwnerDetailsFragment.ERROR_TYPE_OWNER_SURVEY_NUMBER, baseActivity.getResources().getString(R.string.err_owner_survey_number));
            return;
        }
        savingOwnerDetails(firstName, lastName, email, mobile, landphone, gender, placeName, village, street, ownerHouse, ownerOccupation, ownerPostoffice, ownerSurveyNumber);
    }

    public void savingOwnerDetails(String firstName, String lastName, String email, String mobile, String landphone, String gender, String placeName, String village, String street, String ownerHouse, String ownerOccupation, String ownerPostoffice, String ownerSurveyNumber) {
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                ArrayList<BuildingAssets.OwnerDetails> ownerList = AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails();
                data.setOwnerDetails(ownerList == null ? new ArrayList<>() : new ArrayList<>(ownerList));
                BuildingAssets.OwnerDetails ownerDetails;
                if (selectedPosition == -1) {
                    ownerDetails = new BuildingAssets.OwnerDetails();
                    ownerDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                    if (propertyDetails != null) {
                        ownerDetails.setProperty(propertyDetails.getPk());
                    }
                } else {
                    ownerDetails = data.getOwnerDetails().get(selectedPosition);
                    ownerDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                ownerDetails.setFirstname(firstName);
                ownerDetails.setLastname(lastName);
                ownerDetails.setEmail(email);
                ownerDetails.setMobile(mobile);
                ownerDetails.setLandphone(landphone);
                ownerDetails.setGender(Integer.parseInt(gender));
                ownerDetails.setPlaceName(placeName);
                ownerDetails.setVillage(village);
                ownerDetails.setStreet(street);
                ownerDetails.setOwnerHouse(ownerHouse);
                ownerDetails.setOwnerOccup(Integer.parseInt(ownerOccupation));
                ownerDetails.setOwnerPost(ownerPostoffice);
                ownerDetails.setOwnerSurveyNo(ownerSurveyNumber);

                if (selectedPosition == -1) {
                    data.getOwnerDetails().add(ownerDetails);
                } else {
                    data.getOwnerDetails().set(selectedPosition, ownerDetails);
                }
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                ArrayList<BuildingAssets.OwnerDetails> ownerDetails;
                if (data.getOwnerDetails() == null) {
                    ownerDetails = new ArrayList<>();
                } else {
                    ownerDetails = new ArrayList<>(data.getOwnerDetails());
                }
                BuildingAssets.OwnerDetails owner;
                if (selectedPosition != -1 && ownerDetails.size() > selectedPosition) {
                    owner = ownerDetails.get(selectedPosition);
                    owner.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                } else {
                    owner = new BuildingAssets.OwnerDetails();
                    owner.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    if (data.getPropertyDetails() != null) {
                        owner.setProperty(data.getPropertyDetails().getPk());
                    }
                }
                owner.setFirstname(firstName);
                owner.setLastname(lastName);
                owner.setEmail(email);
                owner.setMobile(mobile);
                owner.setLandphone(landphone);
                owner.setGender(Integer.parseInt(gender));
                owner.setPlaceName(placeName);
                owner.setVillage(village);
                owner.setStreet(street);
                owner.setOwnerHouse(ownerHouse);
                owner.setOwnerOccup(Integer.parseInt(ownerOccupation));
                owner.setOwnerPost(ownerPostoffice);
                owner.setOwnerSurveyNo(ownerSurveyNumber);
                if (selectedPosition == -1) {
                    ownerDetails.add(owner);
                } else {
                    ownerDetails.set(selectedPosition, owner);
                }
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setOwnerDetails(ownerDetails);
                saveAssetDetails(newData);
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null
                && response.getData() != null && response.getData().getOwnerDetails() != null) {
            ArrayList<BuildingAssets.OwnerDetails> ownerDetails = AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails();
            if(ownerDetails==null){
                ownerDetails = new ArrayList<>();
            }
            if(selectedPosition == -1 && response.getData().getOwnerDetails().size() > 0){
                ownerDetails.add(response.getData().getOwnerDetails().get(0));
                AppCacheData.getOurInstance().getBuildingAssetData().setOwnerDetails(ownerDetails);
            }else if(ownerDetails.size() > selectedPosition  && response.getData().getOwnerDetails().size() > 0){
                ownerDetails.remove(selectedPosition);
                ownerDetails.add(selectedPosition,response.getData().getOwnerDetails().get(0));
                AppCacheData.getOurInstance().getBuildingAssetData().setOwnerDetails(ownerDetails);
            }
        }
        getMvpView().showToast(response.getMessage());
        getMvpView().hideProgressDialog();
        getMvpView().onAddOrUpdateSuccess();
    }
}
