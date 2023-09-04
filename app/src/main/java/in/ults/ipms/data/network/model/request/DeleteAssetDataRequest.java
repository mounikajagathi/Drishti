package in.ults.ipms.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ults.ipms.data.network.model.base.BaseResponse;

public class DeleteAssetDataRequest extends BaseResponse {


        @Expose
        @SerializedName("buildingasset_tax_details")
        private AssetDeleteDetails taxDetails;

        @Expose
        @SerializedName("buildingasset_floor_prop_area")
        private AssetDeleteDetails floorArea;


        @Expose
        @SerializedName("buildingasset_member_details")
        private AssetDeleteDetails memberDetails;


        @Expose
        @SerializedName("buildingasset_owner_details")
        private AssetDeleteDetails ownerDetails;


        @Expose
        @SerializedName("buildingasset_property_roof_type")
        private AssetDeleteDetails roofTypes;

        @Expose
        @SerializedName("buildingasset_property_floor_type")
        private AssetDeleteDetails floorTypes;

    @Expose
    @SerializedName("buildingasset_member_disability")
    private AssetDeleteDetails disability;

    public AssetDeleteDetails getDisability() {
        return disability;
    }

    public void setDisability(AssetDeleteDetails disability) {
        this.disability = disability;
    }

    public AssetDeleteDetails getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(AssetDeleteDetails taxDetails) {
        this.taxDetails = taxDetails;
    }

    public AssetDeleteDetails getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(AssetDeleteDetails floorArea) {
        this.floorArea = floorArea;
    }

    public AssetDeleteDetails getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(AssetDeleteDetails memberDetails) {
        this.memberDetails = memberDetails;
    }

    public AssetDeleteDetails getOwnerDetails() {
        return ownerDetails;
    }

    public void setOwnerDetails(AssetDeleteDetails ownerDetails) {
        this.ownerDetails = ownerDetails;
    }

    public AssetDeleteDetails getRoofTypes() {
        return roofTypes;
    }

    public void setRoofTypes(AssetDeleteDetails roofTypes) {
        this.roofTypes = roofTypes;
    }

    public AssetDeleteDetails getFloorTypes() {
        return floorTypes;
    }

    public void setFloorTypes(AssetDeleteDetails floorTypes) {
        this.floorTypes = floorTypes;
    }

    public static class AssetDeleteDetails {

        @Expose
        @SerializedName("pk")
        private long pk = -1;

        public AssetDeleteDetails(long pk) {
            this.pk = pk;
        }

        public long getPk() {
            return pk;
        }

        public void setPk(long pk) {
            this.pk = pk;
        }
    }

}
