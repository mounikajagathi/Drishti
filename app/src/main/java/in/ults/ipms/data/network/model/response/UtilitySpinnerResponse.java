package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class UtilitySpinnerResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    private UtilitySpinnerResponse.Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        @Expose
        @SerializedName("drop_down_values")
        private UtilitySpinnerResponse.DropDownValues dropDownValues;

        @Expose
        @SerializedName("layer_details")
        private LayerDetails layerDetails;

        public DropDownValues getDropDownValues() {
            return dropDownValues;
        }

        public LayerDetails getLayerDetails() {
            return layerDetails;
        }
    }


    public static class LayerDetails extends BaseSpinnerData {

        @Expose
        @SerializedName("Bridge")
        private DashboardResponse.LayerCategoryChild bridge;


        @Expose
        @SerializedName("Culvert")
        private DashboardResponse.LayerCategoryChild culvert;


        @Expose
        @SerializedName("Divider")
        private DashboardResponse.LayerCategoryChild divider;


        @Expose
        @SerializedName("Drainage")
        private DashboardResponse.LayerCategoryChild drainage;


        @Expose
        @SerializedName("Parking Area")
        private DashboardResponse.LayerCategoryChild parkingArea;


        @Expose
        @SerializedName("Playground")
        private DashboardResponse.LayerCategoryChild playground;


        @Expose
        @SerializedName("Road")
        private DashboardResponse.LayerCategoryChild road;


        @Expose
        @SerializedName("Road Hump")
        private DashboardResponse.LayerCategoryChild roadHump;


        @Expose
        @SerializedName("Road Junction")
        private DashboardResponse.LayerCategoryChild roadJunction;


        @Expose
        @SerializedName("Road Sign Board")
        private DashboardResponse.LayerCategoryChild roadSignBoard;


        public DashboardResponse.LayerCategoryChild getBridge() {
            return bridge;
        }

        public DashboardResponse.LayerCategoryChild getCulvert() {
            return culvert;
        }

        public DashboardResponse.LayerCategoryChild getDivider() {
            return divider;
        }

        public DashboardResponse.LayerCategoryChild getDrainage() {
            return drainage;
        }

        public DashboardResponse.LayerCategoryChild getParkingArea() {
            return parkingArea;
        }

        public DashboardResponse.LayerCategoryChild getPlayground() {
            return playground;
        }

        public DashboardResponse.LayerCategoryChild getRoad() {
            return road;
        }

        public DashboardResponse.LayerCategoryChild getRoadHump() {
            return roadHump;
        }

        public DashboardResponse.LayerCategoryChild getRoadJunction() {
            return roadJunction;
        }

        public DashboardResponse.LayerCategoryChild getRoadSignBoard() {
            return roadSignBoard;
        }
    }

    public static class DropDownValues extends BaseSpinnerData {


        @Expose
        @SerializedName("maintained_by")
        private ArrayList<MaintainedBy> maintainedBy;

        @Expose
        @SerializedName("road_categories")
        private ArrayList<RoadCategories> roadCategories;

        @Expose
        @SerializedName("road_types")
        private ArrayList<RoadTypes> roadTypes;

        @Expose
        @SerializedName("road_widths")
        private ArrayList<RoadWidths> roadWidths;

        @Expose
        @SerializedName("ward")
        private ArrayList<Ward> ward;

        @Expose
        @SerializedName("signboard_category")
        private ArrayList<SignboardCategory> signboardCategory;

        @Expose
        @SerializedName("drainage_type")
        private ArrayList<DrainageType> drainageType;

        @Expose
        @SerializedName("drainage_material")
        private ArrayList<DrainageMaterial> drainageMaterial;

        @Expose
        @SerializedName("divider_material")
        private ArrayList<DividerMaterial> dividerMaterial;

        @Expose
        @SerializedName("bridge_sub_type")
        private ArrayList<BridgeSubType> bridgeSubType;

        @Expose
        @SerializedName("bridge_material")
        private ArrayList<BridgeMaterial> bridgeMaterial;

        @Expose
        @SerializedName("ground_type")
        private ArrayList<GroundType> groundTypes;

        @Expose
        @SerializedName("parking_type")
        private ArrayList<ParkingType> parkingTypes;


        public ArrayList<MaintainedBy> getMaintainedBy() {
            return maintainedBy;
        }

        public ArrayList<RoadCategories> getRoadCategories() {
            return roadCategories;
        }

        public ArrayList<RoadTypes> getRoadTypes() {
            return roadTypes;
        }

        public ArrayList<RoadWidths> getRoadWidths() {
            return roadWidths;
        }

        public ArrayList<Ward> getWard() {
            return ward;
        }

        public ArrayList<SignboardCategory> getSignboardCategory() {
            return signboardCategory;
        }

        public ArrayList<DrainageType> getDrainageType() {
            return drainageType;
        }

        public ArrayList<DrainageMaterial> getDrainageMaterial() {
            return drainageMaterial;
        }

        public ArrayList<DividerMaterial> getDividerMaterial() {
            return dividerMaterial;
        }

        public ArrayList<BridgeSubType> getBridgeSubType() {
            return bridgeSubType;
        }

        public ArrayList<BridgeMaterial> getBridgeMaterial() {
            return bridgeMaterial;
        }

        public ArrayList<GroundType> getGroundTypes() {
            return groundTypes;
        }

        public ArrayList<ParkingType> getParkingTypes() {
            return parkingTypes;
        }
    }



    public static class MaintainedBy extends BaseSpinnerData {
        @Expose
        @SerializedName("maintained_by")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoadCategories extends BaseSpinnerData {
        @Expose
        @SerializedName("road_category")
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

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Ward extends BaseSpinnerData {
        @Expose
        @SerializedName("ward_name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BridgeMaterial extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BridgeSubType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DividerMaterial extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DrainageMaterial extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DrainageType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class SignboardCategory extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class GroundType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class ParkingType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }



}
