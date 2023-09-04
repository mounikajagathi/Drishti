package in.ults.ipms.ui.buildingassets.otherproperty;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.databinding.FragmentPropertyOtherDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;

public class PropertyOtherDetailsFragment extends BaseFragment<FragmentPropertyOtherDetailsBinding> implements IPropertyOtherDetailsView {

    public static final String TAG = PropertyOtherDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_BUILDING_SUB_CATEGORY = 1;
    public static final int ERROR_TYPE_ELECTRICITY_CONNECTION = 2;
    public static final int ERROR_TYPE_CONSUMER_NUMBER = 3;
    public static final int ERROR_TYPE_WASTE_MANAGEMENT = 4;
    public static final int ERROR_TYPE_BATHROOM = 5;
    public static final int ERROR_TYPE_LATRINE = 6;
    public static final int ERROR_TYPE_PARKING_AREA = 7;
    public static final int ERROR_TYPE_RAINWATER_HARVESTING = 8;
    public static final int ERROR_TYPE_GAS_CONNECTION = 9;
    public static final int ERROR_TYPE_WATER_SOURCE = 10;
    public static final int ERROR_TYPE_WATER_CONNECTION = 11;
    public static final int ERROR_TYPE_WATER_CONNECTION_TYPE= 12;
    public static final int ERROR_TYPE_WELL = 13;
    public static final int ERROR_TYPE_WATER_IN_WELL = 14;
    public static final int ERROR_TYPE_SOLAR_PANEL = 15;
    public static final int ERROR_TYPE_DEATH_IN_1YEAR = 16;
    public static final int ERROR_TYPE_DEATH_COUNT = 17;
    public static final int ERROR_TYPE_DEATH_CAUSE = 18;
    public static final int ERROR_TYPE_RATION_CARD_COLOR = 19;
    public static final int ERROR_TYPE_RATION_CARD_NUMBER = 20;
    public static final int ERROR_TYPE_CATTLE = 21;
    public static final int ERROR_TYPE_CATTLE_DETAILS = 22;
    public static final int ERROR_TYPE_POULTRY = 23;
    public static final int ERROR_TYPE_POULTRY_DETAILS = 24;
    public static final int ERROR_TYPE_RELIGION = 25;
    public static final int ERROR_TYPE_CASTE = 26;
    public static final int ERROR_TYPE_GOVERNMENT_HEALTH_CARD = 27;
    public static final int ERROR_TYPE_AR_STATUS = 28;
    public static final int ERROR_TYPE_RAMP = 29;

    @Inject
    IPropertyOtherDetailsPresenter<IPropertyOtherDetailsView, IPropertyOtherDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BuildingSubTypes> buildingSubTypesAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.ElectricityConnection> electricityConnectionAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WasteManagement> wasteManagementAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Bathroom> bathroomAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Latrine> latrineAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.ParkingArea> parkingAreaAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.RainWaterHarvesting> rainWaterHarvestingAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.GasConnection> gasConnectionAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WaterSources> waterSourcesAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WaterConnection> waterConnectionAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WaterConnTypes> waterConnTypesAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Well> wellAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WaterInWell> waterInWellAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.SolarPanel> solarPanelAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.DeathIn1year> deathIn1yearAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.RationCardColors> rationCardColorsAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Cattle> cattleAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Poultry> poultryAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Religions> religionsAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Castes> castesAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.GovernmentHealthCard> governmentHealthCardAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.ArStatus> arStatusAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Ramp> rampAdapter;



    public static PropertyOtherDetailsFragment newInstance() {
        return new PropertyOtherDetailsFragment();
    }

    @Override
    protected  FragmentPropertyOtherDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentPropertyOtherDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.property_other_details_title);
    }

    @Override
    protected void initInjector() {getFragmentComponent().inject(this);

    }

    @Override
    protected void attachPresenter() {presenter.onAttach(this);

    }

    @Override
    protected void detachPresenter() {presenter.onDetach();

    }

    @Override
    protected void init(View view){
        getViewBinding().btnPropertyOtherDetails.setOnClickListener(v -> submitOnClick());
        buildingSubTypesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherBuildingSubCategory, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBuildingSubTypes());
        electricityConnectionAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherElectricity, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getElectricityConnection());
        wasteManagementAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherWasteManagement, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWasteManagement());
        bathroomAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherBathroom, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBathroom());
        latrineAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherLatrine, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getLatrine());
        parkingAreaAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherParkingArea, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getParkingArea());
        rainWaterHarvestingAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherRainwaterHarvesting, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getRainWaterHarvesting());
        gasConnectionAdapter  = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherGasConnection, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getGasConnection());
        waterSourcesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherWaterSource, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWaterSources());
        waterConnectionAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherWaterConnection, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWaterConnection());
        waterConnTypesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherWaterConnectionType, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWaterConnTypes());
        wellAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherWell, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWell());
        waterInWellAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherWaterInWell, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWaterInWell());
        solarPanelAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherSolarPanel, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getSolarPanel());
        deathIn1yearAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherDeathIn1Year, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getDeathIn1year());
        rationCardColorsAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherRationCardColor, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getRationCardColors());
        cattleAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherCattle, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getCattle());
        poultryAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherPoultry, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getPoultry());
        religionsAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherReligion, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getReligions());
        castesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherCaste, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getCastes());
        governmentHealthCardAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherGovernmentHealthCard, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getGovernmentHealthCard());
        arStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherArStatus, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getArStatus());
        rampAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyOtherRamp, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getRamp());

//        if(AppCacheData.getOurInstance().isAssetUpdate()){
            setEditData();
//        }
    }

    void submitOnClick() {
        String buildingSubCategory = (String) getViewBinding().srPropertyOtherBuildingSubCategory.getTag();
        String electricityConnection = (String) getViewBinding().srPropertyOtherElectricity.getTag();
        String consumerNumber = Objects.requireNonNull(getViewBinding().etPropertyOtherConsumerNumber.getText()).toString().trim();
        String wasteManagement = (String) getViewBinding().srPropertyOtherWasteManagement.getTag();
        String bathroom = (String) getViewBinding().srPropertyOtherBathroom.getTag();
        String latrine = (String) getViewBinding().srPropertyOtherLatrine.getTag();
        String parkingArea = (String) getViewBinding().srPropertyOtherParkingArea.getTag();
        String rainWaterHarvesting = (String) getViewBinding().srPropertyOtherRainwaterHarvesting.getTag();
        String gasConnection = (String) getViewBinding().srPropertyOtherGasConnection.getTag();
        String waterSource = (String) getViewBinding().srPropertyOtherWaterSource.getTag();
        String waterConnection = (String) getViewBinding().srPropertyOtherWaterConnection.getTag();
        String waterConnectionType = (String) getViewBinding().srPropertyOtherWaterConnectionType.getTag();
        String well = (String) getViewBinding().srPropertyOtherWell.getTag();
        String waterInWell = (String) getViewBinding().srPropertyOtherWaterInWell.getTag();
        String solarPanel = (String) getViewBinding().srPropertyOtherSolarPanel.getTag();
        String deathIn1Year = (String) getViewBinding().srPropertyOtherDeathIn1Year.getTag();
        String deathCount = Objects.requireNonNull(getViewBinding().etPropertyOtherDeathCount.getText()).toString().trim();
        String deathCause = Objects.requireNonNull(getViewBinding().etPropertyOtherDeathCause.getText()).toString().trim();
        String rationCardColor = (String) getViewBinding().srPropertyOtherRationCardColor.getTag();
        String rationCardNumber = Objects.requireNonNull(getViewBinding().etPropertyOtherRationCardNumber.getText()).toString().trim();
        String cattle = (String) getViewBinding().srPropertyOtherCattle.getTag();
        String cattleDetails = Objects.requireNonNull(getViewBinding().etPropertyOtherCattleDetails.getText()).toString().trim();
        String poultry = (String) getViewBinding().srPropertyOtherPoultry.getTag();
        String poultryDetails = Objects.requireNonNull(getViewBinding().etPropertyOtherPoultryDetails.getText()).toString().trim();
        String religion = (String) getViewBinding().srPropertyOtherReligion.getTag();
        String caste = (String) getViewBinding().srPropertyOtherCaste.getTag();
        String governmentHealthCard = (String) getViewBinding().srPropertyOtherGovernmentHealthCard.getTag();
        String arStatus = (String) getViewBinding().srPropertyOtherArStatus.getTag();
        String ramp = (String) getViewBinding().srPropertyOtherRamp.getTag();

        presenter.validateData(buildingSubCategory,electricityConnection,consumerNumber,wasteManagement,bathroom,latrine,parkingArea,rainWaterHarvesting,gasConnection,waterSource,waterConnection,waterConnectionType,well,waterInWell,solarPanel,deathIn1Year,deathCount,deathCause,rationCardColor,rationCardNumber,cattle,cattleDetails,poultry,poultryDetails,religion,caste,governmentHealthCard,arStatus,ramp);

    }

    @Override
    public void showPropertyOtherDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_BUILDING_SUB_CATEGORY:
                getViewBinding().layoutPropertyOtherBuildingSubCategory.setError(error);
                getViewBinding().layoutPropertyOtherBuildingSubCategory.requestFocus();
                break;
            case ERROR_TYPE_ELECTRICITY_CONNECTION:
                getViewBinding().layoutPropertyOtherElectricity.setError(error);
                getViewBinding().layoutPropertyOtherElectricity.requestFocus();
                break;
            case ERROR_TYPE_CONSUMER_NUMBER:
                getViewBinding().layoutPropertyOtherConsumerNumber.setError(error);
                getViewBinding().layoutPropertyOtherConsumerNumber.requestFocus();
                break;
            case ERROR_TYPE_WASTE_MANAGEMENT:
                getViewBinding().layoutPropertyOtherWasteManagement.setError(error);
                getViewBinding().layoutPropertyOtherWasteManagement.requestFocus();
                break;
            case ERROR_TYPE_BATHROOM:
                getViewBinding().layoutPropertyOtherBathroom.setError(error);
                getViewBinding().layoutPropertyOtherBathroom.requestFocus();
                break;
            case ERROR_TYPE_LATRINE:
                getViewBinding().layoutPropertyOtherLatrine.setError(error);
                getViewBinding().layoutPropertyOtherLatrine.requestFocus();
                break;
            case ERROR_TYPE_PARKING_AREA:
                getViewBinding().layoutPropertyOtherParkingArea.setError(error);
                getViewBinding().layoutPropertyOtherParkingArea.requestFocus();
                break;
            case ERROR_TYPE_RAINWATER_HARVESTING:
                getViewBinding().layoutPropertyOtherRainwaterHarvesting.setError(error);
                getViewBinding().layoutPropertyOtherRainwaterHarvesting.requestFocus();
                break;
            case ERROR_TYPE_GAS_CONNECTION:
                getViewBinding().layoutPropertyOtherGasConnection.setError(error);
                getViewBinding().layoutPropertyOtherGasConnection.requestFocus();
                break;
            case ERROR_TYPE_WATER_SOURCE:
                getViewBinding().layoutPropertyOtherWaterSource.setError(error);
                getViewBinding().layoutPropertyOtherWaterSource.requestFocus();
                break;
            case ERROR_TYPE_WATER_CONNECTION:
                getViewBinding().layoutPropertyOtherWaterConnection.setError(error);
                getViewBinding().layoutPropertyOtherWaterConnection.requestFocus();
                break;
            case ERROR_TYPE_WATER_CONNECTION_TYPE:
                getViewBinding().layoutPropertyOtherWaterConnectionType.setError(error);
                getViewBinding().layoutPropertyOtherWaterConnectionType.requestFocus();
                break;
            case ERROR_TYPE_WELL:
                getViewBinding().layoutPropertyOtherWell.setError(error);
                getViewBinding().layoutPropertyOtherWell.requestFocus();
                break;
            case ERROR_TYPE_WATER_IN_WELL:
                getViewBinding().layoutPropertyOtherWaterInWell.setError(error);
                getViewBinding().layoutPropertyOtherWaterInWell.requestFocus();
                break;
            case ERROR_TYPE_SOLAR_PANEL:
                getViewBinding().layoutPropertyOtherSolarPanel.setError(error);
                getViewBinding().layoutPropertyOtherSolarPanel.requestFocus();
                break;
            case ERROR_TYPE_DEATH_IN_1YEAR:
                getViewBinding().layoutPropertyOtherDeathIn1Year.setError(error);
                getViewBinding().layoutPropertyOtherDeathIn1Year.requestFocus();
                break;
            case ERROR_TYPE_DEATH_COUNT:
                getViewBinding().layoutPropertyOtherDeathCount.setError(error);
                getViewBinding().layoutPropertyOtherDeathCount.requestFocus();
                break;
            case ERROR_TYPE_DEATH_CAUSE:
                getViewBinding().layoutPropertyOtherDeathCause.setError(error);
                getViewBinding().layoutPropertyOtherDeathCause.requestFocus();
                break;
            case ERROR_TYPE_RATION_CARD_COLOR:
                getViewBinding().layoutPropertyOtherRationCardColor.setError(error);
                getViewBinding().layoutPropertyOtherRationCardColor.requestFocus();
                break;
            case ERROR_TYPE_RATION_CARD_NUMBER:
                getViewBinding().layoutPropertyOtherRationCardNumber.setError(error);
                getViewBinding().layoutPropertyOtherRationCardNumber.requestFocus();
                break;
            case ERROR_TYPE_CATTLE:
                getViewBinding().layoutPropertyOtherCattle.setError(error);
                getViewBinding().layoutPropertyOtherCattle.requestFocus();
                break;
            case ERROR_TYPE_CATTLE_DETAILS:
                getViewBinding().layoutPropertyOtherCattleDetails.setError(error);
                getViewBinding().layoutPropertyOtherCattleDetails.requestFocus();
                break;
            case ERROR_TYPE_POULTRY:
                getViewBinding().layoutPropertyOtherPoultry.setError(error);
                getViewBinding().layoutPropertyOtherPoultry.requestFocus();
                break;
            case ERROR_TYPE_POULTRY_DETAILS:
                getViewBinding().layoutPropertyOtherPoultryDetails.setError(error);
                getViewBinding().layoutPropertyOtherPoultryDetails.requestFocus();
                break;
            case ERROR_TYPE_RELIGION:
                getViewBinding().layoutPropertyOtherReligion.setError(error);
                getViewBinding().layoutPropertyOtherReligion.requestFocus();
                break;
            case ERROR_TYPE_CASTE:
                getViewBinding().layoutPropertyOtherCaste.setError(error);
                getViewBinding().layoutPropertyOtherCaste.requestFocus();
                break;
            case ERROR_TYPE_GOVERNMENT_HEALTH_CARD:
                getViewBinding().layoutPropertyOtherGovernmentHealthCard.setError(error);
                getViewBinding().layoutPropertyOtherGovernmentHealthCard.requestFocus();
                break;
            case ERROR_TYPE_AR_STATUS:
                getViewBinding().layoutPropertyOtherArStatus.setError(error);
                getViewBinding().layoutPropertyOtherArStatus.requestFocus();
                break;
            case ERROR_TYPE_RAMP:
                getViewBinding().layoutPropertyOtherRamp.setError(error);
                getViewBinding().layoutPropertyOtherRamp.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutPropertyOtherBuildingSubCategory.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherElectricity.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherConsumerNumber.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherWasteManagement.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherBathroom.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherLatrine.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherParkingArea.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherRainwaterHarvesting.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherGasConnection.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherWaterSource.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherWaterConnection.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherWaterConnectionType.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherWell.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherWaterInWell.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherSolarPanel.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherDeathIn1Year.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherDeathCount.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherDeathCause.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherRationCardColor.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherRationCardNumber.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherCattle.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherCattleDetails.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherPoultry.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherPoultryDetails.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherReligion.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherCaste.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherGovernmentHealthCard.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherArStatus.setErrorEnabled(false);
        getViewBinding().layoutPropertyOtherRamp.setErrorEnabled(false);
    }

    public void setEditData() {
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getPropertyOtherDetails()!=null) {
            BuildingAssets.PropertyOtherDetails propertyOtherDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyOtherDetails();
            buildingSubTypesAdapter.setContent(String.valueOf(propertyOtherDetails.getBldgSubType()));
            electricityConnectionAdapter.setContent(propertyOtherDetails.getElectricityConn());
            getViewBinding().etPropertyOtherConsumerNumber.setText(propertyOtherDetails.getConsumerNo());
            wasteManagementAdapter.setContent(propertyOtherDetails.getWasteMgmnt());
            bathroomAdapter.setContent(propertyOtherDetails.getBathroom());
            latrineAdapter.setContent(propertyOtherDetails.getLatrine());
            parkingAreaAdapter.setContent(propertyOtherDetails.getParkingArea());
            rainWaterHarvestingAdapter.setContent(propertyOtherDetails.getRainWaterHv());
            gasConnectionAdapter.setContent(propertyOtherDetails.getGasConn());
            waterSourcesAdapter.setContent(propertyOtherDetails.getWaterSource());
            waterConnectionAdapter.setContent(propertyOtherDetails.getWaterConn());
            waterConnTypesAdapter.setContent(String.valueOf(propertyOtherDetails.getWaterConnType()));
            wellAdapter.setContent(propertyOtherDetails.getWell());
            waterInWellAdapter.setContent(String.valueOf(propertyOtherDetails.getWaterInWell()));
            solarPanelAdapter.setContent(propertyOtherDetails.getSolarPanel());
            Log.e("qq", propertyOtherDetails.getSolarPanel()+"     ");
            Log.e("qq", propertyOtherDetails.getDeathInOneyear()+"     ");

            deathIn1yearAdapter.setContent(propertyOtherDetails.getDeathInOneyear());
            getViewBinding().etPropertyOtherDeathCount.setText(String.valueOf(propertyOtherDetails.getDeathCount()));
            getViewBinding().etPropertyOtherDeathCause.setText(propertyOtherDetails.getDeathCouse());
            rationCardColorsAdapter.setContent(String.valueOf(propertyOtherDetails.getRationCardColor()));
            getViewBinding().etPropertyOtherRationCardNumber.setText(propertyOtherDetails.getRationCardNo());
            cattleAdapter.setContent(propertyOtherDetails.getCattles());
            getViewBinding().etPropertyOtherCattleDetails.setText(propertyOtherDetails.getCattlesDetails());
            poultryAdapter.setContent(propertyOtherDetails.getPoultry());
            getViewBinding().etPropertyOtherPoultryDetails.setText(propertyOtherDetails.getPoultryDetails());
            religionsAdapter.setContent(propertyOtherDetails.getReligion());
            castesAdapter.setContent(propertyOtherDetails.getCaste());
            governmentHealthCardAdapter.setContent(propertyOtherDetails.getGovHealthCard());
            arStatusAdapter.setContent(propertyOtherDetails.getArStatus());
            rampAdapter.setContent(propertyOtherDetails.getRamp());
        }
    }
    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false,false);
    }
}
