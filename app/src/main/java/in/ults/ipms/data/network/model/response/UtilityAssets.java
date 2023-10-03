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

    public static class BusBay {

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
        private String busBayPlace;
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

        public String getBusBayPlace() {
            return busBayPlace;
        }

        public void setBusBayPlace(String busBayPlace) {
            this.busBayPlace = busBayPlace;
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

    public static class BusStand {

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
        @SerializedName("name")
        @Expose
        private String busStandName;
        @SerializedName("asset_status")
        @Expose
        private long assetStatus;
        @SerializedName("survey_no")
        @Expose
        private long surveyNo;
        @SerializedName("ownership")
        @Expose
        private long ownership;
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

        public String getBusStandName() {
            return busStandName;
        }

        public void setBusStandName(String busStandName) {
            this.busStandName = busStandName;
        }

        public long getSurveyNo() {
            return surveyNo;
        }

        public void setSurveyNo(long surveyNo) {
            this.surveyNo = surveyNo;
        }

        public long getOwnership() {
            return ownership;
        }

        public void setOwnership(long ownership) {
            this.ownership = ownership;
        }

        public long getAssetStatus() {
            return assetStatus;
        }

        public void setAssetStatus(long assetStatus) {
            this.assetStatus = assetStatus;
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

    public static class BusStop {

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

    public static class Canal {

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
        @SerializedName("name")
        @Expose
        private String canalName;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("steet_name")
        @Expose
        private String canalSteetName;
        @SerializedName("subtype")
        @Expose
        private String canalSubType;
        @SerializedName("type")
        @Expose
        private double type;
        @SerializedName("area")
        @Expose
        private double area;
        @SerializedName("class_field")
        @Expose
        private String classField;
        @SerializedName("sidewall")
        @Expose
        private String sidewall;
        @SerializedName("condition")
        @Expose
        private String condition;
        @SerializedName("start_point")
        @Expose
        private String startPoint;
        @SerializedName("end_point")
        @Expose
        private String endPoint;
        @SerializedName("status")
        @Expose
        private String status;
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

        public String getCanalName() {
            return canalName;
        }

        public void setCanalName(String canalName) {
            this.canalName = canalName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCanalSteetName() {
            return canalSteetName;
        }

        public void setCanalSteetName(String canalSteetName) {
            this.canalSteetName = canalSteetName;
        }

        public String getCanalSubType() {
            return canalSubType;
        }

        public void setCanalSubType(String canalSubType) {
            this.canalSubType = canalSubType;
        }

        public double getType() {
            return type;
        }

        public void setType(double type) {
            this.type = type;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getClassField() {
            return classField;
        }

        public void setClassField(String classField) {
            this.classField = classField;
        }

        public String getSidewall() {
            return sidewall;
        }

        public void setSidewall(String sidewall) {
            this.sidewall = sidewall;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(String startPoint) {
            this.startPoint = startPoint;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

    public static class CanalLine {

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
        @SerializedName("steet_name")
        @Expose
        private String canalLineSteetName;
        @SerializedName("subtype")
        @Expose
        private String canalLineSubType;
        @SerializedName("type")
        @Expose
        private double type;
        @SerializedName("area")
        @Expose
        private double area;
        @SerializedName("class_field")
        @Expose
        private String classField;
        @SerializedName("width")
        @Expose
        private String width;
        @SerializedName("length")
        @Expose
        private String length;
        @SerializedName("start_point")
        @Expose
        private String startPoint;
        @SerializedName("end_point")
        @Expose
        private String endPoint;
        @SerializedName("status")
        @Expose
        private String status;
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

        public String getCanalLineSteetName() {
            return canalLineSteetName;
        }

        public void setCanalLineSteetName(String canalLineSteetName) {
            this.canalLineSteetName = canalLineSteetName;
        }

        public String getCanalLineSubType() {
            return canalLineSubType;
        }

        public void setCanalLineSubType(String canalLineSubType) {
            this.canalLineSubType = canalLineSubType;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public double getType() {
            return type;
        }

        public void setType(double type) {
            this.type = type;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getClassField() {
            return classField;
        }

        public void setClassField(String classField) {
            this.classField = classField;
        }

        public String getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(String startPoint) {
            this.startPoint = startPoint;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

    public static class Garbage {

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
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("place")
        @Expose
        private String place;
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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
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

    public static class MobileTower {

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
        @SerializedName("bldg_name")
        @Expose
        private String buildingName;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("ownr_name")
        @Expose
        private String ownerName;
        @SerializedName("owner_address")
        @Expose
        private String ownerAddress;
        @SerializedName("owner_mobile")
        @Expose
        private String ownerMobile;
        @SerializedName("bldg_status")
        @Expose
        private long buildingStatus;
        @SerializedName("bldg_usage")
        @Expose
        private long buildingUsage;
        @SerializedName("new_pro_id")
        @Expose
        private String newPropertyId;
        @SerializedName("old_pro_id")
        @Expose
        private String oldPropertyId;
        @SerializedName("elec_conn")
        @Expose
        private boolean electricConnectivity;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("year_const")
        @Expose
        private int yearOfConstruction;
        @SerializedName("service_provider")
        @Expose
        private String serviceProvider;
        @SerializedName("consumer_no")
        @Expose
        private String consumerNo;
        @SerializedName("road_width")
        @Expose
        private double roadWidth;
        @SerializedName("road_type")
        @Expose
        private String roadType;
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
        @SerializedName("photo2")
        @Expose
        private String photo2;
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

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerAddress() {
            return ownerAddress;
        }

        public void setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
        }

        public String getOwnerMobile() {
            return ownerMobile;
        }

        public void setOwnerMobile(String ownerMobile) {
            this.ownerMobile = ownerMobile;
        }

        public long getBuildingStatus() {
            return buildingStatus;
        }

        public void setBuildingStatus(long buildingStatus) {
            this.buildingStatus = buildingStatus;
        }

        public long getBuildingUsage() {
            return buildingUsage;
        }

        public void setBuildingUsage(long buildingUsage) {
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

        public boolean isElectricConnectivity() {
            return electricConnectivity;
        }

        public void setElectricConnectivity(boolean electricConnectivity) {
            this.electricConnectivity = electricConnectivity;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public int getYearOfConstruction() {
            return yearOfConstruction;
        }

        public void setYearOfConstruction(int yearOfConstruction) {
            this.yearOfConstruction = yearOfConstruction;
        }

        public String getServiceProvider() {
            return serviceProvider;
        }

        public void setServiceProvider(String serviceProvider) {
            this.serviceProvider = serviceProvider;
        }

        public String getConsumerNo() {
            return consumerNo;
        }

        public void setConsumerNo(String consumerNo) {
            this.consumerNo = consumerNo;
        }

        public double getRoadWidth() {
            return roadWidth;
        }

        public void setRoadWidth(double roadWidth) {
            this.roadWidth = roadWidth;
        }

        public String getRoadType() {
            return roadType;
        }

        public void setRoadType(String roadType) {
            this.roadType = roadType;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }
    }

    public static class Park {

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
        @SerializedName("survey_no")
        @Expose
        private String surveyNo;
        @SerializedName("area")
        @Expose
        private double area;
        @SerializedName("name")
        @Expose
        private String parkName;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("park_type")
        @Expose
        private long parkType;
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

        public String getSurveyNo() {
            return surveyNo;
        }

        public void setSurveyNo(String surveyNo) {
            this.surveyNo = surveyNo;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public long getParkType() {
            return parkType;
        }

        public void setParkType(long parkType) {
            this.parkType = parkType;
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

    public static class Stadium {

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
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("area")
        @Expose
        private double area;
        @SerializedName("survey_no")
        @Expose
        private String surveyNo;
        @SerializedName("no_gallery")
        @Expose
        private String noGallery;
        @SerializedName("bathroom")
        @Expose
        private String bathroom;
        @SerializedName("electricit")
        @Expose
        private String electricity;
        @SerializedName("gallery_co")
        @Expose
        private String galleryCoverage;
        @SerializedName("gallery")
        @Expose
        private int gallery;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getSurveyNo() {
            return surveyNo;
        }

        public void setSurveyNo(String surveyNo) {
            this.surveyNo = surveyNo;
        }

        public String getNoGallery() {
            return noGallery;
        }

        public void setNoGallery(String noGallery) {
            this.noGallery = noGallery;
        }

        public String getBathroom() {
            return bathroom;
        }

        public void setBathroom(String bathroom) {
            this.bathroom = bathroom;
        }

        public String getElectricity() {
            return electricity;
        }

        public void setElectricity(String electricity) {
            this.electricity = electricity;
        }

        public String getGalleryCoverage() {
            return galleryCoverage;
        }

        public void setGalleryCoverage(String galleryCoverage) {
            this.galleryCoverage = galleryCoverage;
        }

        public int getGallery() {
            return gallery;
        }

        public void setGallery(int gallery) {
            this.gallery = gallery;
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

    public static class Statue {

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


        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
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

    public static class StreetTap {

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
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("funded_by")
        @Expose
        private String fundedBy;
        @SerializedName("working_status")
        @Expose
        private boolean workingStatus;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getFundedBy() {
            return fundedBy;
        }

        public void setFundedBy(String fundedBy) {
            this.fundedBy = fundedBy;
        }

        public boolean isWorkingStatus() {
            return workingStatus;
        }

        public void setWorkingStatus(boolean workingStatus) {
            this.workingStatus = workingStatus;
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

    public static class Tank {

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
        @SerializedName("tank_owner")
        @Expose
        private String tankOwner;
        @SerializedName("capacity")
        @Expose
        private int capacity;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("tank_type")
        @Expose
        private long tankType;
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

        public String getTankOwner() {
            return tankOwner;
        }

        public void setTankOwner(String tankOwner) {
            this.tankOwner = tankOwner;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public long getTankType() {
            return tankType;
        }

        public void setTankType(long tankType) {
            this.tankType = tankType;
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

    public static class TaxiStand {

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
        @SerializedName("authorised")
        @Expose
        private boolean authorised;
        @SerializedName("capacity")
        @Expose
        private int capacity;
        @SerializedName("authorisation_details")
        @Expose
        private String AuthorisationDetails;
        @SerializedName("geom")
        @Expose
        private GeomPoint geom;
        @SerializedName("photo1")
        @Expose
        private String photo1;
        @SerializedName("parking_type")
        @Expose
        private long parkingType;
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

        public boolean isAuthorised() {
            return authorised;
        }

        public void setAuthorised(boolean authorised) {
            this.authorised = authorised;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public String getAuthorisationDetails() {
            return AuthorisationDetails;
        }

        public void setAuthorisationDetails(String authorisationDetails) {
            AuthorisationDetails = authorisationDetails;
        }

        public long getParkingType() {
            return parkingType;
        }

        public void setParkingType(long parkingType) {
            this.parkingType = parkingType;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
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

    public static class Transformer {

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
        @SerializedName("Transformer_details")
        @Expose
        private String poleNumber;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("application_no")
        @Expose
        private String applicationNo;
        @SerializedName("consumer_no")
        @Expose
        private String consumerNo;
        @SerializedName("single_tube")
        @Expose
        private int singleTube;
        @SerializedName("double_tube")
        @Expose
        private int doubleTube;
        @SerializedName("led")
        @Expose
        private int LED;
        @SerializedName("cfl")
        @Expose
        private int cfl;
        @SerializedName("bulb")
        @Expose
        private int bulb;
        @SerializedName("sodium_vapour")
        @Expose
        private int sodiumVapour;
        @SerializedName("connected_load")
        @Expose
        private int connectedLoad;
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

        public String getPoleNumber() {
            return poleNumber;
        }

        public void setPoleNumber(String poleNumber) {
            this.poleNumber = poleNumber;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getApplicationNo() {
            return applicationNo;
        }

        public void setApplicationNo(String applicationNo) {
            this.applicationNo = applicationNo;
        }

        public String getConsumerNo() {
            return consumerNo;
        }

        public void setConsumerNo(String consumerNo) {
            this.consumerNo = consumerNo;
        }

        public int getSingleTube() {
            return singleTube;
        }

        public void setSingleTube(int singleTube) {
            this.singleTube = singleTube;
        }

        public int getDoubleTube() {
            return doubleTube;
        }

        public void setDoubleTube(int doubleTube) {
            this.doubleTube = doubleTube;
        }

        public int getLED() {
            return LED;
        }

        public void setLED(int LED) {
            this.LED = LED;
        }

        public int getCfl() {
            return cfl;
        }

        public void setCfl(int cfl) {
            this.cfl = cfl;
        }

        public int getBulb() {
            return bulb;
        }

        public void setBulb(int bulb) {
            this.bulb = bulb;
        }

        public int getSodiumVapour() {
            return sodiumVapour;
        }

        public void setSodiumVapour(int sodiumVapour) {
            this.sodiumVapour = sodiumVapour;
        }

        public int getConnectedLoad() {
            return connectedLoad;
        }

        public void setConnectedLoad(int connectedLoad) {
            this.connectedLoad = connectedLoad;
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

    public static class Well {

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
        @SerializedName("well_purpose")
        @Expose
        private String wellPurpose;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("well_cover")
        @Expose
        private String wellCover;
        @SerializedName("near_road")
        @Expose
        private String nearRoad;
        @SerializedName("survey_no")
        @Expose
        private String surveyNo;
        @SerializedName("well_owner")
        @Expose
        private String wellOwner;
        @SerializedName("seasonal")
        @Expose
        private boolean seasonal;
        @SerializedName("well_type")
        @Expose
        private long wellType;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("rewater_availabilitymarks")
        @Expose
        private String reWaterAvailabilityMarks;
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


        public String getSurveyNo() {
            return surveyNo;
        }

        public void setSurveyNo(String surveyNo) {
            this.surveyNo = surveyNo;
        }

        public String getWellPurpose() {
            return wellPurpose;
        }

        public void setWellPurpose(String wellPurpose) {
            this.wellPurpose = wellPurpose;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getWellCover() {
            return wellCover;
        }

        public void setWellCover(String wellCover) {
            this.wellCover = wellCover;
        }

        public String getNearRoad() {
            return nearRoad;
        }

        public void setNearRoad(String nearRoad) {
            this.nearRoad = nearRoad;
        }

        public String getWellOwner() {
            return wellOwner;
        }

        public void setWellOwner(String wellOwner) {
            this.wellOwner = wellOwner;
        }

        public boolean isSeasonal() {
            return seasonal;
        }

        public void setSeasonal(boolean seasonal) {
            this.seasonal = seasonal;
        }

        public long getWellType() {
            return wellType;
        }

        public void setWellType(long wellType) {
            this.wellType = wellType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReWaterAvailabilityMarks() {
            return reWaterAvailabilityMarks;
        }

        public void setReWaterAvailabilityMarks(String reWaterAvailabilityMarks) {
            this.reWaterAvailabilityMarks = reWaterAvailabilityMarks;
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
