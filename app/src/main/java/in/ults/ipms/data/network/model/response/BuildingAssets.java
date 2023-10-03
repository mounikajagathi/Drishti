package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BuildingAssets {

    //Inner Data Class
    public static class PropertyDetails {

        @Expose
        @SerializedName("any_institute")
        private String anyInstitute;

        @Expose
        @SerializedName("approved_by")
        private String approvedBy;

        @Expose
        @SerializedName("asset_status")
        private String assetStatus;

        @Expose
        @SerializedName("bldg_condition")
        private int bldgCondition;

        @Expose
        @SerializedName("bldg_status")
        private int bldgStatus;

        @Expose
        @SerializedName("bldg_usage")
        private int bldgUsage;

        @Expose
        @SerializedName("bldg_zone")
        private int bldgZone;

        @Expose
        @SerializedName("building")
        private long building;

        @Expose
        @SerializedName("central_ac")
        private String centralAc;

        @Expose
        @SerializedName("changed_by")
        private int changedBy;

        @Expose
        @SerializedName("created_at")
        private String createdAt;

        @Expose
        @SerializedName("created_by")
        private String createdBy;
        @Expose
        @SerializedName("district")
        private long district;
        @Expose
        @SerializedName("door_status")
        private int doorStatus;
        @Expose
        @SerializedName("geom")
        private GeomPoint geom;
        @Expose
        @SerializedName("higher_floor_gt")
        private String higherFloorGt;
        @Expose
        @SerializedName("localbody")
        private int localbody;
        @Expose
        @SerializedName("near_road")
        private String nearRoad;
        @Expose
        @SerializedName("new_pro_id")
        private String newProId;
        @Expose
        @SerializedName("noofyears")
        private int noOfYears;
        @Expose
        @SerializedName("normal_ac")
        private String normalAc;

        @Expose
        @SerializedName("old_pro_id")
        private String oldProId;
        @Expose
        @SerializedName("other_flr_type")
        private String otherFlrType;
        @Expose
        @SerializedName("photo1")
        private String photo1;
        @Expose
        @SerializedName("photo2")
        private String photo2;
        @Expose
        @SerializedName("pk")
        private long pk = -1;
        @Expose
        @SerializedName("place_name")
        private String place_name;
        @Expose
        @SerializedName("plan")
        private String plan;
        @Expose
        @SerializedName("postoffice")
        private int postOffice;
        @Expose
        @SerializedName("rejected_by")
        private String rejectedBy;
        @Expose
        @SerializedName("reject_reason")
        private String rejectReason;
        @Expose
        @SerializedName("remarks")
        private String remarks;
        @Expose
        @SerializedName("road_direction")
        private int roadDirection;
        @Expose
        @SerializedName("road_type")
        private int roadType;
        @Expose
        @SerializedName("road_width")
        private int roadWidth;
        @Expose
        @SerializedName("state")
        private int state;

        @Expose
        @SerializedName("street")
        private String street;
        @Expose
        @SerializedName("structural_change")
        private String structuralChange;
        @Expose
        @SerializedName("unique_id")
        private String uniqueId;
        @Expose
        @SerializedName("updated_at")
        private String updatedAt;
        @Expose
        @SerializedName("updated_by")
        private String updatedBy;
        @Expose
        @SerializedName("updation_status")
        private String updationStatus;
        @Expose
        @SerializedName("verified_by")
        private String verifiedBy;
        @Expose
        @SerializedName("village")
        private String village;
        @Expose
        @SerializedName("ward")
        private int ward;
        @Expose
        @SerializedName("which_floor")
        private String whichFloor;
        @Expose
        @SerializedName("year_const")
        private int yearConst;

        public String getAnyInstitute() {
            return anyInstitute;
        }

        public void setAnyInstitute(String anyInstitute) {
            this.anyInstitute = anyInstitute;
        }

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }

        public String getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(String assetStatus) {
            this.assetStatus = assetStatus;
        }

        public int getBldgCondition() {
            return bldgCondition;
        }

        public void setBldgCondition(int bldgCondition) {
            this.bldgCondition = bldgCondition;
        }

        public int getBldgStatus() {
            return bldgStatus;
        }

        public void setBldgStatus(int bldgStatus) {
            this.bldgStatus = bldgStatus;
        }

        public int getBldgUsage() {
            return bldgUsage;
        }

        public void setBldgUsage(int bldgUsage) {
            this.bldgUsage = bldgUsage;
        }

        public int getBldgZone() {
            return bldgZone;
        }

        public void setBldgZone(int bldgZone) {
            this.bldgZone = bldgZone;
        }

        public long getBuilding() {
            return building;
        }

        public void setBuilding(long building) {
            this.building = building;
        }

        public String getCentralAc() {
            return centralAc;
        }

        public void setCentralAc(String centralAc) {
            this.centralAc = centralAc;
        }

        public int getChangedBy() {
            return changedBy;
        }

        public void setChangedBy(int changedBy) {
            this.changedBy = changedBy;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public int getDoorStatus() {
            return doorStatus;
        }

        public void setDoorStatus(int doorStatus) {
            this.doorStatus = doorStatus;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getHigherFloorGt() {
            return higherFloorGt;
        }

        public void setHigherFloorGt(String higherFloorGt) {
            this.higherFloorGt = higherFloorGt;
        }

        public int getLocalbody() {
            return localbody;
        }

        public void setLocalbody(int localbody) {
            this.localbody = localbody;
        }

        public String getNearRoad() {
            return nearRoad;
        }

        public void setNearRoad(String nearRoad) {
            this.nearRoad = nearRoad;
        }

        public String getNewProId() {
            return newProId;
        }

        public void setNewProId(String newProId) {
            this.newProId = newProId;
        }

        public int getNoOfYears() {
            return noOfYears;
        }

        public void setNoOfYears(int noOfYears) {
            this.noOfYears = noOfYears;
        }

        public String getNormalAc() {
            return normalAc;
        }

        public void setNormalAc(String normalAc) {
            this.normalAc = normalAc;
        }

        public String getOldProId() {
            return oldProId;
        }

        public void setOldProId(String oldProId) {
            this.oldProId = oldProId;
        }

        public String getOtherFlrType() {
            return otherFlrType;
        }

        public void setOtherFlrType(String otherFlrType) {
            this.otherFlrType = otherFlrType;
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

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public int getPostOffice() {
            return postOffice;
        }

        public void setPostOffice(int postOffice) {
            this.postOffice = postOffice;
        }

        public String getRejectedBy() {
            return rejectedBy;
        }

        public void setRejectedBy(String rejectedBy) {
            this.rejectedBy = rejectedBy;
        }

        public String getRejectReason() {
            return rejectReason;
        }

        public void setRejectReason(String rejectReason) {
            this.rejectReason = rejectReason;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getRoadDirection() {
            return roadDirection;
        }

        public void setRoadDirection(int roadDirection) {
            this.roadDirection = roadDirection;
        }

        public int getRoadType() {
            return roadType;
        }

        public void setRoadType(int roadType) {
            this.roadType = roadType;
        }

        public int getRoadWidth() {
            return roadWidth;
        }

        public void setRoadWidth(int roadWidth) {
            this.roadWidth = roadWidth;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStructuralChange() {
            return structuralChange;
        }

        public void setStructuralChange(String structuralChange) {
            this.structuralChange = structuralChange;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getVerifiedBy() {
            return verifiedBy;
        }

        public void setVerifiedBy(String verifiedBy) {
            this.verifiedBy = verifiedBy;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public int getWard() {
            return ward;
        }

        public void setWard(int ward) {
            this.ward = ward;
        }

        public String getWhichFloor() {
            return whichFloor;
        }

        public void setWhichFloor(String whichFloor) {
            this.whichFloor = whichFloor;
        }

        public int getYearConst() {
            return yearConst;
        }

        public void setYearConst(int yearConst) {
            this.yearConst = yearConst;
        }
    }


    //Inner Data Class
    public static class TenantDetails {

        @Expose
        @SerializedName("email")
        private String email;

        @Expose
        @SerializedName("firstname")
        private String firstname;

        @Expose
        @SerializedName("gender")
        private int gender;

        @Expose
        @SerializedName("landphone")
        private String landphone;

        @Expose
        @SerializedName("lastname")
        private String lastname;

        @Expose
        @SerializedName("mobile")
        private String mobile;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("place_name")
        private String placeName;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("rent_amount")
        private String rentAmount;

        @Expose
        @SerializedName("street")
        private String street;

        @Expose
        @SerializedName("tnt_house_name")
        private String tntHouseName;

        @Expose
        @SerializedName("tnt_native")
        private String tntNative;

        @Expose
        @SerializedName("tnt_post_office")
        private String tntPostOffice;

        @Expose
        @SerializedName("tnt_survey_no")
        private String tntSurveyNo;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("village")
        private String village;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getLandphone() {
            return landphone;
        }

        public void setLandphone(String landphone) {
            this.landphone = landphone;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getRentAmount() {
            return rentAmount;
        }

        public void setRentAmount(String rentAmount) {
            this.rentAmount = rentAmount;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getTntHouseName() {
            return tntHouseName;
        }

        public void setTntHouseName(String tntHouseName) {
            this.tntHouseName = tntHouseName;
        }

        public String getTntNative() {
            return tntNative;
        }

        public void setTntNative(String tntNative) {
            this.tntNative = tntNative;
        }

        public String getTntPostOffice() {
            return tntPostOffice;
        }

        public void setTntPostOffice(String tntPostOffice) {
            this.tntPostOffice = tntPostOffice;
        }

        public String getTntSurveyNo() {
            return tntSurveyNo;
        }

        public void setTntSurveyNo(String tntSurveyNo) {
            this.tntSurveyNo = tntSurveyNo;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }
    }

    //Inner Data Class
    public static class EstablishmentDetails {

        @Expose
        @SerializedName("email")
        private String email;

        @Expose
        @SerializedName("estb_name")
        private String estbName;

        @Expose
        @SerializedName("estb_post")
        private String estbPost;

        @Expose
        @SerializedName("estb_type")
        private String estbType;

        @Expose
        @SerializedName("estb_year")
        private int estbYear;

        @Expose
        @SerializedName("gst_no")
        private String gstNo;

        @Expose
        @SerializedName("gst_status")
        private String gstStatus;

        @Expose
        @SerializedName("inchrg_name")
        private String inchrg_name;

        @Expose
        @SerializedName("inchrg_role")
        private String inchrgRole;

        @Expose
        @SerializedName("inchrg_post")
        private String inchrgPost;

        @Expose
        @SerializedName("landphone")
        private String landphone;

        @Expose
        @SerializedName("license")
        private String licence;

        @Expose
        @SerializedName("license_no")
        private String licenceNo;

        @Expose
        @SerializedName("mobile")
        private String mobile;

        @Expose
        @SerializedName("no_of_employees")
        private int no_of_employees;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("prof_tax_pay_status")
        private String profTaxPayStatus;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEstbName() {
            return estbName;
        }

        public void setEstbName(String estbName) {
            this.estbName = estbName;
        }

        public String getEstbPost() {
            return estbPost;
        }

        public void setEstbPost(String estbPost) {
            this.estbPost = estbPost;
        }

        public String getEstbType() {
            return estbType;
        }

        public void setEstbType(String estbType) {
            this.estbType = estbType;
        }

        public int getEstbYear() {
            return estbYear;
        }

        public void setEstbYear(int estbYear) {
            this.estbYear = estbYear;
        }

        public String getGstNo() {
            return gstNo;
        }

        public void setGstNo(String gstNo) {
            this.gstNo = gstNo;
        }

        public String getGstStatus() {
            return gstStatus;
        }

        public void setGstStatus(String gstStatus) {
            this.gstStatus = gstStatus;
        }

        public String getInchrg_name() {
            return inchrg_name;
        }

        public void setInchrg_name(String inchrg_name) {
            this.inchrg_name = inchrg_name;
        }

        public String getInchrgRole() {
            return inchrgRole;
        }

        public void setInchrgRole(String inchrgRole) {
            this.inchrgRole = inchrgRole;
        }

        public String getInchrgPost() {
            return inchrgPost;
        }

        public void setInchrgPost(String inchrgPost) {
            this.inchrgPost = inchrgPost;
        }

        public String getLandphone() {
            return landphone;
        }

        public void setLandphone(String landphone) {
            this.landphone = landphone;
        }

        public String getLicence() {
            return licence;
        }

        public void setLicence(String licence) {
            this.licence = licence;
        }

        public String getLicenceNo() {
            return licenceNo;
        }

        public void setLicenceNo(String licenceNo) {
            this.licenceNo = licenceNo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getNo_of_employees() {
            return no_of_employees;
        }

        public void setNo_of_employees(int no_of_employees) {
            this.no_of_employees = no_of_employees;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getProfTaxPayStatus() {
            return profTaxPayStatus;
        }

        public void setProfTaxPayStatus(String profTaxPayStatus) {
            this.profTaxPayStatus = profTaxPayStatus;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }
    }

    //Inner Data Class
    public static class TaxDetails {

        @Expose
        @SerializedName("assessment_no")
        private String assessmentNo;
        @Expose
        @SerializedName("pk")
        private long pk = -1;
        @Expose
        @SerializedName("property")
        private long property;
        @Expose
        @SerializedName("taxpaid_date")
        private String taxPaidDate;
        @Expose
        @SerializedName("taxpaid_year")
        private long taxPaidYear;
        @Expose
        @SerializedName("tax_amount")
        private String taxAmount;
        @Expose
        @SerializedName("tax_bill_no")
        private String taxBillNo;
        @Expose
        @SerializedName("tax_photo")
        private String taxPhoto;
        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }


        public String getAssessmentNo() {
            return assessmentNo;
        }

        public void setAssessmentNo(String assessmentNo) {
            this.assessmentNo = assessmentNo;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getTaxPaidDate() {
            return taxPaidDate;
        }

        public void setTaxPaidDate(String taxPaidDate) {
            this.taxPaidDate = taxPaidDate;
        }

        public long getTaxPaidYear() {
            return taxPaidYear;
        }

        public void setTaxPaidYear(int taxPaidYear) {
            this.taxPaidYear = taxPaidYear;
        }

        public String getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(String taxAmount) {
            this.taxAmount = taxAmount;
        }

        public String getTaxBillNo() {
            return taxBillNo;
        }

        public void setTaxBillNo(String taxBillNo) {
            this.taxBillNo = taxBillNo;
        }

        public String getTaxPhoto() {
            return taxPhoto;
        }

        public void setTaxPhoto(String taxPhoto) {
            this.taxPhoto = taxPhoto;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }
    }

    //Inner Data Class
    public static class SurveyDetails {

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;
        @Expose
        @SerializedName("surveyor")
        private String surveyor;
        @Expose
        @SerializedName("remark")
        private String remark;
        @Expose
        @SerializedName("property")
        private long property;
        @Expose
        @SerializedName("pk")
        private long pk = -1;
        @Expose
        @SerializedName("ownership_change")
        private String ownershipChange;
        @Expose
        @SerializedName("old_pro_rmk")
        private String oldPropertyRmk;
        @Expose
        @SerializedName("new_pro_rmk")
        private String newPropertyRmk;
        @Expose
        @SerializedName("neighbour_name")
        private String neighbourName;
        @Expose
        @SerializedName("informed_by")
        private String informedBy;
        @Expose
        @SerializedName("cooperation")
        private String cooperation;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }


        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getSurveyor() {
            return surveyor;
        }

        public void setSurveyor(String surveyor) {
            this.surveyor = surveyor;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getOwnershipChange() {
            return ownershipChange;
        }

        public void setOwnershipChange(String ownershipChange) {
            this.ownershipChange = ownershipChange;
        }

        public String getOldPropertyRmk() {
            return oldPropertyRmk;
        }

        public void setOldPropertyRmk(String oldPropertyRmk) {
            this.oldPropertyRmk = oldPropertyRmk;
        }

        public String getNewPropertyRmk() {
            return newPropertyRmk;
        }

        public void setNewPropertyRmk(String newPropertyRmk) {
            this.newPropertyRmk = newPropertyRmk;
        }

        public String getNeighbourName() {
            return neighbourName;
        }

        public void setNeighbourName(String neighbourName) {
            this.neighbourName = neighbourName;
        }

        public String getInformedBy() {
            return informedBy;
        }

        public void setInformedBy(String informedBy) {
            this.informedBy = informedBy;
        }

        public String getCooperation() {
            return cooperation;
        }

        public void setCooperation(String cooperation) {
            this.cooperation = cooperation;
        }
    }

    //Inner Data Class
    public static class FloorArea {

        @Expose
        @SerializedName("floor_area")
        private String floorArea;

        @Expose
        @SerializedName("floor_category")
        private int floorCategory;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }


        public FloorArea(String floorArea, int floorCategory) {
            this.floorArea = floorArea;
            this.floorCategory = floorCategory;
        }

        public String getFloorArea() {
            return floorArea;
        }

        public void setFloorArea(String floorArea) {
            this.floorArea = floorArea;
        }

        public int getFloorCategory() {
            return floorCategory;
        }

        public void setFloorCategory(int floorCategory) {
            this.floorCategory = floorCategory;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }
    }


    //Inner Data Class
    public static class PropertyOtherDetails {

        @Expose
        @SerializedName("ar_status")
        private String arStatus;

        @Expose
        @SerializedName("bathroom")
        private String bathroom;

        @Expose
        @SerializedName("bldg_sub_type")
        private int bldgSubType;

        @Expose
        @SerializedName("caste")
        private String caste;

        @Expose
        @SerializedName("cattles")
        private String cattles;

        @Expose
        @SerializedName("cattles_details")
        private String cattlesDetails;

        @Expose
        @SerializedName("consumer_no")
        private String consumerNo;

        @Expose
        @SerializedName("death_count")
        private int deathCount;

        @Expose
        @SerializedName("death_couse")
        private String deathCouse;

        @Expose
        @SerializedName("death_in_1year")
        private String DeathInOneyear;

        @Expose
        @SerializedName("electricity_conn")
        private String electricityConn;

        @Expose
        @SerializedName("gas_conn")
        private String gasConn;

        @Expose
        @SerializedName("gov_health_card")
        private String govHealthCard;

        @Expose
        @SerializedName("latrine")
        private String latrine;

        @Expose
        @SerializedName("parking_area")
        private String parkingArea;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("poultry")
        private String poultry;

        @Expose
        @SerializedName("poultry_details")
        private String poultryDetails;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("rain_water_hv")
        private String rainWaterHv;

        @Expose
        @SerializedName("ramp")
        private String ramp;

        @Expose
        @SerializedName("ration_card_color")
        private int rationCardColor;

        @Expose
        @SerializedName("ration_card_no")
        private String rationCardNo;

        @Expose
        @SerializedName("religion")
        private String religion;

        @Expose
        @SerializedName("solar_panel")
        private String solarPanel;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("waste_mgmnt")
        private String wasteMgmnt;

        @Expose
        @SerializedName("water_conn")
        private String waterConn;

        @Expose
        @SerializedName("water_conn_type")
        private int waterConnType;

        @Expose
        @SerializedName("water_source")
        private String waterSource;

        @Expose
        @SerializedName("well")
        private String well;

        @Expose
        @SerializedName("wtrinwell")
        private int waterInWell;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }


        public String getArStatus() {
            return arStatus;
        }

        public void setArStatus(String arStatus) {
            this.arStatus = arStatus;
        }

        public String getBathroom() {
            return bathroom;
        }

        public void setBathroom(String bathroom) {
            this.bathroom = bathroom;
        }

        public int getBldgSubType() {
            return bldgSubType;
        }

        public void setBldgSubType(int bldgSubType) {
            this.bldgSubType = bldgSubType;
        }

        public String getCaste() {
            return caste;
        }

        public void setCaste(String caste) {
            this.caste = caste;
        }

        public String getCattles() {
            return cattles;
        }

        public void setCattles(String cattles) {
            this.cattles = cattles;
        }

        public String getCattlesDetails() {
            return cattlesDetails;
        }

        public void setCattlesDetails(String cattlesDetails) {
            this.cattlesDetails = cattlesDetails;
        }

        public String getConsumerNo() {
            return consumerNo;
        }

        public void setConsumerNo(String consumerNo) {
            this.consumerNo = consumerNo;
        }

        public int getDeathCount() {
            return deathCount;
        }

        public void setDeathCount(int deathCount) {
            this.deathCount = deathCount;
        }

        public String getDeathCouse() {
            return deathCouse;
        }

        public void setDeathCouse(String deathCouse) {
            this.deathCouse = deathCouse;
        }

        public String getDeathInOneyear() {
            return DeathInOneyear;
        }

        public void setDeathInOneyear(String deathInOneyear) {
            DeathInOneyear = deathInOneyear;
        }

        public String getElectricityConn() {
            return electricityConn;
        }

        public void setElectricityConn(String electricityConn) {
            this.electricityConn = electricityConn;
        }

        public String getGasConn() {
            return gasConn;
        }

        public void setGasConn(String gasConn) {
            this.gasConn = gasConn;
        }

        public String getGovHealthCard() {
            return govHealthCard;
        }

        public void setGovHealthCard(String govHealthCard) {
            this.govHealthCard = govHealthCard;
        }

        public String getLatrine() {
            return latrine;
        }

        public void setLatrine(String latrine) {
            this.latrine = latrine;
        }

        public String getParkingArea() {
            return parkingArea;
        }

        public void setParkingArea(String parkingArea) {
            this.parkingArea = parkingArea;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getPoultry() {
            return poultry;
        }

        public void setPoultry(String poultry) {
            this.poultry = poultry;
        }

        public String getPoultryDetails() {
            return poultryDetails;
        }

        public void setPoultryDetails(String poultryDetails) {
            this.poultryDetails = poultryDetails;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getRainWaterHv() {
            return rainWaterHv;
        }

        public void setRainWaterHv(String rainWaterHv) {
            this.rainWaterHv = rainWaterHv;
        }

        public String getRamp() {
            return ramp;
        }

        public void setRamp(String ramp) {
            this.ramp = ramp;
        }

        public int getRationCardColor() {
            return rationCardColor;
        }

        public void setRationCardColor(int rationCardColor) {
            this.rationCardColor = rationCardColor;
        }

        public String getRationCardNo() {
            return rationCardNo;
        }

        public void setRationCardNo(String rationCardNo) {
            this.rationCardNo = rationCardNo;
        }

        public String getReligion() {
            return religion;
        }

        public void setReligion(String religion) {
            this.religion = religion;
        }

        public String getSolarPanel() {
            return solarPanel;
        }

        public void setSolarPanel(String solarPanel) {
            this.solarPanel = solarPanel;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getWasteMgmnt() {
            return wasteMgmnt;
        }

        public void setWasteMgmnt(String wasteMgmnt) {
            this.wasteMgmnt = wasteMgmnt;
        }

        public String getWaterConn() {
            return waterConn;
        }

        public void setWaterConn(String waterConn) {
            this.waterConn = waterConn;
        }

        public int getWaterConnType() {
            return waterConnType;
        }

        public void setWaterConnType(int waterConnType) {
            this.waterConnType = waterConnType;
        }

        public String getWaterSource() {
            return waterSource;
        }

        public void setWaterSource(String waterSource) {
            this.waterSource = waterSource;
        }

        public String getWell() {
            return well;
        }

        public void setWell(String well) {
            this.well = well;
        }

        public int getWaterInWell() {
            return waterInWell;
        }

        public void setWaterInWell(int waterInWell) {
            this.waterInWell = waterInWell;
        }
    }


    //Inner Data Class
    public static class MemberDetails {

        @Expose
        @SerializedName("age")
        private int age;

        @Expose
        @SerializedName("bank_account")
        private String bankAccount;

        @Expose
        @SerializedName("bank_type")
        private ArrayList<String> bankType;

        @Expose
        @SerializedName("blood_group")
        private int bloodGroup;

        @Expose
        @SerializedName("education")
        private int education;

        @Expose
        @SerializedName("education_category")
        private int educationCategory;

        @Expose
        @SerializedName("firstname")
        private String firstname;

        @Expose
        @SerializedName("gender")
        private int gender;

        @Expose
        @SerializedName("is_alive")
        private String isAlive;

        @Expose
        @SerializedName("is_coolie_wage")
        private String isCoolieWage;

        @Expose
        @SerializedName("is_month")
        private boolean isMonth;

        @Expose
        @SerializedName("is_qualified")
        private String isQualified;

        @Expose
        @SerializedName("lastname")
        private String lastname;

        @Expose
        @SerializedName("marital_status")
        private int maritalStatus;

        @Expose
        @SerializedName("nri_or_nrk")
        private String nriOrNrk;

        @Expose
        @SerializedName("occuppation")
        private int occuppation;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("diseases")
        private ArrayList<String> diseases;

        @Expose
        @SerializedName("pension")
        private ArrayList<String> pension;

        @Expose
        @SerializedName("disability")
        private ArrayList<Disability> disability;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public ArrayList<Disability> getDisability() {
            return disability;
        }

        public void setDisability(ArrayList<Disability> disability) {
            this.disability = disability;
        }

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public int getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(int bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public int getEducation() {
            return education;
        }

        public void setEducation(int education) {
            this.education = education;
        }

        public int getEducationCategory() {
            return educationCategory;
        }

        public void setEducationCategory(int educationCategory) {
            this.educationCategory = educationCategory;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getIsAlive() {
            return isAlive;
        }

        public void setIsAlive(String isAlive) {
            this.isAlive = isAlive;
        }

        public String getIsCoolieWage() {
            return isCoolieWage;
        }

        public void setIsCoolieWage(String isCoolieWage) {
            this.isCoolieWage = isCoolieWage;
        }

        public boolean isMonth() {
            return isMonth;
        }

        public void setMonth(boolean month) {
            isMonth = month;
        }

        public String getIsQualified() {
            return isQualified;
        }

        public void setIsQualified(String isQualified) {
            this.isQualified = isQualified;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public int getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(int maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getNriOrNrk() {
            return nriOrNrk;
        }

        public void setNriOrNrk(String nriOrNrk) {
            this.nriOrNrk = nriOrNrk;
        }

        public int getOccuppation() {
            return occuppation;
        }

        public void setOccuppation(int occuppation) {
            this.occuppation = occuppation;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public ArrayList<String> getDiseases() {
            return diseases;
        }

        public void setDiseases(ArrayList<String> diseases) {
            this.diseases = diseases;
        }

        public ArrayList<String> getPension() {
            return pension;
        }

        public void setPension(ArrayList<String> pension) {
            this.pension = pension;
        }

        public void setBankType(ArrayList<String> bankType) {
            this.bankType = bankType;
        }

        public ArrayList<String> getBankType() {
            return bankType;
        }
    }

    //Inner Data Class
    public static class RoofType {

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("roof_per")
        private int roofPer;

        @Expose
        @SerializedName("roof_type")
        private int roofType;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }

        public RoofType(int roofPer, int roofType) {
            this.roofPer = roofPer;
            this.roofType = roofType;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public int getRoofPer() {
            return roofPer;
        }

        public void setRoofPer(int roofPer) {
            this.roofPer = roofPer;
        }

        public int getRoofType() {
            return roofType;
        }

        public void setRoofType(int roofType) {
            this.roofType = roofType;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }
    }

    //Inner Data Class
    public static class OwnerDetails {

        @Expose
        @SerializedName("email")
        private String email;

        @Expose
        @SerializedName("firstname")
        private String firstname;

        @Expose
        @SerializedName("gender")
        private int gender;

        @Expose
        @SerializedName("landphone")
        private String landphone;

        @Expose
        @SerializedName("lastname")
        private String lastname;

        @Expose
        @SerializedName("mobile")
        private String mobile;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("place_name")
        private String placeName;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("street")
        private String street;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("village")
        private String village;

        @Expose
        @SerializedName("owner_house")
        private String ownerHouse;

        @Expose
        @SerializedName("owner_occup")
        private int ownerOccup;

        @Expose
        @SerializedName("owner_post")
        private String ownerPost;

        @Expose
        @SerializedName("owner_survey_no")
        private String ownerSurveyNo;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getLandphone() {
            return landphone;
        }

        public void setLandphone(String landphone) {
            this.landphone = landphone;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getOwnerHouse() {
            return ownerHouse;
        }

        public void setOwnerHouse(String ownerHouse) {
            this.ownerHouse = ownerHouse;
        }

        public int getOwnerOccup() {
            return ownerOccup;
        }

        public void setOwnerOccup(int ownerOccup) {
            this.ownerOccup = ownerOccup;
        }

        public String getOwnerPost() {
            return ownerPost;
        }

        public void setOwnerPost(String ownerPost) {
            this.ownerPost = ownerPost;
        }

        public String getOwnerSurveyNo() {
            return ownerSurveyNo;
        }

        public void setOwnerSurveyNo(String ownerSurveyNo) {
            this.ownerSurveyNo = ownerSurveyNo;
        }
    }



    //Inner Data Class
    public static class BuildingDetails {

        @Expose
        @SerializedName("bldg_ref_id")
        private String bldgRefId;

        @Expose
        @SerializedName("bldg_under")
        private int bldgUnder;

        @Expose
        @SerializedName("building_name")
        private String buildingName;

        @Expose
        @SerializedName("district")
        private int district;

        @Expose
        @SerializedName("is_landmark")
        private boolean isLandmark;

        @Expose
        @SerializedName("localbody")
        private int localBody;

        @Expose
        @SerializedName("permit_no")
        private String permitNo;

        @Expose
        @SerializedName("structure_type")
        private String structureType;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("state")
        private int state;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("ward")
        private String ward;

        @Expose
        @SerializedName("geom")
        private GeomPoint geom;


        public String getBldgRefId() {
            return bldgRefId;
        }

        public void setBldgRefId(String bldgRefId) {
            this.bldgRefId = bldgRefId;
        }

        public int getBldgUnder() {
            return bldgUnder;
        }

        public void setBldgUnder(int bldgUnder) {
            this.bldgUnder = bldgUnder;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public boolean isLandmark() {
            return isLandmark;
        }

        public void setLandmark(boolean landmark) {
            isLandmark = landmark;
        }

        public int getLocalBody() {
            return localBody;
        }

        public void setLocalBody(int localBody) {
            this.localBody = localBody;
        }

        public String getPermitNo() {
            return permitNo;
        }

        public void setPermitNo(String permitNo) {
            this.permitNo = permitNo;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }
    }


    //Inner Data Class
    public static class FloorType {

        @Expose
        @SerializedName("floor_type")
        private int floorType;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("parent_feature")
        private String parentFeature = "property";

        public String getParentFeature() {
            return parentFeature;
        }

        public void setParentFeature(String parentFeature) {
            this.parentFeature = parentFeature;
        }

        public int getFloorType() {
            return floorType;
        }

        public void setFloorType(int floorType) {
            this.floorType = floorType;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }
    }

    //Inner Data Class
    public static class Disability {

        @Expose
        @SerializedName("disability_percentage")
        private String disabilityPercentage;

        @Expose
        @SerializedName("disability")
        private int disability;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("property")
        private long property;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("member")
        private long member;



        public Disability(String disabilityPercentage, int disability) {
            this.disabilityPercentage = disabilityPercentage;
            this.disability = disability;
        }

        public String getDisabilityPercentage() {
            return disabilityPercentage;
        }

        public void setDisabilityPercentage(String disabilityPercentage) {
            this.disabilityPercentage = disabilityPercentage;
        }

        public int getDisability() {
            return disability;
        }

        public void setDisability(int disability) {
            this.disability = disability;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public long getMember() {
            return member;
        }

        public void setMember(long member) {
            this.member = member;
        }
    }



    }
