package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class BuildingAssetSpinnerResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private BuildingAssetSpinnerResponse.Data data;

    public Data getData() {
        return data;
    }

    //Inner Data Class
    public static class Data {

        @Expose
        @SerializedName("blood_groups")
        private ArrayList<BloodGroups> bloodGroups;
        @Expose
        @SerializedName("building_conditions")
        private ArrayList<BuildingConditions> buildingConditions;
        @Expose
        @SerializedName("building_status")
        private ArrayList<BuildingStatus> buildingStatus;
        @Expose
        @SerializedName("building_sub_types")
        private ArrayList<BuildingSubTypes> buildingSubTypes;
        @Expose
        @SerializedName("building_under")
        private ArrayList<BuildingUnder> buildingUnder;
        @Expose
        @SerializedName("building_usages")
        private ArrayList<BuildingUsages> buildingUsages;
        @Expose
        @SerializedName("building_zones")
        private ArrayList<BuildingZones> buildingZones;
        @Expose
        @SerializedName("castes")
        private ArrayList<Castes> castes;
        @Expose
        @SerializedName("ration_card_colors")
        private ArrayList<RationCardColors> rationCardColors;
        @Expose
        @SerializedName("disability_types")
        private ArrayList<DisabilityTypes> disabilityTypes;
        @Expose
        @SerializedName("disease_types")
        private ArrayList<DiseaseTypes> diseaseTypes;
        @Expose
        @SerializedName("door_status")
        private ArrayList<DoorStatus> doorStatus;
        @Expose
        @SerializedName("educations")
        private ArrayList<Educations> educations;
        @Expose
        @SerializedName("education_categories")
        private ArrayList<EducationCategories> educationCategories;
        @Expose
        @SerializedName("floor_area_categories")
        private ArrayList<FloorAreaCategories> floorAreaCategories;
        @Expose
        @SerializedName("floor_categories")
        private ArrayList<FloorTypes> floorTypes;
        @Expose
        @SerializedName("floor_numbers")
        private ArrayList<FloorNumbers> floorNumbers;
        @Expose
        @SerializedName("jobs")
        private ArrayList<Jobs> jobs;
        @Expose
        @SerializedName("marital_status")
        private ArrayList<MaritalStatus> maritalStatus;
        @Expose
        @SerializedName("pension_types")
        private ArrayList<PensionTypes> pensionTypes;
        @Expose
        @SerializedName("religions")
        private ArrayList<Religions> religions;
        @Expose
        @SerializedName("road_directions")
        private ArrayList<RoadDirections> roadDirections;
        @Expose
        @SerializedName("roof_categories")
        private ArrayList<RoofCategories> roofCategories;
        @Expose
        @SerializedName("water_conn_types")
        private ArrayList<WaterConnTypes> waterConnTypes;
        @Expose
        @SerializedName("water_in_well")
        private ArrayList<WaterInWell> waterInWell;
        @Expose
        @SerializedName("water_sources")
        private ArrayList<WaterSources> waterSources;
        @Expose
        @SerializedName("ward_nos")
        private ArrayList<WardNos> wardNos;
        @Expose
        @SerializedName("any_institute")
        private ArrayList<AnyInstitute> anyInstitute;
        @Expose
        @SerializedName("central_ac")
        private ArrayList<CentralisedAc> centralisedAc;
        @Expose
        @SerializedName("normal_ac")
        private ArrayList<NormalAc> normalAc;
        @Expose
        @SerializedName("structural_change")
        private ArrayList<AnyStructuralChange> anyStructuralChange;
        @Expose
        @SerializedName("higher_floor_gt")
        private ArrayList<HigherFloorGrt250Sqm> higherFloorGrt250Sqm;
        @Expose
        @SerializedName("other_flr_type")
        private ArrayList<AnyOtherLowerFloorType> AnyOtherLowerFloorType;
        @Expose
        @SerializedName("postoffices")
        private ArrayList<Postoffices> postoffices;
        @Expose
        @SerializedName("road_types")
        private ArrayList<RoadTypes> roadTypes;
        @Expose
        @SerializedName("road_widths")
        private ArrayList<RoadWidths> roadWidths;
        @Expose
        @SerializedName("genders")
        private ArrayList<Genders> genders;
        @Expose
        @SerializedName("license")
        private ArrayList<EstLicense> estLicense;
        @Expose
        @SerializedName("gst_status")
        private ArrayList<EstGstStatus> estGstStatus;
        @Expose
        @SerializedName("electricity_conn")
        private ArrayList<ElectricityConnection> electricityConnection;
        @Expose
        @SerializedName("waste_mgmnt")
        private ArrayList<WasteManagement> wasteManagement;
        @Expose
        @SerializedName("bathroom")
        private ArrayList<Bathroom> bathroom;
        @Expose
        @SerializedName("latrine")
        private ArrayList<Latrine> latrine;
        @Expose
        @SerializedName("parking_area")
        private ArrayList<ParkingArea> parkingArea;
        @Expose
        @SerializedName("rain_water_hv")
        private ArrayList<RainWaterHarvesting> rainWaterHarvesting;
        @Expose
        @SerializedName("gas_conn")
        private ArrayList<GasConnection> gasConnection;
        @Expose
        @SerializedName("water_conn")
        private ArrayList<WaterConnection> waterConnection;
        @Expose
        @SerializedName("well")
        private ArrayList<Well> well;
        @Expose
        @SerializedName("solar_panel")
        private ArrayList<SolarPanel> solarPanel;
        @Expose
        @SerializedName("death_in_1year")
        private ArrayList<DeathIn1year> deathIn1year;
        @Expose
        @SerializedName("cattles")
        private ArrayList<Cattle> cattle;
        @Expose
        @SerializedName("poultry")
        private ArrayList<Poultry> poultry;
        @Expose
        @SerializedName("gov_health_card")
        private ArrayList<GovernmentHealthCard> governmentHealthCard;
        @Expose
        @SerializedName("ar_status")
        private ArrayList<ArStatus> arStatus;
        @Expose
        @SerializedName("ramp")
        private ArrayList<Ramp> ramp;
        @Expose
        @SerializedName("ownership_change")
        private ArrayList<OwnershipChange> ownershipChange;
        @Expose
        @SerializedName("is_coolie_wage")
        private ArrayList<IsCoolieWage> isCoolieWage;
        @Expose
        @SerializedName("is_alive")
        private ArrayList<IsAlive> isAlive;
        @Expose
        @SerializedName("is_qualified")
        private ArrayList<IsQualified> isQualified;
        @Expose
        @SerializedName("bank_account")
        private ArrayList<BankAccount> bankAccount;
        @Expose
        @SerializedName("nri_or_nrk")
        private ArrayList<NriOrNrk> nriOrNrk;

        @Expose
        @SerializedName("bank_types")
        private ArrayList<BankType> bankTypes;


        public ArrayList<BloodGroups> getBloodGroups() {
            return bloodGroups;
        }

        public ArrayList<BuildingConditions> getBuildingConditions() {
            return buildingConditions;
        }

        public ArrayList<BuildingStatus> getBuildingStatus() {
            return buildingStatus;
        }

        public ArrayList<BuildingSubTypes> getBuildingSubTypes() {
            return buildingSubTypes;
        }

        public List<BuildingUnder> getBuildingUnder() {
            return buildingUnder;
        }

        public ArrayList<BuildingUsages> getBuildingUsages() {
            return buildingUsages;
        }

        public ArrayList<BuildingZones> getBuildingZones() {
            return buildingZones;
        }

        public ArrayList<Castes> getCastes() {
            return castes;
        }

        public ArrayList<RationCardColors> getRationCardColors() {
            return rationCardColors;
        }

        public ArrayList<DisabilityTypes> getDisabilityTypes() {
            return disabilityTypes;
        }

        public ArrayList<DiseaseTypes> getDiseaseTypes() {
            return diseaseTypes;
        }

        public ArrayList<DoorStatus> getDoorStatus() {
            return doorStatus;
        }

        public ArrayList<Educations> getEducations() {
            return educations;
        }

        public ArrayList<EducationCategories> getEducationCategories() {
            return educationCategories;
        }

        public ArrayList<FloorAreaCategories> getFloorAreaCategories() {
            return floorAreaCategories;
        }

        public ArrayList<FloorNumbers> getFloorNumbers() {
            return floorNumbers;
        }

        public ArrayList<Jobs> getJobs() {
            return jobs;
        }

        public ArrayList<MaritalStatus> getMaritalStatus() {
            return maritalStatus;
        }

        public ArrayList<PensionTypes> getPensionTypes() {
            return pensionTypes;
        }

        public ArrayList<Religions> getReligions() {
            return religions;
        }

        public ArrayList<RoadDirections> getRoadDirections() {
            return roadDirections;
        }

        public ArrayList<RoofCategories> getRoofCategories() {
            return roofCategories;
        }

        public ArrayList<WaterConnTypes> getWaterConnTypes() {
            return waterConnTypes;
        }

        public ArrayList<WaterInWell> getWaterInWell() {
            return waterInWell;
        }

        public ArrayList<WaterSources> getWaterSources() {
            return waterSources;
        }

        public ArrayList<WardNos> getWardNos() {
            return wardNos;
        }

        public ArrayList<AnyInstitute> getAnyInstitute() {
            return anyInstitute;
        }

        public ArrayList<CentralisedAc> getCentralisedAc() {
            return centralisedAc;
        }

        public ArrayList<NormalAc> getNormalAc() {
            return normalAc;
        }

        public ArrayList<AnyStructuralChange> getAnyStructuralChange() {
            return anyStructuralChange;
        }

        public ArrayList<HigherFloorGrt250Sqm> getHigherFloorGrt250Sqm() {
            return higherFloorGrt250Sqm;
        }

        public ArrayList<AnyOtherLowerFloorType> getAnyOtherLowerFloorType() {
            return AnyOtherLowerFloorType;
        }

        public ArrayList<Postoffices> getPostoffices() {
            return postoffices;
        }

        public ArrayList<RoadTypes> getRoadTypes() {
            return roadTypes;
        }

        public ArrayList<RoadWidths> getRoadWidths() {
            return roadWidths;
        }

        public ArrayList<Genders> getGenders() {
            return genders;
        }

        public ArrayList<EstLicense> getEstLicense() {
            return estLicense;
        }

        public ArrayList<EstGstStatus> getEstGstStatus() {
            return estGstStatus;
        }

        public ArrayList<ElectricityConnection> getElectricityConnection() {
            return electricityConnection;
        }

        public ArrayList<WasteManagement> getWasteManagement() {
            return wasteManagement;
        }

        public ArrayList<Bathroom> getBathroom() {
            return bathroom;
        }

        public ArrayList<Latrine> getLatrine() {
            return latrine;
        }

        public ArrayList<ParkingArea> getParkingArea() {
            return parkingArea;
        }

        public ArrayList<RainWaterHarvesting> getRainWaterHarvesting() {
            return rainWaterHarvesting;
        }

        public ArrayList<GasConnection> getGasConnection() {
            return gasConnection;
        }

        public ArrayList<WaterConnection> getWaterConnection() {
            return waterConnection;
        }

        public ArrayList<Well> getWell() {
            return well;
        }

        public ArrayList<SolarPanel> getSolarPanel() {
            return solarPanel;
        }

        public ArrayList<DeathIn1year> getDeathIn1year() {
            return deathIn1year;
        }

        public ArrayList<Cattle> getCattle() {
            return cattle;
        }

        public ArrayList<Poultry> getPoultry() {
            return poultry;
        }

        public ArrayList<GovernmentHealthCard> getGovernmentHealthCard() {
            return governmentHealthCard;
        }

        public ArrayList<ArStatus> getArStatus() {
            return arStatus;
        }

        public ArrayList<Ramp> getRamp() {
            return ramp;
        }

        public ArrayList<OwnershipChange> getOwnershipChange() {
            return ownershipChange;
        }

        public ArrayList<IsCoolieWage> getIsCoolieWage() {
            return isCoolieWage;
        }

        public ArrayList<IsAlive> getIsAlive() {
            return isAlive;
        }

        public ArrayList<IsQualified> getIsQualified() {
            return isQualified;
        }

        public ArrayList<BankAccount> getBankAccount() {
            return bankAccount;
        }

        public ArrayList<NriOrNrk> getNriOrNrk() {
            return nriOrNrk;
        }

        public ArrayList<BankType> getBankTypes() {
            return bankTypes;
        }

        public ArrayList<FloorTypes> getFloorTypes() {
            return floorTypes;
        }
    }

    public static class BloodGroups extends BaseSpinnerData {
        @Expose
        @SerializedName("group")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingConditions extends BaseSpinnerData {
        @Expose
        @SerializedName("condition")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("status")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingSubTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("bldg_sub_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingUnder extends BaseSpinnerData {
        @Expose
        @SerializedName("building_under")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingUsages extends BaseSpinnerData {
        @Expose
        @SerializedName("bldg_usage")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingZones extends BaseSpinnerData {
        @Expose
        @SerializedName("bldg_zone")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Castes extends BaseSpinnerData {
        @Expose
        @SerializedName("caste")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RationCardColors extends BaseSpinnerData {
        @Expose
        @SerializedName("color")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DisabilityTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("disability")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DiseaseTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("disease")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DoorStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("door_status")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Educations extends BaseSpinnerData {
        @Expose
        @SerializedName("education")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class EducationCategories extends BaseSpinnerData {
        @Expose
        @SerializedName("category")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class FloorAreaCategories extends BaseSpinnerData {
        @Expose
        @SerializedName("category")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class FloorTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("floor_category")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class FloorNumbers extends BaseSpinnerData {
        @Expose
        @SerializedName("floor_name")
        private String spinnerTitle;

        @Expose
        @SerializedName("number")
        private String floorNumber;

        @Expose
        @SerializedName("type")
        private String floorType;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Jobs extends BaseSpinnerData {
        @Expose
        @SerializedName("job")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class MaritalStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("status")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class PensionTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("pension")
        private String spinnerTitle;


        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Religions extends BaseSpinnerData {
        @Expose
        @SerializedName("religion")
        private String spinnerTitle;


        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoadDirections extends BaseSpinnerData {
        @Expose
        @SerializedName("road_direction")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoofCategories extends BaseSpinnerData {
        @Expose
        @SerializedName("roof_category")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WaterConnTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("water_conn_type")
        private String spinnerTitle;


        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WaterInWell extends BaseSpinnerData {
        @Expose
        @SerializedName("water_in_well")
        private String spinnerTitle;


        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WaterSources extends BaseSpinnerData {
        @Expose
        @SerializedName("water_source")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WardNos extends BaseSpinnerData {
        @Expose
        @SerializedName("ward_no")
        private String spinnerTitle;

        @Expose
        @SerializedName("ward_name")
        private String wardName;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class AnyInstitute extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class CentralisedAc extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class NormalAc extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class AnyStructuralChange extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class HigherFloorGrt250Sqm extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class AnyOtherLowerFloorType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Postoffices extends BaseSpinnerData {
        @Expose
        @SerializedName("postoffice")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoadTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoadWidths extends BaseSpinnerData {
        @Expose
        @SerializedName("road_width")
        private String spinnerTitle;

        @Expose
        @SerializedName("road_width_category")
        private String roadWidthCategory;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Genders extends BaseSpinnerData {
        @Expose
        @SerializedName("gender")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class EstLicense extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class EstGstStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class ElectricityConnection extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WasteManagement extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Bathroom extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Latrine extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class ParkingArea extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RainWaterHarvesting extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class GasConnection extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WaterConnection extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Well extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class SolarPanel extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DeathIn1year extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Cattle extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Poultry extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class GovernmentHealthCard extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class ArStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Ramp extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class OwnershipChange extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class IsCoolieWage extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class IsAlive extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class IsQualified extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BankAccount extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class NriOrNrk extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BankType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
}


