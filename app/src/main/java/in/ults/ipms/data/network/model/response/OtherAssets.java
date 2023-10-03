package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherAssets {

    public static class HighLowMastLight {

        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("created_by")
        @Expose
        private long createdBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_by")
        @Expose
        private long updatedBy;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("verified_by")
        @Expose
        private long verifiedBy;
        @SerializedName("approved_by")
        @Expose
        private long approvedBy;
        @SerializedName("rejected_by")
        @Expose
        private long rejectedBy;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("updation_status")
        @Expose
        private String updationStatus;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("type")
        @Expose
        String type;
        @SerializedName("light_type")
        @Expose
        String lightType;
        @SerializedName("funded_by")
        @Expose
        String fundedBy;
        @SerializedName("work_statu")
        @Expose
        String workingStatus;
        @SerializedName("location")
        @Expose
        String locationDetails;
        @SerializedName("address")
        @Expose
        String address;
        @SerializedName("power")
        @Expose
        String power;
        @SerializedName("hight")
        @Expose
        String height;
        @SerializedName("no_of_bulb")
        @Expose
        String bulbNumber;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("pk")
        @Expose
        private long pk = -1;

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public long getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(long createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public long getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(long updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public long getVerifiedBy() {
            return verifiedBy;
        }

        public void setVerifiedBy(long verifiedBy) {
            this.verifiedBy = verifiedBy;
        }

        public long getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(long approvedBy) {
            this.approvedBy = approvedBy;
        }

        public long getRejectedBy() {
            return rejectedBy;
        }

        public void setRejectedBy(long rejectedBy) {
            this.rejectedBy = rejectedBy;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public long getLocalbody() {
            return localbody;
        }

        public void setLocalbody(long localbody) {
            this.localbody = localbody;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLightType() {
            return lightType;
        }

        public void setLightType(String lightType) {
            this.lightType = lightType;
        }

        public String getFundedBy() {
            return fundedBy;
        }

        public void setFundedBy(String fundedBy) {
            this.fundedBy = fundedBy;
        }

        public String getWorkingStatus() {
            return workingStatus;
        }

        public void setWorkingStatus(String workingStatus) {
            this.workingStatus = workingStatus;
        }

        public String getLocationDetails() {
            return locationDetails;
        }

        public void setLocationDetails(String locationDetails) {
            this.locationDetails = locationDetails;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getBulbNumber() {
            return bulbNumber;
        }

        public void setBulbNumber(String bulbNumber) {
            this.bulbNumber = bulbNumber;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public long getWard() {
            return ward;
        }

        public void setWard(long ward) {
            this.ward = ward;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }
    }
}
