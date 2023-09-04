package in.ults.ipms.ui.buildingassets.otherproperty;

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

public class PropertyOtherDetailsPresenter <V extends IPropertyOtherDetailsView, I extends IPropertyOtherDetailsInteractor>
        extends BasePresenter<V, I> implements IPropertyOtherDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public PropertyOtherDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String buildingSubCategory,String electricityConnection,String consumerNumber,String wasteManagement,String bathroom,String latrine,String parkingArea,String rainWaterHarvesting,String gasConnection,String waterSource,String waterConnection,String waterConnectionType,String well,String waterInWell,String solarPanel,String deathIn1Year,String deathCount,String deathCause,String rationCardColor,String rationCardNumber,String cattle,String cattleDetails,String poultry,String poultryDetails,String religion,String caste,String governmentHealthCard,String arStatus,String ramp) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(buildingSubCategory)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_BUILDING_SUB_CATEGORY,baseActivity.getResources().getString(R.string.err_sub_category));
            return;
        }
        if (CommonUtils.isNullString(electricityConnection)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_ELECTRICITY_CONNECTION,baseActivity.getResources().getString(R.string.err_electrcity));
            return;
        }
        if (CommonUtils.isNullString(consumerNumber)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_CONSUMER_NUMBER,baseActivity.getResources().getString(R.string.err_consumer_number));
            return;
        }
        if (CommonUtils.isNullString(wasteManagement)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_WASTE_MANAGEMENT,baseActivity.getResources().getString(R.string.err_waste_management));
            return;
        }
        if (CommonUtils.isNullString(bathroom)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_BATHROOM,baseActivity.getResources().getString(R.string.err_bathroom));
            return;
        }
        if (CommonUtils.isNullString(latrine)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_LATRINE,baseActivity.getResources().getString(R.string.err_latrine));
            return;
        }
        if (CommonUtils.isNullString(parkingArea)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_PARKING_AREA,baseActivity.getResources().getString(R.string.err_parking_area));
            return;
        }
        if (CommonUtils.isNullString(rainWaterHarvesting)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_RAINWATER_HARVESTING,baseActivity.getResources().getString(R.string.err_rainwater_harvesting));
            return;
        }
        if (CommonUtils.isNullString(gasConnection)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_GAS_CONNECTION,baseActivity.getResources().getString(R.string.err_gas_connection));
            return;
        }
        if (CommonUtils.isNullString(waterSource)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_WATER_SOURCE,baseActivity.getResources().getString(R.string.err_water_source));
            return;
        }
        if (CommonUtils.isNullString(waterConnection)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_WATER_CONNECTION,baseActivity.getResources().getString(R.string.err_water_connection));
            return;
        }
        if (CommonUtils.isNullString(waterConnectionType)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_WATER_CONNECTION_TYPE,baseActivity.getResources().getString(R.string.err_water_connection_type));
            return;
        }
        if (CommonUtils.isNullString(well)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_WELL,baseActivity.getResources().getString(R.string.err_well));
            return;
        }
        if (CommonUtils.isNullString(waterInWell)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_WATER_IN_WELL,baseActivity.getResources().getString(R.string.err_water_in_well));
            return;
        }
        if (CommonUtils.isNullString(solarPanel)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_SOLAR_PANEL,baseActivity.getResources().getString(R.string.err_solar_panel));
            return;
        }
        if (CommonUtils.isNullString(deathIn1Year)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_DEATH_IN_1YEAR,baseActivity.getResources().getString(R.string.err_death_in_1year));
            return;
        }
        if (CommonUtils.isNullString(deathCount)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_DEATH_COUNT,baseActivity.getResources().getString(R.string.err_death_count));
            return;
        }
        if (CommonUtils.isNullString(deathCause)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_DEATH_CAUSE,baseActivity.getResources().getString(R.string.err_death_cause));
            return;
        }
        if (CommonUtils.isNullString(rationCardColor)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_RATION_CARD_COLOR,baseActivity.getResources().getString(R.string.err_ration_card_color));
            return;
        }
        if (CommonUtils.isNullString(rationCardNumber)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_RATION_CARD_NUMBER,baseActivity.getResources().getString(R.string.err_ration_card_number));
            return;
        }
        if (CommonUtils.isNullString(cattle)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_CATTLE,baseActivity.getResources().getString(R.string.err_cattle));
            return;
        }
        if (CommonUtils.isNullString(cattleDetails)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_CATTLE_DETAILS,baseActivity.getResources().getString(R.string.err_cattle_details));
            return;
        }
        if (CommonUtils.isNullString(poultry)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_POULTRY,baseActivity.getResources().getString(R.string.err_poultry));
            return;
        }
        if (CommonUtils.isNullString(poultryDetails)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_POULTRY_DETAILS,baseActivity.getResources().getString(R.string.err_poultry_details));
            return;
        }
        if (CommonUtils.isNullString(religion)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_RELIGION,baseActivity.getResources().getString(R.string.err_religion));
            return;
        }
        if (CommonUtils.isNullString(caste)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_CASTE,baseActivity.getResources().getString(R.string.err_caste));
            return;
        }
        if (CommonUtils.isNullString(governmentHealthCard)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_GOVERNMENT_HEALTH_CARD,baseActivity.getResources().getString(R.string.err_government_health_card));
            return;
        }
        if (CommonUtils.isNullString(arStatus)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_AR_STATUS,baseActivity.getResources().getString(R.string.err_ar_status));
            return;
        }
        if (CommonUtils.isNullString(ramp)) {
            getMvpView().showPropertyOtherDetailsFieldError(PropertyOtherDetailsFragment.ERROR_TYPE_RAMP,baseActivity.getResources().getString(R.string.err_ramp));
            return;
        }
        savingPropertyOtherDetails(buildingSubCategory,electricityConnection,consumerNumber,wasteManagement,bathroom,latrine,parkingArea,rainWaterHarvesting,gasConnection,waterSource,waterConnection,waterConnectionType,well,waterInWell,solarPanel,deathIn1Year,deathCount,deathCause,rationCardColor,rationCardNumber,cattle,cattleDetails,poultry,poultryDetails,religion,caste,governmentHealthCard,arStatus,ramp);
    }

    public void savingPropertyOtherDetails(String buildingSubCategory,String electricityConnection,String consumerNumber,String wasteManagement,String bathroom,String latrine,String parkingArea,String rainWaterHarvesting,String gasConnection,String waterSource,
                                           String waterConnection,String waterConnectionType,String well,String waterInWell,String solarPanel,String deathIn1Year,String deathCount,String deathCause,String rationCardColor,String rationCardNumber,
                                           String cattle,String cattleDetails,String poultry,String poultryDetails,String religion,String caste,String governmentHealthCard,String arStatus,String ramp){

        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                BuildingAssets.PropertyOtherDetails propertyOtherDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyOtherDetails();
                data.setPropertyOtherDetails(propertyOtherDetails == null ? new BuildingAssets.PropertyOtherDetails() : propertyOtherDetails);
                data.getPropertyOtherDetails().setBldgSubType(Integer.parseInt(buildingSubCategory));
                data.getPropertyOtherDetails().setElectricityConn(electricityConnection);
                data.getPropertyOtherDetails().setConsumerNo(consumerNumber);
                data.getPropertyOtherDetails().setWasteMgmnt(wasteManagement);
                data.getPropertyOtherDetails().setBathroom(bathroom);
                data.getPropertyOtherDetails().setLatrine(latrine);
                data.getPropertyOtherDetails().setParkingArea(parkingArea);
                data.getPropertyOtherDetails().setRainWaterHv(rainWaterHarvesting);
                data.getPropertyOtherDetails().setGasConn(gasConnection);
                data.getPropertyOtherDetails().setWaterSource(waterSource);
                data.getPropertyOtherDetails().setWaterConn(waterConnection);
                data.getPropertyOtherDetails().setWaterConnType(Integer.parseInt(waterConnectionType));
                data.getPropertyOtherDetails().setWell(well);
                data.getPropertyOtherDetails().setWaterInWell(Integer.parseInt(waterInWell));
                data.getPropertyOtherDetails().setSolarPanel(solarPanel);
                data.getPropertyOtherDetails().setDeathInOneyear(deathIn1Year);
                data.getPropertyOtherDetails().setDeathCount(Integer.parseInt(deathCount));
                data.getPropertyOtherDetails().setDeathCouse(deathCause);
                data.getPropertyOtherDetails().setRationCardColor(Integer.parseInt(rationCardColor));
                data.getPropertyOtherDetails().setRationCardNo(rationCardNumber);
                data.getPropertyOtherDetails().setCattles(cattle);
                data.getPropertyOtherDetails().setCattlesDetails(cattleDetails);
                data.getPropertyOtherDetails().setPoultry(poultry);
                data.getPropertyOtherDetails().setPoultryDetails(poultryDetails);
                data.getPropertyOtherDetails().setReligion(religion);
                data.getPropertyOtherDetails().setCaste(caste);
                data.getPropertyOtherDetails().setGovHealthCard(governmentHealthCard);
                data.getPropertyOtherDetails().setArStatus(arStatus);
                data.getPropertyOtherDetails().setRamp(ramp);
                data.getPropertyOtherDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                BuildingAssets.PropertyOtherDetails propertyOtherDetails = data.getPropertyOtherDetails();
                if (propertyOtherDetails == null) {
                    propertyOtherDetails = new BuildingAssets.PropertyOtherDetails();
                    if (data.getPropertyDetails() != null) {
                        propertyOtherDetails.setProperty(data.getPropertyDetails().getPk());
                    }
                    propertyOtherDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                } else {
                    propertyOtherDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                propertyOtherDetails.setBldgSubType(Integer.parseInt(buildingSubCategory));
                propertyOtherDetails.setElectricityConn(electricityConnection);
                propertyOtherDetails.setConsumerNo(consumerNumber);
                propertyOtherDetails.setWasteMgmnt(wasteManagement);
                propertyOtherDetails.setBathroom(bathroom);
                propertyOtherDetails.setLatrine(latrine);
                propertyOtherDetails.setParkingArea(parkingArea);
                propertyOtherDetails.setRainWaterHv(rainWaterHarvesting);
                propertyOtherDetails.setGasConn(gasConnection);
                propertyOtherDetails.setWaterSource(waterSource);
                propertyOtherDetails.setWaterConn(waterConnection);
                propertyOtherDetails.setWaterConnType(Integer.parseInt(waterConnectionType));
                propertyOtherDetails.setWell(well);
                propertyOtherDetails.setWaterInWell(Integer.parseInt(waterInWell));
                propertyOtherDetails.setSolarPanel(solarPanel);
                propertyOtherDetails.setDeathInOneyear(deathIn1Year);
                propertyOtherDetails.setDeathCount(Integer.parseInt(deathCount));
                propertyOtherDetails.setDeathCouse(deathCause);
                propertyOtherDetails.setRationCardColor(Integer.parseInt(rationCardColor));
                propertyOtherDetails.setRationCardNo(rationCardNumber);
                propertyOtherDetails.setCattles(cattle);
                propertyOtherDetails.setCattlesDetails(cattleDetails);
                propertyOtherDetails.setPoultry(poultry);
                propertyOtherDetails.setPoultryDetails(poultryDetails);
                propertyOtherDetails.setReligion(religion);
                propertyOtherDetails.setCaste(caste);
                propertyOtherDetails.setGovHealthCard(governmentHealthCard);
                propertyOtherDetails.setArStatus(arStatus);
                propertyOtherDetails.setRamp(ramp);
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setPropertyOtherDetails(propertyOtherDetails);
                saveAssetDetails(newData);
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null && response.getData() != null) {
            AppCacheData.getOurInstance().getBuildingAssetData().setPropertyOtherDetails(response.getData().getPropertyOtherDetails());
        }
        getMvpView().onAddOrUpdateSuccess();
    }
}
