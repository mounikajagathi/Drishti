package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;

public class FetchAssetDataResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private FetchAssetDataResponse.Data data;

    public Data getData() {
        return data;
    }

    //Inner Data Class
    public static class Data {


        @Expose
        @SerializedName("buildingasset_tenant_details")
        private BuildingAssets.TenantDetails tenantDetails;

        @Expose
        @SerializedName("buildingasset_tax_details")
        private ArrayList<BuildingAssets.TaxDetails> taxDetails;

        @Expose
        @SerializedName("buildingasset_floor_prop_area")
        private ArrayList<BuildingAssets.FloorArea> floorArea;

        @Expose
        @SerializedName("buildingasset_survey_details")
        private BuildingAssets.SurveyDetails surveyDetails;

        @Expose
        @SerializedName("buildingasset_property_other_details")
        private BuildingAssets.PropertyOtherDetails propertyOtherDetails;

        @Expose
        @SerializedName("buildingasset_establishment")
        private BuildingAssets.EstablishmentDetails establishmentDetails;

        @Expose
        @SerializedName("buildingasset_property")
        private BuildingAssets.PropertyDetails propertyDetails;


        @Expose
        @SerializedName("buildingasset_member_details")
        private ArrayList<BuildingAssets.MemberDetails> memberDetails;


        @Expose
        @SerializedName("buildingasset_owner_details")
        private ArrayList<BuildingAssets.OwnerDetails> ownerDetails;


        @Expose
        @SerializedName("buildingasset_property_roof_type")
        private ArrayList<BuildingAssets.RoofType> roofTypes;

        @Expose
        @SerializedName("buildingasset_property_floor_type")
        private ArrayList<BuildingAssets.FloorType> floorTypes;

        @Expose
        @SerializedName("utility_road")
        private UtilityAssets.RoadUtility roadUtility;

        @Expose
        @SerializedName("buildingasset_building")
        private BuildingAssets.BuildingDetails buildingDetails;

        @Expose
        @SerializedName("utility_bridge")
        private UtilityAssets.Bridge bridgeDetails;

        @Expose
        @SerializedName("utility_culvert")
        private UtilityAssets.Culvert culvertDetails;

        @Expose
        @SerializedName("utility_divider")
        private UtilityAssets.Divider dividerDetails;

        @Expose
        @SerializedName("utility_drainage")
        private UtilityAssets.Drainage drainageDetails;

        @Expose
        @SerializedName("utility_parking_area")
        private UtilityAssets.ParkingArea parkingAreaDetails;

        @Expose
        @SerializedName("utility_playground")
        private UtilityAssets.Playground playgroundDetails;

        @Expose
        @SerializedName("utility_road_hump")
        private UtilityAssets.RoadHump roadHumpDetails;

        @Expose
        @SerializedName("utility_road_junction")
        private UtilityAssets.RoadJunction roadJunctionDetails;

        @Expose
        @SerializedName("utility_road_signboard")
        private UtilityAssets.RoadSignboard roadSignboardDetails;

        @Expose
        @SerializedName("waterbody_pond")
        private WaterBodyAssets.Pond pondDetails;


        @Expose
        @SerializedName("buildingasset_member_disability")
        private ArrayList<BuildingAssets.Disability> disability;


        public BuildingAssets.TenantDetails getTenantDetails() {
            return tenantDetails;
        }

        public ArrayList<BuildingAssets.TaxDetails> getTaxDetails() {
            return taxDetails;
        }

        public ArrayList<BuildingAssets.FloorArea> getFloorArea() {
            return floorArea;
        }

        public BuildingAssets.SurveyDetails getSurveyDetails() {
            return surveyDetails;
        }

        public BuildingAssets.PropertyOtherDetails getPropertyOtherDetails() {
            return propertyOtherDetails;
        }

        public BuildingAssets.EstablishmentDetails getEstablishmentDetails() {
            return establishmentDetails;
        }

        public BuildingAssets.PropertyDetails getPropertyDetails() {
            return propertyDetails;
        }

        public ArrayList<BuildingAssets.MemberDetails> getMemberDetails() {
            return memberDetails;
        }

        public ArrayList<BuildingAssets.OwnerDetails> getOwnerDetails() {
            return ownerDetails;
        }

        public ArrayList<BuildingAssets.RoofType> getRoofTypes() {
            return roofTypes;
        }

        public UtilityAssets.RoadUtility getRoadUtility() {
            return roadUtility;
        }

        public BuildingAssets.BuildingDetails getBuildingDetails() {
            return buildingDetails;
        }

        public UtilityAssets.Bridge getBridgeDetails() {
            return bridgeDetails;
        }

        public UtilityAssets.Culvert getCulvertDetails() {
            return culvertDetails;
        }

        public UtilityAssets.Divider getDividerDetails() {
            return dividerDetails;
        }

        public UtilityAssets.Drainage getDrainageDetails() {
            return drainageDetails;
        }

        public UtilityAssets.ParkingArea getParkingAreaDetails() {
            return parkingAreaDetails;
        }

        public UtilityAssets.Playground getPlaygroundDetails() {
            return playgroundDetails;
        }

        public UtilityAssets.RoadHump getRoadHumpDetails() {
            return roadHumpDetails;
        }

        public UtilityAssets.RoadJunction getRoadJunctionDetails() {
            return roadJunctionDetails;
        }

        public UtilityAssets.RoadSignboard getRoadSignboardDetails() {
            return roadSignboardDetails;
        }

        public WaterBodyAssets.Pond getPondDetails() {
            return pondDetails;
        }

        public ArrayList<BuildingAssets.FloorType> getFloorTypes() {
            return floorTypes;
        }


        public void setTenantDetails(BuildingAssets.TenantDetails tenantDetails) {
            this.tenantDetails = tenantDetails;
        }

        public void setTaxDetails(ArrayList<BuildingAssets.TaxDetails> taxDetails) {
            this.taxDetails = taxDetails;
        }

        public void setFloorArea(ArrayList<BuildingAssets.FloorArea> floorArea) {
            this.floorArea = floorArea;
        }

        public void setSurveyDetails(BuildingAssets.SurveyDetails surveyDetails) {
            this.surveyDetails = surveyDetails;
        }

        public void setPropertyOtherDetails(BuildingAssets.PropertyOtherDetails propertyOtherDetails) {
            this.propertyOtherDetails = propertyOtherDetails;
        }

        public void setEstablishmentDetails(BuildingAssets.EstablishmentDetails establishmentDetails) {
            this.establishmentDetails = establishmentDetails;
        }

        public void setPropertyDetails(BuildingAssets.PropertyDetails propertyDetails) {
            this.propertyDetails = propertyDetails;
        }

        public void setMemberDetails(ArrayList<BuildingAssets.MemberDetails> memberDetails) {
            this.memberDetails = memberDetails;
        }

        public void setOwnerDetails(ArrayList<BuildingAssets.OwnerDetails> ownerDetails) {
            this.ownerDetails = ownerDetails;
        }

        public void setRoofTypes(ArrayList<BuildingAssets.RoofType> roofTypes) {
            this.roofTypes = roofTypes;
        }

        public void setFloorTypes(ArrayList<BuildingAssets.FloorType> floorTypes) {
            this.floorTypes = floorTypes;
        }

        public void setRoadUtility(UtilityAssets.RoadUtility roadUtility) {
            this.roadUtility = roadUtility;
        }

        public void setBuildingDetails(BuildingAssets.BuildingDetails buildingDetails) {
            this.buildingDetails = buildingDetails;
        }

        public void setBridgeDetails(UtilityAssets.Bridge bridgeDetails) {
            this.bridgeDetails = bridgeDetails;
        }

        public void setCulvertDetails(UtilityAssets.Culvert culvertDetails) {
            this.culvertDetails = culvertDetails;
        }

        public void setDividerDetails(UtilityAssets.Divider dividerDetails) {
            this.dividerDetails = dividerDetails;
        }

        public void setDrainageDetails(UtilityAssets.Drainage drainageDetails) {
            this.drainageDetails = drainageDetails;
        }

        public void setParkingAreaDetails(UtilityAssets.ParkingArea parkingAreaDetails) {
            this.parkingAreaDetails = parkingAreaDetails;
        }

        public void setPlaygroundDetails(UtilityAssets.Playground playgroundDetails) {
            this.playgroundDetails = playgroundDetails;
        }

        public void setRoadHumpDetails(UtilityAssets.RoadHump roadHumpDetails) {
            this.roadHumpDetails = roadHumpDetails;
        }

        public void setRoadJunctionDetails(UtilityAssets.RoadJunction roadJunctionDetails) {
            this.roadJunctionDetails = roadJunctionDetails;
        }

        public void setRoadSignboardDetails(UtilityAssets.RoadSignboard roadSignboardDetails) {
            this.roadSignboardDetails = roadSignboardDetails;
        }

        public void setPondDetails(WaterBodyAssets.Pond pondDetails) {
            this.pondDetails = pondDetails;
        }

        public ArrayList<BuildingAssets.Disability> getDisability() {
            return disability;
        }

        public void setDisability(ArrayList<BuildingAssets.Disability> disability) {
            this.disability = disability;
        }
    }



}
