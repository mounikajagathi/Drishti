package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WaterBodyAssets {

    public static class Pond {

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
        @SerializedName("obid")
        @Expose
        private long obid;
        @SerializedName("pond_name")
        @Expose
        private String pondName;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("capacity")
        @Expose
        private String capacity;
        @SerializedName("maintained_by")
        @Expose
        private long maintainedBy;
        @SerializedName("usage")
        @Expose
        private String usage;
        @SerializedName("odour")
        @Expose
        private String odour;
        @SerializedName("pond_status")
        @Expose
        private String pondStatus;
        @SerializedName("pond_type")
        @Expose
        private String pondType;
        @SerializedName("present_condition")
        @Expose
        private String presentCondition;
        @SerializedName("Nature")
        @Expose
        private String nature;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("sidewall")
        @Expose
        private boolean sidewall;
        @SerializedName("sidewall_Type")
        @Expose
        private String sidewallType;
        @SerializedName("pondcondition")
        @Expose
        private String pondcondition;
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

        public long getObid() {
            return obid;
        }

        public void setObid(long obid) {
            this.obid = obid;
        }

        public String getPondName() {
            return pondName;
        }

        public void setPondName(String pondName) {
            this.pondName = pondName;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public long getMaintainedBy() {
            return maintainedBy;
        }

        public void setMaintainedBy(long maintainedBy) {
            this.maintainedBy = maintainedBy;
        }

        public String getUsage() {
            return usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }

        public String getOdour() {
            return odour;
        }

        public void setOdour(String odour) {
            this.odour = odour;
        }

        public String getPondStatus() {
            return pondStatus;
        }

        public void setPondStatus(String pondStatus) {
            this.pondStatus = pondStatus;
        }

        public String getPondType() {
            return pondType;
        }

        public void setPondType(String pondType) {
            this.pondType = pondType;
        }

        public String getPresentCondition() {
            return presentCondition;
        }

        public void setPresentCondition(String presentCondition) {
            this.presentCondition = presentCondition;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
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

        public boolean isSidewall() {
            return sidewall;
        }

        public void setSidewall(boolean sidewall) {
            this.sidewall = sidewall;
        }

        public String getSidewallType() {
            return sidewallType;
        }

        public void setSidewallType(String sidewallType) {
            this.sidewallType = sidewallType;
        }

        public String getPondcondition() {
            return pondcondition;
        }

        public void setPondcondition(String pondcondition) {
            this.pondcondition = pondcondition;
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
