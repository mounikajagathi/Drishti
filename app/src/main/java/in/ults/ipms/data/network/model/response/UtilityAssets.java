package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtilityAssets {

    public static class RoadUtility {

        @Expose
        @SerializedName("approved_by")
        private long approvedBy;

        @Expose
        @SerializedName("asset_status")
        private long assetStatus;

        @Expose
        @SerializedName("carriage_width")
        private String carriageWidth;

        @Expose
        @SerializedName("created_at")
        private String createdAt;

        @Expose
        @SerializedName("created_by")
        private long createdBy;

        @Expose
        @SerializedName("district")
        private long district;

        @Expose
        @SerializedName("end_point")
        private String endPoint;

        @Expose
        @SerializedName("footpath_width")
        private String footpathWidth;

        @Expose
        @SerializedName("length")
        private String length;

        @Expose
        @SerializedName("localbody")
        private int localBody;

        @Expose
        @SerializedName("maintained_by")
        private String maintainedBy;

        @Expose
        @SerializedName("photo1")
        private String photo;

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        @Expose
        @SerializedName("rejected_by")
        private long rejectedBy;

        @Expose
        @SerializedName("remarks")
        private String remarks;

        @Expose
        @SerializedName("rightofway_width")
        private String rightOfWayWidth;

        @Expose
        @SerializedName("roadway_width")
        private String roadwayWidth;

        @Expose
        @SerializedName("road_category")
        private String roadCategory;

        @Expose
        @SerializedName("road_material")
        private String roadMaterial;

        @Expose
        @SerializedName("road_name")
        private String roadName;

        @Expose
        @SerializedName("start_point")
        private String startPoint;

        @Expose
        @SerializedName("state")
        private int state;

        @Expose
        @SerializedName("updated_at")
        private String updatedAt;

        @Expose
        @SerializedName("updated_by")
        private long updatedBy;

        @Expose
        @SerializedName("updation_status")
        private String updationStatus;

        @Expose
        @SerializedName("verified_by")
        private long verifiedBy;

        @Expose
        @SerializedName("geom")
        private GeomPolyLine geom;

        public long getApprovedBy() {
            return approvedBy;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public String getCarriageWidth() {
            return carriageWidth;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public long getCreatedBy() {
            return createdBy;
        }

        public long getDistrict() {
            return district;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public String getFootpathWidth() {
            return footpathWidth;
        }

        public String getLength() {
            return length;
        }

        public int getLocalBody() {
            return localBody;
        }

        public String getMaintainedBy() {
            return maintainedBy;
        }

        public String getPhoto() {
            return photo;
        }

        public long getPk() {
            return pk;
        }

        public long getRejectedBy() {
            return rejectedBy;
        }

        public String getRemarks() {
            return remarks;
        }

        public String getRightOfWayWidth() {
            return rightOfWayWidth;
        }

        public String getRoadwayWidth() {
            return roadwayWidth;
        }

        public String getRoadCategory() {
            return roadCategory;
        }

        public String getRoadMaterial() {
            return roadMaterial;
        }

        public String getRoadName() {
            return roadName;
        }

        public String getStartPoint() {
            return startPoint;
        }

        public int getState() {
            return state;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public long getUpdatedBy() {
            return updatedBy;
        }

        public String getUpdationStatus() {
            return updationStatus;
        }

        public long getVerifiedBy() {
            return verifiedBy;
        }

        public void setApprovedBy(long approvedBy) {
            this.approvedBy = approvedBy;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public void setCarriageWidth(String carriageWidth) {
            this.carriageWidth = carriageWidth;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setCreatedBy(long createdBy) {
            this.createdBy = createdBy;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public void setFootpathWidth(String footpathWidth) {
            this.footpathWidth = footpathWidth;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public void setLocalBody(int localBody) {
            this.localBody = localBody;
        }

        public void setMaintainedBy(String maintainedBy) {
            this.maintainedBy = maintainedBy;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

        public void setRejectedBy(long rejectedBy) {
            this.rejectedBy = rejectedBy;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setRightOfWayWidth(String rightOfWayWidth) {
            this.rightOfWayWidth = rightOfWayWidth;
        }

        public void setRoadwayWidth(String roadwayWidth) {
            this.roadwayWidth = roadwayWidth;
        }

        public void setRoadCategory(String roadCategory) {
            this.roadCategory = roadCategory;
        }

        public void setRoadMaterial(String roadMaterial) {
            this.roadMaterial = roadMaterial;
        }

        public void setRoadName(String roadName) {
            this.roadName = roadName;
        }

        public void setStartPoint(String startPoint) {
            this.startPoint = startPoint;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setUpdatedBy(long updatedBy) {
            this.updatedBy = updatedBy;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public void setVerifiedBy(long verifiedBy) {
            this.verifiedBy = verifiedBy;
        }

        public GeomPolyLine getGeom() {
            return geom;
        }

        public void setGeom(GeomPolyLine geom) {
            this.geom = geom;
        }
    }

    public static class Playground {

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
        @SerializedName("updation_status")
        @Expose
        private String updationStatus;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("ground_name")
        @Expose
        private String groundName;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("ground_type")
        @Expose
        private String groundType;
        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("pk")
        @Expose
        private long pk = -1;

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

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getGroundName() {
            return groundName;
        }

        public void setGroundName(String groundName) {
            this.groundName = groundName;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public String getGroundType() {
            return groundType;
        }

        public void setGroundType(String groundType) {
            this.groundType = groundType;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public long getLocalbody() {
            return localbody;
        }

        public void setLocalbody(long localbody) {
            this.localbody = localbody;
        }

        public long getWard() {
            return ward;
        }

        public void setWard(long ward) {
            this.ward = ward;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }
    }

    public static class Drainage {

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
        @SerializedName("drainage_name")
        @Expose
        private String drainageName;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("drainage_material")
        @Expose
        private String drainageMaterial;
        @SerializedName("drainage_type")
        @Expose
        private String drainageType;
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
        @SerializedName("geom")
        @Expose
        private GeomPolyLine geom;
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

        public String getDrainageName() {
            return drainageName;
        }

        public void setDrainageName(String drainageName) {
            this.drainageName = drainageName;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getDrainageMaterial() {
            return drainageMaterial;
        }

        public void setDrainageMaterial(String drainageMaterial) {
            this.drainageMaterial = drainageMaterial;
        }

        public String getDrainageType() {
            return drainageType;
        }

        public void setDrainageType(String drainageType) {
            this.drainageType = drainageType;
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

        public GeomPolyLine getGeom() {
            return geom;
        }

        public void setGeom(GeomPolyLine geom) {
            this.geom = geom;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }
    }

    public static class ParkingArea {

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
        @SerializedName("updation_status")
        @Expose
        private String updationStatus;
        @SerializedName("capacity")
        @Expose
        private long capacity;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("parking_type")
        @Expose
        private String parkingType;
        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("pk")
        @Expose
        private long pk = -1;

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

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public long getCapacity() {
            return capacity;
        }

        public void setCapacity(long capacity) {
            this.capacity = capacity;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public String getParkingType() {
            return parkingType;
        }

        public void setParkingType(String parkingType) {
            this.parkingType = parkingType;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public long getLocalbody() {
            return localbody;
        }

        public void setLocalbody(long localbody) {
            this.localbody = localbody;
        }

        public long getWard() {
            return ward;
        }

        public void setWard(long ward) {
            this.ward = ward;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

    }

    public static class RoadSignboard {

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
        @SerializedName("updation_status")
        @Expose
        private String updationStatus;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("pk")
        @Expose
        private long pk = -1;

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

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public long getLocalbody() {
            return localbody;
        }

        public void setLocalbody(long localbody) {
            this.localbody = localbody;
        }

        public long getWard() {
            return ward;
        }

        public void setWard(long ward) {
            this.ward = ward;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

    }

    public static class RoadJunction {

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
        @SerializedName("updation_status")
        @Expose
        private String updationStatus;
        @SerializedName("junction_name")
        @Expose
        private String junctionName;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("pk")
        @Expose
        private long pk = -1;

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

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getJunctionName() {
            return junctionName;
        }

        public void setJunctionName(String junctionName) {
            this.junctionName = junctionName;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public long getLocalbody() {
            return localbody;
        }

        public void setLocalbody(long localbody) {
            this.localbody = localbody;
        }

        public long getWard() {
            return ward;
        }

        public void setWard(long ward) {
            this.ward = ward;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

    }

    public static class RoadHump {

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
        @SerializedName("updation_status")
        @Expose
        private String updationStatus;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("road_name")
        @Expose
        private String roadName;
        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("length")
        @Expose
        private long length;
        @SerializedName("Width")
        @Expose
        private long width;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("localbody")
        @Expose
        private long localbody;
        @SerializedName("ward")
        @Expose
        private long ward;
        @SerializedName("district")
        @Expose
        private long district;
        @SerializedName("state")
        @Expose
        private long state;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("pk")
        @Expose
        private long pk = -1;

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

        public String getUpdationStatus() {
            return updationStatus;
        }

        public void setUpdationStatus(String updationStatus) {
            this.updationStatus = updationStatus;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getRoadName() {
            return roadName;
        }

        public void setRoadName(String roadName) {
            this.roadName = roadName;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
        }

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }

        public long getWidth() {
            return width;
        }

        public void setWidth(long width) {
            this.width = width;
        }

        public GeomPoint getGeom() {
            return geom;
        }

        public void setGeom(GeomPoint geom) {
            this.geom = geom;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public long getLocalbody() {
            return localbody;
        }

        public void setLocalbody(long localbody) {
            this.localbody = localbody;
        }

        public long getWard() {
            return ward;
        }

        public void setWard(long ward) {
            this.ward = ward;
        }

        public long getDistrict() {
            return district;
        }

        public void setDistrict(long district) {
            this.district = district;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }

    }

    public static class Divider {

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
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("length")
        @Expose
        private String length;
        @SerializedName("divider_material")
        @Expose
        private String dividerMaterial;
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

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getDividerMaterial() {
            return dividerMaterial;
        }

        public void setDividerMaterial(String dividerMaterial) {
            this.dividerMaterial = dividerMaterial;
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

    public static class Culvert {

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
        @SerializedName("culvert_name")
        @Expose
        private String culvertName;
        @SerializedName("place")
        @Expose
        private String place;
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

        public String getCulvertName() {
            return culvertName;
        }

        public void setCulvertName(String culvertName) {
            this.culvertName = culvertName;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
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

    public static class Bridge {

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
        @SerializedName("bridge_name")
        @Expose
        private String bridgeName;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("bridge_material")
        @Expose
        private String bridgeMaterial;
        @SerializedName("bridge_sub_type")
        @Expose
        private String bridgeSubType;
        @SerializedName("width")
        @Expose
        private String width;
        @SerializedName("bridge_width")
        @Expose
        private String bridgeWidth;
        @SerializedName("maintained_by")
        @Expose
        private long maintainedBy;
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

        public String getBridgeName() {
            return bridgeName;
        }

        public void setBridgeName(String bridgeName) {
            this.bridgeName = bridgeName;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getBridgeMaterial() {
            return bridgeMaterial;
        }

        public void setBridgeMaterial(String bridgeMaterial) {
            this.bridgeMaterial = bridgeMaterial;
        }

        public String getBridgeSubType() {
            return bridgeSubType;
        }

        public void setBridgeSubType(String bridgeSubType) {
            this.bridgeSubType = bridgeSubType;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getBridgeWidth() {
            return bridgeWidth;
        }

        public void setBridgeWidth(String bridgeWidth) {
            this.bridgeWidth = bridgeWidth;
        }

        public long getMaintainedBy() {
            return maintainedBy;
        }

        public void setMaintainedBy(long maintainedBy) {
            this.maintainedBy = maintainedBy;
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
