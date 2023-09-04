package in.ults.ipms.ui.buildingassets.property;

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

/**
 * Created by AmalB on 6/22/2021.
 */

public class PropertyDetailsPresenter<V extends IPropertyDetailsView, I extends IPropertyDetailsInteractor> extends BasePresenter<V, I> implements IPropertyDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public PropertyDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void getBuildingNameSpinnerData() {
        if (AppCacheData.getOurInstance().getBuildingNameSpinnerData() == null) {
            getCompositeDisposable()
                    .add(getInteractor()
                            .getBuildingNameSpinnerData()
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .doOnSubscribe(disposable -> showProgressDialog())
                            .doFinally(this::hideProgressDialog)
                            .doOnError(throwable -> {
                                showApiCallErrors(throwable);
                                hideProgressDialog();
                            })
                            .doOnNext(response -> {
                                if (!isViewAttached()) {
                                    return;
                                }
                                if (isResponseSuccess(response)) {
                                    if (response.getData() != null) {
                                        AppCacheData.getOurInstance().setBuildingNameSpinnerData(response.getData().getBuildingName());
                                        getMvpView().onBuildingNameLoaded(response.getData().getBuildingName());
                                    }

                                }
                            }).subscribe());
        } else {
            getMvpView().onBuildingNameLoaded(AppCacheData.getOurInstance().getBuildingNameSpinnerData());
        }
    }

    @Override
    public void validateData(String buildingName, String buildingCondition, String doorStatus, String whichFloor, String buildingStatus, String buildingUsage, String newPropertyId, String oldPropertyId, String yearOfConstruction, String numberOfYears, String anyInstitute, String centralisedAc, String normalAc, String anyStructuralChange, String higherFloorTypeGt250Sqmts, String anyOtherLowerFloorType, String photo1, String photo2, String plan) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(buildingName)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_BUILDING_NAME, baseActivity.getResources().getString(R.string.err_property_building_name));
            return;
        }
        if (CommonUtils.isNullString(buildingCondition)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_BUILDING_CONDITION, baseActivity.getResources().getString(R.string.err_building_condition));
            return;
        }
        if (CommonUtils.isNullString(doorStatus)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_DOOR_STATUS, baseActivity.getResources().getString(R.string.err_door_status));
            return;
        }
        if (CommonUtils.isNullString(whichFloor)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_WHICH_FLOOR, baseActivity.getResources().getString(R.string.err_which_floor));
            return;
        }
        if (CommonUtils.isNullString(buildingStatus)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_BUILDING_STATUS, baseActivity.getResources().getString(R.string.err_building_status));
            return;
        }
        if (CommonUtils.isNullString(buildingUsage)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_BUILDING_USAGE, baseActivity.getResources().getString(R.string.err_building_usage));
            return;
        }
        if (CommonUtils.isNullString(newPropertyId)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_NEW_PRO_ID, baseActivity.getResources().getString(R.string.err_new_pro_id));
            return;
        }
        if (CommonUtils.isNullString(oldPropertyId)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_OLD_PRO_ID, baseActivity.getResources().getString(R.string.err_old_pro_id));
            return;
        }
        if (CommonUtils.isNullString(yearOfConstruction)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_YEAR_OF_CONSTRUCTION, baseActivity.getResources().getString(R.string.err_year_of_construction));
            return;
        }
        if (CommonUtils.isNullString(numberOfYears)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_NUMBER_YEARS, baseActivity.getResources().getString(R.string.err_number_of_years));
            return;
        }
        if (CommonUtils.isNullString(anyInstitute)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_ANY_INSTITUTE, baseActivity.getResources().getString(R.string.err_any_institute));
            return;
        }
        if (CommonUtils.isNullString(centralisedAc)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_CENTRALISED_AC, baseActivity.getResources().getString(R.string.err_centralised_ac));
            return;
        }
        if (CommonUtils.isNullString(normalAc)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_NORMAL_AC, baseActivity.getResources().getString(R.string.err_normal_ac));
            return;
        }
        if (CommonUtils.isNullString(anyStructuralChange)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_ANY_STRUCTURAL_CHANGE, baseActivity.getResources().getString(R.string.err_any_structural_change));
            return;
        }
        if (CommonUtils.isNullString(higherFloorTypeGt250Sqmts)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_HIGHER_FLOOR_GT_250SQMTS, baseActivity.getResources().getString(R.string.err_higher_floor_gt_250sqmts));
            return;
        }
        if (CommonUtils.isNullString(anyOtherLowerFloorType)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_ANY_OTHER_LOWER_FLOOR_TYPE, baseActivity.getResources().getString(R.string.err_any_other_lower_floor_type));
            return;
        }
        if (CommonUtils.isNullString(photo1)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_PHOTO1, baseActivity.getResources().getString(R.string.err_photo1));
            return;
        }
        if (CommonUtils.isNullString(photo2)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_PHOTO2, baseActivity.getResources().getString(R.string.err_photo2));
            return;
        }
        if (CommonUtils.isNullString(plan)) {
            getMvpView().showPropertyDetailsFieldError(PropertyDetailsFragment.ERROR_TYPE_PLAN, baseActivity.getResources().getString(R.string.err_plan));
            return;
        }

        savePropertyDetails(buildingName, buildingCondition, doorStatus, whichFloor, buildingStatus, buildingUsage, newPropertyId, oldPropertyId, yearOfConstruction, numberOfYears, anyInstitute, centralisedAc, normalAc, anyStructuralChange, higherFloorTypeGt250Sqmts, anyOtherLowerFloorType, photo1, photo2, plan);

    }


    private void savePropertyDetails(String buildingName, String buildingCondition, String doorStatus, String whichFloor, String buildingStatus, String buildingUsage, String newPropertyId,
                                     String oldPropertyId, String yearOfConstruction, String numberOfYears, String anyInstitute, String centralisedAc, String normalAc, String anyStructuralChange,
                                     String higherFloorTypeGt250Sqmts, String anyOtherLowerFloorType, String photo1, String photo2, String plan) {
        if (AppCacheData.getOurInstance().isAssetUpdate() || AppCacheData.getOurInstance().isBuildingAssetPropertyAdded()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                data.setPropertyDetails(propertyDetails == null ? new BuildingAssets.PropertyDetails() : propertyDetails);
                data.getPropertyDetails().setBuilding(Long.parseLong(buildingName));
                data.getPropertyDetails().setBldgCondition(Integer.parseInt(buildingCondition));
                data.getPropertyDetails().setDoorStatus(Integer.parseInt(doorStatus));
                data.getPropertyDetails().setWhichFloor(whichFloor);
                data.getPropertyDetails().setBldgStatus(Integer.parseInt(buildingStatus));
                data.getPropertyDetails().setBldgUsage(Integer.parseInt(buildingUsage));
                data.getPropertyDetails().setNewProId(newPropertyId);
                data.getPropertyDetails().setOldProId(oldPropertyId);
                data.getPropertyDetails().setYearConst(Integer.parseInt(yearOfConstruction));
                data.getPropertyDetails().setNoOfYears(Integer.parseInt(numberOfYears));
                data.getPropertyDetails().setAnyInstitute(anyInstitute);
                data.getPropertyDetails().setCentralAc(centralisedAc);
                data.getPropertyDetails().setNormalAc(normalAc);
                data.getPropertyDetails().setStructuralChange(anyStructuralChange);
                data.getPropertyDetails().setHigherFloorGt(higherFloorTypeGt250Sqmts);
                data.getPropertyDetails().setOtherFlrType(anyOtherLowerFloorType);
                data.getPropertyDetails().setPhoto1(photo1);
                data.getPropertyDetails().setPhoto2(photo2);
                data.getPropertyDetails().setPlan(plan);
                data.getPropertyDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            BuildingAssets.PropertyDetails propertyDetails = new BuildingAssets.PropertyDetails();
            propertyDetails.setBuilding(Long.parseLong(buildingName));
            propertyDetails.setBldgCondition(Integer.parseInt(buildingCondition));
            propertyDetails.setDoorStatus(Integer.parseInt(doorStatus));
            propertyDetails.setWhichFloor(whichFloor);
            propertyDetails.setBldgStatus(Integer.parseInt(buildingStatus));
            propertyDetails.setBldgUsage(Integer.parseInt(buildingUsage));
            propertyDetails.setNewProId(newPropertyId);
            propertyDetails.setOldProId(oldPropertyId);
            propertyDetails.setYearConst(Integer.parseInt(yearOfConstruction));
            propertyDetails.setNoOfYears(Integer.parseInt(numberOfYears));
            propertyDetails.setAnyInstitute(anyInstitute);
            propertyDetails.setCentralAc(centralisedAc);
            propertyDetails.setNormalAc(normalAc);
            propertyDetails.setStructuralChange(anyStructuralChange);
            propertyDetails.setHigherFloorGt(higherFloorTypeGt250Sqmts);
            propertyDetails.setOtherFlrType(anyOtherLowerFloorType);
            propertyDetails.setPhoto1(photo1);
            propertyDetails.setPhoto2(photo2);
            propertyDetails.setPlan(plan);
            propertyDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
            AppCacheData.getOurInstance().setNewProperty(propertyDetails);
            getMvpView().goToNext();
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
