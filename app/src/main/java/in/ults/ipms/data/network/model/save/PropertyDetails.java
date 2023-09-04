package in.ults.ipms.data.network.model.save;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ults.ipms.data.network.model.base.BaseResponse;

public class PropertyDetails extends BaseResponse {

    @Expose
    @SerializedName("building")
    private String buildingName;

    @Expose
    @SerializedName("bldg_condition")
    private String buildingCondition;

    @Expose
    @SerializedName("door_status")
    private String doorStatus;

    @Expose
    @SerializedName("which_floor")
    private String whichFloor;

    @Expose
    @SerializedName("bldg_status")
    private String buildingStatus;

    @Expose
    @SerializedName("bldg_usage")
    private String buildingUsage;

    @Expose
    @SerializedName("new_pro_id")
    private String newPropertyId;

    @Expose
    @SerializedName("old_pro_id")
    private String oldPropertyId;

    @Expose
    @SerializedName("year_const")
    private String yearOfConstruction;

    @Expose
    @SerializedName("noofyears")
    private String noOfYears;

    @Expose
    @SerializedName("any_institute")
    private String anyInstitute;

    @Expose
    @SerializedName("central_ac")
    private String centralisedAc;

    @Expose
    @SerializedName("normal_ac")
    private String normalAc;

    @Expose
    @SerializedName("structural_change")
    private String structuralChange;

    @Expose
    @SerializedName("higher_floor_gt")
    private String higherFloorGt;

    @Expose
    @SerializedName("other_flr_type")
    private String otherLowerFloorType;

    @Expose
    @SerializedName("photo1")
    private String photo1;

    @Expose
    @SerializedName("photo2")
    private String photo2;

    @Expose
    @SerializedName("plan")
    private String plan;

    @Expose
    @SerializedName("place_name")
    private String placeName;

    @Expose
    @SerializedName("bldg_zone")
    private String buildingZone;

    @Expose
    @SerializedName("ward")
    private String wardNumber;

    @Expose
    @SerializedName("postoffice")
    private String postoffice;

    @Expose
    @SerializedName("latitude")
    private String latitude;

    @Expose
    @SerializedName("longitude")
    private String longitude;

    @Expose
    @SerializedName("near_road")
    private String nearRoad;

    @Expose
    @SerializedName("road_type")
    private String roadType;

    @Expose
    @SerializedName("road_width")
    private String roadWidth;

    public PropertyDetails() {
    }

    public PropertyDetails(String buildingName, String buildingCondition, String doorStatus, String whichFloor, String buildingStatus, String buildingUsage, String newPropertyId, String oldPropertyId, String yearOfConstruction, String noOfYears, String anyInstitute, String centralisedAc, String normalAc, String structuralChange, String higherFloorGt, String otherLowerFloorType, String photo1, String photo2, String plan, String placeName, String buildingZone, String wardNumber, String postoffice, String latitude, String longitude, String nearRoad, String roadType, String roadWidth) {
        this.buildingName = buildingName;
        this.buildingCondition = buildingCondition;
        this.doorStatus = doorStatus;
        this.whichFloor = whichFloor;
        this.buildingStatus = buildingStatus;
        this.buildingUsage = buildingUsage;
        this.newPropertyId = newPropertyId;
        this.oldPropertyId = oldPropertyId;
        this.yearOfConstruction = yearOfConstruction;
        this.noOfYears = noOfYears;
        this.anyInstitute = anyInstitute;
        this.centralisedAc = centralisedAc;
        this.normalAc = normalAc;
        this.structuralChange = structuralChange;
        this.higherFloorGt = higherFloorGt;
        this.otherLowerFloorType = otherLowerFloorType;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.plan = plan;
        this.placeName = placeName;
        this.buildingZone = buildingZone;
        this.wardNumber = wardNumber;
        this.postoffice = postoffice;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nearRoad = nearRoad;
        this.roadType = roadType;
        this.roadWidth = roadWidth;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingCondition() {
        return buildingCondition;
    }

    public void setBuildingCondition(String buildingCondition) {
        this.buildingCondition = buildingCondition;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getWhichFloor() {
        return whichFloor;
    }

    public void setWhichFloor(String whichFloor) {
        this.whichFloor = whichFloor;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public String getBuildingUsage() {
        return buildingUsage;
    }

    public void setBuildingUsage(String buildingUsage) {
        this.buildingUsage = buildingUsage;
    }

    public String getNewPropertyId() {
        return newPropertyId;
    }

    public void setNewPropertyId(String newPropertyId) {
        this.newPropertyId = newPropertyId;
    }

    public String getOldPropertyId() {
        return oldPropertyId;
    }

    public void setOldPropertyId(String oldPropertyId) {
        this.oldPropertyId = oldPropertyId;
    }

    public String getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(String yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public String getNoOfYears() {
        return noOfYears;
    }

    public void setNoOfYears(String noOfYears) {
        this.noOfYears = noOfYears;
    }

    public String getAnyInstitute() {
        return anyInstitute;
    }

    public void setAnyInstitute(String anyInstitute) {
        this.anyInstitute = anyInstitute;
    }

    public String getCentralisedAc() {
        return centralisedAc;
    }

    public void setCentralisedAc(String centralisedAc) {
        this.centralisedAc = centralisedAc;
    }

    public String getNormalAc() {
        return normalAc;
    }

    public void setNormalAc(String normalAc) {
        this.normalAc = normalAc;
    }

    public String getStructuralChange() {
        return structuralChange;
    }

    public void setStructuralChange(String structuralChange) {
        this.structuralChange = structuralChange;
    }

    public String getHigherFloorGt() {
        return higherFloorGt;
    }

    public void setHigherFloorGt(String higherFloorGt) {
        this.higherFloorGt = higherFloorGt;
    }

    public String getOtherLowerFloorType() {
        return otherLowerFloorType;
    }

    public void setOtherLowerFloorType(String otherLowerFloorType) {
        this.otherLowerFloorType = otherLowerFloorType;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getBuildingZone() {
        return buildingZone;
    }

    public void setBuildingZone(String buildingZone) {
        this.buildingZone = buildingZone;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getPostoffice() {
        return postoffice;
    }

    public void setPostoffice(String postoffice) {
        this.postoffice = postoffice;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNearRoad() {
        return nearRoad;
    }

    public void setNearRoad(String nearRoad) {
        this.nearRoad = nearRoad;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getRoadWidth() {
        return roadWidth;
    }

    public void setRoadWidth(String roadWidth) {
        this.roadWidth = roadWidth;
    }
}
